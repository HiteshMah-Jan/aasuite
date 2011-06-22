/*
 * Salesorderitem.java
 *
 * Created on Dec 10, 2007, 9:57:01 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import java.io.Serializable;
import javax.persistence.*;

import bean.embed.AbstractSalesOrPurchaseItem;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SalesDeliveryItem")
public class SalesDeliveryItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "salesDeliveryId", nullable = false)
    public int salesDeliveryId;
    @Embedded
    public AbstractSalesOrPurchaseItem items;

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

	public int getSalesDeliveryId() {
		return salesDeliveryId;
	}

	public void setSalesDeliveryId(int salesDeliveryId) {
		this.salesDeliveryId = salesDeliveryId;
	}

	public AbstractSalesOrPurchaseItem getItems() {
		return items;
	}

	public void setItems(AbstractSalesOrPurchaseItem items) {
		this.items = items;
	}

}
