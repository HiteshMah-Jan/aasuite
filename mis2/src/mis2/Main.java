/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mis2;

import springbean.AAAConfig;
import common2.Common2App;

/**
 *
 * @author Entokwaa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //check the package here
    	if (args==null || args.length==0) {
            AAAConfig.getServerInstance();
    	    AAAConfig.server = true;
            springbean.AAAConfig.getInstance().setTitle("MISSoft");
    	}
    	else {
            springbean.AAAConfig.getInstance(args);
            springbean.AAAConfig.getInstance().setTitle("MISSoft");
            springbean.AAAConfig.getInstance().setBootStrap("bean.Member");
    	}
        Common2App.main(args);
    }

    public static void setupPackage(String packageType) {
        if ("COMPLETE".equalsIgnoreCase(packageType)) {
            //show all menu
            return;
        }
    }


}
