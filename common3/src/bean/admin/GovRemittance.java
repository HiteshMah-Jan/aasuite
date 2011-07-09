/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
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
import template.ChildRecord;
import template.ChildRecords;
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
@Table(name = "GovRemittance")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"type","remitDate","startDate","endDate","totalRemittance"})
@Displays({
    @Display(name = "type"),
    @Display(name = "remitDate"),
    @Display(name = "startDate"),
    @Display(name = "endDate"),
    @Display(name = "totalRemittance"),
    @Display(name = "remitBy"),
    @Display(name = "useTIN"),
    @Display(name = "description")
})
@ChildRecords(
    {@ChildRecord(fieldMapping={"seq","govRemittanceId"}, title="Detail", entity = GovRemittanceDetail.class, sql = "SELECT a FROM GovRemittanceDetail a WHERE a.govRemittanceId=${seq}")}
)
@Reports({
    @template.Report(reportFile="DetailRemittance", reportTitle="Detail Remittance", reportSql="${seq}"),
    @template.Report(reportFile="AlphaList", reportTitle="Alpha List", reportSql="${seq}"),
    @template.Report(reportFile="BIR1601F.jrxml", reportTitle="BIR1601F", reportSql="${seq}"),
    @template.Report(reportFile="BIR1604E.jrxml", reportTitle="BIR1604E", reportSql="${seq}"),
    @template.Report(reportFile="BIR2316.jrxml", reportTitle="BIR2316", reportSql="${seq}"),
    @template.Report(reportFile="PAGIBIGM11.jrxml", reportTitle="PAGIBIGM11", reportSql="${seq}"),
    @template.Report(reportFile="PhilhealthClaimForm1.jrxml", reportTitle="PhilhealthClaimForm1", reportSql="${seq}"),
    @template.Report(reportFile="PhilhealthER2.jrxml", reportTitle="PhilhealthER2", reportSql="${seq}"),
    @template.Report(reportFile="PhilhealthRF1.jrxml", reportTitle="PhilhealthRF1", reportSql="${seq}"),
    @template.Report(reportFile="SSSE1.jrxml", reportTitle="SSSE1", reportSql="${seq}"),
    @template.Report(reportFile="SSSE6.jrxml", reportTitle="SSSE6", reportSql="${seq}"),
    @template.Report(reportFile="SSSISL101.jrxml", reportTitle="SSSISL101", reportSql="${seq}"),
    @template.Report(reportFile="SSSR1.jrxml", reportTitle="SSSR1", reportSql="${seq}"),
    @template.Report(reportFile="SSSR1A.jrxml", reportTitle="SSSR1A", reportSql="${seq}"),
    @template.Report(reportFile="SSSR3.jrxml", reportTitle="SSSR3", reportSql="${seq}")
})
@ActionButtons({
    @ActionButton(label="Generate Remittance", name="btnGenerateRemittance")
})
public class GovRemittance extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "type", nullable = false, length = 100)
    public String type;
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
    @Column(name = "remitBy")
    public String remitBy;
    @Column(name = "useTIN")
    public String useTIN;
    @Column(name = "addfield1")
    public String addfield1;
    @Column(name = "addfield2")
    public String addfield2;
    @Column(name = "addfield3")
    public String addfield3;
    @Column(name = "addfield4")
    public String addfield4;
    @Column(name = "addfield5")
    public String addfield5;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getAddfield1() {
        return addfield1;
    }

    public void setAddfield1(String addfield1) {
        this.addfield1 = addfield1;
    }

    public String getAddfield2() {
        return addfield2;
    }

    public void setAddfield2(String addfield2) {
        this.addfield2 = addfield2;
    }

    public String getAddfield3() {
        return addfield3;
    }

    public void setAddfield3(String addfield3) {
        this.addfield3 = addfield3;
    }

    public String getAddfield4() {
        return addfield4;
    }

    public void setAddfield4(String addfield4) {
        this.addfield4 = addfield4;
    }

    public String getAddfield5() {
        return addfield5;
    }

    public void setAddfield5(String addfield5) {
        this.addfield5 = addfield5;
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

    public String getRemitBy() {
        return remitBy;
    }

    public void setRemitBy(String remitBy) {
        this.remitBy = remitBy;
    }

    public Date getRemitDate() {
        return remitDate;
    }

    public void setRemitDate(Date remitDate) {
        this.remitDate = remitDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getTotalRemittance() {
        return totalRemittance;
    }

    public void setTotalRemittance(double totalRemittance) {
        this.totalRemittance = totalRemittance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUseTIN() {
        return useTIN;
    }

    public void setUseTIN(String useTIN) {
        this.useTIN = useTIN;
    }

}
