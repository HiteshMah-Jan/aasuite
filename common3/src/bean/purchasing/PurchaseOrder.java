/*
 * PurchaseOrder.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bean.businesspartners.Supplier;
import bean.hr.Employee;
import bean.inventory.Product;

import service.util.AbstractIBean;
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

/**
 * 
 * @author pogi
 */
@Entity
@Table(name = "PurchaseOrder")
@ActionButtons( {
		@ActionButton(name = "btnRequest", label = "Request PO", parentOnly = false),
		@ActionButton(name = "btnApprove", label = "Approve PO", parentOnly = false),
		@ActionButton(name = "btnApprove2", label = "Approve PO 2", parentOnly = false),
		@ActionButton(name = "btnOverrideApproval", label = "Override", parentOnly = false),
		@ActionButton(name = "btnPrintVoucher", label = "Print Voucher", parentOnly = false),
		@ActionButton(name = "btnReceive", label = "Receive Deliveries", parentOnly = false) })
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {
		"purchaseDate", "purchaseFrom", "totalPOAmount" })
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
		@Display(name = "totalPOAmount"),
		@Display(name = "requestedBy", label = "Requested By", type = "Label", width = 200),
		@Display(name = "requestedDate", label = "Requested Date"),
		@Display(name = "approvedBy", label = "Approved By", type = "Label", width = 200),
		@Display(name = "approvedDate", label = "Approved Date"),
		@Display(name = "approvedBy2", label = "Approved By 2", type = "Label", width = 200),
		@Display(name = "approvedDate2", label = "Approved Date 2"),
		@Display(name = "printVoucherBy", label = "Voucher Printed By", type = "Label", width = 200),
		@Display(name = "printVoucherDate", label = "Voucher Printed Date"),
		@Display(name = "receivedBy", label = "Received By", type = "Label", width = 200),
		@Display(name = "receivedDate", label = "Received Date"),
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
public class PurchaseOrder extends AbstractIBean implements Serializable {

	public PurchaseOrder() {

	}

	@Id
	@Column(name = "seq", nullable = false)
	public Integer seq;
	@Column(name = "gLId")
	public int gLId;
	@Column(name = "purchaseDate")
	@Temporal(value = TemporalType.DATE)
	public Date purchaseDate;
	@Column(name = "purchaseBy")
	public int purchaseBy;
	@Column(name = "purchaseFrom")
	public int purchaseFrom;
	@Column(name = "accountType")
	public String accountType;
	@Column(name = "remarks")
	public String remarks;
	@Column(name = "totalPOAmount")
	public double totalPOAmount;
	@Column(name = "deposit")
	public double deposit;
	@Column(name = "balance")
	public double balance;
	@Column(name = "balancePaymentDate")
	@Temporal(value = TemporalType.DATE)
	public Date balancePaymentDate;
	@Column(name = "cash")
	public boolean cash;
	@Column(name = "onAccount")
	public boolean onAccount;
	@Column(name = "aDp")
	public boolean aDp;
	@Column(name = "onNotes")
	public boolean onNotes;
	@Column(name = "accountPayableId")
	public int accountPayableId;
	@Column(name = "invoiceNumber")
	public String invoiceNumber;
	@Column(name = "invoiceDate")
	@Temporal(value = TemporalType.DATE)
	public Date invoiceDate;

	@Column(name = "product1")
	public String product1;
	@Column(name = "product2")
	public String product2;
	@Column(name = "product3")
	public String product3;
	@Column(name = "product4")
	public String product4;
	@Column(name = "product5")
	public String product5;
	@Column(name = "product6")
	public String product6;
	@Column(name = "product7")
	public String product7;
	@Column(name = "product8")
	public String product8;
	@Column(name = "product9")
	public String product9;
	@Column(name = "product10")
	public String product10;
	@Column(name = "product11")
	public String product11;
	@Column(name = "product12")
	public String product12;
	@Column(name = "product13")
	public String product13;
	@Column(name = "product14")
	public String product14;
	@Column(name = "product15")
	public String product15;
	@Column(name = "product16")
	public String product16;
	@Column(name = "product17")
	public String product17;
	@Column(name = "product18")
	public String product18;
	@Column(name = "product19")
	public String product19;
	@Column(name = "product20")
	public String product20;
	@Column(name = "product21")
	public String product21;
	@Column(name = "product22")
	public String product22;
	@Column(name = "product23")
	public String product23;
	@Column(name = "product24")
	public String product24;
	@Column(name = "product25")
	public String product25;
	@Column(name = "product26")
	public String product26;
	@Column(name = "product27")
	public String product27;
	@Column(name = "product28")
	public String product28;
	@Column(name = "product29")
	public String product29;
	@Column(name = "product30")
	public String product30;

	@Column(name = "quantity1")
	public int quantity1;
	@Column(name = "quantity2")
	public int quantity2;
	@Column(name = "quantity3")
	public int quantity3;
	@Column(name = "quantity4")
	public int quantity4;
	@Column(name = "quantity5")
	public int quantity5;
	@Column(name = "quantity6")
	public int quantity6;
	@Column(name = "quantity7")
	public int quantity7;
	@Column(name = "quantity8")
	public int quantity8;
	@Column(name = "quantity9")
	public int quantity9;
	@Column(name = "quantity10")
	public int quantity10;
	@Column(name = "quantity11")
	public int quantity11;
	@Column(name = "quantity12")
	public int quantity12;
	@Column(name = "quantity13")
	public int quantity13;
	@Column(name = "quantity14")
	public int quantity14;
	@Column(name = "quantity15")
	public int quantity15;
	@Column(name = "quantity16")
	public int quantity16;
	@Column(name = "quantity17")
	public int quantity17;
	@Column(name = "quantity18")
	public int quantity18;
	@Column(name = "quantity19")
	public int quantity19;
	@Column(name = "quantity20")
	public int quantity20;
	@Column(name = "quantity21")
	public int quantity21;
	@Column(name = "quantity22")
	public int quantity22;
	@Column(name = "quantity23")
	public int quantity23;
	@Column(name = "quantity24")
	public int quantity24;
	@Column(name = "quantity25")
	public int quantity25;
	@Column(name = "quantity26")
	public int quantity26;
	@Column(name = "quantity27")
	public int quantity27;
	@Column(name = "quantity28")
	public int quantity28;
	@Column(name = "quantity29")
	public int quantity29;
	@Column(name = "quantity30")
	public int quantity30;

	@Column(name = "amount1")
	public double amount1;
	@Column(name = "amount2")
	public double amount2;
	@Column(name = "amount3")
	public double amount3;
	@Column(name = "amount4")
	public double amount4;
	@Column(name = "amount5")
	public double amount5;
	@Column(name = "amount6")
	public double amount6;
	@Column(name = "amount7")
	public double amount7;
	@Column(name = "amount8")
	public double amount8;
	@Column(name = "amount9")
	public double amount9;
	@Column(name = "amount10")
	public double amount10;
	@Column(name = "amount11")
	public double amount11;
	@Column(name = "amount12")
	public double amount12;
	@Column(name = "amount13")
	public double amount13;
	@Column(name = "amount14")
	public double amount14;
	@Column(name = "amount15")
	public double amount15;
	@Column(name = "amount16")
	public double amount16;
	@Column(name = "amount17")
	public double amount17;
	@Column(name = "amount18")
	public double amount18;
	@Column(name = "amount19")
	public double amount19;
	@Column(name = "amount20")
	public double amount20;
	@Column(name = "amount21")
	public double amount21;
	@Column(name = "amount22")
	public double amount22;
	@Column(name = "amount23")
	public double amount23;
	@Column(name = "amount24")
	public double amount24;
	@Column(name = "amount25")
	public double amount25;
	@Column(name = "amount26")
	public double amount26;
	@Column(name = "amount27")
	public double amount27;
	@Column(name = "amount28")
	public double amount28;
	@Column(name = "amount29")
	public double amount29;
	@Column(name = "amount30")
	public double amount30;

	@Column(name = "totalAmount1")
	public double totalAmount1;
	@Column(name = "totalAmount2")
	public double totalAmount2;
	@Column(name = "totalAmount3")
	public double totalAmount3;
	@Column(name = "totalAmount4")
	public double totalAmount4;
	@Column(name = "totalAmount5")
	public double totalAmount5;
	@Column(name = "totalAmount6")
	public double totalAmount6;
	@Column(name = "totalAmount7")
	public double totalAmount7;
	@Column(name = "totalAmount8")
	public double totalAmount8;
	@Column(name = "totalAmount9")
	public double totalAmount9;
	@Column(name = "totalAmount10")
	public double totalAmount10;
	@Column(name = "totalAmount11")
	public double totalAmount11;
	@Column(name = "totalAmount12")
	public double totalAmount12;
	@Column(name = "totalAmount13")
	public double totalAmount13;
	@Column(name = "totalAmount14")
	public double totalAmount14;
	@Column(name = "totalAmount15")
	public double totalAmount15;
	@Column(name = "totalAmount16")
	public double totalAmount16;
	@Column(name = "totalAmount17")
	public double totalAmount17;
	@Column(name = "totalAmount18")
	public double totalAmount18;
	@Column(name = "totalAmount19")
	public double totalAmount19;
	@Column(name = "totalAmount20")
	public double totalAmount20;
	@Column(name = "totalAmount21")
	public double totalAmount21;
	@Column(name = "totalAmount22")
	public double totalAmount22;
	@Column(name = "totalAmount23")
	public double totalAmount23;
	@Column(name = "totalAmount24")
	public double totalAmount24;
	@Column(name = "totalAmount25")
	public double totalAmount25;
	@Column(name = "totalAmount26")
	public double totalAmount26;
	@Column(name = "totalAmount27")
	public double totalAmount27;
	@Column(name = "totalAmount28")
	public double totalAmount28;
	@Column(name = "totalAmount29")
	public double totalAmount29;
	@Column(name = "totalAmount30")
	public double totalAmount30;
	@Column(name = "requestedBy")
	public String requestedBy;
	@Column(name = "approvedBy")
	public String approvedBy;
	@Column(name = "approvedDate")
	@Temporal(value = TemporalType.DATE)
	public Date approvedDate;
	@Column(name = "approvedBy2")
	public String approvedBy2;
	@Column(name = "approvedDate2")
	@Temporal(value = TemporalType.DATE)
	public Date approvedDate2;
	@Column(name = "requestedDate")
	@Temporal(value = TemporalType.DATE)
	public Date requestedDate;

	@Column(name = "receivedBy")
	public String receivedBy;
	@Column(name = "receivedDate")
	@Temporal(value = TemporalType.DATE)
	public Date receivedDate;

	@Column(name = "printVoucherBy")
	public String printVoucherBy;
	@Column(name = "printVoucherDate")
	@Temporal(value = TemporalType.DATE)
	public Date printVoucherDate;
	@Column(name = "expenseId")
	public int expenseId;

	@Column(name = "supplierName")
	public String supplierName;
	@Column(name = "purchaserName")
	public String purchaserName;

	@Override
	public void save() {
		supplierName = extractPersonName(purchaseFrom);
		purchaserName = extractPersonName(purchaseBy);
		super.save();
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getPrintVoucherBy() {
		return printVoucherBy;
	}

	public void setPrintVoucherBy(String printVoucherBy) {
		this.printVoucherBy = printVoucherBy;
	}

	public Date getPrintVoucherDate() {
		return printVoucherDate;
	}

	public void setPrintVoucherDate(Date printVoucherDate) {
		this.printVoucherDate = printVoucherDate;
	}

	public void setCash(boolean cash) {
		this.cash = cash;
	}

	public void setOnAccount(boolean onAccount) {
		this.onAccount = onAccount;
	}

	public void setADp(boolean dp) {
		aDp = dp;
	}

	public void setOnNotes(boolean onNotes) {
		this.onNotes = onNotes;
	}

	public double getAmount1() {
		return amount1;
	}

	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}

	public double getAmount10() {
		return amount10;
	}

	public void setAmount10(double amount10) {
		this.amount10 = amount10;
	}

	public double getAmount11() {
		return amount11;
	}

	public void setAmount11(double amount11) {
		this.amount11 = amount11;
	}

	public double getAmount12() {
		return amount12;
	}

	public void setAmount12(double amount12) {
		this.amount12 = amount12;
	}

	public double getAmount13() {
		return amount13;
	}

	public void setAmount13(double amount13) {
		this.amount13 = amount13;
	}

	public double getAmount14() {
		return amount14;
	}

	public void setAmount14(double amount14) {
		this.amount14 = amount14;
	}

	public double getAmount15() {
		return amount15;
	}

	public void setAmount15(double amount15) {
		this.amount15 = amount15;
	}

	public double getAmount16() {
		return amount16;
	}

	public void setAmount16(double amount16) {
		this.amount16 = amount16;
	}

	public double getAmount17() {
		return amount17;
	}

	public void setAmount17(double amount17) {
		this.amount17 = amount17;
	}

	public double getAmount18() {
		return amount18;
	}

	public void setAmount18(double amount18) {
		this.amount18 = amount18;
	}

	public double getAmount19() {
		return amount19;
	}

	public void setAmount19(double amount19) {
		this.amount19 = amount19;
	}

	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	public double getAmount20() {
		return amount20;
	}

	public void setAmount20(double amount20) {
		this.amount20 = amount20;
	}

	public double getAmount21() {
		return amount21;
	}

	public void setAmount21(double amount21) {
		this.amount21 = amount21;
	}

	public double getAmount22() {
		return amount22;
	}

	public void setAmount22(double amount22) {
		this.amount22 = amount22;
	}

	public double getAmount23() {
		return amount23;
	}

	public void setAmount23(double amount23) {
		this.amount23 = amount23;
	}

	public double getAmount24() {
		return amount24;
	}

	public void setAmount24(double amount24) {
		this.amount24 = amount24;
	}

	public double getAmount25() {
		return amount25;
	}

	public void setAmount25(double amount25) {
		this.amount25 = amount25;
	}

	public double getAmount26() {
		return amount26;
	}

	public void setAmount26(double amount26) {
		this.amount26 = amount26;
	}

	public double getAmount27() {
		return amount27;
	}

	public void setAmount27(double amount27) {
		this.amount27 = amount27;
	}

	public double getAmount28() {
		return amount28;
	}

	public void setAmount28(double amount28) {
		this.amount28 = amount28;
	}

	public double getAmount29() {
		return amount29;
	}

	public void setAmount29(double amount29) {
		this.amount29 = amount29;
	}

	public double getAmount3() {
		return amount3;
	}

	public void setAmount3(double amount3) {
		this.amount3 = amount3;
	}

	public double getAmount30() {
		return amount30;
	}

	public void setAmount30(double amount30) {
		this.amount30 = amount30;
	}

	public double getAmount4() {
		return amount4;
	}

	public void setAmount4(double amount4) {
		this.amount4 = amount4;
	}

	public double getAmount5() {
		return amount5;
	}

	public void setAmount5(double amount5) {
		this.amount5 = amount5;
	}

	public double getAmount6() {
		return amount6;
	}

	public void setAmount6(double amount6) {
		this.amount6 = amount6;
	}

	public double getAmount7() {
		return amount7;
	}

	public void setAmount7(double amount7) {
		this.amount7 = amount7;
	}

	public double getAmount8() {
		return amount8;
	}

	public void setAmount8(double amount8) {
		this.amount8 = amount8;
	}

	public double getAmount9() {
		return amount9;
	}

	public void setAmount9(double amount9) {
		this.amount9 = amount9;
	}

	public String getProduct1() {
		return product1;
	}

	public void setProduct1(String product1) {
		this.product1 = product1;
	}

	public String getProduct10() {
		return product10;
	}

	public void setProduct10(String product10) {
		this.product10 = product10;
	}

	public String getProduct11() {
		return product11;
	}

	public void setProduct11(String product11) {
		this.product11 = product11;
	}

	public String getProduct12() {
		return product12;
	}

	public void setProduct12(String product12) {
		this.product12 = product12;
	}

	public String getProduct13() {
		return product13;
	}

	public void setProduct13(String product13) {
		this.product13 = product13;
	}

	public String getProduct14() {
		return product14;
	}

	public void setProduct14(String product14) {
		this.product14 = product14;
	}

	public String getProduct15() {
		return product15;
	}

	public void setProduct15(String product15) {
		this.product15 = product15;
	}

	public String getProduct16() {
		return product16;
	}

	public void setProduct16(String product16) {
		this.product16 = product16;
	}

	public String getProduct17() {
		return product17;
	}

	public void setProduct17(String product17) {
		this.product17 = product17;
	}

	public String getProduct18() {
		return product18;
	}

	public void setProduct18(String product18) {
		this.product18 = product18;
	}

	public String getProduct19() {
		return product19;
	}

	public void setProduct19(String product19) {
		this.product19 = product19;
	}

	public String getProduct2() {
		return product2;
	}

	public void setProduct2(String product2) {
		this.product2 = product2;
	}

	public String getProduct20() {
		return product20;
	}

	public void setProduct20(String product20) {
		this.product20 = product20;
	}

	public String getProduct21() {
		return product21;
	}

	public void setProduct21(String product21) {
		this.product21 = product21;
	}

	public String getProduct22() {
		return product22;
	}

	public void setProduct22(String product22) {
		this.product22 = product22;
	}

	public String getProduct23() {
		return product23;
	}

	public void setProduct23(String product23) {
		this.product23 = product23;
	}

	public String getProduct24() {
		return product24;
	}

	public void setProduct24(String product24) {
		this.product24 = product24;
	}

	public String getProduct25() {
		return product25;
	}

	public void setProduct25(String product25) {
		this.product25 = product25;
	}

	public String getProduct26() {
		return product26;
	}

	public void setProduct26(String product26) {
		this.product26 = product26;
	}

	public String getProduct27() {
		return product27;
	}

	public void setProduct27(String product27) {
		this.product27 = product27;
	}

	public String getProduct28() {
		return product28;
	}

	public void setProduct28(String product28) {
		this.product28 = product28;
	}

	public String getProduct29() {
		return product29;
	}

	public void setProduct29(String product29) {
		this.product29 = product29;
	}

	public String getProduct3() {
		return product3;
	}

	public void setProduct3(String product3) {
		this.product3 = product3;
	}

	public String getProduct30() {
		return product30;
	}

	public void setProduct30(String product30) {
		this.product30 = product30;
	}

	public String getProduct4() {
		return product4;
	}

	public void setProduct4(String product4) {
		this.product4 = product4;
	}

	public String getProduct5() {
		return product5;
	}

	public void setProduct5(String product5) {
		this.product5 = product5;
	}

	public String getProduct6() {
		return product6;
	}

	public void setProduct6(String product6) {
		this.product6 = product6;
	}

	public String getProduct7() {
		return product7;
	}

	public void setProduct7(String product7) {
		this.product7 = product7;
	}

	public String getProduct8() {
		return product8;
	}

	public void setProduct8(String product8) {
		this.product8 = product8;
	}

	public String getProduct9() {
		return product9;
	}

	public void setProduct9(String product9) {
		this.product9 = product9;
	}

	public int getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(int purchaseBy) {
		this.purchaseBy = purchaseBy;
	}

	public int getPurchaseFrom() {
		return purchaseFrom;
	}

	public void setPurchaseFrom(int purchaseFrom) {
		this.purchaseFrom = purchaseFrom;
	}

	public int getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(int quantity1) {
		this.quantity1 = quantity1;
	}

	public int getQuantity10() {
		return quantity10;
	}

	public void setQuantity10(int quantity10) {
		this.quantity10 = quantity10;
	}

	public int getQuantity11() {
		return quantity11;
	}

	public void setQuantity11(int quantity11) {
		this.quantity11 = quantity11;
	}

	public int getQuantity12() {
		return quantity12;
	}

	public void setQuantity12(int quantity12) {
		this.quantity12 = quantity12;
	}

	public int getQuantity13() {
		return quantity13;
	}

	public void setQuantity13(int quantity13) {
		this.quantity13 = quantity13;
	}

	public int getQuantity14() {
		return quantity14;
	}

	public void setQuantity14(int quantity14) {
		this.quantity14 = quantity14;
	}

	public int getQuantity15() {
		return quantity15;
	}

	public void setQuantity15(int quantity15) {
		this.quantity15 = quantity15;
	}

	public int getQuantity16() {
		return quantity16;
	}

	public void setQuantity16(int quantity16) {
		this.quantity16 = quantity16;
	}

	public int getQuantity17() {
		return quantity17;
	}

	public void setQuantity17(int quantity17) {
		this.quantity17 = quantity17;
	}

	public int getQuantity18() {
		return quantity18;
	}

	public void setQuantity18(int quantity18) {
		this.quantity18 = quantity18;
	}

	public int getQuantity19() {
		return quantity19;
	}

	public void setQuantity19(int quantity19) {
		this.quantity19 = quantity19;
	}

	public int getQuantity2() {
		return quantity2;
	}

	public void setQuantity2(int quantity2) {
		this.quantity2 = quantity2;
	}

	public int getQuantity20() {
		return quantity20;
	}

	public void setQuantity20(int quantity20) {
		this.quantity20 = quantity20;
	}

	public int getQuantity21() {
		return quantity21;
	}

	public void setQuantity21(int quantity21) {
		this.quantity21 = quantity21;
	}

	public int getQuantity22() {
		return quantity22;
	}

	public void setQuantity22(int quantity22) {
		this.quantity22 = quantity22;
	}

	public int getQuantity23() {
		return quantity23;
	}

	public void setQuantity23(int quantity23) {
		this.quantity23 = quantity23;
	}

	public int getQuantity24() {
		return quantity24;
	}

	public void setQuantity24(int quantity24) {
		this.quantity24 = quantity24;
	}

	public int getQuantity25() {
		return quantity25;
	}

	public void setQuantity25(int quantity25) {
		this.quantity25 = quantity25;
	}

	public int getQuantity26() {
		return quantity26;
	}

	public void setQuantity26(int quantity26) {
		this.quantity26 = quantity26;
	}

	public int getQuantity27() {
		return quantity27;
	}

	public void setQuantity27(int quantity27) {
		this.quantity27 = quantity27;
	}

	public int getQuantity28() {
		return quantity28;
	}

	public void setQuantity28(int quantity28) {
		this.quantity28 = quantity28;
	}

	public int getQuantity29() {
		return quantity29;
	}

	public void setQuantity29(int quantity29) {
		this.quantity29 = quantity29;
	}

	public int getQuantity3() {
		return quantity3;
	}

	public void setQuantity3(int quantity3) {
		this.quantity3 = quantity3;
	}

	public int getQuantity30() {
		return quantity30;
	}

	public void setQuantity30(int quantity30) {
		this.quantity30 = quantity30;
	}

	public int getQuantity4() {
		return quantity4;
	}

	public void setQuantity4(int quantity4) {
		this.quantity4 = quantity4;
	}

	public int getQuantity5() {
		return quantity5;
	}

	public void setQuantity5(int quantity5) {
		this.quantity5 = quantity5;
	}

	public int getQuantity6() {
		return quantity6;
	}

	public void setQuantity6(int quantity6) {
		this.quantity6 = quantity6;
	}

	public int getQuantity7() {
		return quantity7;
	}

	public void setQuantity7(int quantity7) {
		this.quantity7 = quantity7;
	}

	public int getQuantity8() {
		return quantity8;
	}

	public void setQuantity8(int quantity8) {
		this.quantity8 = quantity8;
	}

	public int getQuantity9() {
		return quantity9;
	}

	public void setQuantity9(int quantity9) {
		this.quantity9 = quantity9;
	}

	public double getTotalAmount1() {
		return totalAmount1;
	}

	public void setTotalAmount1(double totalAmount1) {
		this.totalAmount1 = totalAmount1;
	}

	public double getTotalAmount10() {
		return totalAmount10;
	}

	public void setTotalAmount10(double totalAmount10) {
		this.totalAmount10 = totalAmount10;
	}

	public double getTotalAmount11() {
		return totalAmount11;
	}

	public void setTotalAmount11(double totalAmount11) {
		this.totalAmount11 = totalAmount11;
	}

	public double getTotalAmount12() {
		return totalAmount12;
	}

	public void setTotalAmount12(double totalAmount12) {
		this.totalAmount12 = totalAmount12;
	}

	public double getTotalAmount13() {
		return totalAmount13;
	}

	public void setTotalAmount13(double totalAmount13) {
		this.totalAmount13 = totalAmount13;
	}

	public double getTotalAmount14() {
		return totalAmount14;
	}

	public void setTotalAmount14(double totalAmount14) {
		this.totalAmount14 = totalAmount14;
	}

	public double getTotalAmount15() {
		return totalAmount15;
	}

	public void setTotalAmount15(double totalAmount15) {
		this.totalAmount15 = totalAmount15;
	}

	public double getTotalAmount16() {
		return totalAmount16;
	}

	public void setTotalAmount16(double totalAmount16) {
		this.totalAmount16 = totalAmount16;
	}

	public double getTotalAmount17() {
		return totalAmount17;
	}

	public void setTotalAmount17(double totalAmount17) {
		this.totalAmount17 = totalAmount17;
	}

	public double getTotalAmount18() {
		return totalAmount18;
	}

	public void setTotalAmount18(double totalAmount18) {
		this.totalAmount18 = totalAmount18;
	}

	public double getTotalAmount19() {
		return totalAmount19;
	}

	public void setTotalAmount19(double totalAmount19) {
		this.totalAmount19 = totalAmount19;
	}

	public double getTotalAmount2() {
		return totalAmount2;
	}

	public void setTotalAmount2(double totalAmount2) {
		this.totalAmount2 = totalAmount2;
	}

	public double getTotalAmount20() {
		return totalAmount20;
	}

	public void setTotalAmount20(double totalAmount20) {
		this.totalAmount20 = totalAmount20;
	}

	public double getTotalAmount21() {
		return totalAmount21;
	}

	public void setTotalAmount21(double totalAmount21) {
		this.totalAmount21 = totalAmount21;
	}

	public double getTotalAmount22() {
		return totalAmount22;
	}

	public void setTotalAmount22(double totalAmount22) {
		this.totalAmount22 = totalAmount22;
	}

	public double getTotalAmount23() {
		return totalAmount23;
	}

	public void setTotalAmount23(double totalAmount23) {
		this.totalAmount23 = totalAmount23;
	}

	public double getTotalAmount24() {
		return totalAmount24;
	}

	public void setTotalAmount24(double totalAmount24) {
		this.totalAmount24 = totalAmount24;
	}

	public double getTotalAmount25() {
		return totalAmount25;
	}

	public void setTotalAmount25(double totalAmount25) {
		this.totalAmount25 = totalAmount25;
	}

	public double getTotalAmount26() {
		return totalAmount26;
	}

	public void setTotalAmount26(double totalAmount26) {
		this.totalAmount26 = totalAmount26;
	}

	public double getTotalAmount27() {
		return totalAmount27;
	}

	public void setTotalAmount27(double totalAmount27) {
		this.totalAmount27 = totalAmount27;
	}

	public double getTotalAmount28() {
		return totalAmount28;
	}

	public void setTotalAmount28(double totalAmount28) {
		this.totalAmount28 = totalAmount28;
	}

	public double getTotalAmount29() {
		return totalAmount29;
	}

	public void setTotalAmount29(double totalAmount29) {
		this.totalAmount29 = totalAmount29;
	}

	public double getTotalAmount3() {
		return totalAmount3;
	}

	public void setTotalAmount3(double totalAmount3) {
		this.totalAmount3 = totalAmount3;
	}

	public double getTotalAmount30() {
		return totalAmount30;
	}

	public void setTotalAmount30(double totalAmount30) {
		this.totalAmount30 = totalAmount30;
	}

	public double getTotalAmount4() {
		return totalAmount4;
	}

	public void setTotalAmount4(double totalAmount4) {
		this.totalAmount4 = totalAmount4;
	}

	public double getTotalAmount5() {
		return totalAmount5;
	}

	public void setTotalAmount5(double totalAmount5) {
		this.totalAmount5 = totalAmount5;
	}

	public double getTotalAmount6() {
		return totalAmount6;
	}

	public void setTotalAmount6(double totalAmount6) {
		this.totalAmount6 = totalAmount6;
	}

	public double getTotalAmount7() {
		return totalAmount7;
	}

	public void setTotalAmount7(double totalAmount7) {
		this.totalAmount7 = totalAmount7;
	}

	public double getTotalAmount8() {
		return totalAmount8;
	}

	public void setTotalAmount8(double totalAmount8) {
		this.totalAmount8 = totalAmount8;
	}

	public double getTotalAmount9() {
		return totalAmount9;
	}

	public void setTotalAmount9(double totalAmount9) {
		this.totalAmount9 = totalAmount9;
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria);
	}

	public Boolean getADp() {
		return aDp;
	}

	public void setADp(Boolean aDp) {
		this.aDp = aDp;
	}

	public int getAccountPayableId() {
		return accountPayableId;
	}

	public void setAccountPayableId(int accountPayableId) {
		this.accountPayableId = accountPayableId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getBalancePaymentDate() {
		return balancePaymentDate;
	}

	public void setBalancePaymentDate(Date balancePaymentDate) {
		this.balancePaymentDate = balancePaymentDate;
	}

	public Boolean getCash() {
		return cash;
	}

	public void setCash(Boolean cash) {
		this.cash = cash;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public int getGLId() {
		return gLId;
	}

	public void setGLId(int gLId) {
		this.gLId = gLId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Boolean getOnAccount() {
		return onAccount;
	}

	public void setOnAccount(Boolean onAccount) {
		this.onAccount = onAccount;
	}

	public Boolean getOnNotes() {
		return onNotes;
	}

	public void setOnNotes(Boolean onNotes) {
		this.onNotes = onNotes;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public double getTotalPOAmount() {
		return totalPOAmount;
	}

	public void setTotalPOAmount(double totalPOAmount) {
		this.totalPOAmount = totalPOAmount;
	}

	public static void main(String[] args) {

		view(PurchaseOrder.class);
	}

	public double extractTotalPOAmount() {
		return totalAmount1 + totalAmount2 + totalAmount3 + totalAmount4
				+ totalAmount5 + totalAmount6 + totalAmount7 + totalAmount8
				+ totalAmount9 + totalAmount10 + totalAmount11 + totalAmount12
				+ totalAmount13 + totalAmount14 + totalAmount15 + totalAmount16
				+ totalAmount17 + totalAmount18 + totalAmount19 + totalAmount20
				+ totalAmount21 + totalAmount22 + totalAmount23 + totalAmount24
				+ totalAmount25 + totalAmount26 + totalAmount27 + totalAmount28
				+ totalAmount29 + totalAmount30;
	}

}
