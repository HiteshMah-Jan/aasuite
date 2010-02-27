/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.util.IBean;
import springbean.AAAConfig;
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class ExcelWriter {

    HSSFWorkbook wb;

    public ExcelWriter() {
        wb = new HSSFWorkbook();
    }

    public static ExcelWriter getInstance() {
        AAAConfig.getInstance();
        ExcelWriter writer = new ExcelWriter();
        return writer;
    }

    public void writeBeanData(String bean) {
        if (!PanelUtil.canBackup(bean)) {
            System.out.println("CANNOT BACKUP BEAN!!!! [" + bean + "]");
            return;
        }
        HSSFSheet sheet = wb.createSheet(bean);
        List lst = util.DBClient.getList("SELECT a FROM " + bean + " a",0,10000);
        HSSFRow row;
        HSSFCell cell;
        for (int i = 0; lst != null && i < lst.size(); i++) {
            Object object = lst.get(i);
            Field[] fields = object.getClass().getFields();
            if (i==0) {
                row = sheet.createRow(i);
                for (int j = 0; fields != null && j < fields.length; j++) {
                    Field field = fields[j];
                    cell = row.createCell((short) j);
                    try {
                        cell.setCellValue(field.getName());
                    } catch (Exception e) {
                    }
                }
            }
            row = sheet.createRow(i+1);
            for (int j = 0; fields != null && j < fields.length; j++) {
                Field field = fields[j];
                cell = row.createCell((short) j);
                try {
                    Class cls = field.getType();
                    //only Double, Integer, String, Date are allowed
                    if (cls == Integer.class || cls == int.class) {
                        cell.setCellValue((Integer)BeanUtil.getPropertyValue((IBean) object, field.getName()));
                    } else if (cls == Double.class || cls == double.class) {
                        cell.setCellValue((Double)BeanUtil.getPropertyValue((IBean) object, field.getName()));
                    } else if (cls == Date.class) {
                        cell.setCellValue((Date)BeanUtil.getPropertyValue((IBean) object, field.getName()));
                    } else {
                        cell.setCellValue(BeanUtil.getPropertyValue((IBean) object, field.getName()).toString());
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void writeBeanData(String bean, String filename) {
        HSSFSheet sheet = wb.createSheet(bean);
        List lst = util.DBClient.getList("SELECT a FROM " + bean + " a",0,10000);
        HSSFRow row;
        HSSFCell cell;
        for (int i = 0; lst != null && i < lst.size(); i++) {
            Object object = lst.get(i);
            Field[] fields = object.getClass().getFields();
            row = sheet.createRow(i);
            for (int j = 0; fields != null && j < fields.length; j++) {
                Field field = fields[j];
                cell = row.createCell((short) j);
                if (i == 0) {
                    cell.setCellValue(BeanUtil.getPropertyValue((IBean) object, field.getName()).toString());
                }
            }
        }
        writeToFile(filename);
    }

    public void writeAllBeans(String filename) {
        List<String> beans = XMLTester.getAllBeanNames();
        for (String bean : beans) {
            writeBeanData(bean);
        }
        writeToFile(filename);
    }

    private void writeToFile(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            wb.write(fos);
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
