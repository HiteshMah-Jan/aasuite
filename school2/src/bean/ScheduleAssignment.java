/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "ScheduleAssignment")
@UITemplate(columnSearch={"submissionDate","title"}, gridCount=4, title="Assignment")
@Displays({
       // @Display(name="id",addInfoOnly=true),
       // @Display(name="scheduleId",addInfoOnly=true),
        @Display(name="submissionDate"),
        @Display(name="title"),
        @Display(name="assignment", type = "TextArea2", gridFieldWidth = 3, width = -1, height = 350)
})
public class ScheduleAssignment extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "scheduleId", nullable = false)
    public int scheduleId;
    @Column(name = "submissionDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date submissionDate;
    @Column(name = "title")
    public String title;
    @Column(name = "assignment", nullable = false, length=4000)
    public String assignment;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "title","assignment", "submissionDate");
    }

    @Override
    public String toString() {
        return title;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public java.util.Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(java.util.Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    @Override
    public String getComboDisplay() {
        return title;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    public String getScheduleString() {
        if (scheduleId==0) return "";
        Schedule sched = (Schedule) find(Schedule.class, scheduleId);
        return sched.toString();
    }
}
