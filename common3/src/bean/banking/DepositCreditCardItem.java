package bean.banking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "DepositCreditCardItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="depositId"),
	@Display(name="checkDate"),
	@Display(name="cardNumber"),
	@Display(name="reference"),
	@Display(name="paymentMethod"),
	@Display(name="customer"),
	@Display(name="numberOfPayment"),
	@Display(name="totalAmount"),
	@Display(name="parentBean"),
	@Display(name="changeSupport"),
	@Display(name="dummyField"),
	@Display(name="myNode"),
	@Display(name="newCache"),
	@Display(name="cacheMap"),
	@Display(name="includeSearch"),
	@Display(name="showImages"),
	@Display(name="showFile"),
	@Display(name="showSubrecords"),
	@Display(name="showCharts")
})
public class DepositCreditCardItem extends AbstractIBean {
	public static void main(String[] args) {
		view(DepositCreditCardItem.class);
	}

	@Id
	public Integer seq;
	public int depositId;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date checkDate;
	public String cardNumber;
	public String reference;
	public String paymentMethod;
	public String customer;
	public String numberOfPayment;
	public double totalAmount;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getDepositId() {
		return depositId;
	}

	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getNumberOfPayment() {
		return numberOfPayment;
	}

	public void setNumberOfPayment(String numberOfPayment) {
		this.numberOfPayment = numberOfPayment;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
