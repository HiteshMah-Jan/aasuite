package rule;

import bean.admin.AclGroup;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 5, 2009
 * Time: 2:06:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppMenu_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnSetupGroups".equals(comp.getName())) {
            setupGroups();
        }
    }

    private void setupGroups() {
        AclGroup.createGroup("CASHIER", "CASHIER");
        AclGroup.createGroup("ACCOUNTING", "ACCOUNTING");
        AclGroup.createGroup("LIBRARIAN", "LIBRARIAN");
        AclGroup.createGroup("REGISTRAR", "REGISTRAR");
        AclGroup.createGroup("FACULTY", "FACULTY");
        AclGroup.createGroup("HUMAN RESOURCE", "HUMAN RESOURCE");
        AclGroup.createGroup("GUIDANCE", "GUIDANCE");
        AclGroup.createGroup("OSA", "OSA");
        AclGroup.createGroup("CLINIC", "CLINIC");
    }
}
