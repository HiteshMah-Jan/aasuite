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
@Table(name = "LandedCostOtherCostItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="landedCostId"),
	@Display(name="description"),
	@Display(name="allocationBy"),
	@Display(name="fixedCost"),
	@Display(name="amount"),
	@Display(name="miscAmount"),
	@Display(name="totalAmount")
})
public class LandedCostOtherCostItem extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int landedCostId;
    public String description;
    public String allocationBy;
    public boolean fixedCost;
    public double amount;
    public double miscAmount;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAllocationBy() {
		return allocationBy;
	}
	public void setAllocationBy(String allocationBy) {
		this.allocationBy = allocationBy;
	}
	public boolean isFixedCost() {
		return fixedCost;
	}
	public void setFixedCost(boolean fixedCost) {
		this.fixedCost = fixedCost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getMiscAmount() {
		return miscAmount;
	}
	public void setMiscAmount(double miscAmount) {
		this.miscAmount = miscAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public static void main(String[] args) {
		view(LandedCostOtherCostItem.class);
	}
    @Override
    public String popupSearch(String criteria) {
        return null;
    }
}
