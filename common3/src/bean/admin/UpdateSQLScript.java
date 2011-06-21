/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "UpdateSQLScript")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"description", "useDate"})
@Displays({
    @Display(name="description", width=300),
    @Display(name="useDate"),
    @Display(name="script", type="TextArea", width=-1, gridFieldWidth=3, upCase=false)
})
@ActionButtons({
    @ActionButton(name="btnRunCreateTable", label="Run Create Table"),
    @ActionButton(name="btnRunScript", label="Run Selected Script"),
    @ActionButton(name="btnRunAllScript", label="Run All Script")
})
public class UpdateSQLScript extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "script", nullable = false, length = 1000)
    public String script;
    @Column(name = "description", nullable = false, length = 150)
    public String description;
    @Column(name = "useDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date useDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    
}
