package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.Student;
import bean.person.StudentSubjectDetailGrading;

@UITemplate(template=TemplateTabPage.class,
		columnSearch={
			"component","weight",
			"q1Grade","q2Grade","q3Grade","q4Grade","computedGrade",
			"gradeShareQ1","gradeShareQ2","gradeShareQ3","gradeShareQ4",
			"gradeShare"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=8, title="Student Grading", editableCol="2",
		tableColumnWidth="100", sumFooter="1,7,8,9,10,11")
@Displays({
    @Display(name="component", type="Label", gridFieldWidth=5),
    @Display(name="q1Grade", label="1st Qtr. Grade", type="Label"),
    @Display(name="q2Grade", label="2nd", type="Label"),
    @Display(name="q3Grade", label="3rd", type="Label"),
    @Display(name="q4Grade", label="4th", type="Label"),
    @Display(name="computedGrade", label="All", type="Label"),
    @Display(name="gradeShareQ1", label="1st Qtr. Grade Share", type="Label"),
    @Display(name="gradeShareQ2", label="2nd", type="Label"),
    @Display(name="gradeShareQ3", label="3rd", type="Label"),
    @Display(name="gradeShareQ4", label="4th", type="Label"),
    @Display(name="gradeShare", label="All", type="Label")
})
public class StudentSubjectDetailGradingExt extends StudentSubjectDetailGrading {

}
