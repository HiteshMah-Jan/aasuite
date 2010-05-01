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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopup;
import template.screen.TemplateTabSinglePage;
import util.DateUtil;
import bean.accounting.OutPatientLineItem;
import bean.extension.InvoiceExt;
import bean.person.PatientMedicalRecord;
import bean.reference.Department;
import bean.reference.LaboratoryTest;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "OutPatient")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 6, 
	columnSearch = {"patientName","consultDate", "physicianName", "priority"},
	criteriaSearch = {"patientId","consultDate", "physicianId", "priority"}
)
@DiscriminatorValue(value = "ER")
@ChildRecords(
		value = {
				@ChildRecord(entity = PatientMedicalRecord.class, fieldMapping = {"patientId", "patientId"}, sql = "SELECT a FROM PatientMedicalRecord a WHERE a.patientId=${patientId}"),
				@ChildRecord(title="Billing Items", entity = OutPatientLineItem.class, sql = "SELECT a FROM OutPatientLineItem a WHERE a.outPatientId=${seq}", fieldMapping={"seq","outPatientId"},template=ChildTemplateListPopup.class),
				@ChildRecord(title="Official Receipt", entity = InvoiceExt.class, sql = "SELECT a FROM Invoice a WHERE a.recordId=${seq}", fieldMapping={"seq","recordId"},template=ChildTemplateListOnly.class)
				},
info = {
    @ParentAddInfo(title = "Treatment", gridCount = 6,
    displayFields = {"allergy", "currentMedication", "tetanus", "smoker",
                     "alcoholUse", "drugUse", "bloodPressure", "temperature", "pulseRate", "respiratoryRate", "weight", "frequency"}, hideGroup = "0,1,2,3"),
    @ParentAddInfo(title = "Laboratory/Diagnostic Request", gridCount = 4,
    displayFields = {""}, hideGroup = "0,1"),
    @ParentAddInfo(title = "Accounting",
    displayFields = {"receiptNumber", "receiptDate", "emergencyFee", "amountDeposit", "totalFee", "overallAmountPaid"}, hideGroup = "2,3")
})
@ActionButtons({
    @ActionButton(label = "Generate Billing Items", name = "btnGenerateItems"),
    @ActionButton(label = "Dicom Viewer", name = "btnDicomViewer"),
    @ActionButton(label = "Request Admission", name = "btnRequestAdmission")
//    @ActionButton(label = "Accept Payment", name = "btnPayNow"),
//    @ActionButton(label = "Print Receipt", name = "btnPrintReceipt")
})
@DisplayGroups({
    @DisplayGroup(title = "Insurance", gridCount = 6, fields = {"insuranceName","insurancePolicyNumber","amountShoulderedByInsurance"}, addInfoOnly=true),
    @DisplayGroup(title = "Discount", gridCount = 4, fields = {"otherFixedDiscount","otherFixedDiscountReason","usedDiscountMethod","computedOverallDiscount"}, addInfoOnly=true),
    @DisplayGroup(title = "Test Request", gridCount = 4,
	fields = {"labRequest1", "labRequest2", "labRequest3", "labRequest4", "labRequest5", "labRequest6", "labRequest7", "labRequest8", "labRequest9", "labRequest10"}, addInfoOnly = true),
	@DisplayGroup(title = "Other Test Request", gridCount = 4,
    fields = {"labRequest11", "labRequest12", "labRequest13", "labRequest14", "labRequest15", "labRequest16", "labRequest17", "labRequest18", "labRequest19", "labRequest20"}, addInfoOnly = true)
})
@Displays({
    @Display(name="department", label = "Department", type="PopSearch", linktoBean=Department.class),
    @Display(name="consultDate", type = "Label", label = "Emergency Date"),
    @Display(name="consultTime", type="Label", label = "Emergency Time"),
    @Display(name="patientId", label = "Patient", type = "PopSearch", linktoBean=Patient.class, gridFieldWidth=5, width=-1),
    @Display(name="physicianId", label = "Physician", type = "PopSearch", linktoBean=Physician.class, gridFieldWidth=5, width=-1),
//    @Display(name="nurseId", label = "Nurse", type = "PopSearch", linktoBean=Nurse.class, gridFieldWidth=5, width=-1),
//    @Display(name="patientType"),
//    @Display(name="isAdmit"),
//    @Display(name="priority"),
    @Display(name="methodOfArrival"),
    @Display(name="referralDepartment", label = "Refer To Dept.", type = "PopSearch", linktoBean=Department.class),
    @Display(name="familyHistory"),

    @Display(name="recommendation", type="TextArea", gridFieldWidth=5, width=-1),

    @Display(name="smoker", addInfoOnly=true),
    @Display(name="alcoholUse", addInfoOnly=true),
    @Display(name="drugUse", addInfoOnly=true),
    @Display(name="allergy", addInfoOnly=true),
    @Display(name="currentMedication", addInfoOnly=true),
    @Display(name="tetanus", addInfoOnly=true),
    @Display(name="bloodPressure", addInfoOnly=true),
    @Display(name="temperature", addInfoOnly=true),
    @Display(name="pulseRate", addInfoOnly=true),
    @Display(name="respiratoryRate", addInfoOnly=true),
    @Display(name="weight", addInfoOnly=true),
    @Display(name="frequency", addInfoOnly=true),

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

    @Display(name="receiptNumber", addInfoOnly=true, label="OR #"),
    @Display(name="receiptDate", addInfoOnly=true, label="OR Date"),
    @Display(name="overallAmountPaid", addInfoOnly=true, label="Amount Paid"),
    @Display(name="emergencyFee", addInfoOnly=true),
    @Display(name="amountDeposit", addInfoOnly=true),

    @Display(name="otherFixedDiscountReason", addInfoOnly=true, label="Discount Reason"),
    @Display(name="otherFixedDiscount", addInfoOnly=true, label="Fixed Discount"),
    @Display(name="usedDiscountMethod", addInfoOnly=true, label="Discount Method"),
    @Display(name="computedOverallDiscount", addInfoOnly=true, label="Overall Discount"),

    @Display(name="insuranceName"),
    @Display(name="insurancePolicyNumber", label="Policy #"),
    @Display(name="amountShoulderedByInsurance", label="Amount Shouldered")
})
@Reports( {
	@template.Report(reportFile = "EmergencyList", reportTitle = "ER List", reportSql = "")
})
public class Emergency extends OutPatient implements Serializable {

	public Emergency() {
		consultDate = new Date();
		consultTime = DateUtil.getTime();
//		put common lab request
		labRequest1 = "ABG";
		labRequest2 = "CBC";
		patientType = "ER";
	}

	public static void main(String[] args) {
		view(Emergency.class);
	}

    @Column(name = "emergencyDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date emergencyDate;
    @Column(name = "emergencyTime")
    public String emergencyTime;
    @Column(name = "priority")
    public boolean priority;
    @Column(name = "nurseId")
    public int nurseId;
    @Column(name = "methodOfArrival")
    public String methodOfArrival;

    @Column(name = "allergy")
    public String allergy;
    @Column(name = "currentMedication")
    public String currentMedication;
    @Column(name = "tetanus")
    public String tetanus;
    //Vital Signs
    @Column(name = "smoker")
    public boolean smoker;
    @Column(name = "alcoholUse")
    public boolean alcoholUse;
    @Column(name = "drugUse")
    public boolean drugUse;
    @Column(name = "emergencyFee")
    public double emergencyFee;
    @Column(name = "amountDeposit")
    public double amountDeposit;
    @Column(name = "referralDepartment")
    public String referralDepartment;
    @Column(name = "familyHistory")
    public String familyHistory;
    @Column(name = "bloodPressure")
    public String bloodPressure;
    @Column(name = "temperature")
    public String temperature;
    @Column(name = "pulseRate")
    public String pulseRate;
    @Column(name = "respiratoryRate")
    public String respiratoryRate;
    @Column(name = "weight")
    public String weight;
    @Column(name = "frequency")
    public String frequency;


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

    public String getLabRequest1() {
        return labRequest1;
    }

    public void setLabRequest1(String labRequest1) {
        this.labRequest1 = labRequest1;
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

    public String getLabRequest2() {
        return labRequest2;
    }

    public void setLabRequest2(String labRequest2) {
        this.labRequest2 = labRequest2;
    }

    public String getLabRequest20() {
        return labRequest20;
    }

    public void setLabRequest20(String labRequest20) {
        this.labRequest20 = labRequest20;
    }

    public boolean isAlcoholUse() {
        return alcoholUse;
    }

    public void setAlcoholUse(boolean alcoholUse) {
        this.alcoholUse = alcoholUse;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public double getAmountDeposit() {
        return amountDeposit;
    }

    public void setAmountDeposit(double amountDeposit) {
        this.amountDeposit = amountDeposit;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public boolean isDrugUse() {
        return drugUse;
    }

    public void setDrugUse(boolean drugUse) {
        this.drugUse = drugUse;
    }

    public Date getEmergencyDate() {
        return emergencyDate;
    }

    public void setEmergencyDate(Date emergencyDate) {
        this.emergencyDate = emergencyDate;
    }

    public double getEmergencyFee() {
        return emergencyFee;
    }

    public void setEmergencyFee(double emergencyFee) {
        this.emergencyFee = emergencyFee;
    }

    public String getEmergencyTime() {
        return emergencyTime;
    }

    public void setEmergencyTime(String emergencyTime) {
        this.emergencyTime = emergencyTime;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMethodOfArrival() {
        return methodOfArrival;
    }

    public void setMethodOfArrival(String methodOfArrival) {
        this.methodOfArrival = methodOfArrival;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getReferralDepartment() {
        return referralDepartment;
    }

    public void setReferralDepartment(String referralDepartment) {
        this.referralDepartment = referralDepartment;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTetanus() {
        return tetanus;
    }

    public void setTetanus(String tetanus) {
        this.tetanus = tetanus;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
