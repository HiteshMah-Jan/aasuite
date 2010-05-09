/*
 * CarrierEmbargo.java
 * 
 * Created on Sep 30, 2007, 8:02:08 PM
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
import bean.reference.Country;
import bean.reference.SpecialHandling;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CarrierEmbargo")
@UITemplate(template = TemplateSinglePage.class, 
    columnSearch = {"country","airport","fromDate","shc"})
@Displays({
    @Display(name = "country", type="PopSearch", linktoBean=Country.class),
    @Display(name = "airport", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "fromDate"),
    @Display(name = "toDate"),
    @Display(name = "shc", type="PopSearch", linktoBean=SpecialHandling.class)
})
public class CarrierEmbargo extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "toDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date toDate;
    @Column(name = "country", nullable = false, length=3)
    public String country;
    @Column(name = "fromDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date fromDate;
    @Column(name = "remarks", nullable = false, length=150)
    public String remarks;
    @Column(name = "shc", nullable = false, length=3)
    public String shc;
    @Column(name = "airport", nullable = false, length=3)
    public String airport;
    @Column(name = "carrier", nullable = false, length=3)
    public String carrier;

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "carrier","country","airport","fromDate","shc");
	}

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
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

    public String getShc() {
        return shc;
    }

    public void setShc(String shc) {
        this.shc = shc;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
