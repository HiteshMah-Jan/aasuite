/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.person.StudentSubject;
import bean.reference.Subject;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
@UITemplate(columnSearch={"subject", "blockOrSection","grade1","grade2","grade3","grade4"},gridCount=4, title="Student Class Performance")
@Displays({
        @Display(name="subject", type="PopSearch", linktoBean=Subject.class),
        @Display(name="status", type="Combo", modelCombo={"PASSED","FAILED"}),
        @Display(name="facultyId", type="Label"),
        @Display(name="units", type="Label"),

        @Display(name="blockOrSection", type="Label"),
        @Display(name="building", type="Label"),
        @Display(name="room", type="Label"),
        @Display(name="day", type="Label"),
        @Display(name="schedTime", type="Label"),
        @Display(name="timeEnd", type="Label"),

        @Display(name="grade1"),
        @Display(name="grade2"),
        @Display(name="grade3"),
        @Display(name="grade4")
})
@DisplayGroups({
    @DisplayGroup(gridCount=8, title="Grades", fields={"prelimGrade", "midtermGrade", "finalsGrade", "grade"}),
    @DisplayGroup(gridCount=6, title="Schedule", fields={"blockOrSection", "building", "room", "day","schedTime","timeEnd"})
})
public class EnrollmentSubjectFacultyExtOsa extends StudentSubject {
    public static void main(String[] args) {
        view(EnrollmentSubjectFacultyExtOsa.class);
    }
}
