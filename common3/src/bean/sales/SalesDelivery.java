/*
 * Salesorder.java
 *
 * Created on Nov 22, 2007, 6:07:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.embed.AbstractSales;

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
@Table(name = "SalesDelivery")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"customerId", "salesEmployeeId"})
@Displays({
        @Display(name="seq")
})
public class SalesDelivery extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "salesOrderId")
    public int salesOrderId;
    @Embedded
    public AbstractSales salesData;

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

	public AbstractSales getSalesData() {
		return salesData;
	}

	public void setSalesData(AbstractSales salesData) {
		this.salesData = salesData;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

}
