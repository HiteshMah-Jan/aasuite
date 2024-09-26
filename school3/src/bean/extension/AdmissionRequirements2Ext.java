/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Admission;
import javax.persistence.Column;
import javax.persistence.Id;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */

@UITemplate(template=template.screen.TemplateTabPage.class,showChart=true,criteriaSearch={"lastName","firstName"},columnSearch={"lastName", "firstName", "middleInitial", "examinationDate", "remarks"}, gridCount=2)
@Displays({
//        @Display(name="threeColoredIdPictures"),
//        @Display(name="birthCertificateNsoCopy",label="Birth Certificate (NSO Copy)"),
//        @Display(name="baptismalCertificate"),
//        @Display(name="latestReportCard",label="Latest Report Card (3rd Quarter)"),
//        @Display(name="letterOfRecommendation",label="Letter of Recommnedation from the principal / Guidance Counselor")
        @Display(name="copyOfAcr",label="Copy of ACR"),
        @Display(name="passportForVerification"),
        @Display(name="completeScholasticRecords",label="Complete Scholastic Records                                                     ")
                                                                                                                                        //

//        @Display(name="admissionId"),
//        @Display(name="invoiceId"),
//        @Display(name="birthDate"),
//        @Display(name="personId")
})

public class AdmissionRequirements2Ext extends Admission {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId")
    public Integer studentId;
    @Column(name = "threeColoredIdPictures")
    public boolean threeColoredIdPictures;
    @Column(name = "birthCertificateNsoCopy")
    public boolean birthCertificateNsoCopy;
    @Column(name = "baptismalCertificate")
    public boolean baptismalCertificate;
    @Column(name = "latestReportCard")
    public boolean latestReportCard;
    @Column(name = "letterOfRecommendation")
    public boolean letterOfRecommendation;
    @Column(name = "copyOfAcr")
    public boolean copyOfAcr;
    @Column(name = "passportForVerification")
    public boolean passportForVerification;
    @Column(name = "completeScholasticRecords")
    public boolean completeScholasticRecords;

    public boolean isBaptismalCertificate() {
        return baptismalCertificate;
    }

    public void setBaptismalCertificate(boolean baptismalCertificate) {
        this.baptismalCertificate = baptismalCertificate;
    }

    public boolean isBirthCertificateNsoCopy() {
        return birthCertificateNsoCopy;
    }

    public void setBirthCertificateNsoCopy(boolean birthCertificateNsoCopy) {
        this.birthCertificateNsoCopy = birthCertificateNsoCopy;
    }

    public boolean isCompleteScholasticRecords() {
        return completeScholasticRecords;
    }

    public void setCompleteScholasticRecords(boolean completeScholasticRecords) {
        this.completeScholasticRecords = completeScholasticRecords;
    }

    public boolean isCopyOfAcr() {
        return copyOfAcr;
    }

    public boolean setCopyOfAcr(boolean copyOfAcr) {
        this.copyOfAcr = copyOfAcr;
        return True;
    }

    public boolean isLatestReportCard() {
        return latestReportCard;
    }

    public void setLatestReportCard(boolean latestReportCard) {
        this.latestReportCard = latestReportCard;
    }

    public boolean isLetterOfRecommendation() {
        return letterOfRecommendation;
    }

    public void setLetterOfRecommendation(boolean letterOfRecommendation) {
        this.letterOfRecommendation = letterOfRecommendation;
    }

    public boolean isPassportForVerification() {
        return passportForVerification;
    }

    public void setPassportForVerification(boolean passportForVerification) {
        this.passportForVerification = passportForVerification;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public boolean isThreeColoredIdPictures() {
        return threeColoredIdPictures;
    }

    public void setThreeColoredIdPictures(boolean threeColoredIdPictures) {
        this.threeColoredIdPictures = threeColoredIdPictures;
    }

  
}
