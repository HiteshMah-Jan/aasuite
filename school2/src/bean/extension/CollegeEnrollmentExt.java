/*
 * Enrollmentassessment.java
 *
 * Created on Nov 27, 2007, 6:57:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import java.io.Serializable;

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
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;
import bean.Enrollment;
import bean.accounting.PaymentEnrollment;
import bean.admin.AppConfig;
import bean.extension.ScheduleCollegeExt;
import bean.extension.SectionCollegeExt;
import bean.extension.StudentCollegeExt;

/**
 *
 * @author Ebhoy
 */
@UITemplate( //    template=TemplateScrollableTabPage.class,
template = TemplateTabSinglePage.class,
columnSearch = {"student", "schoolYear", "semester", "totalUnit","overAllAmount","paymentPlanType"},
criteriaSearch = {"studentId", "schoolYear", "section","semester", "paymentPlanType"},
gridCount = 6, title = "College Enrollment",
showChart = false, select="SELECT a FROM Enrollment a WHERE a.college=true", autoResizeTable=false)
@ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListOnly.class, fieldMapping = {"seq", "recordId"}, entity = PaymentEnrollment.class, sql = "SELECT a FROM PaymentEnrollment a WHERE a.recordId=${seq}", title = "Payments")
},
info = {
//}),
    @template.ParentAddInfo(title = "Payment Info",displayFields = { }, hideGroup="0"),
    @template.ParentAddInfo(title = "Schedules",displayFields = { }, hideGroup="1,2")
})
@Displays({
    @Display(name = "studentId", linktoBean = StudentCollegeExt.class, label = "Student", gridFieldWidth = 5, width = -1, type = "PopSearch"),
    @Display(name = "schoolYear", enabled=false),
    @Display(name = "section", type = "PopSearch", linktoBean = SectionCollegeExt.class),
    @Display(name = "semester",type="Combo", modelCombo={"1", "2", "3"}),
    @Display(name = "paymentPlanType", gridFieldWidth = 5, type="Combo", label="Plan", modelCombo={"S","Q","M"},width=-1),
//    @Display(name = "tuitionFee", type="Text"),
//    @Display(name = "adjustment", type="Text"),
//    @Display(name = "discount", type="Text"),
//    @Display(name = "backAccount", type="Text"),
//    @Display(name = "accountNumber", modelCombo = {"102", "103.1", "103.2"}, width = 100, mandatory = true, type="Label"),

    @Display(name = "schedule1", labelTop=true,label = "Schedule", leftLabel="1", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=250),
//    @Display(name = "subject1", labelTop=true,label = "Subject", type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit1", labelTop=true,label = "Unit", enabled=false),
//    @Display(name = "faculty1", labelTop=true,label = "Faculty", type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount1", labelTop=true,label = "Amount"),
    
    @Display(name = "schedule2", label="2", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject2", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit2", hideLabel=true, enabled=false),
//    @Display(name = "faculty2", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount2", hideLabel=true),
    
    @Display(name = "schedule3", label="3", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject3", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit3", hideLabel=true, enabled=false),
//    @Display(name = "faculty3", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount3", hideLabel=true),
    
    @Display(name = "schedule4", label="4", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject4", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit4", hideLabel=true, enabled=false),
//    @Display(name = "faculty4", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount4", hideLabel=true),
    
    @Display(name = "schedule5", label="5", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject5", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit5", hideLabel=true, enabled=false),
//    @Display(name = "faculty5", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount5", hideLabel=true),
    
    @Display(name = "schedule6", label="6", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject6", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit6" ,hideLabel=true, enabled=false),
//    @Display(name = "faculty6", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount6", hideLabel=true),
    
    @Display(name = "schedule7", label="7", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject7", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit7", hideLabel=true, enabled=false),
//    @Display(name = "faculty7", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount7", hideLabel=true),
    
    @Display(name = "schedule8", label="8", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject8", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit8", hideLabel=true, enabled=false),
//    @Display(name = "faculty8", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount8", hideLabel=true),
    
    @Display(name = "schedule9", label="9", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject9", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit9", hideLabel=true, enabled=false),
//    @Display(name = "faculty9", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount9", hideLabel=true),
    
    @Display(name = "schedule10", label="10", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject10", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit10", hideLabel=true, enabled=false),
//    @Display(name = "faculty10", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount10", hideLabel=true),
    
    @Display(name = "schedule11", label="11", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject11", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit11", hideLabel=true, enabled=false),
//    @Display(name = "faculty11", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount11", hideLabel=true),
    
    @Display(name = "schedule12", label="12", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject12", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit12", hideLabel=true, enabled=false),
//    @Display(name = "faculty12", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount12", hideLabel=true),
    
    @Display(name = "schedule13", label="13", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject13", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit13", hideLabel=true, enabled=false),
//    @Display(name = "faculty13", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount13", hideLabel=true),
    
    @Display(name = "schedule14", label="14", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject14", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit14", hideLabel=true, enabled=false),
//    @Display(name = "faculty14", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount14", hideLabel=true),
    
    @Display(name = "schedule15", label="15", type = "PopSearch", linktoBean = ScheduleCollegeExt.class, width=-1),
//    @Display(name = "subject15", hideLabel=true, type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "unit15", hideLabel=true, enabled=false),
//    @Display(name = "faculty15", hideLabel=true, type = "PopSearch", linktoBean = EmployeeFaculty.class, enabled=false),
    @Display(name = "amount15", hideLabel=true),
    
    @Display(name = "totalUnits",label="Total Units", enabled=false),
    @Display(name = "totalAmount", label="Total Amount", enabled=false),
    @Display(name = "overAllAmount", label = "Overall Fee", width = -1, type="Text", enabled=false),
    
    @Display(name = "registrationFee", type="Text"),
    @Display(name = "materialsFee", type="Text"),
    @Display(name = "idFee", type="Text"),
    @Display(name = "medicalDentalFee", type="Text"),
    @Display(name = "libraryFee", type="Text"),
    @Display(name = "laboratoryFee", type="Text"),
    @Display(name = "audioVisualFee", type="Text"),
    @Display(name = "athleticFee", type="Text"),
    @Display(name = "insuranceFee", type="Text"),
    @Display(name = "computerFee", type="Text"),
    @Display(name = "handbookFee", type="Text"),
    @Display(name = "diplomaFee", type="Text"),
    @Display(name = "annualFee", type="Text"),
    @Display(name = "miscellaneousFee", type="Text", enabled=false)

    
//        @Display(name = "seq", type="Label"),
//        @Display(name="amountPerUnit", addInfoOnly=true),
//        @Display(name="isActive", addInfoOnly=true)
//        @Display(name="requestedDiscountType"),
//        @Display(name="requestedDiscountAmount"),
//        @Display(name="requestedDiscountApproved",type="CheckBox"),
//        @Display(name="requestedDiscountBy"),
//        @Display(name="requestedDiscountApprovedBy"),
//        @Display(name="requestedDiscountDate"),
//        @Display(name="requestedDiscountApprovedDate")
})
@DisplayGroups({
    @DisplayGroup(gridCount = 6, title = "Schedule Subject",  fields = {
        "schedule1","subject1","unit1","faculty1","amount1",
                    "schedule2","subject2","unit2","faculty2","amount2",
                    "schedule3","subject3","unit3","faculty3","amount3",
                    "schedule4","subject4","unit4","faculty4","amount4",
                    "schedule5","subject5","unit5","faculty5","amount5",
                    "schedule6","subject6","unit6","faculty6","amount6",
                    "schedule7","subject7","unit7","faculty7","amount7",
                    "schedule8","subject8","unit8","faculty8","amount8",
                    "schedule9","subject9","unit9","faculty9","amount9",
                    "schedule10","subject10","unit10","faculty10","amount10",
                    "schedule11","subject11","unit11","faculty11","amount11",
                    "schedule12","subject12","unit12","faculty12","amount12",
                    "schedule13","subject13","unit13","faculty13","amount13",
                    "schedule14","subject14","unit14","faculty14","amount14",
                    "schedule15","subject15","unit15","faculty15","amount15"
    }, addInfoOnly = true),
      @DisplayGroup(gridCount = 6, title = "Miscellaneous Info", fields = {
        "registrationFee", "materialsFee", "idFee", "medicalDentalFee", "libraryFee",
        "laboratoryFee","audioVisualFee", "athleticFee","insuranceFee", "computerFee",
        "handbookFee","diplomaFee", "annualFee", "miscellaneousFee"
    },addInfoOnly = true)
})
@ActionButtons({
    @ActionButton(name = "btnGetMisc", label = "Get Misc."),
    @ActionButton(name = "btnAssess", label = "Assess"),
    @ActionButton(name = "btnPrintSchedule", label = "Print Schedule"),
    @ActionButton(name = "btnPrintSOA", label = "Print SOA"),
    @ActionButton(name="btnPaymentPlan", label="Plans")
//    @ActionButton(name="btnPrintEnrolledSubjects", label="Print Enrolled Subjects")
})
@Reports({
//    @template.Report(reportFile = "StudentSchedule", reportTitle = "Student Schedule", reportSql = "${studentId}"),
//    @template.Report(reportFile = "StatementOfAccount", reportTitle = "Statement Of Account", reportSql = "${seq}"),
//    @template.Report(reportFile = "EnrolledStudentbySection", reportTitle = "Enrollment Report by Section", reportSql = "${section}"),
    @template.Report(reportFile = "OrganizationalListSection", reportTitle = "Student List per Section", reportSql = "${section}"),
     @template.Report(reportFile = "OfficialEnrollmentListStudentCount", reportTitle = "Official Enrollment List", reportSql = " "),
      @template.Report(reportFile = "officialOrganizationalListPreschool", reportTitle = "Preschool Org List", reportSql = ""),
       @template.Report(reportFile = "officialOrganizationalListElementary1", reportTitle = "Grade 1-3 Org List", reportSql = ""),
        @template.Report(reportFile = "officialOrganizationalListElementary2", reportTitle = "Grade 1-4 Org List", reportSql = ""),
         @template.Report(reportFile = "officialOrganizationalListHighschool", reportTitle = "High School Org List", reportSql = "")
    
//    @template.Report(reportFile = "AccountsReceivableSchedule", reportTitle = "Schedule of Accounts Receivables", reportSql = "${section}")
//    @template.Report(reportFile = "StudentLastAttendedSchool", reportTitle = "Student Last Attended School", reportSql = "${section}")
})
public class CollegeEnrollmentExt extends Enrollment {
	public CollegeEnrollmentExt() {
        accountNumber = "102";
        paymentStatus = "NEW";
        dateEnrolled = constants.Constants.useDate;
        schoolYear = AppConfig.getSchoolYear();
		college = true;
	}
	
	public static void main(String[] args) {
        view(CollegeEnrollmentExt.class);
    }

}
