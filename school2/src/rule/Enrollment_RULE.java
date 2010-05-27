/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import springbean.SchoolDefaultProcess;
import template.screen.TablePopup;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;
import bean.Enrollment;
import bean.Schedule;
import bean.reference.Section;
import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public class Enrollment_RULE extends BusinessRuleWrapper {
    springbean.SchoolDefaultProcess sc = new SchoolDefaultProcess();

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnViewPossibleSched".equals(comp.getName())) {
            Enrollment enrollment = (Enrollment) getBean();
            List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.blockOrSection='",enrollment.section,"'");
            TablePopup.showRecords(BeanUtil.concat("Possible Schedule For ",enrollment.section), lst, Schedule.class, 
                    "subject", "schedDay", "blockOrSection", "schedTime", "schedTimeEnd", "faculty");
        }
        else if ("btnCreateSchedules".equals(comp.getName())) {
            Enrollment enrollment = (Enrollment) getBean();
            enrollment.save();
            sc.updateSchedules(enrollment);
            redisplayRecord();
        }
        else if (comp.getName().equals("btnCalculateQ1")) {
        	calculateQ1();
        }
        else if (comp.getName().equals("btnCalculateQ2")) {
        	calculateQ2();
        }
        else if (comp.getName().equals("btnCalculateQ3")) {
        	calculateQ3();
        }
        else if (comp.getName().equals("btnCalculateQ4")) {
        	calculateQ4();
        }
        else if (comp.getName().equals("btnCalculateFinal")) {
        	calculateFinal();
        }
        else if (comp.getName().equals("btnDeleteDuplicate")) {
        	PanelUtil.showError(comp, "To clean the duplicate assessment. Please use the Student Info and remove duplicate entries from the Enrollment sub panel.");
//        	deleteDuplicate();
        }
    }

	protected void deleteDuplicate() {
	    Map<String, Section> map = new HashMap();
		PanelUtil.showWaitFrame("Please wait, deleting duplicate entries.");
		List<Enrollment> lst = this.panel.getRecordList();
		for (Enrollment e:lst) {
			int count = (int) DBClient.getSingleColumnDouble("SELECT COUNT(*) FROM Payment WHERE recordId=",e.seq);
			if (count==0) {
//				check if really duplicate or just no payment
				count = (int) DBClient.getSingleColumnDouble("SELECT COUNT(*) FROM Enrollment WHERE gradeLevel='",e.gradeLevel,"' AND studentId=",e.studentId);
				if (count>=2) {
					e.delete();
				}
				else {
//					check if gradelevel is valid per section
					Section sec = map.get(e.section);
					if (sec==null) {
						sec = (Section) DBClient.getFirstRecord("SELECT a FROM Section a WHERE a.code='",e.section,"'");
						map.put(e.section, sec);
					}
					if (!(BeanUtil.concat(e.gradeLevel,"")).equals(sec.gradeLevel)) {
						e.delete();
					}
				}
			}
		}
		PanelUtil.hideWaitFrame();
	}

	private void calculateQ1() {
//		String schoolYear = PanelUtil.showPromptDefaultMessage(null, "Type school year", UserInfo.getUseYear());
//		DBClient.runSQLNative("update enrollment set gpa1=( " +
//				"select sum(grade1*subject.unit)/sum(subject.unit) " +
//				"from studentsubject, subject where studentsubject.subject=subject.code AND " +
//				"studentsubject.gradelevel=enrollment.gradelevel AND " +
//				"studentsubject.studentId=enrollment.studentId AND grade1>0) " +
//				"where enrollment.schoolYear='",schoolYear,"'");
//		Enrollment.setupRank(schoolYear, 1);
//		refreshRecords();
	}
	private void calculateQ2() {
//		String schoolYear = PanelUtil.showPromptDefaultMessage(null, "Type school year", UserInfo.getUseYear());
//		DBClient.runSQLNative("update enrollment set gpa2=( " +
//				"select sum(grade2*subject.unit)/sum(subject.unit) " +
//				"from studentsubject, subject where studentsubject.subject=subject.code AND " +
//				"studentsubject.gradelevel=enrollment.gradelevel AND " +
//				"studentsubject.studentId=enrollment.studentId AND grade2>0) " +
//				"where enrollment.schoolYear='",schoolYear,"'");
//		Enrollment.setupRank(schoolYear, 2);
//		refreshRecords();
	}
	private void calculateQ3() {
//		String schoolYear = PanelUtil.showPromptDefaultMessage(null, "Type school year", UserInfo.getUseYear());
//		DBClient.runSQLNative("update enrollment set gpa3=( " +
//				"select sum(grade3*subject.unit)/sum(subject.unit) " +
//				"from studentsubject, subject where studentsubject.subject=subject.code AND " +
//				"studentsubject.gradelevel=enrollment.gradelevel AND " +
//				"studentsubject.studentId=enrollment.studentId AND grade3>0) " +
//				"where enrollment.schoolYear='",schoolYear,"'");
//		Enrollment.setupRank(schoolYear, 3);
//		refreshRecords();
	}
	private void calculateQ4() {
//		String schoolYear = PanelUtil.showPromptDefaultMessage(null, "Type school year", UserInfo.getUseYear());
//		DBClient.runSQLNative("update enrollment set gpa4=( " +
//				"select sum(grade4*subject.unit)/sum(subject.unit) " +
//				"from studentsubject, subject where studentsubject.subject=subject.code AND " +
//				"studentsubject.gradelevel=enrollment.gradelevel AND " +
//				"studentsubject.studentId=enrollment.studentId AND grade4>0) " +
//				"where enrollment.schoolYear='",schoolYear,"'");
//		Enrollment.setupRank(schoolYear, 4);
//		refreshRecords();
	}
	private void calculateFinal() {
//		String schoolYear = PanelUtil.showPromptDefaultMessage(null, "Type school year", UserInfo.getUseYear());
//		DBClient.runSQLNative("update enrollment set gpaFinal=( " +
//				"select sum(unitShareFinal) from studentsubject where " +
//				"studentsubject.gradelevel LIKE enrollment.gradelevel AND " +
//				"studentsubject.studentId=enrollment.studentId) " +
//				"where enrollment.schoolYear='",schoolYear,"'");
//		Enrollment.setupRank(schoolYear, 5);
//		refreshRecords();
	}
	
}
