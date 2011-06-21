/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "DynamicReport")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "reportName", "reportTitle"})
@Displays({
        @Display(name="code"),
        @Display(name="reportName"),
        @Display(name="reportTitle"),
        @Display(name="category"),
        @Display(name="sortNumber"),
        @Display(name="publicAccess"),
        @Display(name="xmlContent")
})
public class DynamicReport extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "reportName")
    public String reportName;
    @Column(name = "reportTitle")
    public String reportTitle;
    @Column(name = "category")
    public String category;
    @Column(name = "sortNumber")
    public int sortNumber;
    @Column(name = "publicAccess")
    public boolean publicAccess;  
    @Column(name = "xmlContent", nullable = false, length = 4000)
    public String xmlContent;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public DynamicReport() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
   
    @Override
    public String toString() {
        return reportTitle;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }

    public java.lang.String getReportName() {
        return reportName;
    }

    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }
    @Transient
    private File f;

    public File getFile() {
        return f;
    }

    public void setFile(File f) {
        this.f = f;
    }

    public java.lang.String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(java.lang.String reportTitle) {
        this.reportTitle = reportTitle;
    }

    @Override
    public String getComboDisplay() {
        if (reportTitle == null) {
            return code;
        }
        return reportTitle;
    }

    public boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public java.lang.String getCategory() {
        return category;
    }

    public void setCategory(java.lang.String category) {
        this.category = category;
    }
}
