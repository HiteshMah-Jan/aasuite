/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.ScheduleAssignment;
import bean.reference.Course;
import bean.reference.Room;
import bean.reference.Section;
import bean.reference.Subject;
import java.io.Serializable;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateNoForm;

/**
 *
 * @author Charliemagne Mark
 * Faculty Tool
 */
@UITemplate(select="SELECT a FROM Schedule a WHERE a.facultyId=${personId} ",template=TemplateNoForm.class,columnSearch={"subject","course","blockOrSection","schedDay","room"}, gridCount=6, title="Faculty Tool")
@Displays({
        @Display(name="seq"),
        @Display(name="course", label="Grade Level", type="PopSearch", linktoBean=Course.class),
        @Display(name="blockOrSection",label="Section", gridFieldWidth=3,width=-1, type="PopSearch", linktoBean=Section.class),
        @Display(name="subject", gridFieldWidth=5, width=-1, type="PopSearch", linktoBean=Subject.class),
//        @Display(name="facultyId", gridFieldWidth=5, width=-1, label="Teacher", type="Combo", linktoBean=EmployeeFaculty.class),
        @Display(name="schedDay"),
        @Display(name="schedTime", type="Time", width=80),
        @Display(name="schedTimeEnd", type="Time", width=80),
        @Display(name="building", gridFieldWidth=3),
        @Display(name="room", type="PopSearch", linktoBean=Room.class),
        @Display(name="minimumCapacity"),
        @Display(name="maximumCapacity")
})
@Reports({
   // @template.Report(reportFile="Schedule", reportTitle="Schedule Report", reportSql="")
})
@ChildRecords({
    @ChildRecord(entity = EnrollmentSubjectFacultyExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = ScheduleAssignment.class, sql = "SELECT a FROM ScheduleAssignment a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"})
})
@ActionButtons({
    @ActionButton(name="btnVerifyGrade", label="Verify All Grades"),
    @ActionButton(name="btnPassGrade", label="Pass All Grades")
})
public class Schedule2Ext extends bean.Schedule implements Serializable {
    public static void main(String[] args) {
        view(Schedule2Ext.class);
    }
}
