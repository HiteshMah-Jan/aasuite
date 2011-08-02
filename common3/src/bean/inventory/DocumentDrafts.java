package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "DocumentDrafts")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="user")
})
public class DocumentDrafts extends AbstractIBean{
@Id
public String user;

	public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

	public static void main(String[] args) {
		view(DocumentDrafts.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
