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
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"recommendedRemedialEla"},columnSearch={"recommendedRemedialEla"}, gridCount=4)
@Displays({
        
    @Display(name = "recommendedRemedialEla"),
    @Display(name ="recommendedRemedialMath"),
    @Display(name = "recommendedRemedialSci"),
    @Display(name = "recommendedRemedialFil"),
    @Display(name = "recommendedRemedialSS"),
    @Display(name = "recommendedRemedialCon")
    
})

public class AdmissionRecommendedRemedialClassExt extends Admission {
}
