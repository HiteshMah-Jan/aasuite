/*
 * Room.java
 *
 * Created on Dec 2, 2007, 12:43:17 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import bean.Patient;
import bean.Person;
import bean.accounting.OutPatientLineItem;
import bean.extension.InvoiceExt;
import bean.person.PatientRoom;
import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Room")
@DiscriminatorValue(value = "HOSPITAL")
@UITemplate(
		columnSearch={"room", "bed", "type", "rate"}, 
		criteriaSearch={"room", "bed", "type"}, 
		gridCount=8, title="Room", template=TemplateSinglePage.class, canDelete=false)
@Displays({
//        @Display(name="code"),
        @Display(name="room"),
        @Display(name="bed"),
        @Display(name="rate"),
        @Display(name="type", type="Combo", modelCombo={"PRIVATE","SEMI PRIVATE","WARD","OPERATING ROOM","ICU","DELIVERY ROOM","EMERGENCY ROOM","NURSERY","RADIOLOGY","LABORATORY"})
})
@ChildRecords(
		@template.ChildRecord(title="Room Usage and Utilization", entity = PatientRoom.class, sql = "SELECT a FROM PatientRoom a WHERE a.roomBed='${code}'", fieldMapping={"code","roomBed"},template=ChildTemplateListOnly.class)
)
public class RoomHospital extends Room implements Serializable {

    @Column(name = "bed")
    public String bed;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "patient")
    public String patient;
    @Column(name = "startDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "startTime")
    public String startTime;
    @Column(name = "endDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "endTime")
    public String endTime;
    @Column(name = "admissionId")
    public int admissionId;
    
    public int getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		if (this.patientId==0 || this.patientId!=patientId) {
			startDate = new Date();
			startTime = DateUtil.getTime();
		}
		this.patientId = patientId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public void save() {
		code = room+"-"+bed;
    	if (patientId>0) {
    		try {
        		patient = Person.extractObject(patientId).toString();
    		}
    		catch (Exception e) {
    		}
    	}
    	else {
    		patient = "";
    	}
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "room", "bed", "type", "patient");
    }

    @Override
    public boolean cacheClient() {
        return false;
    }
}
