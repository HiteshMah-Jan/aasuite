/*
 * Expense.java
 * 
 * Created on Feb 12, 2008, 11:03:04 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@DiscriminatorValue(value = "BANKACCOUNTTRANSACTION")
@Displays({
    @Display(name = "item"),
    @Display(name = "amount")
})
public class BankAccountTransactionParticulars extends ExpenseParticulars {
}
