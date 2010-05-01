/*
 * PatientImmunization.java
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/* @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientImmunization")
@UITemplate(template = TemplateSinglePage.class, gridCount=4, columnSearch = {"immunizationDate", "vaccineType", "physicianId"})
@Displays({
        @Display(name="immunizationDate",gridFieldWidth=3),
        @Display(name="vaccineType", type="Combo", sqlCombo="SELECT a FROM VaccineType a",gridFieldWidth=3,width=350),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},label="Physician",gridFieldWidth=3,width=-1),
        @Display(name="hospitalClinic",gridFieldWidth=3,width=-1),
        @Display(name="otherPhysician",gridFieldWidth=3,width=-1),
        @Display(name="remarks",type="TextArea",gridFieldWidth=3,width=-1)
        //@Display(name="patientId", type="Combo", sqlCombo="SELECT v FROM Patient v")
})
public class PatientImmunization extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "vaccineType", nullable = false, length = 50)
    public String vaccineType;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "immunization_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date immunizationDate;
    @Column(name = "hospitalClinic", length = 150)
    public String hospitalClinic;
    @Column(name = "otherPhysician", length = 150)
    public String otherPhysician;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "patientId")
    public int patientId;

    public String getHospitalClinic() {
        return hospitalClinic;
    }

    public void setHospitalClinic(String hospitalClinic) {
        this.hospitalClinic = hospitalClinic;
    }

    public Date getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(Date immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public String getOtherPhysician() {
        return otherPhysician;
    }

    public void setOtherPhysician(String otherPhysician) {
        this.otherPhysician = otherPhysician;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }


    public String getPhysicianName() {
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
