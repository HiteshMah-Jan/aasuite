/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "bug")
public class Bug extends AbstractIBean implements Serializable {
    @Id
    public Integer seq;
    @Column(name = "projectModuleId", nullable = false, length=150)
    public int projectModuleId;
    @Column(name = "title", nullable = false, length=150)
    public String title;
    @Column(name = "severity", nullable = false, length=50)
    public String severity;
    @Column(name = "assignTo", nullable = false, length=100)
    public String assignTo;
    @Column(name = "status", nullable = false, length=100)
    public String status;
    @Column(name = "enteredBy", nullable = false, length=100)
    public String enteredBy;
    @Column(name = "enteredDate")
    @Temporal(value = TemporalType.DATE)
    public Date enteredDate;
    @Column(name = "remarks", length=4000)
    public String remarks;
    @Column(name = "comments", length=4000)
    public String comments;
    @Column(name = "solution", length=4000)
    public String solution;
    @Column(name = "fixDate")
    @Temporal(value = TemporalType.DATE)
    public Date fixDate;
    @Column(name = "fixBy", length=100)
    public String fixBy;

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFixBy() {
        return fixBy;
    }

    public void setFixBy(String fixBy) {
        this.fixBy = fixBy;
    }

    public Date getFixDate() {
        return fixDate;
    }

    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    public int getProjectModuleId() {
        return projectModuleId;
    }

    public void setProjectModuleId(int projectModuleId) {
        this.projectModuleId = projectModuleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "title");
	}
}
