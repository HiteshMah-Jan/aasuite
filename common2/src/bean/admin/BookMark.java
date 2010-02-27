/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "BookMark")
@UITemplate(select="SELECT a FROM BookMark a WHERE a.userId='${userid}'",template = template.screen.TemplateSinglePage.class, gridCount = 6, columnSearch = {"markDate","markText"})
@Displays({
        @Display(name="form", type="Label"),
        @Display(name="recordId", type="Label"),
        @Display(name="markDate", type="Label"),
        @Display(name="markText", gridFieldWidth=5, width=-1, type="Label"),
        @Display(name="remarks", gridFieldWidth=5, width=-1)
})
@template.ActionButtons({
    @template.ActionButton(name="btnShowRecord", label="Show Record")
})
public class BookMark extends AbstractIBean implements Serializable {
    @Id
   @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "form")
    public String form;
    @Column(name = "recordId")
    public String recordId;
    @Column(name = "userId")
    public String userId;
    @Column(name = "markDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date markDate;
    @Column(name = "markText", length=1000)
    public String markText;
    @Column(name = "remarks", length=1000)
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
