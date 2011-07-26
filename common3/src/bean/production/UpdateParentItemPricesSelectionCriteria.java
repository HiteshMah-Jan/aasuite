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
	@Display(name="PriceList"),
	@Display(name="PLFrm"),
	@Display(name="PLTo"),
	@Display(name="Code"),
	@Display(name="CFrm"),
	@Display(name="CTo"),
	@Display(name="SelectionCriterion"),
	@Display(name="ParentItems"),
	@Display(name="ComponentsItems"),
	@Display(name="ItemGroup")
})
public class UpdateParentItemPricesSelectionCriteria extends AbstractIBean{
@Id
public String PriceList;
public String PLFrm;
public String PLTo;
public String Code;
public String CFrm;
public String CTo;
public String SelectionCriterion;
public String ParentItems;
public String ComponentsItems;
public String ItemGroup;




public String getPriceList() {
	return PriceList;
}

public void setPriceList(String priceList) {
	PriceList = priceList;
}

public String getPLFrm() {
	return PLFrm;
}

public void setPLFrm(String pLFrm) {
	PLFrm = pLFrm;
}

public String getPLTo() {
	return PLTo;
}

public void setPLTo(String pLTo) {
	PLTo = pLTo;
}

public String getCode() {
	return Code;
}

public void setCode(String code) {
	Code = code;
}

public String getCFrm() {
	return CFrm;
}

public void setCFrm(String cFrm) {
	CFrm = cFrm;
}

public String getCTo() {
	return CTo;
}

public void setCTo(String cTo) {
	CTo = cTo;
}

public String getSelectionCriterion() {
	return SelectionCriterion;
}

public void setSelectionCriterion(String selectionCriterion) {
	SelectionCriterion = selectionCriterion;
}

public String getParentItems() {
	return ParentItems;
}

public void setParentItems(String parentItems) {
	ParentItems = parentItems;
}

public String getComponentsItems() {
	return ComponentsItems;
}

public void setComponentsItems(String componentsItems) {
	ComponentsItems = componentsItems;
}

public String getItemGroup() {
	return ItemGroup;
}

public void setItemGroup(String itemGroup) {
	ItemGroup = itemGroup;
}

public static void main(String[] args) {
	view(UpdateParentItemPricesSelectionCriteria.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

	
}