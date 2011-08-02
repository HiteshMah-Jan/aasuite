package bean.inventory.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 4, columnSearch = { "taxtLiable", "phantomItem" })
@Displays({
	 @Display(name="taxLiable", fieldPrefix="inventory."),
	 @Display(name="dummyField", type="MergePanel", label="Production Data", fieldPrefix="inventory.", mergeFields={"phantomItem","issueMethod"}, verticalMerge=true),
	 @Display(name="phantomItem", fieldPrefix="inventory."),
	 @Display(name="issueMethod", fieldPrefix="inventory.",type="Combo",modelCombo={"Back Flush","Manual"}),
	
	 @Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"manufacturer","additionalIdentifyer","shippingType"}, verticalMerge=true),
	 @Display(name="manufacturer", fieldPrefix="inventory.",type="Combo",modelCombo={}),
	 @Display(name="additionalIdentifyer", fieldPrefix="inventory."),
	 @Display(name="shippingType", fieldPrefix="inventory.",type="Combo",modelCombo={}),	
	
	 @Display(name="dummyField", type="MergePanel", label="Serial and Batch Numbers", fieldPrefix="inventory.", mergeFields={"manageItemBy"}, verticalMerge=true),
	 @Display(name="manageItemBy", fieldPrefix="inventory.",type="Combo",modelCombo={"None","Serial Numbers","Batches"}),
	 
	
	 
})
public class EmbeddedItemMasterDataGeneral extends AbstractIBean{

	
	
	public static void main(String[] args) {
	viewEmbedded(EmbeddedItemMasterDataGeneral.class);
}

	public boolean taxLiable;
	
	public String manufacturer;
	public String additionalIdentifyer;
	public String shippingType;
	
	public String manageItemBy;
	
	public boolean phantomItem;
	public String issueMethod;
	
	
	
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



	public String getAdditionalIdentifyer() {
		return additionalIdentifyer;
	}



	public void setAdditionalIdentifyer(String additionalIdentifyer) {
		this.additionalIdentifyer = additionalIdentifyer;
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



	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
