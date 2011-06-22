/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import bean.banking.Bank;
import bean.reference.PaymentMethod;
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
@Table(name = "BillingDetail")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"paymentMethod", "referenceNumber"})
@Displays({
        @Display(name="seq"),
        @Display(name="billingId"),
        @Display(name="paymentMethod", type = "PopSearch", gridFieldWidth = 3, width = -1, linktoBean=PaymentMethod.class),
        @Display(name="bankName", type="PopSearch", linktoBean=Bank.class),
        @Display(name="referenceNumber"),
        @Display(name="cardName"),
        @Display(name="creditCardNumber"),
        @Display(name="checkNumber"),
        @Display(name="additionalTransactionFee"),
        @Display(name="description")
})
public class BillingDetail extends AbstractIBean implements Serializable {
    @Id
   @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "billingId", nullable = false)
    public int billingId;
    @Column(name = "paymentMethod", length = 50)
    public String paymentMethod;
    @Column(name = "bankName", length = 50)
    public String bankName;
    @Column(name = "referenceNumber", length = 50)
    public String referenceNumber;
    @Column(name = "cardName", length = 50)
    public String cardName;
    @Column(name = "creditCardNumber", length = 50)
    public String creditCardNumber;
    @Column(name = "checkNumber", length = 50)
    public String checkNumber;
    @Column(name = "additionalTransactionFee")
    public double additionalTransactionFee;
    @Column(name = "description", length = 200)
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAdditionalTransactionFee() {
        return additionalTransactionFee;
    }

    public void setAdditionalTransactionFee(double additionalTransactionFee) {
        this.additionalTransactionFee = additionalTransactionFee;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
