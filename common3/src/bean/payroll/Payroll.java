/*
 * Payroll.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.payroll;

import bean.accounting.IGL;
import bean.hr.Employee;
import bean.person.EmployeeBenefit;
import bean.person.EmployeeDeduction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Payroll")
@UITemplate(template = TemplateDefault.class, gridCount = 8,
    columnSearch = {"department","employeeName","grossPay","sss","withholdingTax","loan","netPay"})
@Displays({
        @Display(name = "employeeId", linktoBean=Employee.class, label="Employee", gridFieldWidth=7, width=-1, type="PopSearch"),
//        @Display(name = "employeeType", type="Label"),
//        @Display(name = "hireDate", type="Label"),
        @Display(name = "department", type="Label"),
        @Display(name = "employeeStatus", type="Label", label="Status"),
        @Display(name = "totalDays"),
        @Display(name = "netPay", type="Label"),

        @Display(name = "taxableIncome"),
        @Display(name = "taxExemption"),
        @Display(name = "tax"),
        @Display(name = "withholdingTax",type="Label"),

//        @Display(name = "basicPay", type="Label"),

        @Display(name = "grossPay"),
        @Display(name = "otPay"),
        @Display(name = "nightDiff"),
        @Display(name = "holidayPay"),
        @Display(name = "taxReturn"),
        @Display(name = "overload"),
        @Display(name = "substitution"),

        @Display(name = "transpoAllowance", label="Transpo"),
        @Display(name = "mealAllowance", label="Meal"),
        @Display(name = "colaAllowance", label="Form of Increase"),
        @Display(name = "otherAllowance", label="10% Inc SY(RICE ALLOW)"),
        @Display(name = "allowance", type="Label", label="Total Allowance"),

        @Display(name = "absent"),
        @Display(name = "tardiness"),
        @Display(name = "undertime"),
        @Display(name = "late"),
        @Display(name = "pagibig"),
        @Display(name = "philhealth"),
        @Display(name = "sss"),
        @Display(name = "deduction", type="Label", label="Total Deduction"),

        @Display(name = "m13"),
        @Display(name = "m13Tax"),
        @Display(name = "m14"),
        @Display(name = "m14Tax"),
        @Display(name = "teachersDayBonus"),
        @Display(name = "chineseNewYearBonus"),

        @Display(name = "sssLoan"),
        @Display(name = "salaryLoan"),
        @Display(name = "pagibigLoan"),
        @Display(name = "bankLoan"),
        @Display(name = "otherLoan"),
        @Display(name = "loan", type="Label", label="Total Loan")
})
@DisplayGroups({
    @DisplayGroup(title="Basic Detail", gridCount=10, fields={"grossPay","otPay","nightDiff","holidayPay","overload","substitution","taxReturn"}),
    @DisplayGroup(title="Allowance Detail", gridCount=10, fields={"transpoAllowance","mealAllowance","colaAllowance","otherAllowance","allowance"}),
    @DisplayGroup(title="Deductions Detail", gridCount=8, fields={"absent","tardiness","undertime","late","pagibig","philhealth","sss","deduction"}),
    @DisplayGroup(title="Loan Detail", gridCount=10, fields={"sssLoan","salaryLoan","pagibigLoan","otherLoan","loan"}),
    @DisplayGroup(title="Extra Months / Bonus", gridCount=8, fields={"m13","m13Tax","m14","m14Tax","teachersDayBonus","chineseNewYearBonus"}),
    @DisplayGroup(title="Tax Details", gridCount=8, fields={"taxableIncome","tax","taxExemption","withholdingTax"})
})
@template.ActionButtons({
    @template.ActionButton(name="btnViewLeave", label="Leave", parentOnly=false),
    @template.ActionButton(name="btnViewAttendance", label="Attendance", parentOnly=false),
    @template.ActionButton(name="btnRecalculate", label="Recalculate", parentOnly=false),
    @template.ActionButton(name="btnViewHistory", label="View History", parentOnly=false),
    @template.ActionButton(name="btnPrintPayslip", label="Print Payslip", parentOnly=false)
//    @template.ActionButton(name="btnPostGL", label="Post to GL")
})
public class Payroll extends AbstractIBean implements Serializable, IGL, IPayroll {
    public static final int FIRST_HALF = 1;
	public static final int SECOND_HALF = 2;
	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "payrollPeriodId", nullable = false)
    public int payrollPeriodId;
    @Column(name = "employeeId")
    public int employeeId;
    @Column(name = "employeeType", length = 30)
    public String employeeType;
    @Column(name = "jobType", length = 30)
    public String jobType;
    @Column(name = "department", length = 30)
    public String department;
    @Column(name = "paymentPeriod", length = 30)
    public String paymentPeriod;
    @Column(name = "employeeStatus", length = 30)
    public String employeeStatus;
    @Column(name = "hireDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date hireDate;
    @Column(name = "terminatedDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date terminatedDate;
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
    @Column(name = "bankLoan")
    public double bankLoan;
    @Column(name = "teachersDayBonus")
    public double teachersDayBonus;
    @Column(name = "chineseNewYearBonus")
    public double chineseNewYearBonus;

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

    public double getBankLoan() {
        return bankLoan;
    }

    public void setBankLoan(double bankLoan) {
        this.bankLoan = bankLoan;
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

    public double getTaxReturn() {
        return taxReturn;
    }

    public void setTaxReturn(double taxReturn) {
        this.taxReturn = taxReturn;
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

    public double getHolidayPay() {
        return holidayPay;
    }

    public void setHolidayPay(double holidayPay) {
        this.holidayPay = holidayPay;
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

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTaxExemption() {
        return taxExemption;
    }

    public void setTaxExemption(double taxExemption) {
        this.taxExemption = taxExemption;
    }

    public List<PayrollBreakdown> getLstBreakdown() {
        return lstBreakdown;
    }

    public void setLstBreakdown(List<PayrollBreakdown> lstBreakdown) {
        this.lstBreakdown = lstBreakdown;
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

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
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

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public int getPayrollPeriodId() {
        return payrollPeriodId;
    }

    public void setPayrollPeriodId(int payrollPeriodId) {
        this.payrollPeriodId = payrollPeriodId;
    }

    public double getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getSss() {
        return sss;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Date getTerminatedDate() {
        return terminatedDate;
    }

    public void setTerminatedDate(Date terminatedDate) {
        this.terminatedDate = terminatedDate;
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
    List<PayrollBreakdown> lstBreakdown;
	public Date startDate;
	public Date endDate;
	public String payrollName;

    public double extractValue(String breakdown) {
        lstBreakdown = extractBreakdownList();
        if (lstBreakdown==null) return 0;
        for (PayrollBreakdown brk : lstBreakdown) {
            if (brk.benefitDeductionCode.equals(breakdown)) return brk.computedAmount;
        }
        return 0;
    }

    public List<PayrollBreakdown> extractBreakdownList() {
        if (lstBreakdown==null && seq!=null && seq>0) {
            lstBreakdown = list("SELECT a FROM PayrollBreakdown a WHERE a.payrollId=",seq);
        }
        else {
            if (lstBreakdown==null) {
                lstBreakdown = new ArrayList<PayrollBreakdown>();
                Employee emp = (Employee) extractEmployee();
                List<EmployeeBenefit> lstBenefits = emp.extractBenefits();
                if (lstBenefits!=null) {
                    for (EmployeeBenefit benefit : lstBenefits) {
                        double d = benefit.getBenefitTypeObj().getCalculatedValue(emp);
                        PayrollBreakdown breakdown = new PayrollBreakdown();
                        breakdown.benefitDeductionCode = benefit.benefitCode;
                        breakdown.computedAmount = d;
                        breakdown.isBenefit = true;
                        lstBreakdown.add(breakdown);
                    }
                }

                List<EmployeeDeduction> lstDeductions = emp.extractDeductions();
                if (lstDeductions!=null) {
                    for (EmployeeDeduction dec : lstDeductions) {
                        if (dec.getDeductionTypeObj()==null) {
                            Log.info("Deduction type not found for ",dec.deductionCode);
                            continue;
                        }
                        double d = dec.getDeductionTypeObj().getCalculatedValue(emp);
                        PayrollBreakdown breakdown = new PayrollBreakdown();
                        breakdown.benefitDeductionCode = dec.deductionCode;
                        breakdown.computedAmount = d;
                        breakdown.isBenefit = false;
                        lstBreakdown.add(breakdown);
                    }
                }
//
//                List<EmployeeLoan> lstLoans = emp.getLoans();
//                for (EmployeeLoan loan : lstLoans) {
//                    double d = loan.getDeductionTypeObj().getCalculatedValue(emp);
//                    PayrollBreakdown breakdown = new PayrollBreakdown();
//                    breakdown.benefitDeductionCode = loan.loanType;
//                    breakdown.computedAmount = d;
//                    breakdown.isBenefit = false;
//                    lstBreakdown.add(breakdown);
//                }
            }
        }
        return lstBreakdown;
    }

    public void recompute() {
        //setup deduction, wages and tax based on total days
    }

    public Employee extractEmployee() {
        return (Employee) AbstractIBean.firstRecord("SELECT a FROM Employee a WHERE a.personId=",employeeId);
    }

    public String getEmployeeName() {
        Employee emp = extractEmployee();
        return emp==null?"":emp.toString();
    }

    public String getLastName() {
        Employee emp = extractEmployee();
        return emp==null?"":emp.lastName;
    }

    public String getFirstName() {
        Employee emp = extractEmployee();
        return emp==null?"":emp.firstName;
    }

    public String extractGLSubType() {
        return "PAYROLL";
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public boolean hardcodePosting() {
        return false;
    }

    public String extractChargeDepartment() {
        Employee emp = extractEmployee();
        return emp==null?"":emp.department;
    }

    public String extractDefaultFormula() {
        return BeanUtil.concat(
                "GL.debit PAYROLL, now, \"701\", PAYROLL.grossPay, \"SALARIES - ACADEMIC PERSONNEL\";",
                "\nGL.debit PAYROLL, now, \"804\", PAYROLL.tax, \"TAXES, FEES, AND LICENSE\";",
                "\nGL.debit PAYROLL, now, \"204\", PAYROLL.withholdingTax, \"WITHHOLDING TAX PAYABLE - COMPENSATION\";",
                "\nGL.debit PAYROLL, now, \"703.9\", PAYROLL.mealAllowance, \"FOOD ALLOWANCE\";",
                "\nGL.debit PAYROLL, now, \"703.5\", PAYROLL.pagibig, \"PAG-IBIG FUND PREMIUM\";",
                "\nGL.debit PAYROLL, now, \"703.4\", PAYROLL.philhealth, \"PHILHEALTH PREMIUM\";",
                "\nGL.debit PAYROLL, now, \"703.3\", PAYROLL.sss, \"SSS PREMIUM\";",
                "\nGL.debit PAYROLL, now, \"703.1\", PAYROLL.m13, \"13TH MONTH PAY\";",
                "\nGL.debit PAYROLL, now, \"703.2\", PAYROLL.teachersDayBonus, \"BONUS\";",
                "\nGL.debit PAYROLL, now, \"703.2\", PAYROLL.chineseNewYearBonus, \"BONUS\";",
                "\nGL.debit PAYROLL, now, \"207\", PAYROLL.sssLoan, \"SSS LOAN PAYABLE\";",
                "\nGL.debit PAYROLL, now, \"206\", PAYROLL.pagibigLoan, \"PAG-IBIG LOAN PAYABLE\";",
                "\ndouble d = ",
                "PAYROLL.grossPay",
                "+PAYROLL.tax",
                "+PAYROLL.withholdingTax",
                "+PAYROLL.mealAllowance",
                "+PAYROLL.pagibig",
                "+PAYROLL.philhealth",
                "+PAYROLL.sss",
                "+PAYROLL.m13",
                "+PAYROLL.teachersDayBonus",
                "+PAYROLL.chineseNewYearBonus",
                "+PAYROLL.sssLoan",
                "+PAYROLL.pagibigLoan;",
                "\nGL.credit PAYROLL, now, \"102\", d, \"TOTAL SALARY EXPENSE\";");
    }
}
