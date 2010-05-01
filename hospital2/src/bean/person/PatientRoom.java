/*
 * RoomManagement.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
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

import service.util.AbstractIBean;
import util.DBClient;
import util.DateUtil;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "RoomManagement")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
		columnSearch = {"patient", "room", "bed", "rate", "startDate", "startTime", "endDate", "endTime"},
		criteriaSearch = {"patientId", "room", "bed"}
)
@Displays({
        @Display(name="room"),
        @Display(name="bed"),
        @Display(name="rate"),
        @Display(name="patientId", label = "Patient", type = "PopSearch", linktoBean=Patient.class),
        @Display(name="startDate"),
        @Display(name="startTime", type="Time"),
        @Display(name="endDate"),
        @Display(name="endTime", type="Time")
})
public class PatientRoom extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "roomBed")
    public String roomBed;
    @Column(name = "room")
    public String room;
    @Column(name = "bed")
    public String bed;
    @Column(name = "rate")
    public double rate;
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
    @Column(name = "patient")
    public String patient;
    @Column(name = "countDays")
    public double countDays;
    @Column(name = "computedBill")
    public double computedBill;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getRoomBed() {
		return roomBed;
	}

	public void setRoomBed(String roomBed) {
		this.roomBed = roomBed;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public double getCountDays() {
		return countDays;
	}

	public void setCountDays(double countDays) {
		this.countDays = countDays;
	}

	public double getComputedBill() {
		return computedBill;
	}

	public void setComputedBill(double computedBill) {
		this.computedBill = computedBill;
	}

	@Override
	public void save() {
//		calculate countDays
		countDays = DateUtil.countDaySpan(startDate, endDate);
		roomBed = room+"-"+bed;
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
		return buildSearch(criteria, this._Key());
	}
}
