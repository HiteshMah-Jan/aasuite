/*
 * Enrollmentassessment.java
 *
 * Created on Nov 27, 2007, 6:57:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Enrollment;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"studentId"})
@Displays({
    @Display(name = "seq", addInfoOnly = true),
//    @Display(name = "cummTotalDaysInGrade",mergeFields="cummTotalYearsInSchool",label=" Cumm Total Days In Grade",gridFieldWidth=1,width=100),
//    @Display(name = "cummTotalYearsInSchool",width=99,label="Cumm Total Yrs. In School "),
//    @Display(name = "currentlyEnrolled", type = "Combo", width =100,mergeFields="dateEnrolled",label="              Currently Enrolled",gridFieldWidth=1),
//    @Display(name = "dateEnrolled", width = 100,label="                   Date Enrolled"),
//    @Display(name = "typeOfTuitionPayment", label = "Payment Type", width =300, type = "Combo", modelCombo = {"CASH, INSTALLMENT"},gridFieldWidth=3),
//    @Display(name = "tuitionFee", label = "Tuition Fee",width=105),
//    @Display(name = "miscellaneousFee", label = "Misc Fee",width=105),
    @Display(name = "status", width =120, type = "Combo", modelCombo = {"PROBATIONARY-AC"},mergeFields="schoolYearPlan",label="           Status"),
    @Display(name = "schoolYearPlan", width = 105,label=" Sch Yr. Plan"),
//    @Display(name = "imagePath", gridFieldWidth = 3, width = -1),
    @Display(name = "siblings1", label = "                 1 ", width = 300,gridFieldWidth=3),
    @Display(name = "siblings2", label = "                 2 ", width = -1,gridFieldWidth=3),
    @Display(name = "siblings3", label = "                 3 ", width = -1,gridFieldWidth=3),
    @Display(name = "siblings4", label = "                 4 ", width = -1,gridFieldWidth=3),
    @Display(name = "siblings5", label = "                 5 ", width = -1,gridFieldWidth=3),
    @Display(name = "unsettledTuitionFee", type = "Combo", width = 100,label="      Unsettled Tuition Fee"),
    @Display(name = "recommendation", type = "Combo", width = 100),
    @Display(name = "remedialClass", type = "Combo", width = 100,label="      Remedial Class"),
    @Display(name = "aTTransferSchool", label = "AT Transfer School", type = "Combo", width = 100),
    @Display(name = "busService", type = "Combo", width = 100,label="      Bus Service"),
    @Display(name = "aTSummerAcademic", label = "AT Summer Academic", type = "Combo", width = 100),
    @Display(name = "transcript", type = "Combo", width = 100,label="      Transcript"),
    @Display(name = "aTSMFailedEnglish", label = "ATSM Failed English", type = "Combo", width = 100),
    @Display(name = "fIS", type = "Combo", width = 100,label="      F.I.S."),
    @Display(name = "aTSMFailedScience", label = "ATSM Failed Science", type = "Combo", width = 100),
    @Display(name = "idPicture", type = "Combo", width = 100,label="      I.D Picture"),
    @Display(name = "aTSMFailedMath", label = "ATSM Failed Math", type = "Combo", width = 100),
    @Display(name = "birthCertificate", type = "Combo", width = 100,label="      Birth Certificate"),
    @Display(name = "aTSM7577English", label = "ATSM 7577 English", type = "Combo", width = 100),
    @Display(name = "baptismal", type = "Combo", width = 100,label="      Baptismal"),
    @Display(name = "aTSM7577Science", label = "ATSM 7577 Science", type = "Combo", width = 100),
    @Display(name = "reportCard", type = "Combo", width = 100,label="      Report Card"),
    @Display(name = "aTSM7577Math", label = "ATSM 7577 Math", type = "Combo", width = 100),
    @Display(name = "aCR", type = "Combo", width = 100),
    @Display(name = "passportVerification", type = "Combo", width = 100,label="Passport Ver."),
    @Display(name = "scholasticRecord", type = "Combo",width=100)
})
@DisplayGroups({
//    @DisplayGroup(title = "School Details", gridCount = 2, fields = {"cummTotalDaysInGrade", "cummTotalYearsInSchool", "currentlyEnrolled", "dateEnrolled"}),
//    @DisplayGroup(title = "Tuition Fee Details", gridCount = 4, fields = {"tuitionFee", "typeOfTuitionPayment", "miscellaneousFee"}),
    @DisplayGroup(title = "Student Status", gridCount = 4, fields = {"status", "schoolYearPlan"}),
    @DisplayGroup(title = "Siblings", gridCount = 4, fields = {"siblings1", "siblings2", "siblings3", "siblings4", "siblings5"}),
    @DisplayGroup(title = "Unsettled Items", gridCount = 4, fields = {"unsettledTuitionFee", "recommendation", "remedialClass", "aTTransferSchool", "busService", "aTSummerAcademic", "transcript", "aTSMFailedEnglish", "fIS", "aTSMFailedScience", "idPicture", "aTSMFailedMath", "birthCertificate", "aTSM7577English", "baptismal", "aTSM7577Science", "reportCard", "aTSM7577Math"}),
    @DisplayGroup(title = "For Foreign Students", gridCount = 6, fields = {"aCR", "passportVerification", "scholasticRecord"})
})
public class EnrollmentInfoExt extends Enrollment {

    public static void main(String[] args) {
        view(EnrollmentInfoExt.class);
    }
}
