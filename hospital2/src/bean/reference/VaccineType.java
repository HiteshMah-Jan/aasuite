/*
 * VaccineType.java
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
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "VaccineType")
@UITemplate(template=TemplateSinglePage.class, gridCount = 2, columnSearch = {"vaccineType", "description"})
@Displays({
        @Display(name="vaccineType"),
        @Display(name="description")
})
public class VaccineType extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "vaccineType", nullable = false, length = 30)
    public String vaccineType;
    @Column(name = "description", length = 150)
    public String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
