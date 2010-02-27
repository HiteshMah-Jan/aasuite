/*
 * StudentExt.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.LibraryBorrowedBooks;
import bean.person.StudentEncounter;
import bean.person.StudentEncounterClinic;
import bean.person.StudentPersonalityExam;
import bean.person.PersonAttendance;
import java.io.Serializable;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template = TemplateTabPage.class, canSave=false, canNew=false, canDelete=false, 
criteriaSearch = {"lastName", "firstName", "section"}, 
columnSearch = {"studentNumber", "lastName", "firstName", "middleInitial"}, gridCount = 6, title = "Student")
@Displays({
    @Display(name = "studentNumber", type = "Label"),
    @Display(name = "lastName", type = "Label"),
    @Display(name = "firstName", type = "Label"),
    @Display(name = "middleInitial", type = "Label"),
    @Display(name = "scholarshipCode", type = "Label", label="Discount code"),
    @Display(name = "course", label = "Grade", type = "Label"),
    @Display(name = "gender", type = "Label"),
    @Display(name = "citizenship", type = "Label"),
    @Display(name = "religion", type = "Label"),
    @Display(name = "contactNumber", type = "Label")
})
public class StudentCashierExt extends bean.Student implements Serializable {

    public static void main(String[] args) {
        view(StudentCashierExt.class);
    }
}
