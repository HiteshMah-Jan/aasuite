/*
 * PalleteRuleManager.java
 * 
 * Created on Jul 19, 2008, 5:42:05 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import bean.admin.AclUserDuty;
import bean.admin.AclUserGroup;
import constants.UserInfo;
import java.util.List;
import javax.swing.JComponent;
import rule.BusinessRuleWrapper;
import util.PanelUtil;
import util.ScriptRunner;

/**
 *
 * @author Entokwaa
 */
public class PalleteRuleManager {
    public final static int ON_CLICK = 1;
    public final static int ON_VALUE_CHANGE = 2;
    public final static int ON_LOST_FOCUS = 3;
    
    public static String getRuleForAllowed(IRule rule) {
        StringBuffer sb = new StringBuffer();
        String duty = rule.getAllowedDuty();
        String group = rule.getAllowedGroup();
        String userid = rule.getAllowedUserId();
        
        if (duty!=null && !duty.trim().isEmpty()) {
            sb.append("\tDuties : [").append(duty).append("]");
        }
        if (group!=null && !group.trim().isEmpty()) {
            sb.append("\tGroups : [").append(group).append("]");
        }
        if (userid!=null && !userid.trim().isEmpty()) {
            sb.append("\tUsers : [").append(userid).append("]");
        }
        return sb.toString();
    }
    
    public static boolean isPassedRule(IRule rule) {
        if (UserInfo.loginUser==null) return true;
        if (UserInfo.loginUser.isSuperAAA()) return true;
        if (PanelUtil.isEmpty(rule.getAllowedDuty()) && PanelUtil.isEmpty(rule.getAllowedGroup()) && PanelUtil.isEmpty(rule.getAllowedUserId())) {
//            no checking
            return true;
        }
        boolean dutyAllowed = isAllowedDuty(rule.getAllowedDuty());
        boolean groupAllowed = isAllowedGroup(rule.getAllowedGroup());
        boolean userAllowed = isAllowedUser(rule.getAllowedUserId());
        
        return dutyAllowed || groupAllowed || userAllowed;
    }
    
    public static void useRule(IRule rule) {
        boolean b = isPassedRule(rule);
        if (rule instanceof JComponent && !b) {
            ((JComponent) rule).setOpaque(b);
            ((JComponent) rule).setEnabled(b);
        }
    }
    
    public static void runOnClick(IRule rule, BusinessRuleWrapper brule) {
        brule.usedComp = (JComponent) rule;
        brule.runOnClick((JComponent) rule);
        String onclick = rule.getOnClickRule();
        if (onclick==null || onclick.trim().isEmpty()) return;
        
        ScriptRunner.runGroovyBackground(onclick, "FIELD", rule);
    }
    
    public static void runOnLostFocus(IRule rule, BusinessRuleWrapper brule) {
        brule.usedComp = (JComponent) rule;
        brule.runFocusLost((JComponent) rule);
        String onlostfocus = rule.getOnLostFocusRule();
        if (onlostfocus==null || onlostfocus.trim().isEmpty()) return;

        ScriptRunner.runGroovyBackground(onlostfocus, "FIELD", rule);
    }
    
    private static boolean isAllowedDuty(String duties) {
        if (duties==null) return false;
        String duty = null;
        List<AclUserDuty> lstDuties = constants.UserInfo.loginUser.getDuties();
        for (AclUserDuty aclUserDuty : lstDuties) {
            duty = aclUserDuty.getDutyCode()+"|";
            if (duties.contains(duty)) return true;
        }
        return false;
    }

    private static boolean isAllowedGroup(String groups) {
        if (groups==null) return false;
        String group = null;
        List<AclUserGroup> lstGrops = constants.UserInfo.loginUser.getGroups();
        for (AclUserGroup aclUserDuty : lstGrops) {
            group = aclUserDuty.getGroupCode()+"|";
            if (groups.contains(group)) return true;
        }
        return false;
    }

    private static boolean isAllowedUser(String users) {
        String username = constants.UserInfo.loginUser.getUser().getUserid()+"|";
        if (users.contains(username)) return true;
        return false;
    }

}
