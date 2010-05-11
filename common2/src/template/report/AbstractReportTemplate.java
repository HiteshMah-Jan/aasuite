/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.report;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.beansbinding.ELProperty;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import service.util.IBean;
import template.Report;
import template.UITemplate;
import util.BeanUtil;
import util.DataUtil;
import util.DateUtil;
import util.NetworkUtil;
import util.PanelUtil;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.ImageScaleMode;
import bean.Person;
import bean.admin.AppConfig;

import common2.Common2View;
import component.AbstractReport;
import component.ReportParamDialog;

import constants.Constants;

/**
 *
 * @author Entokwaa
 */
public class AbstractReportTemplate implements Serializable, IService {
//    note: only one will have a value, we check the bean first as the default value

    Class bean;
    Report report;
    UITemplate template;

    public AbstractReportTemplate() {
        
    }
    
    public static AbstractReportTemplate getInstance() {
        return new AbstractReportTemplate();
    }

    public static AbstractReportTemplate getInstance(Class bean) {
        try {
            UITemplate template = (UITemplate) bean.getAnnotation(UITemplate.class);
            Class reportTemplate = template.reportTemplate();
            AbstractReportTemplate tmp = (AbstractReportTemplate) reportTemplate.newInstance();
            tmp.bean = bean;
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static AbstractReportTemplate getInstance(Report report) {
        try {
            AbstractReportTemplate tmp = (AbstractReportTemplate) report.reportTemplate().newInstance();
            tmp.report = report;
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void showReport() {
        if (report == null || report.reportFile() == null || report.reportFile().isEmpty()) {
            try {
                List lst = AbstractIBean.list(getSql());
                if (lst == null || lst.size() == 0) {
                    PanelUtil.showError(null, "No data found");
                    return;
                }
                for (Object elem : lst) {
                    AbstractIBean b = (AbstractIBean) elem;
                }
//                System.out.println("LIST == " + lst.size());
                JRDataSource ds = new MyJRDSCls(lst);
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(getBuildReport(), new ClassicLayoutManager(), ds);
                JasperViewer.viewReport(jp, false); //finally display the report

            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showReportFromFileTemplate();
        }
    }

    public byte[] getAllRecordReport() {
        String reportDest = "r" + constants.Constants.useDate.getTime() + ".pdf";
        try {
            List lst = AbstractIBean.list(getSql());
            if (lst == null || lst.size() == 0) {
                PanelUtil.showError(null, "No data found");
                return null;
            }
            //always make cache global for report
            for (Object elem : lst) {
                AbstractIBean b = (AbstractIBean) elem;
            }
//            System.out.println("LIST == " + lst.size());
            JRDataSource ds = new MyJRDSCls(lst);
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(getBuildReport(), new ClassicLayoutManager(), ds);
            JasperExportManager.exportReportToPdfFile(jp, reportDest);
        } catch (Exception ex) {
            Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        File f = new File(reportDest);
        if (f.exists()) {
            try {
                int l = (int) f.length();
                byte[] b = new byte[l];
                RandomAccessFile raf = new RandomAccessFile(f, "r");
                raf.read(b);
                raf.close();
                f.delete();
                return b;
            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void showReportToPanel(JPanel pnl, AbstractIBean bean) {
        if (report == null || report.reportFile() == null || report.reportFile().isEmpty()) {
            try {
                List lst = AbstractIBean.list(getSql());
                if (lst == null || lst.size() == 0) {
                    PanelUtil.showError(null, "No data found");
                    return;
                }
                for (Object elem : lst) {
                    AbstractIBean b = (AbstractIBean) elem;
                }
//                System.out.println("LIST == " + lst.size());
                JRDataSource ds = new MyJRDSCls(lst);
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(getBuildReport(), new ClassicLayoutManager(), ds);
                AbstractReport.displayReportToPanel(pnl, jp);
            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showReportFromFileTemplate(pnl, bean);
        }
    }

    public void showReport(List lst) {
        if (report == null || report.reportFile() == null || report.reportFile().isEmpty()) {
            try {
                if (lst == null || lst.size() == 0) {
                    PanelUtil.showError(null, "No data found");
                    return;
                }
                for (Object elem : lst) {
                    AbstractIBean b = (AbstractIBean) elem;
                }
//                System.out.println("LIST == " + lst.size());
                JRDataSource ds = new MyJRDSCls(lst);
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(getBuildReport(), new ClassicLayoutManager(), ds);
                JasperViewer.viewReport(jp, false); //finally display the report

            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showReportFromFileTemplate();
        }
    }

    public void showReportToPanel(List lst, JPanel pnl) {
        if (report == null || report.reportFile() == null || report.reportFile().isEmpty()) {
            try {
                if (lst == null || lst.size() == 0) {
                    PanelUtil.showError(null, "No data found");
                    return;
                }
                for (Object elem : lst) {
                    AbstractIBean b = (AbstractIBean) elem;
                }
//                System.out.println("LIST == " + lst.size());
                JRDataSource ds = new MyJRDSCls(lst);
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(getBuildReport(), new ClassicLayoutManager(), ds);
                AbstractReport.displayReportToPanel(pnl, jp);
            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showReportFromFileTemplate(pnl, null);
        }
    }

    public Map getReportParameter(String addWhere, Map map) {
    	map.put("VIEW_IMAGE", new Boolean(false));
    	if (addWhere!=null) {
    		map.put("REPORT_ADD_WHERE", addWhere.trim());
    	}
    	System.out.println("ADD WHERE == "+addWhere);
        return map;
    }

    private static final String REP_NAME = "XXX_REPORT_XXX";
    private static Map<String, JasperReport> clientJasperCache = new HashMap<String, JasperReport>();
    public JasperReport getJasperReport(String file) {
        JasperReport rep = clientJasperCache.get(file);
        if (rep==null) {
        	String cacheId = "REPORT-"+file;
        	rep = (JasperReport) NetworkUtil.requestCache(cacheId, null);
        	if (rep == null) {
            	ReturnStruct ret = CallService.callService(file, 1, "template.report.AbstractReportTemplate");
                if (ret!=null && ret.getData()!=null && ret.getData() instanceof JasperReport) {
                    rep = (JasperReport) ret.getData();
                    NetworkUtil.requestCache(cacheId, rep);
                    clientJasperCache.put(file, rep);
                }
        	}
        }
        return rep;
    }
    
    public JasperPrint getJasperPrint(JasperReport rep, Map parameter) {
        parameter.put(REP_NAME, rep);
    	PanelUtil.showWaitFrame();
    	try {
            ReturnStruct ret = CallService.callService(parameter, 2, "template.report.AbstractReportTemplate");
        	PanelUtil.hideWaitFrame();
            if (ret!=null && ret.getData()!=null && ret.getData() instanceof JasperPrint) {
            	JasperPrint print = (JasperPrint) ret.getData();
//            	System.out.println("SIZE=="+ret.size());
            	return print;
            }
    	}
    	catch (Exception e) {
        	PanelUtil.hideWaitFrame();
    	}
    	PanelUtil.hideWaitFrame();
        return null;
    }

    public void showReportFromFileTemplate() {
        String file = report.reportFile();
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", report.reportFile(), report.reportFile());
//        }
        JasperReport rep = getJasperReport(file);
        Map map = new HashMap();
        ReportParamDialog.displayParameters(rep, map);
        String addWhere = null;
        try {
            Object mybean = Common2View.getTransactionPanel().getBean();
            addWhere = ELProperty.create(report.reportSql()).getValue(mybean).toString();
        } catch (Exception e) {
        }
        getReportParameter(addWhere, map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
    	JasperViewer.viewReport(jasperPrint, false);
    }

    public byte[] getReportFromFileTemplate(AbstractIBean bean) {
        String file = report.reportFile();
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", report.reportFile(), report.reportFile());
//        }
        JasperReport rep = getJasperReport(file);
        Map map = new HashMap();
        ReportParamDialog.displayParameters(rep, map);
        String addWhere = null;
        String reportDest = "r" + constants.Constants.useDate.getTime() + ".pdf";
        try {
            Object mybean = bean;
            addWhere = ELProperty.create(report.reportSql()).getValue(mybean).toString();
        } catch (Exception e) {
        }
        try {
            getReportParameter(addWhere, map);
            JasperPrint jasperPrint = getJasperPrint(rep, map);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
        } catch (Exception e) {
        }

        File f = new File(reportDest);
        if (f.exists()) {
            try {
                int l = (int) f.length();
                byte[] b = new byte[l];
                RandomAccessFile raf = new RandomAccessFile(f, "r");
                raf.read(b);
                raf.close();
                f.delete();
                return b;
            } catch (Exception ex) {
                Logger.getLogger(AbstractReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void showReportFromFileTemplate(JPanel pnl, AbstractIBean bean) {
        String file = report.reportFile();
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", report.reportFile(), report.reportFile());
//        }
        JasperReport rep = getJasperReport(file);
        Map map = null;
        if (bean!=null) {
            map = bean.extractReportParameter();
        }
        else {
            map = new HashMap();
        }
        ReportParamDialog.displayParameters(rep, map);
        String addWhere = null;
        try {
            Object mybean = Common2View.getTransactionPanel().getBean();
            addWhere = ELProperty.create(report.reportSql()).getValue(mybean).toString();
        } catch (Exception e) {
        }
        getReportParameter(addWhere, map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
        AbstractReport.displayReportToPanel(pnl, jasperPrint);
    }

    public void showReportFromFileTemplate(String file, Object addWhere, JPanel pnl, AbstractIBean bean) {
        JasperReport rep = getJasperReport(file);
        Map map = null;
        if (bean!=null) {
            map = bean.extractReportParameter();
        }
        else {
            map = new HashMap();
        }
        ReportParamDialog.displayParameters(rep, map);
        try {
            Object mybean = Common2View.getTransactionPanel().getBean();
        } catch (Exception e) {
        }
        getReportParameter(addWhere.toString(), map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
        AbstractReport.displayReportToPanel(pnl, jasperPrint);
    }

    public void showReportFromFileTemplate(String file, Object addWhere) {
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", file, file);
//        }
        JasperReport rep = getJasperReport(file);
        Map map = new HashMap();
        ReportParamDialog.displayParameters(rep, map);
        getReportParameter(addWhere.toString(), map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
        JasperViewer.viewReport(jasperPrint, false); //finally display the report
    }

    public void showReportFromFileTemplate(String file, Object addWhere, JPanel pnl) {
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", file, file);
//        }
        JasperReport rep = getJasperReport(file);
        Map map = new HashMap();
        ReportParamDialog.displayParameters(rep, map);
        getReportParameter(addWhere.toString(), map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
        AbstractReport.displayReportToPanel(pnl, jasperPrint);
    }

    public void showReportFromFileTemplateDialog(String file, Object addWhere, String title, JFrame pnl) {
//        if (AppConfig.isDynamicChangeReportFile()) {
//            file = AppConfig.getValue("REPORT", file, file);
//        }
        JasperReport rep = getJasperReport(file);
        Map map = new HashMap();
        ReportParamDialog.displayParameters(rep, map);
        getReportParameter(addWhere.toString(), map);
        JasperPrint jasperPrint = getJasperPrint(rep, map);
        JDialog dlg = new JDialog(pnl);
        dlg.setTitle(title);
        dlg.setLayout(new GridLayout());
        dlg.setModal(true);
        JPanel p = new JPanel();
        dlg.add(p);
        AbstractReport.displayReportToPanel(p, jasperPrint);
        dlg.pack();
        int height = jasperPrint.getPageHeight();
        int width = jasperPrint.getPageWidth();
        dlg.setSize(width+150, height+150);
        dlg.setVisible(true);
    }

    public void showReportFromFileTemplateDialog(JasperPrint jasperPrint, String title, JFrame pnl) {
        JDialog dlg = new JDialog(pnl);
        dlg.setTitle(title);
        dlg.setLayout(new GridLayout());
        dlg.setModal(true);
        JPanel p = new JPanel();
        dlg.add(p);
        AbstractReport.displayReportToPanel(p, jasperPrint);
        dlg.pack();
        int height = jasperPrint.getPageHeight();
        int width = jasperPrint.getPageWidth();
        dlg.setSize(width+150, height+150);
        dlg.setVisible(true);
    }
    
    protected DynamicReport getBuildReport() {
        try {
            if (bean != null) {
                template = (UITemplate) bean.getAnnotation(UITemplate.class);
                String[] strArr = template.columnSearch();
                if (template.reportFields() != null && template.reportFields().length > 0) {
                    strArr = template.reportFields();
                }
                FastReportBuilder drb = new FastReportBuilder();
                IBean b = (IBean) bean.newInstance();
                Style style = new StyleBuilder(false).setHorizontalAlign(HorizontalAlign.CENTER)
                .setBorderColor(Color.WHITE).build();

                for (String string : strArr) {
                    String label = PanelUtil.getLabel(string);
                    Class colClass = BeanUtil.getPropertyType(b, string);
                    if (colClass == boolean.class) {
                        drb = drb.addImageColumn(label, string, 50, true, ImageScaleMode.FILL_PROPORTIONALLY, style);
                    }
                    else {
                        drb = drb.addColumn(label, string, String.class.getName(), 30);
                    }
                }
                if (template.reportGroups() > 0) {
                    drb = drb.addGroups(template.reportGroups());
                }
                drb.addTitle(getTitle());
                //            drb.addSubtitle("This report was generateed at" + new Date());
                drb.addUseFullPageWidth(true);
                drb.setPrintColumnNames(true);
                drb.setIgnorePagination(true);
                DynamicReport dr = drb.build();
                return dr;
            } else {
                String[] strArr = report.reportFields();
                FastReportBuilder drb = new FastReportBuilder();
                for (String string : strArr) {
                    String label = PanelUtil.getLabel(string);
                    drb = drb.addColumn(label, string, String.class.getName(), 30);
                }
                if (report.reportGroups() > 0) {
                    drb = drb.addGroups(report.reportGroups());
                }
                drb.addTitle(getTitle());
                //            drb.addSubtitle("This report was generateed at" + new Date());
                drb.addUseFullPageWidth(true);
                DynamicReport dr = drb.build();
                return dr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getTitle() {
        String title = "";
        if (template != null) {
            title = template.reportTitle();
            if (title.isEmpty()) {
                title = PanelUtil.getTitle(bean) + " Report";
            }
        } else {
            title = report.reportTitle();
        }
        return title;
    }

    protected String getSql() {
        if (bean != null) {
            return PanelUtil.constructSql(bean);
        } else {
            return report.reportSql();
        }
    }

    public static void main(String[] args) throws Exception {
        AbstractReportTemplate test = AbstractReportTemplate.getInstance(Person.class);
        test.showReport();
    }

    private static class MyJRDSCls extends JRBeanCollectionDataSource {

        public MyJRDSCls(Collection arg0) {
            super(arg0);
        }

        @Override
        public Object getFieldValue(JRField arg0) throws JRException {
            Object obj = super.getFieldValue(arg0);
            if (obj == null) {
                return "";
            }
            if (obj instanceof Date) {
                return DateUtil.formatDate((Date) obj, "MMM dd, yyyy");
            }
            else if (obj instanceof Boolean) {
                try {
                    boolean b = (Boolean) obj;
//                    if (b) {
                        return getClass().getResource("/icons/check.png").openStream();
//                    }
//                    return null;
                }
                catch (Exception e) {
                    return null;
                }
            }
            return obj.toString();
        }
    }

    static Map<String, JasperReport> mapReports = new Hashtable<String, JasperReport>();
    
    public ReturnStruct callService(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        ret.setStatus(Constants.FAIL);
        if (param.getActionCommand()==1) {
//            get jasperreport
            String file = param.getData().toString();
            JasperReport rep = mapReports.get(file);
            if (rep==null) {
                rep = AbstractReport.compileReport(DataUtil.getReportStream(file));
                if (!AppConfig.isAlwaysCompileReport()) {
                    mapReports.put(file, rep);
                }
            }
            ret.setData(rep);
            ret.setStatus(Constants.SUCCESS);
//            System.out.println("SERIALIZE REPORT "+file);
        }
        else if (param.getActionCommand()==2) {
//            get jasperprint
            Map map = (Map) param.getData();
            JasperReport rep = (JasperReport) map.remove(REP_NAME);
            Connection conn = AbstractReport.getDBConnection();
            try {
            	map.put("VIEW_IMAGE", new Boolean(false));
                JasperPrint jasperPrint = JasperFillManager.fillReport(rep, map, conn);
                ret.setData(jasperPrint);
                ret.setStatus(Constants.SUCCESS);
            } catch (JRException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            } finally {
                AbstractReport.closeConnection(conn);
            }
        }
        return ret;
    }
}
