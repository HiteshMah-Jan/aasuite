package bean.banking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "Deposit")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="consideredUntilDate"),
	@Display(name="depositCurrency"),
	@Display(name="bankAccount"),
	@Display(name="series"),
	@Display(name="depositDate"),
	@Display(name="bank"),
	@Display(name="branch"),
	@Display(name="account"),
	@Display(name="bankReference"),
	@Display(name="payer"),
	@Display(name="journalRemarks"),
	@Display(name="transactionNo"),
	@Display(name="totalAmount"),
	@Display(name="cashAmount"),
	@Display(name="cashBalance"),
	@Display(name="cashGLAccount")
})
public class Deposit extends AbstractIBean {
	public static void main(String[] args) {
		view(Deposit.class);
	}
	
	@Id
	public Integer seq;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date consideredUntilDate;
	public String depositCurrency;
	public String bankAccount;
	public String series;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date depositDate;
	public String bank;
	public String branch;
	public String account;
	public String bankReference;
	public String payer;
	public String journalRemarks;
	public String transactionNo;
	public double totalAmount;
	
	public double cashAmount;
	public double cashBalance;
	public String cashGLAccount;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getConsideredUntilDate() {
		return consideredUntilDate;
	}

	public void setConsideredUntilDate(Date consideredUntilDate) {
		this.consideredUntilDate = consideredUntilDate;
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

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
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

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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

	public String getCashGLAccount() {
		return cashGLAccount;
	}

	public void setCashGLAccount(String cashGLAccount) {
		this.cashGLAccount = cashGLAccount;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
