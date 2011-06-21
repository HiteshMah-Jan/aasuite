/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Enrollment;
import bean.Student;
import bean.person.StudentSubject;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.GradeLevel;
import bean.reference.Subject;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopup;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePageLeftRightNoCriteria;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabSinglePageLeftRightNoCriteria.class,
columnSearch = {"gradeLevel","section","studentName", "subject", "unit", "finalRating"},  
criteriaSearch = {"gradeLevel", "section", "studentId", "subject"},
gridCount = 6, title = "Per Subject Grades/Ranking", 
select="SELECT a FROM StudentSubject a WHERE a.facultyId=${personId}", orderBy="a.studentName")
@DisplayGroups({
    @DisplayGroup(title = "Subject Grades per Quarter", gridCount = 8, fields = {"grade1", "grade2", "grade3", "grade4", "finalRating","actionTaken"})
//    @DisplayGroup(title = "Ranking", gridCount = 12, fields = {"rankQ1", "rankQ2", "rankQ3", "rankQ4", "rankQ5", "rankFinal"})
})
@Displays({
    @Display(name = "studentId", type = "PopSearch", linktoBean = Student.class, gridFieldWidth = 3, width = 200, label = "Student", enabled=false),
    @Display(name = "gradeLevel", width = 100, type = "PopSearch", linktoBean=GradeLevel.class, enabled=false),
    @Display(name = "subject", type = "PopSearch", linktoBean = Subject.class, gridFieldWidth = 3, width = 200, enabled=false),
    @Display(name = "passed", enabled=false),
    @Display(name = "finalRating", label = "Final",labelTop=true,width=50),
    @Display(name = "actionTaken", label = "Action Taken", type = "Combo", modelCombo = {"PASSED", "FAILED", "DO", "DU"},labelTop=true,width=80, gridFieldWidth=7)
//    
//    @Display(name="rankQ1", type="Label", label="Rank 1st Qtr."),
//    @Display(name="rankQ2", type="Label", label="2nd"),
//    @Display(name="rankQ3", type="Label", label="3rd"),
//    @Display(name="rankQ4", type="Label", label="4th"),
//    @Display(name="rankFinal", type="Label", label="Final")
})
@ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListOnly.class,
    fieldMapping = {"seq", "studentSubjectId"}, entity = StudentSubjectDetailGradingCollegeExt.class, sql = "SELECT a FROM StudentSubjectDetailGrading a WHERE a.studentId=${studentId} AND a.subject='${subject}' ORDER BY a.component", title = "Subject Tasks and Grades")
})
@Reports({
//    @template.Report(reportFile = "ComponentGradePerSection", reportTitle = "Component Grade", reportSql = "${scheduleId}"),
//    @template.Report(reportFile = "ComponentGradePerStudent", reportTitle = "Student Grade", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "QAll_Grades", reportTitle = "GPA", reportSql = "${section}")
})
@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate 1st Qtr."),
//    @ActionButton(name = "btnCalculateQ2", label = "2nd"),
//    @ActionButton(name = "btnCalculateQ3", label = "3rd"),
//    @ActionButton(name = "btnCalculateQ4", label = "4th"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating")
    @ActionButton(name = "btnCalculateAll", label = "Calculate Grades")
})
public class StudentGradingCollegeExt extends StudentSubject {
    public static void main(String[] args) {
        view(StudentGradingCollegeExt.class);
    }
}
