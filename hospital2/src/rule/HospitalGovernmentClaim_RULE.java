package rule;

import javax.swing.JComponent;

import util.DBClient;
import util.PanelUtil;

import bean.HospitalGovernmentClaim;
import bean.HospitalInsuranceClaim;
import bean.OutPatient;
import bean.Patient;

public class HospitalGovernmentClaim_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("patientIsTheMember".equals(comp.getName())) {
			patientIsMember();
		}
	}

	private void patientIsMember() {
		HospitalGovernmentClaim c = (HospitalGovernmentClaim) this.getBean();
		if (c == null || c.isEmptyKey()) {
			PanelUtil.showMessage(null, "Please select record.");
			return;
		}
		if (c.patientIsTheMember) {
			Patient p = (Patient) DBClient.getFirstRecord("SELECT a FROM Patient a WHERE a.personId="+c.patientId);

			
			
			disable("membershipType");
			disable("memberLastName");
			disable("memberFirstName");
			disable("memberMiddleName");
			disable("memberBirthDate");
			disable("memberCivilStatus");
			disable("memberGender");
			disable("memberAddressSt");
			disable("memberAddressBarangay");
			disable("memberAddressMunicipality");
			disable("memberAddressProvince");
			disable("patientRelationshipToMember");
			
			setValue("memberLastName", p.lastName);
			setValue("memberFirstName", p.firstName);
			setValue("memberMiddleName", p.middleInitial);
			setValue("memberBirthDate", p.birthDate);
			setValue("memberCivilStatus", p.status);
			setValue("memberGender", p.gender);
			setValue("memberAddressSt", p.streetNumber);
			setValue("memberAddressBarangay", p.barangay);
			setValue("memberAddressMunicipality", p.townDistrict);
			setValue("memberAddressProvince", p.cityProvince);
		} 
		else {
			
			enable("membershipType");
			enable("memberLastName");
			enable("memberFirstName");
			enable("memberMiddleName");
			enable("memberBirthDate");
			enable("memberCivilStatus");
			enable("memberGender");
			enable("memberAddressSt");
			enable("memberAddressBarangay");
			enable("memberAddressMunicipality");
			enable("memberAddressProvince");
			enable("patientRelationshipToMember");
		}
	}

}
