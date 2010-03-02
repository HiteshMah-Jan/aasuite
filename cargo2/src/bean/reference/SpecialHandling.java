/*
 * SpecialHandling.java
 *
 * Created on Sep 30, 2007, 8:02:10 PM
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
@Table(name = "SpecialHandling")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, 
    columnSearch = {"code","name","description"}, showChart=true)
@Displays({
    @Display(name = "code", width=30),
    @Display(name = "name"),
    @Display(name = "dgNumber1", width=30, mergeFields={"dgNumber2","dgNumber3","dgNumber4","dgNumber5"}, label="DG"),
    @Display(name = "dgNumber2", width=30, hideLabel=true),
    @Display(name = "dgNumber3", width=30, hideLabel=true),
    @Display(name = "dgNumber4", width=30, hideLabel=true),
    @Display(name = "dgNumber5", width=30, hideLabel=true),
    @Display(name = "description", width=-1, gridFieldWidth=5)
})
public class SpecialHandling extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "description", length = 150)
    public String description;
    @Column(name = "dgNumber1", length = 20)
    public String dgNumber1;
    @Column(name = "dgNumber2", length = 20)
    public String dgNumber2;
    @Column(name = "dgNumber3", length = 20)
    public String dgNumber3;
    @Column(name = "dgNumber4", length = 20)
    public String dgNumber4;
    @Column(name = "dgNumber5", length = 20)
    public String dgNumber5;
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

    public String getDgNumber1() {
        return dgNumber1;
    }

    public void setDgNumber1(String dgNumber1) {
        this.dgNumber1 = dgNumber1;
    }

    public String getDgNumber2() {
        return dgNumber2;
    }

    public void setDgNumber2(String dgNumber2) {
        this.dgNumber2 = dgNumber2;
    }

    public String getDgNumber3() {
        return dgNumber3;
    }

    public void setDgNumber3(String dgNumber3) {
        this.dgNumber3 = dgNumber3;
    }

    public String getDgNumber4() {
        return dgNumber4;
    }

    public void setDgNumber4(String dgNumber4) {
        this.dgNumber4 = dgNumber4;
    }

    public String getDgNumber5() {
        return dgNumber5;
    }

    public void setDgNumber5(String dgNumber5) {
        this.dgNumber5 = dgNumber5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","name");
    }
}
