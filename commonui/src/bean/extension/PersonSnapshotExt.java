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
import template.screen.TemplateSinglePage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(imagesHeight=130, imagesWidth=130, showImages=true, imageEditable=false, template = TemplateSinglePage.class, canSave=false, canNew=false, canDelete=false, criteriaSearch = {"lastName", "firstName", "section"}, columnSearch = {"studentNumber", "lastName", "firstName", "course", "section"}, gridCount = 2, title = "Student")
@Displays({
    @Display(name = "studentNumber", label="ID #", width=-1),
    @Display(name = "gradeLevel", label = "Level", width=-1, maxChar=20),
//    @Display(name = "section", width=100, maxChar=20),
//    @Display(name = "plan", width=-1, type="Label", maxChar=20),
    @Display(name = "status", width=100, modelCombo={"ENROLLED","NOT ENROLLED","OFF. DROP","UNOFF. DROP"}),
    @Display(name = "paymentStatus", label = "Check", width=-1, maxChar=20)
})
public class PersonSnapshotExt extends bean.Person implements Serializable {
    public static void main(String[] args) {
        view(PersonSnapshotExt.class);
    }
}
