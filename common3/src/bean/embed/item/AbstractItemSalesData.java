package bean.embed.item;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class AbstractItemSalesData implements Serializable {
	public String salesUom;
	public double itemsPerSalesUnit;
	public String packagingUom;
	public double quantityPerPackaging;
	@Embedded
	public AbstractItemDimension dimension;
	@Embedded
	public AbstractItemFactor factor;
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
