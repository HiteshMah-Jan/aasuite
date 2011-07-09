/*
 * Payment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.*;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PaymentSurchargeHistory")
@DiscriminatorColumn(name = "form", discriminatorType = DiscriminatorType.STRING)
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {""})

@Displays({
        @Display(name="surcharge"),
        @Display(name="amountDue"),
        @Display(name="processDate"),
        @Display(name="dueDate")
        
})

public class PaymentSurchargeHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "paymentId")
    public int paymentId;
    @Column(name = "processDate")
    @Temporal(value = TemporalType.DATE)
    public Date processDate; 
    @Column(name = "dueDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date dueDate;
    @Column(name = "amountDue")
    public double amountDue;
    @Column(name = "surcharge")
    public double surcharge;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

  
    
}
