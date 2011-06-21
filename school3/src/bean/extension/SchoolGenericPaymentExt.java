/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Student;
import bean.sales.PaymentGeneric;

import java.util.Date;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"customerName","amount","paymentDate","accountNumber","description"}, 
    criteriaSearch = {"paidBy","amount","paymentDate","accountNumber","description"})
@Displays({
        @Display(name="paidBy", type="PopSearch", linktoBean=Student.class, label="Customer", gridFieldWidth=5, width=-1),

        @Display(name="amount", width=-1),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, mandatory=true, width=-1),
        @Display(name="cashier", type="Label"),

        @Display(name="paymentDate"),
        @Display(name="orDate"),
        @Display(name="invoiceId", type="Label"),

        @Display(name="orNumber", button="Change", gridFieldWidth=5),
        @Display(name="description", gridFieldWidth=5, width=-1),
        
//        @Display(name="bank1", label = "Bank Name", type="PopSearch", linktoBean=Bank.class, labelTop=true),
        @Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
//        @Display(name="bank2", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber2", hideLabel=true),
        @Display(name="amount2", hideLabel=true),
//        @Display(name="bank3", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
//        @Display(name="bank4", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
//        @Display(name="bank5", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
//        @Display(name="bank6", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
//        @Display(name="bank7", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
//        @Display(name="bank8", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
//        @Display(name="bank9", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
//        @Display(name="bank10", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true)
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false)
})
@DisplayGroups({
    @DisplayGroup(gridCount=4, title="Check", fields={"bank1","accountNumber1","amount1",
    "bank2","accountNumber2","amount2",
    "bank3","accountNumber3","amount3",
    "bank4","accountNumber4","amount4",
    "bank5","accountNumber5","amount5",
    "bank6","accountNumber6","amount6",
    "bank7","accountNumber7","amount7",
    "bank8","accountNumber8","amount8",
    "bank9","accountNumber9","amount9",
    "bank10","accountNumber10","amount10"
    })
})
public class SchoolGenericPaymentExt extends PaymentGeneric {
    public static void main(String[] args) {
        view(SchoolGenericPaymentExt.class);
    }
    
    public SchoolGenericPaymentExt() {
        accountNumber = "102";
        paymentDate = constants.Constants.useDate;
        dueDate = constants.Constants.useDate;
        recordId = 1;
    }
}
