/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Person;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

@UITemplate(orderBy="a.seq DESC", title="Attendance", showChild=false, showForm=false, template = TemplateSinglePage.class, gridCount = 2,
		criteriaSearch = {"attendanceDate","personId"},
		columnSearch = {"attendanceDate","login","logout","late","personName"})
@Displays({
        @Display(name="personId",label="Person", type = "PopSearch", linktoBean=Person.class, linktoColumns={"lastName","firstName","personType"}),
        @Display(name="attendanceDate"),
        @Display(name="login"),
        @Display(name="logout"),
        @Display(name="late")
})
@ActionButtons({
    @ActionButton(name="btnScanner", label="Use Scanner"),
    @ActionButton(name="btnUploadFile", label="Upload File")
})
/**
 *
 * @author Entokwaa
 */
public class AttendanceExt extends bean.accounting.payroll.PersonAttendance {
    public static void main(String[] args) {
        view(AttendanceExt.class);
    }    
}
