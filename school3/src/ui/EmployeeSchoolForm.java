/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import component.TabPanelForm;
/**
 *
 * @author Charliemagne Mark
 */
public class EmployeeSchoolForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Employee";
    }
    @Override
    public String tabs() {
        return "EmployeeFaculty,EmployeeInactiveExt";
    }

}
