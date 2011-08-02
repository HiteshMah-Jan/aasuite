package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "CustomerEquipmentCardReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="customerFrm",label="Customer    From"),
	@Display(name="customerTo",label="To"),
	@Display(name="itemFrm",label="Item    From"),
	@Display(name="itemTo",label="To"),
	@Display(name="group",type="Combo",modelCombo={}),
	@Display(name="status")
})
public class CustomerEquipmentCardReport extends AbstractIBean{
@Id
public String customerFrm;
public String customerTo;

public String itemFrm;
public String itemTo;

public String group;

public boolean status;


	public String getCustomerFrm() {
	return customerFrm;
}

public void setCustomerFrm(String customerFrm) {
	this.customerFrm = customerFrm;
}

public String getCustomerTo() {
	return customerTo;
}

public void setCustomerCodeTo(String customerTo) {
	this.customerTo = customerTo;
}

public String getItemFrm() {
	return itemFrm;
}

public void setItemFrm(String itemFrm) {
	this.itemFrm = itemFrm;
}

public String getItemTo() {
	return itemTo;
}

public void setItemTo(String itemTo) {
	this.itemTo = itemTo;
}

public String getGroup() {
	return group;
}

public void setGroup(String group) {
	this.group = group;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

	public static void main(String[] args) {
	view(CustomerEquipmentCardReport.class);


}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
