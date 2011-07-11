/*
 * ChildTemplate2.java
 *
 * Created on September 8, 2008, 10:26 AM
 */

package template.screen;

import component.IRule;
import component.JButtonPallete;
import component.PalleteRuleManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.ELProperty;
import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.DisplayGroup;
import template.UITemplate;
import template.screen.component.JTableReadOnly;
import util.BeanUtil;
import util.DataUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author  Entokwaa
 */
public class ChildTemplateListPopup extends AbstractChildTemplatePanel {
    boolean alreadyOpen;
    AbstractIBean forUpdate;
    JButton btnCancelUpdate = new JButtonPallete("Cancel");
    JButton btnCancelSave = new JButtonPallete("Cancel");
    JButton myNewButton = new JButtonPallete("     New     ");
    JButton myUpdateButton = new JButtonPallete("    Update    ");
    
    public void showDelete(boolean t) {
    	btnDelete.setVisible(t);
    }
    
    public void showUpdate(boolean t) {
    	btnSave.setVisible(t);
    	myUpdateButton.setVisible(t);
    }
    
    public void showAdd(boolean t) {
    	btnNew.setVisible(t);
    	myNewButton.setVisible(t);
    }
    
    @Override
    public void saveAll() {
        //do nothing on save all
    }

    @Override
    protected void setup(ChildRecord childAnnotation, AbstractIBean parent) {
        bindingGroup = new BindingGroup();
        this.childAnnotation = childAnnotation;
        this.parentObject = parent;
        String sql = null;
        if (parent!=null) {
            try {
                sql = ELProperty.create(childAnnotation.sql()).getValue(parent).toString();
                try {
                    currentClass = childAnnotation.entity();
                    template = (UITemplate) currentClass.getAnnotation(UITemplate.class);
                    screenBean = (AbstractIBean) currentClass.newInstance();
                } catch (Exception ex) {
                    Logger.getLogger(AbstractTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                //bind table record
                if (!((AbstractIBean)parent).isEmptyKey()) {
                    List lstTmp = AbstractIBean.list(sql);
                    if (lstTmp!=null) list.addAll(lstTmp);
                }
            }
            catch (Exception e) {
                Log.out("CHILD SQL EXCEPTION ==",sql);
//                e.printStackTrace();
            }
        }
        else {

        }
        templateParser.bindTable(bindingGroup, list, tblRecord, currentClass);
        tblRecord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    updateRecord();
                }
            }
        });
        
        //bind the main form
        pnlMain.removeAll();
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(5, 2, 2, 2);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridy++;
        
        cons.gridy++;
        JPanel pnlNoGroup = templateParser.constructGroupPanel(bindingGroup, null);
        pnlMain.add(pnlNoGroup, cons);

        DisplayGroup[] groups = templateParser.getGroups();
        if (groups!=null && groups.length>0) {
            for (DisplayGroup displayGroup : groups) {
                JPanel tmp = templateParser.constructGroupPanel(bindingGroup, displayGroup);
                cons.gridy++;
                pnlMain.add(tmp, cons);
            }
        }
        putButton(false, cons);
        bindingGroup.bind();
        pnlPopup.add(pnlMain);
        btnCancelUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelUpdate();
            }
        });
        btnCancelSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelSave();
            }
        });
        pnlSave.add(btnSave);
        pnlSave.add(btnNew);
        pnlSave.add(btnCancelUpdate);
        pnlSave.add(btnCancelSave);
//        dlgForm.setSize(600, 450);
        
        //setup the main childform
        btnDelete.setVisible(childAnnotation.canDelete());
        btnNew.setVisible(childAnnotation.canNew());
        pnlMain.setVisible(childAnnotation.showForm());
        this.removeAll();
        pnlTempLeft.removeAll();
        pnlTempLeft.setLayout(new GridLayout());
        JPanel n = new JPanel(new GridLayout());
        n.add(scrollTable);
        pnlTempLeft.add(getNewPanel(n));

        pnlTempRight.removeAll();
        pnlTempRight.setLayout(new GridBagLayout());
        
        cons = new GridBagConstraints();
        cons.fill = cons.HORIZONTAL;
        cons.insets = new Insets(2,2,2,2);
        cons.gridy++;
        myNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newRecord();
            }
        });
        myUpdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });
        myNewButton.setVisible(childAnnotation.canNew());
        pnlTempRight.add(myNewButton, cons);
        cons.gridy++;
        pnlTempRight.add(myUpdateButton, cons);
        cons.gridy++;
        pnlTempRight.add(btnDelete, cons);
        // add all buttons
        cons.gridy++;
        pnlTempRight.add(new JLabel("  "), cons);
        for (JButton btn : lstBtn) {
            cons.gridy++;
            pnlTempRight.add(btn, cons);
        }
        this.add(pnlRecords);
        if (!childAnnotation.showButtons()) {
            pnlTempRight.setVisible(false);
            pnlSave.setVisible(false);
        }
        if (constants.Constants.panelBackground!=null) {
            setBackground(constants.Constants.panelBackground);
            if (pnlNoGroup!=null) pnlNoGroup.setBackground(constants.Constants.panelBackground);
            if (pnlPopup!=null) pnlPopup.setBackground(constants.Constants.panelBackground);
            if (pnlRecords!=null) pnlRecords.setBackground(constants.Constants.panelBackground);
            if (pnlSave!=null) pnlSave.setBackground(constants.Constants.panelBackground);
            if (pnlTempLeft!=null) pnlTempLeft.setBackground(constants.Constants.panelBackground);
            if (pnlTempRight!=null) pnlTempRight.setBackground(constants.Constants.panelBackground);
        }
        ((JTableReadOnly)tblRecord).setEditableCol(template.editableCol());
        ((JTableReadOnly)tblRecord).setRule(ruleWrapper);
        ruleWrapper.setTable(((JTableReadOnly)tblRecord));
        
        if (template.tableColumnWidth()!=null) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    resizeTable();
                }
            });
        }
    	putParent();
    }
    protected JPanel newpnl;
    private JPanel getNewPanel(JPanel pnl) {
        pnlResultFooter.setVisible(false);
        setupFooter();
    	tblRecord.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
            public void columnMarginChanged(ChangeEvent e) {
                setupFooter();
            }
            public void columnSelectionChanged(ListSelectionEvent e) {
                setupFooter();
            }
            public void columnAdded(TableColumnModelEvent e) {
            }
            public void columnMoved(TableColumnModelEvent e) {
            }
            public void columnRemoved(TableColumnModelEvent e) {
            }
        });
    	newpnl = new JPanel(new BorderLayout());
    	newpnl.add(pnl, BorderLayout.CENTER);
    	newpnl.add(pnlResultFooter, BorderLayout.SOUTH);
    	return newpnl;
    }
    
    public void setupFooter() {
        int count = tblRecord.getColumnCount();
        for (int i = 0; i < count; i++) {
            JLabel p = getFooterLabel(i);
            Rectangle rec = tblRecord.getCellRect(0, i, true);
            Dimension dim = new Dimension(rec.width, 20);
        	if (i==0) {
//        		must have a way to deduct width in the first column
        		if (scrollTable.getVerticalScrollBar().isVisible()) {
            		dim.width -= 15;
        		}
        	}
            p.setPreferredSize(dim);
        }
        pnlResultFooter.updateUI();
        String sumFooter = template.sumFooter();
        pnlResultFooter.setVisible(sumFooter!=null && !sumFooter.isEmpty());
    }
    
    Map<Integer, JLabel> mapFooter = new HashMap<Integer, JLabel>();
    GridBagConstraints conFooter = new GridBagConstraints();
    JPanel pnlResultFooter = new JPanel(new GridBagLayout());
    
    public JLabel getFooterLabel(int i) {
        if (mapFooter.get(i)!=null) {
            return mapFooter.get(i);
        }
        tblRecord.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    calculateSum();
                }
            }
        });
        JLabel lbl = new JLabel();
        if (withSumFooter(i)) {
            lbl.setBorder(new LineBorder(Color.GRAY));
        }
        conFooter.gridx++;
        pnlResultFooter.add(lbl, conFooter);
        mapFooter.put(i, lbl);
        return lbl;
    }
    private void calculateSum() {
        int count = tblRecord.getColumnCount();
        for (int i = 0; i < count; i++) {
            if (withSumFooter(i)) {
                calculateSum(i);
            }
        }
    }
    
    private void calculateSum(int col) {
        double total = 0;
        int rows = tblRecord.getRowCount();
        for (int i = 0; i < rows; i++) {
            try {
                String str = tblRecord.getValueAt(i, col).toString();
                double d = Double.parseDouble(str);
                total += d;
            }
            catch (Exception e) {
            }
        }
        JLabel lbl = getFooterLabel(col);
        lbl.setHorizontalAlignment(JLabel.RIGHT);
        lbl.setText(BeanUtil.concat(DataUtil.getCurrencyFormat(total)));
    }
    
    String sumFooter;
    private boolean withSumFooter(int i) {
        if (sumFooter==null) {
        	sumFooter = BeanUtil.concat(",",template.sumFooter(),",");
        }
        if (sumFooter==null) return false;
        return sumFooter.contains(BeanUtil.concat(",",i,","));
    }

    @Override
    public void setEnabled(boolean b) {
        for (JButton btn : lstBtn) {
            btn.setEnabled(b);
        }
        btnDelete.setEnabled(b);
        btnNew.setEnabled(b);
        btnSave.setEnabled(b);
        myNewButton.setEnabled(b);
        myUpdateButton.setEnabled(b);
        super.setEnabled(b);
    }

    private void cancelUpdate() {
        dlgForm.setVisible(false);
        //revert the value of bean
        AbstractIBean myBean = (AbstractIBean) getCurrentObject();
        if (forUpdate!=null) {
            BeanUtil.copyBean(forUpdate, myBean);
            try {
                int row = tblRecord.getSelectedRow();
                tblRecord.selectAll();
                tblRecord.setRowSelectionInterval(row, row);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cancelSave() {
        dlgForm.setVisible(false);
        list.remove(0);
        try {
            tblRecord.setRowSelectionInterval(0, 0);
        }
        catch (Exception e){
            
        }
    }
    
    public void updateRecord() {
        if (tblRecord.getSelectedRowCount()==0) {
            PanelUtil.showError(tblRecord, "Please select a record to update.");
        }
        else {
            AbstractIBean myBean = (AbstractIBean) getCurrentObject();
            forUpdate = (AbstractIBean) myBean.clone();
            btnCancelUpdate.setVisible(true);
            btnCancelSave.setVisible(false);
            if (!alreadyOpen) {
                dlgForm.pack();
                alreadyOpen = true;
            }
            dlgForm.setVisible(true);
        }
    }
    
    private void newRecord() {
        try {
            Object obj = childAnnotation.entity().newInstance();                                      
            putMapping(obj);
            list.add(0, obj);
            tblRecord.setRowSelectionInterval(0, 0);
            //popup the dialog screen
            btnCancelUpdate.setVisible(false);
            btnCancelSave.setVisible(true);
            if (!alreadyOpen) {
                dlgForm.pack();
                alreadyOpen = true;
            }
            dlgForm.setVisible(true);
        } catch (Exception e) {
        }
    }

    public void putButton(boolean top, GridBagConstraints cons) {
        if (!childAnnotation.showButtons()) return;
        ActionButtons btns = (ActionButtons) currentClass.getAnnotation(ActionButtons.class);
        if (btns==null) {
            return;
        }
        ActionButton buttons[] = btns.value();
        if (buttons==null || buttons.length==0) return;
        
        cons.gridy++;
        cons.gridwidth = template.gridCount();
        for (ActionButton button : buttons) {
            if (button.parentOnly()) continue;
            if (button.top()==top) {
                JButton btn = new JButtonPallete(button.label(), button.name());
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                    }
                });
                btn.setName(button.name());

                JButton btn2 = new JButtonPallete(button.label(), button.name());
                btn2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                    }
                });
                btn2.setName(button.name());
                pnlSave.add(btn2);

                this.addFieldComponent(btn);
                lstBtn.add(btn);
            }
        }
    }

    /** Creates new form ChildTemplate2 */
    public ChildTemplateListPopup() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRecords = new javax.swing.JPanel();
        pnlTempRight = new javax.swing.JPanel();
        pnlTempLeft = new javax.swing.JPanel();
        dlgForm = new javax.swing.JDialog();
        pnlPopup = new javax.swing.JPanel();
        pnlSave = new javax.swing.JPanel();

        pnlRecords.setName("pnlRecords"); // NOI18N
        pnlRecords.setLayout(new java.awt.BorderLayout());

        pnlTempRight.setName("pnlTempRight"); // NOI18N
        pnlRecords.add(pnlTempRight, java.awt.BorderLayout.EAST);

        pnlTempLeft.setName("pnlTempLeft"); // NOI18N
        pnlRecords.add(pnlTempLeft, java.awt.BorderLayout.CENTER);

        dlgForm.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(ChildTemplateListPopup.class);
        dlgForm.setTitle(resourceMap.getString("dlgForm.title")); // NOI18N
        try {
            dlgForm.setAlwaysOnTop(true);
        }
        catch (Exception e) {
        }
        dlgForm.setModal(true);
        dlgForm.setName("dlgForm"); // NOI18N
        dlgForm.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dlgFormWindowClosing(evt);
            }
        });

        pnlPopup.setName("pnlPopup"); // NOI18N
        pnlPopup.setLayout(new java.awt.GridLayout(1, 0));
        dlgForm.getContentPane().add(pnlPopup, java.awt.BorderLayout.CENTER);

        pnlSave.setName("pnlSave"); // NOI18N
        dlgForm.getContentPane().add(pnlSave, java.awt.BorderLayout.SOUTH);

        setLayout(new java.awt.GridLayout(1, 0, 10, 10));
    }// </editor-fold>//GEN-END:initComponents

private void dlgFormWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dlgFormWindowClosing
    AbstractIBean bean = (AbstractIBean) getCurrentObject();
    if (bean==null) return;
    putMapping(bean);
    //check mandatory
    if (mandatoryOk()) {
        dlgForm.setVisible(false);
//        bean.save();
    }
}//GEN-LAST:event_dlgFormWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog dlgForm;
    private javax.swing.JPanel pnlPopup;
    private javax.swing.JPanel pnlRecords;
    protected javax.swing.JPanel pnlSave;
    protected javax.swing.JPanel pnlTempLeft;
    protected javax.swing.JPanel pnlTempRight;
    // End of variables declaration//GEN-END:variables

    public void refreshRecords(AbstractIBean parent) {
    	super.refreshRecords(parent);
    	calculateSum();
    }
}
