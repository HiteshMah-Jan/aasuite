/*
 * ReportDesigner.java
 *
 * Created on Mar 7, 2008, 8:18:46 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.reportdesigner;

import bean.admin.DynamicReport;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import util.DataUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class ReportDesigner extends it.businesslogic.ireport.gui.MainFrame {

    static ReportDesigner designer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static void setupResources() {
        try {
            PanelUtil.showMessage(null, "Note: You must save your design to folder "+constants.Constants.ROOT_FOLDER + "tmp/designing.");
            java.io.InputStream is = java.lang.ClassLoader.getSystemResourceAsStream("resources/reportdesigner.zip");
            if (is==null) {
            	return;
            }
            util.DataUtil.writeToFile(is, constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip");
            
            DataUtil.unzipFile(new File(constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip"), constants.Constants.ROOT_FOLDER + "tmp");
            (new File(constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip")).delete();
            
            System.setProperty("ireport.home", constants.Constants.ROOT_FOLDER + "tmp/reportdesigner");
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public static void setupReportResources() {
        try {
            File f = new File(constants.Constants.ROOT_FOLDER+"tmp/designing");
            if (f.exists() && f.isDirectory()) return;
            f = new File(constants.Constants.ROOT_FOLDER+"tmp");
            if (!f.exists()) f.mkdir();
            java.io.InputStream is = DataUtil.getResource("resources/reportdesigner.zip");
            if (is==null) {
            	return;
            }
        	util.DataUtil.writeToFile(is, constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip");
            
            DataUtil.unzipFile(new File(constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip"), constants.Constants.ROOT_FOLDER + "tmp");
            (new File(constants.Constants.ROOT_FOLDER + "tmp/reportdesigner.zip")).delete();
            
            System.setProperty("ireport.home", constants.Constants.ROOT_FOLDER + "tmp/reportdesigner");
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    public static File[] getDesignFiles() {
        //look into the folder of tmp/designing
        File f = new File(constants.Constants.ROOT_FOLDER+"tmp/designing");
        if (!f.exists()) {
            f.mkdir();
        }
        return f.listFiles();
    }
    
    public static void saveAllDesign() {
        File[] lst = getDesignFiles();
        for (int i=0; lst!=null && i<lst.length; i++) {
            saveDesign(lst[i], lst[i].getName(), i);
        }
    }
    
    public static void saveDesign(File f, String title, int sort) {
        try {
            byte[] b = util.DataUtil.getBytes(new java.io.FileInputStream(f));
            DynamicReport report = new DynamicReport();
            report.setCode(f.getPath());
            report.setReportName(f.getName());
            report.setXmlContent(new String(b));
            report.setSortNumber(sort);
            report.setReportTitle(title);
            report.save();
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showMe(final File f) {
        setupDesigner();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                designer.openFile(f);
            }
        });
    }
    
    public static void showMe() {
        setupDesigner();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                designer.newWizard();
            }
        });
    }
    
    private static void setupDesigner() {
        if (designer == null) {
            //extract the jar to get all the files inside the resource
            setupResources();

            designer = new ReportDesigner();
            Properties prop = designer.getBrandingProperties();
            prop.setProperty("ireport.title", "AAA Report Designer");
            designer.setBrandingProperties(prop);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    designer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    WindowListener[] listeners = designer.getWindowListeners();
                    for (WindowListener listen : listeners) {
                        designer.removeWindowListener(listen);
                    }
                }
            });
        }
        designer.pack();
        designer.setVisible(true);
    }
}
