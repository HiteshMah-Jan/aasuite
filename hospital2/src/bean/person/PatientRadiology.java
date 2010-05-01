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
@UITemplate(template = TemplateTabPage.class, columnSearch = {"radiologist", "radiologyType"})
@DiscriminatorValue("RADIOLOGY")
@Displays({
        @Display(name="radiologist"),
        @Display(name="hasPreviousCtscan"),
        @Display(name="hasPreviousXray"),
        @Display(name="hasPreviousUltrasound"),
        @Display(name="radiologyType"),
        @Display(name="dicom"),
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
public class PatientRadiology extends PatientLaboratory implements Serializable {

    @Column(name = "radiologist")
    public String radiologist;
    @Column(name = "hasPreviousCtscan")
    public boolean hasPreviousCtscan;
    @Column(name = "hasPreviousXray")
    public boolean hasPreviousXray;
    @Column(name = "hasPreviousUltrasound")
    public boolean hasPreviousUltrasound;
    @Column(name = "radiologyType")
    public String radiologyType;

    public java.lang.String getRadiologist() {
        return radiologist;
    }

    public void setRadiologist(java.lang.String radiologist) {
        this.radiologist = radiologist;
    }

    public boolean getHasPreviousCtscan() {
        return hasPreviousCtscan;
    }

    public void setHasPreviousCtscan(boolean hasPreviousCtscan) {
        this.hasPreviousCtscan = hasPreviousCtscan;
    }

    public boolean getHasPreviousXray() {
        return hasPreviousXray;
    }

    public void setHasPreviousXray(boolean hasPreviousXray) {
        this.hasPreviousXray = hasPreviousXray;
    }

    public boolean getHasPreviousUltrasound() {
        return hasPreviousUltrasound;
    }

    public void setHasPreviousUltrasound(boolean hasPreviousUltrasound) {
        this.hasPreviousUltrasound = hasPreviousUltrasound;
    }

    public java.lang.String getRadiologyType() {
        return radiologyType;
    }

    public void setRadiologyType(java.lang.String radiologyType) {
        this.radiologyType = radiologyType;
    }
}
