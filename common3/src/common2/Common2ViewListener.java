package common2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTree;

import org.jdesktop.application.SingleFrameApplication;

import bean.BusinessPartner;
import bean.admin.ChooseCompany;
import bean.admin.CompanyDetails;
import bean.admin.ExchangeRate;
import bean.admin.GeneralSettings;
import bean.admin.PostingPeriod;
import bean.banking.CheckForPayment;
import bean.banking.CheckRegister;
import bean.banking.CreditCardManagement;
import bean.banking.Deposit;
import bean.banking.IncomingPayment;
import bean.banking.OutgoingPayment;
import bean.financial.AccountChart;
import bean.financial.InternalReconcillation;
import bean.financial.JournalEntry;
import bean.financial.JournalVoucher;
import bean.financial.ManualReconcillation;
import bean.financial.Reconcillation;
import bean.financial.ReverseTransactions;
import bean.hr.EmployeeMasterData;
import bean.inventory.GoodsIssue;
import bean.inventory.GoodsReceipt;
import bean.inventory.InventoryTransfer;
import bean.inventory.ItemMasterData;
import bean.inventory.PriceList;
import bean.inventory.SpecialPrices;
import bean.mrp.Forecast;
import bean.mrp.MrpWizard;
import bean.mrp.OrderRecommendation;
import bean.production.BillOfMaterials;
import bean.production.IssueForProduction;
import bean.production.ProductionOrder;
import bean.production.ReceiptFromProduction;
import bean.purchasing.LandedCost;
import bean.purchasing.PurchaseCreditMemo;
import bean.purchasing.PurchaseGoodsReceipt;
import bean.purchasing.PurchaseInvoice;
import bean.purchasing.PurchaseInvoiceDownPayment;
import bean.purchasing.PurchaseOrder;
import bean.purchasing.PurchaseReturn;
import bean.sales.SalesCreditMemo;
import bean.sales.SalesDelivery;
import bean.sales.SalesInvoice;
import bean.sales.SalesInvoiceDownPayment;
import bean.sales.SalesOrder;
import bean.sales.SalesQuotation;
import bean.sales.SalesReturn;
import bean.service.CustomerEquipmentCard;
import bean.service.ServiceCall;
import bean.service.ServiceContract;
import bean.service.SolutionsKnowledgeBase;

import springbean.AAAConfig;
import util.JMenuToJTree;

public class Common2ViewListener {
	Common2View view;

	public static void main(String[] args) {
        AAAConfig.getServerInstance();
		AAAConfig.server = true;
        springbean.AAAConfig.getInstance().setTitle("SchoolSoft");
        springbean.AAAConfig.getInstance().setBootStrap("bean.Student");
		SingleFrameApplication app = new SingleFrameApplication() {
			@Override
			protected void startup() {
			}
		};
		Common2View view = new Common2View(app);
		JTree tree = JMenuToJTree.menuToJTree(view.menuBar);
		JFrame frame = new JFrame("Menu");
		frame.setLayout(new GridLayout());
		frame.add(tree);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void listen() {
		view.mntmChartOfAccounts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(AccountChart.class.getSimpleName());
		    }
		});
		view.mntmJournalEntry.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(JournalEntry.class.getSimpleName());
		    }
		});
		view.mntmJournalVoucher.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(JournalVoucher.class.getSimpleName());
		    }
		});
		view.mntmReverseTransactions.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ReverseTransactions.class.getSimpleName());
		    }
		});
		view.mntmInternalReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(InternalReconcillation.class.getSimpleName());
		    }
		});
		view.mntmSalesQuotation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesQuotation.class.getSimpleName());
		    }
		});
		view.mntmSalesOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesOrder.class.getSimpleName());
		    }
		});
		view.mntmDelivery.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesDelivery.class.getSimpleName());
		    }
		});
		view.mntmReturn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesReturn.class.getSimpleName());
		    }
		});
		view.mntmArDownPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesInvoiceDownPayment.class.getSimpleName());
		    }
		});
		view.mntmArInvoice.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesInvoice.class.getSimpleName());
		    }
		});
		view.mntmArCreditMemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesCreditMemo.class.getSimpleName());
		    }
		});
		view.mntmPurchaseOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseOrder.class.getSimpleName());
		    }
		});
		view.mntmGoodsReceiptPo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseGoodsReceipt.class.getSimpleName());
		    }
		});
		view.mntmGoodsReturn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseReturn.class.getSimpleName());
		    }
		});
		view.mntmApDownPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseInvoiceDownPayment.class.getSimpleName());
		    }
		});
		view.mntmApInvoice.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseInvoice.class.getSimpleName());
		    }
		});
		view.mntmApCreditMemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PurchaseCreditMemo.class.getSimpleName());
		    }
		});
		view.mntmLandedCosts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(LandedCost.class.getSimpleName());
		    }
		});
		view.mntmBusinessPartnerMaster.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(BusinessPartner.class.getSimpleName());
		    }
		});
		view.mntmIncomingPayments.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(IncomingPayment.class.getSimpleName());
		    }
		});
		view.mntmCheckRegister.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(CheckRegister.class.getSimpleName());
		    }
		});
		view.mntmCreditCardManagement.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(CreditCardManagement.class.getSimpleName());
		    }
		});
		view.mntmDeposit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(Deposit.class.getSimpleName());
		    }
		});
		view.mntmOutgoingPayments.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(OutgoingPayment.class.getSimpleName());
		    }
		});
		view.mntmChecksForPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(CheckForPayment.class.getSimpleName());
		    }
		});
		view.mntmReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(Reconcillation.class.getSimpleName());
		    }
		});
		view.mntmManualReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ManualReconcillation.class.getSimpleName());
		    }
		});
		view.mntmItemMasterData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ItemMasterData.class.getSimpleName());
		    }
		});
		view.mntmGoodsReceipt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(GoodsReceipt.class.getSimpleName());
		    }
		});
		view.mntmGoodsIssue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(GoodsIssue.class.getSimpleName());
		    }
		});
		view.mntmInventoryTransfer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(InventoryTransfer.class.getSimpleName());
		    }
		});
		view.mntmPriceLists.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PriceList.class.getSimpleName());
		    }
		});
		view.mntmSpecialPricesFor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SpecialPrices.class.getSimpleName());
		    }
		});
		view.mntmBillOfMaterials.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(BillOfMaterials.class.getSimpleName());
		    }
		});
		view.mntmProductionOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ProductionOrder.class.getSimpleName());
		    }
		});
		view.mntmReceiptFromProduction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ReceiptFromProduction.class.getSimpleName());
		    }
		});
		view.mntmIssueForProduction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(IssueForProduction.class.getSimpleName());
		    }
		});
		view.mntmForecasts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(Forecast.class.getSimpleName());
		    }
		});
		view.mntmMrpWizard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(MrpWizard.class.getSimpleName());
		    }
		});
		view.mntmOrderRecommendation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(OrderRecommendation.class.getSimpleName());
		    }
		});
		view.mntmServiceCall.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ServiceCall.class.getSimpleName());
		    }
		});
		view.mntmCustomerEquipmentCard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(CustomerEquipmentCard.class.getSimpleName());
		    }
		});
		view.mntmServiceContract.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ServiceContract.class.getSimpleName());
		    }
		});
		view.mntmSolutionsKnowledgeBase.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SolutionsKnowledgeBase.class.getSimpleName());
		    }
		});
		view.mntmEmployeeMasterData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(EmployeeMasterData.class.getSimpleName());
		    }
		});
		view.mntmGlAccountsAndReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("GlAccountsAndReport");
		    }
		});
		view.mntmGeneralLedgerReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("GeneralLedgerReport");
		    }
		});
		view.mntmAgingReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showReport("AgingReport");
		        Common2View.mainView.showReport("Books");
		    }
		});
		view.mntmTaxReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("TaxReport");
		    }
		});
		view.mntmBalanceSheetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("BalanceSheetReport");
		    }
		});
		view.mntmTrialBalanceReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("TrialBalanceReport");
		    }
		});
		view.mntmProfitAndLossReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ProfitAndLossReport");
		    }
		});
		view.mntmCashFlowReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("CashFlowReport");
		    }
		});
		view.mntmBudgetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("BudgetReport");
		    }
		});
		view.mntmBalanceSheetBudgetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("BalanceSheetBudgetReport");
		    }
		});
		view.mntmTrialBalanceBudgetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("TrialBalanceBudgetReport");
		    }
		});
		view.mntmProfitAndLossBudgetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ProfitAndLossBudgetReport");
		    }
		});
		view.mntmOpenItemsListSalesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("OpenItemsListSalesReport");
		    }
		});
		view.mntmDocumentDraftsSalesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("DocumentDraftsSalesReport");
		    }
		});
		view.mntmSalesAnalysisSalesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("SalesAnalysisSalesReport");
		    }
		});
		view.mntmBackorderSalesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("BackorderSalesReport");
		    }
		});
		view.mntmDiscountSalesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("DiscountSalesReport");
		    }
		});
		view.mntmOpenItemsListPurchasingReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("OpenItemsListPurchasingReport");
		    }
		});
		view.mntmDocumentDraftsPurchasingReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("DocumentDraftsPurchasingReport");
		    }
		});
		view.mntmPurchaseAnalysisReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("PurchaseAnalysisReport");
		    }
		});
		view.mntmDiscountPurchasingReports.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("DiscountPurchasingReports");
		    }
		});
		view.mntmCheckRegisterReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("CheckRegisterReport");
		    }
		});
		view.mntmItemsListReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ItemsListReport");
		    }
		});
		view.mntmLastPricesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("LastPricesReport");
		    }
		});
		view.mntmDocumentsDraftsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("DocumentsDraftsReport");
		    }
		});
		view.mntmInactiveItemsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("InactiveItemsReport");
		    }
		});
		view.mntmInventoryPostingListReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("InventoryPostingListReport");
		    }
		});
		view.mntmInventoryStatusReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("InventoryStatusReport");
		    }
		});
		view.mntmInventoryInWarehouseReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("InventoryInWarehouseReport");
		    }
		});
		view.mntmInventoryAuditReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("InventoryAuditReport");
		    }
		});
		view.mntmBillOfMaterialsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("BillOfMaterialsReport");
		    }
		});
		view.mntmOpenItemsListProductionReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("OpenItemsListProductionReport");
		    }
		});
		view.mntmServiceCallsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ServiceCallsReport");
		    }
		});
		view.mntmServiceCallsByReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ServiceCallsByReport");
		    }
		});
		view.mntmResponseTimeByReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ResponseTimeByReport");
		    }
		});
		view.mntmAverageClosureTimeReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("AverageClosureTimeReport");
		    }
		});
		view.mntmServiceContractsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ServiceContractsReport");
		    }
		});
		view.mntmCustomerEquipmentCardReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("CustomerEquipmentCardReport");
		    }
		});
		view.mntmServiceMonitorReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("ServiceMonitorReport");
		    }
		});
		view.mntmMyServiceCallsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("MyServiceCallsReport");
		    }
		});
		view.mntmMyOpenServiceReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("MyOpenServiceReport");
		    }
		});
		view.mntmMyOverdueServiceReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("MyOverdueServiceReport");
		    }
		});
		view.mntmEmployeeListReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("EmployeeListReport");
		    }
		});
		view.mntmAbsenceReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("AbsenceReport");
		    }
		});
		view.mntmPhoneBookReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showReport("PhoneBookReport");
		    }
		});
		view.mntmChooseCompany.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ChooseCompany.class.getSimpleName());
		    }
		});
		view.mnuExchangeRate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(ExchangeRate.class.getSimpleName());
		    }
		});
		view.mntmCompanyDetails.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(CompanyDetails.class.getSimpleName());
		    }
		});
		view.mntmGeneralSettings.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(GeneralSettings.class.getSimpleName());
		    }
		});
		view.mntmPostingPeriod.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(PostingPeriod.class.getSimpleName());
		    }
		});
	}
	
	public static void listenToView(Common2View view) {
		Common2ViewListener listen = new Common2ViewListener();
		listen.view = view;
		listen.listen();
	}
}
