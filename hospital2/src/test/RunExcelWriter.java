/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;

/**
 *
 * @author Entokwaa
 */
public class RunExcelWriter {
    public static void main(String[] args) {
//        ExcelReader.importAllBeans(new File("C:/School.xls"));
        ExcelWriter.getInstance().writeAllBeans("C:/test.xls");
    }
}
