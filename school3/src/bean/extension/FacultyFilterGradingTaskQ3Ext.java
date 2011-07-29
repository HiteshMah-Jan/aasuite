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
import bean.person.FacultyGradingTask;
import bean.reference.GradingComponent;
import bean.reference.Section;
import bean.reference.Subject;


@UITemplate(template=template.screen.TemplateTabSinglePageLeftRightNoCriteria.class,
    columnSearch={"schoolYear", "faculty", "section", "subject", "component"},
    criteriaSearch={"schoolYear", "facultyId", "section", "subject"},
    gridCount=4, title="Component Grade Posting", select="SELECT a FROM FacultyGradingTask a WHERE a.facultyId=${personId} AND a.schoolYear='${useYear}'")
@ChildRecords(value={
    @ChildRecord(entity=Q3StudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Q3 Scores", sortable=false),
    @ChildRecord(entity=QAllStudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Student Score Summary")
})
        
@DisplayGroups({
    @DisplayGroup(title = "Item Counts", gridCount = 12, 
    	fields = {
    		"q3ItemCount1","q3ItemCount2","q3ItemCount3","q3ItemCount4","q3ItemCount5","q3ItemCount6",
    		"q3ItemCount7","q3ItemCount8","q3ItemCount9","q3ItemCount10","q3ItemCount11","q3ItemCount12"
    	})
})
@Displays({
    @Display(name="schoolYear",width=60,type="Label"),
    @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
    @Display(name="component", type="PopSearch", linktoBean=GradingComponent.class,width=100, enabled=false),
    @Display(name="weight",width=30, enabled=false),
    @Display(name="usePercentage", enabled=false),
    @Display(name="description",gridFieldWidth=3,width=-1),

    @Display(name="q3ItemCount1", label="1", labelTop=true, leftLabel="3rd Qtr", width=30),
    @Display(name="q3ItemCount2", label="2", labelTop=true, width=30),
    @Display(name="q3ItemCount3", label="3", labelTop=true, width=30),
    @Display(name="q3ItemCount4", label="4", labelTop=true, width=30),
    @Display(name="q3ItemCount5", label="5", labelTop=true, width=30),
    @Display(name="q3ItemCount6", label="6", labelTop=true, width=30),
    @Display(name="q3ItemCount7", label="7", labelTop=true, width=30),
    @Display(name="q3ItemCount8", label="8", labelTop=true, width=30),
    @Display(name="q3ItemCount9", label="9", labelTop=true, width=30),
    @Display(name="q3ItemCount10", label="10", labelTop=true, width=30),
    @Display(name="q3ItemCount11", label="11", labelTop=true, width=30),
    @Display(name="q3ItemCount12", label="12", labelTop=true, width=30)
})
@Reports({
    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "1st Qtr.", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "2nd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "3rd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q4_ClassRecord", reportTitle = "4th", reportSql = "${scheduleId}")
})
@ActionButtons({
    @ActionButton(name = "btnSaveAllScore3", label = "Save All Q3")
})
public class FacultyFilterGradingTaskQ3Ext extends FacultyGradingTask {
}
