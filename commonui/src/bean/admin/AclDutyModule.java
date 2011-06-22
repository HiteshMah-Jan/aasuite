/*
 * AclDutyModule.java
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
@Table(name = "AclDutyModule")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"dutyCode", "moduleName"})
@Displays({
    @Display(name="seq"),
    @Display(name="dutyCode"),
    @Display(name="moduleName"),
    @Display(name="hideMenu"),
    @Display(name="refreshAccess"),
    @Display(name="deleteAccess"),
    @Display(name="insertAccess"),
    @Display(name="updateAccess")
})
public class AclDutyModule extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "dutyCode", nullable = false)
    public String dutyCode;
    @Column(name = "moduleName", nullable = false)
    public String moduleName;
    @Column(name = "hideMenu")
    public boolean hideMenu;
    @Column(name = "refreshAccess")
    public boolean refreshAccess;
    @Column(name = "deleteAccess")
    public boolean deleteAccess;
    @Column(name = "insertAccess")
    public boolean insertAccess;
    @Column(name = "updateAccess")
    public boolean updateAccess;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return dutyCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

   
    public boolean getHideMenu() {
        return hideMenu;
    }

    public void setHideMenu(boolean hideMenu) {
        this.hideMenu = hideMenu;
    }

    public boolean getRefreshAccess() {
        return refreshAccess;
    }

    public void setRefreshAccess(boolean refreshAccess) {
        this.refreshAccess = refreshAccess;
    }

    public boolean getDeleteAccess() {
        return deleteAccess;
    }

    public void setDeleteAccess(boolean deleteAccess) {
        this.deleteAccess = deleteAccess;
    }

    public boolean getInsertAccess() {
        return insertAccess;
    }

    public void setInsertAccess(boolean insertAccess) {
        this.insertAccess = insertAccess;
    }

    public boolean getUpdateAccess() {
        return updateAccess;
    }

    public void setUpdateAccess(boolean updateAccess) {
        this.updateAccess = updateAccess;
    }

    public java.lang.String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(java.lang.String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public java.lang.String getModuleName() {
        return moduleName;
    }

    public void setModuleName(java.lang.String moduleName) {
        this.moduleName = moduleName;
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "moduleName");
	}
}
