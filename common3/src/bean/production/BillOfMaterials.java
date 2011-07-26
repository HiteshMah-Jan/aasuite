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
@Table(name = "BillOfMaterials")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="ProductNo"),
	@Display(name="XQuantity"),
	@Display(name="Warehouse"),
	@Display(name="ProductDescription"),
	@Display(name="BOMType"),
	@Display(name="PriceList"),
	@Display(name="DistrRule"),
	@Display(name="ProductPrice")
})
public class BillOfMaterials extends AbstractIBean {
@Id
public Double ProductNo;
public Integer XQuantity;
public String Warehouse;
public String ProductDescription;
public String BOMType;
public String PriceList;
public String DistrRule;
public String ProductPrice;

public Double getProductNo() {
	return ProductNo;
}
public void setProductNo(Double productNo) {
	ProductNo = productNo;
}
public Integer getXQuantity() {
	return XQuantity;
}
public void setXQuantity(Integer xQuantity) {
	XQuantity = xQuantity;
}
public String getWarehouse() {
	return Warehouse;
}
public void setWarehouse(String warehouse) {
	Warehouse = warehouse;
}
public String getProductDescription() {
	return ProductDescription;
}
public void setProductDescription(String productDescription) {
	ProductDescription = productDescription;
}
public String getBOMType() {
	return BOMType;
}
public void setBOMType(String bOMType) {
	BOMType = bOMType;
}
public String getPriceList() {
	return PriceList;
}
public void setPriceList(String priceList) {
	PriceList = priceList;
}
public String getDistrRule() {
	return DistrRule;
}
public void setDistrRule(String distrRule) {
	DistrRule = distrRule;
}
public String getProductPrice() {
	return ProductPrice;
}
public void setProductPrice(String productPrice) {
	ProductPrice = productPrice;
}
public static void main(String[] args) {
	view(BillOfMaterials.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
