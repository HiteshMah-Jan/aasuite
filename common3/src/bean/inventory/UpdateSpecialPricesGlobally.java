package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "UpdateSpecialPricesGlobally")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Change Discount %", gridCount=8,
				displayFields = {

}),
@ParentAddInfo(title = "Change Price", gridCount=8,
		displayFields = {

	}),
	@ParentAddInfo(title = "Refresh by Price List", gridCount=8,
			displayFields = {

		}),
		@ParentAddInfo(title = "Delete", gridCount=8,
				displayFields = {

			})
})
public class UpdateSpecialPricesGlobally extends AbstractIBean{
@Id

	public static void main(String[] args) {
	view(UpdateSpecialPricesGlobally.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
