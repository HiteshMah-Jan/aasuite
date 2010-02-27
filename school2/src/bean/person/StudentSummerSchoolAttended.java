/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.*;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSummerSchoolAttended")
@UITemplate(
    template=TemplateTabPage.class,
    columnSearch={"yearLevel","yearCount","school","schoolYear","curriculumYear","address"},
    gridCount=4, title="Attended Summer School")
@ChildRecords(
    {@ChildRecord(fieldMapping={"seq", "studentSummerSchoolId"}, template=ChildTemplateListPopup.class, title="Subjects", entity = StudentSummerSchoolSubject.class, sql = "SELECT a FROM StudentSummerSchoolSubject a WHERE a.studentSummerSchoolId=${seq}")}
)
@Displays({
        @Display(name="school"),
        @Display(name="yearLevel", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="yearCount", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="schoolYear"),
        @Display(name="curriculumYear"),
        @Display(name="address"),
        
        @Display(name="month1Days", label="April days"),
        @Display(name="month1Present", label="Present"),
        @Display(name="month2Days", label="May days"),
        @Display(name="month2Present", label="Present")
})
public class StudentSummerSchoolAttended extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "yearLevel")
    public String yearLevel;
    @Column(name = "yearCount")
    public String yearCount;
    @Column(name = "school")
    public String school;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "curriculumYear")
    public String curriculumYear;
    @Column(name = "address")
    public String address;
    @Column(name = "month1Days")
    public int month1Days;
    @Column(name = "month1Present")
    public int month1Present;
    @Column(name = "month2Days")
    public int month2Days;
    @Column(name = "month2Present")
    public int month2Present;
    @Column(name = "month3Days")
    public int month3Days;
    @Column(name = "month3Present")
    public int month3Present;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "yearLevel","school", "schoolYear");
    }

    public int getMonth1Days() {
        return month1Days;
    }

    public void setMonth1Days(int month1Days) {
        this.month1Days = month1Days;
    }

    public int getMonth1Present() {
        return month1Present;
    }

    public void setMonth1Present(int month1Present) {
        this.month1Present = month1Present;
    }

    public int getMonth2Days() {
        return month2Days;
    }

    public void setMonth2Days(int month2Days) {
        this.month2Days = month2Days;
    }

    public int getMonth2Present() {
        return month2Present;
    }

    public void setMonth2Present(int month2Present) {
        this.month2Present = month2Present;
    }

    public int getMonth3Days() {
        return month3Days;
    }

    public void setMonth3Days(int month3Days) {
        this.month3Days = month3Days;
    }

    public int getMonth3Present() {
        return month3Present;
    }

    public void setMonth3Present(int month3Present) {
        this.month3Present = month3Present;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurriculumYear() {
        return curriculumYear;
    }

    public void setCurriculumYear(String curriculumYear) {
        this.curriculumYear = curriculumYear;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

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

    public String getYearCount() {
        return yearCount;
    }

    public void setYearCount(String yearCount) {
        this.yearCount = yearCount;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

	@Override
	public void setupIndex() {
		runIndex(1, "studentId");
	}
}
