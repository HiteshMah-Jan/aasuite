package bean.payroll;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.DBClient;

@Entity
@Table(name = "EmployeePayroll")
@UITemplate(template = TemplateTabPage.class, gridCount = 4,
columnSearch = {"employeeName", "endDate", "perHourSalary", "totalHours", "totalUTHours",
    "totalAbsentHours", "totalOTHours", "totalOverTimePay", "totalNDHours",
    "totalNightTimePay", "totalAdjustmentAmount", "totalAllowanceAmount",
    "totalDeductionAmount", "totalLoanAmount", "totalTaxAmount", "totalGrossPay", "totalNetAmount"
},
criteriaSearch = {"employeeId", "payrollDate"},
editableCol = "2,3,4,5,6,7,8,9,10,11,12,13,14,15", sumFooter = "3,4,5,6,7,8,9,10,11,12,13,14,15")
@Displays({
    @Display(name = "employeeId", gridFieldWidth = 3, label = "Employee"),
    @Display(name = "perHourSalary", label = "Per Hour"),
    @Display(name = "totalHours", label = "Total Hours"),
    @Display(name = "totalUTHours", label = "UT Hour"),
    @Display(name = "totalAbsentHours", label = "Absent"),
    @Display(name = "totalOTHours", label = "OT Hours"),
    @Display(name = "totalOverTimePay", label = "OT Pay"),
    @Display(name = "totalNDHours", label = "Night"),
    @Display(name = "totalNightTimePay", label = "Night Pay"),
    @Display(name = "totalAdjustmentAmount", label = "Adjustment"),
    @Display(name = "totalAllowanceAmount", label = "Allowance"),
    @Display(name = "totalDeductionAmount", label = "Deduction"),
    @Display(name = "totalLoanAmount", label = "Loan"),
    @Display(name = "totalTaxAmount", label = "Tax"),
    @Display(name = "totalGrossPay", label = "Gross"),
    @Display(name = "totalNetAmount", label = "Net")
})
public class EmployeePayroll extends AbstractIBean {

    @Override
	public void delete() {
    	DBClient.runSQLNative("DELETE FROM EmployeePayrollAdjustment WHERE employeePayrollId=",seq);
		super.delete();
	}

	public static void main(String[] args) {
        view(EmployeePayroll.class);
    }
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "payrollId")
    public int payrollId;
    @Column(name = "employeeId")
    public int employeeId;
    @Column(name = "employeeName")
    public String employeeName;
    @Column(name = "perHourSalary")
    public double perHourSalary;
    @Column(name = "basicPay")
    public double basicPay;
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "totalHours")
    public double totalHours;
    @Column(name = "totalUTHours")
    public double totalUTHours;
    @Column(name = "totalAbsentHours")
    public double totalAbsentHours;		//this will have hours of absent, 1 day = 8 hours
    @Column(name = "totalGrossPay")
    public double totalGrossPay;		//automatic calculation based on per hour
    @Column(name = "totalOTHours")
    public double totalOTHours;
    @Column(name = "totalOverTimePay")
    public double totalOverTimePay;		//calculation based on per ot day.
    @Column(name = "totalNDHours")
    public double totalNDHours;
    @Column(name = "totalNightTimePay")
    public double totalNightTimePay;
    @Column(name = "totalAdjustmentAmount")
    public double totalAdjustmentAmount;
    @Column(name = "totalAllowanceAmount")
    public double totalAllowanceAmount;
    @Column(name = "totalDeductionAmount")
    public double totalDeductionAmount;
    @Column(name = "totalLoanAmount")
    public double totalLoanAmount;
    @Column(name = "totalTaxAmount")
    public double totalTaxAmount;
    @Column(name = "totalNetAmount")
    public double totalNetAmount;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getPerHourSalary() {
        return perHourSalary;
    }

    public void setPerHourSalary(double perHourSalary) {
        this.perHourSalary = perHourSalary;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getTotalUTHours() {
        return totalUTHours;
    }

    public void setTotalUTHours(double totalUTHours) {
        this.totalUTHours = totalUTHours;
    }

    public double getTotalAbsentHours() {
        return totalAbsentHours;
    }

    public void setTotalAbsentHours(double totalAbsentHours) {
        this.totalAbsentHours = totalAbsentHours;
    }

    public double getTotalGrossPay() {
        return totalGrossPay;
    }

    public void setTotalGrossPay(double totalGrossPay) {
        this.totalGrossPay = totalGrossPay;
    }

    public double getTotalOTHours() {
        return totalOTHours;
    }

    public void setTotalOTHours(double totalOTHours) {
        this.totalOTHours = totalOTHours;
    }

    public double getTotalOverTimePay() {
        return totalOverTimePay;
    }

    public void setTotalOverTimePay(double totalOverTimePay) {
        this.totalOverTimePay = totalOverTimePay;
    }

    public double getTotalNDHours() {
        return totalNDHours;
    }

    public void setTotalNDHours(double totalNDHours) {
        this.totalNDHours = totalNDHours;
    }

    public double getTotalNightTimePay() {
        return totalNightTimePay;
    }

    public void setTotalNightTimePay(double totalNightTimePay) {
        this.totalNightTimePay = totalNightTimePay;
    }

    public double getTotalAdjustmentAmount() {
        return totalAdjustmentAmount;
    }

    public void setTotalAdjustmentAmount(double totalAdjustmentAmount) {
        this.totalAdjustmentAmount = totalAdjustmentAmount;
    }

    public double getTotalAllowanceAmount() {
        return totalAllowanceAmount;
    }

    public void setTotalAllowanceAmount(double totalAllowanceAmount) {
        this.totalAllowanceAmount = totalAllowanceAmount;
    }

    public double getTotalDeductionAmount() {
        return totalDeductionAmount;
    }

    public void setTotalDeductionAmount(double totalDeductionAmount) {
        this.totalDeductionAmount = totalDeductionAmount;
    }

    public double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public double getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(double totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    @Override
    public String popupSearch(String criteria) {
        return null;
    }
}
