package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.sun.star.bridge.oleautomation.Date;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "ServiceContract")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="customerCode"),
	@Display(name="customerName"),
	@Display(name="contactPerson",type="Combo",modelCombo={}),
	@Display(name="telephoneNo"),
	@Display(name="description",type="TextArea",gridFieldWidth=4, width=-1),
	@Display(name="contactNo"),
	@Display(name="startDate"),
	@Display(name="endDate"),
	@Display(name="terminationDate")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "general", gridCount=8,
			displayFields = {

}),

@ParentAddInfo(title = "coverage", gridCount=8,
			displayFields = {

}),
@ParentAddInfo(title = "attachments", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "serviceCalls", gridCount=8,
		displayFields = {

})
})
public class ServiceContract extends AbstractIBean {
	@Id
	public String customerCode;
	public String customerName;
	public String contactPerson;
	public String telephoneNo;
	public String description;
	public Double contactNo;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date startDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date endDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date terminationDate;
	
	
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
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		telephoneNo = telephoneNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		description = description;
	}
	public Double getContactNo() {
		return contactNo;
	}
	public void setContactNo(Double contactNo) {
		contactNo = contactNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		endDate = endDate;
	}
	public Date getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(Date terminationDate) {
		terminationDate = terminationDate;
	}
public static void main(String[] args) {
	view(ServiceContract.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
