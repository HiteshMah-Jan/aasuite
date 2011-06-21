/*
 * PaymentBudgeting.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import bean.hr.Employee;
import bean.inventory.Product;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PaymentBudgeting")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"seq", "purchaseItem", "price"})
@Displays({
        @Display(name="seq"),
        @Display(name="purchaseDate"),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Employee.class),
        @Display(name="purchaseItem", type = "PopSearch", linktoBean=Product.class),
        @Display(name="price"),
        @Display(name="quantity"),
        @Display(name="totalPrice"),
        @Display(name="reason")
})
public class PaymentBudgeting extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "purchase_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date purchaseDate;
    @Column(name = "physicianId", nullable = false)
    public int physicianId;
    @Column(name = "purchaseItem", nullable = false, length = 50)
    public String purchaseItem;
    @Column(name = "price", nullable = false)
    public double price;
    @Column(name = "quantity", nullable = false)
    public int quantity;
    @Column(name = "totalPrice", nullable = false)
    public double totalPrice;
    @Column(name = "reason", nullable = false, length = 150)
    public String reason;

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

    public String getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(String purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

   

    @Override
    public String toString() {
        return BeanUtil.concat("seq[",seq,"]");
    }

    public java.util.Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(java.util.Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
