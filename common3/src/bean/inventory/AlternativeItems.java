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
@Table(name = "AlternativeItems")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"number","itemNo","remarks","matchFactor"})
@Displays({
	@Display(name="itemNo"),
	@Display(name="Find"),
	@Display(name="itemDescription")
})
public class AlternativeItems extends AbstractIBean{
	@Id
	
	public double itemNo;
	public String Find;
	public String itemDescription;
	
	public double getItemNo() {
		return itemNo;
	}

	public void setItemNo(double itemNo) {
		this.itemNo = itemNo;
	}

	public String getFind() {
		return Find;
	}

	public void setFind(String find) {
		Find = find;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public static void main(String[] args) {
		view(AlternativeItems.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
