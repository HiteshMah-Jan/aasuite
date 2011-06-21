/*
 * Payment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import bean.*;
import bean.accounting.Invoice;

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
@Table(name = "Billing")
@DiscriminatorColumn(name = "form", discriminatorType = DiscriminatorType.STRING)
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"paidBy", "amount", "paymentDate"})
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM BillingDetail a WHERE a.billingId=${seq}", title="Billing Detail")
})
@Displays({
        @Display(name="soldDate"),
        @Display(name="soldBy"),
        @Display(name="paymentDate"),
        @Display(name="dueDate"),
        @Display(name="amount"),
        @Display(name="paid"),
        @Display(name="partialPaymentAmount"),
        @Display(name="partialPaidOnly"),
        @Display(name="paidBy"),
        @Display(name="description"),
        @Display(name="invoiceId")
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
public class Billing extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "paidTo")
    public int paidTo;
    @Column(name = "paymentDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date paymentDate;
    @Column(name = "dueDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date dueDate;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "paid")
    public boolean paid;
    @Column(name = "partialPaymentAmount")
    public double partialPaymentAmount;
    @Column(name = "partialPaidOnly")
    public boolean partialPaidOnly;
    @Column(name = "paidBy", nullable = false)
    public int paidBy;
    @Column(name = "recordId", nullable = false)
    public int recordId;
    @Column(name = "form", length = 50)
    public String form;
    @Column(name = "line")
    public int line;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "description", length = 200)
    public String description;
    @Column(name = "invoiceId")
    public int invoiceId;
    @Column(name = "paymentId")
    public int paymentId;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(int paidBy) {
        this.paidBy = paidBy;
    }

    public int getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(int paidTo) {
        this.paidTo = paidTo;
    }

    public boolean isPartialPaidOnly() {
        return partialPaidOnly;
    }

    public void setPartialPaidOnly(boolean partialPaidOnly) {
        this.partialPaidOnly = partialPaidOnly;
    }

    public double getPartialPaymentAmount() {
        return partialPaymentAmount;
    }

    public void setPartialPaymentAmount(double partialPaymentAmount) {
        this.partialPaymentAmount = partialPaymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Person getCustomer() {
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",paidBy);
    }
    public Person getSeller() {
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",paidTo);
    }
    public Invoice extractInvoice() {
        if (this.invoiceId == 0) {
            return null;
        }
        Invoice inv = (Invoice) AbstractIBean.firstRecord("SELECT a FROM Invoice a WHERE a.seq=",this.invoiceId);
        return inv;
    }

    @Transient
    public String customerName;
    public String getCustomerName() {
        Person p = (Person) Person.find(Person.class, paidBy);
        if (p==null) return "";
        return p.toString();
    }
}
