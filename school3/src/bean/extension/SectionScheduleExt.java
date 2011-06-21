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
import bean.Schedule;
import bean.reference.GradeLevel;
import bean.reference.Room;
import bean.reference.Section;
import bean.reference.Subject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import util.DBClient;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template=template.screen.TemplateSinglePage.class,showChart=false,
		criteriaSearch={"subject","facultyId"},
		columnSearch={"section","subject","faculty","boysAndGirls","room1","day1","schedTime1","schedTimeEnd1"},gridCount=2)
@Displays({
        @Display(name="subject", width=300,linktoBean=Subject.class, type="PopSearch"),
        @Display(name="boysAndGirls", width=-1, type="Combo", modelCombo={"BOTH","BOYS ONLY","GIRLS ONLY"}),
        @Display(name="facultyId", width=300, label="Teacher",linktoBean=EmployeeFaculty.class, type="PopSearch"),

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
@ActionButtons( {
	@ActionButton(name = "btnViewRoom1", label = "View Schedule of Room1", parentOnly=false), 
	@ActionButton(name = "btnViewRoom2", label = "Room2", parentOnly=false), 
	@ActionButton(name = "btnViewRoom3", label = "Room3", parentOnly=false),
	@ActionButton(name = "btnViewFaculty", label = "Faculty", parentOnly=false), 
	@ActionButton(name = "btnCheckSchedules", label = "Check Schedule", parentOnly=false) 
})
public class SectionScheduleExt extends Schedule {
}