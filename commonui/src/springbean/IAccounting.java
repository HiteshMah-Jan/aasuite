/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.accounting.IGL;
import bean.accounting.Invoice;
import bean.hr.Employee;
import bean.payroll.Payroll;
import bean.sales.Payment;
import bean.sales.PaymentLoan;

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

    void payLoan(PaymentLoan pay);
}