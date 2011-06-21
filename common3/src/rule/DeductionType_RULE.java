/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.hr.Employee;
import bean.reference.DeductionType;
import java.util.List;
import javax.swing.JComponent;
import template.screen.TablePopup;

/**
 *
 * @author Entokwaa
 */
public class DeductionType_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnGenerateTable".equals(comp.getName())) {
            generateTable();
        }
        else if ("btnTest".equals(comp.getName())) {
        	testConfig();
        }
    }

    private void generateTable() {
    	new DeductionType().runSetup();
	}

	protected void testConfig() {
        DeductionType type = (DeductionType) getBean();
        List<Employee> lst = type.list("SELECT a FROM Employee a");
        type.setEmployeeValue(lst);
        TablePopup.showRecords("Employee Calculation Test", lst, Employee.class, "lastName","firstName","basicPay","transientCalculation");
    }
}
