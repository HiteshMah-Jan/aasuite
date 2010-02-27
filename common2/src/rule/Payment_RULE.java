/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.GLPostingScript;
import bean.accounting.Invoice;
import bean.accounting.Payment;
import bean.accounting.PaymentLineItem;
import bean.admin.AppConfig;
import constants.UserInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import template.screen.TablePopup;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class Payment_RULE extends BusinessRuleWrapper {
    @Override
    public void onChangeRecord() {
//    	doBounceRule();
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnViewGL")) {
            viewGL();
        }
        else if (comp.getName().equals("btnViewParticulars")) {
            viewParticulars();
        }
    }

    protected void viewGL() {
        Payment payment = (Payment) this.getBean();
        if (payment.paid) {
//        	post invoice
            payment.save();
            Invoice inv = (Invoice) payment.extractInvoice(payment);
            if (payment.invoiceId==0) {
                setValue("invoiceId", inv.seq);
            }
            GLPostingScript.post(inv);
            springbean.IProcess.Process.getInstance().showGL(inv);
        }
        else {
//        	post receivables
            payment.save();
            GLPostingScript.post(payment);
            springbean.IProcess.Process.getInstance().showGL(payment);
        }
        redisplayRecord();
    }

    private void computeCheckPayment(JComponent comp) {
        Payment pay = (Payment) this.getBean();
        for (int i = 1; i <= 10; i++) {
            if (comp.getName().equals("accountNumber"+i)) {
                String val = getValue("accountNumber"+i);
                changeAmount(pay, "amount"+i, val);
            }
        }
    }

    private void changeAmount(final Payment pay, final String amountName, final String val) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (val!=null && !val.trim().isEmpty()) {
                    double check = pay.extractCheckAmount();
                    double useAmount = pay.amount-pay.discount-check;
                    JTextField f = (JTextField) getComponent(amountName);
                    String txt = f.getText();
                    if (txt==null || txt.trim().isEmpty() || txt.trim().equals("0") || txt.trim().equals("0.0")) {
//                        System.out.println("SET TEXT FOR "+amountName+" VALUE:"+useAmount);
                        f.setText(useAmount+"");
                        f.updateUI();
                    }
                }
            }
        });
    }
    
    protected void viewParticulars() {
        Payment p = (Payment) this.getBean();
        List lst = AbstractIBean.list("SELECT a FROM PaymentLineItem a WHERE a.paymentId="+p.seq);
        TablePopup.showRecords("Payment Particulars", lst, PaymentLineItem.class, "code", "description", "charges", "netSurcharge", "discount", "totalPaidAmount");
    }
    
    boolean firstTime;
    boolean isAllowedToMarkBounce;
    boolean isAllowedToLiftCheckStatus;

    protected void doBounceRule() {
        isAllowedToMarkBounce = true;
        isAllowedToLiftCheckStatus = true;

        for (int i=1; i<=10; i++) {
            JCheckBox box = (JCheckBox) getComponent("bounceCheck"+i);
            if (box!=null) box.setEnabled(isAllowedToMarkBounce);
        }
        if (firstTime) return;
        firstTime = true;
//        isAllowedToMarkBounce = UserInfo.canMarkBounceCheck();
//        isAllowedToLiftCheckStatus = UserInfo.canLiftSatusForBounceCheck();
        if (isAllowedToMarkBounce) {
            for (int i=1; i<=10; i++) {
                JCheckBox box = (JCheckBox) getComponent("bounceCheck"+i);
                if (box!=null) {
                    if (box!=null) box.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JCheckBox b = (JCheckBox) e.getSource();
                            if (b!=null) bounceCheck(b);
                        }
                    });
                }
            }
        }
    }
    
    protected void addPaymentPenalty(Payment p) {
//    	by default no penalty
    }
    
    protected void bounceCheck(JCheckBox b) {
        if (b.isSelected()) {
            boolean e = PanelUtil.showPrompt(b, "Mark check as bounce. Would you like to tag the student not allowed to pay in check?");
            if (e) {
//            get the student then change the payment status to NOT ALLOWED
                Payment pay = (Payment) this.getBean();
                DBClient.runSQLNative("UPDATE Person SET paymentStatus='NOT ALLOWED' WHERE personId="+pay.paidBy);
                addPaymentPenalty(pay);
            }
        }
        else {
            boolean e = PanelUtil.showPrompt(b, "Unmark check as bounce. Is student now allowed to pay checks?");
            if (e) {
//              get the student then change the payment status to NOT ALLOWED
                Payment pay = (Payment) this.getBean();
                DBClient.runSQLNative("UPDATE Person SET paymentStatus='ALLOWED' WHERE personId="+pay.paidBy);
            }
            else {
                PanelUtil.showMessage(b, "Check unmarked but the student still not allowed to pay in check.");
            }
        }
    }

	@Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().startsWith("accountNumber")) {
			computeCheckPayment(comp);
		}
		else if (comp.getName().startsWith("amount")) {
			String str = getValue(comp.getName());
			if (str==null || str.isEmpty()) {
				setValue(comp.getName(), "0");
			}
		}
	}
}
