/*
 * ButtonManagerPallete.java
 * 
 * Created on Oct 18, 2007, 1:13:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class ButtonManagerPallete {

    private JButton btnNew;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnSave;
    private JTable masterTable;
    private JComponent disableOnUpdate1;
    private JComponent disableOnUpdate2;
    private JComponent disableOnUpdate3;
    private JComponent disableOnUpdate4;
    private JComponent disableOnUpdate5;
    private JComponent disableOnUpdate6;
    private JComponent disableOnUpdate7;
    private JComponent disableOnUpdate8;
    private JComponent disableOnUpdate9;
    private JComponent disableOnUpdate10;
    private JComponent disableOnNew1;
    private JComponent disableOnNew2;
    private JComponent disableOnNew3;
    private JComponent disableOnNew4;
    private JComponent disableOnNew5;
    private JComponent disableOnNew6;
    private JComponent disableOnNew7;
    private JComponent disableOnNew8;
    private JComponent disableOnNew9;
    private JComponent disableOnNew10;
    private JComponent disableOnRefresh1;
    private JComponent disableOnRefresh2;
    private JComponent disableOnRefresh3;
    private JComponent disableOnRefresh4;
    private JComponent disableOnRefresh5;
    private JComponent disableOnRefresh6;
    private JComponent disableOnRefresh7;
    private JComponent disableOnRefresh8;
    private JComponent disableOnRefresh9;
    private JComponent disableOnRefresh10;
    private JComponent alwaysEnable1;
    private JComponent alwaysEnable2;
    private JComponent alwaysEnable3;
    private JComponent alwaysEnable4;
    private JComponent alwaysEnable5;
    private JComponent alwaysEnable6;
    private JComponent alwaysEnable7;
    private JComponent alwaysEnable8;
    private JComponent alwaysEnable9;
    private JComponent alwaysEnable10;
    private JComponent hideOnNoRecord1;
    private JComponent hideOnNoRecord2;
    private JComponent hideOnNoRecord3;
    private JComponent hideOnNoRecord4;
    private JComponent hideOnNoRecord5;
    private JComponent hideOnNoRecord6;
    private JComponent hideOnNoRecord7;
    private JComponent hideOnNoRecord8;
    private JComponent hideOnNoRecord9;
    private JComponent hideOnNoRecord10;
    private boolean newRecord;
    private FormValidator formValidator = new FormValidator();
    private JPanel pnl;
    private boolean hideNewButton;
    private boolean hideDeleteButton;
    private boolean hideRefreshButton;
    private boolean hideSaveButton;

    public ButtonManagerPallete() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                initializeButtonManager();
                disableAllRefresh();
                setAccessControl();
                unhideAllNoRecord(false);
            }
        });
    }

    private void setAccessControl() {
        AbstractPanel pnl = MainWindow.mainwindow.getDisplayedModule();
        if (pnl == null) {
            return;
//		util.AccessControlUtil.setAccessControl(pnl.getTitle(), pnl.getDescription(), btnNew, btnDelete, btnDelete, btnRefresh);
        }
    }

    private void seekAbstractPanel(Component comp) {
        if (pnl != null) {
            return;
        }
        if (comp instanceof AbstractPanel) {
            this.setPnl((AbstractPanel) comp);
        } else {
            if (comp != null) {
                seekAbstractPanel(comp.getParent());
            }
        }
    }

    public void initializeButtonManager() {
        if (btnDelete != null) {
            seekAbstractPanel(btnDelete);
            btnDelete.setVisible(!hideDeleteButton);
        }
        if (btnRefresh != null) {
            seekAbstractPanel(btnDelete);
            btnRefresh.setVisible(!hideRefreshButton);
        }
        if (btnNew != null) {
            seekAbstractPanel(btnDelete);
            btnNew.setVisible(!hideNewButton);
        }
        if (btnSave != null) {
            seekAbstractPanel(btnDelete);
            btnSave.setVisible(!hideSaveButton);
        }
    }

    public boolean isHideDeleteButton() {
        return hideDeleteButton;
    }

    public void setHideDeleteButton(boolean lhideDeleteButton) {
        this.hideDeleteButton = lhideDeleteButton;
    }

    public boolean isHideNewButton() {
        return hideNewButton;
    }

    public void setHideNewButton(boolean lhideNewButton) {
        this.hideNewButton = lhideNewButton;
    }

    public boolean isHideRefreshButton() {
        return hideRefreshButton;
    }

    public void setHideRefreshButton(boolean lhideRefreshButton) {
        this.hideRefreshButton = lhideRefreshButton;
    }

    public boolean isHideSaveButton() {
        return hideSaveButton;
    }

    public void setHideSaveButton(boolean lhideSaveButton) {
        this.hideSaveButton = lhideSaveButton;
    }

    public JPanel getPnl() {
        return pnl;
    }

    public void setPnl(JPanel pnl) {
        this.pnl = (AbstractPanel) pnl;
        formValidator.setPnl((AbstractPanel) pnl);
    }

    public FormValidator getFormValidator() {
        return formValidator;
    }

    public void setFormValidator(FormValidator formValidator) {
        this.formValidator = formValidator;
//		FormValidator frm = SpringCall.getValidator(formValidator.getClass());
//		if (frm != null) {
//			this.formValidator = frm;
//		}
    }

    protected void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
        this.enableAlways();
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public List<JComponent> getAllDisableOnRefresh() {
        List<JComponent> lst = new ArrayList<JComponent>();
        if (disableOnRefresh1 != null) {
            lst.add(disableOnRefresh1);
        }
        if (disableOnRefresh2 != null) {
            lst.add(disableOnRefresh2);
        }
        if (disableOnRefresh3 != null) {
            lst.add(disableOnRefresh3);
        }
        if (disableOnRefresh4 != null) {
            lst.add(disableOnRefresh4);
        }
        if (disableOnRefresh5 != null) {
            lst.add(disableOnRefresh5);
        }
        if (disableOnRefresh6 != null) {
            lst.add(disableOnRefresh6);
        }
        if (disableOnRefresh7 != null) {
            lst.add(disableOnRefresh7);
        }
        if (disableOnRefresh8 != null) {
            lst.add(disableOnRefresh8);
        }
        if (disableOnRefresh9 != null) {
            lst.add(disableOnRefresh9);
        }
        if (disableOnRefresh10 != null) {
            lst.add(disableOnRefresh10);
        }
        return lst;
    }

    public List<JComponent> getAllHideOnNoRecords() {
        List<JComponent> lst = new ArrayList<JComponent>();
        if (hideOnNoRecord1 != null) {
            lst.add(hideOnNoRecord1);
        }
        if (hideOnNoRecord2 != null) {
            lst.add(hideOnNoRecord2);
        }
        if (hideOnNoRecord3 != null) {
            lst.add(hideOnNoRecord3);
        }
        if (hideOnNoRecord4 != null) {
            lst.add(hideOnNoRecord4);
        }
        if (hideOnNoRecord5 != null) {
            lst.add(hideOnNoRecord5);
        }
        if (hideOnNoRecord6 != null) {
            lst.add(hideOnNoRecord6);
        }
        if (hideOnNoRecord7 != null) {
            lst.add(hideOnNoRecord7);
        }
        if (hideOnNoRecord8 != null) {
            lst.add(hideOnNoRecord8);
        }
        if (hideOnNoRecord9 != null) {
            lst.add(hideOnNoRecord9);
        }
        if (hideOnNoRecord10 != null) {
            lst.add(hideOnNoRecord10);
        }
        return lst;
    }

    public List<JComponent> getAllDisableOnUpdate() {
        List<JComponent> lst = new ArrayList<JComponent>();
        if (disableOnUpdate1 != null) {
            lst.add(disableOnUpdate1);
        }
        if (disableOnUpdate2 != null) {
            lst.add(disableOnUpdate2);
        }
        if (disableOnUpdate3 != null) {
            lst.add(disableOnUpdate3);
        }
        if (disableOnUpdate4 != null) {
            lst.add(disableOnUpdate4);
        }
        if (disableOnUpdate5 != null) {
            lst.add(disableOnUpdate5);
        }
        if (disableOnUpdate6 != null) {
            lst.add(disableOnUpdate6);
        }
        if (disableOnUpdate7 != null) {
            lst.add(disableOnUpdate7);
        }
        if (disableOnUpdate8 != null) {
            lst.add(disableOnUpdate8);
        }
        if (disableOnUpdate9 != null) {
            lst.add(disableOnUpdate9);
        }
        if (disableOnUpdate10 != null) {
            lst.add(disableOnUpdate10);
        }
        return lst;
    }

    public List<JComponent> getAllDisableOnNew() {
        List<JComponent> lst = new ArrayList<JComponent>();
        if (disableOnNew1 != null) {
            lst.add(disableOnNew1);
        }
        if (disableOnNew2 != null) {
            lst.add(disableOnNew2);
        }
        if (disableOnNew3 != null) {
            lst.add(disableOnNew3);
        }
        if (disableOnNew4 != null) {
            lst.add(disableOnNew4);
        }
        if (disableOnNew5 != null) {
            lst.add(disableOnNew5);
        }
        if (disableOnNew6 != null) {
            lst.add(disableOnNew6);
        }
        if (disableOnNew7 != null) {
            lst.add(disableOnNew7);
        }
        if (disableOnNew8 != null) {
            lst.add(disableOnNew8);
        }
        if (disableOnNew9 != null) {
            lst.add(disableOnNew9);
        }
        if (disableOnNew10 != null) {
            lst.add(disableOnNew10);
        }
        return lst;
    }

    public List<JComponent> getAllAlwaysEnable() {
        List<JComponent> lst = new ArrayList<JComponent>();
        if (alwaysEnable1 != null) {
            lst.add(alwaysEnable1);
        }
        if (alwaysEnable2 != null) {
            lst.add(alwaysEnable2);
        }
        if (alwaysEnable3 != null) {
            lst.add(alwaysEnable3);
        }
        if (alwaysEnable4 != null) {
            lst.add(alwaysEnable4);
        }
        if (alwaysEnable5 != null) {
            lst.add(alwaysEnable5);
        }
        if (alwaysEnable6 != null) {
            lst.add(alwaysEnable6);
        }
        if (alwaysEnable7 != null) {
            lst.add(alwaysEnable7);
        }
        if (alwaysEnable8 != null) {
            lst.add(alwaysEnable8);
        }
        if (alwaysEnable9 != null) {
            lst.add(alwaysEnable9);
        }
        if (alwaysEnable10 != null) {
            lst.add(alwaysEnable10);
        }
        return lst;
    }

    public JComponent getDisableOnRefresh1() {
        return disableOnRefresh1;
    }

    public void setDisableOnRefresh1(JComponent disableOnRefresh1) {
        this.disableOnRefresh1 = disableOnRefresh1;
    }

    public JComponent getDisableOnRefresh10() {
        return disableOnRefresh10;
    }

    public void setDisableOnRefresh10(JComponent disableOnRefresh10) {
        this.disableOnRefresh10 = disableOnRefresh10;
    }

    public JComponent getDisableOnRefresh2() {
        return disableOnRefresh2;
    }

    public void setDisableOnRefresh2(JComponent disableOnRefresh2) {
        this.disableOnRefresh2 = disableOnRefresh2;
    }

    public JComponent getDisableOnRefresh3() {
        return disableOnRefresh3;
    }

    public void setDisableOnRefresh3(JComponent disableOnRefresh3) {
        this.disableOnRefresh3 = disableOnRefresh3;
    }

    public JComponent getDisableOnRefresh4() {
        return disableOnRefresh4;
    }

    public void setDisableOnRefresh4(JComponent disableOnRefresh4) {
        this.disableOnRefresh4 = disableOnRefresh4;
    }

    public JComponent getDisableOnRefresh5() {
        return disableOnRefresh5;
    }

    public void setDisableOnRefresh5(JComponent disableOnRefresh5) {
        this.disableOnRefresh5 = disableOnRefresh5;
    }

    public JComponent getDisableOnRefresh6() {
        return disableOnRefresh6;
    }

    public void setDisableOnRefresh6(JComponent disableOnRefresh6) {
        this.disableOnRefresh6 = disableOnRefresh6;
    }

    public JComponent getDisableOnRefresh7() {
        return disableOnRefresh7;
    }

    public void setDisableOnRefresh7(JComponent disableOnRefresh7) {
        this.disableOnRefresh7 = disableOnRefresh7;
    }

    public JComponent getDisableOnRefresh8() {
        return disableOnRefresh8;
    }

    public void setDisableOnRefresh8(JComponent disableOnRefresh8) {
        this.disableOnRefresh8 = disableOnRefresh8;
    }

    public JComponent getDisableOnRefresh9() {
        return disableOnRefresh9;
    }

    public void setDisableOnRefresh9(JComponent disableOnRefresh9) {
        this.disableOnRefresh9 = disableOnRefresh9;
    }

    public JComponent getDisableOnNew1() {
        return disableOnNew1;
    }

    public void setDisableOnNew1(JComponent disableOnNew1) {
        this.disableOnNew1 = disableOnNew1;
    }

    public JComponent getDisableOnNew10() {
        return disableOnNew10;
    }

    public void setDisableOnNew10(JComponent disableOnNew10) {
        this.disableOnNew10 = disableOnNew10;
    }

    public JComponent getDisableOnNew2() {
        return disableOnNew2;
    }

    public void setDisableOnNew2(JComponent disableOnNew2) {
        this.disableOnNew2 = disableOnNew2;
    }

    public JComponent getDisableOnNew3() {
        return disableOnNew3;
    }

    public void setDisableOnNew3(JComponent disableOnNew3) {
        this.disableOnNew3 = disableOnNew3;
    }

    public JComponent getDisableOnNew4() {
        return disableOnNew4;
    }

    public void setDisableOnNew4(JComponent disableOnNew4) {
        this.disableOnNew4 = disableOnNew4;
    }

    public JComponent getDisableOnNew5() {
        return disableOnNew5;
    }

    public void setDisableOnNew5(JComponent disableOnNew5) {
        this.disableOnNew5 = disableOnNew5;
    }

    public JComponent getDisableOnNew6() {
        return disableOnNew6;
    }

    public void setDisableOnNew6(JComponent disableOnNew6) {
        this.disableOnNew6 = disableOnNew6;
    }

    public JComponent getDisableOnNew7() {
        return disableOnNew7;
    }

    public void setDisableOnNew7(JComponent disableOnNew7) {
        this.disableOnNew7 = disableOnNew7;
    }

    public JComponent getDisableOnNew8() {
        return disableOnNew8;
    }

    public void setDisableOnNew8(JComponent disableOnNew8) {
        this.disableOnNew8 = disableOnNew8;
    }

    public JComponent getDisableOnNew9() {
        return disableOnNew9;
    }

    public void setDisableOnNew9(JComponent disableOnNew9) {
        this.disableOnNew9 = disableOnNew9;
    }

    public JComponent getDisableOnUpdate10() {
        return disableOnUpdate10;
    }

    public void setDisableOnUpdate10(JComponent disableOnUpdate10) {
        this.disableOnUpdate10 = disableOnUpdate10;
    }

    public JComponent getDisableOnUpdate2() {
        return disableOnUpdate2;
    }

    public void setDisableOnUpdate2(JComponent disableOnUpdate2) {
        this.disableOnUpdate2 = disableOnUpdate2;
    }

    public JComponent getDisableOnUpdate3() {
        return disableOnUpdate3;
    }

    public void setDisableOnUpdate3(JComponent disableOnUpdate3) {
        this.disableOnUpdate3 = disableOnUpdate3;
    }

    public JComponent getDisableOnUpdate4() {
        return disableOnUpdate4;
    }

    public void setDisableOnUpdate4(JComponent disableOnUpdate4) {
        this.disableOnUpdate4 = disableOnUpdate4;
    }

    public JComponent getDisableOnUpdate5() {
        return disableOnUpdate5;
    }

    public void setDisableOnUpdate5(JComponent disableOnUpdate5) {
        this.disableOnUpdate5 = disableOnUpdate5;
    }

    public JComponent getDisableOnUpdate6() {
        return disableOnUpdate6;
    }

    public void setDisableOnUpdate6(JComponent disableOnUpdate6) {
        this.disableOnUpdate6 = disableOnUpdate6;
    }

    public JComponent getDisableOnUpdate7() {
        return disableOnUpdate7;
    }

    public void setDisableOnUpdate7(JComponent disableOnUpdate7) {
        this.disableOnUpdate7 = disableOnUpdate7;
    }

    public JComponent getDisableOnUpdate8() {
        return disableOnUpdate8;
    }

    public void setDisableOnUpdate8(JComponent disableOnUpdate8) {
        this.disableOnUpdate8 = disableOnUpdate8;
    }

    public JComponent getDisableOnUpdate9() {
        return disableOnUpdate9;
    }

    public void setDisableOnUpdate9(JComponent disableOnUpdate9) {
        this.disableOnUpdate9 = disableOnUpdate9;
    }

    public JTable getMasterTable() {
        return masterTable;
    }

    public void setMasterTable(JTable masterTable) {
        this.masterTable = masterTable;
        TableSelectionListener listener = new TableSelectionListener();
        masterTable.getSelectionModel().addListSelectionListener(listener);
    }

    public JComponent getDisableOnUpdate1() {
        return disableOnUpdate1;
    }

    public void setDisableOnUpdate1(JComponent disableOnUpdate1) {
        this.disableOnUpdate1 = disableOnUpdate1;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton lbtnDelete) {
        this.btnDelete = lbtnDelete;
        this.btnDelete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!isValidDelete()) {
                    return;
                }
                if (btnNew != null) {
                    btnNew.setEnabled(true);
                }
                if (btnDelete != null) {
                    btnDelete.setEnabled(false);
                }
                if (btnRefresh != null) {
                    btnRefresh.setEnabled(true);
                }
                if (btnSave != null) {
                    btnSave.setEnabled(false);
                }
                disableAll();
                disableAllRefresh();
                setNewRecord(false);
                unhideAllNoRecord(false);
            }
        });
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public void setBtnNew(JButton lbtnNew) {
        this.btnNew = lbtnNew;
        this.btnNew.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getFormValidator() != null) {
                    if (!getFormValidator().validateOnClickNew()) {
                        return;
                    }
                }
                if (btnNew != null) {
                    btnNew.setEnabled(false);
                }
                if (btnDelete != null) {
                    btnDelete.setEnabled(false);
                }
                if (btnRefresh != null) {
                    btnRefresh.setEnabled(true);
                }
                if (btnSave != null) {
                    btnSave.setEnabled(true);
                }
                disableAllNew();
                setNewRecord(true);
                unhideAllNoRecord(false);
            }
        });
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public void setBtnRefresh(JButton lbtnRefresh) {
        this.btnRefresh = lbtnRefresh;
        this.btnRefresh.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getFormValidator() != null) {
                    if (!getFormValidator().validateOnClickRefresh()) {
                        return;
                    }
                }
                if (btnNew != null) {
                    btnNew.setEnabled(true);
                }
                if (btnDelete != null) {
                    btnDelete.setEnabled(false);
                }
                if (btnRefresh != null) {
                    btnRefresh.setEnabled(true);
                }
                if (btnSave != null) {
                    btnSave.setEnabled(false);
                }
                disableAllRefresh();
                disableAll();
                setNewRecord(false);
                unhideAllNoRecord(false);
            }
        });
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(JButton lbtnSave) {
        this.btnSave = lbtnSave;
        this.btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getFormValidator() != null) {
                    if (!getFormValidator().validateOnClickSave(isNewRecord())) {
                        return;
                    }
                }
                if (btnNew != null) {
                    btnNew.setEnabled(true);
                }
                if (btnDelete != null) {
                    btnDelete.setEnabled(true);
                }
                if (btnRefresh != null) {
                    btnRefresh.setEnabled(true);
                }
                if (btnSave != null) {
                    btnSave.setEnabled(true);
                }
                disableAllUpdate();
                setNewRecord(false);
            }
        });
    }

    private void enableAll() {
        List<JComponent> lst = getAllDisableOnUpdate();
        lst.addAll(getAllDisableOnNew());
        lst.addAll(getAllDisableOnRefresh());
        for (JComponent comp : lst) {
            comp.setEnabled(true);
        }
    }

    private void disableAll() {
        List<JComponent> lst = getAllDisableOnUpdate();
        lst.addAll(getAllDisableOnNew());
        lst.addAll(getAllDisableOnRefresh());
        for (JComponent comp : lst) {
            comp.setEnabled(false);
        }
    }

    private void disableAllUpdate() {
        enableAll();
        List<JComponent> lst = getAllDisableOnUpdate();
        for (JComponent comp : lst) {
            comp.setEnabled(true);
            if (comp != null) {
                if (comp instanceof JTextComponent) {
                    JTextComponent txt = (JTextComponent) comp;
                    if (!PanelUtil.isEmpty(txt.getText())) {
                        txt.setEnabled(false);
                    }
                }
            }
        }
    }

    private void disableAllRefresh() {
//		enableAll();
        List<JComponent> lst = getAllDisableOnRefresh();
        for (JComponent comp : lst) {
            comp.setEnabled(false);
        }
    }

    private void unhideAllNoRecord(boolean withRecord) {
        List<JComponent> lst = getAllHideOnNoRecords();
        for (JComponent comp : lst) {
            comp.setVisible(withRecord);
        }
    }

    private void disableAllNew() {
        enableAll();
        List<JComponent> lst = getAllDisableOnNew();
        for (JComponent comp : lst) {
            comp.setEnabled(false);
        }
    }

    private void enableAlways() {
        List<JComponent> lst = getAllAlwaysEnable();
        for (JComponent comp : lst) {
            comp.setEnabled(true);
        }
    }

    private class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (e.getSource() == masterTable.getSelectionModel()) {
                    boolean enabled = (masterTable.getSelectedRow() != -1);
                    if (enabled) {
                        disableAllUpdate();
                        if (getBtnSave() != null) {
                            getBtnSave().setEnabled(true);
                        }
                        unhideAllNoRecord(true);
                    }
                }
            }
        }
    }

    public boolean isValidRefresh() {
        if (getFormValidator() != null) {
            return getFormValidator().validateOnClickRefresh();
        }
        return true;
    }

    public boolean isValidDelete() {
        if (getFormValidator() != null) {
            return getFormValidator().validateOnClickDelete();
        }
        return true;
    }

    public boolean isValidNew() {
        if (getFormValidator() != null) {
            return getFormValidator().validateOnClickNew();
        }
        return true;
    }

    public boolean isValidSave() {
        if (getFormValidator() != null) {
            return getFormValidator().validateOnClickSave(newRecord);
        }
        return true;
    }

    public JComponent getAlwaysEnable1() {
        return alwaysEnable1;
    }

    public void setAlwaysEnable1(JComponent alwaysEnable1) {
        this.alwaysEnable1 = alwaysEnable1;
    }

    public JComponent getAlwaysEnable10() {
        return alwaysEnable10;
    }

    public void setAlwaysEnable10(JComponent alwaysEnable10) {
        this.alwaysEnable10 = alwaysEnable10;
    }

    public JComponent getAlwaysEnable2() {
        return alwaysEnable2;
    }

    public void setAlwaysEnable2(JComponent alwaysEnable2) {
        this.alwaysEnable2 = alwaysEnable2;
    }

    public JComponent getAlwaysEnable3() {
        return alwaysEnable3;
    }

    public void setAlwaysEnable3(JComponent alwaysEnable3) {
        this.alwaysEnable3 = alwaysEnable3;
    }

    public JComponent getAlwaysEnable4() {
        return alwaysEnable4;
    }

    public void setAlwaysEnable4(JComponent alwaysEnable4) {
        this.alwaysEnable4 = alwaysEnable4;
    }

    public JComponent getAlwaysEnable5() {
        return alwaysEnable5;
    }

    public void setAlwaysEnable5(JComponent alwaysEnable5) {
        this.alwaysEnable5 = alwaysEnable5;
    }

    public JComponent getAlwaysEnable6() {
        return alwaysEnable6;
    }

    public void setAlwaysEnable6(JComponent alwaysEnable6) {
        this.alwaysEnable6 = alwaysEnable6;
    }

    public JComponent getAlwaysEnable7() {
        return alwaysEnable7;
    }

    public void setAlwaysEnable7(JComponent alwaysEnable7) {
        this.alwaysEnable7 = alwaysEnable7;
    }

    public JComponent getAlwaysEnable8() {
        return alwaysEnable8;
    }

    public void setAlwaysEnable8(JComponent alwaysEnable8) {
        this.alwaysEnable8 = alwaysEnable8;
    }

    public JComponent getAlwaysEnable9() {
        return alwaysEnable9;
    }

    public void setAlwaysEnable9(JComponent alwaysEnable9) {
        this.alwaysEnable9 = alwaysEnable9;
    }

    public JComponent getHideOnNoRecord1() {
        return hideOnNoRecord1;
    }

    public void setHideOnNoRecord1(JComponent hideOnNoRecord1) {
        this.hideOnNoRecord1 = hideOnNoRecord1;
    }

    public JComponent getHideOnNoRecord10() {
        return hideOnNoRecord10;
    }

    public void setHideOnNoRecord10(JComponent hideOnNoRecord10) {
        this.hideOnNoRecord10 = hideOnNoRecord10;
    }

    public JComponent getHideOnNoRecord2() {
        return hideOnNoRecord2;
    }

    public void setHideOnNoRecord2(JComponent hideOnNoRecord2) {
        this.hideOnNoRecord2 = hideOnNoRecord2;
    }

    public JComponent getHideOnNoRecord3() {
        return hideOnNoRecord3;
    }

    public void setHideOnNoRecord3(JComponent hideOnNoRecord3) {
        this.hideOnNoRecord3 = hideOnNoRecord3;
    }

    public JComponent getHideOnNoRecord4() {
        return hideOnNoRecord4;
    }

    public void setHideOnNoRecord4(JComponent hideOnNoRecord4) {
        this.hideOnNoRecord4 = hideOnNoRecord4;
    }

    public JComponent getHideOnNoRecord5() {
        return hideOnNoRecord5;
    }

    public void setHideOnNoRecord5(JComponent hideOnNoRecord5) {
        this.hideOnNoRecord5 = hideOnNoRecord5;
    }

    public JComponent getHideOnNoRecord6() {
        return hideOnNoRecord6;
    }

    public void setHideOnNoRecord6(JComponent hideOnNoRecord6) {
        this.hideOnNoRecord6 = hideOnNoRecord6;
    }

    public JComponent getHideOnNoRecord7() {
        return hideOnNoRecord7;
    }

    public void setHideOnNoRecord7(JComponent hideOnNoRecord7) {
        this.hideOnNoRecord7 = hideOnNoRecord7;
    }

    public JComponent getHideOnNoRecord8() {
        return hideOnNoRecord8;
    }

    public void setHideOnNoRecord8(JComponent hideOnNoRecord8) {
        this.hideOnNoRecord8 = hideOnNoRecord8;
    }

    public JComponent getHideOnNoRecord9() {
        return hideOnNoRecord9;
    }

    public void setHideOnNoRecord9(JComponent hideOnNoRecord9) {
        this.hideOnNoRecord9 = hideOnNoRecord9;
    }
}
