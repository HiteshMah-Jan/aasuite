/*
 * PersonEducation.java
 * 
 * Created on Nov 18, 2007, 8:26:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonRequirement")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"personId","applyingPosition"})
@Displays({
//        @Display(name="personId"),
        @Display(name="personId", type ="PopSearch",label="Name" ,gridFieldWidth=3, width=-1),
        @Display(name="applyingForPosition",gridFieldWidth=3, width=-1),
        @Display(name="degree",gridFieldWidth=3, width=-1),
        @Display(name="majorOrMinor",gridFieldWidth=3, width=-1),
        @Display(name="schoolGraduated",gridFieldWidth=3, width=-1),
        @Display(name="presentEmployer",gridFieldWidth=3, width=-1),
        @Display(name="employerAddress",gridFieldWidth=3, width=-1),
        @Display(name="employerTelephone",gridFieldWidth=3, width=-1),
        
        @Display(name="personalDataSheet"),
        @Display(name="employerTelephoneSubmittedDate", label ="  "),
        @Display(name="lETCertificateOrLicense"),
        @Display(name="lETCertificateOrLicenseSubmittedDate", label ="  "),
        @Display(name="tOR"),
        @Display(name="tORSubmittedDate", label ="  "),
        @Display(name="collegeDiploma"),
        @Display(name="collegeDiplomaSubmittedDate", label ="  "),
        @Display(name="graduateStudies"),
        @Display(name="graduateStudiesSubmittedDate", label ="  "),
        @Display(name="birthCertificate"),
        @Display(name="birthCertificateSubmittedDate", label ="  "),
        @Display(name="baptismalCertificate"),
        @Display(name="baptismalCertificateSubmittedDate", label ="  "),
        @Display(name="confirmationCertificate"),
        @Display(name="confirmationCertificateSubmittedDate", label ="  "),
        @Display(name="marriageLicense"),
        @Display(name="marriageLicenseSubmittedDate", label ="  "),
        @Display(name="nBIClearance"),
        @Display(name="nBIClearanceSubmittedDate", label ="  "),
        @Display(name="barangayClearance"),
        @Display(name="barangayClearanceSubmittedDate", label ="  "),
        @Display(name="medicalCertificate"),
        @Display(name="medicalCertificateSubmittedDate", label ="  "),
        @Display(name="physicalExam"),
        @Display(name="physicalExamSubmittedDate", label ="  "),
        @Display(name="urinalysisExam"),
        @Display(name="urinalysisExamSubmittedDate", label ="  "),
        @Display(name="fecalysisExam"),
        @Display(name="fecalysisExamSubmittedDate", label ="  "),
        @Display(name="cBCExam"),
        @Display(name="cBCExamSubmittedDate", label ="  "),
        @Display(name="xRay"),
        @Display(name="xRaySubmittedDate", label ="  "),
        @Display(name="neuroPsychiatricClearance"),
        @Display(name="neuroPsychiatricClearanceSubmittedDate", label ="  "),
        @Display(name="drugTest"),
        @Display(name="drugTestSubmittedDate", label ="  "),
        @Display(name="tin"),
        @Display(name="tinSubmittedDate", label ="  "),
        @Display(name="sSS"),
        @Display(name="sSSSubmittedDate", label ="  "),
        @Display(name="pagibig"),
        @Display(name="pagibigSubmittedDate", label ="  "),
        @Display(name="philHealth"),
        @Display(name="philHealthSubmittedDate", label ="  "),
        @Display(name="rRC"),
        @Display(name="rRCSubmittedDate", label ="  "),
        @Display(name="verifiedBy"),
        @Display(name="verifiedDate", label ="  "),
        @Display(name="notedBy", type = "PopSearch"),
        @Display(name="notedDate", label ="  "),
        @Display(name="completeRequirements")
})
@DisplayGroups({
    @DisplayGroup(title="Requirements to the HRD Office", fields={"personalDataSheet","employerTelephoneSubmittedDate",
    "lETCertificateOrLicense","lETCertificateOrLicenseSubmittedDate","tOR","tORSubmittedDate","collegeDiploma",
    "collegeDiplomaSubmittedDate","graduateStudies","graduateStudiesSubmittedDate","birthCertificate",
    "birthCertificateSubmittedDate","baptismalCertificate","baptismalCertificateSubmittedDate","confirmationCertificate",
    "confirmationCertificateSubmittedDate","marriageLicense","marriageLicenseSubmittedDate","nBIClearance","nBIClearanceSubmittedDate",
    "barangayClearance","barangayClearanceSubmittedDate","medicalCertificate","medicalCertificateSubmittedDate",
    "physicalExam","physicalExamSubmittedDate","urinalysisExam","urinalysisExamSubmittedDate","fecalysisExam",
    "fecalysisExamSubmittedDate","cBCExam","cBCExamSubmittedDate","xRay","xRaySubmittedDate","neuroPsychiatricClearance",
    "neuroPsychiatricClearanceSubmittedDate","drugTest","drugTestSubmittedDate","tin","tinSubmittedDate","sSS","sSSSubmittedDate",
    "pagibig","pagibigSubmittedDate","philHealth","philHealthSubmittedDate","rRC","rRCSubmittedDate"})
})

public class PersonRequirements extends PersonAttribute implements Serializable {
    public static void main(String[] args) {
        view(PersonRequirements.class);
    }
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date date;    
    @Column(name = "personId")
    public int personId;
    @Column(name = "applyingForPosition")
    public String applyingForPosition ;
    @Column(name = "degree")
    public String degree;
    @Column(name = "majorAndMinor")
    public String majorAndMinor;
    @Column(name = "schoolGraduated")
    public String schoolGraduated;
    @Column(name = "presentEmployer")
    public String presentEmployer;
    @Column(name = "employerAddress")
    public String employerAddress;
    @Column(name = "employerTelephone")
    public String employerTelephone;
    @Column(name = "personalDataSheet")
    public boolean personalDataSheet;
    @Column(name = "personalDataSheetSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date personalDataSheetSubmittedDate;    
    @Column(name = "lETCertificateOrLicense")
    public boolean lETCertificateOrLicense;
    @Column(name = "lETCertificateOrLicenseSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date lETCertificateOrLicenseSubmittedDate;    
    @Column(name = "tOR")
    public boolean tOR;
    @Column(name = "tORSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date tORSubmittedDate;    
    @Column(name = "collegeDiploma")
    public boolean collegeDiploma;
    @Column(name = "collegeDiplomaSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date collegeDiplomaSubmittedDate;    
    @Column(name = "graduateStudies")
    public boolean graduateStudies;
    @Column(name = "graduateStudiesSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date graduateStudiesSubmittedDate;    
    @Column(name = "birthCertificate")
    public boolean birthCertificate;
    @Column(name = "birthCertificateSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date birthCertificateSubmittedDate;    
    @Column(name = "baptismalCertificate")
    public boolean baptismalCertificate;
    @Column(name = "baptismalCertificateSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date baptismalCertificateSubmittedDate;    
    @Column(name = "confirmationCertificate")
    public boolean confirmationCertificate;
    @Column(name = "confirmationCertificateSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date confirmationCertificateSubmittedDate;    
    @Column(name = "marriageLicense")
    public boolean marriageLicense;
    @Column(name = "marriageLicenseSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date marriageLicenseSubmittedDate;    
    @Column(name = "nBIClearance")
    public boolean nBIClearance;
    @Column(name = "nBIClearanceSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date nBIClearanceSubmittedDate;    
    @Column(name = "barangayClearance")
    public boolean barangayClearance;
    @Column(name = "barangayClearanceSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date barangayClearanceSubmittedDate;    
    @Column(name = "medicalCertificate")
    public boolean medicalCertificate;
    @Column(name = "medicalCertificateSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date medicalCertificateSubmittedDate;    
    @Column(name = "physicalExam")
    public boolean physicalExam;
    @Column(name = "physicalExamSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date physicalExamSubmittedDate;    
    @Column(name = "urinalysisExam")
    public boolean urinalysisExam;
    @Column(name = "urinalysisExamSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date urinalysisExamSubmittedDate;    
    @Column(name = "fecalysisExam")
    public boolean fecalysisExam;
    @Column(name = "fecalysisExamSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date fecalysisExamSubmittedDate;    
    @Column(name = "cBCExam")
    public boolean cBCExam;
    @Column(name = "cBCExamSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date cBCExamSubmittedDate;    
    @Column(name = "xRay")
    public boolean xRay;
    @Column(name = "xRaySubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date xRaySubmittedDate;    
    @Column(name = "neuroPsychiatricClearance")
    public boolean neuroPsychiatricClearance;
    @Column(name = "neuroPsychiatricClearanceSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date neuroPsychiatricClearanceSubmittedDate;    
    @Column(name = "drugTest")
    public boolean drugTest;
    @Column(name = "drugTestSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date drugTestSubmittedDate;    
    @Column(name = "tin")
    public boolean tin;
    @Column(name = "tinSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date tinSubmittedDate;    
    @Column(name = "sSS")
    public boolean sSS;
    @Column(name = "sSSSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date sSSSubmittedDate;    
    @Column(name = "pagibig")
    public boolean pagibig;
    @Column(name = "pagibigSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date pagibigSubmittedDate;    
    @Column(name = "philHealth")
    public boolean philHealth;
    @Column(name = "philHealthSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date philHealthSubmittedDate;    
    @Column(name = "rRC")
    public boolean rRC;
    @Column(name = "rRCSubmittedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date rRCSubmittedDate;    
    @Column(name = "completeRequirements")
    public boolean completeRequirements;
    @Column(name = "verifiedBy")
    public int verifiedBy;
    @Column(name = "verifiedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date verifiedDate;    
    @Column(name = "notedBy")
    public int notedBy;
    @Column(name = "notedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date notedDate;

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getEmployerTelephone() {
        return employerTelephone;
    }

    public void setEmployerTelephone(String employerTelephone) {
        this.employerTelephone = employerTelephone;
    }

    public String getPresentEmployer() {
        return presentEmployer;
    }

    public void setPresentEmployer(String presentEmployer) {
        this.presentEmployer = presentEmployer;
    }

    
    
    public String getApplyingForPosition() {
        return applyingForPosition;
    }

    public void setApplyingForPosition(String applyingForPosition) {
        this.applyingForPosition = applyingForPosition;
    }

    public boolean isBaptismalCertificate() {
        return baptismalCertificate;
    }

    public void setBaptismalCertificate(boolean baptismalCertificate) {
        this.baptismalCertificate = baptismalCertificate;
    }

    public Date getBaptismalCertificateSubmittedDate() {
        return baptismalCertificateSubmittedDate;
    }

    public void setBaptismalCertificateSubmittedDate(Date baptismalCertificateSubmittedDate) {
        this.baptismalCertificateSubmittedDate = baptismalCertificateSubmittedDate;
    }

    public boolean isBarangayClearance() {
        return barangayClearance;
    }

    public void setBarangayClearance(boolean barangayClearance) {
        this.barangayClearance = barangayClearance;
    }

    public Date getBarangayClearanceSubmittedDate() {
        return barangayClearanceSubmittedDate;
    }

    public void setBarangayClearanceSubmittedDate(Date barangayClearanceSubmittedDate) {
        this.barangayClearanceSubmittedDate = barangayClearanceSubmittedDate;
    }

    public boolean isBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(boolean birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public Date getBirthCertificateSubmittedDate() {
        return birthCertificateSubmittedDate;
    }

    public void setBirthCertificateSubmittedDate(Date birthCertificateSubmittedDate) {
        this.birthCertificateSubmittedDate = birthCertificateSubmittedDate;
    }

    public boolean isCBCExam() {
        return cBCExam;
    }

    public void setCBCExam(boolean cBCExam) {
        this.cBCExam = cBCExam;
    }

    public Date getCBCExamSubmittedDate() {
        return cBCExamSubmittedDate;
    }

    public void setCBCExamSubmittedDate(Date cBCExamSubmittedDate) {
        this.cBCExamSubmittedDate = cBCExamSubmittedDate;
    }

    public boolean isCollegeDiploma() {
        return collegeDiploma;
    }

    public void setCollegeDiploma(boolean collegeDiploma) {
        this.collegeDiploma = collegeDiploma;
    }

    public Date getCollegeDiplomaSubmittedDate() {
        return collegeDiplomaSubmittedDate;
    }

    public void setCollegeDiplomaSubmittedDate(Date collegeDiplomaSubmittedDate) {
        this.collegeDiplomaSubmittedDate = collegeDiplomaSubmittedDate;
    }

    public boolean isCompleteRequirements() {
        return completeRequirements;
    }

    public void setCompleteRequirements(boolean completeRequirements) {
        this.completeRequirements = completeRequirements;
    }

    public boolean isConfirmationCertificate() {
        return confirmationCertificate;
    }

    public void setConfirmationCertificate(boolean confirmationCertificate) {
        this.confirmationCertificate = confirmationCertificate;
    }

    public Date getConfirmationCertificateSubmittedDate() {
        return confirmationCertificateSubmittedDate;
    }

    public void setConfirmationCertificateSubmittedDate(Date confirmationCertificateSubmittedDate) {
        this.confirmationCertificateSubmittedDate = confirmationCertificateSubmittedDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isDrugTest() {
        return drugTest;
    }

    public void setDrugTest(boolean drugTest) {
        this.drugTest = drugTest;
    }

    public Date getDrugTestSubmittedDate() {
        return drugTestSubmittedDate;
    }

    public void setDrugTestSubmittedDate(Date drugTestSubmittedDate) {
        this.drugTestSubmittedDate = drugTestSubmittedDate;
    }

    public boolean isFecalysisExam() {
        return fecalysisExam;
    }

    public void setFecalysisExam(boolean fecalysisExam) {
        this.fecalysisExam = fecalysisExam;
    }

    public Date getFecalysisExamSubmittedDate() {
        return fecalysisExamSubmittedDate;
    }

    public void setFecalysisExamSubmittedDate(Date fecalysisExamSubmittedDate) {
        this.fecalysisExamSubmittedDate = fecalysisExamSubmittedDate;
    }

    public boolean isGraduateStudies() {
        return graduateStudies;
    }

    public void setGraduateStudies(boolean graduateStudies) {
        this.graduateStudies = graduateStudies;
    }

    public Date getGraduateStudiesSubmittedDate() {
        return graduateStudiesSubmittedDate;
    }

    public void setGraduateStudiesSubmittedDate(Date graduateStudiesSubmittedDate) {
        this.graduateStudiesSubmittedDate = graduateStudiesSubmittedDate;
    }

    public boolean isLETCertificateOrLicense() {
        return lETCertificateOrLicense;
    }

    public void setLETCertificateOrLicense(boolean lETCertificateOrLicense) {
        this.lETCertificateOrLicense = lETCertificateOrLicense;
    }

    public Date getLETCertificateOrLicenseSubmittedDate() {
        return lETCertificateOrLicenseSubmittedDate;
    }

    public void setLETCertificateOrLicenseSubmittedDate(Date lETCertificateOrLicenseSubmittedDate) {
        this.lETCertificateOrLicenseSubmittedDate = lETCertificateOrLicenseSubmittedDate;
    }

    public String getMajorAndMinor() {
        return majorAndMinor;
    }

    public void setMajorAndMinor(String majorAndMinor) {
        this.majorAndMinor = majorAndMinor;
    }

    public boolean isMarriageLicense() {
        return marriageLicense;
    }

    public void setMarriageLicense(boolean marriageLicense) {
        this.marriageLicense = marriageLicense;
    }

    public Date getMarriageLicenseSubmittedDate() {
        return marriageLicenseSubmittedDate;
    }

    public void setMarriageLicenseSubmittedDate(Date marriageLicenseSubmittedDate) {
        this.marriageLicenseSubmittedDate = marriageLicenseSubmittedDate;
    }

    public boolean isMedicalCertificate() {
        return medicalCertificate;
    }

    public void setMedicalCertificate(boolean medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
    }

    public Date getMedicalCertificateSubmittedDate() {
        return medicalCertificateSubmittedDate;
    }

    public void setMedicalCertificateSubmittedDate(Date medicalCertificateSubmittedDate) {
        this.medicalCertificateSubmittedDate = medicalCertificateSubmittedDate;
    }

    public boolean isNBIClearance() {
        return nBIClearance;
    }

    public void setNBIClearance(boolean nBIClearance) {
        this.nBIClearance = nBIClearance;
    }

    public Date getNBIClearanceSubmittedDate() {
        return nBIClearanceSubmittedDate;
    }

    public void setNBIClearanceSubmittedDate(Date nBIClearanceSubmittedDate) {
        this.nBIClearanceSubmittedDate = nBIClearanceSubmittedDate;
    }

    public boolean isNeuroPsychiatricClearance() {
        return neuroPsychiatricClearance;
    }

    public void setNeuroPsychiatricClearance(boolean neuroPsychiatricClearance) {
        this.neuroPsychiatricClearance = neuroPsychiatricClearance;
    }

    public Date getNeuroPsychiatricClearanceSubmittedDate() {
        return neuroPsychiatricClearanceSubmittedDate;
    }

    public void setNeuroPsychiatricClearanceSubmittedDate(Date neuroPsychiatricClearanceSubmittedDate) {
        this.neuroPsychiatricClearanceSubmittedDate = neuroPsychiatricClearanceSubmittedDate;
    }

    public int getNotedBy() {
        return notedBy;
    }

    public void setNotedBy(int notedBy) {
        this.notedBy = notedBy;
    }

    public Date getNotedDate() {
        return notedDate;
    }

    public void setNotedDate(Date notedDate) {
        this.notedDate = notedDate;
    }

    public boolean isPersonalDataSheet() {
        return personalDataSheet;
    }

    public void setPersonalDataSheet(boolean personalDataSheet) {
        this.personalDataSheet = personalDataSheet;
    }

    public Date getPersonalDataSheetSubmittedDate() {
        return personalDataSheetSubmittedDate;
    }

    public void setPersonalDataSheetSubmittedDate(Date personalDataSheetSubmittedDate) {
        this.personalDataSheetSubmittedDate = personalDataSheetSubmittedDate;
    }

    public boolean isPhilHealth() {
        return philHealth;
    }

    public void setPhilHealth(boolean philHealth) {
        this.philHealth = philHealth;
    }

    public Date getPhilHealthSubmittedDate() {
        return philHealthSubmittedDate;
    }

    public void setPhilHealthSubmittedDate(Date philHealthSubmittedDate) {
        this.philHealthSubmittedDate = philHealthSubmittedDate;
    }

    public boolean isPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(boolean physicalExam) {
        this.physicalExam = physicalExam;
    }

    public Date getPhysicalExamSubmittedDate() {
        return physicalExamSubmittedDate;
    }

    public void setPhysicalExamSubmittedDate(Date physicalExamSubmittedDate) {
        this.physicalExamSubmittedDate = physicalExamSubmittedDate;
    }

    public String getSchoolGraduated() {
        return schoolGraduated;
    }

    public void setSchoolGraduated(String schoolGraduated) {
        this.schoolGraduated = schoolGraduated;
    }
  public boolean isTOR() {
        return tOR;
    }

    public void setTOR(boolean tOR) {
        this.tOR = tOR;
    }

    public Date getTORSubmittedDate() {
        return tORSubmittedDate;
    }

    public void setTORSubmittedDate(Date tORSubmittedDate) {
        this.tORSubmittedDate = tORSubmittedDate;
    }

    public boolean isUrinalysisExam() {
        return urinalysisExam;
    }

    public void setUrinalysisExam(boolean urinalysisExam) {
        this.urinalysisExam = urinalysisExam;
    }

    public Date getUrinalysisExamSubmittedDate() {
        return urinalysisExamSubmittedDate;
    }

    public void setUrinalysisExamSubmittedDate(Date urinalysisExamSubmittedDate) {
        this.urinalysisExamSubmittedDate = urinalysisExamSubmittedDate;
    }

    public int getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(int verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public boolean isXRay() {
        return xRay;
    }

    public void setXRay(boolean xRay) {
        this.xRay = xRay;
    }

    public Date getXRaySubmittedDate() {
        return xRaySubmittedDate;
    }

    public void setXRaySubmittedDate(Date xRaySubmittedDate) {
        this.xRaySubmittedDate = xRaySubmittedDate;
    }

    public boolean isPagibig() {
        return pagibig;
    }

    public void setPagibig(boolean pagibig) {
        this.pagibig = pagibig;
    }

    public Date getPagibigSubmittedDate() {
        return pagibigSubmittedDate;
    }

    public void setPagibigSubmittedDate(Date pagibigSubmittedDate) {
        this.pagibigSubmittedDate = pagibigSubmittedDate;
    }

    public boolean isRRC() {
        return rRC;
    }

    public void setRRC(boolean rRC) {
        this.rRC = rRC;
    }

    public Date getRRCSubmittedDate() {
        return rRCSubmittedDate;
    }

    public void setRRCSubmittedDate(Date rRCSubmittedDate) {
        this.rRCSubmittedDate = rRCSubmittedDate;
    }

    public boolean isSSS() {
        return sSS;
    }

    public void setSSS(boolean sSS) {
        this.sSS = sSS;
    }

    public Date getSSSSubmittedDate() {
        return sSSSubmittedDate;
    }

    public void setSSSSubmittedDate(Date sSSSubmittedDate) {
        this.sSSSubmittedDate = sSSSubmittedDate;
    }

    public boolean isTin() {
        return tin;
    }

    public void setTin(boolean tin) {
        this.tin = tin;
    }

    public Date getTinSubmittedDate() {
        return tinSubmittedDate;
    }

    public void setTinSubmittedDate(Date tinSubmittedDate) {
        this.tinSubmittedDate = tinSubmittedDate;
    }

    @Override
    public int getPersonId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getSeq() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
