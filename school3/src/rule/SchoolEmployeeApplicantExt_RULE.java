/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.JobApplicant;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class SchoolEmployeeApplicantExt_RULE extends JobApplicant_RULE {
    protected void hire() {
        JobApplicant a = (JobApplicant) this.getBean();
        DBClient.runSQL("UPDATE Person SET personType='FACULTY' WHERE personId=",a.personId);
        showMessage("Applicant is now hired.");
        this.refreshRecords();
    }
}
