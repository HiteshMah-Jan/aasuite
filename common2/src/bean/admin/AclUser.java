/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.*;
import java.io.Serializable;
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
@Table(name = "AclUser")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"userid", "lastname", "firstname"})
@Displays({
    @Display(name="userid"),
    @Display(name="password"),
    @Display(name="firstname"),
    @Display(name="lastname"),
    @Display(name="active")
})
public class AclUser extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "userid", nullable = false, length = 30)
    public String userid;
    @Column(name = "password", nullable = false, length = 30)
    public String password;
    @Column(name = "firstname", nullable = false, length = 50)
    public String firstname;
    @Column(name = "lastname", nullable = false, length = 50)
    public String lastname;
    @Column(name = "active", nullable = false, length = 1)
    public boolean active;

    public static final transient String USER_DUTIES = "duties";

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    public String getComboDisplay() {
        if (userid == null) {
            return "";
        } else {
            return userid;
        }
    }
    
    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(userid," [",lastname,", ",firstname,"]");
    }

    public Person getPerson() {
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Employee a WHERE a.userid='",userid,"'");
    }

    @Override
    public TreeModel extractTreeModel(String modelType) {
        DefaultMutableTreeNode root = null;
        root = new DefaultMutableTreeNode("Users");
        @SuppressWarnings("unchecked")
        List<AclUser> users = list("SELECT a FROM AclUser a ORDER BY a.userid");
        for (AclUser user : users) {
            if (!user.isEmployee()) {
                continue;
            }
            DefaultMutableTreeNode userSon = new DefaultMutableTreeNode(user);
            root.add(userSon);

            //get all modules for this user
            DefaultMutableTreeNode moduleSon = new DefaultMutableTreeNode("Modules");
            @SuppressWarnings("unchecked")
            List<AclUserModule> modules = this.list("SELECT a FROM AclUserModule a WHERE a.userid='",user.getUserid(),"' ORDER BY a.moduleName");
            for (AclUserModule module : modules) {
                moduleSon.add(new DefaultMutableTreeNode(module));
            }

            //get all duties for this user
            DefaultMutableTreeNode dutySon = new DefaultMutableTreeNode("Duties");
            @SuppressWarnings("unchecked")
            List<AclUserDuty> duties = this.list("SELECT a FROM AclUserDuty a WHERE a.userid='",user.getUserid(),"' ORDER BY a.dutyCode");
            for (AclUserDuty duty : duties) {
                dutySon.add(new DefaultMutableTreeNode(duty));
            }

            //get all groups for this user
            DefaultMutableTreeNode groupSon = new DefaultMutableTreeNode("Groups");
            @SuppressWarnings("unchecked")
            List<AclUserGroup> groups = this.list("SELECT a FROM AclUserGroup a WHERE a.userid='",user.getUserid(),"' ORDER BY a.groupCode");
            for (AclUserGroup group : groups) {
                groupSon.add(new DefaultMutableTreeNode(group));
            }

            userSon.add(moduleSon);
            userSon.add(dutySon);
            userSon.add(groupSon);
        }
        TreeModel model = new DefaultTreeModel(root);
        return model;
    }

    public static AbstractIBean getObject(String userid) {
        return (AbstractIBean) AbstractIBean.firstRecord("SELECT a FROM AclUser a WHERE a.userid='" , userid , "'");
    }

    public AclUserDuty getDutyObj(String dutyCode) {
        return (AclUserDuty) this.selectFirstCache("SELECT a FROM AclUserDuty a WHERE a.userid='" , userid , "' AND a.dutyCode='" , dutyCode , "'");
    }

    public AclUserModule getModuleObj(String moduleName) {
        return (AclUserModule) this.selectFirstCache("SELECT a FROM AclUserModule a WHERE a.userid='" , userid , "' AND a.moduleName='" , moduleName , "'");
    }

    public AclUserGroup getGroupObj(String group) {
        return (AclUserGroup) this.selectFirstCache("SELECT a FROM AclUserGroup a WHERE a.userid='" , userid , "' AND a.groupCode='" , group , "'");
    }

    public List<AclUserDuty> getDuties() {
        return this.list("SELECT a FROM AclUserDuty a WHERE a.userid='" , userid , "'");
    }

    public List<AclUserModule> getModules() {
        return this.list("SELECT a FROM AclUserModule a WHERE a.userid='" , userid , "'");
    }

    public List<AclUserGroup> getGroups() {
        return this.list("SELECT a FROM AclUserGroup a WHERE a.userid='" , userid , "'");
    }

    public boolean isEmployee() {
        Person p = getPerson();
        if (p == null) {
            return false;
        }
        return (p instanceof Employee);
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
