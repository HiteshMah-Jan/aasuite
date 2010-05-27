/*
 * StudentGuidanceEncounter.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.Reports;
import util.BeanUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentPersonalityExam")
@UITemplate(criteriaSearch={"studentId","examType","examDate"}, columnSearch={"studentName","examDate","examType","percentage","rawScore"}, gridCount=6, title="Student Personality Exam")
@Displays({
        @Display(name="studentId", linktoBean=Student.class,label="Student", type="PopSearch", gridFieldWidth=5, width=-1),
        @Display(name="examType",type="Combo", modelCombo={"IQ","PERSONALITY","DIAGNOSTIC","ACHIEVEMENT","APTITUDE","CAREER"},gridFieldWidth=5,width=-1),
        @Display(name="percentage"),
        @Display(name="rawScore"),
        @Display(name="examDate"),
        @Display(name="results", type = "TextArea2", gridFieldWidth = 5, width = -1, height = 150)
})
    @Reports({
   
    @template.Report(reportFile="AllStudentPersonalityExam", reportTitle="All Personality Exam ", reportSql="${seq}"),
    @template.Report(reportFile="StudentPersonalityExam", reportTitle="Student Personality Exam ", reportSql="${studentId}")
    
})
public class StudentPersonalityExam extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "examDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date examDate;
    @Column(name = "examType")
    public String examType;
    @Column(name = "percentage")
    public double percentage;
    @Column(name = "rawScore")
    public double rawScore;
    @Column(name = "results", length=4000)
    public String results;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "examType");
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getRawScore() {
        return rawScore;
    }

    public void setRawScore(double rawScore) {
        this.rawScore = rawScore;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
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
        Student stud = (Student) firstRecord("SELECT a FROM Student a WHERE a.personId=",studentId);
        if (stud==null) return "";
        return BeanUtil.concat(stud.getLastName(),", ",stud.getFirstName());
    }

    public static void main(String[] args) {
        view(StudentPersonalityExam.class);
    }
}
