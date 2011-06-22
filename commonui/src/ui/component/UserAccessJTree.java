/*
 * UserAccessJTree.java
 * 
 * Created on Jan 30, 2008, 9:50:01 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui.component;

import bean.admin.*;
import component.IAuthorization;
import component.JTreePallete;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class UserAccessJTree extends JTreePallete {

    @Override
    public void processTransferredData(Object obj, JComponent source, JComponent target) {
        if (obj instanceof List) {
            for (Object module: (List) obj) {
                if (module instanceof AclModule) {
                    AclModule mod = (AclModule) module;
                    addModule(mod);
                }
                else if (module instanceof AclDuty) {
                    AclDuty mod = (AclDuty) module;
                    addDuty(mod);
                }
            }
            this.refresh();
            this.setExpandAll(true);
        }
        else if (obj instanceof AclModule) {
            addModule((AclModule) obj);
            this.refresh();
            this.setExpandAll(true);
        }
        else if (obj instanceof AclDuty) {
            addDuty((AclDuty) obj);
            this.refresh();
            this.setExpandAll(true);
        }
    }

    private void addModule(AclModule module) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getSelectionModel().getSelectionPath().getLastPathComponent();
        
        AclUser user = (AclUser) PanelUtil.getTreeObject(AclUser.class, node);
        if (user!=null) {
            IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(user);
            intr.addModule(module.getModuleName());
        }
        else {
            //might be using the AclGroup
            AclGroup grp = (AclGroup) PanelUtil.getTreeObject(AclGroup.class, node);
            if (grp!=null) {
                IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(grp);
                intr.addModule(module.getModuleName());
            }
        }
    }
    
    private void addDuty(AclDuty duty) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getSelectionModel().getSelectionPath().getLastPathComponent();
        AclUser user = (AclUser) PanelUtil.getTreeObject(AclUser.class, node);
        if (user!=null) {
            IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(user);
            intr.addDuty(duty.getCode());
        }
        else {
            AclGroup grp = (AclGroup) PanelUtil.getTreeObject(AclGroup.class, node);
            if (grp!=null) {
                IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(grp);
                intr.addDuty(duty.getCode());
            }
            else {
                AclModule mod = (AclModule) PanelUtil.getTreeObject(AclModule.class, node);
                if (mod!=null) {
                    IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(mod);
                    intr.addDuty(duty.getCode());
                }
            }
        }
    }
}
