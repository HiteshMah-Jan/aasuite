/*
 * XMLTester.java
 * 
 * Created on Aug 9, 2008, 7:44:13 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import service.util.AbstractIBean;
import util.DataUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class XMLTester {
    public static void main(String[] args) {
//        new XMLTester();
//        exportToFile(new File("d:/exportFile.mig"));
        importFromFile(new File(constants.Constants.module+".mig"));
    }
    
    public XMLTester() {
        getAllBeanNames();
    }
    
    public static List<String> getAllBeanNames() {
        List<String> lst = new ArrayList<String>();
        try {
            //read from persistence.xml
            org.xml.sax.InputSource source = new org.xml.sax.InputSource();
            source.setByteStream(new java.io.ByteArrayInputStream(DataUtil.getResourceBytes("META-INF/persistence.xml")));
            org.apache.xerces.parsers.DOMParser dom = new org.apache.xerces.parsers.DOMParser();
            dom.parse(source);
            
            Document doc = dom.getDocument();
            NodeList nodes = doc.getElementsByTagName("class");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String bean = node.getTextContent();
                try {
                    bean = Class.forName(bean).getSimpleName();
                    if (!lst.contains(bean)) {
    //                    System.out.println("CONTENT == "+bean);
                        lst.add(bean);
                    }
                }
                catch (Exception e) {
                }
            }
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        Collections.sort(lst);
        return lst;
    }
    
    public static List<String> getAllBeanFullNames() {
        List<String> lst = new ArrayList<String>();
        try {
            //read from persistence.xml
            org.xml.sax.InputSource source = new org.xml.sax.InputSource();
            source.setByteStream(new java.io.ByteArrayInputStream(DataUtil.getResourceBytes("META-INF/persistence.xml")));
            org.apache.xerces.parsers.DOMParser dom = new org.apache.xerces.parsers.DOMParser();
            dom.parse(source);
            
            Document doc = dom.getDocument();
            NodeList nodes = doc.getElementsByTagName("class");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String bean = node.getTextContent();
                bean = ClassLoader.getSystemClassLoader().loadClass(bean).getName();
                if (!lst.contains(bean)) {
//                    System.out.println("CONTENT == "+bean);
                    lst.add(bean);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        Collections.sort(lst);
        return lst;
    }

    public static void exportToFile(File f) { 
        List<String> lstStr = getAllBeanNames();
        // Serialize object into XML
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            for (String string : lstStr) {
                if (!PanelUtil.canBackup(string)) {
                    System.out.println("CANNOT BACKUP BEAN!!!! ["+string+"]");
                    continue;
                }
                try {
                    List lst = AbstractIBean.list("SELECT a FROM "+string+" a"); 
                    encoder.writeObject(lst); 
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            encoder.close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public static void exportToFile(File f, List<String> lstBeans) { 
        // Serialize object into XML
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            for (String string : lstBeans) {
                if (!PanelUtil.canBackup(string)) {
                    System.out.println("CANNOT BACKUP BEAN!!!! ["+string+"]");
                    continue;
                }
                try {
                    List lst = AbstractIBean.list("SELECT a FROM "+string+" a"); 
                    encoder.writeObject(lst); 
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            encoder.close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    public static void exportToFile(File f, String beanName) { 
        if (!PanelUtil.canBackup(beanName)) {
            System.out.println("CANNOT BACKUP BEAN!!!! ["+beanName+"]");
            return;
        }
        // Serialize object into XML
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            try {
                List lst = AbstractIBean.list("SELECT a FROM "+beanName+" a"); 
                encoder.writeObject(lst); 
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            encoder.close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    public static void exportBeanSQLToFile(File f, String sql) { 
        // Serialize object into XML
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            try {
                List lst = AbstractIBean.list(sql); 
                encoder.writeObject(lst); 
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            encoder.close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    public static void importFromFile(File f) {
        List<String> lstStr = getAllBeanNames();
        // Serialize XML into DB
        try {
            FileInputStream fis = new FileInputStream(f);  
            importFromStream(fis);
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    public static void importFromStream(InputStream is) {
        List<String> lstStr = getAllBeanNames();
        // Serialize XML into DB
        try {
            XMLDecoder decoder = new XMLDecoder(is);
            for (String string : lstStr) {
                if (!PanelUtil.canBackup(string)) {
                    System.out.println("CANNOT BACKUP BEAN!!!! ["+string+"]");
                    continue;
                }
                try {
//                    System.out.println("Persisting "+string);
                    List tmp = (List) decoder.readObject();
                    AbstractIBean.save(tmp);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            decoder.close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public static void deleteAndImportAllTableRecords(File f) { 
        System.out.println("...... START DELETE AND IMPORT PROCESS ......");
        System.out.println("...... START DELETE AND IMPORT PROCESS ......");
        System.out.println("...... START DELETE AND IMPORT PROCESS ......");
        System.out.println("...... START DELETE AND IMPORT PROCESS ......");
        System.out.println("...... START DELETE AND IMPORT PROCESS ......");
        List<String> lstStr = getAllBeanNames();
        for (String string : lstStr) {
            System.out.println("\t\tDELETING "+string+"............");
            AbstractIBean.runSQL("DELETE FROM "+string+" a"); 
        }
        importFromFile(f);
    }
}
