/*
 * HumanResourceItem.java
 *
 * Created on Feb 12, 2008, 1:47:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.hr;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "HRItem")
@Displays({
        @Display(name="seq"),
        @Display(name="item"),
        @Display(name="description"),
        @Display(name="personId"),
        @Display(name="submitted"),
        @Display(name="recruitmentId")
})
public class HRItem extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "item", nullable = false)
    public String item;
    @Column(name = "description")
    public String description;
    @Column(name = "personId")
    public int personId;
    @Column(name = "submitted")
    public boolean submitted;
    @Column(name = "recruitmentId")
    public int recruitmentId;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public int getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(int recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
