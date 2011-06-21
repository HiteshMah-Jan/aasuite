/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.*;
import bean.accounting.PaymentEnrollment;
import bean.person.StudentSubject;
import bean.reference.Section;
import java.io.Serializable;
import template.*;
import template.screen.*;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
    template=TemplateTabPage.class,
    columnSearch={"student","schoolYear","section","paymentMode"}, 
    criteriaSearch={"studentId","schoolYear","section","paymentMode"}, 
    gridCount=4, title="Enrollment",
    showChart=true, 
    select="SELECT a FROM Enrollment a WHERE a.enrollmentType='SUMMER' ")
@ChildRecords(value={
    @ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","enrollmentId"}, entity=StudentSubject.class, sql="SELECT a FROM StudentSubject a WHERE a.enrollmentId=${seq}", title="Subjects"),
    @ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","recordId"}, entity=PaymentEnrollment.class, sql="SELECT a FROM PaymentEnrollment a WHERE a.isActive=true AND a.recordId=${seq}", title="Payments")
}
,
    info={
        @ParentAddInfo(title="Miscellaneous Info", 
            displayFields={"registrationFee","materialsFee","idFee","medicalDentalFee","libraryFee","laboratoryFee",
            "audioVisualFee","athleticFee","insuranceFee","computerFee","handbookFee","diplomaFee","annualFee"})
})
@Displays({
        @Display(name="seq", addInfoOnly=true),
        @Display(name="studentId", linktoBean=Student.class, label="Student", gridFieldWidth=3, width=-1, type="PopSearch"),
        @Display(name="schoolYear"),
        @Display(name="section", type="PopSearch", linktoBean=Section.class),
        @Display(name="tuitionFee"),
        @Display(name="miscellaneousFee"),
        @Display(name="adjustment"),
        @Display(name="discount"),
        @Display(name="backAccount"),

        @Display(name="paymentMode", type="Combo", modelCombo={"CASH", "INSTALLMENT"}, gridFieldWidth=3),
        @Display(name="payCount", type="NumberCombo", endCount=10),
        @Display(name="dayOfMonth", type="NumberCombo", endCount=31),
        @Display(name="overAllAmount", label="Overall Fee", width=-1),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=100, mandatory=true),
        @Display(name="installmentAmount", width=-1, label="Cash/Installment Amount"),

        @Display(name = "registrationFee", addInfoOnly=true),
        @Display(name = "materialsFee", addInfoOnly=true),
        @Display(name = "idFee", addInfoOnly=true),
        @Display(name = "medicalDentalFee", addInfoOnly=true),
        @Display(name = "libraryFee", addInfoOnly=true),
        @Display(name = "laboratoryFee", addInfoOnly = true),
        @Display(name = "audioVisualFee", addInfoOnly=true),
        @Display(name = "athleticFee", addInfoOnly=true),
        @Display(name = "insuranceFee", addInfoOnly=true),
        @Display(name = "computerFee", addInfoOnly=true),
        @Display(name = "handbookFee", addInfoOnly=true),
        @Display(name = "diplomaFee", addInfoOnly=true),
        @Display(name = "annualFee", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(title="Payment Scheme", fields={"paymentMode", "semester", "payCount", "dayOfMonth", "overAllAmount", "installmentAmount"})
})
//@ActionButtons({
//    @ActionButton(name="btnCreateSchedules", label="Create Schedules"),
//    @ActionButton(name="btnCreatePayments", label="Create Payment/s"),
//    @ActionButton(name="btnViewPossibleSched", label="View Possible Schedule")
//})
@Reports({
    @template.Report(reportFile="StudentSchedule", reportTitle="Student Schedule", reportSql="${studentId}"),
    @template.Report(reportFile="StatementOfAccount", reportTitle="Statement Of Account", reportSql="${seq}"),
    @template.Report(reportFile="EnrolledStudentbySection", reportTitle="Enrollment Report by Section", reportSql="${section}"),
    @template.Report(reportFile="OfficialEnrollmentList", reportTitle="Official Enrollment List", reportSql="${section}"),
    @template.Report(reportFile="AccountsReceivableSchedule", reportTitle="Schedule of Accounts Receivables", reportSql="${section}"),
    @template.Report(reportFile="StudentLastAttendedSchool", reportTitle="Student Last Attended School", reportSql="${section}")
})
public class EnrollmentSummerExt extends Enrollment implements Serializable {
    public EnrollmentSummerExt() {
        this.enrollmentType = "SUMMER";
    }
}
