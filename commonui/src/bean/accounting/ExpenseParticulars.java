/*
 * Expense.java
 * 
 * Created on Feb 12, 2008, 11:03:04 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "ExpenseParticulars")
@UITemplate(template = TemplateTabPage.class,
gridCount = 4,
columnSearch = {"item", "amount"},
criteriaSearch = {"item", "amount"},
showChart = false)
@DiscriminatorColumn(name = "form", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "EXPENSE")
@Displays({
    @Display(name = "item"),
    @Display(name = "amount")
})
public class ExpenseParticulars extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "form", nullable = false)
    public String form;
    @Column(name = "recordId", nullable = false)
    public int recordId;
    @Column(name = "item", nullable = false)
    public String item;
    @Column(name = "amount", nullable = false)
    public double amount;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "item");
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }    
	
	@Override
	public void setupIndex() {
		runIndex(1, "form");
	}
}
