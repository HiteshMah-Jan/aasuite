/*
 * Invoice.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "InvoiceDetail")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"quantity", "description", "amountPerPiece", "discount", "totalAmount"})
@Displays({
        @Display(name="quantity"),
        @Display(name="amountPerPiece"),
        @Display(name="discount"),
        @Display(name="totalAmount"),
        @Display(name="description", gridFieldWidth=5, width=-1)
})
public class InvoiceDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "invoiceId")
    public int invoiceId;
    @Column(name = "quantity")
    public int quantity;
    @Column(name = "amountPerPiece")
    public double amountPerPiece;
    @Column(name = "discount")
    public double discount;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAmountPerPiece() {
        return amountPerPiece;
    }

    public void setAmountPerPiece(double amountPerPiece) {
        this.amountPerPiece = amountPerPiece;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return BeanUtil.concat(seq,"");
    }
}
