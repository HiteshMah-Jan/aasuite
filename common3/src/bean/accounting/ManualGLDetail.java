/*
 * GeneralLedger.java
 *
 * Created on Nov 24, 2007, 8:44:41 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import bean.financial.AccountChart;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ManualGLDetail")
@UITemplate(gridCount = 4, criteriaSearch = {"accountNumber"}, columnSearch = {"accountNumber","debit","credit","reason"})
@Displays({
//        @Display(name="dateInput", type="Calendar"),
        @Display(name="accountNumber", gridFieldWidth=3, width=-1, type = "PopSearch", linktoBean=AccountChart.class),
        @Display(name="debit"),
        @Display(name="credit"),
        @Display(name="reason", gridFieldWidth=3, width=-1)
})
public class ManualGLDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "manualGLId", nullable = false)
    public int manualGLId;
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
        return buildSearch(criteria);
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDateInput() {
        return dateInput;
    }

    public void setDateInput(Date dateInput) {
        this.dateInput = dateInput;
    }

    public String getDocCompany() {
        return docCompany;
    }

    public void setDocCompany(String docCompany) {
        this.docCompany = docCompany;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public int getManualGLId() {
        return manualGLId;
    }

    public void setManualGLId(int manualGLId) {
        this.manualGLId = manualGLId;
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
