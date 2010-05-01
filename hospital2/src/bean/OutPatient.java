/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.ChildRecord;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateDisplayForm;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;
import util.DBClient;
import util.DateUtil;
import util.NumberToWordConverter;
import bean.accounting.Invoice;
import bean.accounting.OutPatientLineItem;
import bean.admin.AppConfig;
import bean.extension.DemographicExt;
import bean.extension.InvoiceExt;
import bean.person.PatientMedicalRecord;
import bean.reference.Department;
import bean.reference.Insurance;
import bean.reference.LaboratoryTest;
import constants.UserInfo;

/**
 * 
 * @author Ebhoy
 */
@Entity
@Table(name = "OutPatient")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {
		"consultDate", "consultTime", "patientName", "physicianName",
		"patientType" }, criteriaSearch = { "consultDate", "patientId",
		"physicianId" })
@DiscriminatorColumn(name = "patientType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "OPD")
@ChildRecords(value = {
		@template.ChildRecord(title = "Billing Items", entity = OutPatientLineItem.class, sql = "SELECT a FROM OutPatientLineItem a WHERE a.outPatientId=${seq}", fieldMapping = {
				"seq", "outPatientId" }, template = ChildTemplateListPopupDownButton.class),
		@template.ChildRecord(title = "Official Receipt", entity = InvoiceExt.class, sql = "SELECT a FROM Invoice a WHERE a.recordId=${seq}", fieldMapping = {
				"seq", "recordId" }, template = ChildTemplateListOnly.class),
		@template.ChildRecord(title = "Demographics", entity = DemographicExt.class, sql = "SELECT a FROM Patient a WHERE a.personId=${patientId}", template = ChildTemplateDisplayForm.class) }, info = {
		@ParentAddInfo(title = "Laboratory/Diagnostic Request", gridCount = 4, displayFields = { "" }, hideGroup = "0,1"),
		@ParentAddInfo(title = "Accounting", displayFields = { "" }, hideGroup = "2,3") })
@DisplayGroups( {
		@DisplayGroup(title = "Insurance", gridCount = 4, fields = {
				"philHealthNo", "insuranceName", "insurancePolicyNumber",
				"amountShoulderedByInsurance" }, addInfoOnly = true),
		@DisplayGroup(title = "Fees, Discount And Balance", gridCount = 4, fields = {
				"opdFee", "otherFee", "overallAmountPaid", "totalBalance",
				"otherFixedDiscount", "otherFixedDiscountReason" }, addInfoOnly = true),
		@DisplayGroup(title = "Test Request", gridCount = 4, fields = {
				"labRequest1", "labRequest2", "labRequest3", "labRequest4",
				"labRequest5", "labRequest6", "labRequest7", "labRequest8",
				"labRequest9", "labRequest10" }, addInfoOnly = true),
		@DisplayGroup(title = "Other Test Request", gridCount = 4, fields = {
				"labRequest11", "labRequest12", "labRequest13", "labRequest14",
				"labRequest15", "labRequest16", "labRequest17", "labRequest18",
				"labRequest19", "labRequest20" }, addInfoOnly = true) })
@ActionButtons( {
		@ActionButton(label = "Generate Billing Items", name = "btnGenerateItems"),
		@ActionButton(label = "Dicom Viewer", name = "btnDicomViewer"),
		@ActionButton(label = "Request Admission", name = "btnRequestAdmission"),
		// @ActionButton(label = "Accept Payment", name = "btnPayNow"),
		// @ActionButton(label = "Print Receipt", name = "btnPrintReceipt"),
		@ActionButton(label = "PhilHealth", name = "btnPhilHealthNo"),
		@ActionButton(label = "Other Insurance", name = "btnOtherInsurance")
		

})
@Displays( {
		@Display(name = "consultDate"),
		@Display(name = "consultTime", type = "Time"),
		@Display(name = "department", label = "Department", type = "PopSearch", linktoBean = Department.class, gridFieldWidth = 3, width = -1),
		@Display(name = "patientId", label = "Patient", type = "PopSearch", linktoBean = Patient.class, gridFieldWidth = 3, width = -1),
		@Display(name = "physicianId", label = "Physician", type = "PopSearch", linktoBean = Physician.class, gridFieldWidth = 3, width = -1),
		@Display(name = "patientType"),
		@Display(name = "nextAppointmentDate", label = "Next App."),
		// @Display(name="isAdmit"),
		@Display(name = "recommendation", type = "TextArea", gridFieldWidth = 3, width = 300, label = "Recommendation/Treatment", labelTop = true, height = 80),

		@Display(name = "labRequest1", addInfoOnly = true, label = "1", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest2", addInfoOnly = true, label = "2", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest3", addInfoOnly = true, label = "3", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest4", addInfoOnly = true, label = "4", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest5", addInfoOnly = true, label = "5", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest6", addInfoOnly = true, label = "6", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest7", addInfoOnly = true, label = "7", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest8", addInfoOnly = true, label = "8", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest9", addInfoOnly = true, label = "9", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest10", addInfoOnly = true, label = "10", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest11", addInfoOnly = true, label = "11", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest12", addInfoOnly = true, label = "12", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest13", addInfoOnly = true, label = "13", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest14", addInfoOnly = true, label = "14", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest15", addInfoOnly = true, label = "15", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest16", addInfoOnly = true, label = "16", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest17", addInfoOnly = true, label = "17", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest18", addInfoOnly = true, label = "18", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest19", addInfoOnly = true, label = "19", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "labRequest20", addInfoOnly = true, label = "20", width = 200, type = "PopSearch", linktoBean = LaboratoryTest.class),

		// @Display(name="hematologyRequest", addInfoOnly=true),
		// @Display(name="sputumRequest", addInfoOnly=true),
		// @Display(name="urinalysisRequest", addInfoOnly=true),
		// @Display(name="cctaRequest", addInfoOnly=true),
		// @Display(name="ultrasoundRequest", addInfoOnly=true),

		@Display(name = "opdFee", addInfoOnly = true, label = "OPD Fee"),
		@Display(name = "otherFee", addInfoOnly = true),
		@Display(name = "otherFixedDiscountReason", addInfoOnly = true, label = "Discount Reason"),
		@Display(name = "otherFixedDiscount", addInfoOnly = true, label = "Fixed Discount"),

		@Display(name = "overallAmountPaid", addInfoOnly = true, label = "Total Amount Paid", type = "Label"),
		@Display(name = "totalBalance", addInfoOnly = true, type = "Label"),

		@Display(name = "philHealthNo"),
		@Display(name = "insuranceName", type = "PopSearch", linktoBean = Insurance.class),
		@Display(name = "insurancePolicyNumber", label = "Policy #"),
		@Display(name = "amountShoulderedByInsurance", label = "Amount Shouldered") })
@Reports( { 
	@template.Report(reportFile = "OutPatientList", reportTitle = "OPD List", reportSql = ""), 
	@template.Report(reportFile = "PhilHealthClaimList", reportTitle = "PhilHealth Claim List", reportSql = "") 
})
public class OutPatient extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(OutPatient.class);
	}

	public OutPatient() {
		consultDate = new Date();
		consultTime = DateUtil.getTime();
		// put common lab request
		labRequest1 = "ABG";
		labRequest2 = "CBC";
	}

	@Id
	@Column(name = "seq", nullable = false, length = 30)
	public Integer seq;
	@Column(name = "consultDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date consultDate;
	@Column(name = "consultTime")
	public String consultTime;
	@Column(name = "patientId", nullable = false)
	public int patientId;
	@Column(name = "physicianId", nullable = false)
	public int physicianId;
	@Column(name = "department")
	public String department;
	@Column(name = "patientName")
	public String patientName;
	@Column(name = "physicianName")
	public String physicianName;
	@Column(name = "medicineAllergy")
	public String medicineAllergy;
	@Column(name = "foodAllergy")
	public String foodAllergy;
	@Column(name = "recommendation", length = 4000)
	public String recommendation;
	@Column(name = "opdFee")
	public double opdFee;
	@Column(name = "otherFee")
	public double otherFee;
	@Column(name = "totalAmount")
	public double totalAmount;
	@Column(name = "totalBalance")
	public double totalBalance;
	@Column(name = "nextAppointmentDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date nextAppointmentDate;
	@Column(name = "patientType")
	public String patientType;
	@Column(name = "isAdmit")
	public boolean isAdmit;
	@Column(name = "insuranceName")
	public String insuranceName;
	@Column(name = "insurancePolicyNumber")
	public String insurancePolicyNumber;
	@Column(name = "amountShoulderedByInsurance")
	public double amountShoulderedByInsurance;
	@Column(name = "receiptNumber")
	public String receiptNumber;
	@Column(name = "receiptDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date receiptDate;
	@Column(name = "overallAmountPaid")
	public double overallAmountPaid;
	@Column(name = "computedOverallDiscount")
	public double computedOverallDiscount;
	@Column(name = "otherFixedDiscount")
	public double otherFixedDiscount;
	@Column(name = "otherFixedDiscountReason")
	public String otherFixedDiscountReason;
	@Column(name = "finalized")
	public boolean finalized;
	@Column(name = "finalizedDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date finalizedDate;
	@Column(name = "usedDiscountMethod")
	public String usedDiscountMethod;

	@Column(name = "adjustedDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	public Date adjustedDate;
	@Column(name = "adjustedBy")
	public String adjustedBy;
	@Column(name = "schoolYear")
	public String schoolYear;

	@Column(name = "labRequest1")
	public String labRequest1;
	@Column(name = "labRequest2")
	public String labRequest2;
	@Column(name = "labRequest3")
	public String labRequest3;
	@Column(name = "labRequest4")
	public String labRequest4;
	@Column(name = "labRequest5")
	public String labRequest5;
	@Column(name = "labRequest6")
	public String labRequest6;
	@Column(name = "labRequest7")
	public String labRequest7;
	@Column(name = "labRequest8")
	public String labRequest8;
	@Column(name = "labRequest9")
	public String labRequest9;
	@Column(name = "labRequest10")
	public String labRequest10;
	@Column(name = "labRequest11")
	public String labRequest11;
	@Column(name = "labRequest12")
	public String labRequest12;
	@Column(name = "labRequest13")
	public String labRequest13;
	@Column(name = "labRequest14")
	public String labRequest14;
	@Column(name = "labRequest15")
	public String labRequest15;
	@Column(name = "labRequest16")
	public String labRequest16;
	@Column(name = "labRequest17")
	public String labRequest17;
	@Column(name = "labRequest18")
	public String labRequest18;
	@Column(name = "labRequest19")
	public String labRequest19;
	@Column(name = "labRequest20")
	public String labRequest20;
	@Column(name = "philHealthNo")
	public String philHealthNo;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getConsultDate() {
		return consultDate;
	}

	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}

	public String getConsultTime() {
		return consultTime;
	}

	public void setConsultTime(String consultTime) {
		this.consultTime = consultTime;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getMedicineAllergy() {
		return medicineAllergy;
	}

	public void setMedicineAllergy(String medicineAllergy) {
		this.medicineAllergy = medicineAllergy;
	}

	public String getFoodAllergy() {
		return foodAllergy;
	}

	public void setFoodAllergy(String foodAllergy) {
		this.foodAllergy = foodAllergy;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public double getOpdFee() {
		return opdFee;
	}

	public void setOpdFee(double opdFee) {
		this.opdFee = opdFee;
	}

	public double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(double otherFee) {
		this.otherFee = otherFee;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Date getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(Date nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public boolean isAdmit() {
		return isAdmit;
	}

	public void setAdmit(boolean isAdmit) {
		this.isAdmit = isAdmit;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	public double getAmountShoulderedByInsurance() {
		return amountShoulderedByInsurance;
	}

	public void setAmountShoulderedByInsurance(double amountShoulderedByInsurance) {
		this.amountShoulderedByInsurance = amountShoulderedByInsurance;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public double getOverallAmountPaid() {
		return overallAmountPaid;
	}

	public void setOverallAmountPaid(double overallAmountPaid) {
		this.overallAmountPaid = overallAmountPaid;
	}

	public double getComputedOverallDiscount() {
		return computedOverallDiscount;
	}

	public void setComputedOverallDiscount(double computedOverallDiscount) {
		this.computedOverallDiscount = computedOverallDiscount;
	}

	public double getOtherFixedDiscount() {
		return otherFixedDiscount;
	}

	public void setOtherFixedDiscount(double otherFixedDiscount) {
		this.otherFixedDiscount = otherFixedDiscount;
	}

	public String getOtherFixedDiscountReason() {
		return otherFixedDiscountReason;
	}

	public void setOtherFixedDiscountReason(String otherFixedDiscountReason) {
		this.otherFixedDiscountReason = otherFixedDiscountReason;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public Date getFinalizedDate() {
		return finalizedDate;
	}

	public void setFinalizedDate(Date finalizedDate) {
		this.finalizedDate = finalizedDate;
	}

	public String getUsedDiscountMethod() {
		return usedDiscountMethod;
	}

	public void setUsedDiscountMethod(String usedDiscountMethod) {
		this.usedDiscountMethod = usedDiscountMethod;
	}

	public Date getAdjustedDate() {
		return adjustedDate;
	}

	public void setAdjustedDate(Date adjustedDate) {
		this.adjustedDate = adjustedDate;
	}

	public String getAdjustedBy() {
		return adjustedBy;
	}

	public void setAdjustedBy(String adjustedBy) {
		this.adjustedBy = adjustedBy;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getLabRequest1() {
		return labRequest1;
	}

	public void setLabRequest1(String labRequest1) {
		this.labRequest1 = labRequest1;
	}

	public String getLabRequest2() {
		return labRequest2;
	}

	public void setLabRequest2(String labRequest2) {
		this.labRequest2 = labRequest2;
	}

	public String getLabRequest3() {
		return labRequest3;
	}

	public void setLabRequest3(String labRequest3) {
		this.labRequest3 = labRequest3;
	}

	public String getLabRequest4() {
		return labRequest4;
	}

	public void setLabRequest4(String labRequest4) {
		this.labRequest4 = labRequest4;
	}

	public String getLabRequest5() {
		return labRequest5;
	}

	public void setLabRequest5(String labRequest5) {
		this.labRequest5 = labRequest5;
	}

	public String getLabRequest6() {
		return labRequest6;
	}

	public void setLabRequest6(String labRequest6) {
		this.labRequest6 = labRequest6;
	}

	public String getLabRequest7() {
		return labRequest7;
	}

	public void setLabRequest7(String labRequest7) {
		this.labRequest7 = labRequest7;
	}

	public String getLabRequest8() {
		return labRequest8;
	}

	public void setLabRequest8(String labRequest8) {
		this.labRequest8 = labRequest8;
	}

	public String getLabRequest9() {
		return labRequest9;
	}

	public void setLabRequest9(String labRequest9) {
		this.labRequest9 = labRequest9;
	}

	public String getLabRequest10() {
		return labRequest10;
	}

	public void setLabRequest10(String labRequest10) {
		this.labRequest10 = labRequest10;
	}

	public String getLabRequest11() {
		return labRequest11;
	}

	public void setLabRequest11(String labRequest11) {
		this.labRequest11 = labRequest11;
	}

	public String getLabRequest12() {
		return labRequest12;
	}

	public void setLabRequest12(String labRequest12) {
		this.labRequest12 = labRequest12;
	}

	public String getLabRequest13() {
		return labRequest13;
	}

	public void setLabRequest13(String labRequest13) {
		this.labRequest13 = labRequest13;
	}

	public String getLabRequest14() {
		return labRequest14;
	}

	public void setLabRequest14(String labRequest14) {
		this.labRequest14 = labRequest14;
	}

	public String getLabRequest15() {
		return labRequest15;
	}

	public void setLabRequest15(String labRequest15) {
		this.labRequest15 = labRequest15;
	}

	public String getLabRequest16() {
		return labRequest16;
	}

	public void setLabRequest16(String labRequest16) {
		this.labRequest16 = labRequest16;
	}

	public String getLabRequest17() {
		return labRequest17;
	}

	public void setLabRequest17(String labRequest17) {
		this.labRequest17 = labRequest17;
	}

	public String getLabRequest18() {
		return labRequest18;
	}

	public void setLabRequest18(String labRequest18) {
		this.labRequest18 = labRequest18;
	}

	public String getLabRequest19() {
		return labRequest19;
	}

	public void setLabRequest19(String labRequest19) {
		this.labRequest19 = labRequest19;
	}

	public String getLabRequest20() {
		return labRequest20;
	}

	public void setLabRequest20(String labRequest20) {
		this.labRequest20 = labRequest20;
	}

	public String getPhilHealthNo() {
		return philHealthNo;
	}

	public void setPhilHealthNo(String philHealthNo) {
		this.philHealthNo = philHealthNo;
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria);
	}

	@Override
	public void save() {
		if (patientName == null && patientId > 0) {
			try {
				patientName = Patient.extractObject(patientId).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (physicianName == null && physicianId > 0) {
			try {
				physicianName = DBClient.getFirstRecord(
						"SELECT a FROM Person a WHERE a.personId="
								+ physicianId).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<Invoice> lst = DBClient
				.getList("SELECT a FROM Invoice a WHERE a.recordId=" + seq);
		if (lst != null) {
			overallAmountPaid = 0;
			for (Invoice inv : lst) {
				overallAmountPaid += inv.totalAmount;
			}
		}
		opdFee = otherFixedDiscount = 0;
		List<OutPatientLineItem> lstLine = DBClient
				.getList("SELECT a FROM OutPatientLineItem a WHERE a.outPatientId="
						+ seq);
		for (OutPatientLineItem item : lstLine) {
			opdFee += item.amount;
			otherFixedDiscount += item.discount;
		}
		totalAmount = opdFee + otherFee - amountShoulderedByInsurance
				- otherFixedDiscount;
		totalBalance = totalAmount - overallAmountPaid;
		super.save();
	}

	public Invoice createInvoice(double amount, String description) {
		Invoice inv = new Invoice();
		inv.schoolYear = schoolYear;
		inv.invoiceDate = constants.Constants.useDate;
		inv.accountNumber = inv.accountType = "OTHERPAYMENT";
		inv.totalAmount = inv.allAmount = amount;
		inv.accountType = "OUTPATIENT";
		inv.recordId = seq;
		inv.description = description;
		inv.paymentTerms = "CASH";
		inv.orDate = new Date();
		inv.cashier = UserInfo.getUserName();
		String currencyWord = AppConfig.getCurrencyWord();
		String centavoWord = AppConfig.getCentWord();
		inv.amountInWord = NumberToWordConverter.convertMoney(inv.totalAmount,
				currencyWord, centavoWord);
		Person cust = Person.extractObject(patientId);
		inv.payer = cust.toString();
		if (cust != null) {
			inv.billTo = cust.personId + "";
			inv.shipTo = (cust.getAddress());
			inv.shipToAddress = (cust.getAddress());
			inv.studentNumber = cust.studentNumber;
			inv.gradeLevel = cust.gradeLevel;
			inv.section = cust.section;
		}
		return inv;
	}
}
