/*
 * Payroll.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.hr.Employee;
import bean.payroll.Payroll;
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
@UITemplate(template = TemplateDefault.class, gridCount = 8, 
    columnSearch = {"department","employeeName","grossPay","sss","withholdingTax","loan","netPay"})
@Displays({
        @Display(name = "employeeId", linktoBean=Employee.class, label="Employee", gridFieldWidth=7, width=-1, type="PopSearch"),
//        @Display(name = "employeeType", type="Label"),
//        @Display(name = "hireDate", type="Label"),
        @Display(name = "department", type="Label"),
        @Display(name = "employeeStatus", type="Label", label="Status"),
        @Display(name = "totalDays"),
        @Display(name = "netPay", type="Label"),
        
        @Display(name = "taxableIncome"),
        @Display(name = "taxExemption"),
        @Display(name = "tax"),
        @Display(name = "withholdingTax",type="Label"),
        
//        @Display(name = "basicPay", type="Label"),

        @Display(name = "grossPay"),        
//        @Display(name = "otPay"),
//        @Display(name = "nightDiff"),
//        @Display(name = "holidayPay"),
        @Display(name = "overload"),
        @Display(name = "substitution"),
        @Display(name = "taxReturn"),
        
        @Display(name = "transpoAllowance", label="Transpo"),
        @Display(name = "mealAllowance", label="Meal"),
        @Display(name = "colaAllowance", label="COLA"),
        @Display(name = "otherAllowance", label="Others"),
        @Display(name = "allowance", type="Label", label="Total Allowance"),

        @Display(name = "absent"),
        @Display(name = "tardiness"),
        @Display(name = "undertime"),
//        @Display(name = "late"),
        @Display(name = "pagibig"),
        @Display(name = "philhealth"),
        @Display(name = "sss"),
        @Display(name = "deduction", type="Label", label="Total Deduction"),

        @Display(name = "m13"),
        @Display(name = "m13Tax"),
        @Display(name = "m14"),
        @Display(name = "m14Tax"),
        @Display(name = "teachersDayBonus"),
        @Display(name = "chineseNewYearBonus"),
        
        @Display(name = "sssLoan"),
        @Display(name = "salaryLoan"),
        @Display(name = "pagibigLoan"),
        @Display(name = "otherLoan"),
        @Display(name = "loan", type="Label", label="Total Loan")
})
@DisplayGroups({
    @DisplayGroup(title="Basic Detail", gridCount=10, fields={"grossPay","otPay","nightDiff","holidayPay","overload","substitution","taxReturn"}),
    @DisplayGroup(title="Allowance Detail", gridCount=10, fields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance"}),
    @DisplayGroup(title="Deductions Detail", gridCount=8, fields={"absent","tardiness","undertime","late","pagibig","philhealth","sss","deduction"}),
    @DisplayGroup(title="Loan Detail", gridCount=10, fields={"sssLoan","salaryLoan","pagibigLoan","otherLoan","loan"}),
    @DisplayGroup(title="Extra Months / Bonus", gridCount=8, fields={"m13","m13Tax","m14","m14Tax","teachersDayBonus","chineseNewYearBonus"}),
    @DisplayGroup(title="Tax Details", gridCount=8, fields={"taxableIncome","tax","taxExemption","withholdingTax"})
})
@template.ActionButtons({
    @template.ActionButton(name="btnViewLeave", label="Leave", parentOnly=false),
    @template.ActionButton(name="btnViewAttendance", label="Attendance", parentOnly=false),
    @template.ActionButton(name="btnRecalculate", label="Recalculate", parentOnly=false),
    @template.ActionButton(name="btnViewHistory", label="View History", parentOnly=false),
    @template.ActionButton(name="btnPrintPayslip", label="Print Payslip", parentOnly=false)
//    @template.ActionButton(name="btnPostGL", label="Post to GL")
})
public class PayrollExt extends Payroll {
}
