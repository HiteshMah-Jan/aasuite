/*
 * Building.java
 *
 * Created on Dec 2, 2007, 12:37:12 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import constants.UserInfo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "DynamicReportCategory")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"category", "description"})
@Displays({
        @Display(name="category"),
        @Display(name="description")
})
public class DynamicReportCategory extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "category", nullable = false)
    public String category;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return category;
    }

    public java.lang.String getCategory() {
        return category;
    }

    public void setCategory(java.lang.String category) {
        this.category = category;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    @SuppressWarnings("unchecked")
    public List<DynamicReport> getReports() {
        if (category == null || category.isEmpty()) {
            return this.list("SELECT a FROM DynamicReport a WHERE a.category IS NULL");
        } else {
            return this.list("SELECT a FROM DynamicReport a WHERE a.category='" , category , "'");
        }
    }

    @Override
    public TreeModel extractTreeModel(String modelType) {
        DefaultMutableTreeNode root = null;
        root = new DefaultMutableTreeNode("Reports");
        List lst = this.list("SELECT a FROM DynamicReportCategory a");
        for (Object obj : lst) {
            DynamicReportCategory categ = (DynamicReportCategory) obj;
            List<DynamicReport> reports = categ.getReports();
            if (reports == null || reports.size() == 0) {
                continue;
            }
            DefaultMutableTreeNode cat = new DefaultMutableTreeNode(categ);
            root.add(cat);
            for (DynamicReport dyn : reports) {
                if (hasAccess(dyn)) {
                    cat.add(new DefaultMutableTreeNode(dyn));
                }
            }
        }
        DynamicReportCategory categ = new DynamicReportCategory();
        List<DynamicReport> reports = categ.getReports();
        for (DynamicReport dyn : reports) {
            if (hasAccess(dyn)) {
                root.add(new DefaultMutableTreeNode(dyn));
            }
        }

        TreeModel model = new DefaultTreeModel(root);
        return model;
    }
    @Transient
    List<DynamicReportAccess> allUserAccess;

    @SuppressWarnings("unchecked")
    private boolean hasAccess(DynamicReport dyn) {
        if (dyn.getPublicAccess()) {
            return true;
        }
        if (UserInfo.loginUser.isSuperAAA()) {
            return true;
        }
        String userid = UserInfo.loginUser.getUser().getUserid();
        if (allUserAccess == null) {
            allUserAccess = this.list("SELECT a FROM DynamicReportAccess a WHERE a.username='",userid,"'");
        }
        for (DynamicReportAccess access : allUserAccess) {
            if (access.getDynamicReportCode().equals(dyn.getCode())) {
                return true;
            }
        }
        return false;
    }
}
