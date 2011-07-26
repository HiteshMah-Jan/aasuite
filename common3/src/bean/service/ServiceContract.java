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
	@Display(name="CustomerCode"),
	@Display(name="CustomerName"),
	@Display(name="ContactPerson"),
	@Display(name="TelephoneNo"),
	@Display(name="Description"),
	@Display(name="ContactNo"),
	@Display(name="StartDate"),
	@Display(name="EndDate"),
	@Display(name="TerminationDate")
})
public class ServiceContract extends AbstractIBean {
	@Id
	public String CustomerCode;
	public String CustomerName;
	public String ContactPerson;
	public String TelephoneNo;
	public String Description;
	public Double ContactNo;
	public Double StartDate;
	public Double EndDate;
	public Double TerminationDate;
	
	
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
	public String getTelephoneNo() {
		return TelephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		TelephoneNo = telephoneNo;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Double getContactNo() {
		return ContactNo;
	}
	public void setContactNo(Double contactNo) {
		ContactNo = contactNo;
	}
	public Double getStartDate() {
		return StartDate;
	}
	public void setStartDate(Double startDate) {
		StartDate = startDate;
	}
	public Double getEndDate() {
		return EndDate;
	}
	public void setEndDate(Double endDate) {
		EndDate = endDate;
	}
	public Double getTerminationDate() {
		return TerminationDate;
	}
	public void setTerminationDate(Double terminationDate) {
		TerminationDate = terminationDate;
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
