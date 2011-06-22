/*
 * PayrollPeriod.java
 *
 * Created on Nov 23, 2007, 7:42:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.payroll.PayrollPeriod;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, criteriaSearch = {"payrollName", "startDate"}, columnSearch = {"payrollName", "startDate", "endDate"})
@Displays({
        @Display(name="payrollName"),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name = "basicPay", type="Label"),        
        @Display(name = "taxableIncome", type="Label"),
        @Display(name = "taxExemption", type="Label"),
        @Display(name = "withholdingTax",type="Label"),
        @Display(name = "taxReturn", type="Label"),
        
        @Display(name = "overload", type="Label",addInfoOnly=true, label="Coor. Fee"),
        @Display(name = "substitution", type="Label",addInfoOnly=true, label="Adv. Fee"),
        @Display(name = "transpoAllowance", label="Moderator Fee",addInfoOnly=true, type="Label"),
        @Display(name = "mealAllowance", label="Admin Head",addInfoOnly=true, type="Label"),
        @Display(name = "colaAllowance", label="Fringe Benefit",addInfoOnly=true, type="Label"),
        
        @Display(name = "pagibig",addInfoOnly=true, type="Label"),
        @Display(name = "philhealth",addInfoOnly=true, type="Label"),
        @Display(name = "sss",addInfoOnly=true, type="Label"),
        @Display(name = "absent",addInfoOnly=true, type="Label"),
        @Display(name = "tardiness",addInfoOnly=true, type="Label"),

        @Display(name = "sssLoan",addInfoOnly=true, type="Label"),
        @Display(name = "salaryLoan",addInfoOnly=true, type="Label"),
        @Display(name = "pagibigLoan",addInfoOnly=true, type="Label"),
        @Display(name = "otherLoan",addInfoOnly=true, type="Label", label="Bank Loan"),
        @Display(name = "m13",addInfoOnly=true, type="Label", label="13th Month"),
        
        @Display(name = "m20", label="Rice Allowance", addInfoOnly=true, type="Label")
})
@template.ChildRecords(
    value={@template.ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","payrollPeriodId"}, title="Employee Payroll",entity=Payroll1Ext.class,sql="SELECT a FROM Payroll a WHERE a.payrollPeriodId=${seq}")},
    info={@template.ParentAddInfo(title="Additional Information", gridCount=4, 
            displayFields={"overload","substitution","transpoAllowance","mealAllowance","colaAllowance",
                "pagibig","philhealth","sss","absent","tardiness",
                "sssLoan","salaryLoan","pagibigLoan","otherLoan","m13","m20"})}
)
@template.ActionButtons({
    @template.ActionButton(name="btnGeneratePayroll", label="Generate Payroll"),
    @template.ActionButton(name="btnCreatePayroll", label="Compute Payroll"),
    @template.ActionButton(name="btnPostGL", label="Post GL"),
    @template.ActionButton(name="btnViewGL", label="View GL")
})
@Reports({
    @template.Report(reportFile="PaySlip", reportTitle="Payslip", reportSql = "${seq}")
})
public class PayrollPeriod1Ext extends PayrollPeriod {
}
