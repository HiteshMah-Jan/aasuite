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
@Table(name = "FormalTraining")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"type","degree","issuingInstitution","dateReceived"})
@Displays({
        @Display(name="type", type = "Combo", modelCombo={"MAJOR", "MINOR"}),
        @Display(name="degree"),
        @Display(name="dateReceived"),
        @Display(name="issuingInstitution")
        
})
public class FormalTraining extends PersonAttribute {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "degree", nullable = false)
    public String degree;
    @Column(name = "type", nullable = false)
    public String type;
    @Column(name = "dateReceived", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date dateReceived;
    @Column(name = "issuingInstitution", nullable = false)
    public String issuingInstitution;
    
    
    public int getPersonId() {
        return personId;
    }

    public Integer getSeq() {
        return seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIssuingInstitution() {
        return issuingInstitution;
    }

    public void setIssuingInstitution(String issuingInstitution) {
        this.issuingInstitution = issuingInstitution;
    }
    
}
