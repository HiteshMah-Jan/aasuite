/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.IRule;
import component.LookupTableFieldPallete;
import component.PalleteRuleManager;
import component.listener.ValueChangeEvent;
import component.listener.ValueChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.jdesktop.beansbinding.ELProperty;

import service.util.AbstractIBean;
import template.UITemplate;
import util.Log;

/**
 *
 * @author Entokwaa
 */
public class PopSearchRenderer extends AbstractComponentRenderer {
    LookupTableFieldPallete f;

    protected String[] getLinkColumns() {
        String[] cols = this.field.display.linktoColumns();
        if (cols==null || cols.length==0 || (cols.length==1 && (cols[0]==null || cols[0].isEmpty())) ) {
            UITemplate temp = (UITemplate) this.field.display.linktoBean().getAnnotation(UITemplate.class);
            if (temp==null) {
                Log.info("Null link for field ",this.field.display.name());
            }
            return temp.columnSearch();
        }
        return cols;
    }
    
    @Override
    public Object getRenderedField() {
        String name = this.field.field.getName();
        f = new LookupTableFieldPallete();
        f.setBean(this.field.display.linktoBean().getSimpleName());
        f.setColumns(getLinkColumns());
        f.setName(name);
        int width = this.field.display.width();
        if (width>0) {
            Dimension dim = f.getPreferredSize();
            if (tbl!=null) dim.width = width;
            else dim.width = 100;
            f.setPreferredSize(dim);
        }
        bindMe("text", true, f.lookup.txt);
        f.lblDisplay.setEnabled(false);
        f.setToolTipText(this.field.display.name());
        f.lblDisplay.setToolTipText(this.field.display.name());
        f.btn.setToolTipText(this.field.display.name());
        f.lookup.txt.addValueChangeListener(new ValueChangeListener() {
            public void valueChanged(final ValueChangeEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        AbstractIBean bean = null;
                        try {
                            PalleteRuleManager.runOnLostFocus((IRule)evt.getSource(), panel.getRuleWrapper());
                            bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
                        }
                        catch (Exception e) {
                        	if (bean!=null) {
                                util.Log.warning("PopSearch Set Value Error - ",field.field.getName(),"->",bean.getClass().getSimpleName());
                        	}
//                        	else {
//                                util.Log.warning("PopSearch Set Value Error - "+field.field.getName()+"->"+e.getMessage());
//                        	}
                        }
                        if (bean!=null) {
                        	if (f.lookup.txt.getValue()==null || f.lookup.txt.getValue().toString().trim().isEmpty()) {
                        		try {
                                	bean.changeValuePop(field.field.getName(), f.lookup.txt.getValue());
                        		}
                        		catch (Exception e) {
                        		}
                        	}
                        	else {
                        		try {
                                	bean.changeValuePop(field.field.getName(), f.lookup.txt.getValue());
                        		}
                        		catch (Exception e) {
                                    util.Log.warning("Change value of " , field.field.getName()," - ",bean.getClass().getName());
//                        			e.printStackTrace();
                        		}
                        	}
                        }
                    }
                });
            }
        });
        f.btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_DELETE) {
                    clear();
                }
            }
        });
        f.btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    clear();
                }
            }
        });
        return f;
    }

    protected void searchFirst() {
        String bean = this.field.display.linktoBean().getSimpleName();
        String where = this.field.display.linktoBeanAddWhere();
        String value = f.lblDisplay.getText();
        if (value==null || value.isEmpty()) {
            f.lookup.txt.setText("");
            return;
        }
        if (where==null || where.isEmpty()) {
            String col = getLinkColumns()[0];
            AbstractIBean obj1 = (AbstractIBean) AbstractIBean.firstRecord("SELECT a FROM ",bean," a WHERE a.",col," like '",value,"%' ORDER BY a.",col);
            f.lblDisplay.setText(obj1.toString());
            f.lookup.txt.setText(obj1.keyVal().toString());
        }
        else {
            where = where.replaceAll("?", value);
            AbstractIBean obj1 = (AbstractIBean) AbstractIBean.firstRecord("SELECT a FROM ",bean," a WHERE ",where);
            f.lblDisplay.setText(obj1.toString());
            f.lookup.txt.setText(obj1.keyVal().toString());
        }
    }

    private void clear() {
        f.setText("");
    }
}