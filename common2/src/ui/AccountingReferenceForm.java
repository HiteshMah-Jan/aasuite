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
public class AccountingReferenceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Accounting Reference";
    }

    @Override
    public String tabs() {
//        return "BenefitType,DeductionType,GLPostingScript,AccountGroup,AccountChart,Bank,BankAccount,PaymentCalendar,PaymentPlan,OtherPaymentReference";
        return "DeductionType,GLPostingScript,AccountGroup,AccountChart,Bank,BankAccount,PaymentPlan,OtherPaymentReference";
    }

}
