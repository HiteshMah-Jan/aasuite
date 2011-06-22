/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PaymentPartialDetail")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"referenceNumber", "amount", "paymentDate"})
@Displays({
        @Display(name="seq"),
        @Display(name="paymentId"),
        @Display(name="referenceNumber"),
        @Display(name="amount"),
        @Display(name="paymentDate")
})
public class PaymentPartialDetail extends AbstractIBean implements Serializable {
    @Id
     @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "paymentId", nullable = false)
    public int paymentId;
    @Column(name = "referenceNumber", length = 50)
    public String referenceNumber;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "paymentDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date paymentDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    @Override
    public String toString() {
        return referenceNumber;
    }
}
