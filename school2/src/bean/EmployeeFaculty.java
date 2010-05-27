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
import javax.persistence.PostPersist;
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
import template.screen.TemplateTabSinglePage;
import bean.hr.HRAssessment;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.person.PersonExperience;
import bean.person.PersonNotes;
import bean.person.PersonPositionHistory;
import bean.person.PersonSeminarAttended;
import bean.reference.Department;
import bean.reference.EmployeePositionRef;
import bean.reference.EmployeeTaxStatus;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, 
		columnSearch = {"lastName","firstName","department","position"},showImages=true,title="Employee", select="SELECT a FROM EmployeeFaculty a WHERE a.isActive=true")
@ChildRecords(
    value={
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Position History", entity = PersonPositionHistory.class, sql = "SELECT a FROM PersonPositionHistory a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Seminar Attended", entity = PersonSeminarAttended.class, sql = "SELECT a FROM PersonSeminarAttended a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Assessment", entity = HRAssessment.class, sql = "SELECT a FROM HRAssessment a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
//       @ChildRecord(title="Benefit", entity = EmployeeBenefit.class, sql = "SELECT a FROM EmployeeBenefit a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
//       @ChildRecord(title="Deduction", entity = EmployeeDeduction.class, sql = "SELECT a FROM EmployeeDeduction a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
//       @ChildRecord(title="Leave", entity = EmployeeLeaveApplication.class, fieldMapping={"personId","personId"}, sql = "SELECT a FROM EmployeeLeaveApplication a WHERE a.personId=${personId}"),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
//       @ChildRecord(title="Disciplinary Action", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
//       @ChildRecord(title="Attendance", entity = PersonAttendance.class, sql = "SELECT a FROM PersonAttendance a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
//       @ChildRecord(title="Loan", entity = EmployeeLoan.class, sql = "SELECT a FROM EmployeeLoan a WHERE a.personId=${personId}", fieldMapping={"personId","personId"})
 },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"hiredDate", "perHourPay","email","birthDate","gender","age","weight","height","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","aCROrICRNumber", "specialSkills","hobbies","address1","contactNumber1"}),
        @ParentAddInfo(title="PRC License", displayFields={"isLETPasser","prcLevel","prcLicenseNumber","prcRegistrationDate", "prcValidUntil","letRating"})
}
)
@DiscriminatorValue("FACULTY")
@Displays({
        @Display(name="employeeNumber",addInfoOnly=true),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial",label="Middle Name"),
        @Display(name="department",type="PopSearch", linktoBean=Department.class),
        @Display(name="position",type="PopSearch",linktoBean=EmployeePositionRef.class),
        @Display(name="occupation"),
        @Display(name="status",type="PopSearch", linktoBean=EmployeeTaxStatus.class),
        @Display(name="basicPay",width=-1,duties={"CAN EDIT SALARY"},viewOnDuties={"CAN EDIT SALARY"}),
        @Display(name="hiredDate", addInfoOnly = true,gridFieldWidth=3),
        @Display(name="birthDate", addInfoOnly = true),
        @Display(name="age", addInfoOnly = true,width=30),
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
        
        
        @Display(name="perHourPay",addInfoOnly=true,duties={"CAN EDIT SALARY"},viewOnDuties={"CAN EDIT SALARY"}),
        @Display(name="sickLeaveBenefit",addInfoOnly=true),
        @Display(name="sickLeaveUsed",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveUsed",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true),
        @Display(name="scheduleType",addInfoOnly=true),
        
        
        @Display(name="bankName",addInfoOnly=true),
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="rdoCode",addInfoOnly=true),
        @Display(name="personId",addInfoOnly=true),
       
        @Display(name="shortName",addInfoOnly=true),
        @Display(name="personType",addInfoOnly=true),
        
        
        @Display(name="isLETPasser",addInfoOnly=true),
        @Display(name="prcLevel",addInfoOnly=true),
        @Display(name="prcLicenseNumber",addInfoOnly=true),
        @Display(name="prcRegistrationDate",addInfoOnly=true),
        @Display(name="prcValidUntil",addInfoOnly=true),
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
    @template.Report(reportFile="FacultyList", reportTitle="Employee List", reportSql="${personId}"),
    @template.Report(reportFile="PersonnelInformationSheet", reportTitle="Personnel Information", reportSql="${personId}"),
    @template.Report(reportFile="EmployeeApplicationSheet", reportTitle="Employee Application Sheet", reportSql="${personId}")
})
@ActionButtons({
    @ActionButton(label="Add Group Account", name="btnAddGroupAccount"),
    @ActionButton(label="Module Account", name="btnAddModuleAccount"),
    @ActionButton(label="Reset Password", name="btnResetPassword"),
    @ActionButton(label="Inactive", name="btnInactive"),
//    @ActionButton(label="Attendance", name="btnEmployeeAttendance"),
    @ActionButton(label="Login", name="btnAutoLogin"),
    @ActionButton(label="Monitor", name="btnMonitorLogin")
})
public class EmployeeFaculty extends EmployeeSchool implements Serializable {
	public EmployeeFaculty() {
		personType = "EmployeeFaculty";
	}
	
    @Column(name = "course", length = 30)
    public String course;
    public java.lang.String getCourse() {
        return course;
    }

    public void setCourse(java.lang.String course) {
        this.course = course;
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
//        if (personId==null || personId==0) {
//            Employee emp = (Employee) firstRecord("SELECT a FROM Employee a WHERE a.lastName LIKE '",getLastName(),"'");
//            if (emp==null || emp.getPersonId()==null || emp.getPersonId()==0) {
//                setUserid(getLastName());
//            }
//        }
    }

    @PostPersist
    public void afterPersist() {
//        SchoolDefaultProcess.getProcess().doProcess(SchoolDefaultProcess.ADD_FACULTY_MODULES, this);
    }
    
    public static EmployeeFaculty createEmployeeFacultyObj(Integer facultyId,String lastName, String firstName, String department, double basicPay) {
        EmployeeFaculty fac = new EmployeeFaculty();
        fac.personId = facultyId;
        fac.lastName = lastName;
        fac.firstName = firstName;
        fac.department = department;
        fac.basicPay = basicPay;
        return fac;
    }

	@Override
	public boolean cacheClient() {
		return true;
	}
    
//    @Override
//    protected void runSetup() {
//       // createEmployeeFacultyObj("Estabillo", "Niq", "HIGH SCHOOL DEPARTMENT", 40000).save();
//       // createEmployeeFacultyObj("Miranda", "Alex", "HIGH SCHOOL DEPARTMENT", 35000).save();
//    }
}
