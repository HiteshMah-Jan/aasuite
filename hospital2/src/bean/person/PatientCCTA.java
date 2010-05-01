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
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientLaboratory")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"calciumScore", "qualityAssessment"})
@DiscriminatorValue(value = "CCTA")
@Displays({
        @Display(name="calciumScore"),
        @Display(name="qualityAssessment"),
        @Display(name="leftMainCoronaryArtery"),
        @Display(name="leftAnteriorDescendingCoronayArtery"),
        @Display(name="leftCircumflexCoronaryArtery"),
        @Display(name="rightCoronaryArtery"),
        @Display(name="functionalAnalysisEndDiastolicVolume"),
        @Display(name="functionalAnalysisEndSystolicVolume"),
        @Display(name="functionalAnalysisEjectionFraction"),
        @Display(name="anatomicAnalysis"),
        @Display(name="ventricularFunction"),
        @Display(name="lm"),
        @Display(name="lad"),
        @Display(name="cx"),
        @Display(name="rca"),
        @Display(name="coronaryArterieDominance"),
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
public class PatientCCTA extends PatientRadiology implements Serializable {

    @Column(name = "calciumScore")
    public String calciumScore;
    @Column(name = "qualityAssessment")
    public String qualityAssessment;
    @Column(name = "leftMainCoronaryArtery")
    public String leftMainCoronaryArtery;
    @Column(name = "leftAnteriorDescendingCoronayArtery")
    public String leftAnteriorDescendingCoronayArtery;
    @Column(name = "leftCircumflexCoronaryArtery")
    public String leftCircumflexCoronaryArtery;
    @Column(name = "rightCoronaryArtery")
    public String rightCoronaryArtery;
    @Column(name = "functionalAnalysisEndDiastolicVolume")
    public String functionalAnalysisEndDiastolicVolume;
    @Column(name = "functionalAnalysisEndSystolicVolume")
    public String functionalAnalysisEndSystolicVolume;
    @Column(name = "functionalAnalysisEjectionFraction")
    public String functionalAnalysisEjectionFraction;
    @Column(name = "anatomicAnalysis")
    public String anatomicAnalysis;
    @Column(name = "ventricularFunction")
    public String ventricularFunction;
    @Column(name = "lm")
    public String lm;
    @Column(name = "lad")
    public String lad;
    @Column(name = "cx")
    public String cx;
    @Column(name = "rca")
    public String rca;
    @Column(name = "coronaryArterieDominance")
    public String coronaryArterieDominance;

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getAnatomicAnalysis() {
        return anatomicAnalysis;
    }

    public void setAnatomicAnalysis(String anatomicAnalysis) {
        this.anatomicAnalysis = anatomicAnalysis;
    }

    public String getCalciumScore() {
        return calciumScore;
    }

    public void setCalciumScore(String calciumScore) {
        this.calciumScore = calciumScore;
    }

    public String getCoronaryArterieDominance() {
        return coronaryArterieDominance;
    }

    public void setCoronaryArterieDominance(String coronaryArterieDominance) {
        this.coronaryArterieDominance = coronaryArterieDominance;
    }

    public String getFunctionalAnalysisEjectionFraction() {
        return functionalAnalysisEjectionFraction;
    }

    public void setFunctionalAnalysisEjectionFraction(String functionalAnalysisEjectionFraction) {
        this.functionalAnalysisEjectionFraction = functionalAnalysisEjectionFraction;
    }

    public String getFunctionalAnalysisEndDiastolicVolume() {
        return functionalAnalysisEndDiastolicVolume;
    }

    public void setFunctionalAnalysisEndDiastolicVolume(String functionalAnalysisEndDiastolicVolume) {
        this.functionalAnalysisEndDiastolicVolume = functionalAnalysisEndDiastolicVolume;
    }

    public String getFunctionalAnalysisEndSystolicVolume() {
        return functionalAnalysisEndSystolicVolume;
    }

    public void setFunctionalAnalysisEndSystolicVolume(String functionalAnalysisEndSystolicVolume) {
        this.functionalAnalysisEndSystolicVolume = functionalAnalysisEndSystolicVolume;
    }

    public String getLad() {
        return lad;
    }

    public void setLad(String lad) {
        this.lad = lad;
    }

    public String getLeftAnteriorDescendingCoronayArtery() {
        return leftAnteriorDescendingCoronayArtery;
    }

    public void setLeftAnteriorDescendingCoronayArtery(String leftAnteriorDescendingCoronayArtery) {
        this.leftAnteriorDescendingCoronayArtery = leftAnteriorDescendingCoronayArtery;
    }

    public String getLeftCircumflexCoronaryArtery() {
        return leftCircumflexCoronaryArtery;
    }

    public void setLeftCircumflexCoronaryArtery(String leftCircumflexCoronaryArtery) {
        this.leftCircumflexCoronaryArtery = leftCircumflexCoronaryArtery;
    }

    public String getLeftMainCoronaryArtery() {
        return leftMainCoronaryArtery;
    }

    public void setLeftMainCoronaryArtery(String leftMainCoronaryArtery) {
        this.leftMainCoronaryArtery = leftMainCoronaryArtery;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getQualityAssessment() {
        return qualityAssessment;
    }

    public void setQualityAssessment(String qualityAssessment) {
        this.qualityAssessment = qualityAssessment;
    }

    public String getRca() {
        return rca;
    }

    public void setRca(String rca) {
        this.rca = rca;
    }

    public String getRightCoronaryArtery() {
        return rightCoronaryArtery;
    }

    public void setRightCoronaryArtery(String rightCoronaryArtery) {
        this.rightCoronaryArtery = rightCoronaryArtery;
    }

    public String getVentricularFunction() {
        return ventricularFunction;
    }

    public void setVentricularFunction(String ventricularFunction) {
        this.ventricularFunction = ventricularFunction;
    }
}
