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
import template.screen.TemplateTabSinglePageNoSubPanel;

@Entity
@Table(name = "ReverseTransactions")
@UITemplate(template=TemplateTabSinglePageNoSubPanel.class, gridCount = 6, columnSearch = {"period","transactionDate","reverseDate","journalEntryId"})
@Displays({
	@Display(name="seq", label="Journal Id", enabled=false),
	@Display(name="period", enabled=false),
	@Display(name="transactionDate", enabled=false),
	@Display(name="reverseDate", enabled=false),
	@Display(name="journalEntryId", type="PopSearch", linktoBean=JournalEntry.class, label="Journal Id")
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

	public static void main(String[] args) {
		view(ReverseTransactions.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
