/*
 * Payroll.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting.payroll;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import template.Reports;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Payroll")
@UITemplate(template = TemplateTabPage.class, gridCount = 6,
columnSearch = {"payrollName", "startDate", "endDate"})
@Displays({
    @Display(name = "payrollName", width = 200),
    @Display(name = "startDate"),
    @Display(name = "endDate")
})
@template.ActionButtons({
    @template.ActionButton(name = "btnCreateSchedule", label = "Create Payroll Schedules"),
    @template.ActionButton(name = "btnGenerate", label = "Generate Employee List"),
    @template.ActionButton(name = "btnEditAdjustment", label = "Edit Adjustment Detail")
})
@ChildRecords(value = {
    @ChildRecord(entity = EmployeePayroll.class, template = ChildTemplateListPopupDownButton.class, fieldMapping = {"seq", "payrollId"}, sql = "SELECT a FROM EmployeePayroll a WHERE a.payrollId = ${seq}", sortable = false)
})
@Reports( {
    @template.Report(reportFile = "PayrollListEmployee", reportTitle = "Payroll List", reportSql = "${seq}"),
    @template.Report(reportFile = "PayslipListEmployee", reportTitle = "Payslip List", reportSql = "${seq}")
})
public class Payroll extends AbstractIBean {

    public static void main(String[] args) {
        view(Payroll.class);
    }
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column
    public String payrollName;
    @Column
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date endDate;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPayrollName() {
        return payrollName;
    }

    public void setPayrollName(String payrollName) {
        this.payrollName = payrollName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String popupSearch(String criteria) {
        return null;
    }

    public static final int FIRST_HALF = 1;
    public static final int SECOND_HALF = 2;
}
