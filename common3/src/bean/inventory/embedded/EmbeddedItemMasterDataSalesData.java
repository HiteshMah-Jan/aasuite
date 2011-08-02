package bean.inventory.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 4, columnSearch = { "salesUom", "lenght" })
@Displays({
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"salesUom","itemsPerSalesUnit","packegingUomSales","quantityPerPackeagingUom"}, verticalMerge=true),
	@Display(name="salesUom", fieldPrefix="inventory."),
	@Display(name="itemsPerSalesUnit", fieldPrefix="inventory."),
	@Display(name="packegingUomSales", fieldPrefix="inventory."),
	@Display(name="quantityPerPackeagingUom", fieldPrefix="inventory."),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"lenght","width","height","volume","weight"}, verticalMerge=true),	
	@Display(name="lenght", fieldPrefix="inventory."),
	@Display(name="width", fieldPrefix="inventory."),
	@Display(name="height", fieldPrefix="inventory."),
	@Display(name="volume", fieldPrefix="inventory.",type="Combo",modelCombo={}),
	@Display(name="weight", fieldPrefix="inventory."),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="inventory.", mergeFields={"factor1","factor2","factor3","factor4"}, verticalMerge=true),
	@Display(name="factor1", fieldPrefix="inventory."),
	@Display(name="factor2", fieldPrefix="inventory."),
	@Display(name="factor3", fieldPrefix="inventory."),
	@Display(name="factor4", fieldPrefix="inventory.")
	
})
public class EmbeddedItemMasterDataSalesData extends AbstractIBean{
public static void main(String[] args) {
	viewEmbedded(EmbeddedItemMasterDataSalesData.class);
}
public String salesUom;
public double itemsPerSalesUnit;
public String packegingUomSales;
public double quantityPerPackeagingUom;

public Integer lenght;
public Integer width;
public Integer height;
public Integer volume;
public Integer weight;

public double factor1;
public double factor2;
public double factor3;
public double factor4;


public String getSalesUom() {
	return salesUom;
}


public void setSalesUom(String salesUom) {
	this.salesUom = salesUom;
}


public double getItemsPerSalesUnit() {
	return itemsPerSalesUnit;
}


public void setItemsPerSalesUnit(double itemsPerSalesUnit) {
	this.itemsPerSalesUnit = itemsPerSalesUnit;
}


public String getPackegingUomSales() {
	return packegingUomSales;
}


public void setPackegingUomSales(String packegingUomSales) {
	this.packegingUomSales = packegingUomSales;
}


public double getQuantityPerPackeagingUom() {
	return quantityPerPackeagingUom;
}


public void setQuantityPerPackeagingUom(double quantityPerPackeagingUom) {
	this.quantityPerPackeagingUom = quantityPerPackeagingUom;
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


public double getFactor1() {
	return factor1;
}


public void setFactor1(double factor1) {
	this.factor1 = factor1;
}


public double getFactor2() {
	return factor2;
}


public void setFactor2(double factor2) {
	this.factor2 = factor2;
}


public double getFactor3() {
	return factor3;
}


public void setFactor3(double factor3) {
	this.factor3 = factor3;
}


public double getFactor4() {
	return factor4;
}


public void setFactor4(double factor4) {
	this.factor4 = factor4;
}


@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
