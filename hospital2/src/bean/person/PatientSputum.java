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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
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
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"smearResult"})
@DiscriminatorValue(value = "SPUTUM")
@Displays({
        @Display(name="date"),
        @Display(name="smearResult"),
        @Display(name="specimenType"),
        @Display(name="anatomicSite"),
        @Display(name="nucleicAcidAmplificationTestResult"),
        @Display(name="cultureResult"),
        @Display(name="cultureSpecimen"),
        @Display(name="cultureAnatomicSite"),
        @Display(name="isoniazid"),
        @Display(name="rifampicin"),
        @Display(name="ethambutol"),
        @Display(name="other"),
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
public class PatientSputum extends PatientLaboratory implements Serializable {

    @Id
    @Column(name = "date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date date;
    @Column(name = "smearResult")
    public String smearResult;
    @Column(name = "specimenType")
    public String specimenType;
    @Column(name = "anatomicSite")
    public String anatomicSite;
    @Column(name = "nucleicAcidAmplificationTestResult")
    public String nucleicAcidAmplificationTestResult;
    @Column(name = "cultureResult")
    public String cultureResult;
    @Column(name = "cultureSpecimen")
    public String cultureSpecimen;
    @Column(name = "cultureAnatomicSite")
    public String cultureAnatomicSite;
    @Column(name = "isoniazid")
    public Boolean isoniazid;
    @Column(name = "rifampicin")
    public Boolean rifampicin;
    @Column(name = "ethambutol")
    public Boolean ethambutol;
    @Column(name = "other")
    public String other;

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.lang.String getSmearResult() {
        return smearResult;
    }

    public void setSmearResult(java.lang.String smearResult) {
        this.smearResult = smearResult;
    }

    public java.lang.String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(java.lang.String specimenType) {
        this.specimenType = specimenType;
    }

    public java.lang.String getAnatomicSite() {
        return anatomicSite;
    }

    public void setAnatomicSite(java.lang.String anatomicSite) {
        this.anatomicSite = anatomicSite;
    }

    public java.lang.String getNucleicAcidAmplificationTestResult() {
        return nucleicAcidAmplificationTestResult;
    }

    public void setNucleicAcidAmplificationTestResult(java.lang.String nucleicAcidAmplificationTestResult) {
        this.nucleicAcidAmplificationTestResult = nucleicAcidAmplificationTestResult;
    }

    public java.lang.String getCultureResult() {
        return cultureResult;
    }

    public void setCultureResult(java.lang.String cultureResult) {
        this.cultureResult = cultureResult;
    }

    public java.lang.String getCultureSpecimen() {
        return cultureSpecimen;
    }

    public void setCultureSpecimen(java.lang.String cultureSpecimen) {
        this.cultureSpecimen = cultureSpecimen;
    }

    public java.lang.String getCultureAnatomicSite() {
        return cultureAnatomicSite;
    }

    public void setCultureAnatomicSite(java.lang.String cultureAnatomicSite) {
        this.cultureAnatomicSite = cultureAnatomicSite;
    }

    public java.lang.Boolean getIsoniazid() {
        return isoniazid;
    }

    public void setIsoniazid(java.lang.Boolean isoniazid) {
        this.isoniazid = isoniazid;
    }

    public java.lang.Boolean getRifampicin() {
        return rifampicin;
    }

    public void setRifampicin(java.lang.Boolean rifampicin) {
        this.rifampicin = rifampicin;
    }

    public java.lang.Boolean getEthambutol() {
        return ethambutol;
    }

    public void setEthambutol(java.lang.Boolean ethambutol) {
        this.ethambutol = ethambutol;
    }

    public java.lang.String getOther() {
        return other;
    }

    public void setOther(java.lang.String other) {
        this.other = other;
    }
}
