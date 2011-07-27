package rule;

import java.util.List;

import javax.swing.JComponent;

import util.DBClient;
import bean.Schedule;
import bean.Student;
import bean.StudentAssessment;

import component.IGetText;

public class StudentAssessment_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().startsWith("schedule")) {
			String selected = ((IGetText) comp).getText();
			changeSubjectUnitAmount(selected, comp.getName().replaceAll("schedule", ""));
			computeTotal();
		}
		else if ("section".equals(comp.getName())) {
			String selected = ((IGetText) comp).getText();
			chooseSection(selected);
			computeTotal();
		}
		else if ("miscAmount".equals(comp.getName())) {
			computeTotal();
		}
		else if ("studentId".equals(comp.getName())) {
			String selected = ((IGetText) comp).getText();
			changeStudent(selected);
		}
	}

	private void changeStudent(String selected) {
		if (selected !=null) {
			Student stud = (Student) DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.seq=",selected);
			this.setValue("studentName", stud.toString());
		}
	}

	private void changeSubjectUnitAmount(String sched, String index) {
//		change subject, unit and amount
		if (sched==null || sched.isEmpty()) {
			this.setValue("subject"+index, "");
			this.setValue("unit"+index, "0.0");
			this.setValue("amount"+index, "0.0");
		}
		else {
			Schedule s = (Schedule) DBClient.getFirstRecord("SELECT a FROM Schedule a WHERE a.seq=",sched);
			this.setValue("subject"+index, s.getSubject());
			this.setValue("unit"+index, s.getUnit());
			this.setValue("amount"+index, s.getUnitAmount());
		}
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnComplete".equals(comp.getName())) {
			complete();
		}
	}

	private void complete() {
//		check all mandatory
		if (!this.mandatoryCheck("semester", "Please select semester.")) return;
		if (!this.mandatoryCheck("totalUnit", "Please select section or schedule.")) return;
		if (!this.mandatoryCheck("studentId", "Student is a required field.")) return;
		((StudentAssessment)this.getBean()).status = "COMPLETE";
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
					this.setValue("amount"+i, s.getUnitAmount());
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
		this.setValue("totalUnit", totalUnit);
		this.setValue("totalUnitAmount", totalAmount);
		this.setValue("overallAmount", totalAmount+misc);
	}
}
