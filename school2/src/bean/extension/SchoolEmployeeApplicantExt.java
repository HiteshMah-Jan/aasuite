/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.JobApplicant;
import bean.person.FormalTraining;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.person.PersonExperience;
import bean.person.PersonInformation;
import bean.person.PersonNotes;
import bean.person.PersonReference;
import bean.person.PersonSeminarAttended;
import bean.person.TeachingExperience;
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
import template.screen.TemplateScrollableTabPage;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(canBackup=false, template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"lastName", "firstName", "applicationStatus"},showImages=true)
@ChildRecords(
    value={
       @ChildRecord(title="Personal Information", entity = PersonInformation.class, sql = "SELECT a FROM PersonInformation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Seminar Attended", entity = PersonSeminarAttended.class, sql = "SELECT a FROM PersonSeminarAttended a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Personal References", entity = PersonReference.class, sql = "SELECT a FROM PersonReference a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Teaching Experience", entity = TeachingExperience.class, sql = "SELECT a FROM TeachingExperience a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Formal Training", entity = FormalTraining.class, sql = "SELECT a FROM FormalTraining a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"}),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId = ${personId}", fieldMapping={"personId","personId"})
 },
    info={
        @ParentAddInfo(title="Additional Information", displayFields={"hiredDate", "email","birthDate","gender","age","weight","height","aCROrICRNumber", "specialSkills","hobbies","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","address1","contactNumber1"}),
        @ParentAddInfo(title="PRC License", displayFields={"isLETPasser","prcLevel","prcLicenseNumber","prcRegistrationDate", "prcValidUntil","letRating"})
}
)
@Displays({
        @Display(name="lastName",width=-1),
        @Display(name="firstName"),
        @Display(name="middleInitial",width=-1,label="Middle Name"),
        @Display(name="maidenName"),
        @Display(name="applicationStatus",type="Combo",modelCombo={"1ST INTERVIEW","2ND INTERVIEW","FAILED","PASSED"},width=-1),
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="position",width=-1),
        @Display(name="status",type="Combo",modelCombo={"Z","S/ME","ME1/S1","ME2/S2","ME3/S3","ME4/S4"}),
        @Display(name="basicPay",width=80),
        @Display(name="monthlyAllowance",addInfoOnly=true),
        @Display(name = "hiredDate", addInfoOnly = true,gridFieldWidth=3,width=200),
        @Display(name = "birthDate", addInfoOnly = true,width=200),
        @Display(name = "age", addInfoOnly = true,width=30),
        @Display(name="citizenship",addInfoOnly=true,width=200),
        @Display(name="religion",addInfoOnly=true,width=200),
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
       // @Display(name="pagibigNumber",addInfoOnly=true),
        
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
        @Display(name="weight",addInfoOnly=true),
        @Display(name="height",addInfoOnly=true),
        @Display(name="hobbies",addInfoOnly=true),
        @Display(name="specialSkills",addInfoOnly=true),
        @Display(name="aCROrICRNumber",addInfoOnly=true),
        
        @Display(name="provincialAddress",addInfoOnly=true),
        @Display(name="companyTelephoneNumber",addInfoOnly=true),
        @Display(name="companyAddress",addInfoOnly=true),
        
        @Display(name="isLETPasser",addInfoOnly=true),
        @Display(name="prcLevel",addInfoOnly=true),
        @Display(name="prcLicenseNumber",addInfoOnly=true),
        @Display(name="prcRegistrationDate",addInfoOnly=true),
        @Display(name="prcValidUntil",addInfoOnly=true),
        @Display(name="letRating",addInfoOnly=true)
        

})
@Reports({ 
    @template.Report(reportFile="ApplicantList", reportTitle="Applicant Report", reportSql=""),
    @template.Report(reportFile="EmployeeApplicationSheet", reportTitle="Personnel Information Sheet", reportSql="${personId}")
})
@ActionButtons({
    @ActionButton(label="Hire Applicant", name="btnHire")
})
public class SchoolEmployeeApplicantExt extends JobApplicant {

}
