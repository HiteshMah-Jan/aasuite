/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Table(name = "CompanyForms")
@UITemplate(gridCount = 4, columnSearch = {"code", "description", "enteredBy", "enteredDate"})
@Displays({
    @Display(name="code"),
    @Display(name="enteredDate"),
    @Display(name="enteredBy", type = "PopSearch", linktoBean=Employee.class,gridFieldWidth=3,width=250),
    @Display(name="description",gridFieldWidth=3,width=250)
    
 
})
public class CompanyForms extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "description", length = 100)
    public String description;
    @Column(name = "enteredBy", length = 100)
    public String enteredBy;
    @Column(name = "enteredDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date enteredDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public CompanyForms() {
    }

    public CompanyForms(String code) {
        this.code = code;
    }

    public CompanyForms(String code, String description) {
        this.code = code;
        this.description = description;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyForms)) {
            return false;
        }
        CompanyForms other = (CompanyForms) object;
        if (this.code != other.code && (this.code == null || !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return code;
    }

    public java.util.Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(java.util.Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public java.lang.String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(java.lang.String enteredBy) {
        this.enteredBy = enteredBy;
    }
}
