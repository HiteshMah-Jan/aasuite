/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.BankAccountTransactionDetail;
import bean.financial.AccountChart;
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
    }
}
