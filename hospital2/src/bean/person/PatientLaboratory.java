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
@Table(name = "PatientLaboratory")
@UITemplate(template=TemplateSinglePage.class, gridCount = 4, columnSearch = {"labDate", "laboratoryTest","patient"} ,showImages=true)
@DiscriminatorColumn(name = "laboratoryTest", discriminatorType = DiscriminatorType.STRING)
@Displays({
        //@Display(name="seq"),
        @Display(name="labDate",gridFieldWidth=3),
        @Display(name="patient", linktoBean=Patient.class, label="Patient", gridFieldWidth=3, width=400, type="PopSearch", linktoColumns={"lastName","firstName"}),
        @Display(name="laboratoryTest",type="Combo", sqlCombo = "SELECT a FROM LaboratoryTest a",gridFieldWidth=3,width=-1),
        @Display(name="requestingPhysician", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},gridFieldWidth=3,width=-1),
        //@Display(name="commendation"),
        @Display(name="labresult",label="Result",type="TextArea",gridFieldWidth=3,width=-1)
//        @Display(name="amount"),
//        @Display(name="findings"),
//        @Display(name="impression"),
//        @Display(name="exam"),
//        @Display(name="addendum"),
//        @Display(name="sonographer", type = "Combo", sqlCombo = "SELECT a FROM Physician a"),
//        @Display(name="labinput"),
//        @Display(name="laboutput"),
//        @Display(name="er_a"),
//        @Display(name="er_wic"),
//        @Display(name="bilrubin"),
//        @Display(name="alkPhos"),
//        @Display(name="sgot"),
//        @Display(name="sgpt"),
//        @Display(name="amylase"),
//        @Display(name="bun"),
//        @Display(name="creatinine"),
//        @Display(name="requestDate"),
//        @Display(name="requestTime", type="Time"),
//        @Display(name="finishDate"),
//        @Display(name="opdId")
})
public class PatientLaboratory extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patient")
    public int patient;
    @Column(name = "labDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date labDate;
    @Column(name = "laboratoryTest", length = 30)
    public String laboratoryTest;
    @Column(name = "requestingPhysician")
    public int requestingPhysician;
    @Column(name = "commendation", length = 200)
    public String commendation;
    @Column(name = "labresult", length = 200)
    public String labresult;
    @Column(name = "amount")
    public double amount;
    @Column(name = "findings", length = 4000)
    public String findings;
    @Column(name = "impression", length = 4000)
    public String impression;
    @Column(name = "exam", length = 4000)
    public String exam;
    @Column(name = "addendum", length = 4000)
    public String addendum;
    @Column(name = "sonographer")
    public String sonographer;
    @Column(name = "labinput")
    public String labinput;
    @Column(name = "laboutput")
    public String laboutput;
    @Column(name = "er_a")
    public String er_a;
    @Column(name = "er_wic")
    public String er_wic;
    @Column(name = "bilrubin")
    public String bilrubin;
    @Column(name = "alkPhos")
    public String alkPhos;
    @Column(name = "sgot")
    public String sgot;
    @Column(name = "sgpt")
    public String sgpt;
    @Column(name = "amylase")
    public String amylase;
    @Column(name = "bun")
    public String bun;
    @Column(name = "creatinine")
    public String creatinine;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "requestDate")
    public Date requestDate;
    @Column(name = "requestTime")
    public String requestTime;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "finishDate")
    public Date finishDate;
    @Column(name = "opdId")
    public int opdId;

    @Override
    public String _Table() {
        return "PatientLaboratory";
    }

    public PatientLaboratory() {
    }

    public String getLaboratoryTest() {
        return laboratoryTest;
    }

    public void setLaboratoryTest(String laboratoryTest) {
        this.laboratoryTest = laboratoryTest;
    }

    public String getLabresult() {
        return labresult;
    }

    public void setLabresult(String result) {
        this.labresult = result;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getRequestingPhysician() {
        return requestingPhysician;
    }

    public void setRequestingPhysician(int requestingPhysician) {
        this.requestingPhysician = requestingPhysician;
    }

    public String getCommendation() {
        return commendation;
    }

    public void setCommendation(String commendation) {
        this.commendation = commendation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        if (!(object instanceof PatientLaboratory)) {
            return false;
        }
        PatientLaboratory other = (PatientLaboratory) object;
        if (this.seq != other.seq && (this.seq == null || !this.seq.equals(other.seq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PatientLaboratory[seq=" + seq + "]";
    }

    public String _Key() {
        return "seq";
    }

    public String getPatientName() {
        if (patient == 0) {
            return "";
        }
        Patient patientBean = (Patient) selectFirstCache(Patient.class, patient);
        if (patientBean == null) {
            return "";
        }
        return patientBean.getLastName() + ", " + patientBean.getFirstName() + "   " + patientBean.getMiddleInitial();
    }

    public String getPhysicianName() {
        if (requestingPhysician == 0) {
            return "";
        }
        Physician physician = (Physician) selectFirstCache(Physician.class, requestingPhysician);
        if (physician == null) {
            return "";
        }
        return physician.getLastName() + ", " + physician.getFirstName() + "   " + physician.getMiddleInitial();
    }

    public java.util.Date getLabDate() {
        return labDate;
    }

    public void setLabDate(java.util.Date labDate) {
        this.labDate = labDate;
    }

    public String getPhysician() {
        return getPhysicianName();
    }

    public java.lang.String getSonographer() {
        return sonographer;
    }

    public void setSonographer(java.lang.String sonographer) {
        this.sonographer = sonographer;
    }

    public java.lang.String getLabinput() {
        return labinput;
    }

    public void setLabinput(java.lang.String input) {
        this.labinput = input;
    }

    public java.lang.String getLaboutput() {
        return laboutput;
    }

    public void setLaboutput(java.lang.String output) {
        this.laboutput = output;
    }

    public java.lang.String getEr_a() {
        return er_a;
    }

    public void setEr_a(java.lang.String er_a) {
        this.er_a = er_a;
    }

    public java.lang.String getEr_wic() {
        return er_wic;
    }

    public void setEr_wic(java.lang.String er_wic) {
        this.er_wic = er_wic;
    }

    public java.lang.String getBilrubin() {
        return bilrubin;
    }

    public void setBilrubin(java.lang.String bilrubin) {
        this.bilrubin = bilrubin;
    }

    public java.lang.String getAlkPhos() {
        return alkPhos;
    }

    public void setAlkPhos(java.lang.String alkPhos) {
        this.alkPhos = alkPhos;
    }

    public java.lang.String getSgot() {
        return sgot;
    }

    public void setSgot(java.lang.String sgot) {
        this.sgot = sgot;
    }

    public java.lang.String getSgpt() {
        return sgpt;
    }

    public void setSgpt(java.lang.String sgpt) {
        this.sgpt = sgpt;
    }

    public java.lang.String getAmylase() {
        return amylase;
    }

    public void setAmylase(java.lang.String amylase) {
        this.amylase = amylase;
    }

    public java.lang.String getBun() {
        return bun;
    }

    public void setBun(java.lang.String bun) {
        this.bun = bun;
    }

    public java.lang.String getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(java.lang.String creatinine) {
        this.creatinine = creatinine;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getOpdId() {
        return opdId;
    }

    public void setOpdId(int opdId) {
        this.opdId = opdId;
    }

    public String getAddendum() {
        return addendum;
    }

    public void setAddendum(String addendum) {
        this.addendum = addendum;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
