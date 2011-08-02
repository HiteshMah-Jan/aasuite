package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "InventoryStatus")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="codeFrm",label="Code From"),
	@Display(name="vendorFrm",label="Vendor From"),
	@Display(name="itemGroup", type="Combo",modelCombo={}),
	
	@Display(name="codeTo",label="To"),
	@Display(name="vendorTo",label="To")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "By Location", gridCount=8,
			displayFields = {

}),
@ParentAddInfo(title = "By Warehouse", gridCount=8,
		displayFields = {

})
})
public class InventoryStatus extends AbstractIBean{
@Id
public String codeFrm;
public String vendorFrm;
public String itemGroup;

public String codeTo;
public String vendorTo;

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

	public static void main(String[] args) {
	view(InventoryStatus.class);
}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
