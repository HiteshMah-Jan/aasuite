/*
 * PanelHelp.java
 *
 * Created on Aug 17, 2008, 3:55:11 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import common2.Common2View;
import constants.Constants;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JLabel;

/**
 *
 * @author Entokwaa
 */
public class PanelHelp {
    private static HelpBroker helpBroker = null;
    private static CSH.DisplayHelpFromSource csh = null;
    private static String helpSetName = null;
    private static final ActionEvent cshAction = new ActionEvent(new JLabel(), ActionEvent.ACTION_FIRST, null);

    private static HelpBroker getHelpBroker() {
        if (helpSetName == null) {
            helpSetName = Constants.module.toLowerCase()+"Help/"+Constants.module.toLowerCase()+"Help.hs";
//            helpSetName = Constants.module+"Help.hs";
            System.out.println("HS == "+helpSetName);
        }
        if (helpBroker == null) {
            try {
                // <sven@killig.de>: modified for Web Start
                URL hsURL = HelpSet.findHelpSet(ClassLoader.getSystemClassLoader(), helpSetName);
                HelpSet mainHelpSet = new HelpSet(null, hsURL);
                if (mainHelpSet != null)
                    helpBroker = mainHelpSet.createHelpBroker();
            } catch (Throwable ee) {
                System.out.println("\nHelpSet " + helpSetName + " not created.\n" + ee.getMessage());
                return null;
            }
        }
        return helpBroker;
    }
    
    public static void showHelp() {
        String helpName = Common2View.getTransactionPanel().getHelpName();
//        helpName = AppConfig.getValue("HELP", helpName, helpName);
        if (helpBroker == null) {
            if (getHelpBroker() != null) {
                new CSH.DisplayHelpFromSource(helpBroker).actionPerformed(cshAction);
                helpBroker.setDisplayed(true);
                helpBroker.setCurrentID(helpName);
            }
        } else
            helpBroker.setDisplayed(true);
            helpBroker.setCurrentID(helpName);
    }
}
