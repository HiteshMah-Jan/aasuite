/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "PatientTransaction")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"patientId", "physicianId", "patientType"})
@DiscriminatorColumn(name = "patientType", discriminatorType = DiscriminatorType.STRING)
@Displays({
        @Display(name="seq"),
        @Display(name="patientId"),
        @Display(name="physicianId"),
        @Display(name="totalFee"),
        @Display(name="opdFee"),
        @Display(name="otherFee"),
        @Display(name="department"),
        @Display(name="medicineAllergy"),
        @Display(name="foodAllergy"),
        @Display(name="consultDate"),
        @Display(name="consultTime"),
        @Display(name="nextAppointmentDate"),
        @Display(name="recommendation"),
        @Display(name="hematologyRequest"),
        @Display(name="urinalysisRequest"),
        @Display(name="cctaRequest"),
        @Display(name="ultrasoundRequest"),
        @Display(name="patientType"),
        @Display(name="isAdmit")
})
public class PatientTransaction extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false, length = 30)
    public Integer seq;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "physicianId", nullable = false)
    public int physicianId;
    @Column(name = "totalFee")
    public double totalFee;
    @Column(name = "opdFee")
    public double opdFee;
    @Column(name = "otherFee")
    public double otherFee;
    @Column(name = "department")
    public String department;
    @Column(name = "medicineAllergy")
    public String medicineAllergy;
    @Column(name = "foodAllergy")
    public String foodAllergy;
    @Column(name = "consultDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date consultDate;
    @Column(name = "consultTime")
    public String consultTime;
    @Column(name = "nextAppointmentDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date nextAppointmentDate;
    @Column(name = "recommendation", length = 4000)
    public String recommendation;
    @Column(name = "hematologyRequest")
    public boolean hematologyRequest;
    @Column(name = "urinalysisRequest")
    public boolean urinalysisRequest;
    @Column(name = "cctaRequest")
    public boolean cctaRequest;
    @Column(name = "ultrasoundRequest")
    public boolean ultrasoundRequest;
    @Column(name = "patientType")
    public String patientType;
    @Column(name = "isAdmit")
    public boolean isAdmit;

    public PatientTransaction() {
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        int oldPatientId = this.patientId;
        this.patientId = patientId;
        changeSupport.firePropertyChange("patientId", oldPatientId, patientId);
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        int oldPhysicianId = this.physicianId;
        this.physicianId = physicianId;
        changeSupport.firePropertyChange("physicianId", oldPhysicianId, physicianId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seq != null ? seq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OutPatient)) {
            return false;
        }
        OutPatient other = (OutPatient) object;
        if (this.seq != other.seq && (this.seq == null || !this.seq.equals(other.seq))) {
            return false;
        }
        return true;
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

    public java.lang.String getMedicineAllergy() {
        return medicineAllergy;
    }

    public void setMedicineAllergy(java.lang.String medicineAllergy) {
        this.medicineAllergy = medicineAllergy;
    }

    public java.lang.String getFoodAllergy() {
        return foodAllergy;
    }

    public void setFoodAllergy(java.lang.String foodAllergy) {
        this.foodAllergy = foodAllergy;
    }

    public java.util.Date getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(java.util.Date nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }

    public java.util.Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(java.util.Date consultDate) {
        this.consultDate = consultDate;
    }

    public java.lang.String getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(java.lang.String consultTime) {
        this.consultTime = consultTime;
    }

    public java.lang.String getDepartment() {
        return department;
    }

    public void setDepartment(java.lang.String department) {
        this.department = department;
    }

    public boolean getHematologyRequest() {
        return hematologyRequest;
    }

    public void setHematologyRequest(boolean hematologyRequest) {
        this.hematologyRequest = hematologyRequest;
    }

    public boolean getUrinalysisRequest() {
        return urinalysisRequest;
    }

    public void setUrinalysisRequest(boolean urinalysisRequest) {
        this.urinalysisRequest = urinalysisRequest;
    }

    public boolean getCctaRequest() {
        return cctaRequest;
    }

    public void setCctaRequest(boolean cctaRequest) {
        this.cctaRequest = cctaRequest;
    }

    public boolean getUltrasoundRequest() {
        return ultrasoundRequest;
    }

    public void setUltrasoundRequest(boolean ultrasoundRequest) {
        this.ultrasoundRequest = ultrasoundRequest;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public java.lang.String getPatientType() {
        return patientType;
    }

    public void setPatientType(java.lang.String patientType) {
        this.patientType = patientType;
    }

    public double getOpdFee() {
        return opdFee;
    }

    public void setOpdFee(double opdFee) {
        this.opdFee = opdFee;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public boolean getIsAdmit() {
        return isAdmit;
    }

    public void setIsAdmit(boolean isAdmit) {
        this.isAdmit = isAdmit;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
