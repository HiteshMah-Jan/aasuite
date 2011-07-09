/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.reference.Bank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "BankAccount")
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"accountNumber", "bankName", "totalAmount"})
@Displays({
    @Display(name="accountNumber"),
    @Display(name="bankAccountType", type="Combo", modelCombo={"SAVINGS", "CURRENT", "CHECKING"}),
    @Display(name="bankName", type="PopSearch", linktoBean=Bank.class),
    @Display(name="totalAmount", type="Label")
})
@ChildRecords({
    @ChildRecord(template=ChildTemplateListOnly.class, entity=BankAccountTransaction.class, sql="SELECT a FROM BankAccountTransaction a WHERE a.accountNumber='${accountNumber}'", title="Transactions")
})
public class BankAccount extends AbstractIBean {
    @Id
    @Column(name = "accountNumber", nullable = false)
    public String accountNumber;
    @Column(name = "bankAccountType", nullable = false)
    public String bankAccountType;
    @Column(name = "bankName", nullable = false)
    public String bankName;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "isActive")
    public boolean isActive = true;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "accountNumber", "bankAccountType", "bankName");
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return super.toString();
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
