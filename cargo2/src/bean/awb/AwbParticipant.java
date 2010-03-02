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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.reference.Country;
import bean.reference.State;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbParticipant")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"name"})
@Displays({
    @Display(name = "name"),
    @Display(name = "streetAddress"),
    @Display(name = "place"),
    @Display(name = "state", type="PopSearch", linktoBean=State.class),
    @Display(name = "country", type="PopSearch", linktoBean=Country.class),
    @Display(name = "postCode"),
    @Display(name = "contactId"),
    @Display(name = "contactPhone")
})
public class AwbParticipant extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "name")
    public String name;
    @Column(name = "streetAddress")
    public String streetAddress;
    @Column(name = "place")
    public String place;
    @Column(name = "state")
    public String state;
    @Column(name = "country")
    public String country;
    @Column(name = "postCode")
    public String postCode;
    @Column(name = "contactId")
    public String contactId;
    @Column(name = "contactPhone")
    public String contactPhone;
    @Column(name = "messageAddress")
    public String messageAddress;
    @Column(name = "fileReference")
    public String fileReference;
    @Column(name = "identifier")
    public String identifier;
    @Column(name = "partCode")
    public String partCode;
    @Column(name = "airportCode")
    public String airportCode;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
