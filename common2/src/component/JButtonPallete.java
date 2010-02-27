/*
 * JButtonPallete.java
 * 
 * Created on Jul 20, 2008, 11:59:08 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Entokwaa
 */
public class JButtonPallete extends JButton implements IHelp, IRule {
    public JButtonPallete() {
    	if (constants.Constants.font!=null) setFont(constants.Constants.font);
//        if (constants.Constants.panelBackground!=null) {
//            this.setBackground(constants.Constants.panelBackground);
//        }
//        if (constants.Constants.labelColor!=null) {
//            this.setForeground(constants.Constants.labelColor);
//        }
    }

    public JButtonPallete(String label) {
        super(label);
//        if (constants.Constants.panelBackground!=null) {
//            this.setBackground(constants.Constants.panelBackground);
//        }
//        if (constants.Constants.labelColor!=null) {
//            this.setForeground(constants.Constants.labelColor);
//        }
        if (constants.Constants.font!=null) setFont(constants.Constants.font);
    }
    
    public JButtonPallete(String label, String name) {
        super(label);
        this.setName(name);
        if (constants.Constants.font!=null) setFont(constants.Constants.font);
    }
    private JButton getMe() {
        return this;
    }
    
    private String helpName;

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
}

