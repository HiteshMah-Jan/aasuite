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
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "DynamicReportAccess")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"seq", "dynamicReportCode", "username"})
@Displays({
        @Display(name="seq"),
        @Display(name="dynamicReportCode"),
        @Display(name="username")
})
public class DynamicReportAccess extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "dynamicReportCode")
    public String dynamicReportCode;
    @Column(name = "username")
    public String username;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public DynamicReportAccess() {
    }

   
    @Override
    public String toString() {
        return dynamicReportCode;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getDynamicReportCode() {
        return dynamicReportCode;
    }

    public void setDynamicReportCode(java.lang.String dynamicReportCode) {
        this.dynamicReportCode = dynamicReportCode;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }
}
