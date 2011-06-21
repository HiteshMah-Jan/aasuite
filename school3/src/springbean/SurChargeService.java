/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import util.DBClient;
import util.DataUtil;
import util.DateUtil;
import util.Log;
import util.PerfUtil;
import bean.admin.AppConfig;
import bean.sales.Payment;
import bean.service.Services;

/**
 * @author alex
 */
public class SurChargeService extends TimerTask implements IService {
	private static boolean serviceRunning = false;
	private static int hour = 6;
	private static boolean isRunning;
	
	public int getHour() {
		return hour;
	}

	public void run() {
		Log.out("SURCHARGE PROCESS...",DateUtil.getHour()," - ",constants.Constants.useDate);		
		if (DateUtil.getHour()==getHour()) {
			constants.Constants.useDate = new Date();
			process();
		}
		else {
			Log.out("SURCHARGE RUN ONLY AT ",getHour()," am");		
		}
	}

	public static void main(String[] args) {
		AAAConfig.getServerInstance();
		new SurChargeService().process();
	}

	public ReturnStruct callService(ParamStruct param) {
		if (serviceRunning) {
			Log.out("SURCHARGE SERVICE IS ALREADY RUNNING."," - ",constants.Constants.useDate);
			return null;
		}
		if (AAAConfig.server) {
			Log.out("SURCHARGE SERVICE CALLED."," - ",constants.Constants.useDate);
////			every hour
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(this, 1000*10, 1000 * 60 * 60);
			process();
			serviceRunning = true;
		}
		return null;
	}
	
	private void process2() {
//		Log.out("SURCHARGE PROCESS...",DateUtil.getIntTime());
	}
	
	protected void process() {
		if (isRunning) {
			Log.out("SURCHARGE PROCESS STILL RUNNING...",DateUtil.getIntTime()," - ",constants.Constants.useDate);
			return;
		}
		isRunning = true;
		Log.out("SURCHARGE PROCESS...",DateUtil.getIntTime()," - ",constants.Constants.useDate);
		PerfUtil perf = new PerfUtil("Clean Up Surcharge Resources");
		perf.start();
		AAAConfig.getInstance();
		Date d = constants.Constants.useDate;
		d = DateUtil.addDay(d, -5);
		String sql = "SELECT a FROM PaymentEnrollment a WHERE a.dueDate < '"
			+ DateUtil.formatDateToSql(d)
			+ "' AND a.paid=false AND a.balance >= 10";
		List<Payment> lst = DBClient.getList(sql, 0, 100000);
		perf.printSpan();
		for (Payment p : lst) {
			try {
				if (p.paymentDate==null) {
					p.paymentDate = p.dueDate;
				}
				Date tmp = p.paymentDate;
				if (p.paymentDate.getTime()<p.dueDate.getTime()) {
					tmp = p.dueDate;
				}
				int count = DateUtil.countDaySpan(tmp, constants.Constants.useDate);
				double tot = p.balance;
				double sur = tot * AppConfig.getSurchargeValue() / 100;
				p.surcharge = p.doubleVal(sur * count);
				if (p.surcharge < 0) {
					p.surcharge = 0;
				}
		    	p.surchargeBalance = p.surcharge - p.discountSurcharge - p.surchargePaid;
				p.overallAmountDue = p.overallBalance + p.surchargeBalance;
				p.save();
				Log.out(DateUtil.formatDate(tmp),"\t",count,"\t",p.paymentFor,"\t\t",DataUtil.getMoneyFormat(p.balance),"\t\t",DataUtil.getMoneyFormat(p.surcharge),"\t\t",DataUtil.getMoneyFormat(p.surchargePaid),"\t\t",DataUtil.getMoneyFormat(p.surchargeBalance),"\t",p.payer);
			}
			catch (Exception e) {
			}
		}
		DBClient.runSQLNative("UPDATE Payment SET surcharge=0, surchargeBalance=0, overallBalance=0 WHERE balance<=10");
		try {
			Services.logRun(this.getClass().getSimpleName(), lst.size());
		}
		catch (Exception e) {
		}
		lst.clear();
		lst = null;
		perf.printSpanComplete();
		isRunning = false;
	}
}
