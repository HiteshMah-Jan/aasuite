/*
 * PanelUtil.java
 *
 * Created on Aug 19, 2007, 9:59:50 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jdesktop.beansbinding.ELProperty;

import bean.admin.AppConfig;

import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.Display;
import template.TemplateReader;
import template.UITemplate;
import template.screen.AbstractTemplatePanel;
import template.screen.AbstractTemplatePanel.FieldCompose;

import common2.Common2View;
import component.AbstractPanel;
import component.AbstractSubPanel;
import component.IGetText;
import component.JActiveMenuPallete;
import component.JCalendarPallete;
import component.MainWindow;

/**
 *
 * @author Budoy Entokwa
 */
public class PanelUtil {

    public static Color ERROR_COLOR = Color.RED;
    public static Color OK_COLOR = Color.WHITE;
    public static Color MANDATORY_COLOR = Color.BLUE;
    public static Color FOCUS_COLOR = Color.BLUE;
    public static Color DISABLE_COLOR = Color.LIGHT_GRAY;
    private static JFrame frame;

    public static String constructSql(Class beanClass) {
        try {
            AbstractIBean bean = (AbstractIBean) beanClass.newInstance();
            String orderBy = bean.orderBy();
            String addWhere = bean.addWhere();
            String beanName = BeanUtil.getEntityClass(bean.getClass()).getSimpleName();
            if (beanName.endsWith("Ext")) {
                beanName = beanName.replace("Ext", "");
            }
            if (orderBy==null || orderBy.isEmpty()) {
                return BeanUtil.concat("SELECT a FROM ",beanName," a ",addWhere);
            }
            else {
                return BeanUtil.concat("SELECT a FROM ",beanName," a ",addWhere," ORDER BY ",orderBy);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(PanelUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PanelUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static JDialog showDialog(JPanel pnl, String title) {
        JDialog dlg = new JDialog();
        dlg.setTitle(title);
        dlg.setModal(true);
        dlg.setLayout(new GridLayout());
        dlg.add(pnl);
        dlg.pack();
        dlg.setVisible(true);
        return dlg;
    }

    public static void updateColor(JDialog dlgToolbar) {
        if (constants.Constants.panelBackground!=null) dlgToolbar.setBackground(constants.Constants.panelBackground);
    }

    private Component comp;
    private List<IGetText> allGetText = new ArrayList<IGetText>();

    private void extractFirstGetText(JComponent jcomp) {
        Component[] compArr = jcomp.getComponents();
        if (compArr != null) {
            for (Component comp : compArr) {
                if (comp instanceof IGetText) {
                    this.comp = comp;
                    return;
                } else {
                    if (comp instanceof JComponent) {
                        extractFirstGetText((JComponent) comp);
                    }
                }
            }
        }
    }
    
    private void extractAllGetText(JComponent jcomp) {
        Component[] compArr = jcomp.getComponents();
        if (compArr != null) {
            for (Component comp : compArr) {
                if (comp instanceof IGetText) {
                    allGetText.add((IGetText)comp);
                } else {
                    if (comp instanceof JComponent) {
                        extractAllGetText((JComponent) comp);
                    }
                }
            }
        }
    }

    public static Component getFirstGetText(JComponent jcomp) {
        PanelUtil p = new PanelUtil();
        p.extractFirstGetText(jcomp);
        return p.comp;
    }

    public static List<IGetText> getAllGetText(JComponent jcomp) {
        PanelUtil p = new PanelUtil();
        p.extractAllGetText(jcomp);
        return p.allGetText;
    }

    public static void addEmpty(List list) {
        if (list != null) {
            if (!list.isEmpty()) {
                Object obj = list.get(0);
                if (obj instanceof AbstractIBean) {
                    AbstractIBean bean = (AbstractIBean) obj;
                    Object objKey = bean.keyVal();
                    if (objKey != null) {
                        try {
                            obj = list.get(0);
                            java.lang.Object newObj = obj.getClass().newInstance();
                            list.add(0, newObj);
                        } catch (Exception ex) {
                            Logger.getLogger("global").log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        PanelUtil.frame = frame;
        AbstractPanel.frame = frame;
        PanelUtil.frame.setVisible(false);
        PanelUtil.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        PanelUtil.frame.setVisible(true);
        PanelUtil.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        PanelUtil.frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (PanelUtil.showPrompt((JComponent) PanelUtil.frame.getComponent(0), "Click OK to exit.")) {
                    System.exit(0);
                }
            }
        });
    }

    public static boolean isEmpty(JCalendarPallete cal) {
        if (cal == null || cal.getDate() == null) {
            return true;
        }
        return false;
    }

    public PanelUtil() {
    }

    static Map mapPanel = new HashMap();
    static Map mapMenu = new HashMap();

    @SuppressWarnings(value = "unchecked")
    public static JPanel showPanel(String name, String clsStr) {
        Class cls = null;
        try {
            cls = Class.forName(getClassPath(clsStr));
            JPanel pnl = JActiveMenuPallete.getSamePanel(cls);
//            if (pnl instanceof AbstractPanel) {
//                pnl = JActiveMenuPallete.getSamePanel((AbstractPanel) pnl);
////                ((AbstractPanel) pnl).refresh();
//            }
            MainWindow.mainwindow.getMainPanel().removeAll();
            MainWindow.mainwindow.getMainPanel().add(pnl);
            MainWindow.mainwindow.getMainPanel().updateUI();
            MainWindow.mainwindow.getActiveMenu().add(pnl);
            return pnl;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainWindow.mainwindow.getMainPanel(), name, "", JOptionPane.OK_OPTION);
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void showPanel2(String name, JPanel pnl) {
        try {
            Common2View.mainView.pnlMain.removeAll();
            Common2View.mainView.pnlMain.add(pnl);
            Common2View.mainView.pnlMain.add(pnl);
            Common2View.mainView.pnlMain.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog((JPanel)Common2View.getTransactionPanel(), name, "", JOptionPane.OK_OPTION);
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public static JPanel showPanel2(String name, String clsStr) {
        Class cls = null;
        try {
            cls = Class.forName(getClassPath(clsStr));
            JPanel pnl = JActiveMenuPallete.getSamePanel(cls);
            Common2View.mainView.pnlMain.removeAll();
            Common2View.mainView.pnlMain.add(pnl);
            Common2View.mainView.pnlMain.add(pnl);
            Common2View.mainView.pnlMain.updateUI();
            return pnl;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog((JPanel)Common2View.getTransactionPanel(), name, "", JOptionPane.OK_OPTION);
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static JPanel getPanel(String name, String clsStr) {
        Class cls = null;
        try {
            cls = Class.forName(getClassPath(clsStr));
            JPanel pnl = JActiveMenuPallete.getSamePanel(cls);
            return pnl;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog((JPanel)Common2View.getTransactionPanel(), name, "", JOptionPane.OK_OPTION);
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void showPanel(AbstractPanel panel) {
        MainWindow.mainwindow.getMainPanel().removeAll();
        MainWindow.mainwindow.getMainPanel().add(panel);
        MainWindow.mainwindow.getMainPanel().updateUI();

        PanelUtil.setTitle(panel.getTitle());
    }

    public static void showPanel(AbstractPanel panel, List recList) {
        MainWindow.mainwindow.getMainPanel().removeAll();
        MainWindow.mainwindow.getMainPanel().add(panel);
        MainWindow.mainwindow.getMainPanel().updateUI();

        PanelUtil.setTitle(panel.getTitle());
        panel.setRecordList(recList);
    }

    public static void showPanel(AbstractPanel panel, AbstractIBean bean) {
        MainWindow.mainwindow.getMainPanel().removeAll();
        MainWindow.mainwindow.getMainPanel().add(panel);
        MainWindow.mainwindow.getMainPanel().updateUI();

        PanelUtil.setTitle(panel.getTitle());
        List recList = new ArrayList();
        recList.add(bean);
        panel.setRecordList(recList);
    }

    public static String getClassPath(String cls) {
        if (cls.indexOf(".") != -1) {
            return cls;
        }
        return "ui." + cls;
    }

    public static String getBeanClassPath(String cls) {
        if (cls.indexOf(".") != -1) {
            return cls;
        }
        return "bean." + cls;
    }

    public static boolean inActiveWindow(String name) {
        int itemCount = MainWindow.mainwindow.getActiveMenu().getItemCount();
        for (int i = 0; i < itemCount; i++) {
            JMenuItem item = MainWindow.mainwindow.getActiveMenu().getItem(i);
            if (item == null || item.getText() == null) {
                continue;
            }
            if (item.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String getGenericCode(String id) {
        if (id != null && id.trim().length() > 0) {
            if (id.indexOf("-") != -1) {
                return id.substring(0, id.indexOf("-")).trim();
            }
        }
        if (id!=null) return id.trim();
        return id;
    }

    public static boolean isEmpty(String obj) {
        if (obj == null) {
            return true;
        }
        if (obj.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection obj) {
        if (obj == null) {
            return true;
        }
        if (obj.size() == 0) {
            return true;
        }
        return false;
    }

//	public static boolean isEmpty(JTextComponent comp) {
//		if (comp==null) return true;
//		String text = comp.getText();
//		return isEmpty(text);
//	}
    public static boolean isEmpty(IGetText comp) {
        if (comp == null) {
            return true;
        }
        String text = comp.getText();
        return isEmpty(text);
    }

    public static void showMessage(JComponent comp, String message) {
        JOptionPane.showMessageDialog(comp, message);
    }

    public static boolean showPrompt(JComponent comp, String message) {
        int i = JOptionPane.showConfirmDialog(comp, message, "Close Application", JOptionPane.YES_NO_OPTION);
        return i == JOptionPane.OK_OPTION;
    }

    public static boolean showPrompt(JComponent comp, String message, String title) {
        int i = JOptionPane.showConfirmDialog(comp, message, title, JOptionPane.YES_NO_OPTION);
        return i == JOptionPane.OK_OPTION;
    }

    public static String showPromptMessage(JComponent comp, String message, String title) {
        String str = JOptionPane.showInputDialog(comp, message, title, JOptionPane.YES_NO_OPTION);
        return str;
    }

    public static String showPromptDefaultMessage(JComponent comp, String message, String def) {
        String str = JOptionPane.showInputDialog(comp, message, def);
        return str;
    }

    public static double showPromptMessage(JComponent comp, String message, double d) {
        String str = JOptionPane.showInputDialog(comp, message, d+"");
        try {
            return Double.parseDouble(str);
        }
        catch (Exception e) {
            return -1;
        }
    }

    public static Object showPromptMessage(JComponent comp, String message, String def, List lst, Object init) {
        return JOptionPane.showInputDialog(comp, message, def, JOptionPane.QUESTION_MESSAGE, null, lst.toArray(), init);
    }

    public static void showError(JComponent comp, String message) {
    	if (Common2View.mainView!=null){
            if (comp!=null) {
            	comp.requestFocus();
            }
            JOptionPane.showMessageDialog(comp, message, "", JOptionPane.ERROR_MESSAGE);
    	}
    	else {
    		util.Log.info(message);
    	}
    }

    public static boolean isOk(JComponent comp, String message) {
        int i = JOptionPane.showConfirmDialog(comp, message, "", JOptionPane.OK_CANCEL_OPTION);
        return i == JOptionPane.OK_OPTION;
    }

    public static boolean isInteger(String integer) {
        try {
            Integer.parseInt(integer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String dbl) {
        try {
            Double.parseDouble(dbl);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getIntValue(String intValue) {
        if (isInteger(intValue)) {
            return Integer.parseInt(intValue);
        }
        return 0;
    }

    public static double getDoubleValue(String dblValue) {
        if (isDouble(dblValue)) {
            return Double.parseDouble(dblValue);
        }
        return 0;
    }

    public static boolean isGreaterThan(String value, double val) {
        double d = getDoubleValue(value);
        return d > val;
    }

    public static boolean isLessThan(String value, double val) {
        double d = getDoubleValue(value);
        return d < val;
    }

    public static boolean isEqual(String value, double val) {
        double d = getDoubleValue(value);
        return d == val;
    }

    public static void showMessageToScreen(final String message) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                if (Common2View.mainView==null) return;
                Common2View.mainView.statusMessageLabel.setForeground(Color.BLUE);
                Common2View.mainView.statusMessageLabel.setText(message);
                Common2View.mainView.statusMessageLabel.updateUI();
            }
        });
        t.start();
    }

    public static void showErrorMessageToScreen(final String message) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                if (Common2View.mainView==null) return;
                Common2View.mainView.lblClearCache.setForeground(Color.RED);
                Common2View.mainView.lblClearCache.setText(message);
                Common2View.mainView.lblClearCache.updateUI();
            }
        });
        t.start();
    }

    public static int getFormattedDate(Date date) {
        try {
            SimpleDateFormat sdfTo = new SimpleDateFormat("yyyyMMdd");
            return Integer.parseInt(sdfTo.format(date).toUpperCase());
        } catch (Exception e) {
        }
        return 0;
    }

    public static boolean isNotLaterToday(Date date) {
        int mydate = getFormattedDate(date);
        int today = getFormattedDate(constants.Constants.useDate);
        return mydate >= today;
    }

//    public static void hideColumn(JTable tbl, int index) {
//        TableColumnModel colModel = tbl.getColumnModel();
//        TableColumn col = colModel.getColumn(index);
//        colModel.removeColumn(col);
//    }

    public static void setColumnWidth(JTable tbl, int index, int width) {
        TableColumnModel colModel = tbl.getColumnModel();
        colModel.getColumn(index).setWidth(width);
        colModel.getColumn(index).setPreferredWidth(width);
        colModel.getColumn(index).setMaxWidth(width);
        colModel.getColumn(index).setMinWidth(width);
    }

    public static void manipulateButtons(final JButton newButton, final JButton saveButton, final JButton refreshButton) {
        saveButton.setEnabled(false);
        newButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                newButton.setEnabled(false);
                saveButton.setEnabled(true);
                refreshButton.setEnabled(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                newButton.setEnabled(true);
                refreshButton.setEnabled(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                newButton.setEnabled(true);
                refreshButton.setEnabled(true);
            }
        });
    }

    public static void savingError(final JButton newButton, final JButton saveButton, final JButton refreshButton) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                newButton.setEnabled(false);
                saveButton.setEnabled(true);
                refreshButton.setEnabled(true);
            }
        });
    }

    public static void setTitle(String title) {
//        TitledBorder tBorder = (TitledBorder) Common2View.mainView.pnlMain.getBorder();
//        tBorder.setTitle(title);
//
//        frame.setTitle(Constants.appTitle + " - " + title);
    }
    
    public static void showHelp() {
        PanelHelp.showHelp();
    }

    public static AbstractPanel getActivePanel(Component comp) {
        if (comp==null) {
            //return mainpanel
            return MainWindow.getDisplayedModule();
        }
        Component parent = comp.getParent();
        if (parent instanceof AbstractPanel) {
            return (AbstractPanel) parent;
        } else {
            return getActivePanel(parent);
        }
    }

    public static JPanel getMySubPanel(Component comp) {
        if (comp==null) {
            //return mainpanel
            return MainWindow.getDisplayedModule();
        }
        Component parent = comp.getParent();
        if (parent instanceof AbstractSubPanel) {
            return (AbstractSubPanel) parent;
        } else {
            return getMySubPanel(parent);
        }
    }

    public static boolean inMainPanel(Component comp) {
        if (comp==null) return false;
        Component parent = comp.getParent();
        if (parent instanceof AbstractPanel) {
            return true;
        } else if (parent instanceof AbstractSubPanel) {
            return false;
        } else {
            return inMainPanel(parent);
        }
    }

    @SuppressWarnings(value = "unchecked")
    public static void setListData(Collection target, Collection source) {
    	clearList(target);
        if (source != null && source.size()>0 && target != null) {
            target.addAll(source);
            source = null;
        }
    }

    public static void clearList(Collection target) {
        if (target != null && target.size() > 0) {
        	for (Object obj:target) {
        		obj = null;
        	}
            target.clear();
        }
    }
    
    public static void setTableHeaderLabel(JTable tbl, String header, int index) {
        TableColumn col = tbl.getTableHeader().getColumnModel().getColumn(index);
        col.setHeaderValue(header);
    }
    
    public static void hideColumn(JTable tbl, int index) {
        TableColumn column = tbl.getTableHeader().getColumnModel().getColumn(index);
        tbl.getTableHeader().getColumnModel().removeColumn(column);
    }
    
    public static Object getTreeObject(Class cls, TreeNode node) {
        if (node==null) return null;
        DefaultMutableTreeNode dnode = (DefaultMutableTreeNode) node;
        if (dnode.getUserObject().getClass()==cls) {
            return dnode.getUserObject();
        }
        else {
            return getTreeObject(cls, dnode.getParent());
        }
    }

    public static Object getTreeObject(Class cls, JTree tnode) {
        if (tnode==null) return null;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tnode.getSelectionModel().getSelectionPath().getLastPathComponent();
        return getTreeObject(cls, node);
    }
    
    public static File showSelectFile(String title, String defaultFilename, JComponent comp) {
        JFileChooser chooser = new JFileChooser(title);
        chooser.setSelectedFile(new File(defaultFilename)); 
        if (chooser.showSaveDialog(comp)==JFileChooser.APPROVE_OPTION) {
            java.io.File f = chooser.getSelectedFile();
            return f;
        }
        return null;
    }

    public static File showOpenFile(String title, String defaultFilename, JComponent comp) {
        JFileChooser chooser = new JFileChooser(title);
        chooser.setSelectedFile(new File(defaultFilename)); 
        if (chooser.showOpenDialog(comp)==JFileChooser.APPROVE_OPTION) {
            java.io.File f = chooser.getSelectedFile();
            return f;
        }
        return null;
    }
    
    public static boolean contains(Object[] objArr, Object obj) {
        if (objArr==null || obj==null || objArr.length==0) return false;
        for (Object object : objArr) {
            if (object.toString().equals(obj.toString())) return true;
            if (object.equals(obj)) return true;
        }
        return false;
    }
    
    static String[] packages = {"","admin.","accounting.","person.","reference.","hr.","extension.","history.","training.","awb."};
    static Map<String, Class> mapBean = new HashMap<String, Class>();
    public static Class getBeanClass(String bean) {
        Class cls = mapBean.get(bean);
        if (cls!=null) return cls;
        if (bean.indexOf('.')!=-1) {
            try {
                cls = AAAConfig.getInstance().bootStrapClass().getClassLoader().loadClass(bean);
                mapBean.put(bean, cls);
                return cls;
            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
            }
        }
        for (String pack : packages) {
            try {
                cls = AAAConfig.getInstance().bootStrapClass().getClassLoader().loadClass("bean." + pack + bean);
                mapBean.put(bean, cls);
                return cls;
            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
            }
        }
        return cls;
    }
    
    public static boolean canBackup(String bean) {
        Class cls = getBeanClass(bean);
        if (cls==null) return false;
        UITemplate template = (UITemplate) cls.getAnnotation(UITemplate.class);
        if (template==null) {
            System.err.println("TEMPLATE NOT SET FOR BEAN "+bean);
            return false;
        }
        return template.canBackup();
    }

    public static boolean deleteOnUpload(String bean) {
        Class cls = getBeanClass(bean);
        UITemplate template = (UITemplate) cls.getAnnotation(UITemplate.class);
        if (template==null) {
            System.err.println("TEMPLATE NOT SET FOR BEAN "+bean);
            return false;
        }
        return template.deleteOnUpload();
    }
    
    public static String getLabel(FieldCompose f) {
        String fieldName = f.field.getName();
        String label = f.display.label();
        if (label==null || label.isEmpty()) {
            if (fieldName==null || fieldName.isEmpty()) return fieldName;
            label = getLabel(fieldName);
        }
        return label;
    }
    
    public static String getSearchLabel(FieldCompose f) {
        String label = f.display.searchLabel();
        if (label==null || label.isEmpty()) {
            return getLabel(f);
        }
        return label;
    }
    

    public static String getLabel(Field f, Display d) {
        String fieldName = f.getName();
        String label = d.label();
        if (label==null || label.isEmpty()) {
            if (fieldName==null || fieldName.isEmpty()) return fieldName;
            label = getLabel(fieldName);
        }
        return label;
    }

    public static String getLabel(String fieldName, Display d) {
        String label = d==null?fieldName:d.label();
        if (label==null || label.isEmpty()) {
            label = getLabel(fieldName);
        }
        else {
            label = getLabel(label);
        }
        return label;
    }

    public static String getLabel(String label) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < label.length(); i++) {
            char c = label.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(' ');
            }
            sb.append(c);
        }
        String lbl = "";
        try {
            lbl = Character.toUpperCase(sb.charAt(0))+sb.substring(1);
            if (lbl.endsWith(" Ext")) {
                return lbl.substring(0, lbl.length()-4).trim();
            }
        }
        catch (Exception e) {
        }
        return lbl;
    }

    public static String getTitle(String bean) {
        Class cls = PanelUtil.getBeanClass(bean);
        return getTitle(cls);
    }

    public static String getTitle(Class cls) {
        UITemplate template = (UITemplate) cls.getAnnotation(UITemplate.class);
        if (template.title()==null || template.title().isEmpty()) return getLabel(cls.getSimpleName());
        return template.title();
    }
    
    public static String getDescription(Class cls) {
        UITemplate template = (UITemplate) cls.getAnnotation(UITemplate.class);
        if (template.description()==null || template.description().isEmpty()) return getLabel(cls.getSimpleName());
        return template.description();
    }

    public static AbstractIBean getSelectedRecord(JTable tbl) {
        return (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
    }
    
    public static void scroll(JTable tbl, int trow) {
        int row = trow;
        if (trow==-1) {
            row = tbl.getSelectedRow();
        }
        if (row>=0) {
            Rectangle rec = tbl.getCellRect(row, 0, true);
            tbl.scrollRectToVisible(rec);
        }
    }
    
    public static String changeGlobalVar(String str) {
        if (str.indexOf('$')>0) {
            return ELProperty.create(str).getValue(GlobalBean.getInstance()).toString();
//            return ScriptRunner.runFreeMarkerWeb(str, UtilityBean.getInstance()).toString();
        }
        return str;
    }
    
    public static void runLater(Object obj, String methodName) {
        SwingUtilities.invokeLater(new runLater(obj, methodName));
    }
    
    static class runLater implements Runnable {
        Object obj;
        String methodName;
        runLater(Object obj, String methodName) {
            this.obj = obj;
            this.methodName = methodName;
        }
        @Override
        public void run() {
            try {
                obj.getClass().getMethod(methodName, null).invoke(obj, null);
            } catch (Exception ex) {
                Logger.getLogger(PanelUtil.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
        }
    }
    
    public static String getXML(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder x = new XMLEncoder(baos);
        x.writeObject(obj);
        x.close();
        String str = "";
        try {
            str = baos.toString();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(PanelUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    
    public static void displayToFrame(JPanel pnl) {
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.getContentPane().setLayout(new GridLayout());
        fr.getContentPane().add(pnl);
        fr.pack();
        fr.setVisible(true);
    }
    
    public static void updateColor(JPanel pnl) {
        if (constants.Constants.panelBackground==null) {
            String panelColor = AppConfig.extractPanelBackground();
            String labelColor = AppConfig.extractLabelColor();
            try {
                String[] arr1 = panelColor.split(" ");
                String[] arr2 = labelColor.split(" ");
                Color c1 = PanelUtil.getColor(arr1);
                Color c2 = PanelUtil.getColor(arr2);
                constants.Constants.panelBackground = c1;
                constants.Constants.labelColor = c2;
            }
            catch (Exception e) {
            }
        }
        if (constants.Constants.panelBackground!=null) {
            pnl.setBackground(constants.Constants.panelBackground);
            Border b = pnl.getBorder();
            if (b instanceof TitledBorder) {
                TitledBorder t = (TitledBorder) b;
                t.setTitleColor(constants.Constants.labelColor);
            }
        }
    }
    
    public static void updateColor(JLabel lbl) {
        if (constants.Constants.labelColor!=null) {
            lbl.setForeground(constants.Constants.labelColor);
        }
    }

    public static void updateColor(JButton btn) {
        if (constants.Constants.labelColor!=null) {
            btn.setForeground(constants.Constants.labelColor);
            btn.setBackground(constants.Constants.panelBackground);
        }
    }
    
    public static Color getColor(String[] arr) {
        try {
            if (arr.length<3) return null;
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            int c = Integer.parseInt(arr[2]);
            Color clr = new Color(a,b,c);
            return clr;
        }
        catch (Exception e) {
        }
        return null;
    }

    public static String getColorStr(Color col) {
        if (col==null) return "";
        String a = col.getRed()+"";
        String b = col.getGreen()+"";
        String c = col.getBlue()+"";
        return a+" "+b+" "+c;
    }

    public static void popupBeanTemplate(Class bean, String title, boolean showAdd) {
        popupBeanTemplate(bean, title, showAdd, new ArrayList());
    }
    
    public static void popupBeanTemplate(Class bean, String title, boolean showAdd, AbstractIBean ibean) {
    	List lst = new ArrayList();
    	lst.add(ibean);
        popupBeanTemplate(bean, title, showAdd, lst);
    }
    
    public static void popupBeanTemplate(Class bean, String title, boolean showAdd, AbstractIBean ibean, boolean hideSearch) {
    	List lst = new ArrayList();
    	lst.add(ibean);
        popupBeanTemplate(bean, title, showAdd, lst, hideSearch);
    }

    public static void popupBeanTemplate(Class bean, String title, boolean showAdd, List beans) {
        popupBeanTemplate(bean, title, showAdd, beans, false);
    }
    
    public static void popupBeanTemplate(Class bean, String title, boolean showAdd, List beans, final boolean hideSearch) {
        final AbstractTemplatePanel view = TemplateReader.getTemplate(bean);
        if (beans!=null) {
            List lst = view.list;
            if (lst!=null && lst.size()>0) lst.clear();
            lst.addAll(beans);
        }
        JPanel pnl = (JPanel)view.getMainForm();
        JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.add(pnl, BorderLayout.CENTER);
        JPanel pnlBtn = new JPanel();
        JButton btnNew = new JButton("New");
        JButton btnSave = new JButton("Save");
        JButton btnPrev = new JButton("  <<  ");
        JButton btnNext = new JButton("  >>  ");
        JButton btnMore = new JButton("More");
        if (!hideSearch) {
            pnlBtn.add(btnPrev);
            pnlBtn.add(btnNext);
            pnlBtn.add(btnMore);
            pnlBtn.add(btnNew);
        }
        pnlBtn.add(btnSave);
        if (showAdd) {
        	tmp.add(pnlBtn, BorderLayout.SOUTH);
        }
        if (hideSearch) {
        	view.hideSearchCriteria();
        	view.hideSearchResult();
        }
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.searchMoreRecords();
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.prevRecord();
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.nextRecord();
            }
        });
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.newRecord();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (hideSearch) {
                	AbstractIBean b = (AbstractIBean) view.getBean();
                	b.save();
            	}
            	else {
                    view.saveRecord();
            	}
            }
        });
        if (beans!=null && beans.size()>0) {
        	view.firstRecord();
        }
        showDialog(tmp, title);
    }

    public static void setCenterLocation(JFrame comp) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        comp.setLocation(dim.width-140, 0);
    }
    
    public static void setupCursor(Object comp) {
//    	try {
//        	((JComponent)comp).addFocusListener(new FocusAdapter() {
//    			@Override
//    			public void focusGained(FocusEvent arg0) {
//    				((JComponent)arg0.getSource()).setCursor(constants.Constants.currentCursor);
//    			}
//        	});
//    	}
//    	catch (Exception e) {
//    	}
    }
    
    public static void setCenterLocation(JDialog comp) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        comp.setLocation(dim.width-140, 0);
    }
    
    public static void displayWaitMessage(final String message) {
    	if (Common2View.mainView!=null) Common2View.mainView.getFrame().setCursor(constants.Constants.busyCursor);
    }

    public static void hideWaitMessage() {
    	if (Common2View.mainView!=null) Common2View.mainView.getFrame().setCursor(constants.Constants.defaultCursor);
    }
    
  static JDialog frWait;
    public static void showWaitFrame() {
    	showWaitFrame("Please wait...");
    }

    public static void showWaitFrame(final String title) {
    	if (frWait==null) {
    		frWait = new JDialog();
	    	frWait.setTitle("Please wait...");
	        frWait.setLayout(new GridLayout());
	        frWait.setModal(true);
	    	frWait.removeAll();
	        frWait.pack();
	        frWait.setSize(250, 10);
	        frWait.setLocationRelativeTo(null);
	        frWait.setAlwaysOnTop(true);
    	}
    	Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
		    	frWait.setTitle(title);
		    	frWait.setVisible(true);
			}
    	});
    	t.start();
    }

    public static void hideWaitFrame() {
    	if (frWait!=null) {
    		frWait.setVisible(false);
    	}
    }

	public static void updateColor(JSlider slidContrast) {
        if (constants.Constants.labelColor!=null) {
        	slidContrast.setForeground(constants.Constants.labelColor);
        }
	}
}
