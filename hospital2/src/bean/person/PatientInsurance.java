/*
 * PatientInsurance.java
 * 
 * Created on Oct 26, 2007, 9:34:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "PatientInsurance")
@UITemplate(template = TemplateSinglePage.class,gridCount=4, columnSearch = {"insuranceName","policyNumber" })
@Displays({
        @Display(name="insuranceName",gridFieldWidth=3,width=-1),
        @Display(name="policyNumber",width=150),
        @Display(name="insuranceType",width=150),
        @Display(name="insuranceCompany",width=150),
        @Display(name="coverage",width=150),
        @Display(name="remarks",gridFieldWidth=3,width=-1)
      //  @Display(name="patientId")
})
public class PatientInsurance extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "insuranceName", length = 30)
    public String insuranceName;
    @Column(name = "policyNumber", length = 30)
    public String policyNumber;
    @Column(name = "insuranceType", length = 30)
    public String insuranceType;
    @Column(name = "insuranceCompany", length = 150)
    public String insuranceCompany;
    @Column(name = "coverage", length = 30)
    public String coverage;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "patientId")
    public int patientId;

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
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

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
