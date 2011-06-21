/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.Person;
import bean.accounting.Payment;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Payment")
@DiscriminatorValue("GENERIC")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"customerName","amount","paymentDate","accountNumber","description"})
@Displays({
        @Display(name="paidBy", type="PopSearch", linktoBean=Person.class),
        @Display(name="amount", mergeFields={"paid","invoiceId"}),
        @Display(name="invoiceId", type="Label"),
        @Display(name="paymentDate"),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=100, mandatory=true, gridFieldWidth=3),
        @Display(name="description", gridFieldWidth=3, width=-1, type="Combo", modelCombo={"ANNUAL","JS PROM","FIELD TRIP"})
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false)
})
public class PaymentGeneric extends Payment {
    public PaymentGeneric() {
        accountNumber = "102";
        paymentDate = constants.Constants.useDate;
        dueDate = constants.Constants.useDate;
        form = "GENERIC";
        recordId = 1;
    }
}
