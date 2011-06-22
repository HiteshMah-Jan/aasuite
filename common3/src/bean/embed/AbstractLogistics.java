package bean.embed;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class AbstractLogistics implements Serializable {
	@Embedded
	public AbstractAddress shipTo;
	@Embedded
	public AbstractAddress billTo;
	public String shippingType;
	public boolean printPickingSheet;
	public boolean purchaseOrders;
	public boolean approved;
	public boolean allowPartialDelivery;
	public String pickAndPackRemarks;
	public String bpChannelName;
	public String bpChannelContact;
	public AbstractAddress getShipTo() {
		return shipTo;
	}
	public void setShipTo(AbstractAddress shipTo) {
		this.shipTo = shipTo;
	}
	public AbstractAddress getBillTo() {
		return billTo;
	}
	public void setBillTo(AbstractAddress billTo) {
		this.billTo = billTo;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public boolean isPrintPickingSheet() {
		return printPickingSheet;
	}
	public void setPrintPickingSheet(boolean printPickingSheet) {
		this.printPickingSheet = printPickingSheet;
	}
	public boolean isPurchaseOrders() {
		return purchaseOrders;
	}
	public void setPurchaseOrders(boolean purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isAllowPartialDelivery() {
		return allowPartialDelivery;
	}
	public void setAllowPartialDelivery(boolean allowPartialDelivery) {
		this.allowPartialDelivery = allowPartialDelivery;
	}
	public String getPickAndPackRemarks() {
		return pickAndPackRemarks;
	}
	public void setPickAndPackRemarks(String pickAndPackRemarks) {
		this.pickAndPackRemarks = pickAndPackRemarks;
	}
	public String getBpChannelName() {
		return bpChannelName;
	}
	public void setBpChannelName(String bpChannelName) {
		this.bpChannelName = bpChannelName;
	}
	public String getBpChannelContact() {
		return bpChannelContact;
	}
	public void setBpChannelContact(String bpChannelContact) {
		this.bpChannelContact = bpChannelContact;
	}

}
