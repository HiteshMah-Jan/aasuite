/*
 * GroovyRunner.java
 *
 * Created on Jan 21, 2008, 5:42:35 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import bean.admin.BusinessRuleService;
import common2.Common2View;
import freemarker.template.Configuration;
import groovy.lang.GroovyShell;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import service.util.AbstractIBean;
import service.util.IBean;
import ui.DynamicAccessForm;

/**
 *
 * @author Entokwaa
 */
public class ScriptRunner {
    static Configuration cfg;
    static Configuration cfgClass;
    private static String allImports;
    
    private static String getAllImports() {
        if (allImports==null) {
            StringBuffer sb = new StringBuffer();
            sb.append("import bean.*;\n");
            sb.append("import bean.accounting.*;\n");
            sb.append("import bean.admin.*;\n");
            sb.append("import bean.extension.*;\n");
            sb.append("import bean.hr.*;\n");
            sb.append("import bean.person.*;\n");
            sb.append("import bean.reference.*;\n");
            sb.append("import component.*;\n");
            sb.append("import constants.*;\n");
            sb.append("import print.*;\n");
            sb.append("import report.*;\n");
            sb.append("import service.*;\n");
            sb.append("import service.adapter.*;\n");
            sb.append("import service.util.*;\n");
            sb.append("import springbean.*;\n");
            sb.append("import test.*;\n");
            sb.append("import test.formula.*;\n");
            sb.append("import ui.*;\n");
            sb.append("import ui.component.*;\n");
            sb.append("import ui.subpanel.*;\n");
            sb.append("import util.*;\n");
            sb.append("import java.util.*;\n");
            sb.append("import common2.*;\n");

            sb.append("def printc(Object str) {" +
                "\n     ScriptRunner.printConsole(str);" +
                "\n}\n");
            sb.append("def clearConsole() {" +
                "\n     console.text=\"\";" +
                "\n}\n");
            sb.append("def toDouble(String str) {" +
                "\ntry {" +
                "\n   return Double.parseDouble(str);" +
                "\n}" +
                "\ncatch (Exception e) {" +
                "\n   return 0;" +
                "\n}" +
                "\n}\n");
            sb.append("def robot = AbstractRobotTester.getInstance();\n\n");
            allImports = sb.toString();
        }
        return allImports;
    }
    
    private static Object runNow(String script) {
        Logger.getLogger("global").log(Level.INFO, "VARIABLE:SCRIPT = field:" + getAllImports() + script);
        try {
            GroovyShell engine = new GroovyShell();
            return engine.evaluate(getAllImports() + script); 
        } catch (Exception ex) {
            Logger.getLogger(ScriptRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Object runGroovy(String script, String name, Object obj) {
        String strCode = "";
        if (obj!=null && name!=null && !name.isEmpty()) {
            currentObject = obj;
            strCode += BeanUtil.concat("\ndef ",name," = ScriptRunner.currentObject;\n");
        }
        if (Common2View.mainView!=null) {
            strCode += "\ndef module = Common2View.getTransactionPanel();\n";
            strCode += "\ndef bean = module.bean;\n";
        }
        strCode += "\ndef now = constants.Constants.useDate;\n";
        strCode += "\nspringbean.AAAConfig.getInstance();\n";
        return runNow(strCode+script);
    }

    public static void runGroovyBackground(final String script, final String name, final Object obj) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String strCode = "";
                if (obj!=null && name!=null && !name.isEmpty()) {
                    currentObject = obj;
                    strCode += BeanUtil.concat("\ndef ",name," = ScriptRunner.currentObject;\n");
                }
                if (Common2View.mainView!=null) {
                    strCode += "\ndef module = Common2View.getTransactionPanel();\n";
                    strCode += "\ndef bean = module.bean;\n";
                }
                runNow(strCode+script);
            }
        });
    }

    public static Object runGroovyConsole(String script) {
        String strCode = "\ndef module = Common2View.getTransactionPanel();\n";
        strCode += "\ndef bean = module.bean;\n";
        Logger.getLogger("global").log(Level.INFO, "VARIABLE:SCRIPT = field:" + getAllImports() + script);
        try {
            GroovyShell engine = new GroovyShell();
            Object obj = engine.evaluate(getAllImports() + strCode + script); 
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(ScriptRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Object runFreeMarker(String file, Object obj) {
        try {
            if (cfg==null) {
                cfg = new Configuration();
                cfg.setTemplateLoader(new TemplateLoader());
                cfg.setObjectWrapper(new freemarker.template.DefaultObjectWrapper());
            }
            //to clear the cache
            cfg.clearTemplateCache();
            freemarker.template.Template temp = cfg.getTemplate(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Writer out = new OutputStreamWriter(baos);
            temp.process(obj, out);
            out.flush();
            String str = new String(baos.toByteArray());
            return str;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static Object runFreeMarkerWeb(String file, Object obj) {
        try {
            if (cfgClass==null) {
                cfgClass = new Configuration();
                cfgClass.setTemplateLoader(new WebTemplateLoader());
                cfgClass.setObjectWrapper(new freemarker.template.DefaultObjectWrapper());
            }
            //to clear the cache
            cfgClass.clearTemplateCache();
            freemarker.template.Template temp = cfgClass.getTemplate(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Writer out = new OutputStreamWriter(baos);
            temp.process(obj, out);
            out.flush();
            String str = new String(baos.toByteArray());
            return str;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return "";
    }

    static class TemplateLoader extends freemarker.cache.URLTemplateLoader {
        protected URL getURL(String arg0) {
            StringTokenizer st = new StringTokenizer(arg0, "_");
            String resource = BeanUtil.concat("report/template/",st.nextToken(),".htm");
            URL url = this.getClass().getClassLoader().getResource(resource);
            return url;
        }
    }

    static class WebTemplateLoader extends freemarker.cache.URLTemplateLoader {
        protected URL getURL(String arg0) {
            StringTokenizer st = new StringTokenizer(arg0, "_");
            String resource = BeanUtil.concat("template/screen/web/text/",st.nextToken(),".txt");
            Logger.getLogger("global").log(Level.INFO, "Resource = ",resource);
            URL url = this.getClass().getClassLoader().getResource(resource);
            return url;
        }
    }
    
    public static void printConsole(Object str) {
        JTextComponent comp = DynamicAccessForm.form.txtConsole;
        if (str instanceof AbstractIBean) {
            if (comp==null) {
                Logger.getLogger("global").log(Level.INFO, BeanUtil.getPrintedBean((IBean) str));
            }
            else {
                comp.setText(BeanUtil.getPrintedBean((IBean) str));
            }
        }
        else {
            String txt = BeanUtil.concat(comp.getText(),"\n",str.toString());
            if (comp==null) {
                Logger.getLogger("global").log(Level.INFO, txt);
            }
            else {
                comp.setText(txt);
            }
        }
    }

    public static Object currentObject;
    static List<BusinessRuleService> lst;

    public static void runServices() {
        lst = AbstractIBean.list("SELECT a FROM BusinessRuleService a");
        for (BusinessRuleService b : lst) {
            String month = b.getRunMonth();
            String weekday = b.getRunDay();
            int time = Integer.parseInt(b.getRunTime());
            
            if (!DateUtil.getMonthName().startsWith(month)) continue;
            if (!DateUtil.getWeekdayName().startsWith(weekday)) continue;
            if (time>0 && DateUtil.getIntTime()!=time) continue;
            
            Thread t = new myThreadService(b);
            t.start();
        }
    }

    public static void startThreadServices() {
        Timer t = new Timer(); 
        t.schedule(new myService(), 1000, 800);
    }
    
    static class myThreadService extends Thread {
        BusinessRuleService b;
        myThreadService(BusinessRuleService b) {
            this.b = b;
        }
        public void run() {
            String rule = b.getRule();
            runGroovy(rule, "param", b.getAllParam());
        }
    }
    
    static class myService extends TimerTask {
        public void run() {
            runServices();
        }
    }
}
