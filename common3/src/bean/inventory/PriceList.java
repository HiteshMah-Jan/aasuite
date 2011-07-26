package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;


@Entity
@Table(name = "PriceList")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="UpdateEntirePriceList"),
	@Display(name="UpdateBySelection")
})
public class PriceList extends AbstractIBean {
@Id	
	public String UpdateEntirePriceList;
	public String UpdateBySelection;
	
public String getUpdateEntirePriceList() {
		return UpdateEntirePriceList;
	}
	public void setUpdateEntirePriceList(String updateEntirePriceList) {
		UpdateEntirePriceList = updateEntirePriceList;
	}
	public String getUpdateBySelection() {
		return UpdateBySelection;
	}
	public void setUpdateBySelection(String updateBySelection) {
		UpdateBySelection = updateBySelection;
	}
public static void main(String[] args) {
	view(PriceList.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
