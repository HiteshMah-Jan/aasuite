/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import bean.admin.AppConfig;
import component.TabPanelForm;
/**
 *
 * @author Charliemagne Mark
 */
public class SchoolAccountingProcessForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Accounting Process";
    }

    @Override
    public String tabs() {
        return "GL,PaymentAllNotPaidExt,PaymentAllExt,InvoiceExt,CashierDailyBooklet,BookSold,Expense,PayrollPeriod1Ext,ManualGL,BankAccountTransaction";
    }

}