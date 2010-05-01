/*
 * PatientDoctorRound.java
 *
 * Created on Nov 5, 2007, 9:18:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientDoctorRound")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"room", "roundDate", "roundTime", "severity"})
@Displays({
        @Display(name="room",gridFieldWidth=3,width=500),
        @Display(name="roundDate"),
        @Display(name="roundTime", type="Time"),
        @Display(name="severity", type="Combo", modelCombo = {"NEED ATTENTION", "SEVERE"}, gridFieldWidth=3, width=-1),
        @Display(name="notes", type="TextArea", gridFieldWidth=3, width=-1)
})
public class PatientDoctorRound extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "physicianId", nullable = false)
    public int physicianId;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "roundDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date roundDate;
    @Column(name = "roundTime")
    public String roundTime;
    @Column(name = "room")
    public String room;
    @Column(name = "severity")
    public String severity;
    @Column(name = "notes", length=4000)
    public String notes;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public Date getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(Date roundDate) {
        this.roundDate = roundDate;
    }

    public String getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(String roundTime) {
        this.roundTime = roundTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }


    public String getPatientName() {
        Patient patient = (Patient) selectFirstCache(Patient.class, patientId);
        if (patient == null) {
            return "";
        }
        return patient.getLastName() + ", " + patient.getFirstName();
    }

    public String getPhysicianName() {
        Physician physician = (Physician) selectFirstCache(Physician.class, physicianId);
        if (physician == null) {
            return "";
        }
        return physician.getLastName() + ", " + physician.getFirstName();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
