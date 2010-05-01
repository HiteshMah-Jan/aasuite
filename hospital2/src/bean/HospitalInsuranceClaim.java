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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import bean.reference.Insurance;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "HospitalInsuranceClaim")
@UITemplate(template = TemplateTabSinglePage.class, gridCount=6, 
		columnSearch = {"appointmentType","appointmentDate","patient","physician"},
		criteriaSearch = {"appointmentType","appointmentDate","patientId","physicianId"})
@ChildRecords(value = {@template.ChildRecord(title="Private Claim", entity = PrivateClaim.class, sql = "SELECT a FROM PrivateClaim a WHERE a.opdId=${opdId}", fieldMapping={"opdId","opdId"},template=ChildTemplateListPopupDownButton.class)},
info = {
    
})

@Displays({
	@Display(name = "insuranceName",type = "PopSearch", linktoBean = Insurance.class),
    @Display(name = "patientId",label = "Patient", gridFieldWidth = 3, width = -1, type = "PopSearch", linktoBean = Patient.class, enabled = false),
    @Display(name = "physcianId",type = "PopSearch", linktoBean = Physician.class),
    @Display(name = "membershipType"),
    @Display(name = "idNo"),
    @Display(name = "planType"),
    @Display(name = "expiryDate"),
    @Display(name = "hospitalName")
})
public class HospitalInsuranceClaim extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "opdId", nullable = false)
    public int opdId;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "insuranceName")
    public int insuranceName;
    @Column(name = "physcianId")
    public int physcianId;
    @Column(name = "physcian")
    public String physcian;
    @Column(name = "patient")
    public String patient;

    @Column(name = "membershipType")
    public String membershipType;
    @Column(name = "idNo")
    public String idNo;
    @Column(name = "planType")
    public String planType;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "expiryDate")
    public Date expiryDate;
    @Column(name = "hospitalName")
    public String hospitalName;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getOpdId() {
		return opdId;
	}

	public void setOpdId(int opdId) {
		this.opdId = opdId;
	}

	public int getPatientId() {
		return patientId;
	}


	public int getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(int insuranceName) {
		this.insuranceName = insuranceName;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPhyscianId() {
		return physcianId;
	}

	public void setPhyscianId(int physcianId) {
		this.physcianId = physcianId;
	}

	public String getPhyscian() {
		return physcian;
	}

	public void setPhyscian(String physcian) {
		this.physcian = physcian;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
