package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "ProductionOrder")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Type"),
	@Display(name="Status"),
	@Display(name="ProductNo"),
	@Display(name="ProductDescription"),
	@Display(name="PlannedQuantity"),
	@Display(name="Warehouse"),
	@Display(name="DueDate"),
	@Display(name="User"),
	@Display(name="Origin"),
	@Display(name="SalesOrder"),
	@Display(name="Customer"),
	@Display(name="DistributionRule"),
	@Display(name="Remarks", type="TextArea", gridFieldWidth=6, width=-1)
})
public class ProductionOrder extends AbstractIBean {
@Id
public String Type;
public String Status;
public Double ProductNo;
public String ProductDescription;
public Double PlannedQuantity;
public Double Warehouse;
public Double DueDate;
public String User;
public String Origin;
public String SalesOrder;
public String Customer;
public String DistributionRule;
public String Remarks;


public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public Double getProductNo() {
	return ProductNo;
}
public void setProductNo(Double productNo) {
	ProductNo = productNo;
}
public String getProductDescription() {
	return ProductDescription;
}
public void setProductDescription(String productDescription) {
	ProductDescription = productDescription;
}
public Double getPlannedQuantity() {
	return PlannedQuantity;
}
public void setPlannedQuantity(Double plannedQuantity) {
	PlannedQuantity = plannedQuantity;
}
public Double getWarehouse() {
	return Warehouse;
}
public void setWarehouse(Double warehouse) {
	Warehouse = warehouse;
}
public Double getDueDate() {
	return DueDate;
}
public void setDueDate(Double dueDate) {
	DueDate = dueDate;
}
public String getUser() {
	return User;
}
public void setUser(String user) {
	User = user;
}
public String getOrigin() {
	return Origin;
}
public void setOrigin(String origin) {
	Origin = origin;
}
public String getSalesOrder() {
	return SalesOrder;
}
public void setSalesOrder(String salesOrder) {
	SalesOrder = salesOrder;
}
public String getCustomer() {
	return Customer;
}
public void setCustomer(String customer) {
	Customer = customer;
}
public String getDistributionRule() {
	return DistributionRule;
}
public void setDistributionRule(String distributionRule) {
	DistributionRule = distributionRule;
}
public String getRemarks() {
	return Remarks;
}
public void setRemarks(String remarks) {
	Remarks = remarks;
}
public static void main(String[] args) {
	view(ProductionOrder.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
