/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabPage;
import bean.Admission;
import bean.Patient;
import bean.Physician;
import bean.accounting.OutPatientLineItem;
import bean.reference.Department;
import bean.reference.Drug;
import bean.reference.LaboratoryTest;
import bean.reference.RoomHospital;

/**
 * 
 * @author Ebhoy
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {
		"releaseNumber", "room" })
@ChildRecords(value = {
		@template.ChildRecord(title = "Billing Items", entity = OutPatientLineItem.class, sql = "SELECT a FROM OutPatientLineItem a WHERE a.outPatientId=${seq}", fieldMapping = {
				"seq", "outPatientId" }, template = ChildTemplateListPopup.class),
		@template.ChildRecord(title = "Official Receipt", entity = InvoiceExt.class, sql = "SELECT a FROM Invoice a WHERE a.recordId=${seq}", fieldMapping = {
				"seq", "recordId" }, template = ChildTemplateListOnly.class) }, info = {
		@ParentAddInfo(title = "Instruction", gridCount = 2, displayFields = {""}, hideGroup = "0,1,2,3,5,6,7,8,9"),
		@ParentAddInfo(title = "Medication", displayFields = {""}, hideGroup = "0,1,2,4,5,6,7,8,9"),
		@ParentAddInfo(title = "Laboratory Request", gridCount = 2, displayFields = {""}, hideGroup = "0,1,2,3,4,6,7,8,9"),
		@ParentAddInfo(title = "Clearance", gridCount = 4, displayFields = {"releaseDate","releaseNumber"}, hideGroup = "0,1,3,4,5,6,7,8,9")

})
@DisplayGroups( {
		@DisplayGroup(title = "Insurance", gridCount = 6, fields = {
				"insuranceName", "insurancePolicyNumber",
				"amountShoulderedByInsurance" }, addInfoOnly = true),
		@DisplayGroup(title = "Discount", gridCount = 4, fields = {
				"otherFixedDiscount", "otherFixedDiscountReason",
				"usedDiscountMethod", "computedOverallDiscount" }, addInfoOnly = true),
		@DisplayGroup(title = "Clerance", gridCount = 6, fields = {
				"roomClearanceCheck", "roomClearanceCheckDate",
				"roomClearanceChecker", "nurseStationClearanceCheck",
				"nurseStationClearanceCheckDate",
				"nurseStationClearanceChecker", "physicianClearanceCheck",
				"physicianClearanceCheckDate", "physicianClearanceChecker",
				"accountingClearanceCheck", "accountingClearanceCheckDate",
				"accountingClearanceChecker", "cashierClearanceCheck",
				"cashierClearanceCheckDate", "cashierClearanceChecker" }, addInfoOnly = true),
		@DisplayGroup(title = "Drug", gridCount = 10, fields = { "drug1",
				"uOM1", "drugAmount1", "dosage1", "everyHowManyHour1",
				"lastDateTaken1", "drug2", "uOM2", "drugAmount2", "dosage2",
				"everyHowManyHour2", "lastDateTaken2", "drug3", "uOM3",
				"drugAmount3", "dosage3", "everyHowManyHour3",
				"lastDateTaken3", "drug4", "uOM4", "drugAmount4", "dosage4",
				"everyHowManyHour4", "lastDateTaken4", "drug5", "uOM5",
				"drugAmount5", "dosage5", "everyHowManyHour5",
				"lastDateTaken5", "drug6", "uOM6", "drugAmount6", "dosage6",
				"everyHowManyHour6", "lastDateTaken6", "drug7", "uOM7",
				"drugAmount7", "dosage7", "everyHowManyHour7",
				"lastDateTaken7", "drug8", "uOM8", "drugAmount8", "dosage8",
				"everyHowManyHour8", "lastDateTaken8", "drug9", "uOM9",
				"drugAmount9", "dosage9", "everyHowManyHour9",
				"lastDateTaken9", "drug10", "uOM10", "drugAmount10",
				"dosage10", "everyHowManyHour10", "lastDateTaken10" }, addInfoOnly = true),
		@DisplayGroup(title = "Instruction", gridCount = 10, fields = {
				"instruction1", "instruction2" }, addInfoOnly = true),
		@DisplayGroup(title = "Laboratory", gridCount = 8, fields = {
				"laboratory1", "laboratoryAmount1", "laboratoryRequestedDate1",
				"laboratoryLastDateTimeTaken1", "laboratory2",
				"laboratoryAmount2", "laboratoryRequestedDate2",
				"laboratoryLastDateTimeTaken2", "laboratory3",
				"laboratoryAmount3", "laboratoryRequestedDate3",
				"laboratoryLastDateTimeTaken3", "laboratory4",
				"laboratoryAmount4", "laboratoryRequestedDate4",
				"laboratoryLastDateTimeTaken4", "laboratory5",
				"laboratoryAmount5", "laboratoryRequestedDate5",
				"laboratoryLastDateTimeTaken5" }, addInfoOnly = true)

})
@ActionButtons( {
		@ActionButton(label = "Save", name = "btnSave"),
		@ActionButton(label = "Dicom Viewer", name = "btnDicomViewer"),
		@ActionButton(label = "Clearance Check", name = "btnClearanceCheck"),
		@ActionButton(label = "Release Selected Patient", name = "btnReleaseSelectedPatient"),
		@ActionButton(label = "Print Discharge Slip", name = "btnPrintDischargeSlip"),
		@ActionButton(label = "View Discharge Slip", name = "btnViewAvailableRooms"),
		@ActionButton(label = "Refresh", name = "btnRefresh") })
@Displays( {
//		@Display(name = "requestAdmission"),
//		@Display(name = "requestFrom"),
		@Display(name = "admissionDate"),
		@Display(name = "admissionFee"),
		@Display(name = "room", type = "PopSearch", linktoBean = RoomHospital.class),
		@Display(name = "roomRate"),
		@Display(name = "department", label = "Department", type = "PopSearch", linktoBean = Department.class),
		@Display(name = "patientId", label = "Patient", type = "PopSearch", linktoBean = Patient.class, gridFieldWidth = 5, width = -1),
		@Display(name = "physicianId", label = "Physician", type = "PopSearch", linktoBean = Physician.class, gridFieldWidth = 5, width = -1),
		// @Display(name="nurseId", label = "Nurse", type = "PopSearch",
		// linktoBean=Nurse.class, gridFieldWidth=5, width=-1),
//		@Display(name = "patientType"),
//		@Display(name = "isAdmit"),
		// @Display(name="priority"),
		// @Display(name="methodOfArrival"),
		// @Display(name="referralDepartment", label = "Department", type =
		// "PopSearch", linktoBean=Department.class),
		// @Display(name="familyHistory"),

		@Display(name = "releaseDate", addInfoOnly = true),
		@Display(name = "releaseNumber", addInfoOnly = true),

		@Display(name = "roomClearanceChecker", addInfoOnly = true, label = "Checker", labelTop = true, leftLabel = "Room"),
		@Display(name = "roomClearanceCheckDate", addInfoOnly = true, label = "Date", labelTop = true),
		@Display(name = "roomClearanceCheck", addInfoOnly = true, label = "Checked?", labelTop = true),

		@Display(name = "nurseStationClearanceChecker", addInfoOnly = true, label = "Nurse"),
		@Display(name = "nurseStationClearanceCheckDate", addInfoOnly = true, hideLabel = true),
		@Display(name = "nurseStationClearanceCheck", addInfoOnly = true, hideLabel = true),

		@Display(name = "physicianClearanceChecker", addInfoOnly = true, label = "Physician"),
		@Display(name = "physicianClearanceCheckDate", addInfoOnly = true, hideLabel = true),
		@Display(name = "physicianClearanceCheck", addInfoOnly = true, hideLabel = true),

		@Display(name = "accountingClearanceChecker", addInfoOnly = true, label = "Accounting"),
		@Display(name = "accountingClearanceCheckDate", addInfoOnly = true, hideLabel = true),
		@Display(name = "accountingClearanceCheck", addInfoOnly = true, hideLabel = true),

		@Display(name = "cashierClearanceChecker", addInfoOnly = true, label = "Cashier"),
		@Display(name = "cashierClearanceCheckDate", addInfoOnly = true, hideLabel = true),
		@Display(name = "cashierClearanceCheck", addInfoOnly = true, hideLabel = true),

		@Display(name = "hematologyRequest", addInfoOnly = true),
		@Display(name = "sputumRequest", addInfoOnly = true),
		@Display(name = "urinalysisRequest", addInfoOnly = true),
		@Display(name = "cctaRequest", addInfoOnly = true),
		@Display(name = "ultrasoundRequest", addInfoOnly = true),

		@Display(name = "receiptNumber", addInfoOnly = true, label = "OR #"),
		@Display(name = "receiptDate", addInfoOnly = true, label = "OR Date"),
		@Display(name = "overallAmountPaid", addInfoOnly = true, label = "Amount Paid"),
		// @Display(name="emergencyFee", addInfoOnly=true),
		@Display(name = "amountDeposit", addInfoOnly = true),
		// @Display(name="totalFee", addInfoOnly=true),

		@Display(name = "otherFixedDiscountReason", addInfoOnly = true, label = "Discount Reason"),
		@Display(name = "otherFixedDiscount", addInfoOnly = true, label = "Fixed Discount"),
		@Display(name = "usedDiscountMethod", addInfoOnly = true, label = "Discount Method"),
		@Display(name = "computedOverallDiscount", addInfoOnly = true, label = "Overall Discount"),

		@Display(name = "insuranceName"),
		@Display(name = "insurancePolicyNumber", label = "Policy #"),
		@Display(name = "amountShoulderedByInsurance", label = "Amount Shouldered"),

		@Display(name = "drug1", type = "PopSearch", labelTop = true, label = "Drug", leftLabel = "1", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM1", width = 50, type = "Combo", labelTop = true, label = "UOM", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount1",labelTop=true,label =
		// "Amount",leftLabel="1"),
		@Display(name = "dosage1", labelTop = true, label = "Dosage"),
		@Display(name = "everyHowManyHour1", width = 50, labelTop = true, label = "Interval Hour(s)", type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken1", labelTop = true, label = "Last Taken", enabled = false),

		@Display(name = "drug2", label = "2", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM2", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount2",label="2"),
		@Display(name = "dosage2", hideLabel = true),
		@Display(name = "everyHowManyHour2", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken2", hideLabel = true),

		@Display(name = "drug3", label = "3", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM3", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount3",label="3"),
		@Display(name = "dosage3", hideLabel = true),
		@Display(name = "everyHowManyHour3", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken3", hideLabel = true),

		@Display(name = "drug4", label = "4", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM4", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount4",label="4"),
		@Display(name = "dosage4", hideLabel = true),
		@Display(name = "everyHowManyHour4", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken4", hideLabel = true),

		@Display(name = "drug5", label = "5", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM5", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount5",label="5"),
		@Display(name = "dosage5", hideLabel = true),
		@Display(name = "everyHowManyHour5", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken5", hideLabel = true),

		@Display(name = "drug6", label = "6", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM6", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount6",label="6"),
		@Display(name = "dosage6", hideLabel = true),
		@Display(name = "everyHowManyHour6", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken6", hideLabel = true),

		@Display(name = "drug7", label = "7", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM7", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount7",label="7"),
		@Display(name = "dosage7", hideLabel = true),
		@Display(name = "everyHowManyHour7", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken7", hideLabel = true),

		@Display(name = "drug8", label = "8", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM8", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount8",label="8"),
		@Display(name = "dosage8", hideLabel = true),
		@Display(name = "everyHowManyHour8", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken8", hideLabel = true),

		@Display(name = "drug9", label = "9", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM9", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount9",label="9"),
		@Display(name = "dosage9", hideLabel = true),
		@Display(name = "everyHowManyHour9", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken9", hideLabel = true),

		@Display(name = "drug10", label = "10", type = "PopSearch", linktoBean = Drug.class, width = 150),
		@Display(name = "uOM10", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"MG", "ML", "PCS" }),
		// @Display(name="drugAmount10",label="10"),
		@Display(name = "dosage10", hideLabel = true),
		@Display(name = "everyHowManyHour10", width = 50, hideLabel = true, type = "Combo", modelCombo = {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24" }),
		@Display(name = "lastDateTaken10", hideLabel = true),

		@Display(name = "instruction1", type = "TextArea", gridFieldWidth = 11, width = 400),
		@Display(name = "instruction2", type = "TextArea", gridFieldWidth = 11, width = 400),

		@Display(name = "laboratory1", type = "PopSearch", labelTop = true, label = "Laboratory", leftLabel = "1", linktoBean = LaboratoryTest.class),
		@Display(name = "laboratoryAmount1", labelTop = true, label = "Amount"),
		@Display(name = "laboratoryRequestedDate1", labelTop = true, label = "Requested Date"),
		@Display(name = "laboratoryLastDateTimeTaken1", labelTop = true, label = "Last Taken"),

		@Display(name = "laboratory2", type = "PopSearch", label = "2", linktoBean = LaboratoryTest.class),
		@Display(name = "laboratoryAmount2", hideLabel = true),
		@Display(name = "laboratoryRequestedDate2", hideLabel = true),
		@Display(name = "laboratoryLastDateTimeTaken2", hideLabel = true),

		@Display(name = "laboratory3", label = "3", type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "laboratoryAmount3", hideLabel = true),
		@Display(name = "laboratoryRequestedDate3", hideLabel = true),
		@Display(name = "laboratoryLastDateTimeTaken3", hideLabel = true),

		@Display(name = "laboratory4", label = "4", type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "laboratoryAmount4", hideLabel = true),
		@Display(name = "laboratoryRequestedDate4", hideLabel = true),
		@Display(name = "laboratoryLastDateTimeTaken4", hideLabel = true),

		@Display(name = "laboratory5", label = "5", type = "PopSearch", linktoBean = LaboratoryTest.class),
		@Display(name = "laboratoryAmount5", hideLabel = true),
		@Display(name = "laboratoryRequestedDate5", hideLabel = true),
		@Display(name = "laboratoryLastDateTimeTaken5", hideLabel = true)

})
public class AdmissionExt extends Admission {
	public static void main(String[] args) {
		view(AdmissionExt.class);
	}
}
