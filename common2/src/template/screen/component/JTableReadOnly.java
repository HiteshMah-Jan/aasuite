/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.screen.component;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.jdic.desktop.Desktop;

import rule.BusinessRuleWrapper;
import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.DataUtil;
import util.PanelUtil;

import common2.Common2View;

/**
 *
 * @author Entokwaa
 */
public class JTableReadOnly extends JTable {

    private String editableCol;
    PopupMenu menu = new PopupMenu();
    MenuItem mnuShowReport = new MenuItem("Show Report");
    MenuItem mnuExportToExcel = new MenuItem("Export To Excel");
    static JDialog dlg = null;
    static JPanel dlgPanel = new JPanel(new GridLayout());
    BusinessRuleWrapper rule;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    public Class beanClass;
    public List lst;
    Map<String, Class> mapRenderer = new HashMap();
    
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer ren = super.getCellRenderer(row, column);
        if (ren.getClass().getName().contains("DateRend")) {
        	TableCellRenderer obj = getNewRenderer("Date");
        	if (obj==null) return new DateRenderer();
        	return obj;
        }
        if (ren.getClass().getName().contains("Double")) {
        	TableCellRenderer obj = getNewRenderer("Double");
        	if (obj==null) return new DoubleRenderer();
        	return obj;
        }
        if (ren.getClass().getName().contains("Number")) {
        	TableCellRenderer obj = getNewRenderer("Integer");
        	if (obj!=null) return obj;
        }
        return ren;
    }

    private TableCellRenderer getNewRenderer(String str) {
    	Class cls = mapRenderer.get(str);
    	if (cls!=null) {
    		try {
				return (TableCellRenderer) cls.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    public void setNewRenderer(String key, Class cls) {
    	mapRenderer.put(key, cls);
    }
    
    public BusinessRuleWrapper getRule() {
        return rule;
    }

    public void setRule(BusinessRuleWrapper rule) {
        this.rule = rule;
    }

    public String getEditableCol() {
        return editableCol;
    }

    public void setEditableCol(String editableCol) {
    	if (editableCol==null || editableCol.trim().isEmpty()) return;
        this.editableCol = BeanUtil.concat(",",editableCol,",");
    }
    protected ListModel listModel = new AbstractListModel() {

        private static final long serialVersionUID = 1L;

        public int getSize() {
            int rowCount = getModel().getRowCount();
            if (rowCount == 0) {
                return 1;
            }
            return rowCount;
        }

        public Object getElementAt(int index) {
            return index + 1;
        }
    };
    protected JList jlist;
    int rowWidth = 20;

    public int getRowNumberWidth() {
        return rowWidth;
    }

    public JTableReadOnly() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setup();
                ToolTipManager.sharedInstance().unregisterComponent(getMe());
                ToolTipManager.sharedInstance().unregisterComponent(getMe().getTableHeader());
            }
        });
    }

    public JTableReadOnly(int rowWidth) {
        this();
        this.rowWidth = rowWidth;
    }

    private void setup() {
        if (dlg == null) {
            if (Common2View.mainView != null) {
                dlg = new JDialog(Common2View.mainView.getFrame());
            } else {
                dlg = new JDialog(new JFrame());
            }
            dlg.setModal(true);
            dlg.setLayout(new GridLayout());
            dlg.setTitle("Table Report Dialog");
            dlg.getContentPane().add(dlgPanel);
        }
        this.add(menu);
        menu.add(mnuShowReport);
        menu.add(mnuExportToExcel);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    JTable tbl = getMe();
                    menu.show(tbl, e.getX(), e.getY());
                } else {
//                    PalleteRuleManager.runOnClick((IRule)e.getSource());
                }
            }
        });
        mnuShowReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object bean = ELProperty.create("${selectedElement}").getValue(getMe());
                if (bean == null) {
                    PanelUtil.showError(getMe(), "Please select a record.");
                    return;
                }
                if (beanClass==null) beanClass=bean.getClass();
                if (lst!=null) {
                    AbstractReportTemplate.getInstance(beanClass).showReportToPanel(lst, dlgPanel);
                }
                else {
                    AbstractReportTemplate.getInstance(beanClass).showReportToPanel(dlgPanel, null);
                }
                dlg.pack();
                dlg.setVisible(true);
            }
        });
        mnuExportToExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JTable tbl = getMe();
            	int rows = tbl.getRowCount();
            	int cols = tbl.getColumnCount();
            	StringBuffer sb = new StringBuffer();
        		for (int j=0; j<cols; j++) {
        			String txt = tbl.getTableHeader().getColumnModel().getColumn(j).getHeaderValue().toString();
        			sb.append(txt).append("\t");
        		}
    			sb.append("\n");
            	for (int i=0; i<rows; i++) {
            		for (int j=0; j<cols; j++) {
            			sb.append(tbl.getValueAt(i, j)).append("\t");
            		}
        			sb.append("\n");
            	}
            	if (sb.length()>1) {
            		File f = PanelUtil.showSelectFile("Save File To", "export.xls", getMe());
            		try {
						RandomAccessFile raf = new RandomAccessFile(f, "rw");
						raf.write(sb.toString().getBytes());
						raf.close();
						
						Desktop.open(f);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
            	}
            }
        });
    }

    private JTable getMe() {
        return this;
    }

    StringBuffer sb = new StringBuffer();

    @Override
    public boolean isCellEditable(int row, int column) {
        if (editableCol == null || editableCol.isEmpty()) {
            return false;
        }
        sb.setLength(0);
        sb.append(",").append(column).append(",");
        if (editableCol.contains(sb.toString())) { 
            return true;
        }
        return false;
    }

    public static class RowHeaderRenderer1 extends JButton implements ListCellRenderer {

        private static final long serialVersionUID = 1L;
        JTable table;

        public RowHeaderRenderer1(JTable table) {
            this.table = table;
            //setFont(new Font("Dialog", 0, 11));
            setMargin(new Insets(0, 0, 0, 0));
        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus) {
            list.setBackground(getBackground());
            this.setText(value.toString());
            return this;
        }

        public Component getListCellRendererComponent(JList list, Object value,
                boolean isSelected, boolean hasFocus) {
            list.setBackground(getBackground());
            this.setText(value.toString());
            return this;
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (jlist != null) {
            jlist.updateUI();
        }
    }

    public static class DateRenderer extends DefaultTableCellRenderer {
        static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        public DateRenderer() {
            super();
        }
        public void setValue(Object value) {
            setText((value == null) ? "" : formatter.format(value));
        }
    }

    public static class NumberRenderer extends DefaultTableCellRenderer.UIResource {
        public NumberRenderer() {
            super();
            setHorizontalAlignment(JLabel.RIGHT);
        }
    }

    public static class DoubleRenderer extends NumberRenderer {
        public DoubleRenderer() {
            super();
        }
        public void setValue(Object value) {
            setText((value == null) ? "0" : DataUtil.getCurrencyFormat(value.toString()));
        }
    }
    
    public List<TableColumn> columns = new ArrayList();
    private void initCol() {
    	if (columns.isEmpty()) {
    		for (int i=0; i<getColumnCount(); i++) {
    			TableColumn column = getColumnModel().getColumn(i);
    			columns.add(column);
    		}
    	}
    }

    public void hideColumn(int col) {
    	initCol();
		TableColumn column = columns.get(col);
		getColumnModel().removeColumn(column);
    }
    
    public void showAllColumns() {
    	initCol();
    	for (TableColumn col:columns) {
    		getColumnModel().removeColumn(col);
    	}
    	for (TableColumn col:columns) {
    		getColumnModel().addColumn(col);
    	}
    }
}
