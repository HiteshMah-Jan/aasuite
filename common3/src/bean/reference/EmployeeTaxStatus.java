/*
 * Product.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "EmployeeTaxStatus")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 2,
    columnSearch = {"code","description"})
@Displays({
        @Display(name="code",width=200),
        @Display(name="description",width=200)
})
public class EmployeeTaxStatus extends AbstractIBean {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "description", nullable = false)
    public String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "description");
    }

    public static final String ANY = "ANY";
}
