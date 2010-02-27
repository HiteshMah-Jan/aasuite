/*
 * TestDBForm.java
 *
 * Created on April 4, 2009, 2:02 PM
 */

package ui;

import java.util.ArrayList;
import java.util.List;
import util.DBClient;
import util.PanelUtil;
import util.PerfUtil;

/**
 *
 * @author  alex
 */
public class TestDBForm extends javax.swing.JPanel {
    private static boolean stop;
    
    /** Creates new form TestDBForm */
    public TestDBForm() {
        initComponents();
        DBClient.collectSQL = true;
        PanelUtil.showMessage(this, "Please do your work and this will collect the SQL you issued.");
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
        txtSQL = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnClearLog = new javax.swing.JButton();
        btnStartCollect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboTestCount = new javax.swing.JComboBox();
        btnTest = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtSQL.setColumns(20);
        txtSQL.setRows(5);
        txtSQL.setName("txtSQL"); // NOI18N
        jScrollPane1.setViewportView(txtSQL);

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(TestDBForm.class);
        btnClearLog.setText(resourceMap.getString("btnClearLog.text")); // NOI18N
        btnClearLog.setName("btnClearLog"); // NOI18N
        btnClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLogActionPerformed(evt);
            }
        });
        jPanel1.add(btnClearLog);

        btnStartCollect.setText(resourceMap.getString("btnStartCollect.text")); // NOI18N
        btnStartCollect.setName("btnStartCollect"); // NOI18N
        btnStartCollect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartCollectActionPerformed(evt);
            }
        });
        jPanel1.add(btnStartCollect);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        cboTestCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "50", "100" }));
        cboTestCount.setName("cboTestCount"); // NOI18N
        jPanel1.add(cboTestCount);

        btnTest.setText(resourceMap.getString("btnTest.text")); // NOI18N
        btnTest.setName("btnTest"); // NOI18N
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });
        jPanel1.add(btnTest);

        btnStop.setText(resourceMap.getString("btnStop.text")); // NOI18N
        btnStop.setName("btnStop"); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        jPanel1.add(btnStop);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtLog.setColumns(20);
        txtLog.setRows(5);
        txtLog.setName("txtLog"); // NOI18N
        jScrollPane2.setViewportView(txtLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnStartCollectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartCollectActionPerformed
    StringBuffer sb = new StringBuffer();
    List<String> lst = DBClient.collectedSQL;
    for (String sql : lst) {
        sb.append(sql).append("\n");
    }
    txtSQL.setText(sb.toString());
    DBClient.collectedSQL.clear();
}//GEN-LAST:event_btnStartCollectActionPerformed

private void testThread() {
    btnTest.setEnabled(false);
    String txt = txtSQL.getText();
    List<String> sqlList = new ArrayList();
    String[] arr = txt.split("\n");
    for (String sql : arr) {
        sqlList.add(sql);
    }
    PerfUtil p = new PerfUtil(Thread.currentThread().getName());
    p.start();
    StringBuffer sb = new StringBuffer();
    DBClient.batchQueryNoCache(sqlList);
    btnTest.setEnabled(false);
    DBClient.batchQueryNoCache(sqlList);
    btnTest.setEnabled(false);
    sb.append(p.spanMessage()).append("\n");

    for (String sql : arr) {
        if (stop) return;
        btnTest.setEnabled(false);
        DBClient.getList(sql);
        Thread.yield();
    }
    sb.append(p.spanMessageComplete());
    addLog(sb.toString());
    btnTest.setEnabled(true);
}

private synchronized void addLog(String txt) {
    String tmp = txtLog.getText();
    txtLog.setText(tmp+"\n\n"+txt);
}

private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
    stop = false;
    PanelUtil.showMessage(btnTest, "The process will use batch then do single sql sending.");
    int count = PanelUtil.getIntValue(cboTestCount.getSelectedItem().toString());
    btnTest.setEnabled(false);
    for (int i = 1; i < count+1; i++) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                testThread();
            }
        });
        t.setName("Virtual Computer "+i);
        t.start();
    }
}//GEN-LAST:event_btnTestActionPerformed

private void btnClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLogActionPerformed
    txtLog.setText("");
}//GEN-LAST:event_btnClearLogActionPerformed

private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
    stop = true;
}//GEN-LAST:event_btnStopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearLog;
    private javax.swing.JButton btnStartCollect;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnTest;
    private javax.swing.JComboBox cboTestCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextArea txtSQL;
    // End of variables declaration//GEN-END:variables

}
