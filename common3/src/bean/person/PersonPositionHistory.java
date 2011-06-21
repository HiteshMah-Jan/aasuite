/*
 * PersonPositionHistory.java
 * 
 * Created on Nov 18, 2007, 8:26:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.reference.Department;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonPositionHistory")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"schoolYear","position","employer"})
@Displays({
       // @Display(name="personPositionHistoryId"),
       // @Display(name="personId"),
        @Display(name="department", type="PopSearch", linktoBean=Department.class,gridFieldWidth=3),
        @Display(name="position"),
        @Display(name="positionDate"),
        @Display(name="dateFrom"),
        @Display(name="dateTo"),
        @Display(name="employer"),
        @Display(name="grossSalary"),
        @Display(name="schoolYear"),
        @Display(name="gradeOrLevel"),
        @Display(name="subject"),
        @Display(name="reason",gridFieldWidth=3,width=-1)
})
public class PersonPositionHistory extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "department")
    public String department;
    @Column(name = "position", nullable = false)
    public String position;
    @Column(name = "positionDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date positionDate;
    @Column(name = "reason", nullable = false)
    public String reason;

    
    @Column(name = "dateFrom", nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateFrom;
    @Column(name = "dateTo", nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateTo;
    @Column(name = "employer", nullable = false)
    public String employer;
    @Column(name = "grossSalary", nullable = false)
    public double grossSalary;
    @Column(name = "schoolYear", nullable = false)
    public String schoolYear;
    @Column(name = "gradeOrLevel", nullable = false)
    public String gradeOrLevel;
    @Column(name = "subject", nullable = false)
    public String subject;

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
   
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getGradeOrLevel() {
        return gradeOrLevel;
    }

    public void setGradeOrLevel(String gradeOrLevel) {
        this.gradeOrLevel = gradeOrLevel;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    
    
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return position;
    }
}
