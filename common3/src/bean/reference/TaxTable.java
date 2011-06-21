/*
 * Section.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import util.ScriptRunner;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "TaxTable")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "description", "excemption"})
@Displays({
        @Display(name="code"),
        @Display(name="excemption"),
        @Display(name="description")
})
public class TaxTable extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "excemption")
    public double excemption;
    @Column(name = "description")
    public String description;
  
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "description");
    }

    @Override
    public String toString() {
        return code;
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }

    public double getExcemption() {
        return excemption;
    }

    public void setExcemption(double excemption) {
        this.excemption = excemption;
    }
}
