package ui.cashier.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.Enrollment;
import bean.Student;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentPlan;
import bean.reference.GradeLevel;
import springbean.SchoolDefaultProcess;
import ui.cashier.OldStudent;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

public class CashierRuleChangePlan {
	OldStudent old;
	SchoolDefaultProcess proc = new SchoolDefaultProcess();
    static List gradeList;
	
	public CashierRuleChangePlan(OldStudent old) {
		this.old = old;
        if (gradeList == null) {
            gradeList = GradeLevel.extractCacheListBeans(GradeLevel.class);
        }
	}
	
	public void changePlan() {
        try {
        	PaymentEnrollment pay = (PaymentEnrollment) old.pnlPayment.getBean();
        	boolean hasPayment = pay!=null && !pay.isEmptyKey();
            Student stTmp = (Student) old.pnlStudentList.getBean();
            String schoolYear = old.getUseYear();
            
            GradeLevel lvl = null;
            if (!hasPayment) {
                lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), stTmp.gradeLevel);
                lvl = (GradeLevel) PanelUtil.showPromptMessage(old, "Select gradelevel", "Grade Level", gradeList, lvl);
            }

            String plan = PanelUtil.showPromptDefaultMessage(old, "Please select the plan", "A").toString();
            if (plan==null) {
            	throw new Exception("CANCEL CHANGE PLAN");
            }
            PaymentPlan p = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a WHERE a.code='",plan.toUpperCase(),"'");
            if (p==null) {
            	PanelUtil.showError(old, "Plan not found");
            	return;
            }
//          reassess student
            Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=",stTmp.seq," AND a.schoolYear='",schoolYear,"'");
            if (lvl!=null) {
            	e.gradeLevel = lvl.code;
            }
            else {
            	if (e.paymentPlanType.equalsIgnoreCase(plan.toUpperCase())) {
            		PanelUtil.showMessage(old, "Same plan used.");
            		return;
            	}
            	else {
            		if ("SMQ".contains(plan.toUpperCase())) {
                		boolean b = PanelUtil.showPrompt(old, "Changing plan to Semestral, Quarter, Monthly will need manual adjustment in amount due.\nWould you like to continue?");
                		if (!b) return;
            		}
            	}
            }
            e.paymentPlanType = plan.toUpperCase();
            if (!hasPayment) {
                e.save();
                DBClient.runSQL("DELETE FROM Payment a WHERE a.paidBy=",stTmp.seq," AND a.schoolYear='",schoolYear,"' AND a.paid=false");
                proc.createPayment(e);
            }
            else {
                e.save();
                DBClient.runSQL("DELETE FROM Payment a WHERE a.paidBy=",stTmp.seq," AND a.schoolYear='",schoolYear,"' AND a.paid=false");
                proc.createPayment(e);
                updateOldPayment(e);
            }
            old.reloadPayments();
        } catch (Exception ex) {
            Logger.getLogger(OldStudent.class.getName()).log(Level.SEVERE, null, ex);
        }    
	}
	
	private void updateOldPayment(Enrollment e) {
        PaymentPlan pl = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a WHERE a.code='",e.paymentPlanType,"' AND a.gradeLevel='",e.gradeLevel,"'");
        List<PaymentEnrollment> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM PaymentEnrollment a WHERE a.paidBy=",e.studentId," AND a.schoolYear='",e.schoolYear,"' AND a.paid=true"));
        for (PaymentEnrollment pay:lst) {
        	if (pay.paymentFor.contains("FWB")) {
        		continue;
        	}
        	if (pay.paymentFor.contains("MISC")) {
        		continue;
        	}
        	if (pay.oldPaymentFor==null) pay.oldPaymentFor = pay.paymentFor;
        	if (e.paymentPlanType.equals("A")) {
            	pay.paymentFor = BeanUtil.concat(e.gradeLevel,"-TFEE");
        	}
        	else {
            	pay.paymentFor = BeanUtil.concat(e.gradeLevel,"-",e.paymentPlanType+1);
        	}
        	if (pay.overallAmountPaid > pl.amount1) {
        		double overall = pay.overallAmountPaid;
            	pay.overallAmountPaid = pay.amountPaid = pl.amount1;
            	pay.save();

//            	create another entry
            	pay.paymentFor = BeanUtil.concat(e.gradeLevel,"-",e.paymentPlanType+2);
            	pay.seq = null;
            	pay.overallAmountPaid = pay.amountPaid = overall-pl.amount1;
            	pay.save();
        	}
        	else {
            	pay.save();
        	}
        }

        lst = DBClient.getList(BeanUtil.concat("SELECT a FROM PaymentEnrollment a WHERE a.paidBy=",e.studentId," AND a.schoolYear='",e.schoolYear,"' AND a.paid=false"));
        for (PaymentEnrollment pay:lst) {
        	pay.save();
        }
	}
}
