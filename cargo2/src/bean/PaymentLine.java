/*
 * PaymentLine.java
 * 
 * Created on Sep 30, 2007, 8:02:09 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.reference.Airport;
import bean.reference.Country;
import bean.reference.State;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PaymentLine")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"reason","amount"})
@Displays({
    @Display(name = "reason", width=200),
    @Display(name = "amount")
})
public class PaymentLine extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "reason", length=150)
    public String reason;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "paymentSeq", nullable = false)
    public int paymentSeq;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentSeq() {
        return paymentSeq;
    }

    public void setPaymentSeq(int paymentSeq) {
        this.paymentSeq = paymentSeq;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
