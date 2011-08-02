package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "ResponceTimeByAssignedTo")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	
	@Display(name="createdOnFrm", label="Created On            From"),
	@Display(name="resolveOnFrm", label="Resolve On            From"),
	@Display(name="closedOnFrm",  label="Closed On              From"),
	@Display(name="customerCodeFrm", label="Customer Code      From"),
	@Display(name="handledByFrm", label="Handled By             From",type="Combo",modelCombo={}),
	@Display(name="queueIdFrm", label="Queue Id                From"),

	@Display(name="createdOnTo", label="To"),
	@Display(name="resolveOnTo", label="To"),
	@Display(name="closedOnTo", label="To"),
	@Display(name="customerCodeTo", label="To"),
	@Display(name="handledByTo", label="To",type="Combo",modelCombo={}),
	@Display(name="queueIdTo", label="To"),

	@Display(name="problemType"),
	@Display(name="priority"),
	@Display(name="callType"),
	@Display(name="origin"),
	@Display(name="overdueCalls"),
	@Display(name="callStatus"),
	@Display(name="sort")
})
public class ResponceTimeByAssignedTo extends AbstractIBean{
@Id
@Temporal(javax.persistence.TemporalType.DATE)
public Date createdOnFrm;
@Temporal(javax.persistence.TemporalType.DATE)
public Date resolveOnFrm;
@Temporal(javax.persistence.TemporalType.DATE)
public Date closedOnFrm;
public String customerCodeFrm;
public String handledByFrm;
public String queueIdFrm;

@Temporal(javax.persistence.TemporalType.DATE)
public Date createdOnTo;
@Temporal(javax.persistence.TemporalType.DATE)
public Date resolveOnTo;
@Temporal(javax.persistence.TemporalType.DATE)
public Date closedOnTo;
public String customerCodeTo;
public String handledByTo;
public String queueIdTo;

public boolean problemType;
public boolean priority;
public boolean callType;
public boolean origin;
public boolean overdueCalls;
public boolean callStatus;
public boolean sort;


	public Date getCreatedOnFrm() {
	return createdOnFrm;
}

public void setCreatedOnFrm(Date createdOnFrm) {
	this.createdOnFrm = createdOnFrm;
}

public Date getResolveOnFrm() {
	return resolveOnFrm;
}

public void setResolveOnFrm(Date resolveOnFrm) {
	this.resolveOnFrm = resolveOnFrm;
}

public Date getClosedOnFrm() {
	return closedOnFrm;
}

public void setClosedOnFrm(Date closedOnFrm) {
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

public Date getCreatedOnTo() {
	return createdOnTo;
}

public void setCreatedOnTo(Date createdOnTo) {
	this.createdOnTo = createdOnTo;
}

public Date getResolveOnTo() {
	return resolveOnTo;
}

public void setResolveOnTo(Date resolveOnTo) {
	this.resolveOnTo = resolveOnTo;
}

public Date getClosedOnTo() {
	return closedOnTo;
}

public void setClosedOnTo(Date closedOnTo) {
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

public boolean isProblemType() {
	return problemType;
}

public void setProblemType(boolean problemType) {
	this.problemType = problemType;
}

public boolean isPriority() {
	return priority;
}

public void setPriority(boolean priority) {
	this.priority = priority;
}

public boolean isCallType() {
	return callType;
}

public void setCallType(boolean callType) {
	this.callType = callType;
}

public boolean isOrigin() {
	return origin;
}

public void setOrigin(boolean origin) {
	this.origin = origin;
}

public boolean isOverdueCalls() {
	return overdueCalls;
}

public void setOverdueCalls(boolean overdueCalls) {
	this.overdueCalls = overdueCalls;
}

public boolean isCallStatus() {
	return callStatus;
}

public void setCallStatus(boolean callStatus) {
	this.callStatus = callStatus;
}

public boolean isSort() {
	return sort;
}

public void setSort(boolean sort) {
	this.sort = sort;
}

	public static void main(String[] args) {
		view(ResponceTimeByAssignedTo.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
