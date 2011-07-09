/*
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.accounting.Payment;
import bean.accounting.PaymentDetail;
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

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Payment")
@UITemplate(rule="PaymentSchool", gridCount = 4, columnSearch = {"dueDate", "amount", "paid", "paymentDate"})
@DiscriminatorValue("ADMISSION")
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${seq}", title="Payment Detail")
})
@Displays({
        @Display(name="amount"),
        @Display(name="dueDate"),
        @Display(name="paymentDate"),
        @Display(name="description")
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
public class PaymentAdmission extends Payment implements Serializable {
}
