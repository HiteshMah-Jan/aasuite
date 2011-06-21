/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Schedule;
import bean.ScheduleAssignment;
import bean.person.StudentSubject;
import bean.reference.Subject;
import java.io.Serializable;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateNoForm;

/**
 *
 * @author Charliemagne Mark
 * Student Tool
 */
@UITemplate(select="SELECT a FROM StudentSubject a WHERE a.studentId=${personId}", template=TemplateNoForm.class,
    columnSearch={"student","faculty","subject","blockOrSection","day","room", "grade1","grade2","grade3","grade4","status"}, 
    gridCount=6, title="Student Tool")
@Displays({
        @Display(name="subject", type="PopSearch", linktoBean=Subject.class),
        @Display(name="scheduleId", label="Schedule", type="PopSearch", linktoBean=Schedule.class),

        @Display(name="day", type="Label"),
        @Display(name="schedTime", type="Label"),
        @Display(name="timeEnd", type="Label"),
        @Display(name="blockOrSection", type="Label"),
        @Display(name="units", type="Label"),
        @Display(name="building", type="Label"),
        @Display(name="room", type="Label"),
        @Display(name="facultyId", type="Label")
})
@Reports({
   // @template.Report(reportFile="Schedule", reportTitle="Schedule Report", reportSql="")
})
@ChildRecords({    
    @ChildRecord(template=ChildTemplateListPopup.class,entity = ScheduleAssignment.class, sql = "SELECT a FROM ScheduleAssignment a WHERE a.scheduleId='${scheduleId}' ORDER BY a.seq DESC", fieldMapping = {"scheduleId", "scheduleId"},showButtons=false)
})
public class ScheduleExt extends StudentSubject implements Serializable {
    public static void main(String[] args) {
        view(ScheduleExt.class);
    }
}
