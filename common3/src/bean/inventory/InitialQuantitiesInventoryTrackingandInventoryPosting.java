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
@Table(name = "InitialQuantitiesInventoryTrackingandInventoryPosting")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Initial Quantity", gridCount=8,
				displayFields = {
				
		}),
		@ParentAddInfo(title = "Inventory Tracking", gridCount=8,
				displayFields = {
				
		}),
		@ParentAddInfo(title = "Inventory Posting", gridCount=8,
				displayFields = {
				
		}),
		
})
public class InitialQuantitiesInventoryTrackingandInventoryPosting extends	AbstractIBean{
@Id

	public static void main(String[] args) {
		view(InitialQuantitiesInventoryTrackingandInventoryPosting.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
