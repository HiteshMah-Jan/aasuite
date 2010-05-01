package ui;

import component.TabPanelForm;

public class AdmissionDischargeForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Employee";
    }
    @Override
    public String tabs() {
        return "EmployeeHospital,EmployeeInactiveExt";
    }

}
