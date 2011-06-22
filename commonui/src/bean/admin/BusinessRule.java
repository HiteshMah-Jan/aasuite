/*
 * BusinessRule.java
 *
 * Created on Nov 22, 2007, 3:35:11 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "BusinessRule")
public class BusinessRule extends AbstractIBean implements Serializable {

    @Id
   @Column(name = "seq", nullable = false)
    private Integer seq;
    @Column(name = "formName", nullable = false, length = 100)
    private String formName;
    @Column(name = "eventName", nullable = false, length = 100)
    private String eventName;
    @Column(name = "rule", length=4000)
    private String rule;
    @Column(name = "updatedBy", length = 100)
    private String updatedBy;
    @Column(name = "dateUpdated")
    @Temporal(value = TemporalType.DATE)
    private Date dateUpdated;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return formName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
