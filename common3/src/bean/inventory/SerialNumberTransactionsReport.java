package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "SerialNumberTransactionsReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="itemNumberFrm",label="Item Number From"),
	@Display(name="itemNumberTo",label="To"),
	@Display(name="group", type="Combo",modelCombo={})
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Dates", gridCount=8,
			displayFields = {

}),
@ParentAddInfo(title = "Numberings", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "Warehouse", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "BP", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "Documents", gridCount=8,
		displayFields = {

})
})
public class SerialNumberTransactionsReport extends AbstractIBean{
@Id
public String itemNumberFrm;
public String itemNumberTo;
public String group;

	public String getItemNumberFrm() {
	return itemNumberFrm;
}

public void setItemNumberFrm(String itemNumberFrm) {
	this.itemNumberFrm = itemNumberFrm;
}

public String getItemNumberTo() {
	return itemNumberTo;
}

public void setItemNumberTo(String itemNumberTo) {
	this.itemNumberTo = itemNumberTo;
}

public String getGroup() {
	return group;
}

public void setGroup(String group) {
	this.group = group;
}

	public static void main(String[] args) {
	view(SerialNumberTransactionsReport.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
