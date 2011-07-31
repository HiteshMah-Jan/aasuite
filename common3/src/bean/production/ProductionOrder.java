package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "ProductionOrder")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="type", type="Combo", modelCombo={"Standard","Special","Disassembly"}),
	@Display(name="status",type="Combo", modelCombo={"Planned"}),
	@Display(name="productNo"),
	@Display(name="productDescription"),
	@Display(name="plannedQuantity"),
	@Display(name="warehouse"),
	
	@Display(name="number"),
	@Display(name="orderDate"),
	@Display(name="dueDate"),
	@Display(name="user",type="Combo", modelCombo={}),
	@Display(name="origin"),
	@Display(name="salesOrder"),
	@Display(name="customer"),
	@Display(name="distributionRule",type="Combo", modelCombo={"Define New"}),
	@Display(name="remarks", type="TextArea", gridFieldWidth=6, width=-1)
})
public class ProductionOrder extends AbstractIBean {
@Id
public String type;
public String status;
public Double productNo;
public String productDescription;
public Double plannedQuantity;
public Double warehouse;

public double number;
@Temporal(value = TemporalType.DATE)
public double orderDate;
@Temporal(value = TemporalType.DATE)
public Double dueDate;
public String user;
public String origin;
public String salesOrder;
public String customer;
public String distributionRule;
public String remarks;

public String getType() {
	return type;
}
public void setType(String type) {
	type = type;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	status = status;
}
public Double getProductNo() {
	return productNo;
}
public void setProductNo(Double productNo) {
	productNo = productNo;
}
public String getProductDescription() {
	return productDescription;
}
public void setProductDescription(String productDescription) {
	productDescription = productDescription;
}
public Double getPlannedQuantity() {
	return plannedQuantity;
}
public void setPlannedQuantity(Double plannedQuantity) {
	plannedQuantity = plannedQuantity;
}
public Double getWarehouse() {
	return warehouse;
}
public double getNumber() {
	return number;
}
public void setNumber(double number) {
	this.number = number;
}
public double getOrderDate() {
	return orderDate;
}
public void setOrderDate(double orderDate) {
	this.orderDate = orderDate;
}
public void setWarehouse(Double warehouse) {
	warehouse = warehouse;
}
public Double getDueDate() {
	return dueDate;
}
public void setDueDate(Double dueDate) {
	dueDate = dueDate;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	user = user;
}
public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	origin = origin;
}
public String getSalesOrder() {
	return salesOrder;
}
public void setSalesOrder(String salesOrder) {
	salesOrder = salesOrder;
}
public String getCustomer() {
	return customer;
}
public void setCustomer(String customer) {
	customer = customer;
}
public String getDistributionRule() {
	return distributionRule;
}
public void setDistributionRule(String distributionRule) {
	distributionRule = distributionRule;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	remarks = remarks;
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
