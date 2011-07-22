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
@Table(name = "ManualJournalEntry")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="postingDate"),
	@Display(name="dueDate"),
	@Display(name="docDate"),
	@Display(name="remarks"),
	@Display(name="fixedExchangeRate"),
	@Display(name="adjTransaction"),
	@Display(name="period"),
	@Display(name="origin"),
	@Display(name="originNo"),
	@Display(name="transNo"),
	@Display(name="templateType"),
	@Display(name="template"),
	@Display(name="indicator"),
	@Display(name="project"),
	@Display(name="transCode"),
	@Display(name="ref1"),
	@Display(name="ref2")
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
	public boolean adjTransaction;
	public String period;
	public String origin;
	public String originNo;
	public String transNo;
	public String templateType;
	public String template;
	public String indicator;
	public String project;
	public String transCode;
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

	public boolean isAdjTransaction() {
		return adjTransaction;
	}

	public void setAdjTransaction(boolean adjTransaction) {
		this.adjTransaction = adjTransaction;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginNo() {
		return originNo;
	}

	public void setOriginNo(String originNo) {
		this.originNo = originNo;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
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
