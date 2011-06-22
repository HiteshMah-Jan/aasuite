package bean.banking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;

@Entity
@Table(name = "CreditCardDepositItem")
public class CreditCardDepositItem extends AbstractIBean {
	@Id
	public Integer seq;
	public int bankDepositId;
	public String voucherNumber;
    @Temporal(value = TemporalType.DATE)
	public Date creditDate;
	public String cardNumber;
	public String reference;
	public String paymentMethod;
	public String partner;
	public int numberOfPayment;
	public double total;

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getBankDepositId() {
		return bankDepositId;
	}

	public void setBankDepositId(int bankDepositId) {
		this.bankDepositId = bankDepositId;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
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

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public int getNumberOfPayment() {
		return numberOfPayment;
	}

	public void setNumberOfPayment(int numberOfPayment) {
		this.numberOfPayment = numberOfPayment;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
