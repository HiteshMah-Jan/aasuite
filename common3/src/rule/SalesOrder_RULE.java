package rule;

import javax.swing.JComponent;

import bean.sales.SalesOrder;

public class SalesOrder_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnDeliver".equals(comp.getName())) {
			SalesOrder order = (SalesOrder) this.getBean();
//			get all items add checkbox and make quantity editable
//			there will be buttons for "Deliver all selected items" and "Cancel"
		}
		else if ("btnInvoice".equals(comp.getName())) {
			SalesOrder order = (SalesOrder) this.getBean();
//			get all items add checkbox and make quantity editable
//			there will be buttons for "Invoice all selected items" and "Cancel"
		}
	}

}
