/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Enrollment;
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
    columnSearch={"student","section"}, 
    criteriaSearch={"studentId","section"}, 
    gridCount=4, title="Enrollment")
@ChildRecords({
    @ChildRecord(entity=StudentSubject.class, sql="SELECT a FROM StudentSubject a WHERE a.enrollmentId=${enrollmentId}", title="Subjects"),
    @ChildRecord(entity=PaymentEnrollment.class, sql="SELECT a FROM PaymentEnrollment a WHERE a.recordId=${enrollmentId}", title="Payments")
})
@Displays({
        @Display(name="seq", addInfoOnly=true),
        @Display(name="studentId", label="Student", gridFieldWidth=3, width=-1, type = "PopSearch", linktoBean=bean.Student.class),
        @Display(name="schoolYear"),
        @Display(name="section", type="PopSearch", linktoBean=Section.class),
        @Display(name="paymentMode", type="Combo", modelCombo={"CASH", "INSTALLMENT"}),
        @Display(name="miscellaneousFee"),
        @Display(name="tuitionFee"),
        @Display(name="adjustment"),
        @Display(name="discount"),

        @Display(name="payCount", type="NumberCombo", endCount=10),
        @Display(name="dayOfMonth", type="NumberCombo", endCount=31),
        @Display(name="overAllAmount", label="Overall Total", width=-1),
        @Display(name="installmentAmount", width=-1, label="Cash/Installment Amount"),

        @Display(name="semester", addInfoOnly=true),
        @Display(name="miscellaneousFee2", addInfoOnly=true),
        @Display(name="miscellaneousFee3", addInfoOnly=true),
        @Display(name="miscellaneousFee4", addInfoOnly=true),
        @Display(name="total", addInfoOnly=true),
        @Display(name="cash", addInfoOnly=true),
        @Display(name="dueEnrollment", addInfoOnly=true),
        @Display(name="duePrelims", addInfoOnly=true),
        @Display(name="totalUnit", addInfoOnly=true),
        @Display(name="dueMidterm", addInfoOnly=true),
        @Display(name="dueFinals", addInfoOnly=true),
        @Display(name="amountPerUnit", addInfoOnly=true),
        @Display(name="due", addInfoOnly=true),
        @Display(name="installment", addInfoOnly=true),
        @Display(name="isActive", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(title="Payment Scheme", fields={"payCount", "dayOfMonth", "overAllAmount", "installmentAmount"})
})
@ActionButtons({
    @ActionButton(name="btnCreatePayments", label="Create Payment/s")
})
@Reports({
    @template.Report(reportFile="EnrolledStudentbySection", reportTitle="Enrollment Report", reportSql="")
})
public class EnrollmentOnlineExt extends Enrollment implements Serializable {
}
