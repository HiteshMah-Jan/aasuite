/*
 * PatientAllergy.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.*;
import bean.reference.Allergy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientAllergy")
@UITemplate(template = TemplateSinglePage.class, gridCount=4, columnSearch = {"allergy","medication","dateOfMedication","physicianId"})
@Displays({
       // @Display(name="patientAllergyId"),
        @Display(name="allergy", type = "PopSearch", linktoBean=Allergy.class, linktoColumns={"code","name"},gridFieldWidth=3,width=-1),
        @Display(name="medication",gridFieldWidth=3,width=-1),
        @Display(name="dateOfMedication"),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},label="Attending Physician"),
        @Display(name="remarks",type="TextArea",gridFieldWidth=3,width=-1)
       // @Display(name="patientId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"})
})
public class PatientAllergy extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "patientAllergyId", nullable = false)
    public Integer patientAllergyId;
    @Column(name = "allergy", nullable = false, length = 30)
    public String allergy;
    @Column(name = "medication", nullable = false, length = 50)
    public String medication;
    @Column(name = "dateOfMedication")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateOfMedication;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "patientId")
    public int patientId;

    public PatientAllergy() {
    }

    public PatientAllergy(Integer patientAllergyId) {
        this.patientAllergyId = patientAllergyId;
    }

    public PatientAllergy(Integer patientAllergyId, String allergy, String medication) {
        this.patientAllergyId = patientAllergyId;
        this.allergy = allergy;
        this.medication = medication;
    }

    public Integer getPatientAllergyId() {
        return patientAllergyId;
    }

    public void setPatientAllergyId(Integer patientAllergyId) {
        this.patientAllergyId = patientAllergyId;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientAllergyId != null ? patientAllergyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientAllergy)) {
            return false;
        }
        PatientAllergy other = (PatientAllergy) object;
        if (this.patientAllergyId != other.patientAllergyId && (this.patientAllergyId == null || !this.patientAllergyId.equals(other.patientAllergyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hospital.bean.PatientAllergy[patientAllergyId=" + patientAllergyId + "]";
    }

    public java.util.Date getDateOfMedication() {
        return dateOfMedication;
    }

    public void setDateOfMedication(java.util.Date dateOfMedication) {
        this.dateOfMedication = dateOfMedication;
    }

    public String _Key() {
        return "patientAllergyId";
    }

    public String getPhysician() {
//        if (this.physicianId == 0) {
//            return "";
//        }
//        Person p = (Person) DBClient.getFirstRecord("SELECT a FROM Person a WHERE a.personId="+this.physicianId);
//        return p.getFormattedTitle();
//    }
        if (physicianId == 0) {
            return "";
        }
        Physician physician = (Physician) selectFirstCache(Physician.class, physicianId);
        if (physician == null) {
            return "";
        }
        return physician.getLastName() + ", " + physician.getFirstName() + "   " + physician.getMiddleInitial();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
