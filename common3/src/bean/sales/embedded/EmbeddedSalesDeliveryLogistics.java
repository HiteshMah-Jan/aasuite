package bean.sales.embedded;

import javax.persistence.Embeddable;

import bean.reference.Country;
import bean.reference.State;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 6, columnSearch = { "billToContact", "shipToContact" })
@Displays({
    	@Display(name="dummyField", type="MergePanel", label="Bill", fieldPrefix="logistics.", mergeFields={"billToContact","billToStreet","billToBuilding","billToCity","billToZipCode","billToState","billToCountry"}, verticalMerge=true),
        @Display(name="billToContact", fieldPrefix="logistics."),
        @Display(name="billToStreet", fieldPrefix="logistics.", label="Street"),
        @Display(name="billToBuilding", fieldPrefix="logistics.", label="Building"),
//        @Display(name="billToBlock", fieldPrefix="logistics.", label="Block"),
        @Display(name="billToCity", fieldPrefix="logistics.", label="City"),
        @Display(name="billToZipCode", fieldPrefix="logistics.", label="Zip Code"),
//        @Display(name="billToCounty", fieldPrefix="logistics.", label="County"),
        @Display(name="billToState", type="PopSearch", linktoBean=State.class, fieldPrefix="logistics.", label="State"),
        @Display(name="billToCountry", type="PopSearch", linktoBean=Country.class, fieldPrefix="logistics.", label="Country"),

        @Display(name="dummyField", type="MergePanel", label="Ship", fieldPrefix="logistics.", mergeFields={"shipToContact","shipToStreet","shipToBuilding","shipToCity","shipToZipCode","shipToState","shipToCountry"}, verticalMerge=true),
        @Display(name="shipToContact", fieldPrefix="logistics."),
        @Display(name="shipToStreet", fieldPrefix="logistics.", label="Street"),
        @Display(name="shipToBuilding", fieldPrefix="logistics.", label="Building"),
//        @Display(name="shipToBlock", fieldPrefix="logistics.", label="Block"),
        @Display(name="shipToCity", fieldPrefix="logistics.", label="City"),
        @Display(name="shipToZipCode", fieldPrefix="logistics.", label="Zip Code"),
//        @Display(name="shipToCounty", fieldPrefix="logistics.", label="County"),
        @Display(name="shipToState", type="PopSearch", linktoBean=State.class, fieldPrefix="logistics.", label="State"),
        @Display(name="shipToCountry", type="PopSearch", linktoBean=Country.class, fieldPrefix="logistics.", label="Country"),

        @Display(name="dummyField", type="MergePanel", noLabel=false, fieldPrefix="logistics.", 
        		mergeFields={"shippingType","pickAndPackRemarks","bpChannelName","bpChannelContact","trackingNumber","stampNumber"}, verticalMerge=true),
        @Display(name="shippingType", type="Combo", sqlCombo="SELECT a FROM ShippingType a", fieldPrefix="logistics."),
        @Display(name="pickAndPackRemarks", fieldPrefix="logistics."),
        @Display(name="bpChannelName", fieldPrefix="logistics.", label="BP Channel"),
        @Display(name="bpChannelContact", fieldPrefix="logistics.", label="BP Contact"),
        
//        @Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="logistics.", mergeFields={"trackingNumber","stampNumber"}, verticalMerge=true),
        @Display(name="trackingNumber", fieldPrefix="logistics."),
        @Display(name="stampNumber", fieldPrefix="logistics.")
        
})
public class EmbeddedSalesDeliveryLogistics extends AbstractIBean {
	public static void main(String[] args) {
		viewEmbedded(EmbeddedSalesDeliveryLogistics.class);
	}
	public String billToContact;
	public String billToStreet;
	public String billToBuilding;
	public String billToBlock;
	public String billToCity;
	public String billToZipCode;
	public String billToCounty;
	public String billToState;
	public String billToCountry;

	public String shipToContact;
	public String shipToStreet;
	public String shipToBuilding;
	public String shipToBlock;
	public String shipToCity;
	public String shipToZipCode;
	public String shipToCounty;
	public String shipToState;
	public String shipToCountry;
	
    public String shippingType;
    public String trackingNumber;
    public String stampNumber;
    public String pickAndPackRemarks;
    public String bpChannelName;
    public String bpChannelContact;
       
	public String getBillToContact() {
		return billToContact;
	}


	public void setBillToContact(String billToContact) {
		this.billToContact = billToContact;
	}


	public String getBillToStreet() {
		return billToStreet;
	}


	public void setBillToStreet(String billToStreet) {
		this.billToStreet = billToStreet;
	}


	public String getBillToBuilding() {
		return billToBuilding;
	}


	public void setBillToBuilding(String billToBuilding) {
		this.billToBuilding = billToBuilding;
	}


	public String getBillToBlock() {
		return billToBlock;
	}


	public void setBillToBlock(String billToBlock) {
		this.billToBlock = billToBlock;
	}


	public String getBillToCity() {
		return billToCity;
	}


	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}


	public String getBillToZipCode() {
		return billToZipCode;
	}


	public void setBillToZipCode(String billToZipCode) {
		this.billToZipCode = billToZipCode;
	}


	public String getBillToCounty() {
		return billToCounty;
	}


	public void setBillToCounty(String billToCounty) {
		this.billToCounty = billToCounty;
	}


	public String getBillToState() {
		return billToState;
	}


	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}


	public String getBillToCountry() {
		return billToCountry;
	}


	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}


	public String getShipToContact() {
		return shipToContact;
	}


	public void setShipToContact(String shipToContact) {
		this.shipToContact = shipToContact;
	}


	public String getShipToStreet() {
		return shipToStreet;
	}


	public void setShipToStreet(String shipToStreet) {
		this.shipToStreet = shipToStreet;
	}


	public String getShipToBuilding() {
		return shipToBuilding;
	}


	public void setShipToBuilding(String shipToBuilding) {
		this.shipToBuilding = shipToBuilding;
	}


	public String getShipToBlock() {
		return shipToBlock;
	}


	public void setShipToBlock(String shipToBlock) {
		this.shipToBlock = shipToBlock;
	}


	public String getShipToCity() {
		return shipToCity;
	}


	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}


	public String getShipToZipCode() {
		return shipToZipCode;
	}


	public void setShipToZipCode(String shipToZipCode) {
		this.shipToZipCode = shipToZipCode;
	}


	public String getShipToCounty() {
		return shipToCounty;
	}


	public void setShipToCounty(String shipToCounty) {
		this.shipToCounty = shipToCounty;
	}


	public String getShipToState() {
		return shipToState;
	}


	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}


	public String getShipToCountry() {
		return shipToCountry;
	}


	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}


	public String getShippingType() {
		return shippingType;
	}


	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}


	public String getTrackingNumber() {
		return trackingNumber;
	}


	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}


	public String getStampNumber() {
		return stampNumber;
	}


	public void setStampNumber(String stampNumber) {
		this.stampNumber = stampNumber;
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


	@Override
	public String popupSearch(String criteria) {
		return null;
	}
	
}
