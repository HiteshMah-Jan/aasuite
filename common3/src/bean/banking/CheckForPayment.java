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
@Table(name = "CheckForPayment")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="toOrderOf"),
	@Display(name="payTo"),
	@Display(name="creditGlAccount"),
	@Display(name="referenceId"),
	@Display(name="postingDate"),
	@Display(name="createJournalEntry"),
	@Display(name="journalRemarks"),
	@Display(name="signature"),
	@Display(name="total"),
	@Display(name="amountDue"),
	@Display(name="dueDate"),
	@Display(name="endorse"),
	@Display(name="country"),
	@Display(name="bank"),
	@Display(name="accountNumber"),
	@Display(name="branch"),
	@Display(name="checkNo"),
	@Display(name="parentBean"),
	@Display(name="changeSupport"),
	@Display(name="dummyField"),
	@Display(name="myNode"),
	@Display(name="newCache"),
	@Display(name="cacheMap"),
	@Display(name="includeSearch"),
	@Display(name="showImages"),
	@Display(name="showFile"),
	@Display(name="showSubrecords"),
	@Display(name="showCharts")
})

public class CheckForPayment extends AbstractIBean {
	public static void main(String[] args) {
		view(CheckForPayment.class);
	}
	
	@Id
	public Integer seq;
	public String toOrderOf;
	public String payTo;
	public String creditGlAccount;
	public int referenceId;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date postingDate;
	public boolean createJournalEntry;
	public String journalRemarks;
	public String signature;
	public double total;
	public double amountDue;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date dueDate;
	public String endorse;
	public String country;
	public String bank;
	public String accountNumber;
	public String branch;
	public String checkNo;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getToOrderOf() {
		return toOrderOf;
	}

	public void setToOrderOf(String toOrderOf) {
		this.toOrderOf = toOrderOf;
	}

	public String getPayTo() {
		return payTo;
	}

	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}

	public String getCreditGlAccount() {
		return creditGlAccount;
	}

	public void setCreditGlAccount(String creditGlAccount) {
		this.creditGlAccount = creditGlAccount;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public boolean isCreateJournalEntry() {
		return createJournalEntry;
	}

	public void setCreateJournalEntry(boolean createJournalEntry) {
		this.createJournalEntry = createJournalEntry;
	}

	public String getJournalRemarks() {
		return journalRemarks;
	}

	public void setJournalRemarks(String journalRemarks) {
		this.journalRemarks = journalRemarks;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getEndorse() {
		return endorse;
	}

	public void setEndorse(String endorse) {
		this.endorse = endorse;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
