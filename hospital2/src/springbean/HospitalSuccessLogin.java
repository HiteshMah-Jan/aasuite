/*
 * HospitalSuccessLogin.java
 * 
 * Created on Oct 30, 2007, 7:22:25 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import component.MainWindow;
import component.SpringCall;
import constants.UserInfo;
import ui.WelcomePanel;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class HospitalSuccessLogin extends SpringCall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public Object call(Object obj) {
		System.out.println("TEST HOSPITAL LOGIN SUCCESS");		
		String welcomeTxt = "Welcome to "+constants.Constants.appTitle;
		if (!constants.Constants.IS_SINGLE_USER) {
			UserInfo userInfo = (UserInfo) obj;
			welcomeTxt = "Welcome to "+constants.Constants.appTitle+" "+userInfo.getUserName();
		}
		WelcomePanel welcome = new WelcomePanel();
//		welcome.getLblWelcome().setText(welcomeTxt);
		MainWindow.showPanel(welcome);
		PanelUtil.setTitle(welcomeTxt);
		return null;
    }

}
