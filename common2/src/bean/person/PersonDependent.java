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

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonDependent")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"lastName","firstName","relation"})
@Displays({
      //  @Display(name="seq"),
      //  @Display(name="personId"),
          @Display(name="firstName"),
          @Display(name="lastName"),
          @Display(name="relation",type="Combo",modelCombo={"SPOUSE","FATHER","MOTHER","GRAND FATHER","GRAND MOTHER","BROTHER","SISTER","CHILDREN"}),
          @Display(name="birthDate"),
          @Display(name="personAddress", label="Address",gridFieldWidth=3, width=-1),
          @Display(name="alive",label="Living"),
          @Display(name="citizenship"),
          
          @Display(name="religion"),
          @Display(name="occupation"),
          @Display(name="educationStatus"),
          @Display(name="companyName"),
          @Display(name="companyAddress"),
          @Display(name="telephoneNumber"),
          @Display(name="mobileNumber"),
          @Display(name="email")
          
          //  @Display(name="firstName"),
      //  @Display(name="middleInitial"),
      //  @Display(name="birthDate"),
          //  @Display(name="employeeId")
})
public class PersonDependent extends PersonAttribute implements Serializable {
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

    @Column(name = "lastName")
    public String lastName;
    @Column(name = "firstName")
    public String firstName;
    @Column(name = "middleInitial")
    public String middleInitial;
    @Column(name = "birthDate")
    @Temporal(value = TemporalType.DATE)
    public Date birthDate;
    @Column(name = "birthPlace")
    public String birthPlace;
    @Column(name = "educationStatus")
    public String educationStatus;
    @Column(name = "course")
    public String course;
    @Column(name = "relation")
    public String relation;
    @Column(name = "citizenship")
    public String citizenship;
    @Column(name = "religion")
    public String religion;
    @Column(name = "occupation")
    public String occupation;
    @Column(name = "companyName")
    public String companyName;
    @Column(name = "companyAddress")
    public String companyAddress;
    @Column(name = "personAddress")
    public String personAddress;
    @Column(name = "telephoneNumber")
    public String telephoneNumber;
    @Column(name = "mobileNumber")
    public String mobileNumber;
    @Column(name = "email")
    public String email;
    @Column(name = "alive")
    public boolean alive = true;
   

    
    
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Override
    public String toString() {
        return relation;
    }

    public java.lang.String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(java.lang.String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    public java.lang.String getRelation() {
        return relation;
    }

    public void setRelation(java.lang.String relation) {
        this.relation = relation;
    }

    public String getFullname() {
        return lastName + ", " + firstName + ", " + middleInitial;
    }
}
