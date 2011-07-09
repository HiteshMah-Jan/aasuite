/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "ShiftSchedule")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"shiftName", "startShift", "endShift"})
@Displays({
        @Display(name="shiftName"),
        @Display(name="startShift"),
        @Display(name="endShift"),
        @Display(name="totalHours"),
        @Display(name="gracePeriod")
})
public class ShiftSchedule extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "shiftName", nullable = false)
    public String shiftName;
    @Column(name = "startShift")
    public String startShift;
    @Column(name = "endShift")
    public String endShift;
    @Column(name = "totalHours")
    public double totalHours;
    @Column(name = "gracePeriod")
    public String gracePeriod;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "shiftName");
    }

    @Override
    public String toString() {
        return shiftName;
    }

    public java.lang.String getShiftName() {
        return shiftName;
    }

    public void setShiftName(java.lang.String shiftName) {
        this.shiftName = shiftName;
    }

    public java.lang.String getStartShift() {
        return startShift;
    }

    public void setStartShift(java.lang.String startShift) {
        this.startShift = startShift;
    }

    public java.lang.String getEndShift() {
        return endShift;
    }

    public void setEndShift(java.lang.String endShift) {
        this.endShift = endShift;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public java.lang.String getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(java.lang.String gracePeriod) {
        this.gracePeriod = gracePeriod;
    }
}
