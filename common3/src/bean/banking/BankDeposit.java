package bean.banking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;

@Entity
@Table(name = "BankDeposit")
public class BankDeposit extends AbstractIBean {
	@Id
	public Integer seq;
    @Temporal(value = TemporalType.DATE)
	public Date considerDate;
	public String depositCurrency;
	public String bankAccount;
    @Temporal(value = TemporalType.DATE)
	public Date depositDate;
	public String bank;
	public String branch;
	public String account;
	public String bankReference;
	public String payer;
	public String journalRemarks;
	public String transactionNumber;
	public boolean reconcileAmountsAfterDeposit;

	public String glCashAccount;
	public double cashAmount;
	public double cashBalance;
	public double totalCheckAmount;
	public double totalCreditAmount;

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getConsiderDate() {
		return considerDate;
	}

	public void setConsiderDate(Date considerDate) {
		this.considerDate = considerDate;
	}

	public String getDepositCurrency() {
		return depositCurrency;
	}

	public void setDepositCurrency(String depositCurrency) {
		this.depositCurrency = depositCurrency;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankReference() {
		return bankReference;
	}

	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getJournalRemarks() {
		return journalRemarks;
	}

	public void setJournalRemarks(String journalRemarks) {
		this.journalRemarks = journalRemarks;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public boolean isReconcileAmountsAfterDeposit() {
		return reconcileAmountsAfterDeposit;
	}

	public void setReconcileAmountsAfterDeposit(boolean reconcileAmountsAfterDeposit) {
		this.reconcileAmountsAfterDeposit = reconcileAmountsAfterDeposit;
	}

	public String getGlCashAccount() {
		return glCashAccount;
	}

	public void setGlCashAccount(String glCashAccount) {
		this.glCashAccount = glCashAccount;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public double getTotalCheckAmount() {
		return totalCheckAmount;
	}

	public void setTotalCheckAmount(double totalCheckAmount) {
		this.totalCheckAmount = totalCheckAmount;
	}

	public double getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public void setTotalCreditAmount(double totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

}
