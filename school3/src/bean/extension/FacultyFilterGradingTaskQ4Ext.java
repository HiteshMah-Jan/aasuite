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
import bean.reference.Section;
import bean.reference.Subject;


@UITemplate(template=template.screen.TemplateTabSinglePageLeftRightNoCriteria.class,
    columnSearch={"schoolYear", "faculty", "section", "subject", "component"},
    criteriaSearch={"schoolYear", "facultyId", "section", "subject"},
    gridCount=4, title="Component Grade Posting", select="SELECT a FROM FacultyGradingTask a WHERE a.facultyId=${personId} AND a.schoolYear='${useYear}'")
@ChildRecords(value={
    @ChildRecord(entity=Q4StudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Q4 Scores", sortable=false),
    @ChildRecord(entity=QAllStudentSubjectComponentScoreExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Student Score Summary")
})
        
@DisplayGroups({
    @DisplayGroup(title = "Item Counts", gridCount = 12, 
    	fields = {
    		"q4ItemCount1","q4ItemCount2","q4ItemCount3","q4ItemCount4","q4ItemCount5","q4ItemCount6",
    		"q4ItemCount7","q4ItemCount8","q4ItemCount9","q4ItemCount10","q4ItemCount11","q4ItemCount12"
    	})
})
@Displays({
    @Display(name="schoolYear",width=60,type="Label"),
    @Display(name="scheduleId",width=60,type="Label",label="Sched #"),
    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
    @Display(name="component",type="Combo",
    		modelCombo={"ASSIGNMENT","ATTENDANCE","ATTITUDE","COMPOSITION/CREATIVE OUTPUT","FORMAL THEME/JOURNAL","GAP","HANDS ON","LABORATORY","LONG TEST","MASTERY 1","MASTERY 2","OPERATIONAL READING","OPERATIONAL MATH","PARTICIPATION","PERFORMANCE","PRACTICAL TEST","PRO MATH","PROJECT","QUARTERLY TEST","QUIZ","QUIZ/SW/READING TEST","RECITATION","SEATWORK","SW/HW/QUIZ","UNIFORM","WORK ETHICS"},width=100, enabled=false),
    @Display(name="weight",width=30, enabled=false),
    @Display(name="usePercentage", enabled=false),
    @Display(name="description",gridFieldWidth=3,width=-1),

    @Display(name="q4ItemCount1", label="1", labelTop=true, leftLabel="1st Qtr", width=30),
    @Display(name="q4ItemCount2", label="2", labelTop=true, width=30),
    @Display(name="q4ItemCount3", label="3", labelTop=true, width=30),
    @Display(name="q4ItemCount4", label="4", labelTop=true, width=30),
    @Display(name="q4ItemCount5", label="5", labelTop=true, width=30),
    @Display(name="q4ItemCount6", label="6", labelTop=true, width=30),
    @Display(name="q4ItemCount7", label="7", labelTop=true, width=30),
    @Display(name="q4ItemCount8", label="8", labelTop=true, width=30),
    @Display(name="q4ItemCount9", label="9", labelTop=true, width=30),
    @Display(name="q4ItemCount10", label="10", labelTop=true, width=30),
    @Display(name="q4ItemCount11", label="11", labelTop=true, width=30),
    @Display(name="q4ItemCount12", label="12", labelTop=true, width=30)
})
@Reports({
    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "1st Qtr.", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "2nd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "3rd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q4_ClassRecord", reportTitle = "4th", reportSql = "${scheduleId}")
})
@ActionButtons({
    @ActionButton(name = "btnSaveAllScore4", label = "Save All Q4")
})
public class FacultyFilterGradingTaskQ4Ext extends FacultyGradingTask {
}
