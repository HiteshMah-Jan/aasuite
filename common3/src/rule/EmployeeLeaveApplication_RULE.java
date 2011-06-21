/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.accounting.AccountPayable;
import bean.accounting.Expense;
import bean.accounting.GLPostingScript;
import bean.person.EmployeeLeaveApplication;
import java.util.List;
import javax.swing.JComponent;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import template.screen.TablePopup;
import util.DateUtil;

/**
 *
 * @author Entokwaa
 */
public class EmployeeLeaveApplication_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
      
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnPrintEmployeeLeave")) {
            printEmployeeLeaveApplication();
        }
    }
        
    private void printEmployeeLeaveApplication() {
        EmployeeLeaveApplication ela = (EmployeeLeaveApplication) this.getBean();
           AbstractReportTemplate.getInstance().showReportFromFileTemplate("EmployeeLeaveApplication", ela.personId);
 }
}