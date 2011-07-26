package bean.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 8, columnSearch = {"seq"})
@Displays({
	@Display(name="guardianLastName1", label="Last Name", labelTop=true),
	@Display(name="guardianFirstName1", label="First Name", labelTop=true),
	@Display(name="guardianRelation1", label="Relation", labelTop=true),
	@Display(name="guardianContact1", label="Contact", labelTop=true),
	@Display(name="guardianLastName2", hideLabel=true),
	@Display(name="guardianFirstName2", hideLabel=true),
	@Display(name="guardianRelation2", hideLabel=true),
	@Display(name="guardianContact2", hideLabel=true),
	@Display(name="guardianLastName3", hideLabel=true),
	@Display(name="guardianFirstName3", hideLabel=true),
	@Display(name="guardianRelation3", hideLabel=true),
	@Display(name="guardianContact3", hideLabel=true)
})
public class EmbeddedGuardian extends AbstractIBean {
	public String guardianLastName1;
	public String guardianFirstName1;
	public String guardianRelation1;
	public String guardianContact1;
	public String guardianLastName2;
	public String guardianFirstName2;
	public String guardianRelation2;
	public String guardianContact2;
	public String guardianLastName3;
	public String guardianFirstName3;
	public String guardianRelation3;
	public String guardianContact3;
	
	public String getGuardianLastName1() {
		return guardianLastName1;
	}
	public void setGuardianLastName1(String guardianLastName1) {
		this.guardianLastName1 = guardianLastName1;
	}
	public String getGuardianFirstName1() {
		return guardianFirstName1;
	}
	public void setGuardianFirstName1(String guardianFirstName1) {
		this.guardianFirstName1 = guardianFirstName1;
	}
	public String getGuardianRelation1() {
		return guardianRelation1;
	}
	public void setGuardianRelation1(String guardianRelation1) {
		this.guardianRelation1 = guardianRelation1;
	}
	public String getGuardianContact1() {
		return guardianContact1;
	}
	public void setGuardianContact1(String guardianContact1) {
		this.guardianContact1 = guardianContact1;
	}
	public String getGuardianLastName2() {
		return guardianLastName2;
	}
	public void setGuardianLastName2(String guardianLastName2) {
		this.guardianLastName2 = guardianLastName2;
	}
	public String getGuardianFirstName2() {
		return guardianFirstName2;
	}
	public void setGuardianFirstName2(String guardianFirstName2) {
		this.guardianFirstName2 = guardianFirstName2;
	}
	public String getGuardianRelation2() {
		return guardianRelation2;
	}
	public void setGuardianRelation2(String guardianRelation2) {
		this.guardianRelation2 = guardianRelation2;
	}
	public String getGuardianContact2() {
		return guardianContact2;
	}
	public void setGuardianContact2(String guardianContact2) {
		this.guardianContact2 = guardianContact2;
	}
	public String getGuardianLastName3() {
		return guardianLastName3;
	}
	public void setGuardianLastName3(String guardianLastName3) {
		this.guardianLastName3 = guardianLastName3;
	}
	public String getGuardianFirstName3() {
		return guardianFirstName3;
	}
	public void setGuardianFirstName3(String guardianFirstName3) {
		this.guardianFirstName3 = guardianFirstName3;
	}
	public String getGuardianRelation3() {
		return guardianRelation3;
	}
	public void setGuardianRelation3(String guardianRelation3) {
		this.guardianRelation3 = guardianRelation3;
	}
	public String getGuardianContact3() {
		return guardianContact3;
	}
	public void setGuardianContact3(String guardianContact3) {
		this.guardianContact3 = guardianContact3;
	}
	public static void main(String[] args) {
		view(EmbeddedGuardian.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
