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
@Table(name = "ServiceCallReports")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"createdOnFrm","resolveOnFrm","closedOnFrm","customerCodeFrm","handledByFrm","queueIdFrm"}, verticalMerge=true),
	@Display(name="createdOnFrm", label="Created On            From"),
	@Display(name="resolveOnFrm", label="Resolve On            From"),
	@Display(name="closedOnFrm",  label="Closed On              From"),
	@Display(name="customerCodeFrm", label="Customer Code      From"),
	@Display(name="handledByFrm", label="Handled By             From"),
	@Display(name="queueIdFrm", label="Queue Id                From"),

	@Display(name="dummyField", type="MergePanel", noLabel=true, mergeFields={"createdOnTo","resolveOnTo","closedOnTo","customerCodeTo","handledByTo","queueIdTo"}, verticalMerge=true),	
	@Display(name="createdOnTo", label="Created On               To"),
	@Display(name="resolveOnTo", label="Resolve On               To"),
	@Display(name="closedOnTo", label="Closed On                 To"),
	@Display(name="customerCodeTo", label="Customer Code         To"),
	@Display(name="handledByTo", label="Handled By               To"),
	@Display(name="queueIdTo", label="Queue Id                  To")
})
public class ServiceCallReports extends AbstractIBean{
	@Id
	public String createdOnFrm;
	public String resolveOnFrm;
	public String closedOnFrm;
	public String customerCodeFrm;
	public String handledByFrm;
	public String queueIdFrm;
	
	public String createdOnTo;
	public String resolveOnTo;
	public String closedOnTo;
	public String customerCodeTo;
	public String handledByTo;
	public String queueIdTo;
	
	public String getCreatedOnFrm() {
		return createdOnFrm;
	}

	public void setCreatedOnFrm(String createdOnFrm) {
		this.createdOnFrm = createdOnFrm;
	}

	public String getResolveOnFrm() {
		return resolveOnFrm;
	}

	public void setResolveOnFrm(String resolveOnFrm) {
		this.resolveOnFrm = resolveOnFrm;
	}

	public String getClosedOnFrm() {
		return closedOnFrm;
	}

	public void setClosedOnFrm(String closedOnFrm) {
		this.closedOnFrm = closedOnFrm;
	}

	public String getCustomerCodeFrm() {
		return customerCodeFrm;
	}

	public void setCustomerCodeFrm(String customerCodeFrm) {
		this.customerCodeFrm = customerCodeFrm;
	}

	public String getHandledByFrm() {
		return handledByFrm;
	}

	public void setHandledByFrm(String handledByFrm) {
		this.handledByFrm = handledByFrm;
	}

	public String getQueueIdFrm() {
		return queueIdFrm;
	}

	public void setQueueIdFrm(String queueIdFrm) {
		this.queueIdFrm = queueIdFrm;
	}

	public String getCreatedOnTo() {
		return createdOnTo;
	}

	public void setCreatedOnTo(String createdOnTo) {
		this.createdOnTo = createdOnTo;
	}

	public String getResolveOnTo() {
		return resolveOnTo;
	}

	public void setResolveOnTo(String resolveOnTo) {
		this.resolveOnTo = resolveOnTo;
	}

	public String getClosedOnTo() {
		return closedOnTo;
	}

	public void setClosedOnTo(String closedOnTo) {
		this.closedOnTo = closedOnTo;
	}

	public String getCustomerCodeTo() {
		return customerCodeTo;
	}

	public void setCustomerCodeTo(String customerCodeTo) {
		this.customerCodeTo = customerCodeTo;
	}

	public String getHandledByTo() {
		return handledByTo;
	}

	public void setHandledByTo(String handledByTo) {
		this.handledByTo = handledByTo;
	}

	public String getQueueIdTo() {
		return queueIdTo;
	}

	public void setQueueIdTo(String queueIdTo) {
		this.queueIdTo = queueIdTo;
	}

	public static void main(String[] args) {
		view(ServiceCallReports.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
