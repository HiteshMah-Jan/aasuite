/*
 * PatientFee.java
 *
 * Created on Nov 13, 2007, 9:03:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Payment")
@UITemplate(gridCount = 4, columnSearch = {"dueDate", "amount", "paid", "paymentDate"})
@DiscriminatorValue("PATIENTFEE")
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
    @ActionButton(name="btnUpdate", label="Update", parentOnly=false),
    @ActionButton(name="btnMarkAsPaid", label="Mark As Paid", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
public class PaymentPatient extends Payment implements Serializable {
}
