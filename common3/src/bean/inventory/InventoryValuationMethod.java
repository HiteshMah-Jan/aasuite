package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "InventoryValuationMethod")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="itemNoFrm",label="Item No. From"),
	@Display(name="itemNoTo", label="To"),
	@Display(name="group", type="Combo", modelCombo={}),
	@Display(name="defaultValuationMethod", type="Combo", modelCombo={})
})
public class InventoryValuationMethod extends AbstractIBean{
@Id
public String itemNoFrm;
public String itemNoTo;
public String group;
public String defaultValuationMethod;

	public String getItemNoFrm() {
	return itemNoFrm;
}

public void setItemNoFrm(String itemNoFrm) {
	this.itemNoFrm = itemNoFrm;
}

public String getItemNoTo() {
	return itemNoTo;
}

public void setItemNoTo(String itemNoTo) {
	this.itemNoTo = itemNoTo;
}

public String getGroup() {
	return group;
}

public void setGroup(String group) {
	this.group = group;
}

public String getDefaultValuationMethod() {
	return defaultValuationMethod;
}

public void setDefaultValuationMethod(String defaultValuationMethod) {
	this.defaultValuationMethod = defaultValuationMethod;
}

	public static void main(String[] args) {
		view(InventoryValuationMethod.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
