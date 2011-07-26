package bean.inventory;

import javax.persistence.Column;
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
@Table(name = "GoodsReceipt")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"Seq"})
@Displays({
	@Display(name="Number"),
	@Display(name="Series"),
	@Display(name="PostingDate"),
	@Display(name="DocumentDate"),
	@Display(name="Reference"),
	@Display(name="PriceList"),
	@Display(name="Remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="JournalRemarks")
})
public class GoodsReceipt extends AbstractIBean {
	 @Id
	    @Column(name = "Number")
	    public Integer Number;
	    @Column(name = "Series")
	    public int Series;	   
	    @Temporal(javax.persistence.TemporalType.DATE)
	public Double PostingDate;
public void setPostingDate(Double postingDate) {
			PostingDate = postingDate;
		}
		public void setDocumentDate(Double documentDate) {
			DocumentDate = documentDate;
		}
@Temporal(javax.persistence.TemporalType.DATE)
	public Double DocumentDate;
	public String Reference;
	public String PriceList;
	public Integer getSeq() {
		return Number;
	}
	public void setSeq(Integer Number) {
		this.Number = Number;
	}
	public Integer getNumber() {
		return Number;
	}
	public void setNumber(Integer number) {
		Number = number;
	}
	public int getGoodRecieptId() {
		return Series;
	}
	public void setSeries(int Series) {
		Series = Series;
	}
	public int getSeries() {
		return Series;
	}
	public String Remarks;
	public String JournalRemarks;
	


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
	public Double getPostingDate() {
		return PostingDate;
	}
	public void setPostingDate1(Double postingDate) {
		PostingDate = postingDate;
	}
	public Double getDocumentDate() {
		return DocumentDate;
	}
	public void setDocumentDate1(Double documentDate) {
		DocumentDate = documentDate;
	}
	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	public String getPriceList() {
		return PriceList;
	}
	public void setPriceList(String priceList) {
		PriceList = priceList;
	}
	public static void main(String[] args) {
		view(GoodsReceipt.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
