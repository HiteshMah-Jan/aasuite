/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import bean.person.PatientPackageHistory;
import bean.person.PatientMedicalRecord;
import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import util.DBClient;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "TreatmentPackage")
@UITemplate(template=TemplateSinglePage.class, gridCount = 4, columnSearch = {"patientId", "packageDate", "packageType"})
@ChildRecords({
    @ChildRecord(entity = PatientPackageHistory.class, fieldMapping = {"seq", "patientPackageId"}, sql = "SELECT a FROM PatientPackageHistory a WHERE a.patientPackageId=${seq}"),
    @ChildRecord(entity = PatientMedicalRecord.class, fieldMapping = {"seq", "patientId"}, sql = "SELECT a FROM PatientMedicalRecord a WHERE a.patientId=${seq}")
})
@Displays({
        @Display(name="seq", type = "Label"),
        @Display(name="packageDate"),
        @Display(name="patientId", label = "Patient", type = "PopSearch", linktoBean=Patient.class, linktoColumns={"lastName","firstName"}),
        @Display(name="packageType", type = "Combo", modelCombo = {"NORMAL", "NORMAL-PRIVATE"}),
        @Display(name="packageFee"),
        @Display(name="remarks")
})
public class TreatmentPackage extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "packageDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date packageDate;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "packageType")
    public String packageType;
    @Column(name = "packageFee")
    public double packageFee;
    @Column(name = "remarks")
    public String remarks;

    public Date getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(Date packageDate) {
        this.packageDate = packageDate;
    }

    public double getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(double packageFee) {
        this.packageFee = packageFee;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public String getPatient() {
        if (patientId == 0) {
            return "";
        }
        Patient p = (Patient) DBClient.getFirstRecord("SELECT a FROM Patient a WHERE a.personId=" + patientId);
        if (p == null) {
            return "";
        }
        return p.getLastName() + " " + p.getFirstName();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
