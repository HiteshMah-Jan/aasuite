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
import java.io.Serializable;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
    template=template.screen.TemplateTabPage.class, showChart=true,
    title="Collected", gridCount=8,
    columnSearch = {"paymentFor","description", "surcharge", "discount", "amountPaid", "balance", "orNumber"},
    sumFooter="2,3,4,5,6", editableCol="2,3,4,5",
    orderBy="a.paymentDate"
//    tableColumnWidth="30,200,30,30,30,30,40"
    )
@Displays({
        @Display(name="customerName", label="Student", type="Label"),
        @Display(name="amountPaid", type="Label"),
        @Display(name="balance", type="Label"),
        @Display(name="dueDate"),
        @Display(name="paymentDate"),
        @Display(name="description", gridFieldWidth = 3, width = -1),
        @Display(name="paidBy", label="Student", type = "PopSearch", linktoBean=Student.class, addInfoOnly=true)
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
//        @ActionButton(name="btnAssessNoEnrollment", label="Auto Assess No Enrollment", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
@ChildRecords({ 
    @ChildRecord(entity=PaymentLineItem.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PaymentLineItem a WHERE a.paymentId=${seq}", title="Payment Detail")
})
public class PaymentExt extends bean.accounting.EnrollmentAR implements Serializable {
    public static void main(String[] args) {
        view(PaymentExt.class);
    }
}