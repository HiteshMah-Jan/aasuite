/*
 * Allergy.java
 * 
 * Created on Oct 26, 2007, 9:34:51 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
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
@Table(name = "DynamicAccess")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"seq", "code", "formTitle"})
@Displays({
    @Display(name="seq"),
    @Display(name="code"),
    @Display(name="formTitle"),
    @Display(name="users"),
    @Display(name="duties"),
    @Display(name="groups"),
    @Display(name="type"),
    @Display(name="onClick"),
    @Display(name="onValueChange"),
    @Display(name="onLostFocus")
})
public class DynamicAccess extends AbstractIBean implements Serializable {

    @Id
   @Column(name = "seq")
    public Integer seq;
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "formTitle")
    public String formTitle;
    @Column(name = "users")
    public String users;
    @Column(name = "duties")
    public String duties;
    @Column(name = "groups")
    public String groups;
    @Column(name = "type")
    public String type;
   
    @Column(name = "onClick")
    public String onClick;
    @Column(name = "onValueChange")
    public String onValueChange;
    @Column(name = "onLostFocus")
    public String onLostFocus;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
   
    @Override
    public String toString() {
        return code;
    }

    public java.lang.String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(java.lang.String formTitle) {
        this.formTitle = formTitle;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getOnLostFocus() {
        return onLostFocus;
    }

    public void setOnLostFocus(String onLostFocus) {
        this.onLostFocus = onLostFocus;
    }

    public String getOnValueChange() {
        return onValueChange;
    }

    public void setOnValueChange(String onValueChange) {
        this.onValueChange = onValueChange;
    }

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }
}
