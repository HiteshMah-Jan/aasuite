/*
 * Eventholiday.java
 *
 * Created on Nov 27, 2007, 4:31:11 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "OtherPaymentReference")
@UITemplate(gridCount = 6, columnSearch = {"code","accountChartNumber","description"})
@Displays({
       // @Display(name="id"),
        @Display(name="code"),
        @Display(name="orType", type="Combo", modelCombo={"A","N"}),
        @Display(name="amount"),
        @Display(name="description",gridFieldWidth=5,width=-1)
        
              
})
public class OtherPaymentReference extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "description", length = 200)
    public String description;
    @Column(name = "accountChartNumber")
    public String accountChartNumber;
    @Column(name = "amount")
    public double amount;
    @Column(name = "orType")
    public String orType;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "description");
    }

    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrType() {
		return orType;
	}

	public void setOrType(String orType) {
		this.orType = orType;
	}

	public String getAccountChartNumber() {
        return accountChartNumber;
    }

    public void setAccountChartNumber(String accountChartNumber) {
        this.accountChartNumber = accountChartNumber;
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

    @Override
    public boolean cacheClient() {
        return true;
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "orType");
	}
}