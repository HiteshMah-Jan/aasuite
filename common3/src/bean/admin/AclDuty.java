/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AclDuty")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "description"})
@Displays({
    @Display(name = "code"),
    @Display(name = "hideMenuBar"),
    @Display(name = "description")
})
public class AclDuty extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "description", nullable = false, length = 100)
    public String description;
    @Column(name = "hideMenuBar")
    public boolean hideMenuBar;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String seq) {
        this.code = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code;
    }

    public boolean getHideMenuBar() {
        return hideMenuBar;
    }

    public void setHideMenuBar(boolean hideMenuBar) {
        this.hideMenuBar = hideMenuBar;
    }

    public static void createDuty(String code, String desc) {
        AclDuty duty = new AclDuty();
        duty.code = code;
        duty.description = desc;
        duty.save();
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "code");
	}
}
