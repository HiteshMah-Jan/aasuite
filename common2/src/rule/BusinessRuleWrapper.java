/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import component.AbstractRobotTester;
import component.IGetText;
import component.JCalendarPallete;
import component.JCheckBoxPallete;
import constants.UserInfo;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import org.jdesktop.beansbinding.ELProperty;
import service.util.AbstractIBean;
import service.util.IBean;
import template.UITemplate;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.component.JTableReadOnly;
import template.screen.AbstractChildTemplatePanel;
import template.screen.ITemplate;
import template.screen.TransactionPanel;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public abstract class BusinessRuleWrapper {
    public ITemplate panel;
    public JComponent usedComp;
    public JTableReadOnly tbl;
    protected Object oldObj;

	public double getRandomDouble(int low, int high) {
		if (low >= high) return low;
		int i = getRandom(low, high);
		if (i >= high) {
			return high;
		}
		else {
			return i + Math.random();
		}
	}
	
	public int getRandom(int low, int high) {
		if (low >= high) return low;
		double d = Math.random();
		int val = (int) ((high - low) * d);
		int v = low + val;
		if (v > high) {
			return high;
		}
		else {
			return v;
		}
	}

    public void setTable(JTableReadOnly tbl) {
    	this.tbl = tbl;
    }
    
    public boolean isEditable(Object obj, int row, int col) {
//        this rule only applies to the editable cells
        return true;
    }
    
    protected void tableChangedAlways(JTable tbl, Object obj) {
    }
    
    protected void tableChanged(JTable tbl, Object obj) {
    }

    public AbstractIBean getBean() {
        return (AbstractIBean) panel.getCurrentObject();
    }
    
    public BusinessRuleWrapper() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    JTable tbl = panel.getTable();
                    if (tbl!=null) {
                        tbl.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
                            public void columnAdded(TableColumnModelEvent e) {
                            }
                            public void columnRemoved(TableColumnModelEvent e) {
                            }
                            public void columnMoved(TableColumnModelEvent e) {
                            }
                            public void columnMarginChanged(ChangeEvent e) {
                            }
                            public void columnSelectionChanged(ListSelectionEvent e) {
                                JTable tbl = panel.getTable();
                                if (tbl.getSelectedRow()==-1) return;
                                oldObj = ELProperty.create("${selectedElement}").getValue(tbl);
                                tableChangedAlways(tbl, oldObj);
                            }
                        });
                        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting()) {
                                    JTable tbl = panel.getTable();
                                    if (tbl.getSelectedRow()==-1) return;
                                    Object obj = ELProperty.create("${selectedElement}").getValue(tbl);
                                    tableChanged(tbl, obj);
                                }
                            }
                        });
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(BusinessRuleWrapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }
    
    public static BusinessRuleWrapper getInstance(ITemplate panel) {
        try {
            BusinessRuleWrapper wrapper = null;
            UITemplate template = panel.getUITemplate();
            if (template==null || template.rule().isEmpty()) {
                String simpleName = panel.getCurrentClass().getSimpleName();
                wrapper = (BusinessRuleWrapper) Class.forName(BeanUtil.concat("rule.",simpleName,"_RULE")).newInstance();
            }
            else {
                String simpleName = template.rule();
                wrapper = (BusinessRuleWrapper) Class.forName(BeanUtil.concat("rule.",simpleName,"_RULE")).newInstance();
            }
            wrapper.panel = panel;
            return wrapper;
        } catch (Exception ex) {
//            Logger.getLogger(BusinessRuleWrapper.class.getName()).log(Level.SEVERE, null, ex.getMessage()," - No rule defined.");
            BusinessRuleWrapper rule = new BusinessRuleWrapper() {
                @Override
                public void runFocusLost(JComponent comp) {
                }
                @Override
                public void runOnClick(JComponent comp) {
                }
            };
            rule.panel = panel;
            return rule;
        }
    }
    
    public static BusinessRuleWrapper getInstance(UITemplate template) {
        try {
            BusinessRuleWrapper wrapper = null;
            String simpleName = template.rule();
            wrapper = (BusinessRuleWrapper) Class.forName(BeanUtil.concat("rule.",simpleName,"_RULE")).newInstance();
            return wrapper;
        } catch (Exception ex) {
//            Logger.getLogger(BusinessRuleWrapper.class.getName()).log(Level.SEVERE, null, ex.getMessage()," - No rule defined.");
            BusinessRuleWrapper rule = new BusinessRuleWrapper() {
                @Override
                public void runFocusLost(JComponent comp) {
                }
                @Override
                public void runOnClick(JComponent comp) {
                }
            };
            return rule;
        }
    }

    public void setTextAreaValue(String name, Object value) {
        setValue(name, value);
        AbstractIBean bean = getBean();
        bean.changeValue(name, value);
    }
    
    public void setValue(String name, Object value) {
        BeanUtil.setPropertyValue((IBean) panel.getCurrentObject(), name, value);
        JComponent comp = getComponent(name);
        if (comp!=null) {
            if (comp instanceof JCalendarPallete) {
                ((JCalendarPallete) comp).setDate((Date) value);
            }
            else if (comp instanceof JCheckBoxPallete) {
                ((JCheckBoxPallete) comp).setSelected(Boolean.parseBoolean(value.toString()));
            }
            else if (comp instanceof IGetText) {
                IGetText txt = (IGetText) comp;
                if (value==null) {
                    txt.setText("");
                }
                else {
                    txt.setText(value.toString());
                }
            }
        }
    }

    public String getValue(String name) {
        return ((component.IGetText)getComponent(name)).getText();
    }
    
    public int getIntValue(String name, int def) {
        Component comp = getComponent(name);
        if (comp == null) return 0;
        Object obj = ((component.IGetText)comp).getValue();
        if (obj!=null && obj instanceof Integer) {
            return (Integer)obj;
        }
        else {
            try {
                return Integer.parseInt(obj.toString());
            }
            catch (Exception e) {
            }
        }
        return def;
    }
    
    public double getDoubleValue(String name, int def) {
        Component comp = getComponent(name);
        if (comp == null) return 0;
        Object obj = ((component.IGetText)comp).getValue();
        if (obj!=null && obj instanceof Double) {
            return (Double)obj;
        }
        else {
            try {
                return Double.parseDouble(obj.toString());
            }
            catch (Exception e) {
            }
        }
        return def;
    }

    public JComponent getComponent(String name) {
        List<JComponent> comps = panel.getFieldComponents();
        for (JComponent comp : comps) {
            if (comp!=null && comp.getName()!=null && comp.getName().equals(name)) return comp;
        }
        return null;
    }
    public boolean mandatoryOk() {
        List<JComponent> comps = panel.getFieldComponents();
        for (JComponent elem : comps) {
            if (elem instanceof IGetText) {
                boolean empty = ((IGetText) elem).isEmpty();
                if (empty) {
                    try {
                        FieldCompose f = ((IGetText) elem).getFieldCompose();
                        Column col = f.field.getAnnotation(Column.class);
                        if ( (!col.nullable() || f.display.mandatory()) && !f.display.overrideMandatory()){
                            PanelUtil.showError(elem, PanelUtil.getLabel(f)," is mandatory."); 
                            return false;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(BusinessRuleWrapper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return true;
    }
    
    public boolean lengthOk() {
        List<JComponent> comps = panel.getFieldComponents();
        for (JComponent elem : comps) {
            if (elem instanceof IGetText) {
                if (!((IGetText) elem).isEmpty()) {
                    try {
                        FieldCompose f = ((IGetText) elem).getFieldCompose();
                        Column col = f.field.getAnnotation(Column.class);
                        if (col.length()>0) {
                            String txt = ((IGetText) elem).getText();
                            if (txt.length() > col.length()) {
                                PanelUtil.showError(elem, PanelUtil.getLabel(f)," is more than ",col.length()," characters."); 
                                return false;
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(BusinessRuleWrapper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return true;
    }

    public abstract void runFocusLost(JComponent comp);
    public abstract void runOnClick(JComponent comp);
    public void onLoad() {
    }
    public void onNewRecord() {
    }
    public void onChangeRecord() {
    }
    public boolean beforeSave(AbstractIBean bean) {
        return true;
    }
    public void afterSave(AbstractIBean bean) {
    }
    public boolean beforeDelete(AbstractIBean bean) {
        return true;
    }
    public void afterDelete(AbstractIBean bean) {
    }

    public void testRobot(AbstractRobotTester tester) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void saveRecord() {
        TransactionPanel.getCurrentPanel().saveRecord();
    }
    public void saveRecordIfEmpty() {
        AbstractIBean bean = this.getBean();
        if (bean.isEmptyKey()) {
            TransactionPanel.getCurrentPanel().saveRecord();
        }
    }
    public void redisplayRecord() {
        ((TransactionPanel)TransactionPanel.getCurrentPanel()).redisplayRecord();
    }
    public void refreshRecords() {
        ((TransactionPanel)TransactionPanel.getCurrentPanel()).refreshRecords();
    }
    public void setList(List lst) {
        List recList = ((TransactionPanel)TransactionPanel.getCurrentPanel()).parentPanel.list;
        util.PanelUtil.setListData(recList, lst);
    }
    public void alertEmpty(String field, String message) throws Exception {
        boolean b = isEmpty(field);
        if (b) {
            PanelUtil.showMessage(null,message);
            throw new Exception(message);
        }
    }
    public boolean isEmpty(String field) {
        AbstractIBean bean = getBean();
        Object val = BeanUtil.getPropertyValue(bean, field);
        if (val==null || val.toString().isEmpty() || val.toString().equals("0") || val.toString().equals("0.0")) {
            return true;
        }
        else {
            return false;
        }
    }
    public void showMessage(String message) {
        PanelUtil.showMessage(null, message);
    }
    public boolean showPrompt(Object... message) {
        return PanelUtil.showPrompt(null, message);
    }
    public void showError(String... message) {
        PanelUtil.showError(null, message);
    }
    public void showErrorMessageToScreen(String... message) {
        PanelUtil.showErrorMessageToScreen(message);
    }
    public void focus(String comp) {
    	try {
        	getComponent(comp).requestFocus();
    	}
    	catch (Exception e) {
    	}
    }
    public void enable(String comp) {
    	try {
        	getComponent(comp).setEnabled(true);
    	}
    	catch (Exception e) {
    	}
    }
    public void enable(boolean b, Object... comp) {
    	try {
        	getComponent(BeanUtil.concat(comp)).setEnabled(b);
    	}
    	catch (Exception e) {
    	}
    }
    public void disable(final String comp) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(100);
		        	getComponent(comp).setEnabled(false);
		    	}
		    	catch (Exception e) {
		    		Log.out("Problem for component = ",comp," - ",e.getMessage());
		    	}
			}
		});
		t.start();
    }
    public void hide(Object... comp) {
    	try {
        	getComponent(BeanUtil.concat(comp)).setVisible(false);
    	}
    	catch (Exception e) {
    	}
    }
    public void show(Object... comp) {
    	try {
        	getComponent(BeanUtil.concat(comp)).setVisible(true);
    	}
    	catch (Exception e) {
    	}
    }
    
	protected void hideChildTableCol(int i, int col) {
		AbstractChildTemplatePanel tab = panel.getTabs().get(i);
		JTableReadOnly tbl = (JTableReadOnly) tab.tblRecord;
		tbl.hideColumn(col);
	}

	protected void showAllColumns(int i) {
		AbstractChildTemplatePanel tab = panel.getTabs().get(i);
		JTableReadOnly tbl = (JTableReadOnly) tab.tblRecord;
		tbl.showAllColumns();
	}

	Component[] comps;
	List<String> title = new ArrayList();
	protected void hideAllTabs() {
		JTabbedPane tabs = this.panel.getTabPane();
		if (comps==null) {
			for (int i=0; i<tabs.getComponentCount(); i++) {
				title.add(tabs.getTitleAt(i));
			}
			comps = tabs.getComponents();
		}
		tabs.removeAll();
	}
	
	protected void displayTab(int index) {
		JTabbedPane tabs = this.panel.getTabPane();
		if (title.size()>index && comps.length>index) {
			tabs.add(title.get(index), comps[index]);
		}
	}
}
