package bean.mrp;

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
@Table(name = "OrderRecommendation")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="orderType", type="Combo",modelCombo={"all","productionOrder","purchaseOrder"}),
	@Display(name="scenario", type="Combo",modelCombo={}),
	@Display(name="dueDateFrm",label="Due Date   From"),
	@Display(name="dueDateTo",label="To"),

	@Display(name="itemsCodeFrm",label="Code   From"),
	@Display(name="itemsCodeTo",label="To"),
	@Display(name="itemsGroup",label="Group", type="Combo",modelCombo={}),
	
	@Display(name="vendorsCodeFrm",label="Code   From"),
	@Display(name="vendorsTo",label="To"),
	@Display(name="vendorsGroup",label="Group", type="Combo",modelCombo={})
})
public class OrderRecommendation extends AbstractIBean {
	@Id
	public String orderType;
	public String scenario;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date dueDateFrm;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date dueDateTo;
	public String itemsCodeFrm;
	public String itemsCodeTo;
	public String itemsGroup; 
	
	public String vendorsCodeFrm;
	public String vendorsTo;
	
	public String vendorsGroup;
	
public String getItemsCodeFrm() {
		return itemsCodeFrm;
	}
	public void setItemsCodeFrm(String itemsCodeFrm) {
		this.itemsCodeFrm = itemsCodeFrm;
	}
	public String getItemsCodeTo() {
		return itemsCodeTo;
	}
	public void setItemsCodeTo(String itemsCodeTo) {
		this.itemsCodeTo = itemsCodeTo;
	}
	public String getItemsGroup() {
		return itemsGroup;
	}
	public void setItemsGroup(String itemsGroup) {
		this.itemsGroup = itemsGroup;
	}
	public String getVendorsCodeFrm() {
		return vendorsCodeFrm;
	}
	public void setVendorsCodeFrm(String vendorsCodeFrm) {
		this.vendorsCodeFrm = vendorsCodeFrm;
	}
	public String getVendorsGroup() {
		return vendorsGroup;
	}
	public void setVendorsGroup(String vendorsGroup) {
		this.vendorsGroup = vendorsGroup;
	}
	public void setDueDateFrm(Date dueDateFrm) {
		this.dueDateFrm = dueDateFrm;
	}
	public void setVendorsTo(String vendorsTo) {
		this.vendorsTo = vendorsTo;
	}
public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		orderType = orderType;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		scenario = scenario;
	}
	public Date getDueDateFrm() {
		return dueDateFrm;
	}
	public void setdueDateFrm(Date dueDateFrm) {
		dueDateFrm = dueDateFrm;
	}

	public Date getDueDateTo() {
		return dueDateTo;
	}
	public void setDueDateTo(Date dueDateTo) {
		dueDateTo = dueDateTo;
	}
	public String getvendorsCodeFrm() {
		return vendorsCodeFrm;
	}
	public void setvendorsCodeFrm(String vendorsCodeFrm) {
		vendorsCodeFrm = vendorsCodeFrm;
	}
	public String getVendorsTo() {
		return vendorsTo;
	}
	public void setvendorsTo(String vendorsTo) {
		vendorsTo = vendorsTo;
	}
public static void main(String[] args) {
	view(OrderRecommendation.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
