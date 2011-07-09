/*
 * BenefitType.java
 *
 * Created on Feb 13, 2008, 8:28:16 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import bean.Employee;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CompensationType")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@UITemplate(canBackup=false, template=template.screen.TemplateTabPage.class, gridCount = 4, columnSearch = {"code"})
@Displays({
    @Display(name="code"),
    @Display(name="category",type="Combo",modelCombo={"TAX","LOAN","OTHERS"}),
    @Display(name="description",gridFieldWidth=3,width=500)
})
@template.ChildRecords({
       @template.ChildRecord(title="Config Table", entity = DeductionConfigTable.class, fieldMapping={"code","code"}, sql = "SELECT a FROM DeductionConfigTable a")
})
public class CompensationType extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 20)
    public String code;
    @Column(name = "category", nullable = false, length = 20)
    public String category;
    @Column(name = "type", nullable = false, length = 20)
    public String type;
    @Column(name = "employerShare")
    public double employerShare;
    @Column(name = "description", length = 100)
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "category", "type", "description");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getEmployerShare() {
        return employerShare;
    }

    public void setEmployerShare(double employerShare) {
        this.employerShare = employerShare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setEmployeeValue(List<Employee> emp) {
        for (Employee employee : emp) {
            setEmployeeValue(employee);
        }
    }

    public void setEmployeeValue(Employee emp) {
        double d = getCalculatedValue(emp);
        emp.tCalc1 = d;
    }

    public double getCalculatedValue(Employee emp) {
        double retVal = 0;
        List<DeductionConfigTable> lstConfig = selectListCache("SELECT a FROM DeductionConfigTable a WHERE a.code='",code,"' ORDER BY a.fromAmount");
        if (lstConfig!=null) {
            DeductionConfigTable aconf = new DeductionConfigTable();
            for (DeductionConfigTable conf : lstConfig) {
                aconf = conf;
                if (emp.basicPay>=conf.fromAmount && emp.basicPay<=conf.toAmount) {
                    break;
                }
            }
            if (aconf.code==null || aconf.code.isEmpty()) return 0;
            if (aconf.percentageAfterFromAmount>0) {    //this is range
                double remaining = emp.basicPay - aconf.fromAmount;
                if (aconf.fixedAmount>0) {
                    retVal = aconf.fixedAmount;
                }
                else {
                    retVal = aconf.fromAmount * aconf.percentage/100;
                }
                retVal += remaining * aconf.percentageAfterFromAmount/100;
            }
            else {  //total
                if (aconf.fixedAmount>0) {
                    retVal = aconf.fixedAmount;
                }
                else {
                    retVal = emp.basicPay * aconf.percentage/100;
                }
            }
        }
        return retVal;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "type");
	}
}
