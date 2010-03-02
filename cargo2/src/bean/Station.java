/*
 * Airport.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import bean.reference.Airport;
import bean.reference.Country;
import bean.reference.State;
import bean.reference.TraceStatus;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Station")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"code","city","state","name","country"}, showChart=true)
@Displays({
    @Display(name = "code", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "city"),
    @Display(name = "state", type="PopSearch", linktoBean=State.class),
    @Display(name = "name"),
    @Display(name = "country", type="PopSearch", linktoBean=Country.class),
    @Display(name = "hdq"),
    @Display(name = "gmtTimeDiff", width=30)
})
public class Station extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "city", length = 50)
    public String city;
    @Column(name = "state", length = 2)
    public String state;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "country", nullable = false, length = 3)
    public String country;
    @Column(name = "gmtTimeDiff", nullable = false)
    public int gmtTimeDiff;
    @Column(name = "active")
    public boolean active;
    @Column(name = "hdq")
    public boolean hdq;

    public boolean isHdq() {
        return hdq;
    }

    public void setHdq(boolean hdq) {
        this.hdq = hdq;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGmtTimeDiff() {
        return gmtTimeDiff;
    }

    public void setGmtTimeDiff(int gmtTimeDiff) {
        this.gmtTimeDiff = gmtTimeDiff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
