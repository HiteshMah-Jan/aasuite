/*
 * java
 *
 * Created on Sep 30, 2007, 8:02:08 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import constants.UserInfo;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;
import util.BeanUtil;
import util.DBClient;
import bean.admin.CompanyConfig;
import bean.admin.CompanyConfigUser;
import bean.awb.AwbCharges;
import bean.awb.AwbFlt;
import bean.reference.Airport;
import bean.reference.PaymentType;
import bean.reference.ServiceLevel;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Awb")
@UITemplate(template = TemplateTabPage.class, gridCount = 10, columnSearch = {"prefix", "serial"}, showChart=true)
@Displays({
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "prefix"),
    @Display(name = "kgLb", type="Combo", modelCombo={"KG/MC","LB/CF"}),
    @Display(name = "issueDate"),
    @Display(name = "destination", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "departureDate"),
    @Display(name = "shipper", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "arrivalDate"),
    @Display(name = "pieces"),
    @Display(name = "priority"),
    @Display(name = "serial"),
    @Display(name = "serviceLevel", type="PopSearch", linktoBean=ServiceLevel.class),
    @Display(name = "agent", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "contactPerson"),
    @Display(name = "contactPhone"),
    @Display(name = "consignee", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "remarks"),
    @Display(name = "origin", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "paymentType", type="PopSearch", linktoBean=PaymentType.class),
    @Display(name = "master"),
    @Display(name = "house"),
    @Display(name = "mh"),
    @Display(name = "mp"),
    @Display(name = "lp"),
    @Display(name = "lc"),
    @Display(name = "masterAwbSeq"),
    @Display(name = "natureOfGoods"),
    @Display(name = "specialServiceRequest"),
    @Display(name = "otherServiceInformation"),
    @Display(name = "shipmentSupplementaryInformation"),
    @Display(name = "shipmentReferenceInformation"),
    @Display(name = "serviceCode"),
    @Display(name = "rateClassCode"),
    @Display(name = "uldRateClassType"),
    @Display(name = "hwbSerial"),
    @Display(name = "freeDescriptionOfGoods"),
    @Display(name = "slac"),
    @Display(name = "commodityCode"),
    @Display(name = "place"),
    @Display(name = "creditDetails")
})
@DisplayGroups({
    @DisplayGroup(title="Voucher Detail", gridCount=4, fields={"creditDetails","place","hwbSerial","destinationPrepaid"})
})
@ChildRecords({
    @ChildRecord(template=ChildTemplateListPopup.class, entity=AwbFlt.class, sql="SELECT a FROM AwbFlt a WHERE a.awbSeq='${seq}'", title="Flights")
})
public class Awb extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "businessCode", nullable = false)
    public String businessCode;
    @Column(name = "prefix", nullable = false, length = 3)
    public String prefix;
    @Column(name = "kgLb", length = 10)
    public String kgLb;
    @Column(name = "issueDate")
    @Temporal(value = TemporalType.DATE)
    public Date issueDate;
    @Column(name = "destination", nullable = false, length = 3)
    public String destination;
    @Column(name = "departureDate")
    @Temporal(value = TemporalType.DATE)
    public Date departureDate;
    @Column(name = "shipper", length = 30)
    public String shipper;
    @Column(name = "arrivalDate")
    @Temporal(value = TemporalType.DATE)
    public Date arrivalDate;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "priority")
    public int priority;
    @Column(name = "serial", nullable = false, length = 8)
    public String serial;
    @Column(name = "serviceLevel", length = 5)
    public String serviceLevel;
    @Column(name = "agent", length = 30)
    public String agent;
    @Column(name = "contactPerson", nullable = false, length = 100)
    public String contactPerson;
    @Column(name = "contactPhone", length = 25)
    public String contactPhone;
    @Column(name = "consignee", length = 30)
    public String consignee;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "origin", length = 3)
    public String origin;
    @Column(name = "paymentType", length = 5)
    public String paymentType;
    @Column(name = "master")
    public boolean master;
    @Column(name = "house")
    public boolean house;
    @Column(name = "mh")
    public int mh;
    @Column(name = "mp")
    public int mp;
    @Column(name = "lp")
    public int lp;
    @Column(name = "lc")
    public int lc;
    @Column(name = "masterAwbSeq")
    public int masterAwbSeq;
    @Column(name = "active")
    public boolean active;

    //additional fields
    @Column(name = "natureOfGoods", length = 50)
    public String natureOfGoods;
    @Column(name = "specialServiceRequest", length = 150)
    public String specialServiceRequest;
    @Column(name = "otherServiceInformation", length = 150)
    public String otherServiceInformation;
    @Column(name = "shipmentSupplementaryInformation", length = 150)
    public String shipmentSupplementaryInformation;
    @Column(name = "shipmentReferenceInformation", length = 150)
    public String shipmentReferenceInformation;
    @Column(name = "serviceCode", length = 150)
    public String serviceCode;
    @Column(name = "rateClassCode", length = 150)
    public String rateClassCode;
    @Column(name = "uldRateClassType", length = 150)
    public String uldRateClassType;
    @Column(name = "hwbSerial", length = 20)
    public String hwbSerial;
    @Column(name = "freeDescriptionOfGoods", length = 150)
    public String freeDescriptionOfGoods;
    @Column(name = "slac", length = 10)
    public String slac;
    @Column(name = "commodityCode", length = 10)
    public String commodityCode;
    @Column(name = "place", length = 100)
    public String place;
    @Column(name = "creditDetails", length = 100)
    public String creditDetails;

    @Column(name = "awbType")
    public String awbType;
    @Column(name = "descCode", length=1)
    public String descCode;
    @Column(name = "densityGroup", length=2)
    public int densityGroup;
    @Column(name = "totConsignmentPieces")
    public int totConsignmentPieces;
    @Column(name = "volumeCode", length=2)
    public String volumeCode;
    @Column(name = "uldCount")
    public int uldCount;
    @Column(name = "reqOfficeAddress")
    public String reqOfficeAddress;
    @Column(name = "bookRef")
    public String bookRef;
    @Column(name = "reqParticipant")
    public String reqParticipant;
    @Column(name = "harmonizedCode")
    public String harmonizedCode;
    @Column(name = "hshc1")
    public String hshc1;
    @Column(name = "hshc2")
    public String hshc2;
    @Column(name = "shipperSignature")
    public String shipperSignature;
    @Column(name = "carrierSignature")
    public String carrierSignature;
    @Column(name = "issuePlace")
    public String issuePlace;
    @Column(name = "senderReference")
    public String senderReference;
    @Column(name = "senderFileReference")
    public String senderFileReference;
    @Column(name = "senderPartId")
    public String senderPartId;
    @Column(name = "customsOriginCode")
    public String customsOriginCode;
    @Column(name = "commissionAmount")
    public double commissionAmount;
    @Column(name = "commissionPercent")
    public double commissionPercent;
    @Column(name = "salesIncentiveAmount")
    public double salesIncentiveAmount;
    @Column(name = "handlingParty")
    public String handlingParty;
    @Column(name = "shc1")
    public String shc1;
    @Column(name = "shc2")
    public String shc2;

    @Column(name = "originCurrency")
    public String originCurrency;
    @Column(name = "destCurrency")
    public String destCurrency;
    @Column(name = "totalOriginCharges")
    public double totalOriginCharges;
    @Column(name = "totalDestCharges")
    public double totalDestCharges;

    @Column(name = "valueCustoms")
    public double valueCustoms;
    @Column(name = "valueCarriage")
    public double valueCarriage;
    @Column(name = "valueInsurance")
    public double valueInsurance;

    @Column(name = "preCarriage")
    public String preCarriage;
    @Column(name = "placeOfReceiptByPreCarrier")
    public String placeOfReceiptByPreCarrier;
    @Column(name = "placeOfReceipt")
    public String placeOfReceipt;
    @Column(name = "placeOfDelivery")
    public String placeOfDelivery;
    @Column(name = "vesselNumber")
    public String vesselNumber;
    @Column(name = "voyageNumber")
    public String voyageNumber;
    @Column(name = "domesticRouting", length = 1000)
    public String domesticRouting;
    @Column(name = "forDeliveryOfTheGoodsApplyTo")
    public String forDeliveryOfTheGoodsApplyTo;



    public Awb() {
        departureDate = util.DateUtil.addDay(new Date(), 1);
        contactPerson = "--";
        contactPhone = "--";
//        new AwbFltBooking().setupIndex();
//        new AwbFlt().setupIndex();
//        new AwbUld().setupIndex();
//        new AwbShc().setupIndex();
//        new AwbRouting().setupIndex();
    }

    public String getForDeliveryOfTheGoodsApplyTo() {
        return forDeliveryOfTheGoodsApplyTo;
    }

    public void setForDeliveryOfTheGoodsApplyTo(String forDeliveryOfTheGoodsApplyTo) {
        this.forDeliveryOfTheGoodsApplyTo = forDeliveryOfTheGoodsApplyTo;
    }

        public String getDomesticRouting() {
		return domesticRouting;
	}

	public void setDomesticRouting(String domesticRouting) {
		this.domesticRouting = domesticRouting;
	}

	public String getPreCarriage() {
		return preCarriage;
	}

	public void setPreCarriage(String preCarriage) {
		this.preCarriage = preCarriage;
	}

	public String getPlaceOfReceiptByPreCarrier() {
		return placeOfReceiptByPreCarrier;
	}

	public void setPlaceOfReceiptByPreCarrier(String placeOfReceiptByPreCarrier) {
		this.placeOfReceiptByPreCarrier = placeOfReceiptByPreCarrier;
	}

	public String getPlaceOfReceipt() {
		return placeOfReceipt;
	}

	public void setPlaceOfReceipt(String placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}

	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getVesselNumber() {
		return vesselNumber;
	}

	public void setVesselNumber(String vesselNumber) {
		this.vesselNumber = vesselNumber;
	}

	public String getVoyageNumber() {
		return voyageNumber;
	}

	public void setVoyageNumber(String voyageNumber) {
		this.voyageNumber = voyageNumber;
	}

	public double getValueCustoms() {
		return valueCustoms;
	}

	public void setValueCustoms(double valueCustoms) {
		this.valueCustoms = valueCustoms;
	}

	public double getValueCarriage() {
		return valueCarriage;
	}

	public void setValueCarraige(double valueCarriage) {
		this.valueCarriage = valueCarriage;
	}

	public double getValueInsurance() {
		return valueInsurance;
	}

	public void setValueInsurance(double valueInsurance) {
		this.valueInsurance = valueInsurance;
	}

	public double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public double getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(double commissionPercent) {
        this.commissionPercent = commissionPercent;
    }

    public String getCustomsOriginCode() {
        return customsOriginCode;
    }

    public void setCustomsOriginCode(String customsOriginCode) {
        this.customsOriginCode = customsOriginCode;
    }

    public String getHandlingParty() {
        return handlingParty;
    }

    public void setHandlingParty(String handlingParty) {
        this.handlingParty = handlingParty;
    }

    public double getSalesIncentiveAmount() {
        return salesIncentiveAmount;
    }

    public void setSalesIncentiveAmount(double salesIncentiveAmount) {
        this.salesIncentiveAmount = salesIncentiveAmount;
    }

    public String getShc1() {
        return shc1;
    }

    public void setShc1(String shc1) {
        this.shc1 = shc1;
    }

    public String getShc2() {
        return shc2;
    }

    public void setShc2(String shc2) {
        this.shc2 = shc2;
    }

    public String getCarrierSignature() {
        return carrierSignature;
    }

    public void setCarrierSignature(String carrierSignature) {
        this.carrierSignature = carrierSignature;
    }

    public String getHarmonizedCode() {
        return harmonizedCode;
    }

    public void setHarmonizedCode(String harmonizedCode) {
        this.harmonizedCode = harmonizedCode;
    }

    public String getHshc1() {
        return hshc1;
    }

    public void setHshc1(String hshc1) {
        this.hshc1 = hshc1;
    }

    public String getHshc2() {
        return hshc2;
    }

    public void setHshc2(String hshc2) {
        this.hshc2 = hshc2;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getSenderFileReference() {
        return senderFileReference;
    }

    public void setSenderFileReference(String senderFileReference) {
        this.senderFileReference = senderFileReference;
    }

    public String getSenderPartId() {
        return senderPartId;
    }

    public void setSenderPartId(String senderPartId) {
        this.senderPartId = senderPartId;
    }

    public String getSenderReference() {
        return senderReference;
    }

    public void setSenderReference(String senderReference) {
        this.senderReference = senderReference;
    }

    public String getShipperSignature() {
        return shipperSignature;
    }

    public void setShipperSignature(String shipperSignature) {
        this.shipperSignature = shipperSignature;
    }

    public String getBookRef() {
        return bookRef;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public String getReqOfficeAddress() {
        return reqOfficeAddress;
    }

    public void setReqOfficeAddress(String reqOfficeAddress) {
        this.reqOfficeAddress = reqOfficeAddress;
    }

    public String getReqParticipant() {
        return reqParticipant;
    }

    public void setReqParticipant(String reqParticipant) {
        this.reqParticipant = reqParticipant;
    }

    public int getUldCount() {
        return uldCount;
    }

    public void setUldCount(int uldCount) {
        this.uldCount = uldCount;
    }

    public String getVolumeCode() {
        return volumeCode;
    }

    public void setVolumeCode(String volumeCode) {
        this.volumeCode = volumeCode;
    }

    public int getTotConsignmentPieces() {
        return totConsignmentPieces;
    }

    public void setTotConsignmentPieces(int totConsignmentPieces) {
        this.totConsignmentPieces = totConsignmentPieces;
    }

    public int getDensityGroup() {
        return densityGroup;
    }

    public void setDensityGroup(int densityGroup) {
        this.densityGroup = densityGroup;
    }

    public String getDescCode() {
        return descCode;
    }

    public void setDescCode(String descCode) {
        this.descCode = descCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAwbType() {
        return awbType;
    }

    public void setAwbType(String awbType) {
        this.awbType = awbType;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCreditDetails() {
        return creditDetails;
    }

    public void setCreditDetails(String creditDetails) {
        this.creditDetails = creditDetails;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFreeDescriptionOfGoods() {
        return freeDescriptionOfGoods;
    }

    public void setFreeDescriptionOfGoods(String freeDescriptionOfGoods) {
        this.freeDescriptionOfGoods = freeDescriptionOfGoods;
    }

    public boolean isHouse() {
        return house;
    }

    public void setHouse(boolean house) {
        this.house = house;
    }

    public String getHwbSerial() {
        return hwbSerial;
    }

    public void setHwbSerial(String hwbSerial) {
        this.hwbSerial = hwbSerial;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getKgLb() {
        return kgLb;
    }

    public void setKgLb(String kgLb) {
        this.kgLb = kgLb;
    }

    public int getLc() {
        return lc;
    }

    public void setLc(int lc) {
        this.lc = lc;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public int getMasterAwbSeq() {
        return masterAwbSeq;
    }

    public void setMasterAwbSeq(int masterAwbSeq) {
        this.masterAwbSeq = masterAwbSeq;
    }

    public int getMh() {
        return mh;
    }

    public void setMh(int mh) {
        this.mh = mh;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getNatureOfGoods() {
        return natureOfGoods;
    }

    public void setNatureOfGoods(String natureOfGoods) {
        this.natureOfGoods = natureOfGoods;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOtherServiceInformation() {
        return otherServiceInformation;
    }

    public void setOtherServiceInformation(String otherServiceInformation) {
        this.otherServiceInformation = otherServiceInformation;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getShipmentReferenceInformation() {
        return shipmentReferenceInformation;
    }

    public void setShipmentReferenceInformation(String shipmentReferenceInformation) {
        this.shipmentReferenceInformation = shipmentReferenceInformation;
    }

    public String getShipmentSupplementaryInformation() {
        return shipmentSupplementaryInformation;
    }

    public void setShipmentSupplementaryInformation(String shipmentSupplementaryInformation) {
        this.shipmentSupplementaryInformation = shipmentSupplementaryInformation;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getSlac() {
        return slac;
    }

    public void setSlac(String slac) {
        this.slac = slac;
    }

    public String getSpecialServiceRequest() {
        return specialServiceRequest;
    }

    public void setSpecialServiceRequest(String specialServiceRequest) {
        this.specialServiceRequest = specialServiceRequest;
    }

    public String getUldRateClassType() {
        return uldRateClassType;
    }

    public void setUldRateClassType(String uldRateClassType) {
        this.uldRateClassType = uldRateClassType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }   
    
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public String getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}

	public String getDestCurrency() {
		return destCurrency;
	}

	public void setDestCurrency(String destCurrency) {
		this.destCurrency = destCurrency;
	}

	public double getTotalOriginCharges() {
		return totalOriginCharges;
	}

	public void setTotalOriginCharges(double totalOriginCharges) {
		this.totalOriginCharges = totalOriginCharges;
	}

	public double getTotalDestCharges() {
		return totalDestCharges;
	}

	public void setTotalDestCharges(double totalDestCharges) {
		this.totalDestCharges = totalDestCharges;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public void setValueCarriage(double valueCarriage) {
		this.valueCarriage = valueCarriage;
	}

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "prefix","serial");
	}
	
	public AwbFlt findFlight(Flight f) {
		List<AwbFlt> flts = DBClient.getList(BeanUtil.concat("SELECT a FROM AwbFlt a WHERE a.seq=",seq));
		if (flts!=null) {
			for (AwbFlt l:flts) {
				if (!l.carrier.equals(f.carrier)) continue;
				if (!l.flightNumber.equals(f.flightNumber)) continue;
				if (!l.flightDate.equals(f.flightDate)) continue;
				if (!l.origin.equals(f.origin)) continue;
				return l;
			}
		}
		return null;
	}

	@Override
	public void save() {
		if (originCurrency==null || originCurrency.isEmpty()) {
			originCurrency = Airport.extractCurrency(origin);
		}
		if (destCurrency==null || destCurrency.isEmpty()) {
			destCurrency = Airport.extractCurrency(destination);
		}
		if (!isEmptyKey()) {
			double prepaid = 0;
			double collect = 0;
			List<AwbCharges> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM AwbCharges a WHERE a.awbSeq=",seq));
			for (AwbCharges c:lst) {
				if (c.prepaid) {
					prepaid += c.amount;
				}
				else {
					collect += c.amount;
				}
			}
			totalOriginCharges = prepaid;
			totalDestCharges = collect;
			
//			calculate valueCustoms, valueCarraige, valueInsurance if 0
		}
		if (businessCode==null || businessCode.isEmpty()) {
			if ("AAA".equals(UserInfo.getUserName())) {
				CompanyConfig user = (CompanyConfig) DBClient.getFirstRecord("SELECT a FROM CompanyConfig");
				businessCode = user.businessCode;
			}
			else {
				CompanyConfigUser user = (CompanyConfigUser) DBClient.getFirstRecord("SELECT a FROM CompanyConfigUser a WHERE a.userId='",UserInfo.getUserName(),"'");
				businessCode = user.businessCode;
			}
		}
		super.save();
	}
	
	
}
