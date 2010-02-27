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
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"orDate","orNumber"},columnSearch={"orDate", "orNumber"}, gridCount=6)
@Displays({
        
   @Display(name =  "orDate"),
    @Display(name =  "orNumber",gridFieldWidth=3,width=120),
    
    @Display(name = "examinationDate",label="Scheduled Date"),
    @Display(name = "examTime",type="Time"),
    @Display(name = "roomAssign",width=117),
    
    @Display(name = "evaluationDate"),
    @Display(name = "evaluation",type="Combo", modelCombo = {"PASSED", "FAILED", "RECONSIDERED","RESCHEDULED"},label="Result",width=120),
    @Display(name = "enrolledDate")
})

public class AdmissionExaminationDetailExt extends Admission {
}
