/*
 * AwbFlt.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.Awb;
import bean.Flight;
import bean.UldNumber;
import bean.reference.SpecialHandling;
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

import constants.Constants;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbFlt")
@UITemplate(template = TemplateTabPage.class, gridCount = 10, 
	columnSearch = {"carrier", "flightNumber", "flightDate", "weight", "status", "importStatus", "shc1", "shc2", "lastUpdated"},
	criteriaSearch = {"carrier", "flightNumber", "flightDate", "status", "importStatus", "shc1", "shc2"}
)
@Displays({
    @Display(name = "flightSeq", label="Flight", type="PopSearch", linktoBean=Flight.class, width=-1, gridFieldWidth=3),
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class, width=60),
    @Display(name = "importStatus", type="PopSearch", linktoBean=TraceStatus.class, width=60),
    @Display(name = "shc1", type="PopSearch", linktoBean=SpecialHandling.class, width=60),
    @Display(name = "shc2", type="PopSearch", linktoBean=SpecialHandling.class, width=60, gridFieldWidth=3),
    @Display(name = "mh"),
    @Display(name = "mp"),
    @Display(name = "lp"),
    @Display(name = "lc")
})
public class AwbFlt extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "weight")
    public double weight;
    @Column(name = "volume")
    public double volume;
    @Column(name = "nextFlightSeq")
    public int nextFlightSeq;
    @Column(name = "status", length = 3)
    public String status;
    @Column(name = "importStatus", length = 3)
    public String importStatus;
    @Column(name = "pieces")
    public int pieces;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "prevFlightSeq")
    public int prevFlightSeq;
    @Column(name = "flightSeq")
    public int flightSeq;
    @Column(name = "shc1", length = 3)
    public String shc1;
    @Column(name = "shc2", length = 3)
    public String shc2;
    @Column(name = "mh")
    public int mh;
    @Column(name = "mp")
    public int mp;
    @Column(name = "lp")
    public int lp;
    @Column(name = "lc")
    public int lc;
    @Column(name = "line")
    public int line;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    //additional fields
    @Column(name = "flightDate")
    @Temporal(value = TemporalType.DATE)
    public Date flightDate;
    @Column(name = "arrivalDate")
    @Temporal(value = TemporalType.DATE)
    public Date arrivalDate;
    @Column(name = "origin", length = 3)
    public String origin;
    @Column(name = "destination", length = 3)
    public String destination;
    @Column(name = "carrier", length = 2)
    public String carrier;
    @Column(name = "flightNumber", length = 5)
    public String flightNumber;
    @Column(name = "spaceAllocationCode", length = 5)
    public String spaceAllocationCode;
    @Column(name = "allotmentIdentification", length = 5)
    public String allotmentIdentification;
    @Column(name = "kgLb", length = 10)
    public String kgLb;
    @Column(name = "departureTime", length = 4)
    public String departureTime;
    @Column(name = "arrivalTime", length = 4)
    public String arrivalTime;
    @Column(name = "discrepancyCode", length = 10)
    public String discrepancyCode;
    @Column(name = "aircraftRegistration", length = 20)
    public String aircraftRegistration;
    @Column(name = "uldNumber", length = 11)
    public String uldNumber;
    @Column(name = "lastUpdated", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date lastUpdated;

    public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getAircraftRegistration() {
        return aircraftRegistration;
    }

    public void setAircraftRegistration(String aircraftRegistration) {
        this.aircraftRegistration = aircraftRegistration;
    }

    public String getAllotmentIdentification() {
        return allotmentIdentification;
    }

    public void setAllotmentIdentification(String allotmentIdentification) {
        this.allotmentIdentification = allotmentIdentification;
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

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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

    public String getDiscrepancyCode() {
        return discrepancyCode;
    }

    public void setDiscrepancyCode(String discrepancyCode) {
        this.discrepancyCode = discrepancyCode;
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

    public int getFlightSeq() {
        return flightSeq;
    }

    public void setFlightSeq(int flightSeq) {
        this.flightSeq = flightSeq;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
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

    public int getNextFlightSeq() {
        return nextFlightSeq;
    }

    public void setNextFlightSeq(int nextFlightSeq) {
        this.nextFlightSeq = nextFlightSeq;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getPrevFlightSeq() {
        return prevFlightSeq;
    }

    public void setPrevFlightSeq(int prevFlightSeq) {
        this.prevFlightSeq = prevFlightSeq;
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

    public String getSpaceAllocationCode() {
        return spaceAllocationCode;
    }

    public void setSpaceAllocationCode(String spaceAllocationCode) {
        this.spaceAllocationCode = spaceAllocationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUldNumber() {
        return uldNumber;
    }

    public void setUldNumber(String uldNumber) {
        this.uldNumber = uldNumber;
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

    public Awb extractAwb() {
        return (Awb) selectFirstCache("SELECT a FROM Awb a WHERE a.seq="+awbSeq);
    }
    
    public Flight extractNextFlight() {
        AwbFlt f = extractNextAwbFlight();
        return f.extractFlightObj();
    }
    
    public AwbFlt extractNextAwbFlight() {
        AwbFlt f = null;
        List lst = selectListCache("SELECT a FROM AwbFlt a WHERE a.awbSeq="+awbSeq);
        for (int i=0; lst!=null && i<lst.size(); i++) {
            AwbFlt flt = (AwbFlt) lst.get(i);
            if (flt.getSeq().equals(seq)) {
                if (i+1<lst.size()) {
                    f = (AwbFlt) lst.get(i+1);
                    break;
                }
            }
        }
        if (f==null) {
            //we need to create new flt for this
            f = (AwbFlt) clone();
            f.setSeq(0);
            f.setFlightSeq(0);
            f.setOrigin(destination);
            f.setDestination(null);
        }
        f.setStatus("TFR");
        return f;
    }

    private Flight extractFlightObj() {
        return (Flight) selectFirstCache("SELECT a FROM Flight a WHERE a.seq="+flightSeq);
    }
    
    public void removeFromULD() {
        Awb awb = extractAwb();
        uldNumber = null;
        if (origin.equals(awb.origin)) {
            status = "RCS";
        }
        else {
            status = "TFR";
        }
        save();
    }
    
    public void removeFromFlight() {
        if (uldNumber!=null && !uldNumber.isEmpty()) {
            UldNumber uldN = (UldNumber) selectFirstCache("SELECT a FROM UldNumber a WHERE a.uld='"+uldNumber+"'");
            uldN.removeFromFlight();
        }
        else {
            Awb awb = extractAwb();
            if (origin.equals(awb.origin)) {
                status = "RCS";
            }
            else {
                status = "TFR";
            }
            flightSeq = 0;
            save();
        }
    }
    
    public boolean isTerminating() {
        Awb awb = extractAwb();
        return destination.equals(awb.destination);
    }
    
    public void arriveFromFlight() {
        if (isTerminating()) {
            flightSeq = 0;
            if (uldNumber!=null && !uldNumber.isEmpty()) {
                status = "ARR";
            }
            else {
                status = "RCF";
            }
            save();
        }
        else {
            if (nextFlightSeq==0) {
                AwbFlt f = extractNextAwbFlight();
                f.flightSeq = 0;
                if (uldNumber!=null && !uldNumber.isEmpty()) {
                    f.status = "ARR";
                }
                else {
                    f.status = "TFR";
                }
                f.prevFlightSeq = seq;
                f.nextFlightSeq = 0;
                f.importStatus = null;
                f.save();

                nextFlightSeq = f.seq;
                if (uldNumber!=null && !uldNumber.isEmpty()) {
                    importStatus = "ARR";
                }
                else {
                    importStatus = "TFR";
                }
                save();
            }
        }
    }
    
    public void assignToFlight(Flight flight) {
        if (!isAllowedToFly()) return;
        if (isDeparted()) {
            AwbFlt f = extractNextAwbFlight();
            f.setStatus("MAN");
            f.setPrevFlightSeq(seq);
            f.setNextFlightSeq(0);
            f.setImportStatus(null);

            f.setFlightSeq(flight.getSeq());
            f.setCarrier(flight.getCarrier());
            f.setFlightNumber(flight.getFlightNumber());
            f.setFlightDate(flight.getFlightDate());
            f.setOrigin(flight.getOrigin());
            f.setDestination(flight.getDestination());
            
            f.save();

            setNextFlightSeq(f.getSeq());
            setImportStatus("MAN");
            save();
        }
        else {
            setStatus("MAN");
            setFlightSeq(flight.getSeq());
            setCarrier(flight.getCarrier());
            setFlightNumber(flight.getFlightNumber());
            setFlightDate(flight.getFlightDate());
            setOrigin(flight.getOrigin());
            setDestination(flight.getDestination());
            save();
        }
    }

    public void assignToULD(UldNumber uld) {
        setUldNumber(uld.getUld());
        if (isInOrigin()) {
            setStatus("RCS");
        }
        else {
            setStatus("TFR");
        }
        save();
        uld.setIsActive(true);
        if (uld.getCurrentAirport()==null || uld.getStatus()==null) {
            uld.setCurrentAirport(getOrigin());
            uld.setDestination(getDestination());
            uld.setStatus("RCS");
            uld.save();
        }
    }
    
    public boolean isAllowedToFly() {
        if (status==null) return true;
        if (status.equals("MSC")) return false;
        if (status.equals("RCF")) return false;
        if (status.equals("NFD")) return false;
        if (status.equals("DLV")) return false;
        if (status.equals("BDW")) return false;
        if (status.equals("RTD")) return false;
        return true;
    }

    public boolean isDeparted() {
        Flight f = extractFlightObj();
        return ("DEP".equals(f.status));
    }

    public boolean isInOrigin() {
        Awb awb = extractAwb();
        return (origin.equals(awb.origin));
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
	public void save() {
		extractFlight();
		lastUpdated = Constants.useDate;
		status = TraceStatus.extractStatus(status);
		super.save();
	}

	public Flight extractFlight() {
    	Flight f = (Flight) DBClient.getFirstRecord("SELECT a FROM Flight a WHERE a.carrier='"+carrier+"' AND a.flightNumber='"+flightNumber+"'");
    	if (f==null || f.isEmptyKey()) {
    		f = new Flight();
    		f.carrier = carrier;
    		f.flightNumber = flightNumber;
    		f.flightDate = flightDate;
    		f.origin = origin;
    		f.destination = destination;
//    		this is just transient
    		f.legNumber = -1;
    		f.save();
    	}
    	flightSeq = f.seq;
    	return f;
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "carrier","flightNumber","flightDate","origin","awbSeq");
	}
}
