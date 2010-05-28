/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Product;
import bean.PurchaseOrder;
import bean.PurchaseOrderItem;
import bean.SupplierProduct;
import bean.accounting.Expense;
import bean.accounting.ExpenseParticulars;

import javax.swing.JComponent;

import common2.Common2View;

import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

import constants.UserInfo;

/**
 * 
 * @author Entokwaa
 */
public class PurchaseOrder_RULE extends BusinessRuleWrapper {

	@Override
	public void onChangeRecord() {
		PurchaseOrder po = (PurchaseOrder) getBean();
		if (po.requestedBy != null && !po.requestedBy.isEmpty()) {
			disable("btnRequest");
		} else {
			enable("btnRequest");
		}
		if (po.approvedBy != null && !po.approvedBy.isEmpty()) {
			disable("btnApprove");
		} else {
			enable("btnApprove");
		}
		if (po.receivedBy != null && !po.receivedBy.isEmpty()) {
			disable("btnReceive");
		} else {
			enable("btnReceive");
		}
		// if (UserInfo.canApprovePO()) {
		// show("btnApprove");
		// }
		// else {
		// hide("btnApprove");
		// }
		// if (UserInfo.canReceivePO()) {
		// show("btnReceive");
		// }
		// else {
		// hide("btnReceive");
		// }
	}

	@Override
	public void runFocusLost(JComponent comp) {
		PurchaseOrder po = (PurchaseOrder) getBean();

		// 1
		if (comp.getName().equalsIgnoreCase("amount1")) {
			if (po.quantity1 != 0 && po.amount1 != 0) {
				double a = po.quantity1 * po.amount1;
				setValue("totalAmount1", BeanUtil.concat(a));
			} else {
				setValue("totalAmount1", BeanUtil.concat(0.0));
			}
		}
		// 2
		if (comp.getName().equalsIgnoreCase("amount2")) {
			if (po.quantity2 != 0 && po.amount2 != 0) {
				double a = po.quantity2 * po.amount2;
				setValue("totalAmount2", BeanUtil.concat(a));
			} else {
				setValue("totalAmount2", BeanUtil.concat(0.0));
			}
		}
		// 3
		if (comp.getName().equalsIgnoreCase("amount3")) {
			if (po.quantity3 != 0 && po.amount3 != 0) {
				double a = po.quantity3 * po.amount3;
				setValue("totalAmount3", BeanUtil.concat(a));
			} else {
				setValue("totalAmount3", BeanUtil.concat(0.0));
			}
		}
		// 4
		if (comp.getName().equalsIgnoreCase("amount4")) {
			if (po.quantity4 != 0 && po.amount4 != 0) {
				double a = po.quantity4 * po.amount4;
				setValue("totalAmount4", BeanUtil.concat(a));
			} else {
				setValue("totalAmount4", BeanUtil.concat(0.0));
			}
		}
		// 5
		if (comp.getName().equalsIgnoreCase("amount5")) {
			if (po.quantity5 != 0 || po.amount5 != 0) {
				double a = po.quantity5 * po.amount5;
				setValue("totalAmount5", BeanUtil.concat(a));
			} else {
				setValue("totalAmount5", BeanUtil.concat(0.0));
			}
		}
		// 6
		if (comp.getName().equalsIgnoreCase("amount6")) {
			if (po.quantity6 != 0 || po.amount6 != 0) {
				double a = po.quantity6 * po.amount6;
				setValue("totalAmount6", BeanUtil.concat(a));
			} else {
				setValue("totalAmount6", BeanUtil.concat(0.0));
			}
		}
		// 7
		if (comp.getName().equalsIgnoreCase("amount7")) {
			if (po.quantity7 != 0 || po.amount7 != 0) {
				double a = po.quantity7 * po.amount7;
				setValue("totalAmount7", BeanUtil.concat(a));
			} else {
				setValue("totalAmount7", BeanUtil.concat(0.0));
			}
		}
		// 8
		if (comp.getName().equalsIgnoreCase("amount8")) {
			if (po.quantity8 != 0 || po.amount8 != 0) {
				double a = po.quantity8 * po.amount8;
				setValue("totalAmount8", BeanUtil.concat(a));
			} else {
				setValue("totalAmount8", BeanUtil.concat(0.0));
			}
		}
		// 9
		if (comp.getName().equalsIgnoreCase("amount9")) {
			if (po.quantity9 != 0 || po.amount9 != 0) {
				double a = po.quantity9 * po.amount9;
				setValue("totalAmount9", BeanUtil.concat(a));
			} else {
				setValue("totalAmount9", BeanUtil.concat(0.0));
			}
		}
		// 10
		if (comp.getName().equalsIgnoreCase("amount10")) {
			if (po.quantity10 != 0 || po.amount10 != 0) {
				double a = po.quantity10 * po.amount10;
				setValue("totalAmount10", BeanUtil.concat(a));
			} else {
				setValue("totalAmount10", BeanUtil.concat(0.0));
			}
		}
		// 11
		if (comp.getName().equalsIgnoreCase("amount11")) {
			if (po.quantity11 != 0 || po.amount11 != 0) {
				double a = po.quantity11 * po.amount11;
				setValue("totalAmount11", BeanUtil.concat(a));
			} else {
				setValue("totalAmount11", BeanUtil.concat(0.0));
			}
		}
		// 12
		if (comp.getName().equalsIgnoreCase("amount12")) {
			if (po.quantity12 != 0 || po.amount12 != 0) {
				double a = po.quantity12 * po.amount12;
				setValue("totalAmount12", BeanUtil.concat(a));
			} else {
				setValue("totalAmount12", BeanUtil.concat(0.0));
			}
		}
		// 13
		if (comp.getName().equalsIgnoreCase("amount13")) {
			if (po.quantity13 != 0 || po.amount13 != 0) {
				double a = po.quantity13 * po.amount13;
				setValue("totalAmount13", BeanUtil.concat(a));
			} else {
				setValue("totalAmount13", BeanUtil.concat(0.0));
			}
		}
		// 14
		if (comp.getName().equalsIgnoreCase("amount14")) {
			if (po.quantity14 != 0 || po.amount14 != 0) {
				double a = po.quantity14 * po.amount14;
				setValue("totalAmount14", BeanUtil.concat(a));
			} else {
				setValue("totalAmount14", BeanUtil.concat(0.0));
			}
		}
		// 15
		if (comp.getName().equalsIgnoreCase("amount15")) {
			if (po.quantity15 != 0 || po.amount15 != 0) {
				double a = po.quantity15 * po.amount15;
				setValue("totalAmount15", BeanUtil.concat(a));
			} else {
				setValue("totalAmount15", BeanUtil.concat(0.0));
			}
		}
		// 16
		if (comp.getName().equalsIgnoreCase("amount16")) {
			if (po.quantity16 != 0 || po.amount16 != 0) {
				double a = po.quantity16 * po.amount16;
				setValue("totalAmount16", BeanUtil.concat(a));
			} else {
				setValue("totalAmount16", BeanUtil.concat(0.0));
			}
		}
		// 17
		if (comp.getName().equalsIgnoreCase("amount17")) {
			if (po.quantity17 != 0 || po.amount17 != 0) {
				double a = po.quantity17 * po.amount17;
				setValue("totalAmount17", BeanUtil.concat(a));
			} else {
				setValue("totalAmount17", BeanUtil.concat(0.0));
			}
		}
		// 18
		if (comp.getName().equalsIgnoreCase("amount18")) {
			if (po.quantity18 != 0 || po.amount18 != 0) {
				double a = po.quantity18 * po.amount18;
				setValue("totalAmount18", BeanUtil.concat(a));
			} else {
				setValue("totalAmount18", BeanUtil.concat(0.0));
			}
		}
		// 19
		if (comp.getName().equalsIgnoreCase("amount19")) {
			if (po.quantity19 != 0 || po.amount19 != 0) {
				double a = po.quantity19 * po.amount19;
				setValue("totalAmount19", BeanUtil.concat(a));
			} else {
				setValue("totalAmount19", BeanUtil.concat(0.0));
			}
		}
		// 20
		if (comp.getName().equalsIgnoreCase("amount20")) {
			if (po.quantity20 != 0 || po.amount20 != 0) {
				double a = po.quantity20 * po.amount20;
				setValue("totalAmount20", BeanUtil.concat(a));
			} else {
				setValue("totalAmount20", BeanUtil.concat(0.0));
			}
		}
		// 21
		if (comp.getName().equalsIgnoreCase("amount21")) {
			if (po.quantity21 != 0 || po.amount21 != 0) {
				double a = po.quantity21 * po.amount21;
				setValue("totalAmount21", BeanUtil.concat(a));
			} else {
				setValue("totalAmount21", BeanUtil.concat(0.0));
			}
		}
		// 22
		if (comp.getName().equalsIgnoreCase("amount22")) {
			if (po.quantity22 != 0 || po.amount22 != 0) {
				double a = po.quantity22 * po.amount22;
				setValue("totalAmount22", BeanUtil.concat(a));
			} else {
				setValue("totalAmount22", BeanUtil.concat(0.0));
			}
		}
		// 23
		if (comp.getName().equalsIgnoreCase("amount23")) {
			if (po.quantity23 != 0 || po.amount23 != 0) {
				double a = po.quantity23 * po.amount23;
				setValue("totalAmount23", BeanUtil.concat(a));
			} else {
				setValue("totalAmount23", BeanUtil.concat(0.0));
			}
		}
		// 24
		if (comp.getName().equalsIgnoreCase("amount24")) {
			if (po.quantity24 != 0 || po.amount24 != 0) {
				double a = po.quantity24 * po.amount24;
				setValue("totalAmount24", BeanUtil.concat(a));
			} else {
				setValue("totalAmount24", BeanUtil.concat(0.0));
			}
		}
		// 25
		if (comp.getName().equalsIgnoreCase("amount25")) {
			if (po.quantity25 != 0 || po.amount25 != 0) {
				double a = po.quantity25 * po.amount25;
				setValue("totalAmount25", BeanUtil.concat(a));
			} else {
				setValue("totalAmount25", BeanUtil.concat(0.0));
			}
		}
		// 26
		if (comp.getName().equalsIgnoreCase("amount26")) {
			if (po.quantity26 != 0 || po.amount26 != 0) {
				double a = po.quantity26 * po.amount26;
				setValue("totalAmount26", BeanUtil.concat(a));
			} else {
				setValue("totalAmount26", BeanUtil.concat(0.0));
			}
		}
		// 27
		if (comp.getName().equalsIgnoreCase("amount27")) {
			if (po.quantity27 != 0 || po.amount27 != 0) {
				double a = po.quantity27 * po.amount27;
				setValue("totalAmount27", BeanUtil.concat(a));
			} else {
				setValue("totalAmount27", BeanUtil.concat(0.0));
			}
		}
		// 28
		if (comp.getName().equalsIgnoreCase("amount28")) {
			if (po.quantity28 != 0 || po.amount28 != 0) {
				double a = po.quantity28 * po.amount28;
				setValue("totalAmount28", BeanUtil.concat(a));
			} else {
				setValue("totalAmount28", BeanUtil.concat(0.0));
			}
		}
		// 29
		if (comp.getName().equalsIgnoreCase("amount29")) {
			if (po.quantity29 != 0 || po.amount29 != 0) {
				double a = po.quantity29 * po.amount29;
				setValue("totalAmount29", BeanUtil.concat(a));
			} else {
				setValue("totalAmount29", BeanUtil.concat(0.0));
			}
		}
		// 30
		if (comp.getName().equalsIgnoreCase("amount30")) {
			if (po.quantity30 != 0 || po.amount30 != 0) {
				double a = po.quantity30 * po.amount30;
				setValue("totalAmount30", BeanUtil.concat(a));
			} else {
				setValue("totalAmount30", BeanUtil.concat(0.0));
			}
		}
		// totalAmount1
		if (comp.getName().equalsIgnoreCase("totalAmount1")) {
			if (po.totalAmount1 == 0) {
				setValue("totalAmount1", BeanUtil.concat(0.0));
			}
			calculateTotal();
		}
		// totalAmount2
		if (comp.getName().equalsIgnoreCase("totalAmount2")) {
			calculateTotal();
		}
		// totalAmount3
		if (comp.getName().equalsIgnoreCase("totalAmount3")) {
			calculateTotal();
		}
		// totalAmount4
		if (comp.getName().equalsIgnoreCase("totalAmount4")) {
			calculateTotal();
		}
		// totalAmount5
		if (comp.getName().equalsIgnoreCase("totalAmount5")) {
			calculateTotal();
		}
		// totalAmount6
		if (comp.getName().equalsIgnoreCase("totalAmount6")) {
			calculateTotal();
		}
		// totalAmount7
		if (comp.getName().equalsIgnoreCase("totalAmount7")) {
			calculateTotal();
		}
		// totalAmount8
		if (comp.getName().equalsIgnoreCase("totalAmount8")) {
			calculateTotal();
		}
		// totalAmount9
		if (comp.getName().equalsIgnoreCase("totalAmount9")) {
			calculateTotal();
		}
		// totalAmount10
		if (comp.getName().equalsIgnoreCase("totalAmount10")) {
			calculateTotal();
		}
		// totalAmount11
		if (comp.getName().equalsIgnoreCase("totalAmount11")) {
			calculateTotal();
		}
		// totalAmount12
		if (comp.getName().equalsIgnoreCase("totalAmount12")) {
			calculateTotal();
		}
		// totalAmount13
		if (comp.getName().equalsIgnoreCase("totalAmount13")) {
			calculateTotal();
		}
		// totalAmount14
		if (comp.getName().equalsIgnoreCase("totalAmount14")) {
			calculateTotal();
		}
		// totalAmount15
		if (comp.getName().equalsIgnoreCase("totalAmount15")) {
			calculateTotal();
		}
		// totalAmount16
		if (comp.getName().equalsIgnoreCase("totalAmount16")) {
			calculateTotal();
		}
		// totalAmount17
		if (comp.getName().equalsIgnoreCase("totalAmount17")) {
			calculateTotal();
		}
		// totalAmount18
		if (comp.getName().equalsIgnoreCase("totalAmount18")) {
			calculateTotal();
		}
		// totalAmount19
		if (comp.getName().equalsIgnoreCase("totalAmount19")) {
			calculateTotal();
		}
		// totalAmount20
		if (comp.getName().equalsIgnoreCase("totalAmount20")) {
			calculateTotal();
		}
		// totalAmount21
		if (comp.getName().equalsIgnoreCase("totalAmount21")) {
			calculateTotal();
		}
		// totalAmount22
		if (comp.getName().equalsIgnoreCase("totalAmount22")) {
			calculateTotal();
		}
		// totalAmount23
		if (comp.getName().equalsIgnoreCase("totalAmount23")) {
			calculateTotal();
		}
		// totalAmount24
		if (comp.getName().equalsIgnoreCase("totalAmount24")) {
			calculateTotal();
		}
		// totalAmount25
		if (comp.getName().equalsIgnoreCase("totalAmount25")) {
			calculateTotal();
		}
		// totalAmount26
		if (comp.getName().equalsIgnoreCase("totalAmount26")) {
			calculateTotal();
		}
		// totalAmount27
		if (comp.getName().equalsIgnoreCase("totalAmount27")) {
			calculateTotal();
		}
		// totalAmount28
		if (comp.getName().equalsIgnoreCase("totalAmount28")) {
			calculateTotal();
		}
		// totalAmount29
		if (comp.getName().equalsIgnoreCase("totalAmount29")) {
			calculateTotal();
		}
		// totalAmount30
		if (comp.getName().equalsIgnoreCase("totalAmount30")) {
			calculateTotal();
		}

	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnRequest".equals(comp.getName())) {
			requestPO();
		} else if ("btnApprove".equals(comp.getName())) {
			approvePO();
		} else if ("btnApprove2".equals(comp.getName())) {
			approvePO2();
		} else if ("btnOverrideApproval".equals(comp.getName())) {
			overrideApproval();
		} else if ("btnPrintVoucher".equals(comp.getName())) {
			printVoucher();
		} else if ("btnReceive".equals(comp.getName())) {
			receive();
		}
	}

	private void overrideApproval() {
		String b = PanelUtil.showPromptDefaultMessage(null, "Please type reason for overriding approval.\nNote: You can change the default reason", BeanUtil.concat("OVERRIDE BY ",UserInfo.getUserName()));
		if (b!=null && !b.isEmpty()) {
			PurchaseOrder po = (PurchaseOrder) this.getBean();
			po.approvedBy = UserInfo.getUserName();
			po.approvedDate = new Date();
			po.approvedBy2 = UserInfo.getUserName();
			po.approvedDate = po.approvedDate;
			po.save();
			redisplayRecord();
		}
	}

	private void approvePO2() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		po.approvedBy2 = UserInfo.getUserName();
		po.approvedDate2 = new Date();
		po.save();
		redisplayRecord();
	}

	private void receive() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		po.receivedBy = UserInfo.getUserName();
		po.receivedDate = new Date();
		po.save();
		redisplayRecord();
		// need to distribute the line items
		updateStockCount(po);
	}

	private void printVoucher() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		Expense exp = (Expense) DBClient
				.getFirstRecord("SELECT a FROM Expense a WHERE a.seq="
						+ po.expenseId);
		if (exp == null || exp.isEmptyKey()) {
			if (showPrompt("New Voucher Printing, would you like to continue?")) {
				exp = new Expense();
				// populate exp record
				exp.amount = po.totalPOAmount;
				exp.checkDate = new Date();
				exp.approvedBy1 = UserInfo.getUserName();
				exp.expenseDate = new Date();
				exp.checkPayee = po.supplierName;
				exp.reason = BeanUtil.concat(po.remarks," [PURCHASE ORDER]");
				exp.approvedBy1 = po.approvedBy;
				exp.approvedBy2 = po.printVoucherBy;
				exp.expenseType = "PO";
				exp.chargeDepartment = "ALL";
				exp.save();

				ExpenseParticulars part = new ExpenseParticulars();
				part.recordId = exp.seq;
				part.form = "EXPENSE";
				part.amount = po.totalPOAmount;
				part.item = exp.reason;

				po.expenseId = exp.seq;
				po.printVoucherBy = UserInfo.getUserName();
				po.printVoucherDate = new Date();
				DBClient.persistBean(part, po);

				Common2View.showBeanPanel(exp.getClass().getSimpleName(), BeanUtil.concat(exp.seq));
			} else {
				return;
			}
		} else {
			if (showPrompt("Voucher already printed, would you like to change voucher details?")) {
				Common2View.showBeanPanel(exp.getClass().getSimpleName(), BeanUtil.concat(exp.seq));
			} else {
				AbstractReportTemplate.getInstance().showReportFromFileTemplate("CashVoucher", exp.seq);
			}
		}
	}

	private void approvePO() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		po.approvedBy = UserInfo.getUserName();
		po.approvedDate = new Date();
		po.save();
		redisplayRecord();
		// showMessage("APPROVE PO");
	}

	private void requestPO() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		po.requestedBy = UserInfo.getUserName();
		po.requestedDate = new Date();
		po.save();
		redisplayRecord();
		// showMessage("REQUEST PO");
	}

	public void calculateTotal() {
		PurchaseOrder po = (PurchaseOrder) this.getBean();
		setValue("totalPOAmount", po.extractTotalPOAmount());
	}

	private void updateStockCount(PurchaseOrder po) {
		List lst = new ArrayList();
		List lstBean = new ArrayList();
		for (int i = 1; i <= 30; i++) {
			String product = (String) BeanUtil.getPropertyValue(po, "product"
					+ i);
			if (product != null && !product.isEmpty()) {
				int quantity = (int) BeanUtil
						.getDoubleValue(po, BeanUtil.concat("quantity",i));
				if (quantity > 0) {
					String str = BeanUtil.concat("UPDATE Product SET totalStock=totalStock,",quantity," WHERE code='",product,"'");
					lst.add(str);

					double amount = BeanUtil.getDoubleValue(po, BeanUtil.concat("amount",i));
					double totalAmount = BeanUtil.getDoubleValue(po,BeanUtil.concat("totalAmount",i));
					PurchaseOrderItem item = new PurchaseOrderItem();
					item.purchaseOrderId = po.seq;
					item.product = product;
					item.amountPerUnit = amount;
					item.numberOfItem = quantity;
					item.totalAmount = totalAmount;
					lstBean.add(item);

					SupplierProduct sprod = new SupplierProduct();
					sprod.supplierId = po.purchaseFrom;
					sprod.productCode = product;
					sprod.minPricePerUnit = amount;
					sprod.purchaseDate = po.purchaseDate;
					lstBean.add(sprod);
				}
			}
		}
		DBClient.runBatchNative(lst);
		DBClient.persistBean(lstBean);
	}
}
