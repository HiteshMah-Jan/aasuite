/*
 * Recruitment.java
 *
 * Created on Feb 12, 2008, 12:24:56 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.hr;

import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "HRRecruitment")
@Displays({
        @Display(name="seq"),
        @Display(name="applicationDate"),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial"),
        @Display(name="personId")
})
public class HRRecruitment extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "applicationDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date applicationDate;
    @Column(name = "lastName", nullable = false)
    public String lastName;
    @Column(name = "firstName", nullable = false)
    public String firstName;
    @Column(name = "middleInitial", nullable = false)
    public String middleInitial;
    @Column(name = "personId")
    public Integer personId;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

   
    @Override
    public String toString() {
        return lastName;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }
}
