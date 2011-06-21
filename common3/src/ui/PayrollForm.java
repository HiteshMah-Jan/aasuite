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
public class PayrollForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Payroll Form";
    }

    @Override
    public String tabs() {
        return "PersonAttendance,Payroll,AlphaList,PayrollAdjustmentRef,EventHoliday";
    }

}
