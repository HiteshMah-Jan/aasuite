/*
 * StudentExt.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

import constants.UserInfo;

import service.util.AbstractIBean;
import service.util.ChartBean;
import springbean.AAAConfig;
import springbean.SchoolConfig;
import springbean.SchoolDefaultProcess;
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
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateLeftRight;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;
import util.PanelUtil;
import bean.accounting.EnrollmentAR;
import bean.accounting.PaymentEnrollment;
import bean.admin.AppConfig;
import bean.extension.StudentSubjectCollegeExt;
import bean.extension.StudentSubjectExt;
import bean.extension.StudentValuesGradingOverrideExt;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.person.StudentSchoolAttended;
import bean.person.StudentSummerSchoolAttended;
import bean.person.StudentValuesGrading;
import bean.reference.Course;
import bean.reference.CourseSubject;
import bean.reference.GradeLevel;
import bean.reference.ScholarshipTable;
import bean.reference.Section;
import bean.reference.Subject;
import template.screen.ChildTemplateDefault;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopup;

/**
 * 
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Person")
@DiscriminatorValue("STUDENT")
@UITemplate(showChart = false, template = TemplateLeftRight.class, gridCount = 4, columnSearch = {
		"schoolYear", "studentNumber", "lastName", "firstName", "middleInitial",
		"gradeLevel", "section", "age" }, criteriaSearch = { "schoolYear",
		"studentNumber", "lastName", "firstName", "middleInitial", "section" }, showImages = true, imageInsideForm = true, showFiles = false, orderBy = "a.lastName")
@ChildRecords(value = {
		// @ChildRecord(title="Address", entity = PersonAddress.class, sql =
		// "SELECT a FROM PersonAddress a WHERE a.personId=${personId}"),
		@ChildRecord(template = ChildTemplateListOnly.class, fieldMapping = {
				"personId", "personId" }, title = "Parents / Guardians", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId=${personId}")
//		@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
//				"personId", "personId" }, title = "Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId=${personId}"),
//        @ChildRecord(template = ChildTemplateListPopupDownButton.class, canNew=false, canDelete=false, fieldMapping = {
//        		"personId", "studentId" }, title = "Values Grades", entity = StudentValuesGradingOverrideExt.class, sql = "SELECT a FROM StudentValuesGrading a WHERE a.studentId=${personId} ORDER BY a.gradeLevel"),
//	    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
//		    	"personId", "studentId" }, title = "Curriculum Subject", canNew=false, canDelete=false, entity = StudentSubjectExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}"),
//        @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
//        		"personId", "studentId" }, title = "Curriculum Subjects-For College Only", canNew=false, canDelete=false, entity = StudentSubjectCollegeExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}")
// @ChildRecord(template = ChildTemplateListOnly.class, fieldMapping = {"seq",
// "paidBy"}, entity = PaymentExt.class, sql =
// "SELECT a FROM Payment a WHERE a.paidBy=${personId}", title =
// "Payments Ledger"),
// @ChildRecord(template = ChildTemplateListPopupDownButton.class,fieldMapping =
// {"personId", "studentId"}, title = "Attendance", entity =
// StudentAttendance.class, sql =
// "SELECT a FROM StudentAttendance a WHERE a.studentId=${personId}")
// @ChildRecord(template = ChildTemplateListOnly.class, title =
// "History Records", entity = Student.class, sql =
// "SELECT a FROM Student a WHERE a.parentId=${personId}")
}, info = { @ParentAddInfo(title = "Student's Other Info", displayFields = {
		"schoolLastAttended", "schoolLastAttendedAddress" }) })
@Displays( {
		@Display(name = "studentNumber", button = "Change", gridFieldWidth = 3,enabled=false),
		@Display(name = "schoolYear", gridFieldWidth = 3, width = -1,enabled=false),
		@Display(name = "course", type = "PopSearch", linktoBean = Course.class, mandatory = true, gridFieldWidth = 3, width = -1,enabled=false),
		@Display(name = "gradeLevel", label = "Grade", type = "PopSearch", linktoBean = GradeLevel.class, mandatory = true, gridFieldWidth = 3, width = -1,enabled=false),
		@Display(name = "section", type = "PopSearch", linktoBean = Section.class, gridFieldWidth = 3, width = -1,enabled=false),
		// @Display(name = "toSection", type = "PopSearch", linktoBean =
		// Section.class, gridFieldWidth = 3, width =-1),

		@Display(name = "gender", gridFieldWidth = 3, label = "Sex", type = "Combo", modelCombo = {
				"MALE", "FEMALE" }, width = -1,enabled=false),
		@Display(name = "status", gridFieldWidth = 3, type = "Combo", modelCombo = {
				"ENROLLED", "NOT ENROLLED", "OFF. DROP", "UNOFF. DROP",
				"FAILED" }, width = -1,enabled=false),
		@Display(name = "addStatus", gridFieldWidth = 3, type = "Combo", modelCombo = {
				"NEW","REGULAR","PROBATIONARY ACADEMIC","PROBATIONARY BEHAVIOR","PROBATIONARY ACADEMIC AND BEHAVIOR", "TRANSFEREES", "BALIK-ARAL",
				"SPED GRADED","REPEATER","MUSLIM","INDIGENOUS PEOPLE"}, width = -1,enabled=false),

		@Display(name = "scholarshipCode", type = "PopSearch", linktoBean = ScholarshipTable.class, gridFieldWidth = 3, width = -1,enabled=false),
		@Display(name = "tempPass", label = "Password", gridFieldWidth = 3, width = -1,enabled=false),

		@Display(name = "lastName", labelTop = true,enabled=false),
		@Display(name = "firstName", labelTop = true,enabled=false),
		@Display(name = "middleInitial", label = "Middle Name", labelTop = true,enabled=false),

		@Display(name = "mobilePhone",enabled=false),
		@Display(name = "email",enabled=false),
		@Display(name = "im1",enabled=false),
		@Display(name = "im2",enabled=false),
		@Display(name = "contactNumber", label = "Tel. #",enabled=false),
		@Display(name = "contactNumber1", label = "Tel. #(other)",enabled=false),

		@Display(name = "birthDate",enabled=false),
		@Display(name = "age", width = 30,enabled=false),
		@Display(name = "placeOfBirth",enabled=false),
		@Display(name = "nationality",enabled=false),
		@Display(name = "ifAlienAcrNo",enabled=false),
		@Display(name = "acrPlaceIssued",enabled=false),
		@Display(name = "acrDateIssued",enabled=false),
		@Display(name = "religion",enabled=false),
		// @Display(name = "otherReligion"),

		@Display(name = "address", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false,enabled=false),
		@Display(name = "barangay", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false,enabled=false),
		@Display(name = "cityProvince", upCase=false,label = "City/Municipality", addInfoOnly = true,enabled=false),
		@Display(name = "zipCode", width = 150, addInfoOnly = true, upCase=false,enabled=false),
		@Display(name = "provincialAddress", label = "Province", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false,enabled=false),

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

		@Display(name = "childName1", label = "Name", addInfoOnly = true,enabled=false),
		@Display(name = "childNameBirthDate1", label = "Date of Birth", addInfoOnly = true,enabled=false),
		@Display(name = "childNameSchoolEmployee1", label = "School/Employment", addInfoOnly = true,enabled=false),
		@Display(name = "childNameGradeSection1", label = "Gr./Yr-Sec", addInfoOnly = true,enabled=false),
		@Display(name = "childName2", label = "Name", addInfoOnly = true,enabled=false),
		@Display(name = "childNameBirthDate2", label = "Date of Birth", addInfoOnly = true,enabled=false),
		@Display(name = "childNameSchoolEmployee2", label = "School/Employment", addInfoOnly = true,enabled=false),
		@Display(name = "childNameGradeSection2", label = "Gr./Yr-Sec", addInfoOnly = true,enabled=false),
		@Display(name = "childName3", label = "Name", addInfoOnly = true,enabled=false),
		@Display(name = "childNameBirthDate3", label = "Date of Birth", addInfoOnly = true,enabled=false),
		@Display(name = "childNameSchoolEmployee3", label = "School/Employment", addInfoOnly = true,enabled=false),
		@Display(name = "childNameGradeSection3", label = "Gr./Yr-Sec", addInfoOnly = true,enabled=false),

		@Display(name = "schoolLastAttended", gridFieldWidth = 3, width = 250, addInfoOnly = true,enabled=false),
		@Display(name = "schoolLastAttendedAddress", label = "Address", gridFieldWidth = 3, width = 250, addInfoOnly = true,enabled=false),

		// @Display(name="registrationDate"),

		@Display(name = "semester", addInfoOnly = true,enabled=false),
		@Display(name = "registrationNumber", addInfoOnly = true,enabled=false),

		@Display(name = "elementarySchool", addInfoOnly = true,enabled=false),
		@Display(name = "highSchool", addInfoOnly = true,enabled=false),
		@Display(name = "elementaryGraduatedDate", addInfoOnly = true,enabled=false),
		@Display(name = "highSchoolGraduatedDate", addInfoOnly = true,enabled=false),
		@Display(name = "individual", addInfoOnly = true,enabled=false),
		@Display(name = "paymentTerm", addInfoOnly = true,enabled=false),
		@Display(name = "salesPersonId1", addInfoOnly = true,enabled=false),
		@Display(name = "salesPersonId2", addInfoOnly = true,enabled=false),
		@Display(name = "salesPersonId3", addInfoOnly = true,enabled=false),
		@Display(name = "salesPersonId4", addInfoOnly = true,enabled=false),
		@Display(name = "salesPersonId5", addInfoOnly = true,enabled=false),
		@Display(name = "territory", addInfoOnly = true,enabled=false),
		@Display(name = "customerClass", addInfoOnly = true,enabled=false),
		@Display(name = "priceLevel", addInfoOnly = true,enabled=false),
		@Display(name = "contactPerson", addInfoOnly = true,enabled=false),
		@Display(name = "employeeHeadcount", addInfoOnly = true,enabled=false),
		@Display(name = "customerHeadcount", addInfoOnly = true,enabled=false),
		@Display(name = "personId", addInfoOnly = true,enabled=false),
		@Display(name = "hiredDate", addInfoOnly = true,enabled=false),
		@Display(name = "shortName", addInfoOnly = true,enabled=false),
		@Display(name = "personType", addInfoOnly = true,enabled=false),

		@Display(name = "maritalStatus", type = "Combo", modelCombo = {
				"SINGLE", "MARRIED" }, addInfoOnly = true,enabled=false),
		@Display(name = "department", addInfoOnly = true,enabled=false),

		@Display(name = "citizenship", addInfoOnly = true,enabled=false),

		@Display(name = "address1", addInfoOnly = true,enabled=false),
		@Display(name = "tinNumber", addInfoOnly = true,enabled=false),
		@Display(name = "sssNumber", addInfoOnly = true,enabled=false),
		@Display(name = "pagibigNumber", addInfoOnly = true,enabled=false),
		// @Display(name="status", addInfoOnly=true),
		@Display(name = "position", addInfoOnly = true,enabled=false),
		@Display(name = "occupation", addInfoOnly = true,enabled=false),
		@Display(name = "mother", addInfoOnly = true,enabled=false),
		@Display(name = "father", addInfoOnly = true,enabled=false),
		@Display(name = "spouse", addInfoOnly = true,enabled=false),
		@Display(name = "userid", addInfoOnly = true,enabled=false),
		// @Display(name = "guardianName", addInfoOnly = true),
		// @Display(name = "guardianOccupation", addInfoOnly = true),
		// @Display(name = "guardianRelationship", addInfoOnly = true),
		// @Display(name = "guardianContactNumber", addInfoOnly = true),
		// @Display(name = "guardianAddress", addInfoOnly = true),

		@Display(name = "streetNumber", addInfoOnly = true,enabled=false),

		@Display(name = "townDistrict", addInfoOnly = true,enabled=false),

		@Display(name = "isActive", addInfoOnly = true,enabled=false),
		@Display(name = "place", addInfoOnly = true,enabled=false),
		@Display(name = "state", addInfoOnly = true,enabled=false),
		@Display(name = "country", addInfoOnly = true,enabled=false),
		@Display(name = "fax", addInfoOnly = true,enabled=false),
		@Display(name = "companyName", addInfoOnly = true,enabled=false),

		@Display(name = "companyTelephoneNumber", addInfoOnly = true,enabled=false),
		@Display(name = "companyAddress", addInfoOnly = true,enabled=false) })
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
//		@ActionButton(name = "btnChangeGrade", label = "Change Grade/Course"),
//		@ActionButton(name = "btnGenerateCurriculum", label = "Generate Curriculum"),
//		@ActionButton(name = "btnPrintTOR", label = "Print TOR"),
//		// @ActionButton(name="btnAssessNoEnrollment",
//		// label="Auto Assess No Enrollment", parentOnly=false),
//		@ActionButton(name = "btnReAdmit", label = "Re Admit")
})
@Reports( {
		@template.Report(reportFile = "StudentProfile", reportTitle = "Students", reportSql = ""),
		@template.Report(reportFile = "OrganizationalListSection", reportTitle = "Org List", reportSql = ""),
//		@template.Report(reportFile = "StudentList", reportTitle = "Official Enrollment List", reportSql = ""),
//		@template.Report(reportFile = "Form1", reportTitle = "Form 1", reportSql = ""),
//		@template.Report(reportFile = "SchoolProfile", reportTitle = "School Profile", reportSql = ""),
//		// @template.Report(reportFile = "RecommendationForm", reportTitle =
//		// "Recommendation Form", reportSql = "${personId}"),
//		// @template.Report(reportFile = "Id", reportTitle =
//		// "Identification Card", reportSql = ""),
//		@template.Report(reportFile = "ReportCardQuarter1", reportTitle = "Card Q1", reportSql = ""),
//        @template.Report(reportFile = "ReportCardQuarter2", reportTitle = "Q2", reportSql = ""),
//        @template.Report(reportFile = "ReportCardQuarter3", reportTitle = "Q3", reportSql = ""),
//		@template.Report(reportFile = "ReportCardQuarter4", reportTitle = "Q4", reportSql = ""),
//		@template.Report(reportFile = "StudentId", reportTitle = "ID", reportSql = "${personId}"),
//		@template.Report(reportFile = "StudentIdSingle", reportTitle = "Single", reportSql = "${personId}"),
		@template.Report(reportFile = "StudentDirectory", reportTitle = "Directory", reportSql = "${personId}")
// @template.Report(reportFile = "GoodMoral", reportTitle = "Good Moral Report",
// reportSql = "${personId}")
})
public class Student201Ext extends bean.Student implements Serializable {

    public static void main(String[] args) {
        view(Student201Ext.class);
    }
}
