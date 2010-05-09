/*
 * ServiceLevel.java
 *
 * Created on Sep 30, 2007, 8:02:10 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import bean.*;
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
@Table(name = "ServiceLevel")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"code","name","shc1","shc2","shc3"}, showChart=true)
@Displays({
    @Display(name = "code", width=50),
    @Display(name = "name", gridFieldWidth=5, width=-1),
    @Display(name = "shc1", type="PopSearch", gridFieldWidth=3, width=50, linktoBean=SpecialHandling.class, label="Shc", mergeFields={"shc2","shc3","shc4","shc5"}),
    @Display(name = "shc2", type="PopSearch", width=50, hideLabel=true, linktoBean=SpecialHandling.class),
    @Display(name = "shc3", type="PopSearch", width=50, hideLabel=true, linktoBean=SpecialHandling.class),
    @Display(name = "shc4", type="PopSearch", width=50, hideLabel=true, linktoBean=SpecialHandling.class),
    @Display(name = "shc5", type="PopSearch", width=50, hideLabel=true, linktoBean=SpecialHandling.class),
    @Display(name = "chargeCode1", type="PopSearch", width=50, linktoBean=Charges.class, label="Charge Code", mergeFields={"chargeCode2","chargeCode3","chargeCode4","chargeCode5"}),
    @Display(name = "chargeCode2", type="PopSearch", width=50, hideLabel=true, linktoBean=Charges.class),
    @Display(name = "chargeCode3", type="PopSearch", width=50, hideLabel=true, linktoBean=Charges.class),
    @Display(name = "chargeCode4", type="PopSearch", width=50, hideLabel=true, linktoBean=Charges.class),
    @Display(name = "chargeCode5", type="PopSearch", width=50, hideLabel=true, linktoBean=Charges.class)
})
public class ServiceLevel extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "code", nullable = false, length = 6)
    public String code;
    @Column(name = "shc3", length = 3)
    public String shc3;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "shc2", length = 3)
    public String shc2;
    @Column(name = "shc4", length = 3)
    public String shc4;
    @Column(name = "shc1", length = 3)
    public String shc1;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "higherIata", length = 10)
    public String higherIata;
    @Column(name = "shc5", length = 3)
    public String shc5;

    @Column(name = "chargeCode1", length = 5)
    public String chargeCode1;
    @Column(name = "chargeCode2", length = 5)
    public String chargeCode2;
    @Column(name = "chargeCode3", length = 5)
    public String chargeCode3;
    @Column(name = "chargeCode4", length = 5)
    public String chargeCode4;
    @Column(name = "chargeCode5", length = 5)
    public String chargeCode5;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getChargeCode1() {
        return chargeCode1;
    }

    public void setChargeCode1(String chargeCode1) {
        this.chargeCode1 = chargeCode1;
    }

    public String getChargeCode2() {
        return chargeCode2;
    }

    public void setChargeCode2(String chargeCode2) {
        this.chargeCode2 = chargeCode2;
    }

    public String getChargeCode3() {
        return chargeCode3;
    }

    public void setChargeCode3(String chargeCode3) {
        this.chargeCode3 = chargeCode3;
    }

    public String getChargeCode4() {
        return chargeCode4;
    }

    public void setChargeCode4(String chargeCode4) {
        this.chargeCode4 = chargeCode4;
    }

    public String getChargeCode5() {
        return chargeCode5;
    }

    public void setChargeCode5(String chargeCode5) {
        this.chargeCode5 = chargeCode5;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHigherIata() {
        return higherIata;
    }

    public void setHigherIata(String higherIata) {
        this.higherIata = higherIata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShc1() {
        return shc1;
    }

    public void setShc1(String shc1) {
        this.shc1 = shc1;
    }

    public String getShc2() {
        return shc2;
    }

    public void setShc2(String shc2) {
        this.shc2 = shc2;
    }

    public String getShc3() {
        return shc3;
    }

    public void setShc3(String shc3) {
        this.shc3 = shc3;
    }

    public String getShc4() {
        return shc4;
    }

    public void setShc4(String shc4) {
        this.shc4 = shc4;
    }

    public String getShc5() {
        return shc5;
    }

    public void setShc5(String shc5) {
        this.shc5 = shc5;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","name");
    }
}
