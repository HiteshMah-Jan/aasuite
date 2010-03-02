/*
 * ParticipantAllotment.java
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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bean.reference.Airport;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ParticipantAllotment")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"origin","destination","carrier","flightNumber","flightDate","fromDate","toDate","volume","weight"}, showChart=true)
@Displays({
    @Display(name = "flightSeq", type="PopSearch", linktoBean=Flight.class, width=200, gridFieldWidth=3),
    @Display(name = "origin", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "destination", type="PopSearch", linktoBean=Airport.class),

    @Display(name = "fromDate"),
    @Display(name = "toDate"),
    @Display(name = "volume"),
    @Display(name = "weight"),
    @Display(name = "availVolume"),
    @Display(name = "availWeight"),
    
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "participantCode", type="PopSearch", linktoBean=Participant.class)
})
public class ParticipantAllotment extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "toDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date toDate;
    @Column(name = "availVolume")
    public double availVolume;
    @Column(name = "flightSeq", nullable = false)
    public int flightSeq;
    @Column(name = "carrier", nullable = false, length=3)
    public String carrier;
    @Column(name = "flightDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date flightDate;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "flightNumber", nullable = false, length=5)
    public String flightNumber;
    @Column(name = "availWeight")
    public double availWeight;
    @Column(name = "status", nullable = false, length=10)
    public String status;
    @Column(name = "fromDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date fromDate;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "origin", nullable = false, length=3)
    public String origin;
    @Column(name = "destination", nullable = false, length=3)
    public String destination;
    @Column(name = "participantCode", nullable = false, length=20)
    public String participantCode;
    @Column(name = "active")
    public boolean active;

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

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "participantCode","carrier","flightNumber","fromDate","origin");
		runUniqueIndex(2, "participantCode","flightSeq","origin");
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
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

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
}
