/*
 * Project.java
 * 
 * Created on Nov 1, 2007, 6:27:35 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRightNoCriteria;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Project")
@UITemplate(template= TemplateTabSinglePageLeftRightNoCriteria.class, gridCount = 4, 
    columnSearch = {"projectName","product","company"},
	criteriaSearch = {"projectName","product","companyId"}
)
@Displays({
    @Display(name="projectName", gridFieldWidth = 3, width = -1),
    @Display(name="product", gridFieldWidth = 3, width = -1, type="Combo", modelCombo = {"SCHOOL","HOSPITAL","CARGO"}),
    @Display(name="companyId", type="PopSearch", linktoBean = CustomerContact.class, gridFieldWidth = 3, width = -1, label="Company"),
    @Display(name="description", gridFieldWidth = 3, width = -1, upCase=false),
    @Display(name="startDate"),
    @Display(name="completionDate")
})
@ChildRecords({
    @ChildRecord(fieldMapping={"projectName","projectName"}, title="Tasks", entity = ProjectTask.class, sql = "SELECT a FROM ProjectTask a WHERE a.projectName='${projectName}'")
})
@Reports( {
		@template.Report(reportFile = "ProjectTask", reportTitle = "Project Task", reportSql = "${projectName}"),
		@template.Report(reportFile = "ProjectTaskPerClient", reportTitle = "Per Company", reportSql = "${companyId}"),
		@template.Report(reportFile = "EmployeeTimeline", reportTitle = "Employee Timeline", reportSql = "")
})
public class Project extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "projectName", nullable = false)
    public String projectName;
    @Column(name = "product")
    public String product;
    @Column(name = "companyId")
    public int companyId;
    @Column(name = "description")
    public String description;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    public Date startDate;
    @Column(name = "completionDate")
    @Temporal(TemporalType.DATE)
    public Date completionDate;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "company")
    public String company;

    @Override
	public void save() {
    	company = extractPersonName(companyId);
		super.save();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "projectName");
	}
}
