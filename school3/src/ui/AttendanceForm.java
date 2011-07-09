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
public class AttendanceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Attendance";
    }

    @Override
    public String tabs() {
        return "AttendanceExt";
    }

}
