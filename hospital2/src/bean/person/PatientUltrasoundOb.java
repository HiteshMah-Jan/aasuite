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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientLaboratory")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"fetalCardiac"})
@ChildRecords(value = {},
info = {
    @ParentAddInfo(title = "Biometry", displayFields = {"bpd", "hc", "ac", "fl", "ci", "flBpd", "flHc", "flAc", "hcAc", "gestationAge", "fetalWeight", "edd"}),
    @ParentAddInfo(title = "Fetal Anatomy", displayFields = {"fetalVenticles", "fetalCerebellum", "fetalCranium", "fetalFace", "fetalHeart", "fetalDiaphragm", "fetalStomach", "fetalCord", "fetalAbdominal", "fetalSpine", "fetalKidneys", "fetalBladder", "fetalLimbs"})
})
@DiscriminatorValue(value = "ULTRASOUNDOB")
@Displays({
        @Display(name="fetalCardiac"),
        @Display(name="fetalLie"),
        @Display(name="fetalPresentation"),
        @Display(name="placenta"),
        @Display(name="uterus"),
        @Display(name="cervix"),
        @Display(name="adnexa"),
        @Display(name="amnioticFluid"),
        @Display(name="bpd"),
        @Display(name="hc"),
        @Display(name="ac", addInfoOnly = true),
        @Display(name="fl", addInfoOnly = true),
        @Display(name="ci", label = "CI (BPD/OFD) (70-86)", width = 100, addInfoOnly = true),
        @Display(name="flBpd", label = "FL/BPD (71-87)", width = 100, addInfoOnly = true),
        @Display(name="flHc", label = "HC/AC (20.6-23.4)", width = 100, addInfoOnly = true),
        @Display(name="flAc", label = "FL/AC (20-24)", width = 100, addInfoOnly = true),
        @Display(name="hcAc", label = "HC/AC (0.87-1.06)", gridFieldWidth = 3, width = 100, addInfoOnly = true),
        @Display(name="gestationAge", addInfoOnly = true),
        @Display(name="fetalWeight", addInfoOnly = true),
        @Display(name="edd", addInfoOnly = true),
        @Display(name="fetalVenticles", addInfoOnly = true),
        @Display(name="fetalCerebellum", addInfoOnly = true),
        @Display(name="fetalCranium", addInfoOnly = true),
        @Display(name="fetalFace", addInfoOnly = true),
        @Display(name="fetalHeart", addInfoOnly = true),
        @Display(name="fetalDiaphragm", addInfoOnly = true),
        @Display(name="fetalStomach", addInfoOnly = true),
        @Display(name="fetalCord", addInfoOnly = true),
        @Display(name="fetalAbdominal", addInfoOnly = true),
        @Display(name="fetalSpine", addInfoOnly = true),
        @Display(name="fetalKidneys", addInfoOnly = true),
        @Display(name="fetalBladder", addInfoOnly = true),
        @Display(name="fetalLimbs", addInfoOnly = true),
        @Display(name="gallBladder", addInfoOnly = true),
        @Display(name="cbd", addInfoOnly = true),
        @Display(name="liver", addInfoOnly = true),
        @Display(name="spleen", addInfoOnly = true),
        @Display(name="pancreas", addInfoOnly = true),
        @Display(name="rightkidney", addInfoOnly = true),
        @Display(name="leftkidney", addInfoOnly = true),
        @Display(name="aorta", addInfoOnly = true),
        @Display(name="radiologist", addInfoOnly = true),
        @Display(name="hasPreviousCtscan", addInfoOnly = true),
        @Display(name="hasPreviousXray", addInfoOnly = true),
        @Display(name="hasPreviousUltrasound", addInfoOnly = true),
        @Display(name="radiologyType", addInfoOnly = true),
        @Display(name="dicom", addInfoOnly = true),
        @Display(name="seq", addInfoOnly = true),
        @Display(name="patient", addInfoOnly = true),
        @Display(name="labDate", addInfoOnly = true),
        @Display(name="laboratoryTest", addInfoOnly = true),
        @Display(name="requestingPhysician", addInfoOnly = true),
        @Display(name="commendation", addInfoOnly = true),
        @Display(name="labresult", addInfoOnly = true),
        @Display(name="amount", addInfoOnly = true),
        @Display(name="findings", addInfoOnly = true),
        @Display(name="impression", addInfoOnly = true),
        @Display(name="exam", addInfoOnly = true),
        @Display(name="addendum", addInfoOnly = true),
        @Display(name="sonographer", addInfoOnly = true),
        @Display(name="labinput", addInfoOnly = true),
        @Display(name="laboutput", addInfoOnly = true),
        @Display(name="er_a", addInfoOnly = true),
        @Display(name="er_wic", addInfoOnly = true),
        @Display(name="bilrubin", addInfoOnly = true),
        @Display(name="alkPhos", addInfoOnly = true),
        @Display(name="sgot", addInfoOnly = true),
        @Display(name="sgpt", addInfoOnly = true),
        @Display(name="amylase", addInfoOnly = true),
        @Display(name="bun", addInfoOnly = true),
        @Display(name="creatinine", addInfoOnly = true),
        @Display(name="requestDate", addInfoOnly = true),
        @Display(name="requestTime", addInfoOnly = true),
        @Display(name="finishDate", addInfoOnly = true),
        @Display(name="opdId", addInfoOnly = true)
})
public class PatientUltrasoundOb extends PatientUltrasound implements Serializable {

    @Column(name = "fetalCardiac")
    public String fetalCardiac;
    @Column(name = "fetalLie")
    public String fetalLie;
    @Column(name = "fetalPresentation")
    public String fetalPresentation;
    @Column(name = "placenta")
    public String placenta;
    @Column(name = "uterus")
    public String uterus;
    @Column(name = "cervix")
    public String cervix;
    @Column(name = "adnexa")
    public String adnexa;
    @Column(name = "amnioticFluid")
    public String amnioticFluid;

    //parentTabBiometry
    @Column(name = "bpd")
    public String bpd;
    @Column(name = "hc")
    public String hc;
    @Column(name = "ac")
    public String ac;
    @Column(name = "fl")
    public String fl;
    @Column(name = "ci")
    public String ci;
    @Column(name = "flBpd")
    public String flBpd;
    @Column(name = "flHc")
    public String flHc;
    @Column(name = "flAc")
    public String flAc;
    @Column(name = "hcAc")
    public String hcAc;
    @Column(name = "gestationAge")
    public String gestationAge;
    @Column(name = "fetalWeight")
    public String fetalWeight;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "edd")
    public Date edd;
    @Column(name = "fetalVenticles")
    public String fetalVenticles;
    @Column(name = "fetalCerebellum")
    public String fetalCerebellum;
    @Column(name = "fetalCranium")
    public String fetalCranium;
    @Column(name = "fetalFace")
    public String fetalFace;
    @Column(name = "fetalHeart")
    public String fetalHeart;
    @Column(name = "fetalDiaphragm")
    public String fetalDiaphragm;
    @Column(name = "fetalStomach")
    public String fetalStomach;
    @Column(name = "fetalCord")
    public String fetalCord;
    @Column(name = "fetalAbdominal")
    public String fetalAbdominal;
    @Column(name = "fetalSpine")
    public String fetalSpine;
    @Column(name = "fetalKidneys")
    public String fetalKidneys;
    @Column(name = "fetalBladder")
    public String fetalBladder;
    @Column(name = "fetalLimbs")
    public String fetalLimbs;


    public Date getEdd() {
        return edd;
    }

    public void setEdd(Date edd) {
        this.edd = edd;
    }
    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAdnexa() {
        return adnexa;
    }

    public void setAdnexa(String adnexa) {
        this.adnexa = adnexa;
    }

    public String getAmnioticFluid() {
        return amnioticFluid;
    }

    public void setAmnioticFluid(String amnioticFluid) {
        this.amnioticFluid = amnioticFluid;
    }

    public String getBpd() {
        return bpd;
    }

    public void setBpd(String bpd) {
        this.bpd = bpd;
    }

    public String getCervix() {
        return cervix;
    }

    public void setCervix(String cervix) {
        this.cervix = cervix;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getFetalAbdominal() {
        return fetalAbdominal;
    }

    public void setFetalAbdominal(String fetalAbdominal) {
        this.fetalAbdominal = fetalAbdominal;
    }

    public String getFetalBladder() {
        return fetalBladder;
    }

    public void setFetalBladder(String fetalBladder) {
        this.fetalBladder = fetalBladder;
    }

    public String getFetalCardiac() {
        return fetalCardiac;
    }

    public void setFetalCardiac(String fetalCardiac) {
        this.fetalCardiac = fetalCardiac;
    }

    public String getFetalCerebellum() {
        return fetalCerebellum;
    }

    public void setFetalCerebellum(String fetalCerebellum) {
        this.fetalCerebellum = fetalCerebellum;
    }

    public String getFetalCord() {
        return fetalCord;
    }

    public void setFetalCord(String fetalCord) {
        this.fetalCord = fetalCord;
    }

    public String getFetalCranium() {
        return fetalCranium;
    }

    public void setFetalCranium(String fetalCranium) {
        this.fetalCranium = fetalCranium;
    }

    public String getFetalDiaphragm() {
        return fetalDiaphragm;
    }

    public void setFetalDiaphragm(String fetalDiaphragm) {
        this.fetalDiaphragm = fetalDiaphragm;
    }

    public String getFetalFace() {
        return fetalFace;
    }

    public void setFetalFace(String fetalFace) {
        this.fetalFace = fetalFace;
    }

    public String getFetalHeart() {
        return fetalHeart;
    }

    public void setFetalHeart(String fetalHeart) {
        this.fetalHeart = fetalHeart;
    }

    public String getFetalKidneys() {
        return fetalKidneys;
    }

    public void setFetalKidneys(String fetalKidneys) {
        this.fetalKidneys = fetalKidneys;
    }

    public String getFetalLie() {
        return fetalLie;
    }

    public void setFetalLie(String fetalLie) {
        this.fetalLie = fetalLie;
    }

    public String getFetalLimbs() {
        return fetalLimbs;
    }

    public void setFetalLimbs(String fetalLimbs) {
        this.fetalLimbs = fetalLimbs;
    }

    public String getFetalPresentation() {
        return fetalPresentation;
    }

    public void setFetalPresentation(String fetalPresentation) {
        this.fetalPresentation = fetalPresentation;
    }

    public String getFetalSpine() {
        return fetalSpine;
    }

    public void setFetalSpine(String fetalSpine) {
        this.fetalSpine = fetalSpine;
    }

    public String getFetalStomach() {
        return fetalStomach;
    }

    public void setFetalStomach(String fetalStomach) {
        this.fetalStomach = fetalStomach;
    }

    public String getFetalVenticles() {
        return fetalVenticles;
    }

    public void setFetalVenticles(String fetalVenticles) {
        this.fetalVenticles = fetalVenticles;
    }

    public String getFetalWeight() {
        return fetalWeight;
    }

    public void setFetalWeight(String fetalWeight) {
        this.fetalWeight = fetalWeight;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getFlAc() {
        return flAc;
    }

    public void setFlAc(String flAc) {
        this.flAc = flAc;
    }

    public String getFlBpd() {
        return flBpd;
    }

    public void setFlBpd(String flBpd) {
        this.flBpd = flBpd;
    }

    public String getFlHc() {
        return flHc;
    }

    public void setFlHc(String flHc) {
        this.flHc = flHc;
    }

    public String getGestationAge() {
        return gestationAge;
    }

    public void setGestationAge(String gestationAge) {
        this.gestationAge = gestationAge;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getHcAc() {
        return hcAc;
    }

    public void setHcAc(String hcAc) {
        this.hcAc = hcAc;
    }

    public String getPlacenta() {
        return placenta;
    }

    public void setPlacenta(String placenta) {
        this.placenta = placenta;
    }

    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }
}
