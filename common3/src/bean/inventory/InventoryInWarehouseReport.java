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
@Table(name = "InventoryInWarehouseReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="codeFrm",label="Code From"),
	@Display(name="vendorFrm",label="Vendor From"),
	@Display(name="itemGroup", type="Combo",modelCombo={}),
	@Display(name="codeTo",label="To"),
	@Display(name="vendorTo",label="To"),
	@Display(name="priceSource",type="Combo", modelCombo={"Base Price","Discount Purchase Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"})
})
public class InventoryInWarehouseReport extends AbstractIBean{
@Id
public String codeFrm;
public String vendorFrm;
public String itemGroup;

public String codeTo;
public String vendorTo;

public String priceSource;

	public String getCodeFrm() {
	return codeFrm;
}

public void setCodeFrm(String codeFrm) {
	this.codeFrm = codeFrm;
}

public String getVendorFrm() {
	return vendorFrm;
}

public void setVendorFrm(String vendorFrm) {
	this.vendorFrm = vendorFrm;
}

public String getItemGroup() {
	return itemGroup;
}

public void setItemGroup(String itemGroup) {
	this.itemGroup = itemGroup;
}

public String getCodeTo() {
	return codeTo;
}

public void setCodeTo(String codeTo) {
	this.codeTo = codeTo;
}

public String getVendorTo() {
	return vendorTo;
}

public void setVendorTo(String vendorTo) {
	this.vendorTo = vendorTo;
}

public String getPriceSource() {
	return priceSource;
}

public void setPriceSource(String priceSource) {
	this.priceSource = priceSource;
}

	public static void main(String[] args) {
	view(InventoryInWarehouseReport.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
