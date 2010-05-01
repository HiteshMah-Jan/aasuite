package ui.cashier;

import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.Person;
import bean.accounting.CashierDailyBooklet;
import bean.accounting.Invoice;
import bean.accounting.Payment;

public class AcceptPaymentRule {
	AcceptPaymentOPD opd;
	static CashierDailyBooklet booklet;
	
	public static void acceptPayment(AcceptPaymentOPD accept) {
		booklet = CashierDailyBooklet.getMyBooklet();
		if (booklet==null) booklet = new CashierDailyBooklet();
		
		AcceptPaymentRule rule = new AcceptPaymentRule();
		rule.opd = accept;
		rule.runAcceptPayment();
	}
	
    public void runAcceptPayment() {
        try {
            if (opd.pnlBilling.view.list == null || opd.pnlBilling.view.list.isEmpty()) {
                PanelUtil.showMessage(opd, "No billing for selected patient.");
                return;
            }
            String amount = PanelUtil.showPromptDefaultMessage(opd, "Please type amount.", opd.getCandidateAmount()+"");
            if (amount == null) {
                return;
            }
            double damount = PanelUtil.getDoubleValue(amount);
            if (damount>opd.getCandidateAmount()) {
                PanelUtil.showMessage(opd, "More than unpaid amount, please type another.");
                runAcceptPayment();
                return;
            }
            if (damount<=0) {
                PanelUtil.showMessage(opd, "Cannot accept 0 or less or non numeric amount.");
                runAcceptPayment();
                return;
            }
            damount = DataUtil.getMoneyFormat(damount);

            Payment check = null;
            Person stud = (Person) opd.pnPatientList.getBean();
            if ("NOT ALLOWED".equals(stud.paymentStatus)) {
                if (!PanelUtil.showPrompt(opd, "Patient cannot pay in check, would you like to continue?")) {
                    return;
                }
            }
            else {
                boolean b = PanelUtil.showPrompt(opd, "Are there any checks?\nNote: This cannot cancel transaction.");
                if (b) {
                    check = AcceptCheckForm.acceptCheck(stud, damount, 0);
                }
            }
            String orType = "N";
			String or = PanelUtil.showPromptDefaultMessage(opd, "Print OR for Patient?", booklet.extractNextOR(orType) + "");
			if (or==null) {
				throw new Exception(constants.Constants.CANCELLED);
			}
			else {
//				check if out of range
				int i = Integer.parseInt(or);
				if (i<booklet.startOR || i>booklet.endOR) {
					if (!PanelUtil.showPrompt(opd, "OR number out of series, would you like to continue?")) {
						throw new Exception("CANCELLED OR OUT OF RANGE");
					}
				}
//				check or if exist in invoice
				Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber="+or+" AND a.orType='"+orType+"'");
				if (inv!=null && !inv.isEmptyKey()) {
					PanelUtil.showError(opd, "OR# ["+or+"] is already used, please check");
					throw new Exception("OR already used.");
				}
				String description = PanelUtil.showPromptDefaultMessage(opd, "OR Description", "Out Patient Fee");
				inv = opd.getBilling().createInvoice(damount, description);
				inv.orNumber = or;
				inv.orType = orType;
				inv.schoolYear = opd.getUseYear();
				inv.printOR("Print OR", check);
				opd.updateBilling();
			}
            opd.reloadPayments();
        } catch (Exception e) {
        	e.printStackTrace();
            opd.reloadPayments();
        }
    }
}
