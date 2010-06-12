/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateDisplayForm;
import template.screen.TemplateTabSinglePage;
import bean.OutPatient;
import bean.Patient;
import bean.Physician;
import bean.reference.Department;
import bean.reference.LaboratoryTest;

/**
 * 
 * @author Ebhoy
 */
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {
		"consultDate", "consultTime", "patientName", "physicianName",
		"patientType" }, criteriaSearch = { "consultDate", "patientId",
		"physicianId" })
@DiscriminatorColumn(name = "patientType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "OPD")
@ChildRecords(value = {
		@ChildRecord(title = "Demographics", entity = DemographicExt.class, sql = "SELECT a FROM Patient a WHERE a.personId=${patientId}", template = ChildTemplateDisplayForm.class) }, info = {
		@ParentAddInfo(title = "Laboratory/Diagnostic Request", gridCount = 4, displayFields = { "" }, hideGroup = "0,1")
})
@DisplayGroups( {
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
})
@Reports( { 
	@template.Report(reportFile = "OutPatientList", reportTitle = "OPD List", reportSql = "") 
})
public class OutPatientClinicExt extends OutPatient {
	public static void main(String[] args) {
		view(OutPatientClinicExt.class);
	}
}
