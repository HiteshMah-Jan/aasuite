/*
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import bean.sales.PaymentLineItem;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
    template=template.screen.TemplateTabPage.class, showChart=true,
    title="Collected", gridCount=8,
    columnSearch = {"paymentFor","oldPaymentFor","adjustmentStr","orNumber","orDate",
    	"amountPaid","discount",
    	"surchargePaid","discountSurcharge",
    	"orAmount"},
    sumFooter="5,6,7,8,9", editableCol="",
    orderBy="a.orDate",
    tableColumnWidth="55,55,5"
    )
@Displays({
        @Display(name="orType", label="OR Type", type="Label"),
        @Display(name="orNumber", label="OR", type="Label"),
        @Display(name="orDate", type="Label", label="Date"),
        @Display(name="paymentDate"),
        @Display(name="paymentFor", label="Code", width=-1),
        @Display(name="oldPaymentFor", label="Other\nCode", width=-1),

        @Display(name="balance", label="Tuition\nBalance"),
        @Display(name="surchargePaid", label="Surcharge\nPaid", width=-1),
        @Display(name="discount", label="Tuition\nDiscount", width=-1),
        @Display(name="amountPaid", label="Amount\nPaid"),
        @Display(name="discountSurcharge", label="Surcharge\nDiscount", width=-1),

        @Display(name="description", gridFieldWidth=7, width=-1),

        
        @Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
//        @Display(name="bounceCheck1", label="Bounce", type="CheckBox", labelTop=true),
        @Display(name="accountNumber2", labelTop=true, label="Acc.#"),
        @Display(name="amount2", labelTop=true, label="Amount"),
//        @Display(name="bounceCheck2", label="Bounce", labelTop=true, type="CheckBox"),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
//        @Display(name="bounceCheck3", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
//        @Display(name="bounceCheck4", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
//        @Display(name="bounceCheck5", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
//        @Display(name="bounceCheck6", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
//        @Display(name="bounceCheck7", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
//        @Display(name="bounceCheck8", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
//        @Display(name="bounceCheck9", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true),
//        @Display(name="bounceCheck10", hideLabel=true, type="CheckBox")

        @Display(name="adjustmentStr", label="-", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(gridCount=12, title="Check", fields={"bank1","accountNumber1","amount1","bounceCheck1",
    "bank2","accountNumber2","amount2","bounceCheck2",
    "bank3","accountNumber3","amount3","bounceCheck3",
    "bank4","accountNumber4","amount4","bounceCheck4",
    "bank5","accountNumber5","amount5","bounceCheck5",
    "bank6","accountNumber6","amount6","bounceCheck6",
    "bank7","accountNumber7","amount7","bounceCheck7",
    "bank8","accountNumber8","amount8","bounceCheck8",
    "bank9","accountNumber9","amount9","bounceCheck9",
    "bank10","accountNumber10","amount10","bounceCheck10"
    })
})
//@ActionButtons({
//    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
//    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
//})
@ChildRecords({
    @ChildRecord(entity=PaymentLineItem.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PaymentLineItem a WHERE a.paymentId=${seq}", title="Payment Detail")
})
public class PaymentCashierPaymentExt extends bean.sales.Payment implements Serializable {
    public static void main(String[] args) {
        view(PaymentCashierPaymentExt.class);
    }
}