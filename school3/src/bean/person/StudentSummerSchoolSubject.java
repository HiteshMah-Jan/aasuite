/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import bean.*;
import bean.reference.Subject;
import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSummerSchoolSubject")
@UITemplate(
    columnSearch={"studentId","schoolYear","preferredSemester","subject","prelimGrade","midtermGrade","finalsGrade","grade","finalRating"},
    gridCount=6, title="Subjects")
@Displays({
        @Display(name="schoolYear", width=100, type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="preferredSemester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3"}),
        @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
        @Display(name="passed"),
        @Display(name="prelimGrade",label="1st"),
        @Display(name="midtermGrade",label="2nd"),
        @Display(name="finalsGrade",label="3rd"),
        @Display(name="grade",label="4th"),
        @Display(name="finalRating",label="Final Rating"),
        @Display(name="actionTaken",label="Action Taken", type="Combo", modelCombo={"PASSED","FAILED","DO","DU"})
})
public class StudentSummerSchoolSubject extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq; 
    @Column(name = "studentSummerSchoolId", nullable = false)
    public int studentSummerSchoolId;
    @Column(name = "studentId", nullable = false) 
    public int studentId;
    @Column(name = "line") 
    public int line;
    @Column(name = "subject", length = 100)
    public String subject;
    @Column(name = "preSubject", length = 100)
    public String preSubject;
    @Column(name = "schoolYear", length = 4)
    public String schoolYear;
    @Column(name = "preferredSemester")
    public int preferredSemester;
    @Column(name = "passed")
    public boolean passed;
    @Column(name = "prelimGrade")
    public double prelimGrade;
    @Column(name = "midtermGrade")
    public double midtermGrade;
    @Column(name = "finalsGrade")
    public double finalsGrade;
    @Column(name = "enrollmentId")
    public int enrollmentId;
    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "grade")
    public double grade;
    @Column(name = "course")
    public String course;
    @Column(name = "actionTaken")
    public String actionTaken;
    @Column(name = "finalRating")
    public double finalRating;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subject","preSubject");
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getStudentSummerSchoolId() {
        return studentSummerSchoolId;
    }

    public void setStudentSummerSchoolId(int studentSummerSchoolId) {
        this.studentSummerSchoolId = studentSummerSchoolId;
    }

    public double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
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

    public java.lang.String getSubject() {
        return subject;
    }

    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    public java.lang.String getPreSubject() {
        return preSubject;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public void setPreSubject(java.lang.String preSubject) {
        this.preSubject = preSubject;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public java.lang.String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(java.lang.String preferredYear) {
        this.schoolYear = preferredYear;
    }

    public int getPreferredSemester() {
        return preferredSemester;
    }

    public void setPreferredSemester(int preferredSemester) {
        this.preferredSemester = preferredSemester;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public double getPrelimGrade() {
        return prelimGrade;
    }

    public void setPrelimGrade(double prelimGrade) {
        this.prelimGrade = prelimGrade;
    }

    public double getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(double midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public double getFinalsGrade() {
        return finalsGrade;
    }

    public void setFinalsGrade(double finalsGrade) {
        this.finalsGrade = finalsGrade;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudentName() {
        if (studentId == 0) {
            return "";
        }
        Student stud = (Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.personId=",studentId);
        return stud.getFormattedTitle();
    }

    public double getUnit() {
        Subject sub = (Subject) AbstractIBean.firstRecord("SELECT a FROM Subject a WHERE a.code='",subject,"'");
        if (sub==null) return 0;
        return sub.getUnit();
    }

    public String getSubjectName() {
        Subject sub = (Subject) AbstractIBean.firstRecord("SELECT a FROM Subject a WHERE a.code='",subject,"'");
        if (sub==null) return "";
        return sub.getSubject();
    }
}
