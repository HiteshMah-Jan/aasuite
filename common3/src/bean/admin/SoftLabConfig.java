/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import javax.persistence.*;

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
@Table(name = "SoftLabConfig")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"username", "password"})
@Displays({
        @Display(name="seq"),
        @Display(name="username"),
        @Display(name="password"),
        @Display(name="driver"),
        @Display(name="url")
})
public class SoftLabConfig extends AbstractIBean implements Serializable {

    @Id
   @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "username")
    public String username;
    @Column(name = "password")
    public String password;
    @Column(name = "driver")
    public String driver;
    @Column(name = "url")
    public String url;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return username;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.String getDriver() {
        return driver;
    }

    public void setDriver(java.lang.String driver) {
        this.driver = driver;
    }

    public java.lang.String getUrl() {
        return url;
    }

    public void setUrl(java.lang.String url) {
        this.url = url;
    }
}
