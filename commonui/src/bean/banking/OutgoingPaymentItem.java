package bean.banking;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.embed.AbstractSalesOrPurchaseItem;

import service.util.AbstractIBean;

@Entity
@Table(name = "OutgoingPaymentItem")
public class OutgoingPaymentItem extends AbstractIBean implements Serializable{
	@Id
	public Integer seq;
	@Id
	public int outgoingPaymentId;
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

	public int getOutgoingPaymentId() {
		return outgoingPaymentId;
	}

	public void setOutgoingPaymentId(int outgoingPaymentId) {
		this.outgoingPaymentId = outgoingPaymentId;
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
