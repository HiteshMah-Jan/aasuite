/*
 * Person.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Patient;
import bean.person.PatientAllergy;
import bean.person.PatientImmunization;
import bean.person.PatientDoctorRound;

import java.io.Serializable;
import template.screen.TemplateTabPage;

import template.*;
/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"lastName", "firstName"}, criteriaSearch = {"lastName","firstName"} ,showImages=true)
@ChildRecords(
    value={
        @ChildRecord(entity = PatientNoteClinicExt.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientNurseNote a WHERE a.patientId=${personId}",title="Patient Note"),
        @ChildRecord(entity = PatientImmunization.class, sql = "SELECT a FROM PatientImmunization a"),
        @ChildRecord(entity = PatientAllergy.class, sql = "SELECT a FROM PatientAllergy a"),
        //@ChildRecord(entity = PatientDoctorRound.class, sql = "SELECT a FROM PatientDoctorRound a"),
        @ChildRecord(entity = PatientMedicationClinicExt.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientMedication a WHERE a.patientId=${personId}$",title="Patient Medication"),
        @ChildRecord(entity = PatientDoctorRound.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientDoctorRound a WHERE a.patientId=${personId}$",title="Doctor Rounds")
    },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"birthDate","age","placeOfBirth","gender","citizenship","religion","address", "contactNumber"})
}
)
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
//        @Display(name="department",width=-1,type="Combo", sqlCombo="SELECT a FROM Department a"),
//        @Display(name="physicianId",width=-1, type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"}),
        @Display(name="mobilePhone"),
        @Display(name="email"),
        @Display(name="im1"),
        @Display(name="im2"),
        
        
        @Display(name="birthDate", addInfoOnly=true),
        @Display(name="age",width=30, addInfoOnly=true),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"}, addInfoOnly=true,width=-1),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"}, addInfoOnly=true),
        
     
        @Display(name="placeOfBirth", addInfoOnly=true,width=150),
        @Display(name="citizenship", addInfoOnly=true,width=150),
        @Display(name="religion", addInfoOnly=true,width=150),
        @Display(name="contactNumber", addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="address", addInfoOnly=true,gridFieldWidth=3,width=-1)
       
})
@DisplayGroups({
    @DisplayGroup(title="Contact", fields={"mobilePhone", "email", "im1", "im2"})
})
@Reports({
    @template.Report(reportFile="PatientInfo", reportTitle="Patient Info", reportSql = "${personId}"),
    @template.Report(reportFile="PatientNote", reportTitle="Note", reportSql = "${personId}"),
    @template.Report(reportFile="PatientImmunization", reportTitle="Immunization", reportSql = "${personId}"),
    @template.Report(reportFile="PatientAllergy", reportTitle="Allergy", reportSql = "${personId}"),
    @template.Report(reportFile="PatientMedication", reportTitle="Medication", reportSql = "${personId}"),
    @template.Report(reportFile="PatientDoctorRound", reportTitle="Doctor Round", reportSql = "${personId}")
})
public class PatientClinicExt extends Patient implements Serializable  {
    public static void main(String[] args) {
        view(PatientClinicExt.class);
    }
}