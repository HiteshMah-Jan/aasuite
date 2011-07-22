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
@Table(name = "LandedCostDetail")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="landedCostId"),
	@Display(name="itemId"),
	@Display(name="warehouse"),
	@Display(name="priceList"),
	@Display(name="expenditure")
})
public class LandedCostDetail extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int landedCostId;
    public String itemId;
    public String warehouse;
    public String priceList;
    public double expenditure;

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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getPriceList() {
		return priceList;
	}
	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public static void main(String[] args) {
		view(LandedCostDetail.class);
	}
    @Override
    public String popupSearch(String criteria) {
        return null;
    }
}
