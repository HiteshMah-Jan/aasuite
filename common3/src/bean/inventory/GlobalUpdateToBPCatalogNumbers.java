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
@Table(name = "GlobalUpdateToBPCatalogNumbers")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	
	@Display(name="bpCode"),
	@Display(name="bpName"),
	@Display(name="bpCOdeFrm"),
	@Display(name="bpCodeTo"),
	@Display(name="custGroup", type="Combo", modelCombo={}),
	@Display(name="vendorGroup", type="Combo", modelCombo={}),
	
	@Display(name="itemsCodeFrm"),
	@Display(name="itemsCodeTo"),
	@Display(name="vendorCodeFrm"),
	@Display(name="vendorCodeTo"),
	@Display(name="itemsGroup", type="Combo", modelCombo={})
})
public class GlobalUpdateToBPCatalogNumbers extends AbstractIBean{
@Id
public String bpCode;
public String bpName;

public String bpCOdeFrm;
public String bpCodeTo;
public String custGroup;
public String vendorGroup;

public String itemsCodeFrm;
public String itemsCodeTo;
public String vendorCodeFrm;
public String vendorCodeTo;
public String itemsGroup;



	public String getItemsGroup() {
	return itemsGroup;
}

public void setItemsGroup(String itemsGroup) {
	this.itemsGroup = itemsGroup;
}

	public String getBpCode() {
	return bpCode;
}

public void setBpCode(String bpCode) {
	this.bpCode = bpCode;
}

public String getBpName() {
	return bpName;
}

public void setBpName(String bpName) {
	this.bpName = bpName;
}

public String getBpCOdeFrm() {
	return bpCOdeFrm;
}

public void setBpCOdeFrm(String bpCOdeFrm) {
	this.bpCOdeFrm = bpCOdeFrm;
}

public String getBpCodeTo() {
	return bpCodeTo;
}

public void setBpCodeTo(String bpCodeTo) {
	this.bpCodeTo = bpCodeTo;
}

public String getCustGroup() {
	return custGroup;
}

public void setCustGroup(String custGroup) {
	this.custGroup = custGroup;
}

public String getVendorGroup() {
	return vendorGroup;
}

public void setVendorGroup(String vendorGroup) {
	this.vendorGroup = vendorGroup;
}

public String getItemsCodeFrm() {
	return itemsCodeFrm;
}

public void setItemsCodeFrm(String itemsCodeFrm) {
	this.itemsCodeFrm = itemsCodeFrm;
}

public String getItemsCodeTo() {
	return itemsCodeTo;
}

public void setItemsCodeTo(String itemsCodeTo) {
	this.itemsCodeTo = itemsCodeTo;
}

public String getVendorCodeFrm() {
	return vendorCodeFrm;
}

public void setVendorCodeFrm(String vendorCodeFrm) {
	this.vendorCodeFrm = vendorCodeFrm;
}

public String getVendorCodeTo() {
	return vendorCodeTo;
}

public void setVendorCodeTo(String vendorCodeTo) {
	this.vendorCodeTo = vendorCodeTo;
}

	public static void main(String[] args) {
		view(GlobalUpdateToBPCatalogNumbers.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
