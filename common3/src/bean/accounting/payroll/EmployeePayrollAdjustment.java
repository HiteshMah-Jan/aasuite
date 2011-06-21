package bean.accounting.payroll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

@Entity
@Table(name = "EmployeePayrollAdjustment")
@UITemplate(template = TemplateTabPage.class, gridCount = 6,
columnSearch = {"sortNum","adjustmentName","amount","deduct","taxable"})
@Displays({
    @Display(name = "sortNum"),
    @Display(name = "payrollAdjustmentRefId", gridFieldWidth=5, width=-1, label="Adjustment", type="PopSearch", linktoBean = PayrollAdjustmentRef.class),
    @Display(name = "amount"),
    @Display(name = "deduct", type="CheckBox"),
    @Display(name = "taxable", type="CheckBox")
})
public class EmployeePayrollAdjustment extends AbstractIBean {
  public static void main(String[] args) {
        view(EmployeePayrollAdjustment.class);
    }
  
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column
    public int sortNum;
    @Column
    public int employeePayrollId;
    @Column
    public int employeeId;
    @Column
    public int payrollAdjustmentRefId;
    @Column
    public String adjustmentName;	//lookup
    @Column
    public double amount;		//this will have positive and negative value
    @Column
    public boolean taxable;
    @Column
    public boolean deduct;

    @Override
    public String popupSearch(String criteria) {
        return null;
    }

    public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getPayrollAdjustmentRefId() {
		return payrollAdjustmentRefId;
	}

	public void setPayrollAdjustmentRefId(int payrollAdjustmentRefId) {
		this.payrollAdjustmentRefId = payrollAdjustmentRefId;
	}

	public boolean isDeduct() {
        return deduct;
    }

    public void setDeduct(boolean deduct) {
        this.deduct = deduct;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getEmployeePayrollId() {
        return employeePayrollId;
    }

    public void setEmployeePayrollId(int employeePayrollId) {
        this.employeePayrollId = employeePayrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAdjustmentName() {
        return adjustmentName;
    }

    public void setAdjustmentName(String adjustmentName) {
        this.adjustmentName = adjustmentName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
