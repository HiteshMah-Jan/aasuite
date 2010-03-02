/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cargo2;

import springbean.AAAConfig;
import common2.Common2App;
/**
 *
 * @author Charliemagne Mark
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
            springbean.AAAConfig.getInstance().setTitle("CargoSoft");
    	}
    	else {
            springbean.AAAConfig.getInstance(args);
            springbean.AAAConfig.getInstance().setTitle("CargoSoft");
            springbean.AAAConfig.getInstance().setBootStrap("bean.Awb");
    	}
        Common2App.main(args);
    }

}
