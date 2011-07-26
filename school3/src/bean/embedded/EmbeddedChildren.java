package bean.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 8, columnSearch = {"seq"})
@Displays({
	@Display(name="child1", label="Name", labelTop=true),
	@Display(name="childBirthDate1", label="Birth Date", labelTop=true),
	@Display(name="childCompany1", label="Company", labelTop=true),
	@Display(name="childLevel1", label="Level", labelTop=true),
	@Display(name="child2", hideLabel=true),
	@Display(name="childBirthDate2", hideLabel=true),
	@Display(name="childCompany2", hideLabel=true),
	@Display(name="childLevel2", hideLabel=true),
	@Display(name="child3", hideLabel=true),
	@Display(name="childBirthDate3", hideLabel=true),
	@Display(name="childCompany3", hideLabel=true),
	@Display(name="childLevel3", hideLabel=true)
})
public class EmbeddedChildren extends AbstractIBean {
	public String child1;
	public String child2;
	public String child3;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childBirthDate1;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childBirthDate2;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date childBirthDate3;
	public String childCompany1;
	public String childCompany2;
	public String childCompany3;
	public String childLevel1;
	public String childLevel2;
	public String childLevel3;
	
	public String getChild1() {
		return child1;
	}
	public void setChild1(String child1) {
		this.child1 = child1;
	}
	public String getChild2() {
		return child2;
	}
	public void setChild2(String child2) {
		this.child2 = child2;
	}
	public String getChild3() {
		return child3;
	}
	public void setChild3(String child3) {
		this.child3 = child3;
	}
	public Date getChildBirthDate1() {
		return childBirthDate1;
	}
	public void setChildBirthDate1(Date childBirthDate1) {
		this.childBirthDate1 = childBirthDate1;
	}
	public Date getChildBirthDate2() {
		return childBirthDate2;
	}
	public void setChildBirthDate2(Date childBirthDate2) {
		this.childBirthDate2 = childBirthDate2;
	}
	public Date getChildBirthDate3() {
		return childBirthDate3;
	}
	public void setChildBirthDate3(Date childBirthDate3) {
		this.childBirthDate3 = childBirthDate3;
	}
	public String getChildCompany1() {
		return childCompany1;
	}
	public void setChildCompany1(String childCompany1) {
		this.childCompany1 = childCompany1;
	}
	public String getChildCompany2() {
		return childCompany2;
	}
	public void setChildCompany2(String childCompany2) {
		this.childCompany2 = childCompany2;
	}
	public String getChildCompany3() {
		return childCompany3;
	}
	public void setChildCompany3(String childCompany3) {
		this.childCompany3 = childCompany3;
	}
	public String getChildLevel1() {
		return childLevel1;
	}
	public void setChildLevel1(String childLevel1) {
		this.childLevel1 = childLevel1;
	}
	public String getChildLevel2() {
		return childLevel2;
	}
	public void setChildLevel2(String childLevel2) {
		this.childLevel2 = childLevel2;
	}
	public String getChildLevel3() {
		return childLevel3;
	}
	public void setChildLevel3(String childLevel3) {
		this.childLevel3 = childLevel3;
	}
	public static void main(String[] args) {
		view(EmbeddedChildren.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
