package bean.hr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "EmployeeList")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="branch", type="Combo", modelCombo={"all","main"}),
	@Display(name="department", type="Combo", modelCombo={"all","accounting","general","production","purchase","sales","service","all"}),
	@Display(name="role", type="Combo", modelCombo={"purchasing","salesEmployee","technician","all"})
})

public class EmployeeList extends AbstractIBean{
@Id
public String branch;
public String department;
public String role;

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

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

	public static void main(String[] args) {
		view(EmployeeList.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
