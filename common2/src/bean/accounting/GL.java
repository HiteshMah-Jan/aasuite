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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import service.util.AbstractIBean;
import service.util.ChartBean;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateSearchOnly;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import util.DateUtil;
/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "GL")

@UITemplate( 
        template=TemplateSearchOnly.class,
        columnSearch = {"dateInput","accountNumber","debit","credit","reason"},
        criteriaSearch = {"accountNumber"}, 
        gridCount = 4, 
        sumFooter="2,3",
        orderBy="a.dateInput DESC, a.form, a.recordId, a.seq"
)
@Displays({
        @Display(name="dateInput"),
        @Display(name="accountNumber", type = "PopSearch", linktoBean=AccountChart.class),
        @Display(name="debit"),
        @Display(name="credit"),
        @Display(name="docCompany", gridFieldWidth=3, width=-1)
})
@Reports({
    @template.Report(reportFile="GLByDate", reportTitle="GL Posting", reportSql="${seq}"),
    @template.Report(reportFile="TrialBalance", reportTitle="Trial Balance", reportSql="${seq}"),
    @template.Report(reportFile="Receivable", reportTitle="Account Receivables", reportSql="${seq}")
}) 

public class GL extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "dateInput", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date dateInput;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "accountName")
    public String accountName;
    @Column(name = "docCompany")
    public String docCompany;
    @Column(name = "chargeDepartment")
    public String chargeDepartment;
    @Column(name = "docType")
    public String docType;
    @Column(name = "exchangeRate")
    public double exchangeRate;
    @Column(name = "debit")
    public double debit;
    @Column(name = "credit")
    public double credit;
    @Column(name = "subLedger")
    public String subLedger;
    @Column(name = "subLedgerType")
    public String subLedgerType;
    @Column(name = "controlNumber")
    public String controlNumber;
    @Column(name = "recordId", nullable = false)
    public int recordId;
    @Column(name = "form", length = 50)
    public String form;
    @Column(name = "reason")
    public String reason;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountNumber");
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getChargeDepartment() {
        return chargeDepartment;
    }

    public void setChargeDepartment(String chargeDepartment) {
        this.chargeDepartment = chargeDepartment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSubLedger() {
        return subLedger;
    }

    public void setSubLedger(String subLedger) {
        this.subLedger = subLedger;
    }

    public String getSubLedgerType() {
        return subLedgerType;
    }

    public void setSubLedgerType(String subLedgerType) {
        this.subLedgerType = subLedgerType;
    }

    public String getAccountDescription() {
        AccountChart chart = (AccountChart) AbstractIBean.extractObject(AccountChart.class.getSimpleName(), accountNumber);
        if (chart==null) return "NOT FOUND";
        return chart.toString();
    }
    
    public static void main(String[] args) {
        view(GL.class);
    }
    
    public static void credit(Object... obj) {
        GL gl = new GL();
        gl.credit = (Double) obj[3];
        if (gl.credit==0) {
            Logger.getLogger("global").log(Level.WARNING, "Posting 0 credit amount.");
            return;
        }
        defaultPosting(gl, obj);
    }

    public static void debit(Object... obj) {
        GL gl = new GL();
        gl.debit = (Double) obj[3];
        if (gl.debit==0) {
            Logger.getLogger("global").log(Level.WARNING, "Posting 0 debit amount.");
            return;
        }
        defaultPosting(gl, obj);
    }
    
    private static void defaultPosting(GL gl, Object... obj) {
        AbstractIBean bean = (AbstractIBean) obj[0];
        gl.dateInput = (Date) obj[1];
        gl.accountNumber = (String) obj[2];
        gl.reason = (String) obj[4];
        gl.accountName = gl.getAccountDescription();
        if (bean instanceof IGL) {
            gl.chargeDepartment = ((IGL) bean).extractChargeDepartment();
        }
        if (obj.length>6)   gl.docCompany = (String) obj[5];
        if (obj.length>7)   gl.exchangeRate = (Double) obj[6];
        if (obj.length>8)   gl.subLedger = (String) obj[7];
        if (obj.length>9)   gl.subLedgerType = (String) obj[8];
        if (obj.length>10)  gl.controlNumber = (String) obj[9];

        gl.form = bean.getClass().getSimpleName().toUpperCase();
        gl.recordId = bean.intKeyVal();
        gl.save();
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativeBarInstance(this, "General Ledger","SELECT" +DateUtil.getSQLYear("a.dateInput")+", a.accountNumber, SUM(a.debit-a.credit) FROM GL a GROUP BY "+DateUtil.getSQLYear("a.dateInput")+",a.accountNumber"));
        vec.add(ChartBean.getNativePieInstance(this, "Cash Posting Per Month","SELECT" +DateUtil.getSQLMonthName("a.dateInput")+", SUM(a.debit) FROM GL a WHERE a.accountNumber='1001' GROUP BY "+DateUtil.getSQLMonthName("a.dateInput")));
        vec.add(ChartBean.getNativePieInstance(this, "Expense Posting Per Month","SELECT" +DateUtil.getSQLMonthName("a.dateInput")+", SUM(a.debit) FROM GL a, AccountChart b WHERE a.accountNumber=b.accountNumber AND b.category='EXPENSE' GROUP BY "+DateUtil.getSQLMonthName("a.dateInput")));
        vec.add(ChartBean.getNativePieInstance(this, "Expense Posting Per Department","SELECT a.chargeDepartment, SUM(a.debit) FROM GL a, AccountChart b WHERE a.accountNumber=b.accountNumber AND b.category='EXPENSE' GROUP BY chargeDepartment"));
        return vec;
    }
}
