/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerQuote")
public class CustomerQuote extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "customerId", nullable = false)
    public int customerId;
    @Column(name = "quoteDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date quoteDate;
    @Column(name = "quoteUntilDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date quoteUntilDate;
    @Column(name = "subject")
    public String subject;
    @Column(name = "status")
    public String status;

    @Column(name = "contractTerms")
    public String contractTerms;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "paymentTerms")
    public String paymentTerms;
    @Column(name = "quoteAmount")
    public double quoteAmount;
    @Column(name = "customerRequestAmount")
    public double customerRequestAmount;

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getCustomerRequestAmount() {
        return customerRequestAmount;
    }

    public void setCustomerRequestAmount(double customerRequestAmount) {
        this.customerRequestAmount = customerRequestAmount;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(double quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Date getQuoteUntilDate() {
        return quoteUntilDate;
    }

    public void setQuoteUntilDate(Date quoteUntilDate) {
        this.quoteUntilDate = quoteUntilDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "subject");
	}
}
