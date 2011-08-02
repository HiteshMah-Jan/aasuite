package bean.inventory.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 4, columnSearch = { "preferredVendor", "lenght" })
@Displays({

	@Display(name="preferredVendor", fieldPrefix="inventory."),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"lenght","width","height","volume","weight"}, verticalMerge=true),	
	@Display(name="lenght", fieldPrefix="inventory."),
	@Display(name="width", fieldPrefix="inventory."),
	@Display(name="height", fieldPrefix="inventory."),
	@Display(name="volume", fieldPrefix="inventory.",type="Combo",modelCombo={}),
	@Display(name="weight", fieldPrefix="inventory."),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"mfrCatalogNo","purchasingUoM","itemsPerPurchaseUnit","packegingUomPurchasing","quantityPerPackagingUoM","customsGroup","taxType"}, verticalMerge=true),
	@Display(name="mfrCatalogNo", fieldPrefix="inventory."),
	@Display(name="purchasingUoM", fieldPrefix="inventory."),
	@Display(name="itemsPerPurchaseUnit", fieldPrefix="inventory."),
	@Display(name="packegingUomPurchasing", fieldPrefix="inventory."),
	@Display(name="quantityPerPackagingUoM", fieldPrefix="inventory."),
	@Display(name="customsGroup", fieldPrefix="inventory.",type="Combo",modelCombo={}),
	
	@Display(name="taxType", fieldPrefix="inventory.",type="Combo",modelCombo={"Regular Tax","Use Tax","No Tax"}),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"factor1","factor2","factor3","factor4"}, verticalMerge=true),
	@Display(name="factor1", fieldPrefix="inventory."),
	@Display(name="factor2", fieldPrefix="inventory."),
	@Display(name="factor3", fieldPrefix="inventory."),
	@Display(name="factor4", fieldPrefix="inventory.")
	
})
public class EmbeddedItemMasterDataPurchasingData extends AbstractIBean{
public static void main(String[] args) {
	viewEmbedded(EmbeddedItemMasterDataPurchasingData.class);
}
public String preferredVendor;

public double mfrCatalogNo;
public String purchasingUoM;
public Integer itemsPerPurchaseUnit;
public String packegingUomPurchasing;
public Integer quantityPerPackagingUoM;
public String customsGroup;

public String taxType;

public Integer lenght;
public Integer width;
public Integer height;
public Integer volume;
public Integer weight;

public double factor1;
public double factor2;
public double factor3;
public double factor4;


public String getPreferredVendor() {
	return preferredVendor;
}


public void setPreferredVendor(String preferredVendor) {
	this.preferredVendor = preferredVendor;
}


public double getMfrCatalogNo() {
	return mfrCatalogNo;
}


public void setMfrCatalogNo(double mfrCatalogNo) {
	this.mfrCatalogNo = mfrCatalogNo;
}


public String getPurchasingUoM() {
	return purchasingUoM;
}


public void setPurchasingUoM(String purchasingUoM) {
	this.purchasingUoM = purchasingUoM;
}


public Integer getItemsPerPurchaseUnit() {
	return itemsPerPurchaseUnit;
}


public void setItemsPerPurchaseUnit(Integer itemsPerPurchaseUnit) {
	this.itemsPerPurchaseUnit = itemsPerPurchaseUnit;
}


public String getPackegingUomPurchasing() {
	return packegingUomPurchasing;
}


public void setPackegingUomPurchasing(String packegingUomPurchasing) {
	this.packegingUomPurchasing = packegingUomPurchasing;
}


public Integer getQuantityPerPackagingUoM() {
	return quantityPerPackagingUoM;
}


public void setQuantityPerPackagingUoM(Integer quantityPerPackagingUoM) {
	this.quantityPerPackagingUoM = quantityPerPackagingUoM;
}


public String getCustomsGroup() {
	return customsGroup;
}


public void setCustomsGroup(String customsGroup) {
	this.customsGroup = customsGroup;
}


public String getTaxType() {
	return taxType;
}


public void setTaxType(String taxType) {
	this.taxType = taxType;
}


public Integer getLenght() {
	return lenght;
}


public void setLenght(Integer lenght) {
	this.lenght = lenght;
}


public Integer getWidth() {
	return width;
}


public void setWidth(Integer width) {
	this.width = width;
}


public Integer getHeight() {
	return height;
}


public void setHeight(Integer height) {
	this.height = height;
}


public Integer getVolume() {
	return volume;
}


public void setVolume(Integer volume) {
	this.volume = volume;
}


public Integer getWeight() {
	return weight;
}


public void setWeight(Integer weight) {
	this.weight = weight;
}


public Double getFactor1() {
	return factor1;
}


public void setFactor1(Double factor1) {
	this.factor1 = factor1;
}


public Double getFactor2() {
	return factor2;
}


public void setFactor2(Integer factor2) {
	this.factor2 = factor2;
}


public Double getFactor3() {
	return factor3;
}


public void setFactor3(Double factor3) {
	this.factor3 = factor3;
}


public Double getFactor4() {
	return factor4;
}


public void setFactor4(Double factor4) {
	this.factor4 = factor4;
}


@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
