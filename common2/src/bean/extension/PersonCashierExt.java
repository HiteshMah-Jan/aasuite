/*
 * StudentExt.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import java.io.Serializable;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template = TemplateTabPage.class, canSave=false, canNew=false, canDelete=false, 
criteriaSearch = {"lastName", "firstName", "section"}, 
columnSearch = {"lastName", "firstName", "middleInitial"}, gridCount = 6, title = "Payee")
@Displays({
    @Display(name = "studentNumber", type = "Label"),
    @Display(name = "lastName", type = "Label"),
    @Display(name = "firstName", type = "Label"),
    @Display(name = "middleInitial", type = "Label", label="Middle Name"),
    @Display(name = "gender", type = "Label"),
    @Display(name = "citizenship", type = "Label"),
    @Display(name = "religion", type = "Label"),
    @Display(name = "contactNumber", type = "Label")
})
public class PersonCashierExt extends bean.Person implements Serializable {

    public static void main(String[] args) {
        view(PersonCashierExt.class);
    }
}
