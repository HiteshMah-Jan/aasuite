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
@Table(name = "SalesLink")
@UITemplate(template = TemplateDefault.class, gridCount = 4, 
		columnSearch = {"salesOrderId","salesReturnId","salesDeliveryId","salesInvoiceId","salesCreditMemoId"})
@Displays({
        @Display(name="seq"),
        @Display(name="salesOrderId"),
        @Display(name="salesReturnId"),
        @Display(name="salesDeliveryId"),
        @Display(name="salesInvoiceId"),
        @Display(name="salesCreditMemoId"),
})
public class SalesLink extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int salesOrderId;
    public int salesReturnId;
    public int salesDeliveryId;
    public int salesInvoiceId;
    public int salesCreditMemoId;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public int getSalesReturnId() {
		return salesReturnId;
	}

	public void setSalesReturnId(int salesReturnId) {
		this.salesReturnId = salesReturnId;
	}

	public int getSalesDeliveryId() {
		return salesDeliveryId;
	}

	public void setSalesDeliveryId(int salesDeliveryId) {
		this.salesDeliveryId = salesDeliveryId;
	}

	public int getSalesInvoiceId() {
		return salesInvoiceId;
	}

	public void setSalesInvoiceId(int salesInvoiceId) {
		this.salesInvoiceId = salesInvoiceId;
	}

	public int getSalesCreditMemoId() {
		return salesCreditMemoId;
	}

	public void setSalesCreditMemoId(int salesCreditMemoId) {
		this.salesCreditMemoId = salesCreditMemoId;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
