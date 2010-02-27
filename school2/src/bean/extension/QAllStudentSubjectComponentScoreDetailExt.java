package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.Employee;
import bean.Student;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.Subject;

@UITemplate(template=TemplateTabPage.class,
		columnSearch={
			"studentName","schoolYear","facultyName","subject","component","gradeShare","computedGrade"
		}, 
		criteriaSearch={"schoolYear","facultyName","subject","component","studentName"}, 
		gridCount=8, title="Student Grading", editableCol="2",tableColumnWidth="300")
@Displays({
    @Display(name="schoolYear", type="Label"),
    @Display(name="facultyId", type="PopSearch", linktoBean=Employee.class),
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class),
    @Display(name="component", type="Label"),
    @Display(name="studentId", type="PopSearch", linktoBean=Student.class),
    @Display(name="computedGrade", label="Grade", type="Label")
})
public class QAllStudentSubjectComponentScoreDetailExt extends StudentSubjectDetailGrading {

}
