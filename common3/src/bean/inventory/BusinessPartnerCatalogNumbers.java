package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "BusinessPartnerCatalogNumbers")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({

	})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "BP", gridCount=8,
				displayFields = {
						
		}),
		@ParentAddInfo(title = "Items", gridCount=8,
				displayFields = {
						
		}),
})
public class BusinessPartnerCatalogNumbers extends AbstractIBean{
	
	public static void main(String[] args) {
		view(BusinessPartnerCatalogNumbers.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
