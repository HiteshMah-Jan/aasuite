/*
 * Purchaseorderitem.java
 *
 * Created on Dec 10, 2007, 9:57:01 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "PurchaseLink")
@UITemplate(template = TemplateDefault.class, gridCount = 4,
		columnSearch = {"purchaseOrderId","purchaseGoosReceiptId","purchaseInvoiceId","purchaseReturnId","purchaseCreditMemoId"})
@Displays({
        @Display(name="seq"),
        @Display(name="purchaseOrderId"),
        @Display(name="purchaseGoosReceiptId"),
        @Display(name="purchaseInvoiceId"),
        @Display(name="purchaseReturnId"),
        @Display(name="purchaseCreditMemoId"),
})
public class PurchaseLink extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int purchaseOrderId;
    public int purchaseGoosReceiptId;
    public int purchaseInvoiceId;
    public int purchaseReturnId;
    public int purchaseCreditMemoId;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public int getPurchaseGoosReceiptId() {
		return purchaseGoosReceiptId;
	}

	public void setPurchaseGoosReceiptId(int purchaseGoosReceiptId) {
		this.purchaseGoosReceiptId = purchaseGoosReceiptId;
	}

	public int getPurchaseInvoiceId() {
		return purchaseInvoiceId;
	}

	public void setPurchaseInvoiceId(int purchaseInvoiceId) {
		this.purchaseInvoiceId = purchaseInvoiceId;
	}

	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}

	public int getPurchaseCreditMemoId() {
		return purchaseCreditMemoId;
	}

	public void setPurchaseCreditMemoId(int purchaseCreditMemoId) {
		this.purchaseCreditMemoId = purchaseCreditMemoId;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
