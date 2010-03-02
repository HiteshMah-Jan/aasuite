package rule;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import springbean.GradingProcess;
import util.DBClient;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.Student;
import bean.admin.AppConfig;
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
				List<StudentValuesGrading> lst = DBClient.getList("SELECT a FROM StudentValuesGrading a WHERE a.schoolYear='"+AppConfig.getSchoolYear()+"' ORDER BY a.seq DESC",0,10000);
				System.out.println("COUNT IS "+lst.size());
				for (StudentValuesGrading g:lst) {
					ThreadPoolUtil.execute(new Runner(g));
				}
//				List lst = DBClient.getList("SELECT a FROM StudentValuesGrading a WHERE a.schoolYear='"+AppConfig.getSchoolYear()+"' ORDER BY a.seq",0,10000);
//				System.out.println("Count is "+lst.size());
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
//			System.out.println("VALUES "+g.seq);
			g.save();
		}
	}
	
	private void generateRecords() {
		List lst = new ArrayList();
		List<Student> lstStud = DBClient.getList("SELECT a FROM Student a, Section b WHERE a.section=b.code AND b.facultyId="+UserInfo.getPersonId()+" AND a.status='ENROLLED'",0,10000);
		for (Student stud:lstStud) {
			StudentValuesGrading val = (StudentValuesGrading) DBClient.getFirstRecord("SELECT a FROM StudentValuesGrading a WHERE a.studentId="+stud.personId+" AND a.gradeLevel='"+stud.gradeLevel+"'");
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
		if ("|P1|P2|N1|N2|K1|K2|".contains(t.gradeLevel+"|")) {
			displayTab(0);
			displayTab(1);
		}
		else if ("|G1|G2|G3|G4|".contains(t.gradeLevel+"|")) {
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
		enable("kp"+qStr,!b);
		enable("fh"+qStr,!b);
		enable("fr"+qStr,!b);
		enable("aw"+qStr,!b);
		enable("ft"+qStr,!b);
		enable("ic"+qStr,!b);
		enable("ih"+qStr,!b);
		enable("wi"+qStr,!b);
		enable("id"+qStr,!b);
		enable("neatAndOrganize"+qStr,!b);
		enable("de"+qStr,!b);
		enable("wp"+qStr,!b);
		enable("hs"+qStr,!b);
		enable("ir"+qStr,!b);
		enable("ls"+qStr,!b);
		enable("ap"+qStr,!b);
		enable("ce"+qStr,!b);
		enable("tc"+qStr,!b);
		enable("cfp"+qStr,!b);
		enable("df"+qStr,!b);
		enable("wlr"+qStr,!b);
		enable("cst"+qStr,!b);
		enable("ocs"+qStr,!b);
		enable("cwp"+qStr,!b);
		enable("cp"+qStr,!b);
		enable("pa"+qStr,!b);
		enable("con"+qStr,!b);
		enable("mot"+qStr,!b);
		enable("eff"+qStr,!b);
		enable("res"+qStr,!b);
		enable("ini"+qStr,!b);
		enable("per"+qStr,!b);
		enable("car"+qStr,!b);
		enable("tea"+qStr,!b);
		enable("com"+qStr,!b);
		enable("pro"+qStr,!b);
		enable("els"+qStr,!b);
		enable("wfr"+qStr,!b);
		enable("apgw"+qStr,!b);
		enable("spaa"+qStr,!b);
		enable("iva"+qStr,!b);
		enable("isl"+qStr,!b);
		enable("aspvi"+qStr,!b);
		enable("hspd"+qStr,!b);
		enable("sd"+qStr,!b);
		enable("put"+qStr,!b);
		enable("hlew"+qStr,!b);
		enable("prs"+qStr,!b);
		enable("cr"+qStr,!b);
		enable("cws"+qStr,!b);
		enable("pfe"+qStr,!b);
		enable("cra"+qStr,!b);
		enable("ca"+qStr,!b);
		enable("kin"+qStr,!b);
		enable("ec1"+qStr,!b);
		enable("ec2"+qStr,!b);
		
//		AbstractChildTemplatePanel tab = this.panel.getTabs().get(quarter-1);
//		tab.setEnabled(!b);
//		tab.tblRecord.setEnabled(!b);
//		tab.setVisible(!b);
	}
}
