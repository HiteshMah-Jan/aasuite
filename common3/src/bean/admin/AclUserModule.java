/*
 * AclUserDuty.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
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
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AclUserModule")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"userid", "moduleName"})
@Displays({
    @Display(name="userid"),
    @Display(name="moduleName")
})
public class AclUserModule extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "userid", nullable = false)
    public String userid;
    @Column(name = "moduleName", nullable = false)
    public String moduleName;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return moduleName;
    }

    public java.lang.String getUserid() {
        return userid;
    }

    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }

    public java.lang.String getModuleName() {
        return moduleName;
    }

    public void setModuleName(java.lang.String moduleName) {
        this.moduleName = moduleName;
    }

	@Override
	public void setupIndex() {
		runIndex(1, "userid");
	}
}
