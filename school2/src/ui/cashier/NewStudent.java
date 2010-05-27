/*
 * NewStudent.java
 *
 * Created on March 25, 2009, 6:02 AM
 */

package ui.cashier;

import bean.Admission;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import springbean.AAAConfig;
import template.report.AbstractReportTemplate;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author  alex
 */
public class NewStudent extends OldStudent {

    @Override
    public void assessStudent() {
        Admission ad = (Admission) pnlAdmission.getBean();
        if (ad==null || ad.isEmptyKey()) {
            PanelUtil.showError(this, "Please select student.");
            return;
        }
        if (ad.studentNumber==null || ad.studentNumber.trim().isEmpty()) {
            boolean b = PanelUtil.showPrompt(this, "No student number yet, continue to register student?");
            if (!b) {
                return;
            }
            ad.register();
            List lst = DBClient.getList("SELECT a FROM Admission a WHERE a.studentNumber='",ad.studentNumber,"'");
            pnlAdmission.setList(lst);
        }
        super.assessStudent();
    }

    @Override
    protected void searchOldStudent() {
        String txt = txtSearchOldStudent.getText();
        //search in admission first
        List lst = null;
        if (txt==null || txt.trim().isEmpty()) {
            lst = DBClient.getList("SELECT a FROM Admission a");
        }
        else {
            lst = DBClient.getList("SELECT a FROM Admission a WHERE a.lastName LIKE '",txt,"%' OR a.firstName LIKE '",txt,"%'");
        }
        pnlAdmission.setList(lst);
        try {
            pnlAdmission.view.tblResult.setRowSelectionInterval(0, 0);
        }
        catch (Exception e) {
        }
    }

    private void changeStudent() {
        Admission adm = (Admission) pnlAdmission.getBean();
        if (adm==null || adm.personId==0) {
            pnlStudentList.view.tblResult.clearSelection();
            pnlStudentList.setList(new ArrayList());
        }
        else {
            List lst = DBClient.getList(BeanUtil.concat("SELECT a FROM Student a WHERE a.personId=",adm.personId));
            pnlStudentList.setList(lst);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    pnlStudentList.view.tblResult.clearSelection();
                    pnlStudentList.view.tblResult.setRowSelectionInterval(0, 0);
                }
            });
        }
    }
    
    public static void main(String[] args) {
        AAAConfig.getInstance();
        NewStudent pnl = new NewStudent();
        PanelUtil.displayToFrame(pnl);
        pnl.run();
    }

    JDialog dlg;
    protected void acceptAdmission() {
        pnlAcceptAdmission.setBean(new Admission());
        dlg = PanelUtil.showDialog(pnl, "Accept Admission");
        dlg.setVisible(true);
    }
    /** Creates new form NewStudent */
    public NewStudent() {
        try {
            pnlAdmission = new component.BeanPanelPallete();
            pnlAdmission.setMinimumSize(pnlStudentList.getMinimumSize());
            pnlAdmission.setPreferredSize(pnlStudentList.getPreferredSize());
            pnlAdmission.setBeanName("CashierAdmissionExt");
            pnlAdmission.setName("pnlAdmission"); // NOI18N
            pnlAdmission.setShowForm(false);
            pnlAdmission.setShowImage(false);
            pnlAdmission.setShowResult(true);

            pnlStudentList.setList(new ArrayList());
            pnlAssessment.setList(new ArrayList());
            pnlStudentInfo.setList(new ArrayList());
            pnlSearch.removeAll();
            pnlSearch.add(pnlAdmission);
            pnlAdmission.view.tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        changeStudent();
                    }
                }
            });
        }
        catch (Exception e) {
        }
    }

    private void init() {
        pnl = new javax.swing.JPanel();
        pnlAcceptAdmission = new component.BeanPanelPallete();
        btn = new javax.swing.JPanel();
        PanelUtil.updateColor(btn);
        PanelUtil.updateColor(pnl);
        btnSaveAdmission = new javax.swing.JButton();

        pnl.setName("pnl"); // NOI18N
        pnl.setLayout(new java.awt.BorderLayout());

        pnlAcceptAdmission.setBeanName("Admission");
        pnlAcceptAdmission.setName("pnlAcceptAdmission"); // NOI18N
        pnl.add(pnlAcceptAdmission, java.awt.BorderLayout.CENTER);

        btn.setName("btn"); // NOI18N

        btnSaveAdmission.setText("Save Admission");
        btnSaveAdmission.setName("btnSaveAdmission"); // NOI18N
        btnSaveAdmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAdmissionActionPerformed(evt);
            }
        });
        btn.add(btnSaveAdmission);

        pnl.add(btn, java.awt.BorderLayout.SOUTH);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAdmission = new component.BeanPanelPallete();
        pnl = new javax.swing.JPanel();
        pnlAcceptAdmission = new component.BeanPanelPallete();
        btn = new javax.swing.JPanel();
        btnSaveAdmission = new javax.swing.JButton();

        pnlAdmission.setBeanName("CashierAdmissionExt");
        pnlAdmission.setMinimumSize(new java.awt.Dimension(500, 30));
        pnlAdmission.setName("pnlAdmission"); // NOI18N
        pnlAdmission.setPreferredSize(new java.awt.Dimension(500, 30));
        pnlAdmission.setShowForm(false);
        pnlAdmission.setShowImage(false);
        pnlAdmission.setShowResult(true);

        pnl.setName("pnl"); // NOI18N
        pnl.setLayout(new java.awt.BorderLayout());

        pnlAcceptAdmission.setBeanName("AdmissionExt");
        pnlAcceptAdmission.setName("pnlAcceptAdmission"); // NOI18N
        pnl.add(pnlAcceptAdmission, java.awt.BorderLayout.CENTER);

        btn.setName("btn"); // NOI18N

        btnSaveAdmission.setText("Save Admission");
        btnSaveAdmission.setName("btnSaveAdmission"); // NOI18N
        btnSaveAdmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAdmissionActionPerformed(evt);
            }
        });
        btn.add(btnSaveAdmission);

        pnl.add(btn, java.awt.BorderLayout.SOUTH);

        setMinimumSize(new java.awt.Dimension(800, 575));
        setPreferredSize(new java.awt.Dimension(800, 575));
    }// </editor-fold>//GEN-END:initComponents

private void btnSaveAdmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAdmissionActionPerformed
//    check if existing student
    Admission b = (Admission) pnlAcceptAdmission.getBean();
    Admission a = (Admission) DBClient.getFirstRecord("SELECT a FROM Admission a WHERE a.lastName='",b.lastName,"' AND a.firstName='",b.firstName,"' AND a.middleInitial='",b.middleInitial,"'");
//    ask cashier if continue accept
    if (a!=null && !a.isEmptyKey()) {
        if (!PanelUtil.showPrompt(this, "Student exist with name [",b.lastName,", ",b.firstName," ",b.middleInitial,"]. Would you like to continue?")) {
            dlg.setVisible(false);
            return;
        }
    }
//    save admission
    a.save();
//    print OR
    dlg.setVisible(false);
    AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("AdmissionOR", a.seq, "Admission OR", null);
}//GEN-LAST:event_btnSaveAdmissionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn;
    private javax.swing.JButton btnSaveAdmission;
    private javax.swing.JPanel pnl;
    private component.BeanPanelPallete pnlAcceptAdmission;
    private component.BeanPanelPallete pnlAdmission;
    // End of variables declaration//GEN-END:variables

}
