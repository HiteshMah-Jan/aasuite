/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
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
import bean.Enrollment;
import bean.reference.Section;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template=template.screen.TemplateTabPage.class,
        criteriaSearch={"gradeLevel","section","studentId"},
        columnSearch={"schoolYear","gradeLevel","section","student","totalUnit",
			"gpa1","gpa2","gpa3","gpa4","gpaFinal",
			"rankQ1","rankQ2","rankQ3","rankQ4","rankFinal"
			}, title="Final Grade/Ranking",
        gridCount=10, orderBy="a.schoolYear, a.gradeLevel, a.section, a.student",
        select="SELECT a FROM Enrollment a WHERE a.schoolYear='${useYear}'")
@ChildRecords(value = {
    @ChildRecord(template = ChildTemplateListOnly.class, fieldMapping = {"seq", "enrollmentId"}, entity = StudentSubjectExt.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${studentId} AND a.subject LIKE '${gradeLevel}%'", title = "Subjects")
})
        @DisplayGroups({
    @DisplayGroup(title = "Ranking", gridCount = 10, fields = {"rankQ1", "rankQ2", "rankQ3", "rankQ4", "rankFinal"}),
    @DisplayGroup(title = "GPA", gridCount =10, fields = {"gpa1", "gpa2", "gpa3","gpa4", "gpaFinal"})
   
    
})
@Displays({
        @Display(name="schoolYear",gridFieldWidth=2,width=-1),
       // @Display(name="gradeLevel", type="PopSearch", linktoBean=GradeLevel.class),
        @Display(name="section", type="PopSearch", linktoBean=Section.class,gridFieldWidth=7,width=-1),
       // @Display(name="studentId", label="Student", type="PopSearch", linktoBean= Student.class, gridFieldWidth=3),
        
    @Display(name = "q1OpMath",leftLabel="OP Math",labelTop=true,label="Q1"),
    @Display(name = "q2OpMath",labelTop=true,label="Q2"),
    @Display(name = "q3OpMath",labelTop=true,label="Q3"),
    @Display(name = "q4OpMath",labelTop=true,label="Q4"),
    @Display(name = "qallOpMath",labelTop=true,label="Final"),
        
    @Display(name = "q1Reading",label="Reading"),
    @Display(name = "q2Reading",hideLabel=true),
    @Display(name = "q3Reading",hideLabel=true),
    @Display(name = "q4Reading",hideLabel=true),
    @Display(name = "qallReading",hideLabel=true),
        
    @Display(name = "q1Writing",label="Writing"),
    @Display(name = "q2Writing",hideLabel=true),
    @Display(name = "q3Writing",hideLabel=true),
    @Display(name = "q4Writing",hideLabel=true),
    @Display(name = "qallWriting",hideLabel=true),
        
    @Display(name = "q1Math",label="Math"),
    @Display(name = "q2Math",hideLabel=true),
    @Display(name = "q3Math",hideLabel=true),
    @Display(name = "q4Math",hideLabel=true),
    @Display(name = "qallMath",hideLabel=true),
        
    @Display(name = "q1Science",label="Science"),
    @Display(name = "q2Science",hideLabel=true),
    @Display(name = "q3Science",hideLabel=true),
    @Display(name = "q4Science",hideLabel=true),
    @Display(name = "qallScience",hideLabel=true),
        
    @Display(name = "q1English",label="English"),
    @Display(name = "q2English",hideLabel=true),
    @Display(name = "q3English",hideLabel=true),
    @Display(name = "q4English",hideLabel=true),
    @Display(name = "qallEnglish",hideLabel=true),
        
    @Display(name = "q1Filipino",label="Filipino"),
    @Display(name = "q2Filipino",hideLabel=true),
    @Display(name = "q3Filipino",hideLabel=true),
    @Display(name = "q4Filipino",hideLabel=true),
    @Display(name = "qallFilipino",hideLabel=true),
        
    @Display(name = "q1Computer",label="Computer"),
    @Display(name = "q2Computer",hideLabel=true),
    @Display(name = "q3Computer",hideLabel=true),
    @Display(name = "q4Computer",hideLabel=true),
    @Display(name = "qallComputer",hideLabel=true),
        
    @Display(name = "q1CCF",label="CCF"),
    @Display(name = "q2CCF",hideLabel=true),
    @Display(name = "q3CCF",hideLabel=true),
    @Display(name = "q4CCF",hideLabel=true),
    @Display(name = "qallCCF",hideLabel=true),
        
    @Display(name = "q1AP",label="Araling Panlipunan"),
    @Display(name = "q2AP",hideLabel=true),
    @Display(name = "q3AP",hideLabel=true),
    @Display(name = "q4AP",hideLabel=true),
    @Display(name = "qallAP",hideLabel=true),
        
    @Display(name = "q1Hekasi",label="HEKASI"),
    @Display(name = "q2Hekasi",hideLabel=true),
    @Display(name = "q3Hekasi",hideLabel=true),
    @Display(name = "q4Hekasi",hideLabel=true),
    @Display(name = "qallHekasi",hideLabel=true),
        
    @Display(name = "q1TLE",label="TLE"),
    @Display(name = "q2TLE",hideLabel=true),
    @Display(name = "q3TLE",hideLabel=true),
    @Display(name = "q4TLE",hideLabel=true),
    @Display(name = "qallTLE",hideLabel=true),
        
    @Display(name = "q1CE",label="CE"),
    @Display(name = "q2CE",hideLabel=true),
    @Display(name = "q3CE",hideLabel=true),
    @Display(name = "q4CE",hideLabel=true),
    @Display(name = "qallCE",hideLabel=true),
        
    @Display(name = "q1Music",label="Music"),
    @Display(name = "q2Music",hideLabel=true),
    @Display(name = "q3Music",hideLabel=true),
    @Display(name = "q4Music",hideLabel=true),
    @Display(name = "qallMusic",hideLabel=true),
        
    @Display(name = "q1Arts",label="Arts"),
    @Display(name = "q2Arts",hideLabel=true),
    @Display(name = "q3Arts",hideLabel=true),
    @Display(name = "q4Arts",hideLabel=true),
    @Display(name = "qallArts",hideLabel=true),
        
    @Display(name = "q1PE",label="PE"),
    @Display(name = "q2PE",hideLabel=true),
    @Display(name = "q3PE",hideLabel=true),
    @Display(name = "q4PE",hideLabel=true),
    @Display(name = "qallPE",hideLabel=true),
        
    @Display(name = "q1ME",label="ME"),
    @Display(name = "q2ME",hideLabel=true),
    @Display(name = "q3ME",hideLabel=true),
    @Display(name = "q4ME",hideLabel=true),
    @Display(name = "qallME",hideLabel=true),
        
    @Display(name = "q1MAPEH",label="MAPEH"),
    @Display(name = "q2MAPEH",hideLabel=true),
    @Display(name = "q3MAPEH",hideLabel=true),
    @Display(name = "q4MAPEH",hideLabel=true),
    @Display(name = "qallMAPEH",hideLabel=true),
        
    @Display(name = "gpa1", label = "Q1",leftLabel="GPA",labelTop=true),
    @Display(name = "gpa2", label = "Q2",labelTop=true),
    @Display(name = "gpa3", label = "Q3",labelTop=true),
    @Display(name = "gpa4", label = "Q4",labelTop=true),
    @Display(name = "gpaFinal", label = "Final",labelTop=true),
        
    @Display(name = "rankQ1", label = "Q1",leftLabel="Rank",labelTop=true),
    @Display(name = "rankQ2", label = "Q2",labelTop=true),
    @Display(name = "rankQ3", label = "Q3",labelTop=true),
    @Display(name = "rankQ4", label = "Q4",labelTop=true),
    @Display(name = "rankFinal", label = "Final",labelTop=true)
})
//        @ActionButtons( {
//		
//		@ActionButton(name = "btnPrintTOR", label = "Print TOR")
//})
		// @ActionButton(name="btnAssessNoEnrollment",
		// label="Auto Assess No Enrollment", parentOnly=false),
		
//@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate Q1"),
//    @ActionButton(name = "btnCalculateQ2", label = "Q2"),
//    @ActionButton(name = "btnCalculateQ3", label = "Q3"),
//    @ActionButton(name = "btnCalculateQ4", label = "Q4"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating")
//})
@Reports({
    @template.Report(reportFile = "SectionReportCardQuarter1", reportTitle = "1st Qtr. Card", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter2", reportTitle = "2nd", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter3", reportTitle = "3rd", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter4", reportTitle = "4th", reportSql = "${section}")
})
public class EnrollmentGradeExt extends Enrollment {
	public static void main(String[] args) {
		view(EnrollmentGradeExt.class);
	}
}
