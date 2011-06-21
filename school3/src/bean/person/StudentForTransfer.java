/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.hr.Employee;
import bean.reference.Course;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "StudentForTransfer")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"lastName", "firstName"}, gridCount = 4, title = "Recommendation Form for Transfer Students")
@ActionButtons({
    @ActionButton(label = "Print Recommendation for Transfer", name = "btnPrintRecommendation")
})
@ChildRecords(value = {},
info = {
    @ParentAddInfo(title = "General Evaluation", displayFields = {"intellectualAbility", "emotionalStability", "abilityToDealWithOthers", "diligenceInStudyHabits", "integrity", "remarks"}),
    @ParentAddInfo(title = "Student Recommend", displayFields = {"disciplinaryRecord", "indicationOfImprovement", "isStronglyRecommend", "isRecommend", "isReservation", "employeeId", "position", "recommendationDate", "telephoneNumber"})
})
@Displays({
    // @Display(name = "studentForTransferId"),
    @Display(name = "gradeLevelApplied", type="PopSearch", linktoBean=Course.class),
    @Display(name = "studentForTransferDate", label="Date"),
    @Display(name = "lastName"),
    @Display(name = "firstName"),
    @Display(name = "middleInitial", gridFieldWidth=3),
    @Display(name = "presentSchool", gridFieldWidth=3, width=-1),
    @Display(name = "presentSchoolAddress", label="Address",gridFieldWidth=3, width=-1),
    @Display(name = "presentSchoolContactNumber", label="Tel. #"),
    @Display(name = "fax"),
    @Display(name = "email"),
    @Display(name = "intellectualAbility", addInfoOnly = true, type = "Combo", modelCombo = {"SUPERIOR", "ABOVE AVERAGE", "AVERAGE", "BELOW AVERAGE"}),
    @Display(name = "emotionalStability", addInfoOnly = true, type = "Combo", modelCombo = {"SUPERIOR", "ABOVE AVERAGE", "AVERAGE", "BELOW AVERAGE"}),
    @Display(name = "abilityToDealWithOthers", addInfoOnly = true, type = "Combo", modelCombo = {"SUPERIOR", "ABOVE AVERAGE", "AVERAGE", "BELOW AVERAGE"}),
    @Display(name = "diligenceInStudyHabits", addInfoOnly = true, type = "Combo", modelCombo = {"SUPERIOR", "ABOVE AVERAGE", "AVERAGE", "BELOW AVERAGE"}),
    @Display(name = "integrity", addInfoOnly = true, gridFieldWidth=3, type = "Combo", modelCombo = {"SUPERIOR", "ABOVE AVERAGE", "AVERAGE", "BELOW AVERAGE"}),
    @Display(name = "remarks", addInfoOnly = true, gridFieldWidth=3, width=-1),
    @Display(name = "disciplinaryRecord", addInfoOnly = true, gridFieldWidth=3,width=-1),
    @Display(name = "indicationOfImprovement", addInfoOnly = true, gridFieldWidth=3,width=-1),
    @Display(name = "isStronglyRecommend",addInfoOnly = true, type = "CheckBox"),
    @Display(name = "isRecommend",addInfoOnly = true, type = "CheckBox"),
    @Display(name = "isReservation",gridFieldWidth=3, width=-1,addInfoOnly = true, type = "CheckBox"),
    @Display(name = "employeeId", addInfoOnly = true,label="Recommend By", type = "PopSearch", linktoBean=Employee.class),
    @Display(name = "position", addInfoOnly = true, type = "Combo", modelCombo = {"DIRECTOR","OIC","HEAD PRESCHOOL","ASST. OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR","GUARD","MAINTENANCE"}),
    @Display(name = "recommendationDate", addInfoOnly = true, label="Date"),
    @Display(name = "telephoneNumber", addInfoOnly = true)
//        @Display(name="admissionId"),
//        @Display(name="invoiceId"),
//        @Display(name="birthDate"),
//        @Display(name="personId")
})
@Reports({
    @template.Report(reportFile = "RecommendationFormForTransferStudent", reportTitle = "Recommendation for Transfer Report", reportSql = "${studentForTransferId}")
})
public class StudentForTransfer extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentForTransferDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date studentForTransferDate;
    @Column(name = "gradeLevelApplied", nullable = false)
    public String gradeLevelApplied;
    @Column(name = "lastName", nullable = false)
    public String lastName;
    @Column(name = "firstName", nullable = false)
    public String firstName;
    @Column(name = "middleInitial")
    public String middleInitial;
    @Column(name = "presentSchool")
    public String presentSchool;
    @Column(name = "presentSchoolAddress")
    public String presentSchoolAddress;
    @Column(name = "presentSchoolContactNumber")
    public String presentSchoolContactNumber;
    @Column(name = "fax")
    public String fax;
    @Column(name = "email")
    public String email;
    @Column(name = "intellectualAbility")
    public String intellectualAbility;
    @Column(name = "emotionalStability")
    public String emotionalStability;
    @Column(name = "abilityToDealWithOthers")
    public String abilityToDealWithOthers;
    @Column(name = "diligenceInStudyHabits")
    public String diligenceInStudyHabits;
    @Column(name = "integrity")
    public String integrity;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "disciplinaryRecord")
    public String disciplinaryRecord;
    @Column(name = "indicationOfImprovement")
    public String indicationOfImprovement;
    @Column(name = "isStronglyRecommend")
    public Boolean isStronglyRecommend;
    @Column(name = "isRecommend")
    public Boolean isRecommend;
    @Column(name = "isReservation")
    public Boolean isReservation;
    @Column(name = "employeeId", nullable = false)
    public int employeeId;
    @Column(name = "position")
    public String position;
    @Column(name = "recommendationDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date recommendationDate;
    @Column(name = "telephoneNumber")
    public String telephoneNumber;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "lastName","firstName", "middleInitial");
    }

    public String getAbilityToDealWithOthers() {
        return abilityToDealWithOthers;
    }

    public void setAbilityToDealWithOthers(String abilityToDealWithOthers) {
        this.abilityToDealWithOthers = abilityToDealWithOthers;
    }

    public String getDiligenceInStudyHabits() {
        return diligenceInStudyHabits;
    }

    public void setDiligenceInStudyHabits(String diligenceInStudyHabits) {
        this.diligenceInStudyHabits = diligenceInStudyHabits;
    }

    public String getDisciplinaryRecord() {
        return disciplinaryRecord;
    }

    public void setDisciplinaryRecord(String disciplinaryRecord) {
        this.disciplinaryRecord = disciplinaryRecord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmotionalStability() {
        return emotionalStability;
    }

    public void setEmotionalStability(String emotionalStability) {
        this.emotionalStability = emotionalStability;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIndicationOfImprovement() {
        return indicationOfImprovement;
    }

    public void setIndicationOfImprovement(String indicationOfImprovement) {
        this.indicationOfImprovement = indicationOfImprovement;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Boolean getIsReservation() {
        return isReservation;
    }

    public void setIsReservation(Boolean isReservation) {
        this.isReservation = isReservation;
    }

    public Boolean getIsStronglyRecommend() {
        return isStronglyRecommend;
    }

    public void setIsStronglyRecommend(Boolean isStronglyRecommend) {
        this.isStronglyRecommend = isStronglyRecommend;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getIntellectualAbility() {
        return intellectualAbility;
    }

    public void setIntellectualAbility(String intellectualAbility) {
        this.intellectualAbility = intellectualAbility;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPresentSchool() {
        return presentSchool;
    }

    public void setPresentSchool(String presentSchool) {
        this.presentSchool = presentSchool;
    }

    public String getPresentSchoolAddress() {
        return presentSchoolAddress;
    }

    public void setPresentSchoolAddress(String presentSchoolAddress) {
        this.presentSchoolAddress = presentSchoolAddress;
    }

    public String getPresentSchoolContactNumber() {
        return presentSchoolContactNumber;
    }

    public void setPresentSchoolContactNumber(String presentSchoolContactNumber) {
        this.presentSchoolContactNumber = presentSchoolContactNumber;
    }

    public Date getRecommendationDate() {
        return recommendationDate;
    }

    public void setRecommendationDate(Date recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getStudentForTransferDate() {
        return studentForTransferDate;
    }

    public void setStudentForTransferDate(Date studentForTransferDate) {
        this.studentForTransferDate = studentForTransferDate;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getGradeLevelApplied() {
        return gradeLevelApplied;
    }

    public void setGradeLevelApplied(String gradeLevelApplied) {
        this.gradeLevelApplied = gradeLevelApplied;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return lastName;
    }
}
