package bean.payroll;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "Payroll")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="postingPeriod"),
	@Display(name="startDate"),
	@Display(name="endDate"),
	@Display(name="targetHours")
})
public class Payroll extends AbstractIBean {
	public static void main(String[] args) {
		view(Payroll.class);
	}
    public Integer seq;
    public String postingPeriod;
    public Date startDate;
    public Date endDate;
    public double targetHours;
    
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPostingPeriod() {
		return postingPeriod;
	}

	public void setPostingPeriod(String postingPeriod) {
		this.postingPeriod = postingPeriod;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getTargetHours() {
		return targetHours;
	}

	public void setTargetHours(double targetHours) {
		this.targetHours = targetHours;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
