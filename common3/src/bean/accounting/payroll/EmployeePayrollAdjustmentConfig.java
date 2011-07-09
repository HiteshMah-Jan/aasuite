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
@Table(name = "EmployeePayrollAdjustmentConfig")
@UITemplate(template = TemplateTabPage.class, gridCount = 4,
columnSearch = {"displayName", "amount"})
@Displays({
    @Display(name = "category", type="Label"),
    @Display(name = "displayName", linktoBean = PayrollAdjustmentRef.class),
    @Display(name = "amount"),
    @Display(name = "deduct", type="CheckBox"),
    @Display(name = "taxable", type="CheckBox")
})
public class EmployeePayrollAdjustmentConfig extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column
    public boolean deduct;
    @Column
    public int employeeId;
    @Column
    public String displayName;          //lookup
    @Column
    public String category;		//generated from adjustmentName
    @Column
    public double amount;		//this will have positive and negative value
    @Column
    public boolean taxable;

    @Override
    public String popupSearch(String criteria) {
        return null;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean isDeduct() {
        return deduct;
    }

    public void setDeduct(boolean deduct) {
        this.deduct = deduct;
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
