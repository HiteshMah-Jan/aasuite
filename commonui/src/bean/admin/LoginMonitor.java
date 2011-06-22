/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
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

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateDefault;
import util.DateUtil;
import util.NetworkUtil;
import constants.UserInfo;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "LoginMonitor")
@UITemplate(template = TemplateDefault.class, gridCount = 4, 
		columnSearch = {"userid", "lastname", "firstname", "hostname", "loginDate"}, orderBy="a.loginDate DESC")
@Displays({
    @Display(name="userid"),
    @Display(name="password"),
    @Display(name="firstname"),
    @Display(name="lastname"),
    @Display(name="hostname"),
    @Display(name="loginDate")
})
@ChildRecords(
   @ChildRecord(template=ChildTemplateListOnly.class,title="Login History", entity = LoginMonitor.class, sql = "SELECT a FROM LoginMonitor a WHERE a.userid = '${userid}'")
)
@Reports({ 
    @template.Report(reportFile="LoginPerUser", reportTitle="Per User", reportSql="${userid}"),
    @template.Report(reportFile="LoginPerUser", reportTitle="All Users", reportSql="")
})
public class LoginMonitor extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "userid", nullable = false, length = 30)
    public String userid;
    @Column(name = "password", nullable = false, length = 30)
    public String password;
    @Column(name = "firstname", length = 50)
    public String firstname;
    @Column(name = "lastname", length = 50)
    public String lastname;
    @Column(name = "hostname", length = 50)
    public String hostname;
    @Column(name = "loginDate")
    public String loginDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
    
	public static void addLogin() {
		AclUser u = UserInfo.loginUser.getUser();
		LoginMonitor log = new LoginMonitor();
		log.userid = u.userid;
		log.password = u.password;
		log.firstname = u.firstname;
		log.lastname = u.lastname;
		log.loginDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm");
		log.hostname = NetworkUtil.getHostname();
		log.save();
	}
}
