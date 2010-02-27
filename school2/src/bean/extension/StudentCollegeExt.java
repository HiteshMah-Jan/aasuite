/*
 * StudentExt.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.LibraryBorrowedBooks;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.person.StudentEncounter;
import bean.person.StudentEncounterClinic;
import bean.person.StudentPersonalityExam;
import bean.person.PersonAttendance;
import bean.person.StudentSchoolAttended;
import bean.person.StudentSummerSchoolAttended;
import bean.reference.Course;
import bean.reference.GradeLevel;
import bean.reference.ScholarshipTable;

import java.io.Serializable;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateLeftRight;
import template.screen.TemplateTabPage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(showChart = false, template = TemplateLeftRight.class, gridCount = 4, columnSearch = {
	"schoolYear", "studentNumber", "lastName", "firstName", "course",
	"gradeLevel", "section", "age" }, criteriaSearch = { "schoolYear",
	"studentNumber", "lastName", "firstName", "section" }, 
	showImages = true, imageInsideForm = true, showFiles = true, orderBy = "a.lastName",
	select="SELECT a FROM Student a WHERE a.college=true")
@ChildRecords(value = {
	// @ChildRecord(title="Address", entity = PersonAddress.class, sql =
	// "SELECT a FROM PersonAddress a WHERE a.personId=${personId}"),
	@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
			"personId", "personId" }, title = "Parents / Guardians", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId=${personId}"),
	@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
			"personId", "personId" }, title = "Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId=${personId}"),
	@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
			"personId", "studentId" }, title = "Curriculum Subject", entity = StudentSubjectCollegeExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}"),
	@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
			"personId", "studentId" }, title = "School Attended", entity = StudentSchoolAttended.class, sql = "SELECT a FROM StudentSchoolAttended a WHERE a.studentId=${personId}"),
	@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
			"personId", "studentId" }, title = "Summer Attended", entity = StudentSummerSchoolAttended.class, sql = "SELECT a FROM StudentSummerSchoolAttended a WHERE a.studentId=${personId}")
//@ChildRecord(template = ChildTemplateListOnly.class, fieldMapping = {"seq",
//"paidBy"}, entity = PaymentExt.class, sql =
//"SELECT a FROM Payment a WHERE a.paidBy=${personId}", title =
//"Payments Ledger"),
//@ChildRecord(template = ChildTemplateListPopupDownButton.class,fieldMapping =
//{"personId", "studentId"}, title = "Attendance", entity =
//StudentAttendance.class, sql =
//"SELECT a FROM StudentAttendance a WHERE a.studentId=${personId}")
//@ChildRecord(template = ChildTemplateListOnly.class, title =
//"History Records", entity = Student.class, sql =
//"SELECT a FROM Student a WHERE a.parentId=${personId}")
}, info = { @ParentAddInfo(title = "Student's Other Info", displayFields = {
	"schoolLastAttended", "schoolLastAttendedAddress" }) })
@Displays( {
	@Display(name = "studentNumber", button = "Change", gridFieldWidth = 3),
	@Display(name = "schoolYear", gridFieldWidth = 3, width = -1),
	@Display(name = "course", type = "PopSearch", linktoBean = Course.class, mandatory = true, gridFieldWidth = 3, width = -1),
	@Display(name = "gradeLevel", label = "Grade", type = "PopSearch", linktoBean = GradeLevel.class, mandatory = true, gridFieldWidth = 3, width = -1),
	@Display(name = "section", type = "PopSearch", linktoBean = SectionCollegeExt.class, gridFieldWidth = 3, width = -1),
	// @Display(name = "toSection", type = "PopSearch", linktoBean =
	// Section.class, gridFieldWidth = 3, width =-1),

	@Display(name = "gender", gridFieldWidth = 3, label = "Sex", type = "Combo", modelCombo = {
			"MALE", "FEMALE" }, width = -1),
	@Display(name = "status", gridFieldWidth = 3, type = "Combo", modelCombo = {
			"ENROLLED", "NOT ENROLLED", "OFF. DROP", "UNOFF. DROP",
			"FAILED" }, width = -1),
	@Display(name = "addStatus", gridFieldWidth = 3, type = "Combo", modelCombo = {
			"NEW", "REGULAR", "PROBATIONARY", "TRANSFEREES", "BALIK-ARAL",
			"SPED GRADED", "REPEATER", "MUSLIM", "INDIGENOUS PEOPLE" }, width = -1),

	@Display(name = "scholarshipCode", type = "PopSearch", linktoBean = ScholarshipTable.class, gridFieldWidth = 3, width = -1),
	@Display(name = "tempPass", label = "Password", gridFieldWidth = 3, width = -1),

	@Display(name = "lastName", labelTop = true),
	@Display(name = "firstName", labelTop = true),
	@Display(name = "middleInitial", label = "Middle Name", labelTop = true),

	@Display(name = "mobilePhone"),
	@Display(name = "email"),
	@Display(name = "im1"),
	@Display(name = "im2"),
	@Display(name = "contactNumber", label = "Tel. #"),
	@Display(name = "contactNumber1", label = "Tel. #(other)"),

	@Display(name = "birthDate"),
	@Display(name = "age", width = 30),
	@Display(name = "placeOfBirth"),
	@Display(name = "nationality"),
	@Display(name = "ifAlienAcrNo"),
	@Display(name = "acrPlaceIssued"),
	@Display(name = "acrDateIssued"),
	@Display(name = "religion"),
	// @Display(name = "otherReligion"),

	@Display(name = "address", gridFieldWidth = 3, width = -1, addInfoOnly = true),
	@Display(name = "barangay", gridFieldWidth = 3, width = -1, addInfoOnly = true),
	@Display(name = "cityProvince", upCase=false,label = "City/Municipality", addInfoOnly = true),
	@Display(name = "zipCode", width = 150, addInfoOnly = true),
	@Display(name = "provincialAddress", label = "Province", gridFieldWidth = 3, width = -1, addInfoOnly = true),

	// @Display(name = "guardianName", labelTop = true, label = "Guardian",
	// addInfoOnly = true),
	// @Display(name = "guardianRelationship", labelTop = true, label =
	// "Relation/s", addInfoOnly = true),
	// @Display(name = "guardianContactNumber", labelTop = true, label =
	// "Contact No/s.", addInfoOnly = true),
	// @Display(name = "guardianName2",hideLabel=true, addInfoOnly = true),
	// @Display(name = "guardianContactNumber2", hideLabel = true,
	// addInfoOnly = true),
	// @Display(name = "guardianRelationship2", hideLabel = true,
	// addInfoOnly = true),

	@Display(name = "childName1", label = "Name", addInfoOnly = true),
	@Display(name = "childNameBirthDate1", label = "Date of Birth", addInfoOnly = true),
	@Display(name = "childNameSchoolEmployee1", label = "School/Employment", addInfoOnly = true),
	@Display(name = "childNameGradeSection1", label = "Gr./Yr-Sec", addInfoOnly = true),
	@Display(name = "childName2", label = "Name", addInfoOnly = true),
	@Display(name = "childNameBirthDate2", label = "Date of Birth", addInfoOnly = true),
	@Display(name = "childNameSchoolEmployee2", label = "School/Employment", addInfoOnly = true),
	@Display(name = "childNameGradeSection2", label = "Gr./Yr-Sec", addInfoOnly = true),
	@Display(name = "childName3", label = "Name", addInfoOnly = true),
	@Display(name = "childNameBirthDate3", label = "Date of Birth", addInfoOnly = true),
	@Display(name = "childNameSchoolEmployee3", label = "School/Employment", addInfoOnly = true),
	@Display(name = "childNameGradeSection3", label = "Gr./Yr-Sec", addInfoOnly = true),

	@Display(name = "schoolLastAttended", gridFieldWidth = 3, width = 250, addInfoOnly = true),
	@Display(name = "schoolLastAttendedAddress", label = "Address", gridFieldWidth = 3, width = 250, addInfoOnly = true),

	// @Display(name="registrationDate"),

	@Display(name = "semester", addInfoOnly = true),
	@Display(name = "registrationNumber", addInfoOnly = true),

	@Display(name = "elementarySchool", addInfoOnly = true),
	@Display(name = "highSchool", addInfoOnly = true),
	@Display(name = "elementaryGraduatedDate", addInfoOnly = true),
	@Display(name = "highSchoolGraduatedDate", addInfoOnly = true),
	@Display(name = "individual", addInfoOnly = true),
	@Display(name = "paymentTerm", addInfoOnly = true),
	@Display(name = "salesPersonId1", addInfoOnly = true),
	@Display(name = "salesPersonId2", addInfoOnly = true),
	@Display(name = "salesPersonId3", addInfoOnly = true),
	@Display(name = "salesPersonId4", addInfoOnly = true),
	@Display(name = "salesPersonId5", addInfoOnly = true),
	@Display(name = "territory", addInfoOnly = true),
	@Display(name = "customerClass", addInfoOnly = true),
	@Display(name = "priceLevel", addInfoOnly = true),
	@Display(name = "contactPerson", addInfoOnly = true),
	@Display(name = "employeeHeadcount", addInfoOnly = true),
	@Display(name = "customerHeadcount", addInfoOnly = true),
	@Display(name = "personId", addInfoOnly = true),
	@Display(name = "hiredDate", addInfoOnly = true),
	@Display(name = "shortName", addInfoOnly = true),
	@Display(name = "personType", addInfoOnly = true),

	@Display(name = "maritalStatus", type = "Combo", modelCombo = {
			"SINGLE", "MARRIED" }, addInfoOnly = true),
	@Display(name = "department", addInfoOnly = true),

	@Display(name = "citizenship", addInfoOnly = true),

	@Display(name = "address1", addInfoOnly = true),
	@Display(name = "tinNumber", addInfoOnly = true),
	@Display(name = "sssNumber", addInfoOnly = true),
	@Display(name = "pagibigNumber", addInfoOnly = true),
	// @Display(name="status", addInfoOnly=true),
	@Display(name = "position", addInfoOnly = true),
	@Display(name = "occupation", addInfoOnly = true),
	@Display(name = "mother", addInfoOnly = true),
	@Display(name = "father", addInfoOnly = true),
	@Display(name = "spouse", addInfoOnly = true),
	@Display(name = "userid", addInfoOnly = true),
	// @Display(name = "guardianName", addInfoOnly = true),
	// @Display(name = "guardianOccupation", addInfoOnly = true),
	// @Display(name = "guardianRelationship", addInfoOnly = true),
	// @Display(name = "guardianContactNumber", addInfoOnly = true),
	// @Display(name = "guardianAddress", addInfoOnly = true),

	@Display(name = "streetNumber", addInfoOnly = true),

	@Display(name = "townDistrict", addInfoOnly = true),

	@Display(name = "isActive", addInfoOnly = true),
	@Display(name = "place", addInfoOnly = true),
	@Display(name = "state", addInfoOnly = true),
	@Display(name = "country", addInfoOnly = true),
	@Display(name = "fax", addInfoOnly = true),
	@Display(name = "companyName", addInfoOnly = true),

	@Display(name = "companyTelephoneNumber", addInfoOnly = true),
	@Display(name = "companyAddress", addInfoOnly = true) })
@DisplayGroups( {
	@DisplayGroup(gridCount = 6, title = "Name", fields = { "lastName",
			"firstName", "middleInitial" }),
	@DisplayGroup(gridCount = 4, title = "Student Info", fields = {
			"birthDate", "age", "placeOfBirth", "nationality",
			"ifAlienAcrNo", "acrPlaceIssued", "acrDateIssued", "religion" }),
	@DisplayGroup(gridCount = 4, title = "Contact", fields = {
			"mobilePhone", "email", "im1", "im2", "contactNumber",
			"contactNumber1" }),
	@DisplayGroup(gridCount = 4, title = "Address", fields = { "address",
			"barangay", "cityProvince", "zipCode", "provincialAddress" }, addInfoOnly = true),
	// @DisplayGroup(gridCount=4, title="In Case of Emergency",
	// fields={"guardianName","guardianRelationship","guardianContactNumber","guardianName2","guardianContactNumber2","guardianRelationship2"},addInfoOnly=true),
	@DisplayGroup(title = "Other Brother/s and/or Sister/s - 1", gridCount = 2, fields = {
			"childName1", "childNameBirthDate1",
			"childNameSchoolEmployee1", "childNameGradeSection1" }, addInfoOnly = true),
	@DisplayGroup(title = "Other Brother/s and/or Sister/s - 2", gridCount = 2, fields = {
			"childName2", "childNameBirthDate2",
			"childNameSchoolEmployee2", "childNameGradeSection2" }, addInfoOnly = true),
	@DisplayGroup(title = "Other Brother/s and/or Sister/s - 3", gridCount = 2, fields = {
			"childName3", "childNameBirthDate3",
			"childNameSchoolEmployee3", "childNameGradeSection3" }, addInfoOnly = true)

})
@ActionButtons( {
	@ActionButton(name = "btnChangeGrade", label = "Change Grade/Course"),
	@ActionButton(name = "btnGenerateCurriculum", label = "Generate Curriculum"),
	@ActionButton(name = "btnPrintTOR", label = "Print TOR"),
	// @ActionButton(name="btnAssessNoEnrollment",
	// label="Auto Assess No Enrollment", parentOnly=false),
	@ActionButton(name = "btnReAdmit", label = "Re Admit") })
@Reports( {
	@template.Report(reportFile = "StudentProfile", reportTitle = "Student Profile", reportSql = ""),
	@template.Report(reportFile = "OrganizationalListSection", reportTitle = "Organizational List", reportSql = ""),
	@template.Report(reportFile = "StudentList", reportTitle = "Official Enrollment List", reportSql = ""),
	@template.Report(reportFile = "Form1", reportTitle = "Form 1", reportSql = ""),
	@template.Report(reportFile = "SchoolProfile", reportTitle = "School Profile", reportSql = ""),
	// @template.Report(reportFile = "RecommendationForm", reportTitle =
	// "Recommendation Form", reportSql = "${personId}"),
	// @template.Report(reportFile = "Id", reportTitle =
	// "Identification Card", reportSql = ""),
	@template.Report(reportFile = "ReportCard", reportTitle = "Report Card", reportSql = ""),
	@template.Report(reportFile = "StudentId", reportTitle = "Student ID", reportSql = "${personId}"),
	@template.Report(reportFile = "StudentIdSingle", reportTitle = "Single", reportSql = "${personId}")
//@template.Report(reportFile = "GoodMoral", reportTitle = "Good Moral Report",
//reportSql = "${personId}")
})
public class StudentCollegeExt extends bean.Student implements Serializable {
	public StudentCollegeExt() {
		college = true;
	}
	
    public static void main(String[] args) {
        view(StudentCollegeExt.class);
    }

	@Override
	public String addWhere() {
		return " WHERE a.college=true ";
	}
	
	@Override
	public String popupSearch(String criteria) {
		return "SELECT a FROM Student a "+addWhere();
	}

	@Override
	public void save() {
		college = true;
		super.save();
	}
	
}
