/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
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
import template.screen.TemplateTabSinglePage;
import template.screen.TemplateTabSinglePageLeftRight;
import util.PanelUtil;
import bean.accounting.Payment;
import bean.embedded.EmbeddedAddress;
import bean.embedded.EmbeddedChildren;
import bean.embedded.EmbeddedContact;
import bean.embedded.EmbeddedEducation;
import bean.embedded.EmbeddedGuardian;
import bean.embedded.EmbeddedPersonalReference;
import bean.extension.EnrollmentGradeExt;
import bean.extension.StudentSubjectExt;
import bean.extension.StudentValuesGradingOverrideExt;
import bean.person.StudentSubjectTaken;
import bean.reference.GradeLevel;
import constants.UserInfo;

/**
 * 
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Student")
@UITemplate(showChart = false, template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {
		"schoolYear", "studentNumber", "lastName", "firstName", "middleInitial",
		"gradeLevel", "section", "age" }, criteriaSearch = { "schoolYear",
		"studentNumber", "lastName", "firstName", "middleInitial", "section" }, showImages = true, imageInsideForm = true, showFiles = true, orderBy = "a.lastName")
@ChildRecords(value = {
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, canNew=false, canDelete=false, fieldMapping = {
        		"seq", "studentId" }, title = "Values Grades", entity = StudentValuesGradingOverrideExt.class, sql = "SELECT a FROM StudentValuesGrading a WHERE a.studentId=${seq} ORDER BY a.gradeLevel"),
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, autoResizeTable=false,
        		title = "Enrollment", entity = EnrollmentGradeExt.class, sql = "SELECT a FROM Enrollment a WHERE a.studentId=${seq}"),
	    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
		    	"seq", "studentId" }, title = "Curriculum Subject", canNew=false, canDelete=false, entity = StudentSubjectExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${seq}"),
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
        		"seq", "studentId" }, title = "Subjects Taken", canNew=false, canDelete=false, entity = StudentSubjectTaken.class, sql = "SELECT a FROM StudentSubjectTaken a WHERE a.studentId=${seq}")
}, info = { 
		@ParentAddInfo(title = "Other Info", displayFields = {"toSection","plan","age","placeOfBirth","status","addStatus","semester","scholarshipCode","registrationDate","promotedTo" }),
		@ParentAddInfo(title = "Address", displayFields = {"address" }),
		@ParentAddInfo(title = "Contact", displayFields = {"contact" }),
		@ParentAddInfo(title = "Education", displayFields = {"education" }),
		@ParentAddInfo(title = "Children/Siblings", displayFields = {"children" }),
		@ParentAddInfo(title = "Guardian", displayFields = {"guardian" })
})
@Displays({
	@Display(name="schoolYear"),
	@Display(name="studentNumber"),
	@Display(name="lastName"),
	@Display(name="firstName"),
	@Display(name="middleInitial"),
	@Display(name="gradeLevel"),
	@Display(name="course"),
	@Display(name="section"),
	@Display(name="gender"),
	@Display(name="birthDate"),
	
	@Display(name="toSection", addInfoOnly=true),
	@Display(name="plan", addInfoOnly=true),
	@Display(name="age", addInfoOnly=true),
	@Display(name="placeOfBirth", addInfoOnly=true),
	@Display(name="status", addInfoOnly=true),
	@Display(name="addStatus", addInfoOnly=true),
	@Display(name="semester", addInfoOnly=true),
	@Display(name="scholarshipCode", addInfoOnly=true),
	@Display(name="registrationDate", addInfoOnly=true),
	@Display(name="promotedTo", addInfoOnly=true),
	@Display(name="address", type="Embedded", hideLabel=true, addInfoOnly=true),
	@Display(name="contact", type="Embedded", hideLabel=true, addInfoOnly=true),
	@Display(name="education", type="Embedded", hideLabel=true, addInfoOnly=true),
	@Display(name="children", type="Embedded", hideLabel=true, addInfoOnly=true),
	@Display(name="guardian", type="Embedded", hideLabel=true, addInfoOnly=true)
})
@DisplayGroups( {
		@DisplayGroup(gridCount = 6, title = "Name", fields = { "lastName", "firstName", "middleInitial" })
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
		@template.Report(reportFile = "SectionReportCardQuarter1", reportTitle = "Card Q1", reportSql = "${section}"),
	    @template.Report(reportFile = "SectionReportCardQuarter2", reportTitle = "Q2", reportSql = "${section}"),
	    @template.Report(reportFile = "SectionReportCardQuarter3", reportTitle = "Q3", reportSql = "${section}"),
		@template.Report(reportFile = "SectionReportCardQuarter4", reportTitle = "Q4", reportSql = "${section}"),
		@template.Report(reportFile = "SingleReportCard", reportTitle = "Single Card", reportSql = "${personId}"),
		@template.Report(reportFile = "StudentId", reportTitle = "ID", reportSql = "${personId}"),
		@template.Report(reportFile = "StudentIdSingle", reportTitle = "Single", reportSql = "${personId}"),
		@template.Report(reportFile = "StickerGrade", reportTitle = "Sticker", reportSql = "${personId}", duties="WITH STICKER GRADE"),
		@template.Report(reportFile = "StudentDirectory", reportTitle = "Directory", reportSql = "${personId}")
})
public class Student extends AbstractIBean {
	public static void main(String[] args) {
		view(Student.class);
	}
	@Override
	public void delete() {
		if (UserInfo.canDeleteStudent()) {
			super.delete();
		}
		else {
			PanelUtil.showError(null, "Only Administrator or 'DELETE STUDENT' duty code can delete Student records.");
		}
	}

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public String studentNumber;
    public String gradeLevel;
	public String course;
	public String toSection;
	public String plan;

	public String lastName;
    public String firstName;
    public String middleInitial;
    public String gender;
    public String maritalStatus;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date birthDate;
    public int age;
    public String placeOfBirth;
    public String religion;


    public String status;
    public String addStatus;
    public String userid;
    public String tempPass;
    public boolean isActive = true;
    public String section;

    public String gradeLevelDesc;
	public String semester;
	public String schoolYear;
	public String latestSchoolYear;
	public String scholarshipCode;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date registrationDate;
	public String oldStudentNumber;
	public String promotedTo;
    @Embedded
	public EmbeddedAddress address;
    @Embedded
	public EmbeddedGuardian guardian;
    @Embedded
	public EmbeddedPersonalReference reference;
    @Embedded
	public EmbeddedContact contact;
    @Embedded
	public EmbeddedEducation education;
    @Embedded
	public EmbeddedChildren children;
	public String paymentStatus;
	public boolean officiallyRegistered;
    
	public EmbeddedGuardian getGuardian() {
		return guardian;
	}
	public void setGuardian(EmbeddedGuardian guardian) {
		this.guardian = guardian;
	}
	public EmbeddedPersonalReference getReference() {
		return reference;
	}
	public void setReference(EmbeddedPersonalReference reference) {
		this.reference = reference;
	}
	public EmbeddedContact getContact() {
		return contact;
	}
	public void setContact(EmbeddedContact contact) {
		this.contact = contact;
	}
	public EmbeddedEducation getEducation() {
		return education;
	}
	public void setEducation(EmbeddedEducation education) {
		this.education = education;
	}
	public EmbeddedChildren getChildren() {
		return children;
	}
	public void setChildren(EmbeddedChildren children) {
		this.children = children;
	}
	public EmbeddedAddress getAddress() {
		return address;
	}
	public void setAddress(EmbeddedAddress address) {
		this.address = address;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getToSection() {
		return toSection;
	}
	public void setToSection(String toSection) {
		this.toSection = toSection;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddStatus() {
		return addStatus;
	}
	public void setAddStatus(String addStatus) {
		this.addStatus = addStatus;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTempPass() {
		return tempPass;
	}
	public void setTempPass(String tempPass) {
		this.tempPass = tempPass;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getGradeLevelDesc() {
		return gradeLevelDesc;
	}
	public void setGradeLevelDesc(String gradeLevelDesc) {
		this.gradeLevelDesc = gradeLevelDesc;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getLatestSchoolYear() {
		return latestSchoolYear;
	}
	public void setLatestSchoolYear(String latestSchoolYear) {
		this.latestSchoolYear = latestSchoolYear;
	}
	public String getScholarshipCode() {
		return scholarshipCode;
	}
	public void setScholarshipCode(String scholarshipCode) {
		this.scholarshipCode = scholarshipCode;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getOldStudentNumber() {
		return oldStudentNumber;
	}
	public void setOldStudentNumber(String oldStudentNumber) {
		this.oldStudentNumber = oldStudentNumber;
	}
	public String getPromotedTo() {
		return promotedTo;
	}
	public void setPromotedTo(String promotedTo) {
		this.promotedTo = promotedTo;
	}
	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "firstName","lastName");
	}
	public String toString() {
		if (lastName==null || firstName==null) {
			return "";
		}
		return lastName+" "+firstName;
	}
	public void assessStudent(String schoolYear2, GradeLevel lvl,
			String upperCase, int i) {
		// TODO Auto-generated method stub
		
	}
	public List<Payment> extractPayments(String useYear) {
		// TODO Auto-generated method stub
		return null;
	}
	public List extractBackAccount(String schoolYear2) {
		// TODO Auto-generated method stub
		return null;
	}
	public void updateEnrollmentSection() {
		// TODO Auto-generated method stub
		
	}
	public static String getDefaultStudentNumber(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setDefaultStudentNumber() {
		// TODO Auto-generated method stub
		
	}
	public String getFormattedTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
