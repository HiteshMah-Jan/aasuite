/*
 * BusinessRuleHistory.java
 * 
 * Created on Nov 22, 2007, 3:35:12 PM
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
@Table(name = "BusinessRuleHistory")
public class BusinessRuleHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    private Integer seq;
    @Column(name = "businessRuleId", nullable = false)
    private int businessRuleId;
    @Column(name = "updateBy", nullable = false, length=100)
    private String updateBy;
    @Column(name = "dateUpdated", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;
    @Column(name = "rule", nullable = false, length=4000)
    private String rule;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

   
    public int getBusinessRuleId() {
        return businessRuleId;
    }

    public void setBusinessRuleId(int businessRuleId) {
        this.businessRuleId = businessRuleId;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
