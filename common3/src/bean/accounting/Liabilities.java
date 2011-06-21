/*
 * Liabilities.java
 *
 * Created on Nov 25, 2007, 7:44:55 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Liabilities")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"payableFrom", "payableDate", "amount"})
@Displays({
        @Display(name="payableFrom"),
        @Display(name="payableDate"),
        @Display(name="payableType", type = "Combo", modelCombo = {"ACCOUNTS PAYABLE", "NOTES PAYABLE"}),
        @Display(name="amount"),
        @Display(name="payableItem"),
        @Display(name="payableBy", type = "PopSearch", linktoBean=Employee.class),
        @Display(name="remarks")
})
public class Liabilities extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "payableItem", nullable = false)
    public String payableItem;
    @Column(name = "payableFrom")
    public Integer payableFrom;
    @Column(name = "payableDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date payableDate;
    @Column(name = "payableType")
    public String payableType;
    @Column(name = "amount")
    public Double amount;
    @Column(name = "payableBy", nullable = false)
    public int payableBy;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getPayableFrom() {
        return payableFrom;
    }

    public void setPayableFrom(Integer payableFrom) {
        this.payableFrom = payableFrom;
    }

    public Date getPayableDate() {
        return payableDate;
    }

    public void setPayableDate(Date payableDate) {
        this.payableDate = payableDate;
    }

    public String getPayableType() {
        return payableType;
    }

    public void setPayableType(String payableType) {
        this.payableType = payableType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayableItem() {
        return payableItem;
    }

    public void setPayableItem(String payableItem) {
        this.payableItem = payableItem;
    }

    public int getPayableBy() {
        return payableBy;
    }

    public void setPayableBy(int payableBy) {
        this.payableBy = payableBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

   
    @Override
    public String toString() {
        return payableItem;
    }
}
