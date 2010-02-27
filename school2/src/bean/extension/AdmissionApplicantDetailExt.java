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
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"birthDate","birthPlace"},columnSearch={"birthDate", "birthPlace"}, gridCount=4)
@Displays({
        
    @Display(name = "birthDate"),
    @Display(name = "age"),
    @Display(name = "birthPlace"),
    @Display(name = "telNum"),
    @Display(name = "address",gridFieldWidth=3,width=-1),
    @Display(name = "nationality"),
    @Display(name = "ifAlienAcrNo"),
    @Display(name = "acrPlaceIssued"),
    @Display(name = "acrDateIssued"),
    @Display(name = "religion"),
    @Display(name = "otherReligion"),
    @Display(name = "schoolLastAttended",gridFieldWidth=3,width=-1),
    @Display(name = "schoolLastAttendedAddress",gridFieldWidth=3,width=-1),
    @Display(name = "reasonForTransfer",gridFieldWidth=3,width=-1)
})

public class AdmissionApplicantDetailExt extends Admission {
}
