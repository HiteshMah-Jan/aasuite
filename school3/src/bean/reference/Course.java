/*
 * Course.java
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

import bean.Employee;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Course")
@UITemplate(template=TemplateTabSinglePage.class, columnSearch={"code", "courseName", "head"}, gridCount=4, title="Course/Grade")
@ChildRecords({
    @ChildRecord(entity=CourseSubject.class, fieldMapping={"code","course"}, sql="SELECT a FROM CourseSubject a WHERE a.course='${code}'", title="Curriculum Subject"),
    @ChildRecord(entity=CourseTotalDays.class, fieldMapping={"code","course"}, sql="SELECT a FROM CourseTotalDays a WHERE a.course='${code}'", title="Total Days")
})
@Displays({
    @Display(name="code"),
    @Display(name="courseName"),
    @Display(name="headId", linktoBean=Employee.class, type="PopSearch", label="Head", gridFieldWidth=3, width=-1),
    @Display(name="courseDescription", gridFieldWidth=3, width=-1)
})
//@ActionButtons({
//    @ActionButton(label="Generate Curriculum", name="btnGenerateCurriculum"),
//    @ActionButton(name="btnGeneratePlan", label="Generate Plan", parentOnly=false)
//})
public class Course extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 20)
    public String code;
    @Column(name = "courseName", nullable = false, length = 50)
    public String courseName;
    @Column(name = "courseDescription")
    public String courseDescription;
    @Column(name = "college")
    public boolean college;
    @Column(name = "head")
    public String head;
    @Column(name = "headId")
    public int headId;

    @Override
	public void save() {
    	head = extractPersonName(headId);
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
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

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public java.lang.String getHead() {
        return head;
    }

    public void setHead(java.lang.String head) {
        this.head = head;
    }

    public java.lang.String getCourseName() {
        return courseName;
    }

    public void setCourseName(java.lang.String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getComboDisplay() {
        return code;
    }

    public List<GradeLevel> extractGradeLevels() {
        return GradeLevel.extractCacheListBeans(GradeLevel.class);
    }

    public static void main(String[] args) {
    	Course c = new Course();
    	c.runSetup();
        view(Course.class);
    }

    public static Course createCourseObj(String code, String name) {
        Course course = new Course();
        course.code = code;
        course.courseName = name;
        return course;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
