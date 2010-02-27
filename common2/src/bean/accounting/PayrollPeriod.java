/*
 * PayrollPeriod.java
 *
 * Created on Nov 23, 2007, 7:42:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.admin.AppConfig;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PayrollPeriod")
@UITemplate(template = TemplateTabPage.class, showChart=true, gridCount = 8, criteriaSearch = {"payrollName", "startDate"}, columnSearch = {"payrollName", "startDate", "endDate"})
@Displays({
        @Display(name="payrollName"),
//        @Display(name="payrollType",type="Combo",modelCombo={"PER HOUR","DAILY","WEEKLY","BIMONTHLY","MONTHLY"}),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="countDays"),
        @Display(name="holidayCount"),
        
        @Display(name = "taxableIncome", type="Label"),
        @Display(name = "taxExemption", type="Label"),
        @Display(name = "tax", type="Label"),
        @Display(name = "withholdingTax",type="Label"),
        
//        @Display(name = "basicPay", type="Label"),

        @Display(name = "grossPay", type="Label"),        
        @Display(name = "otPay", type="Label"),
        @Display(name = "nightDiff", type="Label"),
        @Display(name = "holidayPay", type="Label"),
        @Display(name = "overload"),
        @Display(name = "substitution"),
        @Display(name = "taxReturn", type="Label"),
        
        @Display(name = "transpoAllowance", label="Transpo",addInfoOnly=true, type="Label"),
        @Display(name = "mealAllowance", label="Meal",addInfoOnly=true, type="Label"),
        @Display(name = "colaAllowance", label="COLA",addInfoOnly=true, type="Label"),
        @Display(name = "otherAllowance", label="Others",addInfoOnly=true, type="Label"),
//        @Display(name = "allowance", type="Label", label="Total Allowance",addInfoOnly=true, type="Label"),

        @Display(name = "absent",addInfoOnly=true, type="Label"),
        @Display(name = "tardiness",addInfoOnly=true, type="Label"),
        @Display(name = "undertime",addInfoOnly=true, type="Label"),
        @Display(name = "late",addInfoOnly=true, type="Label"),
        @Display(name = "pagibig",addInfoOnly=true, type="Label"),
        @Display(name = "philhealth",addInfoOnly=true, type="Label"),
        @Display(name = "sss",addInfoOnly=true, type="Label"),
//        @Display(name = "deduction", type="Label", label="Total Deduction",addInfoOnly=true),

        @Display(name = "m13",addInfoOnly=true, type="Label"),
        @Display(name = "m13Tax",addInfoOnly=true, type="Label"),
        @Display(name = "m14",addInfoOnly=true, type="Label"),
        @Display(name = "m14Tax",addInfoOnly=true, type="Label"),
        @Display(name = "teachersDayBonus",addInfoOnly=true, type="Label"),
        @Display(name = "chineseNewYearBonus",addInfoOnly=true, type="Label"),
        
        @Display(name = "sssLoan",addInfoOnly=true, type="Label"),
        @Display(name = "salaryLoan",addInfoOnly=true, type="Label"),
        @Display(name = "pagibigLoan",addInfoOnly=true, type="Label"),
        @Display(name = "otherLoan",addInfoOnly=true, type="Label")
//        @Display(name = "loan", type="Label", label="Total Loan")
})
@DisplayGroups({
    @DisplayGroup(title="Basic Detail", gridCount=10, fields={"grossPay","otPay","nightDiff","holidayPay","taxReturn"}),
//    @DisplayGroup(title="Allowance Detail", gridCount=8, fields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance"}),
//    @DisplayGroup(title="Deductions Detail", gridCount=8, fields={"absent","tardiness","undertime","late","pagibig","philhealth","loan","sss","deduction"}),
//    @DisplayGroup(title="Extra Months", gridCount=8, fields={"m13","m13Tax","m14","m14Tax"}),
    @DisplayGroup(title="Tax Details", gridCount=8, fields={"taxableIncome","tax","taxExemption","withholdingTax"})
})
@template.ChildRecords(
    value={@template.ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","payrollPeriodId"}, title="Employee Payroll",entity=Payroll.class,sql="SELECT a FROM Payroll a WHERE a.payrollPeriodId=${seq}")},
    info={@template.ParentAddInfo(title="Additional Information", 
            displayFields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance","teachersDayBonus","chineseNewYearBonus",
                "absent","tardiness","undertime","late","pagibig","philhealth","sss","deduction",
                "sssLoan","salaryLoan","pagibigLoan","otherLoan",
                "m13","m13Tax","m14","m14Tax"})}
)
@template.ActionButtons({
    @template.ActionButton(name="btnGeneratePayroll", label="Generate Payroll"),
    @template.ActionButton(name="btnCreatePayroll", label="Compute Payroll"),
    @template.ActionButton(name="btnPostGL", label="Post GL"),
    @template.ActionButton(name="btnViewGL", label="View GL")
})
@Reports({
    @template.Report(reportFile="PayrollPayslipReport", reportTitle="Payslip", reportSql = "${seq}"),
    @template.Report(reportFile="AllPayroll", reportTitle="All Payroll", reportSql = "${seq}"),
    @template.Report(reportFile="PayrollHistory", reportTitle="Payroll History", reportSql = "${seq}")
})
public class PayrollPeriod extends AbstractIBean implements Serializable, IGL, IPayroll {
    @Id
     @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "payrollName", nullable = false)
    public String payrollName;
    @Column(name = "generic")
    public boolean generic;
    @Column(name = "payrollType")
    public String payrollType;
    @Column(name = "startDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date endDate;
    @Column(name = "countDays")
    public int countDays;
    @Column(name = "holidayCount")
    public int holidayCount;
    @Column(name = "enteredBy")
    public String enteredBy;
    @Column(name = "enteredDate")
    @Temporal(value = TemporalType.DATE)
    public Date enteredDate;
    @Column(name = "signedDate")
    @Temporal(value = TemporalType.DATE)
    public Date signedDate;
    @Column(name = "signedBy")
    public String signedBy;
    @Column(name = "isRelease")
    public boolean release;
    @Column(name = "netPay")
    public double netPay;
    @Column(name = "withholdingTax")
    public double withholdingTax;
    @Column(name = "holidayPay")
    public double holidayPay;
    @Column(name = "basicPay")
    public double basicPay;
    @Column(name = "totalDays")
    public int totalDays;
    @Column(name = "totalDependent")
    public int totalDependent;
    @Column(name = "otPay")
    public double otPay;
    @Column(name = "nightDiff")
    public double nightDiff;
    @Column(name = "grossPay")
    public double grossPay;
    @Column(name = "absent")
    public double absent;
    @Column(name = "late")
    public double late;
    @Column(name = "tax")
    public double tax;
    @Column(name = "deduction")
    public double deduction;
    @Column(name = "loan")
    public double loan;
    @Column(name = "m13")
    public double m13;
    @Column(name = "m14")
    public double m14;
    @Column(name = "m15")
    public double m15;
    @Column(name = "m16")
    public double m16;
    @Column(name = "m17")
    public double m17;
    @Column(name = "m18")
    public double m18;
    @Column(name = "m19")
    public double m19;
    @Column(name = "m20")
    public double m20;
    @Column(name = "m21")
    public double m21;
    @Column(name = "m22")
    public double m22;
    @Column(name = "m23")
    public double m23;
    @Column(name = "m13Tax")
    public double m13Tax;
    @Column(name = "m14Tax")
    public double m14Tax;
    @Column(name = "m15Tax")
    public double m15Tax;
    @Column(name = "m16Tax")
    public double m16Tax;
    @Column(name = "m17Tax")
    public double m17Tax;
    @Column(name = "m18Tax")
    public double m18Tax;
    @Column(name = "m19Tax")
    public double m19Tax;
    @Column(name = "m20Tax")
    public double m20Tax;
    @Column(name = "m21Tax")
    public double m21Tax;
    @Column(name = "m22Tax")
    public double m22Tax;
    @Column(name = "m23Tax")
    public double m23Tax;
    @Column(name = "allowance")
    public double allowance;
    @Column(name = "pagibig")
    public double pagibig;
    @Column(name = "philhealth")
    public double philhealth;
    @Column(name = "sss")
    public double sss;
    @Column(name = "posted")
    public boolean posted;
    @Column(name = "taxableIncome")
    public double taxableIncome;
    @Column(name = "taxExemption")
    public double taxExemption;
    @Column(name = "transpoAllowance")
    public double transpoAllowance;
    @Column(name = "mealAllowance")
    public double mealAllowance;
    @Column(name = "colaAllowance")
    public double colaAllowance;
    @Column(name = "otherAllowance")
    public double otherAllowance;
    @Column(name = "tardiness")
    public double tardiness;
    @Column(name = "undertime")
    public double undertime;
    @Column(name = "taxReturn")
    public double taxReturn;
    @Column(name = "overload")
    public double overload;
    @Column(name = "substitution")
    public double substitution;
    @Column(name = "sssLoan")
    public double sssLoan;
    @Column(name = "salaryLoan")
    public double salaryLoan;
    @Column(name = "pagibigLoan")
    public double pagibigLoan;
    @Column(name = "otherLoan")
    public double otherLoan;
    @Column(name = "teachersDayBonus")
    public double teachersDayBonus;
    @Column(name = "chineseNewYearBonus")
    public double chineseNewYearBonus;
    @Column(name = "l1")
    public double l1;
    @Column(name = "l2")
    public double l2;
    @Column(name = "l3")
    public double l3;
    @Column(name = "l4")
    public double l4;
    @Column(name = "l5")
    public double l5;
    @Column(name = "a1")
    public double a1;
    @Column(name = "a2")
    public double a2;
    @Column(name = "a3")
    public double a3;
    @Column(name = "a4")
    public double a4;
    @Column(name = "a5")
    public double a5;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double getA4() {
        return a4;
    }

    public void setA4(double a4) {
        this.a4 = a4;
    }

    public double getA5() {
        return a5;
    }

    public void setA5(double a5) {
        this.a5 = a5;
    }

    public double getL1() {
        return l1;
    }

    public void setL1(double l1) {
        this.l1 = l1;
    }

    public double getL2() {
        return l2;
    }

    public void setL2(double l2) {
        this.l2 = l2;
    }

    public double getL3() {
        return l3;
    }

    public void setL3(double l3) {
        this.l3 = l3;
    }

    public double getL4() {
        return l4;
    }

    public void setL4(double l4) {
        this.l4 = l4;
    }

    public double getL5() {
        return l5;
    }

    public void setL5(double l5) {
        this.l5 = l5;
    }

    public double getChineseNewYearBonus() {
        return chineseNewYearBonus;
    }

    public void setChineseNewYearBonus(double chineseNewYearBonus) {
        this.chineseNewYearBonus = chineseNewYearBonus;
    }

    public double getOtherLoan() {
        return otherLoan;
    }

    public void setOtherLoan(double otherLoan) {
        this.otherLoan = otherLoan;
    }

    public double getOverload() {
        return overload;
    }

    public void setOverload(double overload) {
        this.overload = overload;
    }

    public double getPagibigLoan() {
        return pagibigLoan;
    }

    public void setPagibigLoan(double pagibigLoan) {
        this.pagibigLoan = pagibigLoan;
    }

    public double getSalaryLoan() {
        return salaryLoan;
    }

    public void setSalaryLoan(double salaryLoan) {
        this.salaryLoan = salaryLoan;
    }

    public double getSssLoan() {
        return sssLoan;
    }

    public void setSssLoan(double sssLoan) {
        this.sssLoan = sssLoan;
    }

    public double getSubstitution() {
        return substitution;
    }

    public void setSubstitution(double substitution) {
        this.substitution = substitution;
    }

    public double getTeachersDayBonus() {
        return teachersDayBonus;
    }

    public void setTeachersDayBonus(double teachersDayBonus) {
        this.teachersDayBonus = teachersDayBonus;
    }

    public double getHolidayPay() {
        return holidayPay;
    }

    public void setHolidayPay(double holidayPay) {
        this.holidayPay = holidayPay;
    }

    public double getWithholdingTax() {
        return withholdingTax;
    }

    public void setWithholdingTax(double withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public double getColaAllowance() {
        return colaAllowance;
    }

    public void setColaAllowance(double colaAllowance) {
        this.colaAllowance = colaAllowance;
    }

    public double getMealAllowance() {
        return mealAllowance;
    }

    public void setMealAllowance(double mealAllowance) {
        this.mealAllowance = mealAllowance;
    }

    public double getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(double otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    public double getTardiness() {
        return tardiness;
    }

    public void setTardiness(double tardiness) {
        this.tardiness = tardiness;
    }

    public double getTaxExemption() {
        return taxExemption;
    }

    public void setTaxExemption(double taxExemption) {
        this.taxExemption = taxExemption;
    }

    public double getTaxReturn() {
        return taxReturn;
    }

    public void setTaxReturn(double taxReturn) {
        this.taxReturn = taxReturn;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTranspoAllowance() {
        return transpoAllowance;
    }

    public void setTranspoAllowance(double transpoAllowance) {
        this.transpoAllowance = transpoAllowance;
    }

    public double getUndertime() {
        return undertime;
    }

    public void setUndertime(double undertime) {
        this.undertime = undertime;
    }

    public double getM13() {
        return m13;
    }

    public void setM13(double m13) {
        this.m13 = m13;
    }

    public double getM13Tax() {
        return m13Tax;
    }

    public void setM13Tax(double m13Tax) {
        this.m13Tax = m13Tax;
    }

    public double getM14() {
        return m14;
    }

    public void setM14(double m14) {
        this.m14 = m14;
    }

    public double getM14Tax() {
        return m14Tax;
    }

    public void setM14Tax(double m14Tax) {
        this.m14Tax = m14Tax;
    }

    public double getM15() {
        return m15;
    }

    public void setM15(double m15) {
        this.m15 = m15;
    }

    public double getM15Tax() {
        return m15Tax;
    }

    public void setM15Tax(double m15Tax) {
        this.m15Tax = m15Tax;
    }

    public double getM16() {
        return m16;
    }

    public void setM16(double m16) {
        this.m16 = m16;
    }

    public double getM16Tax() {
        return m16Tax;
    }

    public void setM16Tax(double m16Tax) {
        this.m16Tax = m16Tax;
    }

    public double getM17() {
        return m17;
    }

    public void setM17(double m17) {
        this.m17 = m17;
    }

    public double getM17Tax() {
        return m17Tax;
    }

    public void setM17Tax(double m17Tax) {
        this.m17Tax = m17Tax;
    }

    public double getM18() {
        return m18;
    }

    public void setM18(double m18) {
        this.m18 = m18;
    }

    public double getM18Tax() {
        return m18Tax;
    }

    public void setM18Tax(double m18Tax) {
        this.m18Tax = m18Tax;
    }

    public double getM19() {
        return m19;
    }

    public void setM19(double m19) {
        this.m19 = m19;
    }

    public double getM19Tax() {
        return m19Tax;
    }

    public void setM19Tax(double m19Tax) {
        this.m19Tax = m19Tax;
    }

    public double getM20() {
        return m20;
    }

    public void setM20(double m20) {
        this.m20 = m20;
    }

    public double getM20Tax() {
        return m20Tax;
    }

    public void setM20Tax(double m20Tax) {
        this.m20Tax = m20Tax;
    }

    public double getM21() {
        return m21;
    }

    public void setM21(double m21) {
        this.m21 = m21;
    }

    public double getM21Tax() {
        return m21Tax;
    }

    public void setM21Tax(double m21Tax) {
        this.m21Tax = m21Tax;
    }

    public double getM22() {
        return m22;
    }

    public void setM22(double m22) {
        this.m22 = m22;
    }

    public double getM22Tax() {
        return m22Tax;
    }

    public void setM22Tax(double m22Tax) {
        this.m22Tax = m22Tax;
    }

    public double getM23() {
        return m23;
    }

    public void setM23(double m23) {
        this.m23 = m23;
    }

    public double getM23Tax() {
        return m23Tax;
    }

    public void setM23Tax(double m23Tax) {
        this.m23Tax = m23Tax;
    }

    public double getAbsent() {
        return absent;
    }

    public void setAbsent(double absent) {
        this.absent = absent;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public int getHolidayCount() {
        return holidayCount;
    }

    public void setHolidayCount(int holidayCount) {
        this.holidayCount = holidayCount;
    }

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getNightDiff() {
        return nightDiff;
    }

    public void setNightDiff(double nightDiff) {
        this.nightDiff = nightDiff;
    }

    public double getOtPay() {
        return otPay;
    }

    public void setOtPay(double otPay) {
        this.otPay = otPay;
    }

    public double getPagibig() {
        return pagibig;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }

    public String getPayrollName() {
        return payrollName;
    }

    public void setPayrollName(String payrollName) {
        this.payrollName = payrollName;
    }

    public String getPayrollType() {
        return payrollType;
    }

    public void setPayrollType(String payrollType) {
        this.payrollType = payrollType;
    }

    public double getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public double getSss() {
        return sss;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getTotalDependent() {
        return totalDependent;
    }

    public void setTotalDependent(int totalDependent) {
        this.totalDependent = totalDependent;
    }


    @Transient
    public List<Payroll> listPayroll;

    public List<Payroll> getListPayroll() {
        if (listPayroll==null) {
            listPayroll = this.list("SELECT a FROM Payroll a WHERE a.payrollPeriodId="+seq);
            for (Payroll payroll : listPayroll) {
                payroll.extractBreakdownList();
            }
        }
        return listPayroll;
    }

    public String extractGLSubType() {
        return "PAYROLL";
    }

    public boolean hardcodePosting() {
        boolean hardPost = AppConfig.isHardcodePayrollPosting();
        boolean b = AppConfig.isDetailPayrollPosting();
        if (b) {
            List<Payroll> lstPayroll = list("SELECT a FROM Payroll a WHERE a.payrollPeriodId="+seq);
            for (Payroll payroll : lstPayroll) {
                if (hardPost) {
                    hardPost(payroll);
                    return true;
                }
                else {
                    GLPostingScript.post(payroll);
                }
            }
            return true;
        }
        else {
            if (hardPost) {
                hardPost(this);
                return true;
            }
        }
        return false;
    }    

    private void hardPost(IPayroll pay) {
        GL.credit(pay, constants.Constants.useDate, "502", pay.getBasicPay(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "505.1", pay.getM13(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "505.3", pay.getSss(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "505.4", pay.getPhilhealth(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "505.5", pay.getPagibig(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "505.9", pay.getMealAllowance(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "206", pay.getPagibigLoan(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "207", pay.getSssLoan(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "204", pay.getWithholdingTax(), this.payrollName);
        GL.credit(pay, constants.Constants.useDate, "122", pay.getLoan(), this.payrollName);
       

        //        if (pay instanceof Payroll) {
//            if (((Payroll)pay).isAdmin()) {
//                
//            }
//        }
    }
    
    public String extractChargeDepartment() {
        return "";
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public String extractDefaultFormula() {
        return 
                "GL.debit PAYROLLPERIOD, now, \"701\", PAYROLLPERIOD.grossPay, \"SALARIES - ACADEMIC PERSONNEL\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"804\", PAYROLLPERIOD.tax, \"TAXES, FEES, AND LICENSE\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"204\", PAYROLLPERIOD.withholdingTax, \"WITHHOLDING TAX PAYABLE - COMPENSATION\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.9\", PAYROLLPERIOD.mealAllowance, \"FOOD ALLOWANCE\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.5\", PAYROLLPERIOD.pagibig, \"PAG-IBIG FUND PREMIUM\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.4\", PAYROLLPERIOD.philhealth, \"PHILHEALTH PREMIUM\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.3\", PAYROLLPERIOD.sss, \"SSS PREMIUM\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.1\", PAYROLLPERIOD.m13, \"13TH MONTH PAY\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.2\", PAYROLLPERIOD.teachersDayBonus, \"BONUS\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"703.2\", PAYROLLPERIOD.chineseNewYearBonus, \"BONUS\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"207\", PAYROLLPERIOD.sssLoan, \"SSS LOAN PAYABLE\";" +
                "\nGL.debit PAYROLLPERIOD, now, \"206\", PAYROLLPERIOD.pagibigLoan, \"PAG-IBIG LOAN PAYABLE\";" +
                "\ndouble d = " +
                "PAYROLLPERIOD.grossPay" +
                "+PAYROLLPERIOD.tax" +
                "+PAYROLLPERIOD.withholdingTax" +
                "+PAYROLLPERIOD.mealAllowance" +
                "+PAYROLLPERIOD.pagibig" +
                "+PAYROLLPERIOD.philhealth" +
                "+PAYROLLPERIOD.sss" +
                "+PAYROLLPERIOD.m13" +
                "+PAYROLLPERIOD.teachersDayBonus" +
                "+PAYROLLPERIOD.chineseNewYearBonus" +
                "+PAYROLLPERIOD.sssLoan" +
                "+PAYROLLPERIOD.pagibigLoan;" +
                "\nGL.credit PAYROLLPERIOD, now, \"102\", d, \"TOTAL SALARY EXPENSE\";";
    }
}
