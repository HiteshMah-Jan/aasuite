package bean.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 10, columnSearch = {"seq"})
@Displays({
	@Display(name="contact1", label="Contact From", labelTop=true),
	@Display(name="mobile1", label="Mobile", labelTop=true),
	@Display(name="im1", label="IM", labelTop=true),
	@Display(name="telephone1", label="Telephone", labelTop=true),
	@Display(name="fax1", label="Fax", labelTop=true),
	@Display(name="contact2", hideLabel=true),
	@Display(name="mobile2", hideLabel=true),
	@Display(name="im2", hideLabel=true),
	@Display(name="telephone2", hideLabel=true),
	@Display(name="fax2", hideLabel=true),
	@Display(name="contact3", hideLabel=true),
	@Display(name="mobile3", hideLabel=true),
	@Display(name="im3", hideLabel=true),
	@Display(name="telephone3", hideLabel=true),
	@Display(name="fax3", hideLabel=true)
})
public class EmbeddedContact extends AbstractIBean {
	public String contact1;
	public String mobile1;
	public String im1;
	public String telephone1;
	public String fax1;
	public String contact2;
	public String mobile2;
	public String im2;
	public String telephone2;
	public String fax2;
	public String contact3;
	public String mobile3;
	public String im3;
	public String telephone3;
	public String fax3;
	
	public String getContact1() {
		return contact1;
	}
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getIm1() {
		return im1;
	}
	public void setIm1(String im1) {
		this.im1 = im1;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getContact2() {
		return contact2;
	}
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getIm2() {
		return im2;
	}
	public void setIm2(String im2) {
		this.im2 = im2;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getContact3() {
		return contact3;
	}
	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getIm3() {
		return im3;
	}
	public void setIm3(String im3) {
		this.im3 = im3;
	}
	public String getTelephone3() {
		return telephone3;
	}
	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}
	public String getFax3() {
		return fax3;
	}
	public void setFax3(String fax3) {
		this.fax3 = fax3;
	}
	public static void main(String[] args) {
		view(EmbeddedContact.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
