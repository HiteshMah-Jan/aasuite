/*
 * Salesorder.java
 *
 * Created on Nov 22, 2007, 6:07:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.accounting.Invoice;
import bean.*;
import bean.reference.AccountType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import util.ScriptRunner;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "SalesOrder")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"soldBy", "soldTo"})
@Displays({
        @Display(name="salesOrderId"),
        @Display(name="soldDate"),
        @Display(name="soldBy", type = "PopSearch", linktoBean=Employee.class),
        @Display(name="soldTo", type = "PopSearch", linktoBean=Customer.class),
        @Display(name="accountType", type = "PopSearch", linktoBean=AccountType.class),
        @Display(name="totalSalesAmount"),
        @Display(name="deposit"),
        @Display(name="balance"),
        @Display(name="gLId"),
        @Display(name="cash"),
        @Display(name="onAccount"),
        @Display(name="onNotes"),
        @Display(name="aDP"),
        @Display(name="description"),
        @Display(name="moduleName"),
        @Display(name="recordId"),
        @Display(name="invoiceId")
})
public class SalesOrder extends AbstractIBean implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Id
    @Column(name = "salesOrderId", nullable = false)
    public Integer salesOrderId;
    @Column(name = "soldDate")
    @Temporal(value = TemporalType.DATE)
    public Date soldDate;
    @Column(name = "soldBy", nullable = false)
    public int soldBy;
    @Column(name = "soldTo", nullable = false)
    public int soldTo;
    @Column(name = "accountType")
    public String accountType;
    @Column(name = "totalSalesAmount")
    public double totalSalesAmount;
    @Column(name = "deposit")
    public double deposit;
    @Column(name = "balance")
    public double balance;
    @Column(name = "gLId")
    public int gLId;
    @Column(name = "cash")
    public boolean cash;
    @Column(name = "onAccount")
    public boolean onAccount;
    @Column(name = "onNotes")
    public boolean onNotes;
    @Column(name = "aDP")
    public boolean aDP;
    @Column(name = "description")
    public String description;
    @Column(name = "moduleName", length = 50)
    public String moduleName;
    @Column(name = "recordId")
    public int recordId;
    @Column(name = "invoiceId")
    public int invoiceId;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        Integer oldSalesOrderId = this.salesOrderId;
        this.salesOrderId = salesOrderId;
        changeSupport.firePropertyChange("salesOrderId", oldSalesOrderId, salesOrderId);
    }

    public int getGLId() {
        return gLId;
    }

    public void setGLId(int gLId) {
        int oldGLId = this.gLId;
        this.gLId = gLId;
        changeSupport.firePropertyChange("gLId", oldGLId, gLId);
    }

    public boolean getCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        boolean oldCash = this.cash;
        this.cash = cash;
        changeSupport.firePropertyChange("cash", oldCash, cash);
    }

    public boolean getOnAccount() {
        return onAccount;
    }

    public void setOnAccount(boolean onAccount) {
        boolean oldOnAccount = this.onAccount;
        this.onAccount = onAccount;
        changeSupport.firePropertyChange("onAccount", oldOnAccount, onAccount);
    }

    public boolean getOnNotes() {
        return onNotes;
    }

    public void setOnNotes(boolean onNotes) {
        boolean oldOnNotes = this.onNotes;
        this.onNotes = onNotes;
        changeSupport.firePropertyChange("onNotes", oldOnNotes, onNotes);
    }

    public boolean getADP() {
        return aDP;
    }

    public void setADP(boolean aDP) {
        boolean oldADP = this.aDP;
        this.aDP = aDP;
        changeSupport.firePropertyChange("aDP", oldADP, aDP);
    }

   
    @Override
    public String toString() {
        return "Sales order[" + salesOrderId + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public java.lang.String getAccountType() {
        return accountType;
    }

    public void setAccountType(java.lang.String accountType) {
        this.accountType = accountType;
    }

    public java.util.Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(java.util.Date soldDate) {
        this.soldDate = soldDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(int soldBy) {
        this.soldBy = soldBy;
    }

    public int getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(int soldTo) {
        this.soldTo = soldTo;
    }

    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(double totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    public boolean isInvoiced() {
        if (this.invoiceId == 0) {
            return false;
        }
        Invoice inv = (Invoice) this.firstRecord("SELECT a FROM Invoice a WHERE a.salesOrderId=" + this.salesOrderId);
        return inv != null;
    }

    public Invoice getInvoice() {
        if (this.invoiceId == 0) {
            return null;
        }
        Invoice inv = (Invoice) this.firstRecord("SELECT a FROM Invoice a WHERE a.salesOrderId=" + this.salesOrderId);
        return inv;
    }

    public Person getCustomer() {
        if (this.soldTo == 0) {
            return null;
        }
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=" + this.soldTo);
    }

    public Person getSeller() {
        if (this.soldBy == 0) {
            return null;
        }
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=" + this.soldBy);
    }

    public List getItemList() {
        return this.list("SELECT a FROM Salesorderitem a WHERE a.salesOrderId=" + salesOrderId);
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public String getHTMLInvoice() {
        Object obj = ScriptRunner.runFreeMarker("Invoice", this);
        return obj.toString();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
