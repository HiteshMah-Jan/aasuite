/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Student;
import bean.person.StudentSubject;
import bean.reference.Subject;
import java.io.Serializable;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate( gridCount=10, title="Subjects", template=TemplateSinglePage.class, 
    columnSearch={"subject","schoolYear","grade1","grade2","grade3","grade4","finalRating"},
    criteriaSearch={"subject","prelimGrade","midtermGrade","finalsGrade","finalRating","grade"}
)
@Displays({
        @Display(name="schoolYear", width=100, type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="preferredSemester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3"}),
        @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
        @Display(name="passed"),
        @Display(name="grade1",label="1st"),
        @Display(name="grade2",label="2nd"),
        @Display(name="grade3",label="3rd"),
        @Display(name="grade4",label="4th"),
        @Display(name="finalRating",label="Final Rating")
}  )
        @ChildRecords(value = {
    @ChildRecord(template=ChildTemplateListPopupDownButton.class, fieldMapping={"studentId","studentId"}, entity = StudentSubjectExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${studentId}", title="Curriculum Subject")
})
public class StudentSubject1Ext extends bean.person.StudentSubject implements Serializable {
    public static void main(String[] args) {
//        view(StudentSubjectExt.class);
    }
}
