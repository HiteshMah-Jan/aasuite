/*
 * BillingForm.java
 *
 * Created on June 5, 2009, 8:43 PM
 */

package ui;

import bean.Patient;
import bean.accounting.OutPatientLineItem;
import constants.UserInfo;
import java.awt.Color;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import springbean.AAAConfig;
import template.report.AbstractReportTemplate;
import template.screen.TransactionPanel;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author  alex
 */
public class BillingForm extends TransactionPanel {
    public static void main(String[] args) {
        java.awt.Color clr = new java.awt.Color(44, 117, 44);
        constants.Constants.panelBackground = clr;
        constants.Constants.labelColor = Color.WHITE;
        AAAConfig.getInstance();
        UserInfo.weblogin("WENG", "WENG");
        PanelUtil.displayToFrame(new BillingForm());
    }
    
    /** Creates new form BillingForm */
    public BillingForm() {
        initComponents();
        PanelUtil.updateColor(jPanel1);
        PanelUtil.updateColor(jPanel2);
        PanelUtil.updateColor(jPanel5);
        PanelUtil.updateColor(jPanel4);
        PanelUtil.updateColor(jLabel1);
        
        beanPatient.view.tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				changeBilling();
			}
        });
        try {
        	List lst = DBClient.getList("SELECT a FROM Patient a WHERE a.section IS NOT NULL ORDER BY a.lastName, a.firstName");
        	beanPatient.setList(lst);
        }
        catch (Exception e) {
        }
    }
    
    protected void changeBilling() {
    	Patient p = (Patient) beanPatient.getBean();
    	if (p==null) {
        	beanBilling.setList(null);
    	}
    	else {
        	List lst = DBClient.getList("SELECT a FROM OutPatientLineItem a WHERE a.outPatientId="+p.admissionId);
        	beanBilling.setList(lst);
    	}
	}

	/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        beanPatient = new component.BeanPanelPallete();
        jPanel2 = new javax.swing.JPanel();
        beanBilling = new component.BeanPanelPallete();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnAddDiscount = new javax.swing.JButton();
        btnPrintBilling = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient List"));
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        beanPatient.setBeanName("Patient");
        beanPatient.setShowForm(false);
        beanPatient.setShowImage(false);
        beanPatient.setShowResult(true);

        javax.swing.GroupLayout beanPatientLayout = new javax.swing.GroupLayout(beanPatient);
        beanPatient.setLayout(beanPatientLayout);
        beanPatientLayout.setHorizontalGroup(
            beanPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        beanPatientLayout.setVerticalGroup(
            beanPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );

        jPanel1.add(beanPatient, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Billing"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        beanBilling.setBeanName("OutPatientLineItem");
        beanBilling.setShowForm(false);
        beanBilling.setShowImage(false);
        beanBilling.setShowResult(true);

        javax.swing.GroupLayout beanBillingLayout = new javax.swing.GroupLayout(beanBilling);
        beanBilling.setLayout(beanBillingLayout);
        beanBillingLayout.setHorizontalGroup(
            beanBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        beanBillingLayout.setVerticalGroup(
            beanBillingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );

        jPanel2.add(beanBilling, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel1.setText("Patient/Room:");
        jPanel5.add(jLabel1);

        txtSearch.setPreferredSize(new java.awt.Dimension(150, 20));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel5.add(txtSearch);

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        btnAddDiscount.setText("Add Discount");
        btnAddDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDiscountActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddDiscount);

        btnPrintBilling.setText("Print Billing");
        btnPrintBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintBillingActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrintBilling);

        add(jPanel4, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if (evt.getKeyChar()=='\n') {
        	if (txtSearch.getText()==null || txtSearch.getText().trim().isEmpty()) {
            	List lst = DBClient.getList("SELECT a FROM Patient a WHERE a.section IS NOT NULL ORDER BY a.lastName, a.firstName");
            	beanPatient.setList(lst);
        	}
        	else {
            	List lst = DBClient.getList("SELECT a FROM Patient a WHERE a.section IS NOT NULL AND (a.lastName LIKE '"+txtSearch.getText()+"%' OR a.firstName LIKE '"+txtSearch.getText()+"%' OR a.section LIKE '"+txtSearch.getText()+"%') ORDER BY a.lastName, a.firstName");
            	beanPatient.setList(lst);
        	}
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnAddDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiscountActionPerformed
    	OutPatientLineItem op = (OutPatientLineItem) beanBilling.getBean();
    	if (op==null) {
    		PanelUtil.showError(null, "Please select specific billing to add discount.");
    		return;
    	}
    	else {
    		PanelUtil.popupBeanTemplate(OutPatientLineItem.class, "Add Discount", false, op);
    	}
    }//GEN-LAST:event_btnAddDiscountActionPerformed

    private void btnPrintBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintBillingActionPerformed
    	Patient p = (Patient) beanPatient.getBean();
    	if (p==null) {
    		PanelUtil.showError(null, "Please select patient.");
    	}
    	else {
            AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("PatientBillingReport", p.personId, "Billing Report for "+p.toString(), null);
    	}
    }//GEN-LAST:event_btnPrintBillingActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.BeanPanelPallete beanBilling;
    private component.BeanPanelPallete beanPatient;
    private javax.swing.JButton btnAddDiscount;
    private javax.swing.JButton btnPrintBilling;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
    
}
