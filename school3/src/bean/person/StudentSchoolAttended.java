/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSchoolAttended")
@UITemplate(
    columnSearch={"yearLevel","yearCount","school","schoolYear","curriculumYear","address"},
    gridCount=4, title="Attended School")
@Displays({
        @Display(name="school"),
        @Display(name="yearLevel", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="yearCount", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="schoolYear"),
        @Display(name="curriculumYear"),
        @Display(name="address")
       })
    @ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {"studentId", "studentId"},  entity = StudentSchoolAttended.class, sql = "SELECT a FROM StudentSchoolAttended a WHERE a.studentId=${studentId}", title = "School Attended")
})
public class StudentSchoolAttended extends AbstractIBean {
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

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "school","yearlevel");
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
