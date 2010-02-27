/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
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

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "College")
@Displays({
        @Display(name="code"),
        @Display(name="college"),
        @Display(name="head")
})
public class College extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "college", nullable = false)
    public String college;
    @Column(name = "head")
    public String head;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return college;
    }

    public java.lang.String getCollege() {
        return college;
    }

    public void setCollege(java.lang.String college) {
        this.college = college;
    }

    public java.lang.String getHead() {
        return head;
    }

    public void setHead(java.lang.String head) {
        this.head = head;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
