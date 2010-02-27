/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Admission;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"childName1","childGradeSection1"},columnSearch={"childName1","childGradeSection1"}, gridCount=4)
@Displays({
        
    @Display(name = "childName1",labelTop=true,label="Name"),
    @Display(name = "childGradeSection1",labelTop=true,label="Grade / Section"),
    @Display(name = "childName2",hideLabel=true),
    @Display(name = "childGradeSection2",hideLabel=true),
    @Display(name = "childName3",hideLabel=true),
    @Display(name = "childGradeSection3",hideLabel=true)
})

public class AdmissionExtOtherChildrenStudying extends Admission {
}
