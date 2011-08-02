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
	@Display(name="number"),
	@Display(name="series"),
	@Display(name="postingDate"),
	@Display(name="documentDate"),
	@Display(name="reference"),
	@Display(name="priceList"),
	@Display(name="remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="journalRemarks")
})
public class GoodsReceipt extends AbstractIBean {
	 @Id
	    @Column(name = "number")
	    public Integer number;
	    @Column(name = "series")
	    public int series;	   
	    @Temporal(javax.persistence.TemporalType.DATE)
	    public Double postingDate;

	    @Temporal(javax.persistence.TemporalType.DATE)
	    public Double documentDate;
	    public String reference;
	    public String priceList;

	    public String remarks;
	    public String journalRemarks;

	    public void setPostingDate(Double postingDate) {
			postingDate = postingDate;
		}
		public void setDocumentDate(Double documentDate) {
			documentDate = documentDate;
		}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		number = number;
	}
	public int getGoodRecieptId() {
		return series;
	}
	public void setSeries(int series) {
		series = series;
	}
	public int getSeries() {
		return series;
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
	public double getPostingDate() {
		return postingDate;
	}
	public void setPostingDate1(Double postingDate) {
		postingDate = postingDate;
	}
	public double getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate1(Double documentDate) {
		documentDate = documentDate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		reference = reference;
	}
	public String getPriceList() {
		return priceList;
	}
	public void setPriceList(String priceList) {
		priceList = priceList;
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
