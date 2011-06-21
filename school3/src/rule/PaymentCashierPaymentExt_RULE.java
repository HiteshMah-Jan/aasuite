package rule;

import java.util.Date;

import javax.swing.JCheckBox;

import util.PanelUtil;

import bean.accounting.Payment;

public class PaymentCashierPaymentExt_RULE extends Payment_RULE {

	@Override
	protected void addPaymentPenalty(Payment p) {
//		super.addPaymentPenalty(p);
//		String amt = PanelUtil.showPromptDefaultMessage(usedComp, "Would you like to add penalty?", "2000");
//		if (amt==null) return;
//		double amount = PanelUtil.getDoubleValue(amt);
//		if (amount<=0) return;
//		
//		Payment newp = (Payment) p.clone();
//		newp.seq = null;
//		newp.form = p.form;
//		newp.recordId = p.recordId;
//		newp.paymentFor = "CHK-PENALTY";
//		newp.description = "CHECK PENALTY";
//		newp.amountPaid = newp.discount = newp.balance = 0;;
//		newp.surcharge = newp.discountSurcharge = newp.surchargeBalance = newp.surchargePaid = 0;
//		newp.amount = newp.balance = newp.overallAmountDue = amount;
//		newp.dueDate = constants.Constants.useDate;
//		newp.paymentDate = newp.orDate = null;
//		newp.orNumber = null;
//		newp.orType = "A";
//		newp.paid = false;
//		newp.line = 2000000;
//		newp.save();
	}

}
