package rule;

import javax.swing.JComponent;

import bean.Student;

import util.DBClient;

public class StudentSnapshotExt_RULE extends BusinessRuleWrapper {

	@Override
	public void onChangeRecord() {
		getComponent("studentNumber").setEnabled(false);
		getComponent("gradeLevel").setEnabled(false);
		getComponent("section").setEnabled(false);
		getComponent("paymentStatus").setEnabled(false);
	}

	@Override
	public void runFocusLost(JComponent comp) {
		if ("status".equals(comp.getName())) {
			Student st = (Student) this.getBean();
			st.save();
		}
	}

	@Override
	public void runOnClick(JComponent comp) {
	}

}
