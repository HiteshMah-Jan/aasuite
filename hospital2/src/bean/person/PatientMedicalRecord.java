/*
 * PatientMedicalRecord.java
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
import bean.reference.Department;
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientMedicalRecord")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"recordDate","physicianId","bill"})
@Displays({
       // @Display(name="patientId", type = "PopSearch", linktoBean=Patient.class, linktoColumns={"lastName","firstName"}),
        @Display(name="recordDate",gridFieldWidth=3),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},gridFieldWidth=3,width=-1,label="Physician"),
        @Display(name="department",type="PopSearch",linktoBean=Department.class,gridFieldWidth=3,width=-1),
//        @Display(name="gp"),
//        @Display(name="lmp"),
//        @Display(name="aog"),
        @Display(name="chiefComplaints",gridFieldWidth=3,width=-1),
        @Display(name="remarks",gridFieldWidth=3,width=-1),
//        @Display(name="appointmentType"),
//        @Display(name="paymentType"),
//        @Display(name="problemLists"),
        @Display(name="diagnostic",gridFieldWidth=3,width=-1),
        @Display(name="medication",gridFieldWidth=3,width=-1),
        @Display(name="bill",width=150),
        @Display(name="status",width=150)
})        
public class PatientMedicalRecord extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patientId")
    public int patientId;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "record_date")
    public Date recordDate;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "gp", length = 50)
    public String gp;
    @Column(name = "lmp", length = 50)
    public String lmp;
    @Column(name = "aog", length = 50)
    public String aog;
    @Column(name = "chiefComplaints", length = 30)
    public String chiefComplaints;
    @Column(name = "remarks", length = 150)
    public String remarks;

    @Column(name = "appointmentType", length = 30)
    public String appointmentType;
    @Column(name = "paymentType", length = 30)
    public String paymentType;
    @Column(name = "problemLists", length = 30)
    public String problemLists;
    @Column(name = "diagnostic", length = 30)
    public String diagnostic;
    @Column(name = "medication", length = 30)
    public String medication;
    @Column(name = "bill")
    public double bill;
    @Column(name = "status", length = 30)
    public String status;
    @Column(name = "department")
    public String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getAog() {
        return aog;
    }

    public void setAog(String aog) {
        this.aog = aog;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getChiefComplaints() {
        return chiefComplaints;
    }

    public void setChiefComplaints(String chiefComplaints) {
        this.chiefComplaints = chiefComplaints;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getGp() {
        return gp;
    }

    public void setGp(String gp) {
        this.gp = gp;
    }

    public String getLmp() {
        return lmp;
    }

    public void setLmp(String lmp) {
        this.lmp = lmp;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getProblemLists() {
        return problemLists;
    }

    public void setProblemLists(String problemLists) {
        this.problemLists = problemLists;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhysician() {
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
