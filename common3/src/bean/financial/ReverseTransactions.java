package bean.financial;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "ReverseTransactions")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="transactionDate"),
	@Display(name="reverseDate"),
	@Display(name="remarks"),
	@Display(name="period"),
	@Display(name="journalEntryId"),
	@Display(name="reverseJournalEntryId"),
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

public class ReverseTransactions extends AbstractIBean {
	@Id
	public Integer seq;
    @Temporal(value = TemporalType.DATE)
	public Date transactionDate;
    @Temporal(value = TemporalType.DATE)
	public Date reverseDate;
	public String remarks;
	public String period;
	public int journalEntryId;
	public int reverseJournalEntryId;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getReverseDate() {
		return reverseDate;
	}

	public void setReverseDate(Date reverseDate) {
		this.reverseDate = reverseDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getJournalEntryId() {
		return journalEntryId;
	}

	public void setJournalEntryId(int journalEntryId) {
		this.journalEntryId = journalEntryId;
	}

	public int getReverseJournalEntryId() {
		return reverseJournalEntryId;
	}

	public void setReverseJournalEntryId(int reverseJournalEntryId) {
		this.reverseJournalEntryId = reverseJournalEntryId;
	}

	public static void main(String[] args) {
		view(ReverseTransactions.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
