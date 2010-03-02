/*
 * AwbDeliver.java
 *
 * Created on Sep 30, 2007, 8:02:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import service.util.AbstractIBean;
import util.DBClient;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbDeliver")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"pieces","weight","volume","receivedBy","deliverDate"}, showChart=true)
@Displays({
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "receivedBy"),
    @Display(name = "deliverDate"),
    @Display(name = "totalChargesDue"),
    @Display(name = "totalPiecesReleases"),
    @Display(name = "releaseTo"),
    @Display(name = "releaseToAddress"),
    @Display(name = "releaseToIdType"),
    @Display(name = "remarks")
})
public class AwbDeliver extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "receivedBy", length = 50)
    public String receivedBy;
    @Column(name = "deliverDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date deliverDate;
    @Column(name = "awbMoveSeq", nullable = false)
    public int awbMoveSeq;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    @Column(name = "totalChargesDue")
    public double totalChargesDue;
    @Column(name = "totalPiecesReleases")
    public int totalPiecesReleases;
    @Column(name = "releaseTo", length = 150)
    public String releaseTo;
    @Column(name = "releaseToAddress", length = 150)
    public String releaseToAddress;
    @Column(name = "releaseToIdType", length = 100)
    public String releaseToIdType;

    public int getAwbMoveSeq() {
        return awbMoveSeq;
    }

    public void setAwbMoveSeq(int awbMoveSeq) {
        this.awbMoveSeq = awbMoveSeq;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getReleaseTo() {
        return releaseTo;
    }

    public void setReleaseTo(String releaseTo) {
        this.releaseTo = releaseTo;
    }

    public String getReleaseToAddress() {
        return releaseToAddress;
    }

    public void setReleaseToAddress(String releaseToAddress) {
        this.releaseToAddress = releaseToAddress;
    }

    public String getReleaseToIdType() {
        return releaseToIdType;
    }

    public void setReleaseToIdType(String releaseToIdType) {
        this.releaseToIdType = releaseToIdType;
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

    public double getTotalChargesDue() {
        return totalChargesDue;
    }

    public void setTotalChargesDue(double totalChargesDue) {
        this.totalChargesDue = totalChargesDue;
    }

    public int getTotalPiecesReleases() {
        return totalPiecesReleases;
    }

    public void setTotalPiecesReleases(int totalPiecesReleases) {
        this.totalPiecesReleases = totalPiecesReleases;
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
