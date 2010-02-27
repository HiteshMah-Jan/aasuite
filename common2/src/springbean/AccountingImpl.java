/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.Employee;
import bean.accounting.GL;
import bean.accounting.GLPostingScript;
import bean.accounting.IGL;
import bean.accounting.PaymentLoan;
import bean.accounting.Payroll;
import bean.accounting.PayrollBreakdown;
import bean.accounting.PayrollPeriod;
import java.util.ArrayList;
import java.util.List;
import service.util.AbstractIBean;
import template.screen.TablePopup;

/**
 *
 * @author Entokwaa
 */
public class AccountingImpl implements IAccounting {

    public List getGL(IGL gl) {
        List lst = AbstractIBean.list(util.BeanUtil.concat("SELECT a FROM GL a WHERE a.form LIKE '",gl.extractGLType().toUpperCase(),"%' AND a.recordId=",gl.extractGLRecordId()));
        return lst;
    }

    public void payLoan(PaymentLoan pay) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Employee> getAllEmployee(String contractType) {
        return AbstractIBean.list("SELECT a FROM Employee a WHERE a.contractType='"+contractType+"'");
    }

    public Payroll getNewPayroll(Employee emp) {
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(emp.personId);
        payroll.setDepartment(emp.department);
        payroll.setEmployeeStatus(emp.status);
        payroll.setEmployeeType(emp.position);
        payroll.setJobType(emp.personType);
        payroll.setBasicPay(emp.basicPay);
        payroll.setHireDate(emp.hiredDate);
        IProcess proc = IProcess.Process.getInstance();
        int totalAbsent = proc.getAbsent(emp).size();
        int totalPresent = proc.getAttendance(emp).size();
        payroll.totalDays = totalPresent;
//        payroll.totalAbsent = totalAbsent;
//        payroll.setOtherDeduction(0);
//        payroll.setSalariesWages(emp.basicPay);
//        payroll.setTax(0);
        payroll.extractBreakdownList();
        payroll.recompute();
        return payroll;
    }

    public List<Payroll> getNewPayroll(PayrollPeriod period, List<Employee> emps) {
        List<Payroll> pays = new ArrayList<Payroll>();
        for (Employee emp : emps) {
            pays.add(getNewPayroll(emp));
        }
        period.listPayroll = pays;
        return pays;
    }

    public PayrollPeriod calculatePayroll(PayrollPeriod period) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void savePayroll(PayrollPeriod period) {
        List<Payroll> list = period.listPayroll;
        period.save();
        if (list!=null) {
            for (Payroll payroll : list) {
                payroll.payrollPeriodId = period.seq;
                List<PayrollBreakdown> lstBreakdown = payroll.extractBreakdownList();
                payroll.save();
                if (lstBreakdown!=null) {
                    for (PayrollBreakdown payrollBreakdown : lstBreakdown) {
                        payrollBreakdown.payrollId = payroll.seq;
                        payrollBreakdown.save();
                    }
                }
            }
        }
    }

    public void createAP(IGL gl, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void createAR(IGL gl, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showGL(IGL gl) {
        GLPostingScript.post(gl);
        List lst = getGL(gl);
        TablePopup.showRecords("GL Entries", lst, GL.class, "dateInput", "accountDescription", "reason", "debit", "credit");
    }
}
