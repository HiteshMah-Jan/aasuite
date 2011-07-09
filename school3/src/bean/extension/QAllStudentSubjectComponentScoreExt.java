package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.Student;
import bean.person.StudentSubjectDetailGrading;

@UITemplate(template=TemplateTabPage.class,
		columnSearch={
			"studentName",
			"q1Grade","gradeShareQ1","q2Grade","gradeShareQ2","q3Grade","gradeShareQ3","q4Grade","gradeShareQ4",
			"gradeShare","computedGrade"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=8, title="Student Grading", editableCol="2",tableColumnWidth="300")
@Displays({
        @Display(name="studentName", type="Label", gridFieldWidth=5),
        @Display(name="computedGrade", label="Grade", type="Label"),
        @Display(name="q1Grade", label="1st Qtr. Grade", type="Label"),
        @Display(name="gradeShareQ1", label="Share", type="Label"),
        @Display(name="q2Grade", label="2nd Qtr. Grade", type="Label"),
        @Display(name="gradeShareQ2", label="Share", type="Label"),
        @Display(name="q3Grade", label="3rd Qtr. Grade", type="Label"),
        @Display(name="gradeShareQ3", label="Share", type="Label"),
        @Display(name="q4Grade", label="4th Qtr. Grade", type="Label"),
        @Display(name="gradeShareQ4", label="Share", type="Label")
})
public class QAllStudentSubjectComponentScoreExt extends StudentSubjectDetailGrading {

}
