/*
 * Participant.java
 *
 * Created on Oct 14, 2007, 8:43:21 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import bean.reference.Airport;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@DiscriminatorValue(value = "PARTICIPANT")
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 8, columnSearch = {"code","name","participantType","pimaAddress","airportCode"})
@Displays({
    @Display(name = "code", width=60),
    @Display(name = "name"),
    @Display(name = "airportCode", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "participantType", width=40, type="Combo", modelCombo={"A","S","C","T","N","B"}),
    
    @Display(name = "pimaAddress", width=100),
    @Display(name = "ediLink", width=100),
    @Display(name = "iataCode", width=100),
    @Display(name = "cassCode", width=100),

    //additional fields
    @Display(name = "dateSigned", addInfoOnly=true),
    @Display(name = "signature", addInfoOnly=true),
    @Display(name = "officeFunctionDesignator", addInfoOnly=true),
    @Display(name = "companyDesignator", addInfoOnly=true),
    @Display(name = "fileReference", addInfoOnly=true),
    @Display(name = "participantIdentifier", addInfoOnly=true),

    @Display(name = "address2", addInfoOnly=true, gridFieldWidth=5, width=-1),
    @Display(name = "address3", addInfoOnly=true, gridFieldWidth=5, width=-1)

})
@DisplayGroups({
    @DisplayGroup(title="EDI Addresses and Codes", gridCount=8, fields={"pimaAddress","ediLink","iataCode","cassCode"})
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=ParticipantAllotment.class, sql="SELECT a FROM ParticipantAllotment a WHERE a.participantCode='${code}'", title="Allotments")
    },
    info={
        @ParentAddInfo(gridCount=6, title="Additional Info", 
            displayFields={
                "dateSigned",
                "signature",
                "officeFunctionDesignator",
                "companyDesignator",
                "fileReference",

                "address2",
                "address3",

                "participantIdentifier"
            })
    }
)
public class Participant extends Customer implements Serializable {
    @Column(name = "code", nullable = false, length = 20)
    public String code;
    @Column(name = "name", nullable = false, length = 100)
    public String name;
    @Column(name = "participantType")
    public String participantType;
    @Column(name = "pimaAddress", length = 20)
    public String pimaAddress;
    @Column(name = "ediLink", length = 30)
    public String ediLink;
    @Column(name = "consignee")
    public boolean consignee;
    @Column(name = "agent")
    public boolean agent;
    @Column(name = "shipper")
    public boolean shipper;

    //additional fields
    @Column(name = "iataCode", length = 20)
    public String iataCode;
    @Column(name = "cassCode", length = 20)
    public String cassCode;
    @Column(name = "dateSigned")
    @Temporal(value = TemporalType.DATE)
    public Date dateSigned;
    @Column(name = "signature", length = 50)
    public String signature;
    @Column(name = "airportCode", length = 3)
    public String airportCode;
    @Column(name = "officeFunctionDesignator", length = 30)
    public String officeFunctionDesignator;
    @Column(name = "companyDesignator", length = 30)
    public String companyDesignator;
    @Column(name = "fileReference", length = 30)
    public String fileReference;

    @Column(name = "address2")
    public String address2;
    @Column(name = "address3")
    public String address3;

    @Column(name = "participantIdentifier")
    public String participantIdentifier;

	@Override
	public void setupIndex() {
		runUniqueIndex(100, "code");
	}
	
    public Participant() {
        this.firstName = "XXX";
        this.lastName = "XXX";
    }
    
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCassCode() {
        return cassCode;
    }

    public void setCassCode(String cassCode) {
        this.cassCode = cassCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyDesignator() {
        return companyDesignator;
    }

    public void setCompanyDesignator(String companyDesignator) {
        this.companyDesignator = companyDesignator;
    }

    public boolean isConsignee() {
        return consignee;
    }

    public void setConsignee(boolean consignee) {
        this.consignee = consignee;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getEdiLink() {
        return ediLink;
    }

    public void setEdiLink(String ediLink) {
        this.ediLink = ediLink;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeFunctionDesignator() {
        return officeFunctionDesignator;
    }

    public void setOfficeFunctionDesignator(String officeFunctionDesignator) {
        this.officeFunctionDesignator = officeFunctionDesignator;
    }

    public String getParticipantIdentifier() {
        return participantIdentifier;
    }

    public void setParticipantIdentifier(String participantIdentifier) {
        this.participantIdentifier = participantIdentifier;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public String getPimaAddress() {
        return pimaAddress;
    }

    public void setPimaAddress(String pimaAddress) {
        this.pimaAddress = pimaAddress;
    }

    public boolean isShipper() {
        return shipper;
    }

    public void setShipper(boolean shipper) {
        this.shipper = shipper;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","name","pimaAddress");
    }
}
