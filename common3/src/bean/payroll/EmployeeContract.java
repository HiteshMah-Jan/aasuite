package bean.payroll;

import javax.persistence.Entity;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "EmployeeContract")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="employeeId"),
	@Display(name="basicMonthlyPay"),
	@Display(name="basicHourlyRate"),
	@Display(name="allowedLeaveHours"),
	@Display(name="payrollType"),
	@Display(name="benefit1"),
	@Display(name="benefitAmount1"),
	@Display(name="benefitTaxable1"),
	@Display(name="tax1"),
	@Display(name="taxPercentage1"),
	@Display(name="useTaxTable"),
	@Display(name="adjustment1"),
	@Display(name="adjustmentAmount1"),
	@Display(name="taxStatus")
})
public class EmployeeContract extends AbstractIBean {
	public static void main(String[] args) {
		view(EmployeeContract.class);
	}
    public Integer seq;
    public int employeeId;
    public double basicMonthlyPay;
    public double basicHourlyRate;
    public double allowedLeaveHours;
    public String payrollType;
    
    public String benefit1;
    public double benefitAmount1;
    public boolean benefitTaxable1;
    
    public String tax1;
    public double taxPercentage1;
    public boolean useTaxTable;
    
    public String adjustment1;
    public double adjustmentAmount1;
    
    public String taxStatus;

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

	public double getBasicMonthlyPay() {
		return basicMonthlyPay;
	}

	public void setBasicMonthlyPay(double basicMonthlyPay) {
		this.basicMonthlyPay = basicMonthlyPay;
	}

	public double getBasicHourlyRate() {
		return basicHourlyRate;
	}

	public void setBasicHourlyRate(double basicHourlyRate) {
		this.basicHourlyRate = basicHourlyRate;
	}

	public double getAllowedLeaveHours() {
		return allowedLeaveHours;
	}

	public void setAllowedLeaveHours(double allowedLeaveHours) {
		this.allowedLeaveHours = allowedLeaveHours;
	}

	public String getPayrollType() {
		return payrollType;
	}

	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}

	public String getBenefit1() {
		return benefit1;
	}

	public void setBenefit1(String benefit1) {
		this.benefit1 = benefit1;
	}

	public double getBenefitAmount1() {
		return benefitAmount1;
	}

	public void setBenefitAmount1(double benefitAmount1) {
		this.benefitAmount1 = benefitAmount1;
	}

	public boolean isBenefitTaxable1() {
		return benefitTaxable1;
	}

	public void setBenefitTaxable1(boolean benefitTaxable1) {
		this.benefitTaxable1 = benefitTaxable1;
	}

	public String getTax1() {
		return tax1;
	}

	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}

	public double getTaxPercentage1() {
		return taxPercentage1;
	}

	public void setTaxPercentage1(double taxPercentage1) {
		this.taxPercentage1 = taxPercentage1;
	}

	public boolean isUseTaxTable() {
		return useTaxTable;
	}

	public void setUseTaxTable(boolean useTaxTable) {
		this.useTaxTable = useTaxTable;
	}

	public String getAdjustment1() {
		return adjustment1;
	}

	public void setAdjustment1(String adjustment1) {
		this.adjustment1 = adjustment1;
	}

	public double getAdjustmentAmount1() {
		return adjustmentAmount1;
	}

	public void setAdjustmentAmount1(double adjustmentAmount1) {
		this.adjustmentAmount1 = adjustmentAmount1;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
