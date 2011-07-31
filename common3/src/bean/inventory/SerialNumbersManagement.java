package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;

@Entity
@Table(name = "SerialNumberManagement")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="operation", type="Combo",modelCombo={"Update","Complete"}),
	@Display(name="itemNoFrm", label="Item No. From"),
	@Display(name="group", type="Combo",modelCombo={}),
	@Display(name="updateExistingSerialNumbersTo"),
	@Display(name="dateFrm", label="Date From"),
	@Display(name="To")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Purchasing - A/P", gridCount=8,
				displayFields = {
						
		}),
		@ParentAddInfo(title = "Sales - A/R", gridCount=8,
				displayFields = {
						
		}),
		@ParentAddInfo(title = "Inventory Posting", gridCount=8,
				displayFields = {
						
		})
})
public class SerialNumbersManagement extends AbstractIBean{
@Id
public String operation;
public double itemNoFrm;
public String group;

public String updateExistingSerialNumbersTo;

@Temporal(javax.persistence.TemporalType.DATE)
public Date dateFrm;
public String To;

	public static void main(String[] args) {
		view(SerialNumbersManagement.class);
		
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getItemNoFrm() {
		return itemNoFrm;
	}

	public void setItemNoFrm(double itemNoFrm) {
		this.itemNoFrm = itemNoFrm;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getUpdateExistingSerialNumbersTo() {
		return updateExistingSerialNumbersTo;
	}

	public void setUpdateExistingSerialNumbersTo(
			String updateExistingSerialNumbersTo) {
		this.updateExistingSerialNumbersTo = updateExistingSerialNumbersTo;
	}

	public Date getDateFrm() {
		return dateFrm;
	}

	public void setDateFrm(Date dateFrm) {
		this.dateFrm = dateFrm;
	}

	public String getTo() {
		return To;
	}

	public void setTo(String to) {
		To = to;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
