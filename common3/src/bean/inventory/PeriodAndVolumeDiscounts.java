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
@Table(name = "PeriodAndVolumeDiscounts")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"number","itemNo","itemDescription","price"})
@Displays({
	@Display(name="priceList", type="Combo",modelCombo={})
})
public class PeriodAndVolumeDiscounts extends AbstractIBean{
@Id
public String priceList;
	public String getPriceList() {
	return priceList;
}
public void setPriceList(String priceList) {
	this.priceList = priceList;
}
	public static void main(String[] args) {
		view(PeriodAndVolumeDiscounts.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
