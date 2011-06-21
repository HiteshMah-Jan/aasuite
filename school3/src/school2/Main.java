/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package school2;

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
            springbean.AAAConfig.getInstance().setTitle("SchoolSoft");
    	}
    	else {
            springbean.AAAConfig.getInstance(args);
            springbean.AAAConfig.getInstance().setTitle("SchoolSoft");
            springbean.AAAConfig.getInstance().setBootStrap("bean.Student");
    	}
        Common2App.main(args);
    }

    public static void setupPackage(String packageType) {
        if ("COMPLETE".equalsIgnoreCase(packageType)) {
            //show all menu
            return;
        }
        else if ("A".equalsIgnoreCase(packageType)) {
            //show only Enrollment and relevant menu
            common2.Common2View.mainView.hideAllMenu();
            common2.Common2View.mainView.showMenu("Enrollment");
            common2.Common2View.mainView.showMenu("Student Registration");
            common2.Common2View.mainView.showMenu("Schedule");
            common2.Common2View.mainView.showMenu("School Reference");
            common2.Common2View.mainView.showMenu("School Employee");
        }
        else if ("B".equalsIgnoreCase(packageType)) {
            //show only Enrollment, Accounting and relevant menu
            common2.Common2View.mainView.hideAllMenu();
            common2.Common2View.mainView.showMenu("Enrollment");
            common2.Common2View.mainView.showMenu("Student Registration");
            common2.Common2View.mainView.showMenu("Schedule");
            common2.Common2View.mainView.showMenu("School Reference");
            common2.Common2View.mainView.showMenu("School Employee");
            //add accounting
            common2.Common2View.mainView.showMenu("Accounting Reference");
            common2.Common2View.mainView.showMenu("Accounting Process");
        }
        else if ("C".equalsIgnoreCase(packageType)) {
            //show only Library module
            common2.Common2View.mainView.hideAllMenu();
            common2.Common2View.mainView.showMenu("Student Registration");
            common2.Common2View.mainView.showMenu("School Reference");
            common2.Common2View.mainView.showMenu("School Employee");
            common2.Common2View.mainView.showMenu("Library");
        }
    }

    
}
