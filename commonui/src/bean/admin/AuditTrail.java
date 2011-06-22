/*
 * AppMenu.java
 * 
 * Created on Aug 9, 2008, 2:28:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DateUtil;
import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "AuditTrail")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, 
		columnSearch = {"userId", "auditDate", "auditTime", "module", "reason"}, 
		criteriaSearch = {"userId", "auditDate", "module"}, 
		orderBy = " a.auditDate DESC, a.auditTime DESC")
@Displays({
    @Display(name="userId", enabled=false),
    @Display(name="auditDate", enabled=false),
    @Display(name="auditTime", enabled=false),
    @Display(name="module", enabled=false, gridFieldWidth=5, width=-1),
    @Display(name="reason", enabled=false, gridFieldWidth=5, width=-1),
    @Display(name="description", enabled=false, gridFieldWidth=5, width=-1)
})
public class AuditTrail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Temporal(TemporalType.DATE)
    @Column(name = "auditDate")
    public Date auditDate;
    @Column(name = "auditTime")
    public String auditTime;
    @Column(name = "userId")
    public String userId;
    @Column(name = "module")
    public String module;
    @Column(name = "moduleId")
    public String moduleId;
    @Column(name = "description")
    public String description;
    @Column(name = "reason")
    public String reason;

    public static void addTrail(AbstractIBean bean, String description, String reason) {
    	AuditTrail t = new AuditTrail();
    	t.module = bean.getClass().getSimpleName();
    	t.moduleId = bean.keyVal().toString();
    	t.reason = reason;
    	t.description = description;
    	t.save();
    }
    
    @Override
	public void save() {
    	userId = UserInfo.getUserName();
    	auditDate = new Date();
    	auditTime = DateUtil.getTime();
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "userId","module","reason");
    }

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
