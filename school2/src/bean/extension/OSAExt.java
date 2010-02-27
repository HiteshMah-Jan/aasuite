/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Student;
import bean.reference.ActionTaken;
import bean.reference.Offense;
import java.io.Serializable;
import template.*;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Entokwaa
 */
@UITemplate(canSave=false,template=TemplateSinglePage.class,criteriaSearch={"studentId","dateEncounter","offense","actionTaken"},
columnSearch={"studentName","dateEncounter","offense","actionTaken","reportedTo","dateStart","dateEnd"}, gridCount=8,title="Office of the Guidance Councilor")
@Displays({
        @Display(name="dateEncounter",gridFieldWidth=7),
        @Display(name="studentId",gridFieldWidth=7,linktoBean=Student.class,label="Student", type="PopSearch"),
        @Display(name="offense", type="Label",gridFieldWidth=7,width=-1),
        @Display(name="actionTaken", type="Label",gridFieldWidth=7,width=-1),
        
        @Display(name="reportedTo", type="Label", gridFieldWidth = 7,width=-1)
        //@Display(name="dateStart", type="Label", gridFieldWidth = 3),
       // @Display(name="dateEnd",type="Label", gridFieldWidth = 3),
        //@Display(name="results", type = "Label", gridFieldWidth = 7, width = -1)

})
public class OSAExt extends bean.person.StudentEncounterGuidance implements Serializable {
    public static void main(String[] args) {
        view(OSAExt.class);
    }
}