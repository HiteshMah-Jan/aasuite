package common2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTree;

import org.jdesktop.application.SingleFrameApplication;

import bean.sales.SalesOrder;

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
//		view.mntmChartOfAccounts.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ChartOfAccounts.class.getSimpleName());
//		    }
//		});
//		view.mntmJournalEntry.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(JournalEntry.class.getSimpleName());
//		    }
//		});
//		view.mntmJournalVoucher.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(JournalVoucher.class.getSimpleName());
//		    }
//		});
//		view.mntmReverseTransactions.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ReverseTransactions.class.getSimpleName());
//		    }
//		});
//		view.mntmInternalReconcillation.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(InternalReconcillation.class.getSimpleName());
//		    }
//		});
//		view.mntmSalesQuotation.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(SalesQuotation.class.getSimpleName());
//		    }
//		});
		view.mntmSalesOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Common2View.mainView.showBeanPanel(SalesOrder.class.getSimpleName());
		    }
		});
//		view.mntmDelivery.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(Delivery.class.getSimpleName());
//		    }
//		});
//		view.mntmReturn.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(Return.class.getSimpleName());
//		    }
//		});
//		view.mntmArDownPayment.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ArDownPayment.class.getSimpleName());
//		    }
//		});
//		view.mntmArInvoice.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ArInvoice.class.getSimpleName());
//		    }
//		});
//		view.mntmArCreditMemo.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ArCreditMemo.class.getSimpleName());
//		    }
//		});
//		view.mntmPurchaseOrder.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(PurchaseOrder.class.getSimpleName());
//		    }
//		});
//		view.mntmGoodsReceiptPo.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(GoodsReceiptPo.class.getSimpleName());
//		    }
//		});
//		view.mntmGoodsReturn.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(GoodsReturn.class.getSimpleName());
//		    }
//		});
//		view.mntmApDownPayment.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ApDownPayment.class.getSimpleName());
//		    }
//		});
//		view.mntmApInvoice.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ApInvoice.class.getSimpleName());
//		    }
//		});
//		view.mntmApCreditMemo.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ApCreditMemo.class.getSimpleName());
//		    }
//		});
//		view.mntmLandedCosts.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(LandedCosts.class.getSimpleName());
//		    }
//		});
//		view.mntmBusinessPartnerMaster.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(BusinessPartnerMaster.class.getSimpleName());
//		    }
//		});
//		view.mntmIncomingPayments.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(IncomingPayments.class.getSimpleName());
//		    }
//		});
//		view.mntmCheckRegister.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(CheckRegister.class.getSimpleName());
//		    }
//		});
//		view.mntmCreditCardManagement.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(CreditCardManagement.class.getSimpleName());
//		    }
//		});
//		view.mntmDeposit.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(Deposit.class.getSimpleName());
//		    }
//		});
//		view.mntmOutgoingPayments.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(OutgoingPayments.class.getSimpleName());
//		    }
//		});
//		view.mntmChecksForPayment.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ChecksForPayment.class.getSimpleName());
//		    }
//		});
//		view.mntmReconcillation.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(Reconcillation.class.getSimpleName());
//		    }
//		});
//		view.mntmManualReconcillation.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ManualReconcillation.class.getSimpleName());
//		    }
//		});
//		view.mntmItemMasterData.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ItemMasterData.class.getSimpleName());
//		    }
//		});
//		view.mntmGoodsReceipt.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(GoodsReceipt.class.getSimpleName());
//		    }
//		});
//		view.mntmGoodsIssue.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(GoodsIssue.class.getSimpleName());
//		    }
//		});
//		view.mntmInventoryTransfer.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(InventoryTransfer.class.getSimpleName());
//		    }
//		});
//		view.mntmPriceLists.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(PriceLists.class.getSimpleName());
//		    }
//		});
//		view.mntmSpecialPricesFor.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(SpecialPricesFor.class.getSimpleName());
//		    }
//		});
//		view.mntmBillOfMaterials.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(BillOfMaterials.class.getSimpleName());
//		    }
//		});
//		view.mntmProductionOrder.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ProductionOrder.class.getSimpleName());
//		    }
//		});
//		view.mntmReceiptFromProduction.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ReceiptFromProduction.class.getSimpleName());
//		    }
//		});
//		view.mntmIssueForProduction.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(IssueForProduction.class.getSimpleName());
//		    }
//		});
//		view.mntmForecasts.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(Forecasts.class.getSimpleName());
//		    }
//		});
//		view.mntmMrpWizard.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(MrpWizard.class.getSimpleName());
//		    }
//		});
//		view.mntmOrderRecommendation.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(OrderRecommendation.class.getSimpleName());
//		    }
//		});
//		view.mntmServiceCall.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ServiceCall.class.getSimpleName());
//		    }
//		});
//		view.mntmCustomerEquipmentCard.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(CustomerEquipmentCard.class.getSimpleName());
//		    }
//		});
//		view.mntmServiceContract.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ServiceContract.class.getSimpleName());
//		    }
//		});
//		view.mntmSolutionsKnowledgeBase.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(SolutionsKnowledgeBase.class.getSimpleName());
//		    }
//		});
//		view.mntmEmployeeMasterData.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(EmployeeMasterData.class.getSimpleName());
//		    }
//		});
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
//		view.mntmChooseCompany.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ChooseCompany.class.getSimpleName());
//		    }
//		});
//		view.mnuExchangeRate.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(ExchangeRate.class.getSimpleName());
//		    }
//		});
//		view.mntmCompanyDetails.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(CompanyDetails.class.getSimpleName());
//		    }
//		});
//		view.mntmGeneralSettings.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(GeneralSettings.class.getSimpleName());
//		    }
//		});
//		view.mntmPostingPeriod.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        Common2View.mainView.showBeanPanel(PostingPeriod.class.getSimpleName());
//		    }
//		});
	}
	
	public static void listenToView(Common2View view) {
		Common2ViewListener listen = new Common2ViewListener();
		listen.view = view;
		listen.listen();
	}
}
