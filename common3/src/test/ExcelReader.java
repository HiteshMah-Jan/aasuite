/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import service.util.AbstractIBean;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class ExcelReader {

    String filename;
    Class[] clsArr;

    public static void importAllBeans(File filename) {
        try {
            importAllBeans(new FileInputStream(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void importAllBeans(InputStream filename) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(filename);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            int sheetCount = wb.getNumberOfSheets();
            for (int sheetCounter = 0; sheetCounter < sheetCount; sheetCounter++) {
//                List<IBean> lstRow = new ArrayList<IBean>();
                String bean = wb.getSheetName(sheetCounter);
                Class beanClass = PanelUtil.getBeanClass(bean);
                if (!PanelUtil.canBackup(bean)) {
                    continue;
                }
                HSSFSheet sheet = wb.getSheetAt(sheetCounter);
                // Iterate over each row in the sheet
                Iterator rows = sheet.rowIterator();
                HSSFRow headerrow = null;
                for (int rowCounter = 0; rows.hasNext(); rowCounter++) {
                    AbstractIBean ibean = (AbstractIBean) beanClass.newInstance();
                    HSSFRow row = (HSSFRow) rows.next();
                    if (rowCounter==0) {
                        headerrow = row;
                        continue;
                    }

                    // Iterate over each cell in the row and print out the cell's content
                    String fieldName = "";
                    Object value = null;
                    for (int columnCounter = 0; columnCounter < row.getLastCellNum(); columnCounter++) {
                        try {
                            HSSFCell cellHeader = (HSSFCell) headerrow.getCell((short) columnCounter);
                            HSSFCell cell = (HSSFCell) row.getCell((short) columnCounter);
                            if (cellHeader==null) continue;
                            if (cell==null) continue;

                            fieldName = cellHeader.getStringCellValue();
                            Field f = beanClass.getField(fieldName);
                            Class cls = f.getType();

                            //only Double, Integer, String, Date are allowed
                            if (cls == Double.class || cls == double.class) {
                                value = cell.getNumericCellValue();
                                if (value!=null) BeanUtil.setShowPropertyValue(ibean, fieldName, value);
                            } else if (cls == Integer.class || cls == int.class) {
                                value = (int)cell.getNumericCellValue();
                                if (value!=null) BeanUtil.setShowPropertyValue(ibean, fieldName, value);
                            } else if (cls == String.class) {
                                value = cell.getStringCellValue();
                                if (value!=null) BeanUtil.setShowPropertyValue(ibean, fieldName, value);
                            } else if (cls == Boolean.class || cls == boolean.class) {
                                value = Boolean.valueOf(cell.getStringCellValue());
                                if (value!=null) BeanUtil.setShowPropertyValue(ibean, fieldName, value);
                            } else if (cls == Date.class) {
                                value = cell.getDateCellValue();
                                if (value!=null) BeanUtil.setShowPropertyValue(ibean, fieldName, value);
                            }
                            else {
                            	Log.out(fieldName,"=",cell.getStringCellValue());
                            }
                        } catch (Exception e) {
                            Logger.getLogger("global").log(Level.SEVERE, e.getMessage());
                        }
                    }
                    ibean.save();
                }
//                if (lstRow.size()>0) {
//                    if (PanelUtil.deleteOnUpload(bean)) {
////                        DBClient.runSQL("DELETE FROM ",bean);
//                    }
////                    DBClient.persistBean(lstRow);
//                    Log.out(bean," FINISHED ",lstRow.size());
//                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExcelReader(String filename, Class... cls) {
        this.filename = filename;
        this.clsArr = cls;
    }

    public static ExcelReader getInstance(
            String filename, Class... cls) {
        ExcelReader reader = new ExcelReader(filename, cls);
        return reader;
    }

    public List<List> getRowColumnList(boolean removeHeader) {
        List<List> lstRow = new ArrayList<List>();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);

            // Iterate over each row in the sheet
            Iterator rows = sheet.rowIterator();
            if (removeHeader) {
                rows.next();    //remove the header

            }

            for (int i = 0; rows.hasNext(); i++) {
                List lst = new ArrayList();
                lstRow.add(lst);
                HSSFRow row = (HSSFRow) rows.next();
                Log.info("Row #",row.getRowNum());

                // Iterate over each cell in the row and print out the cell's content
                for (int j = 0; j <
                        clsArr.length; j++) {
                    try {
                        HSSFCell cell = (HSSFCell) row.getCell((short) j);
                        //only Double, Integer, String, Date are allowed
                        if (clsArr[j] == Double.class || clsArr[j] == Integer.class || clsArr[j] == int.class || clsArr[j] == double.class) {
                            lst.add(cell.getNumericCellValue());
                        } else if (clsArr[j] == String.class || clsArr[j] == Boolean.class || clsArr[j] == boolean.class || clsArr[j] == Character.class || clsArr[j] == char.class) {
                            lst.add(cell.getStringCellValue());
                        } else if (clsArr[j] == Date.class) {
                            lst.add(cell.getDateCellValue());
                        } else {
                            lst.add("");
                        }
                    } catch (Exception e) {
                        lst.add("");
//                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }






        return lstRow;
    }

    public List<List> getMapping(String... beanMap) {
        Collection2BeanMapper mapper = Collection2BeanMapper.getInstance();
        return mapper.getMapping(getRowColumnList(true), beanMap);
    }

    public static void main(String[] args) {
    }
}
