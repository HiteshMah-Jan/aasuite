/*
 * AbstractPanel.java
 *
 * Created on Sep 22, 2007, 9:20:32 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import component.listener.ValueChangeEvent;
import component.listener.ValueChangeListener;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import service.util.AbstractIBean;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractSubPanel extends JPanel implements IPanelNavigator {

    private Object bean;
    protected Object parentRecord;
    protected List origRecords = new ArrayList();

    EntityManager entityManager;
    protected AbstractRobotTester robot;

    public abstract void initSubRecord();

    public abstract List getMasterList();

    public abstract JTable getMasterTable();

    public abstract void loadSubRecords(Object parent);

    public abstract void deleteSubRecords();

    public void searchMoreRecords() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getCurrentBean() {
        return bean;
    }

    public void saveSubRecords() {
        if (getMasterList() == null) {
            return;
        }
        putOtherValues();
        putKeyValues();
        List lst = new ArrayList();
        PanelUtil.setListData(lst, getMasterList());
        DBClient.saveSubRecords(origRecords, lst);
    }

    /**
     * This will return the new button
     */
    public abstract javax.swing.JButton getNewButton();

    /**
     * Use this to setup other values
     */
    public void putOtherValues() {
    }

    protected final void putKeyValues() {
        List lst = getMasterList();
        for (Object obj : lst) {
            putKeyValue(parentRecord, obj);
        }
    }

    public abstract void putKeyValue(Object parent, Object child);

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        initSubRecord();
        if (getMasterTable() != null) {
            TableSelectionListener listener = new TableSelectionListener();
            getMasterTable().getSelectionModel().addListSelectionListener(listener);
        }
        entityManager.getTransaction().begin();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initAllComponent();
            }
        });
    }

    private final class TableSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == getMasterTable().getSelectionModel()) {
                if (!e.getValueIsAdjusting()) {
                    if (getMasterTable().getSelectedRow()>=0) {
                        bean = getMasterList().get(getMasterTable().getSelectedRow());
                        onChangeRecord(bean);
                    }
                    else {
                        bean = null;
                    }
                }
            }
        }
    }

    protected void onChangeRecord(Object bean) {
    }
    
    public void preLoadSubRecords(Object parent) {
        javax.swing.JButton btn = getNewButton();
        if (btn != null) {
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    putOtherValues();
                }
            });
        }
    }

    public void preDeleteSubRecord() {
    }

    public void preSaveSubRecords() {
    }

    public final void testPanel() {
        robot = new AbstractRobotTester() {

            protected void runRobotTester() {
                runRobot(this);
            }
        };
        robot.runRobotTester();
    }

    protected void runRobot(AbstractRobotTester robot) {
    }

    private JPanel getMe() {
        return this;
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
            List<IGetText> lst = PanelUtil.getAllGetText(((JComponent) comp));
            for (IGetText cmp : lst) {
                if (cmp instanceof JCalendarPallete) {
                    JCalendarPallete cal = (JCalendarPallete) cmp;
                    cal.addDateListener(new DateListener() {
                        public void dateChanged(DateEvent arg0) {
                            putOtherValues();
                        }
                    });
                }
                else if (cmp instanceof JComboBox) {
                    JComboBox cbo = (JComboBox) cmp;
                    cbo.addItemListener(new ItemListener() {
                        public void itemStateChanged(ItemEvent e) {
                            putOtherValues();
                        }
                    });
                }
                else if (cmp instanceof LookupTableFieldPallete) {
                    LookupTableFieldPallete cbo = (LookupTableFieldPallete) cmp;
                    cbo.lookup.txt.addValueChangeListener(new ValueChangeListener() {
                        public void valueChanged(ValueChangeEvent evt) {
                            putOtherValues();
                        }
                    });
                }
            }
        }
    }

    protected void refreshAllCombo(Component comp) {
        if (comp instanceof JComponent) {
            List<IGetText> lst = PanelUtil.getAllGetText(((JComponent) comp));
            for (IGetText cmp : lst) {
                if (cmp instanceof JComboBoxPallete) {
                    JComboBoxPallete cbo = (JComboBoxPallete) cmp;
                    cbo.refresh();
                }
            }
        }
    }

    public void refresh() {
        refreshAllCombo(this);
    }

    public void firstRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable.getRowCount()==0) return;
        int row = 0;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void lastRecord() {
        javax.swing.JTable masterTable = getMasterTable();
        if (masterTable.getRowCount()==0) return;
        int row = masterTable.getRowCount()-1;
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

    public void newRecord() {
        
    }
    
    public void deleteRecord() {
        
    }
    
    public void saveRecord() {
        
    }
    
    public void refreshRecords() {
        
    }
    
    JDialog dialog;

    public JDialog getDialog() {
        if (dialog == null) {
            dialog = new JDialog(AbstractPanel.frame, true);
            dialog.setLocation(200, 200);
        }
        return dialog;
    }

    public void showPanelAsDialog(String clsStr) {
        JPanel pnl = null;
        try {
            Class cls = Class.forName(PanelUtil.getClassPath(clsStr));
            pnl = (JPanel) cls.newInstance();
            
            JDialog dlg = getDialog();
            dlg.setLayout(new GridLayout());
            dlg.getContentPane().removeAll();
            dlg.getContentPane().add(pnl);
            if (pnl instanceof AbstractPanel) {
                AbstractPanel panel = (AbstractPanel) pnl;
                panel.setParentBean((AbstractIBean) this.getCurrentBean());
                dlg.setTitle(panel.getTitle());
            }
            dlg.pack();
            dlg.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshMainRecord() {
        MainWindow.getDisplayedModule().refreshCurrentRecord();
    }
    
    public void saveMainRecord() {
        MainWindow.getDisplayedModule().saveRecord();
    }

    public void showReport(JComponent btnReport) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
