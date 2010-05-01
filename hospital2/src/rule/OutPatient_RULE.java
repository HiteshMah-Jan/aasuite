package rule;

import bean.HospitalGovernmentClaim;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import component.DicomViewerPanel;

import bean.OutPatient;
import bean.Physician;
import bean.accounting.BillingCodeReference;
import bean.accounting.Invoice;
import bean.accounting.OutPatientLineItem;

import template.screen.AbstractChildTemplatePanel;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;
import bean.HospitalInsuranceClaim;
import bean.HospitalInsuranceClaim;
public class OutPatient_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnGenerateItems".equals(comp.getName())) {
			generateBillingItems();
		}
		else if ("btnRequestAdmission".equals(comp.getName())) {
			requestAdmission();
		}
		else if ("btnPrintReceipt".equals(comp.getName())) {
			printReceipt();
		}
		else if ("btnDicomViewer".equals(comp.getName())) {
			dicomViewer();
		}
		else if ("btnPhilHealthNo".equals(comp.getName())) {
			addPhilHealth();
		}
		else if ("btnOtherInsurance".equals(comp.getName())) {
			addOtherInsurance();
		}
	}

	private void addPhilHealth() {
		OutPatient opd = (OutPatient) this.getBean();
		if (opd==null || opd.isEmptyKey()) {
			PanelUtil.showMessage(null, "Please select record.");
			return;
		}
		if (opd.philHealthNo==null || opd.philHealthNo.isEmpty()) {
			PanelUtil.showMessage(null, "No PhilHealth defined.");
			return;
		}
		HospitalGovernmentClaim h = (HospitalGovernmentClaim) DBClient.getFirstRecord("SELECT a FROM HospitalGovernmentClaim a WHERE a.opdId="+opd.seq);
		if (h==null || h.isEmptyKey()) {
			h = new HospitalGovernmentClaim();
			h.opdId = opd.seq;
			h.patientId = opd.patientId;
			h.idNo = opd.philHealthNo;
			HospitalGovernmentClaim oldClaim = (HospitalGovernmentClaim) DBClient.getFirstRecord("SELECT a FROM HospitalGovernmentClaim a WHERE a.idNo='"+h.idNo+"' ORDER BY a.seq DESC");
			if (oldClaim!=null) {
				h.memberAddressBarangay = oldClaim.memberAddressBarangay;
				h.memberAddressMunicipality = oldClaim.memberAddressMunicipality;
				h.memberAddressProvince = oldClaim.memberAddressProvince;
			}
			h.save();
		}
		PanelUtil.popupBeanTemplate(HospitalGovernmentClaim.class, "Philhealth", true, h, true);
	}

	List<BillingCodeReference> lst;
	protected BillingCodeReference getBillingCode(String code) {
		if (lst==null) {
			lst = DBClient.getList("SELECT a FROM BillingCodeReference a");
		}
		if (lst!=null) {
			for (BillingCodeReference ref:lst) {
				if (ref.code.equals(code)) {
					return ref;
				}
			}
		}
		BillingCodeReference r = new BillingCodeReference();
		r.code = code;
		r.save();
		lst = DBClient.getList("SELECT a FROM BillingCodeReference a");
		return r;
	}
	
	protected void generateBillingItems() {
		OutPatient op = (OutPatient) this.getBean();
		if (op.physicianId<=0) {
			PanelUtil.showError(null, "Please select Physician.");
			return;
		}
		op.save();
//		create billing for physician fee
		Physician p = (Physician) DBClient.getFirstRecord("SELECT a FROM Physician a WHERE a.personId="+op.physicianId);

		List ls = new ArrayList();
		BillingCodeReference bill = getBillingCode("OPD FEE");
		OutPatientLineItem line = new OutPatientLineItem();
		line.billingCode = bill.code;
		line.physicianId = p.personId;
		line.outPatientId = op.seq;
		ls.add(line);

		for (int i=1; i<=20; i++) {
			String lab = BeanUtil.getPropertyValue(op, "labRequest"+i)+"";
			if (lab==null || lab.isEmpty()) continue;
			
			bill = getBillingCode(lab+" FEE");
			line = new OutPatientLineItem();
			line.billingCode = bill.code;
			line.physicianId = p.personId;
			line.outPatientId = op.seq;
			ls.add(line);
		}
		DBClient.persistBean(ls);
		redisplayRecord();
	}

	protected void dicomViewer() {
		OutPatient p = (OutPatient) this.getBean();
//        DicomViewerPanel viewer = new DicomViewerPanel("For patient ["+p.patientName+"]");
        DicomViewerPanel viewer = new DicomViewerPanel();
        PanelUtil.showPanel2("Dicom Viewer", viewer);
	}

	protected void printReceipt() {
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(2);
		Invoice inv = (Invoice) tab.getBean();
		inv.printOR("Print OR");
	}

	protected void requestAdmission() {
            
	}

    private void createInsurance() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void addOtherInsurance() {
		OutPatient opd = (OutPatient) this.getBean();
		if (opd==null || opd.isEmptyKey()) {
			PanelUtil.showMessage(null, "Please select record.");
			return;
		}
		HospitalInsuranceClaim h = (HospitalInsuranceClaim) DBClient.getFirstRecord("SELECT a FROM HospitalInsuranceClaim a WHERE a.opdId="+opd.seq);
		if (h==null || h.isEmptyKey()) {
			h = new HospitalInsuranceClaim();
			h.opdId = opd.seq;
			h.patientId = opd.patientId;
			h.save();
		}
		PanelUtil.popupBeanTemplate(HospitalInsuranceClaim.class, "Other Insurance", true, h, true);
	}


}

    

