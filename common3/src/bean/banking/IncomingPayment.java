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
@Table(name = "IncomingPayment")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="code"),
	@Display(name="name"),
	@Display(name="billToAddress"),
	@Display(name="contractPerson"),
	@Display(name="project"),
	@Display(name="bpType"),
	@Display(name="postingDate"),
	@Display(name="dueDate"),
	@Display(name="documentDate"),
	@Display(name="reference"),
	@Display(name="transactionNo"),
	@Display(name="paymentOnAccount"),
	@Display(name="totalAmountDue"),
	@Display(name="openBalance"),
	@Display(name="remarks"),
	@Display(name="journalRemarks"),
	@Display(name="createdByWizard"),
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

public class IncomingPayment extends AbstractIBean {
	@Id
	public Integer seq;
	public String code;
	public String name;
	public String billToAddress;
	public String contractPerson;
	public String project;
	public String bpType;	//will have Customer, Vendor, Account
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date postingDate;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date dueDate;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date documentDate;
	public String reference;
	public String transactionNo;
	public boolean paymentOnAccount;
	public double totalAmountDue;
	public double openBalance;
	public String remarks;
	public String journalRemarks;
	public boolean createdByWizard;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}
	public String getContractPerson() {
		return contractPerson;
	}
	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getBpType() {
		return bpType;
	}
	public void setBpType(String bpType) {
		this.bpType = bpType;
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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public boolean isPaymentOnAccount() {
		return paymentOnAccount;
	}
	public void setPaymentOnAccount(boolean paymentOnAccount) {
		this.paymentOnAccount = paymentOnAccount;
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
	public boolean isCreatedByWizard() {
		return createdByWizard;
	}
	public void setCreatedByWizard(boolean createdByWizard) {
		this.createdByWizard = createdByWizard;
	}
	public static void main(String[] args) {
		view(IncomingPayment.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
