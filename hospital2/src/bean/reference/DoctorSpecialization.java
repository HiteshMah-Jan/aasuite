/*
 * DoctorSpecialization.java
 * 
 * Created on Oct 26, 2007, 9:34:50 PM
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
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "DoctorSpecialization")
@UITemplate(template=TemplateSinglePage.class, gridCount = 4, columnSearch = {"specialization", "department", "remarks"})
@Displays({
        @Display(name="specialization"),
        @Display(name="department", type = "Combo", sqlCombo = "SELECT a FROM Department a"),
        @Display(name="remarks")
})
public class DoctorSpecialization extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "specialization", nullable = false, length = 50)
    public String specialization;
    @Column(name = "department", nullable = false, length = 30)
    public String department;
    @Column(name = "remarks")
    public String remarks;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
