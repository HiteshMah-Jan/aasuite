package bean.financial;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "JournalEntryItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="journalEntryId"),
	@Display(name="accountNumber"),
	@Display(name="accountName"),
	@Display(name="debit"),
	@Display(name="credit"),
	@Display(name="taxPostingAccount"),
	@Display(name="taxCode"),
	@Display(name="taxJurisdictionType"),
	@Display(name="taxJurisdictionCode"),
	@Display(name="taxAmount"),
	@Display(name="grossTotal"),
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
public class JournalEntryItem extends AbstractIBean {
	@Id
	public Integer seq;
	public int journalEntryId;
	public String accountNumber;
	public String accountName;
	public double debit;
	public double credit;
	public String taxPostingAccount;
	public String taxCode;
	public String taxJurisdictionType;
	public String taxJurisdictionCode;
	public double taxAmount;
	public double grossTotal;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getJournalEntryId() {
		return journalEntryId;
	}

	public void setJournalEntryId(int journalEntryId) {
		this.journalEntryId = journalEntryId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getTaxPostingAccount() {
		return taxPostingAccount;
	}

	public void setTaxPostingAccount(String taxPostingAccount) {
		this.taxPostingAccount = taxPostingAccount;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxJurisdictionType() {
		return taxJurisdictionType;
	}

	public void setTaxJurisdictionType(String taxJurisdictionType) {
		this.taxJurisdictionType = taxJurisdictionType;
	}

	public String getTaxJurisdictionCode() {
		return taxJurisdictionCode;
	}

	public void setTaxJurisdictionCode(String taxJurisdictionCode) {
		this.taxJurisdictionCode = taxJurisdictionCode;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getGrossTotal() {
		return grossTotal;
	}

	public void setGrossTotal(double grossTotal) {
		this.grossTotal = grossTotal;
	}

	public static void main(String[] args) {
		view(JournalEntryItem.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
