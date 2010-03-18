/*
 * UldNumber.java
 *
 * Created on Oct 14, 2007, 9:25:34 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import bean.awb.AwbFlt;
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
import template.screen.TemplateSinglePage;
import template.*;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "UldNumber")
@UITemplate(template = TemplateSinglePage.class, gridCount = 8, 
    columnSearch = {"uld","currentAirport","destination","finalDestination","status"}, showChart=true)
@Displays({
    @Display(name = "uld", width=90),
    @Display(name = "currentAirport", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "destination", label="Dest", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "finalDestination", label="Final Dest", width=60, type="PopSearch", linktoBean=Airport.class),

    @Display(name = "availWeight", label="Weight", width=60, labelTop=true, leftLabel="Available"),
    @Display(name = "availVolume", width=60, label="Volume", labelTop=true),
    @Display(name = "maxWeight", width=60, label="Maximum"),
    @Display(name = "maxVolume", width=60, hideLabel=true),
    @Display(name = "scaleWeight", width=60, gridFieldWidth=3, label="Scale"),
    @Display(name = "tareWeight", width=60, gridFieldWidth=3, label="Tare"),

    @Display(name = "flightSeq", label="Flight", width=200, type="PopSearch", linktoBean=Flight.class),
    @Display(name = "deck", width=60),
    @Display(name = "status", width=60, type="PopSearch", linktoBean=TraceStatus.class)

})
@DisplayGroups({
    @DisplayGroup(title="Dimension", gridCount=4, 
    		fields={"availWeight","availVolume","maxWeight","maxVolume","scaleWeight","tareWeight"}),
    @DisplayGroup(title="Dimension", gridCount=6, 
    	    		fields={"flightSeq","deck","status"})
})
public class UldNumber extends AbstractIBean implements Serializable {
	@Override
	public void save() {
		if (flightSeq>0) {
			Flight f = (Flight) DBClient.getFirstRecord("SELECT a FROM Flight a WHERE a.seq="+flightSeq);
			carrier = f.carrier;
			flightNumber = f.flightNumber;
			flightDate = f.flightDate;
		}
		super.save();
	}

	public static void main(String[] args) {
		view(UldNumber.class);
	}
    @Id
    @Column(name = "uld", nullable = false, length = 11)
    public String uld;
    @Column(name = "availWeight")
    public double availWeight;
    @Column(name = "availVolume")
    public double availVolume;
    @Column(name = "maxWeight", nullable = false)
    public double maxWeight;
    @Column(name = "tareWeight", nullable = false)
    public double tareWeight;
    @Column(name = "maxVolume", nullable = false)
    public double maxVolume;
    @Column(name = "deck", length = 10)
    public String deck;
    @Column(name = "scaleWeight")
    public double scaleWeight;
    @Column(name = "active")
    public boolean active;
    @Column(name = "currentAirport", length = 3)
    public String currentAirport;
    @Column(name = "status", length = 3)
    public String status;
    @Column(name = "destination", length = 3)
    public String destination;
    @Column(name = "finalDestination", length = 3)
    public String finalDestination;
    @Column(name = "flightSeq")
    public int flightSeq;
    @Column(name = "carrier", length = 3)
    public String carrier;
    @Column(name = "flightNumber", length = 5)
    public String flightNumber;
    @Column(name = "flightDate")
    @Temporal(value = TemporalType.DATE)
    public Date flightDate;
    @Column(name = "loadingIndicator")
    public String loadingIndicator;
    @Column(name = "remarks")
    public String remarks;

    public String getLoadingIndicator() {
        return loadingIndicator;
    }

    public void setLoadingIndicator(String loadingIndicator) {
        this.loadingIndicator = loadingIndicator;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getAvailVolume() {
        return availVolume;
    }

    public void setAvailVolume(double availVolume) {
        this.availVolume = availVolume;
    }

    public double getAvailWeight() {
        return availWeight;
    }

    public void setAvailWeight(double availWeight) {
        this.availWeight = availWeight;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(String currentAirport) {
        this.currentAirport = currentAirport;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
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

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getScaleWeight() {
        return scaleWeight;
    }

    public void setScaleWeight(double scaleWeight) {
        this.scaleWeight = scaleWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public String getUld() {
        return uld;
    }

    public void setUld(String uld) {
        this.uld = uld;
    }

    public Flight extractNextFlight() {
        return (Flight) selectFirstCache("SELECT a FROM Flight a WHERE a.origin='"+destination+"' AND a.status NOT IN('DEP')");
    }
    
    public void removeFromFlight() {
        flightSeq = 0;
        status = "RCS";
        save();
        
        //change all the AWBs
        List lst = selectListCache("SELECT a FROM AwbFlt a WHERE a.flightSeq="+flightSeq+" AND a.uldNumber='"+uld+"'");
        for (Object obj:lst) {
            AwbFlt flt = (AwbFlt) obj;
            flt.status = "RCS";
            flt.save();
        }
    }
    
    public void arriveFromFlight() {
        if ("DEP".equals(status)) {
            UldNumber f = (UldNumber) clone();
            f.save();
        }
//        if (flight==null) {
//            uld.setFlightSeq(0);
//        }
//        else {
//            uld.setFlightSeq(flight.getSeq());
//            uld.setCarrier(flight.getCarrier());
//            uld.setFlightNumber(flight.getFlightNumber());
//            uld.setFlightDate(flight.getFlightDate());
//            uld.setCurrentAirport(flight.getOrigin());
//            uld.setDestination(flight.getDestination());
//        }
        status = "RCF";
        save();
        
        //change all the AWBs
        List lst = selectListCache("SELECT a FROM AwbFlt a WHERE a.flightSeq="+flightSeq+" AND a.uldNumber='"+uld+"'");
        for (Object obj:lst) {
            AwbFlt flt = (AwbFlt) obj;
            flt.arriveFromFlight();
        }
    }
    
    protected void assignToFlight(Flight flight) {
        if (!isAllowedToFly()) return;
        if ("DEP".equals(getStatus())) {
            UldNumber f = (UldNumber) clone();
            f.setIsActive(true);
            f.save();
        }
        if (flight==null) {
            setFlightSeq(0);
        }
        else {
            setFlightSeq(flight.getSeq());
            setCarrier(flight.getCarrier());
            setFlightNumber(flight.getFlightNumber());
            setFlightDate(flight.getFlightDate());
            setCurrentAirport(flight.getOrigin());
            setDestination(flight.getDestination());
        }
        setStatus("MAN");
        save();
        
        //change all the AWBs
        List lst = selectListCache("SELECT a FROM AwbFlt a WHERE a.flightSeq="+getFlightSeq()+" AND a.uldNumber='"+getUld()+"'");
        for (Object obj:lst) {
            AwbFlt flt = (AwbFlt) obj;
            flt.assignToFlight(flight);
        }
    }

    private boolean isAllowedToFly() {
        if (status==null) return true;
        if (status.equals("MSC")) return false;
        if (status.equals("RCF")) return false;
        if (status.equals("NFD")) return false;
        if (status.equals("DLV")) return false;
        if (status.equals("BDW")) return false;
        if (status.equals("RTD")) return false;
        return true;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"uld");
    }
}
