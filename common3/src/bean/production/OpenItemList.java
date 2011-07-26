package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "OpenItemList")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="OpenDocuments")
})
public class OpenItemList extends AbstractIBean{
@Id	
public String OpenDocuments;

	public String getOpenDocuments() {
	return OpenDocuments;
}

public void setOpenDocuments(String openDocuments) {
	OpenDocuments = openDocuments;
}

	public static void main(String[] args) {
		view(OpenItemList.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
