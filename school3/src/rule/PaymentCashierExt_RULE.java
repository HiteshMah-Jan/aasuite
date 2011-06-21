/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.admin.AppConfig;
import bean.sales.Payment;

import javax.swing.JComponent;
import javax.swing.JTable;
import org.jdesktop.beansbinding.ELProperty;

/**
 *
 * @author alex
 */
public class PaymentCashierExt_RULE extends BusinessRuleWrapper {
    boolean alwaysMakePaymentEditable = true;
    boolean breakdownPayment = true;

    @Override
    public void onLoad() {
        super.onLoad();
        alwaysMakePaymentEditable = AppConfig.isPaymentEditable();
        breakdownPayment = AppConfig.isPaymentEditable();
    }

    @Override
    public boolean isEditable(Object obj, int row, int col) {
//        if (alwaysMakePaymentEditable) {
//            return true;
//        }
        if (obj != null) {
            JTable tbl = this.panel.getTable();
            Payment p = (Payment) ELProperty.create("${selectedElement}").getValue(tbl);
            if (p.paid) {
                return false;
            }
        }
        return super.isEditable(obj, row, col);
    }

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
