/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Employee;
import bean.accounting.PaymentLoan;
import bean.accounting.Payroll;
import bean.accounting.PayrollPeriod;
import bean.person.EmployeeLeaveApplication;
import bean.person.EmployeeLoan;
import bean.person.PersonAttendance;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import template.screen.TablePopup;
import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public class Payroll_RULE extends BusinessRuleWrapper {

    @Override
    public void afterSave(AbstractIBean bean) {
        Payroll pay = (Payroll) this.getBean();
        //setup all the loans
        if (pay.sssLoan>0) putLoanPayment("SSS", pay.sssLoan);
        if (pay.otherLoan>0) putLoanPayment("OTHERS", pay.otherLoan);
        if (pay.pagibigLoan>0) putLoanPayment("PAGIBIG", pay.pagibigLoan);
        if (pay.salaryLoan>0) putLoanPayment("SALARY", pay.salaryLoan);
    }

    private void printPayslip() {
        Payroll pay = (Payroll) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplate("PaySlip", pay.payrollPeriodId);
    }

    private void putLoanPayment(String loanType, double amount) {
        Payroll pay = (Payroll) this.getBean();
        EmployeeLoan loan = getEmployeeLoanObj(loanType);
        if (loan!=null) {
            //put the payment loan
            PaymentLoan payment = (PaymentLoan) AbstractIBean.firstRecord("SELECT a FROM PaymentLoan a WHERE a.payrollId="+pay.seq+" AND a.employeeLoanId="+loan.seq);
            if (payment==null) {
                payment = new PaymentLoan();
                payment.employeeId = pay.employeeId;
                payment.paidBy = pay.employeeId;
                payment.employeeLoanId = loan.seq;
                payment.payrollId = pay.seq;
                payment.recordId = pay.seq;
            }
            payment.dueDate = constants.Constants.useDate;
            payment.amount = amount;
            payment.save();
            //recompute total for employee loan
            List lstPayments = loan.getPayments();
            loan.totalPaidCount = lstPayments.size();
            loan.totalPaidAmount = BeanUtil.getSum(lstPayments, "amount");
            loan.save();
        }
    }
    
    @Override
    public void runFocusLost(JComponent comp) {
        if ("employeeId".equals(comp.getName())) {
            recalculate();
        }
        computePay();
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnRecalculate".equals(comp.getName())) {
            recalculate();
        }
        else if ("btnViewHistory".equals(comp.getName())) {
            viewHistory();
        }
        else if ("btnPrintPayslip".equals(comp.getName())) {
            printPayslip();
        }
        else if ("btnViewLeave".equals(comp.getName())) {
            viewLeave();
        }
        else if ("btnViewAttendance".equals(comp.getName())) {
            viewAttendance();
        }
        computePay();
    }

    protected void recalculate() {
        Payroll pay = (Payroll) this.getBean();
        if (pay==null) return;
        if (!pay.isEmptyKey()) return;
        if (pay.employeeId==0) {
            showErrorMessageToScreen("Please select employee to calculate the payroll.");
            return;
        }
        Employee emp = pay.extractEmployee();
        pay.jobType = emp.position+"";
        setValue("employeeType", emp.position);
        setValue("department", emp.department);
        setValue("employeeStatus", emp.status);
        if (emp.hiredDate!=null) setValue("hireDate", emp.hiredDate);
        setValue("totalDependent", emp.extractDependentCount());
        if (emp.basicPay>0) {
            double taxExemption = emp.extractBenefitValue(emp.status, "TAX EXEMPTION");
            setValue("basicPay", emp.basicPay/2);   //this is bi monthly
            if (emp.monthlyAllowance>0) setValue("allowance", emp.monthlyAllowance/2);
            if (emp.monthlyLoan>0) setValue("loan", emp.monthlyLoan/2);
            double pagibig = emp.extractDeductionValue("PAGIBIG", "OTHERS");
            double philhealth = emp.extractDeductionValue("PHILHEALTH", "OTHERS");
            double sss = emp.extractDeductionValue("SSS", "OTHERS");
            double tax = emp.extractDeductionValue("TAX", "TAX");

            if (pagibig>0) setValue("pagibig", pagibig/2);
            if (philhealth>0) setValue("philhealth", philhealth/2);
            if (sss>0) setValue("sss", sss/2);
            if (taxExemption>0) setValue("taxExemption", taxExemption/2);
            if (tax>0) setValue("tax", tax/2);
            
            //put all loan amounts
            double sssLoan = getLoanAmount("SSS");
            double pagibigLoan = getLoanAmount("PAGIBIG");
            double salaryLoan = getLoanAmount("SALARY");
            double otherLoan = getLoanAmount("OTHERS");
            double bankLoan = getLoanAmount("BANK");
            setValue("sssLoan", sssLoan);
            setValue("otherLoan", otherLoan);
            setValue("pagibigLoan", pagibigLoan);
            setValue("salaryLoan", salaryLoan);
            setValue("bankLoan", bankLoan);
        }
        else {
            showError("No basic pay for employee "+emp.toString());
        }

        //below are totals only, will be set upon saving
//        pay.basicPay
//        pay.netPay;
//        pay.deduction
    }

    protected void computePay() {
        Payroll pay = (Payroll) this.getBean();
        if (pay==null) return;
        
        double allpay = pay.basicPay+pay.otPay+pay.nightDiff+pay.holidayPay+pay.m13+pay.m14+pay.m15+pay.m16+pay.m17+pay.m18+pay.m19+pay.m20+pay.m21+pay.m22+pay.m23;
        double allowance = pay.transpoAllowance+pay.mealAllowance+pay.colaAllowance+pay.otherAllowance+pay.overload+pay.substitution+pay.a1+pay.a2+pay.a3+pay.a4+pay.a5;
        double loan = pay.salaryLoan+pay.sssLoan+pay.otherLoan+pay.pagibigLoan+pay.bankLoan+pay.l1+pay.l2+pay.l3+pay.l4+pay.l5;
        double deduction = pay.absent+pay.tardiness+pay.undertime+pay.late+pay.pagibig+pay.philhealth+pay.sss;  //for tax only
        double taxableIncome = allpay - deduction;
        double taxWithheld = pay.tax+pay.m13Tax+pay.m14Tax+pay.m15Tax+pay.m16Tax+pay.m17Tax+pay.m18Tax+pay.m19Tax+pay.m20Tax+pay.m21Tax+pay.m22Tax+pay.m23Tax;

        double gross = allpay+allowance;
        double net = allpay+allowance-deduction-taxWithheld-loan;
        setValue("allowance", allowance);
        setValue("deduction", deduction+loan+taxWithheld);
        setValue("withholdingTax", taxWithheld);
        setValue("netPay", net);
        setValue("taxableIncome", taxableIncome);
        setValue("loan", loan);
        setValue("grossPay", gross);
        setValue("netPay", net);
    }
    
    private EmployeeLoan getEmployeeLoanObj(String loanType) {
        Payroll pay = (Payroll) this.getBean();
        List<EmployeeLoan> lstLoans = pay.list("SELECT a FROM EmployeeLoan a WHERE a.personId="+pay.employeeId);
        for (EmployeeLoan employeeLoan : lstLoans) {
            if (employeeLoan.completed) continue;
            if (loanType.equals(employeeLoan.loanType)) {
                return employeeLoan;
            }
        }
        return null;
    }
    
    private double getLoanAmount(String loanType) {
        EmployeeLoan employeeLoan = getEmployeeLoanObj(loanType);
        if (employeeLoan==null) return 0;
        return employeeLoan.amountPerPayroll;
    }

    private void viewAttendance() {
        Payroll pay = (Payroll) this.getBean();
        PayrollPeriod period = (PayrollPeriod) AbstractIBean.firstRecord("SELECT a FROM PayrollPeriod a WHERE a.seq="+pay.payrollPeriodId);
        List lst = AbstractIBean.list("SELECT a FROM PersonAttendance a WHERE a.personId="+pay.employeeId+" AND a.attendanceDate BETWEEN '"+util.DateUtil.formatDateToSql(period.startDate)+"' AND '"+util.DateUtil.formatDateToSql(period.endDate)+"' ORDER BY a.seq DESC");
        TablePopup.showRecords("Employee Attendance", lst, PersonAttendance.class,
                "attendanceDate", "login", "logout", "late", "attendanceType", "nightDiff", "approvedOvertime", "totalHours");
    }

    private void viewHistory() {
        Payroll pay = (Payroll) this.getBean();
        List lst = AbstractIBean.list("SELECT a FROM Payroll a WHERE a.employeeId="+pay.employeeId+" ORDER BY a.seq DESC");
        TablePopup.showRecords("Employee Payroll History", lst, Payroll.class,
                "grossPay", "netPay", "withholdingTax", "sss", "pagibig", "philhealth",
                "sssLoan", "pagibigLoan", "salaryLoan", "otherLoan");
    }

    private void viewLeave() {
        Payroll pay = (Payroll) this.getBean();
        List lst = AbstractIBean.list("SELECT a FROM EmployeeLeaveApplication a WHERE a.personId="+pay.employeeId+" ORDER BY a.seq DESC");
        TablePopup.showRecords("Employee Leave", lst, EmployeeLeaveApplication.class,
                "filedDate", "startDate", "endDate", "leaveType");
    }
}
