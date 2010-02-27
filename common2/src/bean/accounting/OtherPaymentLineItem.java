/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
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
@Table(name = "OtherPaymentLineItem")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"otherPayment", "paymentAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="otherPaymentId"),
        @Display(name="otherPayment"),
        @Display(name="paymentAmount")
        })
public class OtherPaymentLineItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "otherPaymentId")
    public int otherPaymentId;
    @Column(name = "otherPayment")
    public String otherPayment;
    @Column(name = "paymentAmount")
    public double paymentAmount;

    public String getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(String otherPayment) {
        this.otherPayment = otherPayment;
    }

    public int getOtherPaymentId() {
        return otherPaymentId;
    }

    public void setOtherPaymentId(int otherPaymentId) {
        this.otherPaymentId = otherPaymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
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
    
	@Override
	public void setupIndex() {
		runIndex(1, "otherPaymentId");
	}
}
