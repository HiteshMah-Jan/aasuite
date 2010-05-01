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
import bean.Physician;
import bean.person.PatientNurseNote;
import bean.person.PatientAllergy;
import bean.person.PatientMedication;
import bean.person.PatientImmunization;
import bean.person.PatientDoctorRound;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabPage;
/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 2, columnSearch = {"lastName", "firstName"}, criteriaSearch = {"lastName","firstName"} ,showImages=true,imageEditable=false)
@ChildRecords(
    value={
        @ChildRecord(entity = PatientNurseNote.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientNurseNote a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientImmunization.class, sql = "SELECT a FROM PatientImmunization a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientAllergy.class, sql = "SELECT a FROM PatientAllergy a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientDoctorRound.class, sql = "SELECT a FROM PatientDoctorRound a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientMedication.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientMedication a WHERE a.patientId=${personId}")
    },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"birthDate","age","placeOfBirth","gender","citizenship","religion","address", "contactNumber"})
}
)
@Displays({
        @Display(name="physicianId",width=200, type = "PopSearch", linktoBean=Physician.class, label="Physician",enabled=false),
        @Display(name="mobilePhone",width=-1,enabled=false),
        @Display(name="birthDate",enabled=false),
        @Display(name="age",width=-1,enabled=false),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"},width=-1,enabled=false),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"},width=-1,enabled=false),
        @Display(name="contactNumber",width=-1,enabled=false)
})
public class PatientSnapShotExt extends Patient {
}
