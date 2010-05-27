/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cashier;

import bean.Student;
import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentPlan;
import bean.admin.AppConfig;
import bean.extension.Student201Ext;
import bean.extension.Student201Ext;
import bean.reference.GradeLevel;
import constants.UserInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import springbean.SchoolDefaultProcess;
import ui.cashier.rule.CashierRuleAssessStudent;
import ui.cashier.rule.CashierRuleChangePlan;
import ui.cashier.rule.CashierRulePaymentDistribution;
import util.DBClient;
import util.PanelUtil;
import util.DataUtil;
import util.BeanUtil;

/**
 *
 * @author alex
 */
public class CashierRule {
    OldStudent old;
    boolean retainDiscountAndSurcharge;

    public CashierRule(OldStudent old) {
        this.old = old;
        retainDiscountAndSurcharge = AppConfig.isRetainDiscountAndSurcharge();
    }

    public void runAssess() {
    	new CashierRuleAssessStudent(old).assessStudent();
    }

    public void runAcceptPayment() {
        try {
            if (old.pnlAssessment.view.list == null || old.pnlAssessment.view.list.isEmpty()) {
                PanelUtil.showMessage(old, "Student not yet assessed.");
                return;
            }
            String amount = PanelUtil.showPromptDefaultMessage(old, "Please type amount.", BeanUtil.concat(old.getCandidateAmount(),""));
            if (amount == null) {
                return;
            }
            double damount = PanelUtil.getDoubleValue(amount);
            damount = DataUtil.getMoneyFormat(damount);
            double unpaid = DataUtil.getMoneyFormat(getTotalUnpaid());
            //.5 can be negligible
            if (damount>unpaid+.5) {
                PanelUtil.showMessage(old, "More than unpaid amount [",unpaid,"], please type another.");
                runAcceptPayment();
                return;
            }
            if (damount<=0) {
                PanelUtil.showMessage(old, "Cannot accept 0 or less or non numeric amount.");
                runAcceptPayment();
                return;
            }
            Payment p = old.nextPayment(null);

            String discountReason = getDiscountReason(p);
            double discount = getDiscountAmount(p, discountReason);
            double discountSurcharge = getSurchargeDiscountAmount(p);

            Payment check = null;
            Student stud = (Student) old.pnlStudentList.getBean();
            if ("NOT ALLOWED".equals(stud.paymentStatus)) {
                if (!PanelUtil.showPrompt(old, "Student cannot pay in check, would you like to continue?")) {
                    return;
                }
                new CashierRulePaymentDistribution(old, damount, discountReason, discount, discountSurcharge, false).processDistibution();
            }
            else {
                new CashierRulePaymentDistribution(old, damount, discountReason, discount, discountSurcharge, true).processDistibution();
            }
            old.reloadPayments();
            s.updateFWB(p);
//            check status of student if enrolled already
            Student st = (Student) old.pnlStudentList.getBean();
            if (!"ENROLLED".equals(st.status)) {
                List<PaymentEnrollment> lst = old.pnlAssessment.view.list;
                for (PaymentEnrollment ptmp:lst) {
                	if (ptmp.paymentFor.contains("MISC")) {
                		if (ptmp.overallAmountDue==0) {
//                		student can be officially enrolled
                			st.status = "ENROLLED";
                			st.changeValue("status", "ENROLLED");
                			st.changeValue("status", "ENROLLED");
                			st.save();
                		}
                	}
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            old.reloadPayments();
        }
    }

    void runStudent201Ext() {
      PanelUtil.popupBeanTemplate(Student201Ext.class, "Student 201", true);
	}


    private String getDiscountReason(Payment pay) {
    	if (pay.overallBalance==0) {
    		PanelUtil.showError(old, "Selected payment does not have balance.");
    		return null;
    	}

        String discount = (String) PanelUtil.showPromptMessage(old, "Discount Type", "Discount Type", AppConfig.getDiscountTypes(), "EARLY PAYMENT");
    	if ("SURCHARGE".equals(discount) && pay.surcharge==0) {
    		PanelUtil.showError(old, "There is no surcharge in this payment.");
    		return null;
    	}
    	return discount;
    }
    
    private double getDiscountAmount(Payment pay, String discount) throws Exception {
    	if (discount==null) return 0;
        double amount = 0;
		if (discount.equals("EARLY PAYMENT")) {
			List lst = new ArrayList();
			lst.add(1500);
			lst.add(1000);
			lst.add(500);
			lst.add(200);
			try {
		        amount = PanelUtil.getDoubleValue(PanelUtil.showPromptMessage(old, "Discount Amount", "1500", lst, "1500").toString());
			}
			catch (Exception e) {
				throw e;
			}
		}
		else {
	        amount = PanelUtil.showPromptMessage(old, "Discount Amount", 0);
		}
        if (amount==-1) {
        	throw new Exception("CANCEL DISCOUNT.");
        }
    	if ("SURCHARGE".equals(discount)) {
    		if (pay.surcharge<amount) {
    			if (PanelUtil.showPrompt(old, "Discount is more than the surcharge amount. Do you want to continue?")) {
    				return amount;
    			}
    			else {
        			throw new Exception("More than surcharge amount discount.");
    			}
    		}
    		pay.discountSurcharge = amount;
    	}
    	else {
    		if (pay.overallBalance<amount) {
    			if (PanelUtil.showPrompt(old, "Discount is more than the balance amount. Do you want to continue?")) {
    				return amount;
    			}
    			else {
        			throw new Exception("More than balance amount discount.");
    			}
    		}
    	}
    	return amount;
    }
    
    private double getSurchargeDiscountAmount(Payment pay) throws Exception {
    	if (getTotalSurcharge()<=0) return 0;
    	if (!PanelUtil.showPrompt(old, "With surcharge discount")) {
    		return 0;
    	}
        double amount = 0;
		try {
	        amount = PanelUtil.getDoubleValue(PanelUtil.showPromptDefaultMessage(old, "Surcharge Discount Amount", BeanUtil.concat(getTotalSurcharge(),"")).toString());
		}
		catch (Exception e) {
			throw e;
		}
        if (amount==-1) {
        	throw new Exception("CANCEL DISCOUNT.");
        }
		if (amount>getTotalSurcharge()) {
			if (PanelUtil.showPrompt(old, "Discount is more than the surcharge amount. Do you want to continue?")) {
				return amount;
			}
			else {
    			throw new Exception("More than surcharge amount discount.");
			}
		}
    	return amount;
    }

    public void runChangePlan() {
    	double totalDue = 0;
    	List<PaymentEnrollment> lst = old.pnlAssessment.view.list;
    	for (PaymentEnrollment p:lst) {
    		totalDue += p.overallAmountDue;
    	}
    	if (totalDue>0) {
        	new CashierRuleChangePlan(old).changePlan();
    	}
    	else {
    		PanelUtil.showMessage(old, "Already paid, cannot reassess.");
    	}
    }
    
    static SchoolDefaultProcess s = new SchoolDefaultProcess();

    private double getTotalUnpaid() {
        double total = 0;
        List<PaymentEnrollment> lst = old.pnlAssessment.view.list;
        for (int i = 0; i < lst.size(); i++) {
            PaymentEnrollment p = lst.get(i);
            if (!p.paid || p.orNumber==null || p.orNumber.trim().isEmpty()) {
                total += p.overallBalance+p.surchargeBalance;
            }
        }
        return total;
    }
    
    private double getTotalSurcharge() {
    	double d = 0;
    	List<PaymentEnrollment> lst = old.pnlAssessment.view.list;
    	for (PaymentEnrollment p:lst) {
    		d += p.surchargeBalance;
    	}
    	return DataUtil.getMoneyFormat(d);
    }
}
