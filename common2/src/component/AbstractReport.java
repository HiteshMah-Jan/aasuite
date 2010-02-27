/*
 * AbstractReportViewer.java
 *
 * Created on Nov 3, 2007, 7:13:55 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import bean.admin.SoftLabConfig;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import oracle.toplink.essentials.sessions.DatabaseLogin;
import service.ReturnStruct;
import service.util.CallService;
import util.DataUtil;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractReport {

    public abstract String getJRXML();

    public AbstractReportParameterViewer getParamViewer() {
        try {
            java.lang.String viewer = "report.viewer." + this.getClass().getSimpleName() + "Viewer";
            return (AbstractReportParameterViewer) java.lang.Class.forName(viewer).newInstance();
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void showReport() {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            String xml = getJRXML();
            ReturnStruct ret = CallService.callService(xml, 1, "service.Report");
//			ByteArrayInputStream bais = new ByteArrayInputStream(ret.getData().toString().getBytes());
//			jasperReport = JasperCompileManager.compileReport(bais);
//			bais.close();
            jasperReport = (JasperReport) ret.getData();
            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), createDatasource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract List getDataCollection();

    public abstract Object getFieldValue(String field, Object obj);

    public JRDataSource createDatasource() {
        JRDataSource source = new AAAJRDataSource(getDataCollection(), this);
        return source;
    }

    private static class AAAJRDataSource implements JRDataSource {

        List coll;
        AbstractReport report;
        Object obj;
        int i;

        AAAJRDataSource(List lcoll, AbstractReport lreport) {
            coll = lcoll;
            report = lreport;
        }

        public boolean next() throws JRException {
            if (coll == null) {
                return false;
            }
            if (i >= coll.size()) {
                return false;
            }
            obj = coll.get(i);
            i++;
            return true;
        }

        public Object getFieldValue(JRField arg0) throws JRException {
            return report.getFieldValue(arg0.getName(), obj);
        }
    }

    public static JasperReport compileReport(File f) {
        try {
            return net.sf.jasperreports.engine.JasperCompileManager.compileReport(new java.io.FileInputStream(f));
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static JasperReport compileReport(InputStream is) {
        try {
            return net.sf.jasperreports.engine.JasperCompileManager.compileReport(is);
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JasperReport compileReport(String filename) {
//        File f = new File(constants.Constants.ROOT_FOLDER+"tmp/designing/"+filename+".jrxml");
        return compileReport(DataUtil.getReportStream(filename));
    }

    public static List<JRParameter> getReportParameters(JasperReport report) {
        List<JRParameter> lst = new ArrayList<JRParameter>();
        JRParameter[] params = report.getParameters();
        for (JRParameter param:params) {
            String name = param.getName();
            if (!param.isSystemDefined() && !name.toUpperCase().startsWith("REPORT")) {
                lst.add(param);
            }
        }
        return lst;
    }

    public static List<JRParameter> getReportSpecialParameters(JasperReport report) {
        List<JRParameter> lst = new ArrayList<JRParameter>();
        JRParameter[] params = report.getParameters();
        for (JRParameter param:params) {
            String name = param.getName();
            if (param.isSystemDefined() || name.toUpperCase().startsWith("REPORT")) {
                lst.add(param);
            }
        }
        return lst;
    }

    public static void putParameterToPanel(JPanel pnlParameter, List<JRParameter> params, Map<String, JComponent> mapFields, boolean displayOnly) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); 
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        
        pnlParameter.removeAll();
        pnlParameter.setLayout(new GridBagLayout());
        for (int i=0; i<params.size(); i++) {
            JRParameter param = params.get(i);
            String paramName = param.getName();
            Class paramClass = param.getValueClass();
            
            gridBagConstraints.gridy = i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            pnlParameter.add(new JLabel(paramName), gridBagConstraints);
            
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 150;
            
            if (displayOnly) {
                JLabel lbl = new JLabel("<< >>");
                if (param.getDefaultValueExpression()!=null) {
                    lbl.setText("<< "+param.getDefaultValueExpression().getText()+" >>");
                }
                pnlParameter.add(lbl, gridBagConstraints);
            }
            else {
                JTextComponent comp = new JTextFieldPallete(); 
                if (param.getDefaultValueExpression()!=null) {
                    comp.setText(param.getDefaultValueExpression().getText());
                }
                mapFields.put(paramName, comp);
                pnlParameter.add(comp, gridBagConstraints);
            }
        }
        pnlParameter.updateUI();
    }
    
    public static JasperReport compileReport(byte[] b) {
        try {
            return net.sf.jasperreports.engine.JasperCompileManager.compileReport(new ByteArrayInputStream(b));
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    static SoftLabConfig config;
    
    public static Connection getDBConnection() {
        EntityManager entityManager = javax.persistence.Persistence.createEntityManagerFactory(constants.Constants.PERSISTENCE_UNIT).createEntityManager();
        DatabaseLogin login = ((oracle.toplink.essentials.ejb.cmp3.EntityManager) entityManager).getServerSession().getLogin();
        return (Connection)login.connectToDatasource(null);
    }
    
    public static Connection getDBConnection2() {
        try {
            if (config==null) {
                ReturnStruct ret = CallService.callService("", -3, "service.Report");
                config = (SoftLabConfig) ret.getData();
            }
            java.lang.Class.forName(config.getDriver());
            Connection conn = null;
            if (config.getUsername()==null || config.getUsername().isEmpty()) {
                conn = DriverManager.getConnection(config.getUrl());
            }
            else {
//                System.out.println("PASSWORD == "+config.getPassword());
                try {
                    conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
                }
                catch (Exception e) {
                    if (conn==null) {
                        //maybe the password is empty
                        try {
                            config.setPassword(null);
                            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
                        }
                        catch (Exception e2) {
                            //maybe the password is password
                            config.setPassword("password");
                            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
                        }
                    }
                }
            }
            return conn;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
//        try {
//            if (conn!=null) conn.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        conn = null;
    }
    
    public static void displayReportToPanel(JPanel pnl, JasperReport jasperReport, Map map) {
        Connection conn = getDBConnection();
        try {
            net.sf.jasperreports.engine.JasperPrint jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(jasperReport, map, conn);
            net.sf.jasperreports.view.JRViewer viewer = new net.sf.jasperreports.view.JRViewer(jasperPrint);
            pnl.removeAll();
            pnl.setLayout(new GridLayout());
            pnl.add(viewer);
            pnl.updateUI();
        } catch (JRException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    
    public static void displayReportToPanel(JPanel pnl, JasperPrint jasperPrint) {
        try {
            net.sf.jasperreports.view.JRViewer viewer = new net.sf.jasperreports.view.JRViewer(jasperPrint);
            pnl.removeAll();
            pnl.setLayout(new GridLayout());
            pnl.add(viewer);
            pnl.updateUI();
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public static void displayReportToPanel(JPanel pnl, JasperReport jasperReport) {
        displayReportToPanel(pnl, jasperReport, new HashMap());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private static class ReportViewer extends JRViewer {
        public ReportViewer(JasperPrint arg0) {
            super(arg0);
        }        
    }
}
