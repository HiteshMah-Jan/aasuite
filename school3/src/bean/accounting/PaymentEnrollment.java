/*
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.accounting.EnrollmentAR;
import bean.accounting.PaymentDetail;
import bean.accounting.PaymentPartialDetail;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.Reports;
/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Payment")
@UITemplate(gridCount = 6,
        columnSearch = {"paymentFor","amountPaid","overallAmountPaid","overallBalance","paid","dueDate","orNumber"},
        sumFooter = "1,2,3,4,5", canDelete=false, canNew=false
)
@DiscriminatorValue("ENROLLMENT")
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${paymentId}", title="Payment Detail"),
    @ChildRecord(entity=PaymentPartialDetail.class, sql="SELECT a FROM PaymentPartialDetail a WHERE a.paymentId=${paymentId}", title="Partial Payment Detail")
})
@Displays({
        @Display(name="amount"),
        @Display(name="dueDate"),

        @Display(name="amountPaid"),
        @Display(name="overallAmountPaid", label="Overall Amt Pd"),
        
        @Display(name="paymentDate"),
        @Display(name="balance", type="Label"),
        @Display(name="overallBalance", type="Label"),
        
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=-1, mandatory=true),
        @Display(name="description",gridFieldWidth=3,width=-1)
})
//@ActionButtons({
//    @ActionButton(name="btnViewParticulars", label="Particulars", parentOnly=false),
//    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
//    @ActionButton(name="btnNextPaymentDue", label="Next Payment", parentOnly=false),
//    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
//})
@Reports({ 
      @template.Report(reportFile="Receivable", reportTitle="Account Receivables", reportSql="${seq}")
}) 

public class PaymentEnrollment extends Payment implements Serializable {
}
