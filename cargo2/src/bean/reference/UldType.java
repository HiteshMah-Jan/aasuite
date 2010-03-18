/*
 * UldType.java
 *
 * Created on Oct 14, 2007, 9:25:34 AM
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "UldType")
@UITemplate(template = TemplateSinglePage.class,
    columnSearch = {"code", "name"})
@Displays({
    @Display(name = "code"),
    @Display(name = "name", width = 250)
})
public class UldType extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", length=5)
    public String code;
    @Column(name = "name", length=50)
    public String name;
    @Column(name = "description", length=50)
    public String description;
    @Column(name = "shape", length=1)
    public String shape;
    @Column(name = "deck", length=20)
    public String deck;
    @Column(name = "uldGroup", length=10)
    public String uldGroup;

    @Column(name = "ratingClass", length=3)
    public String ratingClass;
    @Column(name = "iataCode", length=3)
    public String iataCode;
    @Column(name = "ataCode", length=3)
    public String ataCode;
    @Column(name = "carrier", length=3)
    public String carrier;

    @Column(name = "pivotWeight")
    public double pivotWeight;
    @Column(name = "tareWeightKg")
    public double tareWeightKg;
    @Column(name = "tareWeightLb")
    public double tareWeightLb;
    @Column(name = "volumeMc")
    public double volumeMc;
    @Column(name = "volumeCf")
    public double volumeCf;
    @Column(name = "maxLoadKg")
    public double maxLoadKg;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getShape() {
        return shape;
    }

    public void setShape(java.lang.String shape) {
        this.shape = shape;
    }

    public java.lang.String getDeck() {
        return deck;
    }

    public void setDeck(java.lang.String deck) {
        this.deck = deck;
    }

    public java.lang.String getUldGroup() {
        return uldGroup;
    }

    public void setUldGroup(java.lang.String uldGroup) {
        this.uldGroup = uldGroup;
    }

    public java.lang.String getRatingClass() {
        return ratingClass;
    }

    public void setRatingClass(java.lang.String ratingClass) {
        this.ratingClass = ratingClass;
    }

    public java.lang.String getIataCode() {
        return iataCode;
    }

    public void setIataCode(java.lang.String iataCode) {
        this.iataCode = iataCode;
    }

    public java.lang.String getAtaCode() {
        return ataCode;
    }

    public void setAtaCode(java.lang.String ataCode) {
        this.ataCode = ataCode;
    }

    public java.lang.String getCarrier() {
        return carrier;
    }

    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }

    public double getPivotWeight() {
        return pivotWeight;
    }

    public void setPivotWeight(double pivotWeight) {
        this.pivotWeight = pivotWeight;
    }

    public double getTareWeightKg() {
        return tareWeightKg;
    }

    public void setTareWeightKg(double tareWeightKg) {
        this.tareWeightKg = tareWeightKg;
    }

    public double getTareWeightLb() {
        return tareWeightLb;
    }

    public void setTareWeightLb(double tareWeightLb) {
        this.tareWeightLb = tareWeightLb;
    }

    public double getVolumeMc() {
        return volumeMc;
    }

    public void setVolumeMc(double volumeMc) {
        this.volumeMc = volumeMc;
    }

    public double getVolumeCf() {
        return volumeCf;
    }

    public void setVolumeCf(double volumeCf) {
        this.volumeCf = volumeCf;
    }

    public double getMaxLoadKg() {
        return maxLoadKg;
    }

    public void setMaxLoadKg(double maxLoadKg) {
        this.maxLoadKg = maxLoadKg;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name","productCode");
    }
}
