/*
 * HierarchyNode.java
 *
 * Created on Oct 19, 2007, 12:38:20 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import service.util.AbstractIBean;
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class HierarchyNode extends DefaultMutableTreeNode {

    AbstractIBean bean;
	List childLst = new ArrayList();

    public AbstractIBean getBean() {
        return bean;
    }

    public List getChildLst() {
        return childLst;
    }

    @Override
    public String toString() {
		if (property!=null && property.trim().length()>0) {
			return BeanUtil.getPropertyValue(bean, property).toString();
		}
		return bean._DataDisplay();
    }

    public HierarchyNode(AbstractIBean bean, String property) {
        super(bean);
        this.bean = bean;
		this.property = property;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
		childLst.add(newChild);
    }
    private String property;
}
