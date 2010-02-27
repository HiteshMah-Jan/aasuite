/*
 * AbstractPanel.java
 *
 * Created on Sep 22, 2007, 9:20:32 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import bean.admin.BusinessRule;
import bean.admin.HelpFields;
import component.listener.CrudEvent;
import component.listener.CrudListener;
import constants.UserInfo;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.beansbinding.ELProperty;
import service.util.AbstractIBean;
import util.ScriptRunner;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractPanel extends JPanel implements IPanelNavigator {

    public static Frame frame = null;
    JDialog dialog;
    AbstractRobotTester tester;

    protected JButton mainRefreshButton;
    protected JButton mainNewButton;
    protected JButton mainSaveButton;
    protected JButton mainDeleteButton;
    protected JButton mainPrintButton;
    protected JButton mainPrevButton;
    protected JButton mainNextButton;
    protected JButton mainReportButton;
    protected Vector<CrudListener> vecCrudListener = new Vector<CrudListener>();
    private boolean firstLoad = true;

    public void searchMoreRecords() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addCrudListener(CrudListener listener) {
        vecCrudListener.add(listener);
    }

    public void initSubRecords() {
        List<AbstractSubPanel> lst = getSubPanels(getMe());
        for (AbstractSubPanel panel : lst) {
            panel.setEntityManager(getMe().getEntityManager());
        }
        onChangeRecord();
    }

    public AbstractPanel getMe() {
        return this;
    }

    public AbstractRobotTester getTester() {
        return tester;
    }

    public void report() {
        JTable tbl = getMasterTable();
        if (tbl instanceof ReadonlyTable) {
            ((ReadonlyTable) tbl).printTable();
        }
    }

    public void setTester(AbstractRobotTester tester) {
        this.tester = tester;
        this.tester.setPanel(this);
    }

    public AbstractPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainRefreshButton = MainWindow.mainwindow.getBtnReload();
                    mainNewButton = MainWindow.mainwindow.getBtnNew();
                    mainSaveButton = MainWindow.mainwindow.getBtnSave();
                    mainDeleteButton = MainWindow.mainwindow.getBtnDelete();
                    mainPrintButton = MainWindow.mainwindow.getBtnPrint();
                    mainPrevButton = MainWindow.mainwindow.getBtnPrev();
                    mainNextButton = MainWindow.mainwindow.getBtnNext();
                    mainReportButton = MainWindow.mainwindow.getBtnReport();

                    mainDeleteButton.setEnabled(isEnableDeleteButton());
                    mainRefreshButton.setEnabled(isEnableRefreshButton());
                    mainNewButton.setEnabled(isEnableNewButton());
                    mainSaveButton.setEnabled(isEnableSaveButton());
                    mainPrintButton.setEnabled(isEnablePrintButton());
                    mainPrevButton.setEnabled(isEnablePrevButton());
                    mainNextButton.setEnabled(isEnableNextButton());
                    mainReportButton.setEnabled(isEnableReportButton());
                    
                    TableSelectionListener listener = new TableSelectionListener();
                    getMasterTable().getSelectionModel().addListSelectionListener(listener);

                    component.AbstractPanel pnl = getMe();
                    java.lang.String simpleName = pnl.getClass().getSimpleName();
                    component.AbstractRobotTester tester = (component.AbstractRobotTester) Class.forName("ui.robottester."+simpleName+"RobotTester").newInstance();
                    setTester(tester);
                } catch (Exception ex) {
//                    Logger.getLogger("global").log(Level.SEVERE, null, ex);
                }
                initAllComponent();
                getMe().firstRecord();
                focusComponent();
                initRules();
            }
        });
        firstLoad = true;
    }

    protected boolean isEnableReportButton() {
        return true;
    }

    protected void runRobot(AbstractRobotTester robot) {
    }

    public static void runRobotForPanel() {
        component.AbstractPanel pnl = component.MainWindow.getDisplayedModule();
        java.lang.String simpleName = pnl.getClass().getSimpleName();
        component.AbstractRobotTester.setENABLE_ROBOT(true);
        try {
            component.AbstractRobotTester tester = (component.AbstractRobotTester) Class.forName("ui.robottester."+simpleName+"RobotTester").newInstance();
            pnl.setTester(tester);
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.INFO, "Using override method for Robot");
        }
        component.AbstractRobotTester tester = new component.AbstractRobotTester() {

            protected void runRobotTester() {
                this.getPanel().runRobot(this);
            }
        };
        tester.setPanel(pnl);
    }

    public abstract String getTitle();

    public abstract JTable getMasterTable();

    public abstract List getMasterList();

    public abstract EntityManager getEntityManager();

    public abstract Query getMasterQuery();

    public abstract Class getBeanClass();

    public boolean close() {
        return true;
    }

    public JDialog getDialog() {
        if (dialog == null) {
            dialog = new JDialog(frame, true);
            dialog.setLocation(200, 200);
        }
        return dialog;
    }

    public JDialog getGenericDialog() {
        JDialog dialog = new JDialog(frame, true);
        dialog.setLocation(200, 200);
        return dialog;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().getName().equals(this.getClass().getName())) {
            AbstractPanel pnl = (AbstractPanel) obj;
            return pnl.getTitle().equals(getTitle());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }

    public String getHelpName() {
        return this.getClass().getSimpleName();
    }

    public List<HelpFields> getAllHelpField() {
        return this.getComponent(this);
    }

    List<HelpFields> lst = new ArrayList<HelpFields>();

    private List<HelpFields> getComponent(JComponent jcomp) {
        Component[] compArr = jcomp.getComponents();
        if (compArr != null) {
            for (Component comp : compArr) {
                if (comp instanceof IHelp) {
                    IHelp tmpH = (IHelp) comp;
                    HelpFields hf = new HelpFields();
//					hf.setHelpName(hlp);
                    hf.setDisplayField(tmpH.getHelpName());
                    hf.setField(tmpH.getHelpName());
                    hf.setHelpField(tmpH.getHelpName());
                    lst.add(hf);
                } else {
                    if (comp instanceof JComponent) {
                        getComponent((JComponent) comp);
                    }
                }
            }
        }
        return lst;
    }

    List<AbstractSubPanel> lstSubPanels = new ArrayList<AbstractSubPanel>();

    public AbstractSubPanel getSub(int index) {
        if (index<lstSubPanels.size()) {
            return lstSubPanels.get(index);
        }
        return null;
    }
    
    public List<AbstractSubPanel> getSubPanels(JComponent jcomp) {
        lstSubPanels.clear();
        traverseSubPanels(jcomp);
        return lstSubPanels;
    }

    private void traverseSubPanels(JComponent jcomp) {
        Component[] compArr = jcomp.getComponents();
        if (compArr != null) {
            for (Component comp : compArr) {
                if (comp instanceof AbstractSubPanel) {
                    AbstractSubPanel tmpH = (AbstractSubPanel) comp;
                    lstSubPanels.add(tmpH);
                } else {
                    if (comp instanceof JComponent) {
                        traverseSubPanels((JComponent) comp);
                    }
                }
            }
        }
    }

    public Object getBean() {
        return ELProperty.create("${selectedElement}").getValue(getMasterTable());
    }

    private void runCrudEvents(int event) {
        for (CrudListener listener : vecCrudListener) {
            CrudEvent evt = new CrudEvent(getBean(), event);
            listener.crudAction(evt);
        }
    }

    public void newRecord() {
        try {
            Query query = getMasterQuery();
            List list = getMasterList();
            if (list != null && list.size() > 0) {
                list.clear();
            }
            list.addAll(query.getResultList());
            Object a = getBeanClass().newInstance();
            list.add(a);
            if (a instanceof AbstractIBean) {
                ((AbstractIBean) a).setNewRecord(true);
            }
            int row = list.size() - 1;
            javax.swing.JTable masterTable = getMasterTable();
            if (row>=0 && row<masterTable.getRowCount()) {
                masterTable.setRowSelectionInterval(row, row);
                masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
            }
            runCrudEvents(CrudEvent.NEW);
            focusComponent();
            runRule(constants.Constants.DEFAULT_EVENT_RULE.NEW);
            PanelUtil.showMessageToScreen("New Record.");
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecord() {
        Object b = ELProperty.create("${selectedElement}").getValue(getMasterTable());
        getEntityManager().remove(b);

        List<AbstractSubPanel> lst = this.getSubPanels(this);
        for (int i = 0; i < lst.size(); i++) {
            AbstractSubPanel panel = (AbstractSubPanel) lst.get(i);
            panel.deleteSubRecords();
        }
        runCrudEvents(CrudEvent.DELETE);
        focusComponent();
        runRule(constants.Constants.DEFAULT_EVENT_RULE.DELETE);
        PanelUtil.showMessageToScreen("Record Deleted.");
    }

    public void saveRecord() {
        Object a = ELProperty.create("${selectedElement}").getValue(getMasterTable());
        List<AbstractSubPanel> lstTmp = this.getSubPanels(this);
        for (int i = 0; i < lstTmp.size(); i++) {
            AbstractSubPanel panel = (AbstractSubPanel) lstTmp.get(i);
            panel.preSaveSubRecords();
        }

        getEntityManager().persist(a);
//        Logger.getLogger("global").log(Level.INFO, "AFTER PERSIST === " + a.getClass().getSimpleName() + " -> " + BeanUtil.getKeyValue((IBean) a).toString());
        for (int i = 0; i < lstTmp.size(); i++) {
            AbstractSubPanel panel = (AbstractSubPanel) lstTmp.get(i);
            panel.saveSubRecords();
        }

        for (int i = 0; i < lstTmp.size(); i++) {
            AbstractSubPanel panel = (AbstractSubPanel) lstTmp.get(i);
            try {
                panel.loadSubRecords(getBean());
            } catch (Exception e) {
            }
        }
        runCrudEvents(CrudEvent.SAVE);
        runRule(constants.Constants.DEFAULT_EVENT_RULE.SAVE);
        PanelUtil.showMessageToScreen("Record Saved.");
        ((AbstractIBean) a).setNewRecord(false);
        focusComponent();
    }

    public void refreshRecords() {
        Query query = getMasterQuery();
        List list = getMasterList();
        if (list != null && list.size() > 0) {
            list.clear();
        }
        list.addAll(query.getResultList());
        onChangeRecord();
        runCrudEvents(CrudEvent.REFRESH);
        if (!firstLoad) {
            refresh();
        }
        firstLoad = false;
        firstRecord();
        focusComponent();
        runRule(constants.Constants.DEFAULT_EVENT_RULE.REFRESH);
        PanelUtil.showMessageToScreen("Refresh Records.");
    }

    protected void onChangeRecord() {
        Object bean = ELProperty.create("${selectedElement}").getValue(getMasterTable());
        List<AbstractSubPanel> lst = this.getSubPanels(this);
        for (AbstractSubPanel panel : lst) {
            if (bean == null) {
                List lstTmp = panel.getMasterList();
                PanelUtil.clearList(lstTmp);
            } else {
                Logger.getLogger("global").log(Level.INFO, "CALLED LOAD SUB RECORDS");
                Thread t = new Thread(new SampleRun(panel, bean));
                t.start();
//                panel.loadSubRecords(bean);
            }
        }
    }

    public void refreshCurrentRecord() {
        Object bean = this.getBean();
        List<AbstractSubPanel> lst = this.getSubPanels(this);
        for (AbstractSubPanel panel : lst) {
            if (bean == null) {
                List lstTmp = panel.getMasterList();
                PanelUtil.clearList(lstTmp);
            } else {
                Logger.getLogger("global").log(Level.INFO, "CALLED LOAD SUB RECORDS");
                Thread t = new Thread(new SampleRun(panel, bean));
                t.start();
            }
        }
    }
    
    private static class SampleRun implements Runnable {

        Object bean;
        AbstractSubPanel panel;

        SampleRun(AbstractSubPanel panel, Object bean) {
            this.panel = panel;
            this.bean = bean;
        }

        public void run() {
            panel.loadSubRecords(bean);
        }
    }

    private class TableSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == getMasterTable().getSelectionModel()) {
                boolean enabled = getMasterTable().getSelectedRow() != -1;
                if (!e.getValueIsAdjusting() && enabled) {
                    onChangeRecord();
                }
            }
        }
    }

    public void runRule(String event) {
        String str = "";
        for (Object obj : populatedRules) {
            BusinessRule rule = (BusinessRule) obj;
            if (rule.getEventName().equalsIgnoreCase(event)) {
                str = rule.getRule();
                break;
            }
        }
        if (str!=null && !str.isEmpty()) {
            ScriptRunner.runGroovyBackground(str, null, null);
        }
    }

    List populatedRules = new ArrayList();

    public void resetRules() {
        initRules();
    }
    
    public void initRules() {
        String formName = getTitle();
        populatedRules = AbstractIBean.list("SELECT a FROM BusinessRule a WHERE a.formName=\'" + formName + "\'");
        if (populatedRules != null && populatedRules.size() == 0) {
//            populatedRules.add(new BusinessRule(0, formName, constants.Constants.DEFAULT_EVENT_RULE.DELETE));
//            populatedRules.add(new BusinessRule(0, formName, constants.Constants.DEFAULT_EVENT_RULE.NEW));
//            populatedRules.add(new BusinessRule(0, formName, constants.Constants.DEFAULT_EVENT_RULE.REFRESH));
//            populatedRules.add(new BusinessRule(0, formName, constants.Constants.DEFAULT_EVENT_RULE.SAVE));
//            populatedRules.add(new BusinessRule(0, formName, constants.Constants.DEFAULT_EVENT_RULE.ROBOT));
            AbstractIBean.save(populatedRules);
        }
    }

    public String getDialogTitle(String usedTitle, IGetText linkFor) {
        if (PanelUtil.isEmpty(linkFor)) {
            return usedTitle;
        } else {
            return usedTitle + " - " + linkFor.getText();
        }
    }

    public String getDescription() {
        return getTitle();
    }

    public void print() {
        PanelUtil.showMessage(this, "Print not implemented in this screen.");
    }

    public void firstRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable == null) {
            return;
        }
        if (masterTable.getRowCount() == 0) {
            newRecord();
            return;
        }
        int row = 0;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void lastRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable.getRowCount() == 0) {
            newRecord();
            return;
        }
        int row = masterTable.getRowCount() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void prevRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable.getSelectedRowCount() == 0) {
            if (masterTable.getRowCount() > 0) {
                masterTable.setRowSelectionInterval(0, 0);
                return;
            }
        }
        int row = masterTable.getSelectedRow();
        if (row == 0) {
            PanelUtil.showMessage(masterTable, "No more previous record.");
            return;
        }
        row--;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void nextRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable.getSelectedRowCount() == 0) {
            if (masterTable.getRowCount() > 0) {
                masterTable.setRowSelectionInterval(0, 0);
                return;
            }
        }
        int row = masterTable.getSelectedRow();
        if (row >= masterTable.getRowCount() - 1) {
            PanelUtil.showMessage(masterTable, "No more next record.");
            return;
        }
        row++;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public boolean isEnableRefreshButton() {
        return true;
    }

    public boolean isEnableNewButton() {
        return true;
    }

    public boolean isEnableSaveButton() {
        return true;
    }

    public boolean isEnableDeleteButton() {
        if (UserInfo.loginUser.isSuperAAA()) return true;
        return false;
    }

    public boolean isEnablePrintButton() {
        return true;
    }

    public boolean isEnablePrevButton() {
        return true;
    }

    public boolean isEnableNextButton() {
        return true;
    }

    /**
     * Use this to setup other values
     */
    public void putOtherValues() {
    }

    public void initAllComponent() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initAllComponent(getMe());
            }
        });
    }

    private void initAllComponent(Component comp) {
        if (comp instanceof JComponent) {
            Component[] comps = ((JComponent) comp).getComponents();
            for (Component cmp : comps) {
                if (cmp instanceof AbstractSubPanel) {
                    continue;
                }
                if (cmp instanceof JPanel || cmp instanceof JTabbedPane) {
                    initAllComponent(cmp);
                } else {
                    if (cmp instanceof JComboBox) {
                        JComboBox cbo = (JComboBox) cmp;
                        cbo.addItemListener(new ItemListener() {

                            public void itemStateChanged(ItemEvent e) {
                                putOtherValues();
                            }
                        });
                        cbo.setEnabled(true);
                    }
                }
            }
        }
    }

    protected void refreshAllCombo(Component comp) {
        if (comp instanceof JComponent) {
            Component[] comps = ((JComponent) comp).getComponents();
            for (Component cmp : comps) {
                if (cmp instanceof AbstractSubPanel) {
                    ((AbstractSubPanel) cmp).refresh();
                    continue;
                }
                if (cmp instanceof JScrollPane || cmp instanceof JPanel) {
                    refreshAllCombo(cmp);
                } else {
                    if (cmp instanceof JComboBoxPallete) {
                        JComboBoxPallete cbo = (JComboBoxPallete) cmp;
                        cbo.refresh();
                    }
                }
            }
        }
    }

    public void refresh() {
        refreshAllCombo(this);
    }

    public void focusComponent() {
        final Component txt = PanelUtil.getFirstGetText(this);
        if (txt!=null) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    txt.requestFocus();
                }
            });
        }
    }

    public boolean isSingleton() {
        return true;
    }

    public void setParentBean(AbstractIBean parentBean) {
        
    }
    
    public void showPanelAsDialog(String clsStr) {
        JPanel pnl = null;
        try {
            Class cls = Class.forName(PanelUtil.getClassPath(clsStr));
            pnl = (JPanel) cls.newInstance();
            
            JDialog dlg = getDialog();
            dlg.removeAll();
            dlg.setLayout(new GridLayout());
            JPanel tmp = new JPanel();
            tmp.add(pnl);
            dlg.add(tmp);
            if (pnl instanceof AbstractPanel) {
                AbstractPanel panel = (AbstractPanel) pnl;
                panel.setParentBean((AbstractIBean) this.getBean());
                dlg.setTitle(panel.getTitle());
            }
//            dlg.pack();
            dlg.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public void setRecordList(List recList) {
        List masterList = getMasterList();
        PanelUtil.setListData(masterList, recList);
    }
    
    public void runRobot() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runRule(constants.Constants.DEFAULT_EVENT_RULE.ROBOT);
            }
        });
    }

    public void showReport(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
