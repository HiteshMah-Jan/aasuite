package rule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import service.util.AbstractIBean;
import springbean.CalculateGradeService;
import springbean.GradingProcess;
import springbean.MultiSavingGradeService;
import springbean.SchoolDefaultProcess;
import template.screen.AbstractChildTemplatePanel;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.Schedule;
import bean.Student;
import bean.admin.AppConfig;
import bean.person.FacultyGradingTask;
import bean.person.StudentSubject;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.GradeLevel;
import bean.reference.LockGrading;
import bean.reference.Section;
import bean.reference.SubjectGradingCriteria;
import constants.UserInfo;

public class FacultyGradingTask_RULE extends BusinessRuleWrapper {
	static List<SubjectGradingCriteria> lst;
	static boolean alreadyRun;

//	@Override
	public boolean beforeSave(AbstractIBean bean) {
		PanelUtil.showError(null, "Please use the Save All Score button.");
		return false;
	}

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnGenerateAllComponents".equals(comp.getName())) {
			generateAllComponents();
		}
		else if ("btnGenerateTask".equals(comp.getName())) {
			generateTask();
		}
		else if ("btnSaveAllScore1".equals(comp.getName())) {
			saveAllScore(1);
		}
		else if ("btnSaveAllScore2".equals(comp.getName())) {
			saveAllScore(2);
		}
		else if ("btnSaveAllScore3".equals(comp.getName())) {
			saveAllScore(3);
		}
		else if ("btnSaveAllScore4".equals(comp.getName())) {
			saveAllScore(4);
		}
		else if ("btnSaveAllSection".equals(comp.getName())) {
			saveAllSection();
		}
		else if ("btnDeleteComponent".equals(comp.getName())) {
//			Common2View.mainView.showBeanPanel(Subject.class.getSimpleName());
			deleteComponent();
		}
		else if ("btnRemoveBoys".equals(comp.getName())) {
			removeBoys();
		}
		else if ("btnRemoveGirls".equals(comp.getName())) {
			removeGirls();
		}
		else if ("btnRankAll1".equals(comp.getName())) {
			rankAll(1);
		}
		else if ("btnRankAll2".equals(comp.getName())) {
			rankAll(2);
		}
		else if ("btnRankAll3".equals(comp.getName())) {
			rankAll(3);
		}
		else if ("btnRankAll4".equals(comp.getName())) {
			rankAll(4);
		}
	}

	private void saveAllSection() {
		int quarter = (int) PanelUtil.showPromptMessage(null, "Type quarter 3-4", 4);
		if (quarter!=3 && quarter!=4) {
			PanelUtil.showMessage(null, "This system is configured for 3rd and 4th quarter only.");
			return;
		}
		if (AppConfig.isTrimester() && quarter==4) {
			PanelUtil.showMessage(null, "This system is configured for trimester, you cannot use this button.");
			return;
		}
		String level = PanelUtil.showPromptDefaultMessage(null, "Please type grade level", "G1");
		List<String> l = new ArrayList<String>();
		List<FacultyGradingTask> filtered = new ArrayList<FacultyGradingTask>();
		List<FacultyGradingTask> alltask = DBClient.getList(BeanUtil.concat("SELECT a FROM FacultyGradingTask a WHERE a.gradeLevel='",level,"' ORDER BY a.faculty, a.section, a.subject"),0,5000);
		if (alltask == null || alltask.isEmpty()) {
			PanelUtil.showError(null, "Task not found.");
			return;
		}
		for (FacultyGradingTask task:alltask) {
			if (task != null && task.weight > 0) {
				String s = BeanUtil.concat(task.faculty,task.gradeLevel,task.section,task.subject);
				if (l.contains(s)) {
					continue;
				}
				l.add(s);
				filtered.add(task);
				Log.out(task.faculty,"\t",task.gradeLevel,"\t",task.section,"\t",task.subject);
			}
		}
		Log.out("Total task count = ",filtered.size());
		for (FacultyGradingTask task:filtered) {
			if (task != null && task.weight > 0) {
				Log.out("RECALCULATE");
				ThreadPoolUtil.execute(new newThread(task, quarter));
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

	private static class newThread implements Runnable {
		FacultyGradingTask task;
		int quarter;
		private newThread(FacultyGradingTask task, int quarter) {
			this.task = task;
			this.quarter = quarter;
		}
		
		@Override
		public void run() {
			Log.out("RECALCULATE");
			List<StudentSubjectDetailGrading> tlist = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId=",task.seq),0,500);
			if (tlist != null && tlist.size() > 0) {
				PanelUtil.showWaitFrame("Recalculating grades [",task.faculty,"-",task.section,"-",task.subject,"-",task.component,"], please wait...");
				MultiSavingGradeService.calculateGrade(quarter, task, tlist);
				PanelUtil.hideWaitFrame();
			}
		}
		
	}
	private void rankAll(int i) {
		GradingProcess.rankAll(i);		
	}

	protected void removeGirls() {
//		if (!UserInfo.canDeleteStudent()) {
//			PanelUtil.showError(null, "No DELETE STUDENT duty code, you cannot use this button.");
//			return;
//		}
		boolean b = PanelUtil.showPrompt(null, "Are you sure you want to remove all girls for this component?");
		if (b) {
			b = PanelUtil.showPrompt(null, "Some students already have grade for 1st quarter. Continue to remove girls?");
			if (b) {
				FacultyGradingTask task = (FacultyGradingTask) this.getBean();
				String sql = BeanUtil.concat("DELETE FROM StudentSubjectDetailGrading WHERE facultyGradingTaskId=",task.seq," AND studentId IN (SELECT personId FROM Person WHERE gender='FEMALE')");
				DBClient.runSQLNative(sql);
				redisplayRecord();
			}
		}
	}

	protected void removeBoys() {
//		if (!UserInfo.canDeleteStudent()) {
//			PanelUtil.showError(null, "No DELETE STUDENT duty code, you cannot use this button.");
//			return;
//		}
		boolean b = PanelUtil.showPrompt(null, "Are you sure you want to remove all boys for this component?");
		if (b) {
			b = PanelUtil.showPrompt(null, "Some students already have grade for 1st quarter. Continue to remove boys?");
			if (b) {
				FacultyGradingTask task = (FacultyGradingTask) this.getBean();
				String sql = BeanUtil.concat("DELETE FROM StudentSubjectDetailGrading WHERE facultyGradingTaskId=",task.seq," AND studentId IN (SELECT personId FROM Person WHERE gender='MALE')");
				DBClient.runSQLNative(sql);
				redisplayRecord();
			}
		}
	}

	private void deleteComponent() {
		FacultyGradingTask task = (FacultyGradingTask) this.getBean();
		boolean b = PanelUtil.showPrompt(null, "Are you sure you want to delete the selected component?");
		if (b) {
			b = PanelUtil.showPrompt(null, "Some students already have grade for 1st quarter, do you want to continue?");
			if (b) {
				DBClient.runSQLNative("DELETE FROM StudentSubjectDetailGrading WHERE facultyGradingTaskId=",task.seq);
				task.delete();
				refreshRecords();
			}
		}
	}

	protected void saveAllScore(int quarter) {
		if (AppConfig.isTrimester() && quarter==4) {
			PanelUtil.showMessage(null, "This system is configured for trimester, you cannot use this button.");
			return;
		}
		FacultyGradingTask task = (FacultyGradingTask) this.getBean();
		if (task.weight==0) {
			PanelUtil.showMessage(usedComp, "Weight is zero, please change weight then save again.");
			getComponent("weight").requestFocus();
			return;
		}
		PanelUtil.showWaitFrame("Recalculating grades, please wait...");
		Log.out("RECALCULATE");
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		
		List<StudentSubjectDetailGrading> subjects = tab.list;
		List<StudentSubjectDetailGrading> tlist = new ArrayList<StudentSubjectDetailGrading>();
		for (StudentSubjectDetailGrading s : subjects) {
			tlist.add(s);
		}
		CalculateGradeService.calculateGrade(quarter, task, tlist);
		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

	protected void saveAllScore2(int quarter) {
		FacultyGradingTask task = (FacultyGradingTask) this.getBean();
		if (task.weight==0) {
			PanelUtil.showMessage(usedComp, "Weight is zero, please change weight then save again.");
			getComponent("weight").requestFocus();
			return;
		}
		PanelUtil.showWaitFrame("Recalculating grades, please wait...");
		Log.out("RECALCULATE");
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		List lst = tab.list;

		List<StudentSubject> subjects = AbstractIBean.listCache(BeanUtil.concat("SELECT a FROM StudentSubject a, StudentSubjectDetailGrading b WHERE a.studentId=b.studentId AND a.subject=b.subject AND a.subject='",task.subject,"' AND b.facultyGradingTaskId=",task.seq));
		for (Object obj:lst) {
			if (obj instanceof StudentSubjectDetailGrading) {
				StudentSubjectDetailGrading det = ((StudentSubjectDetailGrading)obj);
				det.scheduleId = task.scheduleId;
				det.section = task.section;
				det.subject = task.subject;
				det.recalculateGrade();
				StudentSubject sub = extractStudentSubject(subjects, det);
				det.studentSubjectId = sub.seq;
			}
		}
		task.save();
		DBClient.persistBean(lst);
		Log.out("SAVE TASK11");
		
		List allSubjects = new ArrayList();
//		recalculate final grade
		List shares = DBClient.getListNative(BeanUtil.concat("SELECT studentId, SUM(gradeShareQ",quarter,") FROM StudentSubjectDetailGrading WHERE section='",task.section,"' AND subject='",task.subject,"' GROUP BY studentId"));
		Log.out("SAVE TASK21"); 
		for (Object obj:lst) {
			if (obj instanceof StudentSubjectDetailGrading) {
				StudentSubjectDetailGrading det = ((StudentSubjectDetailGrading) obj);
				StudentSubject sub = extractStudentSubject(subjects, det);
				if (sub!=null) {
//					get all the grades
					double d = getGradeShareSum(shares, sub.studentId);
					sub.changeValue(BeanUtil.concat("grade",quarter), d); 
					allSubjects.add(sub);
				}
			}
		}
		DBClient.persistBean(allSubjects);
		Log.out("SAVE SUBJECT GPA");

//		recalculate final grade
		ThreadPoolUtil.execute(new GradingProcess(task.schoolYear, task.section, quarter));

		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

	private StudentSubject extractStudentSubject(List<StudentSubject> subjects, StudentSubjectDetailGrading det) {
		for (StudentSubject s:subjects) {
			if (det.subject.equals(s.subject) && det.studentId==s.studentId) {
				s.scheduleId = det.scheduleId;
				s.section = det.section;
				s.schoolYear = det.schoolYear;
				s.facultyId = det.facultyId;
				Log.out("SIZE = ",subjects.size()," ",s.studentName," - ",s.subject);
				return s;
			}
		}
//		this will create a record
		StudentSubject sub = new StudentSubject();
		sub.studentId = det.studentId;
		sub.subject = det.subject;
		sub.scheduleId = det.scheduleId;
		sub.section = det.section;
		sub.schoolYear = det.schoolYear;
		sub.facultyId = det.facultyId;
		sub.save();
		subjects.add(sub);
		return sub;
	}
	
	private double getGradeShareSum(List shares, int personId) {
		if (shares!=null) {
			for (Object obj:shares) {
				List s = (List) obj;
				int studId = DataUtil.getIntValue(s.get(0));
				if (studId==personId) {
					return DataUtil.getDoubleValue(s.get(1));
				}
			}
		}
		return 0;
	}
	
	private void generateAllComponents() {
		ThreadPoolUtil.execute(new RequestRun());
	}

    static List lstCourseSubject;
	private void generateTask() {
		PanelUtil.showWaitFrame();
		FacultyGradingTask t = (FacultyGradingTask) this.getBean();
		if (t==null || t.isEmptyKey()) {
//			generate all task first
			new RequestRun().createTaskBasedOnFaculty();
			refreshRecords();
			PanelUtil.hideWaitFrame();
			PanelUtil.showMessage(null, "Please select component.");
			return;
		}
		SubjectGradingCriteria crit = (SubjectGradingCriteria) DBClient.getFirstRecord("SELECT a FROM SubjectGradingCriteria a WHERE a.subject='",t.subject,"' AND a.criteria='",t.component,"'");
		if (crit!=null) {
			t.weight= crit.percentage;
			t.save();
		}
		
		List all = new ArrayList();
//		select all student from section
		SchoolDefaultProcess proc = new SchoolDefaultProcess();
		List<Student> lstStud = DBClient.getList("SELECT a FROM Student a WHERE a.section='",t.section,"'");
		Section sec = (Section) Section.extractObject(Section.class.getSimpleName(), t.section);
		GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), sec.gradeLevel);
		Schedule sched = (Schedule) Schedule.extractObject(Schedule.class.getSimpleName(), BeanUtil.concat(t.scheduleId).trim());
		sched.boysAndGirls += "";
		for (Student stud:lstStud) {
//			check if already exist
//			if (studentAlreadyExist(stud.personId)) continue;
	        if (lstCourseSubject==null) {
	        	lstCourseSubject = lvl.selectListCache("SELECT a FROM CourseSubject a WHERE a.course='" + stud.course + "'");
		        if (lstCourseSubject==null || lstCourseSubject.isEmpty()) {
	//	        	create curriculum using subjects created
		        	String sql1 = "update subject set course=(select distinct course from gradelevel where code=subject.gradelevel) where course is null or course=''";
		        	String sql2 = "insert into coursesubject(subject, course, weight) (select distinct code, course, unit from subject where course is not null or course!='')";
		        	DBClient.runBatchNative(sql1, sql2);
		        	lstCourseSubject = lvl.selectListCache("SELECT a FROM CourseSubject a");
		        }
	        }
			if (stud.isEmpty(stud.course)) {
				stud.save();
			}
			if (stud.notEmpty(stud.course) && !stud.officiallyRegistered) {
				stud.assessStudentNoPayment(AppConfig.getSchoolYear(), t.section);
			}
			
//			select subject of student
			StudentSubject sub = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId=",stud.personId," AND a.subject='",t.subject,"'");
			StudentSubjectDetailGrading det = new StudentSubjectDetailGrading();
			if (sub==null) {
				sub = new StudentSubject();
				sub.facultyId = t.facultyId;
				sub.faculty = t.faculty;
				sub.section = t.section;
				sub.scheduleId = t.scheduleId;
				sub.studentId = stud.personId;
				sub.subject = t.subject;
				sub.course = stud.course;
				sub.gradeLevel = stud.gradeLevel;
				sub.schoolYear = t.schoolYear;
				sub.save();
			}
			if (sub!=null) {
				det.studentSubjectId = sub.seq;
				sub.facultyId = t.facultyId;
				sub.faculty = t.faculty;
				sub.section = t.section;
				sub.scheduleId = t.scheduleId;
				sub.schoolYear = t.schoolYear;
			}
			det.component = t.component;
			det.facultyGradingTaskId = t.seq;
			det.studentId = stud.personId;
			det.schoolYear = t.schoolYear;
			det.subject = t.subject;
			det.studentName = stud.toString();
			det.facultyId = t.facultyId;
			det.facultyName = t.faculty;
			det.weight = t.weight;
			det.usePercentage = t.usePercentage;
			det.section = t.section; 
			det.scheduleId = t.scheduleId;
			if (sched.boysAndGirls.contains("BOY")) {
				if ("MALE".equalsIgnoreCase(stud.gender)) {
					all.add(sub);
					all.add(det);
				}
			}
			else if (sched.boysAndGirls.contains("GIRL")) {
				if ("FEMALE".equalsIgnoreCase(stud.gender)) {
					all.add(sub);
					all.add(det);
				}
			}
			else {
				all.add(sub);
				all.add(det);
			}
		}
		DBClient.persistBean(all);
		
		PanelUtil.hideWaitFrame();
		redisplayRecord();
		if (!alreadyRun) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					new RequestRun().createTaskBasedOnFaculty();
				}
			});
			th.start();
		}
		alreadyRun = true;
	}

	private boolean studentAlreadyExist(int studentId) {
		try {
			AbstractChildTemplatePanel p = panel.getTabs().get(0);
			List lst = p.list;
			for (Object obj:lst) {
				StudentSubjectDetailGrading det = (StudentSubjectDetailGrading) obj;
				if (det.studentId==studentId) return true;
			}
		}
		catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public void onChangeRecord() {
		super.onChangeRecord();
		FacultyGradingTask t = (FacultyGradingTask) this.getBean();
		if (UserInfo.loginUser.isSuperAAA()) {
			return;
		}
		else {
			if (t.gradeLevel==null) {
				t.save();
			}
			Log.out("CHANGE RECORD 11 ",t.gradeLevel);
			boolean b = GradeLevel.checkLock(t.gradeLevel);
			if (b) {
				Log.out("CHANGE RECORD 1122");
				disable("btnSaveAllScore1");
				disable("btnSaveAllScore2");
				disable("btnSaveAllScore3");
				disable("btnSaveAllScore4");
			}
		}
		if (t.weight==0) {
			enable("weight");
		}
		else {
			disable("weight");
		}
		LockGrading s = LockGrading.extractGrading(t.schoolYear);
		if (s==null) return;
		
		lock(1, false);
		lock(2, false);
		lock(3, false);
		lock(4, false);
		if (s.isQ1Locked()) {
			lock(1, true);
		}
		if (s.isQ2Locked()) {
			lock(2, true);
		}
		if (s.isQ3Locked()) {
			lock(3, true);
		}
		if (s.isQ4Locked()) {
			lock(4, true);
		}
	}
	
	protected void lock(int quarter, boolean b) {
		enable(!b, "q",quarter,"ItemCount1");
		enable(!b, "q",quarter,"ItemCount2");
		enable(!b, "q",quarter,"ItemCount3");
		enable(!b, "q",quarter,"ItemCount4");
		enable(!b, "q",quarter,"ItemCount5");
		enable(!b, "q",quarter,"ItemCount6");
		enable(!b, "q",quarter,"ItemCount7");
		enable(!b, "q",quarter,"ItemCount8");
		enable(!b, "q",quarter,"ItemCount9");
		enable(!b, "q",quarter,"ItemCount10");
		enable(!b, "q",quarter,"ItemCount11");
		enable(!b, "q",quarter,"ItemCount12");
		
		enable(!b, "btnSaveAllScore",quarter);
	}
	
	private static class RequestRun implements Runnable {
		private List<SubjectGradingCriteria> getCriteria(String subject) {
			List<SubjectGradingCriteria> crit = AbstractIBean.listCache(BeanUtil.concat("SELECT a FROM SubjectGradingCriteria a WHERE a.subject='",subject,"'"));
			return crit;
		}

		@Override
		public void run() {
			PanelUtil.showWaitFrame();
			
			List<Schedule> lstSched = AbstractIBean.listCache("SELECT a FROM Schedule a WHERE a.section IS NOT NULL");
			if (lstSched==null) {
				PanelUtil.hideWaitFrame();
				return;
			}
//			Log.out("SIZE = ",lstSched.size());
			for (Schedule sched:lstSched) {
				if (sched.facultyId<=0) continue;
				if (sched.section==null || sched.section.isEmpty()) continue;
				if (sched.subject==null || sched.subject.isEmpty()) continue;
				createTaskBasedOnSchedule(sched);
			}
			PanelUtil.hideWaitFrame();
		}
		
		private void createTaskBasedOnFaculty() {
			List<Schedule> lstSched = AbstractIBean.listCache(BeanUtil.concat("SELECT a FROM Schedule a WHERE a.facultyId=",UserInfo.getPersonId()));
			if (lstSched==null) {
				PanelUtil.hideWaitFrame();
				return;
			}
//			Log.out("SIZE = ",lstSched.size());
			for (Schedule sched:lstSched) {
				if (sched.facultyId<=0) continue;
				if (sched.section==null || sched.section.isEmpty()) continue;
				if (sched.subject==null || sched.subject.isEmpty()) continue;
				createTaskBasedOnSchedule(sched);
			}
		}
		
		private void createTaskBasedOnSchedule(Schedule sched) {
			List lst = new ArrayList();
			List<SubjectGradingCriteria> lstComp = getCriteria(sched.subject);
			if (lstComp!=null && !lstComp.isEmpty()) {
				for (SubjectGradingCriteria comp:lstComp) {
					FacultyGradingTask task = new FacultyGradingTask();
					task.facultyId = sched.facultyId;
					task.section = sched.section;
					task.subject = sched.subject;
					task.component = comp.criteria;
					task.weight = comp.percentage;
					task.scheduleId = sched.seq;
					lst.add(task);
				}
				DBClient.persistBean(lst);
				lst.clear();
			}
		}
	}
}
