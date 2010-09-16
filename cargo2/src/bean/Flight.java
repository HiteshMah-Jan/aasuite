/*
 * Flight.java
 *
 * Created on Oct 6, 2007, 6:38:21 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import bean.awb.AwbFlt;
import bean.reference.AircraftType;
import bean.reference.Airport;
import bean.reference.TraceStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Flight")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"carrier","flightNumber","flightDate","departureTime","aircraftType","origin","destination","airportList","status"}, showChart=true)
@Displays({
    @Display(name = "carrier", mergeFields={"flightNumber","flightDate"}, linktoBean=Carrier.class, type="PopSearch", width=60),
    @Display(name = "flightNumber", hideLabel=true, width=30),
    @Display(name = "flightDate", hideLabel=true),
    @Display(name = "origin", width=50, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "destination", width=50, type="PopSearch", linktoBean=Airport.class, mergeFields={"airportList"}),
    @Display(name = "airportList", type="Label"),

    @Display(name = "aircraftType", width=150, type="PopSearch", linktoBean=AircraftType.class),
    @Display(name = "departureDate", label="Departure", mergeFields={"departureTime"}),
    @Display(name = "departureTime", hideLabel=true, type="Time"),
    @Display(name = "arrivalDate", label="Arrival", mergeFields={"arrivalTime"}),
    @Display(name = "arrivalTime", hideLabel=true, type="Time"),

//    @Display(name = "flightScheduleSeq"),
//    @Display(name = "legNumber"),
    @Display(name = "status", width=30, type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "grossAmount"),
    @Display(name = "netRevenue"),
    @Display(name = "remarks", gridFieldWidth=3, width=-1),

//    @Display(name = "totalPieces"),
    @Display(name = "availWeight", label="Weight", mergeFields={"availVolume"}, width=40),
    @Display(name = "availVolume", label="Volume", width=40),
    @Display(name = "availMh", width=40, label="Mh", mergeFields={"availMp","availLp","availLc"}),
    @Display(name = "availMp", width=40, label="Mp"),
    @Display(name = "availLp", width=40, label="Lp"),
    @Display(name = "availLc", width=40, label="Lc")
})
@DisplayGroups({
    @DisplayGroup(title="Available Spaces", gridCount=6, fields={"availWeight","availVolume","availMh","availMp","availLp","availLc"}),
    @DisplayGroup(title="Departure/Arrival", gridCount=6, fields={"departureDate","departureTime","arrivalDate","arrivalTime","status"})
})
@Reports({
    @template.Report(reportFile="FlightManifest", reportTitle="Manifest", reportSql = "${seq}"),
    @template.Report(reportFile="FlightManifestAwb", reportTitle="AWB", reportSql = "${seq}"),
    @template.Report(reportFile="FlightManifestUld", reportTitle="ULD", reportSql = "${seq}"),
    @template.Report(reportFile="FlightNotoc", reportTitle="Notoc", reportSql = "${seq}")
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListPopup.class, entity=FlightManifestAwb.class, sql="SELECT a FROM FlightManifestAwb a WHERE a.flightSeq='${seq}'", title="Awb", fieldMapping={"seq","flightSeq"}),
        @ChildRecord(template=ChildTemplateListPopup.class, entity=FlightManifestUld.class, sql="SELECT a FROM FlightManifestUld a WHERE a.flightSeq='${seq}'", title="ULD", fieldMapping={"seq","flightSeq"}),
        @ChildRecord(template=ChildTemplateListPopup.class, entity=FlightManifestUld.class, sql="SELECT a FROM FlightNotoc a WHERE a.flightSeq='${seq}'", title="Notoc", fieldMapping={"seq","flightSeq"})
    }
)
public class Flight extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "flightScheduleSeq")
    public int flightScheduleSeq;
    @Column(name = "departureTime", length = 4)
    public String departureTime;
    @Column(name = "flightNumber", length = 5)
    public String flightNumber;
    @Column(name = "arrivalTime", length = 4)
    public String arrivalTime;
    @Column(name = "flightDate")
    @Temporal(value = TemporalType.DATE)
    public Date flightDate;
    @Column(name = "legNumber")
    public int legNumber;
    @Column(name = "destination", length = 3)
    public String destination;
    @Column(name = "grossAmount")
    public Double grossAmount;
    @Column(name = "carrier", length = 3)
    public String carrier;
    @Column(name = "netRevenue")
    public Double netRevenue;
    @Column(name = "status", length = 3)
    public String status;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "origin", length = 3)
    public String origin;
    @Column(name = "departureDate")
    @Temporal(value = TemporalType.DATE)
    public Date departureDate;
    @Column(name = "aircraftType", length = 20)
    public String aircraftType;
    @Column(name = "arrivalDate")
    @Temporal(value = TemporalType.DATE)
    public Date arrivalDate;

    @Column(name = "totalPieces")
    public int totalPieces;
    @Column(name = "availWeight")
    public Double availWeight;
    @Column(name = "availVolume")
    public Double availVolume;
    @Column(name = "availMh")
    public int availMh;
    @Column(name = "availMp")
    public int availMp;
    @Column(name = "availLp")
    public int availLp;
    @Column(name = "availLc")
    public int availLc;
    @Column(name = "active")
    public boolean active;
    @Column(name = "airportList")
    public String airportList;
    @Column(name = "connectionRestriction")
    public String connectionRestriction;
    @Column(name = "serviceCode")
    public String serviceCode;
    @Column(name = "numberOfStop")
    public int numberOfStop;
    @Column(name = "ssi")
    public String ssi;

    public String getConnectionRestriction() {
        return connectionRestriction;
    }

    public void setConnectionRestriction(String connectionRestriction) {
        this.connectionRestriction = connectionRestriction;
    }

    public int getNumberOfStop() {
        return numberOfStop;
    }

    public void setNumberOfStop(int numberOfStop) {
        this.numberOfStop = numberOfStop;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSsi() {
        return ssi;
    }

    public void setSsi(String ssi) {
        this.ssi = ssi;
    }

    public String getAirportList() {
        return airportList;
    }

    public void setAirportList(String airportList) {
        this.airportList = airportList;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSameBuilt(Flight flt) {
        if (!flt.carrier.equals(carrier)) {
            return false;
        }
        if (!flt.flightNumber.equals(flightNumber)) {
            return false;
        }
        if (!flt.flightDate.equals(flightDate)) {
            return false;
        }
        if (!flt.origin.equals(origin)) {
            return false;
        }
        if (!flt.departureDate.equals(departureDate)) {
            return false;
        }
        return true;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailLc() {
        return availLc;
    }

    public void setAvailLc(int availLc) {
        this.availLc = availLc;
    }

    public int getAvailLp() {
        return availLp;
    }

    public void setAvailLp(int availLp) {
        this.availLp = availLp;
    }

    public int getAvailMh() {
        return availMh;
    }

    public void setAvailMh(int availMh) {
        this.availMh = availMh;
    }

    public int getAvailMp() {
        return availMp;
    }

    public void setAvailMp(int availMp) {
        this.availMp = availMp;
    }

    public Double getAvailVolume() {
        return availVolume;
    }

    public void setAvailVolume(Double availVolume) {
        this.availVolume = availVolume;
    }

    public Double getAvailWeight() {
        return availWeight;
    }

    public void setAvailWeight(Double availWeight) {
        this.availWeight = availWeight;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getFlightScheduleSeq() {
        return flightScheduleSeq;
    }

    public void setFlightScheduleSeq(int flightScheduleSeq) {
        this.flightScheduleSeq = flightScheduleSeq;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public int getLegNumber() {
        return legNumber;
    }

    public void setLegNumber(int legNumber) {
        this.legNumber = legNumber;
    }

    public Double getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(Double netRevenue) {
        this.netRevenue = netRevenue;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }
    
    public void depart() {
        status = "DEP";
        save();
        DBClient.runSQL("UPDATE AwbFlt a SET a.status='DEP' WHERE a.flightSeq=",seq," AND a.status='MAN'");
        DBClient.runSQL("UPDATE UldNumber a SET a.status='DEP' WHERE a.flightSeq=",seq," AND a.status='MAN'");
    }
    
    public void updateFlightSpace() {
        AircraftType aircraft = (AircraftType) DBClient.getFirstRecord("SELECT a FROM AircraftType a WHERE a.code='",getAircraftType(),"'");
        List lst = DBClient.getList(BeanUtil.concat("SELECT a FROM AwbFlt a WHERE a.flightSeq=",seq));
        int totalPiecest = 0;
        double totalWeight = 0;
        double totalVolume = 0;
        int totalMh = 0;
        int totalMp = 0;
        int totalLp = 0;
        int totalLc = 0;
        for (Object obj : lst) {
            AwbFlt flt = (AwbFlt) obj;
            totalPiecest += flt.getPieces();
            totalWeight += flt.getWeight();
            totalVolume += flt.getVolume();
            totalMh += flt.getMh();
            totalMp += flt.getMp();
            totalLp += flt.getLp();
            totalLc += flt.getLc();
        }
        setTotalPieces(totalPiecest);
        setAvailWeight(aircraft.getDefaultWeight() - totalWeight);
        setAvailVolume(aircraft.getDefaultVolume() - totalVolume);
        setAvailMh(aircraft.getMh() - totalMh);
        setAvailMp(aircraft.getMp() - totalMp);
        setAvailLp(aircraft.getLp() - totalLp);
        setAvailLc(aircraft.getLc() - totalLc);
        save();
    }
    
    public boolean goodCapacity(double... capacity) {
        if (capacity==null) return true;
        if (capacity.length==1) {
            return getAvailWeight() >= capacity[0];
        }
        else if (capacity.length==2) {
            return getAvailWeight() >= capacity[0] && getAvailVolume() >= capacity[1];
        }
        else if (capacity.length==3) {
            return getAvailWeight() >= capacity[0] && getAvailVolume() >= capacity[1] && getAvailMh() >= capacity[2];
        }
        else if (capacity.length==4) {
            return getAvailWeight() >= capacity[0] && getAvailVolume() >= capacity[1] && getAvailMh() >= capacity[2] && getAvailMp() >= capacity[3];
        }
        else if (capacity.length==5) {
            return getAvailWeight() >= capacity[0] && getAvailVolume() >= capacity[1] && getAvailMh() >= capacity[2] && getAvailMp() >= capacity[3] && getAvailLp() >= capacity[4];
        }
        return getAvailWeight() >= capacity[0] && getAvailVolume() >= capacity[1] && getAvailMh() >= capacity[2] && getAvailMp() >= capacity[3] && getAvailLp() >= capacity[4] && getAvailLc() >= capacity[5];
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "carrier","flightNumber","flightDate");
	}

	@Override
	public String toString() {
		if (isEmptyKey()) {
			return "";
		}
		return BeanUtil.concat(carrier,flightNumber,"-",DateUtil.formatDate(flightDate,"ddMMM").toUpperCase());
	}
}
