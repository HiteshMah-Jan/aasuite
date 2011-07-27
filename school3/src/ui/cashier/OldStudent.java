/*
 * OldStudent.java
 *
 * Created on March 25, 2009, 6:01 AM
 */
package ui.cashier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import springbean.AAAConfig;
import springbean.SchoolDefaultProcess;
import template.report.AbstractReportTemplate;
import ui.AbstractCashierForm;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.PanelUtil;
import bean.Enrollment;
import bean.Student;
import bean.accounting.CashierDailyBooklet;
import bean.accounting.Payment;
import bean.admin.AppConfig;
import bean.extension.Student201Ext;

import component.BeanPanelPallete;
import component.JTextFieldPallete;
import component.LabelPallete;

import constants.UserInfo;

/**
 *
 * @author  alex
 */
public class OldStudent extends javax.swing.JPanel implements IAcceptPaymentWindow {

    private Enrollment enroll;
    List planList;
    List gradeLevelList;
    CashierDailyBooklet booklet;
    SchoolDefaultProcess s = new springbean.SchoolDefaultProcess();
    private String useYear;
    public AbstractCashierForm drawer;
    CashierRule rule;

    public void hideSurcharge() {
//    	hideColumn(pnlAssessment, 8);
//    	hideColumn(pnlAssessment, 7);
//    	hideColumn(pnlAssessment, 6);
//    	
//    	hideColumn(pnlPayment, 6);
//    	hideColumn(pnlPayment, 5);
    	btnSurchargeCalculator.setVisible(false);
    	btnChangePaymentPlan.setVisible(false);
    }
    
    private void hideColumn(BeanPanelPallete bean, int index) {
    	JTable tbl = bean.view.tblResult;
    	TableColumn col = tbl.getColumnModel().getColumn(index);
    	tbl.removeColumn(col);
    	bean.view.hideFooter(index);
    }
    
    public AbstractCashierForm getDrawer() {
        return drawer;
    }

    public String getUseYear() {
        return useYear;
    }

    public void setUseYear(String useYear) {
        this.useYear = useYear;
    }

    public static void main(String[] args) {
        AAAConfig.getInstance();
        OldStudent pnl = new OldStudent();
        PanelUtil.displayToFrame(pnl);
        pnl.run();
    }

    public void run() {
        assessStudent();
        if (PanelUtil.showPrompt(this, "Would you like to accept payment?")) {
            acceptPayment();
        }
    }

    public void assessStudent() {
        rule.runAssess();
    }

    public void acceptPayment() {
        rule.runAcceptPayment();
    }

    /** Creates new form OldStudent */
    public OldStudent() {
        initComponents();
        try {
            pnlStudentList.view.tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        reloadPayments();
                    }
                }
            });
            rule = new CashierRule(this);
//            btnChangePaymentPlan.setVisible(false);
            btnPrintStatementOfAccount.setText("Print SOA");
            btnRequestDiscount.setText("Discount");
//            if (!UserInfo.canRequestDiscount()) {
//                 btnRequestDiscount.setVisible(false);
//            }
            btnViewBookPayments.setVisible(false);
            btnViewOtherPayments.setVisible(false);
            
            pnlAssessment.view.tblResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    changeLabelAmount();
                }
            });
            lblSurchargeAmount.setVisible(false);
            
        	btnAdjust.setVisible(false);
            if (UserInfo.canLockInvoice()) {
            	btnAdjust.setVisible(true);
            }
        }
        catch (Exception e) {
        }
    }

    private void changeLabelAmount() {
    	int row = pnlAssessment.view.tblResult.getSelectedRow();
    	List<Payment> lst = pnlAssessment.view.list;
    	double totalAmount = 0;
    	double totalSurchargeAmount = 0;
    	try {
            for (int i=0; i<=row; i++) {
                    Payment p = lst.get(i);
                    totalAmount += p.overallAmountDue;
                    totalSurchargeAmount += p.surchargeBalance;
            }
            for (int i=row+1; i<=pnlAssessment.view.tblResult.getRowCount(); i++) {
                    Payment p = lst.get(i);
                    totalAmount += p.surchargeBalance;
            }
    	}
    	catch (Exception e) {
    	}
    	((LabelPallete)lblAmount).setDoubleTxt(DataUtil.getMoneyFormat(totalAmount));
    	((LabelPallete)lblSurchargeAmount).setDoubleTxt(DataUtil.getMoneyFormat(totalSurchargeAmount));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnEdit = new javax.swing.JButton();
        btnViewOtherPayments = new javax.swing.JButton();
        btnDeletePayment = new javax.swing.JButton();
        btnViewBookPayments = new javax.swing.JButton();
        btnRequestDiscount = new javax.swing.JButton();
        pnlAdjust = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSaveAdjustment = new javax.swing.JButton();
        beanPanelPallete1 = new component.BeanPanelPallete();
        lblAmount = new LabelPallete();
        pnlButton = new javax.swing.JPanel();
        btnAssessStudent = new javax.swing.JButton();
        btnAcceptPayment = new javax.swing.JButton();
        btnChangePaymentPlan = new javax.swing.JButton();
        btnReprintOR = new javax.swing.JButton();
        btnPrintStatementOfAccount = new javax.swing.JButton();
        btnSurchargeCalculator = new javax.swing.JButton();
        btnAdjust = new javax.swing.JButton();
        btnStudent = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        pnlStudentList = new component.BeanPanelPallete();
        pnlStudentInfo = new component.BeanPanelPallete();
        txtSearchOldStudent = new JTextFieldPallete();
        pnlAssessment = new component.BeanPanelPallete();
        pnlPayment = new component.BeanPanelPallete();
        lblSurchargeAmount = new LabelPallete();

        btnEdit.setText("Edit"); // NOI18N
        btnEdit.setName("btnEdit"); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnViewOtherPayments.setText("View Other Payments"); // NOI18N
        btnViewOtherPayments.setName("btnViewOtherPayments"); // NOI18N
        btnViewOtherPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOtherPaymentsActionPerformed(evt);
            }
        });

        btnDeletePayment.setText("Delete Payment [TEST]"); // NOI18N
        btnDeletePayment.setName("btnDeletePayment"); // NOI18N
        btnDeletePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePaymentActionPerformed(evt);
            }
        });

        btnViewBookPayments.setText("View Book Payments"); // NOI18N
        btnViewBookPayments.setName("btnViewBookPayments"); // NOI18N
        btnViewBookPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBookPaymentsActionPerformed(evt);
            }
        });

        btnRequestDiscount.setText("Discount"); // NOI18N
        btnRequestDiscount.setName("btnRequestDiscount"); // NOI18N
        btnRequestDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestDiscountActionPerformed(evt);
            }
        });

        pnlAdjust.setName("pnlAdjust"); // NOI18N
        pnlAdjust.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N

        btnSaveAdjustment.setText("Save Adjustment"); // NOI18N
        btnSaveAdjustment.setName("btnSaveAdjustment"); // NOI18N
        btnSaveAdjustment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAdjustmentActionPerformed(evt);
            }
        });
        jPanel1.add(btnSaveAdjustment);

        pnlAdjust.add(jPanel1, java.awt.BorderLayout.SOUTH);

        beanPanelPallete1.setBeanName("PaymentCashierAdjustmentExt"); // NOI18N
        beanPanelPallete1.setName("beanPanelPallete1"); // NOI18N
        beanPanelPallete1.setShowImage(false);

        javax.swing.GroupLayout beanPanelPallete1Layout = new javax.swing.GroupLayout(beanPanelPallete1);
        beanPanelPallete1.setLayout(beanPanelPallete1Layout);
        beanPanelPallete1Layout.setHorizontalGroup(
            beanPanelPallete1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );
        beanPanelPallete1Layout.setVerticalGroup(
            beanPanelPallete1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlAdjust.add(beanPanelPallete1, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.GridBagLayout());

        lblAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAmount.setText("--"); // NOI18N
        lblAmount.setName("lblAmount"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(lblAmount, gridBagConstraints);

        pnlButton.setName("pnlButton"); // NOI18N

        btnAssessStudent.setText("Assess Student"); // NOI18N
        btnAssessStudent.setName("btnAssessStudent"); // NOI18N
        btnAssessStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssessStudentActionPerformed(evt);
            }
        });
        pnlButton.add(btnAssessStudent);

        btnAcceptPayment.setText("Accept Payment"); // NOI18N
        btnAcceptPayment.setName("btnAcceptPayment"); // NOI18N
        btnAcceptPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptPaymentActionPerformed(evt);
            }
        });
        pnlButton.add(btnAcceptPayment);

        btnChangePaymentPlan.setText("ReAssess"); // NOI18N
        btnChangePaymentPlan.setName("btnChangePaymentPlan"); // NOI18N
        btnChangePaymentPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePaymentPlanActionPerformed(evt);
            }
        });
        pnlButton.add(btnChangePaymentPlan);

        btnReprintOR.setText("Reprint OR"); // NOI18N
        btnReprintOR.setName("btnReprintOR"); // NOI18N
        btnReprintOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReprintORActionPerformed(evt);
            }
        });
        pnlButton.add(btnReprintOR);

        btnPrintStatementOfAccount.setText("Print SOA"); // NOI18N
        btnPrintStatementOfAccount.setName("btnPrintStatementOfAccount"); // NOI18N
        btnPrintStatementOfAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintStatementOfAccountActionPerformed(evt);
            }
        });
        pnlButton.add(btnPrintStatementOfAccount);

        btnSurchargeCalculator.setText("Surcharge Calculator"); // NOI18N
        btnSurchargeCalculator.setName("btnSurchargeCalculator"); // NOI18N
        btnSurchargeCalculator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurchargeCalculatorActionPerformed(evt);
            }
        });
        pnlButton.add(btnSurchargeCalculator);

        btnAdjust.setText("Adjust"); // NOI18N
        btnAdjust.setName("btnAdjust"); // NOI18N
        btnAdjust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdjustActionPerformed(evt);
            }
        });
        pnlButton.add(btnAdjust);

        btnStudent.setText("Student 201"); // NOI18N
        btnStudent.setName("btnStudent"); // NOI18N
        btnStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentActionPerformed(evt);
            }
        });
        pnlButton.add(btnStudent);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pnlButton, gridBagConstraints);

        pnlSearch.setName("pnlSearch"); // NOI18N
        pnlSearch.setLayout(new java.awt.GridLayout(1, 0));

        pnlStudentList.setBeanName("StudentCashierExt"); // NOI18N
        pnlStudentList.setMinimumSize(new java.awt.Dimension(500, 30));
        pnlStudentList.setName("pnlStudentList"); // NOI18N
        pnlStudentList.setPreferredSize(new java.awt.Dimension(500, 30));
        pnlStudentList.setShowForm(false);
        pnlStudentList.setShowResult(true);

        javax.swing.GroupLayout pnlStudentListLayout = new javax.swing.GroupLayout(pnlStudentList);
        pnlStudentList.setLayout(pnlStudentListLayout);
        pnlStudentListLayout.setHorizontalGroup(
            pnlStudentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlStudentListLayout.setVerticalGroup(
            pnlStudentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        pnlSearch.add(pnlStudentList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pnlSearch, gridBagConstraints);

        pnlStudentInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        pnlStudentInfo.setBeanName("StudentSnapshotExt"); // NOI18N
        pnlStudentInfo.setMinimumSize(new java.awt.Dimension(300, 180));
        pnlStudentInfo.setName("pnlStudentInfo"); // NOI18N
        pnlStudentInfo.setPreferredSize(new java.awt.Dimension(300, 180));

        javax.swing.GroupLayout pnlStudentInfoLayout = new javax.swing.GroupLayout(pnlStudentInfo);
        pnlStudentInfo.setLayout(pnlStudentInfoLayout);
        pnlStudentInfoLayout.setHorizontalGroup(
            pnlStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );
        pnlStudentInfoLayout.setVerticalGroup(
            pnlStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(pnlStudentInfo, gridBagConstraints);

        txtSearchOldStudent.setMinimumSize(new java.awt.Dimension(280, 30));
        txtSearchOldStudent.setName("txtSearchOldStudent"); // NOI18N
        txtSearchOldStudent.setPreferredSize(new java.awt.Dimension(280, 30));
        txtSearchOldStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchOldStudentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(txtSearchOldStudent, gridBagConstraints);

        pnlAssessment.setBeanName("PaymentCashierAssessmentExt"); // NOI18N
        pnlAssessment.setMinimumSize(new java.awt.Dimension(0, 150));
        pnlAssessment.setName("pnlAssessment"); // NOI18N
        pnlAssessment.setPreferredSize(new java.awt.Dimension(0, 150));
        pnlAssessment.setShowForm(false);
        pnlAssessment.setShowImage(false);
        pnlAssessment.setShowResult(true);

        javax.swing.GroupLayout pnlAssessmentLayout = new javax.swing.GroupLayout(pnlAssessment);
        pnlAssessment.setLayout(pnlAssessmentLayout);
        pnlAssessmentLayout.setHorizontalGroup(
            pnlAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
        );
        pnlAssessmentLayout.setVerticalGroup(
            pnlAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        add(pnlAssessment, gridBagConstraints);

        pnlPayment.setBeanName("PaymentCashierPaymentExt"); // NOI18N
        pnlPayment.setMinimumSize(new java.awt.Dimension(0, 210));
        pnlPayment.setName("pnlPayment"); // NOI18N
        pnlPayment.setPreferredSize(new java.awt.Dimension(0, 210));
        pnlPayment.setShowForm(false);
        pnlPayment.setShowImage(false);
        pnlPayment.setShowResult(true);

        javax.swing.GroupLayout pnlPaymentLayout = new javax.swing.GroupLayout(pnlPayment);
        pnlPayment.setLayout(pnlPaymentLayout);
        pnlPaymentLayout.setHorizontalGroup(
            pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
        );
        pnlPaymentLayout.setVerticalGroup(
            pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        add(pnlPayment, gridBagConstraints);

        lblSurchargeAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSurchargeAmount.setText("--"); // NOI18N
        lblSurchargeAmount.setName("lblSurchargeAmount"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        add(lblSurchargeAmount, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

private void txtSearchOldStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchOldStudentActionPerformed
    searchOldStudent();
}//GEN-LAST:event_txtSearchOldStudentActionPerformed

protected void searchOldStudent() {
    String txt = txtSearchOldStudent.getText();
    Log.out("SEARCH STUDENT SERVER CALL");
    List lst = null;
    if (txt==null || txt.isEmpty()) {
        lst = DBClient.getList("SELECT a FROM Student a ORDER BY a.lastName, a.firstName, a.middleInitial");
    }
    else {
        lst = DBClient.getList("SELECT a FROM Student a WHERE a.lastName LIKE '",txt,"%' OR a.firstName LIKE '",txt,"%' OR a.studentNumber LIKE '",txt,"%' ORDER BY a.lastName, a.firstName, a.middleInitial");
    }
    pnlStudentList.setList(lst);
}

private void btnAcceptPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptPaymentActionPerformed
    acceptPayment();
}//GEN-LAST:event_btnAcceptPaymentActionPerformed

private void btnAssessStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssessStudentActionPerformed
    assessStudent();
}//GEN-LAST:event_btnAssessStudentActionPerformed

private void btnReprintORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprintORActionPerformed
    Payment p = (Payment) pnlPayment.getBean();
    if (p == null || p.orNumber == null || p.orNumber.trim().isEmpty() || p.orNumber.equals("-1")) {
        PanelUtil.showError(btnReprintOR, "Payment does not have OR yet or not yet paid");
    } else {
        p.printOR("Print OR", p);
    }
}//GEN-LAST:event_btnReprintORActionPerformed

private void btnChangePaymentPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePaymentPlanActionPerformed
    rule.runChangePlan();
}//GEN-LAST:event_btnChangePaymentPlanActionPerformed

private void btnPrintStatementOfAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintStatementOfAccountActionPerformed
    Payment p = (Payment) pnlAssessment.getBean();
    if (p == null) {
        PanelUtil.showError(btnReprintOR, "Statement of account not available.");
    }
    else {
//        check if enrollment exist for this record
        if (p.recordId==0 || !DBClient.exist(BeanUtil.concat("SELECT a FROM Enrollment a WHERE a.seq=",p.recordId))) {
//            create enrollment object
            Enrollment e = new Enrollment();
            e.studentId = p.paidBy;
            e.schoolYear = p.schoolYear;
            e.dateEnrolled = p.paymentDate;
            e.save();
//            update all id of payment records
            DBClient.runSQLNative("UPDATE Payment SET recordId=",e.seq," WHERE form='ENROLLMENT' AND paidBy=",e.studentId," AND schoolYear='",p.schoolYear,"'");
            AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("StatementOfAccount", e.studentId, "Statement of Account", null);
        }
        else {
            AbstractReportTemplate.getInstance().showReportFromFileTemplateDialog("StatementOfAccount", p.paidBy, "Statement of Account", null);
        }
    }
}//GEN-LAST:event_btnPrintStatementOfAccountActionPerformed

private void btnDeletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePaymentActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_btnDeletePaymentActionPerformed

private void btnViewOtherPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOtherPaymentsActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_btnViewOtherPaymentsActionPerformed

private void btnViewBookPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBookPaymentsActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_btnViewBookPaymentsActionPerformed

private void btnRequestDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestDiscountActionPerformed
    Student stud = (Student) pnlStudentList.getBean();
    if (stud==null || stud.isEmptyKey()) {
        PanelUtil.showError(this, "Please select a payment.");
        return;
    }

    //check if the selected record has assessment and if has amount to pay
    List<Payment> lst = pnlAssessment.view.list;
    if (lst==null || lst.size()==0) {
        PanelUtil.showError(this, "Student not yet assessed.");
        return;
    }
    double totBalance = 0;
    for (Payment p:lst) {
        totBalance += p.balance;
    }
    if (totBalance<=0) {
        PanelUtil.showError(this, "Complete payment already.");
        return;
    }
	Payment pay = (Payment) pnlAssessment.getBean();
	if (pay.balance==0) {
		PanelUtil.showError(this, "Selected payment does not have balance.");
		return;
	}

    String discount = (String) PanelUtil.showPromptMessage(this, "Discount Type", "Discount Type", AppConfig.getDiscountTypes(), "EARLY PAYMENT");
	if ("SURCHARGE".equals(discount) && pay.surcharge==0) {
		PanelUtil.showError(this, "There is no surcharge in this payment.");
		return;
	}
    double amount = PanelUtil.showPromptMessage(this, "Discount Amount", 0);
    if (amount==-1) return;
    if (!AppConfig.discountNeedRequest()) {
    	if ("SURCHARGE".equals(discount)) {
    		if (pay.surcharge<amount) {
    			PanelUtil.showError(this, "Discount is more than the surcharge amount.");
    			return;
    		}
    		pay.discountSurcharge = amount;
    	}
    	else {
    		if (pay.balance<amount) {
    			PanelUtil.showError(this, "Discount is more than the balance amount.");
    			return;
    		}
    		pay.discount = amount;
    	}
    	pay.discountReason = discount;
    	double d = pay.amount+pay.surcharge-(pay.overallAmountPaid+pay.discount+pay.discountSurcharge);
    	if (d>0) {
    		pay.balance = d;
    	}
    	else {
    		pay.balance = 0;
    	}
    	pay.overallBalance = pay.balance;
    	pay.save();
    	reloadPayments();
    }
    else {
//      get the enrollment for the selected schoolYear
        Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=",stud.seq," AND a.schoolYear='", AppConfig.getSchoolYear(),"'");
        e.requestedDiscountAmount = amount;
        e.requestedDiscountBy = UserInfo.getUserName();
        e.requestedDiscountDate = constants.Constants.useDate;
        e.requestedDiscountType = discount;
        e.save();
        PanelUtil.showMessage(this, "Discount is now requested.");
    }
}//GEN-LAST:event_btnRequestDiscountActionPerformed

private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
	Payment p = (Payment) pnlPayment.getBean();
}//GEN-LAST:event_btnEditActionPerformed

private void btnSurchargeCalculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurchargeCalculatorActionPerformed
//    Student stud = (Student) pnlStudentList.getBean();
//    SurchargeCalculator.display(stud, useYear);
    SurchargeCalculator.display(pnlAssessment.view.list);
}//GEN-LAST:event_btnSurchargeCalculatorActionPerformed

private void btnAdjustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdjustActionPerformed
	Payment p = (Payment) pnlAssessment.getBean();
	if (PanelUtil.showPrompt(null, "Would you like to recalculate payments?")) {
		List lst = pnlAssessment.view.list;
		DBClient.persistBean(lst);
		
		lst = pnlPayment.view.list;
		DBClient.persistBean(lst);
	}
    beanPanelPallete1.setBean(p);
	PanelUtil.showDialog(pnlAdjust, "Assessment Adjustment");
}//GEN-LAST:event_btnAdjustActionPerformed

private void btnSaveAdjustmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAdjustmentActionPerformed
    Payment p = (Payment) beanPanelPallete1.getBean();
    p.form = "ENROLLMENT";
    p.adjustedBy = UserInfo.getUserName();
    p.adjustedDate = new Date();
    p.save();
    
//    for testing only
//    p.setupIndex();
}//GEN-LAST:event_btnSaveAdjustmentActionPerformed

private void btnStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentActionPerformed
 PanelUtil.popupBeanTemplate(Student201Ext.class, "File 201", true);
}//GEN-LAST:event_btnStudentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.BeanPanelPallete beanPanelPallete1;
    private javax.swing.JButton btnAcceptPayment;
    private javax.swing.JButton btnAdjust;
    private javax.swing.JButton btnAssessStudent;
    private javax.swing.JButton btnChangePaymentPlan;
    private javax.swing.JButton btnDeletePayment;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnPrintStatementOfAccount;
    private javax.swing.JButton btnReprintOR;
    private javax.swing.JButton btnRequestDiscount;
    private javax.swing.JButton btnSaveAdjustment;
    private javax.swing.JButton btnStudent;
    private javax.swing.JButton btnSurchargeCalculator;
    private javax.swing.JButton btnViewBookPayments;
    private javax.swing.JButton btnViewOtherPayments;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblSurchargeAmount;
    private javax.swing.JPanel pnlAdjust;
    public component.BeanPanelPallete pnlAssessment;
    protected javax.swing.JPanel pnlButton;
    public component.BeanPanelPallete pnlPayment;
    protected javax.swing.JPanel pnlSearch;
    public component.BeanPanelPallete pnlStudentInfo;
    public component.BeanPanelPallete pnlStudentList;
    public javax.swing.JTextField txtSearchOldStudent;
    // End of variables declaration//GEN-END:variables

    public double getCandidateAmount() {
    	return PanelUtil.getDoubleValue(((LabelPallete)lblAmount).getTxt());
    }
    
    public List<Payment> lstPayments;
    
    public List<Payment> extractPayments() {
        Student st = (Student) pnlStudentList.getBean();
        return st.extractPayments(useYear);
    }
    
    public void reloadPayments() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Student st = (Student) pnlStudentList.getBean();
                //set the student snap shot
                pnlStudentInfo.setBean(st);
                //clear the payments
                pnlAssessment.setList(new ArrayList());
                pnlPayment.setList(new ArrayList());

                //clear the payment line items
                if (st == null || st.isEmptyKey()) {
                    return;
                }
                Log.out("EXTRACT STUDENT PAYMENT SERVER CALL");
                lstPayments = extractPayments();
                Log.out("COUNT = ",lstPayments.size());
//                only the distinct payments here
                List orig = new ArrayList();
                for (Payment p : lstPayments) {
                	if (!p.paid) {
                		if (p.overallAmountPaid==0) {
                            p.overallAmountPaid = getTotalPaid(p.paymentFor);
                		}
                		if (p.overallBalance==0) {
                            p.overallBalance = getTotalBalance(p.paymentFor);
                		}
                        orig.add(p);
                	}
                }
                pnlAssessment.setList(orig);

//                this will have the actual payments
                List payments = new ArrayList();
                for (Payment p : lstPayments) {
                	if (p.paymentFor==null || p.paymentFor.equals(constants.Constants.CANCELLED)) {
                		continue;
                	}
                	if (p.paid) {
                        payments.add(p);
                	}
                }
                pnlPayment.setList(payments);
                
                SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						List<Payment> lst = pnlAssessment.view.list;
						for (int i=0; lst!=null && i<lst.size(); i++) {
							Payment p = lst.get(i);
							if (p.overallAmountDue>0) {
								pnlAssessment.view.tblResult.setRowSelectionInterval(i, i);
								break;
							}
						}
					}
                });
            }
        });
    }

    public double getTotalPaid(String paymentFor) {
    	return getTotal("amountPaid", paymentFor);
    }
    
    public double getTotalBalance(String paymentFor) {
    	return getTotal("balance", paymentFor);
    }

    protected double getTotal(String field, String paymentFor) {
        double d = 0;
        for (Payment p : lstPayments) {
            if (p.paymentFor!=null && p.paymentFor.equals(paymentFor)) {
                try {
                    d += Double.parseDouble(BeanUtil.getPropertyValue(p, field).toString());
                }
                catch (Exception e) {
                }
            }
        }
        return d;
    }

    public void loadOR(String or) {
        String sql = BeanUtil.concat("SELECT a FROM Student a, PaymentEnrollment b WHERE a.personId=b.paidBy AND b.orNumber='",or,"'");
        Student stud = (Student) DBClient.getFirstRecord(sql);
        if (stud!=null) {
            pnlPayment.view.list.clear();
            pnlPayment.view.list.add(stud);
        }
    }
    
    public Payment nextPayment(Payment oldp) {
    	List<Payment> lst = pnlAssessment.view.list;
    	for (Payment p:lst) {
    		if (oldp==null) {
    			if (p.overallBalance>0) return p;
    		}
    		else if (oldp.seq<p.seq) {
    			return p;
    		}
    	}
    	return null;
    }
    
    public Student getStudent() {
    	return (Student) pnlStudentList.getBean();
    }

    public void searchOR(String or) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDrawer(AbstractCashierForm form) {
        this.drawer = form;
    }
}
