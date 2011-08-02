package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "IssueForProduction")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","orderNo","serialNo","rowNo","itemNo","itemDescription","quantity","whse","itemCost","planned","issued","avail","unitOfMeasure"})
@Displays({
	@Display(name="number"),
	@Display(name="series",type="Combo",modelCombo={"Primary"}),
	@Display(name="postingDate"),
	@Display(name="referenceNo"),
	@Display(name="remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="journalRemarks")
})
public class IssueForProduction extends AbstractIBean {
@Id
public Double number;
public String series;
@Temporal(javax.persistence.TemporalType.DATE)
public Date postingDate;
public String referenceNo;
public String remarks;
public String journalRemarks;

public Double getNumber() {
	return number;
}
public void setNumber(Double number) {
	number = number;
}
public String getSeries() {
	return series;
}
public void setSeries(String series) {
	series = series;
}
public Date getPostingDate() {
	return postingDate;
}
public void setPostingDate(Date postingDate) {
	postingDate = postingDate;
}
public String getReferenceNo() {
	return referenceNo;
}
public void setReferenceNo(String referenceNo) {
	referenceNo = referenceNo;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	remarks = remarks;
}
public String getJournalRemarks() {
	return journalRemarks;
}
public void setJournalRemarks(String journalRemarks) {
	journalRemarks = journalRemarks;
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
