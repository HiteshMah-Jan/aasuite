package ui.cashier.rule;

import java.util.List;

import ui.cashier.OldStudent;
import util.DBClient;
import util.PanelUtil;
import bean.Student;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentPlan;
import bean.reference.GradeLevel;

public class CashierRuleAssessStudent {
	OldStudent old;
    static List gradeList;
	
	public CashierRuleAssessStudent(OldStudent old) {
		this.old = old;
        if (gradeList == null) {
            gradeList = GradeLevel.extractCacheListBeans(GradeLevel.class);
        }
	}
	
    public void assessStudent() {
//    	Note: In order to assess student, SRU must generate the curriculum first
        String schoolYear = old.getUseYear();
        if (old.pnlAssessment.view.list != null && !old.pnlAssessment.view.list.isEmpty()) {
            PanelUtil.showMessage(old, "Student already assessed.");
            return;
        }
        try {
            Student stud = (Student) old.pnlStudentList.getBean();
            GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), stud.gradeLevel);
            lvl = (GradeLevel) PanelUtil.showPromptMessage(old, "Select gradelevel", "Grade Level", gradeList, lvl);
            if (lvl != null) {
//                check if already assess in the chosen gradeLevel
                if (!canStudentAssessToLevel(stud, lvl)) return;
                String plan = PanelUtil.showPromptDefaultMessage(old, "To assess student, please select the plan", "A").toString();
                PaymentPlan p = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a WHERE a.code='"+plan.toUpperCase()+"'");
                if (p==null) {
                	PanelUtil.showError(old, "Plan not found");
                	return;
                }
                stud.assessStudent(schoolYear, lvl, plan.toUpperCase(), 0);
            }
            old.reloadPayments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean canStudentAssessToLevel(Student stud, GradeLevel lvl) {
        PaymentEnrollment lastEnroll = null;
        List<PaymentEnrollment> lst = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.paidBy="+stud.personId+" AND a.paymentFor LIKE '%-MISC' ORDER BY a.seq");
        for (PaymentEnrollment e: lst) {
            if (e.paymentFor.startsWith(lvl.code)) {
                PanelUtil.showMessage(old, "Student already assess in the grade level in school year ["+e.schoolYear+"].");
                return false;
            }
            lastEnroll = e;
        }
        if (lastEnroll!=null) {
            String grd = lastEnroll.paymentFor;
            String gcode = lvl.code.substring(0,1);
            System.out.println("GCODE == "+gcode);
            if (grd.startsWith("H") && "KPG".contains(gcode)) {
                if (!PanelUtil.showPrompt(old, "Student already enrolled in High School ["+lastEnroll.schoolYear+"], are you sure you want to continue?")) return false;
            }
            if (grd.startsWith("G") && "KP".contains(gcode)) {
                if (!PanelUtil.showPrompt(old, "Student already enrolled in Grade School ["+lastEnroll.schoolYear+"], are you sure you want to continue?")) return false;
            }
        }
        return true;
    }
}
