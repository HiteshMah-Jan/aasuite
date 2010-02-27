/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.Employee;
import bean.accounting.IGL;
import bean.accounting.Invoice;
import bean.accounting.Payment;
import bean.accounting.PaymentLoan;
import bean.accounting.Payroll;
import bean.accounting.PayrollPeriod;
import java.util.List;

/**
 *
 * @author Entokwaa
 */
public interface IAccounting {
//    boolean isGLCreated(IGL gl);
    void showGL(IGL gl);
    List getGL(IGL gl);

//    void postGL(IGL gl);
    void createAP(IGL gl, double amount);
    void createAR(IGL gl, double amount);
//    Invoice createInvoice(Payment pay);

    List<Employee> getAllEmployee(String contractType);
    
    Payroll getNewPayroll(Employee emp);
    List<Payroll> getNewPayroll(PayrollPeriod period, List<Employee> emp);
    PayrollPeriod calculatePayroll(PayrollPeriod period);
    void savePayroll(PayrollPeriod period);

    void payLoan(PaymentLoan pay);
}