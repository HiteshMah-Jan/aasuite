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

import bean.Product;

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
@Table(name = "SalesCreditMemoItem")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"salesCreditMemoId", "product", "totalAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="salesCreditMemoId"),
        @Display(name="product", type = "PopSearch", linktoBean = Product.class),
        @Display(name="numberOfItem"),
        @Display(name="amountPerUnit"),
        @Display(name="totalAmount"),
        @Display(name="remarks")
})
public class SalesCreditMemoItem extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "salesCreditMemoId", nullable = false)
    public int salesCreditMemoId;
    @Column(name = "product", nullable = false)
    public String product;
    @Column(name = "numberOfItem")
    public int numberOfItem;
    @Column(name = "amountPerUnit")
    public double amountPerUnit;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(double amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public int getSalesCreditMemoId() {
		return salesCreditMemoId;
	}

	public void setSalesCreditMemoId(int salesCreditMemoId) {
		this.salesCreditMemoId = salesCreditMemoId;
	}

	public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
