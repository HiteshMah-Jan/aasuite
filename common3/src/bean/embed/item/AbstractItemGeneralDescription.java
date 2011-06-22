package bean.embed.item;

import javax.persistence.Embeddable;

@Embeddable
public class AbstractItemGeneralDescription {
	public boolean taxLiable;
	public boolean phantomItem;
	public String issueMethod;
	public String manufacturer;
	public String additionalIdentifier;
	public String shippingType;
	public String managedBy;
	public boolean isTaxLiable() {
		return taxLiable;
	}
	public void setTaxLiable(boolean taxLiable) {
		this.taxLiable = taxLiable;
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
	public String getManagedBy() {
		return managedBy;
	}
	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

}
