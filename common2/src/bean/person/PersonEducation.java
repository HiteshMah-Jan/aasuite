/*
 * PersonEducation.java
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
@Table(name = "PersonEducation")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"education","school","startDate","endDate"})
@Displays({
//        @Display(name="personEducationId"),
//        @Display(name="personId"),
        @Display(name="education",type="Combo", modelCombo={"COLLEGE","HIGH SCHOOL","ELEMENTARY","VOCATIONAL COURSE","OTHERS"},gridFieldWidth=3,width=-1),
        @Display(name="school",gridFieldWidth=3,width=-1),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="unitsEarned"),
        @Display(name="courseHonor")
})
public class PersonEducation extends PersonAttribute implements Serializable {
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

    @Column(name = "education")
    public String education;
    @Column(name = "school")
    public String school;
    @Column(name = "address")
    public String address;
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "grade")
    public String grade;
    @Column(name = "completeEnglish")
    public String completeEnglish;
    @Column(name = "completeChinese")
    public String completeChinese;
    @Column(name = "iyear")
    public String year;
    @Column(name = "unitsEarned")
    public double unitsEarned;
    @Column(name = "courseHonor")
    public String courseHonor;
    
    public String getCourseHonor() {
        return courseHonor;
    }

    public void setCourseHonor(String courseHonor) {
        this.courseHonor = courseHonor;
    }

    public double getUnitsEarned() {
        return unitsEarned;
    }

    public void setUnitsEarned(double unitsEarned) {
        this.unitsEarned = unitsEarned;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return school;
    }
}
