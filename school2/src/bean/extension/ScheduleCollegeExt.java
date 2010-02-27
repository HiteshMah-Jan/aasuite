/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.EmployeeFaculty;
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
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateNoForm;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 * Faculty Tool
 */
@UITemplate(select="SELECT a FROM Schedule a WHERE a.college=true ",
		template=TemplateSinglePage.class,columnSearch={"subject","course","section","room1","day1","schedTime1","schedTimeEnd1","college"}, 
		gridCount=6, title="Schedule")
@Displays({
        @Display(name="seq", addInfoOnly=true),
        @Display(name="subject", gridFieldWidth=5, width=-1,linktoBean=Subject.class, type="PopSearch"),
        //@Display(name="course", label="Grade Level", type="PopSearch", linktoBean=Course.class),
        @Display(name="section",label="Section",linktoBean=Section.class, type="PopSearch"),
        @Display(name="facultyId", gridFieldWidth=3, width=250, label="Teacher",linktoBean=EmployeeFaculty.class, type="PopSearch"),
        @Display(name="minimumCapacity", addInfoOnly=true),
        @Display(name="maximumCapacity", addInfoOnly=true),
        @Display(name="room1",label = "Room", linktoBean=Room.class, type="PopSearch", labelTop = true, leftLabel="1"),
        @Display(name="day1",labelTop=true, label = "Days"),
        @Display(name="schedTime1", type = "Time", labelTop=true, label="Time Start"),
        @Display(name="schedTimeEnd1", type = "Time", labelTop=true, label="Time End"),
        @Display(name="room2",label = "2", linktoBean=Room.class, type="PopSearch"),
        @Display(name="day2",hideLabel=true),
        @Display(name="schedTime2", type = "Time",hideLabel=true),
        @Display(name="schedTimeEnd2", type = "Time",hideLabel=true),
        @Display(name="room3",label = "3", linktoBean=Room.class, type="PopSearch"),
        @Display(name="day3",hideLabel=true),
        @Display(name="schedTime3", type = "Time",hideLabel=true),
        @Display(name="schedTimeEnd3", type = "Time",hideLabel=true)
        })
@DisplayGroups({
    @DisplayGroup(gridCount = 8, title = "Schedule", fields = {
        "room1","day1","schedTime1", "schedTimeEnd1",
        "room2","day2","schedTime2", "schedTimeEnd2",
        "room3","day3","schedTime3", "schedTimeEnd3"
        })
})
@Reports({
    @template.Report(reportFile="ScheduleByGradeLevel", reportTitle="All Schedule Report",reportSql="seq"),
    @template.Report(reportFile="ScheduleBySection", reportTitle="Schedule Report",reportSql="${section}")
})
public class ScheduleCollegeExt extends bean.Schedule implements Serializable {
	public ScheduleCollegeExt() {
		college = true;
	}
	
    public static void main(String[] args) {
        view(ScheduleCollegeExt.class);
    }

	@Override
	public String addWhere() {
		return " WHERE a.college=true ";
	}
	
	@Override
	public String popupSearch(String criteria) {
		return "SELECT a FROM Schedule a "+addWhere();
	}

	@Override
	public void save() {
		college = true;
		super.save();
	}	
}
