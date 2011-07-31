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
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"productNo","xQuantity","warehouse"}, verticalMerge=false),
	@Display(name="productNo"),
	@Display(name="xQuantity"),
	@Display(name="warehouse"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"productDescription"}, verticalMerge=true),
	@Display(name="productDescription", type="TextArea", gridFieldWidth=4),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="production.", 
			mergeFields={"bOMType","distrRule","priceList"}, verticalMerge=false),
	@Display(name="bOMType", type="Combo", modelCombo={"Sales","Production","Template" }),
	@Display(name="distrRule"),
	
	@Display(name="priceList"),
})
public class BillOfMaterials extends AbstractIBean {
@Id
public Double productNo;
public Integer xQuantity;
public String warehouse;

public String productDescription;
public String bOMType;
public String priceList;
public String distrRule;

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
public String getWarehouse() {
	return warehouse;
}
public void setWarehouse(String warehouse) {
	warehouse = warehouse;
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
