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
@Table(name = "SpecialPrices")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="BpCode"),
	@Display(name="BpName"),
	@Display(name="BpType"),
	@Display(name="PriceList"),
	@Display(name="DiscountPercentage")
})

public class SpecialPrices extends AbstractIBean {
@Id	

public String BpCode;
public String BpName;
public String BpType;
public String PriceList;
public String DiscountPercentage;

public String getBpCode() {
	return BpCode;
}
public void setBpCode(String bpCode) {
	BpCode = bpCode;
}
public String getBpName() {
	return BpName;
}
public void setBpName(String bpName) {
	BpName = bpName;
}
public String getBpType() {
	return BpType;
}
public void setBpType(String bpType) {
	BpType = bpType;
}
public String getPriceList() {
	return PriceList;
}
public void setPriceList(String priceList) {
	PriceList = priceList;
}
public String getDiscountPercentage() {
	return DiscountPercentage;
}
public void setDiscountPercentage(String discountPercentage) {
	DiscountPercentage = discountPercentage;
}
public static void main(String[] args) {
	view(SpecialPrices.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
