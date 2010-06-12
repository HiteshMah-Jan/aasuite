/*
 * Person.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import bean.person.PatientAllergy;
import bean.person.PatientImmunization;
import bean.person.PatientMedication;
import bean.reference.Department;
import bean.person.PatientMedicalRecord;
import bean.person.PatientInsurance;
import bean.reference.Blood;
import java.util.Date;
import javax.persistence.Temporal;
import template.Reports;
/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 6, 
		columnSearch = {"lastName", "firstName", "department"}, criteriaSearch = {"lastName","firstName","department"} ,showImages=true)
@ChildRecords(
    value={
//        @ChildRecord(entity = PatientNurseNote.class, fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientNurseNote a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientMedicalRecord.class, title="Medical Records", fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientMedicalRecord a WHERE a.patientId=${personId}"),
    
    @ChildRecord(entity = PatientImmunization.class, title="Immunizations", sql = "SELECT a FROM PatientImmunization a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientAllergy.class, title="Allergies", sql = "SELECT a FROM PatientAllergy a WHERE a.patientId=${personId}"),
//        @ChildRecord(entity = PatientDoctorRound.class, sql = "SELECT a FROM PatientDoctorRound a WHERE a.patientId=${personId}"),
        @ChildRecord(entity = PatientMedication.class, title="Medications", fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientMedication a WHERE a.patientId=${personId}"),
 @ChildRecord(entity = PatientInsurance.class, title="Insurance", fieldMapping = {"personId", "patientId"}, sql = "SELECT a FROM PatientInsurance a WHERE a.patientId=${personId}")
    },
   
    info={
        @ParentAddInfo(title="Demographics", displayFields={"birthDate","age","placeOfBirth","gender","maritalStatus","bloodType","citizenship","religion"},gridCount=4)
}
)
@DiscriminatorValue(value = "PATIENT")
@DisplayGroups({
  //  @DisplayGroup(title="Patient Name",fields={"lastName","firstName","middleInitial"},gridCount=6)
    @DisplayGroup(title="Contact", fields={"contactNumber","mobilePhone", "email", "im1", "im2"},addInfoOnly=true,gridCount=4),
    @DisplayGroup(title="Address", fields={"streetNumber","barangay", "townDistrict", "cityProvince", "address"},addInfoOnly=true,gridCount=4)
})
@Displays({
        @Display(name="lastName",width=150),
        @Display(name="firstName",width=150),
        @Display(name="middleInitial",width=30,label="M.I."),
        
        @Display(name="department",width=150, type="PopSearch", linktoBean=Department.class),
        @Display(name="physicianId",label="Primary Doctor", type = "PopSearch", linktoBean=Physician.class,gridFieldWidth=3,width=-1),
        
        
        
        
      //  @Display(name="section", addInfoOnly=true, label="Section/Location"),
        @Display(name="birthDate", addInfoOnly=true),
        @Display(name="age",width=30, addInfoOnly=true),
        @Display(name="placeOfBirth", addInfoOnly=true,width=150),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"}, addInfoOnly=true,width=-1),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"}, addInfoOnly=true,width=-1),
        @Display(name="bloodType", type = "PopSearch", linktoBean=Blood.class,addInfoOnly=true,width=-1),
     
     
        @Display(name="citizenship", addInfoOnly=true,width=150),
        @Display(name="religion", addInfoOnly=true,width=150),
        
        @Display(name="contactNumber",label="Telephone" ,addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="mobilePhone",addInfoOnly=true,width=150),
        @Display(name="email",addInfoOnly=true,width=150),
        @Display(name="im1",addInfoOnly=true,width=150),
        @Display(name="im2",addInfoOnly=true,width=150),
        
        
      @Display(name = "streetNumber", addInfoOnly = true, label = "PO Box #",width=150),
      @Display(name = "barangay", addInfoOnly = true, label = "Street",width=150),
      @Display(name = "townDistrict", addInfoOnly = true, label = "Town/District",width=150),
      @Display(name = "cityProvince", addInfoOnly = true, label = "City/Province",width=150),
      @Display(name = "address", addInfoOnly = true, gridFieldWidth = 3, width = -1)
       
})
@Reports( {
		@template.Report(reportFile = "PatientList", reportTitle = "Patient List", reportSql = "")
})


public class Patient extends Customer implements Serializable {
    @Column(name = "bloodType")
    public String bloodType;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "admissionId")
    public int admissionId;
    @Column(name = "registrationDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date registrationDate = new Date();

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public java.lang.String getBloodType() {
        return bloodType;
    }

    public void setBloodType(java.lang.String bloodType) {
        this.bloodType = bloodType;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public static void main(String[] args) {
        view(Patient.class);
    }
}
