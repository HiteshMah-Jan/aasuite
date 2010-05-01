/*
 * Facility.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
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
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "InPatientDailyService")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"admissionId", "serviceDate", "remarks"})
@Displays({
        @Display(name="seq"),
        @Display(name="admissionId"),
        @Display(name="serviceDate"),
        @Display(name="serviceType"),
        @Display(name="remarks"),
        @Display(name="medicine"),
        @Display(name="medicineCount"),
        @Display(name="laboratory"),
        @Display(name="room"),
        @Display(name="medicineFee"),
        @Display(name="laboratoryFee"),
        @Display(name="serviceFee"),
        @Display(name="otherFee"),
        @Display(name="totalFee"),
        @Display(name="oneTimeOnly")
})
public class InPatientDailyService extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "admissionId")
    public int admissionId;
    @Column(name = "serviceDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date serviceDate;
    @Column(name = "serviceType")
    public String serviceType;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "medicine")
    public String medicine;
    @Column(name = "medicineCount")
    public int medicineCount;
    @Column(name = "laboratory")
    public String laboratory;
    @Column(name = "room")
    public String room;
    @Column(name = "medicineFee")
    public double medicineFee;
    @Column(name = "laboratoryFee")
    public double laboratoryFee;
    @Column(name = "serviceFee")
    public double serviceFee;
    @Column(name = "otherFee")
    public double otherFee;
    @Column(name = "totalFee")
    public double totalFee;
    @Column(name = "oneTimeOnly")
    public boolean oneTimeOnly;

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getMedicineCount() {
        return medicineCount;
    }

    public void setMedicineCount(int medicineCount) {
        this.medicineCount = medicineCount;
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

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
        if (!(object instanceof InPatientDailyService)) {
            return false;
        }
        InPatientDailyService other = (InPatientDailyService) object;
        if (this.seq != other.seq && (this.seq == null || !this.seq.equals(other.seq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hospital.bean.InPatientDailyService[seq=" + seq + "]";
    }

    public String _Key() {
        return "seq";
    }

    public java.lang.String getRoom() {
        return room;
    }

    public void setRoom(java.lang.String room) {
        this.room = room;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public void setMedicineFee(double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public double getLaboratoryFee() {
        return laboratoryFee;
    }

    public void setLaboratoryFee(double laboratoryFee) {
        this.laboratoryFee = laboratoryFee;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public void calculateTotal() {
        setTotalFee(medicineFee + otherFee + laboratoryFee);
    }

    public boolean getOneTimeOnly() {
        return oneTimeOnly;
    }

    public void setOneTimeOnly(boolean oneTimeOnly) {
        this.oneTimeOnly = oneTimeOnly;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
