/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import springbean.AAAConfig;
import util.DBClient;

/**
 *
 * @author alex
 */
public class Cleaner {
    public static void cleanCore() {
        DBClient.runSQLNative("DELETE FROM BankAccount");
        DBClient.runSQLNative("DELETE FROM BankAccountTransaction");
        DBClient.runSQLNative("DELETE FROM BankAccountTransactionParticulars");
        DBClient.runSQLNative("DELETE FROM CashierDailyBooklet");
        DBClient.runSQLNative("DELETE FROM Expense");
        DBClient.runSQLNative("DELETE FROM ExpenseParticulars");
        DBClient.runSQLNative("DELETE FROM Invoice");
        DBClient.runSQLNative("DELETE FROM InvoiceDetail");
        DBClient.runSQLNative("DELETE FROM ManualGL");
        DBClient.runSQLNative("DELETE FROM ManualGLDetail");
        DBClient.runSQLNative("DROP TABLE Payment");
        DBClient.runSQLNative("DELETE FROM PaymentBudgeting");
        DBClient.runSQLNative("DELETE FROM PaymentCalendar");
        DBClient.runSQLNative("DELETE FROM PaymentCalendarItem");
        DBClient.runSQLNative("DELETE FROM PaymentDetail");
        DBClient.runSQLNative("DELETE FROM PaymentGeneric");
        DBClient.runSQLNative("DELETE FROM PaymentLineItem");
        DBClient.runSQLNative("DELETE FROM PaymentLoan");
        DBClient.runSQLNative("DELETE FROM PaymentPartialDetail");
        DBClient.runSQLNative("DELETE FROM PaymentPlan");
        DBClient.runSQLNative("DELETE FROM PaymentSurchargeHistory");
        DBClient.runSQLNative("DELETE FROM Payroll");
        DBClient.runSQLNative("DELETE FROM PayrollBreakdown");
        DBClient.runSQLNative("DELETE FROM PayrollPeriod");
        DBClient.runSQLNative("DELETE FROM AclUser");
        DBClient.runSQLNative("DELETE FROM AclUserDuty");
        DBClient.runSQLNative("DELETE FROM AclUserModule");
        DBClient.runSQLNative("DELETE FROM AclUserGroup");
        DBClient.runSQLNative("DELETE FROM AppMenu");
        DBClient.runSQLNative("DELETE FROM BookMark");
        DBClient.runSQLNative("DELETE FROM DocumentTable");
        DBClient.runSQLNative("DELETE FROM ImageTable");
        DBClient.runSQLNative("DELETE FROM EmployeeAnnualSummary");
        DBClient.runSQLNative("DELETE FROM EmployeeBenefit");
        DBClient.runSQLNative("DELETE FROM EmployeeDeduction");
        DBClient.runSQLNative("DELETE FROM EmployeeEarnings");
        DBClient.runSQLNative("DELETE FROM EmployeeLeaveApplication");
        DBClient.runSQLNative("DELETE FROM EmployeeLoan");
        DBClient.runSQLNative("DELETE FROM PersonAddress");
        DBClient.runSQLNative("DELETE FROM PersonAttendance");
        DBClient.runSQLNative("DELETE FROM PersonAttribute");
        DBClient.runSQLNative("DELETE FROM PersonDependent");
        DBClient.runSQLNative("DELETE FROM PersonEducation");
        DBClient.runSQLNative("DELETE FROM PersonExperience");
        DBClient.runSQLNative("DELETE FROM PersonInformation");
        DBClient.runSQLNative("DELETE FROM PersonNotes");
        DBClient.runSQLNative("DELETE FROM PersonPhilosophy");
        DBClient.runSQLNative("DELETE FROM PersonPositionHistory");
        DBClient.runSQLNative("DELETE FROM PersonReference");
        DBClient.runSQLNative("DELETE FROM PersonRequirements");
        DBClient.runSQLNative("DELETE FROM PersonSeminarAttended");
        DBClient.runSQLNative("DELETE FROM PersonUsedLeave");
        DBClient.runSQLNative("DELETE FROM TeachingExperience");
    }
    
    public static void main(String[] args) {
        AAAConfig.getInstance();
        cleanCore();
        cleanSchool();
    }

    private static void cleanSchool() {
        DBClient.runSQLNative("DELETE FROM Admission");
        DBClient.runSQLNative("DELETE FROM AdmissionExam");
        DBClient.runSQLNative("DELETE FROM CashDrawer");
        DBClient.runSQLNative("DELETE FROM EnrolledSubjectDetailGrading");
        DBClient.runSQLNative("DELETE FROM Enrollment");
        DBClient.runSQLNative("DELETE FROM StudentSubject");
        DBClient.runSQLNative("DELETE FROM ScheduleAssignment");
        DBClient.runSQLNative("DELETE FROM BookSold");
        DBClient.runSQLNative("DELETE FROM MultiPayment");
        DBClient.runSQLNative("DELETE FROM StudentAcademicHistory");
        DBClient.runSQLNative("DELETE FROM StudentAttendance");
        DBClient.runSQLNative("DELETE FROM StudentChangeCourse");
        DBClient.runSQLNative("DELETE FROM StudentEncounter");
        DBClient.runSQLNative("DELETE FROM StudentForTransfer");
        DBClient.runSQLNative("DELETE FROM StudentGrading");
        DBClient.runSQLNative("DELETE FROM StudentPersonalityExam");
        DBClient.runSQLNative("DELETE FROM StudentSchoolAttended");
        DBClient.runSQLNative("DELETE FROM StudentSubject");
        DBClient.runSQLNative("DELETE FROM StudentSummerSchoolAttended");
        DBClient.runSQLNative("DELETE FROM StudentSummerSchoolSubject");
        DBClient.runSQLNative("DELETE FROM BookSaleRef");
        DBClient.runSQLNative("DELETE FROM Section");
        DBClient.runSQLNative("DELETE FROM SubjectDetailGrading");
    }
}
