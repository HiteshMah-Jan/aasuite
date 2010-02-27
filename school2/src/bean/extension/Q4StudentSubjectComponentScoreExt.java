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
			"q4Score1","q4Score2","q4Score3","q4Score4",
			"q4Score5","q4Score6","q4Score7","q4Score8",
			"q4Score9","q4Score10","q4Score11","q4Score12",
			"q4Grade", "gradeShareQ4"
		}, 
		criteriaSearch={"studentName"}, 
		gridCount=4, title="Student Grading", editableCol="1,2,3,4,5,6,7,8,9,10,11,12",tableColumnWidth="200")
@Displays({
        @Display(name="studentName"),
        @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
        @Display(name="q4Score1", label="1"),
        @Display(name="q4Score2", label="2"),
        @Display(name="q4Score3", label="3"),
        @Display(name="q4Score4", label="4"),
        @Display(name="q4Score5", label="5"),
        @Display(name="q4Score6", label="6"),
        @Display(name="q4Score7", label="7"),
        @Display(name="q4Score8", label="8"),
        @Display(name="q4Score9", label="9"),
        @Display(name="q4Score10", label="10"),
        @Display(name="q4Score11", label="11"),
        @Display(name="q4Score12", label="12"),
        @Display(name="q4Grade", label="Grade"),
        @Display(name="gradeShareQ4", label="Share"),
        @Display(name="component", type="Label"),
        @Display(name="studentSubjectId", type="Label"),
        @Display(name="subject", type="Label")
})
public class Q4StudentSubjectComponentScoreExt extends StudentSubjectDetailGrading {

}
