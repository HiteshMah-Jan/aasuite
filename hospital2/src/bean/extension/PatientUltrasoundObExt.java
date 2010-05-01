/*
 * Blood.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.person.*;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, 
    columnSearch = {"commendation","addendum","labDate","requestDate","requestingPhysician"})
@Displays({
        @Display(name="impression"),
        @Display(name="commendation", label="LMP"),
        @Display(name="addendum", label="AOG"),
        @Display(name="labDate", label="Date"),
        @Display(name="requestDate", label="Next Date"),
        @Display(name="requestingPhysician", label="Physician")
})
public class PatientUltrasoundObExt extends PatientUltrasoundOb {
    
}
