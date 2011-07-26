package bean.hr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;
@Entity
@Table(name = "EmployeeMasterData")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="FirstName"),
	@Display(name="MiddleName"),
	@Display(name="lastName"),
	@Display(name="JobTitle"),
	@Display(name="Position"),
	@Display(name="Department"),
	@Display(name="Branch"),
	@Display(name="Manager"),
	@Display(name="UserCode"),
	@Display(name="SalesEmployee"),
	@Display(name="EmployeeNo"),
	@Display(name="OfficePhone"),
	@Display(name="MobilePhone"),
	@Display(name="Pager"),
	@Display(name="HomePhone"),
	@Display(name="E_Mail")
})
public class EmployeeMasterData extends AbstractIBean {
	@Id
	
	public String FirstName;
	public String MiddleName;
	public String lastName;
	public String JobTitle;
	public String Position;
	public String Department;
	public String Branch;
	public String Manager;
	public String UserCode;
	public String SalesEmployee;
	public Double EmployeeNo;
	public Double OfficePhone;
	public Double MobilePhone;
	public Double Pager;
	public Double HomePhone;
	public String E_Mail;
	
	
	
	
	
public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJobTitle() {
		return JobTitle;
	}
	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getManager() {
		return Manager;
	}
	public void setManager(String manager) {
		Manager = manager;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getSalesEmployee() {
		return SalesEmployee;
	}
	public void setSalesEmployee(String salesEmployee) {
		SalesEmployee = salesEmployee;
	}
	public Double getEmployeeNo() {
		return EmployeeNo;
	}
	public void setEmployeeNo(Double employeeNo) {
		EmployeeNo = employeeNo;
	}
	public Double getOfficePhone() {
		return OfficePhone;
	}
	public void setOfficePhone(Double officePhone) {
		OfficePhone = officePhone;
	}
	public Double getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(Double mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public Double getPager() {
		return Pager;
	}
	public void setPager(Double pager) {
		Pager = pager;
	}
	public Double getHomePhone() {
		return HomePhone;
	}
	public void setHomePhone(Double homePhone) {
		HomePhone = homePhone;
	}
	public String getE_Mail() {
		return E_Mail;
	}
	public void setE_Mail(String e_Mail) {
		E_Mail = e_Mail;
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
