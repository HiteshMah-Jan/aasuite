/*
 * AbstractTemplatePanel.java
 *
 * Created on August 24, 2008, 6:58 AM
 */

package template.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import org.jdesktop.beansbinding.AutoBinding;
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
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.component.AbstractComponentRenderer;
import template.screen.component.ComboRenderer;
import template.screen.component.JTableReadOnly;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.PanelUtil;

import common2.Common2View;
import component.ChartDisplayer;
import component.FileUploadRendererPallete;
import component.IGetText;
import component.IRule;
import component.ImageRendererPallete;
import component.JButtonPallete;
import component.JCalendarPallete;
import component.JComboBoxPallete;
import component.LookupTableFieldPallete;
import component.PalleteRuleManager;
import constants.UserInfo;

/**
 *
 * @author  Entokwaa
 */
public class AbstractTemplatePanel extends TransactionPanel implements ITemplate {
    public Class currentClass;
    public AbstractIBean currentObject;
    public List<FieldSearch> lstSearch = new ArrayList<FieldSearch>();
    public List<FieldCompose> lstCompose = new ArrayList<FieldCompose>();
    public List list = ObservableCollections.observableList(new ArrayList());
    protected boolean runMain, runSearch, runResult;
    private AbstractIBean emptyObject;
    public ChildRecords childRecordsAnnotation;
    public UITemplate template;
    protected TemplateParserUtil templateParser;
    public BusinessRuleWrapper ruleWrapper;
    public List<JComponent> lstComponent = new ArrayList<JComponent>();
    public ImageRendererPallete imagePallete;
    public FileUploadRendererPallete filePallete;
    BindingGroup bindingGroup;

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JComponent c : lstComponent) {
            c.setEnabled(enabled);
        }
    }

    public void loadSql(String sql) {
        if (sql==null || sql.isEmpty()) return;
        if (list!=null && list.size()>0) list.clear();
        List lstTmp = DBClient.getList(sql);
        if (lstTmp!=null && lstTmp.size()>0) list.addAll(lstTmp);
        tblResult.updateUI();
    }

    public void refreshCombo() {
        for (AbstractComponentRenderer absComp : lstAbsComponent) {
            if (absComp instanceof ComboRenderer) {
                ((ComboRenderer) absComp).refresh();
            }
        }
    }
    
    @Override
    public TransactionPanel getActivePanel() {
        return this;
    }

    public BusinessRuleWrapper getRuleWrapper() {
        return ruleWrapper;
    }

    public String getTitle() {
        return BeanUtil.concat(constants.Constants.appTitle," - ",(template.title().isEmpty()?PanelUtil.getTitle(currentClass):template.title()));
    }

    public String getSimpleTitle() {
        return (template.title().isEmpty()?PanelUtil.getTitle(currentClass):template.title());
    }

    protected void bindPallete(Object pallete) {
        BindingGroup bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, tblResult, ELProperty.create("${selectedElement}"), pallete, BeanProperty.create("bean"));
        bindingGroup.addBinding(binding);
        bindingGroup.bind();
    }
    
    public void setupImages() {        
        GridBagConstraints cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.WEST;
        if (template.showImages() || template.showFiles()) {
            if (template.showImages()) {
                imagePallete = new ImageRendererPallete();
                imagePallete.setName("images");
                bindPallete(imagePallete);
                cons.gridy++;
                imagePallete.setPreferredSize(new Dimension(template.imagesWidth(), template.imagesHeight()));
                imagePallete.setEditable(template.imageEditable());
                pnlImages.add(imagePallete, cons);
                if (constants.Constants.panelBackground!=null) {
                    PanelUtil.updateColor(pnlImages);
                    PanelUtil.updateColor(pnlImages);
                    imagePallete.pnlImageRenderer.setBackground(Color.WHITE);
                }
            }
            else if (template.showFiles()) {
            }
        }
    }

    protected void bindToTable() {
        templateParser.bindTable(bindingGroup, list, tblResult, currentClass);
    }
    
	public Object getMainForm() {
        if (runMain) {
            return this;
        }
        if (constants.Constants.panelBackground!=null) {
            jScrollPane1.getViewport().setBackground(Color.WHITE);
        }
        bindingGroup = new BindingGroup();
        searchNow(1);
        bindToTable();
//        templateParser.bindTable(bindingGroup, list, tblResult, currentClass);
        setupImages();
        tabChildren.removeAll();
        tabChildren.setVisible(false);
        runMain = true;
        GridBagConstraints cons = new GridBagConstraints();
        JPanel lbl = new JPanel();
        if (constants.Constants.panelBackground!=null) {
            lbl.setBackground(constants.Constants.panelBackground);
        }
        Dimension dim = lbl.getPreferredSize();
        dim.height = template.topMargin();
        lbl.setPreferredSize(dim);
        pnlMainForm.add(lbl, cons);

        cons.insets = new Insets(1, 2, 0, 2);
        cons.gridy++;
        cons.fill = GridBagConstraints.HORIZONTAL;
//        putButton(true, pnlMainForm, cons);

        cons.gridy++;
        JPanel pnlNoGroup = templateParser.constructGroupPanel(bindingGroup, null);
        if (constants.Constants.panelBackground!=null) {
            pnlNoGroup.setBackground(constants.Constants.panelBackground);
        }
        Dimension prefSize = pnlNoGroup.getPreferredSize();
        prefSize.width = template.width();
        pnlNoGroup.setMinimumSize(prefSize);
        
        if (constants.Constants.panelBackground!=null) {
            this.setBackground(constants.Constants.panelBackground);
            pnlTop.setBackground(constants.Constants.panelBackground);
            pnlMainForm.setBackground(constants.Constants.panelBackground);
        }
        if (template.imageInsideForm()) {
            pnlTop.remove(pnlImages);
            JPanel pnl = new JPanel(new GridBagLayout());
            if (constants.Constants.panelBackground!=null) {
                pnl.setBackground(constants.Constants.panelBackground);
            }
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(1, 2, 0, 2);
            pnl.add(pnlNoGroup, c);
            pnl.add(pnlImages);
            pnlMainForm.add(pnl, cons);
        }
        else {
            pnlMainForm.add(pnlNoGroup, cons);
        }

        DisplayGroup[] groups = templateParser.getGroups();
        if (groups!=null && groups.length>0) {
            for (DisplayGroup displayGroup : groups) {
                if (displayGroup.addInfoOnly()) continue;
                JPanel tmp = templateParser.constructGroupPanel(bindingGroup, displayGroup);
                tmp.setName(BeanUtil.concat("grp",displayGroup.title()));
                tmp.setToolTipText(tmp.getName());
                addFieldComponent(tmp);
                cons.gridy++;
                pnlMainForm.add(tmp, cons);
                if (constants.Constants.panelBackground!=null) {
                    tmp.setBackground(constants.Constants.panelBackground);
                }
            }
        }
        
        if (constants.Constants.panelBackground!=null) {
            pnlMainForm.setBackground(constants.Constants.panelBackground);
        }
        cons.gridy++;
        //for childrecords
        constructTabs();
        putButton(false, pnlMainForm, cons);
        bindingGroup.bind();
        if (!template.showChild()) tabChildren.setVisible(false);
        if (!template.showForm()) pnlMainForm.setVisible(false);
        if (!template.showCriteria()) pnlCriteria.setVisible(false);
        tblResult.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
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
        tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
//                    setupFooter();
                }
            }
        });
        ((JTableReadOnly)tblResult).setEditableCol(template.editableCol());
        ((JTableReadOnly)tblResult).setRule(ruleWrapper);
        if (!template.autoResizeTable()) {
            tblResult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
        else {
            tblResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
        ruleWrapper.setTable(((JTableReadOnly)tblResult));
        pnlResultFooter.setVisible(false);
        setupFooter();
        if (template.tableColumnWidth()!=null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    resizeTable();
                }
            });
        }
//        setup all tooltip
        this.setToolTipText(currentClass.getSimpleName());
        this.tblResult.setToolTipText(currentClass.getSimpleName());
        return this;
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
                    col = tblResult.getColumnModel().getColumn(i);
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
	public List<AbstractChildTemplatePanel> getTabs() {
		return tabs;
	}

    List<AbstractChildTemplatePanel> tabs = new ArrayList();
    
	protected void constructTabs() {
        childRecordsAnnotation = (ChildRecords) currentClass.getAnnotation(ChildRecords.class);
        if (childRecordsAnnotation!=null) {
            int countChild = childRecordsAnnotation.value()==null?0:childRecordsAnnotation.value().length;
            int countInfo = childRecordsAnnotation.info()==null?0:childRecordsAnnotation.info().length;
            if (countChild>0 || countInfo>0) {
                ParentAddInfo[] infos = childRecordsAnnotation.info();
                if (infos!=null) {
                    for (ParentAddInfo info : infos) {
                        AbstractAddInfoTemplatePanel pnl = AbstractAddInfoTemplatePanel.getInstance(info, currentObject, tblResult, this);
                        JPanel tmp = new JPanel(new GridLayout());
                        if (constants.Constants.panelBackground!=null) {
                            tmp.setBackground(constants.Constants.panelBackground);
                        }
                        tmp.add(pnl);
                        if (UserInfo.hasDuty(info.duties())) {
                            tabChildren.add(info.title(), tmp);
                        }
                    }
                }
                ChildRecord[] records = childRecordsAnnotation.value();
                if (records!=null) {
                    for (ChildRecord childRecord : records) {
                        AbstractChildTemplatePanel pnl = AbstractChildTemplatePanel.getInstance(childRecord, currentObject);
                        tabs.add(pnl);
                        pnl.btnSave.setVisible(childRecord.canSave());
                        childPanels.add(pnl);
                        JPanel tmp = new JPanel(new GridLayout());
                        if (constants.Constants.panelBackground!=null) {
                            tmp.setBackground(constants.Constants.panelBackground);
                        }
                        tmp.add(pnl);
                        if (UserInfo.hasDuty(childRecord.duties())) {
                            if (childRecord.title().isEmpty()) {
                                tabChildren.addTab(PanelUtil.getTitle(childRecord.entity()), null, tmp, BeanUtil.concat(childRecord.entity().getSimpleName()," - ",childRecord.sql()));
                            }
                            else {
                                tabChildren.addTab(childRecord.title(), null, tmp, BeanUtil.concat(childRecord.entity().getSimpleName()," - ",childRecord.sql()));
                            }
                        }
                    }
                }
                tabChildren.setVisible(true); 
            }
        }
        else {
            tabChildren.setVisible(false);
        }
        if (template.showFiles()) {
            tabChildren.setVisible(true); 
            filePallete = new FileUploadRendererPallete();
            filePallete.setName("files");
            bindPallete(filePallete);
            filePallete.setPreferredSize(new Dimension(template.imagesWidth(), template.imagesHeight()));
            JPanel pnl = new JPanel(new GridLayout());
            if (constants.Constants.panelBackground!=null) {
                pnl.setBackground(constants.Constants.panelBackground);
            }
            pnl.add(filePallete);
            tabChildren.add("File Attachments", pnl);
        }
        if (template.showChart()) {
            tabChildren.setVisible(true); 
            JPanel pnl = new ChartDisplayer(currentClass);
            if (constants.Constants.panelBackground!=null) {
                pnl.setBackground(constants.Constants.panelBackground);
            }
            pnl.setName("chart");
            bindPallete(pnl);
            tabChildren.add("Charts", pnl);
        }
    }
    
    public Object getMainSearch() {
        if (runSearch) {
            return pnlCriteria;
        }
        runSearch = true;
        UITemplate temp = (UITemplate) currentClass.getAnnotation(UITemplate.class);
        String[] columnSearch = temp.columnSearch();
        String[] fields = temp.criteriaSearch();
        if (fields!=null && fields.length>0 && !fields[0].isEmpty()) columnSearch = fields;

        GridBagConstraints cons = new GridBagConstraints();
        List<FieldCompose> lst = templateParser.getAllFields();
        BindingGroup bindingSearchGroup = new BindingGroup();
        for (String col : columnSearch) {
            FieldCompose fieldCompose = null;
            for (FieldCompose f : lst) {
                if (f.display.name().equals(col)) {
                    fieldCompose = f;
                    break;
                }
            }            
            if (fieldCompose==null) continue;
            AbstractComponentRenderer rcomp = AbstractComponentRenderer.getSearchInstance(bindingSearchGroup, fieldCompose, null, this);
            rcomp.withDefaultValue = false;
            rcomp.withEmptyValue = true;
            JComponent field = (JComponent) rcomp.getRenderedField();
            JComponent label = (JComponent) rcomp.getRenderedSearchLabel();
            JComponent criteria = getSearchCriteria(fieldCompose.field);
            cons.gridy++;
            cons.anchor = GridBagConstraints.WEST;
            cons.insets = new Insets(1, 2, 0, 0);
            cons.fill = GridBagConstraints.HORIZONTAL;
            cons.ipadx = 5;
            pnlCriteria.add(label, cons);
            pnlCriteria.add(criteria, cons);
            cons.ipadx = 100;
            pnlCriteria.add(field, cons);

            FieldSearch e = new FieldSearch();
            e.criteria = criteria;
            e.field = fieldCompose.field;
            e.fieldComp = field;
            lstSearch.add(e);
        }
        bindingSearchGroup.bind();
        JButtonPallete btnClear = new JButtonPallete("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearSearch();
            }
        });
        JButtonPallete btnSearch = new JButtonPallete("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchNow(0);
                Common2View.getTransactionPanel().firstRecord();
            }
        });
        JPanel pnlButton = new JPanel();
        pnlButton.add(btnClear);
        pnlButton.add(btnSearch);
        cons.gridy++;
        cons.anchor = GridBagConstraints.CENTER;
        cons.gridwidth = 3;
        cons.insets = new Insets(1, 2, 0, 0);
        pnlCriteria.add(pnlButton, cons);
        if (constants.Constants.panelBackground!=null) {
            setBackground(constants.Constants.panelBackground);
            if (pnlCriteria!=null) pnlCriteria.setBackground(constants.Constants.panelBackground);
            if (pnlButton!=null) pnlButton.setBackground(constants.Constants.panelBackground);
        }
        return pnlCriteria;
    }

    public Object getMainSearchResult() {
        if (runResult) {
            return pnlResults;
        }
        runResult = true;
        tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (tblResult.getSelectedRow()>=0) {
                    currentObject = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tblResult);
                }
                else {
                    currentObject = emptyObject;
                }
                if (!e.getValueIsAdjusting()) {
                    loadSubRecords(currentObject);
                    SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
		                    ruleWrapper.onChangeRecord();
		                    AbstractTemplatePanel pnl = getMe();
		                    if (pnl.currentObject==null || pnl.currentObject.isEmptyKey()) {
		                        pnl.pnlCriteria.setToolTipText(pnl.currentClass.getSimpleName());
		                    }
		                    else {
		                        pnl.pnlCriteria.setToolTipText(BeanUtil.concat(pnl.currentClass.getSimpleName()," - ",pnl.currentObject.keyVal()));
		                        Log.out(pnl.getToolTipText());
		                    }
						}
                    });
                }
            }
        });
        if (constants.Constants.panelBackground!=null) {
//            jScrollPane1.setBackground(constants.Constants.panelBackground);
//            tblResult.setBackground(constants.Constants.panelBackground);
            if (pnlResults!=null) pnlResults.setBackground(constants.Constants.panelBackground);
        }
        return pnlResults;
    }

    protected AbstractTemplatePanel getMe() {
		return this;
	}

	public void loadSubRecords(AbstractIBean bean) {
        currentObject.extractServerChildrensChartsImagesAndFiles();
        List<AbstractChildTemplatePanel> panels = childPanels;
        for (AbstractChildTemplatePanel panel : panels) {
            panel.refreshRecords(bean);
        }
    }
    
    public static AbstractTemplatePanel getInstance(Class templateName, UITemplate template) {
        try {
             AbstractTemplatePanel tmp = (AbstractTemplatePanel) templateName.newInstance();
             tmp.template = template;
             tmp.templateParser = TemplateParserUtil.getInstance(tmp);
             tmp.parentPanel = tmp;
             return tmp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public final void setBean(Class cls) {
        try {
            this.currentClass = cls;
            emptyObject = (AbstractIBean) cls.newInstance();
            currentObject = emptyObject;
        } catch (Exception ex) {
            Logger.getLogger(AbstractTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void putButton(boolean top, JPanel pnl, GridBagConstraints cons) {
        ActionButtons btns = (ActionButtons) currentClass.getAnnotation(ActionButtons.class);
        if (btns==null) {
            pnlButton.setVisible(false);
            return;
        }
        pnlButton.setVisible(true);
        ActionButton buttons[] = btns.value();
        if (buttons==null || buttons.length==0) return;
        
//        JPanel panel = new JPanel(new FlowLayout());
        pnlButton.setLayout(new FlowLayout());
//        pnlButton.add(panel);
        for (ActionButton button : buttons) {
            if (button.top()==top) {
                JButtonPallete btn = new JButtonPallete(button.label(), button.name());
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                    }
                });
                btn.setName(button.name());
                btn.setToolTipText(button.name());
                this.addFieldComponent(btn);
                pnlButton.add(btn);
                if (!UserInfo.hasDuty(button.duties())) {
                	btn.setVisible(false);
                }
            }
        }
        if (constants.Constants.panelBackground!=null) {
            pnlButton.setBackground(constants.Constants.panelBackground);
//            panel.setBackground(constants.Constants.panelBackground);
        }
    }

    protected boolean contains(String[] strArr, String str) {
        for (String string : strArr) {
            if (string.equalsIgnoreCase(str)) return true;
        }
        return false;
    }
    
    public JComboBox getSearchCriteria(Field f) {
        String[] str = {"=", ">", "<"};
        JComboBox box = new JComboBox(str);
        box.setName(BeanUtil.concat("CRITERIA_",f.getName()));
        return box;
    }

    protected void clearSearch() {
        for (FieldSearch fieldSearch : lstSearch) {
            JComboBox box = (JComboBox) fieldSearch.criteria;
            box.setSelectedItem("=");
            
            JComponent comp = fieldSearch.fieldComp;
            if (comp instanceof JTextComponent) {
                ((JTextComponent) comp).setText("");
            }
            else if (comp instanceof JComboBoxPallete) {
                ((JComboBoxPallete) comp).setSelectedIndex(0);
            }
            else if (comp instanceof JCalendarPallete) {
                ((JCalendarPallete) comp).setDate(null);
            }
            else if (comp instanceof LookupTableFieldPallete) {
                ((LookupTableFieldPallete) comp).setText("");
            }
        }
    }

    private int moreCount = 0;
    
    public void searchNow(int moreCount) {
        if (moreCount==0) {
            this.moreCount = 0;
        }
        searchMoreRecords();
    }

    public void searchMoreRecords() {
        String simpleName = BeanUtil.getEntityName(currentClass);
        String select = template.select();
        StringBuffer sql = new StringBuffer();
        if (select.isEmpty()) {
            sql.append(BeanUtil.concat("SELECT a FROM ",simpleName," a "));
        }
        else {
            sql.append(select);
        }
        List<String> lst = new ArrayList<String>();
        for (FieldSearch fieldSearch : lstSearch) {
            JComboBox boxEqual = (JComboBox) fieldSearch.criteria;
            JComponent comp = fieldSearch.fieldComp;
            if (comp instanceof IGetText) {
                String text = ((IGetText) comp).getText();
                if (text!=null && !text.trim().isEmpty()) {
                	if ("searchStr".equalsIgnoreCase(fieldSearch.field.getName())) {
                		Log.out("USING searchStr field, search will insert '%' before the string.");
                        lst.add(createCriteriaEntry(fieldSearch.field, boxEqual.getSelectedItem().toString(), BeanUtil.concat("%",text)));
                	}
                	else {
                        lst.add(createCriteriaEntry(fieldSearch.field, boxEqual.getSelectedItem().toString(), text));
                	}
                }
            }
        }
        if (lst.size()>0) {
            if (select.toUpperCase().contains("WHERE")) {
                sql.append(" AND ");
            }
            else {
                sql.append(" WHERE ");
            }
        }
        Iterator iter = lst.iterator();
        while (iter.hasNext()) {
            String str = iter.next().toString();
            sql.append(str);
            if (iter.hasNext()) {
                sql.append(" AND ");
            }
        }
        String orderBy = template.orderBy();
        if (orderBy!=null && !orderBy.isEmpty()) {
            sql.append(" ORDER BY ").append(orderBy);
        }
        Log.out("SQL==",sql);
        AbstractIBean bean = (AbstractIBean) BeanUtil.getBeanInstance(currentClass);
        if (bean.orderBy()!=null && !bean.orderBy().isEmpty()) {
            if (sql.indexOf("ORDER BY")!=-1) {
                sql.append(", ").append(bean.orderBy());
            }
            else {
                sql.append(" ORDER BY ").append(bean.orderBy());
            }
        }
        int row = (moreCount*bean.getRecordListCount())-1;
        PanelUtil.scroll(tblResult, row);
        String sqlStr = PanelUtil.changeGlobalVar(sql.toString());
        List lstTmp = DBClient.getList(sqlStr, moreCount*bean.getRecordListCount(), bean.getRecordListCount());

        PanelUtil.showMessageToScreen("");
        if (moreCount==0) {
            if (list!=null) {
            	for (Object obj:list) {
            		AbstractIBean b = (AbstractIBean) obj;
            		if (b!=null) b.clearCache();
            	}
            	list.clear();
            }
            if (lstTmp==null || lstTmp.size()==0) {
                PanelUtil.showMessageToScreen("No records found.");
            }
        }
        else {
            if (lstTmp==null || lstTmp.size()==0) {
                PanelUtil.showMessageToScreen("No more records.");
            }
        }
        updateList(lstTmp);
        tblResult.updateUI();
        moreCount++;
    }

    protected void updateList(List<AbstractIBean> newList) {
        if (newList!=null) {
            for (AbstractIBean b : newList) {
                b.showSubrecords = false;
                if (!template.showImages()) {
                    b.showImages = false;
                }
                if (!template.showFiles()) {
                    b.showFile = false;
                }
                if (!template.showChart()) {
                    b.showCharts = false;
                }
            }
        }
        if (newList!=null) {
            list.addAll(newList);
        }
    }

    protected String createCriteriaEntry(Field field, String criteria, String value) {
        StringBuffer sb = new StringBuffer();
        if (field.getType().getSimpleName().equals(String.class.getSimpleName()) ||
            field.getType().getSimpleName().equals(Date.class.getSimpleName())) {
            if (field.getType().getSimpleName().equals(Date.class.getSimpleName())) {
                
            }
            if (criteria.equalsIgnoreCase("=") && field.getType().getSimpleName().equals(String.class.getSimpleName())) {
                sb.append(" a.").append(field.getName()).append(" like '").append(value).append("%' ");
            }
            else {
                sb.append(" a.").append(field.getName()).append(" ").append(criteria).append(" '").append(value).append("' ");
            }
        }
        else {
            sb.append(" a.").append(field.getName()).append(" ").append(criteria).append(" ").append(value).append(" ");
        }
        return sb.toString();
    }
    
    public static class FieldSearch {
        Field field;
        JComponent fieldComp;
        JComponent criteria;
    }
    
    public static class FieldCompose {
        public Object currentObject;
        public Field field;
        public Display display;        
    }

    /** Creates new form AbstractTemplatePanel */
    public AbstractTemplatePanel() {
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
        java.awt.GridBagConstraints gridBagConstraints;

        pnlResults = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new JTableReadOnly(35);
        pnlResultFooter = new javax.swing.JPanel();
        pnlCriteria = new javax.swing.JPanel();
        pnlTopWithTab = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        pnlMainForm = new javax.swing.JPanel();
        pnlImages = new javax.swing.JPanel();
        pnlTabAndButton = new javax.swing.JPanel();
        tabChildren = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();

        pnlResults.setName("pnlResults"); // NOI18N
        pnlResults.setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(AbstractTemplatePanel.class);
        jScrollPane1.setFont(resourceMap.getFont("jScrollPane1.font")); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblResult.setAutoCreateRowSorter(true);
        tblResult.setFont(resourceMap.getFont("tblResult.font")); // NOI18N
        tblResult.setModel(new javax.swing.table.DefaultTableModel(
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
        tblResult.setName("tblResult"); // NOI18N
        tblResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblResult);

        pnlResults.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlResultFooter.setName("pnlResultFooter"); // NOI18N
        pnlResultFooter.setPreferredSize(new java.awt.Dimension(100, 25));
        pnlResultFooter.setLayout(new java.awt.GridBagLayout());
        pnlResults.add(pnlResultFooter, java.awt.BorderLayout.SOUTH);

        pnlCriteria.setName("pnlCriteria"); // NOI18N
        pnlCriteria.setLayout(new java.awt.GridBagLayout());

        pnlTopWithTab.setName("pnlTopWithTab"); // NOI18N

        javax.swing.GroupLayout pnlTopWithTabLayout = new javax.swing.GroupLayout(pnlTopWithTab);
        pnlTopWithTab.setLayout(pnlTopWithTabLayout);
        pnlTopWithTabLayout.setHorizontalGroup(
            pnlTopWithTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopWithTabLayout.setVerticalGroup(
            pnlTopWithTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setName("Form"); // NOI18N

        pnlTop.setName("pnlTop"); // NOI18N
        pnlTop.setLayout(new java.awt.GridBagLayout());

        pnlMainForm.setName("pnlMainForm"); // NOI18N
        pnlMainForm.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlTop.add(pnlMainForm, gridBagConstraints);

        pnlImages.setName("pnlImages"); // NOI18N
        pnlImages.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlTop.add(pnlImages, gridBagConstraints);

        pnlTabAndButton.setName("pnlTabAndButton"); // NOI18N
        pnlTabAndButton.setLayout(new java.awt.BorderLayout());

        tabChildren.setFont(resourceMap.getFont("tabChildren.font")); // NOI18N
        tabChildren.setName("tabChildren"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        tabChildren.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        pnlTabAndButton.add(tabChildren, java.awt.BorderLayout.CENTER);

        pnlButton.setName("pnlButton"); // NOI18N
        pnlTabAndButton.add(pnlButton, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTabAndButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addComponent(pnlTop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabAndButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel pnlButton;
    public javax.swing.JPanel pnlCriteria;
    public javax.swing.JPanel pnlImages;
    public javax.swing.JPanel pnlMainForm;
    public javax.swing.JPanel pnlResultFooter;
    public javax.swing.JPanel pnlResults;
    public javax.swing.JPanel pnlTabAndButton;
    public javax.swing.JPanel pnlTop;
    public javax.swing.JPanel pnlTopWithTab;
    public javax.swing.JTabbedPane tabChildren;
    public javax.swing.JTable tblResult;
    // End of variables declaration//GEN-END:variables

    public Object getCurrentObject() {
        Object obj = ELProperty.create("${selectedElement}").getValue(tblResult);
        if (obj!=null) return obj;
        return currentObject;
    }

    public Class getCurrentClass() {
        return this.currentClass;
    }

    public UITemplate getUITemplate() {
        return this.template;
    }

    public JTable getTable() {
        return this.tblResult;
    }

    public List<JComponent> getFieldComponents() {
        return lstComponent;
    }


    public void addFieldComponent(JComponent comp) {
        lstComponent.add(comp);
    }
    
    public boolean isParent() {
        return true;
    }
    
    public void setAllValues(IBean bean) {
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
        tblResult.updateUI();
    }

    public BindingGroup getBinding() {
        return bindingGroup;
    }
    
    public void hideSearchCriteria() {
        pnlCriteria.setVisible(false);
    }

    public void hideSearchResult() {
        pnlResults.setVisible(false);
    }
    
    Map<Integer, JLabel> mapFooter = new HashMap<Integer, JLabel>();
    GridBagConstraints conFooter = new GridBagConstraints();
    
    public void setupFooter() {
        int count = tblResult.getColumnCount();
        for (int i = 0; i < count; i++) {
            JLabel p = getFooterLabel(i);
            Rectangle rec = tblResult.getCellRect(0, i, true);
            Dimension dim = new Dimension(rec.width, 20);
        	if (i==0) {
//        		must have a way to deduct width in the first column
        		if (jScrollPane1.getVerticalScrollBar().isVisible()) {
            		dim.width -= 15;
        		}
        	}
            p.setPreferredSize(dim);
        }
        pnlResultFooter.updateUI();
        String sumFooter = template.sumFooter();
        pnlResultFooter.setVisible(sumFooter!=null && !sumFooter.isEmpty());
    }
    
    public void hideFooter(int i) {
        if (mapFooter.get(i)!=null) {
            JLabel lbl = mapFooter.get(i);
            lbl.setVisible(false);
        }
    }
    
    public JLabel getFooterLabel(int i) {
        if (mapFooter.get(i)!=null) {
            return mapFooter.get(i);
        }
        tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
        int count = tblResult.getColumnCount();
        for (int i = 0; i < count; i++) {
            if (withSumFooter(i)) {
                calculateSum(i);
            }
        }
    }
    
    private void calculateSum(int col) {
        double total = 0;
        int rows = tblResult.getRowCount();
        for (int i = 0; i < rows; i++) {
            try {
                String str = tblResult.getValueAt(i, col).toString();
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

    public List getRecordList() {
        return list;
    }

    @Override
    public Object getBean() {
        return currentObject;
    }
    
    public JPanel centerPanel(JPanel pnl) {
		JPanel tmp = new JPanel(new BorderLayout());
		tmp.add(pnl, BorderLayout.CENTER);
		tmp.add(new JLabel("  "), BorderLayout.NORTH);
		tmp.add(new JLabel("  "), BorderLayout.SOUTH);
		tmp.add(new JLabel("  "), BorderLayout.WEST);
		tmp.add(new JLabel("  "), BorderLayout.EAST);
		PanelUtil.updateColor(tmp);
		return tmp;
    }

	@Override
	public JTabbedPane getTabPane() {
		return this.tabChildren;
	}
}
