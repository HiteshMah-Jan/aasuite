/*
 * LoginPanel.java
 *
 * Created on October 29, 2007, 1:00 PM
 */

package ui;

import bean.Person;
import bean.admin.AclUser;
import constants.UserInfo;
import javax.swing.JPanel;
import util.PanelUtil;

/**
 *
 * @author  Budoy Entokwa
 */
public class ChangePasswordPanel extends JPanel {

    /** Creates new form LoginPanel */
    public ChangePasswordPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel5 = new javax.swing.JLabel();
        txtChangeNewPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        btnChangePasswordNow = new javax.swing.JButton();
        txtChangeRetypePassword = new javax.swing.JPasswordField();

        setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("New Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        add(txtChangeNewPassword, gridBagConstraints);

        jLabel6.setText("Retype New Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 2, 0);
        add(jLabel6, gridBagConstraints);

        btnChangePasswordNow.setText("Change Password");
        btnChangePasswordNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordNowActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        add(btnChangePasswordNow, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 2, 0);
        add(txtChangeRetypePassword, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

private void btnChangePasswordNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordNowActionPerformed
    //check if password is same with retype
    String newPassword = txtChangeNewPassword.getText().toUpperCase();
    if (newPassword==null || newPassword.isEmpty() || !newPassword.equalsIgnoreCase(txtChangeRetypePassword.getText())) {
        PanelUtil.showMessage(btnChangePasswordNow, "Please retype new password, be sure that it has same value.");
        return;
    }
    //check if the username/password is valid
    UserInfo usr = UserInfo.loginUser;
    boolean b = usr.isTemporary();
    if (b) {
        Person p = (Person) Person.find(Person.class, usr.getPersonId());
        p.tempPass = newPassword;
        p.save();
    }
    else {
        AclUser user = usr.getUser();
        user.setPassword(newPassword);
        user.save();
    }
    PanelUtil.showMessage(btnChangePasswordNow, "Change password was successful.");
}//GEN-LAST:event_btnChangePasswordNowActionPerformed
	
//	@Override
//	public String getTitle() {
//		return "Login";		
//	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePasswordNow;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField txtChangeNewPassword;
    private javax.swing.JPasswordField txtChangeRetypePassword;
    // End of variables declaration//GEN-END:variables
	
}