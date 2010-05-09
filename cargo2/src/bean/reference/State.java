/*
 * State.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
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
@Table(name = "State")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"code","name"})
@Displays({
    @Display(name = "code", width=60),
    @Display(name = "name", gridFieldWidth=5, width=-1),
    @Display(name = "country", type="PopSearch", linktoBean=Country.class),
    @Display(name = "active")
})
public class State extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "code", nullable = false, length = 2)
    public String code;
    @Column(name = "name", nullable = false, length = 150)
    public String name;
    @Column(name = "country", nullable = false, length = 150)
    public String country;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name","country");
    }
}
