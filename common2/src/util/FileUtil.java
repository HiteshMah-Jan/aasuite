/*
 * FileUtil.java
 *
 * Created on Sep 30, 2007, 1:52:14 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Budoy Entokwa
 */
public class FileUtil {

    public static byte[] getFileBytes(String filename) {
        return getFileBytes(new File(filename));
    }
    
    public static byte[] getFileBytes(File f) {
        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(f, "r");
            byte[] b = new byte[(int)f.length()];
//            System.out.println("FILE SIZE = "+f.length());
            raf.readFully(b);
            return b;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String readDefaultFormula(String formula) {
        return readResourceFile("test/formula/"+formula+".formula");
    }
    
    public static String readResourceFile(String fileSource) {
        try {
            java.io.InputStream is = util.DataUtil.getResource(fileSource);
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(is));
            java.lang.StringBuffer buffer = new java.lang.StringBuffer();
            java.lang.String line = null;
            while ((line = in.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            return buffer.toString();
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private static FileUtil fUtil = new FileUtil();

    public static FileUtil getInstance() {
        return fUtil;
    }

    public List<String> readLines(String filename) {
        String str = readFile(filename).trim();
        return DataUtil.splitString(str, "\n");
    }

    public List<String> readLinesIncludeSlant(String filename) {
        String str = readFile(filename).trim();
        return DataUtil.splitStringAddSlant(str);
    }

    public String readFile(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            int len = (int) file.length();
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(file, "r");

            byte[] b = new byte[len];
            raf.read(b);
            raf.close();

            String str = new String(b);
            return str;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void writeFile(String filename, String str) throws Exception {
        try {
            java.io.File file = new java.io.File(filename);
            if (file.exists()) {
                throw new Exception("Cannot create file, file already exist.");
            }
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(file, "rw");
            raf.writeBytes(str);
            raf.close();
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            FileUtil util = FileUtil.getInstance();
            java.lang.String str = util.readFile("D:/ROUTE_DATA.SQL.sql");
            java.lang.System.out.println("STRING == " + str);

            util.writeFile("D:/sample.txt", str.trim());
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
}
