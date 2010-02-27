/*
 * ReadonlyTable.java
 *
 * Created on Aug 27, 2007, 1:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import component.dnd.IComponentDND;
import component.dnd.ComponentTransferHandler;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import service.util.AbstractIBean;
import ui.DynamicAccessForm;
import util.DateUtil;
import util.PanelUtil;
import util.ScriptRunner;

/**
 *
 * @author Budoy Entokwa
 */
public class ReadonlyTable extends JTable implements IComponentDND, IRule {

    private static boolean shownMessage = false;
    
    PopupMenu menu = new PopupMenu();
    MenuItem mnuPrintTable = new MenuItem("Print Table");
    MenuItem mnuPrintRecord = new MenuItem("Print Selected Record");
    MenuItem mnuPrintRecordSubPanel = new MenuItem("Print Selected Record And Sub Records");
    MenuItem mnuSaveTableRecord = new MenuItem("Save to XML");
    MenuItem mnuUseTemplate = new MenuItem("Use Record as Template");
    List recordList;
    Query query;

    public void refresh() {
        if (query!=null) {
            List lst = query.getResultList();
            PanelUtil.setListData(recordList, lst);
        }
    }
    
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
    
    private JTable getMe() {
        return this;
    }

    public ReadonlyTable() {
        this.add(menu);
        menu.add(mnuPrintTable);
        menu.add(mnuPrintRecord);
        menu.add(mnuPrintRecordSubPanel);
        menu.add(mnuSaveTableRecord);
        menu.add(mnuUseTemplate);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    JTable tbl = getMe();
                    menu.show(tbl, e.getX(), e.getY());
                }
                else {
//                    PalleteRuleManager.runOnClick((IRule)e.getSource());
                }
            }
        });
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
//                PalleteRuleManager.runOnLostFocus((IRule)e.getSource());
            }
        });
        mnuPrintTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(getMe(), "Cannot use print menu for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                    return;
                }
                printTable();
            }
        });
        mnuPrintRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(getMe(), "Cannot use print menu for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                    return;
                }
                JTable tbl = getMe();
                int selRow = tbl.getSelectedRowCount();
                if (selRow == 0) {
                    PanelUtil.showMessage(tbl, "No selected record to print.");
                    return;
                }
                printRecord(tbl.getSelectedRow());
            }
        });
        mnuPrintRecordSubPanel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(getMe(), "Cannot use print menu for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                    return;
                }
                JTable tbl = getMe();
                int selRow = tbl.getSelectedRowCount();
                if (selRow == 0) {
                    PanelUtil.showMessage(tbl, "No selected record to print.");
                    return;
                }
                printRecordAndSubRecord(tbl.getSelectedRow());
            }
        });
        mnuSaveTableRecord.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(getMe(), "Cannot use save to xml for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                    return;
                }
                saveTableToXML();
            }
        });
        mnuUseTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(getMe(), "Cannot use template for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                    return;
                }
                JTable tbl = getMe();
                int selRow = tbl.getSelectedRowCount();
                if (selRow == 0) {
                    PanelUtil.showMessage(tbl, "No selected record to use as tempalte.");
                    return;
                }
                if (!shownMessage) {
                    shownMessage = true;
                    PanelUtil.showMessage(getMe(), "This message will only be shown once, please take note that you need to Click Save button in the toolbar to save the template.");
                }
                useTemplateRecord(tbl.getSelectedRow());
            }
        });
        setTransferHandler(new ComponentTransferHandler());
        DynamicAccessForm.initializeComponent(this);
    }

    protected void saveTableToXML() {
        AbstractPanel pnl = PanelUtil.getActivePanel(this);
        JTableHeader header = this.getTableHeader();
        TableColumnModel colmodel = header.getColumnModel();
        int colCount = colmodel.getColumnCount();
        StringBuffer sb = new StringBuffer();
        int rowCount = this.getRowCount();
        sb.append("<XML>");
        for (int k = 0; k < rowCount; k++) {
            sb.append("<ROW>");
            for (int i = 0; i < colCount; i++) {
                String title = getText(this.getValueAt(k, i));
                sb.append("<COL KEY=\"").append(getHeaderValue(i)).append("\">").append(title).append("</COL>");
            }
            sb.append("</ROW>");
        }
        sb.append("</XML>");
        JFileChooser fChooser = new JFileChooser();
        fChooser.setSelectedFile(new File(pnl.getTitle()+".xml"));
        int i = fChooser.showSaveDialog(this);
        if (i==fChooser.APPROVE_OPTION) {
            try {
                java.io.File f = fChooser.getSelectedFile();
                java.io.RandomAccessFile raf = new java.io.RandomAccessFile(f, "rw");
                raf.writeBytes(sb.toString());
                raf.close();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        Logger.getLogger("global").log(Level.INFO, sb.toString());
    }

    private String getHeaderValue(int i) {
        JTableHeader header = this.getTableHeader();
        TableColumnModel colmodel = header.getColumnModel();
        TableColumn col = colmodel.getColumn(i);
        return col.getHeaderValue().toString();
    }

    protected void printTable() {
        AbstractPanel pnl = PanelUtil.getActivePanel(this);
        String str = getHTMLForTable(this, pnl.getTitle());
        GenericPrintDisplayer.printHTML(str);
    }

    private String getText(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Date) {
            return DateUtil.formatDate((Date) obj);
        }
        return obj.toString();
    }

    public void printRecord(int rowSelected) {
        AbstractPanel pnl = PanelUtil.getActivePanel(this);
        String str = getHTMLForRecord(this, rowSelected, pnl.getTitle());
        GenericPrintDisplayer.printHTML(str);
    }

    public void useTemplateRecord(int rowSelected) {
        if (PanelUtil.inMainPanel(this)) {
            AbstractPanel pnl = PanelUtil.getActivePanel(this);
            List lst = pnl.getMasterList();
            AbstractIBean bean = (AbstractIBean) lst.get(rowSelected);
            bean.clone();
            bean.clearId();
            lst.add(bean);
            pnl.getMasterTable().setRowSelectionInterval(lst.size()-1, lst.size()-1);
        }
        else {
            AbstractSubPanel pnl = (AbstractSubPanel) PanelUtil.getMySubPanel(this);
            List lst = pnl.getMasterList();
            AbstractIBean bean = (AbstractIBean) lst.get(rowSelected);
            bean.clone();
            bean.clearId();
            lst.add(bean);
            pnl.getMasterTable().setRowSelectionInterval(lst.size()-1, lst.size()-1);
        }
    }

    public void printRecordAndSubRecord(int rowSelected) {
        AbstractPanel pnl = PanelUtil.getActivePanel(this);
        StringBuffer sb = new StringBuffer();
        sb.append("<HTML><BODY>");
        sb.append(getHTMLForRecord(this, rowSelected, pnl.getTitle()));

        List<AbstractSubPanel> lst = pnl.getSubPanels(pnl);
        for (AbstractSubPanel panel : lst) {
            JTable tbl = panel.getMasterTable();
            String caption = panel.getToolTipText();
            sb.append(getHTMLForTable(tbl, caption));
        }

        sb.append("</BODY></HTML>");
        GenericPrintDisplayer.printHTML(sb.toString());
    }

    public void printSelectedRecordAndSubRecord(int... indexes) {
        printRecordAndSubRecord(this.getSelectedRow(), indexes);
    }
    
    public void printRecordAndSubRecord(int rowSelected, int... indexes) {
        AbstractPanel pnl = PanelUtil.getActivePanel(this);
        StringBuffer sb = new StringBuffer();
        sb.append("<HTML><BODY>");
        sb.append(getHTMLForRecord(this, rowSelected, pnl.getTitle()));

        int counter = 0;
        List<AbstractSubPanel> lst = pnl.getSubPanels(pnl);
        for (AbstractSubPanel panel : lst) {
            //check if included in array
            if (!indexExist(indexes, counter++)) continue;
            
            JTable tbl = panel.getMasterTable();
            String caption = panel.getToolTipText();
            sb.append(getHTMLForTable(tbl, caption));
        }

        sb.append("</BODY></HTML>");
        GenericPrintDisplayer.printHTML(sb.toString());
    }

    private boolean indexExist(int[] indexes, int index) {
        for (int i:indexes) {
            if (i==index) return true;
        }
        return false;
    }
    
    public String getHTMLForRecord(JTable tbl, int rowSelected, String caption) {
        ReadonlyTableData data = new ReadonlyTableData();
        data.setTitle(caption);
        data.setHeaders(new ArrayList<String>());
        data.setValues(new ArrayList<List>());
        
        JTableHeader header = tbl.getTableHeader();
        TableColumnModel colmodel = header.getColumnModel();
        int colCount = colmodel.getColumnCount();
        for (int i = 0; i < colCount; i++) {
            TableColumn col = colmodel.getColumn(i);
            String title = getText(col.getHeaderValue());
            data.getHeaders().add(title);
        }
        List<String> lst = new ArrayList<String>();
        data.getValues().add(lst);
        for (int i = 0; i < colCount; i++) {
            String title = getText(this.getValueAt(rowSelected, i));
            lst.add(title);
        }
        return ScriptRunner.runFreeMarker("PrintTableRecord", data).toString();
    }

    public String getHTMLForTable(JTable tbl, String caption) {
        ReadonlyTableData data = new ReadonlyTableData();
        data.setTitle(caption);
        data.setHeaders(new ArrayList<String>());
        data.setValues(new ArrayList<List>());
        
        JTableHeader header = tbl.getTableHeader();
        TableColumnModel colmodel = header.getColumnModel();
        int colCount = colmodel.getColumnCount();
        for (int i = 0; i < colCount; i++) {
            TableColumn col = colmodel.getColumn(i);
            String title = getText(col.getHeaderValue());
            data.getHeaders().add(title);
        }
        int rowCount = tbl.getRowCount();
        for (int k = 0; k < rowCount; k++) {
            List<String> lst = new ArrayList<String>();
            data.getValues().add(lst);
            for (int i = 0; i < colCount; i++) {
                String title = getText(tbl.getValueAt(k, i));
                lst.add(title);
            }
        }
        return ScriptRunner.runFreeMarker("PrintTableRecord", data).toString();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Object getDataTransfer() throws Exception {
        if (recordList==null) {
            throw new Exception("recordList variable is empty");
        }
        //This will produce multiple records
        List lst = new ArrayList();
        int rowCount = this.getSelectedRowCount();
        if (rowCount>0) {
            int[] rows = this.getSelectedRows();
            for (int i=0; i<rows.length; i++) {
                int rowIndex = rows[i];
                lst.add(recordList.get(rowIndex));
            }
        }
        return lst;
    }

    public void processTransferredData(Object obj, JComponent source, JComponent target) {
        throw new UnsupportedOperationException("This method must be extended.");
    }
    
    public List getSelectedRecords() {
        List lst = new ArrayList();
        int rows[] = this.getSelectedRows();
        for (int row : rows) {
            lst.add(this.recordList.get(row));
        }
        return lst;
    }

    public void removeSelectedRecords() {
        int rows[] = this.getSelectedRows();
        for (int row : rows) {
            this.recordList.remove(row);
        }
    }
    
    public Object getSelectedRecord() {
        if (this.getSelectedRowCount()==0) return null;
        int row = this.getSelectedRow();
        return this.recordList.get(row);
    }

    private void runRuleManager() {
        PalleteRuleManager.useRule(this);
    }
    
    private String allowedUserId;
    private String allowedGroup;
    private String allowedDuty;

    private String onClickRule;
    private String onValueChangeRule;
    private String onLostFocusRule;

    public String getAllowedDuty() {
        return allowedDuty;
    }

    public void setAllowedDuty(String allowedDuty) {
        this.allowedDuty = allowedDuty;
    }

    public String getAllowedGroup() {
        return allowedGroup;
    }

    public void setAllowedGroup(String allowedGroup) {
        this.allowedGroup = allowedGroup;
    }

    public String getAllowedUserId() {
        return allowedUserId;
    }

    public void setAllowedUserId(String allowedUserId) {
        this.allowedUserId = allowedUserId;
    }

    public String getRuleForAllowed() {
        return PalleteRuleManager.getRuleForAllowed(this);
    }
    
    public String getOnClickRule() {
        return onClickRule;
    }

    public void setOnClickRule(String onClickRule) {
        this.onClickRule = onClickRule;
    }

    public String getOnLostFocusRule() {
        return onLostFocusRule;
    }

    public void setOnLostFocusRule(String onLostFocusRule) {
        this.onLostFocusRule = onLostFocusRule;
    }

    public String getOnValueChangeRule() {
        return onValueChangeRule;
    }

    public void setOnValueChangeRule(String onValueChangeRule) {
        this.onValueChangeRule = onValueChangeRule;
    }    
}
