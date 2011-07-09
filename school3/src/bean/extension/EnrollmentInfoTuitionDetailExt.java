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
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template = TemplateSinglePage.class, gridCount = 2, columnSearch = {"studentId"})
@Displays({
    @Display(name = "seq", addInfoOnly = true),
    @Display(name = "typeOfTuitionPayment", label = "Payment Type", width=constants.Constants.DEF_WIDTH, type = "Combo", modelCombo = {"CASH, INSTALLMENT"}),
    @Display(name = "tuitionFee", label = "Tuition Fee",width=-1),
    @Display(name = "miscellaneousFee", label = "Misc Fee",width=-1)
})
public class EnrollmentInfoTuitionDetailExt extends Enrollment {

    public static void main(String[] args) {
        view(EnrollmentInfoTuitionDetailExt.class);
    }
}
