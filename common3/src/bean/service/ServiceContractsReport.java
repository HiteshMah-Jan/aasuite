package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;

@Entity
@Table(name = "AverageClosureTimeReport")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({

	@Display(name="customerCodeFrm", label="Customer Code      From"),
	@Display(name="customerCodeTo", label="To"),
	
	@Display(name="startDateFrm", label="Start Date      From"),
	@Display(name="endDateFrm", label="End Date        From"),
	@Display(name="terminationDateFrm", label="Termination      From"),
	
	@Display(name="startDateTo", label="To"),
	@Display(name="endDateTo", label="To"),
	@Display(name="terminationDateTo", label="To"),
	
	@Display(name="contractType"),
	@Display(name="contractStatus"),
	@Display(name="serviceType"),
	@Display(name="contractRenewalsOnly"),

	
	@Display(name="sort")

})
public class ServiceContractsReport extends AbstractIBean{
	@Id
	public String customerCodeFrm;
	public String customerCodeTo;
	
	public Date startDateFrm;
	public Date startDateTo;
	
	public Date endDateFrm;
	public Date endDateTo;
	
	public Date terminationDateFrm;
	public Date terminationDateTo;
	
	public boolean contractType;
	public boolean contractStatus;
	public boolean serviceType;
	public boolean contractRenewalsOnly;
	
	public boolean sort;
	
	
	public String getCustomerCodeFrm() {
		return customerCodeFrm;
	}

	public void setCustomerCodeFrm(String customerCodeFrm) {
		this.customerCodeFrm = customerCodeFrm;
	}

	public String getCustomerCodeTo() {
		return customerCodeTo;
	}

	public void setCustomerCodeTo(String customerCodeTo) {
		this.customerCodeTo = customerCodeTo;
	}

	public Date getStartDateFrm() {
		return startDateFrm;
	}

	public void setStartDateFrm(Date startDateFrm) {
		this.startDateFrm = startDateFrm;
	}

	public Date getStartDateTo() {
		return startDateTo;
	}

	public void setStartDateTo(Date startDateTo) {
		this.startDateTo = startDateTo;
	}

	public Date getEndDateFrm() {
		return endDateFrm;
	}

	public void setEndDateFrm(Date endDateFrm) {
		this.endDateFrm = endDateFrm;
	}

	public Date getEndDateTo() {
		return endDateTo;
	}

	public void setEndDateTo(Date endDateTo) {
		this.endDateTo = endDateTo;
	}

	public Date getTerminationDateFrm() {
		return terminationDateFrm;
	}

	public void setTerminationDateFrm(Date terminationDateFrm) {
		this.terminationDateFrm = terminationDateFrm;
	}

	public Date getTerminationDateTo() {
		return terminationDateTo;
	}

	public void setTerminationDateTo(Date terminationDateTo) {
		this.terminationDateTo = terminationDateTo;
	}

	public boolean isContractType() {
		return contractType;
	}

	public void setContractType(boolean contractType) {
		this.contractType = contractType;
	}

	public boolean isContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(boolean contractStatus) {
		this.contractStatus = contractStatus;
	}

	public boolean isServiceType() {
		return serviceType;
	}

	public void setServiceType(boolean serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isContractRenewalsOnly() {
		return contractRenewalsOnly;
	}

	public void setContractRenewalsOnly(boolean contractRenewalsOnly) {
		this.contractRenewalsOnly = contractRenewalsOnly;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public static void main(String[] args) {
		view(ServiceContractsReport.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
