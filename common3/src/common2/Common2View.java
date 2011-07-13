/*
 * Common2View.java
 */
package common2;

import bean.admin.AppConfig;
import component.AbstractPanel;
import component.ITransactionPanel;
import component.OlapRunner;
import component.SendEmailDialog;
import java.awt.Component;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;

import constants.UserInfo;

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

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.TemplateReader;
import template.screen.AbstractTemplatePanel;
import template.screen.TemplateDefault;
import template.screen.TransactionPanel;
import ui.TestDBForm;
import ui.admin.ChangeLogo;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * The application's main frame.
 */
public class Common2View extends FrameView {

    public static Common2View mainView;
    public Vector list = new Vector();

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
        Component comp = mainView.desktop.getSelectedFrame().getComponent(0);
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

    public void changeTheme() {
        if (constants.Constants.panelBackground != null) {
            toolbar.setBackground(constants.Constants.panelBackground);
            PanelUtil.updateColor(btnBack);
            PanelUtil.updateColor(btnBookmark);
            PanelUtil.updateColor(btnCalc);
            PanelUtil.updateColor(btnConfigure);
            PanelUtil.updateColor(btnDelete);
            PanelUtil.updateColor(btnEmail);
            PanelUtil.updateColor(btnExit);
            PanelUtil.updateColor(btnHome);
            PanelUtil.updateColor(btnMore);
            PanelUtil.updateColor(btnNew);
            PanelUtil.updateColor(btnNext);
            PanelUtil.updateColor(btnNotepad);
            PanelUtil.updateColor(btnPrev);
            PanelUtil.updateColor(btnRefresh);
            PanelUtil.updateColor(btnReport);
            PanelUtil.updateColor(btnSave);
            PanelUtil.updateColor(btnConfigure);
        }
    }

    public Common2View(SingleFrameApplication app) {
        super(app);
        mainView = this;
        initComponents();
        changeTheme();
        btnBookmark.setVisible(AppConfig.showBookmarkButton());
        btnCalc.setVisible(AppConfig.showCalcButton());
        btnConfigure.setVisible(AppConfig.showConfigureButton());
        btnEmail.setVisible(AppConfig.showEmailButton());
        btnNotepad.setVisible(AppConfig.showNotePadButton());
//        this.showPanel("Home", "WelcomePanel");
        showPanel("Login", "LoginPanel");
        util.AccessControlUtil.setAccessControl(menuBar);

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
        btnExit.setText("Minimize");
        boolean b = AppConfig.isShowDataAnalyzer();
        mnuDataAnalyzer.setVisible(b);
        btnYear.setText(AppConfig.getSchoolYear());
        PanelUtil.updateColor(btnYear);
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
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        mnuBookmark = new javax.swing.JMenuItem();
        mnuLogin = new javax.swing.JMenuItem();
        mnuChangePassword = new javax.swing.JMenuItem();
        mnuAdminConsole = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuAppMenu = new javax.swing.JMenuItem();
        mnuModules = new javax.swing.JMenuItem();
        mnuConfig = new javax.swing.JMenuItem();
        mnuCompanyConfig = new javax.swing.JMenuItem();
        mnuSetupDB = new javax.swing.JMenuItem();
        mnuTraining = new javax.swing.JMenuItem();
        mnuChangeTheme = new javax.swing.JMenuItem();
        mnuServices = new javax.swing.JMenuItem();
        mnuUserStation = new javax.swing.JMenuItem();
        mnuDataAnalyzer = new javax.swing.JMenuItem();
        mnuTestDB = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        lblClearCache = new javax.swing.JLabel();
        toolbar = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnMore = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnBookmark = new javax.swing.JButton();
        btnEmail = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnCalc = new javax.swing.JButton();
        btnNotepad = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnYear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
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

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setFont(resourceMap.getFont("helpMenu.font")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        helpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuActionPerformed(evt);
            }
        });

        mnuBookmark.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuBookmark.setText(resourceMap.getString("mnuBookmark.text")); // NOI18N
        mnuBookmark.setName("mnuBookmark"); // NOI18N
        mnuBookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBookmarkActionPerformed(evt);
            }
        });
        helpMenu.add(mnuBookmark);

        mnuLogin.setFont(resourceMap.getFont("mnuLogin.font")); // NOI18N
        mnuLogin.setText(resourceMap.getString("mnuLogin.text")); // NOI18N
        mnuLogin.setName("mnuLogin"); // NOI18N
        mnuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLoginActionPerformed(evt);
            }
        });
        helpMenu.add(mnuLogin);

        mnuChangePassword.setFont(resourceMap.getFont("mnuChangePassword.font")); // NOI18N
        mnuChangePassword.setText(resourceMap.getString("mnuChangePassword.text")); // NOI18N
        mnuChangePassword.setName("mnuChangePassword"); // NOI18N
        mnuChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChangePasswordActionPerformed(evt);
            }
        });
        helpMenu.add(mnuChangePassword);

        mnuAdminConsole.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuAdminConsole.setText(resourceMap.getString("mnuAdminConsole.text")); // NOI18N
        mnuAdminConsole.setName("mnuAdminConsole"); // NOI18N
        mnuAdminConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAdminConsoleActionPerformed(evt);
            }
        });
        helpMenu.add(mnuAdminConsole);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenu1.setName("jMenu1"); // NOI18N

        mnuAppMenu.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuAppMenu.setText(resourceMap.getString("mnuAppMenu.text")); // NOI18N
        mnuAppMenu.setName("mnuAppMenu"); // NOI18N
        mnuAppMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAppMenuActionPerformed(evt);
            }
        });
        jMenu1.add(mnuAppMenu);

        mnuModules.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuModules.setText("Modules"); // NOI18N
        mnuModules.setName("mnuModules"); // NOI18N
        mnuModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuModulesActionPerformed(evt);
            }
        });
        jMenu1.add(mnuModules);

        mnuConfig.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuConfig.setText(resourceMap.getString("mnuConfig.text")); // NOI18N
        mnuConfig.setName("mnuConfig"); // NOI18N
        mnuConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigActionPerformed(evt);
            }
        });
        jMenu1.add(mnuConfig);

        mnuCompanyConfig.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuCompanyConfig.setText(resourceMap.getString("mnuCompanyConfig.text")); // NOI18N
        mnuCompanyConfig.setName("mnuCompanyConfig"); // NOI18N
        mnuCompanyConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCompanyConfigActionPerformed(evt);
            }
        });
        jMenu1.add(mnuCompanyConfig);

        mnuSetupDB.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuSetupDB.setText(resourceMap.getString("mnuSetupDB.text")); // NOI18N
        mnuSetupDB.setName("mnuSetupDB"); // NOI18N
        mnuSetupDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSetupDBActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSetupDB);

        mnuTraining.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuTraining.setText(resourceMap.getString("mnuTraining.text")); // NOI18N
        mnuTraining.setName("mnuTraining"); // NOI18N
        mnuTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrainingActionPerformed(evt);
            }
        });
        jMenu1.add(mnuTraining);

        mnuChangeTheme.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuChangeTheme.setText(resourceMap.getString("mnuChangeTheme.text")); // NOI18N
        mnuChangeTheme.setName("mnuChangeTheme"); // NOI18N
        mnuChangeTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChangeThemeActionPerformed(evt);
            }
        });
        jMenu1.add(mnuChangeTheme);

        mnuServices.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuServices.setText("Services"); // NOI18N
        mnuServices.setName("mnuServices"); // NOI18N
        mnuServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuServicesActionPerformed(evt);
            }
        });
        jMenu1.add(mnuServices);

        mnuUserStation.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuUserStation.setText(resourceMap.getString("mnuUserStation.text")); // NOI18N
        mnuUserStation.setName("mnuUserStation"); // NOI18N
        mnuUserStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUserStationActionPerformed(evt);
            }
        });
        jMenu1.add(mnuUserStation);

        mnuDataAnalyzer.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuDataAnalyzer.setText("Data Analyzer"); // NOI18N
        mnuDataAnalyzer.setName("mnuDataAnalyzer"); // NOI18N
        mnuDataAnalyzer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDataAnalyzerActionPerformed(evt);
            }
        });
        jMenu1.add(mnuTestDB);
        mnuTestDB.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuTestDB.setText("Test DB"); // NOI18N
        mnuTestDB.setName("mnuTestDB"); // NOI18N
        mnuTestDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTestDBActionPerformed(evt);
            }
        });
        jMenu1.add(mnuTestDB);
        
        helpMenu.add(jMenu1);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getActionMap(Common2View.class, this);
        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setFont(resourceMap.getFont("aboutMenuItem.font")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setFont(resourceMap.getFont("exitMenuItem.font")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(exitMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setLayout(new java.awt.BorderLayout());

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N
        statusPanel.add(statusPanelSeparator, java.awt.BorderLayout.NORTH);

        statusMessageLabel.setFont(resourceMap.getFont("statusMessageLabel.font")); // NOI18N
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

        btnNew.setFont(resourceMap.getFont("btnNew.font")); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png"))); // NOI18N
        btnNew.setText(resourceMap.getString("btnNew.text")); // NOI18N
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

        btnSave.setFont(resourceMap.getFont("btnSave.font")); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSave.setText(resourceMap.getString("btnSave.text")); // NOI18N
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

        btnRefresh.setFont(resourceMap.getFont("btnRefresh.font")); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload.png"))); // NOI18N
        btnRefresh.setText(resourceMap.getString("btnRefresh.text")); // NOI18N
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

        btnDelete.setFont(resourceMap.getFont("btnDelete.font")); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setText(resourceMap.getString("btnDelete.text")); // NOI18N
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

        btnPrev.setFont(resourceMap.getFont("btnPrev.font")); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prevRecord.png"))); // NOI18N
        btnPrev.setText(resourceMap.getString("btnPrev.text")); // NOI18N
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

        btnNext.setFont(resourceMap.getFont("btnNext.font")); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nextRecord.png"))); // NOI18N
        btnNext.setText(resourceMap.getString("btnNext.text")); // NOI18N
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

        btnMore.setFont(resourceMap.getFont("btnMore.font")); // NOI18N
        btnMore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/more.png"))); // NOI18N
        btnMore.setText(resourceMap.getString("btnMore.text")); // NOI18N
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

        btnBookmark.setFont(resourceMap.getFont("btnBookmark.font")); // NOI18N
        btnBookmark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bookmark.png"))); // NOI18N
        btnBookmark.setText(resourceMap.getString("btnBookmark.text")); // NOI18N
        btnBookmark.setFocusable(false);
        btnBookmark.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBookmark.setName("btnBookmark"); // NOI18N
        btnBookmark.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookmarkActionPerformed(evt);
            }
        });
        toolbar.add(btnBookmark);

        btnEmail.setFont(resourceMap.getFont("btnEmail.font")); // NOI18N
        btnEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/email.png"))); // NOI18N
        btnEmail.setText(resourceMap.getString("btnEmail.text")); // NOI18N
        btnEmail.setFocusable(false);
        btnEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmail.setName("btnEmail"); // NOI18N
        btnEmail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });
        toolbar.add(btnEmail);

        btnReport.setFont(resourceMap.getFont("btnReport.font")); // NOI18N
        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        btnReport.setText(resourceMap.getString("btnReport.text")); // NOI18N
        btnReport.setFocusable(false);
        btnReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReport.setName("btnReport"); // NOI18N
        btnReport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        toolbar.add(btnReport);

        btnCalc.setFont(new java.awt.Font("Tahoma", 0, 12));
        btnCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.PNG"))); // NOI18N
        btnCalc.setText(resourceMap.getString("btnCalc.text")); // NOI18N
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

        btnNotepad.setFont(resourceMap.getFont("btnNotepad.font")); // NOI18N
        btnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notepad.png"))); // NOI18N
        btnNotepad.setText(resourceMap.getString("btnNotepad.text")); // NOI18N
        btnNotepad.setFocusable(false);
        btnNotepad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNotepad.setName("btnNotepad"); // NOI18N
        btnNotepad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotepadActionPerformed(evt);
            }
        });
        toolbar.add(btnNotepad);

        btnHome.setFont(resourceMap.getFont("btnHome.font")); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        btnHome.setText(resourceMap.getString("btnHome.text")); // NOI18N
        btnHome.setFocusable(false);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setName("btnHome"); // NOI18N
        btnHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        toolbar.add(btnHome);

        btnBack.setFont(resourceMap.getFont("btnBack.font")); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prev.png"))); // NOI18N
        btnBack.setText(resourceMap.getString("btnBack.text")); // NOI18N
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

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 12));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        btnExit.setText(resourceMap.getString("btnExit.text")); // NOI18N
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

        lstWindow.setFont(resourceMap.getFont("lstWindow.font")); // NOI18N
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

        mnuUpdateSQL.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuUpdateSQL.setText(resourceMap.getString("mnuUpdateSQL.text")); // NOI18N
        mnuUpdateSQL.setName("mnuUpdateSQL"); // NOI18N
        mnuUpdateSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUpdateSQLActionPerformed(evt);
            }
        });

        mnuChangeLogo.setFont(new java.awt.Font("Tahoma", 0, 12));
        mnuChangeLogo.setText(resourceMap.getString("mnuChangeLogo.text")); // NOI18N
        mnuChangeLogo.setName("mnuChangeLogo"); // NOI18N
        mnuChangeLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChangeLogoActionPerformed(evt);
            }
        });

        mnuRuleEngine.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        internalFrame.setLayout(new GridLayout());
        internalFrame.add(pnl);
        desktop.add(internalFrame);
        internalFrame.setVisible(true);
        try {
			internalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
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

private void btnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotepadActionPerformed
    try {
        java.lang.Runtime.getRuntime().exec("notepad");
    } catch (IOException ex) {
        Logger.getLogger("global").log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnNotepadActionPerformed

private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
    mapPanels.remove("Home");
    showPanel("Home", "WelcomePanel");
    resetHome();
}//GEN-LAST:event_btnHomeActionPerformed

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

private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
    getTransactionPanel().showReport(btnReport);
}//GEN-LAST:event_btnReportActionPerformed

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

private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
    try {
        AbstractIBean bean = (AbstractIBean) TransactionPanel.getCurrentPanel().getBean();
        SendEmailDialog.showDialog(bean.extractEmailSubject(), bean.extractEmailContent(), bean.extractEmailCustomerId(), bean.extractEmailRecipient(), bean.extractEmailAttachment());
    } catch (Exception e) {
        SendEmailDialog.showDialog(true);
    }
}//GEN-LAST:event_btnEmailActionPerformed

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

private void btnBookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookmarkActionPerformed
    try {
        ((TransactionPanel) TransactionPanel.getCurrentPanel()).bookmark();
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_btnBookmarkActionPerformed

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
    private javax.swing.JButton btnBookmark;
    private javax.swing.JButton btnCalc;
    private javax.swing.JButton btnConfigure;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMore;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNotepad;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnRealExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReport;
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
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JMenuItem mnuBookmark;
    private javax.swing.JMenuItem mnuChangeLogo;
    public javax.swing.JMenuItem mnuChangePassword;
    public javax.swing.JMenuItem mnuChangeTheme;
    private javax.swing.JMenuItem mnuCompanyConfig;
    private javax.swing.JMenuItem mnuConfig;
    private javax.swing.JMenuItem mnuDataAnalyzer;
    private javax.swing.JMenuItem mnuTestDB;
    public javax.swing.JMenuItem mnuLogin;
    private javax.swing.JMenuItem mnuModules;
    private javax.swing.JMenuItem mnuRuleEngine;
    private javax.swing.JMenuItem mnuServices;
    public javax.swing.JMenuItem mnuSetupDB;
    private javax.swing.JMenuItem mnuTraining;
    private javax.swing.JMenuItem mnuUpdateSQL;
    private javax.swing.JMenuItem mnuUserStation;
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
}
