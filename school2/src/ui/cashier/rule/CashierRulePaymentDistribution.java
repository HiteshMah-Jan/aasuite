package ui.cashier.rule;

import java.util.*;

import constants.UserInfo;

import ui.cashier.AcceptCheckForm;
import ui.cashier.OldStudent;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;

import bean.accounting.CashierDailyBooklet;
import bean.accounting.Invoice;
import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;

public class CashierRulePaymentDistribution {
    CashierDailyBooklet booklet;

    List<PaymentEnrollment> allAssess;
	OldStudent old;
	double amount;
	String discountReason;
	double discount;
	double discountSurcharge;
	boolean paycheck;
	
	public CashierRulePaymentDistribution(OldStudent old, double amount, String discountReason, double discount, double surchargeDiscount, boolean paycheck) {
		this.old = old;
		this.paycheck = paycheck;
		this.amount = amount;
		this.discountReason = discountReason;
		this.discount = discount;
		this.discountSurcharge = surchargeDiscount;
		allAssess = old.pnlAssessment.view.list;
		booklet = CashierDailyBooklet.getMyBooklet();
		if (booklet==null) booklet = new CashierDailyBooklet();
	}

	public void processDistibution() throws Exception {
		double markedAmount = amount;
		double totalOutstanding = 0;
		for (PaymentEnrollment p:allAssess) {
			totalOutstanding += p.surchargeBalance;
		}
		processSurcharge();
		for (PaymentEnrollment p:allAssess) {
			p.outstandingSurcharge = p.surcharge = p.surchargeBalance = p.surchargePaid = 0;
		}
		System.out.println("OUT = "+amount);
		if (totalOutstanding > markedAmount) {
//			clear all surcharges
			PaymentEnrollment lastp = allAssess.get(allAssess.size()-1);
			lastp.outstandingSurcharge = totalOutstanding - markedAmount;
			if (lastp.outstandingSurcharge < 0) {
				lastp.outstandingSurcharge = 0;
			}
			System.out.println("OUT = "+lastp.outstandingSurcharge);
		}
		processMisc();
		processFee();
		putOR(normOR, "N");
		DBClient.persistBean((List)allAssess);
		for (PaymentEnrollment p:allAssess) {
			updateFWB(p);
			break;
		}
//		mergeSameCodeAndOR();
        old.drawer.updateDrawer();
	}
	
	public void processSurcharge() throws Exception {
		//		for surcharge
		for (PaymentEnrollment p:allAssess) {
			if (p.paymentFor.contains("FWB")) continue;
			processSurcharge(p);
		}
	}
	
	public void processMisc() throws Exception {
		//		for misc
		for (PaymentEnrollment p:allAssess) {
			if (p.paymentFor.contains("FWB")) continue;
			processMisc(p);
		}
		putOR(miscOR, "A");
	}

	public void processFee() throws Exception {
		//		for tuition
		for (PaymentEnrollment p:allAssess) {
			if (p.paymentFor.contains("FWB")) continue;
			processTuition(p);
		}
	}
	
	protected void mergeSameCodeAndOR() {
//		merge records only for surcharge
		Map<String, PaymentEnrollment> map = new TreeMap();
		PaymentEnrollment p = (PaymentEnrollment) allAssess.get(0);
		List<PaymentEnrollment> lst = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.paidBy="+p.paidBy+" AND a.schoolYear='"+p.schoolYear+"' AND a.paid=true");
		for (PaymentEnrollment e:lst) {
			if (e.paymentFor.contains("MISC")) {
//				only surcharge was paid here
				if (e.amountPaid==0) {
					e.paymentFor = e.paymentFor.replace("MISC", e.plan+"1");
				}
			}
			if (map.containsKey(e.paymentFor+e.orNumber)) {
				PaymentEnrollment tmp = map.get(e.paymentFor+e.orNumber);
				tmp.amountPaid += e.amountPaid;
				tmp.surchargePaid += e.surchargePaid;
				tmp.discount += e.discount;
				tmp.discountSurcharge += e.discountSurcharge;
				e.delete();
			}
			else {
				map.put(e.paymentFor+e.orNumber, e);
			}
		}
		
		List newlist = new ArrayList();
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			newlist.add(map.get(iter.next()));
		}
		DBClient.persistBean(newlist);
	}
	
	protected void updateFWB(PaymentEnrollment p) {
		PaymentEnrollment fwb = (PaymentEnrollment) DBClient.getFirstRecord("SELECT a FROM PaymentEnrollment a WHERE a.paidBy="+p.paidBy+" AND a.paymentFor='FWB' AND a.description LIKE '%"+p.schoolYear+"%'");
		if (fwb!=null && !fwb.isEmptyKey()) {
			double amount = 0;
			List<PaymentEnrollment> lst = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.paidBy="+fwb.paidBy+" AND a.schoolYear='"+p.schoolYear+"'");
			for (PaymentEnrollment tmp : lst) {
				amount += tmp.overallAmountDue;
			}
			fwb.amount = fwb.overallAmountDue = fwb.balance = fwb.overallBalance = amount;
			fwb.save();
		}
	}
	
	protected double processTuition(PaymentEnrollment p) throws Exception {
		if (p.overallBalance>0 && !p.paymentFor.contains("MISC") && amount>0) {
			PaymentEnrollment newp = new PaymentEnrollment();
			if (amount>p.overallBalance) {
				if (discount>p.overallBalance) {
					newp.discount = p.overallBalance;
					discount -= p.overallBalance;
				}
				else {
					newp.discount = discount;
					discount = 0;
				}
				newp.amountPaid = p.overallBalance;
				amount -= p.overallBalance;
				p.overallBalance = 0;
			}
			else {
				newp.discount = discount;
				discount = 0;
				newp.amountPaid = amount;
				amount = 0;
				p.overallBalance -= newp.amountPaid;
			}
			newp.orNumber = getTuitionOR();
			newp.orType =  "N";
			newp.orAmount =  newp.amountPaid-newp.discount;
			newp.paid = true;
			newp.orDate = newp.paymentDate = constants.Constants.useDate;
			newp.paymentFor = p.paymentFor;
			newp.recordId = p.recordId;
			newp.paidBy = p.paidBy;
			newp.cashier = UserInfo.getUserName();
			newp.discountReason = discountReason;
			newp.schoolYear = p.schoolYear;
			newp.plan = p.plan;
			newp.save();
		}
		return amount;
	}

	protected double processMisc(PaymentEnrollment p) throws Exception {
		if (p.overallBalance>0 && p.paymentFor.contains("MISC") && amount>0) {
			PaymentEnrollment newp = new PaymentEnrollment();
			newp.paid = true;
			if (amount>p.overallBalance) {
				newp.amountPaid = p.overallBalance;
				amount -= p.overallBalance;
				p.overallBalance = 0;
			}
			else {
				newp.amountPaid = amount;
				amount = 0;
				p.overallBalance -= newp.amountPaid;
			}
			newp.orNumber = getMiscOR();
			newp.orType = "A";
			newp.orAmount =  newp.amountPaid-newp.discount;
			newp.paid = true;
			newp.orDate = newp.paymentDate = constants.Constants.useDate;
			newp.paymentFor = p.paymentFor;
			newp.recordId = p.recordId;
			newp.paidBy = p.paidBy;
			newp.cashier = UserInfo.getUserName();
			newp.discountReason = discountReason;
			newp.schoolYear = p.schoolYear;
			newp.plan = p.plan;
			newp.save();
		}
		return amount;
	}
	
	protected double processSurcharge(PaymentEnrollment p) throws Exception {
		if (p.surchargeBalance>0 && amount>0) {
			PaymentEnrollment newp = new PaymentEnrollment();
			if (amount>p.surchargeBalance) {
				if (discountSurcharge>p.surchargeBalance) {
					newp.discountSurcharge = p.surchargeBalance;
					discountSurcharge -= p.surchargeBalance;
				}
				else {
					newp.discountSurcharge = discountSurcharge;
					discountSurcharge = 0;
				}
				newp.surchargePaid = p.surchargeBalance;
				amount -= p.surchargeBalance;
			}
			else {
				newp.discountSurcharge = discountSurcharge;
				discountSurcharge = 0;
				newp.surchargePaid = amount;
				amount = 0;
			}
			newp.paid = true;
			newp.orNumber = getTuitionOR();
			newp.orType =  "N";
			newp.amountPaid =  0;
			newp.orAmount =  newp.surchargePaid;
			newp.paid = true;
			newp.orDate = newp.paymentDate = constants.Constants.useDate;
			newp.paymentFor = p.paymentFor;
			newp.recordId = p.recordId;
			newp.paidBy = p.paidBy;
			newp.cashier = UserInfo.getUserName();
			newp.discountReason = discountReason;
			newp.schoolYear = p.schoolYear;
			newp.plan = p.plan;
			newp.save();
		}
		return amount;
	}
	
	String miscOR;
	String normOR;
	protected String getMiscOR() throws Exception {
		if (miscOR==null) {
			miscOR = PanelUtil.showPromptDefaultMessage(old, "Print OR for MISC?", booklet.extractNextOR("A") + "");
			if (miscOR==null) {
				throw new Exception(constants.Constants.CANCELLED);
			}
			else {
//				check if out of range
				int i = Integer.parseInt(miscOR);
				if (i<booklet.startMIS || i>booklet.endMIS) {
					if (!PanelUtil.showPrompt(old, "OR number out of series, would you like to continue?")) {
						throw new Exception("CANCELLED OR OUT OF RANGE");
					}
				}
//				check or if exist in invoice
				Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber="+miscOR+" AND a.orType='A'");
				if (inv!=null && !inv.isEmptyKey()) {
					PanelUtil.showError(old, "OR# ["+miscOR+"] is already used, please check");
					throw new Exception("OR already used.");
				}
			}
		}
		return miscOR;
	}
	
	protected String getTuitionOR() throws Exception {
		if (normOR==null) {
			normOR = PanelUtil.showPromptDefaultMessage(old, "Print OR", booklet.extractNextOR("N") + "");
			if (normOR==null) {
				throw new Exception(constants.Constants.CANCELLED);
			}
			else {
//				check if out of range
				int i = Integer.parseInt(normOR);
				if (i<booklet.startOR || i>booklet.endOR) {
					if (!PanelUtil.showPrompt(old, "OR number out of series, would you like to continue?")) {
						throw new Exception("CANCELLED OR OUT OF RANGE");
					}
				}
//				check or if exist in invoice
				Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber="+normOR+" AND a.orType='N'");
				if (inv!=null && !inv.isEmptyKey()) {
					PanelUtil.showError(old, "OR# ["+normOR+"] is already used by cashier ["+inv.cashier+"], please check");
					throw new Exception("OR already used.");
				}
			}
		}
		return normOR;
	}

	private void putOR(String or, String orType) {
		if (or==null || or.isEmpty()) return;
		List<PaymentEnrollment> all = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.orNumber="+or+" AND a.orType='"+orType+"'");
		double totalOR = 0;
		for (PaymentEnrollment e:all) {
			totalOR += e.orAmount;
		}
    	PaymentEnrollment p = all.get(0);
    	p.orAmount = DataUtil.getMoneyFormat(totalOR);
    	if (p.orAmount>0.1) {
    		Payment check = null;
    		if (paycheck) {
                boolean b = PanelUtil.showPrompt(old, "Are there any checks?\nNote: This cannot cancel transaction.");
                if (b) {
                    check = AcceptCheckForm.acceptCheck(p.extractCustomer(), p.orAmount, discount);
                }
    		}
            p.printOR("OR Printing", check);
    	}
    }
}
