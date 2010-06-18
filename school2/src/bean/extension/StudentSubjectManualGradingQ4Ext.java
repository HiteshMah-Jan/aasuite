/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.person.StudentSubject;
import bean.reference.Subject;

/**
 *
 * @author Entokwaa
 */
@UITemplate(
    columnSearch={"studentName","grade4"}, 
    criteriaSearch={"studentName"}, 
    gridCount=4, title="Enrollment Subjects", editableCol="1")
@Displays({
    @Display(name="subject", enabled=false, type="Label", linktoBean=Subject.class),
    @Display(name="grade1", enabled=false, type="Label", label="First"),
    @Display(name="grade2", enabled=false, type="Label", label="Second"),
    @Display(name="grade3", enabled=false, type="Label", label="Third"),
    @Display(name="grade4", enabled=false, type="Label", label="Fourth"),
    @Display(name="finalRating", enabled=false, type="Label", label="Overall")
})
public class StudentSubjectManualGradingQ4Ext extends StudentSubject {
    public static void main(String[] args) {
        view(StudentSubjectManualGradingQ4Ext.class);
    }
}
