/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Schedule;
import bean.person.StudentSubject;
import bean.reference.SubjectGradingCriteria;
import component.PopupPanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import template.screen.TransactionPanel;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class EnrollmentSubjectFacultyExt_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnPutGradeDetail1".equals(comp.getName())) {
            putGradeDetail("1");
        }
        else if ("btnPutGradeDetail2".equals(comp.getName())) {
            putGradeDetail("2");
        }
        else if ("btnPutGradeDetail3".equals(comp.getName())) {
            putGradeDetail("3");
        }
        else if ("btnPutGradeDetail4".equals(comp.getName())) {
            putGradeDetail("4");
        }
        else if ("btnCalculateGrades".equals(comp.getName())) {
            calculateGrade();
        }
    }

    private void calculateGrade() {
//        //computation for all records
//        Schedule sc = (Schedule) TransactionPanel.getCurrentPanel().getBean();
//        //get all the records under this schedule
//        List<StudentSubject> lst = sc.selectListCache("SELECT a FROM StudentSubject a WHERE a.scheduleId="+sc.seq);
//        for (StudentSubject sub : lst) {
//            System.out.println("SUBJECT "+sub.subject+" STUDENT="+sub.getStudent());
//            List<EnrolledSubjectDetailGrading> tmp = sub.list("SELECT a FROM EnrolledSubjectDetailGrading a WHERE a.enrollmentSubjectId="+sub.getSeq());
//            sub.grade1 = calculate(tmp, "1");
//            sub.grade2 = calculate(tmp, "2");
//            sub.grade3 = calculate(tmp, "3");
//            sub.grade4 = calculate(tmp, "4");
////            sub.save();
//        }
//        DBClient.persistBean((List)lst);
//        sc.clearCache();
//        this.redisplayRecord();
    }

    private void calculate(List<StudentSubject> lst, String period) {
//        List<EnrolledSubjectDetailGrading> tmp = new ArrayList<EnrolledSubjectDetailGrading>();
//        for (EnrolledSubjectDetailGrading det : lst) {
//            if (period.equals(det.period)) {
//                tmp.add(det);
//            }
//        }
//        double totalGrade = 0;
//        for (EnrolledSubjectDetailGrading det : tmp) {
//            //calculate now
//            double percentage = det.percentage;
//            double grade = det.grade;
//            det.gradeShare = (int)(grade * (percentage/100));
//            System.out.println("GRADE for "+det.name+" = "+grade+":"+percentage+" -> "+det.gradeShare);
//            totalGrade += det.gradeShare;
//        }
//        DBClient.persistBean((List)tmp);
//        return (int)totalGrade;
    }
    
    private void putGradeDetail(String period) {
//        //put the subject grade detail first before showing the popup
//        EnrollmentSubject subject = (EnrollmentSubject) this.getBean();
//        if (subject==null || subject.isEmptyKey()) {
//            showError("Please select a record.");
//            return;
//        }
//        //do not get from cache
//        boolean b = subject.recordExist("SELECT a FROM EnrolledSubjectDetailGrading a WHERE a.enrollmentSubjectId="+subject.getSeq()+" AND a.period="+period);
//        if (!b) {
//            List<SubjectDetailGrading> lst = subject.selectListCache("SELECT a FROM SubjectDetailGrading a WHERE a.subjectCode='"+subject.subject+"'");
//            if (lst!=null) {
//                for (SubjectDetailGrading sub : lst) {
//                    EnrolledSubjectDetailGrading e = new EnrolledSubjectDetailGrading();
//                    e.enrollmentSubjectId = subject.getSeq();
//                    e.period = period;
//                    e.name = sub.name;
//                    e.percentage = sub.percentage;
//                    e.subjectCode = sub.subjectCode;
//                    e.save();
//                }
//            }
//        }
//        PopupPanel pnl = PopupPanel.getPanel("EnrolledSubjectDetailGrading", "SELECT a FROM EnrolledSubjectDetailGrading a WHERE a.enrollmentSubjectId="+subject.getSeq()+" AND a.period="+period);
//        pnl.pnl.hideSearchCriteria();
//        pnl.pack();
//        pnl.setVisible(true);
    }
}

