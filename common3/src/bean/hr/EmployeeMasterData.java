package bean.hr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;
@Entity
@Table(name = "EmployeeMasterData")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="firstName"),
	@Display(name="middleName"),
	@Display(name="lastName"),
	@Display(name="jobTitle"),
	@Display(name="position", type="Combo", modelCombo={}),
	@Display(name="department", type="Combo", modelCombo={"accounting","general","production","purchase","sales","service"}),
	@Display(name="branch", type="Combo", modelCombo={"main"}),
	@Display(name="manager"),
	@Display(name="userCode", type="Combo", modelCombo={}),
	@Display(name="salesEmployee", type="Combo", modelCombo={}),
	@Display(name="employeeNo"),
	@Display(name="officePhone"),
	@Display(name="mobilePhone"),
	@Display(name="pager"),
	@Display(name="homePhone"),
	@Display(name="eMail"),
	@Display(name="ext",label="ext.")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "address", gridCount=8,
			displayFields = {

}),
@ParentAddInfo(title = "membership", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "administration", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "personal", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "finance", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "remarks", gridCount=8,
		displayFields = {

}),
@ParentAddInfo(title = "attachments", gridCount=8,
		displayFields = {

})
})
public class EmployeeMasterData extends AbstractIBean {
	@Id
	
	public String firstName;
	public String middleName;
	public String lastName;
	public String jobTitle;
	public String position;
	public String department;
	public String branch;
	public String manager;
	public String userCode;
	public String salesEmployee;
	public Double employeeNo;
	public Double officePhone;
	public Double mobilePhone;
	public Double pager;
	public Double homePhone;
	public String eMail;
	public String ext;
	
	
	
	
	
	
public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		jobTitle = jobTitle;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		department = department;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		branch = branch;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		manager = manager;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		userCode = userCode;
	}
	public String getSalesEmployee() {
		return salesEmployee;
	}
	public void setSalesEmployee(String salesEmployee) {
		salesEmployee = salesEmployee;
	}
	public Double getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(Double employeeNo) {
		employeeNo = employeeNo;
	}
	public Double getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(Double officePhone) {
		officePhone = officePhone;
	}
	public Double getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(Double mobilePhone) {
		mobilePhone = mobilePhone;
	}
	public Double getPager() {
		return pager;
	}
	public void setPager(Double pager) {
		pager = pager;
	}
	public Double getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(Double homePhone) {
		homePhone = homePhone;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		eMail = eMail;
	}
public static void main(String[] args) {
	view(EmployeeMasterData.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
