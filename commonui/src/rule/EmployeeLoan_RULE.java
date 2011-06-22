/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.person.EmployeeLoan;
import bean.sales.PaymentLoan;

import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Charliemagne Mark
 */
public class EmployeeLoan_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
        if ("loanAmount".equals(comp.getName()) || "countsToPay".equals(comp.getName()) || "loanInterest".equals(comp.getName())) {
            computeAmountPerPayroll();
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnMarkComplete".equals(comp.getName())) {
            markasComplete();
        }
        else if ("btnRecalculatePayment".equals(comp.getName())) {
            recalculatePayment();
        }
    }

    private void computeAmountPerPayroll() {
        EmployeeLoan loan = (EmployeeLoan) getBean();
        int countsToPay = loan.countsToPay;
        double loanAmount = loan.loanAmount;
        if (countsToPay==0 || loanAmount==0) return;
        
        double loanInterest = loan.loanInterest;
        
        double totalAmount = loanAmount + (loanAmount * loanInterest/100);
        double loanPerPayroll = totalAmount / countsToPay;
        setValue("amountPerPayroll", loanPerPayroll);
    }

    private void markasComplete() {
        recalculatePayment();
        //chk if the totalPaidAmount is equal
        EmployeeLoan loan = (EmployeeLoan) getBean();
        double totalAmount = loan.loanAmount + (loan.loanAmount * loan.loanInterest/100);
        if (totalAmount < loan.totalPaidAmount) {
            if (!showPrompt("Total payment of ",loan.totalPaidAmount," is less than the calculated amount of ",totalAmount,". Would you still like to complete this loan?")) {
                return;
            }
        }
        loan.completed = true;
        loan.save();
        redisplayRecord();
    }

    private void recalculatePayment() {
        //check PaymentLoans
        EmployeeLoan loan = (EmployeeLoan) getBean();
        List<PaymentLoan> lstPayment = loan.getPayments();
        if (lstPayment==null) {
            setValue("totalPaidCount", 0);
            setValue("totalPaidAmount", 0);
        }
        else {
            double d = 0;
            for (PaymentLoan paymentLoan : lstPayment) {
                d += paymentLoan.amount;
            }
            setValue("totalPaidCount", lstPayment.size());
            setValue("totalPaidAmount", d);
        }
    }
}
