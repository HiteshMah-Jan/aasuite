/*
 * LaboratoryTest.java
 * 
 * Created on Oct 28, 2007, 8:57:40 PM
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
@Table(name = "LaboratoryTest")
@UITemplate(template=TemplateSinglePage.class, gridCount = 2, columnSearch = {"laboratoryTest", "description"})
@Displays({
        @Display(name="laboratoryTest"),
        @Display(name="description")
})
public class LaboratoryTest extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "laboratoryTest", nullable = false, length = 100)
    public String laboratoryTest;
    @Column(name = "description", length = 200)
    public String description;

    public String getLaboratoryTest() {
        return laboratoryTest;
    }

    public void setLaboratoryTest(String laboratoryTest) {
        this.laboratoryTest = laboratoryTest;
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
