package bean.extension;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePage;
import bean.Employee;
import bean.person.FacultyGradingTask;
import bean.reference.GradingComponent;
import bean.reference.Section;
import bean.reference.Subject;

@UITemplate(template=TemplateTabSinglePage.class,
	    columnSearch={"schoolYear", "faculty", "section", "subject", "component","weight"},
	    criteriaSearch={"schoolYear", "facultyId", "section", "subject", "component"},
	    gridCount=6, title="Component Grade Posting")
	@ChildRecords(value={
	    @ChildRecord(entity=QAllStudentSubjectComponentScoreDetailExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId=${seq} ORDER BY b.gender DESC, a.studentName", title="Per Student"),
	    @ChildRecord(entity=Q1StudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Score Prelim", sortable=false),
	    @ChildRecord(entity=Q2StudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Mid Term", sortable=false),
	    @ChildRecord(entity=Q3StudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Finals", sortable=false)
	})
	        
	@DisplayGroups({
	    @DisplayGroup(title = "Item Counts", gridCount = 24, 
	    	fields = {
	    		"q1ItemCount1","q1ItemCount2","q1ItemCount3","q1ItemCount4","q1ItemCount5","q1ItemCount6",
	    		"q1ItemCount7","q1ItemCount8","q1ItemCount9","q1ItemCount10","q1ItemCount11","q1ItemCount12",

	    		"q2ItemCount1","q2ItemCount2","q2ItemCount3","q2ItemCount4","q2ItemCount5","q2ItemCount6",
	    		"q2ItemCount7","q2ItemCount8","q2ItemCount9","q2ItemCount10","q2ItemCount11","q2ItemCount12",

	    		"q3ItemCount1","q3ItemCount2","q3ItemCount3","q3ItemCount4","q3ItemCount5","q3ItemCount6",
	    		"q3ItemCount7","q3ItemCount8","q3ItemCount9","q3ItemCount10","q3ItemCount11","q3ItemCount12"
	    	})
	})
	@Displays({
	    @Display(name="schoolYear",width=60,type="Label"),
	    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
	    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
	    @Display(name="component", type="PopSearch", linktoBean=GradingComponent.class,width=100, enabled=false),
	    @Display(name="weight",width=30, enabled=false),
	    @Display(name="facultyId", type="PopSearch", linktoBean=Employee.class, enabled=false),
	    @Display(name="description",gridFieldWidth=5,width=-1),

	    @Display(name="q1ItemCount1", label="1", labelTop=true, leftLabel="Prelim", width=30),
	    @Display(name="q1ItemCount2", label="2", labelTop=true, width=30),
	    @Display(name="q1ItemCount3", label="3", labelTop=true, width=30),
	    @Display(name="q1ItemCount4", label="4", labelTop=true, width=30),
	    @Display(name="q1ItemCount5", label="5", labelTop=true, width=30),
	    @Display(name="q1ItemCount6", label="6", labelTop=true, width=30),
	    @Display(name="q1ItemCount7", label="7", labelTop=true, width=30),
	    @Display(name="q1ItemCount8", label="8", labelTop=true, width=30),
	    @Display(name="q1ItemCount9", label="9", labelTop=true, width=30),
	    @Display(name="q1ItemCount10", label="10", labelTop=true, width=30),
	    @Display(name="q1ItemCount11", label="11", labelTop=true, width=30),
	    @Display(name="q1ItemCount12", label="12", labelTop=true, width=30),

	    @Display(name="q2ItemCount1", label="Mid Term", width=30),
	    @Display(name="q2ItemCount2", label="2", hideLabel=true, width=30),
	    @Display(name="q2ItemCount3", label="3", hideLabel=true, width=30),
	    @Display(name="q2ItemCount4", label="4", hideLabel=true, width=30),
	    @Display(name="q2ItemCount5", label="5", hideLabel=true, width=30),
	    @Display(name="q2ItemCount6", label="6", hideLabel=true, width=30),
	    @Display(name="q2ItemCount7", label="7", hideLabel=true, width=30),
	    @Display(name="q2ItemCount8", label="8", hideLabel=true, width=30),
	    @Display(name="q2ItemCount9", label="9", hideLabel=true, width=30),
	    @Display(name="q2ItemCount10", label="10", hideLabel=true, width=30),
	    @Display(name="q2ItemCount11", label="11", hideLabel=true, width=30),
	    @Display(name="q2ItemCount12", label="12", hideLabel=true, width=30),
	    
	    @Display(name="q3ItemCount1", label="Finals", width=30),
	    @Display(name="q3ItemCount2", label="2", hideLabel=true, width=30),
	    @Display(name="q3ItemCount3", label="3", hideLabel=true, width=30),
	    @Display(name="q3ItemCount4", label="4", hideLabel=true, width=30),
	    @Display(name="q3ItemCount5", label="5", hideLabel=true, width=30),
	    @Display(name="q3ItemCount6", label="6", hideLabel=true, width=30),
	    @Display(name="q3ItemCount7", label="7", hideLabel=true, width=30),
	    @Display(name="q3ItemCount8", label="8", hideLabel=true, width=30),
	    @Display(name="q3ItemCount9", label="9", hideLabel=true, width=30),
	    @Display(name="q3ItemCount10", label="10", hideLabel=true, width=30),
	    @Display(name="q3ItemCount11", label="11", hideLabel=true, width=30),
	    @Display(name="q3ItemCount12", label="12", hideLabel=true, width=30)
	})
	@Reports({
	    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "Class Record Prelim", reportSql = "${scheduleId}"),
	    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "Mid Term", reportSql = "${scheduleId}"),
	    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "Finals", reportSql = "${scheduleId}"),
	    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summary Prelim", reportSql = "${section}"),
	    @template.Report(reportFile = "Q2_Grades", reportTitle = "Mid Term", reportSql = "${section}"),
	    @template.Report(reportFile = "Q3_Grades", reportTitle = "Finals", reportSql = "${section}"),
	    @template.Report(reportFile = "QAll_Grades", reportTitle = "Summary Final", reportSql = "${section}"),
	    @template.Report(reportFile = "CollegeClassCard", reportTitle = "Class Card", reportSql = "${section}"),
		@template.Report(reportFile = "AdminReportCollegeClassCard", reportTitle = "Admin Class Card", reportSql = "")
	})
	@ActionButtons({
	    @ActionButton(name = "btnGenerateAllComponents", label = "Generate All Components"),
	    @ActionButton(name = "btnSaveAllScore1", label = "Save All Prelim"),
	    @ActionButton(name = "btnSaveAllScore2", label = "Mid Term"),
	    @ActionButton(name = "btnSaveAllScore3", label = "Finals")
	})
public class FacultyGradingTaskCollegeExt extends FacultyGradingTask {
	public static void main(String[] args) {
		view(FacultyGradingTaskCollegeExt.class);
	}
}
