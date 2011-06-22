/*
 * LoanPayment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import bean.person.EmployeeLoan;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "Payment")
@DiscriminatorValue(value = "LOAN")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"loanType", "amount", "paymentDate", "payrollId"})
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${paymentId}", title="Payment Detail"),
    @ChildRecord(entity=PaymentPartialDetail.class, sql="SELECT a FROM PartialPaymentDetail a WHERE a.paymentId=${paymentId}", title="Partial Payment Detail")
})
@Displays({
        @Display(name="employeeId"),
        @Display(name="payrollId"),
        @Display(name="paymentDate"),
        @Display(name="amount")
})
public class PaymentLoan extends Payment implements Serializable {
    @Column(name = "employeeLoanId")
    public int employeeLoanId;
    @Column(name = "employeeId")
    public int employeeId;
    @Column(name = "payrollId")
    public int payrollId;

    public String getLoanType() {
        bean.person.EmployeeLoan loanType = (EmployeeLoan) AbstractIBean.firstRecord("SELECT a FROM EmployeeLoan a WHERE a.seq=",employeeLoanId);
        if (loanType==null) return "";
        return loanType.toString();
    }
    
    public int getEmployeeLoanId() {
        return employeeLoanId;
    }

    public void setEmployeeLoanId(int employeeLoanId) {
        this.employeeLoanId = employeeLoanId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }
}
