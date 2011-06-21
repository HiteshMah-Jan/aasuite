/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.GLPostingScript;
import bean.hr.Employee;
import bean.payroll.Payroll;
import bean.payroll.PayrollPeriod;
import bean.person.EmployeeLoan;
import java.util.List;
import javax.swing.JComponent;

import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class PayrollPeriod_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreatePayroll".equals(comp.getName())) {
            createPayroll();
        }
        else if ("btnPostGL".equals(comp.getName())) {
            postGL();
        }
        else if ("btnViewGL".equals(comp.getName())) {
            viewGL();
        }
        else if ("btnGeneratePayroll".equals(comp.getName())) {
            generatePayroll();
        }
    }

    protected void createPayroll() {
//        PayrollAction.showPayrollWizard();
        PayrollPeriod p = (PayrollPeriod) this.getBean();
        List<Payroll> lstPay = p.getListPayroll();
        p.absent = getTotal(lstPay, "absent");
        p.allowance = getTotal(lstPay, "allowance");
        p.basicPay = getTotal(lstPay, "basicPay");
        p.colaAllowance = getTotal(lstPay, "colaAllowance");
        p.deduction = getTotal(lstPay, "deduction");
        p.grossPay = getTotal(lstPay, "grossPay");
        p.holidayPay = getTotal(lstPay, "holidayPay");
        p.late = getTotal(lstPay, "late");
        p.loan = getTotal(lstPay, "loan");
        p.mealAllowance = getTotal(lstPay, "mealAllowance");
        p.netPay = getTotal(lstPay, "netPay");
        p.nightDiff = getTotal(lstPay, "nightDiff");
        p.otPay = getTotal(lstPay, "otPay");
        p.otherAllowance = getTotal(lstPay, "otherAllowance");
        p.pagibig = getTotal(lstPay, "pagibig");
        p.philhealth = getTotal(lstPay, "philhealth");
        p.sss = getTotal(lstPay, "sss");
        p.tardiness = getTotal(lstPay, "tardiness");
        p.tax = getTotal(lstPay, "tax");
        p.taxExemption = getTotal(lstPay, "taxExemption");
        p.taxReturn = getTotal(lstPay, "taxReturn");
        p.taxableIncome = getTotal(lstPay, "taxableIncome");
        p.transpoAllowance = getTotal(lstPay, "transpoAllowance");
        p.undertime = getTotal(lstPay, "undertime");
        p.withholdingTax = getTotal(lstPay, "withholdingTax");
        
        p.m13 = getTotal(lstPay, "m13");
        p.m13Tax = getTotal(lstPay, "m13Tax");
        p.m14 = getTotal(lstPay, "m14");
        p.m14Tax = getTotal(lstPay, "m14Tax");
        p.m15 = getTotal(lstPay, "m15");
        p.m15Tax = getTotal(lstPay, "m15Tax");
        p.m16 = getTotal(lstPay, "m16");
        p.m16Tax = getTotal(lstPay, "m16Tax");
        p.m17 = getTotal(lstPay, "m17");
        p.m17Tax = getTotal(lstPay, "m17Tax");
        p.m18 = getTotal(lstPay, "m18");
        p.m18Tax = getTotal(lstPay, "m18Tax");
        p.m19 = getTotal(lstPay, "m19");
        p.m19Tax = getTotal(lstPay, "m19Tax");
        p.m20 = getTotal(lstPay, "m20");
        p.m20Tax = getTotal(lstPay, "m20Tax");
        p.m21 = getTotal(lstPay, "m21");
        p.m21Tax = getTotal(lstPay, "m21Tax");
        p.m22 = getTotal(lstPay, "m22");
        p.m22Tax = getTotal(lstPay, "m22Tax");
        p.m23 = getTotal(lstPay, "m23");
        p.m23Tax = getTotal(lstPay, "m23Tax");

        p.overload = getTotal(lstPay, "overload");
        p.substitution = getTotal(lstPay, "substitution");
        p.sssLoan = getTotal(lstPay, "sssLoan");
        p.salaryLoan = getTotal(lstPay, "salaryLoan");
        p.pagibigLoan = getTotal(lstPay, "pagibigLoan");
        p.otherLoan = getTotal(lstPay, "otherLoan");
        p.teachersDayBonus = getTotal(lstPay, "teachersDayBonus");
        p.chineseNewYearBonus = getTotal(lstPay, "chineseNewYearBonus");
        p.save();
    }

    private void generatePayroll() {
        PayrollPeriod p = (PayrollPeriod) this.getBean();
        List<Payroll> lstp = DBClient.getList(BeanUtil.concat("SELECT a FROM Payroll a WHERE a.payrollPeriodId=",p.seq));
        List<Employee> lst = DBClient.getList("SELECT a FROM Employee a");
        for (Employee e : lst) {
            if (alreadyHasPayroll(e, lstp)) continue;
            if (e.isActive) {
                //create payroll here
                createPayroll(e);
            }
        }
    }
    
    private void createPayroll(Employee e) {
        PayrollPeriod p = (PayrollPeriod) this.getBean();
        Payroll pay = new Payroll();
        pay.employeeId = e.personId;
        pay.payrollPeriodId = p.seq;

        Employee emp = pay.extractEmployee();
        pay.jobType = BeanUtil.concat(emp.position);
        pay.employeeType = emp.position;
        pay.department = emp.department;
        pay.employeeStatus = emp.status;
        if (emp.hiredDate!=null) pay.hireDate = emp.hiredDate;
        pay.totalDependent = emp.extractDependentCount();
        if (emp.basicPay>0) {
            pay.basicPay = emp.basicPay/2;   //this is bi monthly
            double taxExemption = emp.extractBenefitValue(emp.status, "TAX EXEMPTION");
            if (emp.monthlyAllowance>0) pay.allowance = emp.monthlyAllowance/2;
            if (emp.monthlyLoan>0) pay.loan = emp.monthlyLoan/2;
            emp.basicPay = pay.basicPay;
            double pagibig = emp.extractDeductionValue("PAGIBIG", "OTHERS");
            double philhealth = emp.extractDeductionValue("PHILHEALTH", "OTHERS");
            double sss = emp.extractDeductionValue("SSS", "OTHERS");
            double tax = emp.extractDeductionValue(emp.status, emp.status);

            if (pagibig>0) pay.pagibig = DataUtil.getMoneyFormat(pagibig);
            if (philhealth>0) pay.philhealth = DataUtil.getMoneyFormat(philhealth);
            if (sss>0) pay.sss = DataUtil.getMoneyFormat(sss);
            if (taxExemption>0) pay.taxExemption = DataUtil.getMoneyFormat(taxExemption);
            if (tax>0) pay.tax = DataUtil.getMoneyFormat(tax);
            
            //put all loan amounts
            double sssLoan = getLoanAmount("SSS", pay);
            double pagibigLoan = getLoanAmount("PAGIBIG", pay);
            double salaryLoan = getLoanAmount("SALARY", pay);
            double otherLoan = getLoanAmount("OTHERS", pay);
            pay.sssLoan = DataUtil.getMoneyFormat(sssLoan);
            pay.otherLoan = DataUtil.getMoneyFormat(otherLoan);
            pay.pagibigLoan = DataUtil.getMoneyFormat(pagibigLoan);
            pay.salaryLoan = DataUtil.getMoneyFormat(salaryLoan);
        }
        else {
            showErrorMessageToScreen("No basic pay for employee ",emp.toString());
        }
        pay.save();
        //below are totals only, will be set upon saving
//        pay.basicPay
//        pay.netPay;
//        pay.deduction
    }

    private EmployeeLoan getEmployeeLoanObj(String loanType, Payroll pay) {
        List<EmployeeLoan> lstLoans = pay.list("SELECT a FROM EmployeeLoan a WHERE a.personId=",pay.employeeId);
        for (EmployeeLoan employeeLoan : lstLoans) {
            if (employeeLoan.completed) continue;
            if (loanType.equals(employeeLoan.loanType)) {
                return employeeLoan;
            }
        }
        return null;
    }
    
    private double getLoanAmount(String loanType, Payroll pay) {
        EmployeeLoan employeeLoan = getEmployeeLoanObj(loanType, pay);
        if (employeeLoan==null) return 0;
        return employeeLoan.amountPerPayroll;
    }

    private boolean alreadyHasPayroll(Employee e, List<Payroll> lst) {
        for (Payroll payroll : lst) {
            if (e.personId==payroll.employeeId) return true;
        }
        return false;
    }

    private double getTotal(List<Payroll> lst, String field) {
        double d = 0;
        for (Payroll payroll : lst) {
            d += (Double) util.BeanUtil.getPropertyValue(payroll, field);
        }
        return d;
    }
    
    protected void postGL() {
        PayrollPeriod p = (PayrollPeriod) this.getBean();
        if (p.isPosted()) {
            PanelUtil.showErrorMessageToScreen("Payroll already posted in the past.");
            return;
        }
        PanelUtil.showMessage(null, "This may take a few minutes.");
        List<Payroll> pays = p.getListPayroll();
        if (pays==null || pays.size()==0) {
            PanelUtil.showErrorMessageToScreen("Payroll Period does not have payroll entries, this payroll period cannot be posted.");
            return;
        }
        GLPostingScript.post(p);
    }

    private void viewGL() {
        PayrollPeriod p = (PayrollPeriod) this.getBean();
        GLPostingScript.showGL(p);
    }
}
