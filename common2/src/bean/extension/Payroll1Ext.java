/*
 * Payroll.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Employee;
import bean.accounting.Payroll;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateDefault.class, gridCount = 10, 
    columnSearch = {"department","employeeName"})
@Displays({
        @Display(name = "employeeId", linktoBean=Employee.class, label="Employee", gridFieldWidth=5, width=-1, type="PopSearch"),
        @Display(name = "department", type="Label"),
        @Display(name = "employeeStatus", type="Label", label="Status"),
        
        @Display(name = "grossPay"),
        @Display(name = "netPay"),
        @Display(name = "basicPay"),
        @Display(name = "m13", label="13th Month"),
        @Display(name = "m13Tax", label="13 Tax"),
        @Display(name = "taxableIncome"),
        @Display(name = "taxExemption"),
        @Display(name = "tax"),
        @Display(name = "withholdingTax", type="Label"),
        @Display(name = "taxReturn"),
        
        @Display(name = "overload", label="Coor. Fee"),
        @Display(name = "substitution", label="Adv. Fee"),
        @Display(name = "transpoAllowance", label="Moderator Fee"),
        @Display(name = "mealAllowance", label="Admin Head"),
        @Display(name = "colaAllowance", label="Form of Increase"),
        @Display(name = "otherAllowance", label="10% Inc SY(RICE ALLOW)"),
        @Display(name = "a1", label="MA"),
        
        @Display(name = "pagibig"),
        @Display(name = "philhealth"),
        @Display(name = "sss"),
        @Display(name = "absent"),
        @Display(name = "tardiness"),

        @Display(name = "sssLoan"),
        @Display(name = "salaryLoan"),
        @Display(name = "pagibigLoan"),
        @Display(name = "bankLoan", label="Bank Loan"),
        @Display(name = "otherLoan", label="Other Loan")
})
@DisplayGroups({
    @DisplayGroup(title="Allowances and Adjustments", gridCount=8, fields={"overload","substitution","transpoAllowance",
        "mealAllowance","colaAllowance","otherAllowance","a1"}),
    @DisplayGroup(title="Less and Loans", gridCount=10, fields={"pagibig","philhealth","sss","absent","tardiness"
        ,"sssLoan","salaryLoan","pagibigLoan","bankLoan","otherLoan"})
})
@template.ActionButtons({
    @template.ActionButton(name="btnViewLeave", label="Leave", parentOnly=false),
    @template.ActionButton(name="btnViewAttendance", label="Attendance", parentOnly=false),
    @template.ActionButton(name="btnRecalculate", label="Recalculate", parentOnly=false),
    @template.ActionButton(name="btnViewHistory", label="View History", parentOnly=false)
//    @template.ActionButton(name="btnPrintPayslip", label="Print Payslip", parentOnly=false)
})
public class Payroll1Ext extends Payroll {
}
