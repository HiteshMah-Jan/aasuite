package rule;

import java.util.List;

import javax.swing.JComponent;

import bean.Student;
import bean.admin.AppConfig;
import bean.person.StudentHonor;
import bean.person.StudentSubject;
import bean.person.StudentValuesGrading;
import bean.reference.Subject;

import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

public class StudentHonor_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnClick(JComponent comp) {
	}

}
