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

import bean.Participant;
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
@Table(name = "AwbRef")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"city","participantCode"})
@Displays({
    @Display(name = "city"),
    @Display(name = "officeFunctionDesignator"),
    @Display(name = "companyDesignator"),
    @Display(name = "bookingFileReference"),
    @Display(name = "participantCode", type="PopSearch", linktoBean=Participant.class)
})
public class AwbRef extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "city", nullable = false)
    public String city;
    @Column(name = "officeFunctionDesignator")
    public String officeFunctionDesignator;
    @Column(name = "companyDesignator")
    public String companyDesignator;
    @Column(name = "bookingFileReference")
    public String bookingFileReference;
    @Column(name = "participantIdentifier")
    public String participantIdentifier;
    @Column(name = "participantCode")
    public String participantCode;
    @Column(name = "airport")
    public String airport;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getBookingFileReference() {
        return bookingFileReference;
    }

    public void setBookingFileReference(String bookingFileReference) {
        this.bookingFileReference = bookingFileReference;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyDesignator() {
        return companyDesignator;
    }

    public void setCompanyDesignator(String companyDesignator) {
        this.companyDesignator = companyDesignator;
    }

    public String getOfficeFunctionDesignator() {
        return officeFunctionDesignator;
    }

    public void setOfficeFunctionDesignator(String officeFunctionDesignator) {
        this.officeFunctionDesignator = officeFunctionDesignator;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public String getParticipantIdentifier() {
        return participantIdentifier;
    }

    public void setParticipantIdentifier(String participantIdentifier) {
        this.participantIdentifier = participantIdentifier;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }    

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
