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
@Entity
@Table(name = "InventoryAuditReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="systemDatePostingDate", noLabel=true, type="Combo",modelCombo={"System Date","Posting Date"}),
	@Display(name="Frm"),
	@Display(name="To"),
	@Display(name="itemsCodeFrm",label="Code From"),
	@Display(name="itemGroup", type="Combo",modelCombo={}),
	@Display(name="itemsCodeTo",label="To")
})
public class InventoryAuditReport extends AbstractIBean{
@Id
public String systemDatePostingDate;
@Temporal(javax.persistence.TemporalType.DATE)
public String Frm;
@Temporal(javax.persistence.TemporalType.DATE)
public String To;

public String itemsCodeFrm;
public String itemGroup;

public String itemsCodeTo;


	public String getSystemDatePostingDate() {
	return systemDatePostingDate;
}


public void setSystemDatePostingDate(String systemDatePostingDate) {
	this.systemDatePostingDate = systemDatePostingDate;
}


public String getFrm() {
	return Frm;
}


public void setFrm(String frm) {
	Frm = frm;
}


public String getTo() {
	return To;
}


public void setTo(String to) {
	To = to;
}


public String getItemsCodeFrm() {
	return itemsCodeFrm;
}


public void setItemsCodeFrm(String itemsCodeFrm) {
	this.itemsCodeFrm = itemsCodeFrm;
}


public String getItemGroup() {
	return itemGroup;
}


public void setItemGroup(String itemGroup) {
	this.itemGroup = itemGroup;
}


public String getItemsCodeTo() {
	return itemsCodeTo;
}


public void setItemsCodeTo(String itemsCodeTo) {
	this.itemsCodeTo = itemsCodeTo;
}


	public static void main(String[] args) {
	view(InventoryAuditReport.class);
}


	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
