/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "GovRemittanceDetail")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"personId","remitDate","totalRemittance"})
@Displays({
    @Display(name = "personId", type="PopSearch", linktoBean=Employee.class),
    @Display(name = "remitDate"),
    @Display(name = "startDate"),
    @Display(name = "endDate"),
    @Display(name = "totalRemittance"),
    @Display(name = "taxAmount"),
    @Display(name = "sssAmount"),
    @Display(name = "healthAmount"),
    @Display(name = "pagibigAmount"),
    @Display(name = "retirementAmount"),
    @Display(name = "description")
})
@Reports({
    @template.Report(reportFile="EmployeeRemittance", reportTitle="Employee Remittance", reportSql="${personId}")
})
public class GovRemittanceDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "govRemittanceId", nullable = false)
    public int govRemittanceId;
    @Column(name = "personId", nullable = false)
    public int personId;
    @Column(name = "remitDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date remitDate;
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "totalRemittance")
    public double totalRemittance;
    @Column(name = "taxAmount")
    public double taxAmount;
    @Column(name = "sssAmount")
    public double sssAmount;
    @Column(name = "healthAmount")
    public double healthAmount;
    @Column(name = "pagibigAmount")
    public double pagibigAmount;
    @Column(name = "retirementAmount")
    public double retirementAmount;
    @Column(name = "amount1")
    public double amount1;
    @Column(name = "amount2")
    public double amount2;
    @Column(name = "amount3")
    public double amount3;
    @Column(name = "amount4")
    public double amount4;
    @Column(name = "amount5")
    public double amount5;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getAmount1() {
        return amount1;
    }

    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public double getAmount3() {
        return amount3;
    }

    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }

    public double getAmount4() {
        return amount4;
    }

    public void setAmount4(double amount4) {
        this.amount4 = amount4;
    }

    public double getAmount5() {
        return amount5;
    }

    public void setAmount5(double amount5) {
        this.amount5 = amount5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getGovRemittanceId() {
        return govRemittanceId;
    }

    public void setGovRemittanceId(int govRemittanceId) {
        this.govRemittanceId = govRemittanceId;
    }

    public double getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(double healthAmount) {
        this.healthAmount = healthAmount;
    }

    public double getPagibigAmount() {
        return pagibigAmount;
    }

    public void setPagibigAmount(double pagibigAmount) {
        this.pagibigAmount = pagibigAmount;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Date getRemitDate() {
        return remitDate;
    }

    public void setRemitDate(Date remitDate) {
        this.remitDate = remitDate;
    }

    public double getRetirementAmount() {
        return retirementAmount;
    }

    public void setRetirementAmount(double retirementAmount) {
        this.retirementAmount = retirementAmount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getSssAmount() {
        return sssAmount;
    }

    public void setSssAmount(double sssAmount) {
        this.sssAmount = sssAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalRemittance() {
        return totalRemittance;
    }

    public void setTotalRemittance(double totalRemittance) {
        this.totalRemittance = totalRemittance;
    }

}
