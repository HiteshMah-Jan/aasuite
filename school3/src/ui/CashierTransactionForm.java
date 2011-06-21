package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import constants.UserInfo;

import springbean.AAAConfig;
import util.PanelUtil;

public class CashierTransactionForm extends AbstractCashierForm {

    public static void main(String[] args) {
        java.awt.Color clr = new java.awt.Color(44, 117, 44);
        constants.Constants.panelBackground = clr;
        constants.Constants.labelColor = Color.WHITE;
        AAAConfig.getInstance();
        UserInfo.weblogin("WENG", "WENG");
        PanelUtil.displayToFrame(new CashierTransactionForm());
    }

    public CashierTransactionForm() {
        super();
        this.addWindow("Old Student", new ui.cashier.OldStudent());
        this.addWindow("New Student", new ui.cashier.NewStudent());
        this.addWindow("Other Payment 1", new ui.cashier.OtherPaymentAccount1());
        this.addWindow("Other Payment 2", new ui.cashier.OtherPaymentAccount2());
        this.addWindow("Book Payment", new ui.cashier.BookPayment());
        this.addWindow("Edit OR", new ui.cashier.EditOR());
        this.addWindow("Reports", new ui.cashier.ORReportForm());
    }
}
