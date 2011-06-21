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
import bean.reference.Offense;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.Reports;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentEncounter")
@UITemplate(criteriaSearch={"studentId","offense","actionTaken"}, columnSearch={"studentName", "offense","actionTaken"}, gridCount=4, title="Office of the OSA")
@Displays({
    @Display(name="dateEncounter"),
    @Display(name="isMajorOffense",type = "CheckBox"),
    @Display(name="studentId", linktoBean=Student.class,label="Student", type="PopSearch", gridFieldWidth=3,width=300),
    @Display(name="offense",type="PopSearch", linktoBean=Offense.class,gridFieldWidth=3,width=-1),
    @Display(name="actionTaken",type="Combo", sqlCombo="SELECT a FROM ActionTaken a WHERE a.code NOT LIKE 'CLINIC%'",gridFieldWidth=3,width=-1),
    @Display(name="reportedTo", gridFieldWidth=3,width=-1),
    @Display(name="dateStart"),
    @Display(name="dateEnd"),
    @Display(name="results", type = "TextArea2", gridFieldWidth = 3, width = -1, height = 100)
})
@Reports({
    @template.Report(reportFile="OSAStudentRecord", reportTitle="Student Conduct Record", reportSql="${studentId}")
})
@DiscriminatorValue("GUIDANCE")
public class StudentEncounterGuidance extends StudentEncounter implements Serializable {
    public static void main(String[] args) {
        view(StudentEncounterGuidance.class);
    }
}
