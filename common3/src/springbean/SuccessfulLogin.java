/*
 * SuccessfulLogin.java
 *
 * Created on Oct 30, 2007, 6:57:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.admin.AclUserModule;
import component.MainWindow;
import component.SpringCall;
import constants.UserInfo;
import java.util.List;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;

import util.BeanUtil;
import util.PanelUtil;
import common2.Common2View;

import javax.swing.*;

/**
 *
 * @author Budoy Entokwa
 */
public class SuccessfulLogin extends SpringCall {

    SpringCall call;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public Object call(Object obj) {
//        String welcomeTxt = BeanUtil.concat("Welcome to ",constants.Constants.appTitle);
//        if (!constants.Constants.IS_SINGLE_USER) {
//            welcomeTxt = BeanUtil.concat("Welcome to ",constants.Constants.appTitle," ",UserInfo.getUserName());
//        }
//        ui.WelcomePanel welcome = new ui.WelcomePanel(); 
//        Common2View.showPanel(welcome);
//        PanelUtil.setTitle(welcomeTxt);

//        check menubar
        JMenuBar bar = Common2View.mainView.menuBar;
        int menuC = bar.getMenuCount();
        for (int i=0; i<menuC; i++) {
            JMenu menu = bar.getMenu(i);
            if (menu.isVisible()) {
                for (int k=0; k<menu.getItemCount(); k++) {
                    if (menu.getItem(k).isVisible()) {
                        menu.getItem(k).doClick();
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
