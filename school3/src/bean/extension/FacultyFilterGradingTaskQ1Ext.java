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
import template.screen.ChildTemplateListPopupDownButton;
import bean.person.FacultyGradingTask;
import bean.reference.GradingComponent;
import bean.reference.Section;
import bean.reference.Subject;


@UITemplate(template=template.screen.TemplateTabSinglePageLeftRightNoCriteria.class,
    columnSearch={"section", "subject", "component"},
    criteriaSearch={"component"},
    gridCount=4, title="Component Grade Posting", 
    select="SELECT a FROM FacultyGradingTask a WHERE a.facultyId=${personId} AND a.schoolYear='${useYear}'",
    orderBy="a.section, a.subject, a.component")
@ChildRecords(value={
    @ChildRecord(entity=Q1StudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Student Score Q1", sortable=false),
    @ChildRecord(entity=QAllStudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Student Score Summary")
})
        
@DisplayGroups({
    @DisplayGroup(title = "Item Counts - 1st Qtr.", gridCount = 12, 
    	fields = {
    		"q1ItemCount1","q1ItemCount2","q1ItemCount3","q1ItemCount4","q1ItemCount5","q1ItemCount6",
    		"q1ItemCount7","q1ItemCount8","q1ItemCount9","q1ItemCount10","q1ItemCount11","q1ItemCount12"
    	})
})
@Displays({
    @Display(name="schoolYear",width=60,type="Label"),
    @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
    @Display(name="component", type="PopSearch", linktoBean=GradingComponent.class, enabled=false),
    @Display(name="weight",width=30, enabled=false),
    @Display(name="usePercentage"),
    @Display(name="description",gridFieldWidth=3,width=-1),

    @Display(name="q1ItemCount1", label="1", labelTop=true, width=30),
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
    @Display(name="q1ItemCount12", label="12", labelTop=true, width=30)
})
@Reports({
    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "1st Qtr. Class Record", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "2nd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "3rd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q4_ClassRecord", reportTitle = "4th", reportSql = "${scheduleId}")
})
@ActionButtons({
//    @ActionButton(name = "btnDeleteComponent", label = "Delete Component"),
    @ActionButton(name = "btnGenerateTask", label = "Generate Student Component Grade/Score"),
    @ActionButton(name = "btnSaveAllScore1", label = "Save All Scores"),
    @ActionButton(name = "btnRemoveBoys", label = "Remove All Boys"),
    @ActionButton(name = "btnRemoveGirls", label = "Remove All Girls")
})
public class FacultyFilterGradingTaskQ1Ext extends FacultyGradingTask {
	public static void main(String[] args) {
		view(FacultyFilterGradingTaskQ1Ext.class);
	}
}
