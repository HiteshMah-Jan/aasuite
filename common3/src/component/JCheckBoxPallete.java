/*
 * JCheckBoxPallete.java
 * 
 * Created on Jul 20, 2008, 11:54:23 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import template.screen.AbstractTemplatePanel.FieldCompose;
import ui.DynamicAccessForm;

/**
 *
 * @author Entokwaa
 */
public class JCheckBoxPallete extends JCheckBox implements IRule, IHelp, IGetText {
    private String helpName;

    public JCheckBox getMe() {
        return this;
    }
    
    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        PalleteRuleManager.useRule(this);
//        PalleteRuleManager.runOnValueChange(this);
    }
    
    public JCheckBoxPallete(String text) {
        this();
        this.setText(text);
    }
    
    public JCheckBoxPallete() {
        DynamicAccessForm.initializeComponent(this);
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
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

    public boolean isEmpty() {
        return isSelected();
    }

    public Object getValue() {
        return isSelected();
    }
    
    FieldCompose fieldCompose;
    public FieldCompose getFieldCompose() {
        return fieldCompose;
    }

    public void setFieldCompose(FieldCompose field) {
        this.fieldCompose = field;
    }
}
