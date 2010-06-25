package rule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import constants.UserInfo;

import springbean.CalculateGradeService;
import template.screen.AbstractChildTemplatePanel;
import util.DBClient;
import util.Log;
import util.PanelUtil;
import bean.Schedule;
import bean.Student;
import bean.admin.AppConfig;
import bean.extension.ScheduleManualGradingExt;
import bean.person.StudentSubject;
import bean.reference.GradeLevel;
import bean.reference.Section;

public class ScheduleManualGradingExt_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnGenerate".equals(comp.getName())) {
			generateTask();
		}
		if ("btnSaveGrade1".equals(comp.getName())) {
			saveAllGrades(1);
		}
		else if ("btnSaveGrade2".equals(comp.getName())) {
			saveAllGrades(2);
		}
		else if ("btnSaveGrade3".equals(comp.getName())) {
			saveAllGrades(3);
		}
		else if ("btnSaveGrade4".equals(comp.getName())) {
			saveAllGrades(4);
		}
	}

	private void generateTask() {
		Schedule t = (Schedule) this.getBean();
		List<Student> lstStud = DBClient.getList("SELECT a FROM Student a WHERE a.section='",t.section,"'");
		Section sec = (Section) Section.extractObject(Section.class.getSimpleName(), t.section);
		GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), sec.gradeLevel);
		t.boysAndGirls += "";
		List all = new ArrayList();
		for (Student stud:lstStud) {
//			select subject of student
			StudentSubject sub = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId=",stud.personId," AND a.subject='",t.subject,"'");
			if (sub==null) {
				sub = new StudentSubject();
				sub.facultyId = t.facultyId;
				sub.faculty = t.faculty;
				sub.section = t.section;
				sub.scheduleId = t.seq;
				sub.studentId = stud.personId;
				sub.subject = t.subject;
				sub.course = stud.course;
				sub.gradeLevel = stud.gradeLevel;
				sub.schoolYear = AppConfig.getSchoolYear();
				sub.save();
			}
			else {
				if (sub.finalRating > 60) {
					Log.info(sub.studentName, "-", sub.subject, " already has grade ", sub.finalRating);
					continue;
				}
				sub.facultyId = t.facultyId;
				sub.faculty = t.faculty;
				sub.section = t.section;
				sub.scheduleId = t.seq;
				sub.schoolYear = AppConfig.getSchoolYear();
			}
			if (t.boysAndGirls.contains("BOY")) {
				if ("MALE".equalsIgnoreCase(stud.gender)) {
					all.add(sub);
				}
			}
			else if (t.boysAndGirls.contains("GIRL")) {
				if ("FEMALE".equalsIgnoreCase(stud.gender)) {
					all.add(sub);
				}
			}
			else {
				all.add(sub);
			}
		}
		DBClient.persistBean(all);
		
		PanelUtil.hideWaitFrame();
		redisplayRecord();
	}

	private void saveAllGrades(int quarter) {
//		check for schoolyear
		if (!UserInfo.getUseYear().equals(AppConfig.getSchoolYear())) {
			showError("This is school year is not current.[",UserInfo.getUseYear(),"]");
			return;
		}
		ScheduleManualGradingExt bean = (ScheduleManualGradingExt) this.getBean();
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		
		List<StudentSubject> subjects = tab.list;
		List<StudentSubject> tlist = new ArrayList<StudentSubject>();
		for (StudentSubject s : subjects) {
			tlist.add(s);
		}
		
		CalculateGradeService.calculateGrade(quarter, bean, tlist);
		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

}
