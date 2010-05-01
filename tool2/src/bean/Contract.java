/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
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
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Contract")
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Contract",
    gridCount = 2, columnSearch = {"code"})
@Displays({
    @Display(name="code", width=300),
    @Display(name="description", width=300)
})
public class Contract extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code")
    public String code;
    @Column(name = "description", length=4000)
    public String description;

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "code");
	}
	
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
