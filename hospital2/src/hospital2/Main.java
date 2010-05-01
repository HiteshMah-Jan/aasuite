/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital2;

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
            springbean.AAAConfig.getInstance().setTitle("HospitalSoft");
    	}
    	else {
            springbean.AAAConfig.getInstance(args);
            springbean.AAAConfig.getInstance().setTitle("HospitalSoft");
            springbean.AAAConfig.getInstance().setBootStrap("bean.Patient");
    	}
        Common2App.main(args);
    }

}
