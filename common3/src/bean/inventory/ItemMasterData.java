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
@Table(name = "ItemMasterData")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="itemNumber"),
	@Display(name="barCode"),
	@Display(name="description", type="TextArea", gridFieldWidth=4, width=-1),
	@Display(name="descInForeignLanguage", type="TextArea", gridFieldWidth=4, width=-1),
	@Display(name="itemType"),
	@Display(name="itemGroup"),
	@Display(name="inventoryItem"),
	@Display(name="salesItem"),
	@Display(name="purchasedItem"),
	@Display(name="fixedAsset")
})
public class ItemMasterData extends AbstractIBean {
	@Id
	public String itemNumber;
	public String barCode;
	public String description;
	public String descInForeignLanguage;
	public String itemType;
	public String itemGroup;
	public boolean inventoryItem;
	public boolean salesItem;
	public boolean purchasedItem;
	public boolean fixedAsset;
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescInForeignLanguage() {
		return descInForeignLanguage;
	}
	public void setDescInForeignLanguage(String descInForeignLanguage) {
		this.descInForeignLanguage = descInForeignLanguage;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	public boolean isInventoryItem() {
		return inventoryItem;
	}
	public void setInventoryItem(boolean inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	public boolean isSalesItem() {
		return salesItem;
	}
	public void setSalesItem(boolean salesItem) {
		this.salesItem = salesItem;
	}
	public boolean isPurchasedItem() {
		return purchasedItem;
	}
	public void setPurchasedItem(boolean purchasedItem) {
		this.purchasedItem = purchasedItem;
	}
	public boolean isFixedAsset() {
		return fixedAsset;
	}
	public void setFixedAsset(boolean fixedAsset) {
		this.fixedAsset = fixedAsset;
	}
	public static void main(String[] args) {
		view(ItemMasterData.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
