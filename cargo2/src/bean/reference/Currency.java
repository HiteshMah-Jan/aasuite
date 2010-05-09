/*
 * Currency.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
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
import template.screen.TemplateSinglePage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Currency")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6,
    columnSearch = {"code", "name", "scale"}, showChart = true)
@Displays({
    @Display(name = "code", width = 30),
    @Display(name = "scale", width = 30),
    @Display(name = "conversion", width = 30),
    @Display(name = "name", width=-1, gridFieldWidth=5)
})
public class Currency extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    

    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "scale", nullable = false)
    public int scale;
    @Column(name = "conversion", nullable = false)
    public double conversion;
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

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","name");
    }
}
