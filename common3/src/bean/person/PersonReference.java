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
@Table(name = "PersonReference")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"name","phoneNumber","position"})
@Displays({
        @Display(name="referenceType", type="Combo", modelCombo={"SPEAK CHRISTIAN VALUES", "SPEAK PROFESSIONAL TRAINING"}),
        @Display(name="name"),
        @Display(name="completeAddress"),
        @Display(name="phoneNumber"),
        @Display(name="position"),
        @Display(name="speakChristianValues"),
        @Display(name="speakProfessionalTraining")
})
public class PersonReference extends PersonAttribute {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "completeAddress", nullable = false)
    public String completeAddress;
    @Column(name = "phoneNumber")
    public String phoneNumber;
    @Column(name = "position")
    public String position;
    @Column(name = "referenceType", nullable = false)
    public String referenceType;
    @Column(name = "speakChristianValues")
    public boolean speakChristianValues;
    @Column(name = "speakProfessionalTraining")
    public boolean speakProfessionalTraining;

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    
    
    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean getSpeakChristianValues() {
        return speakChristianValues;
    }

    public void setSpeakChristianValues(boolean speakChristianValues) {
        this.speakChristianValues = speakChristianValues;
    }

    public boolean getSpeakProfessionalTraining() {
        return speakProfessionalTraining;
    }

    public void setSpeakProfessionalTraining(boolean speakProfessionalTraining) {
        this.speakProfessionalTraining = speakProfessionalTraining;
    }
    
    public int getPersonId() {
        return personId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    
}
