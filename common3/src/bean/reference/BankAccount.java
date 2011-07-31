package bean.reference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "BankAccount")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"account","bank"})
@Displays({
	@Display(name="account"),
	@Display(name="bank")
})
public class BankAccount extends AbstractIBean {
	@Id
	public String account;
	public String bank;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public static void main(String[] args) {
		view(BankAccount.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
