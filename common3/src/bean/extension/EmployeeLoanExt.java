/*
 * EmployeeLoan.java
 * 
 * Created on Feb 20, 2008, 8:22:32 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.hr.Employee;
import bean.person.*;
import bean.reference.LoanType;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"employeeLoanId", "employeeId", "loanType"})
@Displays({
        @Display(name="personId", gridFieldWidth = 3, label = "Employee", width = -1, type = "PopSearch", linktoBean=Employee.class),
        @Display(name="loanDate"),
        @Display(name="loanAmount"),
        @Display(name="loanType", type = "PopSearch", linktoBean = LoanType.class),
        @Display(name="approvedBy", gridFieldWidth = 3, width = -1, type = "PopSearch", linktoBean=Employee.class),
        @Display(name="countsToPay", type = "Combo", modelCombo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}),
        @Display(name="totalPaidCount"),
        @Display(name="totalPaidAmount"),
        @Display(name="loanInterest"),
        @Display(name="remarks")
})
public class EmployeeLoanExt extends EmployeeLoan {
}
