package ui;

import component.AbstractPanel;
import java.awt.EventQueue;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JTable;
import service.util.WSPersistenceEntityManager;

public class DynamicReportAdminForm extends AbstractPanel {

    @Override
    public String getHelpName() {
        return "Module.Reports.Dynamic Report Category";
    }

    public DynamicReportAdminForm() {
           try {
            initComponents();
            initSubRecords();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }

  
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingContext = new javax.beans.binding.BindingContext();

        entityManager = WSPersistenceEntityManager.getInstance();
        jPanel2 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        query = entityManager.createQuery("SELECT d FROM DynamicReport d");
        list = com.sun.java.util.BindingCollections.observableList(query.getResultList());
        buttonManagerPallete1 = new component.ButtonManagerPallete();
        dynamicReportCategoryQuery = entityManager.createQuery("SELECT d FROM DynamicReportCategory d ORDER BY d.category");
        dynamicReportCategoryList = dynamicReportCategoryQuery.getResultList();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        reportTitleLabel = new javax.swing.JLabel();
        sortNumberLabel = new javax.swing.JLabel();
        reportNameLabel = new javax.swing.JLabel();
        txtSortNumber = new component.JTextFieldPallete();
        chkPublicAccess = new javax.swing.JCheckBox();
        txtReportName = new component.JTextFieldPallete();
        txtReportTitle = new component.JTextFieldPallete();
        txtCode = new component.JTextFieldPallete();
        jLabel1 = new javax.swing.JLabel();
        cboCategory = new component.JComboBoxPallete();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dynamicReportAccessSubPanel1 = new ui.subpanel.DynamicReportAccessSubPanel();

        FormListener formListener = new FormListener();

        newButton.setText("New");
        newButton.addActionListener(formListener);

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(formListener);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(formListener);

        saveButton.setText("Save");
        saveButton.addActionListener(formListener);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(newButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(deleteButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(refreshButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(saveButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {deleteButton, newButton, refreshButton, saveButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(saveButton)
                .add(refreshButton)
                .add(deleteButton)
                .add(newButton))
        );

        buttonManagerPallete1.setBtnDelete(deleteButton);
        buttonManagerPallete1.setBtnNew(newButton);
        buttonManagerPallete1.setBtnRefresh(refreshButton);
        buttonManagerPallete1.setBtnSave(saveButton);
        buttonManagerPallete1.setHideDeleteButton(true);
        buttonManagerPallete1.setHideNewButton(true);
        buttonManagerPallete1.setHideRefreshButton(true);
        buttonManagerPallete1.setHideSaveButton(true);
        buttonManagerPallete1.setMasterTable(masterTable);

        masterTable.getTableHeader().setResizingAllowed(false);
        masterTable.getTableHeader().setReorderingAllowed(false);

        javax.beans.binding.Binding binding = new javax.beans.binding.Binding(list, null, masterTable, "elements");
        javax.beans.binding.Binding childBinding = binding.addChildBinding("${code}", null);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN, 0);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN_CLASS, String.class);
        childBinding = binding.addChildBinding("${reportTitle}", null);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN, 1);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN_CLASS, String.class);
        childBinding = binding.addChildBinding("${sortNumber}", null);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN, 2);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN_CLASS, Integer.class);
        childBinding = binding.addChildBinding("${reportName}", null);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN, 3);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN_CLASS, String.class);
        childBinding = binding.addChildBinding("${publicAccess}", null);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN, 4);
        childBinding.putParameter(javax.swing.binding.ParameterKeys.COLUMN_CLASS, Boolean.class);
        bindingContext.addBinding(binding);

        masterScrollPane.setViewportView(masterTable);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        codeLabel.setText("Code:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(codeLabel, gridBagConstraints);

        reportTitleLabel.setText("Report Title:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(reportTitleLabel, gridBagConstraints);

        sortNumberLabel.setText("Sort Number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(sortNumberLabel, gridBagConstraints);

        reportNameLabel.setText("Report Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel1.add(reportNameLabel, gridBagConstraints);

        bindingContext.addBinding(masterTable, "${selectedElement.sortNumber}", txtSortNumber, "text");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 0);
        jPanel1.add(txtSortNumber, gridBagConstraints);

        chkPublicAccess.setText("Public Access");
        chkPublicAccess.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chkPublicAccess.setMargin(new java.awt.Insets(0, 0, 0, 0));

        bindingContext.addBinding(masterTable, "${selectedElement.publicAccess}", chkPublicAccess, "selected");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 3, 0);
        jPanel1.add(chkPublicAccess, gridBagConstraints);

        bindingContext.addBinding(masterTable, "${selectedElement.reportName}", txtReportName, "text");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 0);
        jPanel1.add(txtReportName, gridBagConstraints);

        bindingContext.addBinding(masterTable, "${selectedElement.reportTitle}", txtReportTitle, "text");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 0);
        jPanel1.add(txtReportTitle, gridBagConstraints);

        bindingContext.addBinding(masterTable, "${selectedElement.code}", txtCode, "text");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 0);
        jPanel1.add(txtCode, gridBagConstraints);

        jLabel1.setText("Category:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 2, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        binding = new javax.beans.binding.Binding(dynamicReportCategoryList, null, cboCategory, "elements");
        binding.addChildBinding("${comboDisplay}", null);
        bindingContext.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        jPanel1.add(cboCategory, gridBagConstraints);

        org.jdesktop.layout.GroupLayout dynamicReportAccessSubPanel1Layout = new org.jdesktop.layout.GroupLayout(dynamicReportAccessSubPanel1);
        dynamicReportAccessSubPanel1.setLayout(dynamicReportAccessSubPanel1Layout);
        dynamicReportAccessSubPanel1Layout.setHorizontalGroup(
            dynamicReportAccessSubPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 574, Short.MAX_VALUE)
        );
        dynamicReportAccessSubPanel1Layout.setVerticalGroup(
            dynamicReportAccessSubPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 117, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dynamic Report Access", dynamicReportAccessSubPanel1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, masterScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(masterScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingContext.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == newButton) {
                DynamicReportAdminForm.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                DynamicReportAdminForm.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                DynamicReportAdminForm.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                DynamicReportAdminForm.this.saveButtonActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
    this.refreshRecords();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        this.deleteRecord();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
       this.newRecord();
    }//GEN-LAST:event_newButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        this.saveRecord();
    }//GEN-LAST:event_saveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ButtonManagerPallete buttonManagerPallete1;
    private component.JComboBoxPallete cboCategory;
    private javax.swing.JCheckBox chkPublicAccess;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton deleteButton;
    private ui.subpanel.DynamicReportAccessSubPanel dynamicReportAccessSubPanel1;
    private java.util.List<bean.admin.DynamicReportCategory> dynamicReportCategoryList;
    private javax.persistence.Query dynamicReportCategoryQuery;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.util.List<bean.admin.DynamicReport> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.persistence.Query query;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel reportNameLabel;
    private javax.swing.JLabel reportTitleLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sortNumberLabel;
    private component.JTextFieldPallete txtCode;
    private component.JTextFieldPallete txtReportName;
    private component.JTextFieldPallete txtReportTitle;
    private component.JTextFieldPallete txtSortNumber;
    private javax.beans.binding.BindingContext bindingContext;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new DynamicReportAdminForm());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public String getTitle() {
       return "Dynamic Report Admin";
    }

    public JTable getMasterTable() {
   return masterTable;
    }

    public List getMasterList() {
    return list;
    }

    public EntityManager getEntityManager() {
    return entityManager;
    }

    public Query getMasterQuery() {
    return query;
    }

    public Class getBeanClass() {
    return bean.admin.DynamicReport.class;
    }
}
