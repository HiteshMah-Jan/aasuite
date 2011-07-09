/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.EmployeeFaculty;
import bean.Student;
import bean.person.StudentSubject;
import bean.reference.Section;
import bean.reference.Subject;
import java.io.Serializable;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateNoForm;
import template.screen.TemplateNoFormWithSearch;
import template.screen.TemplateSearchOnly;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate( gridCount=10, title="Per Subject Grades", template=TemplateNoFormWithSearch.class, 
    columnSearch={"schoolYear","section","studentName","faculty","subject","grade1","grade2","grade3","grade4","finalRating","unit","unitShareQ1","unitShareQ2","unitShareQ3","unitShareQ4","unitShareFinal"},
    criteriaSearch={"section","studentId","facultyId","subject","finalRating"}, 
    sumFooter="10,11,12,13,14,15,16",
    select="SELECT a FROM StudentSubject a WHERE a.schoolYear='${useYear}' AND a.enrollmentId>0"
)
@Displays({
    	@Display(name="studentName", width=100, label="Student"),
        @Display(name="schoolYear", width=100),
        @Display(name="section", width=100, type="PopSearch", linktoBean=Section.class, label="Section"),
        @Display(name="studentId", width=100, type="PopSearch", linktoBean=Student.class, label="Student"),
        @Display(name="facultyId", width=100, type="PopSearch", linktoBean=EmployeeFaculty.class, label="Faculty"),
        @Display(name="preferredSemester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3"}),
        @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
        @Display(name="passed"),
        @Display(name="grade1",label="1st"),
        @Display(name="grade2",label="2nd"),
        @Display(name="grade3",label="3rd"),
        @Display(name="grade4",label="4th"),
        @Display(name="finalRating",label="Final Rating"),
        @Display(name="unitShareQ1",label="Unit Share Q1"),
        @Display(name="unitShareQ2",label="Q2"),
        @Display(name="unitShareQ3",label="Q3"),
        @Display(name="unitShareQ4",label="Q4"),
        @Display(name="unitShareFinal",label="Final")
}  )
//@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate Q1"),
//    @ActionButton(name = "btnCalculateQ2", label = "Q2"),
//    @ActionButton(name = "btnCalculateQ3", label = "Q3"),
//    @ActionButton(name = "btnCalculateQ4", label = "Q4"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating")
//})
public class StudentSubjectSRUExt extends bean.person.StudentSubject implements Serializable {
    public static void main(String[] args) {
//        view(StudentSubjectExt.class);
    }
}
