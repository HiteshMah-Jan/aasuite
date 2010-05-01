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
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"hemoglobin", "hematocrit"})
@DiscriminatorValue(value = "HEMATOLOGY")
@Displays({
        @Display(name="hemoglobin"),
        @Display(name="hematocrit"),
        @Display(name="wbcCount"),
        @Display(name="segmenters"),
        @Display(name="lymphocytes"),
        @Display(name="eosinophils"),
        @Display(name="monocytes"),
        @Display(name="basophils"),
        @Display(name="rbcCount"),
        @Display(name="plateletCount"),
        @Display(name="bleedingTime"),
        @Display(name="coagulationTime"),
        @Display(name="bloodTyping"),
        @Display(name="rhTyping"),
        @Display(name="esr"),
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
public class PatientHematology extends PatientLaboratory implements Serializable {

    @Column(name = "hemoglobin")
    public String hemoglobin;
    @Column(name = "hematocrit")
    public String hematocrit;
    @Column(name = "wbcCount")
    public String wbcCount;
    @Column(name = "segmenters")
    public String segmenters;
    @Column(name = "lymphocytes")
    public String lymphocytes;
    @Column(name = "eosinophils")
    public String eosinophils;
    @Column(name = "monocytes")
    public String monocytes;
    @Column(name = "basophils")
    public String basophils;
    @Column(name = "rbcCount")
    public String rbcCount;
    @Column(name = "plateletCount")
    public String plateletCount;
    @Column(name = "bleedingTime")
    public String bleedingTime;
    @Column(name = "coagulationTime")
    public String coagulationTime;
    @Column(name = "bloodTyping")
    public String bloodTyping;
    @Column(name = "rhTyping")
    public String rhTyping;
    @Column(name = "esr")
    public String esr;

    public String getBasophils() {
        return basophils;
    }

    public void setBasophils(String basophils) {
        this.basophils = basophils;
    }

    public String getBleedingTime() {
        return bleedingTime;
    }

    public void setBleedingTime(String bleedingTime) {
        this.bleedingTime = bleedingTime;
    }

    public String getBloodTyping() {
        return bloodTyping;
    }

    public void setBloodTyping(String bloodTyping) {
        this.bloodTyping = bloodTyping;
    }

    public String getCoagulationTime() {
        return coagulationTime;
    }

    public void setCoagulationTime(String coagulationTime) {
        this.coagulationTime = coagulationTime;
    }

    public String getEosinophils() {
        return eosinophils;
    }

    public void setEosinophils(String eosinophils) {
        this.eosinophils = eosinophils;
    }

    public String getEsr() {
        return esr;
    }

    public void setEsr(String esr) {
        this.esr = esr;
    }

    public String getHematocrit() {
        return hematocrit;
    }

    public void setHematocrit(String hematocrit) {
        this.hematocrit = hematocrit;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public String getLymphocytes() {
        return lymphocytes;
    }

    public void setLymphocytes(String lymphocytes) {
        this.lymphocytes = lymphocytes;
    }

    public String getMonocytes() {
        return monocytes;
    }

    public void setMonocytes(String monocytes) {
        this.monocytes = monocytes;
    }

    public String getPlateletCount() {
        return plateletCount;
    }

    public void setPlateletCount(String plateletCount) {
        this.plateletCount = plateletCount;
    }

    public String getRbcCount() {
        return rbcCount;
    }

    public void setRbcCount(String rbcCount) {
        this.rbcCount = rbcCount;
    }

    public String getRhTyping() {
        return rhTyping;
    }

    public void setRhTyping(String rhTyping) {
        this.rhTyping = rhTyping;
    }

    public String getSegmenters() {
        return segmenters;
    }

    public void setSegmenters(String segmenters) {
        this.segmenters = segmenters;
    }

    public String getWbcCount() {
        return wbcCount;
    }

    public void setWbcCount(String wbcCount) {
        this.wbcCount = wbcCount;
    }
}
