package bean.inventory;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import bean.embed.item.AbstractItemGeneralDescription;
import bean.embed.item.AbstractItemInventory;
import bean.embed.item.AbstractItemPurchasingData;
import bean.embed.item.AbstractItemSalesData;

@Entity
@Table(name = "Item")
public class Item extends AbstractIBean {
	@Id
	public String itemNumber;
	public String barcode;
	public String description;
	public String otherDescription;
	public String itemType;
	public String itemGroup;
	public String priceList;
	public double unitPrice;
	public boolean inventoryItem;
	public boolean salesItem;
	public boolean purchasedItem;
	public boolean fixedAsset;
	public String remarks;

	@Embedded
	public AbstractItemGeneralDescription general;
	@Embedded
	public AbstractItemPurchasingData purchasingData;
	@Embedded
	public AbstractItemSalesData salesData;
	@Embedded
	public AbstractItemInventory inventoryData;

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOtherDescription() {
		return otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
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

	public String getPriceList() {
		return priceList;
	}

	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AbstractItemGeneralDescription getGeneral() {
		return general;
	}

	public void setGeneral(AbstractItemGeneralDescription general) {
		this.general = general;
	}

	public AbstractItemPurchasingData getPurchasingData() {
		return purchasingData;
	}

	public void setPurchasingData(AbstractItemPurchasingData purchasingData) {
		this.purchasingData = purchasingData;
	}

	public AbstractItemSalesData getSalesData() {
		return salesData;
	}

	public void setSalesData(AbstractItemSalesData salesData) {
		this.salesData = salesData;
	}

	public AbstractItemInventory getInventoryData() {
		return inventoryData;
	}

	public void setInventoryData(AbstractItemInventory inventoryData) {
		this.inventoryData = inventoryData;
	}

}
