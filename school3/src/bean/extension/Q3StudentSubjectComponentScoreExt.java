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
			"q3Score1","q3Score2","q3Score3","q3Score4",
			"q3Score5","q3Score6","q3Score7","q3Score8",
			"q3Score9","q3Score10","q3Score11","q3Score12",
			"q3Grade", "gradeShareQ3"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=4, title="Student Grading", editableCol="1,2,3,4,5,6,7,8,9,10,11,12",tableColumnWidth="200")
@Displays({
        @Display(name="studentName"),
        @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
        @Display(name="q3Score1", label="1"),
        @Display(name="q3Score2", label="2"),
        @Display(name="q3Score3", label="3"),
        @Display(name="q3Score4", label="4"),
        @Display(name="q3Score5", label="5"),
        @Display(name="q3Score6", label="6"),
        @Display(name="q3Score7", label="7"),
        @Display(name="q3Score8", label="8"),
        @Display(name="q3Score9", label="9"),
        @Display(name="q3Score10", label="10"),
        @Display(name="q3Score11", label="11"),
        @Display(name="q3Score12", label="12"),
        @Display(name="q3Grade", label="Grade"),
        @Display(name="gradeShareQ3", label="Share"),
        @Display(name="component", type="Label"),
        @Display(name="studentSubjectId", type="Label"),
        @Display(name="subject", type="Label")
})
public class Q3StudentSubjectComponentScoreExt extends StudentSubjectDetailGrading {

}
