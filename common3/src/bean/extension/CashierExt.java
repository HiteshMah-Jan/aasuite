/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.*;
import template.screen.*;
/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, 
    criteriaSearch = {"paidBy","amount","paymentDate"},
    columnSearch = {"paidBy","amount","paid","invoiceId","paymentDate"})
@Displays({
        @Display(name="paidBy"),
        @Display(name="amount", mergeFields={"paid","invoiceId"}),
        @Display(name="paid"),
        @Display(name="invoiceId", type="Label"),
        @Display(name="paymentDate"),
        @Display(name="dueDate"),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=100, mandatory=true),
        @Display(name="description", gridFieldWidth=7)
})
@ActionButtons({
    @ActionButton(name="btnCashierConsole", label="Cashier Console", parentOnly=false)
})
@Reports({
    @template.Report(reportFile="DailyInvoice", reportTitle="Daily Invoice", reportSql = "")
})
public class CashierExt extends bean.accounting.Payment {
    public static void main(String[] args) {
        view(CashierExt.class);
    }
}
