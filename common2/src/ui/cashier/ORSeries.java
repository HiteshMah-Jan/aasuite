/*
 * ORSeries.java
 *
 * Created on March 27, 2009, 8:36 PM
 */

package ui.cashier;

import bean.accounting.CashierDailyBooklet;
import constants.UserInfo;
import java.util.List;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author  alex
 */
public class ORSeries extends javax.swing.JPanel {

    /** Creates new form ORSeries */
    public ORSeries() {
        initComponents();
        PanelUtil.updateColor(jPanel1);
        try {
            System.out.println("OR SERIES INIT SERVER CALL");
            String user = UserInfo.getUserName();
            CashierDailyBooklet b = (CashierDailyBooklet) DBClient.getFirstRecord("SELECT a FROM CashierDailyBooklet a WHERE a.cashier='"+user+"' ORDER BY a.seq DESC");
            pnlSeries.setBean(b);
            updateSeries();
        }
        catch (Exception e) {
        }
    }
    
    public void updateSeries() {
        int nextOR = new CashierDailyBooklet().extractNextOR("N");
        int nextOR2 = new CashierDailyBooklet().extractNextOR("A");
        btnUpdateSeries.setText("["+nextOR+" - "+nextOR2+"]");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSeries = new component.BeanPanelPallete();
        jPanel1 = new javax.swing.JPanel();
        btnUpdateSeries = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlSeries.setBeanName("CashierDailyBookletExt");
        pnlSeries.setName("pnlSeries"); // NOI18N
        pnlSeries.setShowImage(false);

        javax.swing.GroupLayout pnlSeriesLayout = new javax.swing.GroupLayout(pnlSeries);
        pnlSeries.setLayout(pnlSeriesLayout);
        pnlSeriesLayout.setHorizontalGroup(
            pnlSeriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );
        pnlSeriesLayout.setVerticalGroup(
            pnlSeriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        add(pnlSeries, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N

        btnUpdateSeries.setText("Edit Series");
        btnUpdateSeries.setName("btnUpdateSeries"); // NOI18N
        btnUpdateSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSeriesActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateSeries);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

private void btnUpdateSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSeriesActionPerformed
    String start = PanelUtil.showPromptDefaultMessage(btnUpdateSeries, "Start OR", "");
    if (start==null || start.trim().isEmpty()) {
        PanelUtil.showMessage(this, "Cancelled series update.");
        return;
    }
    if (PanelUtil.getIntValue(start)==0) {
        PanelUtil.showMessage(this, "Cannot accept 0 as start series.");
        return;
    }
    String end = PanelUtil.showPromptDefaultMessage(btnUpdateSeries, "End OR", "");
    if (end==null || end.trim().isEmpty()) {
        PanelUtil.showMessage(this, "Cancelled series update.");
        return;
    }
    if (PanelUtil.getIntValue(end)==0) {
        PanelUtil.showMessage(this, "Cannot accept 0 as end series.");
        return;
    }
    String startM = PanelUtil.showPromptDefaultMessage(btnUpdateSeries, "Start MISC OR", "");
    if (startM==null || startM.trim().isEmpty()) {
        PanelUtil.showMessage(this, "Cancelled series update.");
        return;
    }
    if (PanelUtil.getIntValue(startM)==0) {
        PanelUtil.showMessage(this, "Cannot accept 0 as start series.");
        return;
    }
    String endM = PanelUtil.showPromptDefaultMessage(btnUpdateSeries, "End MISC OR", "");
    if (endM==null || endM.trim().isEmpty()) {
        PanelUtil.showMessage(this, "Cancelled series update.");
        return;
    }
    if (PanelUtil.getIntValue(endM)==0) {
        PanelUtil.showMessage(this, "Cannot accept 0 as end series.");
        return;
    }
    int istart = PanelUtil.getIntValue(start);
    int iend = PanelUtil.getIntValue(end);
    int istartM = PanelUtil.getIntValue(startM);
    int iendM = PanelUtil.getIntValue(endM);
    CashierDailyBooklet b = CashierDailyBooklet.logBooklet(istart, iend, istartM, iendM);
    b.save();
    pnlSeries.setBean(b);
    int or = new CashierDailyBooklet().extractNextOR("N");
    btnUpdateSeries.setText("Edit Series ["+or+"]");
}//GEN-LAST:event_btnUpdateSeriesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdateSeries;
    private javax.swing.JPanel jPanel1;
    private component.BeanPanelPallete pnlSeries;
    // End of variables declaration//GEN-END:variables

}
