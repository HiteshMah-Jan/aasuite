package rule;

import java.util.Date;
import java.util.List;

import javax.swing.JComponent;

import service.util.AbstractIBean;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.Schedule;
import bean.Student;
import bean.StudentAssessment;
import bean.accounting.EnrollmentAR;
import bean.accounting.PaymentPlan;
import bean.admin.AppConfig;

import component.IGetText;
import constants.UserInfo;

public class StudentAssessment_RULE extends BusinessRuleWrapper {
	
	@Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().startsWith("schedule")) {
			if (isAlreadyComplete()) {
				return;
			}
			String selected = ((IGetText) comp).getText();
			changeSubjectUnitAmount(selected, comp.getName().replaceAll("schedule", ""));
			computeTotal();
		}
		else if ("section".equals(comp.getName())) {
			if (isAlreadyComplete()) {
				return;
			}
			String selected = ((IGetText) comp).getText();
			chooseSection(selected);
			computeTotal();
		}
		else if ("miscAmount".equals(comp.getName())) {
			if (isAlreadyComplete()) {
				return;
			}
			computeTotal();
		}
		else if ("discount".equals(comp.getName())) {
			if (isAlreadyComplete()) {
				return;
			}
			computeTotal();
		}
		else if ("studentId".equals(comp.getName())) {
			if (isAlreadyComplete()) {
				return;
			}
			String selected = ((IGetText) comp).getText();
			changeStudent(selected);
		}
	}

	private void changeStudent(String selected) {
		if (selected !=null) {
			Student stud = (Student) this.getSelectedBean("studentId");
			this.setValue("studentName", stud.toString());
		}
	}

	private void changeSubjectUnitAmount(String sched, String index) {
//		change subject, unit and amount
		if (sched==null || sched.isEmpty()) {
			this.setValue("schedule"+index, "0");
			this.setValue("subject"+index, "");
			this.setValue("unit"+index, "0.0");
			this.setValue("amount"+index, "0.0");
		}
		else {
			Schedule s = (Schedule) this.getSelectedBean("schedule"+index);;
			this.setValue("subject"+index, s.getSubject());
			this.setValue("unit"+index, s.getUnit());
			double amount = DBClient.getSingleColumnDouble("SELECT a.amount FROM Subject a WHERE a.code='",s.subject,"'");
			this.setValue("amount"+index, amount);
		}
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnFinalize".equals(comp.getName())) {
			if (isAlreadyComplete()) {
				return;
			}
			complete();
		}
		else if ("btnPrintSchedule".equals(comp.getName())) {
			printSchedule();
		}
		else if ("btnSOA".equals(comp.getName())) {
			printSOA();
		}
	}

	private void printSOA() {
		StudentAssessment assess = ((StudentAssessment)this.getBean());
		if (!StudentAssessment.COMPLETE.equals(assess.status)) {
			this.showError("Assessment is not yet finalize, please finalize.");
		}
		else {
			PanelUtil.popupReport("SchoolSOA", String.valueOf(assess.seq), "Statement Of Account");
		}
	}

	private void printSchedule() {
		StudentAssessment assess = ((StudentAssessment)this.getBean());
		if (!StudentAssessment.COMPLETE.equals(assess.status)) {
			this.showError("Assessment is not yet finalize, please finalize.");
		}
		else {
			PanelUtil.popupReport("StudentSchedule", String.valueOf(assess.seq), "Schedule");
		}
	}

	private void complete() {
		StudentAssessment assess = (StudentAssessment)this.getBean();
		if (StudentAssessment.COMPLETE.equals(assess.status)) {
			this.showMessage("This assessment is already finalize.");
			return;
		}
//		check all mandatory
		if (!this.mandatoryCheck("plan", "Please select plan.")) return;
		if (!this.mandatoryCheck("semester", "Please select semester.")) return;
		if (!this.mandatoryCheck("totalUnit", "Please select section or schedule.")) return;
		if (!this.mandatoryCheck("studentId", "Student is a required field.")) return;

		if (!this.showPrompt("Are you sure to finalize, you cannot modify this after finalizing the record?")) return;
//		save to get the actual record id
		assess.status = StudentAssessment.COMPLETE;
		assess.save();
		
//		create all from payment plan
		assessStudent(assess);
		this.redisplayRecord();
		this.updateUI();
	}

	private void chooseSection(String section) {
		if (section!=null && !section.isEmpty()) {
			if (this.showPrompt("Would you like to change the schedule to the selected block or section?")) {
//				clean all first
				for (int i=1; i<=20; i++) {
					changeSubjectUnitAmount("", String.valueOf(i));
				}
				List schedules = DBClient.getList("SELECT a FROM Schedule a WHERE a.section='",section,"'");
				for (int i=1; i<=schedules.size(); i++) {
					Schedule s = (Schedule) schedules.get(i-1);
					this.setValue("schedule"+i, s.seq);
					this.setValue("subject"+i, s.getSubject());
					this.setValue("unit"+i, s.getUnit());
					double amount = DBClient.getSingleColumnDouble("SELECT a.amount FROM Subject a WHERE a.code='",s.subject,"'");
					this.setValue("amount"+i, amount);
				}
			}
		}
	}

	private void computeTotal() {
		double totalUnit = 0;
		double totalAmount = 0;
		for (int i=1; i<=20; i++) {
			totalUnit += this.getDoubleValue("unit"+i, 0);
			totalAmount += this.getDoubleValue("amount"+i, 0);
		}
		double misc = this.getDoubleValue("miscAmount", 0);
		double discount = this.getDoubleValue("discount", 0);
		this.setValue("totalUnit", DataUtil.getMoneyFormat(totalUnit));
		this.setValue("totalUnitAmount", DataUtil.getMoneyFormat(totalAmount));
		this.setValue("overallAmount", DataUtil.getMoneyFormat(totalAmount+misc-discount));
	}

	private void assessStudent(StudentAssessment assess) {
		PaymentPlan plan = (PaymentPlan) this.getSelectedBean("plan");
        for (int i=1; i<=10; i++) {
    		EnrollmentAR payment = new EnrollmentAR();
    		payment.studentId = assess.studentId;
    		payment.assessId = assess.seq;
            payment.schoolYear = assess.schoolYear;
            payment.amount = BeanUtil.getDoubleValue(plan, "amount"+i);
            payment.duedate = (Date) BeanUtil.getPropertyValue(plan, "date"+i);
            if (payment.amount > 0) {
                payment.save();
            }
        }
	}

	private boolean isAlreadyComplete() {
		StudentAssessment assess = ((StudentAssessment)this.getBean());
		if (assess==null) return false;
		boolean b = (StudentAssessment.COMPLETE.equals(assess.status));
		if (b) {
			this.showErrorMessageToScreen("This assessment is already complete, you cannot change any value.");
		}
		return b;
	}

	public void onNewRecord() {
		this.setValue("semester", 1);
		this.setValue("schoolYear", AppConfig.getSchoolYear());
		this.setValue("assessedBy", UserInfo.loginUser.getUserName());
		this.showErrorMessageToScreen("");
	}
	public boolean beforeSave(AbstractIBean bean) {
		complete();
		return false;
	}
	
}
