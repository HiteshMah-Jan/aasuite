/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.hr.HRAssessment;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.person.PersonExperience;
import bean.person.PersonNotes;
import bean.person.PersonPositionHistory;
import bean.reference.Department;
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
import template.screen.TemplateTabSinglePage;

@UITemplate(template = TemplateTabSinglePage.class, showChart=false, gridCount = 4, columnSearch = {"lastName","firstName","department","age"},showImages=true,title="Inactive Employee", select="SELECT a FROM Employee a WHERE a.isActive=false")
@ChildRecords(
    value={
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Position History", entity = PersonPositionHistory.class, sql = "SELECT a FROM PersonPositionHistory a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Assessment", entity = HRAssessment.class, sql = "SELECT a FROM HRAssessment a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
 },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"hiredDate", "email","birthDate","gender","age","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","address1","contactNumber1"})
}
)
@Displays({
        @Display(name="employeeNumber",addInfoOnly=true),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial",width=-1,label="Middle Name"),
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="position",type="Combo",modelCombo={"DIRECTOR","OIC","HEAD PRESCHOOL","ASSISTANT OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR-SCIENCE","ACADEMIC COORDINATOR-FILIPINO","ACADEMIC COORDINATOR-MAKABAYAN","ACADEMIC COORDINATOR-MATHEMATICS","ACADEMIC COORDINATOR-ENGLISH","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR"}),
        @Display(name="occupation",label="Other Position",type="Combo",modelCombo={"DIRECTOR","OIC","HEAD PRESCHOOL","ASSISTANT OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR-SCIENCE","ACADEMIC COORDINATOR-FILIPINO","ACADEMIC COORDINATOR-MAKABAYAN","ACADEMIC COORDINATOR-MATHEMATICS","ACADEMIC COORDINATOR-ENGLISH","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR"}),
        @Display(name="status",type="Combo",modelCombo={"Z","S/ME","ME1/S1","ME2/S2","ME3/S3","ME4/S4"},width=-1),
        @Display(name="basicPay",width=-1),
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
        @Display(name="sickLeaveUsed",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveUsed",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true),
        @Display(name="scheduleType",addInfoOnly=true),
        //@Display(name="pagibigNumber",addInfoOnly=true),
        
        @Display(name="bankName",addInfoOnly=true),
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="rdoCode",addInfoOnly=true),
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
    @template.Report(reportFile="FacultyList", reportTitle="Employee List", reportSql="${personId}"),
    @template.Report(reportFile="PersonnelInformationSheet", reportTitle="Personnel Information", reportSql="${personId}"),
    @template.Report(reportFile="EmployeeApplicationSheet", reportTitle="Employee Application Sheet", reportSql="${personId}")
})
@ActionButtons({
    @ActionButton(label="Make Active", name="btnActive")
})
public class EmployeeInactiveExt extends bean.hr.Employee {
    public static void main(String[] args) {
        view(EmployeeInactiveExt.class);
    }  
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        return vec;
    }    
}
