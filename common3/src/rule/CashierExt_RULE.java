/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import common2.Common2View;
import java.awt.Toolkit;
import javax.swing.JComponent;
import ui.action.CashierScannerAction;

/**
 *
 * @author Charliemagne Mark
 */
public class CashierExt_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if ("btnCashierConsole".equals(comp.getName())) {
            showCashier();
        }
    }

    protected void showCashier() {
        CashierScannerAction action = new CashierScannerAction(Common2View.mainView.getFrame(), true);
        action.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        action.setVisible(true);
    }

}
