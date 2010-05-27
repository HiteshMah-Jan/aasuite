/*
 * GroupedTable.java
 *
 * Created on March 24, 2009, 4:03 PM
 */
package test;

import component.JTextFieldPallete;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author  alex
 */
public class GroupedTable extends javax.swing.JPanel {
    private String sumFooter;
    private boolean showHeaderFilter;

    public boolean isShowHeaderFilter() {
        return showHeaderFilter;
    }

    public void setShowHeaderFilter(boolean showHeaderFilter) {
        this.showHeaderFilter = showHeaderFilter;
    }

    public String getSumFooter() {
        return sumFooter;
    }

    public void setSumFooter(String sumFooter) {
        this.sumFooter = sumFooter;
    }

    public static void main(String[] args) {
        GroupedTable g = new GroupedTable();
        g.setSumFooter("1,3");
//        g.setShowHeaderFilter(true);
        PanelUtil.displayToFrame(g);
    }

    public void setupFooter() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int count = tbl.getColumnCount();
                for (int i = 0; i < count; i++) {
                    JLabel p = getFooterLabel(i);
                    Rectangle rec = tbl.getCellRect(0, i, true);
                    Dimension dim = new Dimension(rec.width, 20);
                    p.setPreferredSize(dim);
                }
                pnlFooter.updateUI();
                pnlFooter.setVisible(sumFooter!=null && !sumFooter.isEmpty());
            }
        });
        t.start();
    }

    public void setupHeader() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int count = tbl.getColumnCount();
                for (int i = 0; i < count; i++) {
                    JTextFieldPallete p = getHeaderFilter(i);
                    Rectangle rec = tbl.getCellRect(0, i, true);
                    Dimension dim = new Dimension(rec.width, 20);
                    p.setPreferredSize(dim);
                }
                pnlHeader.updateUI();
                pnlHeader.setVisible(showHeaderFilter);
            }
        });
        t.start();
    }

    Map<Integer, JLabel> mapFooter = new HashMap<Integer, JLabel>();
    GridBagConstraints conFooter = new GridBagConstraints();
    
    Map<Integer, JTextFieldPallete> mapHeader = new HashMap<Integer, JTextFieldPallete>();
    GridBagConstraints conHeader = new GridBagConstraints();

    private void calculateSum() {
        int count = tbl.getColumnCount();
        for (int i = 0; i < count; i++) {
            if (withSumFooter(i)) {
                calculateSum(i);
            }
        }
    }
    
    private void calculateSum(int col) {
        double total = 0;
        int rows = tbl.getRowCount();
        for (int i = 0; i < rows; i++) {
            try {
                String str = tbl.getValueAt(i, col).toString();
                double d = Double.parseDouble(str);
                total += d;
            }
            catch (Exception e) {
            }
        }
        JLabel lbl = getFooterLabel(col);
        lbl.setHorizontalAlignment(JLabel.RIGHT);
        lbl.setText(BeanUtil.concat(total,""));
    }
    
    private JLabel getFooterLabel(int i) {
        if (mapFooter.get(i)!=null) {
            return mapFooter.get(i);
        }
        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    calculateSum();
                }
            }
        });
        JLabel lbl = new JLabel();
        if (withSumFooter(i)) {
            lbl.setBorder(new LineBorder(Color.GRAY));
        }
        conFooter.gridx++;
        pnlFooter.add(lbl, conFooter);
        mapFooter.put(i, lbl);
        return lbl;
    }
    private JTextFieldPallete getHeaderFilter(int i) {
        if (mapHeader.get(i)!=null) {
            return mapHeader.get(i);
        }
        JTextFieldPallete txt = new JTextFieldPallete();
        txt.setBorder(new LineBorder(Color.GRAY));
        conHeader.insets = new Insets(1,1,1,1);
        conHeader.gridx++;
        pnlHeader.add(txt, conHeader);
        mapHeader.put(i, txt);
        return txt;
    }
    private boolean withSumFooter(int i) {
        if (sumFooter==null) return false;
        return sumFooter.contains(BeanUtil.concat(i,""));
    }

    /** Creates new form GroupedTable */
    public GroupedTable() {
        initComponents();
        tbl.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
            public void columnMarginChanged(ChangeEvent e) {
                setupFooter();
                setupHeader();
            }
            public void columnSelectionChanged(ListSelectionEvent e) {
            }
            public void columnAdded(TableColumnModelEvent e) {
            }
            public void columnMoved(TableColumnModelEvent e) {
            }
            public void columnRemoved(TableColumnModelEvent e) {
            }
        });
        pnlFooter.setVisible(false);
        pnlHeader.setVisible(false);
        setupFooter();
        setupHeader();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        pnlFooter = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl.setName("tbl"); // NOI18N
        jScrollPane1.setViewportView(tbl);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlFooter.setName("pnlFooter"); // NOI18N
        pnlFooter.setPreferredSize(new java.awt.Dimension(100, 25));
        pnlFooter.setLayout(new java.awt.GridBagLayout());
        add(pnlFooter, java.awt.BorderLayout.SOUTH);

        pnlHeader.setName("pnlHeader"); // NOI18N
        pnlHeader.setPreferredSize(new java.awt.Dimension(100, 25));
        pnlHeader.setLayout(new java.awt.GridBagLayout());
        add(pnlHeader, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
