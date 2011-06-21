/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Payment")
@DiscriminatorValue(value = "ONLINE")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"amount", "paymentDate"})
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${paymentId}", title="Payment Detail"),
    @ChildRecord(entity=PaymentPartialDetail.class, sql="SELECT a FROM PartialPaymentDetail a WHERE a.paymentId=${paymentId}", title="Partial Payment Detail")
})
@Displays({
        @Display(name="paymentDate"),
        @Display(name="amount"),
        @Display(name="paid"),
        @Display(name="partialPaymentAmount"),
        @Display(name="partialPaidOnly"),
        @Display(name="invoiceId")
})
public class PaymentOnline extends Payment implements Serializable {
}
