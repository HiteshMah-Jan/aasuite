/*
 * Eventholiday.java
 *
 * Created on Nov 27, 2007, 4:31:11 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "EventHoliday")
//@UITemplate(template = Template2.class, gridCount = 4, columnSearch = {"eventDate", "eventHoliday", "description"})
@UITemplate(gridCount = 4, 
	columnSearch = {"eventHoliday", "eventDate", "percentage", "description"},
	criteriaSearch = {"eventHoliday"}
)
@Displays({
       // @Display(name="id"),
    @Display(name="eventHoliday", gridFieldWidth=3, width=-1),
    @Display(name="eventDate"),
    @Display(name="percentage"),
    @Display(name="description", gridFieldWidth=3, width=-1)        
})
public class EventHoliday extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "eventHoliday", nullable = false)
    public String eventHoliday;
    @Column(name = "eventDate", nullable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date eventDate;
    @Column(name = "description", length = 200)
    public String description;
    @Column(name = "percentage")
    public double percentage;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "eventHoliday", "description");
    }

    public String getEventHoliday() {
        return eventHoliday;
    }

    public void setEventHoliday(String eventHoliday) {
        this.eventHoliday = eventHoliday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return eventHoliday;
    }

    public java.util.Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(java.util.Date eventDate) {
        this.eventDate = eventDate;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
	@Override
	public void setupIndex() {
		EventHoliday hol = new EventHoliday();
		hol.eventHoliday = "NEW YEAR";
		hol.eventDate = DateUtil.readDate("2009-01-01", "yyyy-MM-dd");
		hol.save();

		hol.eventHoliday = "CHRISTMAS";
		hol.eventDate = DateUtil.readDate("2009-12-25", "yyyy-MM-dd");
		hol.save();

		hol.eventHoliday = "MAUNDY THURSDAY";
		hol.eventDate = DateUtil.readDate("2009-04-16", "yyyy-MM-dd");
		hol.save();

		hol.eventHoliday = "GOOD FRIDAY";
		hol.eventDate = DateUtil.readDate("2009-04-17", "yyyy-MM-dd");
		hol.save();
	}

	static List<EventHoliday> lst;
    public static EventHoliday extractHoliday(Date d) {
    	if (lst==null || lst.isEmpty()) {
    		new EventHoliday().setupIndex();
    		lst = DBClient.getList("SELECT a FROM EventHoliday a ORDER BY a.eventDate");
    	}
    	if (lst!=null && !lst.isEmpty()) {
        	for (EventHoliday hol:lst) {
        		String dEvent = DateUtil.formatDate(hol.eventDate, "MMdd");
        		String dDate = DateUtil.formatDate(d, "MMdd");
        		if (dEvent.equals(dDate)) {
        			return hol;
        		}
        	}
    	}
    	return null;
    }
}
