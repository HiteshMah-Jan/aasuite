/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;

import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import ui.action.StudentAction;
import util.BeanUtil;
import util.DBClient;
import util.PerfUtil;
import bean.Student;
import bean.admin.AppConfig;
import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public class Student_RULE extends Person_RULE {
	boolean b = AppConfig.isShowCollege();

	@Override
    public void onLoad() {
        getComponent("studentNumber").setEnabled(false);
        if (!UserInfo.canChangeStudentNumber()) {
        	if (getComponent("btnstudentNumber") != null) {
                getComponent("btnstudentNumber").setEnabled(false);
        	}
        }
        if (!b) {
        	this.hideAllTabs();
        	this.displayTab(0);
        	this.displayTab(1);
        	this.displayTab(2);
        	this.displayTab(3);
        	this.displayTab(4);
        	this.displayTab(5);
        }
    }

    @Override
    public void runFocusLost(JComponent comp) {
        super.runFocusLost(comp);
        if (comp.getName().equals("course")) {
            changeToSectionList();
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnChangeGrade")) {
            changeGrade();
        }
        else if (comp.getName().equals("btnGenerateCurriculum")) {
            generateCurriculum();
        }
        else if (comp.getName().equals("btnPrintTOR")) {
            printTOR();
        }
        else if (comp.getName().equals("btnReAdmit")) {
            // create admission record, create payment
            //set student status as returning
        }
//        else if (comp.getName().equals("btnAssessNoEnrollment")) {
//            Student stud = (Student) this.getBean();
//            if (stud==null) {
//                PanelUtil.showMessage(usedComp, "Please select a student.");
//                return;
//            }
//            new SchoolDefaultProcess().autoAssessNoEnrollment(stud);
//        }
        else if (comp.getName().equals("btnstudentNumber")) {
            getComponent("studentNumber").setEnabled(true);
            getComponent("studentNumber").requestFocus();
            ((JTextField)getComponent("studentNumber")).selectAll();
        }
    }

    private void changeGrade() {
        Student stud = (Student) getBean();
        StudentAction.changeGrade(stud);
        redisplayRecord();
    }

    private void generateCurriculum() {
		PerfUtil p = new PerfUtil("Generate curriculum subjects.");
		p.start();
        Student stud = (Student) getBean();
        if (UserInfo.loginUser.isSuperAAA() && AppConfig.isShowTestButton()) {
        	if (showPrompt("Generate Curriculum for all student?")) {
        		List<Student> lst = DBClient.getList("SELECT a FROM Student a, Section b WHERE a.status='ENROLLED' AND a.section=b.code ORDER BY a.lastName", 0, 10000);
        		for (Student s:lst) {
                    new springbean.SchoolDefaultProcess().createAllSubjects(s);
        		}
        	}
        	else {
                new springbean.SchoolDefaultProcess().createAllSubjects(stud);
        	}
        }
        else {
            new springbean.SchoolDefaultProcess().createAllSubjects(stud);
        }
		p.printSpanComplete();
        this.redisplayRecord();
    }

    private String getSchoolYear() {
        int yr = util.DateUtil.getYear();
        return BeanUtil.concat(yr,"-",(yr+1));
    }

    @Override
    public void onNewRecord() {
        setValue("schoolYear", getSchoolYear());
    }
    
    @Override
    public boolean beforeSave(AbstractIBean bean) {
        Student stud = (Student) getBean();
        if (stud.studentNumber==null || stud.studentNumber.isEmpty()) {
            stud.setDefaultStudentNumber();
            setValue("studentNumber", stud.studentNumber);
        }
        return true;
    }

    @Override
    public void onChangeRecord() {
        Student stud = (Student) getBean();
        if (stud==null || stud.isEmptyKey()) {
            getComponent("course").setEnabled(true);
        }
        else {
            getComponent("course").setEnabled(true);
            if (stud.course==null || stud.course.isEmpty()) {
                getComponent("course").setEnabled(true);
            }
        }
        changeToSectionList();
        changeCurriculumDisplay();
    }

    public void changeCurriculumDisplay() {
//    	this.hideAllTabs();
//    	this.displayTab(0);
//    	this.displayTab(1);
//    	this.displayTab(2);
//    	this.displayTab(3);
//    	this.displayTab(4);
    }
    
    private void changeToSectionList() {
//        Student stud = (Student) getBean();
//        JComboBoxPallete cbo = (JComboBoxPallete) getComponent("toSection");
//        List lstCbo = cbo.getList();
//        if (stud==null || stud.course==null) {
//            if (lstCbo!=null && lstCbo.size()>0) lstCbo.clear();
//            return;
//        }
//        List lst = stud.selectListCache("SELECT a FROM Section a, GradeLevel b WHERE a.gradeLevel=b.gradeLevel AND b.course='",stud.course,"'");
//        PanelUtil.setListData(lstCbo, lst);
//        cbo.updateUI();
    }

    private void printTOR() {
        Student stud = (Student) getBean();
//        if (stud.course.startsWith("H")) {
//            AbstractReportTemplate.getInstance().showReportFromFileTemplate("TORHS", stud.personId);
//        }
//        else if (stud.course.startsWith("G")) {
//            AbstractReportTemplate.getInstance().showReportFromFileTemplate("TORElem", stud.personId);
//        }
//        else {
//            AbstractReportTemplate.getInstance().showReportFromFileTemplate("TORCollege", stud.personId);
//                   }
     AbstractReportTemplate.getInstance().showReportFromFileTemplate("TOR", stud.personId);
    
    }
}
