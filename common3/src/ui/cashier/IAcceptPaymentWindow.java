/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui.cashier;

import ui.AbstractCashierForm;

/**
 *
 * @author alex
 */
public interface IAcceptPaymentWindow {
    void searchOR(String or);
    void run();
    void setUseYear(String year);
    void reloadPayments();
    void setDrawer(AbstractCashierForm form);
}
