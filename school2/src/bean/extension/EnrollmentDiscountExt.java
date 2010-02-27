/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Admission;
import bean.Enrollment;
import bean.Student;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template=template.screen.TemplateNoForm.class,
        criteriaSearch={"schoolYear","student","requestedDiscountStatus"},
        columnSearch={"schoolYear","requestedDiscountDate","student","requestedDiscountType","requestedDiscountAmount","requestedDiscountBy","requestedDiscountApprovedBy","requestedDiscountStatus"},
        gridCount=6)
@Displays({
        @Display(name="schoolYear"),
        @Display(name="studentId", label="Student", type="PopSearch", linktoBean= Student.class),
        @Display(name="requestedDiscountDate", label="Date"),
        @Display(name="requestedDiscountType", type = "Combo", modelCombo={"TUITION FEE","SURCHARGE"}, label="Type"),
        @Display(name="requestedDiscountAmount", label="Amount"),
        @Display(name="requestedDiscountStatus", label="Status"),
        @Display(name="requestedDiscountBy", label= "Cashier"),
        @Display(name="requestedDiscountApprovedBy", label = "Approved By"),
        @Display(name="requestedDiscountApprovedDate")
})

public class EnrollmentDiscountExt extends Enrollment {
}
