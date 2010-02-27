/*
 * AccessControlUtil.java
 *
 * Created on Dec 13, 2007, 9:56:38 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import bean.admin.AclGroupModule;
import bean.admin.AclUserModule; 
import bean.admin.AppMenu;
import bean.admin.AppConfig;
import common2.Common2View;
import component.JActiveMenuPallete;
import constants.UserInfo;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import service.util.AbstractIBean;

/**
 *
 * @author Budoy Entokwa
 */
public class AccessControlUtil {
    static Font font = new java.awt.Font("Tahoma", 0, 12);
    static boolean runAlready = false;
    
    
    static class MenuAction implements ActionListener {
        String menuLabel, moduleName;
        MenuAction(String menuLabel, String moduleName) {
            this.menuLabel = menuLabel;
            this.moduleName = moduleName;
        }
        public void actionPerformed(ActionEvent e) {
            Common2View.showBeanPanel(moduleName);
//            PanelUtil.showPanel(menuLabel, moduleName);
        }
    }
    
    public static void extractMenuFromDB(List lstReturn) {
        List<AppMenu> lstMenu = AbstractIBean.list("SELECT a FROM AppMenu a ORDER BY a.sortNum");
        lstReturn.addAll(lstMenu);
    }

    private static void createMenuFromDB(JMenuBar menuBar) {
        if (runAlready) return;
        runAlready = true;
        Component[] comps = menuBar.getComponents();
        menuBar.removeAll();
        //now create the menu using the AppMenu table
        JMenu menu = null;
        List<AppMenu> lst = AppConfig.lstMenu;
        if (lst==null || lst.size()==0) {
            if (comps!=null && comps.length>0) {
                JMenu mnu1 = (JMenu) comps[0];
                mnu1.setFont(font);
                if (mnu1.getText().equalsIgnoreCase("Active Window")) {
                    //this is just new
                    menu = new JMenu("Admin");
                    menuBar.add(menu);
                    JMenuItem item = new JMenuItem("Department");
                    item.setFont(font);
                    menu.add(item);
                    item.addActionListener(new MenuAction("Department", "DepartmentForm"));
                }
                else {
                    //need migration here
                    List lstApp = new ArrayList();
                    for (int i = 0; i < comps.length; i++) {
                        JMenu mnu = (JMenu) comps[i];
                        mnu.setFont(font);
                        Component[] mnuItems = mnu.getMenuComponents();
                        for (int j = 0; j < mnuItems.length; j++) {
                            JMenuItem item = (JMenuItem) mnuItems[j];
                            item.setFont(font);
                            lstApp.add(new AppMenu(mnu.getText(), item.getText(), item.getText(), (i*100)+j));
                        }
                    }
                    if (lstApp.size()>0) {
                        AbstractIBean.save(lstApp);
                    }
                    runAlready = false;
                    createMenuFromDB(menuBar);
                }
            }
        }
        else {
            for (AppMenu appMenu : lst) {
                String moduleLabel = appMenu.getModuleLabel();
                if (moduleLabel.equalsIgnoreCase("HELP")) continue;
                String menuLabel = appMenu.getMenuLabel();
                String moduleName = appMenu.getModuleName();
                String description = appMenu.getDescription();

    //            check if the menu is a parent menu
                if (menu==null) {
                    menu = new JMenu(moduleLabel);
                    menu.setFont(font);
                    menuBar.add(menu);
                }
                else {
                    String txt = menu.getText();
                    if (!txt.equalsIgnoreCase(moduleLabel)) {
                        menu = new JMenu(moduleLabel);
                        menu.setFont(font);
                        menuBar.add(menu);
                    }
                }
                JMenuItem item = new JMenuItem(menuLabel);
                item.setFont(font);
                menu.add(item);
                item.addActionListener(new MenuAction(menuLabel, moduleName));
                item.setToolTipText(description);
            }
        }
        for (Component oldComp : comps) {
            menuBar.add(oldComp);
        }
    }
    
    private static void putModules(JMenuBar menuBar) {
        createMenuFromDB(menuBar);
        //this will make all visible
        int menuCount = menuBar.getMenuCount();
        List lstTmp = new ArrayList();
        List lst = AppConfig.lstModule;
        for (int i = 0; (lst==null || lst.size()==0 ) && i < menuCount; i++) {
            JMenu menu = menuBar.getMenu(i);
            menu.setFont(font);
            String txtMenu = menu.getText();

            bean.admin.AclModule module = new bean.admin.AclModule();
            module.setModuleName(txtMenu);
            module.setDescription(txtMenu);
            lstTmp.add(module);
            menu.setVisible(true);

            int itemCount = menu.getItemCount();
            for (int j = 0; j < itemCount; j++) {
                JMenuItem item = menu.getItem(j);
                item.setFont(font);
                if (item == null) {
                    continue;
                }
                String txt = item.getText();

                module = new bean.admin.AclModule();
                module.setModuleName(txtMenu + " - " + txt);
                module.setDescription(txt);
                lstTmp.add(module);
                item.setVisible(true);
            }
        }
        if (lstTmp.size()>0) {
            AbstractIBean.save(lstTmp);
        }
    }

    private static void showAll(JMenuBar menuBar) {
        int menuCount = menuBar.getMenuCount();
        for (int i = 0; i < menuCount; i++) {
            JMenu menu = menuBar.getMenu(i);
            menu.setVisible(true);
            int itemCount = menu.getItemCount();
            for (int j = 0; j < itemCount; j++) {
                JMenuItem item = menu.getItem(j);
                item.setVisible(true);
            }
        }
    }
    
    public static void setAccessControl(JMenuBar menuBar) {
        if (menuBar == null) {
            return;
        }
        putModules(menuBar);
        if (constants.Constants.IS_SINGLE_USER) {
            menuBar.setVisible(true);
            showAll(menuBar);
            return;
        }
        UserInfo user = UserInfo.loginUser;
        if (user == null) {
            menuBar.setVisible(false);
            return;
        } else {
            menuBar.setVisible(true);
            if (user.isSuperAAA()) {
                showAll(menuBar);
                return;
            }
        }
        if (!user.isTemporary()) {
            @SuppressWarnings(value = "unchecked")
            List<AclUserModule> lst = AbstractIBean.list("SELECT a FROM AclUserModule a WHERE a.userid='" + user.getUser().getUserid() + "'");
            if (lst!=null && lst.size()>0) menuBar.setVisible(true);
            int menuCount = menuBar.getMenuCount();
            for (int i = 0; i < menuCount; i++) {
                JMenu menu = menuBar.getMenu(i);
                menu.setFont(font);

                String txt = menu.getText();
                if (!withAccess(txt, lst)) {
                    menu.setVisible(false);
                    continue;
                } else {
                    menu.setVisible(true);
                }

                int itemCount = menu.getItemCount();
                for (int j = 0; j < itemCount; j++) {
                    JMenuItem item = menu.getItem(j);
                    item.setFont(font);
                    String text = item.getText();
                    boolean b = withAccess(txt + " - " + text, lst);
                    item.setVisible(b);
                }
            }
        }
        else {
            @SuppressWarnings(value = "unchecked")
            List<AclGroupModule> lst = AbstractIBean.list("SELECT a FROM AclGroupModule a WHERE a.groupCode='" + user.getPersonType().toUpperCase() + "'");
            if (lst!=null && lst.size()>0) menuBar.setVisible(true);
            int menuCount = menuBar.getMenuCount();
            for (int i = 0; i < menuCount; i++) {
                JMenu menu = menuBar.getMenu(i);
                menu.setFont(font);

                String txt = menu.getText();
                if (!withAccess(txt, lst, true)) {
                    menu.setVisible(false);
                    continue;
                } else {
                    menu.setVisible(true);
                }

                int itemCount = menu.getItemCount();
                for (int j = 0; j < itemCount; j++) {
                    JMenuItem item = menu.getItem(j);
                    item.setFont(font);
                    String text = item.getText();
                    boolean b = withAccess(txt + " - " + text, lst, true);
                    item.setVisible(b);
                }
            }
        }
    }

    public static void setAccessControlButtons(JMenuBar menuBar, JPanel pnl) {
        new JMenuButton(menuBar, pnl).setButtons();
    }

    public static boolean withAccess(String menu, List<AclUserModule> lst) {
        for (AclUserModule duty : lst) {
            String module = duty.moduleName;
            if (module.equalsIgnoreCase(menu) || module.startsWith(menu+" -")) {
                return true;
            }
        }
        return false;
    }

    public static boolean withAccess(String menu, List<AclGroupModule> lst, boolean b) {
        for (AclGroupModule duty : lst) {
            String module = duty.moduleName;
            if (module.equalsIgnoreCase(menu) || module.startsWith(menu+" -")) {
                return true;
            }
        }
        return false;
    }

    /** Creates a new instance of AccessControlUtil */
    public AccessControlUtil() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static class JMenuButton implements ActionListener {

        Map<JMenuItem, JButton> map = new HashMap<JMenuItem, JButton>();
        Map<String, JPanel> mapPnl = new HashMap<String, JPanel>();
        JMenuBar menuBar;
        JPanel pnl;

        private JMenuButton(JMenuBar menubar, JPanel pnl) {
            this.menuBar = menubar;
            this.pnl = pnl;
            PanelUtil.updateColor(pnl);
            this.pnl.setLayout(new GridBagLayout());
        }

        private void displayAllFirst() {
            GridBagConstraints cons = new GridBagConstraints();
            int totalCount = 4;
            int menuCount = menuBar.getMenuCount();
            for (int i = 0; i < menuCount; i++) {
                JMenu menu = menuBar.getMenu(i);
                menu.setFont(font);
                if (menu instanceof JActiveMenuPallete) {
                    continue;
                }
                String txt = menu.getText();
                if ("Help".equals(txt)) continue;

                JPanel tmpPanel = new JPanel();
                tmpPanel.setLayout(new GridLayout(0, totalCount, 3, 3));
                tmpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(txt));
                PanelUtil.updateColor(tmpPanel);

                int itemCount = menu.getItemCount();
                if (itemCount<4) tmpPanel.setLayout(new GridLayout(0, itemCount, 3, 3));
                for (int j = 0; j < itemCount; j++) {
                    JMenuItem item = menu.getItem(j);
                    item.setFont(font);
                    String text = item.getText();
                    JButton btn = new JButton(text);
                    btn.setFont(font);
                    Dimension dim = btn.getPreferredSize();
                    dim.height += 25;
                    btn.setSize(dim);
                    btn.setPreferredSize(dim);
                    btn.setMargin(new Insets(4, 1, 4, 1));
                    tmpPanel.add(btn);

                    map.put(item, btn);
                    btn.addActionListener(this);
                }

                mapPnl.put(txt, tmpPanel);
                cons.gridy++;
                cons.fill = GridBagConstraints.HORIZONTAL;
                pnl.add(tmpPanel, cons);
                PanelUtil.updateColor(pnl);
            }
        }

        private void setButtons() {
            displayAllFirst();
            
            if (constants.Constants.IS_SINGLE_USER) {
                return;
            }
            UserInfo user = UserInfo.loginUser;
            if (user == null) {
                for (JButton btn : map.values()) {
                    btn.setVisible(false);
                }
                return;
            } else {
            }
            if (user.isSuperAAA()) {
                return;
            }
            @SuppressWarnings(value = "unchecked")
            List<AclUserModule> lst = user.getModules();
            menuBar.setVisible(true);
            int menuCount = menuBar.getMenuCount();
            for (int i = 0; i < menuCount; i++) {
                JMenu menu = menuBar.getMenu(i);
                menu.setFont(font);

                String txt = menu.getText();
                JPanel tmp = null;
                if (!withAccess(txt, lst)) {
                    tmp = mapPnl.get(txt);
                    if (tmp!=null) {
                        PanelUtil.updateColor(tmp);
                        pnl.remove(tmp);
                    }
                    continue;
                } else {
                    tmp = mapPnl.get(txt);
                    if (tmp!=null) {
                        PanelUtil.updateColor(tmp);
                        tmp.setVisible(true);
                    }
                }

                int itemCount = menu.getItemCount();
                for (int j = 0; j < itemCount; j++) {
                    JMenuItem item = menu.getItem(j);
                    item.setFont(font);
                    String text = item.getText();
                    boolean b = withAccess(txt + " - " + text, lst);
                    JButton btn = map.get(item);
                    if (!b) {
                        if (tmp!=null) tmp.remove(btn);
                    }
                }
            }
            pnl.updateUI();
        }

        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            JMenuItem mnu = null;
            for (JMenuItem itm : map.keySet()) {
                JButton b = map.get(itm);
                if (b.equals(btn)) {
                    mnu = itm;
                    break;
                }
            }
            ActionListener act = mnu.getActionListeners()[0];
            act.actionPerformed(e);
        }
    }
}
