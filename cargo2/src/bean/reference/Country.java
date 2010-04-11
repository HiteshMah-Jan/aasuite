/*
 * Country.java
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
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Country")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"code","name","currency"})
@Displays({
    @Display(name = "code", width=40),
    @Display(name = "name"),
    @Display(name = "currency", width=60, linktoBean=Currency.class, type="PopSearch")
})
public class Country extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "currency", length = 3)
    public String currency;
    @Column(name = "active")
    public boolean active;

    public static String extractCurrency(String country) {
    	Country c = (Country) DBClient.getFirstRecord("SELECT a FROM Country WHERE a.code='"+country+"'");
    	return c.currency;
    }
    
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","name");
    }
}
