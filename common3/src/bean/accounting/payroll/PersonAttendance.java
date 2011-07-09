/*
 * PersonAttendance.java
 *
 * Created on Nov 18, 2007, 8:26:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting.payroll;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabSinglePageNoSubPanel;
import bean.Employee;
import bean.person.PersonAttribute;


/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonAttendance")
@UITemplate(template = TemplateTabSinglePageNoSubPanel.class,
		gridCount = 10,
        columnSearch = {"personName","attendanceDate","attendanceType","login","logout","totalHours","otHours","underTime","approvedOTHours"},
        criteriaSearch = {"attendanceDate","personId"}, sumFooter="5,6,7,8")
                       
@Displays({
        @Display(name="personId", label ="Employee", linktoBean=Employee.class, type="PopSearch", gridFieldWidth=9, width=-1),
        @Display(name="attendanceType", label="Attendance Type", type="Combo", modelCombo={"PRESENT","VACATION","SICK","OTHERS"}, gridFieldWidth=9, width=-1),
        @Display(name="attendanceDate", label="Date", gridFieldWidth=5, width=-1),
        @Display(name="login", label="In", type="Time"),
        @Display(name="logout", label="Out", type="Time"),
        @Display(name="totalHours"),
        @Display(name="underTime"),
        @Display(name="nightHours"),
        @Display(name="otHours"),
        @Display(name="approvedOTHours", label="Approved OT")
        })
@template.ActionButtons({
    @template.ActionButton(name="btnCreateAttendance", label="Create Attendance"),
    @template.ActionButton(name="btnCalculate", label="Calculate Hours"),
    @template.ActionButton(name="btnCalculateAll", label="Calculate Hours All Display")
})
@Reports( {
    @template.Report(reportFile = "TimeSheet", reportTitle = "Daily Time Sheet", reportSql = ""),
    @template.Report(reportFile = "TimeSheetPerEmployee", reportTitle = "Daily Time Sheet per Employee", reportSql = "${personId}")
})

public class PersonAttendance extends PersonAttribute implements Serializable {
	public static void main(String[] args) {
        view(PersonAttendance.class);
    }
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "attendanceDate")
    @Temporal(value = TemporalType.DATE)
    public Date attendanceDate;
    @Column(name = "login", length = 5)
    public String login;
    @Column(name = "logout", length = 5)
    public String logout;
    @Column(name = "totalHours")
    public double totalHours;
    @Column(name = "nightHours")
    public double nightHours;
    @Column(name = "otHours")
    public double otHours;
    @Column(name = "underTime")
    public double underTime;
    @Column(name = "attendanceType")
    public String attendanceType;
    @Column(name = "approvedOTHours")
    public double approvedOTHours;
    @Column(name = "personName")
    public String personName;
    
   
    @Override
	public void save() {
		personName = extractPersonName(personId);
		super.save();
	}


    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
		return seq;
    }

    public void setSeq(Integer seq) {
		this.seq = seq;
    }

    public double getApprovedOTHours() {
        return approvedOTHours;
    }

    public void setApprovedOTHours(double approvedOTHours) {
        this.approvedOTHours = approvedOTHours;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public double getNightHours() {
        return nightHours;
    }

    public void setNightHours(double nightHours) {
        this.nightHours = nightHours;
    }

    public double getOtHours() {
        return otHours;
    }

    public void setOtHours(double otHours) {
        this.otHours = otHours;
    }

    public double getUnderTime() {
        return underTime;
    }

    public void setUnderTime(double underTime) {
        this.underTime = underTime;
    }


	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public void setPersonId(int personId) {
		this.personId = personId;
	}
    
   
}
