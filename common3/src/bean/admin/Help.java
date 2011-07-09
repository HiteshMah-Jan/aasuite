/*
 * Help.java
 * 
 * Created on Oct 11, 2007, 2:55:40 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

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
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Help")
@UITemplate(template = TemplateDefault.class, columnSearch = {"name", "used_class", "context"})
@Displays({
        @Display(name="name"),
        @Display(name="usedClass"),
        @Display(name="context")
})
public class Help extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "used_class", nullable = false, length = 150)
    public String usedClass;
    @Column(name = "context", length=4000)
    public String context;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsedClass() {
        return usedClass;
    }

    public void setUsedClass(String usedClass) {
        this.usedClass = usedClass;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
