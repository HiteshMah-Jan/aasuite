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
@UITemplate(template=template.screen.TemplateTabPage.class,criteriaSearch={"father","mother"},columnSearch={"father", "mother"}, gridCount=4)
@Displays({
        
    @Display(name = "father",label="Father's Name"),
    @Display(name = "fatherOccupation",label="Occupation"),
    @Display(name = "fatherCompany",label="Company"),
    @Display(name = "fatherCompanyTelNo",label="Tel No./s"),
    @Display(name = "fatherCompanyAddress",gridFieldWidth=3,width=-1,label="Company address"),
    @Display(name = "mother",label="Mother's Name"),
    @Display(name = "motherOccupation",label="Occupation"),
    @Display(name = "motherCompany",label="Company"),
    @Display(name = "motherCompanyTelNo",label="Tel No./s"),
    @Display(name = "motherCompanyAddress",gridFieldWidth=3,width=-1,label="Company Address")
})

public class AdmissionParentDetailExt extends Admission {
}
