package common2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTree;

import org.jdesktop.application.SingleFrameApplication;

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
		view.mntmPostingPeriod.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmPostingPeriod();
		    }
		});
		view.mntmGeneralSettings.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGeneralSettings();
		    }
		});
		view.mntmCompanyDetails.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCompanyDetails();
		    }
		});
		view.mnuExchangeRate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mnuExchangeRate();
		    }
		});
		view.mntmChooseCompany.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmChooseCompany();
		    }
		});
		view.mntmPhoneBook.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmPhoneBook();
		    }
		});
		view.mntmAbsenceReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmAbsenceReport();
		    }
		});
		view.mntmEmployeeList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmEmployeeList();
		    }
		});
		view.mntmEmployeeMasterData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmEmployeeMasterData();
		    }
		});
		view.mntmMyOverdueServiec.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmMyOverdueServiec();
		    }
		});
		view.mntmMyOpenService.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmMyOpenService();
		    }
		});
		view.mntmMyServiceCalls.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmMyServiceCalls();
		    }
		});
		view.mntmServiceMonitor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceMonitor();
		    }
		});
		view.mntmCustomerEquipmentCard_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCustomerEquipmentCard_1();
		    }
		});
		view.mntmServiceContracts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceContracts();
		    }
		});
		view.mntmAverageClosureTime.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmAverageClosureTime();
		    }
		});
		view.mntmResponseTimeBy.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmResponseTimeBy();
		    }
		});
		view.mntmServiceCallsBy.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceCallsBy();
		    }
		});
		view.mntmServiceCalls.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceCalls();
		    }
		});
		view.mntmSolutionsKnowledgeBase.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmSolutionsKnowledgeBase();
		    }
		});
		view.mntmServiceContract.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceContract();
		    }
		});
		view.mntmCustomerEquipmentCard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCustomerEquipmentCard();
		    }
		});
		view.mntmServiceCall.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmServiceCall();
		    }
		});
		view.mntmOrderRecommendation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmOrderRecommendation();
		    }
		});
		view.mntmMrpWizard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmMrpWizard();
		    }
		});
		view.mntmForecasts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmForecasts();
		    }
		});
		view.mntmOpenItemsList_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmOpenItemsList_2();
		    }
		});
		view.mntmBillOfMaterials_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBillOfMaterials_1();
		    }
		});
		view.mntmIssueForProduction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmIssueForProduction();
		    }
		});
		view.mntmReceiptFromProduction.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmReceiptFromProduction();
		    }
		});
		view.mntmProductionOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmProductionOrder();
		    }
		});
		view.mntmBillOfMaterials.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBillOfMaterials();
		    }
		});
		view.mntmInventoryAuditReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInventoryAuditReport();
		    }
		});
		view.mntmInventoryInWarehouse.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInventoryInWarehouse();
		    }
		});
		view.mntmInventoryStatus.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInventoryStatus();
		    }
		});
		view.mntmInventoryPostingList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInventoryPostingList();
		    }
		});
		view.mntmInactiveItems.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInactiveItems();
		    }
		});
		view.mntmDocumentsDraftsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDocumentsDraftsReport();
		    }
		});
		view.mntmLastPricesReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmLastPricesReport();
		    }
		});
		view.mntmItemsList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmItemsList();
		    }
		});
		view.mntmSpecialPricesFor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmSpecialPricesFor();
		    }
		});
		view.mntmPriceLists.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmPriceLists();
		    }
		});
		view.mntmInventoryTransfer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInventoryTransfer();
		    }
		});
		view.mntmGoodsIssue.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGoodsIssue();
		    }
		});
		view.mntmGoodsReceipt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGoodsReceipt();
		    }
		});
		view.mntmItemMasterData.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmItemMasterData();
		    }
		});
		view.mntmCheckRegisterReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCheckRegisterReport();
		    }
		});
		view.mntmManualReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmManualReconcillation();
		    }
		});
		view.mntmReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmReconcillation();
		    }
		});
		view.mntmChecksForPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmChecksForPayment();
		    }
		});
		view.mntmOutgoingPayments.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmOutgoingPayments();
		    }
		});
		view.mntmDeposit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDeposit();
		    }
		});
		view.mntmCreditCardManagement.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCreditCardManagement();
		    }
		});
		view.mntmCheckRegister.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCheckRegister();
		    }
		});
		view.mntmIncomingPayments.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmIncomingPayments();
		    }
		});
		view.mntmBusinessPartnerMaster.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBusinessPartnerMaster();
		    }
		});
		view.mntmDiscountReports.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDiscountReports();
		    }
		});
		view.mntmPurchaseAnalysis.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmPurchaseAnalysis();
		    }
		});
		view.mntmDocumentDraftsReport_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDocumentDraftsReport_1();
		    }
		});
		view.mntmOpenItemsList_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmOpenItemsList_1();
		    }
		});
		view.mntmLandedCosts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmLandedCosts();
		    }
		});
		view.mntmApCreditMemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmApCreditMemo();
		    }
		});
		view.mntmApInvoice.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmApInvoice();
		    }
		});
		view.mntmApDownPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmApDownPayment();
		    }
		});
		view.mntmGoodsReturn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGoodsReturn();
		    }
		});
		view.mntmGoodsReceiptPo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGoodsReceiptPo();
		    }
		});
		view.mntmPurchaseOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmPurchaseOrder();
		    }
		});
		view.mntmDiscountReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDiscountReport();
		    }
		});
		view.mntmBackorder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBackorder();
		    }
		});
		view.mntmSalesAnalysis.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmSalesAnalysis();
		    }
		});
		view.mntmDocumentDraftsReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDocumentDraftsReport();
		    }
		});
		view.mntmOpenItemsList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmOpenItemsList();
		    }
		});
		view.mntmArCreditMemo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmArCreditMemo();
		    }
		});
		view.mntmArInvoice.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmArInvoice();
		    }
		});
		view.mntmArDownPayment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmArDownPayment();
		    }
		});
		view.mntmReturn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmReturn();
		    }
		});
		view.mntmDelivery.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmDelivery();
		    }
		});
		view.mntmSalesOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmSalesOrder();
		    }
		});
		view.mntmSalesQuotation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmSalesQuotation();
		    }
		});
		view.mntmProfitAndLoss_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmProfitAndLoss_1();
		    }
		});
		view.mntmTrialBalanceBudget.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmTrialBalanceBudget();
		    }
		});
		view.mntmBalanceSheetBudget.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBalanceSheetBudget();
		    }
		});
		view.mntmBudgetReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBudgetReport();
		    }
		});
		view.mntmCashFlow.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmCashFlow();
		    }
		});
		view.mntmProfitAndLoss.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmProfitAndLoss();
		    }
		});
		view.mntmTrialBalance.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmTrialBalance();
		    }
		});
		view.mntmBalanceSheet.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmBalanceSheet();
		    }
		});
		view.mntmTaxReport.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmTaxReport();
		    }
		});
		view.mntmAging.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmAging();
		    }
		});
		view.mntmGeneralLedger.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGeneralLedger();
		    }
		});
		view.mntmGlAccountsAnd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmGlAccountsAnd();
		    }
		});
		view.mntmInternalReconcillation.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmInternalReconcillation();
		    }
		});
		view.mntmReverseTransactions.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmReverseTransactions();
		    }
		});
		view.mntmJournalVoucher.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmJournalVoucher();
		    }
		});
		view.mntmJournalEntry.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmJournalEntry();
		    }
		});
		view.mntmChartOfAccounts.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        mntmChartOfAccounts();
		    }
		});
	}
	
	public static void listenToView(Common2View view) {
		Common2ViewListener listen = new Common2ViewListener();
		listen.view = view;
		listen.listen();
	}
	private void mntmChartOfAccounts() {
	}
	private void mntmJournalEntry() {
	}
	private void mntmJournalVoucher() {
	}
	private void mntmReverseTransactions() {
	}
	private void mntmInternalReconcillation() {
	}
	private void mntmGlAccountsAnd() {
	}
	private void mntmGeneralLedger() {
	}
	private void mntmAging() {
	}
	private void mntmTaxReport() {
	}
	private void mntmBalanceSheet() {
	}
	private void mntmTrialBalance() {
	}
	private void mntmProfitAndLoss() {
	}
	private void mntmCashFlow() {
	}
	private void mntmBudgetReport() {
	}
	private void mntmBalanceSheetBudget() {
	}
	private void mntmTrialBalanceBudget() {
	}
	private void mntmProfitAndLoss_1() {
	}
	private void mntmSalesQuotation() {
	}
	private void mntmSalesOrder() {
	}
	private void mntmDelivery() {
	}
	private void mntmReturn() {
	}
	private void mntmArDownPayment() {
	}
	private void mntmArInvoice() {
	}
	private void mntmArCreditMemo() {
	}
	private void mntmOpenItemsList() {
	}
	private void mntmDocumentDraftsReport() {
	}
	private void mntmSalesAnalysis() {
	}
	private void mntmBackorder() {
	}
	private void mntmDiscountReport() {
	}
	private void mntmPurchaseOrder() {
	}
	private void mntmGoodsReceiptPo() {
	}
	private void mntmGoodsReturn() {
	}
	private void mntmApDownPayment() {
	}
	private void mntmApInvoice() {
	}
	private void mntmApCreditMemo() {
	}
	private void mntmLandedCosts() {
	}
	private void mntmOpenItemsList_1() {
	}
	private void mntmDocumentDraftsReport_1() {
	}
	private void mntmPurchaseAnalysis() {
	}
	private void mntmDiscountReports() {
	}
	private void mntmBusinessPartnerMaster() {
	}
	private void mntmIncomingPayments() {
	}
	private void mntmCheckRegister() {
	}
	private void mntmCreditCardManagement() {
	}
	private void mntmDeposit() {
	}
	private void mntmOutgoingPayments() {
	}
	private void mntmChecksForPayment() {
	}
	private void mntmReconcillation() {
	}
	private void mntmManualReconcillation() {
	}
	private void mntmCheckRegisterReport() {
	}
	private void mntmItemMasterData() {
	}
	private void mntmGoodsReceipt() {
	}
	private void mntmGoodsIssue() {
	}
	private void mntmInventoryTransfer() {
	}
	private void mntmPriceLists() {
	}
	private void mntmSpecialPricesFor() {
	}
	private void mntmItemsList() {
	}
	private void mntmLastPricesReport() {
	}
	private void mntmDocumentsDraftsReport() {
	}
	private void mntmInactiveItems() {
	}
	private void mntmInventoryPostingList() {
	}
	private void mntmInventoryStatus() {
	}
	private void mntmInventoryInWarehouse() {
	}
	private void mntmInventoryAuditReport() {
	}
	private void mntmBillOfMaterials() {
	}
	private void mntmProductionOrder() {
	}
	private void mntmReceiptFromProduction() {
	}
	private void mntmIssueForProduction() {
	}
	private void mntmBillOfMaterials_1() {
	}
	private void mntmOpenItemsList_2() {
	}
	private void mntmForecasts() {
	}
	private void mntmMrpWizard() {
	}
	private void mntmOrderRecommendation() {
	}
	private void mntmServiceCall() {
	}
	private void mntmCustomerEquipmentCard() {
	}
	private void mntmServiceContract() {
	}
	private void mntmSolutionsKnowledgeBase() {
	}
	private void mntmServiceCalls() {
	}
	private void mntmServiceCallsBy() {
	}
	private void mntmResponseTimeBy() {
	}
	private void mntmAverageClosureTime() {
	}
	private void mntmServiceContracts() {
	}
	private void mntmCustomerEquipmentCard_1() {
	}
	private void mntmServiceMonitor() {
	}
	private void mntmMyServiceCalls() {
	}
	private void mntmMyOpenService() {
	}
	private void mntmMyOverdueServiec() {
	}
	private void mntmEmployeeMasterData() {
	}
	private void mntmEmployeeList() {
	}
	private void mntmAbsenceReport() {
	}
	private void mntmPhoneBook() {
	}
	private void mntmChooseCompany() {
	}
	private void mnuExchangeRate() {
	}
	private void mntmCompanyDetails() {
	}
	private void mntmGeneralSettings() {
	}
	private void mntmPostingPeriod() {
	}
}
