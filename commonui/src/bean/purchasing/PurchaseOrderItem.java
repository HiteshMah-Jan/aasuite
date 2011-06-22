/*
 * PurchaseOrderItem.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import bean.embed.AbstractSalesOrPurchaseItem;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PurchaseOrderItem")
public class PurchaseOrderItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="purchaseOrderId")
    public PurchaseOrder purchaseOrder;

    @Embedded
    public AbstractSalesOrPurchaseItem item;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public AbstractSalesOrPurchaseItem getItem() {
		return item;
	}

	public void setItem(AbstractSalesOrPurchaseItem item) {
		this.item = item;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

}
