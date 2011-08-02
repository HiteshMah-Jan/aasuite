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
@Table(name = "PhoneBook")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="branch", type="Combo", modelCombo={"all","main"}),
	@Display(name="departments", type="Combo", modelCombo={"all","accounting","general","production","purchase","sales","service"})
})
public class PhoneBook extends AbstractIBean{
@Id
public String branch;
public String departments;
	
	public String getBranch() {
	return branch;
}

public void setBranch(String branch) {
	this.branch = branch;
}

public String getDepartments() {
	return departments;
}

public void setDepartments(String departments) {
	this.departments = departments;
}

	public static void main(String[] args) {
	view(PhoneBook.class);
}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}
}
