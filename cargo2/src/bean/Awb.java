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

import service.FlightService;
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
import util.DBClient;
import bean.awb.AwbCharges;
import bean.awb.AwbDangerousGoods;
import bean.awb.AwbFlt;
import bean.awb.AwbShc;
import bean.reference.Airport;
import bean.reference.Country;
import bean.reference.PaymentType;
import bean.reference.ServiceLevel;
import bean.reference.SpecialHandling;
import bean.reference.TraceStatus;

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
    @Display(name = "declaredValueCarriage"),
    @Display(name = "declaredValueCustoms"),
    @Display(name = "amountInsurance"),
    @Display(name = "currencyCode"),
    @Display(name = "originPrepaid"),
    @Display(name = "destinationPrepaid"),
    @Display(name = "hwbSerial"),
    @Display(name = "freeDescriptionOfGoods"),
    @Display(name = "slac"),
    @Display(name = "commodityCode"),
    @Display(name = "place"),
    @Display(name = "creditDetails"),
    @Display(name = "originChargeCode"),
    @Display(name = "destinationChargeCode")
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
    @Column(name = "declaredValueCarriage")
    public double declaredValueCarriage;
    @Column(name = "declaredValueCustoms")
    public double declaredValueCustoms;
    @Column(name = "amountInsurance")
    public double amountInsurance;
    @Column(name = "currencyCode", length = 5)
    public String currencyCode;
    @Column(name = "originPrepaid")
    public boolean originPrepaid;
    @Column(name = "destinationPrepaid")
    public boolean destinationPrepaid;
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

    @Column(name = "originChargeCode", length = 150)
    public String originChargeCode;
    @Column(name = "destinationChargeCode", length = 150)
    public String destinationChargeCode;

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

    public double getAmountInsurance() {
        return amountInsurance;
    }

    public void setAmountInsurance(double amountInsurance) {
        this.amountInsurance = amountInsurance;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getDeclaredValueCarriage() {
        return declaredValueCarriage;
    }

    public void setDeclaredValueCarriage(double declaredValueCarriage) {
        this.declaredValueCarriage = declaredValueCarriage;
    }

    public double getDeclaredValueCustoms() {
        return declaredValueCustoms;
    }

    public void setDeclaredValueCustoms(double declaredValueCustoms) {
        this.declaredValueCustoms = declaredValueCustoms;
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

    public String getDestinationChargeCode() {
        return destinationChargeCode;
    }

    public void setDestinationChargeCode(String destinationChargeCode) {
        this.destinationChargeCode = destinationChargeCode;
    }

    public boolean isDestinationPrepaid() {
        return destinationPrepaid;
    }

    public void setDestinationPrepaid(boolean destinationPrepaid) {
        this.destinationPrepaid = destinationPrepaid;
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

    public String getOriginChargeCode() {
        return originChargeCode;
    }

    public void setOriginChargeCode(String originChargeCode) {
        this.originChargeCode = originChargeCode;
    }

    public boolean isOriginPrepaid() {
        return originPrepaid;
    }

    public void setOriginPrepaid(boolean originPrepaid) {
        this.originPrepaid = originPrepaid;
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
    
    public void createCharge(String charge) {
        AwbCharges ch = new AwbCharges();
        ch.chargeCode = charge;
        ch.awbSeq = seq;
        ch.save();
    }
    public void createDG(String un, int pieces) {
        AwbDangerousGoods dg = new AwbDangerousGoods();
        dg.awbSeq = seq;
        dg.unNumber = un;
        dg.pieces = pieces;
        dg.save();
    }
    public void createBooking() {
        if (!isEmptyKey()) {
            autoCreateSHC();
            autoCreateFlights();
            autoCreateCharges();
        }
    }
    public Country extractOriginCountry() {
        return (Country) selectFirstCache("SELECT a FROM Country a, Airport b WHERE a.code=b.country AND b.code='"+origin+"'");
    }    
    public Country extractDestCountry() {
        return (Country) selectFirstCache("SELECT a FROM Country a, Airport b WHERE a.code=b.country AND b.code='"+destination+"'");
    }    
    public void autoCreateFlights() {
        List col = selectListCache("SELECT a FROM AwbFltBooking a WHERE a.awbSeq="+seq);
        if (col != null && col.size() > 0) {
            return;
        }
        //get all direct flights first
        String origint = getOrigin();
        String destinationt = getDestination();
        double weightt = getWeight();
        double volumet = getVolume();
        int mpt = getMp();
        int mht = getMh();
        int lpt = getLp();
        int lct = getLc();
        FlightService service = new FlightService();
        List lst = service.getBestRoute(origint, destinationt, departureDate, weightt, volumet, mht, mpt, lpt, lct);
        putRouting(lst);
    }
    public void putRouting(List lst) {
        int line = 0;
        for (Object obj : lst) {
            Flight flight = (Flight) obj;
            AwbFlt flt = new AwbFlt();
            flt.setLine(++line);
            flt.setArrivalDate(flight.getArrivalDate());
            flt.setAwbSeq(getSeq());
            flt.setFlightSeq(flight.getSeq());
            flt.setPieces(getPieces());
            flt.setWeight(getWeight());
            flt.setVolume(getVolume());
            flt.setMh(getMh());
            flt.setMp(getMp());
            flt.setLc(getLc());
            flt.setLp(getLp());
            flt.setOrigin(flight.getOrigin());
            flt.setDestination(flight.getDestination());
            flt.setCarrier(flight.getCarrier());
            flt.setFlightNumber(flight.getFlightNumber());
            flt.setFlightDate(flight.getDepartureDate());
            flt.setStatus("KK");
            flt.save();
//
//            AwbRouting route = new AwbRouting(); 
//            route.setAwbSeq(getSeq());
//            route.setLine(line);
//            route.setOrigin(flight.getOrigin());
//            route.setDestination(flight.getDestination());
//            route.setStatus("KK");
//            route.save();
        }
    }
    
    public void autoCreateCharges() {
        String serviceLevelt = getServiceLevel();
        if (util.DataUtil.isEmpty(serviceLevelt)) return;
        //check if there are charges already
        AwbCharges charge = (AwbCharges) selectFirstCache("SELECT a FROM AwbCharges a WHERE a.awbSeq="+seq);
        if (charge!=null && !charge.isEmptyKey()) return;
        ServiceLevel serv = (ServiceLevel) selectFirstCache("SELECT a FROM ServiceLevel a WHERE a.code='"+serviceLevelt+"'");
        if (serv.getChargeCode1()!=null) {
            createCharge(serv.chargeCode1);
        }
        if (serv.getChargeCode2()!=null) {
            createCharge(serv.chargeCode2);
        }
        if (serv.getChargeCode3()!=null) {
            createCharge(serv.chargeCode3);
        }
        if (serv.getChargeCode4()!=null) {
            createCharge(serv.chargeCode4);
        }
        if (serv.getChargeCode5()!=null) {
            createCharge(serv.chargeCode5);
        }
    }
    public void autoCreateDG(AwbShc awbShc) {
        SpecialHandling shc = (SpecialHandling) selectFirstCache("SELECT a FROM SpecialHandling a WHERE a.code='"+awbShc.getShcCode()+"'");
        if (shc.getDgNumber1()!=null) {
            createDG(shc.dgNumber1, pieces);
        }
        if (shc.getDgNumber2()!=null) {
            createDG(shc.dgNumber2, pieces);
        }
        if (shc.getDgNumber3()!=null) {
            createDG(shc.dgNumber3, pieces);
        }
        if (shc.getDgNumber4()!=null) {
            createDG(shc.dgNumber4, pieces);
        }
        if (shc.getDgNumber5()!=null) {
            createDG(shc.dgNumber5, pieces);
        }
    }
    public void autoCreateSHC() {
        List col = selectListCache("SELECT a FROM AwbShc a WHERE a.awbSeq="+seq);
        if (col != null && col.size() > 0) {
            return;
        }
        //check the service
        String serviceLevelt = getServiceLevel();
        ServiceLevel serv = (ServiceLevel) selectFirstCache("SELECT a FROM ServiceLevel a WHERE a.code='"+serviceLevelt+"'");
        if (serv != null) {
            if (!util.DataUtil.isEmpty(serv.getShc1())) {
                AwbShc shc1 = new AwbShc();
                shc1.setAwbSeq(getSeq());
                shc1.setShcCode(serv.getShc1());
                shc1.save();
                autoCreateDG(shc1);
            }
            if (!util.DataUtil.isEmpty(serv.getShc2())) {
                AwbShc shc2 = new AwbShc();
                shc2.setAwbSeq(getSeq());
                shc2.setShcCode(serv.getShc2());
                shc2.save();
                autoCreateDG(shc2);
            }
            if (!util.DataUtil.isEmpty(serv.getShc3())) {
                AwbShc shc3 = new AwbShc();
                shc3.setAwbSeq(getSeq());
                shc3.setShcCode(serv.getShc3());
                shc3.save();
                autoCreateDG(shc3);
            }
            if (!util.DataUtil.isEmpty(serv.getShc4())) {
                AwbShc shc4 = new AwbShc();
                shc4.setAwbSeq(getSeq());
                shc4.setShcCode(serv.getShc4());
                shc4.save();
                autoCreateDG(shc4);
            }
            if (!util.DataUtil.isEmpty(serv.getShc5())) {
                AwbShc shc5 = new AwbShc();
                shc5.setAwbSeq(getSeq());
                shc5.setShcCode(serv.getShc5());
                shc5.save();
                autoCreateDG(shc5);
            }
        }
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "prefix","serial");
	}
	
	public AwbFlt findFlight(Flight f) {
		List<AwbFlt> flts = DBClient.getList("SELECT a FROM AwbFlt a WHERE a.seq="+seq);
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
}
