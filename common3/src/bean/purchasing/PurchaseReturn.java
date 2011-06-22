/*
 * PurchaseOrder.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import service.util.AbstractIBean;
import bean.embed.AbstractSalesOrPurchase;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PurchaseReturn")
public class PurchaseReturn extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Embedded
    public AbstractSalesOrPurchase poData;

    @OneToMany(mappedBy="purchaseReturn")
    List<PurchaseReturnItem> items;

	@Override
	public List<AbstractIBean> nextStep() {
		// TODO: this should return Delivery, AR Invoice. AR Down Payment or Reserve Invoice
		return super.nextStep();
	}

	@Override
	public AbstractIBean prevStep() {
		// TODO: this should return null or Order Quotation
		return super.prevStep();
	}

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

	public AbstractSalesOrPurchase getPoData() {
		return poData;
	}

	public void setPoData(AbstractSalesOrPurchase poData) {
		this.poData = poData;
	}

	public List<PurchaseReturnItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseReturnItem> items) {
		this.items = items;
	}
}
