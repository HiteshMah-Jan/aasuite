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
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"prevSchool"},columnSearch={"prevSchool"}, gridCount=14)
@Displays({
        
   @Display(name = "prevSchool",label="Prev. School",gridFieldWidth=13,width=-1),
    @Display(name = "gpaEla",label="E.L.A",width=35,labelTop=true),
    @Display(name = "gpaMath",label="Math",width=35,labelTop=true),
    @Display(name = "gpaSci",label="Sci",width=35,labelTop=true),
    @Display(name = "gpaFil",label="Fil",width=35,labelTop=true),
    @Display(name = "gpaSs",label="SS",width=35,labelTop=true),
    @Display(name = "gpaCon",label="Con",width=35,labelTop=true),
    @Display(name = "gpaAve",label="Ave.",width=35,labelTop=true)
})

public class AdmissionGpaPreviousSchoolExt extends Admission {
}
