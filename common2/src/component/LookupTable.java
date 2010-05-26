/*
 * LookupTable.java
 *
 * Created on July 27, 2008, 6:27 PM
 */

package component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.observablecollections.ObservableCollections;

import service.util.AbstractIBean;
import template.screen.TemplateParserUtil;
import template.screen.component.JTableReadOnly;
import util.BeanUtil;
import util.ClientCache;
import util.DBClient;
import util.Log;
import util.PanelUtil;
import util.PerfUtil;

/**
 *
 * @author  Entokwaa
 */
public class LookupTable extends javax.swing.JPanel {
    String bean;
    String title;
    String[] columns;
    public List list = ObservableCollections.observableList(new ArrayList());
    List origList = new ArrayList();
    public JTextFieldPallete txt = new JTextFieldPallete();
    public int pageSize = 200;
    public int moreField = 0;
    
    public AbstractIBean getFromBeans(String code) {
        List<AbstractIBean> lst = list;
        for (AbstractIBean ib : lst) {
            String c = ib.keyVal().toString();
            if (code.equals(c)) {
            	return ib;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        return txt.getText()==null || txt.getText().isEmpty() || txt.getText().equals("0");
    }
    
    public String getText() {
        return txt.getText();
    }
    
    public void setText(String txtStr) {
        txt.setText(txtStr);
    }
    
    public static void main(String[] args) {
        JTextFieldPallete f = new JTextFieldPallete();
    }
    
    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** Creates new form LookupTable */
    public LookupTable() {
        initComponents();
        txtPageSize.setText(BeanUtil.concat(pageSize,""));
    }
    
    private List getList(String bean, int more, int page) {
        List l = null;
    	Class cls = PanelUtil.getBeanClass(bean);
    	try {
			AbstractIBean b = (AbstractIBean) cls.newInstance();
	        String sql = PanelUtil.constructSql(PanelUtil.getBeanClass(bean));
	        if (b.cacheClient()) {
		        String id = sql.trim();
		        if (more > 0) {
		        	id = BeanUtil.concat(sql.trim(),"-",more);
		        }
		        l = (List) ClientCache.getCache(id.toLowerCase());
		        if (l==null) {
		        	l = (List) ClientCache.resetCache(id.toLowerCase(), DBClient.getListServerCache(sql.trim(), more, page));
		        }
	        }
	        else {
		        l = DBClient.getList(sql, more, page);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
    }
    
    private List getList(String bean, String sql) {
        List l = null;
    	Class cls = PanelUtil.getBeanClass(bean);
    	try {
			AbstractIBean b = (AbstractIBean) cls.newInstance();
			if (b.cacheClient()) {
		        l = (List) ClientCache.getCache(sql.trim().toLowerCase());
		        if (l==null) {
		        	l = (List) ClientCache.resetCache(sql.trim().toLowerCase(), DBClient.getListServerCache(sql));
		        }
			}
			else {
		        l = DBClient.getList(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
    }

    boolean firstCall = true;
    public void showSearch() {
        if (firstCall) {
            list.addAll(getList(bean, moreField, pageSize));
            origList.clear();
            origList.addAll(list);
            generateCriteria();
            dlgLookup.setTitle(PanelUtil.getTitle(bean));
            
            BindingGroup bindingGroup = new BindingGroup();
            TemplateParserUtil.bindPopTable(bindingGroup, list, tblRecords, PanelUtil.getBeanClass(bean), columns);
            bindingGroup.bind();
            tblRecords.addMouseListener(new MouseAdapter() { 
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount()==2) {
                        useSelectedRecord();
                    }
                }
            });
            tblRecords.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                        useSelectedRecord();
                    }
                    else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                        if (firstField!=null) firstField.requestFocus();
                    }
                }
            });
            dlgLookup.pack();
        }
        firstCall = false;
        if (firstField!=null) firstField.requestFocus();
        dlgLookup.setVisible(true);
    }
    
    private List<JTextFieldPallete> lst = new ArrayList<JTextFieldPallete>();
    private JTextFieldPallete firstField;
    JTextFieldPallete txtCriteria;
    private void generateCriteria() {
        txtCriteria = new JTextFieldPallete();
        Dimension dim = txtCriteria.getPreferredSize();
        dim.width = 300;
        txtCriteria.setPreferredSize(dim);
        txtCriteria.setMinimumSize(dim);
        txtCriteria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                keyHelper(e);
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    searchFilter();
                }
            }
        });
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtCriteria.setText("");
            }
        });
        pnlCriteria.setLayout(new FlowLayout());
        pnlCriteria.add(new JLabel("Search: "));
        pnlCriteria.add(txtCriteria);
        pnlCriteria.add(btnClear);
    }

    private void searchFilter() {
        Class cls = PanelUtil.getBeanClass(bean);
        List l = null;
        try {
            AbstractIBean ibean = (AbstractIBean) cls.newInstance();
            String isql = ibean.popupSearch(txtCriteria.getText());
            lblStatus.setText("Searching please wait...");
            l = getList(bean, isql);
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        lblStatus.setText("");
        if (list!=null && list.size()>0) {
            list.clear();
        }
        if (l!=null && l.size()>0) {
            list.addAll(l);
        }
    }
    
    private void generateCriteriaOld() {
        pnlCriteria.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(0, 8, 0, 0);
        gridBagConstraints1.ipadx = 5;

        java.awt.GridBagConstraints gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(0, 2, 0, 0);
        gridBagConstraints2.ipadx = 75;

        int colCount = columns.length;
        for (int i = 0; i < colCount; i++) {
            String col = PanelUtil.getLabel(columns[i]);
            JTextFieldPallete txtCriteria = new JTextFieldPallete();
            txtCriteria.setName(columns[i]);
            lst.add(txtCriteria);
            if (i==0) firstField = txtCriteria;
            pnlCriteria.add(new JLabel(col), gridBagConstraints1);
            pnlCriteria.add(txtCriteria, gridBagConstraints2);
            
            txtCriteria.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    keyHelper(e);
                    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                filter();
                            }
                        });
                        t.start();
//                        SwingUtilities.invokeLater(new Runnable() {
//                            public void run() {
//                                filter();
//                            }
//                        });
                    }
                }
            });
        }
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearCriteria();
            }
        });
        gridBagConstraints1.fill = gridBagConstraints1.VERTICAL;
        pnlCriteria.add(btnClear, gridBagConstraints1);
    }
    
    private void keyHelper(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            tblRecords.requestFocus();
            tblRecords.setRowSelectionInterval(0, 0);
        }
        else if (e.getKeyCode()==KeyEvent.VK_HOME) {
            tblRecords.setRowSelectionInterval(0, 0);
            PanelUtil.scroll(tblRecords, 0);
        }
        else if (e.getKeyCode()==KeyEvent.VK_END) {
            int row = tblRecords.getRowCount()-1;
            tblRecords.setRowSelectionInterval(row, row);
            PanelUtil.scroll(tblRecords, row);
        }
        else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            dlgLookup.setVisible(false);
        }
    }
    
    private void clearCriteria() {
        for (JTextFieldPallete f : lst) {
            f.setText("");
        }
        filter();
    }
    int chunk = 10;
    List<Boolean> ready = new ArrayList<Boolean>();
    private void filter() {
        ready.clear();
        chunk = origList.size()/500;
        if (chunk==0) chunk = 1;
        for (int i = 0; i < chunk; i++) {
            ready.add(true);
        }
        lblStatus.setText("Filtering... Please wait.");
        int size = origList.size();
        int total = size/chunk;
        Log.out("TOTAL == ",total);
        for (int i = 0; i < chunk; i++) {
            if (i+1==chunk) {
                threadFilter(i, i*total, size);
            }
            else {
                threadFilter(i, i*total, (i+1)*total);
            }
        }
        iwait = 0;
        waitWhenReady();
        PerfUtil perf = new PerfUtil("LIST ");
        perf.start();
        List tmp = new ArrayList();
        list.clear();
        for (int i = 0; i < origList.size(); i++) {
            AbstractIBean ibean = (AbstractIBean) origList.get(i);
            if (ibean.includeSearch) {
                tmp.add(ibean);
            }
        }
        list.addAll(tmp);
        tblRecords.updateUI();
        lblStatus.setText("");
        perf.printSpan();
    }
    
    int iwait = 0;
    private void waitWhenReady() {
        for (boolean b : ready) {
            if (!b) {
                try {
                    Thread.sleep(500);
                    Log.out("WAITING....",(++iwait));
                    waitWhenReady();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(LookupTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void threadFilter(final int flag, final int start, final int end) {
        ready.set(flag, false);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                PerfUtil perf = new PerfUtil("FILTER CHUNK READY ");
                perf.start();
                int col = lst.size();
                int sl = 0;
                for (int i = start; i < end && i<origList.size(); i++) {
                    if (sl++ == 50) {
                        Thread.yield();
                        sl = 0;
                    }
                    AbstractIBean ibean = (AbstractIBean) origList.get(i);
                    ibean.includeSearch = true;
                    for (int j = 0; j < col; j++) {
                        if (noFilter(lst.get(j))) continue;

                        if (!passedFilter(ibean, lst.get(j))) {
                            ibean.includeSearch = false;
                            break;
                        }
                    }
                }
                ready.set(flag, true);
                perf.printSpan();
            }
        });
        t.start();
    }
    
    private boolean noFilter(JTextFieldPallete pal) {
        String text = pal.getText();
        return (text==null || text.isEmpty());
    }
    
    Map<String, BeanProperty> map = new HashMap<String, BeanProperty>();
    private boolean passedFilter(AbstractIBean obj, JTextFieldPallete pal) {
        String text = pal.getText();
        if (text==null || text.isEmpty()) {
            return true;
        }        
        else {
            BeanProperty prop = null;
            if (map.containsKey(pal.getName())) {
                prop = map.get(pal.getName());
            }
            else {
                prop = BeanProperty.create(pal.getName());
                map.put(pal.getName(), prop);
            }
            Object tmp = prop.getValue(obj);
            if (tmp==null || tmp.toString()==null || tmp.toString().trim().isEmpty()) return false;
            return (tmp.toString().toUpperCase().startsWith(text));
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPageSize = new JTextFieldPallete();
        btnRefresh = new javax.swing.JButton();
        btnMoreRecords = new javax.swing.JButton();
        dlgLookup = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecords = new JTableReadOnly(35);
        jPanel1 = new javax.swing.JPanel();
        btnSelectRecord = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        pnlCriteria = new javax.swing.JPanel();
        btnLookup = new component.JButtonPallete();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(LookupTable.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N

        txtPageSize.setText(resourceMap.getString("txtPageSize.text")); // NOI18N
        txtPageSize.setPreferredSize(new java.awt.Dimension(40, 20));
        txtPageSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPageSizeActionPerformed(evt);
            }
        });
        txtPageSize.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPageSizeFocusLost(evt);
            }
        });

        btnRefresh.setText(resourceMap.getString("btnRefresh.text")); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnMoreRecords.setText(resourceMap.getString("btnMoreRecords.text")); // NOI18N
        btnMoreRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreRecordsActionPerformed(evt);
            }
        });

        dlgLookup.setTitle("Lookup"); // NOI18N
        try {
            dlgLookup.setAlwaysOnTop(true);
        }
        catch (Exception e) {
        }
        dlgLookup.setMinimumSize(new java.awt.Dimension(450, 250));
        dlgLookup.setModal(true);

        tblRecords.setAutoCreateRowSorter(true);
        tblRecords.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblRecordsKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblRecords);

        btnSelectRecord.setText("Use Selected Record"); // NOI18N
        btnSelectRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectRecordActionPerformed(evt);
            }
        });
        jPanel1.add(btnSelectRecord);

        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        jPanel1.add(lblStatus);

        pnlCriteria.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout dlgLookupLayout = new javax.swing.GroupLayout(dlgLookup.getContentPane());
        dlgLookup.getContentPane().setLayout(dlgLookupLayout);
        dlgLookupLayout.setHorizontalGroup(
            dlgLookupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgLookupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgLookupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCriteria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
                .addContainerGap())
        );
        dlgLookupLayout.setVerticalGroup(
            dlgLookupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgLookupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setLayout(new java.awt.GridLayout(1, 0));

        btnLookup.setText("..."); // NOI18N
        btnLookup.setIconTextGap(0);
        btnLookup.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLookup.setName("btnLookup"); // NOI18N
        btnLookup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupActionPerformed(evt);
            }
        });
        add(btnLookup);
    }// </editor-fold>//GEN-END:initComponents

private void tblRecordsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRecordsKeyTyped
    if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
//        useSelectedRecord();
    }
}//GEN-LAST:event_tblRecordsKeyTyped

private void btnLookupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupActionPerformed
//    show(title, bean, columns);
}//GEN-LAST:event_btnLookupActionPerformed

private void btnSelectRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectRecordActionPerformed
    if (tblRecords.getSelectedRowCount()==0) {
        PanelUtil.showError(btnSelectRecord, "Please select a record");
        return;
    }
    useSelectedRecord();
}//GEN-LAST:event_btnSelectRecordActionPerformed

private void btnMoreRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreRecordsActionPerformed
    lblStatus.setText("SEARCHING...");
    moreField += pageSize;
    List l = getList(bean, moreField, pageSize);
    list.addAll(l);
    origList.addAll(l);
    tblRecords.updateUI();
    lblStatus.setText("");    
}//GEN-LAST:event_btnMoreRecordsActionPerformed

private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
    lblStatus.setText("SEARCHING...");
    clearCriteria();
    moreField = 0;
    List l = getList(bean, moreField, pageSize);
    list.clear();
    list.addAll(l);
    origList.clear();
    origList.addAll(l);
    tblRecords.updateUI();
    lblStatus.setText("");    
}//GEN-LAST:event_btnRefreshActionPerformed

private void txtPageSizeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPageSizeFocusLost
    try {
        pageSize = Integer.parseInt(txtPageSize.getText());
    }
    catch (Exception e) {
        txtPageSize.setText(BeanUtil.concat(pageSize,""));
    }
}//GEN-LAST:event_txtPageSizeFocusLost

private void txtPageSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPageSizeActionPerformed
    pageSize = Integer.parseInt(txtPageSize.getText());
    btnRefreshActionPerformed(null);
}//GEN-LAST:event_txtPageSizeActionPerformed
 
private void useSelectedRecord() {
    if (tblRecords.getSelectedRowCount()==0) {
        return;
    }
    AbstractIBean b = PanelUtil.getSelectedRecord(tblRecords);
    Object obj = b.keyVal();
    txt.setText(obj.toString());
    dlgLookup.setVisible(false);
    PanelUtil.showMessageToScreen("");
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.JButtonPallete btnLookup;
    private javax.swing.JButton btnMoreRecords;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSelectRecord;
    public javax.swing.JDialog dlgLookup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlCriteria;
    private javax.swing.JTable tblRecords;
    private javax.swing.JTextField txtPageSize;
    // End of variables declaration//GEN-END:variables
    
}
