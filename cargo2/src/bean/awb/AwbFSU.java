/*
 * AwbFlt.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.Carrier;
import bean.UldNumber;
import bean.reference.Airport;
import bean.reference.TraceStatus;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbFSU")
@UITemplate(template = TemplateTabPage.class, gridCount = 10, columnSearch = {"status", "fsuDate", "airport", "uld"})
@Displays({
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "fsuDate"),
    @Display(name = "fsuTime"),
    @Display(name = "airport", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "carrier", type="PopSearch", linktoBean=Carrier.class),
    @Display(name = "flightNumber"),
    @Display(name = "flightDate"),
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "uld", type="PopSearch", linktoBean=UldNumber.class)
})
public class AwbFSU extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "status", nullable = false)
    public String status;
    @Column(name = "fsuDate")
    public String fsuDateTime;
    @Column(name = "airport")
    public String airport;
    @Column(name = "carrier")
    public String carrier;
    @Column(name = "flightNumber")
    public String flightNumber;
    @Column(name = "flightDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date flightDate;
    @Column(name = "pieces")
    public int pieces;
    @Column(name = "weight")
    public double weight;
    @Column(name = "volume")
    public double volume;
    @Column(name = "uld")
    public String uld;
    @Column(name = "descCode")
    public String descCode;
    @Column(name = "weightCode")
    public String weightCode;
    @Column(name = "recivFrom")
    public String recivFrom;
    @Column(name = "volumeCode")
    public String volumeCode;
    @Column(name = "depInfo")
    public String depInfo;
    @Column(name = "arrInfo")
    public String arrInfo;
    @Column(name = "subCode")
    public String subCode;

    public String getArrInfo() {
        return arrInfo;
    }

    public void setArrInfo(String arrInfo) {
        this.arrInfo = arrInfo;
    }

    public String getDepInfo() {
        return depInfo;
    }

    public void setDepInfo(String depInfo) {
        this.depInfo = depInfo;
    }

    public String getRecivFrom() {
        return recivFrom;
    }

    public void setRecivFrom(String recivFrom) {
        this.recivFrom = recivFrom;
    }

    public String getVolumeCode() {
        return volumeCode;
    }

    public void setVolumeCode(String volumeCode) {
        this.volumeCode = volumeCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getDescCode() {
        return descCode;
    }

    public void setDescCode(String descCode) {
        this.descCode = descCode;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
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

    public String getFsuDateTime() {
        return fsuDateTime;
    }

    public void setFsuDateTime(String fsuDateTime) {
        this.fsuDateTime = fsuDateTime;
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
