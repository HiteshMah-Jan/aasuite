package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "CustomerEquipmentCard")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"mfrSerialNo","serialNo","itemNo","itemDescription"}, verticalMerge=true),
	@Display(name="mfrSerialNo"),
	@Display(name="serialNo"),
	@Display(name="itemNo"),
	@Display(name="itemDescription"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"customerCode","customerName","contactPerson","telephoneNo"}, verticalMerge=true),	
	@Display(name="customerCode"),
	@Display(name="customerName"),
	@Display(name="contactPerson"),
	@Display(name="telephoneNo"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"status","previousSN","newSN"}, verticalMerge=true),
	@Display(name="status"),
	@Display(name="previousSN"),
	@Display(name="newSN"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"technician","territory"}, verticalMerge=true),
	@Display(name="technician"),
	@Display(name="territory")
})
public class CustomerEquipmentCard extends AbstractIBean {
	@Id
	public Double mfrSerialNo;
	public Double serialNo;
	public Double itemNo;
	public String itemDescription;
	
	public String customerCode;
	public String customerName;
	public String contactPerson;
	public Double telephoneNo;
	
	public String status;
	public String previousSN;
	public String newSN;
	
	public String technician;
	public String territory;
	
	
	
public Double getMfrSerialNo() {
		return mfrSerialNo;
	}
	public void setMfrSerialNo(Double mfrSerialNo) {
		mfrSerialNo = mfrSerialNo;
	}
	public Double getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Double serialNo) {
		serialNo = serialNo;
	}
	public Double getItemNo() {
		return itemNo;
	}
	public void setItemNo(Double itemNo) {
		itemNo = itemNo;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		itemDescription = itemDescription;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		customerName = customerName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		contactPerson = contactPerson;
	}
	public Double getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(Double telephoneNo) {
		telephoneNo = telephoneNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		status = status;
	}
	public String getPreviousSN() {
		return previousSN;
	}
	public void setPreviousSN(String previousSN) {
		previousSN = previousSN;
	}
	public String getNewSN() {
		return newSN;
	}
	public void setNewSN(String newSN) {
		newSN = newSN;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		technician = technician;
	}
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		territory = territory;
	}
public static void main(String[] args) {
	view(CustomerEquipmentCard.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}