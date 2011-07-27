package ui.cashier.rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ui.cashier.AcceptCheckForm;
import ui.cashier.OldStudent;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.accounting.CashierDailyBooklet;
import bean.accounting.Invoice;
import bean.accounting.EnrollmentAR;
import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;

public class ORExtractorUtil {
	public static String getMiscOR(OldStudent old, CashierDailyBooklet booklet) throws Exception {
		String miscOR = null;
		if (miscOR==null) {
			miscOR = PanelUtil.showPromptDefaultMessage(old, "Print OR for MISC?", BeanUtil.concat(booklet.extractNextOR("A")));
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
				Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber=",miscOR," AND a.orType='A'");
				if (inv!=null && !inv.isEmptyKey()) {
					PanelUtil.showError(old, "OR# [",miscOR,"] is already used, please check");
					throw new Exception("OR already used.");
				}
			}
		}
		return miscOR;
	}
	
	public static String getTuitionOR(OldStudent old, CashierDailyBooklet booklet) throws Exception {
		String normOR = null;
		if (normOR==null) {
			normOR = PanelUtil.showPromptDefaultMessage(old, "Print OR", BeanUtil.concat(booklet.extractNextOR("N")));
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
				Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber=",normOR," AND a.orType='N'");
				if (inv!=null && !inv.isEmptyKey()) {
					PanelUtil.showError(old, "OR# [",normOR,"] is already used by cashier [",inv.cashier,"], please check");
					throw new Exception("OR already used.");
				}
			}
		}
		return normOR;
	}

	public static void putOR(OldStudent old, String or, String orType, boolean paycheck, double discount, List<PaymentEnrollment> all) {
		if (or==null || or.isEmpty()) return;
		double totalOR = 0;
		PaymentEnrollment p = null;
		List<Payment> allSameOR = new ArrayList<Payment>();
		for (PaymentEnrollment e:all) {
			if (e.orNumber != null && e.orNumber.equals(or) && e.paid) {
				totalOR += e.orAmount;
				p = e;
				allSameOR.add(p);
			}
		}
		if (totalOR > 0) {
	    	double orAmount = DataUtil.getMoneyFormat(totalOR);
	    	if (orAmount>0.1) {
	    		Payment check = null;
	    		if (paycheck) {
	                boolean b = PanelUtil.showPrompt(old, "Are there any checks?\nNote: This cannot cancel transaction.");
	                if (b) {
	                    check = AcceptCheckForm.acceptCheck(p.extractCustomer(), orAmount, discount);
	                }
	    		}
//	            p.printOR("OR Printing", check, allSameOR);
	    	}
		}
    }
	
	@SuppressWarnings("unchecked")
	public static void mergeSameOR(String or) {
//		merge records only for surcharge
		Map<String, PaymentEnrollment> map = new TreeMap();
		List<PaymentEnrollment> lst = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.orNumber=",or," AND a.paid=true");
		for (PaymentEnrollment e:lst) {
			if (e.paymentFor.contains("MISC")) {
//				only surcharge was paid here
				if (e.amountPaid==0) {
					e.paymentFor = e.paymentFor.replace("MISC", BeanUtil.concat(e.plan,"1"));
				}
			}
			if (map.containsKey(e.paymentFor+e.orNumber)) {
				PaymentEnrollment tmp = map.get(BeanUtil.concat(e.paymentFor,e.orNumber));
				tmp.amountPaid += e.amountPaid;
				tmp.surchargePaid += e.surchargePaid;
				tmp.discount += e.discount;
				tmp.discountSurcharge += e.discountSurcharge;
				e.delete();
			}
			else {
				map.put(BeanUtil.concat(e.paymentFor,e.orNumber), e);
			}
		}
		
		List newlist = new ArrayList();
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			newlist.add(map.get(iter.next()));
		}
		DBClient.persistBean(newlist);
	}
}
