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
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","itemNo","itemDescription","priceList","discount","priceAfterDiscount","Auto"})
@Displays({
	@Display(name="bpCode"),
	@Display(name="bpName"),
	@Display(name="bpType"),
	@Display(name="priceList", type="Combo",modelCombo={"Base Price","Discount Purchase Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price","Without Price List"}),
	@Display(name="discountPercentage")
})

public class SpecialPricesForBusinessPartner extends AbstractIBean {
@Id	

public String bpCode;
public String bpName;
public String bpType;
public String priceList;
public Integer discountPercentage;

public String getBpCode() {
	return bpCode;
}
public void setBpCode(String bpCode) {
	bpCode = bpCode;
}
public String getBpName() {
	return bpName;
}
public void setBpName(String bpName) {
	bpName = bpName;
}
public String getBpType() {
	return bpType;
}
public void setBpType(String bpType) {
	bpType = bpType;
}
public String getPriceList() {
	return priceList;
}
public void setPriceList(String priceList) {
	priceList =	priceList;
}
public Integer getDiscountPercentage() {
	return discountPercentage;
}
public void setDiscountPercentage(Integer discountPercentage) {
	discountPercentage = discountPercentage;
}
public static void main(String[] args) {
	view(SpecialPricesForBusinessPartner.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
