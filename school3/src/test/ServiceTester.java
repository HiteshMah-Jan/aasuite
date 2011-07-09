/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import springbean.AAAConfig;

/**
 *
 * @author Charliemagne Mark
 */
public class ServiceTester {
    public static void main(String[] args) {
        AAAConfig.getInstance();
        new bean.admin.SendMessageAccount().runSetup();
        util.ServiceUtil.main(args);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServiceTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
