/*
 * AclModule.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AclModule")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"moduleName", "description"})
@Displays({
    @Display(name="moduleName", upCase=false),
    @Display(name="description", upCase=false)
})
public class AclModule extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "moduleName", nullable = false)
    public String moduleName;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return moduleName;
    }

    public java.lang.String getModuleName() {
        return moduleName;
    }

    public void setModuleName(java.lang.String moduleName) {
        this.moduleName = moduleName;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public static AbstractIBean getObject(String seq) {
        return (AbstractIBean) AbstractIBean.firstRecord("SELECT a FROM AclModule a WHERE a.moduleName='",seq,"'");
    }

    @Override
    public TreeModel extractTreeModel(String modelType) {
        DefaultMutableTreeNode root = null;
        root = new DefaultMutableTreeNode("Modules");
        @SuppressWarnings("unchecked")
        List<AclModule> modules = this.list("SELECT a FROM AclModule a ORDER BY a.moduleName");
        for (AclModule module : modules) {
            DefaultMutableTreeNode userSon = new DefaultMutableTreeNode(module);
            root.add(userSon);

            //get all duties for this user
            DefaultMutableTreeNode dutySon = new DefaultMutableTreeNode("Duties");
            @SuppressWarnings("unchecked")
            List<AclDutyModule> duties = this.list("SELECT a FROM AclDutyModule a WHERE a.moduleName='",module.getModuleName(),"' ORDER BY a.dutyCode");
            for (AclDutyModule duty : duties) {
                dutySon.add(new DefaultMutableTreeNode(duty));
            }

            userSon.add(dutySon);
        }
        TreeModel model = new DefaultTreeModel(root);
        return model;
    }

    public AclDutyModule getDutyObj(String dutyCode) {
        return (AclDutyModule) this.selectFirstCache("SELECT a FROM AclDutyModule a WHERE a.dutyCode='",dutyCode,"' AND a.moduleName='",moduleName,"'");
    }

    public List<String> getDutyList() {
        List<String> lstReturn = new ArrayList<String>();
        List lst = this.list("SELECT a FROM AclDutyModule a WHERE a.moduleName='",moduleName,"'");
        for (Object bean : lst) {
            AclDutyModule mod = (AclDutyModule) bean;
            lstReturn.add(mod.getDutyCode());
        }
        return lstReturn;
    }
}
