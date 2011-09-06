/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRightNoCriteria;
import bean.EmployeeFaculty;
import bean.Student;
import bean.person.StudentValuesGrading;
import bean.reference.GradeLevel;
import bean.reference.Section;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
   template = TemplateTabSinglePageLeftRightNoCriteria.class, showChart = false, showFiles = false,showImages=false,
    columnSearch={"gradeLevel","section","student","studentNumber"},
    criteriaSearch={"gradeLevel","section","student"},
    gridCount=4, title="Student Values Grading", 
    select="SELECT a FROM StudentValuesGrading a, Student b, Section s WHERE s.facultyId=${personId} AND a.gradeLevel=s.gradeLevel AND a.section=s.code AND a.section=b.section AND a.studentId=b.personId",
    orderBy="a.gradeLevel, a.section, b.gender DESC, a.student")
    
@ChildRecords(value = { //@ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","admissionId"}, entity=AdmissionExamReference.class, sql="SELECT a FROM AdmissionExam a WHERE a.admissionId=${seq} ORDER BY a.examType", title="Exams")
},
info = {
    @ParentAddInfo(title = "Affective Development  (Preschool)", gridCount = 2,
    		displayFields = {""},hideGroup="1,2,3,4,5,6,7,8"),
    @ParentAddInfo(title = "Psychomotor Development  (Preschool)", gridCount = 2,
    		displayFields = {""},hideGroup="0,2,3,4,5,6,7,8"),
    @ParentAddInfo(title = "Mega Skills  (Grade 1-4)", gridCount = 2,
    		displayFields = {""},hideGroup="0,1,3,4,5,6,7,8"),
    @ParentAddInfo(title = "Values Promoted  (Grade 5-6/High School)", gridCount = 2,
    		displayFields = {""},hideGroup="0,1,2,4,5,6,7,8"),
    @ParentAddInfo(title = "ZL2 Components  (Grade 5-6/High School)", gridCount = 2,
    		displayFields = {""},hideGroup="0,1,2,3,5,6,7,8"),
    @ParentAddInfo(title = "Monthly Attendance", gridCount = 2,
    	    displayFields = {""},hideGroup="0,1,2,3,4")
})
    @DisplayGroups({
    
//    @DisplayGroup(title = "Teacher's Comment", gridCount = 4, fields = {}),
//    @DisplayGroup(title = "Misc Grades", gridCount = 4, fields = {}),
    //Affective Development count = 15
    @DisplayGroup(title = "Affective Development", gridCount = 4, fields = {"kp2","fh2","fr2","aw2","ft2","ic2","ih2","wi2","id2","neatAndOrganize2","de2","wp2","hs2","ir2","ls2"},addInfoOnly=true),
    //Psychomotor Development count = 11
    @DisplayGroup(title = "Psychomotor Development", gridCount = 4, fields = {"ap2","ce2","tc2","cfp2","df2","wlr2","cst2","ocs2","cwp2","cp2","pa2"},addInfoOnly=true),
    //Megaskills count = 10
    @DisplayGroup(title = "Mega Skills", gridCount = 4, fields = {"con2", "mot2", "eff2", "res2","ini2", "per2","car2","tea2","com2","pro2","clubsAndParticipation2","focus2","respect2","scouting2"},addInfoOnly=true),
    //Values Promoted count = 14
    @DisplayGroup(title = "Values Promoted", gridCount = 4, fields = {"els2", "wfr2", "apgw2", "spaa2","iva2", "isl2", "aspvi2", "hspd2", "sd2", "put2","hlew2","prs2","cr2","cws2"},addInfoOnly=true),
    //ZL2 Components count = 6
    @DisplayGroup(title = "ZL2 Components", gridCount = 4, fields = {"pfe2","cra2","ca2","kin2","ec12","ec22"},addInfoOnly=true),
    @DisplayGroup(title = "Monthly Days", gridCount = 12, fields = {
    		"jan", "feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dece" }, addInfoOnly = true),
    @DisplayGroup(title = "Monthly Absent", gridCount = 12, fields = {
    		"janAbsent", "febAbsent","marAbsent","aprAbsent","mayAbsent","junAbsent","julAbsent","augAbsent","sepAbsent","octAbsent","novAbsent","deceAbsent" }, addInfoOnly = true),
    @DisplayGroup(title = "Monthly Tardiness", gridCount = 12, fields = {
    	    "janTardy", "febTardy","marTardy","aprTardy","mayTardy","junTardy","julTardy","augTardy","sepTardy","octTardy","novTardy","deceTardy" }, addInfoOnly = true),
    @DisplayGroup(title = "Extra Curricular/Deportment", gridCount = 6, fields = {"deportment12","ec12", "ec22" }, addInfoOnly = true)
    
})
@Displays({
    @Display(name = "studentNumber",width=100,type="Label"),
    @Display(name = "facultyId", type = "PopSearch", linktoBean = EmployeeFaculty.class,label="Faculty",width=150,enabled=false),
    @Display(name = "studentId", type = "PopSearch", linktoBean = Student.class,label="Student",width=150,enabled=false),
    @Display(name = "gradeLevel", type = "PopSearch", linktoBean = GradeLevel.class,label="Grade Level",width=150,enabled=false),
    @Display(name = "section", type = "PopSearch", linktoBean = Section.class,width=150,enabled=false),
    @Display(name = "status",type="Combo",width=150,modelCombo={"New","Old"}),
	@Display(name = "totalDays2",width=50, enabled=false),
	@Display(name = "present2",width=30,mergeFields={"absent2","tardy2"},label="Pres/Abs/Tardy", enabled=false),
	@Display(name = "absent2",width=30,hideLabel=true),
	@Display(name = "tardy2",width=30,hideLabel=true),
    @Display(name = "comment2",width=400,gridFieldWidth=3,height=50,label="2nd Quarter Comment",type="TextArea",labelTop=true,upCase=false),
   
    //Affective Development
    @Display(name = "kp2", addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30),
//    @Display(name = "fh2", addInfoOnly = true, label = "Is Aware of Filipino Culture and Heritage", width = 30),
    @Display(name = "fr2", addInfoOnly = true, label = "Follows Classroom Routine", width = 30),
    @Display(name = "aw2", addInfoOnly = true, label = "Accepts Suggestions Willingly", width = 30),
    @Display(name = "ft2", addInfoOnly = true, label = "Finishes Assigned Task on Time", width = 30),
    @Display(name = "ic2", addInfoOnly = true, label = "Is Confident", width = 30),
    @Display(name = "ih2", addInfoOnly = true, label = "Is Honest", width = 30),
    @Display(name = "wi2", addInfoOnly = true, label = "Works Independently", width = 30),
    @Display(name = "id2", addInfoOnly = true, label = "Is Responsible", width = 30),
    @Display(name = "neatAndOrganize2", addInfoOnly = true, label = "Is Neat and Organized", width = 30),
    @Display(name = "de2", addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30),
    @Display(name = "wp2", addInfoOnly = true, label = "Works and Plays Well with Others", width = 30),
    @Display(name = "hs2", addInfoOnly = true, label = "Adapts to Varied Situations", width = 30),
    @Display(name = "ir2", addInfoOnly = true, label = "Is Respectful to Others", width = 30),
    @Display(name = "ls2", addInfoOnly = true, label = "Listens While others Speak", width = 30),
    
    //Psychomotor Development
    @Display(name = "ap2", addInfoOnly = true, label = "Actively Participates at Play", width = 30),
    @Display(name = "ce2", addInfoOnly = true, label = "Colors Evenly", width = 30),
    @Display(name = "tc2", addInfoOnly = true, label = "Traces Clearly", width = 30),
//    @Display(name = "cfp2", addInfoOnly = true, label = "Can Follow Patterns", width = 30),
    @Display(name = "df2", addInfoOnly = true, label = "Draws Recognizable Figures", width = 30),
    @Display(name = "wlr2", addInfoOnly = true, label = "Writes Following Left to Right", width = 30),
//    @Display(name = "cst2", addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30),
    @Display(name = "ocs2", addInfoOnly = true, label = "Observes Correct Spacing", width = 30),
    @Display(name = "cwp2", addInfoOnly = true, label = "Copies with Precision", width = 30),
    @Display(name = "cp2", addInfoOnly = true, label = "Cuts Precisely", width = 30),
    @Display(name = "pa2", addInfoOnly = true, label = "Pastes Accurately", width = 30),
    @Display(name = "cfp2", addInfoOnly = true, label = "Manipulates Simple Tools Correctly", width = 30),
    @Display(name = "cst2", addInfoOnly = true, label = "Demonstrates Life's Practical Skills", width = 30),
   
    //Mega Skill
    @Display(name = "con2", addInfoOnly = true, label = "Confidence", width = 30),
    @Display(name = "mot2", addInfoOnly = true, label = "Motivation", width = 30),
    @Display(name = "eff2", addInfoOnly = true, label = "Effort", width = 30),
    @Display(name = "res2", addInfoOnly = true, label = "Responsiblity", width = 30),
    @Display(name = "ini2", addInfoOnly = true, label = "Initiative", width = 30),
    @Display(name = "per2", addInfoOnly = true, label = "Perseverance", width = 30),
    @Display(name = "car2", addInfoOnly = true, label = "Caring", width = 30),
    @Display(name = "tea2", addInfoOnly = true, label = "Teamwork", width = 30),
    @Display(name = "com2", addInfoOnly = true, label = "Common Sense", width = 30),
    @Display(name = "pro2", addInfoOnly = true, label = "Problem Solving", width = 30),
    @Display(name = "focus2", addInfoOnly = true, label = "Focus", width = 30),
    @Display(name = "respect2", addInfoOnly = true, label = "Respect", width = 30),
    @Display(name = "scouting2",width=50),
    @Display(name = "clubsAndParticipation2",width=30,label="Clubs"),
   
    // Values promoted
    @Display(name = "els2", addInfoOnly = true, label = "Effective Leadership Skills", width = 30),
    @Display(name = "wfr2", addInfoOnly = true, label = "Wholesome Followership Role", width = 30),
    @Display(name = "apgw2", addInfoOnly = true, label = "Active Participation at Group Work", width = 30),
    @Display(name = "spaa2", addInfoOnly = true, label = "Seriousness of Purpose in All Activities", width = 30),
    @Display(name = "iva2", addInfoOnly = true, label = "Integration of Values Acquired", width = 30),
    @Display(name = "isl2", addInfoOnly = true, label = "Integration of Skills Learned", width = 30),
    @Display(name = "aspvi2", addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30),
    @Display(name = "hspd2", addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30),
    @Display(name = "sd2", addInfoOnly = true, label = "Self-directedness", width = 30),
    @Display(name = "put2", addInfoOnly = true, label = "Productive Use of Time", width = 30),
    @Display(name = "hlew2", addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30),
    @Display(name = "prs2", addInfoOnly = true, label = "Practical Recycling Skills", width = 30),
    @Display(name = "cr2", addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30),
    @Display(name = "cws2", addInfoOnly = true, label = "Completion of Work Started", width = 30),
    
    //ZL2 components
    @Display(name = "pfe2", addInfoOnly = true, label = "Program for Exceptionality", width = 30),
    @Display(name = "cra2", addInfoOnly = true, label = "Career-related Activities", width = 30),
    @Display(name = "ca2", addInfoOnly = true, label = "Creative Activities", width = 30),
    @Display(name = "kin2", addInfoOnly = true, label = "Kinesthetic", width = 30),
    @Display(name = "ec12", addInfoOnly = true, label = "Extra Curricular 1", width = 30),
    @Display(name = "ec22", addInfoOnly = true, label = "Extra Curricular 2", width = 30),

	@Display(name = "jan", width = 30, label="Jan"),
	@Display(name = "feb", width = 30, label="Feb"),
	@Display(name = "mar", width = 30, label="Mar"),
	@Display(name = "apr", width = 30, label="Apr"),
	@Display(name = "may", width = 30, label="May"),
	@Display(name = "jun", width = 30, label="Jun"),
	@Display(name = "jul", width = 30, label="Jul"),
	@Display(name = "aug", width = 30, label="Aug"),
	@Display(name = "sep", width = 30, label="Sep"),
	@Display(name = "oct", width = 30, label="Oct"),
	@Display(name = "nov", width = 30, label="Nov"),
	@Display(name = "dece", width = 30, label="Dec"),
	
	@Display(name = "janAbsent", width = 30, label="Jan"),
	@Display(name = "febAbsent", width = 30, label="Feb"),
	@Display(name = "marAbsent", width = 30, label="Mar"),
	@Display(name = "aprAbsent", width = 30, label="Apr"),
	@Display(name = "mayAbsent", width = 30, label="May"),
	@Display(name = "junAbsent", width = 30, label="Jun"),
	@Display(name = "julAbsent", width = 30, label="Jul"),
	@Display(name = "augAbsent", width = 30, label="Aug"),
	@Display(name = "sepAbsent", width = 30, label="Sep"),
	@Display(name = "octAbsent", width = 30, label="Oct"),
	@Display(name = "novAbsent", width = 30, label="Nov"),
	@Display(name = "deceAbsent", width = 30, label="Dec"),
	@Display(name = "janTardy", width = 30, label="Jan"),
	@Display(name = "febTardy", width = 30, label="Feb"),
	@Display(name = "marTardy", width = 30, label="Mar"),
	@Display(name = "aprTardy", width = 30, label="Apr"),
	@Display(name = "mayTardy", width = 30, label="May"),
	@Display(name = "junTardy", width = 30, label="Jun"),
	@Display(name = "julTardy", width = 30, label="Jul"),
	@Display(name = "augTardy", width = 30, label="Aug"),
	@Display(name = "sepTardy", width = 30, label="Sep"),
	@Display(name = "octTardy", width = 30, label="Oct"),
	@Display(name = "novTardy", width = 30, label="Nov"),
	@Display(name = "deceTardy", width = 30, label="Dec"),
    @Display(name = "deportment2",width=30)
})
@ActionButtons({
    @ActionButton(name = "btnGenerateRecords", label = "Generate Records"),
    @ActionButton(name = "btnRankQ2", label = "Rank 2nd Quarter")
})
@Reports({
    @template.Report(reportFile = "Q1_ValuesGrades", reportTitle = "1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_ValuesGrades", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_ValuesGrades", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_ValuesGrades", reportTitle = "4th", reportSql = "${section}"),
    @template.Report(reportFile = "Q1_NarrativeGrades", reportTitle = "Narr. 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_NarrativeGrades", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_NarrativeGrades", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_NarrativeGrades", reportTitle = "4th", reportSql = "${section}"),

    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summary 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_Grades", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_Grades", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_Grades", reportTitle = "4th", reportSql = "${section}")
})
public class StudentValuesFilterGradingQ2Ext extends StudentValuesGrading implements Serializable {
}
