package bean.person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
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
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;
import bean.Employee;
import bean.EmployeeFaculty;
import bean.Person;
import bean.admin.AppConfig;
import bean.extension.QAllStudentSubjectComponentScoreDetailExt;
import bean.reference.LockGrading;
import bean.reference.Section;
import bean.reference.Subject;
import constants.UserInfo;


@Entity
@Table(name = "FacultyGradingTask")
@UITemplate(template=TemplateTabSinglePage.class,
    columnSearch={"schoolYear", "faculty", "section", "subject", "component","weight"},
    criteriaSearch={"schoolYear", "facultyId", "section", "subject", "component"},
    gridCount=6, title="Component Grade Posting")
@ChildRecords(value={
    @ChildRecord(entity=QAllStudentSubjectComponentScoreDetailExt.class, template=ChildTemplateListOnly.class, fieldMapping={"seq","facultyGradingTaskId"}, sql="SELECT a FROM StudentSubjectDetailGrading a, Student b WHERE a.studentId=b.personId AND a.facultyGradingTaskId='${seq}' ORDER BY b.gender DESC, a.studentName", title="Per Student")
})
        
@DisplayGroups({
    @DisplayGroup(title = "Item Counts", gridCount = 24, 
    	fields = {
    		"q1ItemCount1","q1ItemCount2","q1ItemCount3","q1ItemCount4","q1ItemCount5","q1ItemCount6",
    		"q1ItemCount7","q1ItemCount8","q1ItemCount9","q1ItemCount10","q1ItemCount11","q1ItemCount12",

    		"q2ItemCount1","q2ItemCount2","q2ItemCount3","q2ItemCount4","q2ItemCount5","q2ItemCount6",
    		"q2ItemCount7","q2ItemCount8","q2ItemCount9","q2ItemCount10","q2ItemCount11","q2ItemCount12",

    		"q3ItemCount1","q3ItemCount2","q3ItemCount3","q3ItemCount4","q3ItemCount5","q3ItemCount6",
    		"q3ItemCount7","q3ItemCount8","q3ItemCount9","q3ItemCount10","q3ItemCount11","q3ItemCount12",

    		"q4ItemCount1","q4ItemCount2","q4ItemCount3","q4ItemCount4","q4ItemCount5","q4ItemCount6",
    		"q4ItemCount7","q4ItemCount8","q4ItemCount9","q4ItemCount10","q4ItemCount11","q4ItemCount12"
    	})
})
@Displays({
    @Display(name="schoolYear",width=60,type="Label"),
    @Display(name="section", type="PopSearch", linktoBean=Section.class,width=100,enabled=false), 
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class,width=100,enabled=false),
    @Display(name="component",type="Combo",
    		modelCombo={"ASSIGNMENT","ATTENDANCE","ATTITUDE","COMPOSITION/CREATIVE OUTPUT","FORMAL THEME/JOURNAL","GAP","HANDS ON","LABORATORY","LONG TEST","MASTERY 1","MASTERY 2","OPERATIONAL READING","OPERATIONAL MATH","PARTICIPATION","PERFORMANCE","PRACTICAL TEST","PRO MATH","PROJECT","QUARTERLY TEST","QUIZ","QUIZ/SW/READING TEST","RECITATION","SEATWORK","SW/HW/QUIZ","UNIFORM","WORK ETHICS"},width=100, enabled=false),
    @Display(name="weight",width=30, enabled=false),
    @Display(name="facultyId", type="PopSearch", linktoBean=Employee.class, enabled=false),
    @Display(name="description",gridFieldWidth=5,width=-1),

    @Display(name="q1ItemCount1", label="1", labelTop=true, leftLabel="1st Qtr", width=30),
    @Display(name="q1ItemCount2", label="2", labelTop=true, width=30),
    @Display(name="q1ItemCount3", label="3", labelTop=true, width=30),
    @Display(name="q1ItemCount4", label="4", labelTop=true, width=30),
    @Display(name="q1ItemCount5", label="5", labelTop=true, width=30),
    @Display(name="q1ItemCount6", label="6", labelTop=true, width=30),
    @Display(name="q1ItemCount7", label="7", labelTop=true, width=30),
    @Display(name="q1ItemCount8", label="8", labelTop=true, width=30),
    @Display(name="q1ItemCount9", label="9", labelTop=true, width=30),
    @Display(name="q1ItemCount10", label="10", labelTop=true, width=30),
    @Display(name="q1ItemCount11", label="11", labelTop=true, width=30),
    @Display(name="q1ItemCount12", label="12", labelTop=true, width=30),

    @Display(name="q2ItemCount1", label="2nd Qtr", width=30),
    @Display(name="q2ItemCount2", label="2", hideLabel=true, width=30),
    @Display(name="q2ItemCount3", label="3", hideLabel=true, width=30),
    @Display(name="q2ItemCount4", label="4", hideLabel=true, width=30),
    @Display(name="q2ItemCount5", label="5", hideLabel=true, width=30),
    @Display(name="q2ItemCount6", label="6", hideLabel=true, width=30),
    @Display(name="q2ItemCount7", label="7", hideLabel=true, width=30),
    @Display(name="q2ItemCount8", label="8", hideLabel=true, width=30),
    @Display(name="q2ItemCount9", label="9", hideLabel=true, width=30),
    @Display(name="q2ItemCount10", label="10", hideLabel=true, width=30),
    @Display(name="q2ItemCount11", label="11", hideLabel=true, width=30),
    @Display(name="q2ItemCount12", label="12", hideLabel=true, width=30),
    
    @Display(name="q3ItemCount1", label="3rd Qtr", width=30),
    @Display(name="q3ItemCount2", label="2", hideLabel=true, width=30),
    @Display(name="q3ItemCount3", label="3", hideLabel=true, width=30),
    @Display(name="q3ItemCount4", label="4", hideLabel=true, width=30),
    @Display(name="q3ItemCount5", label="5", hideLabel=true, width=30),
    @Display(name="q3ItemCount6", label="6", hideLabel=true, width=30),
    @Display(name="q3ItemCount7", label="7", hideLabel=true, width=30),
    @Display(name="q3ItemCount8", label="8", hideLabel=true, width=30),
    @Display(name="q3ItemCount9", label="9", hideLabel=true, width=30),
    @Display(name="q3ItemCount10", label="10", hideLabel=true, width=30),
    @Display(name="q3ItemCount11", label="11", hideLabel=true, width=30),
    @Display(name="q3ItemCount12", label="12", hideLabel=true, width=30),
    
    @Display(name="q4ItemCount1", label="4th Qtr", width=30),
    @Display(name="q4ItemCount2", label="2", hideLabel=true, width=30),
    @Display(name="q4ItemCount3", label="3", hideLabel=true, width=30),
    @Display(name="q4ItemCount4", label="4", hideLabel=true, width=30),
    @Display(name="q4ItemCount5", label="5", hideLabel=true, width=30),
    @Display(name="q4ItemCount6", label="6", hideLabel=true, width=30),
    @Display(name="q4ItemCount7", label="7", hideLabel=true, width=30),
    @Display(name="q4ItemCount8", label="8", hideLabel=true, width=30),
    @Display(name="q4ItemCount9", label="9", hideLabel=true, width=30),
    @Display(name="q4ItemCount10", label="10", hideLabel=true, width=30),
    @Display(name="q4ItemCount11", label="11", hideLabel=true, width=30),
    @Display(name="q4ItemCount12", label="12", hideLabel=true, width=30)
})
@Reports({
    @template.Report(reportFile = "Q1_ClassRecord", reportTitle = "Class Record Q1", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q2_ClassRecord", reportTitle = "Q2", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q3_ClassRecord", reportTitle = "Q3", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q4_ClassRecord", reportTitle = "Q4", reportSql = "${scheduleId}"),
    @template.Report(reportFile = "Q1_Grades", reportTitle = "Summary Q1", reportSql = "${section}"),
    @template.Report(reportFile = "Q2_Grades", reportTitle = "Q2", reportSql = "${section}"),
    @template.Report(reportFile = "Q3_Grades", reportTitle = "Q3", reportSql = "${section}"),
    @template.Report(reportFile = "Q4_Grades", reportTitle = "Q4", reportSql = "${section}"),
    @template.Report(reportFile = "QAll_Grades", reportTitle = "Summary Final", reportSql = "${section}"),
	@template.Report(reportFile = "SectionReportCardQuarter1", reportTitle = "Card Q1", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter2", reportTitle = "Q2", reportSql = "${section}"),
    @template.Report(reportFile = "SectionReportCardQuarter3", reportTitle = "Q3", reportSql = "${section}"),
	@template.Report(reportFile = "SectionReportCardQuarter4", reportTitle = "Q4", reportSql = "${section}")
})
@ActionButtons({
//    @ActionButton(name = "btnGenerateAllComponents", label = "Generate All Components"),
    @ActionButton(name = "btnGenerateTask", label = "Student Component Grade/Score"),
    @ActionButton(name = "btnSaveAllScore1", label = "Save All 1st Qtr."),
    @ActionButton(name = "btnSaveAllScore2", label = "2nd"),
    @ActionButton(name = "btnSaveAllScore3", label = "3rd"),
    @ActionButton(name = "btnSaveAllScore4", label = "4th"),
    @ActionButton(name = "btnSaveAllSection", label = "All"),
    @ActionButton(name = "btnRankAll1", label = "Rank All 1st Qtr."),
    @ActionButton(name = "btnRankAll2", label = "2nd"),
    @ActionButton(name = "btnRankAll3", label = "3rd"),
    @ActionButton(name = "btnRankAll4", label = "4th")
})
public class FacultyGradingTask extends AbstractIBean implements Serializable {

	@Override
	public int getRecordListCount() {
		return 100;
	}

	@Override
	public void delete() {
		LockGrading s = LockGrading.extractGrading(schoolYear);
		if (!s.isQ1Locked()) {
			boolean b = PanelUtil.showPrompt(null, "Are you sure you want to delete the selected component?");
			if (b) {
				b = PanelUtil.showPrompt(null, "Some students already have grade for 1st quarter, do you want to continue?");
				if (b) {
					DBClient.runSQLNative("DELETE FROM StudentSubjectDetailGrading WHERE facultyGradingTaskId=",seq);
					super.delete();
				}
			}
		}
		else {
			PanelUtil.showError(null, "You can only delete component on first quarter.");
		}
	}

	public FacultyGradingTask() {
		schoolYear = AppConfig.getSchoolYear();
		facultyId = UserInfo.getPersonId();
	}
	
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "section")
    public String section;
    @Column(name = "subject")
    public String subject;
    @Column(name = "component")
    public String component;
    @Column(name = "faculty")
    public String faculty;

    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "description")
    public String description;
    @Column(name = "weight")
    public double weight;
    @Column(name = "usePercentage")
    public boolean usePercentage;
   
    @Column(name = "q1ItemCount1")
    public int q1ItemCount1;
    @Column(name = "q1ItemCount2")
    public int q1ItemCount2;
    @Column(name = "q1ItemCount3")
    public int q1ItemCount3;
    @Column(name = "q1ItemCount4")
    public int q1ItemCount4;
    @Column(name = "q1ItemCount5")
    public int q1ItemCount5;
    @Column(name = "q1ItemCount6")
    public int q1ItemCount6;
    @Column(name = "q1ItemCount7")
    public int q1ItemCount7;
    @Column(name = "q1ItemCount8")
    public int q1ItemCount8;
    @Column(name = "q1ItemCount9")
    public int q1ItemCount9;
    @Column(name = "q1ItemCount10")
    public int q1ItemCount10;
    @Column(name = "q1ItemCount11")
    public int q1ItemCount11;
    @Column(name = "q1ItemCount12")
    public int q1ItemCount12;

    @Column(name = "q2ItemCount1")
    public int q2ItemCount1;
    @Column(name = "q2ItemCount2")
    public int q2ItemCount2;
    @Column(name = "q2ItemCount3")
    public int q2ItemCount3;
    @Column(name = "q2ItemCount4")
    public int q2ItemCount4;
    @Column(name = "q2ItemCount5")
    public int q2ItemCount5;
    @Column(name = "q2ItemCount6")
    public int q2ItemCount6;
    @Column(name = "q2ItemCount7")
    public int q2ItemCount7;
    @Column(name = "q2ItemCount8")
    public int q2ItemCount8;
    @Column(name = "q2ItemCount9")
    public int q2ItemCount9;
    @Column(name = "q2ItemCount10")
    public int q2ItemCount10;
    @Column(name = "q2ItemCount11")
    public int q2ItemCount11;
    @Column(name = "q2ItemCount12")
    public int q2ItemCount12;

    @Column(name = "q3ItemCount1")
    public int q3ItemCount1;
    @Column(name = "q3ItemCount2")
    public int q3ItemCount2;
    @Column(name = "q3ItemCount3")
    public int q3ItemCount3;
    @Column(name = "q3ItemCount4")
    public int q3ItemCount4;
    @Column(name = "q3ItemCount5")
    public int q3ItemCount5;
    @Column(name = "q3ItemCount6")
    public int q3ItemCount6;
    @Column(name = "q3ItemCount7")
    public int q3ItemCount7;
    @Column(name = "q3ItemCount8")
    public int q3ItemCount8;
    @Column(name = "q3ItemCount9")
    public int q3ItemCount9;
    @Column(name = "q3ItemCount10")
    public int q3ItemCount10;
    @Column(name = "q3ItemCount11")
    public int q3ItemCount11;
    @Column(name = "q3ItemCount12")
    public int q3ItemCount12;

    @Column(name = "q4ItemCount1")
    public int q4ItemCount1;
    @Column(name = "q4ItemCount2")
    public int q4ItemCount2;
    @Column(name = "q4ItemCount3")
    public int q4ItemCount3;
    @Column(name = "q4ItemCount4")
    public int q4ItemCount4;
    @Column(name = "q4ItemCount5")
    public int q4ItemCount5;
    @Column(name = "q4ItemCount6")
    public int q4ItemCount6;
    @Column(name = "q4ItemCount7")
    public int q4ItemCount7;
    @Column(name = "q4ItemCount8")
    public int q4ItemCount8;
    @Column(name = "q4ItemCount9")
    public int q4ItemCount9;
    @Column(name = "q4ItemCount10")
    public int q4ItemCount10;
    @Column(name = "q4ItemCount11")
    public int q4ItemCount11;
    @Column(name = "q4ItemCount12")
    public int q4ItemCount12;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isUsePercentage() {
		return usePercentage;
	}

	public void setUsePercentage(boolean usePercentage) {
		this.usePercentage = usePercentage;
	}

	public int getQ1ItemCount1() {
		return q1ItemCount1;
	}

	public void setQ1ItemCount1(int itemCount1) {
		q1ItemCount1 = itemCount1;
	}

	public int getQ1ItemCount2() {
		return q1ItemCount2;
	}

	public void setQ1ItemCount2(int itemCount2) {
		q1ItemCount2 = itemCount2;
	}

	public int getQ1ItemCount3() {
		return q1ItemCount3;
	}

	public void setQ1ItemCount3(int itemCount3) {
		q1ItemCount3 = itemCount3;
	}

	public int getQ1ItemCount4() {
		return q1ItemCount4;
	}

	public void setQ1ItemCount4(int itemCount4) {
		q1ItemCount4 = itemCount4;
	}

	public int getQ1ItemCount5() {
		return q1ItemCount5;
	}

	public void setQ1ItemCount5(int itemCount5) {
		q1ItemCount5 = itemCount5;
	}

	public int getQ1ItemCount6() {
		return q1ItemCount6;
	}

	public void setQ1ItemCount6(int itemCount6) {
		q1ItemCount6 = itemCount6;
	}

	public int getQ1ItemCount7() {
		return q1ItemCount7;
	}

	public void setQ1ItemCount7(int itemCount7) {
		q1ItemCount7 = itemCount7;
	}

	public int getQ1ItemCount8() {
		return q1ItemCount8;
	}

	public void setQ1ItemCount8(int itemCount8) {
		q1ItemCount8 = itemCount8;
	}

	public int getQ1ItemCount9() {
		return q1ItemCount9;
	}

	public void setQ1ItemCount9(int itemCount9) {
		q1ItemCount9 = itemCount9;
	}

	public int getQ1ItemCount10() {
		return q1ItemCount10;
	}

	public void setQ1ItemCount10(int itemCount10) {
		q1ItemCount10 = itemCount10;
	}

	public int getQ1ItemCount11() {
		return q1ItemCount11;
	}

	public void setQ1ItemCount11(int itemCount11) {
		q1ItemCount11 = itemCount11;
	}

	public int getQ1ItemCount12() {
		return q1ItemCount12;
	}

	public void setQ1ItemCount12(int itemCount12) {
		q1ItemCount12 = itemCount12;
	}

	public int getQ2ItemCount1() {
		return q2ItemCount1;
	}

	public void setQ2ItemCount1(int itemCount1) {
		q2ItemCount1 = itemCount1;
	}

	public int getQ2ItemCount2() {
		return q2ItemCount2;
	}

	public void setQ2ItemCount2(int itemCount2) {
		q2ItemCount2 = itemCount2;
	}

	public int getQ2ItemCount3() {
		return q2ItemCount3;
	}

	public void setQ2ItemCount3(int itemCount3) {
		q2ItemCount3 = itemCount3;
	}

	public int getQ2ItemCount4() {
		return q2ItemCount4;
	}

	public void setQ2ItemCount4(int itemCount4) {
		q2ItemCount4 = itemCount4;
	}

	public int getQ2ItemCount5() {
		return q2ItemCount5;
	}

	public void setQ2ItemCount5(int itemCount5) {
		q2ItemCount5 = itemCount5;
	}

	public int getQ2ItemCount6() {
		return q2ItemCount6;
	}

	public void setQ2ItemCount6(int itemCount6) {
		q2ItemCount6 = itemCount6;
	}

	public int getQ2ItemCount7() {
		return q2ItemCount7;
	}

	public void setQ2ItemCount7(int itemCount7) {
		q2ItemCount7 = itemCount7;
	}

	public int getQ2ItemCount8() {
		return q2ItemCount8;
	}

	public void setQ2ItemCount8(int itemCount8) {
		q2ItemCount8 = itemCount8;
	}

	public int getQ2ItemCount9() {
		return q2ItemCount9;
	}

	public void setQ2ItemCount9(int itemCount9) {
		q2ItemCount9 = itemCount9;
	}

	public int getQ2ItemCount10() {
		return q2ItemCount10;
	}

	public void setQ2ItemCount10(int itemCount10) {
		q2ItemCount10 = itemCount10;
	}

	public int getQ2ItemCount11() {
		return q2ItemCount11;
	}

	public void setQ2ItemCount11(int itemCount11) {
		q2ItemCount11 = itemCount11;
	}

	public int getQ2ItemCount12() {
		return q2ItemCount12;
	}

	public void setQ2ItemCount12(int itemCount12) {
		q2ItemCount12 = itemCount12;
	}

	public int getQ3ItemCount1() {
		return q3ItemCount1;
	}

	public void setQ3ItemCount1(int itemCount1) {
		q3ItemCount1 = itemCount1;
	}

	public int getQ3ItemCount2() {
		return q3ItemCount2;
	}

	public void setQ3ItemCount2(int itemCount2) {
		q3ItemCount2 = itemCount2;
	}

	public int getQ3ItemCount3() {
		return q3ItemCount3;
	}

	public void setQ3ItemCount3(int itemCount3) {
		q3ItemCount3 = itemCount3;
	}

	public int getQ3ItemCount4() {
		return q3ItemCount4;
	}

	public void setQ3ItemCount4(int itemCount4) {
		q3ItemCount4 = itemCount4;
	}

	public int getQ3ItemCount5() {
		return q3ItemCount5;
	}

	public void setQ3ItemCount5(int itemCount5) {
		q3ItemCount5 = itemCount5;
	}

	public int getQ3ItemCount6() {
		return q3ItemCount6;
	}

	public void setQ3ItemCount6(int itemCount6) {
		q3ItemCount6 = itemCount6;
	}

	public int getQ3ItemCount7() {
		return q3ItemCount7;
	}

	public void setQ3ItemCount7(int itemCount7) {
		q3ItemCount7 = itemCount7;
	}

	public int getQ3ItemCount8() {
		return q3ItemCount8;
	}

	public void setQ3ItemCount8(int itemCount8) {
		q3ItemCount8 = itemCount8;
	}

	public int getQ3ItemCount9() {
		return q3ItemCount9;
	}

	public void setQ3ItemCount9(int itemCount9) {
		q3ItemCount9 = itemCount9;
	}

	public int getQ3ItemCount10() {
		return q3ItemCount10;
	}

	public void setQ3ItemCount10(int itemCount10) {
		q3ItemCount10 = itemCount10;
	}

	public int getQ3ItemCount11() {
		return q3ItemCount11;
	}

	public void setQ3ItemCount11(int itemCount11) {
		q3ItemCount11 = itemCount11;
	}

	public int getQ3ItemCount12() {
		return q3ItemCount12;
	}

	public void setQ3ItemCount12(int itemCount12) {
		q3ItemCount12 = itemCount12;
	}

	public int getQ4ItemCount1() {
		return q4ItemCount1;
	}

	public void setQ4ItemCount1(int itemCount1) {
		q4ItemCount1 = itemCount1;
	}

	public int getQ4ItemCount2() {
		return q4ItemCount2;
	}

	public void setQ4ItemCount2(int itemCount2) {
		q4ItemCount2 = itemCount2;
	}

	public int getQ4ItemCount3() {
		return q4ItemCount3;
	}

	public void setQ4ItemCount3(int itemCount3) {
		q4ItemCount3 = itemCount3;
	}

	public int getQ4ItemCount4() {
		return q4ItemCount4;
	}

	public void setQ4ItemCount4(int itemCount4) {
		q4ItemCount4 = itemCount4;
	}

	public int getQ4ItemCount5() {
		return q4ItemCount5;
	}

	public void setQ4ItemCount5(int itemCount5) {
		q4ItemCount5 = itemCount5;
	}

	public int getQ4ItemCount6() {
		return q4ItemCount6;
	}

	public void setQ4ItemCount6(int itemCount6) {
		q4ItemCount6 = itemCount6;
	}

	public int getQ4ItemCount7() {
		return q4ItemCount7;
	}

	public void setQ4ItemCount7(int itemCount7) {
		q4ItemCount7 = itemCount7;
	}

	public int getQ4ItemCount8() {
		return q4ItemCount8;
	}

	public void setQ4ItemCount8(int itemCount8) {
		q4ItemCount8 = itemCount8;
	}

	public int getQ4ItemCount9() {
		return q4ItemCount9;
	}

	public void setQ4ItemCount9(int itemCount9) {
		q4ItemCount9 = itemCount9;
	}

	public int getQ4ItemCount10() {
		return q4ItemCount10;
	}

	public void setQ4ItemCount10(int itemCount10) {
		q4ItemCount10 = itemCount10;
	}

	public int getQ4ItemCount11() {
		return q4ItemCount11;
	}

	public void setQ4ItemCount11(int itemCount11) {
		q4ItemCount11 = itemCount11;
	}

	public int getQ4ItemCount12() {
		return q4ItemCount12;
	}

	public void setQ4ItemCount12(int itemCount12) {
		q4ItemCount12 = itemCount12;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear", "component");
    }
        
    public static void main (String[]args){
        view(FacultyGradingTask.class);
    }
    
    public String getFaculty() {
        EmployeeFaculty fac = (EmployeeFaculty) EmployeeFaculty.extractObject(EmployeeFaculty.class.getSimpleName(), BeanUtil.concat(facultyId,""));
        if (fac==null) return "";
        return fac.toString();
    }
    
	@Override
	public void save() {
		if (facultyId>0 && faculty==null) {
			Person f = EmployeeFaculty.extractObject(facultyId);
			faculty = f.toString();
		}
		if (section!=null) {
			Section s = (Section) Section.extractObject(Section.class.getSimpleName(), section);
			gradeLevel = s.gradeLevel;
		}
		super.save();
	}

	@Override
	public void setupIndex() {
		runIndex(1, "subject", "component");
		runUniqueIndex(2, "subject","component","facultyId","section");
		runIndex(3, "facultyId");
		runIndex(4, "facultyId","schoolYear");
		runIndex(5, "scheduleId");
	}

	@Override
	public Map<String, String> extractReportParameter() {
		List<FacultyGradingTask> tasks = DBClient.getList(BeanUtil.concat("SELECT a FROM FacultyGradingTask a WHERE a.scheduleId=",scheduleId));
		Map<String, String> map = new HashMap<String, String>();
		for (int i=1; i<=tasks.size(); i++) {
			map.put(BeanUtil.concat("component",i), tasks.get(i-1).getComponent());
		}
		return map;
	}
	
}
