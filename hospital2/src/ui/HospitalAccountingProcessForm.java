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
public class HospitalAccountingProcessForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Accounting Process";
    }
    @Override
    public String tabs() {
        String def = AppConfig.getUsePayroll();
        if ("PAYROLL1".equals(def)) {
            return "GL,HospitalGenericPaymentExt,PaymentAllNotPaidExt,PaymentAllExt,CashierDailyBooklet,Expense,PayrollPeriod1Ext,ManualGL,BankAccountTransaction";
        }
        return "GL,HospitalGenericPaymentExt,PaymentAllNotPaidExt,PaymentAllExt,CashierDailyBooklet,Expense,PayrollPeriodExt,ManualGL,BankAccountTransaction";
    }

}