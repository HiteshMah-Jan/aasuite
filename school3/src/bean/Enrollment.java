/*
 * Enrollment.java
 *
 * Created on Nov 27, 2007, 6:57:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import service.util.ChartBean;
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
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.accounting.PaymentEnrollment;
import bean.admin.AppConfig;
import bean.person.StudentSubject;
import bean.reference.GradeLevel;
import bean.reference.Section;
import springbean.AAAConfig;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "Enrollment")
@UITemplate( //    template=TemplateScrollableTabPage.class,
template = TemplateTabSinglePage.class,
columnSearch = {"student", "schoolYear", "section", "totalUnit", "gradeLevel", "paymentPlanType","q1Math"},
criteriaSearch = {"studentId", "schoolYear", "gradeLevel", "section", "paymentPlanType"},
gridCount = 6, title = "Enrollment",
showChart = false, orderBy="a.student")
@ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {"seq", "enrollmentId"}, entity = StudentSubject.class, sql = "SELECT a FROM StudentSubject a WHERE a.enrollmentId=${seq}", title = "Subjects"),
    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {"seq", "recordId"}, entity = PaymentEnrollment.class, sql = "SELECT a FROM PaymentEnrollment a WHERE a.recordId=${seq}", title = "Payments")
//},
//info = {
//    @ParentAddInfo(title = "Miscellaneous Info",
//    displayFields = {"registrationFee", "materialsFee", "idFee", "medicalDentalFee", "libraryFee", "laboratoryFee",
//        "audioVisualFee", "athleticFee", "insuranceFee", "computerFee", "handbookFee", "diplomaFee", "annualFee"
//    })
})
@Displays({
    @Display(name = "studentId", linktoBean = Student.class, label = "Student", gridFieldWidth = 5, width = -1, type = "PopSearch"),
    @Display(name = "schoolYear"),
    @Display(name = "gradeLevel", type="PopSearch", linktoBean=GradeLevel.class),
    @Display(name = "section", type = "PopSearch", linktoBean = Section.class),
    @Display(name = "paymentPlanType", label="Plan", gridFieldWidth = 3, type="Combo", modelCombo={"A","S","Q","M"},width=-1),
    @Display(name = "tuitionFee", type="Label"),
    @Display(name = "miscellaneousFee", type="Label"),
    @Display(name = "adjustment", type="Label"),
    @Display(name = "discount", type="Label"),
    @Display(name = "backAccount", type="Label"),
    @Display(name = "overAllAmount", label = "Overall Fee", width = -1, type="Label"),

    @Display(name = "seq", addInfoOnly = true, type="Label"),
    @Display(name = "registrationFee", addInfoOnly = true, type="Label"),
    @Display(name = "materialsFee", addInfoOnly = true, type="Label"),
    @Display(name = "idFee", addInfoOnly = true, type="Label"),
    @Display(name = "medicalDentalFee", addInfoOnly = true, type="Label"),
    @Display(name = "libraryFee", addInfoOnly = true, type="Label"),
    @Display(name = "laboratoryFee", addInfoOnly = true, type="Label"),
    @Display(name = "audioVisualFee", addInfoOnly = true, type="Label"),
    @Display(name = "athleticFee", addInfoOnly = true, type="Label"),
    @Display(name = "insuranceFee", addInfoOnly = true, type="Label"),
    @Display(name = "computerFee", addInfoOnly = true, type="Label"),
    @Display(name = "handbookFee", addInfoOnly = true, type="Label"),
    @Display(name = "diplomaFee", addInfoOnly = true, type="Label"),
    @Display(name = "annualFee", addInfoOnly = true, type="Label"),
//        @Display(name="totalUnit", addInfoOnly=true),
//        @Display(name="amountPerUnit", addInfoOnly=true),
//        @Display(name="isActive", addInfoOnly=true)
        @Display(name="requestedDiscountType", addInfoOnly=true),
        @Display(name="requestedDiscountAmount", addInfoOnly=true),
        @Display(name="requestedDiscountApproved",type="CheckBox", addInfoOnly=true),
        @Display(name="requestedDiscountBy", addInfoOnly=true),
        @Display(name="requestedDiscountApprovedBy", addInfoOnly=true),
        @Display(name="requestedDiscountDate", addInfoOnly=true),
        @Display(name="requestedDiscountApprovedDate", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(gridCount = 4, title = "Payment Scheme", fields = {
        "paymentPlanType",
        "tuitionFee",
        "miscellaneousFee", "adjustment", "discount", "backAccount",
        "overAllAmount"
    })
})
@ActionButtons({
    @ActionButton(name = "btnCreateSchedules", label = "Create Schedules"),
    @ActionButton(name = "btnDeleteDuplicate", label = "Delete All Duplicate"),
    @ActionButton(name = "btnViewPossibleSched", label = "View Possible Schedule")
//    @ActionButton(name="btnPrintStatementOfAccount", label="Print Statement of Account"),
//    @ActionButton(name="btnPrintEnrolledSubjects", label="Print Enrolled Subjects")
})
@Reports({
//    @template.Report(reportFile = "StudentSchedule", reportTitle = "Student Schedule", reportSql = "${studentId}"),
//    @template.Report(reportFile = "StatementOfAccount", reportTitle = "Statement Of Account", reportSql = "${seq}"),
//    @template.Report(reportFile = "EnrolledStudentbySection", reportTitle = "Enrollment Report by Section", reportSql = "${section}"),
    @template.Report(reportFile = "OrganizationalListSection", reportTitle = "Student List per Section", reportSql = "${section}")
//     @template.Report(reportFile = "OfficialEnrollmentListStudentCount", reportTitle = "Official Enrollment List", reportSql = " ")
//      @template.Report(reportFile = "officialOrganizationalListPreschool", reportTitle = "Preschool Org List", reportSql = ""),
//       @template.Report(reportFile = "officialOrganizationalListElementary1", reportTitle = "Grade 1-3 Org List", reportSql = ""),
//        @template.Report(reportFile = "officialOrganizationalListElementary2", reportTitle = "Grade 1-4 Org List", reportSql = ""),
//         @template.Report(reportFile = "officialOrganizationalListHighschool", reportTitle = "High School Org List", reportSql = "")

//    @template.Report(reportFile = "AccountsReceivableSchedule", reportTitle = "Schedule of Accounts Receivables", reportSql = "${section}")
//    @template.Report(reportFile = "StudentLastAttendedSchool", reportTitle = "Student Last Attended School", reportSql = "${section}")
})
public class Enrollment extends AbstractIBean implements Serializable {
	@Override
	public void delete() {
		int count = (int) DBClient.getSingleColumnDouble("SELECT COUNT(*) FROM Payment WHERE recordId=",seq);
		if (count>0) {
			PanelUtil.showError(null, "Cannot delete record, this record has payment.");
		}
		else {
			super.delete();
		}
	}

	private List<StudentSubject> extractSubjects() {
		if (isEmpty(gradeLevel) && notEmpty(section)) {
			save();
		}
		return DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubject a WHERE a.gradeLevel='",gradeLevel,"' AND a.studentId=",studentId));
	}

	public void calculateQ1() {
		List<StudentSubject> lst = extractSubjects();
		double totalGpa = 0;
		totalUnit = 0;
		for (StudentSubject s:lst) {
			totalGpa += s.grade1*s.unit;
			totalUnit += s.unit;
		}
        changeSupport.firePropertyChange("gpa1", gpa1, gpa1=doubleVal(totalGpa/totalUnit));
		save();
	}

	public void calculateQ2() {
		List<StudentSubject> lst = extractSubjects();
		double totalGpa = 0;
		for (StudentSubject s:lst) {
			totalGpa += s.unitShareQ2;
		}
		save();
	}

	public void calculateQ3() {
		if (gpa3<SchoolDefaultProcess.MAX_GRADE_RECALCULATE) {
			List<StudentSubject> lst = extractSubjects();
			double totalGpa = 0;
			for (StudentSubject s:lst) {
//				s.calculateQ3();
				totalGpa += s.unitShareQ3;
			}
	        changeSupport.firePropertyChange("gpa3", gpa3, gpa3=doubleVal(totalGpa));
		}
		save();
	}

	public void calculateQ4() {
		if (gpa4<SchoolDefaultProcess.MAX_GRADE_RECALCULATE) {
			List<StudentSubject> lst = extractSubjects();
			double totalGpa = 0;
			for (StudentSubject s:lst) {
//				s.calculateQ4();
				totalGpa += s.unitShareQ4;
			}
	        changeSupport.firePropertyChange("gpa4", gpa4, gpa4=doubleVal(totalGpa));
		}
		save();
	}

	public void calculateFinal() {
		if (gpaFinal<SchoolDefaultProcess.MAX_GRADE_RECALCULATE) {
			List<StudentSubject> lst = extractSubjects();
			double totalGpa = 0;
			for (StudentSubject s:lst) {
//				s.calculateFinal();
				totalGpa += s.unitShareFinal;
			}
	        changeSupport.firePropertyChange("gpaFinal", gpaFinal, gpaFinal=doubleVal(totalGpa));
		}
		save();
	}

	public void extractAllGrades() {
//		get all the studentsubject then put all grades in fields
		List<StudentSubject> lst = extractSubjects();
		for (StudentSubject s:lst) {
			if (s.subject.toUpperCase().contains("FIL")) {
				this.q1Filipino = s.grade1;
				this.q2Filipino = s.grade2;
				this.q3Filipino = s.grade3;
				this.q4Filipino = s.grade4;
			}
		}
	}

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "enrollmentType")
    public String enrollmentType;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "semester")
    public int semester;
    @Column(name = "miscellaneousFee")
    public double miscellaneousFee;
    @Column(name = "discount")
    public double discount;
    @Column(name = "totalUnit")
    public int totalUnit;
    @Column(name = "amountPerUnit")
    public double amountPerUnit;
    @Column(name = "adjustment")
    public double adjustment;
    @Column(name = "tuitionFee")
    public double tuitionFee;
    @Column(name = "paymentMode")
    public String paymentMode;
    @Column(name = "section")
    public String section;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "paymentPlanType")
    public String paymentPlanType;
    @Column(name = "payCount")
    public int payCount;
    @Column(name = "dayOfMonth")
    public int dayOfMonth;
    @Column(name = "overAllAmount")
    public double overAllAmount;
    @Column(name = "installmentAmount")
    public double installmentAmount;
    @Column(name = "isActive")
    public boolean isActive;
    @Column(name = "backAccount")
    public double backAccount;
    @Column(name = "dateEnrolled")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dateEnrolled;
    @Column(name = "paymentStatus")
    public String paymentStatus;
    @Column(name = "status")
    public String status;
    @Column(name = "registrationFee")
    public double registrationFee;
    @Column(name = "materialsFee")
    public double materialsFee;
    @Column(name = "idFee")
    public double idFee;
    @Column(name = "medicalDentalFee")
    public double medicalDentalFee;
    @Column(name = "libraryFee")
    public double libraryFee;
    @Column(name = "laboratoryFee")
    public double laboratoryFee;
    @Column(name = "audioVisualFee")
    public double audioVisualFee;
    @Column(name = "athleticFee")
    public double athleticFee;
    @Column(name = "insuranceFee")
    public double insuranceFee;
    @Column(name = "computerFee")
    public double computerFee;
    @Column(name = "handbookFee")
    public double handbookFee;
    @Column(name = "diplomaFee")
    public double diplomaFee;
    @Column(name = "annualFee")
    public double annualFee;
    @Column(name = "cummTotalDaysInGrade")
    public int cummTotalDaysInGrade;
    @Column(name = "cummTotalYearsInSchool")
    public int cummTotalYearsInSchool;
    @Column(name = "currentlyEnrolled")
    public String currentlyEnrolled;
    @Column(name = "typeOfTuitionPayment")
    public String typeOfTuitionPayment;
    @Column(name = "schoolYearPlan")
    public String schoolYearPlan;
    @Column(name = "imagePath")
    public String imagePath;
    @Column(name = "siblings1")
    public String siblings1;
    @Column(name = "siblings2")
    public String siblings2;
    @Column(name = "siblings3")
    public String siblings3;
    @Column(name = "siblings4")
    public String siblings4;
    @Column(name = "siblings5")
    public String siblings5;
    @Column(name = "unsettledTuitionFee")
    public String unsettledTuitionFee;
    @Column(name = "remedialClass")
    public String remedialClass;
    @Column(name = "busService")
    public String busService;
    @Column(name = "transcript")
    public String transcript;
    @Column(name = "fIS")
    public String fIS;
    @Column(name = "idPicture")
    public String idPicture;
    @Column(name = "birthCertificate")
    public String birthCertificate;
    @Column(name = "baptismal")
    public String baptismal;
    @Column(name = "reportCard")
    public String reportCard;
    @Column(name = "recommendation")
    public String recommendation;
    @Column(name = "aTTransferSchool")
    public String aTTransferSchool;
    @Column(name = "aTSummerAcademic")
    public String aTSummerAcademic;
    @Column(name = "aTSMFailedEnglish")
    public String aTSMFailedEnglish;
    @Column(name = "aTSMFailedScience")
    public String aTSMFailedScience;
    @Column(name = "aTSMFailedMath")
    public String aTSMFailedMath;
    @Column(name = "aTSM7577English")
    public String aTSM7577English;
    @Column(name = "aTSM7577Science")
    public String aTSM7577Science;
    @Column(name = "aTSM7577Math")
    public String aTSM7577Math;
    @Column(name = "aCR")
    public String aCR;
    @Column(name = "passportVerification")
    public String passportVerification;
    @Column(name = "scholasticRecord")
    public String scholasticRecord;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "requestedDiscountType")
    public String requestedDiscountType;
    @Column(name = "requestedDiscountAmount")
    public double requestedDiscountAmount;
    @Column(name = "requestedDiscountStatus")
    public String requestedDiscountStatus;
    @Column(name = "requestedDiscountApproved")
    public boolean requestedDiscountApproved;
    @Column(name = "requestedDiscountBy")
    public String requestedDiscountBy;
    @Column(name = "requestedDiscountApprovedBy")
    public String requestedDiscountApprovedBy;
    @Column(name = "requestedDiscountDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date requestedDiscountDate;
    @Column(name = "requestedDiscountApprovedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date requestedDiscountApprovedDate;
    @Column(name = "gpa1")
    public double gpa1;
    @Column(name = "gpa2")
    public double gpa2;
    @Column(name = "gpa3")
    public double gpa3;
    @Column(name = "gpa4")
    public double gpa4;
    @Column(name = "gpaFinal")
    public double gpaFinal;

    @Column(name = "rankQ1")
    public int rankQ1;
    @Column(name = "rankQ2")
    public int rankQ2;
    @Column(name = "rankQ3")
    public int rankQ3;
    @Column(name = "rankQ4")
    public int rankQ4;
    @Column(name = "rankFinal")
    public int rankFinal;

    @Column(name = "gpaValues1")
    public double gpaValues1;
    @Column(name = "gpaValues2")
    public double gpaValues2;
    @Column(name = "gpaValues3")
    public double gpaValues3;
    @Column(name = "gpaValues4")
    public double gpaValues4;
    @Column(name = "gpaValuesFinal")
    public double gpaValuesFinal;

    @Column(name = "meritQ1")
    public String meritQ1;
    @Column(name = "meritQ2")
    public String meritQ2;
    @Column(name = "meritQ3")
    public String meritQ3;
    @Column(name = "meritQ4")
    public String meritQ4;
    @Column(name = "meritFinal")
    public String meritFinal;

    @Column(name = "student")
    public String student;

    @Column(name = "q1OpMath")
    public double q1OpMath;
    @Column(name = "q2OpMath")
    public double q2OpMath;
    @Column(name = "q3OpMath")
    public double q3OpMath;
    @Column(name = "q4OpMath")
    public double q4OpMath;
    @Column(name = "qallOpMath")
    public double qallOpMath;

    @Column(name = "q1Math")
    public double q1Math;
    @Column(name = "q1Math2")
    public double q1Math2;
    @Column(name = "q1Math3")
    public double q1Math3;
    @Column(name = "q1Science")
    public double q1Science;
    @Column(name = "q1Science2")
    public double q1Science2;
    @Column(name = "q1Science3")
    public double q1Science3;
    @Column(name = "q1English")
    public double q1English;
    @Column(name = "q1English2")
    public double q1English2;
    @Column(name = "q1English3")
    public double q1English3;
    @Column(name = "q1Language")
    public double q1Language;
    @Column(name = "q1Filipino")
    public double q1Filipino;
    @Column(name = "q1Reading")
    public double q1Reading;
    @Column(name = "q1Writing")
    public double q1Writing;
    @Column(name = "q1Computer")
    public double q1Computer;

    @Column(name = "q1CCF")
    public double q1CCF;
    @Column(name = "q1AP")
    public double q1AP;
    @Column(name = "q1Hekasi")
    public double q1Hekasi;
    @Column(name = "q1TLE")
    public double q1TLE;
    @Column(name = "q1CE")
    public double q1CE;
    @Column(name = "q1Music")
    public double q1Music;
    @Column(name = "q1Arts")
    public double q1Arts;
    @Column(name = "q1PE")
    public double q1PE;
    @Column(name = "q1ME")
    public double q1ME;
    @Column(name = "q1MAPEH")
    public double q1MAPEH;
    @Column(name = "q1TEPP")
    public double q1TEPP;

    @Column(name = "q1ChineseA")
    public double q1ChineseA;
    @Column(name = "q1ChineseB")
    public double q1ChineseB;

    @Column(name = "q2Math")
    public double q2Math;
    @Column(name = "q2Math2")
    public double q2Math2;
    @Column(name = "q2Math3")
    public double q2Math3;
    @Column(name = "q2Science")
    public double q2Science;
    @Column(name = "q2Science2")
    public double q2Science2;
    @Column(name = "q2Science3")
    public double q2Science3;
    @Column(name = "q2English")
    public double q2English;
    @Column(name = "q2English2")
    public double q2English2;
    @Column(name = "q2English3")
    public double q2English3;
    @Column(name = "q2Language")
    public double q2Language;
    @Column(name = "q2Filipino")
    public double q2Filipino;
    @Column(name = "q2Reading")
    public double q2Reading;
    @Column(name = "q2Writing")
    public double q2Writing;
    @Column(name = "q2Computer")
    public double q2Computer;
    @Column(name = "q1Makabayan")
    public double q1Makabayan;
    @Column(name = "q2Makabayan")
    public double q2Makabayan;
    @Column(name = "q3Makabayan")
    public double q3Makabayan;
    @Column(name = "q4Makabayan")
    public double q4Makabayan;
    @Column(name = "qallMakabayan")
    public double qallMakabayan;
    @Column(name = "q2CCF")
    public double q2CCF;
    @Column(name = "q2AP")
    public double q2AP;
    @Column(name = "q2Hekasi")
    public double q2Hekasi;
    @Column(name = "q2TLE")
    public double q2TLE;
    @Column(name = "q2CE")
    public double q2CE;
    @Column(name = "q2Music")
    public double q2Music;
    @Column(name = "q2Arts")
    public double q2Arts;
    @Column(name = "q2PE")
    public double q2PE;
    @Column(name = "q2ME")
    public double q2ME;
    @Column(name = "q2MAPEH")
    public double q2MAPEH;
    @Column(name = "q2TEPP")
    public double q2TEPP;

    @Column(name = "q2ChineseA")
    public double q2ChineseA;
    @Column(name = "q2ChineseB")
    public double q2ChineseB;

    @Column(name = "q3Math")
    public double q3Math;
    @Column(name = "q3Math2")
    public double q3Math2;
    @Column(name = "q3Math3")
    public double q3Math3;
    @Column(name = "q3Science")
    public double q3Science;
    @Column(name = "q3Science2")
    public double q3Science2;
    @Column(name = "q3Science3")
    public double q3Science3;
    @Column(name = "q3English")
    public double q3English;
    @Column(name = "q3English2")
    public double q3English2;
    @Column(name = "q3English3")
    public double q3English3;
    @Column(name = "q3Language")
    public double q3Language;
    @Column(name = "q3Filipino")
    public double q3Filipino;
    @Column(name = "q3Reading")
    public double q3Reading;
    @Column(name = "q3Writing")
    public double q3Writing;
    @Column(name = "q3Computer")
    public double q3Computer;

    @Column(name = "q3CCF")
    public double q3CCF;
    @Column(name = "q3AP")
    public double q3AP;
    @Column(name = "q3Hekasi")
    public double q3Hekasi;
    @Column(name = "q3TLE")
    public double q3TLE;
    @Column(name = "q3CE")
    public double q3CE;
    @Column(name = "q3Music")
    public double q3Music;
    @Column(name = "q3Arts")
    public double q3Arts;
    @Column(name = "q3PE")
    public double q3PE;
    @Column(name = "q3ME")
    public double q3ME;
    @Column(name = "q3MAPEH")
    public double q3MAPEH;
    @Column(name = "q3TEPP")
    public double q3TEPP;

    @Column(name = "q3ChineseA")
    public double q3ChineseA;
    @Column(name = "q3ChineseB")
    public double q3ChineseB;

    @Column(name = "q4Math")
    public double q4Math;
    @Column(name = "q4Math2")
    public double q4Math2;
    @Column(name = "q4Math3")
    public double q4Math3;
    @Column(name = "q4Science")
    public double q4Science;
    @Column(name = "q4Science2")
    public double q4Science2;
    @Column(name = "q4Science3")
    public double q4Science3;
    @Column(name = "q4English")
    public double q4English;
    @Column(name = "q4English2")
    public double q4English2;
    @Column(name = "q4English3")
    public double q4English3;
    @Column(name = "q4Language")
    public double q4Language;
    @Column(name = "q4Filipino")
    public double q4Filipino;
    @Column(name = "q4Reading")
    public double q4Reading;
    @Column(name = "q4Writing")
    public double q4Writing;
    @Column(name = "q4Computer")
    public double q4Computer;

    @Column(name = "q4CCF")
    public double q4CCF;
    @Column(name = "q4AP")
    public double q4AP;
    @Column(name = "q4Hekasi")
    public double q4Hekasi;
    @Column(name = "q4TLE")
    public double q4TLE;
    @Column(name = "q4CE")
    public double q4CE;
    @Column(name = "q4Music")
    public double q4Music;
    @Column(name = "q4Arts")
    public double q4Arts;
    @Column(name = "q4PE")
    public double q4PE;
    @Column(name = "q4ME")
    public double q4ME;
    @Column(name = "q4MAPEH")
    public double q4MAPEH;
    @Column(name = "q4TEPP")
    public double q4TEPP;

    @Column(name = "q4ChineseA")
    public double q4ChineseA;
    @Column(name = "q4ChineseB")
    public double q4ChineseB;

    @Column(name = "qallMath")
    public double qallMath;
    @Column(name = "qallMath2")
    public double qallMath2;
    @Column(name = "qallMath3")
    public double qallMath3;
    @Column(name = "qallScience")
    public double qallScience;
    @Column(name = "qallScience2")
    public double qallScience2;
    @Column(name = "qallScience3")
    public double qallScience3;
    @Column(name = "qallEnglish")
    public double qallEnglish;
    @Column(name = "qallEnglish2")
    public double qallEnglish2;
    @Column(name = "qallEnglish3")
    public double qallEnglish3;
    @Column(name = "qallLanguage")
    public double qallLanguage;
    @Column(name = "qallFilipino")
    public double qallFilipino;
    @Column(name = "qallReading")
    public double qallReading;
    @Column(name = "qallWriting")
    public double qallWriting;
    @Column(name = "qallComputer")
    public double qallComputer;

    @Column(name = "qallCCF")
    public double qallCCF;
    @Column(name = "qallAP")
    public double qallAP;
    @Column(name = "qallHekasi")
    public double qallHekasi;
    @Column(name = "qallTLE")
    public double qallTLE;
    @Column(name = "qallCE")
    public double qallCE;
    @Column(name = "qallMusic")
    public double qallMusic;
    @Column(name = "qallArts")
    public double qallArts;
    @Column(name = "qallPE")
    public double qallPE;
    @Column(name = "qallME")
    public double qallME;
    @Column(name = "qallMAPEH")
    public double qallMAPEH;
    @Column(name = "qallTEPP")
    public double qallTEPP;

    @Column(name = "qallChineseA")
    public double qallChineseA;
    @Column(name = "qallChineseB")
    public double qallChineseB;

    @Column(name = "q1VP")
    public double q1VP;
    @Column(name = "q2VP")
    public double q2VP;
    @Column(name = "q3VP")
    public double q3VP;
    @Column(name = "q4VP")
    public double q4VP;
    @Column(name = "qallVP")
    public double qallVP;

    @Column(name = "q1ZL")
    public double q1ZL;
    @Column(name = "q2ZL")
    public double q2ZL;
    @Column(name = "q3ZL")
    public double q3ZL;
    @Column(name = "q4ZL")
    public double q4ZL;
    @Column(name = "qallZL")
    public double qallZL;

    @Column(name = "q1MC")
    public double q1MC;
    @Column(name = "q2MC")
    public double q2MC;
    @Column(name = "q3MC")
    public double q3MC;
    @Column(name = "q4MC")
    public double q4MC;
    @Column(name = "qallMC")
    public double qallMC;

    @Column(name = "q1AttendanceNoOfDays")
    public int q1AttendanceNoOfDays;
    @Column(name = "q2AttendanceNoOfDays")
    public int q2AttendanceNoOfDays;
    @Column(name = "q3AttendanceNoOfDays")
    public int q3AttendanceNoOfDays;
    @Column(name = "q4AttendanceNoOfDays")
    public int q4AttendanceNoOfDays;
    @Column(name = "qallAttendanceNoOfDays")
    public int qallAttendanceNoOfDays;

    @Column(name = "q1AttendanceAbsent")
    public int q1AttendanceAbsent;
    @Column(name = "q2AttendanceAbsent")
    public int q2AttendanceAbsent;
    @Column(name = "q3AttendanceAbsent")
    public int q3AttendanceAbsent;
    @Column(name = "q4AttendanceAbsent")
    public int q4AttendanceAbsent;
    @Column(name = "qallAttendanceAbsent")
    public int qallAttendanceAbsent;

    @Column(name = "q1AttendanceTardy")
    public int q1AttendanceTardy;
    @Column(name = "q2AttendanceTardy")
    public int q2AttendanceTardy;
    @Column(name = "q3AttendanceTardy")
    public int q3AttendanceTardy;
    @Column(name = "q4AttendanceTardy")
    public int q4AttendanceTardy;
    @Column(name = "qallAttendanceTardy")
    public int qallAttendanceTardy;

//    for college
    @Column(name = "schedule1")
    public int schedule1;
    @Column(name = "schedule2")
    public int schedule2;
    @Column(name = "schedule3")
    public int schedule3;
    @Column(name = "schedule4")
    public int schedule4;
    @Column(name = "schedule5")
    public int schedule5;
    @Column(name = "schedule6")
    public int schedule6;
    @Column(name = "schedule7")
    public int schedule7;
    @Column(name = "schedule8")
    public int schedule8;
    @Column(name = "schedule9")
    public int schedule9;
    @Column(name = "schedule10")
    public int schedule10;
    @Column(name = "schedule11")
    public int schedule11;
    @Column(name = "schedule12")
    public int schedule12;
    @Column(name = "schedule13")
    public int schedule13;
    @Column(name = "schedule14")
    public int schedule14;
    @Column(name = "schedule15")
    public int schedule15;

    @Column(name = "subject1")
    public String subject1;
    @Column(name = "subject2")
    public String subject2;
    @Column(name = "subject3")
    public String subject3;
    @Column(name = "subject4")
    public String subject4;
    @Column(name = "subject5")
    public String subject5;
    @Column(name = "subject6")
    public String subject6;
    @Column(name = "subject7")
    public String subject7;
    @Column(name = "subject8")
    public String subject8;
    @Column(name = "subject9")
    public String subject9;
    @Column(name = "subject10")
    public String subject10;
    @Column(name = "subject11")
    public String subject11;
    @Column(name = "subject12")
    public String subject12;
    @Column(name = "subject13")
    public String subject13;
    @Column(name = "subject14")
    public String subject14;
    @Column(name = "subject15")
    public String subject15;

    @Column(name = "unit1")
    public double unit1;
    @Column(name = "unit2")
    public double unit2;
    @Column(name = "unit3")
    public double unit3;
    @Column(name = "unit4")
    public double unit4;
    @Column(name = "unit5")
    public double unit5;
    @Column(name = "unit6")
    public double unit6;
    @Column(name = "unit7")
    public double unit7;
    @Column(name = "unit8")
    public double unit8;
    @Column(name = "unit9")
    public double unit9;
    @Column(name = "unit10")
    public double unit10;
    @Column(name = "unit11")
    public double unit11;
    @Column(name = "unit12")
    public double unit12;
    @Column(name = "unit13")
    public double unit13;
    @Column(name = "unit14")
    public double unit14;
    @Column(name = "unit15")
    public double unit15;

    @Column(name = "faculty1")
    public int faculty1;
    @Column(name = "faculty2")
    public int faculty2;
    @Column(name = "faculty3")
    public int faculty3;
    @Column(name = "faculty4")
    public int faculty4;
    @Column(name = "faculty5")
    public int faculty5;
    @Column(name = "faculty6")
    public int faculty6;
    @Column(name = "faculty7")
    public int faculty7;
    @Column(name = "faculty8")
    public int faculty8;
    @Column(name = "faculty9")
    public int faculty9;
    @Column(name = "faculty10")
    public int faculty10;
    @Column(name = "faculty11")
    public int faculty11;
    @Column(name = "faculty12")
    public int faculty12;
    @Column(name = "faculty13")
    public int faculty13;
    @Column(name = "faculty14")
    public int faculty14;
    @Column(name = "faculty15")
    public int faculty15;

    @Column(name = "amount1")
    public double amount1;
    @Column(name = "amount2")
    public double amount2;
    @Column(name = "amount3")
    public double amount3;
    @Column(name = "amount4")
    public double amount4;
    @Column(name = "amount5")
    public double amount5;
    @Column(name = "amount6")
    public double amount6;
    @Column(name = "amount7")
    public double amount7;
    @Column(name = "amount8")
    public double amount8;
    @Column(name = "amount9")
    public double amount9;
    @Column(name = "amount10")
    public double amount10;
    @Column(name = "amount11")
    public double amount11;
    @Column(name = "amount12")
    public double amount12;
    @Column(name = "amount13")
    public double amount13;
    @Column(name = "amount14")
    public double amount14;
    @Column(name = "amount15")
    public double amount15;
    @Column(name = "totalUnits")
    public double totalUnits;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "college")
    public boolean college;

    @Column(name = "q1Research")
    public double q1Research;
    @Column(name = "q2Research")
    public double q2Research;
    @Column(name = "q3Research")
    public double q3Research;
    @Column(name = "q4Research")
    public double q4Research;
    @Column(name = "qallResearch")
    public double qallResearch;

    @Column(name = "q1Health")
    public double q1Health;
    @Column(name = "q2Health")
    public double q2Health;
    @Column(name = "q3Health")
    public double q3Health;
    @Column(name = "q4Health")
    public double q4Health;
    @Column(name = "qallHealth")
    public double qallHealth;
    @Column(name = "promotedTo")
    public String promotedTo;
    @Column(name = "retainedAs")
    public String retainedAs;

    public String getPromotedTo() {
        return promotedTo;
    }

    public void setPromotedTo(String promotedTo) {
        this.promotedTo = promotedTo;
    }

    public String getRetainedAs() {
        return retainedAs;
    }

    public void setRetainedAs(String retainedAs) {
        this.retainedAs = retainedAs;
    }


    public double getQallHealth() {
		return qallHealth;
	}

	public void setQallHealth(double qallHealth) {
		this.qallHealth = qallHealth;
	}

	public double getQ1Health() {
		return q1Health;
	}

	public void setQ1Health(double health) {
		q1Health = health;
	}

	public double getQ2Health() {
		return q2Health;
	}

	public void setQ2Health(double health) {
		q2Health = health;
	}

	public double getQ3Health() {
		return q3Health;
	}

	public void setQ3Health(double health) {
		q3Health = health;
	}

	public double getQ4Health() {
		return q4Health;
	}

	public void setQ4Health(double health) {
		q4Health = health;
	}

	public double getQ1Research() {
		return q1Research;
	}

	public void setQ1Research(double research) {
		q1Research = research;
	}

	public double getQ2Research() {
		return q2Research;
	}

	public void setQ2Research(double research) {
		q2Research = research;
	}

	public double getQ3Research() {
		return q3Research;
	}

	public void setQ3Research(double research) {
		q3Research = research;
	}

	public double getQ4Research() {
		return q4Research;
	}

	public void setQ4Research(double research) {
		q4Research = research;
	}

	public double getQallResearch() {
		return qallResearch;
	}

	public void setQallResearch(double qallResearch) {
		this.qallResearch = qallResearch;
	}

	public double getQallOpMath() {
		return qallOpMath;
	}

	public void setQallOpMath(double qallOpMath) {
		this.qallOpMath = qallOpMath;
	}

	public double getQ1OpMath() {
		return q1OpMath;
	}

	public void setQ1OpMath(double opMath) {
		q1OpMath = opMath;
	}

	public double getQ2OpMath() {
		return q2OpMath;
	}

	public void setQ2OpMath(double opMath) {
		q2OpMath = opMath;
	}

	public double getQ3OpMath() {
		return q3OpMath;
	}

	public void setQ3OpMath(double opMath) {
		q3OpMath = opMath;
	}

	public double getQ4OpMath() {
		return q4OpMath;
	}

	public void setQ4OpMath(double opMath) {
		q4OpMath = opMath;
	}

	public String getMeritQ1() {
		return meritQ1;
	}

	public void setMeritQ1(String meritQ1) {
		this.meritQ1 = meritQ1;
	}

	public String getMeritQ2() {
		return meritQ2;
	}

	public void setMeritQ2(String meritQ2) {
		this.meritQ2 = meritQ2;
	}

	public String getMeritQ3() {
		return meritQ3;
	}

	public void setMeritQ3(String meritQ3) {
		this.meritQ3 = meritQ3;
	}

	public String getMeritQ4() {
		return meritQ4;
	}

	public void setMeritQ4(String meritQ4) {
		this.meritQ4 = meritQ4;
	}

	public String getMeritFinal() {
		return meritFinal;
	}

	public void setMeritFinal(String meritFinal) {
		this.meritFinal = meritFinal;
	}

	public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public int getSchedule1() {
		return schedule1;
	}

	public void setSchedule1(int schedule1) {
		this.schedule1 = schedule1;
	}

	public int getSchedule2() {
		return schedule2;
	}

	public void setSchedule2(int schedule2) {
		this.schedule2 = schedule2;
	}

	public int getSchedule3() {
		return schedule3;
	}

	public void setSchedule3(int schedule3) {
		this.schedule3 = schedule3;
	}

	public int getSchedule4() {
		return schedule4;
	}

	public void setSchedule4(int schedule4) {
		this.schedule4 = schedule4;
	}

	public int getSchedule5() {
		return schedule5;
	}

	public void setSchedule5(int schedule5) {
		this.schedule5 = schedule5;
	}

	public int getSchedule6() {
		return schedule6;
	}

	public void setSchedule6(int schedule6) {
		this.schedule6 = schedule6;
	}

	public int getSchedule7() {
		return schedule7;
	}

	public void setSchedule7(int schedule7) {
		this.schedule7 = schedule7;
	}

	public int getSchedule8() {
		return schedule8;
	}

	public void setSchedule8(int schedule8) {
		this.schedule8 = schedule8;
	}

	public int getSchedule9() {
		return schedule9;
	}

	public void setSchedule9(int schedule9) {
		this.schedule9 = schedule9;
	}

	public int getSchedule10() {
		return schedule10;
	}

	public void setSchedule10(int schedule10) {
		this.schedule10 = schedule10;
	}

	public int getSchedule11() {
		return schedule11;
	}

	public void setSchedule11(int schedule11) {
		this.schedule11 = schedule11;
	}

	public int getSchedule12() {
		return schedule12;
	}

	public void setSchedule12(int schedule12) {
		this.schedule12 = schedule12;
	}

	public int getSchedule13() {
		return schedule13;
	}

	public void setSchedule13(int schedule13) {
		this.schedule13 = schedule13;
	}

	public int getSchedule14() {
		return schedule14;
	}

	public void setSchedule14(int schedule14) {
		this.schedule14 = schedule14;
	}

	public int getSchedule15() {
		return schedule15;
	}

	public void setSchedule15(int schedule15) {
		this.schedule15 = schedule15;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public String getSubject4() {
		return subject4;
	}

	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	public String getSubject5() {
		return subject5;
	}

	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}

	public String getSubject6() {
		return subject6;
	}

	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}

	public String getSubject7() {
		return subject7;
	}

	public void setSubject7(String subject7) {
		this.subject7 = subject7;
	}

	public String getSubject8() {
		return subject8;
	}

	public void setSubject8(String subject8) {
		this.subject8 = subject8;
	}

	public String getSubject9() {
		return subject9;
	}

	public void setSubject9(String subject9) {
		this.subject9 = subject9;
	}

	public String getSubject10() {
		return subject10;
	}

	public void setSubject10(String subject10) {
		this.subject10 = subject10;
	}

	public String getSubject11() {
		return subject11;
	}

	public void setSubject11(String subject11) {
		this.subject11 = subject11;
	}

	public String getSubject12() {
		return subject12;
	}

	public void setSubject12(String subject12) {
		this.subject12 = subject12;
	}

	public String getSubject13() {
		return subject13;
	}

	public void setSubject13(String subject13) {
		this.subject13 = subject13;
	}

	public String getSubject14() {
		return subject14;
	}

	public void setSubject14(String subject14) {
		this.subject14 = subject14;
	}

	public String getSubject15() {
		return subject15;
	}

	public void setSubject15(String subject15) {
		this.subject15 = subject15;
	}

	public double getUnit1() {
		return unit1;
	}

	public void setUnit1(double unit1) {
		this.unit1 = unit1;
	}

	public double getUnit2() {
		return unit2;
	}

	public void setUnit2(double unit2) {
		this.unit2 = unit2;
	}

	public double getUnit3() {
		return unit3;
	}

	public void setUnit3(double unit3) {
		this.unit3 = unit3;
	}

	public double getUnit4() {
		return unit4;
	}

	public void setUnit4(double unit4) {
		this.unit4 = unit4;
	}

	public double getUnit5() {
		return unit5;
	}

	public void setUnit5(double unit5) {
		this.unit5 = unit5;
	}

	public double getUnit6() {
		return unit6;
	}

	public void setUnit6(double unit6) {
		this.unit6 = unit6;
	}

	public double getUnit7() {
		return unit7;
	}

	public void setUnit7(double unit7) {
		this.unit7 = unit7;
	}

	public double getUnit8() {
		return unit8;
	}

	public void setUnit8(double unit8) {
		this.unit8 = unit8;
	}

	public double getUnit9() {
		return unit9;
	}

	public void setUnit9(double unit9) {
		this.unit9 = unit9;
	}

	public double getUnit10() {
		return unit10;
	}

	public void setUnit10(double unit10) {
		this.unit10 = unit10;
	}

	public double getUnit11() {
		return unit11;
	}

	public void setUnit11(double unit11) {
		this.unit11 = unit11;
	}

	public double getUnit12() {
		return unit12;
	}

	public void setUnit12(double unit12) {
		this.unit12 = unit12;
	}

	public double getUnit13() {
		return unit13;
	}

	public void setUnit13(double unit13) {
		this.unit13 = unit13;
	}

	public double getUnit14() {
		return unit14;
	}

	public void setUnit14(double unit14) {
		this.unit14 = unit14;
	}

	public double getUnit15() {
		return unit15;
	}

	public void setUnit15(double unit15) {
		this.unit15 = unit15;
	}

	public int getFaculty1() {
		return faculty1;
	}

	public void setFaculty1(int faculty1) {
		this.faculty1 = faculty1;
	}

	public int getFaculty2() {
		return faculty2;
	}

	public void setFaculty2(int faculty2) {
		this.faculty2 = faculty2;
	}

	public int getFaculty3() {
		return faculty3;
	}

	public void setFaculty3(int faculty3) {
		this.faculty3 = faculty3;
	}

	public int getFaculty4() {
		return faculty4;
	}

	public void setFaculty4(int faculty4) {
		this.faculty4 = faculty4;
	}

	public int getFaculty5() {
		return faculty5;
	}

	public void setFaculty5(int faculty5) {
		this.faculty5 = faculty5;
	}

	public int getFaculty6() {
		return faculty6;
	}

	public void setFaculty6(int faculty6) {
		this.faculty6 = faculty6;
	}

	public int getFaculty7() {
		return faculty7;
	}

	public void setFaculty7(int faculty7) {
		this.faculty7 = faculty7;
	}

	public int getFaculty8() {
		return faculty8;
	}

	public void setFaculty8(int faculty8) {
		this.faculty8 = faculty8;
	}

	public int getFaculty9() {
		return faculty9;
	}

	public void setFaculty9(int faculty9) {
		this.faculty9 = faculty9;
	}

	public int getFaculty10() {
		return faculty10;
	}

	public void setFaculty10(int faculty10) {
		this.faculty10 = faculty10;
	}

	public int getFaculty11() {
		return faculty11;
	}

	public void setFaculty11(int faculty11) {
		this.faculty11 = faculty11;
	}

	public int getFaculty12() {
		return faculty12;
	}

	public void setFaculty12(int faculty12) {
		this.faculty12 = faculty12;
	}

	public int getFaculty13() {
		return faculty13;
	}

	public void setFaculty13(int faculty13) {
		this.faculty13 = faculty13;
	}

	public int getFaculty14() {
		return faculty14;
	}

	public void setFaculty14(int faculty14) {
		this.faculty14 = faculty14;
	}

	public int getFaculty15() {
		return faculty15;
	}

	public void setFaculty15(int faculty15) {
		this.faculty15 = faculty15;
	}

	public double getAmount1() {
		return amount1;
	}

	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}

	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	public double getAmount3() {
		return amount3;
	}

	public void setAmount3(double amount3) {
		this.amount3 = amount3;
	}

	public double getAmount4() {
		return amount4;
	}

	public void setAmount4(double amount4) {
		this.amount4 = amount4;
	}

	public double getAmount5() {
		return amount5;
	}

	public void setAmount5(double amount5) {
		this.amount5 = amount5;
	}

	public double getAmount6() {
		return amount6;
	}

	public void setAmount6(double amount6) {
		this.amount6 = amount6;
	}

	public double getAmount7() {
		return amount7;
	}

	public void setAmount7(double amount7) {
		this.amount7 = amount7;
	}

	public double getAmount8() {
		return amount8;
	}

	public void setAmount8(double amount8) {
		this.amount8 = amount8;
	}

	public double getAmount9() {
		return amount9;
	}

	public void setAmount9(double amount9) {
		this.amount9 = amount9;
	}

	public double getAmount10() {
		return amount10;
	}

	public void setAmount10(double amount10) {
		this.amount10 = amount10;
	}

	public double getAmount11() {
		return amount11;
	}

	public void setAmount11(double amount11) {
		this.amount11 = amount11;
	}

	public double getAmount12() {
		return amount12;
	}

	public void setAmount12(double amount12) {
		this.amount12 = amount12;
	}

	public double getAmount13() {
		return amount13;
	}

	public void setAmount13(double amount13) {
		this.amount13 = amount13;
	}

	public double getAmount14() {
		return amount14;
	}

	public void setAmount14(double amount14) {
		this.amount14 = amount14;
	}

	public double getAmount15() {
		return amount15;
	}

	public void setAmount15(double amount15) {
		this.amount15 = amount15;
	}

	public double getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(double totalUnits) {
		this.totalUnits = totalUnits;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	//    for college
    public double getQ1VP() {
		return q1VP;
	}

	public void setQ1VP(double q1vp) {
		q1VP = q1vp;
	}

	public double getQ2VP() {
		return q2VP;
	}

	public void setQ2VP(double q2vp) {
		q2VP = q2vp;
	}

	public double getQ3VP() {
		return q3VP;
	}

	public void setQ3VP(double q3vp) {
		q3VP = q3vp;
	}

	public double getQ4VP() {
		return q4VP;
	}

	public void setQ4VP(double q4vp) {
		q4VP = q4vp;
	}

	public double getQallVP() {
		return qallVP;
	}

	public void setQallVP(double qallVP) {
		this.qallVP = qallVP;
	}

	public double getQ1ZL() {
		return q1ZL;
	}

	public void setQ1ZL(double q1zl) {
		q1ZL = q1zl;
	}

	public double getQ2ZL() {
		return q2ZL;
	}

	public void setQ2ZL(double q2zl) {
		q2ZL = q2zl;
	}

	public double getQ3ZL() {
		return q3ZL;
	}

	public void setQ3ZL(double q3zl) {
		q3ZL = q3zl;
	}

	public double getQ4ZL() {
		return q4ZL;
	}

	public void setQ4ZL(double q4zl) {
		q4ZL = q4zl;
	}

	public double getQallZL() {
		return qallZL;
	}

	public void setQallZL(double qallZL) {
		this.qallZL = qallZL;
	}

	public double getQ1MC() {
		return q1MC;
	}

	public void setQ1MC(double q1mc) {
		q1MC = q1mc;
	}

	public double getQ2MC() {
		return q2MC;
	}

	public void setQ2MC(double q2mc) {
		q2MC = q2mc;
	}

	public double getQ3MC() {
		return q3MC;
	}

	public void setQ3MC(double q3mc) {
		q3MC = q3mc;
	}

	public double getQ4MC() {
		return q4MC;
	}

	public void setQ4MC(double q4mc) {
		q4MC = q4mc;
	}

	public double getQallMC() {
		return qallMC;
	}

	public void setQallMC(double qallMC) {
		this.qallMC = qallMC;
	}

	public int getQ1AttendanceNoOfDays() {
		return q1AttendanceNoOfDays;
	}

	public void setQ1AttendanceNoOfDays(int attendanceNoOfDays) {
		q1AttendanceNoOfDays = attendanceNoOfDays;
	}

	public int getQ2AttendanceNoOfDays() {
		return q2AttendanceNoOfDays;
	}

	public void setQ2AttendanceNoOfDays(int attendanceNoOfDays) {
		q2AttendanceNoOfDays = attendanceNoOfDays;
	}

	public int getQ3AttendanceNoOfDays() {
		return q3AttendanceNoOfDays;
	}

	public void setQ3AttendanceNoOfDays(int attendanceNoOfDays) {
		q3AttendanceNoOfDays = attendanceNoOfDays;
	}

	public int getQ4AttendanceNoOfDays() {
		return q4AttendanceNoOfDays;
	}

	public void setQ4AttendanceNoOfDays(int attendanceNoOfDays) {
		q4AttendanceNoOfDays = attendanceNoOfDays;
	}

	public int getQallAttendanceNoOfDays() {
		return qallAttendanceNoOfDays;
	}

	public void setQallAttendanceNoOfDays(int qallAttendanceNoOfDays) {
		this.qallAttendanceNoOfDays = qallAttendanceNoOfDays;
	}

	public int getQ1AttendanceAbsent() {
		return q1AttendanceAbsent;
	}

	public void setQ1AttendanceAbsent(int attendanceAbsent) {
		q1AttendanceAbsent = attendanceAbsent;
	}

	public int getQ2AttendanceAbsent() {
		return q2AttendanceAbsent;
	}

	public void setQ2AttendanceAbsent(int attendanceAbsent) {
		q2AttendanceAbsent = attendanceAbsent;
	}

	public int getQ3AttendanceAbsent() {
		return q3AttendanceAbsent;
	}

	public void setQ3AttendanceAbsent(int attendanceAbsent) {
		q3AttendanceAbsent = attendanceAbsent;
	}

	public int getQ4AttendanceAbsent() {
		return q4AttendanceAbsent;
	}

	public void setQ4AttendanceAbsent(int attendanceAbsent) {
		q4AttendanceAbsent = attendanceAbsent;
	}

	public int getQallAttendanceAbsent() {
		return qallAttendanceAbsent;
	}

	public void setQallAttendanceAbsent(int qallAttendanceAbsent) {
		this.qallAttendanceAbsent = qallAttendanceAbsent;
	}

	public int getQ1AttendanceTardy() {
		return q1AttendanceTardy;
	}

	public void setQ1AttendanceTardy(int attendanceTardy) {
		q1AttendanceTardy = attendanceTardy;
	}

	public int getQ2AttendanceTardy() {
		return q2AttendanceTardy;
	}

	public void setQ2AttendanceTardy(int attendanceTardy) {
		q2AttendanceTardy = attendanceTardy;
	}

	public int getQ3AttendanceTardy() {
		return q3AttendanceTardy;
	}

	public void setQ3AttendanceTardy(int attendanceTardy) {
		q3AttendanceTardy = attendanceTardy;
	}

	public int getQ4AttendanceTardy() {
		return q4AttendanceTardy;
	}

	public void setQ4AttendanceTardy(int attendanceTardy) {
		q4AttendanceTardy = attendanceTardy;
	}

	public int getQallAttendanceTardy() {
		return qallAttendanceTardy;
	}

	public void setQallAttendanceTardy(int qallAttendanceTardy) {
		this.qallAttendanceTardy = qallAttendanceTardy;
	}

	public double getQallMath() {
		return qallMath;
	}

	public void setQallMath(double qallMath) {
		this.qallMath = qallMath;
	}

	public double getQallScience() {
		return qallScience;
	}

	public void setQallScience(double qallScience) {
		this.qallScience = qallScience;
	}

	public double getQallEnglish() {
		return qallEnglish;
	}

	public void setQallEnglish(double qallEnglish) {
		this.qallEnglish = qallEnglish;
	}

	public double getQallFilipino() {
		return qallFilipino;
	}

	public void setQallFilipino(double qallFilipino) {
		this.qallFilipino = qallFilipino;
	}

	public double getQallReading() {
		return qallReading;
	}

	public void setQallReading(double qallReading) {
		this.qallReading = qallReading;
	}

	public double getQallWriting() {
		return qallWriting;
	}

	public void setQallWriting(double qallWriting) {
		this.qallWriting = qallWriting;
	}

	public double getQallComputer() {
		return qallComputer;
	}

	public void setQallComputer(double qallComputer) {
		this.qallComputer = qallComputer;
	}

	public double getQallCCF() {
		return qallCCF;
	}

	public void setQallCCF(double qallCCF) {
		this.qallCCF = qallCCF;
	}

	public double getQallAP() {
		return qallAP;
	}

	public void setQallAP(double qallAP) {
		this.qallAP = qallAP;
	}

	public double getQallHekasi() {
		return qallHekasi;
	}

	public void setQallHekasi(double qallHekasi) {
		this.qallHekasi = qallHekasi;
	}

	public double getQallTLE() {
		return qallTLE;
	}

	public void setQallTLE(double qallTLE) {
		this.qallTLE = qallTLE;
	}

	public double getQallCE() {
		return qallCE;
	}

	public void setQallCE(double qallCE) {
		this.qallCE = qallCE;
	}

	public double getQallMusic() {
		return qallMusic;
	}

	public void setQallMusic(double qallMusic) {
		this.qallMusic = qallMusic;
	}

	public double getQallArts() {
		return qallArts;
	}

	public void setQallArts(double qallArts) {
		this.qallArts = qallArts;
	}

	public double getQallPE() {
		return qallPE;
	}

	public void setQallPE(double qallPE) {
		this.qallPE = qallPE;
	}

	public double getQallME() {
		return qallME;
	}

	public void setQallME(double qallME) {
		this.qallME = qallME;
	}

	public double getQallMAPEH() {
		return qallMAPEH;
	}

	public void setQallMAPEH(double qallMAPEH) {
		this.qallMAPEH = qallMAPEH;
	}

	public double getQallChineseA() {
		return qallChineseA;
	}

	public void setQallChineseA(double qallChineseA) {
		this.qallChineseA = qallChineseA;
	}

	public double getQallChineseB() {
		return qallChineseB;
	}

	public void setQallChineseB(double qallChineseB) {
		this.qallChineseB = qallChineseB;
	}

	public double getQ1Math() {
		return q1Math;
	}

	public void setQ1Math(double math) {
		q1Math = math;
	}

	public double getQ1Science() {
		return q1Science;
	}

	public void setQ1Science(double science) {
		q1Science = science;
	}

	public double getQ1English() {
		return q1English;
	}

	public void setQ1English(double english) {
		q1English = english;
	}

	public double getQ1Filipino() {
		return q1Filipino;
	}

	public void setQ1Filipino(double filipino) {
		q1Filipino = filipino;
	}

	public double getQ1Reading() {
		return q1Reading;
	}

	public void setQ1Reading(double reading) {
		q1Reading = reading;
	}

	public double getQ1Writing() {
		return q1Writing;
	}

	public void setQ1Writing(double writing) {
		q1Writing = writing;
	}

	public double getQ1Computer() {
		return q1Computer;
	}

	public void setQ1Computer(double computer) {
		q1Computer = computer;
	}

	public double getQ1CCF() {
		return q1CCF;
	}

	public void setQ1CCF(double q1ccf) {
		q1CCF = q1ccf;
	}

	public double getQ1AP() {
		return q1AP;
	}

	public void setQ1AP(double q1ap) {
		q1AP = q1ap;
	}

	public double getQ1Hekasi() {
		return q1Hekasi;
	}

	public void setQ1Hekasi(double hekasi) {
		q1Hekasi = hekasi;
	}

	public double getQ1TLE() {
		return q1TLE;
	}

	public void setQ1TLE(double q1tle) {
		q1TLE = q1tle;
	}

	public double getQ1CE() {
		return q1CE;
	}

	public void setQ1CE(double q1ce) {
		q1CE = q1ce;
	}

	public double getQ1Music() {
		return q1Music;
	}

	public void setQ1Music(double music) {
		q1Music = music;
	}

	public double getQ1Arts() {
		return q1Arts;
	}

	public void setQ1Arts(double arts) {
		q1Arts = arts;
	}

	public double getQ1PE() {
		return q1PE;
	}

	public void setQ1PE(double q1pe) {
		q1PE = q1pe;
	}

	public double getQ1ME() {
		return q1ME;
	}

	public void setQ1ME(double q1me) {
		q1ME = q1me;
	}

	public double getQ1MAPEH() {
		return q1MAPEH;
	}

	public void setQ1MAPEH(double q1mapeh) {
		q1MAPEH = q1mapeh;
	}

	public double getQ1ChineseA() {
		return q1ChineseA;
	}

	public void setQ1ChineseA(double chineseA) {
		q1ChineseA = chineseA;
	}

	public double getQ1ChineseB() {
		return q1ChineseB;
	}

	public void setQ1ChineseB(double chineseB) {
		q1ChineseB = chineseB;
	}

	public double getQ2Math() {
		return q2Math;
	}

	public void setQ2Math(double math) {
		q2Math = math;
	}

	public double getQ2Science() {
		return q2Science;
	}

	public void setQ2Science(double science) {
		q2Science = science;
	}

	public double getQ2English() {
		return q2English;
	}

	public void setQ2English(double english) {
		q2English = english;
	}

	public double getQ2Filipino() {
		return q2Filipino;
	}

	public void setQ2Filipino(double filipino) {
		q2Filipino = filipino;
	}

	public double getQ2Reading() {
		return q2Reading;
	}

	public void setQ2Reading(double reading) {
		q2Reading = reading;
	}

	public double getQ2Writing() {
		return q2Writing;
	}

	public void setQ2Writing(double writing) {
		q2Writing = writing;
	}

	public double getQ2Computer() {
		return q2Computer;
	}

	public void setQ2Computer(double computer) {
		q2Computer = computer;
	}

	public double getQ2CCF() {
		return q2CCF;
	}

	public void setQ2CCF(double q2ccf) {
		q2CCF = q2ccf;
	}

	public double getQ2AP() {
		return q2AP;
	}

	public void setQ2AP(double q2ap) {
		q2AP = q2ap;
	}

	public double getQ2Hekasi() {
		return q2Hekasi;
	}

	public void setQ2Hekasi(double hekasi) {
		q2Hekasi = hekasi;
	}

	public double getQ2TLE() {
		return q2TLE;
	}

	public void setQ2TLE(double q2tle) {
		q2TLE = q2tle;
	}

	public double getQ2CE() {
		return q2CE;
	}

	public void setQ2CE(double q2ce) {
		q2CE = q2ce;
	}

	public double getQ2Music() {
		return q2Music;
	}

	public void setQ2Music(double music) {
		q2Music = music;
	}

	public double getQ2Arts() {
		return q2Arts;
	}

	public void setQ2Arts(double arts) {
		q2Arts = arts;
	}

	public double getQ2PE() {
		return q2PE;
	}

	public void setQ2PE(double q2pe) {
		q2PE = q2pe;
	}

	public double getQ2ME() {
		return q2ME;
	}

	public void setQ2ME(double q2me) {
		q2ME = q2me;
	}

	public double getQ2MAPEH() {
		return q2MAPEH;
	}

	public void setQ2MAPEH(double q2mapeh) {
		q2MAPEH = q2mapeh;
	}

	public double getQ2ChineseA() {
		return q2ChineseA;
	}

	public void setQ2ChineseA(double chineseA) {
		q2ChineseA = chineseA;
	}

	public double getQ2ChineseB() {
		return q2ChineseB;
	}

	public void setQ2ChineseB(double chineseB) {
		q2ChineseB = chineseB;
	}

	public double getQ3Math() {
		return q3Math;
	}

	public void setQ3Math(double math) {
		q3Math = math;
	}

	public double getQ3Science() {
		return q3Science;
	}

	public void setQ3Science(double science) {
		q3Science = science;
	}

	public double getQ3English() {
		return q3English;
	}

	public void setQ3English(double english) {
		q3English = english;
	}

	public double getQ3Filipino() {
		return q3Filipino;
	}

	public void setQ3Filipino(double filipino) {
		q3Filipino = filipino;
	}

	public double getQ3Reading() {
		return q3Reading;
	}

	public void setQ3Reading(double reading) {
		q3Reading = reading;
	}

	public double getQ3Writing() {
		return q3Writing;
	}

	public void setQ3Writing(double writing) {
		q3Writing = writing;
	}

	public double getQ3Computer() {
		return q3Computer;
	}

	public void setQ3Computer(double computer) {
		q3Computer = computer;
	}

	public double getQ3CCF() {
		return q3CCF;
	}

	public void setQ3CCF(double q3ccf) {
		q3CCF = q3ccf;
	}

	public double getQ3AP() {
		return q3AP;
	}

	public void setQ3AP(double q3ap) {
		q3AP = q3ap;
	}

	public double getQ3Hekasi() {
		return q3Hekasi;
	}

	public void setQ3Hekasi(double hekasi) {
		q3Hekasi = hekasi;
	}

	public double getQ3TLE() {
		return q3TLE;
	}

	public void setQ3TLE(double q3tle) {
		q3TLE = q3tle;
	}

	public double getQ3CE() {
		return q3CE;
	}

	public void setQ3CE(double q3ce) {
		q3CE = q3ce;
	}

	public double getQ3Music() {
		return q3Music;
	}

	public void setQ3Music(double music) {
		q3Music = music;
	}

	public double getQ3Arts() {
		return q3Arts;
	}

	public void setQ3Arts(double arts) {
		q3Arts = arts;
	}

	public double getQ3PE() {
		return q3PE;
	}

	public void setQ3PE(double q3pe) {
		q3PE = q3pe;
	}

	public double getQ3ME() {
		return q3ME;
	}

	public void setQ3ME(double q3me) {
		q3ME = q3me;
	}

	public double getQ3MAPEH() {
		return q3MAPEH;
	}

	public void setQ3MAPEH(double q3mapeh) {
		q3MAPEH = q3mapeh;
	}

	public double getQ3ChineseA() {
		return q3ChineseA;
	}

	public void setQ3ChineseA(double chineseA) {
		q3ChineseA = chineseA;
	}

	public double getQ3ChineseB() {
		return q3ChineseB;
	}

	public void setQ3ChineseB(double chineseB) {
		q3ChineseB = chineseB;
	}

	public double getQ4Math() {
		return q4Math;
	}

	public void setQ4Math(double math) {
		q4Math = math;
	}

	public double getQ4Science() {
		return q4Science;
	}

	public void setQ4Science(double science) {
		q4Science = science;
	}

	public double getQ4English() {
		return q4English;
	}

	public void setQ4English(double english) {
		q4English = english;
	}

	public double getQ4Filipino() {
		return q4Filipino;
	}

	public void setQ4Filipino(double filipino) {
		q4Filipino = filipino;
	}

	public double getQ4Reading() {
		return q4Reading;
	}

	public void setQ4Reading(double reading) {
		q4Reading = reading;
	}

	public double getQ4Writing() {
		return q4Writing;
	}

	public void setQ4Writing(double writing) {
		q4Writing = writing;
	}

	public double getQ4Computer() {
		return q4Computer;
	}

	public void setQ4Computer(double computer) {
		q4Computer = computer;
	}

	public double getQ4CCF() {
		return q4CCF;
	}

	public void setQ4CCF(double q4ccf) {
		q4CCF = q4ccf;
	}

	public double getQ4AP() {
		return q4AP;
	}

	public void setQ4AP(double q4ap) {
		q4AP = q4ap;
	}

	public double getQ4Hekasi() {
		return q4Hekasi;
	}

	public void setQ4Hekasi(double hekasi) {
		q4Hekasi = hekasi;
	}

	public double getQ4TLE() {
		return q4TLE;
	}

	public void setQ4TLE(double q4tle) {
		q4TLE = q4tle;
	}

	public double getQ4CE() {
		return q4CE;
	}

	public void setQ4CE(double q4ce) {
		q4CE = q4ce;
	}

	public double getQ4Music() {
		return q4Music;
	}

	public void setQ4Music(double music) {
		q4Music = music;
	}

	public double getQ4Arts() {
		return q4Arts;
	}

	public void setQ4Arts(double arts) {
		q4Arts = arts;
	}

	public double getQ4PE() {
		return q4PE;
	}

	public void setQ4PE(double q4pe) {
		q4PE = q4pe;
	}

	public double getQ4ME() {
		return q4ME;
	}

	public void setQ4ME(double q4me) {
		q4ME = q4me;
	}

	public double getQ4MAPEH() {
		return q4MAPEH;
	}

	public void setQ4MAPEH(double q4mapeh) {
		q4MAPEH = q4mapeh;
	}

	public double getQ4ChineseA() {
		return q4ChineseA;
	}

	public void setQ4ChineseA(double chineseA) {
		q4ChineseA = chineseA;
	}

	public double getQ4ChineseB() {
		return q4ChineseB;
	}

	public void setQ4ChineseB(double chineseB) {
		q4ChineseB = chineseB;
	}

	public double getQ1Math2() {
		return q1Math2;
	}

	public void setQ1Math2(double math2) {
		q1Math2 = math2;
	}

	public double getQ1Math3() {
		return q1Math3;
	}

	public void setQ1Math3(double math3) {
		q1Math3 = math3;
	}

	public double getQ1Science2() {
		return q1Science2;
	}

	public void setQ1Science2(double science2) {
		q1Science2 = science2;
	}

	public double getQ1Science3() {
		return q1Science3;
	}

	public void setQ1Science3(double science3) {
		q1Science3 = science3;
	}

	public double getQ1English2() {
		return q1English2;
	}

	public void setQ1English2(double english2) {
		q1English2 = english2;
	}

	public double getQ1English3() {
		return q1English3;
	}

	public void setQ1English3(double english3) {
		q1English3 = english3;
	}

	public double getQ2Math2() {
		return q2Math2;
	}

	public void setQ2Math2(double math2) {
		q2Math2 = math2;
	}

	public double getQ2Math3() {
		return q2Math3;
	}

	public void setQ2Math3(double math3) {
		q2Math3 = math3;
	}

	public double getQ2Science2() {
		return q2Science2;
	}

	public void setQ2Science2(double science2) {
		q2Science2 = science2;
	}

	public double getQ2Science3() {
		return q2Science3;
	}

	public void setQ2Science3(double science3) {
		q2Science3 = science3;
	}

	public double getQ2English2() {
		return q2English2;
	}

	public void setQ2English2(double english2) {
		q2English2 = english2;
	}

	public double getQ2English3() {
		return q2English3;
	}

	public void setQ2English3(double english3) {
		q2English3 = english3;
	}

	public double getQ3Math2() {
		return q3Math2;
	}

	public void setQ3Math2(double math2) {
		q3Math2 = math2;
	}

	public double getQ3Math3() {
		return q3Math3;
	}

	public void setQ3Math3(double math3) {
		q3Math3 = math3;
	}

	public double getQ3Science2() {
		return q3Science2;
	}

	public void setQ3Science2(double science2) {
		q3Science2 = science2;
	}

	public double getQ3Science3() {
		return q3Science3;
	}

	public void setQ3Science3(double science3) {
		q3Science3 = science3;
	}

	public double getQ3English2() {
		return q3English2;
	}

	public void setQ3English2(double english2) {
		q3English2 = english2;
	}

	public double getQ3English3() {
		return q3English3;
	}

	public void setQ3English3(double english3) {
		q3English3 = english3;
	}

	public double getQ4Math2() {
		return q4Math2;
	}

	public void setQ4Math2(double math2) {
		q4Math2 = math2;
	}

	public double getQ4Math3() {
		return q4Math3;
	}

	public void setQ4Math3(double math3) {
		q4Math3 = math3;
	}

	public double getQ4Science2() {
		return q4Science2;
	}

	public void setQ4Science2(double science2) {
		q4Science2 = science2;
	}

	public double getQ4Science3() {
		return q4Science3;
	}

	public void setQ4Science3(double science3) {
		q4Science3 = science3;
	}

	public double getQ4English2() {
		return q4English2;
	}

	public void setQ4English2(double english2) {
		q4English2 = english2;
	}

	public double getQ4English3() {
		return q4English3;
	}

	public void setQ4English3(double english3) {
		q4English3 = english3;
	}

	public double getQallMath2() {
		return qallMath2;
	}

	public void setQallMath2(double qallMath2) {
		this.qallMath2 = qallMath2;
	}

	public double getQallMath3() {
		return qallMath3;
	}

	public void setQallMath3(double qallMath3) {
		this.qallMath3 = qallMath3;
	}

	public double getQallScience2() {
		return qallScience2;
	}

	public void setQallScience2(double qallScience2) {
		this.qallScience2 = qallScience2;
	}

	public double getQallScience3() {
		return qallScience3;
	}

	public void setQallScience3(double qallScience3) {
		this.qallScience3 = qallScience3;
	}

	public double getQallEnglish2() {
		return qallEnglish2;
	}

	public void setQallEnglish2(double qallEnglish2) {
		this.qallEnglish2 = qallEnglish2;
	}

	public double getQallEnglish3() {
		return qallEnglish3;
	}

	public void setQallEnglish3(double qallEnglish3) {
		this.qallEnglish3 = qallEnglish3;
	}

	public double getQ1Language() {
		return q1Language;
	}

	public void setQ1Language(double language) {
		q1Language = language;
	}

	public double getQ2Language() {
		return q2Language;
	}

	public void setQ2Language(double language) {
		q2Language = language;
	}

	public double getQ3Language() {
		return q3Language;
	}

	public void setQ3Language(double language) {
		q3Language = language;
	}

	public double getQ4Language() {
		return q4Language;
	}

	public void setQ4Language(double language) {
		q4Language = language;
	}

	public double getQallLanguage() {
		return qallLanguage;
	}

	public void setQallLanguage(double qallLanguage) {
		this.qallLanguage = qallLanguage;
	}

	public double getQ1Makabayan() {
		return q1Makabayan;
	}

	public void setQ1Makabayan(double makabayan) {
		q1Makabayan = makabayan;
	}

	public double getQ2Makabayan() {
		return q2Makabayan;
	}

	public void setQ2Makabayan(double makabayan) {
		q2Makabayan = makabayan;
	}

	public double getQ3Makabayan() {
		return q3Makabayan;
	}

	public void setQ3Makabayan(double makabayan) {
		q3Makabayan = makabayan;
	}

	public double getQ4Makabayan() {
		return q4Makabayan;
	}

	public void setQ4Makabayan(double makabayan) {
		q4Makabayan = makabayan;
	}

	public double getQallMakabayan() {
		return qallMakabayan;
	}

	public void setQallMakabayan(double qallMakabayan) {
		this.qallMakabayan = qallMakabayan;
	}

	public int getRankQ1() {
		return rankQ1;
	}

	public void setRankQ1(int rankQ1) {
		this.rankQ1 = rankQ1;
	}

	public int getRankQ2() {
		return rankQ2;
	}

	public void setRankQ2(int rankQ2) {
		this.rankQ2 = rankQ2;
	}

	public int getRankQ3() {
		return rankQ3;
	}

	public void setRankQ3(int rankQ3) {
		this.rankQ3 = rankQ3;
	}

	public int getRankQ4() {
		return rankQ4;
	}

	public void setRankQ4(int rankQ4) {
		this.rankQ4 = rankQ4;
	}

	public int getRankFinal() {
		return rankFinal;
	}

	public void setRankFinal(int rankFinal) {
		this.rankFinal = rankFinal;
	}

	public double getGpaValues1() {
        return gpaValues1;
    }

    public void setGpaValues1(double gpaValues1) {
        this.gpaValues1 = gpaValues1;
    }

    public double getGpaValues2() {
        return gpaValues2;
    }

    public void setGpaValues2(double gpaValues2) {
        this.gpaValues2 = gpaValues2;
    }

    public double getGpaValues3() {
        return gpaValues3;
    }

    public void setGpaValues3(double gpaValues3) {
        this.gpaValues3 = gpaValues3;
    }

    public double getGpaValues4() {
        return gpaValues4;
    }

    public void setGpaValues4(double gpaValues4) {
        this.gpaValues4 = gpaValues4;
    }

    public double getGpaValuesFinal() {
        return gpaValuesFinal;
    }

    public void setGpaValuesFinal(double gpaValuesFinal) {
        this.gpaValuesFinal = gpaValuesFinal;
    }

    public double getQ1TEPP() {
        return q1TEPP;
    }

    public void setQ1TEPP(double q1TEPP) {
        this.q1TEPP = q1TEPP;
    }

    public double getQ2TEPP() {
        return q2TEPP;
    }

    public void setQ2TEPP(double q2TEPP) {
        this.q2TEPP = q2TEPP;
    }

    public double getQ3TEPP() {
        return q3TEPP;
    }

    public void setQ3TEPP(double q3TEPP) {
        this.q3TEPP = q3TEPP;
    }

    public double getQ4TEPP() {
        return q4TEPP;
    }

    public void setQ4TEPP(double q4TEPP) {
        this.q4TEPP = q4TEPP;
    }

    public double getQallTEPP() {
        return qallTEPP;
    }

    public void setQallTEPP(double qallTEPP) {
        this.qallTEPP = qallTEPP;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear","semester", "gradeLevel");
    }

    @Override
    public void save() {
    	if (isEmpty(student)) {
            Student stud = extractStudentObj();
        	if (stud!=null) {
        		student = stud.toString();
        	}
        	if (stud!=null) {
                stud.plan = paymentPlanType;
                stud.status = "Enrolled";
                stud.save();
        	}
            if (college) {
            	if (AppConfig.getSchoolYear().equals(schoolYear) && !(BeanUtil.concat(stud.section)).equals(section)) {
            		stud.section = section;
            		stud.save();
            	}
            }
    	}
		setFinalGrade("OpMath",
				"Math","Math2","Math3",
				"Science","Science2","Science3",
				"English","English2","English3",
				"Reading",
				"Language",
				"Writing",
				"Computer",
				"CCF","CE",
				"AP",
				"Hekasi",
				"TLE",
				"Music",
				"Arts",
				"PE",
				"MAPEH",
				"ChineseA","ChineseB",
				"Makabayan",
				"TEPP",
				"Filipino",
				"VP",
				"ZL",
				"MC",
				"Health");
//		check merit for final
		String meritAll = BeanUtil.concat("|",meritQ1,"|",meritQ2,"|",meritQ3,"|",meritQ4,"|");
		if (meritAll.contains("||") || meritAll.contains("|null|")) {
			meritFinal = "";
		}
		else if (meritAll.contains("|WHITE|")) {
			meritFinal = "BRONZE";
		}
		else if (meritAll.contains("|YELLOW|")) {
			meritFinal = "SILVER";
		}
		else if (meritAll.contains("|GREEN|")) {
			meritFinal = "GOLD";
		}
        super.save();
    }

    private void setFinalGrade(String... string) {
    	for (String s:string) {
    		double val1 = BeanUtil.getDoubleValue(this, BeanUtil.concat("q1",s));
    		double val2 = BeanUtil.getDoubleValue(this, BeanUtil.concat("q2",s));
    		double val3 = BeanUtil.getDoubleValue(this, BeanUtil.concat("q3",s));
    		double val4 = BeanUtil.getDoubleValue(this, BeanUtil.concat("q4",s));
    		int counter = 0;
    		double total = 0;
    		if (val1 > 60) {
    			counter++;
    			total += (int)(val1+.5);
    		}
    		if (val2 > 60) {
    			counter++;
    			total += (int)(val2+.5);
    		}
    		if (val3 > 60) {
    			counter++;
    			total += (int)(val3+.5);
    		}
    		if (val4 > 60) {
    			counter++;
    			total += (int)(val4+.5);
    		}
    		if (total > 50) {
        		double d = DataUtil.getMoneyFormat(total/counter);
        		BeanUtil.setPropertyValue(this, BeanUtil.concat("qall",s), d);
    		}
    		else {
        		BeanUtil.setPropertyValue(this, BeanUtil.concat("qall",s), 0);
    		}
    	}
	}

	public double getGpa1() {
		return gpa1;
	}

	public void setGpa1(double gpa1) {
		this.gpa1 = gpa1;
	}

	public double getGpa2() {
		return gpa2;
	}

	public void setGpa2(double gpa2) {
		this.gpa2 = gpa2;
	}

	public double getGpa3() {
		return gpa3;
	}

	public void setGpa3(double gpa3) {
		this.gpa3 = gpa3;
	}

	public double getGpa4() {
		return gpa4;
	}

	public void setGpa4(double gpa4) {
		this.gpa4 = gpa4;
	}

	public double getGpaFinal() {
		return gpaFinal;
	}

	public void setGpaFinal(double gpaFinal) {
		this.gpaFinal = gpaFinal;
	}

	public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getRequestedDiscountAmount() {
        return requestedDiscountAmount;
    }

    public void setRequestedDiscountAmount(double requestedDiscountAmount) {
        this.requestedDiscountAmount = requestedDiscountAmount;
    }

    public boolean isRequestedDiscountApproved() {
        return requestedDiscountApproved;
    }

    public void setRequestedDiscountApproved(boolean requestedDiscountApproved) {
        this.requestedDiscountApproved = requestedDiscountApproved;
    }

    public String getRequestedDiscountStatus() {
        return requestedDiscountStatus;
    }

    public void setRequestedDiscountStatus(String requestedDiscountStatus) {
        this.requestedDiscountStatus = requestedDiscountStatus;
    }

    public Date getRequestedDiscountApprovedDate() {
        return requestedDiscountApprovedDate;
    }

    public void setRequestedDiscountApprovedDate(Date requestedDiscountApprovedDate) {
        this.requestedDiscountApprovedDate = requestedDiscountApprovedDate;
    }

    public String getRequestedDiscountApprovedBy() {
        return requestedDiscountApprovedBy;
    }

    public void setRequestedDiscountApprovedBy(String requestedDiscountApprovedBy) {
        this.requestedDiscountApprovedBy = requestedDiscountApprovedBy;
    }

    public String getRequestedDiscountBy() {
        return requestedDiscountBy;
    }

    public void setRequestedDiscountBy(String requestedDiscountBy) {
        this.requestedDiscountBy = requestedDiscountBy;
    }


    public Date getRequestedDiscountDate() {
        return requestedDiscountDate;
    }

    public void setRequestedDiscountDate(Date requestedDiscountDate) {
        this.requestedDiscountDate = requestedDiscountDate;
    }

    public String getRequestedDiscountType() {
        return requestedDiscountType;
    }

    public void setRequestedDiscountType(String requestedDiscountType) {
        this.requestedDiscountType = requestedDiscountType;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getACR() {
        return aCR;
    }

    public void setACR(String aCR) {
        this.aCR = aCR;
    }

    public String getATSM7577English() {
        return aTSM7577English;
    }

    public void setATSM7577English(String aTSM7577English) {
        this.aTSM7577English = aTSM7577English;
    }

    public String getATSM7577Math() {
        return aTSM7577Math;
    }

    public void setATSM7577Math(String aTSM7577Math) {
        this.aTSM7577Math = aTSM7577Math;
    }

    public String getATSM7577Science() {
        return aTSM7577Science;
    }

    public void setATSM7577Science(String aTSM7577Science) {
        this.aTSM7577Science = aTSM7577Science;
    }

    public String getATSMFailedEnglish() {
        return aTSMFailedEnglish;
    }

    public void setATSMFailedEnglish(String aTSMFailedEnglish) {
        this.aTSMFailedEnglish = aTSMFailedEnglish;
    }

    public String getATSMFailedMath() {
        return aTSMFailedMath;
    }

    public void setATSMFailedMath(String aTSMFailedMath) {
        this.aTSMFailedMath = aTSMFailedMath;
    }

    public String getATSMFailedScience() {
        return aTSMFailedScience;
    }

    public void setATSMFailedScience(String aTSMFailedScience) {
        this.aTSMFailedScience = aTSMFailedScience;
    }

    public String getATSummerAcademic() {
        return aTSummerAcademic;
    }

    public void setATSummerAcademic(String aTSummerAcademic) {
        this.aTSummerAcademic = aTSummerAcademic;
    }

    public String getATTransferSchool() {
        return aTTransferSchool;
    }

    public void setATTransferSchool(String aTTransferSchool) {
        this.aTTransferSchool = aTTransferSchool;
    }

    public String getBaptismal() {
        return baptismal;
    }

    public void setBaptismal(String baptismal) {
        this.baptismal = baptismal;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getBusService() {
        return busService;
    }

    public void setBusService(String busService) {
        this.busService = busService;
    }

    public int getCummTotalDaysInGrade() {
        return cummTotalDaysInGrade;
    }

    public void setCummTotalDaysInGrade(int cummTotalDaysInGrade) {
        this.cummTotalDaysInGrade = cummTotalDaysInGrade;
    }

    public int getCummTotalYearsInSchool() {
        return cummTotalYearsInSchool;
    }

    public void setCummTotalYearsInSchool(int cummTotalYearsInSchool) {
        this.cummTotalYearsInSchool = cummTotalYearsInSchool;
    }

    public String getCurrentlyEnrolled() {
        return currentlyEnrolled;
    }

    public void setCurrentlyEnrolled(String currentlyEnrolled) {
        this.currentlyEnrolled = currentlyEnrolled;
    }

    public String getFIS() {
        return fIS;
    }

    public void setFIS(String fIS) {
        this.fIS = fIS;
    }

    public String getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPassportVerification() {
        return passportVerification;
    }

    public void setPassportVerification(String passportVerification) {
        this.passportVerification = passportVerification;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRemedialClass() {
        return remedialClass;
    }

    public void setRemedialClass(String remedialClass) {
        this.remedialClass = remedialClass;
    }

    public String getReportCard() {
        return reportCard;
    }

    public void setReportCard(String reportCard) {
        this.reportCard = reportCard;
    }

    public String getScholasticRecord() {
        return scholasticRecord;
    }

    public void setScholasticRecord(String scholasticRecord) {
        this.scholasticRecord = scholasticRecord;
    }

    public String getSchoolYearPlan() {
        return schoolYearPlan;
    }

    public void setSchoolYearPlan(String schoolYearPlan) {
        this.schoolYearPlan = schoolYearPlan;
    }

    public String getSiblings1() {
        return siblings1;
    }

    public void setSiblings1(String siblings1) {
        this.siblings1 = siblings1;
    }

    public String getSiblings2() {
        return siblings2;
    }

    public void setSiblings2(String siblings2) {
        this.siblings2 = siblings2;
    }

    public String getSiblings3() {
        return siblings3;
    }

    public void setSiblings3(String siblings3) {
        this.siblings3 = siblings3;
    }

    public String getSiblings4() {
        return siblings4;
    }

    public void setSiblings4(String siblings4) {
        this.siblings4 = siblings4;
    }

    public String getSiblings5() {
        return siblings5;
    }

    public void setSiblings5(String siblings5) {
        this.siblings5 = siblings5;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getTypeOfTuitionPayment() {
        return typeOfTuitionPayment;
    }

    public void setTypeOfTuitionPayment(String typeOfTuitionPayment) {
        this.typeOfTuitionPayment = typeOfTuitionPayment;
    }

    public String getUnsettledTuitionFee() {
        return unsettledTuitionFee;
    }

    public void setUnsettledTuitionFee(String unsettledTuitionFee) {
        this.unsettledTuitionFee = unsettledTuitionFee;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public double getAthleticFee() {
        return athleticFee;
    }

    public void setAthleticFee(double athleticFee) {
        this.athleticFee = athleticFee;
    }

    public double getAudioVisualFee() {
        return audioVisualFee;
    }

    public void setAudioVisualFee(double audioVisualFee) {
        this.audioVisualFee = audioVisualFee;
    }

    public double getComputerFee() {
        return computerFee;
    }

    public void setComputerFee(double computerFee) {
        this.computerFee = computerFee;
    }

    public double getDiplomaFee() {
        return diplomaFee;
    }

    public void setDiplomaFee(double diplomaFee) {
        this.diplomaFee = diplomaFee;
    }

    public double getHandbookFee() {
        return handbookFee;
    }

    public void setHandbookFee(double handbookFee) {
        this.handbookFee = handbookFee;
    }

    public double getIdFee() {
        return idFee;
    }

    public void setIdFee(double idFee) {
        this.idFee = idFee;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public double getLaboratoryFee() {
        return laboratoryFee;
    }

    public void setLaboratoryFee(double laboratoryFee) {
        this.laboratoryFee = laboratoryFee;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getMaterialsFee() {
        return materialsFee;
    }

    public void setMaterialsFee(double materialsFee) {
        this.materialsFee = materialsFee;
    }

    public double getMedicalDentalFee() {
        return medicalDentalFee;
    }

    public void setMedicalDentalFee(double medicalDentalFee) {
        this.medicalDentalFee = medicalDentalFee;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public Date getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(Date dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Enrollment() {
        accountNumber = "102";
        paymentStatus = "NEW";
        dateEnrolled = constants.Constants.useDate;
        schoolYear = AppConfig.getSchoolYear();
    }

    public double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(double adjustment) {
        this.adjustment = adjustment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(double amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }

    public double getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(double backAccount) {
        this.backAccount = backAccount;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    public void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public double getMiscellaneousFee() {
        return miscellaneousFee;
    }

    public void setMiscellaneousFee(double miscellaneousFee) {
        this.miscellaneousFee = miscellaneousFee;
    }

    public double getOverAllAmount() {
        return overAllAmount;
    }

    public void setOverAllAmount(double overAllAmount) {
        this.overAllAmount = overAllAmount;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(int totalUnit) {
        this.totalUnit = totalUnit;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public double getComputedOverallMisc() {
        miscellaneousFee = registrationFee + materialsFee + idFee + medicalDentalFee + libraryFee + laboratoryFee +
                audioVisualFee + athleticFee + insuranceFee + computerFee + handbookFee + diplomaFee + annualFee;
        return miscellaneousFee;
    }

    public double getComputedOverallAmount() {
        boolean includeMisc = SchoolConfig.isMiscPartOfOverallFee();
        if (includeMisc) {
            return getComputedOverallMisc() + tuitionFee + adjustment - discount;
        } else {
            return tuitionFee + adjustment - discount;
        }
    }

    public double getComputedInstallmentAmount() {
        return getComputedOverallAmount() / payCount;
    }

    public void setStudent(String student) {
		this.student = student;
	}

	public String getStudent() {
		return student;
    }

    public Student extractStudentObj() {
        if (studentId == 0) {
            return null;
        }
        return (Student) DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.seq=",this.studentId);
    }

    public static void main(String[] args) {
        AAAConfig.getServerInstance();
    	new Enrollment().setupIndex();
        view(Enrollment.class);
    }
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getBarInstance(this, "Enrollment By SY - Course", "SELECT a.schoolYear, b.course, COUNT(b.course) FROM Enrollment a, Student b WHERE a.studentId=b.personId GROUP BY a.schoolYear, b.course"));
        vec.add(ChartBean.getPieInstance(this, "Enrollment By Grade/Course", "SELECT b.course, COUNT(b.course) FROM Enrollment a, Student b WHERE a.studentId=b.personId GROUP BY b.course"));
        vec.add(ChartBean.getBarInstance(this, "Enrollment By Section", "SELECT b.gradeLevel, b.code, COUNT(a.section) FROM Enrollment a, Section b WHERE a.section=b.code AND b.gradeLevel='" + gradeLevel + "' GROUP BY b.gradeLevel, b.code"));
        vec.add(ChartBean.getPieInstance(this, "Student Performance", "SELECT a.subject, a.grade FROM StudentSubject a WHERE a.enrollmentId=" + (seq == null ? 0 : seq)));
        return vec;
    }

    public String getPaymentPlanType() {
        return paymentPlanType;
    }

    public void setPaymentPlanType(String paymentPlanType) {
        this.paymentPlanType = paymentPlanType;
    }
	@Override
	public void setupIndex() {
            DBClient.runSQLNative("DELETE FROM Enrollment WHERE studentId=0");
		String sql = "select studentid, gradelevel, count(*) c from enrollment where studentid > 0 and gradelevel is not null group by studentid, gradelevel having c > 1";
		List l = DBClient.getListNative(sql);
                for (Object o:l) {
                    List v = (List) o;
                    String studId = v.get(0).toString();
                    String gLevel = (String) v.get(1);
                    List<Enrollment> le = DBClient.getList("SELECT a FROM Enrollment a WHERE a.studentId=",studId," AND a.gradeLevel='",gLevel,"'");
//                    just get the enroll id
                    if (le.size() > 1) {
                        Enrollment enrollWithGrade = le.get(0);
                        for (Enrollment e:le) {
                            if (e.q1Math > 0 || e.q1English > 0 || e.q1Science > 0 || e.q1MAPEH > 0) {
                                enrollWithGrade = e;
//                                need to modify the schoolyear
                            }
                        }
                        for (Enrollment e:le) {
                            if (e.equals(enrollWithGrade)) {
                                continue;
                            }
                            DBClient.runSQLNative("UPDATE Payment SET recordId="+enrollWithGrade.seq+" WHERE recordId="+e.seq);
                            e.delete();
                        }
                    }
                }
		runIndex(1, "studentId","schoolYear");
		runIndex(2, "schoolYear","section");
		runIndex(3, "studentId","gradeLevel");
		runUniqueIndex(4, "studentId","gradeLevel");
	}

	public boolean noConflict() {
		List<Integer> duplicateLst = new ArrayList();
		for (int i=1; i<=15; i++) {
			Integer val = (Integer) BeanUtil.getPropertyValue(this, BeanUtil.concat("schedule",i));
			if (val==0) continue;
			if (duplicateLst.contains(val)) {
				return false;
			}
			duplicateLst.add(val);
		}

		StringBuffer sb = new StringBuffer();
		sb.append(schedule1).append(",");
		sb.append(schedule2).append(",");
		sb.append(schedule3).append(",");
		sb.append(schedule4).append(",");
		sb.append(schedule5).append(",");
		sb.append(schedule6).append(",");
		sb.append(schedule7).append(",");
		sb.append(schedule8).append(",");
		sb.append(schedule9).append(",");
		sb.append(schedule10).append(",");
		sb.append(schedule11).append(",");
		sb.append(schedule12).append(",");
		sb.append(schedule13).append(",");
		sb.append(schedule14).append(",");
		sb.append(schedule15);
		List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.seq IN (",sb.toString(),")");
		return Schedule.noConflict(lst);
	}

	public void linkSubjects() {
		for (int i=1; i<=15; i++) {
			Object sub = BeanUtil.getPropertyValue(this, BeanUtil.concat("subject",i));
			if (sub==null) {
				continue;
			}
			StudentSubject s = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId=",studentId," AND a.subject='",sub.toString(),"'");
			if (s!=null) {
				s.schoolYear = schoolYear;
				s.preferredSemester = semester;
				s.save();
			}
		}
	}
}
