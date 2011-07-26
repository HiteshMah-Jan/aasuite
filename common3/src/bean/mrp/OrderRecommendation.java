package bean.mrp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "OrderRecommendation")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="OrderType"),
	@Display(name="Scenario"),
	@Display(name="DueDate"),
	@Display(name="DDFrm"),
	@Display(name="DDTo"),
	@Display(name="Items"),
	@Display(name="Code"),
	@Display(name="CFrm"),
	@Display(name="CTo"),
	@Display(name="Vendors"),
	@Display(name="VCode"),
	@Display(name="VCFrm"),
	@Display(name="VCTo"),
	@Display(name="Group")
})
public class OrderRecommendation extends AbstractIBean {
	@Id
public String OrderType;
	public String Scenario;
	public String DueDate;
	public Double DDFrm;
	public Double DDTo;
	public String Items;
	public String Code;
	public String CFrm;
	public String CTo;
	public String Vendors;
	public String VCode;
	public String VCFrm;
	public String VCTo;
	public String Group;
	
public String getOrderType() {
		return OrderType;
	}
	public void setOrderType(String orderType) {
		OrderType = orderType;
	}
	public String getScenario() {
		return Scenario;
	}
	public void setScenario(String scenario) {
		Scenario = scenario;
	}
	public String getDueDate() {
		return DueDate;
	}
	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}
	public Double getDDFrm() {
		return DDFrm;
	}
	public void setDDFrm(Double dDFrm) {
		DDFrm = dDFrm;
	}
	public Double getDDTo() {
		return DDTo;
	}
	public void setDDTo(Double dDTo) {
		DDTo = dDTo;
	}
	public String getItems() {
		return Items;
	}
	public void setItems(String items) {
		Items = items;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getCFrm() {
		return CFrm;
	}
	public void setCFrm(String cFrm) {
		CFrm = cFrm;
	}
	public String getCTo() {
		return CTo;
	}
	public void setCTo(String cTo) {
		CTo = cTo;
	}
	public String getVendors() {
		return Vendors;
	}
	public void setVendors(String vendors) {
		Vendors = vendors;
	}
	public String getVCode() {
		return VCode;
	}
	public void setVCode(String vCode) {
		VCode = vCode;
	}
	public String getVCFrm() {
		return VCFrm;
	}
	public void setVCFrm(String vCFrm) {
		VCFrm = vCFrm;
	}
	public String getVCTo() {
		return VCTo;
	}
	public void setVCTo(String vCTo) {
		VCTo = vCTo;
	}
	public String getGroup() {
		return Group;
	}
	public void setGroup(String group) {
		Group = group;
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
