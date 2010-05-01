/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tool2;

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
    	if (args==null || args.length==0) {
            AAAConfig.getServerInstance();
    		AAAConfig.server = true;
            springbean.AAAConfig.getInstance().setTitle("CRMSoft");
    	}
    	else {
            springbean.AAAConfig.getInstance(args);
            springbean.AAAConfig.getInstance().setTitle("CRMSoft");
            springbean.AAAConfig.getInstance().setBootStrap("bean.Student");
    	}
        Common2App.main(args);
    }

}
