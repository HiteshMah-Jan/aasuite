/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.action;

import bean.*;
import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentGeneric;
import bean.admin.AppConfig;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;
import rule.Payment_RULE;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class SchoolCashierScannerAction extends CashierScannerAction {
    Payment pay;
    String studentNumber;
    int personId;
    
    @Override
    protected void newPayment() {
        //this must check and setup the payor
        String id = txtScan.getText();
        studentNumber = id;
        Person p = (Person) util.DBClient.getFirstRecord("SELECT a FROM Student a WHERE a.studentNumber='",id,"'");
        if (p == null || p.isEmptyKey()) {
            util.PanelUtil.showError(txtScan, "Not found");
            return;
        }
        personId = p.personId;
        lblStudentDisplay.setText(p.toString());
        dlg.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
//        dlg.pack();
        dlg.setVisible(true);
    }

    @Override
    protected void paymentForUpdated() {
        String str = (String) this.cboPaymentFor.getSelectedItem();
        if ("ENROLLMENT".equals(str)) {
            //get the next amount due
            pay = (PaymentEnrollment) DBClient.getFirstRecord("SELECT a FROM PaymentEnrollment a, Enrollment b, Student c WHERE a.recordId=b.seq AND a.paid=false AND b.studentId=c.personId AND c.studentNumber='",studentNumber,"' ORDER BY a.seq");
            if (pay!=null) {
                txtAmountDue.setText(BeanUtil.concat(pay.overallAmountDue,""));
                txtPayAmount.setText(BeanUtil.concat(pay.overallAmountDue,""));
                txtDescription.setText(pay.description);
            }
        }
        else {
            String val = "0";
            txtAmountDue.setText(val);
            txtPayAmount.setText(val);
            txtDescription.setText(BeanUtil.concat("PAYMENT FOR ",str));
        }
    }

    public SchoolCashierScannerAction(Frame parent, boolean modal) {
        super(parent, modal);
    }

    static class PaymentTmp extends Payment_RULE {
        Payment pay;
        CashierScannerAction scanner;
        PaymentTmp(Payment pay, CashierScannerAction scanner) {   
            this.pay = pay;
            this.scanner = scanner;
        }
        
        @Override
        public AbstractIBean getBean() {
            return pay;
        }
    }
}
