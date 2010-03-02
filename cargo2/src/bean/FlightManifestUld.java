/*
 * FlightManifestUld.java
 * 
 * Created on Sep 30, 2007, 8:02:11 PM
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

import bean.reference.TraceStatus;

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
@Table(name = "FlightManifestUld")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"checkDate","checkTime","weight","volume"}, showChart=true)
@Displays({
    @Display(name = "checkDate"),
    @Display(name = "checkTime", type="Time"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "formNumber"),
    @Display(name = "uld", type="PopSearch", linktoBean=UldNumber.class)
})
public class FlightManifestUld extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "checkDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date checkDate;
    @Column(name = "checkTime", nullable = false)
    public int checkTime;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "status", nullable = false, length=3)
    public String status;
    @Column(name = "formNumber", nullable = false, length=10)
    public String formNumber;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "flightSeq", nullable = false)
    public int flightSeq;
    @Column(name = "uld", nullable = false)
    public String uld;

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "flightSeq","uldSeq","checkDate");
	}

	public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public int getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(int checkTime) {
        this.checkTime = checkTime;
    }

    public int getFlightSeq() {
        return flightSeq;
    }

    public void setFlightSeq(int flightSeq) {
        this.flightSeq = flightSeq;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
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

    public String getUld() {
		return uld;
	}

	public void setUld(String uld) {
		this.uld = uld;
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
