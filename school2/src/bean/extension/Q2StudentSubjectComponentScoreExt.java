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
			"q2Score1","q2Score2","q2Score3","q2Score4",
			"q2Score5","q2Score6","q2Score7","q2Score8",
			"q2Score9","q2Score10","q2Score11","q2Score12",
			"q2Grade", "gradeShareQ2"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=4, title="Student Grading", editableCol="1,2,3,4,5,6,7,8,9,10,11,12",tableColumnWidth="200")
@Displays({
        @Display(name="studentName"),
        @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
        @Display(name="q2Score1", label="1"),
        @Display(name="q2Score2", label="2"),
        @Display(name="q2Score3", label="3"),
        @Display(name="q2Score4", label="4"),
        @Display(name="q2Score5", label="5"),
        @Display(name="q2Score6", label="6"),
        @Display(name="q2Score7", label="7"),
        @Display(name="q2Score8", label="8"),
        @Display(name="q2Score9", label="9"),
        @Display(name="q2Score10", label="10"),
        @Display(name="q2Score11", label="11"),
        @Display(name="q2Score12", label="12"),
        @Display(name="q2Grade", label="Grade"),
        @Display(name="gradeShareQ2", label="Share"),
        @Display(name="component", type="Label"),
        @Display(name="studentSubjectId", type="Label"),
        @Display(name="subject", type="Label")
})
public class Q2StudentSubjectComponentScoreExt extends StudentSubjectDetailGrading {

}
