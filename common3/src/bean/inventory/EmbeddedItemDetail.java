package bean.inventory;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="taxLiable"),
	@Display(name="manufacturer"),
	@Display(name="additionalIdentifier"),
	@Display(name="shippingType"),
	@Display(name="manageItemBy"),
	@Display(name="phantomItem"),
	@Display(name="issueMethod"),
	@Display(name="active")
})
public class EmbeddedItemDetail extends AbstractIBean {
	public boolean taxLiable;
	public String manufacturer;
	public String additionalIdentifier;
	public String shippingType;
	public String manageItemBy;
	public boolean phantomItem;
	public String issueMethod;
	public boolean active;
	
	public boolean isTaxLiable() {
		return taxLiable;
	}
	public void setTaxLiable(boolean taxLiable) {
		this.taxLiable = taxLiable;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getAdditionalIdentifier() {
		return additionalIdentifier;
	}
	public void setAdditionalIdentifier(String additionalIdentifier) {
		this.additionalIdentifier = additionalIdentifier;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getManageItemBy() {
		return manageItemBy;
	}
	public void setManageItemBy(String manageItemBy) {
		this.manageItemBy = manageItemBy;
	}
	public boolean isPhantomItem() {
		return phantomItem;
	}
	public void setPhantomItem(boolean phantomItem) {
		this.phantomItem = phantomItem;
	}
	public String getIssueMethod() {
		return issueMethod;
	}
	public void setIssueMethod(String issueMethod) {
		this.issueMethod = issueMethod;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static void main(String[] args) {
		view(EmbeddedItemDetail.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
