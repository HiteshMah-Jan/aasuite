/*
 * Blood.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientLaboratory")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"pregnancyTest", "appearance"})
@DiscriminatorValue(value = "URINALYSIS")
@Displays({
        @Display(name="color"),
        @Display(name="pusCells"),
        @Display(name="appearance"),
        @Display(name="rbc"),
        @Display(name="reaction"),
        @Display(name="epithelialCells"),
        @Display(name="specificGravity"),
        @Display(name="amorphousSubstance"),
        @Display(name="albumin"),
        @Display(name="mucusThreads"),
        @Display(name="sugar"),
        @Display(name="bacteria"),
        @Display(name="pregnancyTest"),
        @Display(name="cast"),
        @Display(name="seq"),
        @Display(name="patient"),
        @Display(name="labDate"),
        @Display(name="laboratoryTest"),
        @Display(name="requestingPhysician"),
        @Display(name="commendation"),
        @Display(name="labresult"),
        @Display(name="amount"),
        @Display(name="findings"),
        @Display(name="impression"),
        @Display(name="exam"),
        @Display(name="addendum"),
        @Display(name="sonographer"),
        @Display(name="labinput"),
        @Display(name="laboutput"),
        @Display(name="er_a"),
        @Display(name="er_wic"),
        @Display(name="bilrubin"),
        @Display(name="alkPhos"),
        @Display(name="sgot"),
        @Display(name="sgpt"),
        @Display(name="amylase"),
        @Display(name="bun"),
        @Display(name="creatinine"),
        @Display(name="requestDate"),
        @Display(name="requestTime"),
        @Display(name="finishDate"),
        @Display(name="opdId")
})
public class PatientUrinalysis extends PatientLaboratory implements Serializable {

    @Column(name = "color")
    public String color;
    @Column(name = "pusCells")
    public String pusCells;
    @Column(name = "appearance")
    public String appearance;
    @Column(name = "rbc")
    public String rbc;
    @Column(name = "reaction")
    public String reaction;
    @Column(name = "epithelialCells")
    public String epithelialCells;
    @Column(name = "specificGravity")
    public String specificGravity;
    @Column(name = "amorphousSubstance")
    public String amorphousSubstance;
    @Column(name = "albumin")
    public String albumin;
    @Column(name = "mucusThreads")
    public String mucusThreads;
    @Column(name = "sugar")
    public String sugar;
    @Column(name = "bacteria")
    public String bacteria;
    @Column(name = "pregnancyTest")
    public String pregnancyTest;
    @Column(name = "cast")
    public String cast;

    public String getAlbumin() {
        return albumin;
    }

    public void setAlbumin(String albumin) {
        this.albumin = albumin;
    }

    public String getAmorphousSubstance() {
        return amorphousSubstance;
    }

    public void setAmorphousSubstance(String amorphousSubstance) {
        this.amorphousSubstance = amorphousSubstance;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getBacteria() {
        return bacteria;
    }

    public void setBacteria(String bacteria) {
        this.bacteria = bacteria;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEpithelialCells() {
        return epithelialCells;
    }

    public void setEpithelialCells(String epithelialCells) {
        this.epithelialCells = epithelialCells;
    }

    public String getMucusThreads() {
        return mucusThreads;
    }

    public void setMucusThreads(String mucusThreads) {
        this.mucusThreads = mucusThreads;
    }

    public String getPregnancyTest() {
        return pregnancyTest;
    }

    public void setPregnancyTest(String pregnancyTest) {
        this.pregnancyTest = pregnancyTest;
    }

    public String getPusCells() {
        return pusCells;
    }

    public void setPusCells(String pusCells) {
        this.pusCells = pusCells;
    }

    public String getRbc() {
        return rbc;
    }

    public void setRbc(String rbc) {
        this.rbc = rbc;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }
}
