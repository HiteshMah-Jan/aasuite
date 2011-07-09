package rule;

import javax.swing.JComponent;

import springbean.GradingProcess;
import springbean.StudentSubjectToEnrollmentGrade;
import util.DBClient;
import util.PanelUtil;
import bean.Enrollment;
import bean.person.StudentSubject;
import bean.reference.LockGrading;

public class StudentSubject2Ext_RULE extends StudentSubject_RULE {
    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnPassQ1")) {
        	passQ1();
        }
    }

	private void passQ1() {
		PanelUtil.showMessage(null, "This function is under construction.");
	}
}
