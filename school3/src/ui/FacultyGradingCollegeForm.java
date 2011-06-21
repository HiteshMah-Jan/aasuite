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
public class FacultyGradingCollegeForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Faculty Grading Tool";
    }
    
    @Override
    public String tabs() {
        return "FacultyGradingTaskCollegeExt,StudentGradingCollegeExt";
    }

}
