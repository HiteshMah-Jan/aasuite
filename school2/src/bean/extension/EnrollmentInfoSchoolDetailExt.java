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
    @Display(name = "cummTotalDaysInGrade",label="Cumm Total Days In Grade",width=-1),
    @Display(name = "cummTotalYearsInSchool",width=-1,label="Cumm Total Yrs. In School "),
    @Display(name = "currentlyEnrolled", type = "Combo", width=-1,label="Currently Enrolled"),
    @Display(name = "dateEnrolled", label="Date Enrolled,",width=-1)
})
public class EnrollmentInfoSchoolDetailExt extends Enrollment {
    public static void main(String[] args) {
        view(EnrollmentInfoSchoolDetailExt.class);
    }
}
