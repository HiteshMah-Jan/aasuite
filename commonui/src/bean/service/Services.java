/*
 * Accounttype.java
 *
 * Created on Nov 25, 2007, 2:55:53 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.service;

import bean.reference.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import template.screen.TemplateSinglePage;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "Services")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"code"})
@Displays({
    @Display(name="code", label="Service", upCase=false),
    @Display(name="lastRunDate"),
    @Display(name="lastRunTime", type="Time"),
    @Display(name="lastRunCount")
})
@ActionButtons( {
		@ActionButton(name = "btnRun", label = "Run Service")
})
public class Services extends AbstractIBean implements Serializable {
	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code");
    }

    @Id
    @Column(name = "code", nullable = false)
    public String code;    
    @Column(name = "lastRunDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date lastRunDate;
    @Column(name = "lastRunTime")
    public String lastRunTime;    
    @Column(name = "lastRunCount")
    public int lastRunCount;    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLastRunCount() {
        return lastRunCount;
    }

    public void setLastRunCount(int lastRunCount) {
        this.lastRunCount = lastRunCount;
    }

    public Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(String lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public static void logRun(String service, int count) {
		Services serv = (Services) DBClient.getFirstRecord("SELECT a FROM Services a WHERE a.code='",service,"'");
		serv.lastRunCount = count;
		serv.lastRunDate = new Date();
		serv.lastRunTime = DateUtil.getTime();
		serv.save();
    }
}
