/*
 * AwbCheckin.java
 *
 * Created on Sep 30, 2007, 8:02:12 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.reference.Airport;
import bean.reference.SpecialHandling;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbCheckin")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"flightSeq","pieces","grossWeight","shc1","shc2"}, showChart=true)
@Displays({
    @Display(name = "flightSeq"),
    @Display(name = "pieces"),
    @Display(name = "grossWeight"),
    @Display(name = "ipcs"),
    @Display(name = "container"),
    @Display(name = "containerWeight"),
    @Display(name = "shc1", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "shc2", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "location"),
    @Display(name = "scr"),
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "dangerousGood"),
    @Display(name = "identifier"),
    @Display(name = "offload", type="PopSearch", linktoBean=Airport.class)
})
public class AwbCheckin extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "shc2", length = 3)
    public String shc2;
    @Column(name = "location", length = 5)
    public String location;
    @Column(name = "flightSeq", nullable = false)
    public int flightSeq;
    @Column(name = "container", length = 20)
    public String container;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "grossWeight", nullable = false)
    public double grossWeight;
    @Column(name = "scr", nullable = false, length = 50)
    public String scr;
    @Column(name = "containerWeight")
    public double containerWeight;
    @Column(name = "status", nullable = false, length = 3)
    public String status;
    @Column(name = "ipcs")
    public int ipcs;
    @Column(name = "dangerousGood")
    public boolean dangerousGood;
    @Column(name = "identifier")
    public String identifier;
    @Column(name = "shc1", length = 3)
    public String shc1;
    @Column(name = "offload", length = 3)
    public String offload;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public double getContainerWeight() {
        return containerWeight;
    }

    public void setContainerWeight(double containerWeight) {
        this.containerWeight = containerWeight;
    }

    public boolean isDangerousGood() {
        return dangerousGood;
    }

    public void setDangerousGood(boolean dangerousGood) {
        this.dangerousGood = dangerousGood;
    }

    public int getFlightSeq() {
        return flightSeq;
    }

    public void setFlightSeq(int flightSeq) {
        this.flightSeq = flightSeq;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getIpcs() {
        return ipcs;
    }

    public void setIpcs(int ipcs) {
        this.ipcs = ipcs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOffload() {
        return offload;
    }

    public void setOffload(String offload) {
        this.offload = offload;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getScr() {
        return scr;
    }

    public void setScr(String scr) {
        this.scr = scr;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
