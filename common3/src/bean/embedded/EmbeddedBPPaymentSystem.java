package bean.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="houseBankCountry"),
	@Display(name="houseBank"),
	@Display(name="houseAccount"),
	@Display(name="houseBranch"),
	@Display(name="houseControlNo"),
	@Display(name="paymentBlock"),
	@Display(name="singlePayment"),
	@Display(name="collectionAuthorization"),
	@Display(name="bankCharges")
})
public class EmbeddedBPPaymentSystem extends AbstractIBean {
	public String houseBankCountry;
	public String houseBank;
	public String houseAccount;
	public String houseBranch;
	public String houseControlNo;
	public String paymentBlock;
	public boolean singlePayment;
	public boolean collectionAuthorization;
	public String bankCharges;
	
	public String getHouseBankCountry() {
		return houseBankCountry;
	}
	public void setHouseBankCountry(String houseBankCountry) {
		this.houseBankCountry = houseBankCountry;
	}
	public String getHouseBank() {
		return houseBank;
	}
	public void setHouseBank(String houseBank) {
		this.houseBank = houseBank;
	}
	public String getHouseAccount() {
		return houseAccount;
	}
	public void setHouseAccount(String houseAccount) {
		this.houseAccount = houseAccount;
	}
	public String getHouseBranch() {
		return houseBranch;
	}
	public void setHouseBranch(String houseBranch) {
		this.houseBranch = houseBranch;
	}
	public String getHouseControlNo() {
		return houseControlNo;
	}
	public void setHouseControlNo(String houseControlNo) {
		this.houseControlNo = houseControlNo;
	}
	public String getPaymentBlock() {
		return paymentBlock;
	}
	public void setPaymentBlock(String paymentBlock) {
		this.paymentBlock = paymentBlock;
	}
	public boolean isSinglePayment() {
		return singlePayment;
	}
	public void setSinglePayment(boolean singlePayment) {
		this.singlePayment = singlePayment;
	}
	public boolean isCollectionAuthorization() {
		return collectionAuthorization;
	}
	public void setCollectionAuthorization(boolean collectionAuthorization) {
		this.collectionAuthorization = collectionAuthorization;
	}
	public String getBankCharges() {
		return bankCharges;
	}
	public void setBankCharges(String bankCharges) {
		this.bankCharges = bankCharges;
	}
	public static void main(String[] args) {
		view(EmbeddedBPPaymentSystem.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
