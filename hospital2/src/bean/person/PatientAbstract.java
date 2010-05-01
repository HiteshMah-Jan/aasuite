/*
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
 * * @author Charliemagne
 */

@Entity
@Table(name = "PatientAbstract")
@UITemplate(template=TemplateSinglePage.class, gridCount = 4, columnSearch = {"patient","abstractDate"} )

@Displays({
        //@Display(name="seq"),
        
    @Display(name = "patient", linktoBean = Patient.class, label = "Patient", gridFieldWidth = 3, width = 400, type = "PopSearch", linktoColumns = {"lastName", "firstName"}),
    @Display(name = "physician", type = "PopSearch", linktoBean = Physician.class, linktoColumns = {"lastName", "firstName"}, gridFieldWidth = 3, width = -1),
    @Display(name = "abstractDate"),
    @Display(name = "abstractType"),
    
    @Display(name = "chiefComplaint",gridFieldWidth=3,width=-1),
    @Display(name = "familyHistory",gridFieldWidth=3,width=-1),
    @Display(name = "previousIllnessHistory",label="Previous Illness & History",gridFieldWidth=3,width=-1),
    @Display(name = "presentIllness",gridFieldWidth=3,width=-1),
    @Display(name = "physicalFindings", gridFieldWidth = 3, width = -1),
    @Display(name = "laboratoryFindings", gridFieldWidth = 3, width = -1),
    @Display(name = "observation",label="Important Observation", gridFieldWidth = 3, width = -1),
    @Display(name = "diagnosis",label="Working Diagnosis", gridFieldWidth = 3, width = -1),
    @Display(name = "treatments",label="Treatments/Medication", gridFieldWidth = 3, width = -1),
    @Display(name = "remarks", gridFieldWidth = 3, width = -1)

})
public class PatientAbstract extends AbstractIBean implements Serializable {
    
    public static void main(String[] args) {
		view(PatientAbstract.class);
	}
   
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "patient")
    public String patient;
    @Column(name = "physician")
    public String physician;
    @Column(name = "abstractDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date abstractDate;
    @Column(name = "physicalFindings")
    public String physicalFindings;
    @Column(name = "laboratoryFindings")
    public String laboratoryFindings;
    @Column(name = "observation")
    public String observation;
    @Column(name = "treatments")
    public String treatments;
    @Column(name = "abstractType")
    public String abstractType;
    
    @Column(name = "chiefComplaint")
    public String chiefComplaint;
    @Column(name = "familyHistory")
    public String familyHistory;
    @Column(name = "previousIllnessHistory")
    public String previousIllnessHistory;
    @Column(name = "presentIllness")
    public String presentIllness;
    @Column(name = "diagnosis")
    public String diagnosis;
    @Column(name = "remarks")
    public String remarks;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getAbstractDate() {
        return abstractDate;
    }

    public void setAbstractDate(Date abstractDate) {
        this.abstractDate = abstractDate;
    }

    public String getAbstractType() {
        return abstractType;
    }

    public void setAbstractType(String abstractType) {
        this.abstractType = abstractType;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getLaboratoryFindings() {
        return laboratoryFindings;
    }

    public void setLaboratoryFindings(String laboratoryFindings) {
        this.laboratoryFindings = laboratoryFindings;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPhysicalFindings() {
        return physicalFindings;
    }

    public void setPhysicalFindings(String physicalFindings) {
        this.physicalFindings = physicalFindings;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getPreviousIllnessHistory() {
        return previousIllnessHistory;
    }

    public void setPreviousIllnessHistory(String previousIllnessHistory) {
        this.previousIllnessHistory = previousIllnessHistory;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    
    

    @Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
    
}
