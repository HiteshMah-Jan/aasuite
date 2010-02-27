/*
 * Eventholiday.java
 *
 * Created on Nov 27, 2007, 4:31:11 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.Reports;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SchoolActivity")
//@UITemplate(template = Template2.class, gridCount = 4, columnSearch = {"eventDate", "eventHoliday", "description"})
@UITemplate(gridCount = 4, columnSearch = {"activity","activityDate","location"})
@Displays({
       // @Display(name="id"),
        @Display(name="activity",width=250),
        @Display(name="activityDate"),
        @Display(name="location",width=-1),
        @Display(name="activityFee",width=-1),

        @Display(name="description",gridFieldWidth=3,width=-1)

})
    @Reports({
    @template.Report(reportFile="OSASchoolActivity", reportTitle="School Activity Report", reportSql="${seq}")

})
public class SchoolActivity extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "activityDate", nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date activityDate;
    @Column(name = "activity", nullable = false, length = 100)
    public String activity;
    @Column(name = "location")
    public String location;
    @Column(name = "activityFee")
    public double activityFee;
    @Column(name = "description", length = 200)
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "activity","location");
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public double getActivityFee() {
        return activityFee;
    }

    public void setActivityFee(double activityFee) {
        this.activityFee = activityFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return activity;
    }
}
