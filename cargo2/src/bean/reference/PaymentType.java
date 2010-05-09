/*
 * PaymentType.java
 * 
 * Created on Sep 30, 2007, 8:02:12 PM
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
@Table(name = "PaymentType")
@UITemplate(template = TemplateSinglePage.class,
    columnSearch = {"code", "name", "description"})
@Displays({
    @Display(name = "code"),
    @Display(name = "name", width = 250),
    @Display(name = "description", gridFieldWidth=3, width=-1)
})
public class PaymentType extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "code", nullable = false, length=3)
    public String code;
    @Column(name = "name", length=50)
    public String name;
    @Column(name = "description", length=150)
    public String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name");
    }
}
