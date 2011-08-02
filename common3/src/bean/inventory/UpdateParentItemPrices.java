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
@Table(name = "UpdateParentItemPrices")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="priceListFrm",label="Price List From",type="Combo", modelCombo={"Base Price","Discount Purchase Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"}),
	@Display(name="priceListTo",label="Price List To",type="Combo", modelCombo={"Base Price","Discount Purchase Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"}),
	@Display(name="priceListCodeFrm",label="Price List Code From"),
	@Display(name="priceListCodeTo",label="Price List Code To"),
	@Display(name="itemGroup",type="Combo", modelCombo={})
})

public class UpdateParentItemPrices extends AbstractIBean{
@Id
public String priceListFrm;
public String priceListTo;

public String priceListCodeFrm;
public String priceListCodeTo;

public String itemGroup;

	public String getPriceListFrm() {
	return priceListFrm;
}

public void setPriceListFrm(String priceListFrm) {
	this.priceListFrm = priceListFrm;
}

public String getPriceListTo() {
	return priceListTo;
}

public void setPriceListTo(String priceListTo) {
	this.priceListTo = priceListTo;
}

public String getPriceListCodeFrm() {
	return priceListCodeFrm;
}

public void setPriceListCodeFrm(String priceListCodeFrm) {
	this.priceListCodeFrm = priceListCodeFrm;
}

public String getPriceListCodeTo() {
	return priceListCodeTo;
}

public void setPriceListCodeTo(String priceListCodeTo) {
	this.priceListCodeTo = priceListCodeTo;
}

public String getItemGroup() {
	return itemGroup;
}

public void setItemGroup(String itemGroup) {
	this.itemGroup = itemGroup;
}

	public static void main(String[] args) {
		view(UpdateParentItemPrices.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
