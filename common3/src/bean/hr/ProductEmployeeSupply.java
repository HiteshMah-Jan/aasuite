/*
 * Payabletype.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.hr;

import bean.inventory.Product;
import bean.reference.Department;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "ProductEmployeeSupply")
@UITemplate(title="Employee Supplies", template = TemplateDefault.class, gridCount = 4, 
		columnSearch = {"employee", "productCode","count"},
		criteriaSearch = {"department","employeeId", "productCode"}
)
@Displays({
        @Display(name="department",width=200,type="PopSearch",linktoBean=Department.class),
        @Display(name="employeeId", label = "Employee",type="PopSearch", linktoBean=Employee.class,width=200),
        @Display(name="productCode", label="Product",type = "PopSearch", linktoBean = Product.class,width=200),
        @Display(name="count"),
        @Display(name="suppliedBy",width=-1),
        @Display(name="supplyDate"),
        @Display(name="purchaseDate"),
        @Display(name="resupplyDate"),
        @Display(name="remarks", gridFieldWidth=3, width=-1)
        
})
@ChildRecords(value = {
    @ChildRecord(title="Supplies For Employee", template=ChildTemplateListOnly.class, fieldMapping = {"employeeId", "employeeId"}, entity = ProductEmployeeSupply.class, sql = "SELECT a FROM ProductEmployeeSupply a WHERE a.employeeId=${employeeId} ORDER BY a.supplyDate DESC"),
    @ChildRecord(title="Product Description", template=ChildTemplateListPopupDownButton.class, fieldMapping = {"code", "productCode"}, entity = Product.class, sql = "SELECT a FROM Product a WHERE a.code='${productCode}'")
})
public class ProductEmployeeSupply extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "employeeId", nullable = false)
    public int employeeId;
    @Column(name = "employee")
    public String employee;
    @Column(name = "purchaseDate")
    @Temporal(value = TemporalType.DATE)
    public Date purchaseDate;
    @Column(name = "supplyDate")
    @Temporal(value = TemporalType.DATE)
    public Date supplyDate;
    @Column(name = "resupplyDate")
    @Temporal(value = TemporalType.DATE)
    public Date resupplyDate;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "count")
    public int count;
     @Column(name = "suppliedBy")
    public String suppliedBy;
      @Column(name = "department")
    public String department;
    @Column(name = "minPricePerUnit")
    public double minPricePerUnit;
    @Column(name = "maxPricePerUnit")
    public double maxPricePerUnit;
    @Column(name = "remarks")
    public String remarks;
    
    @Override
	public void save() {
    	employee = extractPersonName(employeeId);
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }
    
    

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public Date getResupplyDate() {
        return resupplyDate;
    }

    public void setResupplyDate(Date resupplyDate) {
        this.resupplyDate = resupplyDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getMinPricePerUnit() {
        return minPricePerUnit;
    }

    public void setMinPricePerUnit(double minPricePerUnit) {
        this.minPricePerUnit = minPricePerUnit;
    }

    public double getMaxPricePerUnit() {
        return maxPricePerUnit;
    }

    public void setMaxPricePerUnit(double maxPricePerUnit) {
        this.maxPricePerUnit = maxPricePerUnit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
