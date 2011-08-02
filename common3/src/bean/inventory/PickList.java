package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "PickList")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"number","transactType","docNo","customerCode","delvDueDate","itemNumber","itemDescription","unitOfMeasuer","itemsPerUnit","whse","wrhseName","ordered","released","picked","availToPick"})
@Displays({
	@Display(name="pickNumber"),
	@Display(name="pickDate"),
	@Display(name="user", type="Combo",modelCombo={""}),
	@Display(name="picker"),
	@Display(name="status", type="Combo",modelCombo={"Released","Picked","Partially Picked","Partially Delivered","Closed"}),
	@Display(name="remarks",type="TextArea",gridFieldWidth=4,width=-1)
})
public class PickList extends AbstractIBean{
@Id
public double pickNumber;
@Temporal(javax.persistence.TemporalType.DATE)
public Date pickDate;
public String user;
public String picker;
public String status;

public String remarks;
	
	public double getPickNumber() {
	return pickNumber;
}

public void setPickNumber(double pickNumber) {
	this.pickNumber = pickNumber;
}

public Date getPickDate() {
	return pickDate;
}

public void setPickDate(Date pickDate) {
	this.pickDate = pickDate;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getPicker() {
	return picker;
}

public void setPicker(String picker) {
	this.picker = picker;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

	public static void main(String[] args) {
	view(PickList.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
