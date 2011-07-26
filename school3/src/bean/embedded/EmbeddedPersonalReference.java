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
	@Display(name="person1", label="Name", labelTop=true),
	@Display(name="personContact1", label="Contact", labelTop=true),
	@Display(name="personCompany1", label="Company", labelTop=true),
	@Display(name="personLevel1", label="Level", labelTop=true),
	@Display(name="person2", hideLabel=true),
	@Display(name="personContact2", hideLabel=true),
	@Display(name="personCompany2", hideLabel=true),
	@Display(name="personLevel2", hideLabel=true),
	@Display(name="person3", hideLabel=true),
	@Display(name="personContact3", hideLabel=true),
	@Display(name="personCompany3", hideLabel=true),
	@Display(name="personLevel3", hideLabel=true)
})
public class EmbeddedPersonalReference extends AbstractIBean {
	public String person1;
	public String person2;
	public String person3;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date personContact1;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date personContact2;
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date personContact3;
	public String personCompany1;
	public String personCompany2;
	public String personCompany3;
	public String personLevel1;
	public String personLevel2;
	public String personLevel3;
	
	public String getPerson1() {
		return person1;
	}
	public void setPerson1(String person1) {
		this.person1 = person1;
	}
	public String getPerson2() {
		return person2;
	}
	public void setPerson2(String person2) {
		this.person2 = person2;
	}
	public String getPerson3() {
		return person3;
	}
	public void setPerson3(String person3) {
		this.person3 = person3;
	}
	public Date getPersonContact1() {
		return personContact1;
	}
	public void setPersonContact1(Date personContact1) {
		this.personContact1 = personContact1;
	}
	public Date getPersonContact2() {
		return personContact2;
	}
	public void setPersonContact2(Date personContact2) {
		this.personContact2 = personContact2;
	}
	public Date getPersonContact3() {
		return personContact3;
	}
	public void setPersonContact3(Date personContact3) {
		this.personContact3 = personContact3;
	}
	public String getPersonCompany1() {
		return personCompany1;
	}
	public void setPersonCompany1(String personCompany1) {
		this.personCompany1 = personCompany1;
	}
	public String getPersonCompany2() {
		return personCompany2;
	}
	public void setPersonCompany2(String personCompany2) {
		this.personCompany2 = personCompany2;
	}
	public String getPersonCompany3() {
		return personCompany3;
	}
	public void setPersonCompany3(String personCompany3) {
		this.personCompany3 = personCompany3;
	}
	public String getPersonLevel1() {
		return personLevel1;
	}
	public void setPersonLevel1(String personLevel1) {
		this.personLevel1 = personLevel1;
	}
	public String getPersonLevel2() {
		return personLevel2;
	}
	public void setPersonLevel2(String personLevel2) {
		this.personLevel2 = personLevel2;
	}
	public String getPersonLevel3() {
		return personLevel3;
	}
	public void setPersonLevel3(String personLevel3) {
		this.personLevel3 = personLevel3;
	}
	public static void main(String[] args) {
		view(EmbeddedPersonalReference.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
