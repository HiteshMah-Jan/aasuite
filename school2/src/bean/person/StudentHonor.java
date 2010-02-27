/*
 * Studentacademichistory.java
 *
 * Created on Nov 27, 2007, 6:32:51 PM
 *
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import template.screen.TemplateTabSinglePageLeftRight;
import util.DBClient;
import bean.Person;
import bean.Student;
import bean.reference.GradeLevel;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentHonor")
@UITemplate(
		criteriaSearch={
				"studentId","schoolYear","gradeLevel"
		}, 
		columnSearch={
				"studentName","schoolYear","gradeLevel", 
				"q1Honor","q1AchievementGPA","q1AchievementLowestGrade","q1Values1GPA","q1Values1LowestGrade","q1Values2GPA","q1Values2LowestGrade",
				"q2Honor","q2AchievementGPA","q2AchievementLowestGrade","q2Values1GPA","q2Values1LowestGrade","q2Values2GPA","q2Values2LowestGrade",
				"q3Honor","q3AchievementGPA","q3AchievementLowestGrade","q3Values1GPA","q3Values1LowestGrade","q3Values2GPA","q3Values2LowestGrade",
				"q4Honor","q4AchievementGPA","q4AchievementLowestGrade","q4Values1GPA","q4Values1LowestGrade","q4Values2GPA","q4Values2LowestGrade",
				"finalHonor","finalAchievementGPA","finalAchievementLowestGrade","finalValues1GPA","finalValues1LowestGrade","finalValues2GPA","finalValues2LowestGrade"
		}, 
		autoResizeTable=false, gridCount=14, template=TemplateTabSinglePage.class
)
@Displays({
		@Display(name="schoolYear", gridFieldWidth=14, width=-1, enabled=false),
		@Display(name="gradeLevel", gridFieldWidth=14, width=-1, enabled=false, type="PopSearch", linktoBean=GradeLevel.class),
        @Display(name="studentId",type="PopSearch", linktoBean=Student.class, gridFieldWidth=14, width=-1, label="Student", enabled=false),
        
        @Display(name="q1Honor", label="Honor", enabled=false, width=70, labelTop=true, leftLabel="Q1"),
        @Display(name="q1AchievementGPA", label="GPA", enabled=false, width=30, labelTop=true, leftLabel="   Academic"),
        @Display(name="q1AchievementLowestGrade", label="Lowest", enabled=false, width=30, labelTop=true),
        @Display(name="q1Values1GPA", label="GPA", enabled=false, width=30, labelTop=true, leftLabel="   Values 1"),
        @Display(name="q1Values1LowestGrade", label="Lowest", enabled=false, width=30, labelTop=true),
        @Display(name="q1Values2GPA", label="GPA", enabled=false, width=30, labelTop=true, leftLabel="   Values 2"),
        @Display(name="q1Values2LowestGrade", label="Lowest", enabled=false, width=30, labelTop=true),
        
        @Display(name="q2Honor", label="Q2", enabled=false, width=70),
        @Display(name="q2AchievementGPA", label="GPA", enabled=false, width=30, hideLabel=true),
        @Display(name="q2AchievementLowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q2Values1GPA", label="Values 1", enabled=false, width=30, hideLabel=true),
        @Display(name="q2Values1LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q2Values2GPA", label="Values 2", enabled=false, width=30, hideLabel=true),
        @Display(name="q2Values2LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),

        @Display(name="q3Honor", label="Q3", enabled=false, width=70),
        @Display(name="q3AchievementGPA", label="GPA", enabled=false, width=30, hideLabel=true),
        @Display(name="q3AchievementLowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q3Values1GPA", label="Values 1", enabled=false, width=30, hideLabel=true),
        @Display(name="q3Values1LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q3Values2GPA", label="Values 2", enabled=false, width=30, hideLabel=true),
        @Display(name="q3Values2LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),

        @Display(name="q4Honor", label="Q4", enabled=false, width=70),
        @Display(name="q4AchievementGPA", label="GPA", enabled=false, width=30, hideLabel=true),
        @Display(name="q4AchievementLowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q4Values1GPA", label="Values 1", enabled=false, width=30, hideLabel=true),
        @Display(name="q4Values1LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="q4Values2GPA", label="Values 2", enabled=false, width=30, hideLabel=true),
        @Display(name="q4Values2LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),

        @Display(name="finalHonor", label="Final", enabled=false, width=70),
        @Display(name="finalAchievementGPA", label="GPA", enabled=false, width=30, hideLabel=true),
        @Display(name="finalAchievementLowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="finalValues1GPA", label="Values 1", enabled=false, width=30, hideLabel=true),
        @Display(name="finalValues1LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true),
        @Display(name="finalValues2GPA", label="Values 2", enabled=false, width=30, hideLabel=true),
        @Display(name="finalValues2LowestGrade", label="Lowest", enabled=false, width=30, hideLabel=true)
})
//@ActionButtons( {
//	@ActionButton(name = "btnGetHonorQ1", label = "Get Honor Q1"),
//	@ActionButton(name = "btnGetHonorQ2", label = "Q2"),
//	@ActionButton(name = "btnGetHonorQ3", label = "Q3"),
//	@ActionButton(name = "btnGetHonorQ4", label = "Q4"),
//	@ActionButton(name = "btnGetHonorFinal", label = "Final")
//})
public class StudentHonor extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(StudentHonor.class);
	}
	
    @Override
	public void save() {
    	if (studentName==null) {
        	Person p = extractPerson(studentId);
    		studentName = p.toString();
    		section = p.section;
    	}
    	String sql = "UPDATE Enrollment SET meritQ1='"+q1Honor+"', meritQ2='"+q2Honor+"', meritQ3='"+q3Honor+"', meritQ4='"+q4Honor+"', meritFinal='"+finalHonor+"' WHERE studentId="+studentId+" AND gradeLevel='"+gradeLevel+"'";
    	DBClient.runSQLNative(sql);
		super.save();
	}

	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "studentName")
    public String studentName;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "section")
    public String section;
    
    @Column(name = "q1Honor")
    public String q1Honor;
    @Column(name = "q1AchievementGPA")
    public int q1AchievementGPA;
    @Column(name = "q1AchievementLowestGrade")
    public int q1AchievementLowestGrade;

    @Column(name = "q1Values1GPA")
    public int q1Values1GPA;
    @Column(name = "q1Values1LowestGrade")
    public int q1Values1LowestGrade;

    @Column(name = "q1Values2GPA")
    public int q1Values2GPA;
    @Column(name = "q1Values2LowestGrade")
    public int q1Values2LowestGrade;

    @Column(name = "q2Honor")
    public String q2Honor;
    @Column(name = "q2AchievementGPA")
    public int q2AchievementGPA;
    @Column(name = "q2AchievementLowestGrade")
    public int q2AchievementLowestGrade;

    @Column(name = "q2Values1GPA")
    public int q2Values1GPA;
    @Column(name = "q2Values1LowestGrade")
    public int q2Values1LowestGrade;

    @Column(name = "q2Values2GPA")
    public int q2Values2GPA;
    @Column(name = "q2Values2LowestGrade")
    public int q2Values2LowestGrade;

    @Column(name = "q3Honor")
    public String q3Honor;
    @Column(name = "q3AchievementGPA")
    public int q3AchievementGPA;
    @Column(name = "q3AchievementLowestGrade")
    public int q3AchievementLowestGrade;

    @Column(name = "q3Values1GPA")
    public int q3Values1GPA;
    @Column(name = "q3Values1LowestGrade")
    public int q3Values1LowestGrade;

    @Column(name = "q3Values2GPA")
    public int q3Values2GPA;
    @Column(name = "q3Values2LowestGrade")
    public int q3Values2LowestGrade;

    @Column(name = "q4Honor")
    public String q4Honor;
    @Column(name = "q4AchievementGPA")
    public int q4AchievementGPA;
    @Column(name = "q4AchievementLowestGrade")
    public int q4AchievementLowestGrade;

    @Column(name = "q4Values1GPA")
    public int q4Values1GPA;
    @Column(name = "q4Values1LowestGrade")
    public int q4Values1LowestGrade;

    @Column(name = "q4Values2GPA")
    public int q4Values2GPA;
    @Column(name = "q4Values2LowestGrade")
    public int q4Values2LowestGrade;

    @Column(name = "finalHonor")
    public String finalHonor;
    @Column(name = "finalAchievementGPA")
    public int finalAchievementGPA;
    @Column(name = "finalAchievementLowestGrade")
    public int finalAchievementLowestGrade;

    @Column(name = "finalValues1GPA")
    public int finalValues1GPA;
    @Column(name = "finalValues1LowestGrade")
    public int finalValues1LowestGrade;

    @Column(name = "finalValues2GPA")
    public int finalValues2GPA;
    @Column(name = "finalValues2LowestGrade")
    public int finalValues2LowestGrade;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subject","subjectCode", "unit");
    }

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getQ1Honor() {
		return q1Honor;
	}

	public void setQ1Honor(String honor) {
		q1Honor = honor;
	}

	public int getQ1AchievementGPA() {
		return q1AchievementGPA;
	}

	public void setQ1AchievementGPA(int achievementGPA) {
		q1AchievementGPA = achievementGPA;
	}

	public int getQ1AchievementLowestGrade() {
		return q1AchievementLowestGrade;
	}

	public void setQ1AchievementLowestGrade(int achievementLowestGrade) {
		q1AchievementLowestGrade = achievementLowestGrade;
	}

	public int getQ1Values1GPA() {
		return q1Values1GPA;
	}

	public void setQ1Values1GPA(int values1GPA) {
		q1Values1GPA = values1GPA;
	}

	public int getQ1Values1LowestGrade() {
		return q1Values1LowestGrade;
	}

	public void setQ1Values1LowestGrade(int values1LowestGrade) {
		q1Values1LowestGrade = values1LowestGrade;
	}

	public int getQ1Values2GPA() {
		return q1Values2GPA;
	}

	public void setQ1Values2GPA(int values2GPA) {
		q1Values2GPA = values2GPA;
	}

	public int getQ1Values2LowestGrade() {
		return q1Values2LowestGrade;
	}

	public void setQ1Values2LowestGrade(int values2LowestGrade) {
		q1Values2LowestGrade = values2LowestGrade;
	}

	public String getQ2Honor() {
		return q2Honor;
	}

	public void setQ2Honor(String honor) {
		q2Honor = honor;
	}

	public int getQ2AchievementGPA() {
		return q2AchievementGPA;
	}

	public void setQ2AchievementGPA(int achievementGPA) {
		q2AchievementGPA = achievementGPA;
	}

	public int getQ2AchievementLowestGrade() {
		return q2AchievementLowestGrade;
	}

	public void setQ2AchievementLowestGrade(int achievementLowestGrade) {
		q2AchievementLowestGrade = achievementLowestGrade;
	}

	public int getQ2Values1GPA() {
		return q2Values1GPA;
	}

	public void setQ2Values1GPA(int values1GPA) {
		q2Values1GPA = values1GPA;
	}

	public int getQ2Values1LowestGrade() {
		return q2Values1LowestGrade;
	}

	public void setQ2Values1LowestGrade(int values1LowestGrade) {
		q2Values1LowestGrade = values1LowestGrade;
	}

	public int getQ2Values2GPA() {
		return q2Values2GPA;
	}

	public void setQ2Values2GPA(int values2GPA) {
		q2Values2GPA = values2GPA;
	}

	public int getQ2Values2LowestGrade() {
		return q2Values2LowestGrade;
	}

	public void setQ2Values2LowestGrade(int values2LowestGrade) {
		q2Values2LowestGrade = values2LowestGrade;
	}

	public String getQ3Honor() {
		return q3Honor;
	}

	public void setQ3Honor(String honor) {
		q3Honor = honor;
	}

	public int getQ3AchievementGPA() {
		return q3AchievementGPA;
	}

	public void setQ3AchievementGPA(int achievementGPA) {
		q3AchievementGPA = achievementGPA;
	}

	public int getQ3AchievementLowestGrade() {
		return q3AchievementLowestGrade;
	}

	public void setQ3AchievementLowestGrade(int achievementLowestGrade) {
		q3AchievementLowestGrade = achievementLowestGrade;
	}

	public int getQ3Values1GPA() {
		return q3Values1GPA;
	}

	public void setQ3Values1GPA(int values1GPA) {
		q3Values1GPA = values1GPA;
	}

	public int getQ3Values1LowestGrade() {
		return q3Values1LowestGrade;
	}

	public void setQ3Values1LowestGrade(int values1LowestGrade) {
		q3Values1LowestGrade = values1LowestGrade;
	}

	public int getQ3Values2GPA() {
		return q3Values2GPA;
	}

	public void setQ3Values2GPA(int values2GPA) {
		q3Values2GPA = values2GPA;
	}

	public int getQ3Values2LowestGrade() {
		return q3Values2LowestGrade;
	}

	public void setQ3Values2LowestGrade(int values2LowestGrade) {
		q3Values2LowestGrade = values2LowestGrade;
	}

	public String getQ4Honor() {
		return q4Honor;
	}

	public void setQ4Honor(String honor) {
		q4Honor = honor;
	}

	public int getQ4AchievementGPA() {
		return q4AchievementGPA;
	}

	public void setQ4AchievementGPA(int achievementGPA) {
		q4AchievementGPA = achievementGPA;
	}

	public int getQ4AchievementLowestGrade() {
		return q4AchievementLowestGrade;
	}

	public void setQ4AchievementLowestGrade(int achievementLowestGrade) {
		q4AchievementLowestGrade = achievementLowestGrade;
	}

	public int getQ4Values1GPA() {
		return q4Values1GPA;
	}

	public void setQ4Values1GPA(int values1GPA) {
		q4Values1GPA = values1GPA;
	}

	public int getQ4Values1LowestGrade() {
		return q4Values1LowestGrade;
	}

	public void setQ4Values1LowestGrade(int values1LowestGrade) {
		q4Values1LowestGrade = values1LowestGrade;
	}

	public int getQ4Values2GPA() {
		return q4Values2GPA;
	}

	public void setQ4Values2GPA(int values2GPA) {
		q4Values2GPA = values2GPA;
	}

	public int getQ4Values2LowestGrade() {
		return q4Values2LowestGrade;
	}

	public void setQ4Values2LowestGrade(int values2LowestGrade) {
		q4Values2LowestGrade = values2LowestGrade;
	}

	public String getFinalHonor() {
		return finalHonor;
	}

	public void setFinalHonor(String finalHonor) {
		this.finalHonor = finalHonor;
	}

	public int getFinalAchievementGPA() {
		return finalAchievementGPA;
	}

	public void setFinalAchievementGPA(int finalAchievementGPA) {
		this.finalAchievementGPA = finalAchievementGPA;
	}

	public int getFinalAchievementLowestGrade() {
		return finalAchievementLowestGrade;
	}

	public void setFinalAchievementLowestGrade(int finalAchievementLowestGrade) {
		this.finalAchievementLowestGrade = finalAchievementLowestGrade;
	}

	public int getFinalValues1GPA() {
		return finalValues1GPA;
	}

	public void setFinalValues1GPA(int finalValues1GPA) {
		this.finalValues1GPA = finalValues1GPA;
	}

	public int getFinalValues1LowestGrade() {
		return finalValues1LowestGrade;
	}

	public void setFinalValues1LowestGrade(int finalValues1LowestGrade) {
		this.finalValues1LowestGrade = finalValues1LowestGrade;
	}

	public int getFinalValues2GPA() {
		return finalValues2GPA;
	}

	public void setFinalValues2GPA(int finalValues2GPA) {
		this.finalValues2GPA = finalValues2GPA;
	}

	public int getFinalValues2LowestGrade() {
		return finalValues2LowestGrade;
	}

	public void setFinalValues2LowestGrade(int finalValues2LowestGrade) {
		this.finalValues2LowestGrade = finalValues2LowestGrade;
	}
    
	@Override
	public void setupIndex() {
		runIndex(1, "studentId","gradeLevel");
		runUniqueIndex(2, "studentId","gradeLevel");
	}
}
