/*
 * Accountpayable.java
 *
 * Created on Dec 10, 2007, 2:01:33 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.financial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
/**
 *
 * @author pogi
 */
@Entity
@Table(name = "AccountGroup")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, criteriaSearch = {"category", "groupName", "sortNumber"}, columnSearch = {"category", "groupName", "sortNumber"}, orderBy="a.sortNumber")
@Displays({
    @Display(name = "sortNumber"),
    @Display(name = "category", type = "Combo", modelCombo = {"ASSET ACCOUNTS","LIABILITY ACCOUNTS","FUND / EQUITY", "EXPENSE ACCOUNTS", "ADMINISTRATIVE EXPENSES"}),
    @Display(name = "groupName")
    })
@Reports({
    @template.Report(reportFile = "AccountChart", reportTitle = "Account Chart List", reportSql="'${accountNumber}")
})
@template.ChildRecords({
       @template.ChildRecord(title="Account Charts", entity = AccountChart.class, fieldMapping={"groupName","groupName"}, sql = "SELECT a FROM AccountChart a WHERE a.groupName='${groupName}'")
})
public class AccountGroup extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "groupName", nullable=false)
    public String groupName;
    @Column(name = "sortNumber", nullable=false)
    public String sortNumber;
    @Column(name = "category", nullable=false)
    public String category;
    @Column(name = "isActive")
    public boolean isActive = true;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "groupName");
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }
     @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(category," - ",groupName);
    }   
    
    public static AccountGroup createAccountGroupObj(String groupName, String category, String sortNumber) {
        AccountGroup stud = new AccountGroup();
        stud.groupName = groupName;
        stud.category = category;
        stud.sortNumber = sortNumber;
        return stud;
    }
    
    @Override
    protected void runSetup() {
        createAccountGroupObj("ASSET", "ASSET", "1").save();
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}


/*
	Balance Sheet Accounts
		Asset Accounts
			Cash
			Acount Receivable
			Prepaid Expenses
			Supplies
			Inventory
			Land
			Buildings
			Vehicles and Equipment
			Accumulated Depreciation
			Other Assets
		Liability Accounts
			Accounts Payable
			Notes Payable - Current
			Notes Payable - Long Term
		Stockholder's Equity Accounts
			Common Stock
			Retained Earnings
	Income Statement Accounts
		Revenue Accounts
			Sales Revenue
			Sales Returns And Allowances
			Sales Discounts
			Interest Income
		Expense Accounts
			Advertising Expense
			Bank Fees
			Depreciation Expense
			Payroll Expense
			Payroll Tax Expense
			Rent Expense
			Income Tax Expense
			Telephone Expense
			Utilities Expense
*/