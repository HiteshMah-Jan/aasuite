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
@UITemplate(template = TemplateTabPage.class, columnSearch = {"gallBladder", "liver", "pancreas"})
@DiscriminatorValue("ULTRASOUND")
@Displays({
        @Display(name="gallBladder"),
        @Display(name="cbd"),
        @Display(name="liver"),
        @Display(name="spleen"),
        @Display(name="pancreas"),
        @Display(name="rightkidney"),
        @Display(name="leftkidney"),
        @Display(name="aorta"),
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
public class PatientUltrasound extends PatientRadiology implements Serializable {

    @Column(name = "gallBladder")
    public String gallBladder;
    @Column(name = "cbd")
    public String cbd;
    @Column(name = "liver")
    public String liver;
    @Column(name = "spleen")
    public String spleen;
    @Column(name = "pancreas")
    public String pancreas;
    @Column(name = "rightkidney")
    public String rightkidney;
    @Column(name = "leftkidney")
    public String leftkidney;
    @Column(name = "aorta")
    public String aorta;

    public String getAorta() {
        return aorta;
    }

    public void setAorta(String aorta) {
        this.aorta = aorta;
    }

    public String getCbd() {
        return cbd;
    }

    public void setCbd(String cbd) {
        this.cbd = cbd;
    }

    public String getGallBladder() {
        return gallBladder;
    }

    public void setGallBladder(String gallBladder) {
        this.gallBladder = gallBladder;
    }

    public String getLeftkidney() {
        return leftkidney;
    }

    public void setLeftkidney(String leftkidney) {
        this.leftkidney = leftkidney;
    }

    public String getLiver() {
        return liver;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public String getPancreas() {
        return pancreas;
    }

    public void setPancreas(String pancreas) {
        this.pancreas = pancreas;
    }

    public String getRightkidney() {
        return rightkidney;
    }

    public void setRightkidney(String rightkidney) {
        this.rightkidney = rightkidney;
    }

    public String getSpleen() {
        return spleen;
    }

    public void setSpleen(String spleen) {
        this.spleen = spleen;
    }
}
