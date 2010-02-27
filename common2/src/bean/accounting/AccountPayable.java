/*
 * Accountpayable.java
 *
 * Created on Dec 10, 2007, 2:01:33 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.ActionButtons;
import template.ActionButton;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "AccountPayable")
@UITemplate(template = template.screen.TemplateTabPage.class, showChart = false, gridCount = 6, columnSearch = {"dueDate", "company", "amount"})
@Displays({
    @Display(name = "dueDate"),
    @Display(name = "invoiceId"),
    @Display(name = "amount"),
    @Display(name = "company", gridFieldWidth = 5, width = -1),
    @Display(name = "remarks", gridFieldWidth = 5, width = -1)
})
@ActionButtons({
    @ActionButton(label = "Pay Now", name = "btnPayNow")
})
public class AccountPayable extends AbstractIBean implements Serializable, IGL {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "expenseId", nullable = false)
    public int expenseId;
    @Column(name = "dueDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date dueDate;
    @Column(name = "invoiceId")
    public int invoiceId;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "company")
    public String company;
    @Column(name = "currency")
    public String currency;
    @Column(name = "exchangeRate")
    public double exchangeRate;
    @Column(name = "paymentId")
    public int paymentId;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "posted")
    public boolean posted;
    @Column(name = "paid")
    public boolean paid;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "company");
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return invoiceId + " - " + amount;
    }

    public String extractGLSubType() {
        return "";
    }
    
    public boolean hardcodePosting() {
        return false;
    }

    public String extractChargeDepartment() {
        if (isEmptyKey() || expenseId==0) return "";
        Expense exp = (Expense) AbstractIBean.firstRecord("SELECT a FROM Expense a WHERE a.seq="+expenseId);
        if (exp==null) return "";
        return exp.chargeDepartment;
    }

    @Override
    public String extractCounterPostAccountNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String extractAccount() {
        Expense exp = (Expense) AbstractIBean.firstRecord("SELECT a FROM Expense a WHERE a.seq="+expenseId);
        if (exp!=null) return exp.accountType;
        return "";
    }
    
    public String extractDefaultFormula() {
        return 
                "GL.debit ACCOUNTPAYABLE, now, ACCOUNTPAYABLE.extractAccount(), ACCOUNTPAYABLE.totalAmount, ACCOUNTPAYABLE.remarks;" +
                "\nGL.credit ACCOUNTPAYABLE, now, \"102\", ACCOUNTPAYABLE.totalAmount, \"CASH\";";
    }
}
