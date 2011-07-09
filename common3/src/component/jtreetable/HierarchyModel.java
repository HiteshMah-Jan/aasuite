/*
 * FileSystemModel.java
 *
 * Created on Oct 19, 2007, 11:17:58 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import service.util.AbstractIBean;
import util.BeanUtil;


/**
 *
 * @author Budoy Entokwa
 */
public class HierarchyModel extends AbstractTreeTableModel implements TreeTableModel {
	List<ColumnHeader> headers;
	DefaultMutableTreeNode root;

    public HierarchyModel(DefaultMutableTreeNode root, Class aClass, String string, List<ColumnHeader> headers) {
		super(root);
		this.root = root;
		cls = aClass;
		additionalCriteria = string;
		this.headers = headers;
    }

    @SuppressWarnings("unchecked")
	protected Object[] getChildren(Object node) {
		if (node.toString().equals("+")) {
			List lst = new ArrayList();
			Enumeration e = root.children();
			while (e.hasMoreElements()) {
				lst.add(e.nextElement());
			}
			return lst.toArray();
		}
		else {
			if (node instanceof HierarchyNode) {
				HierarchyNode hnode = (HierarchyNode) node;
				List lst = hnode.getChildLst();
				if (lst!=null) {
					return lst.toArray();
				}
			}
			return null;
		}
    }

    //
    // The TreeModel interface
    //
    public int getChildCount(Object node) {
        return getChildren(node)==null?0:getChildren(node).length;
    }

    public Object getChild(Object node, int i) {
        return getChildren(node)==null?null:getChildren(node)[i];
    }

    // The superclass's implementation would work, but this is more efficient.
    public boolean isLeaf(Object node) {
		return this.getChildCount(node) <= 0;
    }

    //
    //  The TreeTableNode interface.
    //
    public int getColumnCount() {
        return headers.size();
    }

    public String getColumnName(int column) {
        return headers.get(column).getHeader();
    }

    public Class getColumnClass(int column) {
		if (column==0) {
			return TreeTableModel.class;
		}
        return super.getColumnClass(column);
    }

    public Object getValueAt(Object node, int column) {
		if (node instanceof HierarchyNode) {
			HierarchyNode hnode = (HierarchyNode) node;
			AbstractIBean bean = hnode.getBean();
			return BeanUtil.getPropertyValue(bean, headers.get(column).getParentField());
		}
		return "";
    }
    private Class cls;
    private String additionalCriteria;
}