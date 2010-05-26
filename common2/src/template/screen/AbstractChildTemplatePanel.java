/*
 * ChildTemplate1.java
 *
 * Created on August 24, 2008, 6:52 AM
 */

package template.screen;

import component.IGetText;
import component.IRule;
import component.JButtonPallete;
import component.JCalendarPallete;
import component.PalleteRuleManager;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.observablecollections.ObservableCollections;
import rule.BusinessRuleWrapper;
import service.util.AbstractIBean;
import service.util.IBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.DisplayGroup;
import template.UITemplate;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.component.JTableReadOnly;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author  Entokwaa
 */
public class AbstractChildTemplatePanel extends javax.swing.JPanel implements ITemplate {
    public ChildRecord childAnnotation;
    AbstractIBean parentObject;
    public List list = ObservableCollections.observableList(new ArrayList());
    protected Class currentClass;
    protected AbstractIBean screenBean;
    public UITemplate template;
    protected TemplateParserUtil templateParser;
    protected BusinessRuleWrapper ruleWrapper;
    public List<JComponent> lstComponent = new ArrayList<JComponent>();
    BindingGroup bindingGroup;

    public AbstractIBean getBean() {
    	if (tblRecord.getRowCount()>0) {
    		if (tblRecord.getSelectedRowCount()==0) {
    			tblRecord.setRowSelectionInterval(0, 0);
    		}
            Object obj = ELProperty.create("${selectedElement}").getValue(tblRecord);
            return (AbstractIBean) obj;
    	}
    	return null;
    }
    
    public void showDelete(boolean t) {
    	btnDelete.setVisible(t);
    }
    
    public void showUpdate(boolean t) {
    	btnSave.setVisible(t);
    }
    
    public void showAdd(boolean t) {
    	btnNew.setVisible(t);
    }
    
    @Override
	public List<AbstractChildTemplatePanel> getTabs() {
		return null;
	}

    public void saveAll() {
        String sql = ELProperty.create(childAnnotation.sql()).getValue(parentObject).toString();
        List lstTmp = AbstractIBean.list(sql.toString());
        for (Object object : list) {
            putMapping(object);
        }
        
        //use parent to save records
        List lst = new ArrayList();
        for (Object object : list) {
            lst.add(object);
        }
        parentObject.save(lst);
        if (lstTmp!=null) {
            for (Object object : lstTmp) {
                AbstractIBean bean = (AbstractIBean) object;
                if (!listContains(list, bean)) {
                    parentObject.delete(bean);
                }
            }
        }
    	putParent();
    }
    
    private boolean listContains(List<AbstractIBean> lst, AbstractIBean bean) {
        for (AbstractIBean abstractIBean : lst) {
            if (abstractIBean.equals(bean)) {
                return true;
            }
        }
        return false;
    }
    
    public void refreshRecords(AbstractIBean parent) {
        this.parentObject = parent;
        if (list!=null) list.clear();
        AbstractIBean bean = parent;
        if (parent!=null && !bean.isEmptyKey()) {
            String sql = ELProperty.create(childAnnotation.sql()).getValue(parent).toString();
            List lstTmp = parent.selectListCache(sql);
            if (lstTmp!=null) {
                list.addAll(lstTmp);
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setEnabled(true);
                }
            });
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setEnabled(false);
                }
            });
        }
    	putParent();
    }

    public void putParent() {
    	if (list!=null) {
    		for (Object obj:list) {
    			try {
        			AbstractIBean b = (AbstractIBean) obj;
        			b.parentBean = this.parentObject;
    			}
    			catch (Exception e) {
    			}
    		}
    	}
    }
    
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
                    if (lstTmp!=null) {
                    	list.addAll(lstTmp);
                    }
                }
            }
            catch (Exception e) {
                Log.out(BeanUtil.concat("CHILD SQL EXCEPTION ==",sql));
//                e.printStackTrace();
            }
        }
        else {

        }
        templateParser.bindTable(bindingGroup, list, tblRecord, currentClass);

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
        putButton(false, pnlMain, cons);
        bindingGroup.bind();
        
        btnDelete.setVisible(childAnnotation.canDelete());
        btnNew.setVisible(childAnnotation.canNew());
        pnlMain.setVisible(childAnnotation.showForm());
        ((JTableReadOnly)tblRecord).setEditableCol(template.editableCol());
        ((JTableReadOnly)tblRecord).setRule(ruleWrapper);
        ruleWrapper.setTable(((JTableReadOnly)tblRecord));
        
        if (template.tableColumnWidth()!=null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    resizeTable();
                }
            });
        }
    	putParent();
   }

    public void resizeTable() {
        TableColumn col;
        String string = null;
        String[] arr = template.tableColumnWidth().split(",");
        if (arr!=null && arr.length>0) {
            for (int i = 0; i < arr.length; i++) {
                string = arr[i];
                if (string==null || string.isEmpty()) continue;
                try {
                    int k = Integer.parseInt(string);
                    col = tblRecord.getColumnModel().getColumn(i);
//                        col.setMaxWidth(k);
                    col.setMinWidth(k);
                    col.setPreferredWidth(k);
                    col.setWidth(k);
                }
                catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setEnabled(boolean b) {
        for (JButton btn : lstBtn) {
            btn.setEnabled(b);
        }
        for (JComponent c : lstComponent) {
            c.setEnabled(b);
        }
        btnDelete.setEnabled(b);
        btnNew.setEnabled(b);
        btnSave.setEnabled(b);
        super.setEnabled(b);
    }
    
    protected List<JButton> lstBtn = new ArrayList<JButton>();
    public void putButton(boolean top, JPanel pnl, GridBagConstraints cons) {
        if (!childAnnotation.showButtons()) return;
        ActionButtons btns = (ActionButtons) currentClass.getAnnotation(ActionButtons.class);
        if (btns==null) {
            return;
        }
        ActionButton buttons[] = btns.value();
        if (buttons==null || buttons.length==0) return;
        
        JPanel panel = new JPanel(new FlowLayout());
        cons.gridy++;
        cons.gridwidth = template.gridCount();
        pnl.add(panel, cons);
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
                this.addFieldComponent(btn);
                lstBtn.add(btn);
                panel.add(btn);
            }
        }
    }
    
    public static AbstractChildTemplatePanel getInstance(ChildRecord childAnnotation, AbstractIBean parent) {
        try {
            AbstractChildTemplatePanel tmp = (AbstractChildTemplatePanel) childAnnotation.template().newInstance();
            tmp.templateParser = TemplateParserUtil.getInstance(tmp);
            tmp.setBean(childAnnotation.entity());
            UITemplate template = (UITemplate) childAnnotation.entity().getAnnotation(UITemplate.class);
            if (template.rule().isEmpty()) {
                tmp.ruleWrapper = BusinessRuleWrapper.getInstance(tmp);
            }
            else {
                tmp.ruleWrapper = BusinessRuleWrapper.getInstance(template);
                tmp.ruleWrapper.panel = tmp;
            }
            tmp.setup(childAnnotation, parent);
            if (constants.Constants.panelBackground!=null) {
                tmp.setBackground(constants.Constants.panelBackground);
                if (tmp.pnlMain!=null) tmp.pnlMain.setBackground(constants.Constants.panelBackground);
                if (tmp.pnlMainForm!=null) tmp.pnlMainForm.setBackground(constants.Constants.panelBackground);
                if (tmp.pnlRightButtons!=null) tmp.pnlRightButtons.setBackground(constants.Constants.panelBackground);
                if (tmp.pnlTop!=null) tmp.pnlTop.setBackground(constants.Constants.panelBackground);
            }
        	if (!childAnnotation.autoResizeTable()) {
        		tmp.tblRecord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        	}
            if (childAnnotation.sortable()) {
                tmp.tblRecord.setAutoCreateRowSorter(true);
            }
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractChildTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public final void setBean(Class cls) {
        try {
            this.currentClass = cls;
        } catch (Exception ex) {
            Logger.getLogger(AbstractTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Creates new form ChildTemplate1 */
    public AbstractChildTemplatePanel() {
        initComponents();
        if (constants.Constants.panelBackground!=null) {
            scrollTable.getViewport().setBackground(Color.WHITE);
//            tblRecord.setBackground(constants.Constants.panelBackground);
        }
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

        jScrollPane2 = new javax.swing.JScrollPane();
        pnlTop = new javax.swing.JPanel();
        pnlRightButtons = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        tblRecord = new JTableReadOnly();
        pnlMainForm = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        pnlTop.setName("pnlTop"); // NOI18N

        pnlRightButtons.setName("pnlRightButtons"); // NOI18N
        pnlRightButtons.setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(AbstractChildTemplatePanel.class);
        btnNew.setFont(resourceMap.getFont("btnNew.font")); // NOI18N
        btnNew.setText(resourceMap.getString("btnNew.text")); // NOI18N
        btnNew.setName("btnNew"); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlRightButtons.add(btnNew);

        btnDelete.setFont(resourceMap.getFont("btnNew.font")); // NOI18N
        btnDelete.setText(resourceMap.getString("btnDelete.text")); // NOI18N
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlRightButtons.add(btnDelete);

        btnSave.setFont(resourceMap.getFont("btnNew.font")); // NOI18N
        btnSave.setText(resourceMap.getString("btnSave.text")); // NOI18N
        btnSave.setName("btnSave"); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlRightButtons.add(btnSave);

        scrollTable.setFont(resourceMap.getFont("scrollTable.font")); // NOI18N
        scrollTable.setName("scrollTable"); // NOI18N

        tblRecord.setFont(resourceMap.getFont("tblRecord.font")); // NOI18N
        tblRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRecord.setName("tblRecord"); // NOI18N
        tblRecord.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollTable.setViewportView(tblRecord);

        pnlMainForm.setName("pnlMainForm"); // NOI18N
        pnlMainForm.setLayout(new java.awt.GridBagLayout());

        pnlMain.setName("pnlMain"); // NOI18N
        pnlMain.setLayout(new java.awt.GridBagLayout());

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        pnlMain.add(jButton1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlMainForm.add(pnlMain, gridBagConstraints);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMainForm, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlRightButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(pnlRightButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMainForm, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(pnlTop);

        add(jScrollPane2);
    }// </editor-fold>//GEN-END:initComponents

    protected void putMapping(Object obj) {
        String chi = "";
        String par = "";
        try {
            String[] mapping = childAnnotation.fieldMapping();
            for (int i = 0; i < mapping.length; i++) {
                par = mapping[i++];
                if (par==null || par.isEmpty()) break;
                chi = mapping[i];
                try {
                    Object parObj = BeanProperty.create(par).getValue(parentObject);
                    if (parObj!=null) BeanProperty.create(chi).setValue(obj, parObj);
                }
                catch (Exception e) {
                    util.Log.severe("MAPPING ERROR ",chi,":",par);
                }
            }
        } catch (Exception ex) {
            util.Log.severe("MAPPING ERROR ",chi,":",par);
        }
    }
    
private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
	try {   
		Object obj = childAnnotation.entity().newInstance();//GEN-LAST:event_btnNewActionPerformed
	    putMapping(obj);
	    list.add(obj);
	    tblRecord.setRowSelectionInterval(list.size()-1, list.size()-1);
		putParent();
	} catch (Exception e) {
	}
}

protected boolean deleteRecord() {
    return PanelUtil.showPrompt(btnDelete, "Are you sure to delete the record?");
}

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    int row = tblRecord.getSelectedRow();
    if (row>=0) {
        if (deleteRecord()) {
            Object obj = ELProperty.create("${selectedElement}").getValue(tblRecord);
            ruleWrapper.beforeDelete((AbstractIBean) obj);
            AbstractIBean bean = (AbstractIBean) obj;
            bean.delete();
            ((TransactionPanel)TransactionPanel.getCurrentPanel()).redisplayRecord();
            list.remove(obj);
            if (tblRecord.getRowCount()>0) {
                if (tblRecord.getRowCount()>row) {
                    tblRecord.setRowSelectionInterval(row, row);
                }
                else {
                    tblRecord.setRowSelectionInterval(0, 0);
                }
            }
            ruleWrapper.afterDelete((AbstractIBean) obj);
        }
    }
}//GEN-LAST:event_btnDeleteActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    saveRecord();
}//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnNew;
    public javax.swing.JButton btnSave;
    public javax.swing.JButton jButton1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel pnlMain;
    public javax.swing.JPanel pnlMainForm;
    public javax.swing.JPanel pnlRightButtons;
    public javax.swing.JPanel pnlTop;
    public javax.swing.JScrollPane scrollTable;
    public javax.swing.JTable tblRecord;
    // End of variables declaration//GEN-END:variables

    public Object getCurrentObject() {
        AbstractIBean b = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tblRecord);
        BeanUtil.copyBean(b, screenBean);
        return screenBean;
    }

    public Class getCurrentClass() {
        return this.currentClass;
    }

    public UITemplate getUITemplate() {
        return this.template;
    }

    public JTable getTable() {
        return this.tblRecord;
    }

    public BusinessRuleWrapper getRuleWrapper() {
        return ruleWrapper;
    }

    public List<JComponent> getFieldComponents() {
        return lstComponent;
    }

    public void addFieldComponent(JComponent comp) {
        lstComponent.add(comp);
    }

    public boolean isParent() {
        return false;
    }

    public BindingGroup getBinding() {
        return bindingGroup;
    }

    protected void setAllValues(IBean bean) {
        for (JComponent elem : lstComponent) {
            if (elem instanceof JCalendarPallete) {
                String name = elem.getName();
                BeanUtil.setPropertyValue(bean, name, ((JCalendarPallete)elem).getDate());
            }
            else if (elem instanceof IGetText) {
                try {
                    String name = elem.getName();
                    Class type = currentClass.getField(name).getType();
                    if (type.getSimpleName().toLowerCase().indexOf("int")!=-1) {
                        BeanUtil.setPropertyValue(bean, name, Integer.parseInt(((IGetText)elem).getText()));
                    }
                    else if (type.getSimpleName().toLowerCase().indexOf("dou")!=-1) {
                        BeanUtil.setPropertyValue(bean, name, Double.parseDouble(((IGetText)elem).getText()));
                    }
                    else {
                        BeanUtil.setPropertyValue(bean, name, ((IGetText)elem).getText());
                    }
                }
                catch (Exception e) {
                }
            }
        }
        tblRecord.updateUI();
    }

    protected void saveRecord() {
        AbstractIBean bean = (AbstractIBean) getCurrentObject();
        String beanName = currentClass.getSimpleName();
        Log.out("BEAN == ",beanName);
        if (bean==null) {
        	return;
        }
        setAllValues(bean);
        putMapping(bean);
        //check mandatory
        if (mandatoryOk()) {
            bean.save();
            ruleWrapper.afterSave(bean);
//            ((TransactionPanel)TransactionPanel.getCurrentPanel()).redisplayRecord();
            PanelUtil.showErrorMessageToScreen("Child record was saved.");
        }
    }
    
    public boolean mandatoryOk() {
        for (JComponent elem : lstComponent) {
            if (elem instanceof IGetText) {
                boolean empty = ((IGetText) elem).isEmpty();
                if (empty) {
                    try {
                        FieldCompose f = ((IGetText) elem).getFieldCompose();
                        Column col = f.field.getAnnotation(Column.class);
                        if (col!=null && !col.nullable()){
                            PanelUtil.showError(elem, PanelUtil.getLabel(f)," is mandatory."); 
                            return false;
                        }
                        if (f.display!=null && f.display.mandatory()){
                            PanelUtil.showError(elem, PanelUtil.getLabel(f)," is mandatory."); 
                            return false;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AbstractChildTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return true;
    }

    public List getRecordList() {
        return list;
    }

	@Override
	public JTabbedPane getTabPane() {
		// TODO Auto-generated method stub
		return null;
	}

//    public boolean wasChanged() {
//        AbstractIBean bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tblRecord);
//    }
}
