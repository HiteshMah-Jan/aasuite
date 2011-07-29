/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;
import bean.Student;
import bean.person.StudentSubject;
import bean.reference.GradeLevel;
import bean.reference.Section;
import bean.reference.Subject;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabPage.class,
columnSearch = {"gradeLevel","section","studentName", "subject", "grade1", "grade2", "grade3", "grade4", "finalRating",
	"rankQ1","rankQ2","rankQ3","rankQ4","rankFinal"
	}, 
criteriaSearch = {"gradeLevel", "section", "studentId", "subject"},
gridCount = 6, title = "Per Student Subject")
@DisplayGroups({
    @DisplayGroup(title = "Subject Grades per Quarter", gridCount = 12, fields = {"grade1", "grade2", "grade3", "grade4", "finalRating","actionTaken"}),
    @DisplayGroup(title = "Ranking", gridCount = 12, fields = {"rankQ1", "rankQ2", "rankQ3", "rankQ4", "rankQ5", "rankFinal"})
})
@Displays({
    @Display(name = "studentId", type = "PopSearch", linktoBean = Student.class, gridFieldWidth = 3, width = 300, label = "Student", enabled=false),
    @Display(name = "gradeLevel", width = 100, type = "PopSearch", linktoBean=GradeLevel.class, enabled=false),
    @Display(name = "section", width = 100, type = "PopSearch", linktoBean=Section.class, enabled=false),
    @Display(name = "subject", type = "PopSearch", linktoBean = Subject.class, enabled=false),
    @Display(name = "passed", enabled=false),
    @Display(name = "grade1", label = "1st Qtr.",labelTop=true,width=50),
    @Display(name = "grade2", label = "2nd",labelTop=true,width=50),
    @Display(name = "grade3", label = "3rd",labelTop=true,width=50),
    @Display(name = "grade4", label = "4th",labelTop=true,width=50),
    @Display(name = "finalRating", label = "Final",labelTop=true,width=50),
    @Display(name = "actionTaken", label = "Action Taken", type = "Combo", modelCombo = {"PASSED", "FAILED", "DO", "DU"},labelTop=true,width=80),
    
    @Display(name="rankQ1", type="Label", label="Rank 1st Qtr."),
    @Display(name="rankQ2", type="Label", label="2nd"),
    @Display(name="rankQ3", type="Label", label="3rd"),
    @Display(name="rankQ4", type="Label", label="4th"),
    @Display(name="rankFinal", type="Label", label="Final")
})
@ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListOnly.class,
    fieldMapping = {"seq", "studentSubjectId"}, entity = StudentSubjectDetailGradingExt.class, sql = "SELECT a FROM StudentSubjectDetailGrading a WHERE a.studentId=${studentId} AND a.subject='${subject}'", title = "Subject Tasks and Grades")
})
@Reports({
    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summary 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_Grades", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_Grades", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_Grades", reportTitle = "4th", reportSql = "${section}"),
    @template.Report(reportFile = "QAll_Grades", reportTitle = "GPA", reportSql = "${section}"),
    @template.Report(reportFile = "Q1_GradesAll", reportTitle = "Admin 1st", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_GradesAll", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_GradesAll", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_GradesAll", reportTitle = "4th", reportSql = "${section}"),
    @template.Report(reportFile = "QAll_GradesAll", reportTitle = "GPA", reportSql = "${section}")
})
//@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate 1st Qtr."),
//    @ActionButton(name = "btnCalculateQ2", label = "2nd"),
//    @ActionButton(name = "btnCalculateQ3", label = "3rd"),
//    @ActionButton(name = "btnCalculateQ4", label = "4th"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating")
//    @ActionButton(name = "btnCalculateAll", label = "Calculate Grades")
//})
public class StudentGradingExt extends StudentSubject {
    public static void main(String[] args) {
        view(StudentGradingExt.class);
    }
}
