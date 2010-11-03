package rule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import springbean.CalculateGradeService;
import template.screen.AbstractChildTemplatePanel;
import util.DBClient;
import util.Log;
import util.PanelUtil;
import bean.Schedule;
import bean.Student;
import bean.admin.AppConfig;
import bean.person.StudentSubject;
import bean.reference.GradeLevel;
import bean.reference.Section;
import constants.UserInfo;

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
		else if ("btnTestGrading".equals(comp.getName())) {
				testGrading();
		}
	}

	private void testGrading() {
		if (UserInfo.loginUser.isSuperAAA() && AppConfig.isShowTestButton()) {
			if (showPrompt("Put test grade for all students?")) {
				List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.section IS NOT NULL ORDER BY a.gradeLevel, a.section, a.subject",0,5000);
				for (Schedule sched:lst) {
//					ThreadPoolUtil.execute(new RunTestGrading(sched));
					PanelUtil.showWaitFrame("Generating grades for ", sched.gradeLevel, " - ", sched.section);
					new RunTestGrading(sched).run();
					PanelUtil.hideWaitFrame();
				}
			}
			else {
				AbstractChildTemplatePanel tab = this.panel.getTabs().get(0);
				List<StudentSubject> subjects = tab.list;
				if (subjects == null || subjects.size() == 0) {
					generateTask();
				}
				List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
				
				subjects = tabs.get(0).list;
				for (int i=0; i<subjects.size(); i++) {
					StudentSubject s = subjects.get(i);
					s.grade1 = getRandomDouble(60+i, 99);
					s.grade2 = getRandomDouble(60+i, 99);
					s.grade3 = getRandomDouble(60+i, 99);
					s.grade4 = getRandomDouble(60+i, 99);
				}
				saveAllGrades(1);
				saveAllGrades(2);
				saveAllGrades(3);
				saveAllGrades(4);
			}
			this.tbl.updateUI();
		}
	}

	private class RunTestGrading implements Runnable {
		Schedule sched;
		private RunTestGrading(Schedule s) {
			this.sched = s;
		}
		
		@Override
		public void run() {
			List<StudentSubject> subjects = DBClient.getList("SELECT a FROM StudentSubject a WHERE a.scheduleId=",String.valueOf(sched.seq)," AND a.section='",sched.section,"' AND a.subject='",sched.subject,"' AND a.schoolYear='",AppConfig.getSchoolYear(),"'");
			if (subjects != null && !subjects.isEmpty()) {
				for (int i=0; i<subjects.size(); i++) {
					StudentSubject s = subjects.get(i);
					if (s.grade1<60) s.grade1 = getRandomDouble(60+i, 99);
					if (s.grade2<60)s.grade2 = getRandomDouble(60+i, 99);
					if (s.grade3<60)s.grade3 = getRandomDouble(60+i, 99);
					if (s.grade4<60)s.grade4 = getRandomDouble(60+i, 99);
				}
				CalculateGradeService.calculateGrade(1, sched, subjects);
				CalculateGradeService.calculateGrade(2, sched, subjects);
				CalculateGradeService.calculateGrade(3, sched, subjects);
				CalculateGradeService.calculateGrade(4, sched, subjects);
			}
		}
	}
	
	private void generateTask() {
        if (UserInfo.loginUser.isSuperAAA()) {
        	if (showPrompt("Generate grading for all student?")) {
            	List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a, Section b WHERE a.section=b.code", 0, 10000);
            	for (Schedule s:lst) {
                    try {
                    	generateTask(s);
                    }
                    catch (Exception e) {
                    	e.printStackTrace();
                    }
            	}
            	return;
        	}
        }
		Schedule t = (Schedule) this.getBean();
		generateTask(t);
	}
	
	private void generateTask(Schedule t) {
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
				if (sub.grade3 > 61) {
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

	protected void saveAllGrades(int quarter) {
//		check for schoolyear
		if (!UserInfo.getUseYear().equals(AppConfig.getSchoolYear())) {
			showError("This school year is not current.[",UserInfo.getUseYear(),"]");
			return;
		}
		Schedule bean = (Schedule) this.getBean();
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		
		List<StudentSubject> subjects = tab.list;
		List<StudentSubject> tlist = new ArrayList<StudentSubject>();
		for (StudentSubject s : subjects) {
			if (quarter == 2) {
				if (s.grade2 >= 75 && s.grade1 < 75) {
					s.grade1 = 75.0;
				}
			}
			tlist.add(s);
		}
		
		CalculateGradeService.calculateGrade(quarter, bean, tlist);
		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

}
