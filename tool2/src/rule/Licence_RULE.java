/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Licence;
import component.SendEmailDialog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import util.PanelUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class Licence_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreateLicence".equals(comp.getName())) {
            createLicence();
            downloadLicence();
        }
        else if ("btnSendLicence".equals(comp.getName())) {
            sendLicence();
        }
    }

    private void createLicence() {
        Licence lic = (Licence) getBean();
        Map map = new HashMap();
        map.put("product", lic.product);
        map.put("customerName", lic.customerName);
        map.put("email", lic.email);
        map.put("sentBy", lic.sentBy);
        map.put("packageType", lic.packageType);
        constants.Constants.saveLicence(map);
        downloadLicence();
    }

    private void downloadLicence() {
        File licfile = new java.io.File(constants.Constants.licencename);
        System.out.println(licfile.getAbsolutePath());
        File newFile = PanelUtil.showSelectFile("Save Licence", constants.Constants.licencename, null);
        licfile.renameTo(newFile);
    }

    private void sendLicence() {
        createLicence();
        //send the licence
        Licence lic = (Licence) getBean();
        SendEmailDialog.showDialog("Licence Key from Miranda Workbench Softwares", "Licence Key from Miranda Workbench Softwares", 0, lic.email, constants.Constants.licencename);
    }

}
