/*
 * PatientLaboratory.java
 *
 * Created on Oct 30, 2007, 8:48:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientPregnancyHistory")
@UITemplate(template=TemplateSinglePage.class, gridCount = 8, 
    columnSearch = {"scheduleDate","ageOfGestation","expectedDeliveryDate","bloodPressure","weight","abdominalCircumference","fetalHeartBeat","nextAppointmentDate"})
@Displays({
    @Display(name = "scheduleDate"),
    @Display(name = "ageOfGestation"),
    @Display(name = "expectedDeliveryDate"),    
    @Display(name = "bloodPressure"),
    
    @Display(name = "weight"),
    @Display(name = "abdominalCircumference"),    
    @Display(name = "fetalHeartBeat"),
    @Display(name = "nextAppointmentDate")
})
public class PatientPregnancyHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "childCount", nullable = false)
    public int childCount;
    @Column(name = "childName")
    public String childName;
    @Column(name = "scheduleDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date scheduleDate;    
    @Column(name = "ageOfGestation", length = 200)
    public String ageOfGestation;
    @Column(name = "expectedDeliveryDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date expectedDeliveryDate;
    @Column(name = "bloodPressure")
    public String bloodPressure;
    @Column(name = "weight")
    public double weight;
    @Column(name = "abdominalCircumference")
    public double abdominalCircumference;
    @Column(name = "fetalHeartBeat")
    public int fetalHeartBeat;
    @Column(name = "remarks", length = 200)
    public String remarks;
    @Column(name = "instruction", length = 200)
    public String instruction;
    @Column(name = "labTest", length = 200)
    public String labTest;
    @Column(name = "supplements", length = 200)
    public String supplements;
    @Column(name = "others", length = 200)
    public String others;
    @Column(name = "nextAppointmentDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date nextAppointmentDate;    
    @Column(name = "physicianId")
    public int physicianId;    
    @Column(name = "amount")
    public double amount;

    public double getAbdominalCircumference() {
        return abdominalCircumference;
    }

    public void setAbdominalCircumference(double abdominalCircumference) {
        this.abdominalCircumference = abdominalCircumference;
    }

    public String getAgeOfGestation() {
        return ageOfGestation;
    }

    public void setAgeOfGestation(String ageOfGestation) {
        this.ageOfGestation = ageOfGestation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public int getFetalHeartBeat() {
        return fetalHeartBeat;
    }

    public void setFetalHeartBeat(int fetalHeartBeat) {
        this.fetalHeartBeat = fetalHeartBeat;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getLabTest() {
        return labTest;
    }

    public void setLabTest(String labTest) {
        this.labTest = labTest;
    }

    public Date getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(Date nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSupplements() {
        return supplements;
    }

    public void setSupplements(String supplements) {
        this.supplements = supplements;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
