/*
 * LoanType.java
 * 
 * Created on Feb 19, 2008, 11:13:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import bean.*;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import util.ScriptRunner;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "LoanType")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "loanType", "employeePosition"})
@Displays({
        @Display(name="code"),
        @Display(name="loanType"),
        @Display(name="isCompanyLoan"),
        @Display(name="computeMonth"),
        @Display(name="description"),
        @Display(name="employeePosition"),
        @Display(name="formula")
})
public class LoanType extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "loanType", nullable = false)
    public String loanType;
    @Column(name = "isCompanyLoan", nullable = false)
    public boolean isCompanyLoan;
    @Column(name = "computeMonth")
    public Integer computeMonth;
    @Column(name = "description")
    public String description;
    @Column(name = "employeePosition")
    public String employeePosition;
    @Column(name = "formula", length = 4000)
    public String formula;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "loanType");
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double getCalculatedAmount(Employee emp) {
        Logger.getLogger("global").info(formula);
        Object obj = ScriptRunner.runGroovy(formula, "Employee", emp);
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
        }
        return 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public boolean getIsCompanyLoan() {
        return isCompanyLoan;
    }

    public void setIsCompanyLoan(boolean isCompanyLoan) {
        this.isCompanyLoan = isCompanyLoan;
    }

    public Integer getComputeMonth() {
        return computeMonth;
    }

    public void setComputeMonth(Integer computeMonth) {
        this.computeMonth = computeMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    @Override
    public String getComboDisplay() {
        if (code == null) {
            return "";
        } else {
            return loanType;
        }
    }
   
    @Override
    public String toString() {
        return code;
    }
}
