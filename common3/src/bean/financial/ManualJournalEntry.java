package bean.financial;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "ManualJournalEntry")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 6, columnSearch = {"period","postingDate","transactionType","origin","transNo"})
@Displays({
	@Display(name="seq", label="Journal Id", enabled=false),
	@Display(name="period", enabled=false),
	@Display(name="indicator"),
	@Display(name="postingDate", enabled=false),
	@Display(name="dueDate", enabled=false),
	@Display(name="docDate", enabled=false),
	@Display(name="transactionType", type="Combo", 
			modelCombo={"Reverse","Sales","Purchase","Inventory","Others"}),
	@Display(name="transNo"),
	@Display(name="fixedExchangeRate"),
	@Display(name="project", gridFieldWidth=5, width=-1),
	@Display(name="remarks", gridFieldWidth=5, width=-1),
	@Display(name="ref1", gridFieldWidth=5, width=-1),
	@Display(name="ref2", gridFieldWidth=5, width=-1)
})
@ChildRecords({
        @ChildRecord(template = ChildTemplateListPopupDownButton.class, fieldMapping = {
        	"seq", "journalEntryId" }, title = "Journal Items", entity = ManualJournalEntryItem.class, sql = "SELECT a FROM ManualJournalEntryItem a WHERE a.journalEntryId=${seq}")
})
public class ManualJournalEntry extends AbstractIBean {
	@Id
	public Integer seq;
    @Temporal(value = TemporalType.DATE)
	public Date postingDate;
    @Temporal(value = TemporalType.DATE)
	public Date dueDate;
    @Temporal(value = TemporalType.DATE)
	public Date docDate;
	public String remarks;
	public boolean fixedExchangeRate;
	public String period;
	public String transactionType;
	public String transNo;
	public String indicator;
	public String project;
	public String ref1;
	public String ref2;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isFixedExchangeRate() {
		return fixedExchangeRate;
	}

	public void setFixedExchangeRate(boolean fixedExchangeRate) {
		this.fixedExchangeRate = fixedExchangeRate;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public static void main(String[] args) {
		view(ManualJournalEntry.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		return null;
	}

}
