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
	@Display(name="consolidatingBp"),
	@Display(name="accountsReceivable"),
	@Display(name="paymentAdvice"),
	@Display(name="taxStatus")
})
public class EmbeddedBPAccounting extends AbstractIBean {
	public String consolidatingBp;
	public double accountsReceivable;
	public double paymentAdvice;
	public String taxStatus;
	
	public String getConsolidatingBp() {
		return consolidatingBp;
	}
	public void setConsolidatingBp(String consolidatingBp) {
		this.consolidatingBp = consolidatingBp;
	}
	public double getAccountsReceivable() {
		return accountsReceivable;
	}
	public void setAccountsReceivable(double accountsReceivable) {
		this.accountsReceivable = accountsReceivable;
	}
	public double getPaymentAdvice() {
		return paymentAdvice;
	}
	public void setPaymentAdvice(double paymentAdvice) {
		this.paymentAdvice = paymentAdvice;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public static void main(String[] args) {
		view(EmbeddedBPAccounting.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
