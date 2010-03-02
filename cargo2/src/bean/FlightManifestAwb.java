/*
 * FlightManifestAwb.java
 * 
 * Created on Sep 30, 2007, 8:02:13 PM
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
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "FlightManifestAwb")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"checkDate","checkTime","weight","volume"}, showChart=true)
@Displays({
    @Display(name = "checkDate"),
    @Display(name = "checkTime"),
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "formNumber"),
    @Display(name = "awbSeq")
})
public class FlightManifestAwb extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "checkTime", nullable = false)
    public int checkTime;
    @Column(name = "status", nullable = false, length=3)
    public String status;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "checkDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date checkDate;
    @Column(name = "formNumber", nullable = false, length=10)
    public String formNumber;
    @Column(name = "awbSeq")
    public int awbSeq;
    @Column(name = "flightSeq")
    public int flightSeq;

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "flightSeq","awbSeq","checkDate");
	}
	
    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
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

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
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
