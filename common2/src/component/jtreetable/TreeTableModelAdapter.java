/*
 * TreeTableModelAdapter.java
 *
 * Created on Oct 19, 2007, 11:15:52 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import java.util.List;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Budoy Entokwa
 */
public class TreeTableModelAdapter extends AbstractTableModel {

    List<ColumnHeader> headers;
    JTree tree;
    TreeTableModel treeTableModel;

    public TreeTableModelAdapter(TreeTableModel treeTableModel, JTree tree) {
        this.headers = headers;
        this.tree = tree;
        this.treeTableModel = treeTableModel;

        tree.addTreeExpansionListener(new TreeExpansionListener() {

            // Don't use fireTableRowsInserted() here;
            // the selection model would get  updated twice.
            public void treeExpanded(TreeExpansionEvent event) {
                fireTableDataChanged();
            }

            public void treeCollapsed(TreeExpansionEvent event) {
                fireTableDataChanged();
            }
        });
    }

    // Wrappers, implementing TableModel interface.
    public int getColumnCount() {
        return treeTableModel.getColumnCount();
    }

    public String getColumnName(int column) {
        return treeTableModel.getColumnName(column);
    }

    public Class getColumnClass(int column) {
        return treeTableModel.getColumnClass(column);
    }

    public int getRowCount() {
        return tree.getRowCount();
    }

    protected Object nodeForRow(int row) {
        TreePath treePath = tree.getPathForRow(row);
        return treePath.getLastPathComponent();
    }

    public Object getValueAt(int row, int column) {
        return treeTableModel.getValueAt(nodeForRow(row), column);
    }

    public boolean isCellEditable(int row, int column) {
        return treeTableModel.isCellEditable(nodeForRow(row), column);
    }

    public void setValueAt(Object value, int row, int column) {
        treeTableModel.setValueAt(value, nodeForRow(row), column);
    }
}
