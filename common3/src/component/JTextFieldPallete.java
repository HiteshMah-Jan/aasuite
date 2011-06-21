/*
 * JTextFieldPallete.java
 *
 * Created on Aug 19, 2007, 9:28:24 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import component.listener.ValueChangeEvent;
import component.listener.ValueChangeListener;
import java.awt.Color;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

import template.screen.AbstractTemplatePanel.FieldCompose;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JTextFieldPallete extends JTextField implements IHelp, IGetText, IRule {

    public static int ALPHA = 1;
    public static int INTEGER = 2;
    public static int DOUBLE = 3;
    private String oldValue;
    private String newValue;
    private String lookup;
    int dataType = ALPHA;
    boolean upCase = true;
    boolean mandatory = false;
    int maxColumns = -1;
    int scale = 0;

    private String errorText;

    public boolean withError() {
        return errorText!=null && !errorText.isEmpty();
    }
    
    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public JTextFieldPallete() {
        addFocus();
    }

    private void addFocus() {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextFieldPallete me = (JTextFieldPallete) e.getSource();
                me.errorText = null;
                me.setBackground(PanelUtil.OK_COLOR);
                String text = me.getText();
                if (me.isMandatory() && (text==null || text.isEmpty())) {
                    me.errorText = "Mandatory field.";
                    PanelUtil.showMessageToScreen(me.errorText);
                    me.setBackground(PanelUtil.ERROR_COLOR);
                    return;
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                JTextFieldPallete me = (JTextFieldPallete) e.getSource();
                me.selectAll();
            }
        });
    }

	public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public void setMaxColumns(int maxColumns) {
        this.maxColumns = maxColumns;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isUpCase() {
        return upCase;
    }

    public void setUpCase(boolean upCase) {
        this.upCase = upCase;
    }

    protected void skipKeyEvent(KeyEvent v) {
        super.processKeyEvent(v);
    }

    @Override
    protected void processKeyEvent(KeyEvent v) {
        char ch = v.getKeyChar();
        if (dataType == INTEGER) {
            if (!isAlwaysAllow(ch) && !Character.isDigit(ch)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                v.consume();
            }
        } else if (dataType == DOUBLE) {
            if (!isAlwaysAllow(ch) && !(Character.isDigit(ch) || ch == '.')) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                v.consume();
            }
        }
        if (upCase) {
            v.setKeyChar(Character.toUpperCase(ch));
        }
        if (maxColumns > 0) {
            String text = this.getText();
            if (text.length() >= maxColumns) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                v.consume();
            }
        }
        super.processKeyEvent(v);
    }

    private boolean isAlwaysAllow(char c) {
        switch(c) {
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_HOME:
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_END:
            case KeyEvent.VK_ENTER:
                return true;
        }
        return false;
    }
    
    @Override
    public void setText(String t) {
        oldValue = this.getText();
        newValue = t;
        super.setText(t);
        try {
            ValueChangeEvent v = new ValueChangeEvent(this, newValue, oldValue);
            processValueChangeEvent(v);
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, null, e);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runRuleManager();
            }
        });
//        PalleteRuleManager.runOnValueChange(this);
        revalidate();
    }

    Vector valueChangeListener;

    @SuppressWarnings(value = "unchecked")
    public synchronized void addValueChangeListener(ValueChangeListener a) {
        if (valueChangeListener == null) {
            valueChangeListener = new Vector(1);
        }
        if (!valueChangeListener.contains(a)) {
            valueChangeListener.addElement(a);
        }
    }

    public synchronized void removeValueChangeListener(ValueChangeListener a) {
        valueChangeListener.removeElement(a);
        if (valueChangeListener.size() == 0) {
            valueChangeListener = null;
        }
    }

    protected void processValueChangeEvent(ValueChangeEvent v) {
        if (valueChangeListener == null) {
            return;
        }
        Vector vec = (Vector) valueChangeListener.clone();
        int size = vec.size();
        for (int ix = 0; ix < size; ++ix) {
            ValueChangeListener a = (ValueChangeListener) vec.elementAt(ix);
            a.valueChanged(v);
            
//            PalleteRuleManager.runOnValueChange(this);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBackground(Color.WHITE);
    }

    String helpName;

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public boolean isEmpty() {
        return PanelUtil.isEmpty(this);
    }

    public int getIntText() {
        return (int) this.getDoubleText();
    }

    public double getDoubleText() {
        try {
            return Double.parseDouble(this.getText());
        } catch (Exception e) {
            return 0;
        }
    }
    
    public boolean isNumeric() {
        String str = this.getText();
        if (str==null) return false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c) || c=='.') {
                continue;
            }
            else return false;
        }
        return true;
    }
    
    public JTextFieldPallete getMe() {
        return this;
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
}