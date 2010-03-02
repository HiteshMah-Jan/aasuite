/*
 * UldStatus.java
 * 
 * Created on Sep 30, 2007, 8:02:05 PM
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
@Table(name = "UldStatus")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"origin","destination","carrier","flightNumber","flightDate"})
@Displays({
    @Display(name = "origin",linktoBean=Airport.class,type="PopSearch"),
    @Display(name = "destination",linktoBean=Airport.class,type="PopSearch"),
    @Display(name = "carrier",linktoBean=Carrier.class,type="PopSearch"),
    @Display(name = "flightNumber"),
    @Display(name = "flightDate")
})
public class UldStatus extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    private Integer seq;
    @Column(name = "flightDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date flightDate;
    @Column(name = "uldCode", nullable = false)
    private int uldCode;
    @Column(name = "status", nullable = false, length=3)
    private String status;
    @Column(name = "flightNumber", nullable = false, length=5)
    private String flightNumber;
    @Column(name = "destination", nullable = false, length=3)
    private String destination;
    @Column(name = "carrier", nullable = false, length=3)
    private String carrier;
    @Column(name = "tstamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tstamp;
    @Column(name = "origin", nullable = false, length=3)
    private String origin;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public int getUldCode() {
        return uldCode;
    }

    public void setUldCode(int uldCode) {
        this.uldCode = uldCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getTstamp() {
        return tstamp;
    }

    public void setTstamp(Date tstamp) {
        this.tstamp = tstamp;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
