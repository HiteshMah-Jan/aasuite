/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
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
import template.screen.TemplateLeftRight;
import util.BeanUtil;
import util.PanelUtil;
import bean.EmployeeFaculty;
import bean.Person;
import bean.Student;
import bean.admin.AppConfig;
import bean.reference.CourseTotalDays;
import bean.reference.GradeLevel;
import bean.reference.LockGrading;
import bean.reference.Section;
import constants.UserInfo;

/**
 * 
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentValuesGrading")
@UITemplate(template = TemplateLeftRight.class, showChart = false, showFiles = false, showImages = false, columnSearch = {
		"faculty", "gradeLevel", "section", "student", "studentNumber" }, criteriaSearch = {
		"facultyId", "gradeLevel", "section", "studentId" }, gridCount = 2, title = "Student Values Grading")
@ChildRecords(value = { // @ChildRecord(template=ChildTemplateListPopup.class,
						// fieldMapping={"seq","admissionId"},
						// entity=AdmissionExamReference.class,
						// sql="SELECT a FROM AdmissionExam a WHERE a.admissionId=${seq} ORDER BY a.examType",
						// title="Exams")
}, info = {
		@ParentAddInfo(title = "Affective Development  (Preschool)", gridCount = 2, displayFields = { "" }, hideGroup = "0,3,4,5,6"),
		@ParentAddInfo(title = "Psychomotor Development  (Preschool)", gridCount = 2, displayFields = { "" }, hideGroup = "0,2,4,5,6"),
		@ParentAddInfo(title = "Mega Skills  (Grade 1-4)", gridCount = 2, displayFields = { "" }, hideGroup = "0,2,3,5,6"),
		@ParentAddInfo(title = "Values Promoted  (Grade 5-6/High School)", gridCount = 2, displayFields = { "" }, hideGroup = "0,2,3,4,6"),
		@ParentAddInfo(title = "ZL2 Components  (Grade 5-6/High School)", gridCount = 2, displayFields = { "" }, hideGroup = "0,2,3,4,5"),
                @ParentAddInfo(title = "Comments", gridCount = 2, displayFields = { ""}, hideGroup = "1,2,3,4,5,6")
// @ParentAddInfo(title = "Affective Development", gridCount = 2,
// displayFields =
// {"kp","fh","fr","aw","ft","ic","ih","wi","id","in","de","wp","hs","ir","ls"}),
// @ParentAddInfo(title = "Psychomotor Development", gridCount = 2,
// displayFields =
// {"ap","ce","tc","cfp","df","wlr","cst","ocs","cwp","cp","pa"})
//    
})
@DisplayGroups( {

		@DisplayGroup(title = "Teacher's Comment", gridCount = 2, fields = {
				"comment1", "comment2", "comment3", "comment4" },addInfoOnly=true),
		@DisplayGroup(title = "Misc Grades", gridCount = 8, fields = {
				"totalDays", "present", "absent","tardy",
				"totalDays2", "present2", "absent2","tardy2",
				"totalDays3", "present3", "absent3","tardy3",
				"totalDays4", "present4", "absent4","tardy4"}),
		// Affective Development count = 15
		@DisplayGroup(title = "Affective Development", gridCount = 10, fields = {
				"kp", "kp2", "kp3", "kp4", "kpFinal", "fh", "fh2", "fh3",
				"fh4", "fhFinal", "fr", "fr2", "fr3", "fr4", "frFinal", "aw",
				"aw2", "aw3", "aw4", "awFinal", "ft", "ft2", "ft3", "ft4",
				"ftFinal", "ic", "ic2", "ic3", "ic4", "icFinal", "ih", "ih2",
				"ih3", "ih4", "ihFinal", "wi", "wi2", "wi3", "wi4", "wiFinal",
				"id", "id2", "id3", "id4", "idFinal", "neatAndOrganize",
				"neatAndOrganize2", "neatAndOrganize3", "neatAndOrganize4",
				"neatAndOrganizeFinal", "de", "de2", "de3", "de4", "deFinal",
				"wp", "wp2", "wp3", "wp4", "wpFinal", "hs", "hs2", "hs3",
				"hs4", "hsFinal", "ir", "ir2", "ir3", "ir4", "irFinal", "ls",
				"ls2", "ls3", "ls4", "lsFinal" }, addInfoOnly = true),
		// Psychomotor Development count = 11
		@DisplayGroup(title = "Psychomotor Development", gridCount = 10, fields = {
				"ap", "ap2", "ap3", "ap4", "apFinal", "ce", "ce2", "ce3",
				"ce4", "ceFinal", "tc", "tc2", "tc3", "tc4", "tcFinal", "cfp",
				"cfp2", "cfp3", "cfp4", "cfpFinal", "df", "df2", "df3", "df4",
				"dfFinal", "wlr", "wlr2", "wlr3", "wlr4", "wlrFinal", "cst",
				"cst2", "cst3", "cst4", "cstFinal", "ocs", "ocs2", "ocs3",
				"ocs4", "ocsFinal", "cwp", "cwp2", "cwp3", "cwp4", "cwpFinal",
				"cp", "cp2", "cp3", "cp4", "cpFinal", "pa", "pa2", "pa3",
				"pa4", "paFinal" }, addInfoOnly = true),
		// Megaskills count = 12
		@DisplayGroup(title = "Mega Skills", gridCount = 10, fields = { "con",
				"con2", "con3", "con4", "conFinal", "mot", "mot2", "mot3",
				"mot4", "motFinal", "eff", "eff2", "eff3", "eff4", "effFinal",
				"res", "res2", "res3", "res4", "resFinal", "ini", "ini2",
				"ini3", "ini4", "iniFinal", "per", "per2", "per3", "per4",
				"perFinal", "car", "car2", "car3", "car4", "carFinal", "tea",
				"tea2", "tea3", "tea4", "teaFinal", "com", "com2", "com3",
				"com4", "comFinal", "pro", "pro2", "pro3", "pro4", "proFinal",
				"focus", "focus2", "focus3", "focus4", "focusFinal", "respect",
				"respect2", "respect3", "respect4", "respectFinal",
				"clubsAndParticipation", "clubsAndParticipation2",
				"clubsAndParticipation3", "clubsAndParticipation4",
				"clubsAndParticipationFinal",
				"scouting", "scouting2",
				"scouting3", "scouting4",
				"scoutingFinal"}, addInfoOnly = true),
		// Values Promoted count = 14
		@DisplayGroup(title = "Values Promoted", gridCount = 10, fields = {
				"els", "els2", "els3", "els4", "elsFinal", "wfr", "wfr2",
				"wfr3", "wfr4", "wfrFinal", "apgw", "apgw2", "apgw3", "apgw4",
				"apgwFinal", "spaa", "spaa2", "spaa3", "spaa4", "spaaFinal",
				"iva", "iva2", "iva3", "iva4", "ivaFinal", "isl", "isl2",
				"isl3", "isl4", "islFinal", "aspvi", "aspvi2", "aspvi3",
				"aspvi4", "aspviFinal", "hspd", "hspd2", "hspd3", "hspd4",
				"hspdFinal", "sd", "sd2", "sd3", "sd4", "sdFinal", "put",
				"put2", "put3", "put4", "putFinal", "hlew", "hlew2", "hlew3",
				"hlew4", "hlewFinal", "prs", "prs2", "prs3", "prs4",
				"prsFinal", "cr", "cr2", "cr3", "cr4", "crFinal", "cws",
				"cws2", "cws3", "cws4", "cwsFinal" }, addInfoOnly = true),
		// ZL2 Components count = 6
		@DisplayGroup(title = "ZL2 Components", gridCount = 10, fields = {
				"pfe", "pfe2", "pfe3", "pfe4", "pfeFinal", "cra", "cra2",
				"cra3", "cra4", "craFinal", "ca", "ca2", "ca3", "ca4",
				"caFinal", "kin", "kin2", "kin3", "kin4", "kinFinal", "ec1",
				"ec12", "ec13", "ec14", "ec1Final", "ec2", "ec22", "ec23",
				"ec24", "ec2Final" }, addInfoOnly = true),
        @DisplayGroup(title = "Monthly Attendance", gridCount = 12, fields = {
        		"jan", "feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dece" }, addInfoOnly = true)

})
@Displays( {
		@Display(name = "studentNumber", width = 150, type = "Label"),
		@Display(name = "facultyId", type = "PopSearch", linktoBean = EmployeeFaculty.class, label = "Faculty", width = 300, enabled = false),
		@Display(name = "studentId", type = "PopSearch", linktoBean = Student.class, label = "Student", width = 300, enabled = false),
		@Display(name = "gradeLevel", type = "PopSearch", linktoBean = GradeLevel.class, label = "Grade Level", width = 300),
		@Display(name = "section", type = "PopSearch", linktoBean = Section.class, width = 300),
	    @Display(name = "status",type="Combo",width=300,modelCombo={"Regular","Probationary Academic","Probationary Behavior","Probationary Academic and Behavior"}),
		// @Display(name = "quarter", type="Combo",
		// modelCombo={"1","2","3","4"},width=300),
		@Display(name = "comment1", width = 400, label = "1st Quarter Comment", type = "TextArea", height=60),
		@Display(name = "comment2", width = 400, label = "2nd Quarter Comment", type = "TextArea", height=60),
		@Display(name = "comment3", width = 400, label = "3rd Quarter Comment", type = "TextArea", height=60),
		@Display(name = "comment4", width = 400, label = "4th Quarter Comment", type = "TextArea", height=60),
		@Display(name = "totalDays", width = 30),
		@Display(name = "present", width = 30),
		@Display(name = "absent", width = 30),
		@Display(name = "tardy", width = 30),
		@Display(name = "totalDays2", width = 30),
		@Display(name = "present2", width = 30),
		@Display(name = "absent2", width = 30),
		@Display(name = "tardy2", width = 30),
		@Display(name = "totalDays3", width = 30),
		@Display(name = "present3", width = 30),
		@Display(name = "absent3", width = 30),
		@Display(name = "tardy3", width = 30),
		@Display(name = "totalDays4", width = 30),
		@Display(name = "present4", width = 30),
		@Display(name = "absent4", width = 30),
		@Display(name = "tardy4", width = 30),

		// Affective Development
		@Display(name = "kp", addInfoOnly = true, leftLabel = "Knows and recites Simple Prayers", labelTop = true, label = "1st", width = 30),
		@Display(name = "kp2", addInfoOnly = true, width = 30, labelTop = true, label = "2nd"),
		@Display(name = "kp3", addInfoOnly = true, width = 30, labelTop = true, label = "3rd"),
		@Display(name = "kp4", addInfoOnly = true, width = 30, labelTop = true, label = "4th"),
		@Display(name = "kpFinal", enabled = false, addInfoOnly = true, width = 30, labelTop = true, label = "Final"),
		@Display(name = "fh", addInfoOnly = true, label = "Is Aware of Filipino Culture and Heritage", width = 30),
		@Display(name = "fh2", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fh3", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fh4", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fhFinal", enabled = false, addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fr", addInfoOnly = true, label = "Follows Classroom Routine", width = 30),
		@Display(name = "fr2", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fr3", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "fr4", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "frFinal", enabled = false, addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "aw", addInfoOnly = true, label = "Accepts Suggestions Willingly", width = 30),
		@Display(name = "aw2", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "aw3", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "aw4", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "awFinal", enabled = false, addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "ft", addInfoOnly = true, label = "Finishes Assigned Task on Time", width = 30),
		@Display(name = "ft2", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "ft3", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "ft4", addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "ftFinal", enabled = false, addInfoOnly = true, width = 30, hideLabel = true),
		@Display(name = "ic", addInfoOnly = true, label = "Is Confident", width = 30),
		@Display(name = "ic2", addInfoOnly = true, label = "Is Confident", width = 30, hideLabel = true),
		@Display(name = "ic3", addInfoOnly = true, label = "Is Confident", width = 30, hideLabel = true),
		@Display(name = "ic4", addInfoOnly = true, label = "Is Confident", width = 30, hideLabel = true),
		@Display(name = "icFinal", enabled = false, addInfoOnly = true, label = "Is Confident", width = 30, hideLabel = true),
		@Display(name = "ih", addInfoOnly = true, label = "Is Consistently Honest", width = 30),
		@Display(name = "ih2", addInfoOnly = true, label = "Is Consistently Honest", width = 30, hideLabel = true),
		@Display(name = "ih3", addInfoOnly = true, label = "Is Consistently Honest", width = 30, hideLabel = true),
		@Display(name = "ih4", addInfoOnly = true, label = "Is Consistently Honest", width = 30, hideLabel = true),
		@Display(name = "ihFinal", enabled = false, addInfoOnly = true, label = "Is Consistently Honest", width = 30, hideLabel = true),
		@Display(name = "wi", addInfoOnly = true, label = "Works Independently", width = 30),
		@Display(name = "wi2", addInfoOnly = true, label = "Works Independently", width = 30, hideLabel = true),
		@Display(name = "wi3", addInfoOnly = true, label = "Works Independently", width = 30, hideLabel = true),
		@Display(name = "wi4", addInfoOnly = true, label = "Works Independently", width = 30, hideLabel = true),
		@Display(name = "wiFinal", enabled = false, addInfoOnly = true, label = "Works Independently", width = 30, hideLabel = true),
		@Display(name = "id", addInfoOnly = true, label = "Is Dependable", width = 30),
		@Display(name = "id2", addInfoOnly = true, label = "Is Dependable", width = 30, hideLabel = true),
		@Display(name = "id3", addInfoOnly = true, label = "Is Dependable", width = 30, hideLabel = true),
		@Display(name = "id4", addInfoOnly = true, label = "Is Dependable", width = 30, hideLabel = true),
		@Display(name = "idFinal", enabled = false, addInfoOnly = true, label = "Is Dependable", width = 30, hideLabel = true),
		@Display(name = "neatAndOrganize", addInfoOnly = true, label = "Is Neat and Organized", width = 30),
		@Display(name = "neatAndOrganize2", addInfoOnly = true, label = "Is Neat and Organized", width = 30, hideLabel = true),
		@Display(name = "neatAndOrganize3", addInfoOnly = true, label = "Is Neat and Organized", width = 30, hideLabel = true),
		@Display(name = "neatAndOrganize4", addInfoOnly = true, label = "Is Neat and Organized", width = 30, hideLabel = true),
		@Display(name = "neatAndOrganizeFinal", enabled = false, addInfoOnly = true, label = "Is Neat and Organized", width = 30, hideLabel = true),
		@Display(name = "de", addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30),
		@Display(name = "de2", addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30, hideLabel = true),
		@Display(name = "de3", addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30, hideLabel = true),
		@Display(name = "de4", addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30, hideLabel = true),
		@Display(name = "deFinal", enabled = false, addInfoOnly = true, label = "Demonstrates Concern for Environment", width = 30, hideLabel = true),
		@Display(name = "wp", addInfoOnly = true, label = "Works and Plays Well with Others", width = 30),
		@Display(name = "wp2", addInfoOnly = true, label = "Works and Plays Well with Others", width = 30, hideLabel = true),
		@Display(name = "wp3", addInfoOnly = true, label = "Works and Plays Well with Others", width = 30, hideLabel = true),
		@Display(name = "wp4", addInfoOnly = true, label = "Works and Plays Well with Others", width = 30, hideLabel = true),
		@Display(name = "wpFinal", enabled = false, addInfoOnly = true, label = "Works and Plays Well with Others", width = 30, hideLabel = true),
		@Display(name = "hs", addInfoOnly = true, label = "Handles Varied Situations", width = 30),
		@Display(name = "hs2", addInfoOnly = true, label = "Handles Varied Situations", width = 30, hideLabel = true),
		@Display(name = "hs3", addInfoOnly = true, label = "Handles Varied Situations", width = 30, hideLabel = true),
		@Display(name = "hs4", addInfoOnly = true, label = "Handles Varied Situations", width = 30, hideLabel = true),
		@Display(name = "hsFinal", enabled = false, addInfoOnly = true, label = "Handles Varied Situations", width = 30, hideLabel = true),
		@Display(name = "ir", addInfoOnly = true, label = "Is Respectful to Others", width = 30),
		@Display(name = "ir2", addInfoOnly = true, label = "Is Respectful to Others", width = 30, hideLabel = true),
		@Display(name = "ir3", addInfoOnly = true, label = "Is Respectful to Others", width = 30, hideLabel = true),
		@Display(name = "ir4", addInfoOnly = true, label = "Is Respectful to Others", width = 30, hideLabel = true),
		@Display(name = "irFinal", enabled = false, addInfoOnly = true, label = "Is Respectful to Others", width = 30, hideLabel = true),
		@Display(name = "ls", addInfoOnly = true, label = "Listens While others Speak", width = 30),
		@Display(name = "ls2", addInfoOnly = true, label = "Listens While others Speak", width = 30, hideLabel = true),
		@Display(name = "ls3", addInfoOnly = true, label = "Listens While others Speak", width = 30, hideLabel = true),
		@Display(name = "ls4", addInfoOnly = true, label = "Listens While others Speak", width = 30, hideLabel = true),
		@Display(name = "lsFinal", enabled = false, addInfoOnly = true, label = "Listens While others Speak", width = 30, hideLabel = true),

		// Psychomotor Development
		@Display(name = "ap", addInfoOnly = true, label = "Actively Participates at Play", width = 30),
		@Display(name = "ap2", addInfoOnly = true, label = "Actively Participates at Play", width = 30, hideLabel = true),
		@Display(name = "ap3", addInfoOnly = true, label = "Actively Participates at Play", width = 30, hideLabel = true),
		@Display(name = "ap4", addInfoOnly = true, label = "Actively Participates at Play", width = 30, hideLabel = true),
		@Display(name = "apFinal", enabled = false, addInfoOnly = true, label = "Actively Participates at Play", width = 30, hideLabel = true),
		@Display(name = "ce", addInfoOnly = true, label = "Colors Evenly", width = 30),
		@Display(name = "ce2", addInfoOnly = true, label = "Colors Evenly", width = 30, hideLabel = true),
		@Display(name = "ce3", addInfoOnly = true, label = "Colors Evenly", width = 30, hideLabel = true),
		@Display(name = "ce4", addInfoOnly = true, label = "Colors Evenly", width = 30, hideLabel = true),
		@Display(name = "ceFinal", enabled = false, addInfoOnly = true, label = "Colors Evenly", width = 30, hideLabel = true),
		@Display(name = "tc", addInfoOnly = true, label = "Traces Clearly", width = 30),
		@Display(name = "tc2", addInfoOnly = true, label = "Traces Clearly", width = 30, hideLabel = true),
		@Display(name = "tc3", addInfoOnly = true, label = "Traces Clearly", width = 30, hideLabel = true),
		@Display(name = "tc4", addInfoOnly = true, label = "Traces Clearly", width = 30, hideLabel = true),
		@Display(name = "tcFinal", enabled = false, addInfoOnly = true, label = "Traces Clearly", width = 30, hideLabel = true),
                @Display(name = "df", addInfoOnly = true, label = "Draws Recognizable Figures", width = 30),
		@Display(name = "df2", addInfoOnly = true, label = "Draws Recognizable Figures", width = 30, hideLabel = true),
		@Display(name = "df3", addInfoOnly = true, label = "Draws Recognizable Figures", width = 30, hideLabel = true),
		@Display(name = "df4", addInfoOnly = true, label = "Draws Recognizable Figures", width = 30, hideLabel = true),
		@Display(name = "dfFinal", enabled = false, addInfoOnly = true, label = "Draws Recognizable Figures", width = 30, hideLabel = true),
                @Display(name = "wlr", addInfoOnly = true, label = "Writes Following Left to Right Progression", width = 30),
		@Display(name = "wlr2", addInfoOnly = true, label = "Writes Following Left to Right Progression", width = 30, hideLabel = true),
		@Display(name = "wlr3", addInfoOnly = true, label = "Writes Following Left to Right Progression", width = 30, hideLabel = true),
		@Display(name = "wlr4", addInfoOnly = true, label = "Writes Following Left to Right Progression", width = 30, hideLabel = true),
		@Display(name = "wlrFinal", enabled = false, addInfoOnly = true, label = "Writes Following Left to Right Progression", width = 30, hideLabel = true),
                @Display(name = "ocs", addInfoOnly = true, label = "Observes Correct Spacing", width = 30),
		@Display(name = "ocs2", addInfoOnly = true, label = "Observes Correct Spacing", width = 30, hideLabel = true),
		@Display(name = "ocs3", addInfoOnly = true, label = "Observes Correct Spacing", width = 30, hideLabel = true),
		@Display(name = "ocs4", addInfoOnly = true, label = "Observes Correct Spacing", width = 30, hideLabel = true),
		@Display(name = "ocsFinal", enabled = false, addInfoOnly = true, label = "Observes Correct Spacing", width = 30, hideLabel = true),
                @Display(name = "cwp", addInfoOnly = true, label = "Copies with Precission", width = 30),
		@Display(name = "cwp2", addInfoOnly = true, label = "Copies with Precission", width = 30, hideLabel = true),
		@Display(name = "cwp3", addInfoOnly = true, label = "Copies with Precission", width = 30, hideLabel = true),
		@Display(name = "cwp4", addInfoOnly = true, label = "Copies with Precission", width = 30, hideLabel = true),
		@Display(name = "cwpFinal", enabled = false, addInfoOnly = true, label = "Copies with Precission", width = 30, hideLabel = true),
                @Display(name = "cp", addInfoOnly = true, label = "Cuts Precisely", width = 30),
		@Display(name = "cp2", addInfoOnly = true, label = "Cuts Precisely", width = 30, hideLabel = true),
		@Display(name = "cp3", addInfoOnly = true, label = "Cuts Precisely", width = 30, hideLabel = true),
		@Display(name = "cp4", addInfoOnly = true, label = "Cuts Precisely", width = 30, hideLabel = true),
		@Display(name = "cpFinal", enabled = false, addInfoOnly = true, label = "Cuts Precisely", width = 30, hideLabel = true),
		@Display(name = "pa", addInfoOnly = true, label = "Pastes Accurately", width = 30),
		@Display(name = "pa2", addInfoOnly = true, label = "Pastes Accurately", width = 30, hideLabel = true),
		@Display(name = "pa3", addInfoOnly = true, label = "Pastes Accurately", width = 30, hideLabel = true),
		@Display(name = "pa4", addInfoOnly = true, label = "Pastes Accurately", width = 30, hideLabel = true),
		@Display(name = "paFinal", enabled = false, addInfoOnly = true, label = "Pastes Accurately", width = 30, hideLabel = true),
                
		@Display(name = "cfp", addInfoOnly = true, label = "Can Follow Patterns", width = 30),
		@Display(name = "cfp2", addInfoOnly = true, label = "Can Follow Patterns", width = 30, hideLabel = true),
		@Display(name = "cfp3", addInfoOnly = true, label = "Can Follow Patterns", width = 30, hideLabel = true),
		@Display(name = "cfp4", addInfoOnly = true, label = "Can Follow Patterns", width = 30, hideLabel = true),
		@Display(name = "cfpFinal", enabled = false, addInfoOnly = true, label = "Can Follow Patterns", width = 30, hideLabel = true),
		@Display(name = "cst", addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30),
		@Display(name = "cst2", addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30, hideLabel = true),
		@Display(name = "cst3", addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30, hideLabel = true),
		@Display(name = "cst4", addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30, hideLabel = true),
		@Display(name = "cstFinal", enabled = false, addInfoOnly = true, label = "Is Concious of Stroke Terminals", width = 30, hideLabel = true),
		
		
		

		// Mega Skill
		@Display(name = "con", addInfoOnly = true, leftLabel = "Confidence", labelTop = true, label = "1st", width = 30),
		@Display(name = "con2", addInfoOnly = true, labelTop = true, label = "2nd", width = 30),
		@Display(name = "con3", addInfoOnly = true, labelTop = true, label = "3rd", width = 30),
		@Display(name = "con4", addInfoOnly = true, labelTop = true, label = "4th", width = 30),
		@Display(name = "conFinal", enabled = false, addInfoOnly = true, labelTop = true, label = "Final", width = 30),
		@Display(name = "mot", addInfoOnly = true, label = "Motivation", width = 30),
		@Display(name = "mot2", addInfoOnly = true, label = "Motivation", width = 30, hideLabel = true),
		@Display(name = "mot3", addInfoOnly = true, label = "Motivation", width = 30, hideLabel = true),
		@Display(name = "mot4", addInfoOnly = true, label = "Motivation", width = 30, hideLabel = true),
		@Display(name = "motFinal", enabled = false, addInfoOnly = true, label = "Motivation", width = 30, hideLabel = true),
		@Display(name = "eff", addInfoOnly = true, label = "Effort", width = 30),
		@Display(name = "eff2", addInfoOnly = true, label = "Effort", width = 30, hideLabel = true),
		@Display(name = "eff3", addInfoOnly = true, label = "Effort", width = 30, hideLabel = true),
		@Display(name = "eff4", addInfoOnly = true, label = "Effort", width = 30, hideLabel = true),
		@Display(name = "effFinal", enabled = false, addInfoOnly = true, label = "Effort", width = 30, hideLabel = true),
		@Display(name = "res", addInfoOnly = true, label = "Responsiblity", width = 30),
		@Display(name = "res2", addInfoOnly = true, label = "Responsiblity", width = 30, hideLabel = true),
		@Display(name = "res3", addInfoOnly = true, label = "Responsiblity", width = 30, hideLabel = true),
		@Display(name = "res4", addInfoOnly = true, label = "Responsiblity", width = 30, hideLabel = true),
		@Display(name = "resFinal", enabled = false, addInfoOnly = true, label = "Responsiblity", width = 30, hideLabel = true),
		@Display(name = "ini", addInfoOnly = true, label = "Initiative", width = 30),
		@Display(name = "ini2", addInfoOnly = true, label = "Initiative", width = 30, hideLabel = true),
		@Display(name = "ini3", addInfoOnly = true, label = "Initiative", width = 30, hideLabel = true),
		@Display(name = "ini4", addInfoOnly = true, label = "Initiative", width = 30, hideLabel = true),
		@Display(name = "iniFinal", enabled = false, addInfoOnly = true, label = "Initiative", width = 30, hideLabel = true),
		@Display(name = "per", addInfoOnly = true, label = "Perseverance", width = 30),
		@Display(name = "per2", addInfoOnly = true, label = "Perseverance", width = 30, hideLabel = true),
		@Display(name = "per3", addInfoOnly = true, label = "Perseverance", width = 30, hideLabel = true),
		@Display(name = "per4", addInfoOnly = true, label = "Perseverance", width = 30, hideLabel = true),
		@Display(name = "perFinal", enabled = false, addInfoOnly = true, label = "Perseverance", width = 30, hideLabel = true),
		@Display(name = "car", addInfoOnly = true, label = "Caring", width = 30),
		@Display(name = "car2", addInfoOnly = true, label = "Caring", width = 30, hideLabel = true),
		@Display(name = "car3", addInfoOnly = true, label = "Caring", width = 30, hideLabel = true),
		@Display(name = "car4", addInfoOnly = true, label = "Caring", width = 30, hideLabel = true),
		@Display(name = "carFinal", enabled = false, addInfoOnly = true, label = "Caring", width = 30, hideLabel = true),
		@Display(name = "tea", addInfoOnly = true, label = "Teamwork", width = 30),
		@Display(name = "tea2", addInfoOnly = true, label = "Teamwork", width = 30, hideLabel = true),
		@Display(name = "tea3", addInfoOnly = true, label = "Teamwork", width = 30, hideLabel = true),
		@Display(name = "tea4", addInfoOnly = true, label = "Teamwork", width = 30, hideLabel = true),
		@Display(name = "teaFinal", enabled = false, addInfoOnly = true, label = "Teamwork", width = 30, hideLabel = true),
		@Display(name = "com", addInfoOnly = true, label = "Common Sense", width = 30),
		@Display(name = "com2", addInfoOnly = true, label = "Common Sense", width = 30, hideLabel = true),
		@Display(name = "com3", addInfoOnly = true, label = "Common Sense", width = 30, hideLabel = true),
		@Display(name = "com4", addInfoOnly = true, label = "Common Sense", width = 30, hideLabel = true),
		@Display(name = "comFinal", enabled = false, addInfoOnly = true, label = "Common Sense", width = 30, hideLabel = true),
		@Display(name = "pro", addInfoOnly = true, label = "Problem Solving", width = 30),
		@Display(name = "pro2", addInfoOnly = true, label = "Problem Solving", width = 30, hideLabel = true),
		@Display(name = "pro3", addInfoOnly = true, label = "Problem Solving", width = 30, hideLabel = true),
		@Display(name = "pro4", addInfoOnly = true, label = "Problem Solving", width = 30, hideLabel = true),
		@Display(name = "proFinal", enabled = false, addInfoOnly = true, label = "Problem Solving", width = 30, hideLabel = true),
		@Display(name = "focus", addInfoOnly = true, label = "Focus", width = 30),
		@Display(name = "focus2", addInfoOnly = true, label = "Focus", width = 30, hideLabel = true),
		@Display(name = "focus3", addInfoOnly = true, label = "Focus", width = 30, hideLabel = true),
		@Display(name = "focus4", addInfoOnly = true, label = "Focus", width = 30, hideLabel = true),
		@Display(name = "focusFinal", enabled = false, addInfoOnly = true, label = "Focus", width = 30, hideLabel = true),
		@Display(name = "respect", addInfoOnly = true, label = "Respect", width = 30),
		@Display(name = "respect2", addInfoOnly = true, label = "Respect", width = 30, hideLabel = true),
		@Display(name = "respect3", addInfoOnly = true, label = "Respect", width = 30, hideLabel = true),
		@Display(name = "respect4", addInfoOnly = true, label = "Respect", width = 30, hideLabel = true),
		@Display(name = "respectFinal", enabled = false, addInfoOnly = true, label = "Respect", width = 30, hideLabel = true),
		@Display(name = "clubsAndParticipation", addInfoOnly = true, label = "Clubs", width = 30),
		@Display(name = "clubsAndParticipation2", addInfoOnly = true, label = "Clubs", width = 30, hideLabel = true),
		@Display(name = "clubsAndParticipation3", addInfoOnly = true, label = "Clubs", width = 30, hideLabel = true),
		@Display(name = "clubsAndParticipation4", addInfoOnly = true, label = "Clubs", width = 30, hideLabel = true),
		@Display(name = "clubsAndParticipationFinal", enabled = false, addInfoOnly = true, label = "Clubs", width = 30, hideLabel = true),
		@Display(name = "scouting", addInfoOnly = true, label = "Scouting", width = 30),
		@Display(name = "scouting2", addInfoOnly = true, label = "Scouting", width = 30, hideLabel = true),
		@Display(name = "scouting3", addInfoOnly = true, label = "Scouting", width = 30, hideLabel = true),
		@Display(name = "scouting4", addInfoOnly = true, label = "Scouting", width = 30, hideLabel = true),
		@Display(name = "scoutingFinal", enabled = false, addInfoOnly = true, label = "Scouting", width = 30, hideLabel = true),

		// Values promoted
		@Display(name = "els", addInfoOnly = true, leftLabel = "Effective Leadership Skills", labelTop = true, label = "1st", width = 30),
		@Display(name = "els2", addInfoOnly = true, labelTop = true, label = "2nd", width = 30),
		@Display(name = "els3", addInfoOnly = true, labelTop = true, label = "3rd", width = 30),
		@Display(name = "els4", addInfoOnly = true, labelTop = true, label = "4th", width = 30),
		@Display(name = "elsFinal", enabled = false, addInfoOnly = true, labelTop = true, label = "Final", width = 30),
		@Display(name = "wfr", addInfoOnly = true, label = "Wholesome Followership Role", width = 30),
		@Display(name = "wfr2", addInfoOnly = true, label = "Wholesome Followership Role", width = 30, hideLabel = true),
		@Display(name = "wfr3", addInfoOnly = true, label = "Wholesome Followership Role", width = 30, hideLabel = true),
		@Display(name = "wfr4", addInfoOnly = true, label = "Wholesome Followership Role", width = 30, hideLabel = true),
		@Display(name = "wfrFinal", enabled = false, addInfoOnly = true, label = "Wholesome Followership Role", width = 30, hideLabel = true),
		@Display(name = "apgw", addInfoOnly = true, label = "Active Participation at Group Work", width = 30),
		@Display(name = "apgw2", addInfoOnly = true, label = "Active Participation at Group Work", width = 30, hideLabel = true),
		@Display(name = "apgw3", addInfoOnly = true, label = "Active Participation at Group Work", width = 30, hideLabel = true),
		@Display(name = "apgw4", addInfoOnly = true, label = "Active Participation at Group Work", width = 30, hideLabel = true),
		@Display(name = "apgwFinal", enabled = false, addInfoOnly = true, label = "Active Participation at Group Work", width = 30, hideLabel = true),
		@Display(name = "spaa", addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30),
		@Display(name = "spaa2", addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30, hideLabel = true),
		@Display(name = "spaa3", addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30, hideLabel = true),
		@Display(name = "spaa4", addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30, hideLabel = true),
		@Display(name = "spaaFinal", enabled = false, addInfoOnly = true, label = "Knows and recites Simple Prayers", width = 30, hideLabel = true),
		@Display(name = "iva", addInfoOnly = true, label = "Integration of Values Acquired", width = 30),
		@Display(name = "iva2", addInfoOnly = true, label = "Integration of Values Acquired", width = 30, hideLabel = true),
		@Display(name = "iva3", addInfoOnly = true, label = "Integration of Values Acquired", width = 30, hideLabel = true),
		@Display(name = "iva4", addInfoOnly = true, label = "Integration of Values Acquired", width = 30, hideLabel = true),
		@Display(name = "ivaFinal", enabled = false, addInfoOnly = true, label = "Integration of Values Acquired", width = 30, hideLabel = true),
		@Display(name = "isl", addInfoOnly = true, label = "Integration of Skills Learned", width = 30),
		@Display(name = "isl2", addInfoOnly = true, label = "Integration of Skills Learned", width = 30, hideLabel = true),
		@Display(name = "isl3", addInfoOnly = true, label = "Integration of Skills Learned", width = 30, hideLabel = true),
		@Display(name = "isl4", addInfoOnly = true, label = "Integration of Skills Learned", width = 30, hideLabel = true),
		@Display(name = "islFinal", enabled = false, addInfoOnly = true, label = "Integration of Skills Learned", width = 30, hideLabel = true),
		@Display(name = "aspvi", addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30),
		@Display(name = "aspvi2", addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30, hideLabel = true),
		@Display(name = "aspvi3", addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30, hideLabel = true),
		@Display(name = "aspvi4", addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30, hideLabel = true),
		@Display(name = "aspviFinal", enabled = false, addInfoOnly = true, label = "Active Sharing of Personal Views and Ideas", width = 30, hideLabel = true),
		@Display(name = "hspd", addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30),
		@Display(name = "hspd2", addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30, hideLabel = true),
		@Display(name = "hspd3", addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30, hideLabel = true),
		@Display(name = "hspd4", addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30, hideLabel = true),
		@Display(name = "hspdFinal", enabled = false, addInfoOnly = true, label = "High Standards of Personal Discipline", width = 30, hideLabel = true),
		@Display(name = "sd", addInfoOnly = true, label = "Self-directedness", width = 30),
		@Display(name = "sd2", addInfoOnly = true, label = "Self-directedness", width = 30, hideLabel = true),
		@Display(name = "sd3", addInfoOnly = true, label = "Self-directedness", width = 30, hideLabel = true),
		@Display(name = "sd4", addInfoOnly = true, label = "Self-directedness", width = 30, hideLabel = true),
		@Display(name = "sdFinal", enabled = false, addInfoOnly = true, label = "Self-directedness", width = 30, hideLabel = true),
		@Display(name = "put", addInfoOnly = true, label = "Productive Use of Time", width = 30),
		@Display(name = "put2", addInfoOnly = true, label = "Productive Use of Time", width = 30, hideLabel = true),
		@Display(name = "put3", addInfoOnly = true, label = "Productive Use of Time", width = 30, hideLabel = true),
		@Display(name = "put4", addInfoOnly = true, label = "Productive Use of Time", width = 30, hideLabel = true),
		@Display(name = "putFinal", enabled = false, addInfoOnly = true, label = "Productive Use of Time", width = 30, hideLabel = true),
		@Display(name = "hlew", addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30),
		@Display(name = "hlew2", addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30, hideLabel = true),
		@Display(name = "hlew3", addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30, hideLabel = true),
		@Display(name = "hlew4", addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30, hideLabel = true),
		@Display(name = "hlewFinal", enabled = false, addInfoOnly = true, label = "Highest Level of Excellence at Work", width = 30, hideLabel = true),
		@Display(name = "prs", addInfoOnly = true, label = "Practical Recycling Skills", width = 30),
		@Display(name = "prs2", addInfoOnly = true, label = "Practical Recycling Skills", width = 30, hideLabel = true),
		@Display(name = "prs3", addInfoOnly = true, label = "Practical Recycling Skills", width = 30, hideLabel = true),
		@Display(name = "prs4", addInfoOnly = true, label = "Practical Recycling Skills", width = 30, hideLabel = true),
		@Display(name = "prsFinal", enabled = false, addInfoOnly = true, label = "Practical Recycling Skills", width = 30, hideLabel = true),
		@Display(name = "cr", addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30),
		@Display(name = "cr2", addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30, hideLabel = true),
		@Display(name = "cr3", addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30, hideLabel = true),
		@Display(name = "cr4", addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30, hideLabel = true),
		@Display(name = "crFinal", enabled = false, addInfoOnly = true, label = "Creativity and Resourcefullness", width = 30, hideLabel = true),
		@Display(name = "cws", addInfoOnly = true, label = "Completion of Work Started", width = 30),
		@Display(name = "cws2", addInfoOnly = true, label = "Completion of Work Started", width = 30, hideLabel = true),
		@Display(name = "cws3", addInfoOnly = true, label = "Completion of Work Started", width = 30, hideLabel = true),
		@Display(name = "cws4", addInfoOnly = true, label = "Completion of Work Started", width = 30, hideLabel = true),
		@Display(name = "cwsFinal", enabled = false, addInfoOnly = true, label = "Completion of Work Started", width = 30, hideLabel = true),

		// ZL2 components
		@Display(name = "pfe", addInfoOnly = true, leftLabel = "Program for Exceptionality                       ", labelTop = true, label = "1st", width = 30),
		@Display(name = "pfe2", addInfoOnly = true, labelTop = true, label = "2nd", width = 30),
		@Display(name = "pfe3", addInfoOnly = true, labelTop = true, label = "3rd", width = 30),
		@Display(name = "pfe4", addInfoOnly = true, labelTop = true, label = "4th", width = 30),
		@Display(name = "pfeFinal", enabled = false, addInfoOnly = true, labelTop = true, label = "Final", width = 30),
		@Display(name = "cra", addInfoOnly = true, label = "Career-related Activities", width = 30),
		@Display(name = "cra2", addInfoOnly = true, label = "Career-related Activities", width = 30, hideLabel = true),
		@Display(name = "cra3", addInfoOnly = true, label = "Career-related Activities", width = 30, hideLabel = true),
		@Display(name = "cra4", addInfoOnly = true, label = "Career-related Activities", width = 30, hideLabel = true),
		@Display(name = "craFinal", enabled = false, addInfoOnly = true, label = "Career-related Activities", width = 30, hideLabel = true),
		@Display(name = "ca", addInfoOnly = true, label = "Creative Activities", width = 30),
		@Display(name = "ca2", addInfoOnly = true, label = "Creative Activities", width = 30, hideLabel = true),
		@Display(name = "ca3", addInfoOnly = true, label = "Creative Activities", width = 30, hideLabel = true),
		@Display(name = "ca4", addInfoOnly = true, label = "Creative Activities", width = 30, hideLabel = true),
		@Display(name = "caFinal", enabled = false, addInfoOnly = true, label = "Creative Activities", width = 30, hideLabel = true),
		@Display(name = "kin", addInfoOnly = true, label = "Kinesthetic", width = 30),
		@Display(name = "kin2", addInfoOnly = true, label = "Kinesthetic", width = 30, hideLabel = true),
		@Display(name = "kin3", addInfoOnly = true, label = "Kinesthetic", width = 30, hideLabel = true),
		@Display(name = "kin4", addInfoOnly = true, label = "Kinesthetic", width = 30, hideLabel = true),
		@Display(name = "kinFinal", enabled = false, addInfoOnly = true, label = "Kinesthetic", width = 30, hideLabel = true),
		@Display(name = "ec1", addInfoOnly = true, label = "Extra Curricular 1", width = 30),
		@Display(name = "ec12", addInfoOnly = true, label = "Extra Curricular 1", width = 30, hideLabel = true),
		@Display(name = "ec13", addInfoOnly = true, label = "Extra Curricular 1", width = 30, hideLabel = true),
		@Display(name = "ec14", addInfoOnly = true, label = "Extra Curricular 1", width = 30, hideLabel = true),
		@Display(name = "ec1Final", enabled = false, addInfoOnly = true, label = "Extra Curricular 1", width = 30, hideLabel = true),
		@Display(name = "ec2", addInfoOnly = true, label = "Extra Curricular 2", width = 30),
		@Display(name = "ec22", addInfoOnly = true, label = "Extra Curricular 2", width = 30, hideLabel = true),
		@Display(name = "ec23", addInfoOnly = true, label = "Extra Curricular 2", width = 30, hideLabel = true),
		@Display(name = "ec24", addInfoOnly = true, label = "Extra Curricular 2", width = 30, hideLabel = true),
		@Display(name = "ec2Final", enabled = false, addInfoOnly = true, label = "Extra Curricular 2", width = 30, hideLabel = true),

		@Display(name = "jan", width = 30),
		@Display(name = "feb", width = 30),
		@Display(name = "mar", width = 30),
		@Display(name = "apr", width = 30),
		@Display(name = "may", width = 30),
		@Display(name = "jun", width = 30),
		@Display(name = "jul", width = 30),
		@Display(name = "aug", width = 30),
		@Display(name = "sep", width = 30),
		@Display(name = "oct", width = 30),
		@Display(name = "nov", width = 30),
		@Display(name = "dece", width = 30)
})
@ActionButtons({
    @ActionButton(name = "btnRankQ1", label = "Rank 1st Qtr."),
    @ActionButton(name = "btnRankQ2", label = "2nd"),
    @ActionButton(name = "btnRankQ3", label = "3rd"),
    @ActionButton(name = "btnRankQ4", label = "4th")
})
@Reports({
    @template.Report(reportFile = "Q1_ValuesGrades", reportTitle = "Values 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_ValuesGrades", reportTitle = "2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_ValuesGrades", reportTitle = "3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_ValuesGrades", reportTitle = "4", reportSql = "${section}"),
    @template.Report(reportFile = "Q1_NarrativeGrades", reportTitle = "Narr. 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_NarrativeGrades", reportTitle = "2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_NarrativeGrades", reportTitle = "3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_NarrativeGrades", reportTitle = "4", reportSql = "${section}"),
    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summ. 1st Qtr.", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_Grades", reportTitle = "2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_Grades", reportTitle = "3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_Grades", reportTitle = "4", reportSql = "${section}"),
    @template.Report(reportFile = "Q1_GradesAll", reportTitle = "Admin 1st", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_GradesAll", reportTitle = "2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_GradesAll", reportTitle = "3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_GradesAll", reportTitle = "4", reportSql = "${section}")
})
public class StudentValuesGrading extends AbstractIBean implements Serializable {

	@Override
	public int getRecordListCount() {
		return 100;
	}
	
	@Override
	public void delete() {
		LockGrading s = LockGrading.extractGrading(schoolYear);
		if (!s.isQ1Locked()) {
			boolean b = PanelUtil.showPrompt(null, "Are you sure you want to delete the selected student?");
			if (b) {
				b = PanelUtil.showPrompt(null, "This student already have grade for 1st quarter, do you want to continue?");
				if (b) {
					super.delete();
				}
			}
		}
		else {
			PanelUtil.showError(null, "You can only delete component on first quarter.");
		}
	}

	public StudentValuesGrading() {
		schoolYear = AppConfig.getSchoolYear();
		facultyId = UserInfo.getPersonId();
	}

	public static void main(String[] args) {
		view(StudentValuesGrading.class);
	}

	@Id
	@Column(name = "seq", nullable = false)
	public Integer seq;
	@Column(name = "studentId", nullable = false)
	public int studentId;
	@Column(name = "student")
	public String student;
	@Column(name = "gradeLevel")
	public String gradeLevel;
	@Column(name = "section")
	public String section;
	@Column(name = "enrollmentId")
	public int enrollmentId;

	@Column(name = "studentNumber")
	public String studentNumber;
	@Column(name = "status")
	public String status;

	@Column(name = "comment1")
	public String comment1;
	@Column(name = "comment2")
	public String comment2;
	@Column(name = "comment3")
	public String comment3;
	@Column(name = "comment4")
	public String comment4;

	@Column(name = "deportment1")
	public String deportment1;
	@Column(name = "deportment2")
	public String deportment2;
	@Column(name = "deportment3")
	public String deportment3;
	@Column(name = "deportment4")
	public String deportment4;
	@Column(name = "deportmentFinal")
	public String deportmentFinal;

	@Column(name = "clubsAndParticipation")
	public int clubsAndParticipation;
	@Column(name = "clubsAndParticipation2")
	public int clubsAndParticipation2;
	@Column(name = "clubsAndParticipation3")
	public int clubsAndParticipation3;
	@Column(name = "clubsAndParticipation4")
	public int clubsAndParticipation4;
	@Column(name = "clubsAndParticipationFinal")
	public int clubsAndParticipationFinal;

	@Column(name = "scouting")
	public int scouting;
	@Column(name = "scouting2")
	public int scouting2;
	@Column(name = "scouting3")
	public int scouting3;
	@Column(name = "scouting4")
	public int scouting4;
	@Column(name = "scoutingFinal")
	public int scoutingFinal;

	@Column(name = "totalDays")
	public int totalDays;
	@Column(name = "present")
	public int present;
	@Column(name = "absent")
	public int absent;
	@Column(name = "tardy")
	public int tardy;
	@Column(name = "totalDays2")
	public int totalDays2;
	@Column(name = "present2")
	public int present2;
	@Column(name = "absent2")
	public int absent2;
	@Column(name = "tardy2")
	public int tardy2;
	@Column(name = "totalDays3")
	public int totalDays3;
	@Column(name = "present3")
	public int present3;
	@Column(name = "absent3")
	public int absent3;
	@Column(name = "tardy3")
	public int tardy3;
	@Column(name = "totalDays4")
	public int totalDays4;
	@Column(name = "present4")
	public int present4;
	@Column(name = "absent4")
	public int absent4;
	@Column(name = "tardy4")
	public int tardy4;

	// affective development - preschool
	@Column(name = "kp")
	public int kp;
	@Column(name = "kp2")
	public int kp2;
	@Column(name = "kp3")
	public int kp3;
	@Column(name = "kp4")
	public int kp4;
	@Column(name = "kpFinal")
	public int kpFinal;
	@Column(name = "fh")
	public int fh;
	@Column(name = "fh2")
	public int fh2;
	@Column(name = "fh3")
	public int fh3;
	@Column(name = "fh4")
	public int fh4;
	@Column(name = "fhFinal")
	public int fhFinal;
	@Column(name = "fr")
	public int fr;
	@Column(name = "fr2")
	public int fr2;
	@Column(name = "fr3")
	public int fr3;
	@Column(name = "fr4")
	public int fr4;
	@Column(name = "frFinal")
	public int frFinal;
	@Column(name = "aw")
	public int aw;
	@Column(name = "aw2")
	public int aw2;
	@Column(name = "aw3")
	public int aw3;
	@Column(name = "aw4")
	public int aw4;
	@Column(name = "awFinal")
	public int awFinal;
	@Column(name = "ft")
	public int ft;
	@Column(name = "ft2")
	public int ft2;
	@Column(name = "ft3")
	public int ft3;
	@Column(name = "ft4")
	public int ft4;
	@Column(name = "ftFinal")
	public int ftFinal;
	@Column(name = "ic")
	public int ic;
	@Column(name = "ic2")
	public int ic2;
	@Column(name = "ic3")
	public int ic3;
	@Column(name = "ic4")
	public int ic4;
	@Column(name = "icFinal")
	public int icFinal;
	@Column(name = "ih")
	public int ih;
	@Column(name = "ih2")
	public int ih2;
	@Column(name = "ih3")
	public int ih3;
	@Column(name = "ih4")
	public int ih4;
	@Column(name = "ihFinal")
	public int ihFinal;
	@Column(name = "wi")
	public int wi;
	@Column(name = "wi2")
	public int wi2;
	@Column(name = "wi3")
	public int wi3;
	@Column(name = "wi4")
	public int wi4;
	@Column(name = "wiFinal")
	public int wiFinal;
	@Column(name = "id")
	public int id;
	@Column(name = "id2")
	public int id2;
	@Column(name = "id3")
	public int id3;
	@Column(name = "id4")
	public int id4;
	@Column(name = "idFinal")
	public int idFinal;
	@Column(name = "neatAndOrganize")
	public int neatAndOrganize;
	@Column(name = "neatAndOrganize2")
	public int neatAndOrganize2;
	@Column(name = "neatAndOrganize3")
	public int neatAndOrganize3;
	@Column(name = "neatAndOrganize4")
	public int neatAndOrganize4;
	@Column(name = "neatAndOrganizeFinal")
	public int neatAndOrganizeFinal;
	@Column(name = "de")
	public int de;
	@Column(name = "de2")
	public int de2;
	@Column(name = "de3")
	public int de3;
	@Column(name = "de4")
	public int de4;
	@Column(name = "deFinal")
	public int deFinal;
	@Column(name = "wp")
	public int wp;
	@Column(name = "wp2")
	public int wp2;
	@Column(name = "wp3")
	public int wp3;
	@Column(name = "wp4")
	public int wp4;
	@Column(name = "wpFinal")
	public int wpFinal;
	@Column(name = "hs")
	public int hs;
	@Column(name = "hs2")
	public int hs2;
	@Column(name = "hs3")
	public int hs3;
	@Column(name = "hs4")
	public int hs4;
	@Column(name = "hsFinal")
	public int hsFinal;
	@Column(name = "ir")
	public int ir;
	@Column(name = "ir2")
	public int ir2;
	@Column(name = "ir3")
	public int ir3;
	@Column(name = "ir4")
	public int ir4;
	@Column(name = "irFinal")
	public int irFinal;
	@Column(name = "ls")
	public int ls;
	@Column(name = "ls2")
	public int ls2;
	@Column(name = "ls3")
	public int ls3;
	@Column(name = "ls4")
	public int ls4;
	@Column(name = "lsFinal")
	public int lsFinal;
	// psychomotor development - preschool
	@Column(name = "ap")
	public int ap;
	@Column(name = "ap2")
	public int ap2;
	@Column(name = "ap3")
	public int ap3;
	@Column(name = "ap4")
	public int ap4;
	@Column(name = "apFinal")
	public int apFinal;
	@Column(name = "ce")
	public int ce;
	@Column(name = "ce2")
	public int ce2;
	@Column(name = "ce3")
	public int ce3;
	@Column(name = "ce4")
	public int ce4;
	@Column(name = "ceFinal")
	public int ceFinal;
	@Column(name = "tc")
	public int tc;
	@Column(name = "tc2")
	public int tc2;
	@Column(name = "tc3")
	public int tc3;
	@Column(name = "tc4")
	public int tc4;
	@Column(name = "tcFinal")
	public int tcFinal;
	@Column(name = "cfp")
	public int cfp;
	@Column(name = "cfp2")
	public int cfp2;
	@Column(name = "cfp3")
	public int cfp3;
	@Column(name = "cfp4")
	public int cfp4;
	@Column(name = "cfpFinal")
	public int cfpFinal;
	@Column(name = "df")
	public int df;
	@Column(name = "df2")
	public int df2;
	@Column(name = "df3")
	public int df3;
	@Column(name = "df4")
	public int df4;
	@Column(name = "dfFinal")
	public int dfFinal;
	@Column(name = "wlr")
	public int wlr;
	@Column(name = "wlr2")
	public int wlr2;
	@Column(name = "wlr3")
	public int wlr3;
	@Column(name = "wlr4")
	public int wlr4;
	@Column(name = "wlrFinal")
	public int wlrFinal;
	@Column(name = "cst")
	public int cst;
	@Column(name = "cst2")
	public int cst2;
	@Column(name = "cst3")
	public int cst3;
	@Column(name = "cst4")
	public int cst4;
	@Column(name = "cstFinal")
	public int cstFinal;
	@Column(name = "ocs")
	public int ocs;
	@Column(name = "ocs2")
	public int ocs2;
	@Column(name = "ocs3")
	public int ocs3;
	@Column(name = "ocs4")
	public int ocs4;
	@Column(name = "ocsFinal")
	public int ocsFinal;
	@Column(name = "cwp")
	public int cwp;
	@Column(name = "cwp2")
	public int cwp2;
	@Column(name = "cwp3")
	public int cwp3;
	@Column(name = "cwp4")
	public int cwp4;
	@Column(name = "cwpFinal")
	public int cwpFinal;
	@Column(name = "cp")
	public int cp;
	@Column(name = "cp2")
	public int cp2;
	@Column(name = "cp3")
	public int cp3;
	@Column(name = "cp4")
	public int cp4;
	@Column(name = "cpFinal")
	public int cpFinal;
	@Column(name = "pa")
	public int pa;
	@Column(name = "pa2")
	public int pa2;
	@Column(name = "pa3")
	public int pa3;
	@Column(name = "pa4")
	public int pa4;
	@Column(name = "paFinal")
	public int paFinal;
	// megaskills - grade 1-4
	@Column(name = "con")
	public int con;
	@Column(name = "con2")
	public int con2;
	@Column(name = "con3")
	public int con3;
	@Column(name = "con4")
	public int con4;
	@Column(name = "conFinal")
	public int conFinal;
	@Column(name = "mot")
	public int mot;
	@Column(name = "mot2")
	public int mot2;
	@Column(name = "mot3")
	public int mot3;
	@Column(name = "mot4")
	public int mot4;
	@Column(name = "motFinal")
	public int motFinal;
	@Column(name = "eff")
	public int eff;
	@Column(name = "eff2")
	public int eff2;
	@Column(name = "eff3")
	public int eff3;
	@Column(name = "eff4")
	public int eff4;
	@Column(name = "effFinal")
	public int effFinal;
	@Column(name = "res")
	public int res;
	@Column(name = "res2")
	public int res2;
	@Column(name = "res3")
	public int res3;
	@Column(name = "res4")
	public int res4;
	@Column(name = "resFinal")
	public int resFinal;
	@Column(name = "ini")
	public int ini;
	@Column(name = "ini2")
	public int ini2;
	@Column(name = "ini3")
	public int ini3;
	@Column(name = "ini4")
	public int ini4;
	@Column(name = "iniFinal")
	public int iniFinal;
	@Column(name = "per")
	public int per;
	@Column(name = "per2")
	public int per2;
	@Column(name = "per3")
	public int per3;
	@Column(name = "per4")
	public int per4;
	@Column(name = "perFinal")
	public int perFinal;
	@Column(name = "car")
	public int car;
	@Column(name = "car2")
	public int car2;
	@Column(name = "car3")
	public int car3;
	@Column(name = "car4")
	public int car4;
	@Column(name = "carFinal")
	public int carFinal;
	@Column(name = "tea")
	public int tea;
	@Column(name = "tea2")
	public int tea2;
	@Column(name = "tea3")
	public int tea3;
	@Column(name = "tea4")
	public int tea4;
	@Column(name = "teaFinal")
	public int teaFinal;
	@Column(name = "com")
	public int com;
	@Column(name = "com2")
	public int com2;
	@Column(name = "com3")
	public int com3;
	@Column(name = "com4")
	public int com4;
	@Column(name = "comFinal")
	public int comFinal;
	@Column(name = "pro")
	public int pro;
	@Column(name = "pro2")
	public int pro2;
	@Column(name = "pro3")
	public int pro3;
	@Column(name = "pro4")
	public int pro4;
	@Column(name = "proFinal")
	public int proFinal;
	@Column(name = "focus")
	public int focus;
	@Column(name = "focus2")
	public int focus2;
	@Column(name = "focus3")
	public int focus3;
	@Column(name = "focus4")
	public int focus4;
	@Column(name = "focusFinal")
	public int focusFinal;
	@Column(name = "respect")
	public int respect;
	@Column(name = "respect2")
	public int respect2;
	@Column(name = "respect3")
	public int respect3;
	@Column(name = "respect4")
	public int respect4;
	@Column(name = "respectFinal")
	public int respectFinal;
	// values promoted - grade 5-6/highschool
	@Column(name = "els")
	public int els;
	@Column(name = "els2")
	public int els2;
	@Column(name = "els3")
	public int els3;
	@Column(name = "els4")
	public int els4;
	@Column(name = "elsFinal")
	public int elsFinal;
	@Column(name = "wfr")
	public int wfr;
	@Column(name = "wfr2")
	public int wfr2;
	@Column(name = "wfr3")
	public int wfr3;
	@Column(name = "wfr4")
	public int wfr4;
	@Column(name = "wfrFinal")
	public int wfrFinal;
	@Column(name = "apgw")
	public int apgw;
	@Column(name = "apgw2")
	public int apgw2;
	@Column(name = "apgw3")
	public int apgw3;
	@Column(name = "apgw4")
	public int apgw4;
	@Column(name = "apgwFinal")
	public int apgwFinal;
	@Column(name = "spaa")
	public int spaa;
	@Column(name = "spaa2")
	public int spaa2;
	@Column(name = "spaa3")
	public int spaa3;
	@Column(name = "spaa4")
	public int spaa4;
	@Column(name = "spaaFinal")
	public int spaaFinal;
	@Column(name = "iva")
	public int iva;
	@Column(name = "iva2")
	public int iva2;
	@Column(name = "iva3")
	public int iva3;
	@Column(name = "iva4")
	public int iva4;
	@Column(name = "ivaFinal")
	public int ivaFinal;
	@Column(name = "isl")
	public int isl;
	@Column(name = "isl2")
	public int isl2;
	@Column(name = "isl3")
	public int isl3;
	@Column(name = "isl4")
	public int isl4;
	@Column(name = "islFinal")
	public int islFinal;
	@Column(name = "aspvi")
	public int aspvi;
	@Column(name = "aspvi2")
	public int aspvi2;
	@Column(name = "aspvi3")
	public int aspvi3;
	@Column(name = "aspvi4")
	public int aspvi4;
	@Column(name = "aspviFinal")
	public int aspviFinal;
	@Column(name = "hspd")
	public int hspd;
	@Column(name = "hspd2")
	public int hspd2;
	@Column(name = "hspd3")
	public int hspd3;
	@Column(name = "hspd4")
	public int hspd4;
	@Column(name = "hspdFinal")
	public int hspdFinal;
	@Column(name = "sd")
	public int sd;
	@Column(name = "sd2")
	public int sd2;
	@Column(name = "sd3")
	public int sd3;
	@Column(name = "sd4")
	public int sd4;
	@Column(name = "sdFinal")
	public int sdFinal;
	@Column(name = "put")
	public int put;
	@Column(name = "put2")
	public int put2;
	@Column(name = "put3")
	public int put3;
	@Column(name = "put4")
	public int put4;
	@Column(name = "putFinal")
	public int putFinal;
	@Column(name = "hlew")
	public int hlew;
	@Column(name = "hlew2")
	public int hlew2;
	@Column(name = "hlew3")
	public int hlew3;
	@Column(name = "hlew4")
	public int hlew4;
	@Column(name = "hlewFinal")
	public int hlewFinal;
	@Column(name = "prs")
	public int prs;
	@Column(name = "prs2")
	public int prs2;
	@Column(name = "prs3")
	public int prs3;
	@Column(name = "prs4")
	public int prs4;
	@Column(name = "prsFinal")
	public int prsFinal;
	@Column(name = "cr")
	public int cr;
	@Column(name = "cr2")
	public int cr2;
	@Column(name = "cr3")
	public int cr3;
	@Column(name = "cr4")
	public int cr4;
	@Column(name = "crFinal")
	public int crFinal;
	@Column(name = "cws")
	public int cws;
	@Column(name = "cws2")
	public int cws2;
	@Column(name = "cws3")
	public int cws3;
	@Column(name = "cws4")
	public int cws4;
	@Column(name = "cwsFinal")
	public int cwsFinal;
	// zl2 components - grade 5-6/highschool
	@Column(name = "pfe")
	public int pfe;
	@Column(name = "pfe2")
	public int pfe2;
	@Column(name = "pfe3")
	public int pfe3;
	@Column(name = "pfe4")
	public int pfe4;
	@Column(name = "pfeFinal")
	public int pfeFinal;
	@Column(name = "cra")
	public int cra;
	@Column(name = "cra2")
	public int cra2;
	@Column(name = "cra3")
	public int cra3;
	@Column(name = "cra4")
	public int cra4;
	@Column(name = "craFinal")
	public int craFinal;
	@Column(name = "ca")
	public int ca;
	@Column(name = "ca2")
	public int ca2;
	@Column(name = "ca3")
	public int ca3;
	@Column(name = "ca4")
	public int ca4;
	@Column(name = "caFinal")
	public int caFinal;
	@Column(name = "kin")
	public int kin;
	@Column(name = "kin2")
	public int kin2;
	@Column(name = "kin3")
	public int kin3;
	@Column(name = "kin4")
	public int kin4;
	@Column(name = "kinFinal")
	public int kinFinal;
	@Column(name = "ec1")
	public int ec1;
	@Column(name = "ec12")
	public int ec12;
	@Column(name = "ec13")
	public int ec13;
	@Column(name = "ec14")
	public int ec14;
	@Column(name = "ec1Final")
	public int ec1Final;
	@Column(name = "ec2")
	public int ec2;
	@Column(name = "ec22")
	public int ec22;
	@Column(name = "ec23")
	public int ec23;
	@Column(name = "ec24")
	public int ec24;
	@Column(name = "ec2Final")
	public int ec2Final;
	@Column(name = "schoolYear")
	public String schoolYear;
	@Column(name = "faculty")
	public String faculty;
	@Column(name = "facultyId")
	public int facultyId;

	@Column(name = "jan")
	public int jan;
	@Column(name = "feb")
	public int feb;
	@Column(name = "mar")
	public int mar;
	@Column(name = "apr")
	public int apr;
	@Column(name = "may")
	public int may;
	@Column(name = "jun")
	public int jun;
	@Column(name = "jul")
	public int jul;
	@Column(name = "aug")
	public int aug;
	@Column(name = "sep")
	public int sep;
	@Column(name = "oct")
	public int oct;
	@Column(name = "nov")
	public int nov;
	@Column(name = "dece")
	public int dece;

	@Column(name = "janAbsent")
	public int janAbsent;
	@Column(name = "febAbsent")
	public int febAbsent;
	@Column(name = "marAbsent")
	public int marAbsent;
	@Column(name = "aprAbsent")
	public int aprAbsent;
	@Column(name = "mayAbsent")
	public int mayAbsent;
	@Column(name = "junAbsent")
	public int junAbsent;
	@Column(name = "julAbsent")
	public int julAbsent;
	@Column(name = "augAbsent")
	public int augAbsent;
	@Column(name = "sepAbsent")
	public int sepAbsent;
	@Column(name = "octAbsent")
	public int octAbsent;
	@Column(name = "novAbsent")
	public int novAbsent;
	@Column(name = "deceAbsent")
	public int deceAbsent;

	@Column(name = "janTardy")
	public int janTardy;
	@Column(name = "febTardy")
	public int febTardy;
	@Column(name = "marTardy")
	public int marTardy;
	@Column(name = "aprTardy")
	public int aprTardy;
	@Column(name = "mayTardy")
	public int mayTardy;
	@Column(name = "junTardy")
	public int junTardy;
	@Column(name = "julTardy")
	public int julTardy;
	@Column(name = "augTardy")
	public int augTardy;
	@Column(name = "sepTardy")
	public int sepTardy;
	@Column(name = "octTardy")
	public int octTardy;
	@Column(name = "novTardy")
	public int novTardy;
	@Column(name = "deceTardy")
	public int deceTardy;

	@Column(name = "affectiveAverage1")
	public double affectiveAverage1;
	@Column(name = "psychomotorAverage1")
	public double psychomotorAverage1;
	@Column(name = "megaskillAverage1")
	public double megaskillAverage1;
	@Column(name = "valuesPromotedAverage1")
	public double valuesPromotedAverage1;
	@Column(name = "zl2Average1")
	public double zl2Average1;

	@Column(name = "affectiveAverage2")
	public double affectiveAverage2;
	@Column(name = "psychomotorAverage2")
	public double psychomotorAverage2;
	@Column(name = "megaskillAverage2")
	public double megaskillAverage2;
	@Column(name = "valuesPromotedAverage2")
	public double valuesPromotedAverage2;
	@Column(name = "zl2Average2")
	public double zl2Average2;

	@Column(name = "affectiveAverage3")
	public double affectiveAverage3;
	@Column(name = "psychomotorAverage3")
	public double psychomotorAverage3;
	@Column(name = "megaskillAverage3")
	public double megaskillAverage3;
	@Column(name = "valuesPromotedAverage3")
	public double valuesPromotedAverage3;
	@Column(name = "zl2Average3")
	public double zl2Average3;

	@Column(name = "affectiveAverage4")
	public double affectiveAverage4;
	@Column(name = "psychomotorAverage4")
	public double psychomotorAverage4;
	@Column(name = "megaskillAverage4")
	public double megaskillAverage4;
	@Column(name = "valuesPromotedAverage4")
	public double valuesPromotedAverage4;
	@Column(name = "zl2Average4")
	public double zl2Average4;

	public double getAffectiveAverage1() {
		return affectiveAverage1;
	}

	public void setAffectiveAverage1(double affectiveAverage1) {
		this.affectiveAverage1 = affectiveAverage1;
	}

	public double getPsychomotorAverage1() {
		return psychomotorAverage1;
	}

	public void setPsychomotorAverage1(double psychomotorAverage1) {
		this.psychomotorAverage1 = psychomotorAverage1;
	}

	public double getMegaskillAverage1() {
		return megaskillAverage1;
	}

	public void setMegaskillAverage1(double megaskillAverage1) {
		this.megaskillAverage1 = megaskillAverage1;
	}

	public double getValuesPromotedAverage1() {
		return valuesPromotedAverage1;
	}

	public void setValuesPromotedAverage1(double valuesPromotedAverage1) {
		this.valuesPromotedAverage1 = valuesPromotedAverage1;
	}

	public double getZl2Average1() {
		return zl2Average1;
	}

	public void setZl2Average1(double zl2Average1) {
		this.zl2Average1 = zl2Average1;
	}

	public double getAffectiveAverage2() {
		return affectiveAverage2;
	}

	public void setAffectiveAverage2(double affectiveAverage2) {
		this.affectiveAverage2 = affectiveAverage2;
	}

	public double getPsychomotorAverage2() {
		return psychomotorAverage2;
	}

	public void setPsychomotorAverage2(double psychomotorAverage2) {
		this.psychomotorAverage2 = psychomotorAverage2;
	}

	public double getMegaskillAverage2() {
		return megaskillAverage2;
	}

	public void setMegaskillAverage2(double megaskillAverage2) {
		this.megaskillAverage2 = megaskillAverage2;
	}

	public double getValuesPromotedAverage2() {
		return valuesPromotedAverage2;
	}

	public void setValuesPromotedAverage2(double valuesPromotedAverage2) {
		this.valuesPromotedAverage2 = valuesPromotedAverage2;
	}

	public double getZl2Average2() {
		return zl2Average2;
	}

	public void setZl2Average2(double zl2Average2) {
		this.zl2Average2 = zl2Average2;
	}

	public double getAffectiveAverage3() {
		return affectiveAverage3;
	}

	public void setAffectiveAverage3(double affectiveAverage3) {
		this.affectiveAverage3 = affectiveAverage3;
	}

	public double getPsychomotorAverage3() {
		return psychomotorAverage3;
	}

	public void setPsychomotorAverage3(double psychomotorAverage3) {
		this.psychomotorAverage3 = psychomotorAverage3;
	}

	public double getMegaskillAverage3() {
		return megaskillAverage3;
	}

	public void setMegaskillAverage3(double megaskillAverage3) {
		this.megaskillAverage3 = megaskillAverage3;
	}

	public double getValuesPromotedAverage3() {
		return valuesPromotedAverage3;
	}

	public void setValuesPromotedAverage3(double valuesPromotedAverage3) {
		this.valuesPromotedAverage3 = valuesPromotedAverage3;
	}

	public double getZl2Average3() {
		return zl2Average3;
	}

	public void setZl2Average3(double zl2Average3) {
		this.zl2Average3 = zl2Average3;
	}

	public double getAffectiveAverage4() {
		return affectiveAverage4;
	}

	public void setAffectiveAverage4(double affectiveAverage4) {
		this.affectiveAverage4 = affectiveAverage4;
	}

	public double getPsychomotorAverage4() {
		return psychomotorAverage4;
	}

	public void setPsychomotorAverage4(double psychomotorAverage4) {
		this.psychomotorAverage4 = psychomotorAverage4;
	}

	public double getMegaskillAverage4() {
		return megaskillAverage4;
	}

	public void setMegaskillAverage4(double megaskillAverage4) {
		this.megaskillAverage4 = megaskillAverage4;
	}

	public double getValuesPromotedAverage4() {
		return valuesPromotedAverage4;
	}

	public void setValuesPromotedAverage4(double valuesPromotedAverage4) {
		this.valuesPromotedAverage4 = valuesPromotedAverage4;
	}

	public double getZl2Average4() {
		return zl2Average4;
	}

	public void setZl2Average4(double zl2Average4) {
		this.zl2Average4 = zl2Average4;
	}

	public int getJanAbsent() {
		return janAbsent;
	}

	public void setJanAbsent(int janAbsent) {
		this.janAbsent = janAbsent;
	}

	public int getFebAbsent() {
		return febAbsent;
	}

	public void setFebAbsent(int febAbsent) {
		this.febAbsent = febAbsent;
	}

	public int getMarAbsent() {
		return marAbsent;
	}

	public void setMarAbsent(int marAbsent) {
		this.marAbsent = marAbsent;
	}

	public int getAprAbsent() {
		return aprAbsent;
	}

	public void setAprAbsent(int aprAbsent) {
		this.aprAbsent = aprAbsent;
	}

	public int getMayAbsent() {
		return mayAbsent;
	}

	public void setMayAbsent(int mayAbsent) {
		this.mayAbsent = mayAbsent;
	}

	public int getJunAbsent() {
		return junAbsent;
	}

	public void setJunAbsent(int junAbsent) {
		this.junAbsent = junAbsent;
	}

	public int getJulAbsent() {
		return julAbsent;
	}

	public void setJulAbsent(int julAbsent) {
		this.julAbsent = julAbsent;
	}

	public int getAugAbsent() {
		return augAbsent;
	}

	public void setAugAbsent(int augAbsent) {
		this.augAbsent = augAbsent;
	}

	public int getSepAbsent() {
		return sepAbsent;
	}

	public void setSepAbsent(int sepAbsent) {
		this.sepAbsent = sepAbsent;
	}

	public int getOctAbsent() {
		return octAbsent;
	}

	public void setOctAbsent(int octAbsent) {
		this.octAbsent = octAbsent;
	}

	public int getNovAbsent() {
		return novAbsent;
	}

	public void setNovAbsent(int novAbsent) {
		this.novAbsent = novAbsent;
	}

	public int getDeceAbsent() {
		return deceAbsent;
	}

	public void setDeceAbsent(int deceAbsent) {
		this.deceAbsent = deceAbsent;
	}

	public String getDeportmentFinal() {
		return deportmentFinal;
	}

	public void setDeportmentFinal(String deportmentFinal) {
		this.deportmentFinal = deportmentFinal;
	}

	public String getDeportment1() {
		return deportment1;
	}

	public void setDeportment1(String deportment1) {
		this.deportment1 = deportment1;
	}

	public String getDeportment2() {
		return deportment2;
	}

	public void setDeportment2(String deportment2) {
		this.deportment2 = deportment2;
	}

	public String getDeportment3() {
		return deportment3;
	}

	public void setDeportment3(String deportment3) {
		this.deportment3 = deportment3;
	}

	public String getDeportment4() {
		return deportment4;
	}

	public void setDeportment4(String deportment4) {
		this.deportment4 = deportment4;
	}

	public int getJanTardy() {
		return janTardy;
	}

	public void setJanTardy(int janTardy) {
		this.janTardy = janTardy;
	}

	public int getFebTardy() {
		return febTardy;
	}

	public void setFebTardy(int febTardy) {
		this.febTardy = febTardy;
	}

	public int getMarTardy() {
		return marTardy;
	}

	public void setMarTardy(int marTardy) {
		this.marTardy = marTardy;
	}

	public int getAprTardy() {
		return aprTardy;
	}

	public void setAprTardy(int aprTardy) {
		this.aprTardy = aprTardy;
	}

	public int getMayTardy() {
		return mayTardy;
	}

	public void setMayTardy(int mayTardy) {
		this.mayTardy = mayTardy;
	}

	public int getJunTardy() {
		return junTardy;
	}

	public void setJunTardy(int junTardy) {
		this.junTardy = junTardy;
	}

	public int getJulTardy() {
		return julTardy;
	}

	public void setJulTardy(int julTardy) {
		this.julTardy = julTardy;
	}

	public int getAugTardy() {
		return augTardy;
	}

	public void setAugTardy(int augTardy) {
		this.augTardy = augTardy;
	}

	public int getSepTardy() {
		return sepTardy;
	}

	public void setSepTardy(int sepTardy) {
		this.sepTardy = sepTardy;
	}

	public int getOctTardy() {
		return octTardy;
	}

	public void setOctTardy(int octTardy) {
		this.octTardy = octTardy;
	}

	public int getNovTardy() {
		return novTardy;
	}

	public void setNovTardy(int novTardy) {
		this.novTardy = novTardy;
	}

	public int getDeceTardy() {
		return deceTardy;
	}

	public void setDeceTardy(int deceTardy) {
		this.deceTardy = deceTardy;
	}

	public int getJan() {
		return jan;
	}

	public void setJan(int jan) {
		this.jan = jan;
	}

	public int getFeb() {
		return feb;
	}

	public void setFeb(int feb) {
		this.feb = feb;
	}

	public int getMar() {
		return mar;
	}

	public void setMar(int mar) {
		this.mar = mar;
	}

	public int getApr() {
		return apr;
	}

	public void setApr(int apr) {
		this.apr = apr;
	}

	public int getMay() {
		return may;
	}

	public void setMay(int may) {
		this.may = may;
	}

	public int getJun() {
		return jun;
	}

	public void setJun(int jun) {
		this.jun = jun;
	}

	public int getJul() {
		return jul;
	}

	public void setJul(int jul) {
		this.jul = jul;
	}

	public int getAug() {
		return aug;
	}

	public void setAug(int aug) {
		this.aug = aug;
	}

	public int getSep() {
		return sep;
	}

	public void setSep(int sep) {
		this.sep = sep;
	}

	public int getOct() {
		return oct;
	}

	public void setOct(int oct) {
		this.oct = oct;
	}

	public int getNov() {
		return nov;
	}

	public void setNov(int nov) {
		this.nov = nov;
	}

	public int getDece() {
		return dece;
	}

	public void setDece(int dec) {
		this.dece = dec;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public int getClubsAndParticipation2() {
		return clubsAndParticipation2;
	}

	public void setClubsAndParticipation2(int clubsAndParticipation2) {
		this.clubsAndParticipation2 = clubsAndParticipation2;
	}

	public int getClubsAndParticipation3() {
		return clubsAndParticipation3;
	}

	public void setClubsAndParticipation3(int clubsAndParticipation3) {
		this.clubsAndParticipation3 = clubsAndParticipation3;
	}

	public int getClubsAndParticipation4() {
		return clubsAndParticipation4;
	}

	public void setClubsAndParticipation4(int clubsAndParticipation4) {
		this.clubsAndParticipation4 = clubsAndParticipation4;
	}

	public int getClubsAndParticipationFinal() {
		return clubsAndParticipationFinal;
	}

	public void setClubsAndParticipationFinal(int clubsAndParticipationFinal) {
		this.clubsAndParticipationFinal = clubsAndParticipationFinal;
	}

	public int getFocus() {
		return focus;
	}

	public void setFocus(int focus) {
		this.focus = focus;
	}

	public int getFocus2() {
		return focus2;
	}

	public void setFocus2(int focus2) {
		this.focus2 = focus2;
	}

	public int getFocus3() {
		return focus3;
	}

	public void setFocus3(int focus3) {
		this.focus3 = focus3;
	}

	public int getFocus4() {
		return focus4;
	}

	public void setFocus4(int focus4) {
		this.focus4 = focus4;
	}

	public int getFocusFinal() {
		return focusFinal;
	}

	public void setFocusFinal(int focusFinal) {
		this.focusFinal = focusFinal;
	}

	public int getPresent() {
		return present;
	}

	public void setPresent(int present) {
		this.present = present;
	}

	public int getRespect() {
		return respect;
	}

	public void setRespect(int respect) {
		this.respect = respect;
	}

	public int getRespect2() {
		return respect2;
	}

	public void setRespect2(int respect2) {
		this.respect2 = respect2;
	}

	public int getRespect3() {
		return respect3;
	}

	public void setRespect3(int respect3) {
		this.respect3 = respect3;
	}

	public int getRespect4() {
		return respect4;
	}

	public void setRespect4(int respect4) {
		this.respect4 = respect4;
	}

	public int getRespectFinal() {
		return respectFinal;
	}

	public void setRespectFinal(int respectFinal) {
		this.respectFinal = respectFinal;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getTotalDays2() {
		return totalDays2;
	}

	public void setTotalDays2(int totalDays2) {
		this.totalDays2 = totalDays2;
	}

	public int getPresent2() {
		return present2;
	}

	public void setPresent2(int present2) {
		this.present2 = present2;
	}

	public int getAbsent2() {
		return absent2;
	}

	public void setAbsent2(int absent2) {
		this.absent2 = absent2;
	}

	public int getTotalDays3() {
		return totalDays3;
	}

	public void setTotalDays3(int totalDays3) {
		this.totalDays3 = totalDays3;
	}

	public int getPresent3() {
		return present3;
	}

	public void setPresent3(int present3) {
		this.present3 = present3;
	}

	public int getAbsent3() {
		return absent3;
	}

	public void setAbsent3(int absent3) {
		this.absent3 = absent3;
	}

	public int getTotalDays4() {
		return totalDays4;
	}

	public void setTotalDays4(int totalDays4) {
		this.totalDays4 = totalDays4;
	}

	public int getPresent4() {
		return present4;
	}

	public void setPresent4(int present4) {
		this.present4 = present4;
	}

	public int getAbsent4() {
		return absent4;
	}

	public void setAbsent4(int absent4) {
		this.absent4 = absent4;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public String getComment3() {
		return comment3;
	}

	public void setComment3(String comment3) {
		this.comment3 = comment3;
	}

	public String getComment4() {
		return comment4;
	}

	public void setComment4(String comment4) {
		this.comment4 = comment4;
	}

	@Override
	public void save() {
		if (student == null) {
			Student stud = (Student) extractPerson(studentId);
			student = stud.toString();
			stud.addStatus = status;
			stud.save();
			if (section == null || section.trim().isEmpty()) {
				section = stud.section;
				gradeLevel = stud.gradeLevel;
			}
		}
		if (faculty == null) {
			Person f = (Person) extractPerson(facultyId);
			if (f != null)
				faculty = f.toString();
		}
		putDays();
		if (tardy==0) setTardy(0);
		if (tardy2==0) setTardy2(0);
		if (tardy3==0) setTardy3(0);
		if (tardy4==0) setTardy4(0);
		present = totalDays-absent;
		present2 = totalDays2-absent2;
		present3 = totalDays3-absent3;
		present4 = totalDays4-absent4;
		
		affectiveAverage1 = extractAverage("", "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
		affectiveAverage2 = extractAverage("2", "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
		affectiveAverage3 = extractAverage("3", "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
		affectiveAverage4 = extractAverage("4", "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
		
		psychomotorAverage1 = extractAverage("", "ap", "ce", "tc", "cfp", "df", "wlr", "cst", "ocs", "cwp", "cp", "pa");
		psychomotorAverage2 = extractAverage("2", "ap", "ce", "tc", "cfp", "df", "wlr", "cst", "ocs", "cwp", "cp", "pa");
		psychomotorAverage3 = extractAverage("3", "ap", "ce", "tc", "cfp", "df", "wlr", "cst", "ocs", "cwp", "cp", "pa");
		psychomotorAverage4 = extractAverage("4", "ap", "ce", "tc", "cfp", "df", "wlr", "cst", "ocs", "cwp", "cp", "pa");

//		megaskillAverage1 = extractAverage("", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "clubsAndParticipation", "focus", "respect");
//		megaskillAverage2 = extractAverage("2", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "clubsAndParticipation", "focus", "respect");
//		megaskillAverage3 = extractAverage("3", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "clubsAndParticipation", "focus", "respect");
//		megaskillAverage4 = extractAverage("4", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "clubsAndParticipation", "focus", "respect");

		megaskillAverage1 = extractAverage("", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "focus", "respect");
		megaskillAverage2 = extractAverage("2", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "focus", "respect");
		megaskillAverage3 = extractAverage("3", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "focus", "respect");
		megaskillAverage4 = extractAverage("4", "con", "mot", "eff", "res", "ini", "per", "car", "tea", "com", "pro", "focus", "respect");

		valuesPromotedAverage1 = extractAverage("", "els", "wfr", "apgw", "spaa", "iva", "isl", "aspvi", "hspd", "sd", "put", "hlew", "prs", "cr", "cws");
		valuesPromotedAverage2 = extractAverage("2", "els", "wfr", "apgw", "spaa", "iva", "isl", "aspvi", "hspd", "sd", "put", "hlew", "prs", "cr", "cws");
		valuesPromotedAverage3 = extractAverage("3", "els", "wfr", "apgw", "spaa", "iva", "isl", "aspvi", "hspd", "sd", "put", "hlew", "prs", "cr", "cws");
		valuesPromotedAverage4 = extractAverage("4", "els", "wfr", "apgw", "spaa", "iva", "isl", "aspvi", "hspd", "sd", "put", "hlew", "prs", "cr", "cws");

		zl2Average1 = extractAverage("", "pfe", "cra", "ca", "kin", "ec1", "ec2");
		zl2Average2 = extractAverage("2", "pfe", "cra", "ca", "kin", "ec1", "ec2");
		zl2Average3 = extractAverage("3", "pfe", "cra", "ca", "kin", "ec1", "ec2");
		zl2Average4 = extractAverage("4", "pfe", "cra", "ca", "kin", "ec1", "ec2");
		System.out.println("SAVE RECORD "+seq);
		super.save();
	}

	private int extractAverage(String quarter, String...str) {
		double totalScore = 0;
		double totalCount = 0;
		for (String s:str) {
			double val = BeanUtil.getDoubleValue(this, s+quarter);
			if (val==0) {
				continue;
			}
			totalScore += val;
			totalCount++;
		}
		return (int) ((totalScore/totalCount)+.5);
	}
	
	public int getAp2() {
		return ap2;
	}

	public void setAp2(int ap2) {
		this.ap2 = ap2;
	}

	public int getAp3() {
		return ap3;
	}

	public void setAp3(int ap3) {
		this.ap3 = ap3;
	}

	public int getAp4() {
		return ap4;
	}

	public void setAp4(int ap4) {
		this.ap4 = ap4;
	}

	public int getApFinal() {
		return apFinal;
	}

	public void setApFinal(int apFinal) {
		this.apFinal = apFinal;
	}

	public int getApgw2() {
		return apgw2;
	}

	public void setApgw2(int apgw2) {
		this.apgw2 = apgw2;
	}

	public int getApgw3() {
		return apgw3;
	}

	public void setApgw3(int apgw3) {
		this.apgw3 = apgw3;
	}

	public int getApgw4() {
		return apgw4;
	}

	public void setApgw4(int apgw4) {
		this.apgw4 = apgw4;
	}

	public int getApgwFinal() {
		return apgwFinal;
	}

	public void setApgwFinal(int apgwFinal) {
		this.apgwFinal = apgwFinal;
	}

	public int getAspvi2() {
		return aspvi2;
	}

	public void setAspvi2(int aspvi2) {
		this.aspvi2 = aspvi2;
	}

	public int getAspvi3() {
		return aspvi3;
	}

	public void setAspvi3(int aspvi3) {
		this.aspvi3 = aspvi3;
	}

	public int getAspvi4() {
		return aspvi4;
	}

	public void setAspvi4(int aspvi4) {
		this.aspvi4 = aspvi4;
	}

	public int getAspviFinal() {
		return aspviFinal;
	}

	public void setAspviFinal(int aspviFinal) {
		this.aspviFinal = aspviFinal;
	}

	public int getAw2() {
		return aw2;
	}

	public void setAw2(int aw2) {
		this.aw2 = aw2;
	}

	public int getAw3() {
		return aw3;
	}

	public void setAw3(int aw3) {
		this.aw3 = aw3;
	}

	public int getAw4() {
		return aw4;
	}

	public void setAw4(int aw4) {
		this.aw4 = aw4;
	}

	public int getAwFinal() {
		return awFinal;
	}

	public void setAwFinal(int awFinal) {
		this.awFinal = awFinal;
	}

	public int getCa2() {
		return ca2;
	}

	public void setCa2(int ca2) {
		this.ca2 = ca2;
	}

	public int getCa3() {
		return ca3;
	}

	public void setCa3(int ca3) {
		this.ca3 = ca3;
	}

	public int getCa4() {
		return ca4;
	}

	public void setCa4(int ca4) {
		this.ca4 = ca4;
	}

	public int getCaFinal() {
		return caFinal;
	}

	public void setCaFinal(int caFinal) {
		this.caFinal = caFinal;
	}

	public int getCar2() {
		return car2;
	}

	public void setCar2(int car2) {
		this.car2 = car2;
	}

	public int getCar3() {
		return car3;
	}

	public void setCar3(int car3) {
		this.car3 = car3;
	}

	public int getCar4() {
		return car4;
	}

	public void setCar4(int car4) {
		this.car4 = car4;
	}

	public int getCarFinal() {
		return carFinal;
	}

	public void setCarFinal(int carFinal) {
		this.carFinal = carFinal;
	}

	public int getCe2() {
		return ce2;
	}

	public void setCe2(int ce2) {
		this.ce2 = ce2;
	}

	public int getCe3() {
		return ce3;
	}

	public void setCe3(int ce3) {
		this.ce3 = ce3;
	}

	public int getCe4() {
		return ce4;
	}

	public void setCe4(int ce4) {
		this.ce4 = ce4;
	}

	public int getCeFinal() {
		return ceFinal;
	}

	public void setCeFinal(int ceFinal) {
		this.ceFinal = ceFinal;
	}

	public int getCfp2() {
		return cfp2;
	}

	public void setCfp2(int cfp2) {
		this.cfp2 = cfp2;
	}

	public int getCfp3() {
		return cfp3;
	}

	public void setCfp3(int cfp3) {
		this.cfp3 = cfp3;
	}

	public int getCfp4() {
		return cfp4;
	}

	public void setCfp4(int cfp4) {
		this.cfp4 = cfp4;
	}

	public int getCfpFinal() {
		return cfpFinal;
	}

	public void setCfpFinal(int cfpFinal) {
		this.cfpFinal = cfpFinal;
	}

	public int getCom2() {
		return com2;
	}

	public void setCom2(int com2) {
		this.com2 = com2;
	}

	public int getCom3() {
		return com3;
	}

	public void setCom3(int com3) {
		this.com3 = com3;
	}

	public int getCom4() {
		return com4;
	}

	public void setCom4(int com4) {
		this.com4 = com4;
	}

	public int getComFinal() {
		return comFinal;
	}

	public void setComFinal(int comFinal) {
		this.comFinal = comFinal;
	}

	public int getCon2() {
		return con2;
	}

	public void setCon2(int con2) {
		this.con2 = con2;
	}

	public int getCon3() {
		return con3;
	}

	public void setCon3(int con3) {
		this.con3 = con3;
	}

	public int getCon4() {
		return con4;
	}

	public void setCon4(int con4) {
		this.con4 = con4;
	}

	public int getConFinal() {
		return conFinal;
	}

	public void setConFinal(int conFinal) {
		this.conFinal = conFinal;
	}

	public int getCp2() {
		return cp2;
	}

	public void setCp2(int cp2) {
		this.cp2 = cp2;
	}

	public int getCp3() {
		return cp3;
	}

	public void setCp3(int cp3) {
		this.cp3 = cp3;
	}

	public int getCp4() {
		return cp4;
	}

	public void setCp4(int cp4) {
		this.cp4 = cp4;
	}

	public int getCpFinal() {
		return cpFinal;
	}

	public void setCpFinal(int cpFinal) {
		this.cpFinal = cpFinal;
	}

	public int getCr2() {
		return cr2;
	}

	public void setCr2(int cr2) {
		this.cr2 = cr2;
	}

	public int getCr3() {
		return cr3;
	}

	public void setCr3(int cr3) {
		this.cr3 = cr3;
	}

	public int getCr4() {
		return cr4;
	}

	public void setCr4(int cr4) {
		this.cr4 = cr4;
	}

	public int getCrFinal() {
		return crFinal;
	}

	public void setCrFinal(int crFinal) {
		this.crFinal = crFinal;
	}

	public int getCra2() {
		return cra2;
	}

	public void setCra2(int cra2) {
		this.cra2 = cra2;
	}

	public int getCra3() {
		return cra3;
	}

	public void setCra3(int cra3) {
		this.cra3 = cra3;
	}

	public int getCra4() {
		return cra4;
	}

	public void setCra4(int cra4) {
		this.cra4 = cra4;
	}

	public int getCraFinal() {
		return craFinal;
	}

	public void setCraFinal(int craFinal) {
		this.craFinal = craFinal;
	}

	public int getCst2() {
		return cst2;
	}

	public void setCst2(int cst2) {
		this.cst2 = cst2;
	}

	public int getCst3() {
		return cst3;
	}

	public void setCst3(int cst3) {
		this.cst3 = cst3;
	}

	public int getCst4() {
		return cst4;
	}

	public void setCst4(int cst4) {
		this.cst4 = cst4;
	}

	public int getCstFinal() {
		return cstFinal;
	}

	public void setCstFinal(int cstFinal) {
		this.cstFinal = cstFinal;
	}

	public int getCwp2() {
		return cwp2;
	}

	public void setCwp2(int cwp2) {
		this.cwp2 = cwp2;
	}

	public int getCwp3() {
		return cwp3;
	}

	public void setCwp3(int cwp3) {
		this.cwp3 = cwp3;
	}

	public int getCwp4() {
		return cwp4;
	}

	public void setCwp4(int cwp4) {
		this.cwp4 = cwp4;
	}

	public int getCwpFinal() {
		return cwpFinal;
	}

	public void setCwpFinal(int cwpFinal) {
		this.cwpFinal = cwpFinal;
	}

	public int getCws2() {
		return cws2;
	}

	public void setCws2(int cws2) {
		this.cws2 = cws2;
	}

	public int getCws3() {
		return cws3;
	}

	public void setCws3(int cws3) {
		this.cws3 = cws3;
	}

	public int getCws4() {
		return cws4;
	}

	public void setCws4(int cws4) {
		this.cws4 = cws4;
	}

	public int getCwsFinal() {
		return cwsFinal;
	}

	public void setCwsFinal(int cwsFinal) {
		this.cwsFinal = cwsFinal;
	}

	public int getDe2() {
		return de2;
	}

	public void setDe2(int de2) {
		this.de2 = de2;
	}

	public int getDe3() {
		return de3;
	}

	public void setDe3(int de3) {
		this.de3 = de3;
	}

	public int getDe4() {
		return de4;
	}

	public void setDe4(int de4) {
		this.de4 = de4;
	}

	public int getDeFinal() {
		return deFinal;
	}

	public void setDeFinal(int deFinal) {
		this.deFinal = deFinal;
	}

	public int getDf2() {
		return df2;
	}

	public void setDf2(int df2) {
		this.df2 = df2;
	}

	public int getDf3() {
		return df3;
	}

	public void setDf3(int df3) {
		this.df3 = df3;
	}

	public int getDf4() {
		return df4;
	}

	public void setDf4(int df4) {
		this.df4 = df4;
	}

	public int getDfFinal() {
		return dfFinal;
	}

	public void setDfFinal(int dfFinal) {
		this.dfFinal = dfFinal;
	}

	public int getEc12() {
		return ec12;
	}

	public void setEc12(int ec12) {
		this.ec12 = ec12;
	}

	public int getEc13() {
		return ec13;
	}

	public void setEc13(int ec13) {
		this.ec13 = ec13;
	}

	public int getEc1Final() {
		return ec1Final;
	}

	public void setEc1Final(int ec1Final) {
		this.ec1Final = ec1Final;
	}

	public int getEc22() {
		return ec22;
	}

	public void setEc22(int ec22) {
		this.ec22 = ec22;
	}

	public int getEc23() {
		return ec23;
	}

	public void setEc23(int ec23) {
		this.ec23 = ec23;
	}

	public int getEc24() {
		return ec24;
	}

	public void setEc24(int ec24) {
		this.ec24 = ec24;
	}

	public int getEc2Final() {
		return ec2Final;
	}

	public void setEc2Final(int ec2Final) {
		this.ec2Final = ec2Final;
	}

	public int getEc14() {
		return ec14;
	}

	public void setEc14(int ec14) {
		this.ec14 = ec14;
	}

	public int getEff2() {
		return eff2;
	}

	public void setEff2(int eff2) {
		this.eff2 = eff2;
	}

	public int getEff3() {
		return eff3;
	}

	public void setEff3(int eff3) {
		this.eff3 = eff3;
	}

	public int getEff4() {
		return eff4;
	}

	public void setEff4(int eff4) {
		this.eff4 = eff4;
	}

	public int getEffFinal() {
		return effFinal;
	}

	public void setEffFinal(int effFinal) {
		this.effFinal = effFinal;
	}

	public int getEls2() {
		return els2;
	}

	public void setEls2(int els2) {
		this.els2 = els2;
	}

	public int getEls3() {
		return els3;
	}

	public void setEls3(int els3) {
		this.els3 = els3;
	}

	public int getEls4() {
		return els4;
	}

	public void setEls4(int els4) {
		this.els4 = els4;
	}

	public int getElsFinal() {
		return elsFinal;
	}

	public void setElsFinal(int elsFinal) {
		this.elsFinal = elsFinal;
	}

	public int getFh2() {
		return fh2;
	}

	public void setFh2(int fh2) {
		this.fh2 = fh2;
	}

	public int getFh3() {
		return fh3;
	}

	public void setFh3(int fh3) {
		this.fh3 = fh3;
	}

	public int getFh4() {
		return fh4;
	}

	public void setFh4(int fh4) {
		this.fh4 = fh4;
	}

	public int getFhFinal() {
		return fhFinal;
	}

	public void setFhFinal(int fhFinal) {
		this.fhFinal = fhFinal;
	}

	public int getFr2() {
		return fr2;
	}

	public void setFr2(int fr2) {
		this.fr2 = fr2;
	}

	public int getFr3() {
		return fr3;
	}

	public void setFr3(int fr3) {
		this.fr3 = fr3;
	}

	public int getFr4() {
		return fr4;
	}

	public void setFr4(int fr4) {
		this.fr4 = fr4;
	}

	public int getFrFinal() {
		return frFinal;
	}

	public void setFrFinal(int frFinal) {
		this.frFinal = frFinal;
	}

	public int getFt2() {
		return ft2;
	}

	public void setFt2(int ft2) {
		this.ft2 = ft2;
	}

	public int getFt3() {
		return ft3;
	}

	public void setFt3(int ft3) {
		this.ft3 = ft3;
	}

	public int getFt4() {
		return ft4;
	}

	public void setFt4(int ft4) {
		this.ft4 = ft4;
	}

	public int getFtFinal() {
		return ftFinal;
	}

	public void setFtFinal(int ftFinal) {
		this.ftFinal = ftFinal;
	}

	public int getHlew2() {
		return hlew2;
	}

	public void setHlew2(int hlew2) {
		this.hlew2 = hlew2;
	}

	public int getHlew3() {
		return hlew3;
	}

	public void setHlew3(int hlew3) {
		this.hlew3 = hlew3;
	}

	public int getHlew4() {
		return hlew4;
	}

	public void setHlew4(int hlew4) {
		this.hlew4 = hlew4;
	}

	public int getHlewFinal() {
		return hlewFinal;
	}

	public void setHlewFinal(int hlewFinal) {
		this.hlewFinal = hlewFinal;
	}

	public int getHs2() {
		return hs2;
	}

	public void setHs2(int hs2) {
		this.hs2 = hs2;
	}

	public int getHs3() {
		return hs3;
	}

	public void setHs3(int hs3) {
		this.hs3 = hs3;
	}

	public int getHs4() {
		return hs4;
	}

	public void setHs4(int hs4) {
		this.hs4 = hs4;
	}

	public int getHsFinal() {
		return hsFinal;
	}

	public void setHsFinal(int hsFinal) {
		this.hsFinal = hsFinal;
	}

	public int getHspd2() {
		return hspd2;
	}

	public void setHspd2(int hspd2) {
		this.hspd2 = hspd2;
	}

	public int getHspd3() {
		return hspd3;
	}

	public void setHspd3(int hspd3) {
		this.hspd3 = hspd3;
	}

	public int getHspd4() {
		return hspd4;
	}

	public void setHspd4(int hspd4) {
		this.hspd4 = hspd4;
	}

	public int getHspdFinal() {
		return hspdFinal;
	}

	public void setHspdFinal(int hspdFinal) {
		this.hspdFinal = hspdFinal;
	}

	public int getIc2() {
		return ic2;
	}

	public void setIc2(int ic2) {
		this.ic2 = ic2;
	}

	public int getIc3() {
		return ic3;
	}

	public void setIc3(int ic3) {
		this.ic3 = ic3;
	}

	public int getIc4() {
		return ic4;
	}

	public void setIc4(int ic4) {
		this.ic4 = ic4;
	}

	public int getIcFinal() {
		return icFinal;
	}

	public void setIcFinal(int icFinal) {
		this.icFinal = icFinal;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getId3() {
		return id3;
	}

	public void setId3(int id3) {
		this.id3 = id3;
	}

	public int getId4() {
		return id4;
	}

	public void setId4(int id4) {
		this.id4 = id4;
	}

	public int getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(int idFinal) {
		this.idFinal = idFinal;
	}

	public int getIh2() {
		return ih2;
	}

	public void setIh2(int ih2) {
		this.ih2 = ih2;
	}

	public int getIh3() {
		return ih3;
	}

	public void setIh3(int ih3) {
		this.ih3 = ih3;
	}

	public int getIh4() {
		return ih4;
	}

	public void setIh4(int ih4) {
		this.ih4 = ih4;
	}

	public int getIhFinal() {
		return ihFinal;
	}

	public void setIhFinal(int ihFinal) {
		this.ihFinal = ihFinal;
	}

	public int getIni2() {
		return ini2;
	}

	public void setIni2(int ini2) {
		this.ini2 = ini2;
	}

	public int getIni3() {
		return ini3;
	}

	public void setIni3(int ini3) {
		this.ini3 = ini3;
	}

	public int getIni4() {
		return ini4;
	}

	public void setIni4(int ini4) {
		this.ini4 = ini4;
	}

	public int getIniFinal() {
		return iniFinal;
	}

	public void setIniFinal(int iniFinal) {
		this.iniFinal = iniFinal;
	}

	public int getIr2() {
		return ir2;
	}

	public void setIr2(int ir2) {
		this.ir2 = ir2;
	}

	public int getIr3() {
		return ir3;
	}

	public void setIr3(int ir3) {
		this.ir3 = ir3;
	}

	public int getIr4() {
		return ir4;
	}

	public void setIr4(int ir4) {
		this.ir4 = ir4;
	}

	public int getIrFinal() {
		return irFinal;
	}

	public void setIrFinal(int irFinal) {
		this.irFinal = irFinal;
	}

	public int getIsl2() {
		return isl2;
	}

	public void setIsl2(int isl2) {
		this.isl2 = isl2;
	}

	public int getIsl3() {
		return isl3;
	}

	public void setIsl3(int isl3) {
		this.isl3 = isl3;
	}

	public int getIsl4() {
		return isl4;
	}

	public void setIsl4(int isl4) {
		this.isl4 = isl4;
	}

	public int getIslFinal() {
		return islFinal;
	}

	public void setIslFinal(int islFinal) {
		this.islFinal = islFinal;
	}

	public int getIva2() {
		return iva2;
	}

	public void setIva2(int iva2) {
		this.iva2 = iva2;
	}

	public int getIva3() {
		return iva3;
	}

	public void setIva3(int iva3) {
		this.iva3 = iva3;
	}

	public int getIva4() {
		return iva4;
	}

	public void setIva4(int iva4) {
		this.iva4 = iva4;
	}

	public int getIvaFinal() {
		return ivaFinal;
	}

	public void setIvaFinal(int ivaFinal) {
		this.ivaFinal = ivaFinal;
	}

	public int getKin2() {
		return kin2;
	}

	public void setKin2(int kin2) {
		this.kin2 = kin2;
	}

	public int getKin3() {
		return kin3;
	}

	public void setKin3(int kin3) {
		this.kin3 = kin3;
	}

	public int getKin4() {
		return kin4;
	}

	public void setKin4(int kin4) {
		this.kin4 = kin4;
	}

	public int getKinFinal() {
		return kinFinal;
	}

	public void setKinFinal(int kinFinal) {
		this.kinFinal = kinFinal;
	}

	public int getKp2() {
		return kp2;
	}

	public void setKp2(int kp2) {
		this.kp2 = kp2;
	}

	public int getKp3() {
		return kp3;
	}

	public void setKp3(int kp3) {
		this.kp3 = kp3;
	}

	public int getKp4() {
		return kp4;
	}

	public void setKp4(int kp4) {
		this.kp4 = kp4;
	}

	public int getKpFinal() {
		return kpFinal;
	}

	public void setKpFinal(int kpFinal) {
		this.kpFinal = kpFinal;
	}

	public int getLs2() {
		return ls2;
	}

	public void setLs2(int ls2) {
		this.ls2 = ls2;
	}

	public int getLs3() {
		return ls3;
	}

	public void setLs3(int ls3) {
		this.ls3 = ls3;
	}

	public int getLs4() {
		return ls4;
	}

	public void setLs4(int ls4) {
		this.ls4 = ls4;
	}

	public int getLsFinal() {
		return lsFinal;
	}

	public void setLsFinal(int lsFinal) {
		this.lsFinal = lsFinal;
	}

	public int getMot2() {
		return mot2;
	}

	public void setMot2(int mot2) {
		this.mot2 = mot2;
	}

	public int getMot3() {
		return mot3;
	}

	public void setMot3(int mot3) {
		this.mot3 = mot3;
	}

	public int getMot4() {
		return mot4;
	}

	public void setMot4(int mot4) {
		this.mot4 = mot4;
	}

	public int getMotFinal() {
		return motFinal;
	}

	public void setMotFinal(int motFinal) {
		this.motFinal = motFinal;
	}

	public int getNeatAndOrganize2() {
		return neatAndOrganize2;
	}

	public void setNeatAndOrganize2(int neatAndOrganize2) {
		this.neatAndOrganize2 = neatAndOrganize2;
	}

	public int getNeatAndOrganize3() {
		return neatAndOrganize3;
	}

	public void setNeatAndOrganize3(int neatAndOrganize3) {
		this.neatAndOrganize3 = neatAndOrganize3;
	}

	public int getNeatAndOrganize4() {
		return neatAndOrganize4;
	}

	public void setNeatAndOrganize4(int neatAndOrganize4) {
		this.neatAndOrganize4 = neatAndOrganize4;
	}

	public int getNeatAndOrganizeFinal() {
		return neatAndOrganizeFinal;
	}

	public void setNeatAndOrganizeFinal(int neatAndOrganizeFinal) {
		this.neatAndOrganizeFinal = neatAndOrganizeFinal;
	}

	public int getOcs2() {
		return ocs2;
	}

	public void setOcs2(int ocs2) {
		this.ocs2 = ocs2;
	}

	public int getOcs3() {
		return ocs3;
	}

	public void setOcs3(int ocs3) {
		this.ocs3 = ocs3;
	}

	public int getOcs4() {
		return ocs4;
	}

	public void setOcs4(int ocs4) {
		this.ocs4 = ocs4;
	}

	public int getOcsFinal() {
		return ocsFinal;
	}

	public void setOcsFinal(int ocsFinal) {
		this.ocsFinal = ocsFinal;
	}

	public int getPa2() {
		return pa2;
	}

	public void setPa2(int pa2) {
		this.pa2 = pa2;
	}

	public int getPa3() {
		return pa3;
	}

	public void setPa3(int pa3) {
		this.pa3 = pa3;
	}

	public int getPa4() {
		return pa4;
	}

	public void setPa4(int pa4) {
		this.pa4 = pa4;
	}

	public int getPaFinal() {
		return paFinal;
	}

	public void setPaFinal(int paFinal) {
		this.paFinal = paFinal;
	}

	public int getPer2() {
		return per2;
	}

	public void setPer2(int per2) {
		this.per2 = per2;
	}

	public int getPer3() {
		return per3;
	}

	public void setPer3(int per3) {
		this.per3 = per3;
	}

	public int getPer4() {
		return per4;
	}

	public void setPer4(int per4) {
		this.per4 = per4;
	}

	public int getPerFinal() {
		return perFinal;
	}

	public void setPerFinal(int perFinal) {
		this.perFinal = perFinal;
	}

	public int getPfe2() {
		return pfe2;
	}

	public void setPfe2(int pfe2) {
		this.pfe2 = pfe2;
	}

	public int getPfe3() {
		return pfe3;
	}

	public void setPfe3(int pfe3) {
		this.pfe3 = pfe3;
	}

	public int getPfe4() {
		return pfe4;
	}

	public void setPfe4(int pfe4) {
		this.pfe4 = pfe4;
	}

	public int getPfeFinal() {
		return pfeFinal;
	}

	public void setPfeFinal(int pfeFinal) {
		this.pfeFinal = pfeFinal;
	}

	public int getPro2() {
		return pro2;
	}

	public void setPro2(int pro2) {
		this.pro2 = pro2;
	}

	public int getPro3() {
		return pro3;
	}

	public void setPro3(int pro3) {
		this.pro3 = pro3;
	}

	public int getPro4() {
		return pro4;
	}

	public void setPro4(int pro4) {
		this.pro4 = pro4;
	}

	public int getProFinal() {
		return proFinal;
	}

	public void setProFinal(int proFinal) {
		this.proFinal = proFinal;
	}

	public int getPrs2() {
		return prs2;
	}

	public void setPrs2(int prs2) {
		this.prs2 = prs2;
	}

	public int getPrs3() {
		return prs3;
	}

	public void setPrs3(int prs3) {
		this.prs3 = prs3;
	}

	public int getPrs4() {
		return prs4;
	}

	public void setPrs4(int prs4) {
		this.prs4 = prs4;
	}

	public int getPrsFinal() {
		return prsFinal;
	}

	public void setPrsFinal(int prsFinal) {
		this.prsFinal = prsFinal;
	}

	public int getPut2() {
		return put2;
	}

	public void setPut2(int put2) {
		this.put2 = put2;
	}

	public int getPut3() {
		return put3;
	}

	public void setPut3(int put3) {
		this.put3 = put3;
	}

	public int getPut4() {
		return put4;
	}

	public void setPut4(int put4) {
		this.put4 = put4;
	}

	public int getPutFinal() {
		return putFinal;
	}

	public void setPutFinal(int putFinal) {
		this.putFinal = putFinal;
	}

	public int getRes2() {
		return res2;
	}

	public void setRes2(int res2) {
		this.res2 = res2;
	}

	public int getRes3() {
		return res3;
	}

	public void setRes3(int res3) {
		this.res3 = res3;
	}

	public int getRes4() {
		return res4;
	}

	public void setRes4(int res4) {
		this.res4 = res4;
	}

	public int getResFinal() {
		return resFinal;
	}

	public void setResFinal(int resFinal) {
		this.resFinal = resFinal;
	}

	public int getSd2() {
		return sd2;
	}

	public void setSd2(int sd2) {
		this.sd2 = sd2;
	}

	public int getSd3() {
		return sd3;
	}

	public void setSd3(int sd3) {
		this.sd3 = sd3;
	}

	public int getSd4() {
		return sd4;
	}

	public void setSd4(int sd4) {
		this.sd4 = sd4;
	}

	public int getSdFinal() {
		return sdFinal;
	}

	public void setSdFinal(int sdFinal) {
		this.sdFinal = sdFinal;
	}

	public int getSpaa2() {
		return spaa2;
	}

	public void setSpaa2(int spaa2) {
		this.spaa2 = spaa2;
	}

	public int getSpaa3() {
		return spaa3;
	}

	public void setSpaa3(int spaa3) {
		this.spaa3 = spaa3;
	}

	public int getSpaa4() {
		return spaa4;
	}

	public void setSpaa4(int spaa4) {
		this.spaa4 = spaa4;
	}

	public int getSpaaFinal() {
		return spaaFinal;
	}

	public void setSpaaFinal(int spaaFinal) {
		this.spaaFinal = spaaFinal;
	}

	public int getTc2() {
		return tc2;
	}

	public void setTc2(int tc2) {
		this.tc2 = tc2;
	}

	public int getTc3() {
		return tc3;
	}

	public void setTc3(int tc3) {
		this.tc3 = tc3;
	}

	public int getTc4() {
		return tc4;
	}

	public void setTc4(int tc4) {
		this.tc4 = tc4;
	}

	public int getTcFinal() {
		return tcFinal;
	}

	public void setTcFinal(int tcFinal) {
		this.tcFinal = tcFinal;
	}

	public int getTea2() {
		return tea2;
	}

	public void setTea2(int tea2) {
		this.tea2 = tea2;
	}

	public int getTea3() {
		return tea3;
	}

	public void setTea3(int tea3) {
		this.tea3 = tea3;
	}

	public int getTea4() {
		return tea4;
	}

	public void setTea4(int tea4) {
		this.tea4 = tea4;
	}

	public int getTeaFinal() {
		return teaFinal;
	}

	public void setTeaFinal(int teaFinal) {
		this.teaFinal = teaFinal;
	}

	public int getWfr2() {
		return wfr2;
	}

	public void setWfr2(int wfr2) {
		this.wfr2 = wfr2;
	}

	public int getWfr3() {
		return wfr3;
	}

	public void setWfr3(int wfr3) {
		this.wfr3 = wfr3;
	}

	public int getWfr4() {
		return wfr4;
	}

	public void setWfr4(int wfr4) {
		this.wfr4 = wfr4;
	}

	public int getWfrFinal() {
		return wfrFinal;
	}

	public void setWfrFinal(int wfrFinal) {
		this.wfrFinal = wfrFinal;
	}

	public int getWi2() {
		return wi2;
	}

	public void setWi2(int wi2) {
		this.wi2 = wi2;
	}

	public int getWi3() {
		return wi3;
	}

	public void setWi3(int wi3) {
		this.wi3 = wi3;
	}

	public int getWi4() {
		return wi4;
	}

	public void setWi4(int wi4) {
		this.wi4 = wi4;
	}

	public int getWiFinal() {
		return wiFinal;
	}

	public void setWiFinal(int wiFinal) {
		this.wiFinal = wiFinal;
	}

	public int getWlr2() {
		return wlr2;
	}

	public void setWlr2(int wlr2) {
		this.wlr2 = wlr2;
	}

	public int getWlr3() {
		return wlr3;
	}

	public void setWlr3(int wlr3) {
		this.wlr3 = wlr3;
	}

	public int getWlr4() {
		return wlr4;
	}

	public void setWlr4(int wlr4) {
		this.wlr4 = wlr4;
	}

	public int getWlrFinal() {
		return wlrFinal;
	}

	public void setWlrFinal(int wlrFinal) {
		this.wlrFinal = wlrFinal;
	}

	public int getWp2() {
		return wp2;
	}

	public void setWp2(int wp2) {
		this.wp2 = wp2;
	}

	public int getWp3() {
		return wp3;
	}

	public void setWp3(int wp3) {
		this.wp3 = wp3;
	}

	public int getWp4() {
		return wp4;
	}

	public void setWp4(int wp4) {
		this.wp4 = wp4;
	}

	public int getWpFinal() {
		return wpFinal;
	}

	public void setWpFinal(int wpFinal) {
		this.wpFinal = wpFinal;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	public int getApgw() {
		return apgw;
	}

	public void setApgw(int apgw) {
		this.apgw = apgw;
	}

	public int getAspvi() {
		return aspvi;
	}

	public void setAspvi(int aspvi) {
		this.aspvi = aspvi;
	}

	public int getAw() {
		return aw;
	}

	public void setAw(int aw) {
		this.aw = aw;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
	}

	public int getCe() {
		return ce;
	}

	public void setCe(int ce) {
		this.ce = ce;
	}

	public int getCfp() {
		return cfp;
	}

	public void setCfp(int cfp) {
		this.cfp = cfp;
	}

	public int getClubsAndParticipation() {
		return clubsAndParticipation;
	}

	public void setClubsAndParticipation(int clubsAndParticipation) {
		this.clubsAndParticipation = clubsAndParticipation;
	}

	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	public int getCra() {
		return cra;
	}

	public void setCra(int cra) {
		this.cra = cra;
	}

	public int getCst() {
		return cst;
	}

	public void setCst(int cst) {
		this.cst = cst;
	}

	public int getCwp() {
		return cwp;
	}

	public void setCwp(int cwp) {
		this.cwp = cwp;
	}

	public int getCws() {
		return cws;
	}

	public void setCws(int cws) {
		this.cws = cws;
	}

	public int getDe() {
		return de;
	}

	public void setDe(int de) {
		this.de = de;
	}

	public int getDf() {
		return df;
	}

	public void setDf(int df) {
		this.df = df;
	}

	public int getEc1() {
		return ec1;
	}

	public void setEc1(int ec1) {
		this.ec1 = ec1;
	}

	public int getEc2() {
		return ec2;
	}

	public void setEc2(int ec2) {
		this.ec2 = ec2;
	}

	public int getEff() {
		return eff;
	}

	public void setEff(int eff) {
		this.eff = eff;
	}

	public int getEls() {
		return els;
	}

	public void setEls(int els) {
		this.els = els;
	}

	public int getFh() {
		return fh;
	}

	public void setFh(int fh) {
		this.fh = fh;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}

	public int getFt() {
		return ft;
	}

	public void setFt(int ft) {
		this.ft = ft;
	}

	public int getHlew() {
		return hlew;
	}

	public void setHlew(int hlew) {
		this.hlew = hlew;
	}

	public int getHs() {
		return hs;
	}

	public void setHs(int hs) {
		this.hs = hs;
	}

	public int getHspd() {
		return hspd;
	}

	public void setHspd(int hspd) {
		this.hspd = hspd;
	}

	public int getIc() {
		return ic;
	}

	public void setIc(int ic) {
		this.ic = ic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIh() {
		return ih;
	}

	public void setIh(int ih) {
		this.ih = ih;
	}

	public int getNeatAndOrganize() {
		return neatAndOrganize;
	}

	public void setNeatAndOrganize(int neatAndOrganize) {
		this.neatAndOrganize = neatAndOrganize;
	}

	public int getIni() {
		return ini;
	}

	public void setIni(int ini) {
		this.ini = ini;
	}

	public int getIr() {
		return ir;
	}

	public void setIr(int ir) {
		this.ir = ir;
	}

	public int getIsl() {
		return isl;
	}

	public void setIsl(int isl) {
		this.isl = isl;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getKin() {
		return kin;
	}

	public void setKin(int kin) {
		this.kin = kin;
	}

	public int getKp() {
		return kp;
	}

	public void setKp(int kp) {
		this.kp = kp;
	}

	public int getLs() {
		return ls;
	}

	public void setLs(int ls) {
		this.ls = ls;
	}

	public int getMot() {
		return mot;
	}

	public void setMot(int mot) {
		this.mot = mot;
	}

	public int getOcs() {
		return ocs;
	}

	public void setOcs(int ocs) {
		this.ocs = ocs;
	}

	public int getPa() {
		return pa;
	}

	public void setPa(int pa) {
		this.pa = pa;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	public int getPfe() {
		return pfe;
	}

	public void setPfe(int pfe) {
		this.pfe = pfe;
	}

	public int getPro() {
		return pro;
	}

	public void setPro(int pro) {
		this.pro = pro;
	}

	public int getPrs() {
		return prs;
	}

	public void setPrs(int prs) {
		this.prs = prs;
	}

	public int getPut() {
		return put;
	}

	public void setPut(int put) {
		this.put = put;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public int getSd() {
		return sd;
	}

	public void setSd(int sd) {
		this.sd = sd;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getSpaa() {
		return spaa;
	}

	public void setSpaa(int spaa) {
		this.spaa = spaa;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getTea() {
		return tea;
	}

	public void setTea(int tea) {
		this.tea = tea;
	}

	public int getWfr() {
		return wfr;
	}

	public void setWfr(int wfr) {
		this.wfr = wfr;
	}

	public int getWi() {
		return wi;
	}

	public void setWi(int wi) {
		this.wi = wi;
	}

	public int getWlr() {
		return wlr;
	}

	public void setWlr(int wlr) {
		this.wlr = wlr;
	}

	public int getWp() {
		return wp;
	}

	public void setWp(int wp) {
		this.wp = wp;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String yearLevel) {
		this.gradeLevel = yearLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTardy() {
		return tardy;
	}

	public void setTardy(int tardy) {
		this.tardy = tardy;
	}

	public int getTardy2() {
		return tardy2;
	}

	public void setTardy2(int tardy2) {
		this.tardy2 = tardy2;
	}

	public int getTardy3() {
		return tardy3;
	}

	public void setTardy3(int tardy3) {
		this.tardy3 = tardy3;
	}

	public int getTardy4() {
		return tardy4;
	}

	public void setTardy4(int tardy4) {
		this.tardy4 = tardy4;
	}

	public int getScouting() {
		return scouting;
	}

	public void setScouting(int scouting) {
		this.scouting = scouting;
	}

	public int getScouting2() {
		return scouting2;
	}

	public void setScouting2(int scouting2) {
		this.scouting2 = scouting2;
	}

	public int getScouting3() {
		return scouting3;
	}

	public void setScouting3(int scouting3) {
		this.scouting3 = scouting3;
	}

	public int getScouting4() {
		return scouting4;
	}

	public void setScouting4(int scouting4) {
		this.scouting4 = scouting4;
	}

	public int getScoutingFinal() {
		return scoutingFinal;
	}

	public void setScoutingFinal(int scoutingFinal) {
		this.scoutingFinal = scoutingFinal;
	}

	@Override
	public String popupSearch(String criteria) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void putDays() {
		CourseTotalDays tot = CourseTotalDays.extractTotalGradeLevelDays(gradeLevel);
		
		jan = tot.jan;
		feb = tot.feb;
		mar = tot.mar;
		apr = tot.apr;
		may = tot.may;
		jun = tot.jun;
		jul = tot.jul;
		aug = tot.aug;
		sep = tot.sep;
		oct = tot.oct;
		nov = tot.nov;
		dece = tot.dece;

		totalDays = tot.q1;
		totalDays2 = tot.q2;
		totalDays3 = tot.q3;
		totalDays4 = tot.q4;
	}
	
	@Override
	public void setupIndex() {
		runIndex(1, "section", "studentId");
		runUniqueIndex(2, "studentId", "gradeLevel");
		runIndex(3, "facultyId");
		runIndex(4, "facultyId","schoolYear","studentId");
	}
}
