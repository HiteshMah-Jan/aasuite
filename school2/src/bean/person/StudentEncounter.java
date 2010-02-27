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
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentEncounter")
@UITemplate(columnSearch={"offense","actionTaken"}, gridCount=4, title="Student Encounter")
@Displays({
        @Display(name="dateEncounter"),
        @Display(name="isMajorOffense",type = "CheckBox"),
        @Display(name="studentId",label="Student", type = "PopSearch", linktoBean=Student.class, gridFieldWidth=3,width=280),
        @Display(name="offense",type="PopSearch", linktoBean=Offense.class,gridFieldWidth=3,width=280),
        @Display(name="actionTaken", gridFieldWidth=3,type="Combo", sqlCombo="SELECT a FROM ActionTaken a",width=280),
        @Display(name="reportedTo", gridFieldWidth=3,width=280),
        @Display(name="dateStart"),
        @Display(name="dateEnd"),
        @Display(name="results", type = "TextArea2", gridFieldWidth = 3, width = -1, height = 100)
})
@DiscriminatorColumn(name = "encounterType", discriminatorType = DiscriminatorType.STRING)
public class StudentEncounter extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "encounterType")
    public String encounterType;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "dateEncounter", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dateEncounter;
    @Column(name = "isMajorOffense")
    public boolean isMajorOffense;
    @Column(name = "offense")
    public String offense;

    @Column(name = "reportedTo")
    public String reportedTo; //parents or guardians
    @Column(name = "actionTaken")
    public String actionTaken; //can also be considered as penalty
    @Column(name = "dateStart", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dateStart;
    @Column(name = "dateEnd", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date dateEnd;
    @Column(name = "results", length=4000)
    public String results;
    @Column(name = "studentName")
    public String studentName;

     @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "encounterType","offense", "actionTaken");
    }


    public int getRecordCount() {
        return 0;
    }

    public int getMajorOffenseCount() {
        return 0;
    }

    public int getMinorOffenseCount() {
        return 0;
    }
    
    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Date getDateEncounter() {
        return dateEncounter;
    }

    public void setDateEncounter(Date dateEncounter) {
        this.dateEncounter = dateEncounter;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public boolean isIsMajorOffense() {
        return isMajorOffense;
    }

    public void setIsMajorOffense(boolean isMajorOffense) {
        this.isMajorOffense = isMajorOffense;
    }

    public String getOffense() {
        return offense;
    }

    public void setOffense(String offense) {
        this.offense = offense;
    }

    public String getReportedTo() {
        return reportedTo;
    }

    public void setReportedTo(String reportedTo) {
        this.reportedTo = reportedTo;
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

    @Override
	public void save() {
        Student stud = (Student) firstRecord("SELECT a FROM Student a WHERE a.personId="+studentId);
        if (stud!=null) studentName = stud.toString();
		super.save();
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentName() {
		return studentName;
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "encounterType");
	}
}
