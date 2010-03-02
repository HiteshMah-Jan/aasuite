/*
 * StationAllotment.java
 * 
 * Created on Sep 30, 2007, 8:02:09 PM
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
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "StationAllotment")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"fromDate","toDate","volume","weight","destination"}, showChart=true)
@Displays({
    @Display(name = "fromDate"),
    @Display(name = "toDate"),
    @Display(name = "volume"),
    @Display(name = "weight"),
    @Display(name = "availVolume"),
    @Display(name = "availWeight"),
    @Display(name = "destination", type="PopSearch", linktoBean=Airport.class)
})
public class StationAllotment extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "toDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date toDate;
    @Column(name = "station", nullable = false, length=3)
    public String station;
    @Column(name = "availWeight")
    public double availWeight;
    @Column(name = "fromDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date fromDate;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "availVolume")
    public double availVolume;
    @Column(name = "destination", length=3)
    public String destination;
    @Column(name = "active")
    public boolean active;

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "station","fromDate","destination");
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
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
