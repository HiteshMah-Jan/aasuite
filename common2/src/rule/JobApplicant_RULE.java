/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.JobApplicant;
import javax.swing.JComponent;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class JobApplicant_RULE extends Person_RULE {
    @Override
    public void runOnClick(JComponent comp) {
//        System.out.println("ON CLICK for "+comp.getName());
        if ("btnHire".equals(comp.getName())) {
            hire();
        }
    }

    protected void hire() {
        JobApplicant a = (JobApplicant) this.getBean();
        DBClient.runSQL("UPDATE Person SET personType='EMPLOYEE' WHERE personId="+a.personId);
        showMessage("Applicant is now hired.");
        this.refreshRecords();
    }

}
