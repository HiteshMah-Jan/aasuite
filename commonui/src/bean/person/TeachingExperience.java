/*
 * PersonExperience.java
 * 
 * Created on Nov 18, 2007, 8:26:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "TeachingExperience")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"schoolName","dates","gradesOrSubjects"})
@Displays({
        @Display(name="schoolName"),
        @Display(name="dates"),
        @Display(name="gradesOrSubjects")
})
public class TeachingExperience extends PersonAttribute{
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
   
    @Column(name = "schoolName", nullable = false)
    public String schoolName;
    @Column(name = "dates", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date dates;
    @Column(name = "gradesOrSubjects", nullable = false)
    public String gradesOrSubjects;
    
    public int getPersonId() {
        return personId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getGradesOrSubjects() {
        return gradesOrSubjects;
    }

    public void setGradesOrSubjects(String gradesOrSubjects) {
        this.gradesOrSubjects = gradesOrSubjects;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

        
}
