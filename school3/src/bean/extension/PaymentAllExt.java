/*
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Student;
import bean.accounting.PaymentLineItem;
import bean.reference.Bank;
import java.io.Serializable;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template=template.screen.TemplateTabSinglePage.class, rule="PaymentEnrollment", title="Collected", gridCount=4, 
    columnSearch = {"orNumber","cashier","paidBy", "dueDate", "amount", "paid", "paymentDate"}, 
    select="SELECT a FROM PaymentEnrollment a WHERE a.paid=true ", orderBy="a.paymentDate", autoResizeTable=false)
@Displays({
        @Display(name="paidBy", label="Student", type="PopSearch", gridFieldWidth=3, linktoBean=Student.class, width=-1),
        @Display(name="cashier", type="Label", gridFieldWidth=3),
        
        @Display(name="amount", type="Label"),
        @Display(name="surcharge", type="Label"),
        
        @Display(name="dueDate"),
        @Display(name="orDate"),
        @Display(name="orNumber", button="Change", width=120, gridFieldWidth=3),
        
        @Display(name="description", gridFieldWidth=7, width=-1),
        
//        @Display(name="forApproval1", label="For Tuition Discount Approval", mergeFields={"approvalStatus1"}, gridFieldWidth=3),
//        @Display(name="approvalStatus1", label="Status", type="Combo", modelCombo={"APPROVED","NOT APPROVED"}, width=120),
//        @Display(name="forApproval2", label="For Surcharge Discount Approval", mergeFields={"approvalStatus2"}, gridFieldWidth=3),
//        @Display(name="approvalStatus2", label="Status", type="Combo", modelCombo={"APPROVED","NOT APPROVED"}, width=120),
        
//        @Display(name="bank1", label = "Bank Name", type="PopSearch", linktoBean=Bank.class, labelTop=true),
        @Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
        @Display(name="bounceCheck1", label="Bounce", type="CheckBox", labelTop=true),
        //        @Display(name="bank2", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber2", labelTop=true, label="Acc.#"),
        @Display(name="amount2", labelTop=true, label="Amount"),
        @Display(name="bounceCheck2", label="Bounce", labelTop=true, type="CheckBox"),
       
        //        @Display(name="bank3", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
        @Display(name="bounceCheck3", hideLabel=true, type="CheckBox"),
        
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
        @Display(name="bounceCheck4", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank5", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
        @Display(name="bounceCheck5", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank6", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
        @Display(name="bounceCheck6", hideLabel=true, type="CheckBox"),
        //        @Display(name="bank7", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
        @Display(name="bounceCheck7", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank8", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
        @Display(name="bounceCheck8", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank9", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
        @Display(name="bounceCheck9", hideLabel=true, type="CheckBox"),
       //        @Display(name="bank10", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true),
        @Display(name="bounceCheck10", hideLabel=true, type="CheckBox")
})
@DisplayGroups({
//    @DisplayGroup(gridCount=8, title="Discount Approval", fields={
//    "forApproval1","approvalStatus1",
//    "forApproval2","approvalStatus2"
//    }),
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
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
@ChildRecords({ 
    @ChildRecord(entity=PaymentLineItem.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PaymentLineItem a WHERE a.paymentId=${seq}", title="Payment Detail")
})
public class PaymentAllExt extends bean.accounting.EnrollmentAR implements Serializable {
    public static void main(String[] args) {
        view(PaymentAllExt.class);
    }
}