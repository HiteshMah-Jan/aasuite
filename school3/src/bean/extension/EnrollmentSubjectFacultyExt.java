/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.person.StudentSubject;
import bean.reference.Subject;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
@UITemplate(
    columnSearch={"student", "subject", "blockOrSection","grade1","grade2","grade3","grade4","status"}, 
    criteriaSearch={"student", "subject", "blockOrSection"}, 
    gridCount=4, title="Enrollment Subjects")
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

        @Display(name="grade1", label="First"),
        @Display(name="grade2", label="Second"),
        @Display(name="grade3", label="Third"),
        @Display(name="grade4", label="Fourth"),
        @Display(name="overAllGrade", label="Overall")
})
@DisplayGroups({
    @DisplayGroup(gridCount=8, title="Grades", fields={"prelimGrade", "midtermGrade", "finalsGrade", "grade", "overAllGrade"}),
    @DisplayGroup(gridCount=6, title="Schedule", fields={"blockOrSection", "building", "room", "day","schedTime","timeEnd"})
})
@ActionButtons({
    @ActionButton(name="btnPutGradeDetail1", label="Grade Detail 1", parentOnly=false),
    @ActionButton(name="btnPutGradeDetail2", label="Grade Detail 2", parentOnly=false),
    @ActionButton(name="btnPutGradeDetail3", label="Grade Detail 3", parentOnly=false),
    @ActionButton(name="btnPutGradeDetail4", label="Grade Detail 4", parentOnly=false),
    @ActionButton(name="btnCalculateGrades", label="Calc. Grades", parentOnly=false)
})
public class EnrollmentSubjectFacultyExt extends StudentSubject {
    public static void main(String[] args) {
        view(EnrollmentSubjectFacultyExt.class);
    }
}
