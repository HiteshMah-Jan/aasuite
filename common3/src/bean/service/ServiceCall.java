package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;
@Entity
@Table(name = "ServiceCall")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="customer"),
	@Display(name="name"),
	@Display(name="contactPerson"),
	@Display(name="telephoneNo"),
	@Display(name="mfrSerialNo"),
	@Display(name="item"),
	@Display(name="description"),
	@Display(name="itemGroup"),
	@Display(name="subject", type="TextArea", gridFieldWidth=6, width=-1),
	@Display(name="callStatus"),
	@Display(name="callId"),
	@Display(name="priority"),
	@Display(name="createdOn"),
	@Display(name="closeOn"),
	@Display(name="contactNo"),
	@Display(name="endDate")
})
public class ServiceCall extends AbstractIBean {
	@Id
	public String customer;
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		customer = customer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
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
	public Double getMfrSerialNo() {
		return mfrSerialNo;
	}
	public void setMfrSerialNo(Double mfrSerialNo) {
		mfrSerialNo = mfrSerialNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		item = item;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		description = description;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		itemGroup = itemGroup;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		subject = subject;
	}
	public String getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(String callStatus) {
		callStatus = callStatus;
	}
	public Double getCallId() {
		return callId;
	}
	public void setCallId(Double callId) {
		callId = callId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		priority = priority;
	}
	public Double getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Double createdOn) {
		createdOn = createdOn;
	}
	public Double getCloseOn() {
		return closeOn;
	}
	public void setCloseOn(Double closeOn) {
		closeOn = closeOn;
	}
	public Double getContactNo() {
		return contactNo;
	}
	public void setContactNo(Double contactNo) {
		contactNo = contactNo;
	}
	public Double getEndDate() {
		return endDate;
	}
	public void setEndDate(Double endDate) {
		endDate = endDate;
	}
	public String name;
	public String contactPerson;
	public Double telephoneNo;
	public Double mfrSerialNo;
	public String item;
	public String description;
	public String itemGroup;
	public String subject;
	public String callStatus;
	public Double callId;
	public String priority;
	public Double createdOn;
	public Double closeOn;
	public Double contactNo;
	public Double endDate;
public static void main(String[] args) {
	view(ServiceCall.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
