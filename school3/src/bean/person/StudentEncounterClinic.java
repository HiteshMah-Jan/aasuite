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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentEncounter")
@UITemplate(criteriaSearch={"studentId"}, columnSearch={"studentName","actionTaken"}, gridCount=4, title="Student Clinic Encounter")
@Displays({
        @Display(name="dateEncounter", gridFieldWidth=3),
        @Display(name="studentId", linktoBean=Student.class,label="Student", type="PopSearch", gridFieldWidth=3, width=-1),
        @Display(name="actionTaken", gridFieldWidth=3,type="Combo", sqlCombo="SELECT a FROM ActionTaken a WHERE a.code LIKE 'CLINIC%'",width=-1),
        @Display(name="reportedTo", gridFieldWidth=3,width=-1),
        @Display(name="dateStart"),
        @Display(name="dateEnd"),
        @Display(name="results", type = "TextArea2", gridFieldWidth = 3, width = -1, height = 100)
})
@DiscriminatorValue("CLINIC")
public class StudentEncounterClinic extends StudentEncounter implements Serializable {
    public static void main(String[] args) {
        view(StudentEncounterClinic.class);
    }
}
