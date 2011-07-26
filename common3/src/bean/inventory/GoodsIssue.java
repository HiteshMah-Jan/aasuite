package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "GoodsIssue")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Number"),
	@Display(name="Series"),
	@Display(name="PostingDate"),
	@Display(name="DocumentDate"),
	@Display(name="Reference"),
	@Display(name="PriceList"),
	@Display(name="Remarks"),
	@Display(name="JournalRemarks")
})
public class GoodsIssue extends AbstractIBean {
@Id
	public Integer Number;
	public String Series;
	public Double PostingDate;
	public Double DocumentDate;
	public Double Reference;
	public String PriceList;
	public String Remarks;
	public String JournalRemarks;
	
	
public Integer getNumber() {
		return Number;
	}
	public void setNumber(Integer number) {
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
	public Double getDocumentDate() {
		return DocumentDate;
	}
	public void setDocumentDate(Double documentDate) {
		DocumentDate = documentDate;
	}
	public Double getReference() {
		return Reference;
	}
	public void setReference(Double reference) {
		Reference = reference;
	}
	public String getPriceList() {
		return PriceList;
	}
	public void setPriceList(String priceList) {
		PriceList = priceList;
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
	view(GoodsIssue.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
