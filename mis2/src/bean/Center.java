/*
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@Entity
@Table(name = "Center")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"name", "branchName", "address"})
@Displays({
        @Display(name="name"),
        @Display(name="branchName", linktoBean=Branch.class, type="PopSearch"),
        @Display(name="address")
})
public class Center extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "branchName", nullable = false)
    public String branchName;
    @Column(name = "address")
    public String address;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "name", "branchName");
    }
    
    @Override
    public void setupIndex() {
        runUniqueIndex(1, "name", "branchName");
    }
}
