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
@Table(name = "BillOfMaterialsReport")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="codeFrm",label="Code From"),
	
	@Display(name="codeTo",label="To"),
	@Display(name="group",type="Combo",modelCombo={}),
	@Display(name="bOMType",type="Combo",modelCombo={"All","Production","Sales","Template"},label="BOM Type")
})
public class BillOfMaterialsReport extends AbstractIBean{
@Id
public String codeFrm;
public String codeTo;
public String group;
public String bOMType;


	public String getCodeFrm() {
	return codeFrm;
}

public void setCodeFrm(String codeFrm) {
	codeFrm = codeFrm;
}
public String getCTo() {
	return codeTo;
}

public void setCodeTo(String codeTo) {
	codeTo = codeTo;
}

public String getGroup() {
	return group;
}

public void setGroup(String group) {
	group = group;
}

public String getBOMType() {
	return bOMType;
}

public void setBOMType(String bOMType) {
	bOMType = bOMType;
}

	public static void main(String[] args) {
		view(BillOfMaterialsReport.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
