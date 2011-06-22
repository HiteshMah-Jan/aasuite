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
import bean.inventory.Product;

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
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"salesOrderId", "product", "totalAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="salesDeliveryId"),
        @Display(name="productId", type = "PopSearch", linktoBean = Product.class),
        @Display(name="quantity"),
        @Display(name="unitPrice"),
        @Display(name="discountPercentage"),
        @Display(name="totalPrice"),
        @Display(name="remarks")
})
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
