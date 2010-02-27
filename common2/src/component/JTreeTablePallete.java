/*
 * JTreeTable.java
 *
 * Created on Oct 19, 2007, 11:16:54 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import component.dnd.IComponentDND;
import component.dnd.ComponentTransferHandler;
import component.jtreetable.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author Budoy Entokwa
 */
public class JTreeTablePallete extends JTable implements IComponentDND {

    public TreeTableCellRenderer tree;
    private TreeTableModel mymodel;
    TreeTableModelAdapter modelAdapter;

    private void initTable() {
        TableColumn col = this.getColumnModel().getColumn(0);
        int width = 220;
        col.setPreferredWidth(width);
    }
    
    public TreeTableModel getTreeModel() {
        return mymodel;
    }

    public void setTreeModel(TreeTableModel treeTableModel) {
        mymodel = treeTableModel;
        tree = new TreeTableCellRenderer(treeTableModel);
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
    }

    public JTreeTablePallete(TreeTableModel treeTableModel) {
        // Create the tree. It will be used as a renderer and editor.
        mymodel = treeTableModel;
        tree = new TreeTableCellRenderer(treeTableModel);

        // Install a tableModel representing the visible rows in the tree.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

        // Force the JTable and JTree to share their row selection models.
        tree.setSelectionModel(new DefaultTreeSelectionModel() {
            // Extend the implementation of the constructor, as if:
            /* public this() */
            {
                setSelectionModel(listSelectionModel);
            }
        });
        // Make the tree and table row heights the same.
        tree.setRowHeight(getRowHeight());

        // Install the tree editor renderer and editor.
        setDefaultRenderer(TreeTableModel.class, tree);
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setTransferHandler(new ComponentTransferHandler());
        initTable();
    }

    public JTreeTablePallete() {
        SampleJTreeTableModel treeTableModel = new SampleJTreeTableModel(null);
        mymodel = treeTableModel;
        // Create the tree. It will be used as a renderer and editor.
        tree = new TreeTableCellRenderer(treeTableModel);

        // Install a tableModel representing the visible rows in the tree.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

        // Force the JTable and JTree to share their row selection models.
        tree.setSelectionModel(new DefaultTreeSelectionModel() {
            // Extend the implementation of the constructor, as if:
            /* public this() */
            {
                setSelectionModel(listSelectionModel);
            }
        });
        // Make the tree and table row heights the same.
        tree.setRowHeight(getRowHeight());

        // Install the tree editor renderer and editor.
        setDefaultRenderer(TreeTableModel.class, tree);
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setTransferHandler(new ComponentTransferHandler());
        initTable();
    }
    @Override
    public int getEditingRow() {
        return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 : editingRow;
    }

    public class TreeTableCellRenderer extends JTree implements TableCellRenderer {
        public Object selectedNode;
        protected int visibleRow;

        public TreeTableCellRenderer(TreeModel model) {
            super(model);
            this.setRootVisible(false);
        }

        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, 0, w, JTreeTablePallete.this.getHeight());
        }

        public void paint(Graphics g) {
            g.translate(0, -visibleRow * getRowHeight());
            super.paint(g);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                selectedNode = value;
            } else {
                setBackground(table.getBackground());
            }
            visibleRow = row;
            return this;
        }
    }

    public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
            return tree;
        }
    }

    public Object getDataTransfer() throws Exception {
        List data = new ArrayList();
        int[] allRows = this.getSelectedRows();
        for (int rowIndex : allRows) {
            Object str = this.getValueAt(rowIndex, 0);
            Logger.getLogger("global").log(Level.INFO, "SELECTED === " + str + " - " + str.getClass().getName());
            data.add(str);
        }
        return data;
    }

    public void processTransferredData(Object obj, JComponent source, JComponent target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void refresh() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mymodel.refresh();
                AbstractTableModel model = (AbstractTableModel) getModel();
                model.fireTableDataChanged();
                updateUI();
            }
        });
    }
}
