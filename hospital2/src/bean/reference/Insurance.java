/*
 * Insurance.java
 * 
 * Created on Oct 26, 2007, 9:34:48 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Insurance")
@UITemplate(gridCount = 4, columnSearch = {"insuranceName", "insuranceType", "insuranceCompany"})
@Displays({
        @Display(name="insuranceName"),
        @Display(name="insuranceType"),
        @Display(name="insuranceCompany"),
        @Display(name="coverage"),
        @Display(name="remarks")
})
public class Insurance extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "insuranceName", nullable = false, length = 50)
    public String insuranceName;
    @Column(name = "insuranceType", nullable = false, length = 50)
    public String insuranceType;
    @Column(name = "insuranceCompany", nullable = false, length = 150)
    public String insuranceCompany;
    @Column(name = "coverage", length = 150)
    public String coverage;
    @Column(name = "remarks", length = 150)
    public String remarks;

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

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
