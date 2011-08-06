package bean.payroll;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.payroll.embedded.EmbeddedPayrollAdjustment;
import bean.payroll.embedded.EmbeddedPayrollBenefit;
import bean.payroll.embedded.EmbeddedPayrollTax;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "PayrollEmployee")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, 
		columnSearch = {"approvedHours"})
@Displays({
	@Display(name="approvedHours"),
	@Display(name="approvedOTHours"),
	@Display(name="approvedHolidayHours"),
	@Display(name="approvedLeaveHours"),
	@Display(name="basicPay"),
	@Display(name="otPay"),
	@Display(name="holidayPay"),
	@Display(name="taxStatus"),
	
	@Display(name="adjustment", type="Embedded"),
	@Display(name="benefit", type="Embedded"),
	@Display(name="tax", type="Embedded")
})
public class PayrollEmployee extends AbstractIBean {
	@Id
    public Integer seq;
    public int employeeId;
    public int payrollId;
    public double approvedHours;
    public double approvedOTHours;
    public double approvedHolidayHours;
    public double approvedLeaveHours;
    public double basicPay;
    public double otPay;
    public double holidayPay;
    public String taxStatus;
    @Embedded
    public EmbeddedPayrollAdjustment adjustment;
    @Embedded
    public EmbeddedPayrollBenefit benefit;
    @Embedded
    public EmbeddedPayrollTax tax;
    
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getPayrollId() {
		return payrollId;
	}
	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
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
	public double getApprovedHolidayHours() {
		return approvedHolidayHours;
	}
	public void setApprovedHolidayHours(double approvedHolidayHours) {
		this.approvedHolidayHours = approvedHolidayHours;
	}
	public double getApprovedLeaveHours() {
		return approvedLeaveHours;
	}
	public void setApprovedLeaveHours(double approvedLeaveHours) {
		this.approvedLeaveHours = approvedLeaveHours;
	}
	public double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public double getOtPay() {
		return otPay;
	}
	public void setOtPay(double otPay) {
		this.otPay = otPay;
	}
	public double getHolidayPay() {
		return holidayPay;
	}
	public void setHolidayPay(double holidayPay) {
		this.holidayPay = holidayPay;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public EmbeddedPayrollAdjustment getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(EmbeddedPayrollAdjustment adjustment) {
		this.adjustment = adjustment;
	}
	public EmbeddedPayrollBenefit getBenefit() {
		return benefit;
	}
	public void setBenefit(EmbeddedPayrollBenefit benefit) {
		this.benefit = benefit;
	}
	public EmbeddedPayrollTax getTax() {
		return tax;
	}
	public void setTax(EmbeddedPayrollTax tax) {
		this.tax = tax;
	}
	public static void main(String[] args) {
		view(PayrollEmployee.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
