/*
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
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@Entity
@Table(name = "Branch")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"name", "address"})
@Displays({
        @Display(name="name"),
        @Display(name="address")
})
public class Branch extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "address")
    public String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "name", "address");
    }
}
