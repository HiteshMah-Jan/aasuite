package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.person.StudentSubjectDetailGrading;

@UITemplate(template=TemplateTabPage.class,
		columnSearch={"component","weight","computedGrade","gradeShare"}, 
		criteriaSearch={"studentName"}, 
		gridCount=4, title="Student Grading", editableCol="2",
		tableColumnWidth="100", sumFooter="2,3")
@Displays({
    @Display(name="component", type="Label", gridFieldWidth=3),
    @Display(name="computedGrade", label="All", type="Label"),
    @Display(name="gradeShare", label="All", type="Label")
})
public class StudentSubjectDetailGradingCollegeExt extends StudentSubjectDetailGrading {

}
