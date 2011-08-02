package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
@Entity
@Table(name = "CycleCountRecommendation")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="cycleCodeFrm", label="Cycle Code From", type="Combo", modelCombo={}),
	@Display(name="cycleCodeTo", label="To", type="Combo", modelCombo={})
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "By Location", gridCount=8,
				displayFields = {
						
		}),
		@ParentAddInfo(title = "By Warehouse", gridCount=8,
				displayFields = {
						
		})
		
})
public class CycleCountRecommendation extends AbstractIBean{
@Id
public String cycleCodeFrm;
public String cycleCodeTo;

	public String getCycleCodeFrm() {
	return cycleCodeFrm;
}

public void setCycleCodeFrm(String cycleCodeFrm) {
	this.cycleCodeFrm = cycleCodeFrm;
}

public String getCycleCodeTo() {
	return cycleCodeTo;
}

public void setCycleCodeTo(String cycleCodeTo) {
	this.cycleCodeTo = cycleCodeTo;
}

	public static void main(String[] args) {
		view(CycleCountRecommendation.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
