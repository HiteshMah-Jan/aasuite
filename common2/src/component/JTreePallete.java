/*
 * JTreePallete.java
 *
 * Created on Jan 11, 2008, 10:55:48 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import component.dnd.ComponentTransferHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import service.util.AbstractIBean;

/**
 *
 * @author Entokwaa
 */
public class JTreePallete extends JTree implements component.dnd.IComponentDND {

    AbstractIBean rootBean;

    public AbstractIBean getRootBean() {
        return rootBean;
    }

    public void setRootBean(AbstractIBean rootBean) {
        this.rootBean = rootBean;
        this.setModel(rootBean.extractTreeModel(null));
    }

    public JTreePallete() {
        setTransferHandler(new ComponentTransferHandler());
    }

    public Object getDataTransfer() throws Exception {
        //By default we don't know what to transfer so this must be extended
        Object obj = this.getSelectionPath().getLastPathComponent();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
        Logger.getLogger("global").log(Level.INFO, node.getUserObject().getClass().toString());
        return node.getUserObject();
    }

    public void processTransferredData(Object obj, JComponent source, JComponent target) {
        throw new UnsupportedOperationException("This must be extended.");
    }

    public void refresh() {
        setModel(rootBean.extractTreeModel(null));
    }

    public void setExpandAll(boolean expand) {
        int rowCount = this.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            if (expand) {
                this.expandRow(i);
            } else {
                this.collapseRow(i);
            }
        }
    }

    public List<AbstractIBean> getSelectedObjects() {
        List<AbstractIBean> lst = new ArrayList<AbstractIBean>();
        int[] allRows = this.getSelectionRows();
        for (int rowIndex:allRows) {
            Object[] arrObj = this.getPathForRow(rowIndex).getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) arrObj[arrObj.length-1];
            Object obj = node.getUserObject();
            if (obj instanceof AbstractIBean) {
                lst.add((AbstractIBean) obj);
            }
        }
        return lst;
    }
}
