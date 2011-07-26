package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;
@Entity
@Table(name = "IssueForProduction")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Number"),
	@Display(name="Series"),
	@Display(name="PostingDate"),
	@Display(name="ReferenceNo"),
	@Display(name="Remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="JournalRemarks")
})
public class IssueForProduction extends AbstractIBean {
@Id
public Double Number;
public String Series;
public Double PostingDate;
public String ReferenceNo;
public String Remarks;
public String JournalRemarks;

public Double getNumber() {
	return Number;
}
public void setNumber(Double number) {
	Number = number;
}
public String getSeries() {
	return Series;
}
public void setSeries(String series) {
	Series = series;
}
public Double getPostingDate() {
	return PostingDate;
}
public void setPostingDate(Double postingDate) {
	PostingDate = postingDate;
}
public String getReferenceNo() {
	return ReferenceNo;
}
public void setReferenceNo(String referenceNo) {
	ReferenceNo = referenceNo;
}
public String getRemarks() {
	return Remarks;
}
public void setRemarks(String remarks) {
	Remarks = remarks;
}
public String getJournalRemarks() {
	return JournalRemarks;
}
public void setJournalRemarks(String journalRemarks) {
	JournalRemarks = journalRemarks;
}
public static void main(String[] args) {
	view(IssueForProduction.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
