/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import javax.swing.JComponent;

/**
 *
 * @author Charliemagne Mark
 */
public class EmployeeFaculty_RULE extends Employee_RULE {
    @Override
    public void runOnClick(JComponent comp) {
    	super.runOnClick(comp);
//        Log.out("ON CLICK for ",comp.getName());
        if ("btnAddFacultyAccount".equals(comp.getName())) {
            addToGroup("FACULTY");
        }
        else if ("btnAddSRUAccount".equals(comp.getName())) {
            addToGroup("SRU");
        }
        else if ("btnCashierAccount".equals(comp.getName())) {
            addToGroup("CASHIER");
        }
    }
}
