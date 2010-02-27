/*
 * Report.java
 *
 * Created on Sep 8, 2007, 5:57:30 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import bean.admin.SoftLabConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import oracle.toplink.essentials.internal.ejb.cmp3.EntityManagerImpl;
import oracle.toplink.essentials.sessions.Session;
import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import util.DataUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class Report implements IService {

    private static Map<String, JasperReport> xmlMap = new HashMap<String, JasperReport>();

    public ReturnStruct callService(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        try {
            java.lang.String xml = (java.lang.String) param.getData();
            if (xmlMap.containsKey(xml)) {
                JasperReport jasper = xmlMap.get(xml);
                ret.setData(ret);
                return ret;
            }
//            System.out.println("XML == " + xml);
            java.io.InputStream is = DataUtil.getResource(xml);
            JasperReport jasperReport = JasperCompileManager.compileReport(is);
            ret.setData(jasperReport);
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
