/*
 * PersonAttendance.java
 *
 * Created on Nov 18, 2007, 8:26:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import util.DateUtil;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonUsedLeave")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"personId", "personUsedLeaveId", "leaveType"})
@Displays({
        @Display(name="personUsedLeaveId"),
        @Display(name="personId"),
        @Display(name="dateFrom"),
        @Display(name="dateTo"),
        @Display(name="dateFiled"),
        @Display(name="dateUsed"),
        @Display(name="payrollid"),
        @Display(name="leaveType"),
        @Display(name="leaveApplicationID"),
        @Display(name="attendanceId")
})
public class PersonUsedLeave extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "dateFrom")
    @Temporal(value = TemporalType.DATE)
    public Date dateFrom;
    @Column(name = "dateTo")
    @Temporal(value = TemporalType.DATE)
    public Date dateTo;
    @Column(name = "dateFiled")
    @Temporal(value = TemporalType.DATE)
    public Date dateFiled;
    @Column(name = "dateUsed")
    @Temporal(value = TemporalType.DATE)
    public Date dateUsed;
    @Column(name = "payrollid")
    public int payrollid;
    @Column(name = "leaveType")
    public String leaveType;
    @Column(name = "leaveApplicationID")
    public int leaveApplicationID;
    @Column(name = "attendanceId")
    public int attendanceId;

    @Override
    public String toString() {
        if (dateUsed == null) {
            return "";
        }
        return DateUtil.formatDate(dateUsed, "MMM dd yyyy");
    }

    public java.util.Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(java.util.Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public java.util.Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(java.util.Date dateTo) {
        this.dateTo = dateTo;
    }

    public java.util.Date getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(java.util.Date dateFiled) {
        this.dateFiled = dateFiled;
    }

    public java.util.Date getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(java.util.Date dateUsed) {
        this.dateUsed = dateUsed;
    }

    public int getPayrollid() {
        return payrollid;
    }

    public void setPayrollid(int payrollid) {
        this.payrollid = payrollid;
    }

    public java.lang.String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(java.lang.String leaveType) {
        this.leaveType = leaveType;
    }

    public int getLeaveApplicationID() {
        return leaveApplicationID;
    }

    public void setLeaveApplicationID(int leaveApplicationID) {
        this.leaveApplicationID = leaveApplicationID;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }
}
