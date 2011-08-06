package bean.financial;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "PostingPeriod")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="year"),
	@Display(name="quarter"),
	@Display(name="month"),
	@Display(name="isCurrent")
})
public class PostingPeriod extends AbstractIBean {
	@Id
	public Integer seq;
	public String year;
	public int quarter;
	public int month;
	public boolean isCurrent;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public static void main(String[] args) {
		view(PostingPeriod.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
