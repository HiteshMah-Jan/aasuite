/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "personattendance")
@NamedQueries({@NamedQuery(name = "Personattendance.findBySeq", query = "SELECT p FROM Personattendance p WHERE p.seq = :seq"), @NamedQuery(name = "Personattendance.findByNightDiff", query = "SELECT p FROM Personattendance p WHERE p.nightDiff = :nightDiff"), @NamedQuery(name = "Personattendance.findByPayrollid", query = "SELECT p FROM Personattendance p WHERE p.payrollid = :payrollid"), @NamedQuery(name = "Personattendance.findByAttendanceDate", query = "SELECT p FROM Personattendance p WHERE p.attendanceDate = :attendanceDate"), @NamedQuery(name = "Personattendance.findByAttendanceType", query = "SELECT p FROM Personattendance p WHERE p.attendanceType = :attendanceType"), @NamedQuery(name = "Personattendance.findByLogout", query = "SELECT p FROM Personattendance p WHERE p.logout = :logout"), @NamedQuery(name = "Personattendance.findByApprovedOvertime", query = "SELECT p FROM Personattendance p WHERE p.approvedOvertime = :approvedOvertime"), @NamedQuery(name = "Personattendance.findByLate", query = "SELECT p FROM Personattendance p WHERE p.late = :late"), @NamedQuery(name = "Personattendance.findByApprovedNightDiff", query = "SELECT p FROM Personattendance p WHERE p.approvedNightDiff = :approvedNightDiff"), @NamedQuery(name = "Personattendance.findByPersonId", query = "SELECT p FROM Personattendance p WHERE p.personId = :personId"), @NamedQuery(name = "Personattendance.findByOvertimeAmount", query = "SELECT p FROM Personattendance p WHERE p.overtimeAmount = :overtimeAmount"), @NamedQuery(name = "Personattendance.findByTotalHours", query = "SELECT p FROM Personattendance p WHERE p.totalHours = :totalHours"), @NamedQuery(name = "Personattendance.findByNightDifAmount", query = "SELECT p FROM Personattendance p WHERE p.nightDifAmount = :nightDifAmount"), @NamedQuery(name = "Personattendance.findByLogin", query = "SELECT p FROM Personattendance p WHERE p.login = :login"), @NamedQuery(name = "Personattendance.findByLateAmountDeduction", query = "SELECT p FROM Personattendance p WHERE p.lateAmountDeduction = :lateAmountDeduction"), @NamedQuery(name = "Personattendance.findByOvertime", query = "SELECT p FROM Personattendance p WHERE p.overtime = :overtime"), @NamedQuery(name = "Personattendance.findByTotalAmount", query = "SELECT p FROM Personattendance p WHERE p.totalAmount = :totalAmount"), @NamedQuery(name = "Personattendance.findByPersonName", query = "SELECT p FROM Personattendance p WHERE p.personName = :personName")})
public class Personattendance implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "seq", nullable = false)
    private Integer seq;
    @Column(name = "nightDiff")
    private Double nightDiff;
    @Column(name = "payrollid")
    private Integer payrollid;
    @Column(name = "attendanceDate")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    @Column(name = "attendanceType")
    private String attendanceType;
    @Column(name = "logout")
    private String logout;
    @Column(name = "approvedOvertime")
    private Double approvedOvertime;
    @Column(name = "late")
    private Double late;
    @Column(name = "approvedNightDiff")
    private Double approvedNightDiff;
    @Column(name = "personId")
    private Integer personId;
    @Column(name = "overtimeAmount")
    private Double overtimeAmount;
    @Column(name = "totalHours")
    private Double totalHours;
    @Column(name = "nightDifAmount")
    private Double nightDifAmount;
    @Column(name = "login")
    private String login;
    @Column(name = "lateAmountDeduction")
    private Double lateAmountDeduction;
    @Column(name = "overtime")
    private Double overtime;
    @Column(name = "totalAmount")
    private Double totalAmount;
    @Column(name = "personName")
    private String personName;

    public Personattendance() {
    }

    public Personattendance(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        Integer oldSeq = this.seq;
        this.seq = seq;
        changeSupport.firePropertyChange("seq", oldSeq, seq);
    }

    public Double getNightDiff() {
        return nightDiff;
    }

    public void setNightDiff(Double nightDiff) {
        Double oldNightDiff = this.nightDiff;
        this.nightDiff = nightDiff;
        changeSupport.firePropertyChange("nightDiff", oldNightDiff, nightDiff);
    }

    public Integer getPayrollid() {
        return payrollid;
    }

    public void setPayrollid(Integer payrollid) {
        Integer oldPayrollid = this.payrollid;
        this.payrollid = payrollid;
        changeSupport.firePropertyChange("payrollid", oldPayrollid, payrollid);
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        Date oldAttendanceDate = this.attendanceDate;
        this.attendanceDate = attendanceDate;
        changeSupport.firePropertyChange("attendanceDate", oldAttendanceDate, attendanceDate);
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        String oldAttendanceType = this.attendanceType;
        this.attendanceType = attendanceType;
        changeSupport.firePropertyChange("attendanceType", oldAttendanceType, attendanceType);
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        String oldLogout = this.logout;
        this.logout = logout;
        changeSupport.firePropertyChange("logout", oldLogout, logout);
    }

    public Double getApprovedOvertime() {
        return approvedOvertime;
    }

    public void setApprovedOvertime(Double approvedOvertime) {
        Double oldApprovedOvertime = this.approvedOvertime;
        this.approvedOvertime = approvedOvertime;
        changeSupport.firePropertyChange("approvedOvertime", oldApprovedOvertime, approvedOvertime);
    }

    public Double getLate() {
        return late;
    }

    public void setLate(Double late) {
        Double oldLate = this.late;
        this.late = late;
        changeSupport.firePropertyChange("late", oldLate, late);
    }

    public Double getApprovedNightDiff() {
        return approvedNightDiff;
    }

    public void setApprovedNightDiff(Double approvedNightDiff) {
        Double oldApprovedNightDiff = this.approvedNightDiff;
        this.approvedNightDiff = approvedNightDiff;
        changeSupport.firePropertyChange("approvedNightDiff", oldApprovedNightDiff, approvedNightDiff);
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        Integer oldPersonId = this.personId;
        this.personId = personId;
        changeSupport.firePropertyChange("personId", oldPersonId, personId);
    }

    public Double getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(Double overtimeAmount) {
        Double oldOvertimeAmount = this.overtimeAmount;
        this.overtimeAmount = overtimeAmount;
        changeSupport.firePropertyChange("overtimeAmount", oldOvertimeAmount, overtimeAmount);
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        Double oldTotalHours = this.totalHours;
        this.totalHours = totalHours;
        changeSupport.firePropertyChange("totalHours", oldTotalHours, totalHours);
    }

    public Double getNightDifAmount() {
        return nightDifAmount;
    }

    public void setNightDifAmount(Double nightDifAmount) {
        Double oldNightDifAmount = this.nightDifAmount;
        this.nightDifAmount = nightDifAmount;
        changeSupport.firePropertyChange("nightDifAmount", oldNightDifAmount, nightDifAmount);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        String oldLogin = this.login;
        this.login = login;
        changeSupport.firePropertyChange("login", oldLogin, login);
    }

    public Double getLateAmountDeduction() {
        return lateAmountDeduction;
    }

    public void setLateAmountDeduction(Double lateAmountDeduction) {
        Double oldLateAmountDeduction = this.lateAmountDeduction;
        this.lateAmountDeduction = lateAmountDeduction;
        changeSupport.firePropertyChange("lateAmountDeduction", oldLateAmountDeduction, lateAmountDeduction);
    }

    public Double getOvertime() {
        return overtime;
    }

    public void setOvertime(Double overtime) {
        Double oldOvertime = this.overtime;
        this.overtime = overtime;
        changeSupport.firePropertyChange("overtime", oldOvertime, overtime);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        Double oldTotalAmount = this.totalAmount;
        this.totalAmount = totalAmount;
        changeSupport.firePropertyChange("totalAmount", oldTotalAmount, totalAmount);
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        String oldPersonName = this.personName;
        this.personName = personName;
        changeSupport.firePropertyChange("personName", oldPersonName, personName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seq != null ? seq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personattendance)) {
            return false;
        }
        Personattendance other = (Personattendance) object;
        if ((this.seq == null && other.seq != null) || (this.seq != null && !this.seq.equals(other.seq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.Personattendance[seq=" + seq + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

}
