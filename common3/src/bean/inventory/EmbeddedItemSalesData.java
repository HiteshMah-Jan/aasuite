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
	@Display(name="salesUom"),
	@Display(name="itemsPerSalesUnit"),
	@Display(name="packagingUom"),
	@Display(name="salesLength"),
	@Display(name="salesWidth"),
	@Display(name="salesHeight"),
	@Display(name="salesVolume"),
	@Display(name="salesWeight"),
	@Display(name="salesFactor1"),
	@Display(name="salesFactor2"),
	@Display(name="salesFactor3"),
	@Display(name="salesFactor4")
})
public class EmbeddedItemSalesData extends AbstractIBean {
	public String salesUom;
	public double itemsPerSalesUnit;
	public String packagingUom;
	public double salesLength;
	public double salesWidth;
	public double salesHeight;
	public double salesVolume;
	public double salesWeight;
	public String salesFactor1;
	public String salesFactor2;
	public String salesFactor3;
	public String salesFactor4;
	
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
	public double getSalesLength() {
		return salesLength;
	}
	public void setSalesLength(double salesLength) {
		this.salesLength = salesLength;
	}
	public double getSalesWidth() {
		return salesWidth;
	}
	public void setSalesWidth(double salesWidth) {
		this.salesWidth = salesWidth;
	}
	public double getSalesHeight() {
		return salesHeight;
	}
	public void setSalesHeight(double salesHeight) {
		this.salesHeight = salesHeight;
	}
	public double getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(double salesVolume) {
		this.salesVolume = salesVolume;
	}
	public double getSalesWeight() {
		return salesWeight;
	}
	public void setSalesWeight(double salesWeight) {
		this.salesWeight = salesWeight;
	}
	public String getSalesFactor1() {
		return salesFactor1;
	}
	public void setSalesFactor1(String salesFactor1) {
		this.salesFactor1 = salesFactor1;
	}
	public String getSalesFactor2() {
		return salesFactor2;
	}
	public void setSalesFactor2(String salesFactor2) {
		this.salesFactor2 = salesFactor2;
	}
	public String getSalesFactor3() {
		return salesFactor3;
	}
	public void setSalesFactor3(String salesFactor3) {
		this.salesFactor3 = salesFactor3;
	}
	public String getSalesFactor4() {
		return salesFactor4;
	}
	public void setSalesFactor4(String salesFactor4) {
		this.salesFactor4 = salesFactor4;
	}
	public static void main(String[] args) {
		view(EmbeddedItemSalesData.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
