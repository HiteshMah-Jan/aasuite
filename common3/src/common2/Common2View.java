/*
 * Common2View.java
 */
package common2;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;

import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.TemplateReader;
import template.report.AbstractReportTemplate;
import template.screen.AbstractTemplatePanel;
import template.screen.TemplateDefault;
import template.screen.TransactionPanel;
import ui.TestDBForm;
import ui.admin.ChangeLogo;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;
import bean.admin.AppConfig;

import component.AbstractPanel;
import component.ITransactionPanel;
import component.OlapRunner;

import constants.UserInfo;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

/**
 * The application's main frame.
 */
public class Common2View extends FrameView {

    public static Common2View mainView;
    public Vector list = new Vector();
	public JMenuItem mntmChooseCompany;
	public JMenuItem mnuExchangeRate;

    public static void showRule(JPanel pnl) {
        mainView.pnlBusinessRule.removeAll();
        mainView.pnlBusinessRule.add(pnl);
        mainView.pnlBusinessRule.updateUI();
        mainView.pnlBusinessRule.setVisible(true);
        mainView.splitMain.setDividerSize(2);
    }

    @SuppressWarnings("empty-statement")
    public static ITransactionPanel getTransactionPanel() {
        if (mainView == null || mainView.pnlMain == null) {
            return TransactionPanel.dummy;
        }
//        Component comp = mainView.pnlMain.getComponent(0);
        Component comp = mainView.desktop.getSelectedFrame().getContentPane().getComponent(0);
        if (comp instanceof TransactionPanel) {
            TransactionPanel pnl = (TransactionPanel) comp;
            if (pnl.getActivePanel() == null) {
                return pnl;
            } else {
                return pnl.getActivePanel();
            }
        } else if (comp instanceof AbstractPanel) {
            return (AbstractPanel) comp;
        }
        return TransactionPanel.dummy;
    }

    public Common2View(SingleFrameApplication app) {
        super(app);
        mainView = this;
        initComponents();
        btnCalc.setVisible(AppConfig.showCalcButton());
        btnConfigure.setVisible(AppConfig.showConfigureButton());
//        this.showPanel("Home", "WelcomePanel");
        showPanel("Login", "LoginPanel");
        util.AccessControlUtil.setAccessControl(menuBar);
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        mnuBookmark = new javax.swing.JMenuItem();
        mnuLogin = new javax.swing.JMenuItem();
        mnuChangePassword = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem.setText("About");
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        exitMenuItem.setText("Exit");
        
                helpMenu.setText("Help"); // NOI18N
                helpMenu.setName("helpMenu"); // NOI18N
                helpMenu.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        helpMenuActionPerformed(evt);
                    }
                });
                
                        mnuBookmark.setText("Bookmark"); // NOI18N
                        mnuBookmark.setName("mnuBookmark"); // NOI18N
                        mnuBookmark.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mnuBookmarkActionPerformed(evt);
                            }
                        });
                        helpMenu.add(mnuBookmark);
                        
                                mnuLogin.setText("Login"); // NOI18N
                                mnuLogin.setName("mnuLogin"); // NOI18N
                                mnuLogin.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        mnuLoginActionPerformed(evt);
                                    }
                                });
                                helpMenu.add(mnuLogin);
                                
                                        mnuChangePassword.setText("Change Password"); // NOI18N
                                        mnuChangePassword.setName("mnuChangePassword"); // NOI18N
                                        mnuChangePassword.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                mnuChangePasswordActionPerformed(evt);
                                            }
                                        });
                                        helpMenu.add(mnuChangePassword);
//                                                                                                                                aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
                                                                                                                                aboutMenuItem.setName("aboutMenuItem"); // NOI18N
                                                                                                                                helpMenu.add(aboutMenuItem);
                                                                                                                                
//                                                                                                                                        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
                                                                                                                                        exitMenuItem.setName("exitMenuItem"); // NOI18N
                                                                                                                                        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
                                                                                                                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                                                                                                exitMenuItemActionPerformed(evt);
                                                                                                                                            }
                                                                                                                                        });
                                                                                                                                        helpMenu.add(exitMenuItem);
                                                                                                                                        
                                                                                                                                                menuBar.add(helpMenu);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon(BeanUtil.concat("StatusBar.busyIcons[" ,i,"]"));
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        jSplitPane1.setDividerLocation(600);
        pnlBusinessRule.setVisible(false);
        splitMain.setDividerSize(0);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                list.clear();
            }
        });
        boolean b = AppConfig.isShowDataAnalyzer();
        mnuDataAnalyzer.setVisible(b);
        btnYear.setText(AppConfig.getSchoolYear());
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Common2App.getApplication().getMainFrame();
            aboutBox = new Common2AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Common2App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        mnuDataAnalyzer = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        lblClearCache = new javax.swing.JLabel();
        toolbar = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnNew.setToolTipText("New");
        btnSave = new javax.swing.JButton();
        btnSave.setToolTipText("Save");
        btnRefresh = new javax.swing.JButton();
        btnRefresh.setToolTipText("Refresh");
        btnDelete = new javax.swing.JButton();
        btnDelete.setToolTipText("Delete");
        jLabel4 = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnPrev.setToolTipText("Previous");
        btnNext = new javax.swing.JButton();
        btnNext.setToolTipText("Next");
        btnMore = new javax.swing.JButton();
        btnMore.setToolTipText("More");
        jLabel3 = new javax.swing.JLabel();
        btnCalc = new javax.swing.JButton();
        btnCalc.setToolTipText("Calculator");
        btnBack = new javax.swing.JButton();
        btnBack.setToolTipText("Back");
        jLabel2 = new javax.swing.JLabel();
        btnYear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnExit.setToolTipText("Exit");
        btnConfigure = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlSearchResult = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        pnlSearchCriteria = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        splitMain = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnlBusinessRule = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstWindow = new JList(getDataList());
        jPanel3 = new javax.swing.JPanel();
        dlgUtility = new javax.swing.JDialog();
        mnuUpdateSQL = new javax.swing.JMenuItem();
        mnuChangeLogo = new javax.swing.JMenuItem();
        mnuRuleEngine = new javax.swing.JMenuItem();
        frmMinimize = new javax.swing.JFrame();
        btnMaximize = new javax.swing.JButton();
        btnRealExit = new javax.swing.JButton();
        statusAnimationLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.GridLayout(1, 0));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(Common2View.class);

        menuBar.setName("menuBar"); // NOI18N

        mnuDataAnalyzer.setText("Data Analyzer"); // NOI18N
        mnuDataAnalyzer.setName("mnuDataAnalyzer"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getActionMap(Common2View.class, this);
        
        JMenu Modules = new JMenu("Modules");
        menuBar.add(Modules);
        
        JMenu mnFinancials = new JMenu("Financials");
        Modules.add(mnFinancials);
        
        mntmChartOfAccounts = new JMenuItem("Chart of Accounts");
        mntmChartOfAccounts.setName("mntmChartOfAccounts");
        mnFinancials.add(mntmChartOfAccounts);
        
        mntmJournalEntry = new JMenuItem("Journal Entry");
        mntmJournalEntry.setName("mntmJournalEntry");
        mnFinancials.add(mntmJournalEntry);
        
        mntmJournalVoucher = new JMenuItem("Journal Voucher");
        mntmJournalVoucher.setName("mntmJournalVoucher");
        mnFinancials.add(mntmJournalVoucher);
        
        mntmReverseTransactions = new JMenuItem("Reverse Transactions");
        mntmReverseTransactions.setName("mntmReverseTransactions");
        mnFinancials.add(mntmReverseTransactions);
        
        mntmInternalReconcillation = new JMenuItem("Internal Reconcillation");
        mntmInternalReconcillation.setName("mntmInternalReconcillation");
        mnFinancials.add(mntmInternalReconcillation);
        
        JMenu mnSalesAr = new JMenu("Sales - A/R");
        Modules.add(mnSalesAr);
        
        mntmSalesQuotation = new JMenuItem("Sales Quotation");
        mntmSalesQuotation.setName("mntmSalesQuotation");
        mnSalesAr.add(mntmSalesQuotation);
        
        mntmSalesOrder = new JMenuItem("Sales Order");
        mntmSalesOrder.setName("mntmSalesOrder");
        mnSalesAr.add(mntmSalesOrder);
        
        mntmDelivery = new JMenuItem("Delivery");
        mntmDelivery.setName("mntmDelivery");
        mnSalesAr.add(mntmDelivery);
        
        mntmReturn = new JMenuItem("Return");
        mntmReturn.setName("mntmReturn");
        mnSalesAr.add(mntmReturn);
        
        mntmArDownPayment = new JMenuItem("A/R Down Payment Invoice");
        mntmArDownPayment.setName("mntmArDownPayment");
        mnSalesAr.add(mntmArDownPayment);
        
        mntmArInvoice = new JMenuItem("A/R Invoice");
        mntmArInvoice.setName("mntmArInvoice");
        mnSalesAr.add(mntmArInvoice);
        
        mntmArCreditMemo = new JMenuItem("A/R Credit Memo");
        mntmArCreditMemo.setName("mntmArCreditMemo");
        mnSalesAr.add(mntmArCreditMemo);
        
        JMenu mnPurchasingAp = new JMenu("Purchasing - A/P");
        Modules.add(mnPurchasingAp);
        
        mntmPurchaseOrder = new JMenuItem("Purchase Order");
        mntmPurchaseOrder.setName("mntmPurchaseOrder");
        mnPurchasingAp.add(mntmPurchaseOrder);
        
        mntmGoodsReceiptPo = new JMenuItem("Goods Receipt PO");
        mntmGoodsReceiptPo.setName("mntmGoodsReceiptPo");
        mnPurchasingAp.add(mntmGoodsReceiptPo);
        
        mntmGoodsReturn = new JMenuItem("Goods Return");
        mntmGoodsReturn.setName("mntmGoodsReturn");
        mnPurchasingAp.add(mntmGoodsReturn);
        
        mntmApDownPayment = new JMenuItem("A/P Down Payment Invoice");
        mntmApDownPayment.setName("mntmApDownPayment");
        mnPurchasingAp.add(mntmApDownPayment);
        
        mntmApInvoice = new JMenuItem("A/P Invoice");
        mntmApInvoice.setName("mntmApInvoice");
        mnPurchasingAp.add(mntmApInvoice);
        
        mntmApCreditMemo = new JMenuItem("A/P Credit Memo");
        mntmApCreditMemo.setName("mntmApCreditMemo");
        mnPurchasingAp.add(mntmApCreditMemo);
        
        mntmLandedCosts = new JMenuItem("Landed Costs");
        mntmLandedCosts.setName("mntmLandedCosts");
        mnPurchasingAp.add(mntmLandedCosts);
        
        JMenu mnBusinessPartners = new JMenu("Business Partners");
        Modules.add(mnBusinessPartners);
        
        mntmBusinessPartnerMaster = new JMenuItem("Business Partner Master Data");
        mntmBusinessPartnerMaster.setName("mntmBusinessPartnerMaster");
        mnBusinessPartners.add(mntmBusinessPartnerMaster);
        
        JMenu mnBanking = new JMenu("Banking");
        Modules.add(mnBanking);
        
        mnIncomingPayments = new JMenu("Incoming Payments");
        mnBanking.add(mnIncomingPayments);
        
        mntmIncomingPayments = new JMenuItem("Incoming Payments");
        mntmIncomingPayments.setName("mntmIncomingPayments");
        mnIncomingPayments.add(mntmIncomingPayments);
        
        mntmCheckRegister = new JMenuItem("Check Register");
        mntmCheckRegister.setName("mntmCheckRegister");
        mnIncomingPayments.add(mntmCheckRegister);
        
        mntmCreditCardManagement = new JMenuItem("Credit Card Management");
        mntmCreditCardManagement.setName("mntmCreditCardManagement");
        mnIncomingPayments.add(mntmCreditCardManagement);
        
        mnDeposits = new JMenu("Deposits");
        mnBanking.add(mnDeposits);
        
        mntmDeposit = new JMenuItem("Deposit");
        mntmDeposit.setName("mntmDeposit");
        mnDeposits.add(mntmDeposit);
        
        mnOutgoingPayments = new JMenu("Outgoing Payments");
        mnBanking.add(mnOutgoingPayments);
        
        mntmOutgoingPayments = new JMenuItem("Outgoing Payments");
        mntmOutgoingPayments.setName("mntmOutgoingPayments");
        mnOutgoingPayments.add(mntmOutgoingPayments);
        
        mntmChecksForPayment = new JMenuItem("Checks for Payment");
        mntmChecksForPayment.setName("mntmChecksForPayment");
        mnOutgoingPayments.add(mntmChecksForPayment);
        
        mnBankStatementsAnd = new JMenu("Bank Statements and External Reconcilliations");
        mnBanking.add(mnBankStatementsAnd);
        
        mntmReconcillation = new JMenuItem("Reconcillation");
        mntmReconcillation.setName("mntmReconcillation");
        mnBankStatementsAnd.add(mntmReconcillation);
        
        mntmManualReconcillation = new JMenuItem("Manual Reconcillation");
        mntmManualReconcillation.setName("mntmManualReconcillation");
        mnBankStatementsAnd.add(mntmManualReconcillation);
        
        JMenu mnInventory = new JMenu("Inventory");
        Modules.add(mnInventory);
        
        mntmItemMasterData = new JMenuItem("Item Master Data");
        mntmItemMasterData.setName("mntmItemMasterData");
        mnInventory.add(mntmItemMasterData);
        
        mnInventoryTransactions = new JMenu("Inventory Transactions");
        mnInventory.add(mnInventoryTransactions);
        
        mntmGoodsReceipt = new JMenuItem("Goods Receipt");
        mntmGoodsReceipt.setName("mntmGoodsReceipt");
        mnInventoryTransactions.add(mntmGoodsReceipt);
        
        mntmGoodsIssue = new JMenuItem("Goods Issue");
        mntmGoodsIssue.setName("mntmGoodsIssue");
        mnInventoryTransactions.add(mntmGoodsIssue);
        
        mntmInventoryTransfer = new JMenuItem("Inventory Transfer");
        mntmInventoryTransfer.setName("mntmInventoryTransfer");
        mnInventoryTransactions.add(mntmInventoryTransfer);
        
        mntmPriceLists = new JMenuItem("Price Lists");
        mntmPriceLists.setName("mntmPriceLists");
        mnInventoryTransactions.add(mntmPriceLists);
        
        mntmSpecialPricesFor = new JMenuItem("Special Prices for Business Partners");
        mntmSpecialPricesFor.setName("mntmSpecialPricesFor");
        mnInventoryTransactions.add(mntmSpecialPricesFor);
        
        JMenu mnProduction = new JMenu("Production");
        Modules.add(mnProduction);
        
        mntmBillOfMaterials = new JMenuItem("Bill of Materials");
        mntmBillOfMaterials.setName("mntmBillOfMaterials");
        mnProduction.add(mntmBillOfMaterials);
        
        mntmProductionOrder = new JMenuItem("Production Order");
        mntmProductionOrder.setMnemonic(KeyEvent.VK_ENTER);
        mntmProductionOrder.setName("mntmProductionOrder");
        mnProduction.add(mntmProductionOrder);
        
        mntmReceiptFromProduction = new JMenuItem("Receipt from Production");
        mntmReceiptFromProduction.setName("mntmReceiptFromProduction");
        mnProduction.add(mntmReceiptFromProduction);
        
        mntmIssueForProduction = new JMenuItem("Issue for Production");
        mntmIssueForProduction.setName("mntmIssueForProduction");
        mnProduction.add(mntmIssueForProduction);
        
        JMenu mnMRP = new JMenu("MRP");
        Modules.add(mnMRP);
        
        mntmForecasts = new JMenuItem("Forecasts");
        mntmForecasts.setName("mntmForecasts");
        mnMRP.add(mntmForecasts);
        
        mntmMrpWizard = new JMenuItem("MRP Wizard");
        mntmMrpWizard.setName("mntmMrpWizard");
        mnMRP.add(mntmMrpWizard);
        
        mntmOrderRecommendation = new JMenuItem("Order Recommendation");
        mntmOrderRecommendation.setName("mntmOrderRecommendation");
        mnMRP.add(mntmOrderRecommendation);
        
        JMenu mnService = new JMenu("Service");
        Modules.add(mnService);
        
        mntmServiceCall = new JMenuItem("Service Call");
        mntmServiceCall.setName("mntmServiceCall");
        mnService.add(mntmServiceCall);
        
        mntmCustomerEquipmentCard = new JMenuItem("Customer Equipment Card");
        mntmCustomerEquipmentCard.setName("mntmCustomerEquipmentCard");
        mnService.add(mntmCustomerEquipmentCard);
        
        mntmServiceContract = new JMenuItem("Service Contract");
        mntmServiceContract.setName("mntmServiceContract");
        mnService.add(mntmServiceContract);
        
        mntmSolutionsKnowledgeBase = new JMenuItem("Solutions Knowledge Base");
        mntmSolutionsKnowledgeBase.setName("mntmSolutionsKnowledgeBase");
        mnService.add(mntmSolutionsKnowledgeBase);
        
        JMenu mnHumanResources = new JMenu("Human Resources");
        Modules.add(mnHumanResources);
        
        mntmEmployeeMasterData = new JMenuItem("Employee Master Data");
        mntmEmployeeMasterData.setName("mntmEmployeeMasterData");
        mnHumanResources.add(mntmEmployeeMasterData);
        
        mnReports = new JMenu("Reports");
        Modules.add(mnReports);
        
        mnFinancialReports = new JMenu("Financial Reports");
        mnReports.add(mnFinancialReports);
        
        mnAccounting = new JMenu("Accounting");
        mnFinancialReports.add(mnAccounting);
        
        mntmGlAccountsAndReport = new JMenuItem("G/L Accounts and Business Partners");
        mntmGlAccountsAndReport.setName("mntmGlAccountsAndReport");
        mnAccounting.add(mntmGlAccountsAndReport);
        
        mntmGeneralLedgerReport = new JMenuItem("General Ledger");
        mntmGeneralLedgerReport.setName("mntmGeneralLedgerReport");
        mnAccounting.add(mntmGeneralLedgerReport);
        
        mntmAgingReport = new JMenuItem("Aging");
        mntmAgingReport.setName("mntmAgingReport");
        mnAccounting.add(mntmAgingReport);
        
        mntmTaxReport = new JMenuItem("Tax Report");
        mntmTaxReport.setName("mntmTaxReport");
        mnAccounting.add(mntmTaxReport);
        
        mnNewMenu = new JMenu("Financial");
        mnFinancialReports.add(mnNewMenu);
        
        mntmBalanceSheetReport = new JMenuItem("Balance Sheet");
        mntmBalanceSheetReport.setName("mntmBalanceSheetReport");
        mnNewMenu.add(mntmBalanceSheetReport);
        
        mntmTrialBalanceReport = new JMenuItem("Trial Balance");
        mntmTrialBalanceReport.setName("mntmTrialBalanceReport");
        mnNewMenu.add(mntmTrialBalanceReport);
        
        mntmProfitAndLossReport = new JMenuItem("Profit and Loss Statement");
        mntmProfitAndLossReport.setName("mntmProfitAndLossReport");
        mnNewMenu.add(mntmProfitAndLossReport);
        
        mntmCashFlowReport = new JMenuItem("Cash Flow");
        mntmCashFlowReport.setName("mntmCashFlowReport");
        mnNewMenu.add(mntmCashFlowReport);
        
        mnNewMenu_1 = new JMenu("Budget");
        mnFinancialReports.add(mnNewMenu_1);
        
        mntmBudgetReport = new JMenuItem("Budget Report");
        mntmBudgetReport.setName("mntmBudgetReport");
        mnNewMenu_1.add(mntmBudgetReport);
        
        mntmBalanceSheetBudgetReport = new JMenuItem("Balance Sheet Budget Report");
        mntmBalanceSheetBudgetReport.setName("mntmBalanceSheetBudgetReport");
        mnNewMenu_1.add(mntmBalanceSheetBudgetReport);
        
        mntmTrialBalanceBudgetReport = new JMenuItem("Trial Balance Budget Report");
        mntmTrialBalanceBudgetReport.setName("mntmTrialBalanceBudgetReport");
        mnNewMenu_1.add(mntmTrialBalanceBudgetReport);
        
        mntmProfitAndLossBudgetReport = new JMenuItem("Profit and Loss Statement Budget Report");
        mntmProfitAndLossBudgetReport.setName("mntmProfitAndLossBudgetReport");
        mnNewMenu_1.add(mntmProfitAndLossBudgetReport);
        
        mnSalesReports = new JMenu("Sales Reports");
        mnReports.add(mnSalesReports);
        
        mntmOpenItemsListSalesReport = new JMenuItem("Open Items List");
        mntmOpenItemsListSalesReport.setName("mntmOpenItemsListSalesReport");
        mnSalesReports.add(mntmOpenItemsListSalesReport);
        
        mntmDocumentDraftsSalesReport = new JMenuItem("Document Drafts Report");
        mntmDocumentDraftsSalesReport.setName("mntmDocumentDraftsSalesReport");
        mnSalesReports.add(mntmDocumentDraftsSalesReport);
        
        mntmSalesAnalysisSalesReport = new JMenuItem("Sales Analysis");
        mntmSalesAnalysisSalesReport.setName("mntmSalesAnalysisSalesReport");
        mnSalesReports.add(mntmSalesAnalysisSalesReport);
        
        mntmBackorderSalesReport = new JMenuItem("Backorder");
        mntmBackorderSalesReport.setName("mntmBackorderSalesReport");
        mnSalesReports.add(mntmBackorderSalesReport);
        
        mntmDiscountSalesReport = new JMenuItem("Discount Report");
        mntmDiscountSalesReport.setName("mntmDiscountSalesReport");
        mnSalesReports.add(mntmDiscountSalesReport);
        
        mnPurchasingReports = new JMenu("Purchasing Reports");
        mnReports.add(mnPurchasingReports);
        
        mntmOpenItemsListPurchasingReport = new JMenuItem("Open Items List");
        mntmOpenItemsListPurchasingReport.setName("mntmOpenItemsListPurchasingReport");
        mnPurchasingReports.add(mntmOpenItemsListPurchasingReport);
        
        mntmDocumentDraftsPurchasingReport = new JMenuItem("Document Drafts Report");
        mntmDocumentDraftsPurchasingReport.setName("mntmDocumentDraftsPurchasingReport");
        mnPurchasingReports.add(mntmDocumentDraftsPurchasingReport);
        
        mntmPurchaseAnalysisReport = new JMenuItem("Purchase Analysis");
        mntmPurchaseAnalysisReport.setName("mntmPurchaseAnalysisReport");
        mnPurchasingReports.add(mntmPurchaseAnalysisReport);
        
        mntmDiscountPurchasingReports = new JMenuItem("Discount Reports");
        mntmDiscountPurchasingReports.setName("mntmDiscountPurchasingReports");
        mnPurchasingReports.add(mntmDiscountPurchasingReports);
        
        mnBankingReports = new JMenu("Banking Reports");
        mnReports.add(mnBankingReports);
        
        mntmCheckRegisterReport = new JMenuItem("Check Register Report");
        mntmCheckRegisterReport.setName("mntmCheckRegisterReport");
        mnBankingReports.add(mntmCheckRegisterReport);
        
        mnInventoryReports = new JMenu("Inventory Reports");
        mnReports.add(mnInventoryReports);
        
        mntmItemsListReport = new JMenuItem("Items List");
        mntmItemsListReport.setName("mntmItemsListReport");
        mnInventoryReports.add(mntmItemsListReport);
        
        mntmLastPricesReport = new JMenuItem("Last Prices Report");
        mntmLastPricesReport.setName("mntmLastPricesReport");
        mnInventoryReports.add(mntmLastPricesReport);
        
        mntmDocumentsDraftsReport = new JMenuItem("Documents Drafts Report");
        mntmDocumentsDraftsReport.setName("mntmDocumentsDraftsReport");
        mnInventoryReports.add(mntmDocumentsDraftsReport);
        
        mntmInactiveItemsReport = new JMenuItem("Inactive Items");
        mntmInactiveItemsReport.setName("mntmInactiveItemsReport");
        mnInventoryReports.add(mntmInactiveItemsReport);
        
        mntmInventoryPostingListReport = new JMenuItem("Inventory Posting List");
        mntmInventoryPostingListReport.setName("mntmInventoryPostingListReport");
        mnInventoryReports.add(mntmInventoryPostingListReport);
        
        mntmInventoryStatusReport = new JMenuItem("Inventory Status");
        mntmInventoryStatusReport.setName("mntmInventoryStatusReport");
        mnInventoryReports.add(mntmInventoryStatusReport);
        
        mntmInventoryInWarehouseReport = new JMenuItem("Inventory in Warehouse Report");
        mntmInventoryInWarehouseReport.setName("mntmInventoryInWarehouseReport");
        mnInventoryReports.add(mntmInventoryInWarehouseReport);
        
        mntmInventoryAuditReport = new JMenuItem("Inventory Audit Report");
        mntmInventoryAuditReport.setName("mntmInventoryAuditReport");
        mnInventoryReports.add(mntmInventoryAuditReport);
        
        mnProductionReports = new JMenu("Production Reports");
        mnReports.add(mnProductionReports);
        
        mntmBillOfMaterialsReport = new JMenuItem("Bill of Materials Report");
        mntmBillOfMaterialsReport.setName("mntmBillOfMaterialsReport");
        mnProductionReports.add(mntmBillOfMaterialsReport);
        
        mntmOpenItemsListProductionReport = new JMenuItem("Open Items List");
        mntmOpenItemsListProductionReport.setName("mntmOpenItemsListProductionReport");
        mnProductionReports.add(mntmOpenItemsListProductionReport);
        
        mnServiceReports = new JMenu("Service Reports");
        mnReports.add(mnServiceReports);
        
        mntmServiceCallsReport = new JMenuItem("Service Calls");
        mntmServiceCallsReport.setName("mntmServiceCallsReport");
        mnServiceReports.add(mntmServiceCallsReport);
        
        mntmServiceCallsByReport = new JMenuItem("Service Calls by Queue");
        mntmServiceCallsByReport.setName("mntmServiceCallsByReport");
        mnServiceReports.add(mntmServiceCallsByReport);
        
        mntmResponseTimeByReport = new JMenuItem("Response Time by Assigned to");
        mntmResponseTimeByReport.setName("mntmResponseTimeByReport");
        mnServiceReports.add(mntmResponseTimeByReport);
        
        mntmAverageClosureTimeReport = new JMenuItem("Average Closure Time");
        mntmAverageClosureTimeReport.setName("mntmAverageClosureTimeReport");
        mnServiceReports.add(mntmAverageClosureTimeReport);
        
        mntmServiceContractsReport = new JMenuItem("Service Contracts");
        mntmServiceContractsReport.setName("mntmServiceContractsReport");
        mnServiceReports.add(mntmServiceContractsReport);
        
        mntmCustomerEquipmentCardReport = new JMenuItem("Customer Equipment card Report");
        mntmCustomerEquipmentCardReport.setName("mntmCustomerEquipmentCardReport");
        mnServiceReports.add(mntmCustomerEquipmentCardReport);
        
        mntmServiceMonitorReport = new JMenuItem("Service Monitor");
        mntmServiceMonitorReport.setName("mntmServiceMonitorReport");
        mnServiceReports.add(mntmServiceMonitorReport);
        
        mntmMyServiceCallsReport = new JMenuItem("My Service Calls");
        mntmMyServiceCallsReport.setName("mntmMyServiceCallsReport");
        mnServiceReports.add(mntmMyServiceCallsReport);
        
        mntmMyOpenServiceReport = new JMenuItem("My Open Service Calls");
        mntmMyOpenServiceReport.setName("mntmMyOpenServiceReport");
        mnServiceReports.add(mntmMyOpenServiceReport);
        
        mntmMyOverdueServiceReport = new JMenuItem("My Overdue Serviec Calls");
        mntmMyOverdueServiceReport.setName("mntmMyOverdueServiceReport");
        mnServiceReports.add(mntmMyOverdueServiceReport);
        
        mnHumanResourcesReports = new JMenu("Human Resources Reports");
        mnReports.add(mnHumanResourcesReports);
        
        mntmEmployeeListReport = new JMenuItem("Employee List");
        mntmEmployeeListReport.setName("mntmEmployeeListReport");
        mnHumanResourcesReports.add(mntmEmployeeListReport);
        
        mntmAbsenceReport = new JMenuItem("Absence Report");
        mntmAbsenceReport.setName("mntmAbsenceReport");
        mnHumanResourcesReports.add(mntmAbsenceReport);
        
        mntmPhoneBookReport = new JMenuItem("Phone Book");
        mntmPhoneBookReport.setName("mntmPhoneBookReport");
        mnHumanResourcesReports.add(mntmPhoneBookReport);
        
        JMenu mnuAdministration = new JMenu("Administration");
        menuBar.add(mnuAdministration);
        
        mnuExchangeRate = new JMenuItem("Exchange Rate");
        mnuExchangeRate.setName("mnuExchangeRate");
        mnuAdministration.add(mnuExchangeRate);
        mnuAppMenu = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuAppMenu);
        
                mnuAppMenu.setText("App Menu"); // NOI18N
                mnuAppMenu.setName("mnuAppMenu"); // NOI18N
                mnuAppMenu.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuAppMenuActionPerformed(evt);
                    }
                });
        mnuModules = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuModules);
        
                mnuModules.setText("Modules"); // NOI18N
                mnuModules.setName("mnuModules"); // NOI18N
                mnuModules.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuModulesActionPerformed(evt);
                    }
                });
        mnuConfig = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuConfig);
        
                mnuConfig.setText("Config"); // NOI18N
                mnuConfig.setName("mnuConfig"); // NOI18N
                mnuConfig.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuConfigActionPerformed(evt);
                    }
                });
        mnuCompanyConfig = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuCompanyConfig);
        
                mnuCompanyConfig.setText("Company Config"); // NOI18N
                mnuCompanyConfig.setName("mnuCompanyConfig"); // NOI18N
                mnuCompanyConfig.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuCompanyConfigActionPerformed(evt);
                    }
                });
        mnuTraining = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuTraining);
        
                mnuTraining.setText("Training"); // NOI18N
                mnuTraining.setName("mnuTraining"); // NOI18N
                mnuTraining.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuTrainingActionPerformed(evt);
                    }
                });
        mnuServices = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuServices);
        
                mnuServices.setText("Services"); // NOI18N
                mnuServices.setName("mnuServices"); // NOI18N
                mnuServices.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuServicesActionPerformed(evt);
                    }
                });
        mnuUserStation = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuUserStation);
        
                mnuUserStation.setText("User Station"); // NOI18N
                mnuUserStation.setName("mnuUserStation"); // NOI18N
                mnuUserStation.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuUserStationActionPerformed(evt);
                    }
                });
        mnuAdminConsole = new javax.swing.JMenuItem();
        mnuAdministration.add(mnuAdminConsole);
        
                mnuAdminConsole.setText("Admin Console"); // NOI18N
                mnuAdminConsole.setName("mnuAdminConsole"); // NOI18N
                mnuAdminConsole.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuAdminConsoleActionPerformed(evt);
                    }
                });
        
        mnSystemInitialization = new JMenu("System Initialization");
        mnuAdministration.add(mnSystemInitialization);
        mnuChangeTheme = new javax.swing.JMenuItem();
        mnSystemInitialization.add(mnuChangeTheme);
        
                mnuChangeTheme.setText("Change Theme"); // NOI18N
                mnuChangeTheme.setName("mnuChangeTheme"); // NOI18N
                mnuChangeTheme.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuChangeThemeActionPerformed(evt);
                    }
                });
        
        mntmCompanyDetails = new JMenuItem("Company Details");
        mntmCompanyDetails.setName("mntmCompanyDetails");
        mnSystemInitialization.add(mntmCompanyDetails);
        
        mntmChooseCompany = new JMenuItem("Choose Company");
        mnSystemInitialization.add(mntmChooseCompany);
        mntmChooseCompany.setName("mntmChooseCompany");
        
        mntmGeneralSettings = new JMenuItem("General Settings");
        mntmGeneralSettings.setName("mntmGeneralSettings");
        mnSystemInitialization.add(mntmGeneralSettings);
        
        mntmPostingPeriod = new JMenuItem("Posting Period");
        mntmPostingPeriod.setName("mntmPostingPeriod");
        mnSystemInitialization.add(mntmPostingPeriod);
        mnuSetupDB = new javax.swing.JMenuItem();
        mnSystemInitialization.add(mnuSetupDB);
        
                mnuSetupDB.setText("Setup DB"); // NOI18N
                mnuSetupDB.setName("mnuSetupDB"); // NOI18N
                mnuSetupDB.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        mnuSetupDBActionPerformed(evt);
                    }
                });
        mnuTestDB = new javax.swing.JMenuItem();
        mnSystemInitialization.add(mnuTestDB);
        mnuTestDB.setText("Test DB"); // NOI18N
        mnuTestDB.setName("mnuTestDB"); // NOI18N
        mnuTestDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTestDBActionPerformed(evt);
            }
        });

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setLayout(new java.awt.BorderLayout());

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N
        statusPanel.add(statusPanelSeparator, java.awt.BorderLayout.NORTH);

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusPanel.add(statusMessageLabel, java.awt.BorderLayout.WEST);

        lblClearCache.setText(resourceMap.getString("lblClearCache.text")); // NOI18N
        lblClearCache.setName("lblClearCache"); // NOI18N
        lblClearCache.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClearCacheMouseClicked(evt);
            }
        });
        statusPanel.add(lblClearCache, java.awt.BorderLayout.EAST);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setName("toolbar"); // NOI18N

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png")));
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setName("btnNew"); // NOI18N
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        toolbar.add(btnNew);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png")));
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setName("btnSave"); // NOI18N
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        toolbar.add(btnSave);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload.png")));
        btnRefresh.setFocusable(false);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setName("btnRefresh"); // NOI18N
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        toolbar.add(btnRefresh);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png")));
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        toolbar.add(btnDelete);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        toolbar.add(jLabel4);

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prevRecord.png")));
        btnPrev.setFocusable(false);
        btnPrev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrev.setName("btnPrev"); // NOI18N
        btnPrev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        toolbar.add(btnPrev);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nextRecord.png")));
        btnNext.setFocusable(false);
        btnNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNext.setName("btnNext"); // NOI18N
        btnNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        toolbar.add(btnNext);

        btnMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/more.png")));
        btnMore.setFocusable(false);
        btnMore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMore.setName("btnMore"); // NOI18N
        btnMore.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreActionPerformed(evt);
            }
        });
        toolbar.add(btnMore);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        toolbar.add(jLabel3);

        btnCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.PNG")));
        btnCalc.setFocusable(false);
        btnCalc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalc.setName("btnCalc"); // NOI18N
        btnCalc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcActionPerformed(evt);
            }
        });
        toolbar.add(btnCalc);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prev.png")));
        btnBack.setFocusable(false);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setName("btnBack"); // NOI18N
        btnBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        toolbar.add(btnBack);

        jLabel2.setText("                      "); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        toolbar.add(jLabel2);

        btnYear.setText("Year: "); // NOI18N
        btnYear.setFocusable(false);
        btnYear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnYear.setName("btnYear"); // NOI18N
        btnYear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYearActionPerformed(evt);
            }
        });
        toolbar.add(btnYear);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png")));
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setName("btnExit"); // NOI18N
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        toolbar.add(btnExit);

        btnConfigure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configure.png"))); // NOI18N
        btnConfigure.setText(resourceMap.getString("btnConfigure.text")); // NOI18N
        btnConfigure.setFocusable(false);
        btnConfigure.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfigure.setName("btnConfigure"); // NOI18N
        btnConfigure.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigureActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        pnlSearchResult.setName("pnlSearchResult"); // NOI18N
        pnlSearchResult.setLayout(new java.awt.GridLayout(1, 0));

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.setName("jButton2"); // NOI18N
        pnlSearchResult.add(jButton2);

        jTabbedPane1.addTab(resourceMap.getString("pnlSearchResult.TabConstraints.tabTitle"), pnlSearchResult); // NOI18N

        pnlSearchCriteria.setName("pnlSearchCriteria"); // NOI18N
        pnlSearchCriteria.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setName("jButton1"); // NOI18N
        pnlSearchCriteria.add(jButton1);

        jTabbedPane1.addTab(resourceMap.getString("pnlSearchCriteria.TabConstraints.tabTitle"), pnlSearchCriteria); // NOI18N

        jPanel2.add(jTabbedPane1);

        jSplitPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jSplitPane1.setDividerLocation(650);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setOneTouchExpandable(true);

        splitMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        splitMain.setDividerLocation(1000);
        splitMain.setDividerSize(1);
        splitMain.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitMain.setName("splitMain"); // NOI18N
        splitMain.setOneTouchExpandable(true);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        pnlBusinessRule.setName("pnlBusinessRule"); // NOI18N
        pnlBusinessRule.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane3.setViewportView(pnlBusinessRule);

        splitMain.setRightComponent(jScrollPane3);

        jSplitPane1.setLeftComponent(splitMain);

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane2.setDividerLocation(100);
        jSplitPane2.setDividerSize(10);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setName("jSplitPane2"); // NOI18N
        jSplitPane2.setOneTouchExpandable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstWindow.setName("lstWindow"); // NOI18N
        lstWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstWindowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstWindow);

        jPanel1.add(jScrollPane1);

        jSplitPane2.setTopComponent(jPanel1);

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout gl_jPanel3 = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(gl_jPanel3);
        gl_jPanel3.setHorizontalGroup(
            gl_jPanel3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );
        gl_jPanel3.setVerticalGroup(
            gl_jPanel3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel3);

        jSplitPane1.setRightComponent(jSplitPane2);

        dlgUtility.setName("dlgUtility"); // NOI18N
        dlgUtility.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        mnuUpdateSQL.setText(resourceMap.getString("mnuUpdateSQL.text")); // NOI18N
        mnuUpdateSQL.setName("mnuUpdateSQL"); // NOI18N
        mnuUpdateSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUpdateSQLActionPerformed(evt);
            }
        });

        mnuChangeLogo.setText(resourceMap.getString("mnuChangeLogo.text")); // NOI18N
        mnuChangeLogo.setName("mnuChangeLogo"); // NOI18N
        mnuChangeLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChangeLogoActionPerformed(evt);
            }
        });

        mnuRuleEngine.setText(resourceMap.getString("mnuRuleEngine.text")); // NOI18N
        mnuRuleEngine.setName("mnuRuleEngine"); // NOI18N
        mnuRuleEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRuleEngineActionPerformed(evt);
            }
        });

        frmMinimize.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmMinimize.setTitle(resourceMap.getString("frmMinimize.title")); // NOI18N
        frmMinimize.setAlwaysOnTop(true);
        frmMinimize.setLocationByPlatform(true);
        frmMinimize.setName("frmMinimize"); // NOI18N
        frmMinimize.setResizable(false);
        frmMinimize.getContentPane().setLayout(new java.awt.GridBagLayout());

        btnMaximize.setText(resourceMap.getString("btnMaximize.text")); // NOI18N
        btnMaximize.setName("btnMaximize"); // NOI18N
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        frmMinimize.getContentPane().add(btnMaximize, gridBagConstraints);

        btnRealExit.setText(resourceMap.getString("btnRealExit.text")); // NOI18N
        btnRealExit.setName("btnRealExit"); // NOI18N
        btnRealExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealExitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        frmMinimize.getContentPane().add(btnRealExit, gridBagConstraints);

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(600, 26));

        progressBar.setName("progressBar"); // NOI18N

        setComponent(mainPanel);
        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        
                pnlMain.setName("pnlMain"); // NOI18N
                pnlMain.setLayout(new java.awt.GridLayout(1, 0, 20, 20));
                
                        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
                        jLabel1.setName("jLabel1"); // NOI18N
                        pnlMain.add(jLabel1);
                        
//                                mainPanel.add(pnlMain);
        
        pnlDesk = new JPanel();
        mainPanel.add(pnlDesk);
        pnlDesk.setLayout(new GridLayout(1, 0, 0, 0));
        
        desktop = new JDesktopPane();
        pnlDesk.add(desktop);
        
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(toolbar);
    }// </editor-fold>//GEN-END:initComponents

    private void newIFrame(String title, JPanel pnl) {
        JInternalFrame internalFrame = new JInternalFrame(title);
        internalFrame.setResizable(true);
        internalFrame.setMaximizable(true);
        internalFrame.setIconifiable(true);
        internalFrame.setClosable(true);
        internalFrame.setBounds(0, 0, 110, 89);
        internalFrame.getContentPane().setLayout(new GridLayout());
        internalFrame.getContentPane().add(pnl);
        desktop.add(internalFrame);
		internalFrame.pack();
        internalFrame.setVisible(true);
        if (title.contains("Login")) {
        	try {
				internalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
        }
    }

private void lstWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstWindowMouseClicked
    BeanPanel value = (BeanPanel) lstWindow.getSelectedValue();
    if (value == null) {
        return;
    }
    showBeanPanel(value.bean);
}//GEN-LAST:event_lstWindowMouseClicked

private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
    getTransactionPanel().newRecord();
}//GEN-LAST:event_btnNewActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    AAAConfig.getPackageType();
    getTransactionPanel().saveRecord();
}//GEN-LAST:event_btnSaveActionPerformed

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    getTransactionPanel().deleteRecord();
}//GEN-LAST:event_btnDeleteActionPerformed

private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
    getTransactionPanel().prevRecord();
}//GEN-LAST:event_btnPrevActionPerformed

private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
    getTransactionPanel().nextRecord();
}//GEN-LAST:event_btnNextActionPerformed

private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed
    try {
        java.lang.Runtime.getRuntime().exec("calc");
    } catch (IOException ex) {
        Logger.getLogger("global").log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnCalcActionPerformed

    public void resetHome() {
    }

private void btnConfigureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigureActionPerformed
    if (pnlBusinessRule.isVisible()) {
        splitMain.setDividerSize(0);
        splitMain.setDividerLocation(1200);
    } else {
        splitMain.setDividerSize(2);
        splitMain.setDividerLocation(200);
    }
    pnlBusinessRule.setVisible(!pnlBusinessRule.isVisible());
}//GEN-LAST:event_btnConfigureActionPerformed

private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
    getTransactionPanel().refreshRecords();
}//GEN-LAST:event_btnRefreshActionPerformed

private void btnMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreActionPerformed
    getTransactionPanel().searchMoreRecords();
}//GEN-LAST:event_btnMoreActionPerformed

private void mnuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLoginActionPerformed
    showPanel("Login", "LoginPanel");
}//GEN-LAST:event_mnuLoginActionPerformed

private void helpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuActionPerformed
}//GEN-LAST:event_helpMenuActionPerformed

private void mnuAdminConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAdminConsoleActionPerformed
    showPanel("User Account Admin", "UserAccountForm");
}//GEN-LAST:event_mnuAdminConsoleActionPerformed

private void mnuChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChangePasswordActionPerformed
    showPanel("Change Password", "ChangePasswordPanel");
}//GEN-LAST:event_mnuChangePasswordActionPerformed

private void mnuChangeLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChangeLogoActionPerformed
    ChangeLogo.getInstance().showDialog();
}//GEN-LAST:event_mnuChangeLogoActionPerformed

private void mnuSetupDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSetupDBActionPerformed
    showPanel("Setup DB", "SetupDBForm");
}//GEN-LAST:event_mnuSetupDBActionPerformed

private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
    if (lstPanel.isEmpty() || lstPanel.size() <= 1) {
        PanelUtil.showErrorMessageToScreen("No more screen to show.");
    } else {
        lstPanel.remove(lstPanel.size() - 1);
        String scr = lstPanel.get(lstPanel.size() - 1);
        if (scr.contains("|")) {
            String bean = scr.substring(0, scr.indexOf("|"));
            String txt = scr.substring(bean.length() + 1);
            showBeanPanel(bean, txt, false, "");
        }
    }
}//GEN-LAST:event_btnBackActionPerformed

private void mnuAppMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAppMenuActionPerformed
    showBeanPanel("AppMenu", "");
}//GEN-LAST:event_mnuAppMenuActionPerformed

private void mnuUpdateSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateSQLActionPerformed
    showBeanPanel("UpdateSQLScript", "");
}//GEN-LAST:event_mnuUpdateSQLActionPerformed

private void mnuConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConfigActionPerformed
    showBeanPanel("AppConfig", "");
}//GEN-LAST:event_mnuConfigActionPerformed

private void mnuBookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBookmarkActionPerformed
    showBeanPanel("BookMark", "");
}//GEN-LAST:event_mnuBookmarkActionPerformed

private void mnuCompanyConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCompanyConfigActionPerformed
    showBeanPanel("CompanyConfig", "");
}//GEN-LAST:event_mnuCompanyConfigActionPerformed

boolean first;
private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//    if (PanelUtil.showPrompt(btnExit, "Click yes to minimize otherwise system will exit?")) {
	this.getFrame().setVisible(false);
    this.frmMinimize.pack();
	if (!first) {
	    PanelUtil.setCenterLocation(this.frmMinimize);
	    first = true;
	}
    this.frmMinimize.setTitle(UserInfo.getUserName());
    this.frmMinimize.setVisible(true);
//    }
//    else {
//        System.exit(0);        
//    }
}//GEN-LAST:event_btnExitActionPerformed

private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    System.exit(0);
}//GEN-LAST:event_exitMenuItemActionPerformed

private void mnuTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrainingActionPerformed
    showBeanPanel("Training", "");
}//GEN-LAST:event_mnuTrainingActionPerformed

private void mnuRuleEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRuleEngineActionPerformed
    showBeanPanel("BusinessRuleService", "");
}//GEN-LAST:event_mnuRuleEngineActionPerformed

private void mnuChangeThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChangeThemeActionPerformed
    showBeanPanel("ChangeThemeForm", "");
}//GEN-LAST:event_mnuChangeThemeActionPerformed

private void mnuUserStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUserStationActionPerformed
    showBeanPanel("UserStation", "");
}//GEN-LAST:event_mnuUserStationActionPerformed

private void btnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYearActionPerformed
//    showBeanPanel("TestDBForm", "");
    List years = AppConfig.getSchoolYears();
    String year = (String) PanelUtil.showPromptMessage(null, "Use Year", AppConfig.getSchoolYear(), years, AppConfig.getSchoolYear());
	if (year!=null) {
		UserInfo.useYear = year;
		btnYear.setText(year);
	}
}//GEN-LAST:event_btnYearActionPerformed

private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
    frmMinimize.setVisible(false);
    this.getFrame().setVisible(true);
}//GEN-LAST:event_btnMaximizeActionPerformed

private void btnRealExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealExitActionPerformed
    System.exit(0);
}//GEN-LAST:event_btnRealExitActionPerformed

private void mnuModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuModulesActionPerformed
    showBeanPanel("AclModule", "");
}//GEN-LAST:event_mnuModulesActionPerformed

private void mnuServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuServicesActionPerformed
    showBeanPanel("Services", "");
}//GEN-LAST:event_mnuServicesActionPerformed

private void mnuDataAnalyzerActionPerformed(java.awt.event.ActionEvent evt) {                                                
    if (OlapRunner.reportViewer==null) {
            OlapRunner reportViewerApp = new OlapRunner("", null);
            try {
                    reportViewerApp.show();
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    Common2View.showPanel2("Data Analyzer", OlapRunner.reportViewer.getMainPanel());
}                                               

private void mnuTestDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDataAnalyzerActionPerformed
	Common2View.showPanel2("Test DB", new TestDBForm());
}//GEN-LAST:event_mnuDataAnalyzerActionPerformed
    public static Map<String, JPanel> mapPanels = new HashMap<String, JPanel>();
    public static List<String> lstPanel = new ArrayList<String>();

    public static void showReport(String reportName) {
        JPanel pnl = new JPanel(new GridLayout());
        Common2View.mainView.newIFrame(reportName, pnl);
        try {
			Common2View.mainView.desktop.getSelectedFrame().setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    	new AbstractReportTemplate().showReportFromFileTemplate(reportName, "", pnl);
    }
    
    private final static void showPanel(String name, String clsStr) {
        JPanel pnl = mapPanels.get(name);
        if (pnl == null) {
            pnl = PanelUtil.getPanel(name, clsStr);
            mapPanels.put(name, pnl);
        }
        if (pnl!=null && pnl.getName() != null) {
            BeanPanel p = new BeanPanel(name, pnl.getName());
            boolean b = mainView.list.contains(p);
            if (!b) {
                mainView.list.add(p);
                mainView.lstWindow.updateUI();
            }
        }
        Common2View.mainView.newIFrame(PanelUtil.getTitle(name), pnl);
//        Common2View.mainView.pnlMain.removeAll();
//        Common2View.mainView.pnlMain.add(pnl);
//        Common2View.mainView.pnlMain.add(pnl);
//        Common2View.mainView.pnlMain.updateUI();
        if (pnl instanceof AbstractTemplatePanel) {
            AbstractTemplatePanel tmp = (AbstractTemplatePanel) pnl;
            Common2View.showSearch(tmp);
        }
    }

    private static void showPanel2(String name, JPanel pnl) {
        try {
            Common2View.mainView.newIFrame(PanelUtil.getTitle(name), pnl);
//            Common2View.mainView.pnlMain.removeAll();
//            Common2View.mainView.pnlMain.add(pnl);
//            Common2View.mainView.pnlMain.add(pnl);
//            Common2View.mainView.pnlMain.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog((JPanel)Common2View.getTransactionPanel(), name, "", JOptionPane.OK_OPTION);
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public final static void showBeanPanel(String bean) {
        showBeanPanel(bean, "");
    }

    public final static void showBeanPanel(String bean, component.IGetText txt) {
        if (txt != null) {
            showBeanPanel(bean, txt.getText());
        } else {
            showBeanPanel(bean, "");
        }
    }

    public final static void showBeanPanel(String bean, String txt) {
        showBeanPanel(bean, txt, true, "");
    }

    private final static void showBeanPanel(String bean, String txt, boolean add, String sql) {
        if (add) {
            lstPanel.add(BeanUtil.concat(bean,"|",txt));
        }
        try {
            if (bean == null || bean.trim().isEmpty()) {
                return;
            //this will check if the bean has a suffix Form
            }
            if (bean.endsWith("Form")) {
                showPanel(bean, bean);
                return;
            }
            AbstractTemplatePanel tmp = TemplateReader.getTemplate(PanelUtil.getBeanClass(bean));
            mainView.pnlMain.removeAll();
            mainView.pnlSearchCriteria.removeAll();
            mainView.pnlSearchResult.removeAll();
//            mainView.pnlMain.add((JPanel) tmp.getMainForm());
            mainView.newIFrame(PanelUtil.getTitle(bean), (JPanel) tmp.getMainForm());
            mainView.pnlSearchCriteria.add((JPanel) tmp.getMainSearch());
            mainView.pnlSearchResult.add((JPanel) tmp.getMainSearchResult());
            mainView.pnlMain.updateUI();
            mainView.pnlSearchCriteria.updateUI();
            mainView.pnlSearchResult.updateUI();
            mainView.getFrame().setTitle(tmp.getTitle());
            BeanPanel p = new BeanPanel(bean, tmp.getSimpleTitle());
            boolean b = mainView.list.contains(p);
            if (!b) {
                mainView.list.add(p);
                mainView.lstWindow.updateUI();
            }
            mainView.splitMain.setDividerSize(0);
            mainView.splitMain.setDividerLocation(1200);
            mainView.pnlBusinessRule.setVisible(false);
            if (txt != null && !txt.isEmpty()) {
                AbstractIBean ibean = (AbstractIBean) PanelUtil.getBeanClass(bean).newInstance();
                ibean = ibean.find(txt);
                if (tmp.list != null) {
                    tmp.list.clear();
                }
                tmp.list.add(ibean);
            }
            if (tmp.list.size() == 0) {
                tmp.newRecord();
            }
            tmp.firstRecord();
            tmp.ruleWrapper.onLoad();
            tmp.loadSql(sql);
        } catch (Exception ex) {
            Log.info("BEAN == ",bean);
            Logger.getLogger(Common2View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class BeanPanel {

        String bean;
        String title;

        BeanPanel(String bean, String title) {
            this.bean = bean;
            this.title = title;
        }

        @Override
        public String toString() {
            return BeanUtil.concat("    ",title);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof BeanPanel) {
                BeanPanel p = (BeanPanel) obj;
                if (p.bean.equals(bean)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 89 * hash + (this.bean != null ? this.bean.hashCode() : 0);
            return hash;
        }
    }

    public final static void showSearch(AbstractTemplatePanel tmp) {
        try {
            if (tmp instanceof TemplateDefault) {
                mainView.pnlSearchCriteria.removeAll();
                mainView.pnlSearchResult.removeAll();
                mainView.pnlSearchCriteria.add((JPanel) tmp.getMainSearch());
                mainView.pnlSearchResult.add((JPanel) tmp.getMainSearchResult());
                mainView.pnlSearchCriteria.updateUI();
                mainView.pnlSearchResult.updateUI();
                mainView.getFrame().setTitle(tmp.getTitle());
                mainView.splitMain.setDividerSize(0);
                mainView.splitMain.setDividerLocation(1200);
                mainView.pnlBusinessRule.setVisible(false);
                tmp.firstRecord();
            }
        } catch (Exception ex) {
            Logger.getLogger(Common2View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Vector getDataList() {
//    List<String> beans = XMLTester.getAllBeanNames();
//    for (String string : beans) {
//        list.add(string);
//    }
        return list;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCalc;
    private javax.swing.JButton btnConfigure;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMore;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnRealExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnYear;
    public static javax.swing.JDialog dlgUtility;
    private javax.swing.JFrame frmMinimize;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lblClearCache;
    public javax.swing.JList lstWindow;
    private javax.swing.JPanel mainPanel;
    public javax.swing.JMenuBar menuBar;
    public javax.swing.JMenuItem mnuAdminConsole;
    public javax.swing.JMenuItem mnuAppMenu;
    public javax.swing.JMenuItem mnuBookmark;
    public javax.swing.JMenuItem mnuChangeLogo;
    public javax.swing.JMenuItem mnuChangePassword;
    public javax.swing.JMenuItem mnuChangeTheme;
    public javax.swing.JMenuItem mnuCompanyConfig;
    public javax.swing.JMenuItem mnuConfig;
    public javax.swing.JMenuItem mnuDataAnalyzer;
    public javax.swing.JMenuItem mnuTestDB;
    public javax.swing.JMenuItem mnuLogin;
    public javax.swing.JMenuItem mnuModules;
    public javax.swing.JMenuItem mnuRuleEngine;
    public javax.swing.JMenuItem mnuServices;
    public javax.swing.JMenuItem mnuSetupDB;
    public javax.swing.JMenuItem mnuTraining;
    public javax.swing.JMenuItem mnuUpdateSQL;
    public javax.swing.JMenuItem mnuUserStation;
    private javax.swing.JPanel pnlBusinessRule;
    public javax.swing.JPanel pnlMain;
    public javax.swing.JPanel pnlSearchCriteria;
    public javax.swing.JPanel pnlSearchResult;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JSplitPane splitMain;
    private javax.swing.JLabel statusAnimationLabel;
    public javax.swing.JLabel statusMessageLabel;
    public javax.swing.JPanel statusPanel;
    public javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JPanel pnlDesk;
    private JDesktopPane desktop;
    public JMenuItem mntmReverseTransactions;
    public JMenuItem mntmInternalReconcillation;
    public JMenu mnFinancialReports;
    public JMenuItem mntmGlAccountsAndReport;
    public JMenuItem mntmGeneralLedgerReport;
    public JMenuItem mntmAgingReport;
    public JMenuItem mntmTaxReport;
    public JMenu mnAccounting;
    public JMenu mnNewMenu;
    public JMenuItem mntmNewMenuItem;
    public JMenuItem mntmBalanceSheetReport;
    public JMenuItem mntmTrialBalanceReport;
    public JMenuItem mntmProfitAndLossReport;
    public JMenuItem mntmCashFlowReport;
    public JMenu mnNewMenu_1;
    public JMenuItem mntmBudgetReport;
    public JMenuItem mntmBalanceSheetBudgetReport;
    public JMenuItem mntmTrialBalanceBudgetReport;
    public JMenuItem mntmProfitAndLossBudgetReport;
    public JMenuItem mntmSalesQuotation;
    public JMenuItem mntmSalesOrder;
    public JMenuItem mntmDelivery;
    public JMenuItem mntmReturn;
    public JMenuItem mntmArDownPayment;
    public JMenuItem mntmArInvoice;
    public JMenuItem mntmArCreditMemo;
    public JMenu mnSalesReports;
    public JMenuItem mntmOpenItemsListSalesReport;
    public JMenuItem mntmDocumentDraftsSalesReport;
    public JMenuItem mntmSalesAnalysisSalesReport;
    public JMenuItem mntmBackorderSalesReport;
    public JMenuItem mntmDiscountSalesReport;
    public JMenuItem mntmPurchaseOrder;
    public JMenuItem mntmGoodsReceiptPo;
    public JMenuItem mntmGoodsReturn;
    public JMenuItem mntmApDownPayment;
    public JMenuItem mntmApInvoice;
    public JMenuItem mntmApCreditMemo;
    public JMenuItem mntmLandedCosts;
    public JMenu mnPurchasingReports;
    public JMenuItem mntmOpenItemsListPurchasingReport;
    public JMenuItem mntmDocumentDraftsPurchasingReport;
    public JMenuItem mntmPurchaseAnalysisReport;
    public JMenuItem mntmDiscountPurchasingReports;
    public JMenuItem mntmBusinessPartnerMaster;
    public JMenu mnIncomingPayments;
    public JMenu mnDeposits;
    public JMenu mnOutgoingPayments;
    public JMenu mnBankStatementsAnd;
    public JMenu mnBankingReports;
    public JMenuItem mntmIncomingPayments;
    public JMenuItem mntmCheckRegister;
    public JMenuItem mntmCreditCardManagement;
    public JMenuItem mntmDeposit;
    public JMenuItem mntmOutgoingPayments;
    public JMenuItem mntmNewMenuItem_1;
    public JMenuItem mntmNewMenuItem_2;
    public JMenuItem mntmChecksForPayment;
    public JMenuItem mntmReconcillation;
    public JMenuItem mntmManualReconcillation;
    public JMenuItem mntmCheckRegisterReport;
    public JMenuItem mntmItemMasterData;
    public JMenu mnInventoryTransactions;
    public JMenuItem mntmGoodsReceipt;
    public JMenuItem mntmGoodsIssue;
    public JMenuItem mntmInventoryTransfer;
    public JMenuItem mntmPriceLists;
    public JMenuItem mntmSpecialPricesFor;
    public JMenu mnInventoryReports;
    public JMenuItem mntmItemsListReport;
    public JMenuItem mntmDocumentsDraftsReport;
    public JMenuItem mntmLastPricesReport;
    public JMenuItem mntmInactiveItemsReport;
    public JMenuItem mntmInventoryPostingListReport;
    public JMenuItem mntmInventoryStatusReport;
    public JMenuItem mntmInventoryInWarehouseReport;
    public JMenuItem mntmInventoryAuditReport;
    public JMenuItem mntmBillOfMaterials;
    public JMenuItem mntmProductionOrder;
    public JMenuItem mntmReceiptFromProduction;
    public JMenuItem mntmIssueForProduction;
    public JMenu mnProductionReports;
    public JMenuItem mntmBillOfMaterialsReport;
    public JMenuItem mntmOpenItemsListProductionReport;
    public JMenuItem mntmForecasts;
    public JMenuItem mntmMrpWizard;
    public JMenuItem mntmOrderRecommendation;
    public JMenuItem mntmServiceCall;
    public JMenuItem mntmCustomerEquipmentCard;
    public JMenuItem mntmServiceContract;
    public JMenuItem mntmSolutionsKnowledgeBase;
    public JMenu mnServiceReports;
    public JMenuItem mntmServiceCallsReport;
    public JMenuItem mntmServiceCallsByReport;
    public JMenuItem mntmResponseTimeByReport;
    public JMenuItem mntmAverageClosureTimeReport;
    public JMenuItem mntmServiceContractsReport;
    public JMenuItem mntmCustomerEquipmentCardReport;
    public JMenuItem mntmServiceMonitorReport;
    public JMenuItem mntmMyServiceCallsReport;
    public JMenuItem mntmMyOpenServiceReport;
    public JMenuItem mntmMyOverdueServiceReport;
    public JMenuItem mntmEmployeeMasterData;
    public JMenu mnHumanResourcesReports;
    public JMenuItem mntmEmployeeListReport;
    public JMenuItem mntmAbsenceReport;
    public JMenuItem mntmPhoneBookReport;
    public JMenu mnSystemInitialization;
    public JMenuItem mntmCompanyDetails;
    public JMenuItem mntmGeneralSettings;
    public JMenuItem mntmPostingPeriod;
	public JMenuItem mntmChartOfAccounts;
	public JMenuItem mntmJournalEntry;
	public JMenuItem mntmJournalVoucher;
	private JMenu mnReports;

    public void showAllMenu() {
        menuBar.setVisible(true);
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                menu.setVisible(true);
                Component[] menuComps = menu.getComponents();
                for (Component comp : menuComps) {
                    if (comp instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) comp;
                        item.setVisible(true);
                    }
                }
            }
        }
    }

    public void showModule(String moduleName) {
        menuBar.setVisible(true);
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                if (menu.getText().equalsIgnoreCase(moduleName)) {
                    menu.setVisible(true);
                }
            }
        }
    }

    public void showMenu(String menuTitle) {
        menuBar.setVisible(true);
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                Component[] menuComps = menu.getMenuComponents();
                for (Component comp : menuComps) {
                    if (comp instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) comp;
                        if (item.getText().equals(menuTitle)) {
                            item.setVisible(true);
                            menu.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    public void hideAllMenu() {
        menuBar.setVisible(false);
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                menu.setVisible(false);
                Component[] menuComps = menu.getMenuComponents();
                for (Component comp : menuComps) {
                    if (comp instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) comp;
                        item.setVisible(false);
                    }
                }
            }
        }
    }

    public void hideModule(String moduleName) {
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                if (menu.getText().equalsIgnoreCase(moduleName)) {
                    menu.setVisible(false);
                    Component[] menuComps = menu.getMenuComponents();
                    for (Component comp : menuComps) {
                        if (comp instanceof JMenuItem) {
                            JMenuItem item = (JMenuItem) comp;
                            item.setVisible(false);
                        }
                    }
                }
            }
        }
    }

    public void hideMenu(String menuTitle) {
        Component[] comps = menuBar.getComponents();
        for (Component component : comps) {
            if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                Component[] menuComps = menu.getComponents();
                for (Component comp : menuComps) {
                    if (comp instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) comp;
                        if (item.getText().equals(menuTitle)) {
                            item.setVisible(false);
                        }
                    }
                }
            }
        }
    }
    
    void lblClearCacheMouseClicked(Object obj) {
        
    }
	public JDesktopPane getDesktop() {
		return desktop;
	}
}
