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
@Table(name = "AccountType")
@UITemplate(gridCount=4, columnSearch={"type", "value"})
@Displays({
    @Display(name="type"),
    @Display(name="value",label="Description")
})
public class AccountType extends ReferenceType implements Serializable {
    @Id
    @Column(name = "type", nullable = false)
    public String type;
    @Column(name = "value")
    public String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
