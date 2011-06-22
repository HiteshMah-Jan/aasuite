/*
 * AclGroup.java
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
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AclGroup")
@UITemplate(template = TemplateDefault.class, gridCount = 4,columnSearch = {"code","groupName", "description"})
@Displays({
    @Display(name="code"),
    @Display(name="groupName"),
    @Display(name="description"),
    @Display(name="active")
})
public class AclGroup extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "groupName", nullable = false, length = 50)
    public String groupName;
    @Column(name = "description", length = 100)
    public String description;
    @Column(name = "active")
    public boolean active;    

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public AclGroupModule getModuleObj(String module) {
        return (AclGroupModule) this.selectFirstCache("SELECT a FROM AclGroupModule a WHERE a.moduleName='",module,"' AND a.groupCode='", code,"'");
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }   

    @Override
    public String toString() {
        return BeanUtil.concat(code," - ",groupName);
    }

    public static AbstractIBean getObject(String code) {
        return (AbstractIBean) AbstractIBean.firstRecord("SELECT a FROM AclGroup a WHERE a.code='",code,"'");
    }

    @Override
    public TreeModel extractTreeModel(String modelType) {
        DefaultMutableTreeNode root = null;
        root = new DefaultMutableTreeNode("Groups");
        @SuppressWarnings("unchecked")
        List<AclGroup> groups = this.list("SELECT a FROM AclGroup a ORDER BY a.code");
        for (AclGroup group : groups) {
            DefaultMutableTreeNode groupSon = new DefaultMutableTreeNode(group);
            root.add(groupSon);

            //get all modules for this user
            DefaultMutableTreeNode moduleSon = new DefaultMutableTreeNode("Modules");
            @SuppressWarnings("unchecked")
            List<AclGroupModule> modules = this.list("SELECT a FROM AclGroupModule a WHERE a.groupCode='",group.getCode(),"' ORDER BY a.moduleName");
            for (AclGroupModule module : modules) {
                moduleSon.add(new DefaultMutableTreeNode(module));
            }

            //get all duties for this user
            DefaultMutableTreeNode dutySon = new DefaultMutableTreeNode("Duties");
            @SuppressWarnings("unchecked")
            List<AclGroupDuty> duties = this.list("SELECT a FROM AclGroupDuty a WHERE a.groupCode='",group.getCode(),"' ORDER BY a.seq");
            for (AclGroupDuty duty : duties) {
                dutySon.add(new DefaultMutableTreeNode(duty));
            }

            groupSon.add(moduleSon);
            groupSon.add(dutySon);
        }
        TreeModel model = new DefaultTreeModel(root);
        return model;
    }

    public AclGroupDuty getDutyObj(String seq) {
        return (AclGroupDuty) this.selectFirstCache("SELECT a FROM AclGroupDuty a WHERE a.seq='",seq,"' AND a.groupCode='",code,"'");
    }

    public List<String> getDutyList() {
        List<String> lstReturn = new ArrayList<String>();
        List lst = list("SELECT a FROM AclGroupDuty a WHERE a.groupCode='",code,"'");
        for (Object bean : lst) {
            AclGroupDuty mod = (AclGroupDuty) bean;
            lstReturn.add(mod.getDutyCode());
        }
        return lstReturn;
    }

    public List<String> getModuleList() {
        List<String> lstReturn = new ArrayList<String>();
        List lst = this.list("SELECT a FROM AclGroupModule a WHERE a.groupCode='",code,"'");
        for (Object bean : lst) {
            AclGroupModule mod = (AclGroupModule) bean;
            lstReturn.add(mod.getModuleName());
        }
        return lstReturn;
    }

    public static void createGroup(String code, String name) {
        AclGroup grp = new AclGroup();
        grp.code = code;
        grp.groupName = name;
        grp.save();
    }
}
