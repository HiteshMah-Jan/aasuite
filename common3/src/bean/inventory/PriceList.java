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
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","priceListNAme","basePriceList","factor","roundingMethod","authorizationMethod"})
@Displays({

})
public class PriceList extends AbstractIBean {
@Id	
	
public static void main(String[] args) {
	view(PriceList.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
