/*
 * StudentAttendance.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import bean.reference.Subject;
import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentAttendance")
@UITemplate(columnSearch={"myear","attendanceType","mjanuary","mfebruary","mmarch","mapril","mmay","mjune","mjuly","maugust","mseptember","moctober","mnovember","mdecember"}, gridCount=6, title="Attendance")
@Displays({
    @Display(name = "myear", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}, label="Year"),
    @Display(name = "attendanceType", type="Combo", modelCombo={"SCHOOLDAYS","ATTENDANCE","TARDY"}, label="Type"),
    @Display(name = "mjanuary", label="January"),
    @Display(name = "mfebruary", label="February"),
    @Display(name = "mmarch", label="March"),
    @Display(name = "mapril", label="April"),
    @Display(name = "mmay", label="May"),
    @Display(name = "mjune", label="June"),
    @Display(name = "mjuly", label="July"),
    @Display(name = "maugust", label="August"),
    @Display(name = "mseptember", label="September"),
    @Display(name = "moctober", label="October"),
    @Display(name = "mnovember", label="November"),
    @Display(name = "mdecember", label="December")
})
public class StudentAttendance extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "myear", nullable = false)
    public int myear;
    @Column(name = "attendanceType", length = 100)
    public String attendanceType;
    @Column(name = "mjanuary")
    public int mjanuary;
    @Column(name = "mfebruary")
    public int mfebruary;
    @Column(name = "mmarch")
    public int mmarch;
    @Column(name = "mapril")
    public int mapril;
    @Column(name = "mmay")
    public int mmay;
    @Column(name = "mjune")
    public int mjune;
    @Column(name = "mjuly")
    public int mjuly;
    @Column(name = "maugust")
    public int maugust;
    @Column(name = "mseptember")
    public int mseptember;
    @Column(name = "moctober")
    public int moctober;
    @Column(name = "mnovember")
    public int mnovember;
    @Column(name = "mdecember")
    public int mdecember;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public int getMapril() {
        return mapril;
    }

    public void setMapril(int mapril) {
        this.mapril = mapril;
    }

    public int getMaugust() {
        return maugust;
    }

    public void setMaugust(int maugust) {
        this.maugust = maugust;
    }

    public int getMdecember() {
        return mdecember;
    }

    public void setMdecember(int mdecember) {
        this.mdecember = mdecember;
    }

    public int getMfebruary() {
        return mfebruary;
    }

    public void setMfebruary(int mfebruary) {
        this.mfebruary = mfebruary;
    }

    public int getMjanuary() {
        return mjanuary;
    }

    public void setMjanuary(int mjanuary) {
        this.mjanuary = mjanuary;
    }

    public int getMjuly() {
        return mjuly;
    }

    public void setMjuly(int mjuly) {
        this.mjuly = mjuly;
    }

    public int getMjune() {
        return mjune;
    }

    public void setMjune(int mjune) {
        this.mjune = mjune;
    }

    public int getMmarch() {
        return mmarch;
    }

    public void setMmarch(int mmarch) {
        this.mmarch = mmarch;
    }

    public int getMmay() {
        return mmay;
    }

    public void setMmay(int mmay) {
        this.mmay = mmay;
    }

    public int getMnovember() {
        return mnovember;
    }

    public void setMnovember(int mnovember) {
        this.mnovember = mnovember;
    }

    public int getMoctober() {
        return moctober;
    }

    public void setMoctober(int moctober) {
        this.moctober = moctober;
    }

    public int getMseptember() {
        return mseptember;
    }

    public void setMseptember(int mseptember) {
        this.mseptember = mseptember;
    }

    public int getMyear() {
        return myear;
    }

    public void setMyear(int myear) {
        this.myear = myear;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    
}
