package bean.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 10, columnSearch = {"seq"})
@Displays({
	@Display(name="street1", label="Street", labelTop=true),
	@Display(name="barangay1", label="Barangay", labelTop=true),
	@Display(name="city1", label="City", labelTop=true),
	@Display(name="zipCode1", label="Zip Code", labelTop=true),
	@Display(name="province1", label="Province", labelTop=true),
	@Display(name="street2", hideLabel=true),
	@Display(name="barangay2", hideLabel=true),
	@Display(name="city2", hideLabel=true),
	@Display(name="zipCode2", hideLabel=true),
	@Display(name="province2", hideLabel=true),
	@Display(name="street3", hideLabel=true),
	@Display(name="barangay3", hideLabel=true),
	@Display(name="city3", hideLabel=true),
	@Display(name="zipCode3", hideLabel=true),
	@Display(name="province3", hideLabel=true)
})
public class EmbeddedAddress extends AbstractIBean {
	public String street1;
	public String barangay1;
	public String city1;
	public String zipCode1;
	public String province1;
	public String street2;
	public String barangay2;
	public String city2;
	public String zipCode2;
	public String province2;
	public String street3;
	public String barangay3;
	public String city3;
	public String zipCode3;
	public String province3;
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getBarangay1() {
		return barangay1;
	}
	public void setBarangay1(String barangay1) {
		this.barangay1 = barangay1;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getZipCode1() {
		return zipCode1;
	}
	public void setZipCode1(String zipCode1) {
		this.zipCode1 = zipCode1;
	}
	public String getProvince1() {
		return province1;
	}
	public void setProvince1(String province1) {
		this.province1 = province1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getBarangay2() {
		return barangay2;
	}
	public void setBarangay2(String barangay2) {
		this.barangay2 = barangay2;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	public String getZipCode2() {
		return zipCode2;
	}
	public void setZipCode2(String zipCode2) {
		this.zipCode2 = zipCode2;
	}
	public String getProvince2() {
		return province2;
	}
	public void setProvince2(String province2) {
		this.province2 = province2;
	}
	public String getStreet3() {
		return street3;
	}
	public void setStreet3(String street3) {
		this.street3 = street3;
	}
	public String getBarangay3() {
		return barangay3;
	}
	public void setBarangay3(String barangay3) {
		this.barangay3 = barangay3;
	}
	public String getCity3() {
		return city3;
	}
	public void setCity3(String city3) {
		this.city3 = city3;
	}
	public String getZipCode3() {
		return zipCode3;
	}
	public void setZipCode3(String zipCode3) {
		this.zipCode3 = zipCode3;
	}
	public String getProvince3() {
		return province3;
	}
	public void setProvince3(String province3) {
		this.province3 = province3;
	}
	public static void main(String[] args) {
		view(EmbeddedAddress.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
