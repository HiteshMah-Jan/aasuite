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
@Table(name = "ServiceContract")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="customerCode"),
	@Display(name="customerName"),
	@Display(name="contactPerson"),
	@Display(name="telephoneNo"),
	@Display(name="description"),
	@Display(name="contactNo"),
	@Display(name="startDate"),
	@Display(name="endDate"),
	@Display(name="terminationDate")
})
public class ServiceContract extends AbstractIBean {
	@Id
	public String customerCode;
	public String customerName;
	public String contactPerson;
	public String telephoneNo;
	public String description;
	public Double contactNo;
	public Double startDate;
	public Double endDate;
	public Double terminationDate;
	
	
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
	public Double getStartDate() {
		return startDate;
	}
	public void setStartDate(Double startDate) {
		startDate = startDate;
	}
	public Double getEndDate() {
		return endDate;
	}
	public void setEndDate(Double endDate) {
		endDate = endDate;
	}
	public Double getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(Double terminationDate) {
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
