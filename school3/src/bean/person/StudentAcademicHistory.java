/*
 * Studentacademichistory.java
 *
 * Created on Nov 27, 2007, 6:32:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

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
@Table(name = "StudentAcademicHistory")
@UITemplate(columnSearch={"subject", "grade"})
@Displays({
        @Display(name="seq"),
        @Display(name="subject"),
        @Display(name="unit"),
        @Display(name="subjectCode"),
        @Display(name="grade"),
        @Display(name="remarks")
})
public class StudentAcademicHistory extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "subject", nullable = false)
    public String subject;
    @Column(name = "unit", nullable = false)
    public int unit;
    @Column(name = "subjectCode", nullable = false)
    public String subjectCode;
    @Column(name = "grade", nullable = false)
    public double grade;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subject","subjectCode", "unit");
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
