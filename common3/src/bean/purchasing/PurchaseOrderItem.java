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
import java.util.Date;
import javax.persistence.*;

import bean.inventory.Product;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PurchaseOrderItem")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"seq", "product", "totalAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="purchaseOrderId"),
        @Display(name="product", type = "PopSearch", linktoBean = Product.class),
        @Display(name="amountPerUnit"),
        @Display(name="numberOfItem"),
        @Display(name="totalAmount"),
        @Display(name="lastReceivedDate"),
        @Display(name="receivedCount"),
        @Display(name="totalReceivedCount"),
        @Display(name="remarks")
})
public class PurchaseOrderItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "purchaseOrderId", nullable = false)
    public int purchaseOrderId;
    @Column(name = "product")
    public String product;
    @Column(name = "amountPerUnit")
    public double amountPerUnit;
    @Column(name = "numberOfItem")
    public int numberOfItem;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "lastReceivedDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date lastReceivedDate;
    @Column(name = "receivedCount")
    public int receivedCount;
    @Column(name = "totalReceivedCount")
    public int totalReceivedCount;
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

    public Date getLastReceivedDate() {
        return lastReceivedDate;
    }

    public void setLastReceivedDate(Date lastReceivedDate) {
        this.lastReceivedDate = lastReceivedDate;
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

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public int getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(int receivedCount) {
        this.receivedCount = receivedCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public int getTotalReceivedCount() {
        return totalReceivedCount;
    }

    public void setTotalReceivedCount(int totalReceivedCount) {
        this.totalReceivedCount = totalReceivedCount;
    }
}
