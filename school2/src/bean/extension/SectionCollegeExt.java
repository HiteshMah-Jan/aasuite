/*
 * Section.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.EmployeeFaculty;
import bean.Schedule;
import bean.extension.Q1StudentSubjectComponentScoreExt;
import bean.extension.Q2StudentSubjectComponentScoreExt;
import bean.extension.Q3StudentSubjectComponentScoreExt;
import bean.extension.Q4StudentSubjectComponentScoreExt;
import bean.extension.QAllStudentSubjectComponentScoreExt;
import bean.extension.SectionScheduleExt;
import bean.extension.StudentExt;
import bean.reference.GradeLevel;
import bean.reference.Section;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
    criteriaSearch={"code", "sectionDescription","gradeLevel","facultyId"},
    columnSearch={"code", "sectionDescription","gradeLevel","faculty"},
    gridCount=4, template=TemplateTabSinglePage.class, select="SELECT a FROM Section a WHERE a.college=true")
@Displays({
        @Display(name="code"),
        @Display(name="maxCount"),
        @Display(name="gradeLevel", type="PopSearch", linktoBean=GradeLevelCollegeExt.class, gridFieldWidth=3, width=-1),
        @Display(name="sectionDescription", gridFieldWidth=3, width=-1, label="Description"),
        @Display(name="facultyId", linktoBean=EmployeeFaculty.class, label="Adviser", gridFieldWidth=3, width=-1, type="PopSearch"),
        @Display(name="startTime", type = "Time", label="Time Start"),
        @Display(name="endTime", type = "Time",label="Time End")
})
@ChildRecords(value={
    @ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=SectionScheduleExt.class, fieldMapping={"code","section"}, sql="SELECT a FROM Schedule a WHERE a.section='${code}'", title="Schedule"),
    @ChildRecord(template=ChildTemplateListOnly.class,entity=StudentExt.class, fieldMapping={"code","section"}, sql="SELECT a FROM Student a WHERE a.section='${code}'", title="Students")
})
        
public class SectionCollegeExt extends Section implements Serializable {
	public SectionCollegeExt() {
		college = true;
	}

	@Override
	public String addWhere() {
		return " WHERE a.college=true ";
	}
	
	@Override
	public String popupSearch(String criteria) {
		return "SELECT a FROM Section a "+addWhere();
	}

	@Override
	public void save() {
		college = true;
		gradeLevel = "ANY";
		super.save();
	}
	
}
