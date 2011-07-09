/*
 * Facility.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
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
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Facility")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "department", "location"})
@Displays({
        @Display(name="code"),
        @Display(name="department", type = "PopSearch", linktoBean = Department.class),
        @Display(name="location"),
        @Display(name="usaget"),
        @Display(name="remarks")
})
public class Facility extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "department", nullable = false, length = 30)
    public String department;
    @Column(name = "location", length = 100)
    public String location;
    @Column(name = "usaget", length = 150)
    public String usaget;
    @Column(name = "remarks", length = 150)
    public String remarks;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "department", "location");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUsaget() {
        return usaget;
    }

    public void setUsaget(String usaget) {
        this.usaget = usaget;
    }

}
