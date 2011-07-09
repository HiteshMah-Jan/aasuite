/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import ui.action.SchoolCashierScannerAction;
import common2.Common2View;
import java.awt.Toolkit;
import ui.action.CashierScannerAction;

/**
 *
 * @author Charliemagne Mark
 */
public class SchoolCashierExt_RULE extends CashierExt_RULE {
    protected void showCashier() { 
        CashierScannerAction action = new SchoolCashierScannerAction(Common2View.mainView.getFrame(), true);
        action.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        action.setVisible(true);
    }
}
