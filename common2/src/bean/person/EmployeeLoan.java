/*
 * EmployeeLoan.java
 * 
 * Created on Feb 20, 2008, 8:22:32 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.Employee;
import bean.accounting.PaymentLoan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "EmployeeLoan")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"loanDate","loanAmount","loanType","countsToPay","totalPaidCount","totalPaidAmount","loanInterest","completed"})
@Displays({
        @Display(name="loanDate"),
        @Display(name="loanType", type = "Combo", modelCombo={"SSS","SALARY","PAGIBIG","OTHERS"}, width=100),
        @Display(name="completed", type="Label"),

        @Display(name="loanAmount"),
        @Display(name="countsToPay", type = "NumberCombo", startCount=1, endCount=24),
        @Display(name="loanInterest"),

        @Display(name="amountPerPayroll"),
        @Display(name="totalPaidCount"),
        @Display(name="totalPaidAmount"),

        @Display(name="approvedBy", gridFieldWidth = 5, width = -1, type = "PopSearch", linktoBean=Employee.class),
        @Display(name="remarks", gridFieldWidth = 5, width = -1)
})
@template.ActionButtons({
    @template.ActionButton(name="btnMarkComplete", label="Mark Complete", parentOnly=false),
    @template.ActionButton(name="btnRecalculatePayment", label="Recalculate", parentOnly=false)
})
public class EmployeeLoan extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "loanableAmount")
    public double loanableAmount;
    @Column(name = "loanDate")
    @Temporal(TemporalType.DATE)
    public Date loanDate;
    @Column(name = "loanType", nullable = false)
    public String loanType;
    @Column(name = "loanAmount", nullable = false)
    public double loanAmount;
    @Column(name = "amountPerPayroll")
    public double amountPerPayroll;
    @Column(name = "approvedBy", nullable = false)
    public int approvedBy;
    @Column(name = "countsToPay", nullable = false)
    public int countsToPay;
    @Column(name = "totalPaidCount")
    public int totalPaidCount;
    @Column(name = "totalPaidAmount")
    public double totalPaidAmount;
    @Column(name = "loanInterest")
    public double loanInterest;
    @Column(name = "gLId")
    public int gLId;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "completed")
    public boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getAmountPerPayroll() {
        return amountPerPayroll;
    }

    public void setAmountPerPayroll(double amountPerPayroll) {
        this.amountPerPayroll = amountPerPayroll;
    }


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

    @Override
    public String toString() {
        return loanType;
    }

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getCountsToPay() {
        return countsToPay;
    }

    public void setCountsToPay(int countsToPay) {
        this.countsToPay = countsToPay;
    }

    public int getGLId() {
        return gLId;
    }

    public void setGLId(int gLId) {
        this.gLId = gLId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanableAmount() {
        return loanableAmount;
    }

    public void setLoanableAmount(double loanableAmount) {
        this.loanableAmount = loanableAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public int getTotalPaidCount() {
        return totalPaidCount;
    }

    public void setTotalPaidCount(int totalPaidCount) {
        this.totalPaidCount = totalPaidCount;
    }


    @SuppressWarnings("unchecked")
    public List<PaymentLoan> getPayments() {
        return list("SELECT a FROM PaymentLoan a WHERE a.employeeId="+personId+" AND a.employeeLoanId="+seq);
    }

    public PaymentLoan getNextLoanPayment() {
        List<PaymentLoan> payments = getPayments();
        for (PaymentLoan pay : payments) {
            int payrollId = pay.getPayrollId();
            if (payrollId > 0) {
                continue;
            }
            return pay;
        }
        return null;
    }

    public boolean isFinishedLoan() {
        return getNextLoanPayment() == null;
    }
}
