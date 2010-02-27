/*
 * Gltype.java
 *
 * Created on Nov 25, 2007, 2:57:33 PM
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
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "GLType")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"glCode", "accountNumber", "classification"})
@Displays({
        @Display(name="glCode"),
        @Display(name="accountNumber"),
        @Display(name="classification"),
        @Display(name="subclassification"),
        @Display(name="form"),
        @Display(name="description")
})
public class GLType extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "glCode", nullable = false)
    public String glCode;
    @Column(name = "accountNumber")
    public int accountNumber;
    @Column(name = "classification")
    public String classification;
    @Column(name = "subclassification")
    public String subclassification;
    @Column(name = "form")
    public String form;
    @Column(name = "description")
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "glCode", "accountNumber");
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComboDisplay() {
        if (glCode == null) {
            return "";
        } else {
            return glCode + " - " + accountNumber;
        }
    }


    @Override
    public String toString() {
        return glCode;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        classification = constants.Constants.ChartOfAccount.getClassification(accountNumber);
        subclassification = constants.Constants.ChartOfAccount.getSubClassification(accountNumber);
    }

    public java.lang.String getClassification() {
        return classification;
    }

    public void setClassification(java.lang.String classification) {
        this.classification = classification;
    }

    public java.lang.String getSubclassification() {
        return subclassification;
    }

    public void setSubclassification(java.lang.String subclassification) {
        this.subclassification = subclassification;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
