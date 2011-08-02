package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "InventoryValuationSimulation")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="codeFrm",label="Code From" ),
	@Display(name="vendorFrm",label="Vendor From"),
	@Display(name="itemGroup", type="Combo",modelCombo={}),
	
	@Display(name="codeTo",label="To"),
	@Display(name="vendorTo",label="To"),
	@Display(name="postingDateTo",label="Posting Date             To"),
	@Display(name="projectFrm",label="Project From"),
	@Display(name="calcMethod",label="Calc. Method",type="Combo",modelCombo={"Moving Average","FIFO","By Price List","Last Evaluated Price"}),
	@Display(name="projectTo",label="To")
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

public class InventoryValuationSimulation extends AbstractIBean{
@Id
public String codeFrm;
public String vendorFrm;
public String itemGroup;

public String codeTo;
public String vendorTo;

@Temporal(javax.persistence.TemporalType.DATE)
public Date postingDateTo;
public String projectFrm;
public String calcMethod;

public String projectTo;



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

public Date getPostingDateTo() {
	return postingDateTo;
}

public void setPostingDateTo(Date postingDateTo) {
	this.postingDateTo = postingDateTo;
}

public String getProjectFrm() {
	return projectFrm;
}

public void setProjectFrm(String projectFrm) {
	this.projectFrm = projectFrm;
}

public String getCalcMethod() {
	return calcMethod;
}

public void setCalcMethod(String calcMethod) {
	this.calcMethod = calcMethod;
}

public String getProjectTo() {
	return projectTo;
}

public void setProjectTo(String projectTo) {
	this.projectTo = projectTo;
}

	public static void main(String[] args) {
		view(InventoryValuationSimulation.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
