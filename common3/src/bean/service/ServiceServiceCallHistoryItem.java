package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "ServiceServiceCallHistoryItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","dateOfUpdate","updateTime","updatedBy","description","previousValue","newValue"})
@Displays({
//	@Display(name="number", label="#"),
//	@Display(name="dateOfUpdate", label="Date of Update"),
//	@Display(name="updateTime", label="Update Time"),
//	@Display(name="updatedBy", label="Updated By"),
//	@Display(name="description", label="Description"),
//	@Display(name="previousValue", label="Previous Value"),
//	@Display(name="newValue", label="New Value")
})
public class ServiceServiceCallHistoryItem extends AbstractIBean{
	@Id
	public double number;
	public Date dateOfUpdate;
	public double updateTime;
	public String updatedBy;
	public String description;
	public String previousValue;
	public String newValue;
	
	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public Date getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Date dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public double getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(double updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public static void main(String[] args) {
		view(ServiceServiceCallHistoryItem.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
