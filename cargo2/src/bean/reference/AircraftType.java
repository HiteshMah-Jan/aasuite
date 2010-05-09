/*
 * AircraftType.java
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

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AircraftType")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, 
    columnSearch = {"code","name","flightType","defaultVolume","defaultWeight"}, showChart=true)
@Displays({
    @Display(name = "code", width=40),
    @Display(name = "name", width=40),
    @Display(name = "flightType", width=100, type="Combo", modelCombo={"PASSENGER","FREIGHTER","TRUCK"}),
    @Display(name = "defaultVolume", width=50),
    @Display(name = "defaultWeight", label="Weigth", width=50),
    @Display(name = "mh", width=30, mergeFields={"mp","lp","lc"}, label="ULD MH"),
    @Display(name = "mp", width=30, label="MP"),
    @Display(name = "lp", width=30, label="LP"),
    @Display(name = "lc", width=30, label="LC")
})
public class AircraftType extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }

	public static void main(String[] args) {
		view(AircraftType.class);
	}
    @Id
    @Column(name = "code", nullable = false, length = 20)
    public String code;
    @Column(name = "defaultVolume")
    public double defaultVolume;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "defaultWeight")
    public double defaultWeight;
    @Column(name = "flightType", nullable = false, length = 50)
    public String flightType;
    @Column(name = "mh")
    public int mh;
    @Column(name = "mp")
    public int mp;
    @Column(name = "lp")
    public int lp;
    @Column(name = "lc")
    public int lc;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDefaultVolume() {
        return defaultVolume;
    }

    public void setDefaultVolume(double defaultVolume) {
        this.defaultVolume = defaultVolume;
    }

    public double getDefaultWeight() {
        return defaultWeight;
    }

    public void setDefaultWeight(double defaultWeight) {
        this.defaultWeight = defaultWeight;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public int getLc() {
        return lc;
    }

    public void setLc(int lc) {
        this.lc = lc;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getMh() {
        return mh;
    }

    public void setMh(int mh) {
        this.mh = mh;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name","flightType");
    }
}
