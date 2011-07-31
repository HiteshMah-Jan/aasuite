package bean.service;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;

@Entity
@Table(name = "ServiceServiceCallExpensesItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","documentType","documentNo","date","itemNo","itemDescription","quantity","frmWhse","toWhse"})
@Displays({
//	@Display(name="number", label="#"),
//	@Display(name="documentType", label = "Document Type"),
//	@Display(name="documentNo", label="Document No."),
//	@Display(name="date", label="Date"),
//	@Display(name="itemNo", label="Item No."),
//	@Display(name="itemDescription", label="Item Description"),
//	@Display(name="quantity", label="QUantity"),
//	@Display(name="frmWhse", label="From Warehouse"),
//	@Display(name="toWhse", label ="To Warehouse")
})
public class ServiceServiceCallExpensesItem extends AbstractIBean{
@Id
public double number;
public String documentType;
public double documentNo;
public Date date;
public double itemNo;
public String itemDescription;
public double quantity;
public String frmWhse;
public String toWhse;


	public double getNumber() {
	return number;
}

public void setNumber(double number) {
	this.number = number;
}

public String getDocumentType() {
	return documentType;
}

public void setDocumentType(String documentType) {
	this.documentType = documentType;
}

public double getDocumentNo() {
	return documentNo;
}

public void setDocumentNo(double documentNo) {
	this.documentNo = documentNo;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public double getItemNo() {
	return itemNo;
}

public void setItemNo(double itemNo) {
	this.itemNo = itemNo;
}

public String getItemDescription() {
	return itemDescription;
}

public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}

public double getQuantity() {
	return quantity;
}

public void setQuantity(double quantity) {
	this.quantity = quantity;
}

public String getFrmWhse() {
	return frmWhse;
}

public void setFrmWhse(String frmWhse) {
	this.frmWhse = frmWhse;
}

public String getToWhse() {
	return toWhse;
}

public void setToWhse(String toWhse) {
	this.toWhse = toWhse;
}

	public static void main(String[] args) {
		view(ServiceServiceCallExpensesItem.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
