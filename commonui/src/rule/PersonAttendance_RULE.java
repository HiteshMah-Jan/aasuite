package rule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;



import bean.hr.PersonAttendance;
import springbean.PayrollService;
import util.DBClient;

public class PersonAttendance_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreateAttendance".equals(comp.getName())) {
            createAttendance();
        }
        else if ("btnCalculate".equals(comp.getName())) {
            calculate();
            if (showPrompt("Would you like to save calculated hours?")) {
                saveRecord();
            }
            redisplayRecord();
        }
        else if ("btnCalculateAll".equals(comp.getName())) {
            calculateAll();
            refreshRecords();
        }
    }

    private void calculateAll() {
    	List lst = this.panel.getRecordList();
    	PayrollService.calculateAttendance(lst);
        if (showPrompt("Would you like to save all calculated hours?")) {
        	List l = new ArrayList();
        	l.addAll(lst);
            DBClient.persistBean(l);
        }
	}

	private void calculate() {
        PersonAttendance att = (PersonAttendance) this.getBean();
        att.login = getValue("login");
        att.logout = getValue("logout");

        PayrollService.calculateAttendance(att);

        double total = att.totalHours;
        double ot = att.otHours;
        double ut = att.underTime;
        double nt = att.nightHours;

        doubleSetValue("totalHours", 0, total);
        doubleSetValue("otHours", 0, ot);
        doubleSetValue("underTime", 0, ut);
        doubleSetValue("nightHours", 0, nt);
    }

    protected void doubleSetValue(String field, Object first, Object second) {
        setValue(field, first);
        setValue(field, second);
    }

    private void createAttendance() {
        new PayrollService().createAttendace();
    }
}
