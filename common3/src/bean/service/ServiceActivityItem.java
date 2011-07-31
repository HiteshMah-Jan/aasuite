package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;

@Entity
@Table(name = "ServiceActivityItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"activityNumber","date","Time","newActivity","content"})
@Displays({
//	@Display(name="activitieNumber"),
//	@Display(name="date"),
//	@Display(name="Time"),
//	@Display(name="newActivity"),
//	@Display(name="content")
})

public class ServiceActivityItem extends AbstractIBean{
@Id
	public double activityNumber;
@Temporal(value = TemporalType.DATE)
	public Date date;
	public double Time;
	public String newActivity;
	public String content;
	public double getActivitieNumber() {
		return activityNumber;
	}

	public void setActivityNumber(double activityNumber) {
		this.activityNumber = activityNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTime() {
		return Time;
	}

	public void setTime(double time) {
		Time = time;
	}

	public String getNewActivity() {
		return newActivity;
	}

	public void setNewActivity(String newActivity) {
		this.newActivity = newActivity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static void main(String[] args) {
		view(ServiceActivityItem.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
