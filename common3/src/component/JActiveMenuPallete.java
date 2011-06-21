/*
 * JActiveMenuPallete.java
 *
 * Created on Oct 11, 2007, 9:59:19 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import util.Log;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JActiveMenuPallete extends JMenu {
    public static JActiveMenuPallete mymenu;
    static List<AbstractPanel> lst = new ArrayList<AbstractPanel>();
    private static AbstractPanel activePanel;

    public static void removeAllFromMemory() {
        lst.clear();
    }
    /** Creates a new instance of JActiveMenuPallete */
    public JActiveMenuPallete() {
        mymenu = this;
    }

    @Override
    public JMenuItem add(Component panel) {
        if (panel instanceof AbstractPanel) {
            activePanel = (AbstractPanel) panel;
            if (lst.contains(panel)) {
                lst.remove(activePanel);
            }
            lst.add(activePanel);
            this.resetAllTitle();
            return this.getMenu(activePanel);
        } else {
            Log.out("Only JActiveMenuItemPallete class can be added to this menu");
            return null;
        }
    }

    private JActiveMenuItemPallete getMenu(AbstractPanel pnl) {
        String title = pnl.getTitle();
        return new JActiveMenuItemPallete(pnl, title);
    }

    public void resetAllTitle() {
        this.removeAll();
        for (AbstractPanel pnl : lst) {
            super.add(getMenu(pnl));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static void closeActiveScreen() {
        if (lst.size() == 1) {
            PanelUtil.showMessageToScreen("No more screen to close.");
            return;
        } else {
//            PanelUtil.showMessageToScreen(" ");
        }
        lst.remove(activePanel);
        if (lst.size() > 0) {
            activePanel = lst.get(lst.size() - 1);
            PanelUtil.showPanel(activePanel);
        }
        mymenu.resetAllTitle();
    }

    public static AbstractPanel getSamePanel(AbstractPanel pnl) {
        if (lst == null) {
            return pnl;
        }
        for (AbstractPanel tmp : lst) {
            if (tmp.equals(pnl)) {
                return tmp;
            }
        }
        return pnl;
    }

    public static JPanel getSamePanel(Class pnl) {
        try {
//            if (lst == null) {
//                JPanel panel = (JPanel) pnl.newInstance();
//                lst = new java.util.ArrayList();
//                if (panel instanceof AbstractPanel) lst.add((AbstractPanel)panel);
//                return panel;
//            }
//            for (JPanel tmp : lst) {
//                if (tmp.getClass().equals(pnl)) {
//                    if (tmp instanceof AbstractPanel) {
//                        if (((AbstractPanel)tmp).isSingleton()) return tmp;
//                    }
//                    return (JPanel) pnl.newInstance();
//                }
//            }
            JPanel panel = (JPanel) pnl.newInstance();
//            if (panel instanceof AbstractPanel) lst.add((AbstractPanel)panel);
            return panel;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
