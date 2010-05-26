/*
 * LookupTableFieldPallete.java
 *
 * Created on August 16, 2008, 7:49 PM
 */
package component;

import component.listener.ValueChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import service.util.AbstractIBean;
import service.util.IBean;
import template.screen.AbstractTemplatePanel.FieldCompose;
import util.BeanUtil;
import util.ClientCache;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author  Entokwaa
 */
public class LookupTableFieldPallete extends javax.swing.JPanel implements IGetText, IRule {
    private static String NO_SUCH_CODE = "No such code.";
    private String title = "Select Record";
    private String[] columns;
    private String bean;
    private JLabel labelDisplay;
    private String text;
    public LookupTable lookup;
    static Map<String, AbstractIBean> mapKey = new HashMap<String, AbstractIBean>();

    private LookupTableFieldPallete getMe() {
    	return this;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btn.setEnabled(enabled);
        btn.setVisible(enabled);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        lookup.txt.setName(name);
    }

    public AbstractIBean getSelectedBean() {
        AbstractIBean ibean = null;
        if (mapKey.containsKey(BeanUtil.concat(bean,"-",lookup.txt.getText()))) {
            ibean = mapKey.get(BeanUtil.concat(bean,"-",lookup.txt.getText()));
        }
        else {
            String txt = lookup.txt.getText();
            if (txt==null || txt.trim().isEmpty() || txt.trim().equals("0")) {
                try {
                    java.lang.Class cls = PanelUtil.getBeanClass(getBean());
                    cls = AbstractIBean.extractBeanClass(cls);
                    mapKey.put(BeanUtil.concat(bean,"-",lookup.txt.getText()), (AbstractIBean) cls.newInstance());
                } catch (Exception ex) {
                }
            }
            else {
                Log.out("................EXTRACT LINK ",bean,":",lookup.txt.getText());
//                check first from the lookup
                ibean = lookup.getFromBeans(lookup.txt.getText());
                if (ibean==null) {
                	ibean = AbstractIBean.extractObject(bean, lookup.txt.getText());
                }
                mapKey.put(BeanUtil.concat(bean,"-",lookup.txt.getText()), ibean);
            }
        }
        return ibean;
    }

    public JLabel getLabelDisplay() {
        return labelDisplay;
    }

    public void setLabelDisplay(JLabel labelDisplay) {
        this.labelDisplay = labelDisplay;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
        lookup.setBean(bean);
    }

    public JButton getBtn() {
        return btn;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
        lookup.setColumns(columns);
    }

    public boolean isEmpty() {
        return lookup.isEmpty();
    }

    public String getText() {
        text = lookup.getText();
        return text;
    }
    
    public int getIntText() {
        String txt = getText();
        try {
            return Integer.parseInt(txt);
        }
        catch (Exception e) {
            return -1;
        }
    }
    IBean mybean;

    public void setText(String text) {
        lookup.setText(text);
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        lookup.setTitle(title);
    }

    /** Creates new form LookupTableFieldPallete */
    public LookupTableFieldPallete() {
        initComponents();
        lookup = new LookupTable();
        lookup.txt.addValueChangeListener(new ValueChangeListener() {
            public void valueChanged(component.listener.ValueChangeEvent evt) {
                ThreadRunner t = new ThreadRunner(getMe());
                t.run();
            }
        });
    }

    private static class ThreadRunner implements Runnable {
    	LookupTableFieldPallete pallete;
    	ThreadRunner(LookupTableFieldPallete pallete) {
    		this.pallete = pallete;
    	}
    	
		@Override
		public void run() {
            AbstractIBean ibean = null;
            if (mapKey.containsKey(BeanUtil.concat(pallete.bean,"-",pallete.lookup.txt.getText()))) {
                ibean = mapKey.get(BeanUtil.concat(pallete.bean,"-",pallete.lookup.txt.getText()));
            }
            else {
                String txt = pallete.lookup.txt.getText();
                if (txt==null || txt.trim().isEmpty() || txt.trim().equals("0")) {
                    try {
                        java.lang.Class cls = PanelUtil.getBeanClass(pallete.getBean());
                        cls = AbstractIBean.extractBeanClass(cls);
                        mapKey.put(BeanUtil.concat(pallete.bean,"-",pallete.lookup.txt.getText()), (AbstractIBean) cls.newInstance());
                    } catch (Exception ex) {
                    }
                }
                else {
                    Log.out("...................EXTRACT LINK ",pallete.bean,":",pallete.lookup.txt.getText());
                    ibean = pallete.lookup.getFromBeans(pallete.lookup.txt.getText());
                    if (ibean==null) {
//                    	check to see if this is cache in the DB Call, if not there must be a cache in this component because this is lookup
                        java.lang.Class cls = PanelUtil.getBeanClass(pallete.getBean());
                        try {
                            AbstractIBean tmp = (AbstractIBean) cls.newInstance();
                            if (!tmp.cacheClient()) {
                            	String id = BeanUtil.concat(pallete.bean,"-",pallete.lookup.txt.getText());
                            	ibean = (AbstractIBean) ClientCache.getCache(id);
                            	if (ibean==null) {
                                	ibean = (AbstractIBean) ClientCache.resetCache(id, AbstractIBean.extractObject(pallete.bean, pallete.lookup.txt.getText()));
                            	}
                            }
                            else {
                            	ibean = AbstractIBean.extractObject(pallete.bean, pallete.lookup.txt.getText());
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                    mapKey.put(BeanUtil.concat(pallete.bean,"-",pallete.lookup.txt.getText()), ibean);
                }
            }
            if (ibean==null) {
            	pallete.lblDisplay.setText("");
            }
            else {
            	pallete.lblDisplay.setText(ibean.toString());
            }
            pallete.lblDisplay.setCaretPosition(0);
		}
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn = new javax.swing.JButton();
        lblDisplay = new JTextFieldPallete();

        setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(LookupTableFieldPallete.class);
        btn.setIcon(resourceMap.getIcon("btn.icon")); // NOI18N
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn.setContentAreaFilled(false);
        btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn.setIconTextGap(0);
        btn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        add(btn, java.awt.BorderLayout.EAST);

        lblDisplay.setFont(constants.Constants.font);
        lblDisplay.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDisplayMouseEntered(evt);
            }
        });
        add(lblDisplay, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
    lookup.showSearch();
    AbstractIBean ibean = getSelectedBean();
    if (ibean != null) lblDisplay.setText(ibean.toString());
}//GEN-LAST:event_btnActionPerformed

private void lblDisplayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDisplayMouseEntered
    lblDisplay.setToolTipText(lblDisplay.getText());
}//GEN-LAST:event_lblDisplayMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn;
    public javax.swing.JTextField lblDisplay;
    // End of variables declaration//GEN-END:variables
    FieldCompose fieldCompose;

    public FieldCompose getFieldCompose() {
        return fieldCompose;
    }

    public void setFieldCompose(FieldCompose field) {
        this.fieldCompose = field;
    }

    public Object getValue() {
        return getText();
    }

    private void runRuleManager() {
        PalleteRuleManager.useRule(this);
    }
    private String allowedUserId;
    private String allowedGroup;
    private String allowedDuty;
    private String onClickRule;
    private String onValueChangeRule;
    private String onLostFocusRule;

    public String getAllowedDuty() {
        return allowedDuty;
    }

    public void setAllowedDuty(String allowedDuty) {
        this.allowedDuty = allowedDuty;
    }

    public String getAllowedGroup() {
        return allowedGroup;
    }

    public void setAllowedGroup(String allowedGroup) {
        this.allowedGroup = allowedGroup;
    }

    public String getAllowedUserId() {
        return allowedUserId;
    }

    public void setAllowedUserId(String allowedUserId) {
        this.allowedUserId = allowedUserId;
    }

    public String getRuleForAllowed() {
        return PalleteRuleManager.getRuleForAllowed(this);
    }

    public String getOnClickRule() {
        return onClickRule;
    }

    public void setOnClickRule(String onClickRule) {
        this.onClickRule = onClickRule;
    }

    public String getOnLostFocusRule() {
        return onLostFocusRule;
    }

    public void setOnLostFocusRule(String onLostFocusRule) {
        this.onLostFocusRule = onLostFocusRule;
    }

    public String getOnValueChangeRule() {
        return onValueChangeRule;
    }

    public void setOnValueChangeRule(String onValueChangeRule) {
        this.onValueChangeRule = onValueChangeRule;
    }
}