package bean.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"tel1"})
@Displays({
	@Display(name="tel1"),
	@Display(name="tel2"),
	@Display(name="mobilePhone"),
	@Display(name="fax"),
	@Display(name="email"),
	@Display(name="website"),
	@Display(name="shippingType"),
	@Display(name="password"),
	@Display(name="factoringIndicator"),
	@Display(name="project"),
	@Display(name="aliasName"),
	@Display(name="active"),
	@Display(name="contactPerson"),
	@Display(name="idNo2"),
	@Display(name="remarks"),
	@Display(name="userDefinedCommission"),
	@Display(name="commision"),
	@Display(name="bpChannelCode"),
	@Display(name="territory")
})public class EmbeddedBPDetail extends AbstractIBean {
	public String tel1;
	public String tel2;
	public String mobilePhone;
	public String fax;
	public String email;
	public String website;
	public String shippingType;
	public String password;
	public String factoringIndicator;
	public String project;
	public String aliasName;
	public boolean active;
	public String contactPerson;
	public String idNo2;
	public String remarks;
	public String userDefinedCommission;
	public double commision;
	public String bpChannelCode;
	public String territory;
	
	public static void main(String[] args) {
		view(EmbeddedBPDetail.class);
	}
	
	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFactoringIndicator() {
		return factoringIndicator;
	}

	public void setFactoringIndicator(String factoringIndicator) {
		this.factoringIndicator = factoringIndicator;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getIdNo2() {
		return idNo2;
	}

	public void setIdNo2(String idNo2) {
		this.idNo2 = idNo2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUserDefinedCommission() {
		return userDefinedCommission;
	}

	public void setUserDefinedCommission(String userDefinedCommission) {
		this.userDefinedCommission = userDefinedCommission;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public String getBpChannelCode() {
		return bpChannelCode;
	}

	public void setBpChannelCode(String bpChannelCode) {
		this.bpChannelCode = bpChannelCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
