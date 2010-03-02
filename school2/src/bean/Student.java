/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateLeftRight;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;
import util.PanelUtil;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentPlan;
import bean.admin.AppConfig;
import bean.extension.EnrollmentGradeExt;
import bean.extension.StudentSubjectCollegeExt;
import bean.extension.StudentSubjectExt;
import bean.extension.StudentValuesGradingOverrideExt;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.reference.Course;
import bean.reference.CourseSubject;
import bean.reference.GradeLevel;
import bean.reference.ScholarshipTable;
import bean.reference.Section;
import bean.reference.Subject;
import constants.UserInfo;

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
		"studentNumber", "lastName", "firstName", "middleInitial", "section" }, showImages = true, imageInsideForm = true, showFiles = true, orderBy = "a.lastName")
@ChildRecords(value = {
		// @ChildRecord(title="Address", entity = PersonAddress.class, sql =
		// "SELECT a FROM PersonAddress a WHERE a.personId=${personId}"),
		@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
				"personId", "personId" }, title = "Parents / Guardians", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId=${personId}"),
		@ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
				"personId", "personId" }, title = "Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId=${personId}"),
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, canNew=false, canDelete=false, fieldMapping = {
        		"personId", "studentId" }, title = "Values Grades", entity = StudentValuesGradingOverrideExt.class, sql = "SELECT a FROM StudentValuesGrading a WHERE a.studentId=${personId} ORDER BY a.gradeLevel"),
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, autoResizeTable=false,
        		title = "Enrollment", entity = EnrollmentGradeExt.class, sql = "SELECT a FROM Enrollment a WHERE a.studentId=${personId}"),
	    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
		    	"personId", "studentId" }, title = "Curriculum Subject", canNew=false, canDelete=false, entity = StudentSubjectExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}"),
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
        		"personId", "studentId" }, title = "Curriculum Subjects-For College Only", canNew=false, canDelete=false, entity = StudentSubjectCollegeExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}")
}, info = { @ParentAddInfo(title = "Student's Other Info", displayFields = {
		"schoolLastAttended", "schoolLastAttendedAddress" }) })
@Displays( {
		@Display(name = "studentNumber", button = "Change", gridFieldWidth = 3),
		@Display(name = "schoolYear", gridFieldWidth = 3, width = -1),
		@Display(name = "course", type = "PopSearch", linktoBean = Course.class, mandatory = true, gridFieldWidth = 3, width = -1),
		@Display(name = "gradeLevel", label = "Grade", type = "PopSearch", linktoBean = GradeLevel.class, mandatory = true, gridFieldWidth = 3, width = -1),
		@Display(name = "section", type = "PopSearch", linktoBean = Section.class, gridFieldWidth = 3, width = -1),
		// @Display(name = "toSection", type = "PopSearch", linktoBean =
		// Section.class, gridFieldWidth = 3, width =-1),

		@Display(name = "gender", gridFieldWidth = 3, label = "Sex", type = "Combo", modelCombo = {
				"MALE", "FEMALE" }, width = -1),
		@Display(name = "status", gridFieldWidth = 3, type = "Combo", modelCombo = {
				"ENROLLED", "NOT ENROLLED", "OFF. DROP", "UNOFF. DROP",
				"FAILED" }, width = -1),
		@Display(name = "addStatus", gridFieldWidth = 3, type = "Combo", modelCombo = {
				"NEW","REGULAR","PROBATIONARY ACADEMIC","PROBATIONARY BEHAVIOR","PROBATIONARY ACADEMIC AND BEHAVIOR", "TRANSFEREES", "BALIK-ARAL",
				"SPED GRADED","REPEATER","MUSLIM","INDIGENOUS PEOPLE"}, width = -1),

		@Display(name = "scholarshipCode", type = "PopSearch", linktoBean = ScholarshipTable.class, gridFieldWidth = 3, width = -1),
		@Display(name = "tempPass", label = "Password", gridFieldWidth = 3, width = -1),
		@Display(name = "promotedTo", type = "PopSearch", linktoBean = GradeLevel.class, gridFieldWidth = 3, width = -1),

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

		@Display(name = "address", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false),
		@Display(name = "barangay", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false),
		@Display(name = "cityProvince", upCase=false,label = "City/Municipality", addInfoOnly = true),
		@Display(name = "zipCode", width = 150, addInfoOnly = true, upCase=false),
		@Display(name = "provincialAddress", label = "Province", gridFieldWidth = 3, width = -1, addInfoOnly = true, upCase=false),

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
		@ActionButton(name = "btnGenerateCurriculum", label = "Generate Curriculum"),
		@ActionButton(name = "btnPrintTOR", label = "Print TOR"),
		// @ActionButton(name="btnAssessNoEnrollment",
		// label="Auto Assess No Enrollment", parentOnly=false),
		@ActionButton(name = "btnReAdmit", label = "Re Admit") })
@Reports( {
		@template.Report(reportFile = "StudentProfile", reportTitle = "Students", reportSql = ""),
		@template.Report(reportFile = "OrganizationalListSection", reportTitle = "Org List", reportSql = ""),
//		@template.Report(reportFile = "StudentList", reportTitle = "Official Enrollment List", reportSql = ""),
		@template.Report(reportFile = "Form1", reportTitle = "Form 1", reportSql = ""),
		@template.Report(reportFile = "SchoolProfile", reportTitle = "School Profile", reportSql = ""),
		// @template.Report(reportFile = "RecommendationForm", reportTitle =
		// "Recommendation Form", reportSql = "${personId}"),
		// @template.Report(reportFile = "Id", reportTitle =
		// "Identification Card", reportSql = ""),
		@template.Report(reportFile = "ReportCardQuarter1", reportTitle = "Card Q1", reportSql = ""),
        @template.Report(reportFile = "ReportCardQuarter2", reportTitle = "Q2", reportSql = ""),
        @template.Report(reportFile = "ReportCardQuarter3", reportTitle = "Q3", reportSql = ""),
		@template.Report(reportFile = "ReportCardQuarter4", reportTitle = "Q4", reportSql = ""),
		@template.Report(reportFile = "StudentId", reportTitle = "ID", reportSql = "${personId}"),
		@template.Report(reportFile = "StudentIdSingle", reportTitle = "Single", reportSql = "${personId}"),
		@template.Report(reportFile = "StudentDirectory", reportTitle = "Directory", reportSql = "${personId}")
// @template.Report(reportFile = "GoodMoral", reportTitle = "Good Moral Report",
// reportSql = "${personId}")
})
public class Student extends Customer implements Serializable {

	@Override
	public void delete() {
		if (UserInfo.canDeleteStudent()) {
			super.delete();
		}
		else {
			PanelUtil.showError(null, "Only Administrator or 'DELETE STUDENT' duty code can delete Student records.");
		}
	}

	public Student() {
		personType = "STUDENT";
	}

	@Column(name = "semester", length = 5)
	public String semester;
	@Column(name = "schoolYear", length = 10)
	public String schoolYear;
	@Column(name = "latestSchoolYear")
	public String latestSchoolYear;
	@Column(name = "scholarshipCode")
	public String scholarshipCode;
	@Column(name = "registrationNumber")
	public String registrationNumber;
	@Column(name = "registrationDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date registrationDate;
	@Column(name = "nationality")
	public String nationality;
	@Column(name = "course", length = 30)
	public String course;
	@Column(name = "toSection")
	public String toSection;
	@Column(name = "elementarySchool")
	public String elementarySchool;
	@Column(name = "highSchool")
	public String highSchool;
	@Column(name = "elementaryGraduatedDate")
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date elementaryGraduatedDate;
	@Column(name = "highSchoolGraduatedDate")
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date highSchoolGraduatedDate;
	@Column(name = "schoolLastAttended")
	public String schoolLastAttended;
	@Column(name = "schoolLastAttendedAddress")
	public String schoolLastAttendedAddress;
	@Column(name = "yearStartedInThisSchool", length = 10)
	public String yearStartedInThisSchool;
	@Column(name = "yearsInThisSchool")
	public int yearsInThisSchool;
	@Column(name = "studentCountThisSCYear")
	public int studentCountThisSCYear;
	@Column(name = "yearsLeftInThisSchool")
	public int yearsLeftInThisSchool;
	@Column(name = "livesWith")
	public String livesWith;
	@Column(name = "schoolBusNumber")
	public String schoolBusNumber;
	@Column(name = "busOperator")
	public String busOperator;
	@Column(name = "busOperatorTelephoneNumber")
	public String busOperatorTelephoneNumber;
	@Column(name = "homeType")
	public String homeType;
	@Column(name = "languagesAtHome")
	public String languagesAtHome;
	@Column(name = "facilitiesForHomeStudy")
	public String facilitiesForHomeStudy;
	@Column(name = "tutorAtHome")
	public String tutorAtHome;
	@Column(name = "obstacleToStudy")
	public String obstacleToStudy;
	@Column(name = "relativeAtHome")
	public String relativeAtHome;
	@Column(name = "schoolWorkSup1")
	public String schoolWorkSup1;
	@Column(name = "schoolWorkSup2")
	public String schoolWorkSup2;
	@Column(name = "schoolWorkSup3")
	public String schoolWorkSup3;
	@Column(name = "schoolWorkSup4")
	public String schoolWorkSup4;
	@Column(name = "plan")
	public String plan;
	@Column(name = "oldStudentNumber", length = 20)
	public String oldStudentNumber;
	@Column(name = "childName1")
	public String childName1;
	@Column(name = "childName2")
	public String childName2;
	@Column(name = "childName3")
	public String childName3;
	@Column(name = "childNameBirthDate1")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childNameBirthDate1;
	@Column(name = "childNameBirthDate2")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childNameBirthDate2;
	@Column(name = "childNameBirthDate3")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childNameBirthDate3;
	@Column(name = "childNameSchoolEmployee1")
	public String childNameSchoolEmployee1;
	@Column(name = "childNameSchoolEmployee2")
	public String childNameSchoolEmployee2;
	@Column(name = "childNameSchoolEmployee3")
	public String childNameSchoolEmployee3;
	@Column(name = "childNameGradeSection1")
	public String childNameGradeSection1;
	@Column(name = "childNameGradeSection2")
	public String childNameGradeSection2;
	@Column(name = "childNameGradeSection3")
	public String childNameGradeSection3;

	@Column(name = "ifAlienAcrNo")
	public String ifAlienAcrNo;
	@Column(name = "acrPlaceIssued")
	public String acrPlaceIssued;
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "acrDateIssued")
	public Date acrDateIssued;
	@Column(name = "college")
	public boolean college;
        @Column(name = "promotedTo")
	public String promotedTo;

    public String getPromotedTo() {
        return promotedTo;
    }

    public void setPromotedTo(String promotedTo) {
        this.promotedTo = promotedTo;
    }

        
	public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public String getLatestSchoolYear() {
		return latestSchoolYear;
	}

	public void setLatestSchoolYear(String latestSchoolYear) {
		this.latestSchoolYear = latestSchoolYear;
	}

	public Date getAcrDateIssued() {
		return acrDateIssued;
	}

	public void setAcrDateIssued(Date acrDateIssued) {
		this.acrDateIssued = acrDateIssued;
	}

	public String getAcrPlaceIssued() {
		return acrPlaceIssued;
	}

	public void setAcrPlaceIssued(String acrPlaceIssued) {
		this.acrPlaceIssued = acrPlaceIssued;
	}

	public String getChildName1() {
		return childName1;
	}

	public void setChildName1(String childName1) {
		this.childName1 = childName1;
	}

	public String getChildName2() {
		return childName2;
	}

	public void setChildName2(String childName2) {
		this.childName2 = childName2;
	}

	public String getChildName3() {
		return childName3;
	}

	public void setChildName3(String childName3) {
		this.childName3 = childName3;
	}

	public Date getChildNameBirthDate1() {
		return childNameBirthDate1;
	}

	public void setChildNameBirthDate1(Date childNameBirthDate1) {
		this.childNameBirthDate1 = childNameBirthDate1;
	}

	public Date getChildNameBirthDate2() {
		return childNameBirthDate2;
	}

	public void setChildNameBirthDate2(Date childNameBirthDate2) {
		this.childNameBirthDate2 = childNameBirthDate2;
	}

	public Date getChildNameBirthDate3() {
		return childNameBirthDate3;
	}

	public void setChildNameBirthDate3(Date childNameBirthDate3) {
		this.childNameBirthDate3 = childNameBirthDate3;
	}

	public String getChildNameGradeSection1() {
		return childNameGradeSection1;
	}

	public void setChildNameGradeSection1(String childNameGradeSection1) {
		this.childNameGradeSection1 = childNameGradeSection1;
	}

	public String getChildNameGradeSection2() {
		return childNameGradeSection2;
	}

	public void setChildNameGradeSection2(String childNameGradeSection2) {
		this.childNameGradeSection2 = childNameGradeSection2;
	}

	public String getChildNameGradeSection3() {
		return childNameGradeSection3;
	}

	public void setChildNameGradeSection3(String childNameGradeSection3) {
		this.childNameGradeSection3 = childNameGradeSection3;
	}

	public String getChildNameSchoolEmployee1() {
		return childNameSchoolEmployee1;
	}

	public void setChildNameSchoolEmployee1(String childNameSchoolEmployee1) {
		this.childNameSchoolEmployee1 = childNameSchoolEmployee1;
	}

	public String getChildNameSchoolEmployee2() {
		return childNameSchoolEmployee2;
	}

	public void setChildNameSchoolEmployee2(String childNameSchoolEmployee2) {
		this.childNameSchoolEmployee2 = childNameSchoolEmployee2;
	}

	public String getChildNameSchoolEmployee3() {
		return childNameSchoolEmployee3;
	}

	public void setChildNameSchoolEmployee3(String childNameSchoolEmployee3) {
		this.childNameSchoolEmployee3 = childNameSchoolEmployee3;
	}

	public String getIfAlienAcrNo() {
		return ifAlienAcrNo;
	}

	public void setIfAlienAcrNo(String ifAlienAcrNo) {
		this.ifAlienAcrNo = ifAlienAcrNo;
	}

	public String getSchoolLastAttendedAddress() {
		return schoolLastAttendedAddress;
	}

	public void setSchoolLastAttendedAddress(String schoolLastAttendedAddress) {
		this.schoolLastAttendedAddress = schoolLastAttendedAddress;
	}

	public String getOldStudentNumber() {
		return oldStudentNumber;
	}

	public void setOldStudentNumber(String oldStudentNumber) {
		this.oldStudentNumber = oldStudentNumber;
	}

	public int getStudentCountThisSCYear() {
		return studentCountThisSCYear;
	}

	public void setStudentCountThisSCYear(int studentCountThisSCYear) {
		this.studentCountThisSCYear = studentCountThisSCYear;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getBusOperator() {
		return busOperator;
	}

	public void setBusOperator(String busOperator) {
		this.busOperator = busOperator;
	}

	public String getBusOperatorTelephoneNumber() {
		return busOperatorTelephoneNumber;
	}

	public void setBusOperatorTelephoneNumber(String busOperatorTelephoneNumber) {
		this.busOperatorTelephoneNumber = busOperatorTelephoneNumber;
	}

	public String getFacilitiesForHomeStudy() {
		return facilitiesForHomeStudy;
	}

	public void setFacilitiesForHomeStudy(String facilitiesForHomeStudy) {
		this.facilitiesForHomeStudy = facilitiesForHomeStudy;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getLanguagesAtHome() {
		return languagesAtHome;
	}

	public void setLanguagesAtHome(String languagesAtHome) {
		this.languagesAtHome = languagesAtHome;
	}

	public String getLivesWith() {
		return livesWith;
	}

	public void setLivesWith(String livesWith) {
		this.livesWith = livesWith;
	}

	public String getObstacleToStudy() {
		return obstacleToStudy;
	}

	public void setObstacleToStudy(String obstacleToStudy) {
		this.obstacleToStudy = obstacleToStudy;
	}

	public String getRelativeAtHome() {
		return relativeAtHome;
	}

	public void setRelativeAtHome(String relativeAtHome) {
		this.relativeAtHome = relativeAtHome;
	}

	public String getSchoolBusNumber() {
		return schoolBusNumber;
	}

	public void setSchoolBusNumber(String schoolBusNumber) {
		this.schoolBusNumber = schoolBusNumber;
	}

	public String getSchoolWorkSup1() {
		return schoolWorkSup1;
	}

	public void setSchoolWorkSup1(String schoolWorkSup1) {
		this.schoolWorkSup1 = schoolWorkSup1;
	}

	public String getSchoolWorkSup2() {
		return schoolWorkSup2;
	}

	public void setSchoolWorkSup2(String schoolWorkSup2) {
		this.schoolWorkSup2 = schoolWorkSup2;
	}

	public String getSchoolWorkSup3() {
		return schoolWorkSup3;
	}

	public void setSchoolWorkSup3(String schoolWorkSup3) {
		this.schoolWorkSup3 = schoolWorkSup3;
	}

	public String getSchoolWorkSup4() {
		return schoolWorkSup4;
	}

	public void setSchoolWorkSup4(String schoolWorkSup4) {
		this.schoolWorkSup4 = schoolWorkSup4;
	}

	public String getTutorAtHome() {
		return tutorAtHome;
	}

	public void setTutorAtHome(String tutorAtHome) {
		this.tutorAtHome = tutorAtHome;
	}

	public String getYearStartedInThisSchool() {
		return yearStartedInThisSchool;
	}

	public void setYearStartedInThisSchool(String yearStartedInThisSchool) {
		this.yearStartedInThisSchool = yearStartedInThisSchool;
	}

	public int getYearsInThisSchool() {
		return yearsInThisSchool;
	}

	public void setYearsInThisSchool(int yearsInThisSchool) {
		this.yearsInThisSchool = yearsInThisSchool;
	}

	public int getYearsLeftInThisSchool() {
		return yearsLeftInThisSchool;
	}

	public void setYearsLeftInThisSchool(int yearsLeftInThisSchool) {
		this.yearsLeftInThisSchool = yearsLeftInThisSchool;
	}

	public String getSchoolLastAttended() {
		return schoolLastAttended;
	}

	public void setSchoolLastAttended(String schoolLastAttended) {
		this.schoolLastAttended = schoolLastAttended;
	}

	public String getToSection() {
		return toSection;
	}

	public void setToSection(String toSection) {
		this.toSection = toSection;
	}

	public String getElementarySchool() {
		return elementarySchool;
	}

	public void setElementarySchool(String elementarySchool) {
		this.elementarySchool = elementarySchool;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public Date getElementaryGraduatedDate() {
		return elementaryGraduatedDate;
	}

	public void setElementaryGraduatedDate(Date elementaryGraduatedDate) {
		this.elementaryGraduatedDate = elementaryGraduatedDate;
	}

	public Date getHighSchoolGraduatedDate() {
		return highSchoolGraduatedDate;
	}

	public void setHighSchoolGraduatedDate(Date highSchoolGraduatedDate) {
		this.highSchoolGraduatedDate = highSchoolGraduatedDate;
	}

	public void setDefaultStudentNumber() {
		// set student id
		try {
			if (studentNumber == null || studentNumber.trim().isEmpty()) {
				this.registrationDate = constants.Constants.useDate;
				schoolYear = SchoolConfig.getSchoolYear();
				String yy = schoolYear.substring(schoolYear.indexOf("-")+1);
				Object obj = singleColumn("SELECT MAX(studentNumber) FROM Person WHERE personType='STUDENT' AND studentNumber LIKE '"
							+ yy + "-%'");
				int count = 0;
				if (obj != null) {
					try {
						String ycount = obj.toString(); 
						ycount = ycount.substring(ycount.indexOf("-")+1);
						count = Integer.parseInt(ycount);
					} catch (Exception e) {
					}
				}
				if (count > 1000) {
					studentNumber = yy + "-" + (count + 1);
				}
				else {
					studentNumber = yy + "-" + (count + 1 + 1000);
				}
			}
			userid = studentNumber;
			tempPass = studentNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getScholarshipCode() {
		return scholarshipCode;
	}

	public void setScholarshipCode(String scholarshipCode) {
		this.scholarshipCode = scholarshipCode;
	}

	public java.lang.String getSemester() {
		return semester;
	}

	public void setSemester(java.lang.String semester) {
		this.semester = semester;
	}

	public java.lang.String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(java.lang.String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public java.lang.String getNationality() {
		return nationality;
	}

	public void setNationality(java.lang.String nationality) {
		this.nationality = nationality;
	}

	public java.lang.String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(java.lang.String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public java.lang.String getCourse() {
		return course;
	}

	public void setCourse(java.lang.String course) {
		this.course = course;
	}

	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(java.util.Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@PostPersist
	public void postPersist() {
		// SchoolDefaultProcess.getProcess().doProcess(SchoolDefaultProcess.CREATE_ALL_SUBJECTS,
		// this);
		// SchoolDefaultProcess.getProcess().doProcess(SchoolDefaultProcess.ADD_STUDENT_MODULES,
		// this);
	}

	@PostUpdate
	public void postUpdate() {
		// SchoolDefaultProcess.getProcess().doProcess(SchoolDefaultProcess.CREATE_ALL_SUBJECTS,
		// this);
	}

	public List extractAllSubjects() {
		return list("SELECT a FROM StudentSubject a WHERE a.studentId=",
				personId, " ORDER BY a.subject");
	}

	public boolean isScholar() {
		return scholarshipCode != null && !scholarshipCode.isEmpty();
	}

	public double getScholarshipDiscount(double enrollmentAmount) {
		if (isScholar()) {
			return 0;
		}
		ScholarshipTable tbl = (ScholarshipTable) firstRecord("SELECT a FROM ScholarshipTable a WHERE a.code='"
				+ scholarshipCode + "'");
		if (tbl != null) {
			return tbl.getScholarshipDiscount(enrollmentAmount);
		}
		return 0;
	}

	public static String getDefaultStudentNumber(int number) {
		// set student id
		String yy = DateUtil.formatDate(constants.Constants.useDate, "yy");
		return BeanUtil.concat(yy, "_", (number + 1 + 10000));
	}

	public static void main(String[] args) {
		// view(Student.class);
		AAAConfig.getInstance();
		// Student stud = (Student)
		// DBClient.getFirstRecord("SELECT a FROM Student a");
		// BeanFormDisplayerPage.displayBeanForm(stud);
		view(Student.class);
	}

	@Override
	public Vector allChart() {
		java.util.Vector<ChartBean> vec = new java.util.Vector();
		vec
				.add(ChartBean
						.getBarInstance(
								this,
								"Total Student Per SY",
								"SELECT a.schoolYear, a.course, COUNT(a.personId) FROM Student a GROUP BY a.schoolYear, a.course"));
		vec
				.add(ChartBean
						.getPieInstance(this, "Total Per Gender",
								"SELECT a.gender, COUNT(a.personId) FROM Student a GROUP BY a.gender"));
		vec
				.add(ChartBean
						.getPieInstance(this, "Total Per Age",
								"SELECT a.age, COUNT(a.personId) FROM Student a GROUP BY a.age"));
		return vec;
	}

	@Override
	public boolean canCache() {
		return true;
	}

	@Override
	protected void runSetup() {
		createStudentObj("Estabillo", "Bong", "H3_ASSUMPTION", "H4_ASSUMPTION",
				"H3", null).save();
		createStudentObj("Baluyot", "Eboy", "H3_ASSUMPTION", "H4_ASSUMPTION",
				"H3", null).save();
	}

	public Student createStudentObj(String lastName, String firstName,
			String section, String toSection, String gradeLevel,
			String scholarship) {
		Student stud = new Student();
		stud.lastName = lastName;
		stud.firstName = firstName;
		stud.section = section;
		stud.toSection = toSection;
		stud.scholarshipCode = scholarship;
		stud.gradeLevel = gradeLevel;
		stud.setDefaultStudentNumber();
		return stud;
	}

	public Enrollment extractLastEnrollment(String schoolYear) {
		if (personId == null || personId == 0)
			return null;
		return (Enrollment) AbstractIBean
				.firstRecord("SELECT a FROM Enrollment a WHERE a.studentId="
						+ personId + " AND a.schoolYear='" + schoolYear
						+ "' ORDER BY a.seq DESC");
	}

	public List extractBackAccount(String beforeSchoolYear) {
		List<PaymentEnrollment> lst = DBClient
				.getList("SELECT a FROM PaymentEnrollment a WHERE a.schoolYear<'"
						+ beforeSchoolYear
						+ "' AND a.paidBy="
						+ personId
						+ " ORDER BY a.schoolYear DESC");
		List lstBack = new ArrayList();
		double totalBack = 0;
		String useYear = "";
		if (lst != null && lst.size() > 0) {
			useYear = lst.get(0).schoolYear;
		}
		for (PaymentEnrollment p : lst) {
			if (p.schoolYear.equals(useYear) && !p.paid) {
				totalBack += p.overallAmountDue;
			}
		}
		lstBack.add(totalBack);
		lstBack.add(0);
		lstBack.add(useYear);
		return lstBack;
	}

	public void assessStudent(String schoolYear, GradeLevel lvl, String plan, double backAccount) {
		SchoolDefaultProcess s = new SchoolDefaultProcess();
		String sql = "SELECT a FROM Enrollment a WHERE a.studentId=" + personId
				+ " AND a.gradeLevel='" + lvl.code + "'";
		Enrollment e = (Enrollment) DBClient.getFirstRecord(sql);
		if (e==null) {
			e = new Enrollment();
			e.studentId = personId;
			e.paymentPlanType = plan;
			e.gradeLevel = lvl.code;
			e.schoolYear = schoolYear;
			e.save();
		}
		e.paymentPlanType = plan;
		e.gradeLevel = lvl.code;
		e.schoolYear = schoolYear;
		
        PaymentPlan cal = s.extractPaymentPlan(e);
		if (cal==null) {
			PanelUtil.showError(null, "Payment Plan for "+e.gradeLevel + "-" + plan + " not found.");
			return;
		}
		s.setupMisc(e, cal);
		e.save();
		if (e.college) {
			s.createPayment(e, e.miscellaneousFee, e.totalAmount);
		}
		else {
			s.createPayment(e);
		}
	}

	public void assessStudentNoPayment(String schoolYear, String section) {
		SchoolDefaultProcess s = new SchoolDefaultProcess();
		String sql = "SELECT a FROM Enrollment a WHERE a.studentId=" + personId
				+ " AND a.schoolYear='" + schoolYear + "'";
		Enrollment e = (Enrollment) DBClient.getFirstRecord(sql);
		if (e == null || e.isEmptyKey()) {
			e = new Enrollment();
			e.studentId = personId;
			e.schoolYear = schoolYear;
		}
		e.section = section;
		e.gradeLevel = gradeLevel;
		e.save();
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearchUseEntity(criteria, "Student", "lastName",
				"firstName", "studentNumber");
	}

	@Override
	public void save() {
		// get gradelevel from section
		if (section != null && !section.isEmpty()) {
			if (gradeLevel == null || gradeLevel.isEmpty()) {
				Section sec = (Section) Section.extractObject(Section.class.getSimpleName(), section);
				gradeLevel = sec.gradeLevel;
			}
		}
		if (course == null && gradeLevel != null && !gradeLevel.isEmpty()) {
			GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), gradeLevel);
			college = lvl.college;
			course = lvl.course;
		}
		if (course!=null && course.startsWith("B")) {
			college = true;
		}
		if (!isEmptyKey()) {
//			we can get the latest enrollment schoolyear
			try {
				if ("ENROLLED".equals(status)) {
					String sc = DBClient.getSingleColumn("SELECT a.schoolYear FROM Enrollment a WHERE a.studentId="+personId+" ORDER BY a.schoolYear DESC").toString();
					latestSchoolYear = sc;
				}
			}
			catch (Exception e) {
			}
		}
		if (schoolYear==null || schoolYear.isEmpty()) {
			schoolYear = AppConfig.getSchoolYear();
		}
		personType = "STUDENT";
		super.save();
	}

	public void updateEnrollmentSection() {
		Enrollment e = (Enrollment) firstRecord("SELECT a FROM Enrollment a WHERE a.studentId="
				+ personId + " AND a.gradeLevel='"+gradeLevel+"'");
		if (e != null) {
			e.section = section;
			e.save();
			new SchoolDefaultProcess().updateSchedules(e);
		}
	}

	@Override
	public void setupIndex() {
		AAAConfig.getInstance();
		// if (PanelUtil.showPrompt(null,
		// "Would you like to setup curriculum? This will take several minutes."))
		// {
		List lst = new ArrayList();
		lst.add(Course.createCourseObj("PRESCHOOL", "PRESCHOOL"));
		lst.add(Course.createCourseObj("GRADE SCHOOL", "GRADE SCHOOL"));
		lst.add(Course.createCourseObj("HIGH SCHOOL", "HIGH SCHOOL"));

		if (!DBClient.exist("SELECT a FROM GradeLevel a")) {
			lst.add(GradeLevel.createGradeLevelObj("P1", "PRESCHOOL", 55378, 3050, 0));
			lst.add(GradeLevel.createGradeLevelObj("P2", "PRESCHOOL", 55378, 3050,0));
			lst.add(GradeLevel.createGradeLevelObj("N1", "PRESCHOOL", 35000, 6270,0));
			lst.add(GradeLevel.createGradeLevelObj("N2", "PRESCHOOL", 35000, 6270,0));
			lst.add(GradeLevel.createGradeLevelObj("K1", "PRESCHOOL", 35000, 6300,0));
			lst.add(GradeLevel.createGradeLevelObj("K2", "PRESCHOOL", 35000, 6300,0));
			lst.add(GradeLevel.createGradeLevelObj("G1", "GRADE SCHOOL", 46535,10190, 0));
			lst.add(GradeLevel.createGradeLevelObj("G2", "GRADE SCHOOL", 44826,10190, 0));
			lst.add(GradeLevel.createGradeLevelObj("G3", "GRADE SCHOOL", 44826,11190, 0));
			lst.add(GradeLevel.createGradeLevelObj("G4", "GRADE SCHOOL", 44826,10190, 0));
			lst.add(GradeLevel.createGradeLevelObj("G5", "GRADE SCHOOL", 46434,12040, 0));
			lst.add(GradeLevel.createGradeLevelObj("G6", "GRADE SCHOOL", 46434,12040, 0));
			lst.add(GradeLevel.createGradeLevelObj("G7", "GRADE SCHOOL", 46434,12040, 0));
			lst.add(GradeLevel.createGradeLevelObj("H1", "HIGH SCHOOL", 55279,12840, 0));
			lst.add(GradeLevel.createGradeLevelObj("H2", "HIGH SCHOOL", 52129,12840, 0));
			lst.add(GradeLevel.createGradeLevelObj("H3", "HIGH SCHOOL", 52419,13040, 0));
			lst.add(GradeLevel.createGradeLevelObj("H4", "HIGH SCHOOL", 52419,17640, 0));
		}

		for (int i = 1; i <= 2; i++) {
			lst.add(Subject.createSubjectObj("PRESCHOOL", "SCIENCE", "K", i));
			lst.add(Subject.createSubjectObj("PRESCHOOL", "MATHEMATICS", "K", i));
			lst.add(Subject.createSubjectObj("PRESCHOOL", "ENGLISH", "K", i));
			lst.add(Subject.createSubjectObj("PRESCHOOL", "READING", "K", i));
			lst.add(Subject.createSubjectObj("PRESCHOOL", "WRITING", "K", i));
		}

		boolean b = DBClient.exist("SELECT a FROM Subject a");
		if (!b) {
			for (int i = 1; i <= 6; i++) {
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "FILIPINO", "G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "ENGLISH","G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "SCIENCE","G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "MATHEMATICS","G", i));

				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "RELIGION", "G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "SOCIAL", "G", i));

				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "MUSIC", "G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "ARTS", "G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "PE", "G", i));
				lst.add(Subject.createSubjectObj("GRADE SCHOOL", "COMPUTER", "G", i));

				if (i == 5 || i == 6) {
					lst.add(Subject.createSubjectObj("GRADE SCHOOL", "TLE",	"G", i));
				}
			}

			for (int i = 1; i <= 4; i++) {
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "FILIPINO",	"H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "ENGLISH", "H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "SCIENCE", "H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "MATHEMATICS", "H",	i));

				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "RELIGION", "H",i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "SOCIAL", "H", i));

				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "TLE", "H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "COMPUTER", "H",i));

				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "PE", "H",i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "HEALTH","H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "MUSIC","H", i));
				lst.add(Subject.createSubjectObj("HIGH SCHOOL", "ARTS","H", i));
			}
		}
		DBClient.persistBean(lst);

		CourseSubject hasCurr = (CourseSubject) DBClient.getFirstRecord("SELECT a FROM CourseSubject a");
		if (hasCurr == null || hasCurr.isEmptyKey()) {
			List<Course> ls = Course.extractCacheListBeans(Course.class);
			List<CourseSubject> lstSub = new ArrayList<CourseSubject>();
			for (Course c : ls) {
				List<Subject> lstCur = selectListCache("SELECT a FROM Subject a WHERE a.course='"+ c.code + "'");
				for (Subject subject : lstCur) {
					for (int i = 1; i <= 6; i++) {
						if (subject.gradeLevel.contains(i + "")) {
							CourseSubject cs = new CourseSubject();
							cs.course = c.code;
							cs.subject = subject.code;
							cs.preferredYear = subject.gradeLevel;
							cs.weight = subject.unit;
							lstSub.add(cs);
						}
					}
				}
			}
			DBClient.persistBean((List) lstSub);
		}
		List<Student> tmp = DBClient.getList("SELECT a FROM Student a WHERE a.gradeLevelDesc IS NULL OR a.course IS NULL OR a.schoolYear IS NULL");
		if (tmp != null) {
			for (Student s : tmp) {
				if (s.section != null) {
					lst.add(s);
				}
			}
			DBClient.persistBean(lst);
		}
		DBClient.runSQLNative("update person set persontype='STUDENT' where personType IS NULL OR personType=''");
		new SchoolDefaultProcess().setupPaymentPlan();
		// }
		runUniqueIndex(20, "lastName", "firstName", "middleInitial");
	}
}
