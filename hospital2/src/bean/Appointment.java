/*
 * Appointment.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import service.util.AbstractIBean;
import service.util.ChartBean;
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.DateUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Appointment")
@UITemplate(showChart=true, template = TemplateTabPage.class, gridCount=6, 
		columnSearch = {"appointmentType","appointmentDate","patient","physician"},
	criteriaSearch = {"appointmentType","appointmentDate","patientId","physicianId"}
)
@Displays({
        //@Display(name="appointmentId"),
        @Display(name="appointmentType", type="Combo", modelCombo={"CHECK UP", "VACCINE"}),
        @Display(name="appointmentDate",gridFieldWidth=3),
//        @Display(name="amount",width=70),
       
        @Display(name="patientId", linktoBean=Patient.class, label="Patient", gridFieldWidth=5, width=-1, type="PopSearch"),
        @Display(name="physicianId", linktoBean=Physician.class, label="Physician", gridFieldWidth=5, width=-1, type = "PopSearch"),
        @Display(name="reason",gridFieldWidth=5,width=-1,type="TextArea")
        //@Display(name="patientName")
})
public class Appointment extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "appointmentType", length = 30)
    public String appointmentType;
    @Column(name = "amount", length = 10)
    public double amount;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "patient")
    public String patient;
    @Column(name = "physician")
    public String physician;
    @Column(name = "appointmentDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date appointmentDate;
    @Column(name = "reason", length = 150)
    public String reason;
    
    public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public java.util.Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(java.util.Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
	public void save() {
        if (physicianId>0) {
	        Person p = (Person) extractPerson(physicianId);
	        if (p != null) {
	            physician = p.toString();
	        }
        }
        if (patientId>0) {
        	Person p = (Person) extractPerson(patientId);
	        if (p != null) {
	            patient = p.toString();
	        }
        }
		super.save();
	}

	@Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativeBarInstance(this,"Total Incoming Appointments","SELECT "+DateUtil.getSQLDayName("a.appointmentDate")+",a.appointmentType, COUNT(a.seq) FROM Appointment a WHERE a.appointmentDate BETWEEN '"+DateUtil.formatDateToSql(new Date())+"' AND '"+DateUtil.formatDateToSql(new Date(),7)+"' GROUP BY "+DateUtil.getSQLDayName("a.appointmentDate")+",a.appointmentType"));
        vec.add(ChartBean.getNativePieInstance(this,"Total Appointment Types","SELECT a.appointmentType, COUNT(a.seq) FROM Appointment a WHERE a.appointmentDate>'"+DateUtil.formatDateToSql(new Date())+"' GROUP BY a.appointmentType"));
        return vec;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
