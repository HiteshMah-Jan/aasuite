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
@Table(name = "DailyTimeRecord")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="entryDate"),
	@Display(name="isPaid"),
	@Display(name="timeIn"),
	@Display(name="timeOut"),
	@Display(name="totalHours"),
	@Display(name="approvedHours"),
	@Display(name="approvedOTHours"),
	@Display(name="percentage"),
	@Display(name="isHoliday"),
	@Display(name="leaveHours"),
	@Display(name="leavelWithPay"),
	@Display(name="leaveType"),
	@Display(name="approvedBy")
})
public class DailyTimeRecord extends AbstractIBean {
	public static void main(String[] args) {
		view(DailyTimeRecord.class);
	}
    public Integer seq;
    public Date entryDate;
    public boolean isPaid;
    public String timeIn;
    public String timeOut;
    public double totalHours;
    public double approvedHours;
    public double approvedOTHours;
    public int percentage;
    public boolean isHoliday;
    public double leaveHours;
    public boolean leavelWithPay;
    public String leaveType;
    public String approvedBy;
    
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public double getApprovedHours() {
		return approvedHours;
	}

	public void setApprovedHours(double approvedHours) {
		this.approvedHours = approvedHours;
	}

	public double getApprovedOTHours() {
		return approvedOTHours;
	}

	public void setApprovedOTHours(double approvedOTHours) {
		this.approvedOTHours = approvedOTHours;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	public double getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(double leaveHours) {
		this.leaveHours = leaveHours;
	}

	public boolean isLeavelWithPay() {
		return leavelWithPay;
	}

	public void setLeavelWithPay(boolean leavelWithPay) {
		this.leavelWithPay = leavelWithPay;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
