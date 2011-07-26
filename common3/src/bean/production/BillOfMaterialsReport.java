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
	@Display(name="Code"),
	@Display(name="CFrm"),
	@Display(name="CTo"),
	@Display(name="Group"),
	@Display(name="BOMType")
})
public class BillOfMaterialsReport extends AbstractIBean{
@Id
public String Code;
public String CFrm;
public String CTo;
public String Group;
public String BOMType;


	public String getCode() {
	return Code;
}

public void setCode(String code) {
	Code = code;
}

public String getCFrm() {
	return CFrm;
}

public void setCFrm(String cFrm) {
	CFrm = cFrm;
}

public String getCTo() {
	return CTo;
}

public void setCTo(String cTo) {
	CTo = cTo;
}

public String getGroup() {
	return Group;
}

public void setGroup(String group) {
	Group = group;
}

public String getBOMType() {
	return BOMType;
}

public void setBOMType(String bOMType) {
	BOMType = bOMType;
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
