/*
 * Person.java
 * 
 * Created on Oct 26, 2007, 9:34:48 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.hr.HRAssessment;
import bean.person.*;
import bean.reference.Department;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateDefault;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(canBackup=false, template = TemplateTabPage.class, gridCount = 6, columnSearch = {"lastName", "firstName","department"},showImages=true)
@ChildRecords(
    value={
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Position History", entity = PersonPositionHistory.class, sql = "SELECT a FROM PersonPositionHistory a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Seminar Attended", entity = PersonSeminarAttended.class, sql = "SELECT a FROM PersonSeminarAttended a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Assessment", entity = HRAssessment.class, sql = "SELECT a FROM HRAssessment a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Benefit", entity = EmployeeBenefit.class, sql = "SELECT a FROM EmployeeBenefit a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Deduction", entity = EmployeeDeduction.class, sql = "SELECT a FROM EmployeeDeduction a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Leave", entity = EmployeeLeaveApplication.class, fieldMapping={"personId","personId"}, sql = "SELECT a FROM EmployeeLeaveApplication a WHERE a.personId=${personId}"),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Attendance", entity = PersonAttendance.class, sql = "SELECT a FROM PersonAttendance a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Loan", fieldMapping={"personId","personId"}, entity = EmployeeLoan.class, sql = "SELECT a FROM EmployeeLoan a WHERE a.personId=${personId}")
 },
    info={
         @ParentAddInfo(title="Personal Information", displayFields={"hiredDate", "email","birthDate","gender","age","weight","height","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","aCROrICRNumber", "specialSkills","hobbies","address1","contactNumber1"}),
         @ParentAddInfo(title="PRC License", displayFields={"prcLicenseNumber","prcRegistrationDate", "prcValidUntil","isLETPasser","letRating"})
}
)
@DiscriminatorValue("HOSPITALEMPLOYEE")
@Displays({
        @Display(name="employeeNumber",addInfoOnly=true),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial",width=20,label="M.I"),
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="position",type="Combo",modelCombo={"DIRECTOR","OIC","HEAD PRESCHOOL","ASSISTANT OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR-SCIENCE","ACADEMIC COORDINATOR-FILIPINO","ACADEMIC COORDINATOR-MAKABAYAN","ACADEMIC COORDINATOR-MATHEMATICS","ACADEMIC COORDINATOR-ENGLISH","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR","GUARD","MAINTENANCE"},gridFieldWidth=3),
        @Display(name="status",type="Combo",modelCombo={"Z","SME","SME1","SME2","SME3","SME4"}),
        @Display(name="basicPay",width=80),
        @Display(name = "hiredDate", addInfoOnly = true,gridFieldWidth=5),
        @Display(name = "birthDate", addInfoOnly = true),
        @Display(name = "age", addInfoOnly = true,gridFieldWidth=3,width=30),
        @Display(name="citizenship",addInfoOnly=true,width=150),
        @Display(name="religion",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="gender",addInfoOnly=true,type="Combo",modelCombo={"MALE", "FEMALE"},width=150),
        @Display(name="maritalStatus",addInfoOnly=true,type="Combo",modelCombo={"SINGLE", "MARRIED"},gridFieldWidth=3,width=150),
        @Display(name = "placeOfBirth", addInfoOnly = true,width=150),
        @Display(name="email",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="contactNumber",addInfoOnly=true,width=150),
        @Display(name="contactNumber1",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="address",addInfoOnly=true,gridFieldWidth=5,width=433),
        @Display(name="address1",addInfoOnly=true,gridFieldWidth=5,width=433),
        @Display(name="tinNumber",addInfoOnly=true,width=150),
        @Display(name="sssNumber",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="pagibigNumber",addInfoOnly=true,width=150),
        @Display(name="philhealthNumber",addInfoOnly=true,width=150),
        
        
        @Display(name="perHourPay",addInfoOnly=true),
        @Display(name="sickLeaveBenefit",addInfoOnly=true),
        @Display(name="sickLeaveUsed",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveUsed",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true),
        @Display(name="scheduleType",addInfoOnly=true),
        @Display(name="pagibigNumber",addInfoOnly=true),
        
        @Display(name="bankName",addInfoOnly=true),
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="rdoCode",addInfoOnly=true),
        @Display(name="personId",addInfoOnly=true),
       
        @Display(name="shortName",addInfoOnly=true),
        @Display(name="personType",addInfoOnly=true),
        
        
     
        @Display(name="prcLicenseNumber",addInfoOnly=true),
        @Display(name="prcRegistrationDate",addInfoOnly=true),
        @Display(name="prcValidUntil",addInfoOnly=true),
        @Display(name="isLETPasser",addInfoOnly=true),
        @Display(name="letRating",addInfoOnly=true),
        
      
        @Display(name="acrorICRNumber",addInfoOnly=true),
        @Display(name="weight",addInfoOnly=true),
        @Display(name="height",addInfoOnly=true),
        @Display(name="hobbies",addInfoOnly=true),
        @Display(name="specialSkills",addInfoOnly=true),
       
       
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
    @template.Report(reportFile="EmployeeList", reportTitle="Employee Report", reportSql="${personId}")
})
public class EmployeeHospital extends Employee implements Serializable {  
}
