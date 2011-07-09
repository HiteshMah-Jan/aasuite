/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "StudentChangeCourse")
@Displays({
        @Display(name="seq"),
        @Display(name="studentId"),
        @Display(name="course"),
        @Display(name="changeCourseDate"),
        @Display(name="approvedBy"),
        @Display(name="changeBy"),
        @Display(name="reason"),
        @Display(name="remarks")
})
public class StudentChangeCourse extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "course", nullable = false)
    public String course;
    @Column(name = "changeCourseDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date changeCourseDate;
    @Column(name = "approvedBy", nullable = false)
    public String approvedBy;

    @Column(name = "changeBy", nullable = false)
    public String changeBy;
    @Column(name = "reason", nullable = false)
    public String reason;
    @Column(name = "remarks")
    public String remarks;

     @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "course","changeCourseDate", "approvedBy","changedBy");
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return BeanUtil.concat("StudentChangeCourse[",seq,"]");
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public java.lang.String getCourse() {
        return course;
    }

    public void setCourse(java.lang.String course) {
        this.course = course;
    }

    public java.lang.String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(java.lang.String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public java.util.Date getChangeCourseDate() {
        return changeCourseDate;
    }

    public void setChangeCourseDate(java.util.Date changeCourseDate) {
        this.changeCourseDate = changeCourseDate;
    }

    public java.lang.String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(java.lang.String changeBy) {
        this.changeBy = changeBy;
    }

    public java.lang.String getReason() {
        return reason;
    }

    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }
}
