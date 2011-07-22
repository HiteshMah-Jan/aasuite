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
@Table(name = "LandedCostVendor")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="landedCostId"),
	@Display(name="vendorId")
})
public class LandedCostVendor extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int landedCostId;
    public int vendorId;

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
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public static void main(String[] args) {
		view(LandedCostVendor.class);
	}
    @Override
    public String popupSearch(String criteria) {
        return null;
    }
}
