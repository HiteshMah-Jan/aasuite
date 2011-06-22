/*
 * Accounttype.java
 *
 * Created on Nov 25, 2007, 2:55:53 PM
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "ProductCategory")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "description"})
@Displays({
    @Display(name="code", label="Category"),
    @Display(name="description", label="Description")
})
public class ProductCategory extends ReferenceType implements Serializable {
	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code");
    }

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "description")
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
    
}
