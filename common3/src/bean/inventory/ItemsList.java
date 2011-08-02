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
@Table(name = "ItemsList")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="itemNoFrm",label="Item No. From"),
	@Display(name="group",type="Combo",modelCombo={}),
	@Display(name="itemNoTo",label="Item No. To")
})
public class ItemsList extends AbstractIBean{
@Id
public String itemNoFrm;
public String group;

public String itemNoTo;

	public static void main(String[] args) {
	view(ItemsList.class);
}

public String getItemNoFrm() {
		return itemNoFrm;
	}

	public void setItemNoFrm(String itemNoFrm) {
		this.itemNoFrm = itemNoFrm;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getItemNoTo() {
		return itemNoTo;
	}

	public void setItemNoTo(String itemNoTo) {
		this.itemNoTo = itemNoTo;
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
