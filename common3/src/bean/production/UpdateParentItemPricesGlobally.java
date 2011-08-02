package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "UpdateParentItemPricesSelectionCriteria")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="priceListFrm",label="Price List From", type="Combo",modelCombo={"Base Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"}),
	
	@Display(name="priceListTo",label="To", type="Combo",modelCombo={"Base Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"}),
	@Display(name="codeFrm",label="Code From"),
	@Display(name="codeTo",label="To"),
	@Display(name="selectionCriterion"),
	@Display(name="parentItems"),
	@Display(name="componentsItems"),
	@Display(name="itemGroup", type="Combo",modelCombo={})
})
public class UpdateParentItemPricesGlobally extends AbstractIBean{
@Id
public String priceListFrm;
public String priceListTo;
public String codeFrm;
public String codeTo;
public String selectionCriterion;
public String parentItems;
public String componentsItems;
public String itemGroup;




public String getPriceListFrm() {
	return priceListFrm;
}

public void setPriceListFrm(String priceListFrm) {
	priceListFrm = priceListFrm;
}

public String getPriceListTo() {
	return priceListTo;
}

public void setPriceListTo(String priceListTo) {
	priceListTo = priceListTo;
}

public String getCodeFrm() {
	return codeFrm;
}

public void setCodeFrm(String codeFrm) {
	codeFrm = codeFrm;
}

public String getCodeTo() {
	return codeTo;
}

public void setCodeTo(String codeTo) {
	codeTo = codeTo;
}

public String getSelectionCriterion() {
	return selectionCriterion;
}

public void setSelectionCriterion(String selectionCriterion) {
	selectionCriterion = selectionCriterion;
}

public String getParentItems() {
	return parentItems;
}

public void setParentItems(String parentItems) {
	parentItems = parentItems;
}

public String getComponentsItems() {
	return componentsItems;
}

public void setComponentsItems(String componentsItems) {
	componentsItems = componentsItems;
}

public String getItemGroup() {
	return itemGroup;

}
public void setItemGroup(String itemGroup) {
	itemGroup = itemGroup;
}

public static void main(String[] args) {
	view(UpdateParentItemPricesGlobally.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

	
}