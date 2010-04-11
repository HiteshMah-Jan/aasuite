/*
 * Airport.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.screen.TemplateSinglePage;
import template.*;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Airport")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"code","city","state","name","country"}, showChart=true)
@Displays({
    @Display(name = "code", width=40),
    @Display(name = "name"),
    @Display(name = "gmtTimeDiff", width=30),
    @Display(name = "city"),
    @Display(name = "state", type="PopSearch", linktoBean=State.class),
    @Display(name = "country", width=70, type="PopSearch", linktoBean=Country.class)
})
public class Airport extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "city", length = 150)
    public String city;
    @Column(name = "state", length = 20)
    public String state;
    @Column(name = "name", nullable = false, length = 150)
    public String name;
    @Column(name = "country", nullable = false, length = 3)
    public String country;
    @Column(name = "gmtTimeDiff", nullable = false)
    public int gmtTimeDiff;
    @Column(name = "active")
    public boolean active;

    public static String extractCurrency(String airport) {
    	Country c = (Country) DBClient.getFirstRecord("SELECT a FROM Country a, Airport b WHERE a.code=b.country AND b.code='"+airport+"'");
    	return c.currency;
    }    

    public static String extractCountry(String airport) {
    	Country c = (Country) DBClient.getFirstRecord("SELECT a FROM Country a, Airport b WHERE a.code=b.country AND b.code='"+airport+"'");
    	return c.code;
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
        return buildSearch(criteria, "code","name","country");
    }
}
