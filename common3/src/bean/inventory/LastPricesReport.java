package bean.inventory;

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
@Table(name = "LastPricesReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"number","document","date","quantity","unitPrice","discount","priceAfterDisc","uoM"})
@Displays({
	@Display(name="bpCode",label="BP Code"),
	@Display(name="itemNo"),
	@Display(name="display"),
	@Display(name="date"),
	@Display(name="quantity")
})


public class LastPricesReport extends AbstractIBean{
@Id
public String bpCode;
public String itemNo;
public String display;
@Temporal(javax.persistence.TemporalType.DATE)
public Date date;
public Integer quantity;


	public String getBpCode() {
	return bpCode;
}


public void setBpCode(String bpCode) {
	this.bpCode = bpCode;
}


public String getItemNo() {
	return itemNo;
}


public void setItemNo(String itemNo) {
	this.itemNo = itemNo;
}


public String getDisplay() {
	return display;
}


public void setDisplay(String display) {
	this.display = display;
}


public Date getDate() {
	return date;
}


public void setDate(Date date) {
	this.date = date;
}


public Integer getQuantity() {
	return quantity;
}


public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}


	public static void main(String[] args) {
		view(LastPricesReport.class);
	}


	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
