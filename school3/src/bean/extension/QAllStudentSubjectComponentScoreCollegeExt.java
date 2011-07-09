package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.person.StudentSubjectDetailGrading;

@UITemplate(template=TemplateTabPage.class,
		columnSearch={
			"studentName","q4Grade","gradeShareQ4"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=8, title="Student Grading", editableCol="2",tableColumnWidth="300")
@Displays({
        @Display(name="studentName", type="Label", gridFieldWidth=5),
        @Display(name="q4Grade", label="Grade", type="Label"),
        @Display(name="gradeShareQ4", label="Share", type="Label")
})
public class QAllStudentSubjectComponentScoreCollegeExt extends StudentSubjectDetailGrading {

}
