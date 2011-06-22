/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;


/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "EmployeeLeaveApplication")
@UITemplate(template = TemplateDefault.class, gridCount = 6, criteriaSearch={"filedDate", "personId"},columnSearch = {"filedDate", "personId", "leaveType"})
@Displays({
      //  @Display(name="seq"),
        @Display(name="filedDate", gridFieldWidth=5),
        //@Display(name="personId", label = "Employee",gridFieldWidth=3,width=300),
        @Display(name="leaveType",width = 250, type = "Combo",modelCombo={"SICK","EMERGENCY","PERSONAL","UNDERTIME","BEREAVEMENT","MATERNITY/PATERNITY","OFFICIAL EX.SEMINARS/TRAININGS_ETC."}),
        @Display(name="reason", gridFieldWidth = 3, width=-1),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="leaveCount"),
        @Display(name="employeeSignedDate", label="Emp. Signed Date"),
        @Display(name="departmentHeadSignedDate",label="Dep. Head Signed Date")
        
        //   @Display(name="leaveCount", type = "Combo", modelCombo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},width=40)
})
        @template.ActionButtons({
    @template.ActionButton(name="btnPrintEmployeeLeave", label="Print Leave", parentOnly=false)
//    @template.ActionButton(name="btnPostGL", label="Post to GL")
})
public class EmployeeLeaveApplication extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "filedDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date filedDate;
    @Column(name = "startDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date endDate;
    @Column(name = "leaveType")
    public String leaveType;
    @Column(name = "reason")
    public String reason;
    @Column(name = "employeeSignedDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date employeeSignedDate;
    @Column(name = "departmentHeadSignedDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date departmentHeadSignedDate;
    
    @Column(name = "leaveCount")
    public int leaveCount;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getFiledDate() {
        return filedDate;
    }

    public void setFiledDate(Date filedDate) {
        this.filedDate = filedDate;
    }

    public int getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        this.leaveCount = leaveCount;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDepartmentHeadSignedDate() {
        return departmentHeadSignedDate;
    }

    public void setDepartmentHeadSignedDate(Date departmentHeadSignedDate) {
        this.departmentHeadSignedDate = departmentHeadSignedDate;
    }

    public Date getEmployeeSignedDate() {
        return employeeSignedDate;
    }

    public void setEmployeeSignedDate(Date employeeSignedDate) {
        this.employeeSignedDate = employeeSignedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }
}
