/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import java.io.Serializable;

import springbean.GradingProcess;
import springbean.StudentSubjectToEnrollmentGrade;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DBClient;
import bean.Enrollment;
import bean.Student;
import bean.reference.GradeLevel;
import bean.reference.LockGrading;
import bean.reference.Subject;

/**
 * 
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateSinglePage.class, gridCount = 8, title = "Subjects", autoResizeTable = false, columnSearch = {
		"schoolYear", "gradeLevel", "subject", "studentName", "unit", "grade1",
		"grade2", "grade3", "grade4", "finalRating", "passed" }, criteriaSearch = {
		"schoolYear", "gradeLevel", "section", "studentId", "subject",
		"grade1", "grade2","grade3","grade4" }
// , sumFooter="4,5,6,7,8,9,10"
)
@Displays( {
		@Display(name = "schoolYear", width = 100, gridFieldWidth = 3),
		@Display(name = "gradeLevel", width = 100, gridFieldWidth = 3, linktoBean = GradeLevel.class, type = "PopSearch"),
		@Display(name = "studentId", type = "PopSearch", linktoBean = Student.class, gridFieldWidth = 7, width = -1),
		@Display(name = "subject", type = "PopSearch", linktoBean = Subject.class, gridFieldWidth = 7, width = -1),
		@Display(name = "grade1", label = "1st"),
		@Display(name = "grade2", label = "2nd"),
		@Display(name = "grade3", label = "3rd"),
		@Display(name = "grade4", label = "4th"),
		@Display(name = "seq", type = "Label"),
		@Display(name = "finalRating", label = "Final Rating") })
@ActionButtons( { @ActionButton(name = "btnPassQ1", label = "Delete By AAA") })
public class StudentSubject2Ext extends bean.person.StudentSubject implements
		Serializable {
	public static void main(String[] args) {
		// view(StudentSubjectExt.class);
	}
}
