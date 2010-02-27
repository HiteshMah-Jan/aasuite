/*
 * HelpFields.java
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
@Table(name = "HelpFields")
@UITemplate(template = TemplateDefault.class, columnSearch = {"help_field", "field", "help_name"})
@Displays({
        @Display(name="helpField"),
        @Display(name="field"),
        @Display(name="displayField"),
        @Display(name="context"),
        @Display(name="helpName")
})
public class HelpFields extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "help_field", nullable = false, length = 50)
    public String helpField;
    @Column(name = "field", nullable = false, length = 50)
    public String field;
    @Column(name = "display_field", nullable = false, length = 50)
    public String displayField;
 
    @Column(name = "context")
    public String context;
    @Column(name = "help_name", nullable = false, length = 50)
    public String helpName;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getHelpField() {
        return helpField;
    }

    public void setHelpField(String helpField) {
        this.helpField = helpField;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDisplayField() {
        return displayField;
    }

    public void setDisplayField(String displayField) {
        this.displayField = displayField;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }
   
    @Override
    public String toString() {
        return helpField;
    }
}
