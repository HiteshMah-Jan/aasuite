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
@Table(name = "DiscountGroups")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="bpCode"),
	@Display(name="bpNAme")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Item Group", gridCount=8,
				displayFields = {

}),
@ParentAddInfo(title = "Properties", gridCount=8,
		displayFields = {

	}),
	@ParentAddInfo(title = "Manufacturer", gridCount=8,
			displayFields = {

		})
})
public class DiscountGroups extends AbstractIBean{
@Id
public String bpCode;
public String bpNAme;

	public String getBpCode() {
	return bpCode;
}

public void setBpCode(String bpCode) {
	this.bpCode = bpCode;
}

public String getBpNAme() {
	return bpNAme;
}

public void setBpNAme(String bpNAme) {
	this.bpNAme = bpNAme;
}

	public static void main(String[] args) {
		view(DiscountGroups.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
