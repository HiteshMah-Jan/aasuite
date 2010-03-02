/*
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
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;

/**
 * Alphalist bean.
 * Once this class is inserted to the database it should trigger generation of
 * AlphalistEmployee and AlphaListRemittanceSummary records.
 * @author asmiranda
 */
@Entity
@Table(name = "AlphaList")
@UITemplate(template=TemplateTabPage.class,
    columnSearch={"year","tin","rdoCode"},
    criteriaSearch={"year"},
    gridCount=4, title="Alpha List")
@ChildRecords(value={
    @ChildRecord(entity=AlphaListEmployee.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","alphaListId"}, sql="SELECT a FROM AlphaListEmployee a WHERE a.alphalistId = ${seq}", sortable=false),
    @ChildRecord(entity=AlphaListRemittanceSummary.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","alphaListId"}, sql="SELECT a FROM AlphaListRemittanceSummary a WHERE a.alphalistId = ${seq}", sortable=false)
})
@DisplayGroups({
    @DisplayGroup(title = "", gridCount = 4, 
    	fields = {
    		"tin","rdoCode","lineOfBusiness","withholdingAgentName","telephoneNumber","registeredAddress",
    		"zipCode","hasRevisedFundForEmployees","revisedFundDate","totalRemittanceAmount","monthFirstCredit","withholdingAgentCategory"})
        })
@Displays({
    @Display(name="year",width=60),
    @Display(name="amendedReturn"),
    
    @Display(name="tin",width=120),
    @Display(name="rdoCode",width=60),
     
    
    @Display(name="withholdingAgentName",gridFieldWidth=3,width=-1),
    @Display(name="lineOfBusiness",width=120),
    @Display(name="telephoneNumber",width=120),
    @Display(name="registeredAddress",gridFieldWidth=3,width=-1),
    @Display(name="zipCode",width=120),
    @Display(name="withholdingAgentCategory",type="Combo",modelCombo={"Private","Government"}),
    @Display(name="hasRevisedFundForEmployees"),
    @Display(name="revisedFundDate",width=-1),
    @Display(name="totalRemittanceAmount",width=120),
    @Display(name="monthFirstCredit",type="Combo",modelCombo={"01","02","03","04","05","06","07","08","09","10","11","12"})
})
@template.ActionButtons({
    @template.ActionButton(name = "btnGenerate", label = "Create AlphaList")
})
@Reports({
    @template.Report(reportFile = "Alphalist", reportTitle = "1604-CF", reportSql = "")
})
public class AlphaList extends AbstractIBean {
    public static void main(String[] args) {
        view(AlphaList.class);
    }
    @Id
    @Column(name="seq")
    public Integer seq;
    @Column(name="year")
    public int year;
    @Column(name="amendedReturn")
    public boolean amendedReturn;
    @Column(name="numberofAttachedSheet")
    public int numberofAttachedSheet;
    @Column(name="tin")
    public String tin;
    @Column(name="rdoCode")
    public String rdoCode;
    @Column(name="lineOfBusiness")
    public String lineOfBusiness;
    @Column(name="withholdingAgentName")
    public String withholdingAgentName;
    @Column(name="telephoneNumber")
    public String telephoneNumber;
    @Column(name="registeredAddress")
    public String registeredAddress;
    @Column(name="zipCode")
    public String zipCode;
    @Column(name="hasRevisedFundForEmployees")
    public boolean hasRevisedFundForEmployees;
    @Column(name="revisedFundDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date revisedFundDate;
    @Column(name="totalRemittanceAmount")
    public double totalRemittanceAmount;
    @Column(name="monthFirstCredit")
    public String monthFirstCredit;
    @Column(name="withholdingAgentCategory")
    public String withholdingAgentCategory;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public boolean isAmendedReturn() {
        return amendedReturn;
    }

    public void setAmendedReturn(boolean amendedReturn) {
        this.amendedReturn = amendedReturn;
    }

    public boolean isHasRevisedFundForEmployees() {
        return hasRevisedFundForEmployees;
    }

    public void setHasRevisedFundForEmployees(boolean hasRevisedFundForEmployees) {
        this.hasRevisedFundForEmployees = hasRevisedFundForEmployees;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getMonthFirstCredit() {
        return monthFirstCredit;
    }

    public void setMonthFirstCredit(String monthFirstCredit) {
        this.monthFirstCredit = monthFirstCredit;
    }

    public int getNumberofAttachedSheet() {
        return numberofAttachedSheet;
    }

    public void setNumberofAttachedSheet(int numberofAttachedSheet) {
        this.numberofAttachedSheet = numberofAttachedSheet;
    }

    public String getRdoCode() {
        return rdoCode;
    }

    public void setRdoCode(String rdoCode) {
        this.rdoCode = rdoCode;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public Date getRevisedFundDate() {
        return revisedFundDate;
    }

    public void setRevisedFundDate(Date revisedFundDate) {
        this.revisedFundDate = revisedFundDate;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public double getTotalRemittanceAmount() {
        return totalRemittanceAmount;
    }

    public void setTotalRemittanceAmount(double totalRemittanceAmount) {
        this.totalRemittanceAmount = totalRemittanceAmount;
    }

    public String getWithholdingAgentCategory() {
        return withholdingAgentCategory;
    }

    public void setWithholdingAgentCategory(String withholdingAgentCategory) {
        this.withholdingAgentCategory = withholdingAgentCategory;
    }

    public String getWithholdingAgentName() {
        return withholdingAgentName;
    }

    public void setWithholdingAgentName(String withholdingAgentName) {
        this.withholdingAgentName = withholdingAgentName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String popupSearch(String criteria) {
        return null;
    }
    
}
