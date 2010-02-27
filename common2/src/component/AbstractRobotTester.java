/*
 * AbstractRobotTester.java
 *
 * Created on Nov 9, 2007, 7:42:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import util.DataUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class AbstractRobotTester implements Runnable {
    static AbstractRobotTester myTester;
        
    public static AbstractRobotTester getInstance() {
        if (myTester==null) {
            myTester = new AbstractRobotTester();
        }
        return myTester;
    }
    
    private static boolean ENABLE_ROBOT = false;

    public static boolean isENABLE_ROBOT() {
        return ENABLE_ROBOT;
    }

    public static void setENABLE_ROBOT(boolean ENABLE_ROBOT) {
        AbstractRobotTester.ENABLE_ROBOT = ENABLE_ROBOT;
    }

    private static JDialog mouseLocationDlg;
    static JLabel lbl = new JLabel("     [x=0:y=0]      ");
    protected static boolean PANEL_ROBOT_RUNNING = false;

    protected static Robot robot;
    protected AbstractPanel panel;
    protected boolean mainRobot;
    protected MainWindow mainWindow;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainRobot = true;
        AbstractRobotTester.ENABLE_ROBOT = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public AbstractPanel getPanel() {
        return panel;
    }

    public void setPanel(AbstractPanel panel) {
        this.panel = panel;
        mainRobot = false;
        Thread thread = new Thread(this);
        thread.start();
    }

    public AbstractRobotTester() {
        getRobot();
        if (mouseLocationDlg == null) {
            mouseLocationDlg = new JDialog();
            mouseLocationDlg.setAlwaysOnTop(true);
            mouseLocationDlg.setUndecorated(true);
            mouseLocationDlg.add(lbl);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            mouseLocationDlg.setLayout(new GridLayout());
            mouseLocationDlg.setLocation(300, 5);
            mouseLocationDlg.pack();

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    waitManySec(2);
//					mouseLocationDlg.setVisible(true);
                    boolean b = true;
                    while (b) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        lbl.setText((int) p.getX() + ":" + (int) p.getY());
                        lbl.updateUI();
                        waitMilliSec(100);
                    }
                }
            });
            // Start the thread
            thread.start();
        }
    }

    @Override
    public void run() {
        if (ENABLE_ROBOT) {
            if (mainRobot) {
                runRobotTester();
            } else {
                PANEL_ROBOT_RUNNING = true;
                waitManySec(1);
                runRobotTester();
                PANEL_ROBOT_RUNNING = false;
            }
        }
    }

    protected void runRobotTester() {
        
    }

    public static Robot getRobot() {
        if (robot == null) {
            try {
                robot = new java.awt.Robot();
            } catch (AWTException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return robot;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void focusComponent(final JComponent comp) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                comp.setEnabled(true);
                comp.requestFocus();
            }
        });
        t.start();
        waitMilliSec(500);
    }

    public void moveMouse(final JComponent comp) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                Point p = comp.getLocation();
                SwingUtilities.convertPointToScreen(p, comp);
                robot.mouseMove(p.x, p.y);
            }
        });
        t.start();
        waitMilliSec(200);
    }

    public void clickButton(final JButton comp) {
        focusComponent(comp);
        moveMouse(comp);
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                waitMilliSec(100);
                String text = comp.getText();
                EventListener[] events = comp.getListeners(ActionListener.class);
                for (int i = 0; events != null && i < events.length; i++) {
                    ActionEvent evt = new ActionEvent(comp, ActionEvent.ACTION_PERFORMED, text);
                    ActionListener event = (ActionListener) events[i];
                    event.actionPerformed(evt);
                }
            }
        });
        t.start();
        waitOneSec();
    }

    JMenuItem comp;

    public void runMenu(JMenuBar menubar, String mnemonic, int menuItemIndex) {
        JMenu menu = null;
        int count = menubar.getMenuCount();
        for (int i = 0; i < count; i++) {
            JMenu mnu = menubar.getMenu(i);
            int keyCode = mnemonic.charAt(0);
            if (keyCode == mnu.getMnemonic()) {
                menu = mnu;
            }
        }
        if (menu == null) {
            PanelUtil.showMessageToScreen("Cannot find menu with mnemonic == " + mnemonic);
            return;
        }
        expandMenu(mnemonic);
        for (int i = 0; i < menuItemIndex; i++) {
            robot.keyPress(KeyEvent.VK_DOWN);
            waitMilliSec(500);
//			robot.keyRelease(KeyEvent.VK_DOWN);
        }
        comp = menu.getItem(menuItemIndex);
        waitOneSec();
        robot.keyPress(KeyEvent.VK_ESCAPE);

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                waitMilliSec(100);
                String text = comp.getText();
                EventListener[] events = comp.getListeners(ActionListener.class);
                for (int i = 0; events != null && i < events.length; i++) {
                    ActionEvent evt = new ActionEvent(comp, ActionEvent.ACTION_PERFORMED, text);
                    ActionListener event = (ActionListener) events[i];
                    event.actionPerformed(evt);
                }
            }
        });
        t.start();
        waitOneSec();
    }

    public void runMenu(JMenuBar menubar, String mnemonic) {
        JMenu menu = null;
        int count = menubar.getMenuCount();
        for (int i = 0; i < count; i++) {
            JMenu mnu = menubar.getMenu(i);
            int keyCode = mnemonic.charAt(0);
            if (keyCode == mnu.getMnemonic()) {
                menu = mnu;
            }
        }
        if (menu == null) {
            PanelUtil.showMessageToScreen("Cannot find menu with mnemonic == " + mnemonic);
            return;
        }
        count = menu.getItemCount();
        for (int i = 0; i < count; i++) {
            runMenu(menubar, mnemonic, i);
            waitForPanelRobot();
            waitManySec(1);
        }
    }

    public void setActiveTab(final JTabbedPane tab, final int index) {
        if (index >= tab.getTabCount()) {
            return;
        }
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                tab.setSelectedIndex(index);
            }
        });
        t.start();
        tab.setSelectedIndex(index);
    }

    public void setActiveTab(final JTabbedPane tab, int index, final AbstractSubPanel subPanel) {
        if (index >= tab.getTabCount()) {
            return;
        }
        tab.setSelectedIndex(index);
        subPanel.testPanel();
        this.waitMilliSec(300);
    }

    public void typeText(JTextComponent comp, String text) {
        focusComponent(comp);
        if (comp instanceof JTextFieldPallete) {
            JTextFieldPallete field = (JTextFieldPallete) comp;
            if (field.upCase) {
                text = text.toUpperCase();
            }
        }
        comp.transferFocus();
        waitMilliSec(100);
    }

    public void expandMenu(String mnemonic) {
        robot.keyPress(KeyEvent.VK_ALT);
        int keyCode = mnemonic.charAt(0);
        robot.keyPress(keyCode);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    public void tabNext() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                robot.keyRelease(KeyEvent.VK_TAB);
            }
        });
        t.start();
        waitMilliSec(200);
    }

    public void tabPrevious() {
        robot.keyRelease(KeyEvent.VK_SHIFT | KeyEvent.VK_TAB);
        waitMilliSec(300);
    }

    public void selectTableRecord(final JTable tbl, final int row) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                tbl.setRowSelectionInterval(row, row);
            }
        });
        t.start();
        waitMilliSec(1000);
    }

    public void setSelected(final JCheckBox chk, final boolean b) {
        Color oldColor = chk.getBackground();
        chk.setBackground(Color.BLUE);
        Thread t = new Thread(new Runnable() {

            public void run() {
                chk.setSelected(b);
            }
        });
        t.start();
        waitMilliSec(500);
        chk.setBackground(oldColor);
    }

    public void setCode(final JComboBoxPallete comp, final String code) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                int i = DataUtil.getIntValue(code);
                if (i == -1) {
                    comp.setCode(code);
                } else {
                    comp.setSelectedIndex(i);
                }
            }
        });
        t.start();
        waitMilliSec(500);
    }

    public void setDate(final JCalendarPallete calBirthDate, final Date birthDate) {
        calBirthDate.setEnabled(true);
        Thread t = new Thread(new Runnable() {

            public void run() {
                calBirthDate.setDate(birthDate);
            }
        });
        t.start();
        waitMilliSec(300);
    }

    public boolean waitForPanelRobot() {
        int i = 0;
        while (PANEL_ROBOT_RUNNING) {
            waitOneSec();
            i++;
            if (i > 60) {
                //too slow
i = 0;          break;
            }
        }
        return true;
    }

    public void waitMilliSec(int sec) {
        try {
            Thread.currentThread().sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitOneSec() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitManySec(long s) {
        try {
            Thread.currentThread().sleep(s * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayTestNumber(int testNum, String detail) {
        MainWindow.mainwindow.getLabelMessage().setText("                  Test #" + testNum + " - " + detail);
        waitMilliSec(1000);
    }

    public void setValue(final JComponent comp, final Object value) {
        if (comp instanceof JCalendarPallete) {
            setDate((JCalendarPallete) comp, (Date) value);
        }
        else if (comp instanceof JComboBoxPallete) {
            setCode((JComboBoxPallete) comp, value.toString());
        }
        else if (comp instanceof JTextComponent) {
            typeText((JTextComponent) comp, value.toString());
        }
    }
}
