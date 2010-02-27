package rule;

import java.util.List;

import javax.swing.SwingUtilities;

import bean.accounting.Invoice;
import bean.extension.InvoiceExt;

import constants.UserInfo;

import template.screen.AbstractChildTemplatePanel;

public class InvoiceExt_RULE extends Invoice_RULE {

	@Override
	public void onChangeRecord() {
		super.onChangeRecord();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Invoice inv = (Invoice) getBean();
				List<AbstractChildTemplatePanel> lst = panel.getTabs();
				for (AbstractChildTemplatePanel pnl:lst) {
					pnl.showDelete(true);
					pnl.showUpdate(true);
					pnl.showAdd(true);
				    if (!UserInfo.canLockInvoice()) {
					    if (inv.isLocked()) {
							pnl.showDelete(false);
							pnl.showUpdate(false);
							pnl.showAdd(false);
					    }
					    else {
							if (!UserInfo.getUserName().equals(inv.cashier)) {
								pnl.showDelete(false);
								pnl.showUpdate(false);
								pnl.showAdd(false);
							}
					    }
				    }
				}
			}
		});
	}

}
