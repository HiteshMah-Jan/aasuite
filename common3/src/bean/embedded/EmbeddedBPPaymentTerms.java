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
	@Display(name="paymentTerms"),
	@Display(name="percentInterestOnArrears"),
	@Display(name="priceList"),
	@Display(name="percentTotalDiscount"),
	@Display(name="creditLimit"),
	@Display(name="commitmentLimit"),
	@Display(name="dunningTerms"),
	@Display(name="bankCountry"),
	@Display(name="bank"),
	@Display(name="account"),
	@Display(name="controlInternationalId"),
	@Display(name="creditCardType"),
	@Display(name="creditCardNo"),
	@Display(name="expirationDate"),
	@Display(name="idNumber"),
	@Display(name="averageDelay"),
	@Display(name="priority"),
	@Display(name="holidays"),
	@Display(name="allowPartialDeliveryOfSO")
})
public class EmbeddedBPPaymentTerms extends AbstractIBean {
	public String paymentTerms;
	public double percentInterestOnArrears;
	public String priceList;
	public double percentTotalDiscount;
	public double creditLimit;
	public double commitmentLimit;
	public String dunningTerms;
	public String bankCountry;
	public String bank;
	public String account;
	public String controlInternationalId;
	public String creditCardType;
	public String creditCardNo;
	public String expirationDate;
	public String idNumber;
	public double averageDelay;
	public String priority;
	public String holidays;
	public boolean allowPartialDeliveryOfSO;
	
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public double getPercentInterestOnArrears() {
		return percentInterestOnArrears;
	}
	public void setPercentInterestOnArrears(double percentInterestOnArrears) {
		this.percentInterestOnArrears = percentInterestOnArrears;
	}
	public String getPriceList() {
		return priceList;
	}
	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}
	public double getPercentTotalDiscount() {
		return percentTotalDiscount;
	}
	public void setPercentTotalDiscount(double percentTotalDiscount) {
		this.percentTotalDiscount = percentTotalDiscount;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getCommitmentLimit() {
		return commitmentLimit;
	}
	public void setCommitmentLimit(double commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}
	public String getDunningTerms() {
		return dunningTerms;
	}
	public void setDunningTerms(String dunningTerms) {
		this.dunningTerms = dunningTerms;
	}
	public String getBankCountry() {
		return bankCountry;
	}
	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getControlInternationalId() {
		return controlInternationalId;
	}
	public void setControlInternationalId(String controlInternationalId) {
		this.controlInternationalId = controlInternationalId;
	}
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public double getAverageDelay() {
		return averageDelay;
	}
	public void setAverageDelay(double averageDelay) {
		this.averageDelay = averageDelay;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public boolean isAllowPartialDeliveryOfSO() {
		return allowPartialDeliveryOfSO;
	}
	public void setAllowPartialDeliveryOfSO(boolean allowPartialDeliveryOfSO) {
		this.allowPartialDeliveryOfSO = allowPartialDeliveryOfSO;
	}
	public static void main(String[] args) {
		view(EmbeddedBPPaymentTerms.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
