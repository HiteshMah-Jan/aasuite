/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.ELProperty;

import service.util.AbstractIBean;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.ITemplate;
import template.screen.TransactionPanel;
import ui.BusinessRuleForm;
import ui.BusinessRuleServiceForm;
import ui.DynamicAccessForm;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

import common2.Common2View;
import component.IGetText;
import component.IParentComp;
import component.IRule;
import component.JCheckBoxPallete;
import component.JLinkLabelPallete;
import component.LookupTableFieldPallete;
import component.PalleteRuleManager;
import component.SecuredField;

import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public abstract class AbstractComponentRenderer {
    public boolean withDefaultValue = true;
    public boolean withEmptyValue = false;
    protected FieldCompose field;
    protected Object tbl;
    protected ITemplate panel;
    BindingGroup bindingGroup;
    public boolean forSearch = false;
    
    public static AbstractComponentRenderer getInstance(BindingGroup bindingGroup, FieldCompose field, Object tbl, ITemplate panel) {
        try {
            String type = field.display.type();
            Class cls = field.field.getType();
            if (type.equals("Any")) {
                if (cls.getSimpleName().equalsIgnoreCase("Date")) {
                    type = "Calendar";
                }
                else if (cls.getSimpleName().equalsIgnoreCase("Boolean")) {
                    type = "CheckBox";
                }
                else {
                    if (cls.getSimpleName().toUpperCase().startsWith("INT")) {
                        type = "TextInt";
                    }
                    else if (cls.getSimpleName().equalsIgnoreCase("Double")) {
                        type = "TextDouble";
                    }
                    else {
                        type = "Text";
                    }
                }
            }
            if (tbl==null) {
//                automatic changes for search criteria
                if (type.equals("Label")) {
                    if (cls.getSimpleName().toUpperCase().startsWith("INT")) {
                        type = "TextInt";
                    }
                    else if (cls.getSimpleName().equalsIgnoreCase("Double")) {
                        type = "TextDouble";
                    }
                    else {
                        type = "Text";
                    }
                }
            }
            AbstractComponentRenderer comp = (AbstractComponentRenderer) Class.forName(BeanUtil.concat("template.screen.component.",type,"Renderer")).newInstance();
            comp.bindingGroup = bindingGroup;
            comp.setField(field);
            comp.setObject(tbl);
            comp.panel = panel;
            return comp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractComponentRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static AbstractComponentRenderer getSearchInstance(BindingGroup bindingGroup, FieldCompose field, Object tbl, ITemplate panel) {
        try {
            String type = field.display.type();
            Class cls = field.field.getType();
            if (type.equals("Any")) {
                if (cls.getSimpleName().equalsIgnoreCase("Date")) {
                    type = "Calendar";
                }
                else if (cls.getSimpleName().equalsIgnoreCase("Boolean")) {
                    type = "CheckBox";
                }
                else {
                    if (cls.getSimpleName().toUpperCase().startsWith("INT")) {
                        type = "TextInt";
                    }
                    else if (cls.getSimpleName().equalsIgnoreCase("Double")) {
                        type = "TextDouble";
                    }
                    else {
                        type = "Text";
                    }
                }
            }
            if (tbl==null) {
//                automatic changes for search criteria
                if (type.equals("Label")) {
                    if (cls.getSimpleName().toUpperCase().startsWith("INT")) {
                        type = "TextInt";
                    }
                    else if (cls.getSimpleName().equalsIgnoreCase("Double")) {
                        type = "TextDouble";
                    }
                    else {
                        type = "Text";
                    }
                }
            }
            AbstractComponentRenderer comp = (AbstractComponentRenderer) Class.forName(BeanUtil.concat("template.screen.component.",type,"Renderer")).newInstance();
            comp.forSearch = true;
            comp.bindingGroup = bindingGroup;
            comp.setField(field);
            comp.setObject(tbl);
            comp.panel = panel;
            return comp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractComponentRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public final void setField(FieldCompose field) {
        this.field = field;
    }
    public final void setObject(Object tbl) {
        this.tbl = tbl;
    }
    
    public Object getRenderedLabel() {
        String label = PanelUtil.getLabel(field);
        if (field.display.hideLabel()) {
            JLabel lbl = new JLabel();
            lbl.setPreferredSize(new Dimension(1,1));
            return lbl;
        }
        obj = getRenderedListeningField();
        if (!forSearch) {
//        	if (!field.display.enabled()) {
        		((JComponent) obj).setEnabled(field.display.enabled());
//        	}
        }
        JLinkLabelPallete lbl = new JLinkLabelPallete(BeanUtil.concat(label,":"), (IGetText) obj); 
        if (this.field.display.linktoBean()!=String.class) {
            if (obj!=null && obj instanceof IGetText) {
                lbl.addLinkBean(this.field.display.linktoBean());
            }
        }
        if (this.field.display.labelWidth()>0) setSize(lbl, this.field.display.labelWidth());
        if (constants.Constants.font!=null) lbl.setFont(constants.Constants.font);
        lbl.setName(BeanUtil.concat("lbl_",label));
        if (this.field.display.withHelp()) {
            lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/help.png")));
        }
        Column col = this.field.field.getAnnotation(Column.class);
        if (col!=null && !col.nullable() || this.field.display.mandatory()) {
            if (tbl!=null) {
                if (constants.Constants.labelColor!=null) {
                    lbl.setForeground(constants.Constants.labelColor);
                    lbl.superText(BeanUtil.concat("*",label,":"));
                }
                else {
                    lbl.setForeground(PanelUtil.MANDATORY_COLOR);
                }
            }
        }
        PanelUtil.setupCursor(lbl);
        return lbl;
    }

    public Object getRenderedSearchLabel() {
        String label = PanelUtil.getSearchLabel(field);
        obj = getRenderedListeningField();
		((JComponent) obj).setEnabled(true);
        JLinkLabelPallete lbl = new JLinkLabelPallete(BeanUtil.concat(label,":"), (IGetText) obj); 
        if (this.field.display.linktoBean()!=String.class) {
            if (obj!=null && obj instanceof IGetText) {
                lbl.addLinkBean(this.field.display.linktoBean());
            }
        }
        if (this.field.display.labelWidth()>0) setSize(lbl, this.field.display.labelWidth());
        if (constants.Constants.font!=null) lbl.setFont(constants.Constants.font);
        lbl.setName(label);
        if (this.field.display.withHelp()) {
            lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/help.png")));
        }
        Column col = this.field.field.getAnnotation(Column.class);
        if (col!=null && !col.nullable() || this.field.display.mandatory()) {
            if (tbl!=null) lbl.setForeground(PanelUtil.MANDATORY_COLOR);
        }
        return lbl;
    }
    
    public abstract Object getRenderedField();
    
    public void setFieldValue(Object obj) {
        try {
            Class cls = field.field.getType();
            if (cls==String.class) {
                BeanProperty.create(field.field.getName()).setValue(field.currentObject, obj.toString());
            }
            else if (cls==Integer.class){
                BeanProperty.create(field.field.getName()).setValue(field.currentObject, Integer.parseInt(obj.toString()));
            }
            else if (cls==Double.class){
                BeanProperty.create(field.field.getName()).setValue(field.currentObject, Double.parseDouble(obj.toString()));
            }
            else {
                BeanProperty.create(field.field.getName()).setValue(field.currentObject, obj);
            }
//            Log.out("TEST");
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractComponentRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSize(JComponent f) {
        Dimension dim = f.getPreferredSize();        
        int width = this.field.display.width();
        if (width>0) {
            if (tbl!=null) dim.width = width;
            else dim.width = 100;
            f.setPreferredSize(dim);
            f.setSize(dim);
            f.setMinimumSize(dim);
        }
    }
    
    public void setSize(JComponent f, int width) {
        Dimension dim = f.getPreferredSize();        
        if (tbl!=null) dim.width = width;
        else dim.width = 100;
        f.setPreferredSize(dim);
        f.setSize(dim);
        f.setMinimumSize(dim);
    }

    Object obj;
    public Object getRenderedListeningField() {
        if (obj==null) obj = getRenderedField();
        if (obj instanceof IGetText) {
            ((IGetText)obj).setFieldCompose(field);
        }
		((JComponent) obj).setEnabled(field.display.enabled());
        if (constants.Constants.font!=null) ((JComponent) obj).setFont(constants.Constants.font);
        if (obj instanceof JComponent) {
            DynamicAccessForm.initializeComponent((JComponent) obj);
            ((JComponent) obj).addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    typedTran(e);
                    passTheKey(e);
                }

                @Override
                public void keyTyped(KeyEvent e) {
                    typed();
                }
                
            });
            if (obj instanceof JComboBox) {
                ((JComboBox) obj).getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        typedTran(e);
                        passTheKey(e);
                    }
                });
                ((JComboBox) obj).getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        lostFocus();
                        PalleteRuleManager.runOnLostFocus((IRule)obj, panel.getRuleWrapper());
                    }
                });
            }
            if (obj instanceof LookupTableFieldPallete) {
                ((LookupTableFieldPallete) obj).lblDisplay.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        lostFocus();
                        PalleteRuleManager.runOnLostFocus((IRule)obj, panel.getRuleWrapper());
                    }
                });
            }
            ((JComponent) obj).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() instanceof IRule) {
                        if (e.getSource() instanceof JCheckBoxPallete) {
//                            lostFocus();
                        }
                        PalleteRuleManager.runOnClick((IRule)e.getSource(), panel.getRuleWrapper());
                    }
                }
            });
            ((JComponent) obj).addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    lostFocus();
                    if (e.getSource() instanceof IRule) {
                        PalleteRuleManager.runOnLostFocus((IRule)e.getSource(), panel.getRuleWrapper());
                    }
                }
            });
        }
        PanelUtil.setupCursor(obj);
        String[] duties = field.display.duties();
        if (!UserInfo.hasDuty(duties)) {
        	Log.out(field.display.name()," not allowed ",BeanUtil.concat(duties));
            String[] viewOnDuties = field.display.viewOnDuties();
            if (!UserInfo.hasDuty(viewOnDuties)) {
            	return new SecuredField(duties, "<<Secured>>");
            }
            else {
            	((JComponent) obj).setEnabled(false);
            	Thread t = new Thread(new Runnable() {
					public void run() {
						try {
							Thread.currentThread().sleep(2000);
			            	((JComponent) obj).setEnabled(false);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
            	});
            	t.start();
            }
        }
        return obj;
    }
    
    protected void lostFocus() {
        if (tbl instanceof JTable) {
            Object objTmp = getRenderedField();
            if (objTmp instanceof IGetText) {
                String name = this.field.display.fieldPrefix()+this.field.field.getName();
                AbstractIBean bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
                
                if (objTmp instanceof IParentComp) {
                    objTmp = ((IParentComp) objTmp).getTrueTxt();
                }
                if (bean!=null && objTmp!=null) {
                    bean.changeValue(name, ((IGetText)objTmp).getValue());
                }
            }
        }
    }
    
    protected void typed() {
        if (tbl instanceof JTable) {
            Object objTmp = getRenderedField();
            if (objTmp instanceof IGetText) {
                String name = this.field.display.fieldPrefix()+this.field.field.getName();
                AbstractIBean bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
                bean.changeValue(name, ((IGetText)objTmp).getValue());
            }
        }
    }

    protected void typedTran(KeyEvent v) {
        if (v.getKeyCode()!=KeyEvent.VK_ENTER) return;
        Object objTmp = getRenderedField();
        if (objTmp instanceof IGetText) {
            if (forSearch) {
                //do search
                TransactionPanel.getCurrentPanel().refreshRecords();
            }
            else {
                //do save
                //no saving yet, because the field may be located in the child panel
            }
        }
    }

    private boolean passTheKey(KeyEvent v) {
        TransactionPanel pnl = (TransactionPanel) Common2View.getTransactionPanel();
        int c = v.getKeyCode();
        int mod = v.getModifiers();

        if (c == KeyEvent.VK_BACK_SPACE) {
        } else if (c == KeyEvent.VK_DELETE) {
        } else if (c == KeyEvent.VK_F1) {
            PanelUtil.showHelp();
        } else if (c == KeyEvent.VK_ESCAPE) {
        } else if (c == KeyEvent.VK_PAGE_UP) {
            if (pnl!=null) pnl.prevRecord();
            return true;
        } else if (c == KeyEvent.VK_PAGE_DOWN) {
            if (pnl!=null) pnl.nextRecord();
            return true;
        }
        else if (c == KeyEvent.VK_INSERT) {
            if (mod == KeyEvent.CTRL_MASK) {
                pnl.newCopyRecord();
            }
            else {
                if (pnl!=null) pnl.newRecord();
            }
            return true;
        }
        else if (c == KeyEvent.VK_S && mod == KeyEvent.CTRL_MASK) {
            if (pnl!=null) pnl.saveRecord();
            return true;
        }
        else if (c == KeyEvent.VK_DELETE && mod == KeyEvent.CTRL_MASK) {
            if (pnl!=null) pnl.deleteRecord();
        }
        else if (c == KeyEvent.VK_F5) {
            if (pnl!=null) pnl.refreshRecords();
            return true;
        }
        else if (c==KeyEvent.VK_F8) {
            BusinessRuleForm.showDialog();
        }
        else if (c==KeyEvent.VK_F9) {
            BusinessRuleServiceForm.showDialog();
        }
        else if (c==KeyEvent.VK_F10) {
            DynamicAccessForm.showDialog((JComponent)v.getSource());
        }
        else if (c==KeyEvent.VK_F11) {
//            Common2View.showRule(AppMenuForm.getInstance());
        }
        else if (c==KeyEvent.VK_F12) {
            Common2View.getTransactionPanel().runRobot();
        }
        return false;
    }
    
    public void bindMe(String property, boolean sourceUnreadable, Object obj) {
        String name = this.field.display.fieldPrefix()+this.field.field.getName();
        if (tbl!=null) {
            if (tbl instanceof JTable) {
                org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, tbl, ELProperty.create(BeanUtil.concat("${selectedElement.",name,"}")), obj, BeanProperty.create(property));
                if (sourceUnreadable) 
                    binding.setSourceUnreadableValue(null);
                bindingGroup.addBinding(binding);

                binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, tbl, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), obj, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
            }
            else {
                org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, tbl, ELProperty.create(BeanUtil.concat("${",name,"}")), obj, BeanProperty.create(property));
                bindingGroup.addBinding(binding);
            }
        }
    }
}
