/*
 * Charges.java
 * 
 * Created on Sep 30, 2007, 8:02:08 PM
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
@Table(name = "Charges")
@UITemplate(template = TemplateSinglePage.class, gridCount = 8, 
    columnSearch = {"code","name","amount"}, showChart=true)
@Displays({
    @Display(name = "code", width=30),
    @Display(name = "name"),
    @Display(name = "amount", width=60),
    @Display(name = "always", width=30),
    @Display(name = "reason", width=-1, gridFieldWidth=7)
})
public class Charges extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length=5)
    public String code;
    @Column(name = "name", nullable = false, length=50)
    public String name;
    @Column(name = "reason", nullable = false, length=150)
    public String reason;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "always")
    public boolean always;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAlways() {
        return always;
    }

    public void setAlways(boolean always) {
        this.always = always;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name");
    }
}
