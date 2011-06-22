package bean.embed.item;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AbstractItemInventory implements Serializable {
	public String glAccount;
	public boolean manageByWarehouse;
	public String uom;
	public String valuationMethod;
	public double itemCost;
	public double requiredCount;
	public double minimumCount;
	public double maximumCount;
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public boolean isManageByWarehouse() {
		return manageByWarehouse;
	}
	public void setManageByWarehouse(boolean manageByWarehouse) {
		this.manageByWarehouse = manageByWarehouse;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getValuationMethod() {
		return valuationMethod;
	}
	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}
	public double getItemCost() {
		return itemCost;
	}
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	public double getRequiredCount() {
		return requiredCount;
	}
	public void setRequiredCount(double requiredCount) {
		this.requiredCount = requiredCount;
	}
	public double getMinimumCount() {
		return minimumCount;
	}
	public void setMinimumCount(double minimumCount) {
		this.minimumCount = minimumCount;
	}
	public double getMaximumCount() {
		return maximumCount;
	}
	public void setMaximumCount(double maximumCount) {
		this.maximumCount = maximumCount;
	}

}
