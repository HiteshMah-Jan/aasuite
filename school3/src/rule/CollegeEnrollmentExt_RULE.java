package rule;

import java.util.List;

import javax.swing.JComponent;

import springbean.SchoolDefaultProcess;
import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;
import bean.Enrollment;
import bean.Schedule;
import bean.Student;
import bean.accounting.Payment;
import bean.accounting.PaymentPlan;
import bean.admin.AppConfig;
import bean.person.StudentSubject;
import bean.reference.GradeLevel;
import bean.reference.Section;
import bean.reference.Subject;

import component.JTextFieldPallete;
import component.LookupTableFieldPallete;

public class CollegeEnrollmentExt_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().contains("schedule")) {
			updateSchedule(comp.getName());
			computeAmounts();
		}
		else if (comp.getName().contains("section")) {
			updateSection();
			computeAmounts();
		}
		else if (comp.getName().contains("Fee")) {
			computeAmounts();
		}
	}

	private void updateSection() {
		Enrollment col = (Enrollment) this.getBean();
		if (col==null || col.isEmptyKey()) {
			String val = getValue("section");
			Section sec = (Section) Section.extractObject(Section.class.getSimpleName(), val);
			if(sec!=null || !sec.isEmptyKey()) {
				List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.section='",sec.code,"'");
				for (int i=0; i<lst.size(); i++) {
					int index = i+1;
					String scname = BeanUtil.concat("schedule",index);
					Schedule sched = lst.get(i);
					((LookupTableFieldPallete)getComponent(scname)).setText(BeanUtil.concat(sched.seq));
					updateSchedule(BeanUtil.concat("schedule",index));
				}
			}
		}
	}

	private void updateSchedule(String name) {
        JTextFieldPallete l = (JTextFieldPallete) usedComp;
		String val = getValue(name);
        String amt = name.replace("schedule", "amount");
        String unt = name.replace("schedule", "unit");
        String sub = name.replace("schedule", "subject");
        String fac = name.replace("schedule", "faculty");
        if (l.isEmpty()) {
            setValue(amt, 0);
            setValue(unt, 0);
            setValue(sub, "");
            setValue(fac, 0);
		}
		else {
			Schedule sched = (Schedule) Schedule.extractObject(Schedule.class.getSimpleName(), val);
			if (sched!=null) {
				double totalAmount = AppConfig.getPerUnitAmount()*sched.unit;
	            setValue(unt, sched.unit);
	            setValue(sub, sched.subject);
	            setValue(fac, sched.facultyId);
				setValue(amt, totalAmount);
			}
		}
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnAssess".equals(comp.getName())) {
			assess();
		}
		else if ("btnPrintSchedule".equals(comp.getName())) {
			printSchedule();
		}
		else if ("btnPrintSOA".equals(comp.getName())) {
			printSOA();
		}
		else if ("btnPaymentPlan".equals(comp.getName())) {
			PanelUtil.popupBeanTemplate(PaymentPlan.class, "Payment Plan", true);
		}
		else if ("btnGetMisc".equals(comp.getName())) {
			getMisc();
		}
	}

	private void getMisc() {
		Enrollment col = (Enrollment) this.getBean();
		Student stud = (Student) DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.seq=",col.studentId);
		String plan = col.paymentPlanType;
		PaymentPlan pl = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a WHERE a.code='",plan,"' AND a.gradeLevel='",stud.gradeLevel,"'");
		if (pl==null || pl.isEmptyKey()) {
			pl = PaymentPlan.createPlan(plan, BeanUtil.concat("Plan ",plan," for ",stud.gradeLevel), 1, col.schoolYear, stud.gradeLevel, 0);
			pl.save();
		}
		if (pl==null || pl.amount1==0) {
			PanelUtil.showError(null, "Payment calendar not yet set for plan ",plan," : ",stud.gradeLevel);
			return;
		}
		new SchoolDefaultProcess().setupMisc(col, pl);
		computeAmounts();
		col.save();
		redisplayRecord();
	}

	private void printSOA() {
		Enrollment e = (Enrollment) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("StatementOfAccount", e.studentId, "Statement of Account", null);
	}

	private void printSchedule() {
		Enrollment e = (Enrollment) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("StudentSchedule", e.seq, "Schedule", null);
	}

	protected void computeAmounts() {
		Enrollment col = (Enrollment) this.getBean();
		col.totalUnits = col.unit1;
		col.totalUnits += col.unit2;
		col.totalUnits += col.unit3;
		col.totalUnits += col.unit4;
		col.totalUnits += col.unit5;
		col.totalUnits += col.unit6;
		col.totalUnits += col.unit7;
		col.totalUnits += col.unit8;
		col.totalUnits += col.unit9;
		col.totalUnits += col.unit10;
		col.totalUnits += col.unit11;
		col.totalUnits += col.unit12;
		col.totalUnits += col.unit13;
		col.totalUnits += col.unit14;
		col.totalUnits += col.unit15;
		
		col.totalAmount = col.amount1;
		col.totalAmount += col.amount2;
		col.totalAmount += col.amount3;
		col.totalAmount += col.amount4;
		col.totalAmount += col.amount5;
		col.totalAmount += col.amount6;
		col.totalAmount += col.amount7;
		col.totalAmount += col.amount8;
		col.totalAmount += col.amount9;
		col.totalAmount += col.amount10;
		col.totalAmount += col.amount11;
		col.totalAmount += col.amount12;
		col.totalAmount += col.amount13;
		col.totalAmount += col.amount14;
		col.totalAmount += col.amount15;

		col.overAllAmount = col.getComputedOverallMisc() + col.totalAmount;
		setValue("miscellaneousFee",col.getComputedOverallMisc());
		setValue("totalAmount",BeanUtil.concat(col.totalAmount));
		setValue("totalUnits",col.totalUnits);
		setValue("overAllAmount",BeanUtil.concat(col.overAllAmount));
	}
	
	protected boolean validSchedule() {
		Enrollment col = (Enrollment) this.getBean();
		boolean b = col.noConflict();
		if (!b) {
			PanelUtil.showError(null, "Please check your schedule for conflicts.");
			return false;
		}
		return true;
	}
	
	protected boolean validPrerequisite() {
		Enrollment col = (Enrollment) this.getBean();
//		get student subject
		List<StudentSubject> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubject a WHERE a.studentId=",col.studentId));
		for (int i=1; i<=15; i++) {
			String sub = (String) BeanUtil.getPropertyValue(col, BeanUtil.concat("subject",i));
			if (sub!=null && !sub.isEmpty()) {
				Subject subject = (Subject) Subject.extractObject(Subject.class.getSimpleName(), sub);
				if (subject!=null) {
//					get subject from curriculum
					String preSubject = null;
					for (StudentSubject s:lst) {
						if (sub.equals(s.subject)) {
							preSubject =BeanUtil.concat( "|",s.preSubject1,"|",s.preSubject2,"|",s.preSubject3,"|",s.preSubject4,"|",s.preSubject5,"|");
						}
					}
					if (preSubject!=null) {
//						check if pre subject is already passed and taken
						for (StudentSubject s:lst) {
							if (preSubject.contains(BeanUtil.concat("|",s.subject,"|"))) {
								if (!s.passed) {
									PanelUtil.showError(null, "Subject [",sub,"] has pre requisite [",preSubject,"]. You need to pass the pre requisite subject first.");
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	protected boolean hasSubject(String subject) {
		Enrollment col = (Enrollment) this.getBean();
		for (int i=1; i<=15; i++) {
			String sub = (String) BeanUtil.getPropertyValue(col, BeanUtil.concat("subject",i));
			if (sub.equals(subject)) return true;
		}
		return false;
	}
	
	protected boolean validCorequisite() {
		Enrollment col = (Enrollment) this.getBean();
//		get student subject
		List<StudentSubject> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubject a WHERE a.studentId=",col.studentId));
		for (int i=1; i<=15; i++) {
			String sub = (String) BeanUtil.getPropertyValue(col, "subject",i);
			if (sub!=null && !sub.isEmpty()) {
				Subject subject = (Subject) Subject.extractObject(Subject.class.getSimpleName(), sub);
				if (subject!=null) {
//					get subject from curriculum
					String coSubject = null;
					for (StudentSubject s:lst) {
						if (sub.equals(s.subject)) {
							if (s.coSubject1!=null && !s.coSubject1.isEmpty() && !hasSubject(s.coSubject1)) {
								coSubject = BeanUtil.concat("|",s.coSubject1,"|");
							}
							if (s.coSubject2!=null && !s.coSubject2.isEmpty() && !hasSubject(s.coSubject2)) {
								coSubject = BeanUtil.concat("|",s.coSubject2,"|");
							}
							if (s.coSubject3!=null && !s.coSubject3.isEmpty() && !hasSubject(s.coSubject3)) {
								coSubject = BeanUtil.concat("|",s.coSubject3,"|");
							}
							if (s.coSubject4!=null && !s.coSubject4.isEmpty() && !hasSubject(s.coSubject4)) {
								coSubject = BeanUtil.concat("|",s.coSubject4,"|");
							}
							if (s.coSubject5!=null && !s.coSubject5.isEmpty() && !hasSubject(s.coSubject5)) {
								coSubject = BeanUtil.concat("|",s.coSubject5,"|");
							}
						}
					}
					if (coSubject!=null) {
//						check if co subject is already passed and taken
						for (StudentSubject s:lst) {
							if (coSubject.contains(BeanUtil.concat("|",s.subject,"|"))) {
								if (!s.passed) {
									PanelUtil.showError(null, "Subject [",sub,"] has co requisite [",coSubject,"]. You need to pass or take the co requisite.");
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	protected void assess() {
		if (!validSchedule()) return;
		if (!validPrerequisite()) return;
		if (!validCorequisite()) return;
//		get payment calendar
		Enrollment col = (Enrollment) this.getBean();
		if (col.paymentPlanType!=null && !col.paymentPlanType.isEmpty()) {
//			check if there are payments already
			Payment p = (Payment) DBClient.getFirstRecord("SELECT a FROM Payment a WHERE a.schoolYear='",col.schoolYear,"' AND a.paidBy=",col.studentId," AND a.paid=true");
			if (p!=null) {
				PanelUtil.showMessage(null, "Student already has payment, cannot assess.");
				return;
			}
			else {
				DBClient.runSQLNative("DELETE FROM Payment WHERE schoolYear='",col.schoolYear,"' AND paidBy=",col.studentId);
			}
		}
		
		String plan = PanelUtil.showPromptDefaultMessage(null, "Type payment plan [S,Q,M]", "S");
		if (plan!=null) {
			Student stud = (Student) DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.seq="+col.studentId);
			
			PaymentPlan pl = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a WHERE a.code='",plan,"' AND a.gradeLevel='",stud.gradeLevel,"'");
			if (pl==null || pl.isEmptyKey()) {
				pl = PaymentPlan.createPlan(plan, BeanUtil.concat("Plan ",plan," for ",stud.gradeLevel), 1, col.schoolYear, stud.gradeLevel, 0);
				pl.save();
			}
			if (pl==null || pl.amount1==0) {
				PanelUtil.showError(null, "Payment calendar not yet set for plan ",plan," : ",stud.gradeLevel);
				return;
			}
			col.paymentPlanType = pl.code;
			col.gradeLevel = stud.gradeLevel;
			col.save();
			GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), stud.gradeLevel);
			stud.assessStudent(col.schoolYear, lvl, plan, 0);
			redisplayRecord();	
			PanelUtil.showMessage(null, "Student assessed successfully, please check payments.");
		}
//		link studentsubjects to enrollment
		col.linkSubjects();
	}
}
