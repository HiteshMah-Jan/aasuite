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

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "InventoryPostingList")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="dateFrm",label="From"),
	@Display(name="dateTo",label="To")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Items", gridCount=8,
			displayFields = {

}),
@ParentAddInfo(title = "BP", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "Others", gridCount=8,
		displayFields = {

})
})
public class InventoryPostingList extends AbstractIBean{
@Id
public Date dateFrm;
public Date getDateFrm() {
	return dateFrm;
}

public void setDateFrm(Date dateFrm) {
	this.dateFrm = dateFrm;
}

public Date getDateTo() {
	return dateTo;
}

public void setDateTo(Date dateTo) {
	this.dateTo = dateTo;
}

public Date dateTo;

	public static void main(String[] args) {
	view(InventoryPostingList.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
