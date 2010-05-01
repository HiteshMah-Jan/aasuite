/*
 * PatientMedication.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.*;
import bean.reference.Drug;
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
@Table(name = "PatientMedication")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"patientId", "patientMedicationId", "remarks"})
@Displays({
        @Display(name="drugName", type = "PopSearch", linktoBean=Drug.class, linktoColumns={"code","name"}),
        //@Display(name="patientId", type = "PopSearch", linktoBean=Patient.class, linktoColumns={"lastName","firstName"}),
        @Display(name="dose"),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},gridFieldWidth=3,width=-1),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="instruction",gridFieldWidth=3,width=-1),
        @Display(name="remarks",gridFieldWidth=3,width=-1)
})
public class PatientMedication extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "drugName", nullable = false, length = 30)
    public String drugName;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "dose", length = 30)
    public String dose;
    @Column(name = "physicianId", nullable = false)
    public int physicianId;
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "instruction", length = 150)
    public String instruction;
    @Column(name = "remarks", length = 150)
    public String remarks;

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public String getPhysicianName() {
        Physician physician = (Physician) selectFirstCache(Physician.class, physicianId);
        if (physician == null) {
            return "";
        }
        return physician.getLastName() + ", " + physician.getFirstName();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
