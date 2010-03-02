/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePage;
import util.DataUtil;
import util.NumberToWordConverter;
import bean.admin.AppConfig;
import bean.reference.Department;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "BankAccountTransaction")
@UITemplate(title="Bank Transaction", template=TemplateTabSinglePage.class, gridCount = 4, 
    columnSearch = {"accountNumber", "transactionType", "amount", "postedBy", "transactionDescription"})
@Displays({
    @Display(name="accountNumber", type="PopSearch", linktoBean=BankAccount.class),
    @Display(name="transactionType", type="Combo", modelCombo={"DEPOSIT", "WITHDRAWAL"}),
//        modelCombo={"DEPOSIT", "WITHDRAWAL", "TRANSFER FUNDS", "BILLS PAYMENT"}),
    @Display(name="transactionNumber"),
    @Display(name="amount"),

//    @Display(name="currency"),
//    @Display(name="postedBy"),
    @Display(name="chargeDepartment", type="PopSearch", linktoBean=Department.class),
    @Display(name="transactionDescription", label="Description"),
    
    @Display(name="totalDeposit", type="Label"),
    @Display(name="totalWithdrawal", type="Label"),
    @Display(name="totalBalance", type="Label"),

    @Display(name="checkType", label="Type", type="Combo", 
        modelCombo={"ORDER","BEARER"}),
//        modelCombo={"ORDER","BEARER","COUNTER","CASHIER","TRAVELERS","CERTIFIED","OVERDRAFT","POSTDATED","STALE"}),
    @Display(name="checkNumber", label="Check Number"),
    @Display(name="checkPayee", label="Payee", gridFieldWidth = 3, width=-1),
    @Display(name="checkMemo", label="Memo"),
    @Display(name="checkDate", label="Check Date"),
    @Display(name="checkPrinted", label="Check Printed", type="Label"),
    
    @Display(name = "preparedBy"),
    @Display(name = "approvedBy1"),
    @Display(name = "approvedBy2"),
    @Display(name = "voucherNumber")
})
@template.ActionButtons({
    @template.ActionButton(name="btnPrintCheckVoucher", label="Print Check Voucher", parentOnly=true),
    @template.ActionButton(name="btnPrintCheck", label="Print Check"),
    @template.ActionButton(name="btnCreateGL", label="Post GL", parentOnly=true),
    @template.ActionButton(name="btnViewGL", label="View GL", parentOnly=true)
})
@DisplayGroups({
    @DisplayGroup(title="Voucher Detail", gridCount=4, fields={"checkPayee","preparedBy", "approvedBy1", "approvedBy2", "voucherNumber"}),
    @DisplayGroup(title="Check Printing For Withdrawal", fields={"checkType", "checkNumber", "checkMemo", "checkPrinted", "checkDate"}),
    @DisplayGroup(title="Account History", gridCount=4, fields={"totalDeposit", "totalWithdrawal", "totalBalance"})
})
@ChildRecords({
    @ChildRecord(fieldMapping = {"seq", "recordId"}, entity = BankAccountTransactionParticulars.class, sql = "SELECT a FROM BankAccountTransactionParticulars a WHERE a.recordId=${seq} AND a.form='BANKACCOUNTTRANSACTION'", title = "Particulars"),
    @ChildRecord(title="Sub Transaction", entity = BankAccountTransactionDetail.class, sql = "SELECT a FROM BankAccountTransactionDetail a WHERE a.bankAccountTransactionId=${seq}", fieldMapping={"seq","bankAccountTransactionId"}),
    @ChildRecord(template=ChildTemplateListOnly.class, entity=BankAccountTransaction.class, sql="SELECT a FROM BankAccountTransaction a WHERE a.accountNumber='${accountNumber}'", title="Transactions For Bank Account")
})
@Reports({
    @template.Report(reportFile="BankAccountStatement", reportTitle="Bank Account Statement", reportSql = "${seq}"),
    @template.Report(reportFile="AllPrintedChecks", reportTitle="All Printed Checks", reportSql = "${seq}")
})
public class BankAccountTransaction extends AbstractIBean implements IGL {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "accountNumber", nullable = false)
    public String accountNumber;
    @Column(name = "transactionType", nullable = false)
    public String transactionType;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "totalDeposit")
    public double totalDeposit;
    @Column(name = "totalWithdrawal")
    public double totalWithdrawal;
    @Column(name = "totalBalance")
    public double totalBalance;
    @Column(name = "amountInWord")
    public String amountInWord;
    @Column(name = "postedBy")
    public String postedBy;
    @Column(name = "posted")
    public boolean posted;
    @Column(name = "currency")
    public String currency;
    @Column(name = "postedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date postedDate;
    @Column(name = "transactionDescription")
    public String transactionDescription;
    @Column(name = "chargeDepartment")
    public String chargeDepartment;
    @Column(name = "transactionNumber", nullable = false)
    public String transactionNumber;
    
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

    @Column(name = "voucherNumber")
    public String voucherNumber;
    @Column(name = "preparedBy")
    public String preparedBy;
    @Column(name = "approvedBy1")
    public String approvedBy1;
    @Column(name = "approvedBy2")
    public String approvedBy2;

    @Override
	public void save() {
        String currencyWord = AppConfig.getCurrencyWord();
        String centavoWord = AppConfig.getCentWord();
        amount = DataUtil.getMoneyFormat(amount);
        amountInWord = NumberToWordConverter.convertMoney(amount, currencyWord, centavoWord);
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountNumber", "transactionType");
    }

    public String getChargeDepartment() {
		return chargeDepartment;
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

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(double totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public void setChargeDepartment(String chargeDepartment) {
        this.chargeDepartment = chargeDepartment;
    }

    public String getAmountInWord() {
        return amountInWord;
    }

    public void setAmountInWord(String amountInWord) {
        this.amountInWord = amountInWord;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckMemo() {
        return checkMemo;
    }

    public void setCheckMemo(String checkMemo) {
        this.checkMemo = checkMemo;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckPayee() {
        return checkPayee;
    }

    public void setCheckPayee(String checkPayee) {
        this.checkPayee = checkPayee;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public boolean isCheckPrinted() {
        return checkPrinted;
    }

    public void setCheckPrinted(boolean checkPrinted) {
        this.checkPrinted = checkPrinted;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String extractGLSubType() {
//        return "BANK TRANSACTION";
        return "";
    }

    public boolean isPosted() {
        return posted;
    }

     public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public String extractChargeDepartment() {
        return chargeDepartment;
    }
    
    public boolean hardcodePosting() {
        if (this.posted) {
            Logger.getLogger("global").log(Level.INFO, "GL is already posted for "+this.extractGLType().toUpperCase()+"-"+seq+".");
            return true;
        }
        List<BankAccountTransactionDetail> details = list("SELECT a FROM BankAccountTransactionDetail a WHERE a.bankAccountTransactionId="+seq);
        if (details!=null && details.size()>1 && isBalanced(details)) {
            for (BankAccountTransactionDetail mgl : details) {
                GL gl = new GL();
                gl.accountNumber = mgl.accountNumber;
                gl.debit = mgl.debit;
                gl.credit = mgl.credit;
                gl.reason = mgl.reason;
                gl.accountName = gl.getAccountDescription();
                gl.form = getClass().getSimpleName().toUpperCase();
                gl.recordId = seq;
                gl.subLedger = "BankAccountTransaction";
                gl.dateInput = constants.Constants.useDate;
                gl.save();
            }
            this.posted = true;
            save();
        }
        return true;
    }    
    
    private boolean isBalanced(List<BankAccountTransactionDetail> details) {
        double debit = 0;
        double credit = 0;
        for (BankAccountTransactionDetail gl : details) {
            debit += gl.debit;
            credit += gl.credit;
        }
        return debit>0 && debit==credit;
    }

    public boolean isBalanced() {
        List<BankAccountTransactionDetail> details = list("SELECT a FROM BankAccountTransactionDetail a WHERE a.manualGLId="+seq);
        return isBalanced(details);
    }

    public String extractDefaultFormula() {
        return "";
    }
}
