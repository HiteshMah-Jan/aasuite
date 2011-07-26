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
	@Display(name="MfrSerialNo"),
	@Display(name="SerialNo"),
	@Display(name="ItemNo"),
	@Display(name="ItemDescription"),
	@Display(name="CustomerCode"),
	@Display(name="CustomerName"),
	@Display(name="ContactPerson"),
	@Display(name="TelephoneNo"),
	@Display(name="Status"),
	@Display(name="PreviousSN"),
	@Display(name="NewSN"),
	@Display(name="Technician"),
	@Display(name="Territory")
})
public class CustomerEquipmentCard extends AbstractIBean {
	@Id
	public Double MfrSerialNo;
	public Double SerialNo;
	public Double ItemNo;
	public String ItemDescription;
	public String CustomerCode;
	public String CustomerName;
	public String ContactPerson;
	public Double TelephoneNo;
	public String Status;
	public String PreviousSN;
	public String NewSN;
	public String Technician;
	public String Territory;
	
	
	
public Double getMfrSerialNo() {
		return MfrSerialNo;
	}
	public void setMfrSerialNo(Double mfrSerialNo) {
		MfrSerialNo = mfrSerialNo;
	}
	public Double getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(Double serialNo) {
		SerialNo = serialNo;
	}
	public Double getItemNo() {
		return ItemNo;
	}
	public void setItemNo(Double itemNo) {
		ItemNo = itemNo;
	}
	public String getItemDescription() {
		return ItemDescription;
	}
	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}
	public String getCustomerCode() {
		return CustomerCode;
	}
	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public Double getTelephoneNo() {
		return TelephoneNo;
	}
	public void setTelephoneNo(Double telephoneNo) {
		TelephoneNo = telephoneNo;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPreviousSN() {
		return PreviousSN;
	}
	public void setPreviousSN(String previousSN) {
		PreviousSN = previousSN;
	}
	public String getNewSN() {
		return NewSN;
	}
	public void setNewSN(String newSN) {
		NewSN = newSN;
	}
	public String getTechnician() {
		return Technician;
	}
	public void setTechnician(String technician) {
		Technician = technician;
	}
	public String getTerritory() {
		return Territory;
	}
	public void setTerritory(String territory) {
		Territory = territory;
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