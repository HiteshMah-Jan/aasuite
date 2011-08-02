package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "PickAndPackManager")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="status",type="Combo", modelCombo={"Open","Released","Picked"}),
	@Display(name="salesOrdersFrm",label="Sales Orders From"),
	@Display(name="postingDateFrm",label="Posting Date From"),
	@Display(name="requiredDateFrm",label="Required Date From"),
	@Display(name="deliveryDueDateFrm",label="Delivery Due Date From"),
	@Display(name="cancellationDateFrm",label="Cancellation Date From"),
	@Display(name="pickListNoFrm",label="Pick List No. From"),
	@Display(name="itemFrm",label="Item From"),
	
	@Display(name="view",type="Combo",modelCombo={"Detailed","Summary"}),
	@Display(name="salesOrdersTo",label="Sales Orders To"),
	@Display(name="postingDateTo",label="Posting Date To"),
	@Display(name="requiredDateTo",label="Required Date To"),
	@Display(name="deliveryDueDateTo",label="Delivery Due Date To"),
	@Display(name="cancellationDateTo",label="Cancellation Date To"),
	@Display(name="pickListNoTo",label="Pick List No To"),
	@Display(name="itemTo",label="Item To"),
	
	@Display(name="sortBy",label="Sort By",type="Combo",modelCombo={"Delivery Date","Customer","Document Number","Warehouse","Priority","Pick List"})
})
public class PickAndPackManager extends AbstractIBean{
@Id
public String status;

public String salesOrdersFrm;
public Date postingDateFrm;
public Date requiredDateFrm;
public Date deliveryDueDateFrm;
public Date cancellationDateFrm;
public String pickListNoFrm;
public String itemFrm;


public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getSalesOrdersFrm() {
	return salesOrdersFrm;
}

public void setSalesOrdersFrm(String salesOrdersFrm) {
	this.salesOrdersFrm = salesOrdersFrm;
}

public Date getPostingDateFrm() {
	return postingDateFrm;
}

public void setPostingDateFrm(Date postingDateFrm) {
	this.postingDateFrm = postingDateFrm;
}

public Date getRequiredDateFrm() {
	return requiredDateFrm;
}

public void setRequiredDateFrm(Date requiredDateFrm) {
	this.requiredDateFrm = requiredDateFrm;
}

public Date getDeliveryDueDateFrm() {
	return deliveryDueDateFrm;
}

public void setDeliveryDueDateFrm(Date deliveryDueDateFrm) {
	this.deliveryDueDateFrm = deliveryDueDateFrm;
}

public Date getCancellationDateFrm() {
	return cancellationDateFrm;
}

public void setCancellationDateFrm(Date cancellationDateFrm) {
	this.cancellationDateFrm = cancellationDateFrm;
}

public String getPickListNoFrm() {
	return pickListNoFrm;
}

public void setPickListNoFrm(String pickListNoFrm) {
	this.pickListNoFrm = pickListNoFrm;
}

public String getItemFrm() {
	return itemFrm;
}

public void setItemFrm(String itemFrm) {
	this.itemFrm = itemFrm;
}

public String getView() {
	return view;
}

public void setView(String view) {
	this.view = view;
}

public String getSalesOrdersTo() {
	return salesOrdersTo;
}

public void setSalesOrdersTo(String salesOrdersTo) {
	this.salesOrdersTo = salesOrdersTo;
}

public Date getPostingDateTo() {
	return postingDateTo;
}

public void setPostingDateTo(Date postingDateTo) {
	this.postingDateTo = postingDateTo;
}

public Date getRequiredDateTo() {
	return requiredDateTo;
}

public void setRequiredDateTo(Date requiredDateTo) {
	this.requiredDateTo = requiredDateTo;
}

public Date getDeliveryDueDateTo() {
	return deliveryDueDateTo;
}

public void setDeliveryDueDateTo(Date deliveryDueDateTo) {
	this.deliveryDueDateTo = deliveryDueDateTo;
}

public Date getCancellationDateTo() {
	return cancellationDateTo;
}

public void setCancellationDateTo(Date cancellationDateTo) {
	this.cancellationDateTo = cancellationDateTo;
}

public String getPickListNoTo() {
	return pickListNoTo;
}

public void setPickListNoTo(String pickListNoTo) {
	this.pickListNoTo = pickListNoTo;
}

public String getItemTo() {
	return itemTo;
}

public void setItemTo(String itemTo) {
	this.itemTo = itemTo;
}

public String view;
public String salesOrdersTo;
public Date postingDateTo;
public Date requiredDateTo;
public Date deliveryDueDateTo;
public Date cancellationDateTo;
public String pickListNoTo;
public String itemTo;
public String sortBy;


	public String getSortBy() {
	return sortBy;
}

public void setSortBy(String sortBy) {
	this.sortBy = sortBy;
}

	public static void main(String[] args) {
		view(PickAndPackManager.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
