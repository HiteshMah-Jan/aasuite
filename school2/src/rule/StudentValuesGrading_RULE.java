package rule;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import springbean.GradingProcess;
import template.screen.AbstractChildTemplatePanel;
import util.BeanUtil;
import util.DBClient;
import util.Log;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.Student;
import bean.admin.AppConfig;
import bean.person.StudentSubject;
import bean.person.StudentValuesGrading;
import bean.reference.GradeLevel;
import bean.reference.LockGrading;
import constants.UserInfo;

public class StudentValuesGrading_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
//		if ("|totalDays|present|".contains(comp.getName())) {
//			updateAbsent();
//		}
	}

	private void updateAbsent() {
		StudentValuesGrading val = (StudentValuesGrading) this.getBean();
		if (val.totalDays-val.present > 0) {
			setValue("absent", val.totalDays-val.present);
		}
		else {
			setValue("absent", 0);
		}
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnGenerateRecords".equals(comp.getName())) {
			generateRecords();
		}
		else if ("btnRankQ1".equals(comp.getName())) {
			merit(1);
		}
		else if ("btnRankQ2".equals(comp.getName())) {
			merit(2);
		}
		else if ("btnRankQ3".equals(comp.getName())) {
			merit(3);
		}
		else if ("btnRankQ4".equals(comp.getName())) {
			merit(4);
		}
		else if ("btnSaveAllDisplayed".equals(comp.getName())) {
			saveAllDisplayed();
		}
		else if ("btnTestGrading".equals(comp.getName())) {
			testGrading();
		}
	}

	private void testGrading() {
		String[] arrComment = {"Kind","Patient","Polite","Good"};
		if (UserInfo.loginUser.isSuperAAA() && AppConfig.isShowTestButton()) {
			StudentValuesGrading bean = (StudentValuesGrading) this.getBean();
			List<StudentValuesGrading> lst = DBClient.getList("SELECT a FROM StudentValuesGrading a WHERE a.gradeLevel='",bean.gradeLevel,"' AND a.section='",bean.section,"' AND a.schoolYear='",AppConfig.getSchoolYear(),"'");
			for (StudentValuesGrading val:lst) {
				putRandomGrade(val, 1, 5, "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
				putRandomGrade(val, 1, 5, "ap", "ce", "tc", "cfp", "df", "wlr", "cst", "ocs", "cwp", "cp", "pa");
				putRandomGrade(val, 1, 5, "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "focus", "respect");
				putRandomGrade(val, 1, 5, "els", "wfr", "apgw", "spaa", "iva", "isl", "aspvi", "hspd", "sd", "put", "hlew", "prs", "cr", "cws");
				putRandomGrade(val, 1, 5, "pfe", "cra", "ca", "kin", "ec1", "ec2");
				putRandomGrade(val, 75, 99, "scouting");
				putRandomGrade(val, 0, 5, "absent");
				val.comment1 = arrComment[getRandom(0, 3)];
				val.comment2 = arrComment[getRandom(0, 3)];
				val.comment3 = arrComment[getRandom(0, 3)];
				val.comment4 = arrComment[getRandom(0, 3)];
				val.save();
			}
			this.redisplayRecord();
		}
	}

	private void putRandomGrade(StudentValuesGrading val, int i, int j, String... str) {
		for (String s:str) {
			BeanUtil.setPropertyValue(val, s, getRandom(i, j));
			BeanUtil.setPropertyValue(val, BeanUtil.concat(s,"2"), getRandom(i, j));
			BeanUtil.setPropertyValue(val, BeanUtil.concat(s,"3"), getRandom(i, j));
			BeanUtil.setPropertyValue(val, BeanUtil.concat(s,"4"), getRandom(i, j));
		}
	}

	private int getRandom(int low, int high) {
		double d = Math.random();
		int val = (int) ((high - low) * d);
		return low + val;
	}

	private void saveAllDisplayed() {
		PanelUtil.showWaitFrame("Saving records, please wait");
		List lst = this.panel.getRecordList();
		DBClient.persistBean(lst);
		this.redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

	protected void merit(int quarter) {
		if (AppConfig.isTrimester() && quarter==4) {
			PanelUtil.showMessage(null, "This system is configured for trimester, you cannot use this button.");
			return;
		}

		StudentValuesGrading grd = (StudentValuesGrading) this.getBean();
		if (grd==null || grd.isEmptyKey()) {
			PanelUtil.showError(null, "Please select a record to perform merit process.");
			return;
		}
//		call only if AAA, this is really slow
		if (UserInfo.loginUser.isSuperAAA()) {
			boolean b = PanelUtil.showPrompt(null, "If you are AAA then you will see this, would you like to save all records first, this will take several minutes.");
			if (b) {
				PanelUtil.showWaitFrame("Saving all records.");
				List<StudentValuesGrading> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentValuesGrading a WHERE a.schoolYear='",AppConfig.getSchoolYear(),"' ORDER BY a.seq DESC"),0,10000);
				Log.out("COUNT IS ",lst.size());
				for (StudentValuesGrading g:lst) {
					ThreadPoolUtil.execute(new Runner(g));
				}
//				List lst = DBClient.getList("SELECT a FROM StudentValuesGrading a WHERE a.schoolYear='",AppConfig.getSchoolYear(),"' ORDER BY a.seq",0,10000);
//				Log.out("Count is ",lst.size());
//				DBClient.persistBean(lst);
				PanelUtil.hideWaitFrame();
			}
		}
//		run only when the third tab is remove
		GradingProcess proc = new GradingProcess(grd.schoolYear, grd.section, quarter);
		proc.setHonors();
		
		PanelUtil.hideWaitFrame();
	}

	private class Runner implements Runnable {
		StudentValuesGrading g;
		private Runner(StudentValuesGrading g) {
			this.g = g;
		}

		@Override
		public void run() {
//			Log.out("VALUES ",g.seq);
			g.save();
		}
	}
	
	private void generateRecords() {
		List lst = new ArrayList();
		List<Student> lstStud = DBClient.getList(BeanUtil.concat("SELECT a FROM Student a, Section b WHERE a.section=b.code AND b.facultyId=",UserInfo.getPersonId()," AND a.status='ENROLLED'"),0,10000);
		for (Student stud:lstStud) {
			StudentValuesGrading val = (StudentValuesGrading) DBClient.getFirstRecord("SELECT a FROM StudentValuesGrading a WHERE a.studentId=",stud.personId," AND a.gradeLevel='",stud.gradeLevel,"'");
			if (val==null) {
				val = new StudentValuesGrading();
			}
			val.section = stud.section;
			val.gradeLevel = stud.gradeLevel;
			val.facultyId = UserInfo.getPersonId();
			val.faculty = UserInfo.loginUser.getPersonName();
			val.studentId = stud.personId;
			val.student = stud.toString();
			val.studentNumber = stud.studentNumber;
			lst.add(val);
		}
		DBClient.persistBean(lst);
		refreshRecords();
	}

	Component[] comps;
	List<String> title = new ArrayList();

	@Override
	public void onChangeRecord() {
		super.onChangeRecord();
		StudentValuesGrading t = (StudentValuesGrading) this.getBean();
		if (UserInfo.loginUser.isSuperAAA()) {
			return;
		}
		else {
			boolean b = GradeLevel.checkLock(t.gradeLevel);
			if (b) {
				disable("btnRankQ1");
				disable("btnRankQ2");
				disable("btnRankQ3");
				disable("btnRankQ4");
			}
		}
		LockGrading s = LockGrading.extractGrading(t.schoolYear);
		if (s==null) {
			return;
		}
		
		lock("", false);
		lock("2", false);
		lock("3", false);
		lock("4", false);
		lock("Final", true);
		if (s.isQ1Locked()) {
			lock("", true);
		}
		if (s.isQ2Locked()) {
			lock("2", true);
		}
		if (s.isQ3Locked()) {
			lock("3", true);
		}
		if (s.isQ4Locked()) {
			lock("4", true);
		}
		hideAllTabs();
		if ("|P1|P2|N1|N2|K1|K2|".contains(BeanUtil.concat(t.gradeLevel,"|"))) {
			displayTab(0);
			displayTab(1);
		}
		else if ("|G1|G2|G3|G4|".contains(BeanUtil.concat(t.gradeLevel,"|"))) {
			displayTab(2);
		}
		else {
			displayTab(3);
			displayTab(4);
		}
		boolean b = AppConfig.isShowMonthlyAttendance();
		if (b) {
			displayTab(5);
		}
	}
	
	private void lock(String quarter, boolean b) {
		String qStr = quarter;
		enable(!b, "kp",qStr);
		enable(!b, "fh",qStr);
		enable(!b, "fr",qStr);
		enable(!b, "aw",qStr);
		enable(!b, "ft",qStr);
		enable(!b, "ic",qStr);
		enable(!b, "ih",qStr);
		enable(!b, "wi",qStr);
		enable(!b, "id",qStr);
		enable(!b, "neatAndOrganize",qStr);
		enable(!b, "de",qStr);
		enable(!b, "wp",qStr);
		enable(!b, "hs",qStr);
		enable(!b, "ir",qStr);
		enable(!b, "ls",qStr);
		enable(!b, "ap",qStr);
		enable(!b, "ce",qStr);
		enable(!b, "tc",qStr);
		enable(!b, "cfp",qStr);
		enable(!b, "df",qStr);
		enable(!b, "wlr",qStr);
		enable(!b, "cst",qStr);
		enable(!b, "ocs",qStr);
		enable(!b, "cwp",qStr);
		enable(!b, "cp",qStr);
		enable(!b, "pa",qStr);
		enable(!b, "con",qStr);
		enable(!b, "mot",qStr);
		enable(!b, "eff",qStr);
		enable(!b, "res",qStr);
		enable(!b, "ini",qStr);
		enable(!b, "per",qStr);
		enable(!b, "car",qStr);
		enable(!b, "tea",qStr);
		enable(!b, "com",qStr);
		enable(!b, "pro",qStr);
		enable(!b, "els",qStr);
		enable(!b, "wfr",qStr);
		enable(!b, "apgw",qStr);
		enable(!b, "spaa",qStr);
		enable(!b, "iva",qStr);
		enable(!b, "isl",qStr);
		enable(!b, "aspvi",qStr);
		enable(!b, "hspd",qStr);
		enable(!b, "sd",qStr);
		enable(!b, "put",qStr);
		enable(!b, "hlew",qStr);
		enable(!b, "prs",qStr);
		enable(!b, "cr",qStr);
		enable(!b, "cws",qStr);
		enable(!b, "pfe",qStr);
		enable(!b, "cra",qStr);
		enable(!b, "ca",qStr);
		enable(!b, "kin",qStr);
		enable(!b, "ec1",qStr);
		enable(!b, "ec2",qStr);
		
//		AbstractChildTemplatePanel tab = this.panel.getTabs().get(quarter-1);
//		tab.setEnabled(!b);
//		tab.tblRecord.setEnabled(!b);
//		tab.setVisible(!b);
	}
}
