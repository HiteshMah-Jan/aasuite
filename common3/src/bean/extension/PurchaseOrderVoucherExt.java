/*
 * PurchaseOrder.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import bean.businesspartners.Supplier;
import bean.hr.Employee;
import bean.inventory.Product;
import bean.purchasing.PurchaseOrder;

/**
 * 
 * @author pogi
 */
@ActionButtons( {
	@ActionButton(name = "btnPrintVoucher", label = "Print Voucher", parentOnly = false) })
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {
		"purchaseDate", "purchaseFrom", "totalPOAmount" }, select="SELECT a FROM PurchaseOrder a WHERE a.approvedBy2 IS NOT NULL")
@ChildRecords(value = {}, info = {
		@ParentAddInfo(title = "Item 1-10", gridCount = 8, displayFields = { "" }, hideGroup = "1,2"),
		@ParentAddInfo(title = "Item 11-20", gridCount = 8, displayFields = { "" }, hideGroup = "0,2"),
		@ParentAddInfo(title = "Item 21-30", gridCount = 8, displayFields = { "" }, hideGroup = "0,1") })
@DisplayGroups( {
		@DisplayGroup(title = "Items 1-10", gridCount = 8, fields = {
				"product1", "quantity1", "amount1", "totalAmount1", "product2",
				"quantity2", "amount2", "totalAmount2", "product3",
				"quantity3", "amount3", "totalAmount3", "product4",
				"quantity4", "amount4", "totalAmount4", "product5",
				"quantity5", "amount5", "totalAmount5", "product6",
				"quantity6", "amount6", "totalAmount6", "product7",
				"quantity7", "amount7", "totalAmount7", "product8",
				"quantity8", "amount8", "totalAmount8", "product9",
				"quantity9", "amount9", "totalAmount9", "product10",
				"quantity10", "amount10", "totalAmount10" }, addInfoOnly = true),
		@DisplayGroup(title = "Items 11-20", gridCount = 8, fields = {
				"product11", "quantity11", "amount11", "totalAmount11",
				"product12", "quantity12", "amount12", "totalAmount12",
				"product13", "quantity13", "amount13", "totalAmount13",
				"product14", "quantity14", "amount14", "totalAmount14",
				"product15", "quantity15", "amount15", "totalAmount15",
				"product16", "quantity16", "amount16", "totalAmount16",
				"product17", "quantity17", "amount17", "totalAmount17",
				"product18", "quantity18", "amount18", "totalAmount18",
				"product19", "quantity19", "amount19", "totalAmount19",
				"product20", "quantity20", "amount20", "totalAmount20" }, addInfoOnly = true),
		@DisplayGroup(title = "Items 21-30", gridCount = 8, fields = {
				"product21", "quantity21", "amount21", "totalAmount21",
				"product22", "quantity22", "amount22", "totalAmount22",
				"product23", "quantity23", "amount23", "totalAmount23",
				"product24", "quantity24", "amount24", "totalAmount24",
				"product25", "quantity25", "amount25", "totalAmount25",
				"product26", "quantity26", "amount26", "totalAmount26",
				"product27", "quantity27", "amount27", "totalAmount27",
				"product28", "quantity28", "amount28", "totalAmount28",
				"product29", "quantity29", "amount29", "totalAmount29",
				"product30", "quantity30", "amount30", "totalAmount30" }, addInfoOnly = true)

})
@Displays( {
		// @Display(name="purchaseOrderId"),
		// @Display(name="gLId"),
		@Display(name = "purchaseDate", gridFieldWidth = 3),
		@Display(name = "purchaseBy", type = "PopSearch", linktoBean = Employee.class, gridFieldWidth = 3, width = -1),
		@Display(name = "purchaseFrom", label = "Supplier", type = "PopSearch", linktoBean = Supplier.class, gridFieldWidth = 3, width = -1),
		@Display(name = "remarks", width = 200),
		@Display(name = "totalPOAmount", width = -1),
		@Display(name = "requestedBy", label = "Requested By", width = 200, enabled=false),
		@Display(name = "requestedDate", label = "Requested Date", enabled=false),
		@Display(name = "approvedBy", label = "Approved By", width = 200, enabled=false),
		@Display(name = "approvedDate", label = "Approved Date", enabled=false),
		@Display(name = "approvedBy2", label = "Approved By 2", width = 200, enabled=false),
		@Display(name = "approvedDate2", label = "Approved Date 2", enabled=false),
		@Display(name = "printVoucherBy", label = "Voucher Printed By", width = 200, enabled=false),
		@Display(name = "printVoucherDate", label = "Voucher Printed Date", enabled=false),
		@Display(name = "receivedBy", label = "Received By", width = 200, enabled=false),
		@Display(name = "receivedDate", label = "Received Date", enabled=false),
		// @Display(name="accountType", type = "PopSearch",
		// linktoBean=AccountType.class),

		// @Display(name="deposit"),
		// @Display(name="balance"),
		// @Display(name="balancePaymentDate"),
		// @Display(name="cash"),
		// @Display(name="onAccount"),
		// @Display(name="aDp"),
		// @Display(name="onNotes"),
		// @Display(name="accountPayableId"),
		@Display(name = "invoiceNumber", width = -1),
		@Display(name = "invoiceDate"),

		@Display(name = "product1", addInfoOnly = true, labelTop = true, label = "Product", leftLabel = "1", type = "PopSearch", linktoBean = Product.class, width = 200),
		@Display(name = "quantity1", addInfoOnly = true, labelTop = true, label = "Quantity", width = 60),
		@Display(name = "amount1", addInfoOnly = true, labelTop = true, label = "Amount", width = 80),
		@Display(name = "totalAmount1", addInfoOnly = true, labelTop = true, label = "TotalAmount", width = 80),

		@Display(name = "product2", addInfoOnly = true, label = "2", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity2", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount2", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount2", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product3", addInfoOnly = true, label = "3", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity3", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount3", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount3", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product4", addInfoOnly = true, label = "4", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity4", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount4", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount4", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product5", addInfoOnly = true, label = "5", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity5", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount5", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount5", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product6", addInfoOnly = true, label = "6", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity6", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount6", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount6", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product7", addInfoOnly = true, label = "7", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity7", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount7", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount7", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product8", addInfoOnly = true, label = "8", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity8", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount8", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount8", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product9", addInfoOnly = true, label = "9", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity9", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount9", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount9", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product10", addInfoOnly = true, label = "10", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity10", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount10", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount10", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product11", addInfoOnly = true, label = "11", type = "PopSearch", linktoBean = Product.class, width = 200),
		@Display(name = "quantity11", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount11", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount11", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product12", addInfoOnly = true, label = "12", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity12", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount12", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount12", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product13", addInfoOnly = true, label = "13", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity13", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount13", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount13", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product14", addInfoOnly = true, label = "14", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity14", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount14", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount14", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product15", addInfoOnly = true, label = "15", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity15", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount15", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount15", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product16", addInfoOnly = true, label = "16", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity16", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount16", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount16", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product17", addInfoOnly = true, label = "17", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity17", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount17", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount17", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product18", addInfoOnly = true, label = "18", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity18", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount18", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount18", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product19", addInfoOnly = true, label = "19", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity19", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount19", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount19", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product20", addInfoOnly = true, label = "20", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity20", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount20", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount20", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product21", addInfoOnly = true, label = "21", type = "PopSearch", linktoBean = Product.class, width = 200),
		@Display(name = "quantity21", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount21", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount21", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product22", addInfoOnly = true, label = "22", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity22", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount22", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount22", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product23", addInfoOnly = true, label = "23", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity23", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount23", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount23", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product24", addInfoOnly = true, label = "24", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity24", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount24", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount24", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product25", addInfoOnly = true, label = "25", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity25", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount25", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount25", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product26", addInfoOnly = true, label = "26", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity26", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount26", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount26", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product27", addInfoOnly = true, label = "27", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity27", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount27", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount27", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product28", addInfoOnly = true, label = "28", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity28", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount28", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount28", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product29", addInfoOnly = true, label = "29", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity29", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount29", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount29", addInfoOnly = true, hideLabel = true, width = 80),

		@Display(name = "product30", addInfoOnly = true, label = "30", type = "PopSearch", linktoBean = Product.class, width = -1),
		@Display(name = "quantity30", addInfoOnly = true, hideLabel = true, width = 60),
		@Display(name = "amount30", addInfoOnly = true, hideLabel = true, width = 80),
		@Display(name = "totalAmount30", addInfoOnly = true, hideLabel = true, width = 80)

})
@Reports( { @template.Report(reportFile = "PurchaseOrder", reportTitle = "Purchase Order", reportSql = "") })
public class PurchaseOrderVoucherExt extends PurchaseOrder {
	public static void main(String[] args) {
		view(PurchaseOrderVoucherExt.class);
	}
}
