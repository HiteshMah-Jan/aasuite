/*
 * Blood.java
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
import template.Reports;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Blood")
@UITemplate(template=TemplateSinglePage.class, gridCount=2,columnSearch = {"bloodType"})
@Displays({
        @Display(name="bloodType"),
        @Display(name="remarks")
})
          
public class Blood extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "bloodType", nullable = false, length = 3)
    public String bloodType;
    @Column(name = "remarks", length = 100)
    public String remarks;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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
