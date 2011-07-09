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
			"q1Score1","q1Score2","q1Score3","q1Score4",
			"q1Score5","q1Score6","q1Score7","q1Score8",
			"q1Score9","q1Score10","q1Score11","q1Score12",
			"q1Grade", "gradeShareQ1"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=4, title="Student Grading", editableCol="1,2,3,4,5,6,7,8,9,10,11,12",tableColumnWidth="200")
@Displays({
        @Display(name="studentName"),
        @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
        @Display(name="q1Score1", label="1"),
        @Display(name="q1Score2", label="2"),
        @Display(name="q1Score3", label="3"),
        @Display(name="q1Score4", label="4"),
        @Display(name="q1Score5", label="5"),
        @Display(name="q1Score6", label="6"),
        @Display(name="q1Score7", label="7"),
        @Display(name="q1Score8", label="8"),
        @Display(name="q1Score9", label="9"),
        @Display(name="q1Score10", label="10"),
        @Display(name="q1Score11", label="11"),
        @Display(name="q1Score12", label="12"),
        @Display(name="q1Grade", label="Grade"),
        @Display(name="gradeShareQ1", label="Share"),
        @Display(name="component", type="Label"),
        @Display(name="studentSubjectId", type="Label"),
        @Display(name="subject", type="Label")
})
public class Q1StudentSubjectComponentScoreExt extends StudentSubjectDetailGrading {
}
