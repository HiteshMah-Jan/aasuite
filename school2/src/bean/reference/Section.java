/*
 * Section.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.UserInfo;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;
import util.DBClient;
import util.PanelUtil;
import bean.Employee;
import bean.EmployeeFaculty;
import bean.admin.AppConfig;
import bean.extension.SectionScheduleExt;
import bean.extension.StudentExt;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Section")
@UITemplate(
    criteriaSearch={"code", "sectionDescription","gradeLevel","facultyId"},
    columnSearch={"code", "sectionDescription","gradeLevel","faculty"},
    gridCount=4, template=TemplateTabSinglePage.class)
@Displays({
        @Display(name="code"),
        @Display(name="maxCount"),
        @Display(name="gradeLevel", type="PopSearch", linktoBean=GradeLevel.class, gridFieldWidth=3, width=-1),
        @Display(name="sectionDescription", gridFieldWidth=3, width=-1, label="Description"),
        @Display(name="facultyId", linktoBean=EmployeeFaculty.class, label="Adviser", gridFieldWidth=3, width=-1, type="PopSearch"),
        @Display(name="startTime", type = "Time", label="Time Start"),
        @Display(name="endTime", type = "Time",label="Time End"),
        @Display(name="headId", linktoBean=Employee.class, type="PopSearch", label="Head", gridFieldWidth=3, width=-1),
        @Display(name="college")
})
@ChildRecords(value={
    @ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=SectionScheduleExt.class, fieldMapping={"code","section"}, sql="SELECT a FROM Schedule a WHERE a.section='${code}'", title="Schedule"),
    @ChildRecord(template=ChildTemplateListOnly.class,entity=StudentExt.class, fieldMapping={"code","section"}, sql="SELECT a FROM Student a WHERE a.section='${code}'", title="Students"),
    @ChildRecord(template=ChildTemplateListOnly.class,entity=SectionScheduleExt.class, fieldMapping={"facultyId","facultyId"}, sql="SELECT a FROM Schedule a WHERE a.facultyId=${facultyId}", title="Schedule-Faculty")
})
@ActionButtons( {
		@ActionButton(name = "btnCheckSchedules", label = "Check Schedule") 
})        
@Reports({
    @template.Report(reportFile="FacultySchedule", reportTitle="Faculty Schedule",reportSql=""),
    @template.Report(reportFile="RoomSchedule", reportTitle="Room Schedule",reportSql=""),
    @template.Report(reportFile="SectionSchedule", reportTitle="Section Schedule",reportSql="")
})
public class Section extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "gradeLevel", nullable = false)
    public String gradeLevel;
    @Column(name = "sectionDescription")
    public String sectionDescription;
    @Column(name = "maximumCount")
    public int maxCount;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "faculty")
    public String faculty;
    @Column(name = "shift")
    public int shift;
    @Column(name = "startTime")
    public String startTime;
    @Column(name = "endTime")
    public String endTime;
    @Column(name = "college")
    public boolean college;
    @Column(name = "head")
    public String head;
    @Column(name = "headId")
    public int headId;

    public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    
    public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","gradeLevel","faculty");
    }

    @Override
	public void delete() {
		if (UserInfo.canModifyReference()) {
	    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
	    	if (lock.isQ1Locked()) {
	    		PanelUtil.showError(null, "Q1 is already locked, you cannot delete Section record.");
	    		return;
	    	}
			super.delete();
		}
		else {
    		PanelUtil.showError(null, "You do not have HAS REFERENCE ACCESS duty, you cannot delete Section record.");
		}
	}

	@Override
	public void save() {
    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
    	if (lock.isQ1Locked()) {
    		PanelUtil.showError(null, "Q1 is already locked, you cannot update Section record.");
    		return;
    	}
        if (facultyId>0) {
            EmployeeFaculty f = (EmployeeFaculty) AbstractIBean.firstRecord("SELECT a FROM EmployeeFaculty a WHERE a.personId="+facultyId);
            if (f==null) faculty = "";
            else faculty = f.toString();
        }
    	head = extractPersonName(headId);
		super.save();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				if (UserInfo.canModifyReference()) {
//		    		get all values grades
		    		DBClient.runSQLNative("UPDATE StudentValuesGrading SET facultyId="+facultyId+", faculty='"+faculty+"' WHERE section='"+code+"' AND schoolYear='"+AppConfig.getSchoolYear()+"'");
		    	}
			}
		});
		t.start();
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getFaculty() {
		return this.faculty;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public String getCourse() {
    	if (gradeLevel.startsWith("G")) {
    		return "GRADE SCHOOL";
    	}
    	else if (gradeLevel.startsWith("H")) {
    		return "HIGH SCHOOL";
    	}
    	return "PRESCHOOL";
    }
    
    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return gradeLevel+" - "+code;
    }

    public static Section createSectionObj(String section, String gradeLevel, int facultyId) {
        Section sec = new Section();
        sec.code = section;
        sec.gradeLevel = gradeLevel;
        sec.facultyId = facultyId;
        return sec;
    }
    
    @Override
    protected void runSetup() {
        createSectionObj("H3_ASSUMPTION", "H3", 5).save();
        createSectionObj("H4_ASSUMPTION", "H4", 6).save();
    }    

    @Override
    public boolean cacheClient() {
        return true;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "gradeLevel");
		runIndex(2, "gradeLevel", "shift");

		DBClient.runSQLNative("UPDATE Section SET shift=1 WHERE shift IS NULL");
		List lst = DBClient.getList("SELECT a FROM Section a WHERE a.faculty IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}
}
