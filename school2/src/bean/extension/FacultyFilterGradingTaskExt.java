package bean.extension;

import bean.EmployeeFaculty;
import bean.Schedule;
import bean.admin.AppConfig;
import bean.extension.Q1StudentSubjectComponentScoreExt;
import bean.extension.Q2StudentSubjectComponentScoreExt;
import bean.extension.Q3StudentSubjectComponentScoreExt;
import bean.extension.Q4StudentSubjectComponentScoreExt;
import bean.extension.QAllStudentSubjectComponentScoreExt;
import bean.person.FacultyGradingTask;
import bean.reference.Section;
import bean.reference.Subject;

import java.io.Serializable;
import java.util.Date;
import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import javax.persistence.*;

import constants.UserInfo;


@UITemplate(template=TemplateTabPage.class,
    columnSearch={"schoolYear", "faculty", "section", "subject", "component"},
    criteriaSearch={"schoolYear", "facultyId", "section", "subject"},
    gridCount=12, title="Component Grade Posting", select="SELECT a FROM FacultyGradingTask a WHERE a.facultyId=${personId}")
@ChildRecords(value={
    @ChildRecord(entity=Q1StudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId='${seq}' ORDER BY a.studentName", title="Student Score Q1"),
    @ChildRecord(entity=Q2StudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId='${seq}' ORDER BY a.studentName", title="Q2"),
    @ChildRecord(entity=Q3StudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId='${seq}' ORDER BY a.studentName", title="Q3"),
    @ChildRecord(entity=Q4StudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId='${seq}' ORDER BY a.studentName", title="Q4"),
    @ChildRecord(entity=QAllStudentSubjectComponentScoreExt.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a WHERE a.facultyGradingTaskId='${seq}' ORDER BY a.studentName", title="Student Score Summary")
})
        
@DisplayGroups({
    @DisplayGroup(title = "Item Counts", gridCount = 24, 
    	fields = {
    		"q1ItemCount1","q1ItemCount2","q1ItemCount3","q1ItemCount4","q1ItemCount5","q1ItemCount6",
    		"q1ItemCount7","q1ItemCount8","q1ItemCount9","q1ItemCount10","q1ItemCount11","q1ItemCount12",

    		"q2ItemCount1","q2ItemCount2","q2ItemCount3","q2ItemCount4","q2ItemCount5","q2ItemCount6",
    		"q2ItemCount7","q2ItemCount8","q2ItemCount9","q2ItemCount10","q2ItemCount11","q2ItemCount12",

    		"q3ItemCount1","q3ItemCount2","q3ItemCount3","q3ItemCount4","q3ItemCount5","q3ItemCount6",
    		"q3ItemCount7","q3ItemCount8","q3ItemCount9","q3ItemCount10","q3ItemCount11","q3ItemCount12",

    		"q4ItemCount1","q4ItemCount2","q4ItemCount3","q4ItemCount4","q4ItemCount5","q4ItemCount6",
    		"q4ItemCount7","q4ItemCount8","q4ItemCount9","q4ItemCount10","q4ItemCount11","q4ItemCount12"
    	})
})
@Displays({
    @Display(name="schoolYear",width=60,type="Label"),
    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
    @Display(name="component",type="Combo",
    		modelCombo={"ASSIGNMENT","ATTENDANCE","ATTITUDE","COMPOSITION/CREATIVE OUTPUT","FORMAL THEME/JOURNAL","GAP","HANDS ON","LABORATORY","LONG TEST","MASTERY 1","MASTERY 2","OPERATIONAL READING","OPERATIONAL MATH","PARTICIPATION","PERFORMANCE","PRACTICAL TEST","PRO MATH","PROJECT","QUARTERLY TEST","QUIZ","QUIZ/SW/READING TEST","RECITATION","SEATWORK","SW/HW/QUIZ","UNIFORM","WORK ETHICS"},width=100, enabled=false),
    @Display(name="weight",width=30, enabled=false),
    @Display(name="usePercentage", enabled=false),
    @Display(name="description",gridFieldWidth=11,width=-1),

    @Display(name="q1ItemCount1", label="1", labelTop=true, leftLabel="1st Qtr", width=30),
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

    @Display(name="q2ItemCount1", label="2nd Qtr", width=30),
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
    
    @Display(name="q3ItemCount1", label="3rd Qtr", width=30),
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
    @Display(name="q3ItemCount12", label="12", hideLabel=true, width=30),
    
    @Display(name="q4ItemCount1", label="4th Qtr", width=30),
    @Display(name="q4ItemCount2", label="2", hideLabel=true, width=30),
    @Display(name="q4ItemCount3", label="3", hideLabel=true, width=30),
    @Display(name="q4ItemCount4", label="4", hideLabel=true, width=30),
    @Display(name="q4ItemCount5", label="5", hideLabel=true, width=30),
    @Display(name="q4ItemCount6", label="6", hideLabel=true, width=30),
    @Display(name="q4ItemCount7", label="7", hideLabel=true, width=30),
    @Display(name="q4ItemCount8", label="8", hideLabel=true, width=30),
    @Display(name="q4ItemCount9", label="9", hideLabel=true, width=30),
    @Display(name="q4ItemCount10", label="10", hideLabel=true, width=30),
    @Display(name="q4ItemCount11", label="11", hideLabel=true, width=30),
    @Display(name="q4ItemCount12", label="12", hideLabel=true, width=30)
})
@Reports({
    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "1st Qtr.", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "2nd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "3rd", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q4_ClassRecord", reportTitle = "4th", reportSql = "${scheduleId}")
})
@ActionButtons({
    @ActionButton(name = "btnGenerateTask", label = "Generate Student Component Grade/Score"),
    @ActionButton(name = "btnSaveAllScore1", label = "Save All Q1"),
    @ActionButton(name = "btnSaveAllScore2", label = "Save All Q2"),
    @ActionButton(name = "btnSaveAllScore3", label = "Save All Q3"),
    @ActionButton(name = "btnSaveAllScore4", label = "Save All Q4")
})
public class FacultyFilterGradingTaskExt extends FacultyGradingTask {
}
