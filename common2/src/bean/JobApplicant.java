/*
 * Person.java
 * 
 * Created on Oct 26, 2007, 9:34:48 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.person.*;
import bean.reference.Department;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
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
@Table(name = "Person")
@UITemplate(canBackup=false, template = TemplateTabPage.class, gridCount = 4, columnSearch = {"lastName", "firstName", "applicationStatus"},showImages=true)
@ChildRecords(
    value={
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Seminar Attended", entity = PersonSeminarAttended.class, sql = "SELECT a FROM PersonSeminarAttended a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
 },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"hiredDate", "email","birthDate","gender","age","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","address1","contactNumber1"})
}
)
@DiscriminatorValue("JOB APPLICANT")
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial",width=-1,label="Middle Name"),
        @Display(name="applicationStatus",type="Combo",modelCombo={"1ST INTERVIEW","2ND INTERVIEW","FAILED","PASSED"}),
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="position",type="Combo",modelCombo={"DIRECTOR","OIC","HEAD PRESCHOOL","ASSISTANT OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR","GUARD","MAINTENANCE"},width=-1),
        @Display(name="status",type="Combo",modelCombo={"Z","S/ME","ME1/S1","ME2/S2","ME3/S3","ME4/S4"}),
        @Display(name="basicPay",width=-1),
        @Display(name="monthlyAllowance",addInfoOnly=true),
        @Display(name = "hiredDate", addInfoOnly = true,gridFieldWidth=3),
        @Display(name = "birthDate", addInfoOnly = true),
        @Display(name = "age", addInfoOnly = true,width=30),
        @Display(name="citizenship",addInfoOnly=true,width=-1),
        @Display(name="religion",addInfoOnly=true,width=-1),
        @Display(name="gender",addInfoOnly=true,type="Combo",modelCombo={"MALE", "FEMALE"},width=-1),
        @Display(name="maritalStatus",addInfoOnly=true,type="Combo",modelCombo={"SINGLE", "MARRIED"},width=-1),
        @Display(name = "placeOfBirth", addInfoOnly = true,width=-1),
        @Display(name="email",addInfoOnly=true,width=-1),
        @Display(name="contactNumber",addInfoOnly=true,width=-1),
        @Display(name="contactNumber1",addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="address",addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="address1",addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="tinNumber",addInfoOnly=true,width=-1),
        @Display(name="sssNumber",addInfoOnly=true,width=-1),
        @Display(name="pagibigNumber",addInfoOnly=true,width=-1),
        @Display(name="philhealthNumber",addInfoOnly=true,width=-1),
        
        @Display(name="perHourPay",addInfoOnly=true),
        @Display(name="sickLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true,type="Combo",modelCombo={"PER HOUR","DAILY","WEEKLY","BIMONTHLY","MONTHLY"}),
        @Display(name="scheduleType",addInfoOnly=true),
      //  @Display(name="pagibigNumber",addInfoOnly=true),
        
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="personId",addInfoOnly=true),
       
        @Display(name="shortName",addInfoOnly=true),
        @Display(name="personType",addInfoOnly=true),
       
        @Display(name="occupation",addInfoOnly=true),
        @Display(name="mother",addInfoOnly=true),
        @Display(name="father",addInfoOnly=true),
        @Display(name="spouse",addInfoOnly=true),
        @Display(name="im1",addInfoOnly=true),
        @Display(name="im2",addInfoOnly=true),
        @Display(name="mobilePhone",addInfoOnly=true),
        @Display(name="userid",addInfoOnly=true),
        @Display(name="guardianOccupation",addInfoOnly=true),
        @Display(name="guardianName",addInfoOnly=true),
        @Display(name="guardianRelationship",addInfoOnly=true),
        @Display(name="guardianAddress",addInfoOnly=true),
        @Display(name="guardianContactNumber",addInfoOnly=true),
        @Display(name="zipCode",addInfoOnly=true),
        @Display(name="streetNumber",addInfoOnly=true),
        @Display(name="barangay",addInfoOnly=true),
        @Display(name="townDistrict",addInfoOnly=true),
        @Display(name="cityProvince",addInfoOnly=true),
        @Display(name="isActive",addInfoOnly=true),
        @Display(name="place",addInfoOnly=true),
        @Display(name="state",addInfoOnly=true),
        @Display(name="country",addInfoOnly=true),
        @Display(name="fax",addInfoOnly=true),
        @Display(name="companyName",addInfoOnly=true),
        @Display(name="provincialAddress",addInfoOnly=true),
        @Display(name="companyTelephoneNumber",addInfoOnly=true),
        @Display(name="companyAddress",addInfoOnly=true)
})
@Reports({ 
    @template.Report(reportFile="ApplicantList", reportTitle="Applicant Report", reportSql="")
})
@ActionButtons({
    @ActionButton(label="Hire Applicant", name="btnHire")
})
public class JobApplicant extends Person implements Serializable {  
    @Column(name = "applicationStatus")
    public String applicationStatus;

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
