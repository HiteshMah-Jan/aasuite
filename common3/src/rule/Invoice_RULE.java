/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.Invoice;
import bean.accounting.Payment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import constants.UserInfo;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class Invoice_RULE extends BusinessRuleWrapper {

	@Override
	public void onLoad() {
    	doBounceRule();
	}

	@Override
    public void onChangeRecord() {
    	doBounceRule();
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnViewGL")) {
            viewGL();
        }
    }

    protected void viewGL() {
    	Invoice inv = (Invoice) this.getBean();
        redisplayRecord();
    }

    private void computeCheckPayment(JComponent comp) {
    	Invoice pay = (Invoice) this.getBean();
        for (int i = 1; i <= 10; i++) {
            if (comp.getName().equals(BeanUtil.concat("accountNumber",i))) {
                String val = getValue(BeanUtil.concat("accountNumber",i));
                changeAmount(pay, BeanUtil.concat("amount",i), val);
            }
        }
    }

    private void changeAmount(final Invoice pay, final String amountName, final String val) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (val!=null && !val.trim().isEmpty()) {
                    double check = pay.checkAmount;
                    double useAmount = pay.allAmount-check;
                    JTextField f = (JTextField) getComponent(amountName);
                    String txt = f.getText();
                    if (txt==null || txt.trim().isEmpty() || txt.trim().equals("0") || txt.trim().equals("0.0")) {
                        f.setText(BeanUtil.concat(useAmount));
                        f.updateUI();
                    }
                }
            }
        });
    }
    
    boolean firstTime;

    protected void doBounceRule() {
    	boolean canlock = UserInfo.canLockInvoice();

        for (int i=1; i<=10; i++) {
            JCheckBox box = (JCheckBox) getComponent(BeanUtil.concat("bounceCheck",i));
            if (box!=null) {
            	box.setEnabled(canlock);
            }
        }
        if (firstTime) return;
        firstTime = true;
        if (canlock) {
            for (int i=1; i<=10; i++) {
                JCheckBox box = (JCheckBox) getComponent(BeanUtil.concat("bounceCheck",i));
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
    
    protected void bounceCheck(JCheckBox b) {
        if (b.isSelected()) {
            boolean e = PanelUtil.showPrompt(b, "Mark check as bounce. Would you like to tag the payor not allowed to pay in check?");
            if (e) {
//            get the student then change the payment status to NOT ALLOWED
            	Invoice pay = (Invoice) this.getBean();
                DBClient.runSQLNative("UPDATE Person SET paymentStatus='NOT ALLOWED' WHERE personId=",pay.billTo);
                addPaymentPenalty(pay, b.getName());
            }
            adjustBounceCheck(b.getName());
        }
        else {
            boolean e = PanelUtil.showPrompt(b, "Unmark check as bounce. Is student now allowed to pay checks?");
            if (e) {
//              get the student then change the payment status to NOT ALLOWED
            	Invoice pay = (Invoice) this.getBean();
                DBClient.runSQLNative("UPDATE Person SET paymentStatus='ALLOWED' WHERE personId=",pay.billTo);
            }
            else {
                PanelUtil.showMessage(b, "Check unmarked but the student still not allowed to pay in check.");
            }
            clearBounceCheck(b.getName());
        }
    }
    
    protected void clearBounceCheck(String chkIndex) {
//    	get all payments with same check number
    	try {
        	Invoice pay = (Invoice) this.getBean();
        	int index = PanelUtil.getIntValue(chkIndex.replace("bounceCheck", ""));
        	String check = (String) BeanUtil.getPropertyValue(pay, BeanUtil.concat("accountNumber",index));
//        	double amount = (Double) BeanUtil.getPropertyValue(pay, "amount",index);
        	
        	StringBuffer sb = new StringBuffer("SELECT a FROM Payment a WHERE a.oldPaymentFor='").append(check).append("'");
        	List<Payment> lst = DBClient.getList(sb.toString());
        	for (Payment p:lst) {
        		if (p.paymentFor.startsWith("CHK")) continue;
        		p.adjustmentStr = "CLEAR BOUNCE";
        		p.oldPaymentFor = check;
        		p.amountPaid = 0;
        		clearBounceCheck(p, check);
        		p.save();

        		Payment tmp = (Payment) DBClient.getFirstRecord("SELECT a FROM Payment a WHERE a.schoolYear='",p.schoolYear,"' AND a.paidBy=",p.paidBy," AND a.paymentFor='",p.paymentFor,"' AND a.paid=false");
        		tmp.save();
        	}
        	pay.save();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    protected void adjustBounceCheck(String chkIndex) {
//    	get all payments with same check number
    	try {
        	Invoice pay = (Invoice) this.getBean();
        	int index = PanelUtil.getIntValue(chkIndex.replace("bounceCheck", ""));
        	String check = (String) BeanUtil.getPropertyValue(pay, BeanUtil.concat("accountNumber",index));

        	if (DBClient.exist("SELECT a FROM Payment a WHERE a.oldPaymentFor='",check,"' AND a.paid=true")) {
//        		delete all payment with 0 amount
//        		PanelUtil.showPrompt(usedComp, "Payment is already posted before, would you like to repost ");
        		DBClient.runSQLNative("DELETE FROM Payment WHERE oldPaymentFor='",check,"' AND paid=1 AND amountPaid=0");
        	}
        	
        	StringBuffer sb = new StringBuffer("SELECT a FROM Payment a WHERE a.paid=true AND ");
        	sb.append("(a.accountNumber1='").append(check).append("' OR ");
        	sb.append("a.accountNumber2='").append(check).append("' OR ");
        	sb.append("a.accountNumber3='").append(check).append("' OR ");
        	sb.append("a.accountNumber4='").append(check).append("' OR ");
        	sb.append("a.accountNumber5='").append(check).append("' OR ");
        	sb.append("a.accountNumber6='").append(check).append("' OR ");
        	sb.append("a.accountNumber7='").append(check).append("' OR ");
        	sb.append("a.accountNumber8='").append(check).append("' OR ");
        	sb.append("a.accountNumber9='").append(check).append("' OR ");
        	sb.append("a.accountNumber10='").append(check).append("')");
        	List<Payment> lst = DBClient.getList(sb.toString());
        	for (Payment p:lst) {
        		if (p.paymentFor.startsWith("CHK") && p.paid==false) continue;
        		Payment newp = (Payment) p.clone();
        		newp.seq=null;
        		newp.paid = true;
        		newp.adjustmentStr = "BOUNCE";
        		newp.oldPaymentFor = check;
        		newp.amountPaid = getCheckAmount(p, check)*-1;
        		newp.save();

        		Payment tmp = (Payment) DBClient.getFirstRecord("SELECT a FROM Payment a WHERE a.schoolYear='",p.schoolYear,"' AND a.paidBy=",p.paidBy," AND a.paymentFor='",p.paymentFor,"' AND a.paid=false");
        		tmp.save();
        	}
        	pay.save();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    protected double getCheckAmount(Payment p, String check) {
    	if (check.equals(p.accountNumber1)) return p.amount1;
    	if (check.equals(p.accountNumber2)) return p.amount2;
    	if (check.equals(p.accountNumber3)) return p.amount3;
    	if (check.equals(p.accountNumber4)) return p.amount4;
    	if (check.equals(p.accountNumber5)) return p.amount5;
    	if (check.equals(p.accountNumber6)) return p.amount6;
    	if (check.equals(p.accountNumber7)) return p.amount7;
    	if (check.equals(p.accountNumber8)) return p.amount8;
    	if (check.equals(p.accountNumber9)) return p.amount9;
    	if (check.equals(p.accountNumber10)) return p.amount10;
    	return 0;
    }
    
    protected void setBounceCheck(Payment p, String check) {
    	if (check.equals(p.accountNumber1)) p.bounceCheck1 = true;
    	if (check.equals(p.accountNumber2)) p.bounceCheck2 = true;
    	if (check.equals(p.accountNumber3)) p.bounceCheck3 = true;
    	if (check.equals(p.accountNumber4)) p.bounceCheck4 = true;
    	if (check.equals(p.accountNumber5)) p.bounceCheck5 = true;
    	if (check.equals(p.accountNumber6)) p.bounceCheck6 = true;
    	if (check.equals(p.accountNumber7)) p.bounceCheck7 = true;
    	if (check.equals(p.accountNumber8)) p.bounceCheck8 = true;
    	if (check.equals(p.accountNumber9)) p.bounceCheck9 = true;
    	if (check.equals(p.accountNumber10)) p.bounceCheck10 = true;
    }

    protected void clearBounceCheck(Payment p, String check) {
    	if (check.equals(p.accountNumber1)) p.bounceCheck1 = false;
    	if (check.equals(p.accountNumber2)) p.bounceCheck2 = false;
    	if (check.equals(p.accountNumber3)) p.bounceCheck3 = false;
    	if (check.equals(p.accountNumber4)) p.bounceCheck4 = false;
    	if (check.equals(p.accountNumber5)) p.bounceCheck5 = false;
    	if (check.equals(p.accountNumber6)) p.bounceCheck6 = false;
    	if (check.equals(p.accountNumber7)) p.bounceCheck7 = false;
    	if (check.equals(p.accountNumber8)) p.bounceCheck8 = false;
    	if (check.equals(p.accountNumber9)) p.bounceCheck9 = false;
    	if (check.equals(p.accountNumber10)) p.bounceCheck10 = false;
    }

    @Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().startsWith("amount")) {
			computeCheckPayment(comp);
		}
	}

	protected void addPaymentPenalty(Invoice p, String chkIndex) {
    	int index = PanelUtil.getIntValue(chkIndex.replace("bounceCheck", ""));
    	String check = (String) BeanUtil.getPropertyValue(p, BeanUtil.concat("accountNumber",index));
    	if (DBClient.exist("SELECT a FROM Payment a WHERE a.oldPaymentFor='",check,"' AND a.paid=false")) {
    		util.Log.warning("PENALTY FOR CHECK ALREADY INSERTED.");
    		return;
    	}
    	
    	Payment tmp = (Payment) DBClient.getFirstRecord("SELECT a FROM Payment a WHERE a.invoiceId=",p.seq);
		if (tmp==null) return;

		String amt = PanelUtil.showPromptDefaultMessage(usedComp, "Would you like to add penalty?", "2000");
		if (amt==null) return;
		double amount = PanelUtil.getDoubleValue(amt);
		if (amount<=0) return;

		Payment newp = (Payment) tmp.clone();
		newp.seq = null;
		newp.recordId = p.recordId;
		newp.paymentFor = "CHK-PENALTY";
		newp.oldPaymentFor = check;
		newp.description = "CHECK PENALTY";
		newp.amountPaid = newp.discount = newp.balance = 0;
		newp.surcharge = newp.discountSurcharge = newp.surchargeBalance = newp.surchargePaid = 0;
		newp.amount = newp.balance = newp.overallAmountDue = amount;
		newp.dueDate = constants.Constants.useDate;
		newp.paymentDate = newp.orDate = null;
		newp.orNumber = null;
		newp.orType = "N";
		newp.paid = false;
		newp.line = 2000000;
		newp.save();
	}

}
