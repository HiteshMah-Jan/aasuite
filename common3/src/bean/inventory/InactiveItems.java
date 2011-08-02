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
@Table(name = "InactiveItems")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="itemNoFrm",label="Item No. From"),
	@Display(name="dateFrm",label="Date From"),
	@Display(name="itemGroup"),
	@Display(name="itemNoTo",label="Item No. To")
})
public class InactiveItems extends AbstractIBean{
@Id
public String itemNoFrm;
public Date dateFrm;
public String itemGroup;

public String itemNoTo;
	
	public String getItemNoFrm() {
	return itemNoFrm;
}

public void setItemNoFrm(String itemNoFrm) {
	this.itemNoFrm = itemNoFrm;
}

public Date getDateFrm() {
	return dateFrm;
}

public void setDateFrm(Date dateFrm) {
	this.dateFrm = dateFrm;
}

public String getItemGroup() {
	return itemGroup;
}

public void setItemGroup(String itemGroup) {
	this.itemGroup = itemGroup;
}

public String getItemNoTo() {
	return itemNoTo;
}

public void setItemNoTo(String itemNoTo) {
	this.itemNoTo = itemNoTo;
}

	public static void main(String[] args) {
	view(InactiveItems.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
