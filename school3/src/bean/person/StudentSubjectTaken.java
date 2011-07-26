/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.PanelUtil;
import bean.reference.Subject;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSubjectTaken")
@UITemplate(
    columnSearch={"studentId","schoolYear","semester","subject","unit","passed","finalRating","actionTaken"}, 
    gridCount=6, title="Subjects")
@Displays({
    @Display(name="gradeLevel", width=100, label="Grade", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
    @Display(name="schoolYear", width=100, label="Year"),
    @Display(name="semester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3","4"}),
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
    @Display(name="unit"),
    @Display(name="passed"),
    @Display(name="finalRating",label="Final Rating"),
    @Display(name="actionTaken",label="Action Taken", type="Combo", modelCombo={"PASSED","FAILED","DO","DU"})
})
public class StudentSubjectTaken extends AbstractIBean implements Serializable {

	@Override
	public int getRecordListCount() {
		return 100;
	}

	@Override
	public void delete() {
		if (UserInfo.canDeleteGrade()) {
			super.delete();
		}
		else {
			PanelUtil.showError(null, "You do not have duty code [HAS DELETE GRADE] to delete component.");
		}
	}
	
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "section")
    public String section;
    @Column(name = "subject", length = 100)
    public String subject;
    @Column(name = "schoolYear", length = 20)
    public String schoolYear;
    @Column(name = "semester")
    public int semester;
    @Column(name = "passed")
    public boolean passed;
    @Column(name = "enrollmentId")
    public int enrollmentId;
    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "faculty")
    public String faculty;
    @Column(name = "course")
    public String course;
    @Column(name = "actionTaken")
    public String actionTaken;
    @Column(name = "grade1")
    public double grade1;
    @Column(name = "grade2")
    public double grade2;
    @Column(name = "grade3")
    public double grade3;
    @Column(name = "grade4")
    public double grade4;
    @Column(name = "finalRating")
    public double finalRating;

    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "unit")
    public double unit;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public double getGrade1() {
		return grade1;
	}

	public void setGrade1(double grade1) {
		this.grade1 = grade1;
	}

	public double getGrade2() {
		return grade2;
	}

	public void setGrade2(double grade2) {
		this.grade2 = grade2;
	}

	public double getGrade3() {
		return grade3;
	}

	public void setGrade3(double grade3) {
		this.grade3 = grade3;
	}

	public double getGrade4() {
		return grade4;
	}

	public void setGrade4(double grade4) {
		this.grade4 = grade4;
	}

	public double getFinalRating() {
		return finalRating;
	}

	public void setFinalRating(double finalRating) {
		this.finalRating = finalRating;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public double getUnit() {
		return unit;
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subject","preSubject", "cashier");
    }
}
