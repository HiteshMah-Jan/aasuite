/*
 * AclUserGroup.java
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
@Table(name = "AclUserGroup")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"userid", "groupCode"})
@Displays({
    @Display(name="userid"),
    @Display(name="groupCode")
})
public class AclUserGroup extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "userid", nullable = false)
    public String userid;
    @Column(name = "groupCode", nullable = false)
    public String groupCode;

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
        return groupCode;
    }

    public java.lang.String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }

    public java.lang.String getUserid() {
        return userid;
    }

    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }

	@Override
	public void setupIndex() {
		runIndex(1, "userid");
	}
}
