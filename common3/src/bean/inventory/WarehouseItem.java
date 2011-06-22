package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WarehouseItem")
public class WarehouseItem {
	@Id
	public int seq;
	public String warehouseCode;
	public String itemNumber;
	public double locked;
	public double inStock;
	public double committed;
	public double ordered;
	public double available;
	public double minimumInvestment;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public double getLocked() {
		return locked;
	}
	public void setLocked(double locked) {
		this.locked = locked;
	}
	public double getInStock() {
		return inStock;
	}
	public void setInStock(double inStock) {
		this.inStock = inStock;
	}
	public double getCommitted() {
		return committed;
	}
	public void setCommitted(double committed) {
		this.committed = committed;
	}
	public double getOrdered() {
		return ordered;
	}
	public void setOrdered(double ordered) {
		this.ordered = ordered;
	}
	public double getAvailable() {
		return available;
	}
	public void setAvailable(double available) {
		this.available = available;
	}
	public double getMinimumInvestment() {
		return minimumInvestment;
	}
	public void setMinimumInvestment(double minimumInvestment) {
		this.minimumInvestment = minimumInvestment;
	}

}
