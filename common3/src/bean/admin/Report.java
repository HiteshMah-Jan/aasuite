/*
 * Report.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
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
@Table(name = "Report")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"seq", "allowed_duties", "description"})
@Displays({
        @Display(name="seq"),
        @Display(name="allowedDuties"),
        @Display(name="description"),
        @Display(name="active"),
        @Display(name="withParam"),
        @Display(name="reportName"),
        @Display(name="reportLabel")
})
public class Report extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "allowed_duties", length = 150)
    public String allowedDuties;
    @Column(name = "description", length = 150)
    public String description;
    @Column(name = "active")
    public Boolean active;
    @Column(name = "withParam")
    public Boolean withParam;
    @Column(name = "report_name", nullable = false, length = 150)
    public String reportName;
    @Column(name = "report_label", nullable = false, length = 50)
    public String reportLabel;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String toString() {
        return reportLabel;
    }

    public java.lang.String getReportLabel() {
        return reportLabel;
    }

    public void setReportLabel(java.lang.String reportLabel) {
        this.reportLabel = reportLabel;
    }

    public java.lang.Boolean getActive() {
        return active;
    }

    public void setActive(java.lang.Boolean active) {
        this.active = active;
    }

    public java.lang.Boolean getWithParam() {
        return withParam;
    }

    public void setWithParam(java.lang.Boolean withParam) {
        this.withParam = withParam;
    }

    public java.lang.String getAllowedDuties() {
        return allowedDuties;
    }

    public void setAllowedDuties(java.lang.String allowedDuties) {
        this.allowedDuties = allowedDuties;
    }
}
