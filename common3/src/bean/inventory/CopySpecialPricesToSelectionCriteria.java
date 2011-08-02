package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "CopySpecialPricesToSelectionCriteria")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="bpCode"),
	@Display(name="bpName"),
	@Display(name="bpCOdeFrm"),
	@Display(name="bpCOdeTo"),
	@Display(name="customerGroup",type="Combo", modelCombo={}),
	@Display(name="vendorGroup",type="Combo", modelCombo={}),
	@Display(name="itemsCOdeFrm"),
	@Display(name="itemsCOdeTo"),
	@Display(name="itemGroup",type="Combo", modelCombo={})
})
public class CopySpecialPricesToSelectionCriteria extends AbstractIBean{

	@Id
	public String bpCode;
	public String bpName;
	
	public String bpCOdeFrm;
	public String bpCOdeTo;
	public String customerGroup;
	public String vendorGroup;
	
	public String itemsCOdeFrm;
	public String itemsCOdeTo;
	public String itemGroup;
	
	
	
public String getBpCode() {
		return bpCode;
	}
	public void setBpCode(String bpCode) {
		this.bpCode = bpCode;
	}
	public String getBpName() {
		return bpName;
	}
	public void setBpName(String bpName) {
		this.bpName = bpName;
	}
	public String getBpCOdeFrm() {
		return bpCOdeFrm;
	}
	public void setBpCOdeFrm(String bpCOdeFrm) {
		this.bpCOdeFrm = bpCOdeFrm;
	}
	public String getBpCOdeTo() {
		return bpCOdeTo;
	}
	public void setBpCOdeTo(String bpCOdeTo) {
		this.bpCOdeTo = bpCOdeTo;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getVendorGroup() {
		return vendorGroup;
	}
	public void setVendorGroup(String vendorGroup) {
		this.vendorGroup = vendorGroup;
	}
	public String getItemsCOdeFrm() {
		return itemsCOdeFrm;
	}
	public void setItemsCOdeFrm(String itemsCOdeFrm) {
		this.itemsCOdeFrm = itemsCOdeFrm;
	}
	public String getItemsCOdeTo() {
		return itemsCOdeTo;
	}
	public void setItemsCOdeTo(String itemsCOdeTo) {
		this.itemsCOdeTo = itemsCOdeTo;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
public static void main(String[] args) {
	view(CopySpecialPricesToSelectionCriteria.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
