/*
 * PersonExperience.java
 * 
 * Created on Nov 18, 2007, 8:26:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

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
@Table(name = "PersonExperience")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"company","title","startDate","endDate"})
@Displays({
//        @Display(name="personExperienceId",addInfoOnly=true),
//        @Display(name="personId",addInfoOnly=true),
        @Display(name="company",gridFieldWidth=3,width=-1),
        @Display(name="title",gridFieldWidth=3,width=-1),
        @Display(name="descriptionOfWork",gridFieldWidth=3,width=-1),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="position"),
        @Display(name="supervisorName"),
        @Display(name="supervisorContactNumber"),
        @Display(name="reasonForLeaving"),
        @Display(name="grossSalary")
        
})
public class PersonExperience extends PersonAttribute implements Serializable {
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

    @Column(name = "company", nullable = false)
    public String company;
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "descriptionOfWork", nullable = false)
    public String descriptionOfWork;
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date endDate;

    @Column(name = "position", nullable = false)
    public String position;
    @Column(name = "grossSalary")
    public double grossSalary;
    @Column(name = "supervisorName", nullable = false)
    public String supervisorName;
    @Column(name = "supervisorContactNumber", nullable = false)
    public String supervisorContactNumber;
    @Column(name = "reasonForLeaving", nullable = false)
    public String reasonForLeaving;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    public String getSupervisorContactNumber() {
        return supervisorContactNumber;
    }

    public void setSupervisorContactNumber(String supervisorContactNumber) {
        this.supervisorContactNumber = supervisorContactNumber;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
    
    
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    @Override
    public String toString() {
        return company;
    }
}
