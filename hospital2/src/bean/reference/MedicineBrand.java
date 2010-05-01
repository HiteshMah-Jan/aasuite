/*
 * MedicineBrand.java
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
@Table(name = "MedicineBrand")
@UITemplate(template=TemplateSinglePage.class, gridCount = 4, columnSearch = {"medicineBrand", "description"})
@Displays({
        @Display(name="medicineBrand"),
        @Display(name="description")
})
public class MedicineBrand extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "medicineBrand", nullable = false, length = 50)
    public String medicineBrand;
    @Column(name = "description", length = 150)
    public String description;

    public String getMedicineBrand() {
        return medicineBrand;
    }

    public void setMedicineBrand(String medicineBrand) {
        this.medicineBrand = medicineBrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
