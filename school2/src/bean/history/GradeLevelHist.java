/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.history;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.*;
import bean.reference.Course;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "GradeLevelHist")
@UITemplate(columnSearch={"schoolYear", "code", "course"}, gridCount=4, title="Level")
@Displays({
        @Display(name="schoolYear"),
        @Display(name="code"),
        @Display(name="course", type="PopSearch", linktoBean= Course.class),
        @Display(name="tuitionFee"),
        @Display(name="otherFee"),

        @Display(name = "miscFee"),
        @Display(name = "registrationFee", addInfoOnly=true),
        @Display(name = "materialsFee", addInfoOnly=true),
        @Display(name = "idFee", addInfoOnly=true),
        @Display(name = "medicalDentalFee", addInfoOnly=true),
        @Display(name = "libraryFee", addInfoOnly=true),
        @Display(name = "laboratoryFee", addInfoOnly=true),
        @Display(name = "audioVisualFee", addInfoOnly=true),
        @Display(name = "athleticFee", addInfoOnly=true),
        @Display(name = "insuranceFee", addInfoOnly=true),
        @Display(name = "computerFee", addInfoOnly=true),
        @Display(name = "handbookFee", addInfoOnly=true),
        @Display(name = "diplomaFee", addInfoOnly=true),
        @Display(name = "annualFee", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(title="Misc. Breakdown",
        fields={"registrationFee","materialsFee","idFee","medicalDentalFee","libraryFee","laboratoryFee",
            "audioVisualFee","athleticFee","insuranceFee","computerFee","handbookFee","diplomaFee","annualFee"})
})
public class GradeLevelHist extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public String seq;
    @Column(name = "schoolYear", nullable = false)
    public String schoolYear;
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "course", nullable = false)
    public String course;
    @Column(name = "tuitionFee")
    public double tuitionFee;
    @Column(name = "miscFee")
    public double miscFee;
    @Column(name = "otherFee")
    public double otherFee;
    @Column(name = "registrationFee")
    public double registrationFee;
    @Column(name = "materialsFee")
    public double materialsFee;
    @Column(name = "idFee")
    public double idFee;
    @Column(name = "medicalDentalFee")
    public double medicalDentalFee;
    @Column(name = "libraryFee")
    public double libraryFee;
    @Column(name = "laboratoryFee")
    public double laboratoryFee;
    @Column(name = "audioVisualFee")
    public double audioVisualFee;
    @Column(name = "athleticFee")
    public double athleticFee;
    @Column(name = "insuranceFee")
    public double insuranceFee;
    @Column(name = "computerFee")
    public double computerFee;
    @Column(name = "handbookFee")
    public double handbookFee;
    @Column(name = "diplomaFee")
    public double diplomaFee;
    @Column(name = "annualFee")
    public double annualFee;

    @Column(name = "elaItemCount")
    public int elaItemCount;
    @Column(name = "mathItemCount")
    public int mathItemCount;
    @Column(name = "sciItemCount")
    public int sciItemCount;
    @Column(name = "pracItemCount")
    public int pracItemCount;
    @Column(name = "otherItemCount1")
    public int otherItemCount1;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","course");
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public int getElaItemCount() {
        return elaItemCount;
    }

    public void setElaItemCount(int elaItemCount) {
        this.elaItemCount = elaItemCount;
    }

    public int getMathItemCount() {
        return mathItemCount;
    }

    public void setMathItemCount(int mathItemCount) {
        this.mathItemCount = mathItemCount;
    }

    public int getPracItemCount() {
        return pracItemCount;
    }

    public void setPracItemCount(int pracItemCount) {
        this.pracItemCount = pracItemCount;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getOtherItemCount1() {
        return otherItemCount1;
    }

    public void setOtherItemCount1(int otherItemCount1) {
        this.otherItemCount1 = otherItemCount1;
    }

    public int getSciItemCount() {
        return sciItemCount;
    }

    public void setSciItemCount(int sciItemCount) {
        this.sciItemCount = sciItemCount;
    }

    public double getComputedMiscFee() {
        miscFee=registrationFee+materialsFee+idFee+medicalDentalFee+libraryFee+laboratoryFee+
                audioVisualFee+athleticFee+insuranceFee+computerFee+handbookFee+diplomaFee+annualFee;
        return miscFee;
    }
    public double getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public double getAthleticFee() {
        return athleticFee;
    }

    public void setAthleticFee(double athleticFee) {
        this.athleticFee = athleticFee;
    }

    public double getAudioVisualFee() {
        return audioVisualFee;
    }

    public void setAudioVisualFee(double audioVisualFee) {
        this.audioVisualFee = audioVisualFee;
    }

    public double getComputerFee() {
        return computerFee;
    }

    public void setComputerFee(double computerFee) {
        this.computerFee = computerFee;
    }

    public double getDiplomaFee() {
        return diplomaFee;
    }

    public void setDiplomaFee(double diplomaFee) {
        this.diplomaFee = diplomaFee;
    }

    public double getHandbookFee() {
        return handbookFee;
    }

    public void setHandbookFee(double handbookFee) {
        this.handbookFee = handbookFee;
    }

    public double getIdFee() {
        return idFee;
    }

    public void setIdFee(double idFee) {
        this.idFee = idFee;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public double getLaboratoryFee() {
        return laboratoryFee;
    }

    public void setLaboratoryFee(double laboratoryFee) {
        this.laboratoryFee = laboratoryFee;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getMaterialsFee() {
        return materialsFee;
    }

    public void setMaterialsFee(double materialsFee) {
        this.materialsFee = materialsFee;
    }

    public double getMedicalDentalFee() {
        return medicalDentalFee;
    }

    public void setMedicalDentalFee(double medicalDentalFee) {
        this.medicalDentalFee = medicalDentalFee;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMiscFee() {
        return miscFee;
    }

    public void setMiscFee(double miscFee) {
        this.miscFee = miscFee;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return course+"-"+code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected void runSetup() {
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
