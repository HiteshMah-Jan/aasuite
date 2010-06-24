/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePage;
import util.GlobalBean;
import bean.reference.Course;
import bean.reference.Section;
import bean.reference.Subject;

/**
 *
 * @author Charliemagne Mark
 * Faculty Tool
 */
@UITemplate(template=TemplateTabSinglePage.class,columnSearch={"subject","course","section"}, gridCount=6, title="Faculty Grading")
@Displays({
        @Display(name="course", enabled=false, label="Grade Level", type="PopSearch", linktoBean=Course.class),
        @Display(name="section", enabled=false,label="Section", gridFieldWidth=3,width=-1, type="PopSearch", linktoBean=Section.class),
        @Display(name="subject", enabled=false, gridFieldWidth=5, width=-1, type="PopSearch", linktoBean=Subject.class)
})
@Reports({
    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summary Q1", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_Grades", reportTitle = "Q2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_Grades", reportTitle = "Q3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_Grades", reportTitle = "Q4", reportSql = "${section}"),
    @template.Report(reportFile = "QAll_Grades", reportTitle = "Summary Final", reportSql = "${section}"),
	@template.Report(reportFile = "SectionReportCardQuarter1", reportTitle = "Card Q1", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter2", reportTitle = "Q2", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter3", reportTitle = "Q3", reportSql = "${section}"),
	@template.Report(reportFile = "SectionReportCardQuarter4", reportTitle = "Q4", reportSql = "${section}")
})
@ChildRecords({
    @ChildRecord(entity = StudentSubjectManualGradingQ1Ext.class, template=ChildTemplateListOnly.class, title="Q1", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq} AND a.schoolYear='${useYear}'", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ2Ext.class, template=ChildTemplateListOnly.class, title="Q2", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq} AND a.schoolYear='${useYear}'", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ3Ext.class, template=ChildTemplateListOnly.class, title="Q3", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq} AND a.schoolYear='${useYear}'", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ4Ext.class, template=ChildTemplateListOnly.class, title="Q4", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq} AND a.schoolYear='${useYear}'", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingExt.class, template=ChildTemplateListOnly.class, title="All Quarters", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq} AND a.schoolYear='${useYear}'", fieldMapping = {"seq", "scheduleId"})
})
@ActionButtons({
    @ActionButton(name="btnSaveGrade1", label="Save Grade Q1"),
    @ActionButton(name="btnSaveGrade2", label="Q2"),
    @ActionButton(name="btnSaveGrade3", label="Q3"),
    @ActionButton(name="btnSaveGrade4", label="Q4")
})
public class ScheduleManualGradingExt extends bean.Schedule implements Serializable {
	public static void main(String[] args) {
    	GlobalBean.getInstance().setPersonId(10967);
        view(ScheduleManualGradingExt.class);
    }
}
