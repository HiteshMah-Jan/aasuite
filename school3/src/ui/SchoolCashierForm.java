/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import component.TabPanelForm;

/**
 *
 * @author Charliemagne Mark
 */
public class SchoolCashierForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Cashier";
    }
    @Override
    public String tabs() {
        return "Admission,SchoolGenericPaymentExt,PaymentAllNotPaidExt,PaymentAllExt,MultiPayment,BookSold";
    }

}
