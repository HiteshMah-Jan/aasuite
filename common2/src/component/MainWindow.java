/*
 * MainWindow.java
 *
 * Created on Sep 15, 2007, 9:05:55 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import common2.Common2View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
//import ui.ManagerLicenceDialog;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class MainWindow extends FrameView {

    //This must be setup by the subclass
    public static MainWindow mainwindow = null;
    AbstractRobotTester tester;

    public void showLicenceManager() {
//        ManagerLicenceDialog dlg = new ManagerLicenceDialog(this.getFrame());
//        dlg.txtLicenceTo.setText(constants.Constants.getCustomerName());
//        dlg.txtLicenceType.setText(constants.Constants.getLicenceType());
//        dlg.pack();
//        dlg.setVisible(true);
    }

    public MainWindow getMe() {
        return this;
    }

    public AbstractRobotTester getTester() {
        return tester;
    }

    public void setTester(AbstractRobotTester tester) {
        this.tester = tester;
//		this.tester.setMainWindow(this);
    }

    public void runRobot() {
        this.tester.setMainWindow(this);
    }

    public MainWindow(SingleFrameApplication app) {
        super(app);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainwindow.getBtnDelete().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().deleteRecord();
                    }
                });
                mainwindow.getBtnNew().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().newRecord();
                    }
                });
                mainwindow.getBtnNext().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().nextRecord();
                    }
                });
                mainwindow.getBtnPrev().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().prevRecord();
                    }
                });
                mainwindow.getBtnReload().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().refreshRecords();
                    }
                });
                mainwindow.getBtnSave().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        mainwindow.getDisplayedModule().saveRecord();
                    }
                });
                mainwindow.getBtnPrint().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (constants.Constants.isLicenced()) {
                            mainwindow.getDisplayedModule().print();
                        } else {
                            PanelUtil.showMessage(getBtnPrint(), "Cannot use print button for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                        }
                    }
                });
                mainwindow.getBtnReport().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (constants.Constants.isLicenced()) {
                            mainwindow.getDisplayedModule().report();
                        } else {
                            PanelUtil.showMessage(getBtnReport(), "Cannot use report button for trial version. For licence please email our sales at " + constants.Constants.SALES_EMAIL);
                        }
                    }
                });
                mainwindow.getBtnSave().addMouseListener(new MouseAdapter() {

                    public void mousePressed(java.awt.event.MouseEvent e) {
                        Thread t = new Thread(new Runnable() {

                            public void run() {
                                mainwindow.getDisplayedModule().requestFocusInWindow();
                            }
                        });
                        t.start();
                        try {
                            Thread.currentThread().sleep(200);
                        } catch (InterruptedException exp) {
                            exp.printStackTrace();
                        }
                    }
                });
                try {
                    component.MainWindow pnl = getMe();
                    java.lang.String simpleName = pnl.getClass().getSimpleName();
                    component.AbstractRobotTester tester = (component.AbstractRobotTester) Class.forName("ui.robottester."+simpleName+"RobotTester").newInstance();
                    setTester(tester);
                } catch (Exception ex) {
                    Logger.getLogger("global").log(Level.SEVERE, "CLASS NOT FOUND " + ex.getMessage());
                }
                focusButton(mainwindow.getBtnSave());
                if (!constants.Constants.isLicenced()) {
                    PanelUtil.showMessage(mainwindow.getMainPanel(), "Please obtain licence from our sales at " + constants.Constants.SALES_EMAIL + " to use all the features. \nFor corporate or bulk licence ask our sales for special pricing and discounts.");
                }
                getMessageSender().setVisible(false);
            }
        });
    }

    private void focusButton(JButton button) {
        button.addMouseListener(new MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.requestFocus();
            }
        });
    }

    public JButton getMessageSender() {
        return new JButton();
    }
    
    public abstract JPanel getMainPanel();

    public abstract JMenu getActiveMenu();

    public abstract JLabel getStatusLabel();

    public static AbstractPanel getDisplayedModule() {
        if (mainwindow == null || mainwindow.getMainPanel() == null) {
            return null;
        }
        try {
            Object obj = mainwindow.getMainPanel().getComponent(0);
            if (obj instanceof AbstractPanel) {
                return (AbstractPanel) obj;
            }
        }
        catch (Exception e) {
        }
        return null;
    }

    public final void showPanel(String name, String clsStr) {
        JPanel panel = PanelUtil.showPanel(name, clsStr);
        if (panel instanceof AbstractPanel) {
            String title = ((AbstractPanel) panel).getTitle();
            PanelUtil.setTitle(title);
        }
    }

    public static final void showPanel(JPanel panel) {
        Common2View.mainView.pnlMain.removeAll();
        Common2View.mainView.pnlMain.add(panel);
        Common2View.mainView.pnlMain.updateUI();
    }

    public JButton getBtnDelete() {
        return null;
    }

    public JButton getBtnNew() {
        return null;
    }

    public JButton getBtnNext() {
        return null;
    }

    public JButton getBtnPrev() {
        return null;
    }

    public JButton getBtnReport() {
        return null;
    }

    public JButton getBtnReload() {
        return null;
    }

    public JButton getBtnSave() {
        return null;
    }

    public JButton getBtnPrint() {
        return null;
    }

    public JLabel getLabelMessage() {
        return null;
    }
}
