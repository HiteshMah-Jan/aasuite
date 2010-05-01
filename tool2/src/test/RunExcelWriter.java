/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import springbean.AAAConfig;

/**
 *
 * @author Entokwaa
 */
public class RunExcelWriter {
    public static void main(String[] args) {
        ExcelWriter.getInstance().writeAllBeans("C:/Tool.xls");
//        util.DBClient.getList("SELECT a FROM CustomerContact a");
    }
}
