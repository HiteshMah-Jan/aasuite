/*
 * Purchaseorderitem.java
 *
 * Created on Dec 10, 2007, 9:57:01 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "LandedCostItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="landedCostId"),
	@Display(name="itemNo"),
	@Display(name="quantity"),
	@Display(name="baseDocument"),
	@Display(name="baseDocumentNo"),
	@Display(name="projectCustomer"),
	@Display(name="expenditure"),
	@Display(name="warehousePrice"),
	@Display(name="totalAmount")
})
public class LandedCostItem extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int landedCostId;
    public int itemNo;
    public double quantity;
    public String baseDocument;
    public int baseDocumentNo;
    public String projectCustomer;
    public double expenditure;
    public double warehousePrice;
    public double totalAmount;    

    public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public int getLandedCostId() {
		return landedCostId;
	}
	public void setLandedCostId(int landedCostId) {
		this.landedCostId = landedCostId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getBaseDocument() {
		return baseDocument;
	}
	public void setBaseDocument(String baseDocument) {
		this.baseDocument = baseDocument;
	}
	public int getBaseDocumentNo() {
		return baseDocumentNo;
	}
	public void setBaseDocumentNo(int baseDocumentNo) {
		this.baseDocumentNo = baseDocumentNo;
	}
	public String getProjectCustomer() {
		return projectCustomer;
	}
	public void setProjectCustomer(String projectCustomer) {
		this.projectCustomer = projectCustomer;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public double getWarehousePrice() {
		return warehousePrice;
	}
	public void setWarehousePrice(double warehousePrice) {
		this.warehousePrice = warehousePrice;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public static void main(String[] args) {
		view(LandedCostItem.class);
	}
    @Override
    public String popupSearch(String criteria) {
        return null;
    }
}
