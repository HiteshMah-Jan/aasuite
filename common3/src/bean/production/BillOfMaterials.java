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
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 2, columnSearch = {"number","itemNo","itemDescription","quantity","uoM","warehoue","issueMethod","priceList","unitPrice","total","comments"})
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"productNo","xQuantity","productDescription"}, verticalMerge=true),
	@Display(name="productNo"),
	@Display(name="xQuantity"),
	@Display(name="productDescription", type="TextArea", gridFieldWidth=2, width=-1),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"bOMType"}, verticalMerge=false),
	@Display(name="bOMType", type="Combo", modelCombo={"Sales","Production","Template" }),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"warehouse"}, verticalMerge=true),
	@Display(name="warehouse", type="Combo", modelCombo={}),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"priceList","distrRule"}, verticalMerge=true),
	@Display(name="priceList", type="Combo", modelCombo={"Base Price","Discount Purchase Price","Distributor Sales Price","Regular Purchase Price","Regular Sales Price","Small Account Sales Price","Last Purchase Price","Last Evaluated Price"}),
	@Display(name="distrRule", type="Combo", modelCombo={}),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"productPrice"}, verticalMerge=true),
	@Display(name="productPrice"),
})
public class BillOfMaterials extends AbstractIBean {
@Id
public Double productNo;
public Integer xQuantity;

public String productDescription;
public String bOMType;

public String warehouse;

public String priceList;
public String distrRule;

public Integer productPrice;

public Integer getxQuantity() {
	return xQuantity;
}
public void setxQuantity(Integer xQuantity) {
	this.xQuantity = xQuantity;
}
public String getbOMType() {
	return bOMType;
}
public void setbOMType(String bOMType) {
	this.bOMType = bOMType;
}
public Integer getProductPrice() {
	return productPrice;
}
public void setProductPrice(Integer productPrice) {
	this.productPrice = productPrice;
}
public Double getProductNo() {
	return productNo;
}
public void setProductNo(Double productNo) {
	productNo = productNo;
}
public Integer getXQuantity() {
	return xQuantity;
}
public void setXQuantity(Integer xQuantity) {
	xQuantity = xQuantity;
}
public String getProductDescription() {
	return productDescription;
}
public void setProductDescription(String productDescription) {
	productDescription = productDescription;
}
public String getBOMType() {
	return bOMType;
}
public void setBOMType(String bOMType) {
	bOMType = bOMType;
}
public String getWarehouse() {
	return warehouse;
}
public void setWarehouse(String warehouse) {
	warehouse = warehouse;
}
public String getPriceList() {
	return priceList;
}
public void setPriceList(String priceList) {
	priceList = priceList;
}
public String getDistrRule() {
	return distrRule;
}
public void setDistrRule(String distrRule) {
	distrRule = distrRule;
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
