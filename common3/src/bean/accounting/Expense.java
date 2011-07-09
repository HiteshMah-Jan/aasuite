/*
 * Expense.java
 * 
 * Created on Feb 12, 2008, 11:03:04 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.extension.AccountPayableExt;
import bean.reference.Department;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import service.util.ChartBean;
import template.Display;
import template.DisplayGroup;
import template.Displays;
import template.UITemplate;
import template.Reports;
import template.ActionButton;
import template.ActionButtons;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DateUtil;
import template.ChildRecord;
import template.ChildRecords;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "Expense")
@UITemplate(template = TemplateTabSinglePage.class,
gridCount = 4,
columnSearch = {"expenseDate", "amount","payee","voucherNumber"},
criteriaSearch = {"expenseDate", "expenseType", "amount","payee","approvedBy1","voucherNumber"},
showChart = false)
@Displays({
    @Display(name="expenseType", gridFieldWidth=3, width=-1, type = "PopSearch", linktoBean=AccountChart.class),
    @Display(name = "expenseDate"),
    @Display(name = "chargeDepartment", type="PopSearch", linktoBean=Department.class),
    @Display(name = "amount"),
    @Display(name = "amountPaid"),
    @Display(name = "reason", gridFieldWidth = 5, width = -1),
    @Display(name = "payee", gridFieldWidth = 3, width=-1),
    @Display(name = "preparedBy"),
    @Display(name = "voucherNumber"),
    @Display(name = "approvedBy1"),
    @Display(name = "approvedBy2"),
    
    @Display(name="accountNumber", type="PopSearch", linktoBean=BankAccount.class),
    @Display(name="checkNumber", label="Check Number"),
    @Display(name="checkPayee", label="Payee", gridFieldWidth = 3, width=-1),
    @Display(name="checkMemo", label="Memo"),
    @Display(name="checkDate", label="Check Date"),
    @Display(name="checkPrinted", label="Check Printed", type="Label")
})
@Reports({
    @template.Report(reportFile = "Expense", reportTitle = "Expenses", reportSql = "${seq}"),
    @template.Report(reportFile = "ExpenseByDate", reportTitle = "Daily Expenses", reportSql = "${seq}")
})
@template.DisplayGroups({
    @DisplayGroup(gridCount=4, title="Voucher Detail", fields={"payee", "preparedBy", "approvedBy1", "approvedBy2", "voucherNumber"}),
    @DisplayGroup(gridCount=4, title="Check Details", fields={"accountNumber", "checkType", "checkNumber", "checkMemo", "checkPrinted", "checkDate"})
})
@ActionButtons({
    @ActionButton(name="btnPrintCashVoucher", label="Print Voucher", parentOnly=true),
    @ActionButton(label = "Post GL", name = "btnCreateGL"),
    @ActionButton(label = "View GL", name = "btnViewGL")
})
@ChildRecords(value = {
    @ChildRecord(fieldMapping = {"seq", "recordId"}, entity = ExpenseParticulars.class, sql = "SELECT a FROM ExpenseParticulars a WHERE a.recordId=${seq} AND a.form='EXPENSE'", title = "Particulars")
//    @ChildRecord(canSave = false, fieldMapping = {"seq", "expenseId"}, entity = AccountPayableExt.class, sql = "SELECT a FROM AccountPayable a WHERE a.expenseId=${seq}", title = "Accounts Payable"),
//    @ChildRecord(canSave = false, fieldMapping = {"seq", "expenseId"}, entity = AccountPayableExt.class, sql = "SELECT a FROM AccountPayable a WHERE a.paid=true AND a.expenseId=${seq}", title = "Accounts Payable Paid")
// @ChildRecord(fieldMapping={"seq", "expenseId"}, entity=AccountPayableExt.class, sql="SELECT a FROM AccountPayableExt a WHERE a.expenseId=${seq}", title="AccountPayable")
// @ChildRecord(entity=PaymentEnrollment.class, sql="SELECT a FROM PaymentEnrollment a WHERE a.recordId=${seq}", title="Payments")
})
public class Expense extends AbstractIBean implements Serializable, IGL {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "expenseDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date expenseDate;
    @Column(name = "accountType")
    public String accountType;
    @Column(name = "chargeDepartment", nullable = false)
    public String chargeDepartment;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "amountPaid", nullable = false)
    public double amountPaid;
    @Column(name = "reason")
    public String reason;
    @Column(name = "posted")
    public boolean posted;
    @Column(name = "expenseType", nullable = false)
    public String expenseType;
    @Column(name = "payee")
    public String payee;
    @Column(name = "voucherNumber")
    public String voucherNumber;
    @Column(name = "preparedBy")
    public String preparedBy;
    @Column(name = "approvedBy1")
    public String approvedBy1;
    @Column(name = "approvedBy2")
    public String approvedBy2;

    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "checkPayee")
    public String checkPayee;
    @Column(name = "checkMemo")
    public String checkMemo;
    @Column(name = "checkType")
    public String checkType;
    @Column(name = "checkDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date checkDate;
    @Column(name = "checkNumber")
    public String checkNumber;
    @Column(name = "checkPrinted")
    public boolean checkPrinted;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountType", "reason", "voucherNumber", "checkNumber");
    }

    public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckPayee() {
		return checkPayee;
	}

	public void setCheckPayee(String checkPayee) {
		this.checkPayee = checkPayee;
	}

	public String getCheckMemo() {
		return checkMemo;
	}

	public void setCheckMemo(String checkMemo) {
		this.checkMemo = checkMemo;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public boolean isCheckPrinted() {
		return checkPrinted;
	}

	public void setCheckPrinted(boolean checkPrinted) {
		this.checkPrinted = checkPrinted;
	}

	public String getApprovedBy1() {
        return approvedBy1;
    }

    public void setApprovedBy1(String approvedBy1) {
        this.approvedBy1 = approvedBy1;
    }

    public String getApprovedBy2() {
        return approvedBy2;
    }

    public void setApprovedBy2(String approvedBy2) {
        this.approvedBy2 = approvedBy2;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getChargeDepartment() {
        return chargeDepartment;
    }

    public void setChargeDepartment(String chargeDepartment) {
        this.chargeDepartment = chargeDepartment;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
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

    @Override
    public String toString() {
        return accountType;
    }

    public String extractGLSubType() {
        return "EXPENSE";
    }

    public String getAccountName() {
        AccountChart chart = (AccountChart) AbstractIBean.extractObject(AccountChart.class.getSimpleName(), expenseType);
        if (chart==null) return "";
        return chart.accountName;
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativeBarInstance(this, "Expense", "SELECT",DateUtil.getSQLYear("a.expenseDate"),", a.chargeDepartment, SUM(a.amount) FROM Expense a GROUP BY a.chargeDepartment,",DateUtil.getSQLYear("a.expenseDate")));
        return vec;
    }
    
    public boolean hardcodePosting() {
        return false;
    }

    public String extractDefaultFormula() {
        return BeanUtil.concat(
                "GL.debit EXPENSE, now, EXPENSE.expenseType+\"\", EXPENSE.amount, EXPENSE.getAccountName();",
                "\nGL.credit EXPENSE, now, \"103\", EXPENSE.amountPaid, \"CASH / CASH ON HAND\";",
                "\nGL.credit EXPENSE, now, \"201\", EXPENSE.amount - EXPENSE.amountPaid, \"ACCOUNTS PAYABLE\";");
    }

    public String extractChargeDepartment() {
        return chargeDepartment;
    }
}
