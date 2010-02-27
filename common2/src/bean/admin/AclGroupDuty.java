/*
 * AclGroupDuty.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
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
@Table(name = "AclGroupDuty")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"dutyCode", "groupCod"})
@Displays({
    @Display(name="dutyCode"),
    @Display(name="groupCode")
})
public class AclGroupDuty extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "dutyCode", nullable = false)
    public String dutyCode;
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
        return dutyCode;
    }

    public String getDutyDescription() {
        bean.admin.AclDuty duty = (AclDuty) this.selectFirstCache("SELECT a FROM AclDuty a WHERE a.code='" + this.dutyCode + "'");
        if (duty == null) {
            return "";
        }
        return duty.getDescription();
    }

    public java.lang.String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(java.lang.String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public java.lang.String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "groupCode");
	}
}
