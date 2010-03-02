/*
 * Carrier.java
 *
 * Created on Sep 30, 2007, 8:02:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Carrier")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, 
    columnSearch = {"code","prefix","name"}, showChart=true)
@Displays({
    @Display(name = "code", width=40),
    @Display(name = "prefix", width=40),
    @Display(name = "name", width=250),
    @Display(name = "address1", gridFieldWidth=5, width=-1),
    @Display(name = "address2", gridFieldWidth=5, width=-1),
    @Display(name = "address3", gridFieldWidth=5, width=-1)
})
public class Carrier extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "prefix", nullable = false, length = 3)
    public String prefix;
    @Column(name = "address1", length = 150)
    public String address1;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "address2", length = 150)
    public String address2;
    @Column(name = "address3", length = 150)
    public String address3;
    @Column(name = "acive")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","name","prefix");
    }
}
