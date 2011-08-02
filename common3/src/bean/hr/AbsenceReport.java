package bean.hr;

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
@Table(name = "AbsenceReport")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="employeeLastNameFrm",label="Employee Last Name    From"),
	@Display(name="branch", type="Combo", modelCombo={"all","main"}),
	@Display(name="department", type="Combo", modelCombo={"all","accounting","general","production","purchase","sales","service"}),
	@Display(name="manager"),
	@Display(name="startDate"),
	@Display(name="endDate"),
	
	@Display(name="employeeLastNameTo",label="To")
})
public class AbsenceReport extends AbstractIBean{
@Id
public String employeeLastNameFrm;
public String branch;
public String department;
public String manager;
@Temporal(javax.persistence.TemporalType.DATE)
public Date startDate;
public String employeeLastNameTo;


public String getEmployeeLastNameTo() {
	return employeeLastNameTo;
}

public void setEmployeeLastNameTo(String employeeLastNameTo) {
	this.employeeLastNameTo = employeeLastNameTo;
}

public String getEmployeeLastNameFrm() {
	return employeeLastNameFrm;
}

public String getEmployeeLastName() {
	return employeeLastNameFrm;
}

public void setEmployeeLastNameFrm(String employeeLastNameFrm) {
	this.employeeLastNameFrm = employeeLastNameFrm;
}

public String getBranch() {
	return branch;
}

public void setBranch(String branch) {
	this.branch = branch;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getManager() {
	return manager;
}

public void setManager(String manager) {
	this.manager = manager;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

@Temporal(javax.persistence.TemporalType.DATE)
public Date endDate;

	public static void main(String[] args) {
		view(AbsenceReport.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
