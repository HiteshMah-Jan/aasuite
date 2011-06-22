package rule;

import javax.swing.JComponent;

import bean.extension.EmployeePayrollExt;
import bean.payroll.EmployeePayroll;
import bean.payroll.Payroll;

import springbean.PayrollService;
import template.screen.TablePopup;
import util.PanelUtil;

public class Payroll_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnGenerate".equals(comp.getName())) {
            generateEmployees();
        } else if ("btnEditAdjustment".equals(comp.getName())) {
            editAdjustment();
        } else if ("btnCreateSchedule".equals(comp.getName())) {
            createSchedule();
        }
    }

    private void createSchedule() {
        PayrollService.createPayroll();
        refreshRecords();
    }

    private void editAdjustment() {
        EmployeePayroll p = (EmployeePayroll) this.panel.getTabs().get(0).getBean();
        PanelUtil.popupBeanTemplate(EmployeePayrollExt.class, "Payroll Adjustment", false, p, true);
        if (PanelUtil.showPrompt(usedComp, "Would you like to recalculate payroll?")) {
            new PayrollService().recalculatePayroll(p);
        }
        redisplayRecord();
    }

    private void generateEmployees() {
        Payroll pay = (Payroll) this.getBean();
        new PayrollService().createEmployeePayroll(pay);
        redisplayRecord();
    }
}
