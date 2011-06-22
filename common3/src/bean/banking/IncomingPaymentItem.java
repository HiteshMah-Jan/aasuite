package bean.banking;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.embed.AbstractSalesOrPurchaseItem;

import service.util.AbstractIBean;

@Entity
@Table(name = "IncomingPaymentItem")
public class IncomingPaymentItem extends AbstractIBean implements Serializable{
	@Id
	public Integer seq;
	@Id
	public int incomingPaymentId;
    @Embedded
    public AbstractSalesOrPurchaseItem item;
    public double totalDiscount;
    public double totalPayment;

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

	public int getIncomingPaymentId() {
		return incomingPaymentId;
	}

	public void setIncomingPaymentId(int incomingPaymentId) {
		this.incomingPaymentId = incomingPaymentId;
	}

	public AbstractSalesOrPurchaseItem getItem() {
		return item;
	}

	public void setItem(AbstractSalesOrPurchaseItem item) {
		this.item = item;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

}
