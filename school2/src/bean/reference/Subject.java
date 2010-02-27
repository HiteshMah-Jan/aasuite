/*
 * Subject.java
 *
 * Created on Nov 15, 2007, 1:18:50 PM
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

import service.util.AbstractIBean;
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
import bean.extension.SectionScheduleExt;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Subject")
@UITemplate(template=TemplateTabSinglePage.class, sumFooter="2",
		columnSearch={"code", "subject", "unit", "gradeLevel", "college"}, gridCount=4, title="Subject")
@ChildRecords({
    @ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=SubjectGradingCriteria.class, fieldMapping={"code","subject"}, sql="SELECT a FROM SubjectGradingCriteria a WHERE a.subject='${code}'", title="Components"),
    @ChildRecord(template=ChildTemplateListOnly.class,entity=SectionScheduleExt.class, fieldMapping={"code","subject"}, sql="SELECT a FROM Schedule a WHERE a.subject='${code}'", title="Schedule")
})
@Displays({
    @Display(name="code"),
    @Display(name="unit",width=40),
    @Display(name="subject",gridFieldWidth=3,width=-1),
    @Display(name="course",type="PopSearch",linktoBean=Course.class,gridFieldWidth=3,width=-1),
    @Display(name="gradeLevel",gridFieldWidth=3,width=-1,type="PopSearch",linktoBean=GradeLevel.class),
    @Display(name="college",gridFieldWidth=3,width=-1)
})
@Reports({
    @template.Report(reportFile="SubjectComponent", reportTitle="Components", reportSql="")
   })
public class Subject extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false, length = 100)
    public String code;
    @Column(name = "course" )
    public String course;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "unit", nullable = false)
    public double unit;
    @Column(name = "subject", nullable = false, length = 150)
    public String subject;
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

	@Override
	public void delete() {
		if (UserInfo.canModifyReference()) {
			String oldCode = code;
			super.delete();
			boolean b = PanelUtil.showPrompt(null, "Subject is already deleted, would you like to delete records from Curriculum?");
			if (b) {
				DBClient.runSQLNative("DELETE FROM CourseSubject WHERE subject='"+oldCode+"'");
				b = PanelUtil.showPrompt(null, "Curriculum Subjects is already deleted, would you like to delete Student Curriculum?");
				if (b) {
					DBClient.runSQLNative("DELETE FROM StudentSubject WHERE subject='"+oldCode+"' AND grade1<=0");
				}
			}
		}
		else {
			PanelUtil.showError(null, "Only Administrator or HAS REFERENCE ACCESS duty can delete Subject records.");
		}
	}

	@Override
	public void save() {
		if (!UserInfo.canModifyReference()) {
			PanelUtil.showError(null, "Only Administrator or 'HAS REFERENCE ACCESS' duty code can update Subject records.");
			return;
		}
		if (!"|PRESCHOOL|GRADE SCHOOL|HIGH SCHOOL|".contains("|"+course+"|")) {
			college = true;
		}
		DBClient.runSQLNative("UPDATE StudentSubject SET gradeLevel='"+gradeLevel+"', unit="+unit+" WHERE subject='"+code+"'");
    	head = extractPersonName(headId);
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "subject");
    }

    public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
    	if (isEmptyKey()) return "";
        return code+"_"+subject;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public static Subject createSubjectObj(String course, String code, String gradeLevel, int unit) {
        Subject sec = new Subject();
        sec.course = course;
        sec.code = gradeLevel+unit+"_"+code;
        sec.subject = code;
        sec.gradeLevel = gradeLevel+unit;
        sec.unit = 1;
        return sec;
    }
    
    @Override
    protected void runSetup() {
    }   

    @Override
    public boolean cacheClient() {
        return true;
    }
    
	@Override
	public void setupIndex() {
		DBClient.runSQLNative("UPDATE Section SET shift=1 WHERE shift IS NULL");
		List lst = DBClient.getList("SELECT a FROM Section a WHERE a.faculty IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}
}
