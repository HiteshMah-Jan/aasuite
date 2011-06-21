/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;
import bean.Enrollment;
import bean.Student;

import javax.swing.*;

import util.PanelUtil;
import util.DBClient;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import springbean.SchoolDefaultProcess;

/**
 *
 * @author Entokwaa
 */
public class PayrollExt_RULE extends Payroll_RULE {
    Student stud;

    @Override
    public void runOnClick(JComponent comp) {
        super.runOnClick(comp);
//        if ("btnAssessNoEnrollment".equals(comp.getName())) {
//            assessNoEnrollment();
//        }
    }

//    private void assessNoEnrollment() {
//        Payment p = (Payment) this.getBean();
//        if (p==null) {
//            PanelUtil.showMessage(usedComp, "Please select at least 1 payment record.");
//            return;
//        }
//        if (stud==null) {
//            stud = (Student) DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.personId=",p.paidBy);
//        }
//        new SchoolDefaultProcess().autoAssessNoEnrollment(stud);
//    }
}
