/*
 * AwbRef.java
 * 
 * Created on Oct 25, 2007, 3:07:57 PM
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

import bean.reference.Country;
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
@Table(name = "AwbCCDestinationCurrency")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"country", "totalCharge"})
@Displays({
    @Display(name = "country", type="PopSearch", linktoBean=Country.class),
    @Display(name = "exchangeRate"),
    @Display(name = "ccAmount"),
    @Display(name = "destinationAmount"),
    @Display(name = "totalCharge")
})
public class AwbCCDestinationCurrency extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "country")
    public String country;
    @Column(name = "exchangeRate")
    public double exchangeRate;
    @Column(name = "ccAmount")
    public double ccAmount;
    @Column(name = "destinationAmount")
    public double destinationAmount;
    @Column(name = "totalCharge")
    public double totalCharge;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public double getCcAmount() {
        return ccAmount;
    }

    public void setCcAmount(double ccAmount) {
        this.ccAmount = ccAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getDestinationAmount() {
        return destinationAmount;
    }

    public void setDestinationAmount(double destinationAmount) {
        this.destinationAmount = destinationAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
