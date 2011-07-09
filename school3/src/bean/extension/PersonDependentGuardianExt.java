/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.person.PersonDependent;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
/**
 *
 * @author Budoy Entokwa
 */

@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"lastName","relation"})
@Displays({
        //@Display(name="seq"),
        //@Display(name="personId"),
          @Display(name="lastName", label="Name",gridFieldWidth=3,width=-1),
        //@Display(name="relation",type="Combo",modelCombo={"SPOUSE","FATHER","MOTHER","GRAND FATHER","GRAND MOTHER","BROTHER","SISTER","CHILDREN"}),  
          @Display(name="birthDate",width=100),
          @Display(name="birthPlace",width=100),
          @Display(name="religion",width=-1),
          @Display(name="citizenship",label="Nationality",width=100),
          @Display(name="educationStatus",gridFieldWidth=3,width=-1),
          @Display(name="occupation",width=-1),
          @Display(name="course",width=100),
          @Display(name="companyName",gridFieldWidth=3,width=-1),
          @Display(name="companyAddress",gridFieldWidth=3,width=-1),
          @Display(name="mobileNumber",width=-1),
          @Display(name="telephoneNumber",label="Co. Phone",width=100)
        //@Display(name="email")
        //@Display(name="firstName"),
        //@Display(name="middleInitial"),
        //@Display(name="birthDate"),
         //@Display(name="employeeId")
})
public class PersonDependentGuardianExt extends PersonDependent {
    
}

   
    