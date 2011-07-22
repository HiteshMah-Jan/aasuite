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
	@Display(name="preferredVendor"),
	@Display(name="mfrCatalorNo"),
	@Display(name="purchasingUom"),
	@Display(name="quantityPerUom"),
	@Display(name="customsGroup"),
	@Display(name="purchasingTaxType"),
	@Display(name="length"),
	@Display(name="width"),
	@Display(name="height"),
	@Display(name="volume"),
	@Display(name="weight"),
	@Display(name="purchasingFactor1"),
	@Display(name="purchasingFactor2")
})
public class EmbeddedItemPurchasingData extends AbstractIBean {
	public String preferredVendor;
	public String mfrCatalorNo;
	public String purchasingUom;
	public double quantityPerUom;
	public String customsGroup;
	public String purchasingTaxType;
	public double length;
	public double width;
	public double height;
	public double volume;
	public double weight;
	public String purchasingFactor1;
	public String purchasingFactor2;
	
	public String getPreferredVendor() {
		return preferredVendor;
	}
	public void setPreferredVendor(String preferredVendor) {
		this.preferredVendor = preferredVendor;
	}
	public String getMfrCatalorNo() {
		return mfrCatalorNo;
	}
	public void setMfrCatalorNo(String mfrCatalorNo) {
		this.mfrCatalorNo = mfrCatalorNo;
	}
	public String getPurchasingUom() {
		return purchasingUom;
	}
	public void setPurchasingUom(String purchasingUom) {
		this.purchasingUom = purchasingUom;
	}
	public double getQuantityPerUom() {
		return quantityPerUom;
	}
	public void setQuantityPerUom(double quantityPerUom) {
		this.quantityPerUom = quantityPerUom;
	}
	public String getCustomsGroup() {
		return customsGroup;
	}
	public void setCustomsGroup(String customsGroup) {
		this.customsGroup = customsGroup;
	}
	public String getPurchasingTaxType() {
		return purchasingTaxType;
	}
	public void setPurchasingTaxType(String purchasingTaxType) {
		this.purchasingTaxType = purchasingTaxType;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getPurchasingFactor1() {
		return purchasingFactor1;
	}
	public void setPurchasingFactor1(String purchasingFactor1) {
		this.purchasingFactor1 = purchasingFactor1;
	}
	public String getPurchasingFactor2() {
		return purchasingFactor2;
	}
	public void setPurchasingFactor2(String purchasingFactor2) {
		this.purchasingFactor2 = purchasingFactor2;
	}
	public static void main(String[] args) {
		view(EmbeddedItemPurchasingData.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
