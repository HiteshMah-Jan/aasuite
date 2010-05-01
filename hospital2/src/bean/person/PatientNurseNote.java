/*
 * PatientNurseNote.java
 * 
 * Created on Nov 5, 2007, 9:18:22 PM
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
@Table(name = "PatientNurseNote")
@UITemplate(template = TemplateTabPage.class, gridCount=4 ,columnSearch = {"seq", "roundDate"})
@Displays({
        @Display(name="nurseId", type = "PopSearch", linktoBean=Nurse.class, linktoColumns={"lastName","firstName"}),
        @Display(name="patientId", type = "PopSearch", linktoBean=Patient.class, linktoColumns={"lastName","firstName"}),
        @Display(name="roundDate"),
        @Display(name="roundTime"),
        @Display(name="severity"),
        @Display(name="bloodPressure"),
        @Display(name="temperature"),
        @Display(name="pulseRate"),
        @Display(name="medicineTaken"),
        @Display(name="weight"),
        @Display(name="height"),
        @Display(name="respiratoryRate"),
        @Display(name="ie"),
        @Display(name="note")
})
public class PatientNurseNote extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "nurseId", nullable = false)
    public int nurseId;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "roundDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date roundDate;
    @Column(name = "roundTime")
    public String roundTime;
    @Column(name = "severity")
    public String severity;
    @Column(name = "bloodPressure")
    public String bloodPressure;
    @Column(name = "temperature")
    public String temperature;
    @Column(name = "pulseRate")
    public String pulseRate;
    @Column(name = "medicineTaken")
    public String medicineTaken;
    @Column(name = "weight")
    public String weight;
    @Column(name = "height")
    public String height;
    @Column(name = "respiratoryRate")
    public String respiratoryRate;
    @Column(name = "ie")
    public String ie;
    @Column(name = "note", length=4000)
    public String note;

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getMedicineTaken() {
        return medicineTaken;
    }

    public void setMedicineTaken(String medicineTaken) {
        this.medicineTaken = medicineTaken;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getPatientName() {
        Patient patient = (Patient) selectFirstCache(Patient.class, patientId);
        if (patient == null) {
            return "";
        }
        return patient.getLastName() + ", " + patient.getFirstName();
    }

    public String getNurseName() {
        Patient nurse = (Patient) selectFirstCache(Patient.class, nurseId);
        if (nurse == null) {
            return "";
        }
        return nurse.getLastName() + ", " + nurse.getFirstName();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
