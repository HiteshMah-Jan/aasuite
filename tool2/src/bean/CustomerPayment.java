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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerPayment")
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Customer Contract",
    gridCount = 4, columnSearch = {"description","dueDate","paymentDate","amountDue","amountPaid","balance"}, sumFooter="3,4,5")
@Displays({
//    @Display(name="customerId", type="PopSearch", linktoBean=CustomerContact.class, gridFieldWidth=3, width=-1),
    @Display(name="description", gridFieldWidth=3, width=-1),
    @Display(name="dueDate"),
    @Display(name="amountDue"),
    @Display(name="paymentDate"),
    @Display(name="amountPaid"),
    @Display(name="balance", enabled=false)
})
public class CustomerPayment extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public String seq;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "amountDue")
    public double amountDue;
    @Column(name = "amountPaid")
    public double amountPaid;
    @Column(name = "balance")
    public double balance;
    @Column(name = "dueDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dueDate;
    @Column(name = "paymentDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date paymentDate;
    @Column(name = "description", length=4000)
    public String description;

	@Override
	public void save() {
		balance = amountDue-amountPaid;
		super.save();
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "contract");
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
