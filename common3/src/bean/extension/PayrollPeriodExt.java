/*
 * PayrollPeriod.java
 *
 * Created on Nov 23, 2007, 7:42:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.accounting.PayrollPeriod;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, showChart=true, gridCount = 8, criteriaSearch = {"payrollName", "startDate"}, columnSearch = {"payrollName", "startDate", "endDate"})
@Displays({
        @Display(name="payrollName"),
//        @Display(name="payrollType",type="Combo",modelCombo={"PER HOUR","DAILY","WEEKLY","BIMONTHLY","MONTHLY"}),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="countDays"),
//        @Display(name="holidayCount"),
        
        @Display(name = "taxableIncome", type="Label"),
        @Display(name = "taxExemption", type="Label"),
        @Display(name = "tax", type="Label"),
        @Display(name = "withholdingTax",type="Label"),
        
//        @Display(name = "basicPay", type="Label"),

        @Display(name = "grossPay", type="Label"),        
//        @Display(name = "otPay", type="Label"),
//        @Display(name = "nightDiff", type="Label"),
//        @Display(name = "holidayPay", type="Label"),
        @Display(name = "overload", type="Label"),
        @Display(name = "substitution", type="Label"),
        @Display(name = "taxReturn", type="Label"),
        
        @Display(name = "transpoAllowance", label="Transpo",addInfoOnly=true, type="Label"),
        @Display(name = "mealAllowance", label="Meal",addInfoOnly=true, type="Label"),
        @Display(name = "colaAllowance", label="COLA",addInfoOnly=true, type="Label"),
        @Display(name = "otherAllowance", label="Others",addInfoOnly=true, type="Label"),
//        @Display(name = "allowance", type="Label", label="Total Allowance",addInfoOnly=true, type="Label"),

        @Display(name = "absent",addInfoOnly=true, type="Label"),
        @Display(name = "tardiness",addInfoOnly=true, type="Label"),
        @Display(name = "undertime",addInfoOnly=true, type="Label"),
//        @Display(name = "late",addInfoOnly=true, type="Label"),
        @Display(name = "pagibig",addInfoOnly=true, type="Label"),
        @Display(name = "philhealth",addInfoOnly=true, type="Label"),
        @Display(name = "sss",addInfoOnly=true, type="Label"),
//        @Display(name = "deduction", type="Label", label="Total Deduction",addInfoOnly=true),

        @Display(name = "m13",addInfoOnly=true, type="Label"),
        @Display(name = "m13Tax",addInfoOnly=true, type="Label"),
        @Display(name = "m14",addInfoOnly=true, type="Label"),
        @Display(name = "m14Tax",addInfoOnly=true, type="Label"),
        @Display(name = "teachersDayBonus",addInfoOnly=true, type="Label"),
        @Display(name = "chineseNewYearBonus",addInfoOnly=true, type="Label"),
        
        @Display(name = "sssLoan",addInfoOnly=true, type="Label"),
        @Display(name = "salaryLoan",addInfoOnly=true, type="Label"),
        @Display(name = "pagibigLoan",addInfoOnly=true, type="Label"),
        @Display(name = "otherLoan",addInfoOnly=true, type="Label")
//        @Display(name = "loan", type="Label", label="Total Loan")
})
@DisplayGroups({
    @DisplayGroup(title="Basic Detail", gridCount=10, fields={"grossPay","otPay","nightDiff","holidayPay","overload","substitution","taxReturn"}),
//    @DisplayGroup(title="Allowance Detail", gridCount=8, fields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance"}),
//    @DisplayGroup(title="Deductions Detail", gridCount=8, fields={"absent","tardiness","undertime","late","pagibig","philhealth","loan","sss","deduction"}),
//    @DisplayGroup(title="Extra Months", gridCount=8, fields={"m13","m13Tax","m14","m14Tax"}),
    @DisplayGroup(title="Tax Details", gridCount=8, fields={"taxableIncome","tax","taxExemption","withholdingTax"})
})
@template.ChildRecords(
    value={@template.ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","payrollPeriodId"}, title="Employee Payroll",entity=PayrollExt.class,sql="SELECT a FROM Payroll a WHERE a.payrollPeriodId=${seq}")},
    info={@template.ParentAddInfo(title="Additional Information", 
            displayFields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance","teachersDayBonus","chineseNewYearBonus",
                "absent","tardiness","undertime","late","pagibig","philhealth","sss","deduction",
                "sssLoan","salaryLoan","pagibigLoan","otherLoan",
                "m13","m13Tax","m14","m14Tax"})}
)
@template.ActionButtons({
    @template.ActionButton(name="btnCreatePayroll", label="Compute Payroll"),
    @template.ActionButton(name="btnPostGL", label="Post GL"),
    @template.ActionButton(name="btnViewGL", label="View GL")
})
@Reports({
    @template.Report(reportFile="PayrollPeriodPayslip", reportTitle="Payslip", reportSql = "${seq}")
})
public class PayrollPeriodExt extends PayrollPeriod {
}
