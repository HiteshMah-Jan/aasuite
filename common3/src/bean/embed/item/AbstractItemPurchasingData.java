package bean.embed.item;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class AbstractItemPurchasingData implements Serializable {
	public String preferredVendor;
	public String mfrCatalogNumber;
	public String purchasingUom;
	public double itemsPerPurchaseUnit;
	public String packagingUom;
	public double quantityPerPackaging;
	public String customsGroup;
	public String taxType;
	@Embedded
	public AbstractItemDimension dimension;
	@Embedded
	public AbstractItemFactor factor;
	public String getPreferredVendor() {
		return preferredVendor;
	}
	public void setPreferredVendor(String preferredVendor) {
		this.preferredVendor = preferredVendor;
	}
	public String getMfrCatalogNumber() {
		return mfrCatalogNumber;
	}
	public void setMfrCatalogNumber(String mfrCatalogNumber) {
		this.mfrCatalogNumber = mfrCatalogNumber;
	}
	public String getPurchasingUom() {
		return purchasingUom;
	}
	public void setPurchasingUom(String purchasingUom) {
		this.purchasingUom = purchasingUom;
	}
	public double getItemsPerPurchaseUnit() {
		return itemsPerPurchaseUnit;
	}
	public void setItemsPerPurchaseUnit(double itemsPerPurchaseUnit) {
		this.itemsPerPurchaseUnit = itemsPerPurchaseUnit;
	}
	public String getPackagingUom() {
		return packagingUom;
	}
	public void setPackagingUom(String packagingUom) {
		this.packagingUom = packagingUom;
	}
	public double getQuantityPerPackaging() {
		return quantityPerPackaging;
	}
	public void setQuantityPerPackaging(double quantityPerPackaging) {
		this.quantityPerPackaging = quantityPerPackaging;
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
	public AbstractItemDimension getDimension() {
		return dimension;
	}
	public void setDimension(AbstractItemDimension dimension) {
		this.dimension = dimension;
	}
	public AbstractItemFactor getFactor() {
		return factor;
	}
	public void setFactor(AbstractItemFactor factor) {
		this.factor = factor;
	}

}
