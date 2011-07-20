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
import template.UITemplate;
import template.Reports;
import util.BeanUtil;
/**
 *
 * @author pogi
 */
@Entity
@Table(name = "AccountChart")
@UITemplate(gridCount = 4, criteriaSearch = {"category", "groupName", "accountName", "sign"}, columnSearch = {"category", "groupName", "accountName", "accountNumber", "sign"}, orderBy="a.category, a.groupName, a.accountName")
@Displays({
    @Display(name = "accountNumber"),
    @Display(name = "accountName"),
    @Display(name = "groupName", type="PopSearch", gridFieldWidth=3, width=-1, linktoBean=AccountGroup.class),
//    @Display(name = "category", type = "Label"),
    @Display(name = "sign", type="Combo", modelCombo = {"DEBIT", "CREDIT", "BOTH"}),
    @Display(name = "description")
})
@Reports({
    @template.Report(reportFile = "AccountChart", reportTitle = "Account Chart List", reportSql="'${accountNumber}")
})
public class AccountChart extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "accountNumber", nullable=false)
    public String accountNumber;
    @Column(name = "accountName", nullable=false)
    public String accountName;
    @Column(name = "category")
    public String category;
    @Column(name = "groupName")
    public String groupName;
    @Column(name = "sign")
    public String sign;
    @Column(name = "description")
    public String description;
    @Column(name = "isActive")
    public boolean isActive = true;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountNumber", "accountName", "groupName", "description");
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static String extractAccountNumber(String accountName) {
        AccountChart chart = (AccountChart) AbstractIBean.firstRecord("SELECT a FROM AccountChart a WHERE a.accountName='",accountName,"'");
        if (chart==null) {
            AccountChart c = new AccountChart();
            c.accountName = accountName;
            c.accountNumber = BeanUtil.concat(accountName," PLS CHANGE");
            c.save();
            return c.accountNumber;
        }
        return chart.accountNumber;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(accountNumber," - ",accountName);
    }   
    

    public static AccountChart createAccountChartObj(String accountNumber, String accountName, String category, String groupName, String sign) {
        AccountChart stud = new AccountChart();
        stud.accountNumber = accountNumber;
        stud.accountName = accountName;
        stud.category = category;
        stud.groupName = groupName;
        stud.sign = sign;
        return stud;
    }
    
    @Override
    protected void runSetup() {
        createAccountChartObj("102", "CASH", "ASSET", "ASSET", "BOTH").save();
        createAccountChartObj("804", "PAYROLL", "EXPENSE", "SALARY EXPENSE", "DEBIT").save();
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