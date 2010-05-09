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
import template.screen.TemplateSinglePage;
import util.BeanUtil;
import util.DBClient;
import bean.admin.AppConfig;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "LockGrading")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"schoolYear","q1LockBy","q2LockBy","q3LockBy","q4LockBy"},
    criteriaSearch = {"schoolYear"}, 
    orderBy="a.schoolYear DESC")
@Displays({
        @Display(name="schoolYear", gridFieldWidth=3),
        @Display(name="q1LockBy", enabled=false),
        @Display(name="q1LockDate", enabled=false),
        @Display(name="q2LockBy", enabled=false),
        @Display(name="q2LockDate", enabled=false),
        @Display(name="q3LockBy", enabled=false),
        @Display(name="q3LockDate", enabled=false),
        @Display(name="q4LockBy", enabled=false),
        @Display(name="q4LockDate", enabled=false)
})
@ActionButtons({
    @ActionButton(name="btnLockQ1", label="Lock Q1"),
    @ActionButton(name="btnLockQ2", label="Lock Q2"),
    @ActionButton(name="btnLockQ3", label="Lock Q3"),
    @ActionButton(name="btnLockQ4", label="Lock Q4"),
    @ActionButton(name="btnReleaseLock", label="Release Lock")
})
public class LockGrading extends AbstractIBean implements Serializable {
	@Override
	public void save() {
		super.save();
		lock = null;
	}
	
	@Override
	public boolean cacheClient() {
		return true;
	}

	public LockGrading() {
		schoolYear = AppConfig.getSchoolYear();
	}
	
	static LockGrading lock;
	public static LockGrading extractGrading(String schoolYear) {
		if (lock!=null) {
			return lock;
		}
		lock = (LockGrading) DBClient.getFirstRecord("SELECT a FROM LockGrading a WHERE a.schoolYear='"+schoolYear+"'");
		if (lock==null) {
			lock = new LockGrading();
			lock.schoolYear = schoolYear;
			lock.save();
		}
		return lock;
	}
	
	public boolean isQ1Locked() {
		return (q1LockBy!=null && !q1LockBy.trim().isEmpty());
	}
	public boolean isQ2Locked() {
		return (q2LockBy!=null && !q2LockBy.trim().isEmpty());
	}
	public boolean isQ3Locked() {
		return (q3LockBy!=null && !q3LockBy.trim().isEmpty());
	}
	public boolean isQ4Locked() {
		return (q4LockBy!=null && !q4LockBy.trim().isEmpty());
	}

	public void lockAll(boolean b) {
		lockQ1(b);
		lockQ2(b);
		lockQ3(b);
		lockQ4(b);
	}
	
	public void lockQ1(boolean b) {
		lock(b, 1);
	}
	
	public void lockQ2(boolean b) {
		lock(b, 2);
	}

	public void lockQ3(boolean b) {
		lock(b, 3);
	}
	
	public void lockQ4(boolean b) {
		lock(b, 4);
	}
	private void lock(boolean b, int quarter) {
		String qname = "q"+quarter+"LockBy";
		if (b) {
			BeanUtil.setPropertyValue(this, qname, UserInfo.getUserName());
		}
		else {
			BeanUtil.setPropertyValue(this, qname, null);
		}

		qname = "q"+quarter+"LockDate";
		if (b) {
			BeanUtil.setPropertyValue(this, qname, new Date());
		}
		else {
			BeanUtil.setPropertyValue(this, qname, null);
		}
	}

    @Id
    @Column(name = "schoolYear", nullable = false)
    public String schoolYear;
    @Column(name = "q1LockBy")
    public String q1LockBy;
    @Column(name="q1LockDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date q1LockDate;
    @Column(name = "q2LockBy")
    public String q2LockBy;
    @Column(name="q2LockDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date q2LockDate;
    @Column(name = "q3LockBy")
    public String q3LockBy;
    @Column(name="q3LockDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date q3LockDate;
    @Column(name = "q4LockBy")
    public String q4LockBy;
    @Column(name="q4LockDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date q4LockDate;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }


	public String getSchoolYear() {
		return schoolYear;
	}


	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}


	public String getQ1LockBy() {
		return q1LockBy;
	}


	public void setQ1LockBy(String lockBy) {
		q1LockBy = lockBy;
	}


	public Date getQ1LockDate() {
		return q1LockDate;
	}


	public void setQ1LockDate(Date lockDate) {
		q1LockDate = lockDate;
	}


	public String getQ2LockBy() {
		return q2LockBy;
	}


	public void setQ2LockBy(String lockBy) {
		q2LockBy = lockBy;
	}


	public Date getQ2LockDate() {
		return q2LockDate;
	}


	public void setQ2LockDate(Date lockDate) {
		q2LockDate = lockDate;
	}


	public String getQ3LockBy() {
		return q3LockBy;
	}


	public void setQ3LockBy(String lockBy) {
		q3LockBy = lockBy;
	}


	public Date getQ3LockDate() {
		return q3LockDate;
	}


	public void setQ3LockDate(Date lockDate) {
		q3LockDate = lockDate;
	}


	public String getQ4LockBy() {
		return q4LockBy;
	}


	public void setQ4LockBy(String lockBy) {
		q4LockBy = lockBy;
	}


	public Date getQ4LockDate() {
		return q4LockDate;
	}

	public void setQ4LockDate(Date lockDate) {
		q4LockDate = lockDate;
	}
	
	public int useQuarter() {
		return isQ4Locked()?4:isQ3Locked()?3:isQ2Locked()?2:1;
	}
}
