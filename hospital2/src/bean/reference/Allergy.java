/*
 * Allergy.java
 * 
 * Created on Oct 26, 2007, 9:34:51 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import template.Reports;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Allergy")
@UITemplate(template=TemplateSinglePage.class, gridCount=2, columnSearch={"code", "allergy"})
@Displays({
        @Display(name="code"),
        @Display(name="allergy"),
        @Display(name="allergen"),
        @Display(name="remarks")
})
        @Reports( {
		
		@template.Report(reportFile = "Allergy", reportTitle = "Allergy List", reportSql = "")

})
public class Allergy extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length=30)
    public String code;
    @Column(name = "allergy", nullable = false, length=100)
    public String allergy;
    @Column(name = "allergen", nullable = false, length=100)
    public String allergen;
    @Column(name = "remarks", length=100)
    public String remarks;

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
