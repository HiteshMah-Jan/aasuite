/*
 * IRule.java
 * 
 * Created on Jul 19, 2008, 5:37:38 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

/**
 *
 * @author Entokwaa
 */
public interface IRule {
    String getAllowedUserId();
    String getAllowedGroup();
    String getAllowedDuty();

    String getOnClickRule();
    String getOnValueChangeRule();
    String getOnLostFocusRule();
    
    void setAllowedUserId(String str);
    void setAllowedGroup(String str);
    void setAllowedDuty(String str);

    void setOnClickRule(String str);
    void setOnValueChangeRule(String str);
    void setOnLostFocusRule(String str);

    String getRuleForAllowed();
}
