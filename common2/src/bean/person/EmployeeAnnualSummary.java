/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "EmployeeAnnualSummary")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"seq", "year", "personId"})
@Displays({
        @Display(name="seq"),
        @Display(name="year"),
        @Display(name="personId", label="Employee", gridFieldWidth = 3, width = -1, type = "PopSearch", linktoBean=Employee.class),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="pay13Month"),
        @Display(name="totalSssOrGsis"),
        @Display(name="totalPhilhealth"),
        @Display(name="totalPagibig"),
        @Display(name="totalUnionDues"),
        @Display(name="totalSalary"),
        @Display(name="totalExempt"),
        @Display(name="totalBasicSalary"),
        @Display(name="totalRepresentation"),
        @Display(name="totalTransportation"),
        @Display(name="totalCostOfLiving"),
        @Display(name="totalHousingAllowance"),
        @Display(name="totalOthersA"),
        @Display(name="totalOthersB"),
        @Display(name="totalCommission"),
        @Display(name="totalProfitShare"),
        @Display(name="totalFees"),
        @Display(name="taxable13Month"),
        @Display(name="totalHazardPay"),
        @Display(name="otherSupplementA"),
        @Display(name="otherSupplementB"),
        @Display(name="totalTaxableCompensation"),
        @Display(name="summaryTaxIncomeEmp"),
        @Display(name="summaryTaxFromPreviousEmp"),
        @Display(name="summaryGrossTaxIncome"),
        @Display(name="summaryTotalExemption"),
        @Display(name="summaryPremiumPaid"),
        @Display(name="summaryTaxIncome"),
        @Display(name="summaryTaxDue"),
        @Display(name="summaryAmountTaxWithheld"),
        @Display(name="summaryTaxWithheldPresent"),
        @Display(name="summaryTaxWithheldPrevious"),
        @Display(name="summaryTotalAmountTaxWithheld")
})

public class EmployeeAnnualSummary extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "iyear")
    public int year;
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "pay13Month")
    public double pay13Month;
    @Column(name = "totalSssOrGsis")
    public double totalSssOrGsis;
    @Column(name = "totalPhilhealth")
    public double totalPhilhealth;
    @Column(name = "totalPagibig")
    public double totalPagibig;
    @Column(name = "totalUnionDues")
    public double totalUnionDues;
    @Column(name = "totalSalary")
    public double totalSalary;
    @Column(name = "totalExempt")
    public double totalExempt;
    @Column(name = "totalBasicSalary")
    public double totalBasicSalary;
    @Column(name = "totalRepresentation")
    public double totalRepresentation;
    @Column(name = "totalTransportation")
    public double totalTransportation;
    @Column(name = "totalCostOfLiving")
    public double totalCostOfLiving;
    @Column(name = "totalHousingAllowance")
    public double totalHousingAllowance;
    @Column(name = "totalOthersA")
    public double totalOthersA;
    @Column(name = "totalOthersB")
    public double totalOthersB;
    @Column(name = "totalCommission")
    public double totalCommission;
    @Column(name = "totalProfitShare")
    public double totalProfitShare;
    @Column(name = "totalFees")
    public double totalFees;
    @Column(name = "taxable13Month")
    public double taxable13Month;
    @Column(name = "totalHazardPay")
    public double totalHazardPay;
    @Column(name = "otherSupplementA")
    public double otherSupplementA;
    @Column(name = "otherSupplementB")
    public double otherSupplementB;
    @Column(name = "totalTaxableCompensation")
    public double totalTaxableCompensation;
    @Column(name = "summaryTaxIncomeEmp")
    public double summaryTaxIncomeEmp;
    @Column(name = "summaryTaxFromPreviousEmp")
    public double summaryTaxFromPreviousEmp;
    @Column(name = "summaryGrossTaxIncome")
    public double summaryGrossTaxIncome;
    @Column(name = "summaryTotalExemption")
    public double summaryTotalExemption;
    @Column(name = "summaryPremiumPaid")
    public double summaryPremiumPaid;
    @Column(name = "summaryTaxIncome")
    public double summaryTaxIncome;
    @Column(name = "summaryTaxDue")
    public double summaryTaxDue;
    @Column(name = "summaryAmountTaxWithheld")
    public double summaryAmountTaxWithheld;
    @Column(name = "summaryTaxWithheldPresent")
    public double summaryTaxWithheldPresent;
    @Column(name = "summaryTaxWithheldPrevious")
    public double summaryTaxWithheldPrevious;
    @Column(name = "summaryTotalAmountTaxWithheld")
    public double summaryTotalAmountTaxWithheld;

    public double getOtherSupplementA() {
        return otherSupplementA;
    }

    public void setOtherSupplementA(double otherSupplementA) {
        this.otherSupplementA = otherSupplementA;
    }

    public double getOtherSupplementB() {
        return otherSupplementB;
    }

    public void setOtherSupplementB(double otherSupplementB) {
        this.otherSupplementB = otherSupplementB;
    }

    public double getPay13Month() {
        return pay13Month;
    }

    public void setPay13Month(double pay13Month) {
        this.pay13Month = pay13Month;
    }

    public double getSummaryAmountTaxWithheld() {
        return summaryAmountTaxWithheld;
    }

    public void setSummaryAmountTaxWithheld(double summaryAmountTaxWithheld) {
        this.summaryAmountTaxWithheld = summaryAmountTaxWithheld;
    }

    public double getSummaryGrossTaxIncome() {
        return summaryGrossTaxIncome;
    }

    public void setSummaryGrossTaxIncome(double summaryGrossTaxIncome) {
        this.summaryGrossTaxIncome = summaryGrossTaxIncome;
    }

    public double getSummaryPremiumPaid() {
        return summaryPremiumPaid;
    }

    public void setSummaryPremiumPaid(double summaryPremiumPaid) {
        this.summaryPremiumPaid = summaryPremiumPaid;
    }

    public double getSummaryTaxDue() {
        return summaryTaxDue;
    }

    public void setSummaryTaxDue(double summaryTaxDue) {
        this.summaryTaxDue = summaryTaxDue;
    }

    public double getSummaryTaxFromPreviousEmp() {
        return summaryTaxFromPreviousEmp;
    }

    public void setSummaryTaxFromPreviousEmp(double summaryTaxFromPreviousEmp) {
        this.summaryTaxFromPreviousEmp = summaryTaxFromPreviousEmp;
    }

    public double getSummaryTaxIncome() {
        return summaryTaxIncome;
    }

    public void setSummaryTaxIncome(double summaryTaxIncome) {
        this.summaryTaxIncome = summaryTaxIncome;
    }

    public double getSummaryTaxIncomeEmp() {
        return summaryTaxIncomeEmp;
    }

    public void setSummaryTaxIncomeEmp(double summaryTaxIncomeEmp) {
        this.summaryTaxIncomeEmp = summaryTaxIncomeEmp;
    }

    public double getSummaryTaxWithheldPresent() {
        return summaryTaxWithheldPresent;
    }

    public void setSummaryTaxWithheldPresent(double summaryTaxWithheldPresent) {
        this.summaryTaxWithheldPresent = summaryTaxWithheldPresent;
    }

    public double getSummaryTaxWithheldPrevious() {
        return summaryTaxWithheldPrevious;
    }

    public void setSummaryTaxWithheldPrevious(double summaryTaxWithheldPrevious) {
        this.summaryTaxWithheldPrevious = summaryTaxWithheldPrevious;
    }

    public double getSummaryTotalAmountTaxWithheld() {
        return summaryTotalAmountTaxWithheld;
    }

    public void setSummaryTotalAmountTaxWithheld(double summaryTotalAmountTaxWithheld) {
        this.summaryTotalAmountTaxWithheld = summaryTotalAmountTaxWithheld;
    }

    public double getSummaryTotalExemption() {
        return summaryTotalExemption;
    }

    public void setSummaryTotalExemption(double summaryTotalExemption) {
        this.summaryTotalExemption = summaryTotalExemption;
    }

    public double getTaxable13Month() {
        return taxable13Month;
    }

    public void setTaxable13Month(double taxable13Month) {
        this.taxable13Month = taxable13Month;
    }

    public double getTotalBasicSalary() {
        return totalBasicSalary;
    }

    public void setTotalBasicSalary(double totalBasicSalary) {
        this.totalBasicSalary = totalBasicSalary;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }

    public double getTotalCostOfLiving() {
        return totalCostOfLiving;
    }

    public void setTotalCostOfLiving(double totalCostOfLiving) {
        this.totalCostOfLiving = totalCostOfLiving;
    }

    public double getTotalExempt() {
        return totalExempt;
    }

    public void setTotalExempt(double totalExempt) {
        this.totalExempt = totalExempt;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public double getTotalHazardPay() {
        return totalHazardPay;
    }

    public void setTotalHazardPay(double totalHazardPay) {
        this.totalHazardPay = totalHazardPay;
    }

    public double getTotalHousingAllowance() {
        return totalHousingAllowance;
    }

    public void setTotalHousingAllowance(double totalHousingAllowance) {
        this.totalHousingAllowance = totalHousingAllowance;
    }

    public double getTotalOthersA() {
        return totalOthersA;
    }

    public void setTotalOthersA(double totalOthersA) {
        this.totalOthersA = totalOthersA;
    }

    public double getTotalOthersB() {
        return totalOthersB;
    }

    public void setTotalOthersB(double totalOthersB) {
        this.totalOthersB = totalOthersB;
    }

    public double getTotalPagibig() {
        return totalPagibig;
    }

    public void setTotalPagibig(double totalPagibig) {
        this.totalPagibig = totalPagibig;
    }

    public double getTotalPhilhealth() {
        return totalPhilhealth;
    }

    public void setTotalPhilhealth(double totalPhilhealth) {
        this.totalPhilhealth = totalPhilhealth;
    }

    public double getTotalProfitShare() {
        return totalProfitShare;
    }

    public void setTotalProfitShare(double totalProfitShare) {
        this.totalProfitShare = totalProfitShare;
    }

    public double getTotalRepresentation() {
        return totalRepresentation;
    }

    public void setTotalRepresentation(double totalRepresentation) {
        this.totalRepresentation = totalRepresentation;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getTotalSssOrGsis() {
        return totalSssOrGsis;
    }

    public void setTotalSssOrGsis(double totalSssOrGsis) {
        this.totalSssOrGsis = totalSssOrGsis;
    }

    public double getTotalTaxableCompensation() {
        return totalTaxableCompensation;
    }

    public void setTotalTaxableCompensation(double totalTaxableCompensation) {
        this.totalTaxableCompensation = totalTaxableCompensation;
    }

    public double getTotalTransportation() {
        return totalTransportation;
    }

    public void setTotalTransportation(double totalTransportation) {
        this.totalTransportation = totalTransportation;
    }

    public double getTotalUnionDues() {
        return totalUnionDues;
    }

    public void setTotalUnionDues(double totalUnionDues) {
        this.totalUnionDues = totalUnionDues;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return BeanUtil.concat(year,"");
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
}
