/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.AccountChart;
import bean.accounting.BankAccountTransactionDetail;
import component.LookupTableFieldPallete;
import javax.swing.JComponent;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class BankAccountTransactionDetail_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
        if (comp.getName().equals("accountNumber")) {
            setNominalAccount();
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
    }

    private void setNominalAccount() {
        getComponent("debit").setEnabled(false);
        getComponent("credit").setEnabled(false);
        LookupTableFieldPallete cbo = (LookupTableFieldPallete) getComponent("accountNumber");
        AccountChart acct = (AccountChart) cbo.getSelectedBean();
        if (acct!=null) {
            if ("BOTH".equals(acct.sign)) {
                getComponent("debit").setEnabled(true);
                getComponent("credit").setEnabled(true);
                BankAccountTransactionDetail det = (BankAccountTransactionDetail) getBean();
                if (det.debit!=0 && det.credit!=0) {
                    PanelUtil.showMessageToScreen("Cannot have value on both credit and debit, removing value in the credit box.");
                    setValue("credit", 0);
                }
            }
            else {
                if ("DEBIT".equals(acct.sign)) {
                    getComponent("debit").setEnabled(true);
                    setValue("credit",0);
                }
                else {
                    getComponent("credit").setEnabled(true);
                    setValue("debit",0);
                }
            }
        }
    }
}
