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
@Table(name = "ServiceCall")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Customer"),
	@Display(name="Name"),
	@Display(name="ContactPerson"),
	@Display(name="TelephoneNo"),
	@Display(name="MfrSerialNo"),
	@Display(name="Item"),
	@Display(name="Description"),
	@Display(name="ItemGroup"),
	@Display(name="Subject", type="TextArea", gridFieldWidth=6, width=-1),
	@Display(name="CallStatus"),
	@Display(name="CallId"),
	@Display(name="Priority"),
	@Display(name="CreatedOn"),
	@Display(name="CloseOn"),
	@Display(name="ContactNo"),
	@Display(name="EndDate")
})
public class ServiceCall extends AbstractIBean {
	@Id
	public String Customer;
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public Double getMfrSerialNo() {
		return MfrSerialNo;
	}
	public void setMfrSerialNo(Double mfrSerialNo) {
		MfrSerialNo = mfrSerialNo;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getItemGroup() {
		return ItemGroup;
	}
	public void setItemGroup(String itemGroup) {
		ItemGroup = itemGroup;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getCallStatus() {
		return CallStatus;
	}
	public void setCallStatus(String callStatus) {
		CallStatus = callStatus;
	}
	public Double getCallId() {
		return CallId;
	}
	public void setCallId(Double callId) {
		CallId = callId;
	}
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
	public Double getCreatedOn() {
		return CreatedOn;
	}
	public void setCreatedOn(Double createdOn) {
		CreatedOn = createdOn;
	}
	public Double getCloseOn() {
		return CloseOn;
	}
	public void setCloseOn(Double closeOn) {
		CloseOn = closeOn;
	}
	public Double getContactNo() {
		return ContactNo;
	}
	public void setContactNo(Double contactNo) {
		ContactNo = contactNo;
	}
	public Double getEndDate() {
		return EndDate;
	}
	public void setEndDate(Double endDate) {
		EndDate = endDate;
	}
	public String Name;
	public String ContactPerson;
	public Double TelephoneNo;
	public Double MfrSerialNo;
	public String Item;
	public String Description;
	public String ItemGroup;
	public String Subject;
	public String CallStatus;
	public Double CallId;
	public String Priority;
	public Double CreatedOn;
	public Double CloseOn;
	public Double ContactNo;
	public Double EndDate;
public static void main(String[] args) {
	view(ServiceCall.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
