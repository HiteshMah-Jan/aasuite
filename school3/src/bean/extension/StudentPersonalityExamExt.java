/*
 * StudentGuidanceEncounter.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.person.StudentPersonalityExam;
import bean.*;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.Reports;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template=template.screen.TemplateSearchOnly.class, canSave=false, criteriaSearch={"studentId","examType","examDate"}, 
columnSearch={"studentName","examType","percentage","rawScore","examDate"}, gridCount=8, title="Student Personality Exam")
@Displays({
        @Display(name="studentId", linktoBean=Student.class,label="Student", type="PopSearch", gridFieldWidth=5, width=-1),
        @Display(name="examType",type="Label"),
        @Display(name="percentage",type="Label"),
        @Display(name="rawScore",type="Label"),
        @Display(name="examDate",type="Label"),
        @Display(name="results", type = "Label", gridFieldWidth = 7, width = -1, height = 100)
})
    @Reports({
   
    @template.Report(reportFile="AllStudentPersonalityExam", reportTitle="All Personality Exam ", reportSql="${seq}"),
    @template.Report(reportFile="StudentPersonalityExam", reportTitle="Student Personality Exam ", reportSql="${studentId}")
    
})
public class StudentPersonalityExamExt extends StudentPersonalityExam {
}
