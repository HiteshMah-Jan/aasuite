/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "BankAccountTransactionDetail")
@UITemplate(gridCount = 4, criteriaSearch = {"accountNumber"}, columnSearch = {"accountNumber","debit","credit","reason"})
@Displays({
//        @Display(name="dateInput", type="Calendar"),
        @Display(name="accountNumber", gridFieldWidth=3, width=-1, type = "PopSearch", linktoBean=AccountChart.class),
        @Display(name="debit"),
        @Display(name="credit"),
        @Display(name="reason", gridFieldWidth=3, width=-1)
})
public class BankAccountTransactionDetail extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "bankAccountTransactionId", nullable = false)
    public int bankAccountTransactionId;
    @Column(name = "dateInput", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date dateInput = constants.Constants.useDate; 
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "debit")
    public double debit;
    @Column(name = "credit")
    public double credit;
    @Column(name = "reason")
    public String reason;
    @Column(name = "docCompany")
    public String docCompany;
    @Column(name = "controlNumber")
    public String controlNumber;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountNumber");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankAccountTransactionId() {
        return bankAccountTransactionId;
    }

    public void setBankAccountTransactionId(int bankAccountTransactionId) {
        this.bankAccountTransactionId = bankAccountTransactionId;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getDateInput() {
        return dateInput;
    }

    public void setDateInput(Date dateInput) {
        this.dateInput = dateInput;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public String getDocCompany() {
        return docCompany;
    }

    public void setDocCompany(String docCompany) {
        this.docCompany = docCompany;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
}
