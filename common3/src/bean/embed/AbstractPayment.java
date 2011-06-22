package bean.embed;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AbstractPayment implements Serializable {
	public String partnerCode;
	public String partnerName;

	//TODO: the 3 fields will be distributed.
	public String billTo;	//link to address
	public String payTo;	//link to address
	public String payToBank;//link to bank
	public String contactPerson;
	public String project;
    @Temporal(value = TemporalType.DATE)
	public Date postingDate;
    @Temporal(value = TemporalType.DATE)
	public Date dueDate;
    @Temporal(value = TemporalType.DATE)
	public Date documentDate;
	public String referenceNumber;
	public String transactionNumber;
	public String remarks;
	public String journalRemarks;
	public boolean paymentOnAccount;
	public double paymentOnAccountAmount;
	public double totalAmountDue;
	public double openBalance;
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getPayTo() {
		return payTo;
	}
	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}
	public String getPayToBank() {
		return payToBank;
	}
	public void setPayToBank(String payToBank) {
		this.payToBank = payToBank;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getJournalRemarks() {
		return journalRemarks;
	}
	public void setJournalRemarks(String journalRemarks) {
		this.journalRemarks = journalRemarks;
	}
	public boolean isPaymentOnAccount() {
		return paymentOnAccount;
	}
	public void setPaymentOnAccount(boolean paymentOnAccount) {
		this.paymentOnAccount = paymentOnAccount;
	}
	public double getPaymentOnAccountAmount() {
		return paymentOnAccountAmount;
	}
	public void setPaymentOnAccountAmount(double paymentOnAccountAmount) {
		this.paymentOnAccountAmount = paymentOnAccountAmount;
	}
	public double getTotalAmountDue() {
		return totalAmountDue;
	}
	public void setTotalAmountDue(double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}
	public double getOpenBalance() {
		return openBalance;
	}
	public void setOpenBalance(double openBalance) {
		this.openBalance = openBalance;
	}

}
