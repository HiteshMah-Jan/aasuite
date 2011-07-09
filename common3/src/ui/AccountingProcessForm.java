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
public class AccountingProcessForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Accounting Process";
    }

    @Override
    public String tabs() {
        return "GL,PaymentAllNotPaidExt,PaymentAllExt,Expense,AccountPayable,PayrollPeriod,ManualGL,BankAccountTransaction";
    }

}
