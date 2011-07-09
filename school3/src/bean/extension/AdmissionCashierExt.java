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
@UITemplate(template=template.screen.TemplateNoForm.class,criteriaSearch={"lastName","firstName"},columnSearch={"lastName", "firstName", "middleInitial"}, gridCount=6)
@Displays({
        
        
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial", label = "Middle Name"),
        @Display(name="schoolYear"),
        @Display(name="gradeLevel", type = "Combo", sqlCombo="SELECT a FROM GradeLevel a", gridFieldWidth=3, width =-1),
        @Display(name="examinationFee", label="Amount Fee"),
        @Display(name="orDate"),
        @Display(name="orNumber")
        })

public class AdmissionCashierExt extends Admission {
}
