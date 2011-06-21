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
import javax.persistence.*;

import constants.UserInfo;

import service.util.AbstractIBean;

import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CourseSubject")
@UITemplate(template=TemplateSinglePage.class, columnSearch={"subject","weight","preferredYear","preSubject1"}, gridCount=4)
@Displays({
   // @Display(name="courseSubjectId", addInfoOnly=true),
   // @Display(name="course", label="Grade / Level", type="PopSearch", linktoBean=Course.class,addInfoOnly=true),
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,gridFieldWidth=3,width=-1),
//    @Display(name="preSubject", type="PopSearch", linktoBean=Subject.class,gridFieldWidth=3,width=-1),
    @Display(name="weight"),
    @Display(name="preferredYear", type="PopSearch", linktoBean=GradeLevel.class, label="Grade Level"),
    @Display(name="preSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300)
 })
 @DisplayGroups({
    @DisplayGroup(gridCount = 2, title = "Prerequisite Subject",  fields = {
        "preSubject1","preSubject2","preSubject3","preSubject4","preSubject5"
    }, addInfoOnly=true),
     @DisplayGroup(gridCount = 2, title = "Corequisite Subject",  fields = {
        "coSubject1","coSubject2","coSubject3","coSubject4","coSubject5"
    }, addInfoOnly=true)}
)
public class CourseSubject extends AbstractIBean implements Serializable {
	@Override
	public void delete() {
		if (UserInfo.canModifyReference()) {
			String oldCourse = course;
			String oldCode = subject;
			super.delete();
			boolean b = PanelUtil.showPrompt(null, "Curriculum Subjects is already deleted, would you like to delete Student Curriculum?");
			if (b) {
				DBClient.runSQLNative("DELETE FROM StudentSubject WHERE course='",oldCourse,"' AND subject='",oldCode,"' AND grade1<=0");
			}
		}
		else {
			PanelUtil.showError(null, "Only Administrator can delete Curriculum Subject records.");
		}
	}

	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "preferredYear", length = 4)
    public String preferredYear;
    @Column(name = "preferredSemester")
    public int preferredSemester;
    @Column(name = "subject", length = 100, nullable = false)
    public String subject;
//    @Column(name = "preSubject", length = 100)
//    public String preSubject;
    @Column(name = "exceptCurriculumYears", length = 200)
    public String exceptCurriculumYears;
    @Column(name = "course", length = 20)
    public String course;
    @Column(name = "weight")
    public double weight;
    @Column(name = "preSubject1", length = 100)
    public String preSubject1;
    @Column(name = "preSubject2", length = 100)
    public String preSubject2;
    @Column(name = "preSubject3", length = 100)
    public String preSubject3;
    @Column(name = "preSubject4", length = 100)
    public String preSubject4;
    @Column(name = "preSubject5", length = 100)
    public String preSubject5;
    @Column(name = "coSubject1", length = 100)
    public String coSubject1;
    @Column(name = "coSubject2", length = 100)
    public String coSubject2;
    @Column(name = "coSubject3", length = 100)
    public String coSubject3;
    @Column(name = "coSubject4", length = 100)
    public String coSubject4;
    @Column(name = "coSubject5", length = 100)
    public String coSubject5;

    public String getCoSubject1() {
        return coSubject1;
    }

    public void setCoSubject1(String coSubject1) {
        this.coSubject1 = coSubject1;
    }

    public String getCoSubject2() {
        return coSubject2;
    }

    public void setCoSubject2(String coSubject2) {
        this.coSubject2 = coSubject2;
    }

    public String getCoSubject3() {
        return coSubject3;
    }

    public void setCoSubject3(String coSubject3) {
        this.coSubject3 = coSubject3;
    }

    public String getCoSubject4() {
        return coSubject4;
    }

    public void setCoSubject4(String coSubject4) {
        this.coSubject4 = coSubject4;
    }

    public String getCoSubject5() {
        return coSubject5;
    }

    public void setCoSubject5(String coSubject5) {
        this.coSubject5 = coSubject5;
    }

    public String getPreSubject1() {
        return preSubject1;
    }

    public void setPreSubject1(String preSubject1) {
        this.preSubject1 = preSubject1;
    }

    public String getPreSubject2() {
        return preSubject2;
    }

    public void setPreSubject2(String preSubject2) {
        this.preSubject2 = preSubject2;
    }

    public String getPreSubject3() {
        return preSubject3;
    }

    public void setPreSubject3(String preSubject3) {
        this.preSubject3 = preSubject3;
    }

    public String getPreSubject4() {
        return preSubject4;
    }

    public void setPreSubject4(String preSubject4) {
        this.preSubject4 = preSubject4;
    }

    public String getPreSubject5() {
        return preSubject5;
    }

    public void setPreSubject5(String preSubject5) {
        this.preSubject5 = preSubject5;
    }


    @Override
	public void save() {
		if (!UserInfo.canModifyReference()) {
			PanelUtil.showError(null, "Only Administrator or 'HAS REFERENCE ACCESS' duty code can update Curriculum records.");
			return;
		}
		if (weight==0) {
			Subject sub = (Subject) DBClient.getFirstRecord("SELECT a FROM Subject a WHERE a.code='",subject,"'");
			if (sub!=null) weight = sub.unit;
		}
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return subject;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public java.lang.String getSubject() {
        return subject;
    }

    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    public java.lang.String getExceptCurriculumYears() {
        return exceptCurriculumYears;
    }

    public void setExceptCurriculumYears(java.lang.String exceptCurriculumYears) {
        this.exceptCurriculumYears = exceptCurriculumYears;
    }

//    public java.lang.String getPreSubject() {
//        return preSubject;
//    }
//
//    public void setPreSubject(java.lang.String preSubject) {
//        this.preSubject = preSubject;
//    }
//
    public java.lang.String getPreferredYear() {
        return preferredYear;
    }

    public void setPreferredYear(java.lang.String preferredYear) {
        this.preferredYear = preferredYear;
    }

    public int getPreferredSemester() {
        return preferredSemester;
    }

    public void setPreferredSemester(int preferredSemester) {
        this.preferredSemester = preferredSemester;
    }

    public Course extractCourseObj() {
        if (course == null) {
            return null;
        }
        return (Course) firstRecord("SELECT a FROM Course a WHERE a.code='",course,"'");
    }

    public static CourseSubject createCourseSubjectObj(String course, String subject) {
        CourseSubject sec = new CourseSubject();
        sec.course = course;
        sec.subject = subject;
        return sec;
    }

    @Override
    protected void runSetup() {
        createCourseSubjectObj("HIGH SCHOOL", "H3_SCIENCE III").save();
        createCourseSubjectObj("HIGH SCHOOL", "H4_SCIENCE IV").save();
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "course");
		runUniqueIndex(2, "course","subject");
	}
}
