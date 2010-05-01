/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.accounting.OutPatientLineItem;
import bean.extension.InvoiceExt;
import bean.person.PatientBillingItem;
import bean.reference.Department;
import bean.reference.RoomHospital;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import template.ActionButton;
import template.ActionButtons;
import util.DBClient;
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
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "OutPatient")
@DiscriminatorValue(value = "IPD")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"releaseNumber", "room"})
@ChildRecords(
		value = {
				@template.ChildRecord(title="Billing Items", entity = OutPatientLineItem.class, sql = "SELECT a FROM OutPatientLineItem a WHERE a.outPatientId=${seq}", fieldMapping={"seq","outPatientId"},template=ChildTemplateListPopup.class),
				@template.ChildRecord(title="Official Receipt", entity = InvoiceExt.class, sql = "SELECT a FROM Invoice a WHERE a.recordId=${seq}", fieldMapping={"seq","recordId"},template=ChildTemplateListOnly.class)
				},
info = {
	@ParentAddInfo(title = "Treatment", gridCount=2,
    		displayFields = {"allergy","currentMedication","tetanus","smoker",
				"alcoholUse","drugUse","bloodPressure","temperature","pulseRate","respiratoryRate","weight","frequency"}, hideGroup="0,1,2"),
    @ParentAddInfo(title = "Laboratory Request", gridCount=2,
    		displayFields = {"hematologyRequest", "sputumRequest", "urinalysisRequest", "cctaRequest", "ultrasoundRequest"}, hideGroup="0,1,2"),
    @ParentAddInfo(title = "Accounting", hideGroup="2",
    		displayFields = {"receiptNumber","receiptDate","emergencyFee","amountDeposit","totalFee","overallAmountPaid"}),
    @ParentAddInfo(title = "Clearance", gridCount=6, hideGroup="0,1", 
    		displayFields = {"releaseDate","releaseNumber"})
})
@DisplayGroups({
    @DisplayGroup(title = "Insurance", gridCount = 6, fields = {"insuranceName","insurancePolicyNumber","amountShoulderedByInsurance"}, addInfoOnly=true),
    @DisplayGroup(title = "Discount", gridCount = 4, fields = {"otherFixedDiscount","otherFixedDiscountReason","usedDiscountMethod","computedOverallDiscount"}, addInfoOnly=true),
    @DisplayGroup(title = "Clerance", gridCount = 6, fields = {"roomClearanceCheck","roomClearanceCheckDate",
			"roomClearanceChecker","nurseStationClearanceCheck","nurseStationClearanceCheckDate","nurseStationClearanceChecker",
			"physicianClearanceCheck","physicianClearanceCheckDate","physicianClearanceChecker","accountingClearanceCheck",
			"accountingClearanceCheckDate","accountingClearanceChecker","cashierClearanceCheck","cashierClearanceCheckDate","cashierClearanceChecker"}, addInfoOnly=true)
})
@ActionButtons({
    @ActionButton(label="Save", name="btnSave"),
    @ActionButton(label="Dicom Viewer", name = "btnDicomViewer"),
    @ActionButton(label="Clearance Check", name="btnClearanceCheck"),
    @ActionButton(label="Release Selected Patient", name="btnReleaseSelectedPatient"),
    @ActionButton(label="Print Discharge Slip", name="btnPrintDischargeSlip"),
    @ActionButton(label="View Discharge Slip", name="btnViewAvailableRooms"),
    @ActionButton(label="Refresh", name="btnRefresh")
})
@Displays({
    @Display(name="requestAdmission"),
    @Display(name="requestFrom"),
    @Display(name="admissionDate"),
    @Display(name="admissionFee"),
    @Display(name="room", type="PopSearch", linktoBean=RoomHospital.class),
    @Display(name="roomRate"),
    @Display(name="department", label = "Department", type = "PopSearch", linktoBean=Department.class),
    @Display(name="patientId", label = "Patient", type = "PopSearch", linktoBean=Patient.class, gridFieldWidth=5, width=-1),
    @Display(name="physicianId", label = "Physician", type = "PopSearch", linktoBean=Physician.class, gridFieldWidth=5, width=-1),
//    @Display(name="nurseId", label = "Nurse", type = "PopSearch", linktoBean=Nurse.class, gridFieldWidth=5, width=-1),
    @Display(name="patientType"),
    @Display(name="isAdmit"),
//    @Display(name="priority"),
//    @Display(name="methodOfArrival"),
//    @Display(name="referralDepartment", label = "Department", type = "PopSearch", linktoBean=Department.class),
//    @Display(name="familyHistory"),

    @Display(name="releaseDate", addInfoOnly=true),
    @Display(name="releaseNumber", addInfoOnly=true),
    
    @Display(name="roomClearanceChecker", addInfoOnly=true, label="Checker", labelTop=true, leftLabel="Room"),
    @Display(name="roomClearanceCheckDate", addInfoOnly=true, label="Date", labelTop=true),
    @Display(name="roomClearanceCheck", addInfoOnly=true, label="Checked?", labelTop=true),
    
    @Display(name="nurseStationClearanceChecker", addInfoOnly=true, label="Nurse"),
    @Display(name="nurseStationClearanceCheckDate", addInfoOnly=true, hideLabel=true),
    @Display(name="nurseStationClearanceCheck", addInfoOnly=true, hideLabel=true),

    @Display(name="physicianClearanceChecker", addInfoOnly=true, label="Physician"),
    @Display(name="physicianClearanceCheckDate", addInfoOnly=true, hideLabel=true),
    @Display(name="physicianClearanceCheck", addInfoOnly=true, hideLabel=true),

    @Display(name="accountingClearanceChecker", addInfoOnly=true, label="Accounting"),
    @Display(name="accountingClearanceCheckDate", addInfoOnly=true, hideLabel=true),
    @Display(name="accountingClearanceCheck", addInfoOnly=true, hideLabel=true),

    @Display(name="cashierClearanceChecker", addInfoOnly=true, label="Cashier"),
    @Display(name="cashierClearanceCheckDate", addInfoOnly=true, hideLabel=true),
    @Display(name="cashierClearanceCheck", addInfoOnly=true, hideLabel=true),
    
    @Display(name="hematologyRequest", addInfoOnly=true),
    @Display(name="sputumRequest", addInfoOnly=true),
    @Display(name="urinalysisRequest", addInfoOnly=true),
    @Display(name="cctaRequest", addInfoOnly=true),
    @Display(name="ultrasoundRequest", addInfoOnly=true),

    @Display(name="receiptNumber", addInfoOnly=true, label="OR #"),
    @Display(name="receiptDate", addInfoOnly=true, label="OR Date"),
    @Display(name="overallAmountPaid", addInfoOnly=true, label="Amount Paid"),
//    @Display(name="emergencyFee", addInfoOnly=true),
    @Display(name="amountDeposit", addInfoOnly=true),
//    @Display(name="totalFee", addInfoOnly=true),

    @Display(name="otherFixedDiscountReason", addInfoOnly=true, label="Discount Reason"),
    @Display(name="otherFixedDiscount", addInfoOnly=true, label="Fixed Discount"),
    @Display(name="usedDiscountMethod", addInfoOnly=true, label="Discount Method"),
    @Display(name="computedOverallDiscount", addInfoOnly=true, label="Overall Discount"),

    @Display(name="insuranceName"),
    @Display(name="insurancePolicyNumber", label="Policy #"),
    @Display(name="amountShoulderedByInsurance", label="Amount Shouldered")
})
@Reports( {
	@template.Report(reportFile = "AdmissionList", reportTitle = "Admission List", reportSql = ""),
	@template.Report(reportFile = "VacantRoomList", reportTitle = "Vacant Room List", reportSql = "")
})
public class Admission extends OutPatient implements Serializable {
	public static void main(String[] args) {
		view(Admission.class);
	}

	@Column(name = "releaseNumber", length = 30)
    public String releaseNumber;
    @Column(name = "admissionDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date admissionDate;
    @Column(name = "releaseDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date releaseDate;
    @Column(name = "admissionFee")
    public double admissionFee;
    @Column(name = "room", length = 30)
    public String room;
    @Column(name = "roomRate")
    public double roomRate;
    @Column(name = "amountDeposit")
    public double amountDeposit;
    @Column(name = "patientBillingId")
    public int patientBillingId;
    @Column(name = "requestAdmission")
    public boolean requestAdmission = true;
    @Column(name = "requestFrom")
    public String requestFrom;
    @Column(name = "roomClearanceCheck")
    public boolean roomClearanceCheck;
    @Column(name = "roomClearanceCheckDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date roomClearanceCheckDate;
    @Column(name = "roomClearanceChecker")
    public String roomClearanceChecker;
    @Column(name = "nurseStationClearanceCheck")
    public boolean nurseStationClearanceCheck;
    @Column(name = "nurseStationClearanceCheckDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date nurseStationClearanceCheckDate;
    @Column(name = "nurseStationClearanceChecker")
    public String nurseStationClearanceChecker;
    @Column(name = "physicianClearanceCheck")
    public boolean physicianClearanceCheck;
    @Column(name = "physicianClearanceCheckDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date physicianClearanceCheckDate;
    @Column(name = "physicianClearanceChecker")
    public String physicianClearanceChecker;
    @Column(name = "accountingClearanceCheck")
    public boolean accountingClearanceCheck;
    @Column(name = "accountingClearanceCheckDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date accountingClearanceCheckDate;
    @Column(name = "accountingClearanceChecker")
    public String accountingClearanceChecker;
    @Column(name = "cashierClearanceCheck")
    public boolean cashierClearanceCheck;
    @Column(name = "cashierClearanceCheckDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date cashierClearanceCheckDate;
    @Column(name = "cashierClearanceChecker")
    public String cashierClearanceChecker;

    public boolean isAccountingClearanceCheck() {
        return accountingClearanceCheck;
    }

    public void setAccountingClearanceCheck(boolean accountingClearanceCheck) {
        this.accountingClearanceCheck = accountingClearanceCheck;
    }

    public Date getAccountingClearanceCheckDate() {
        return accountingClearanceCheckDate;
    }

    public void setAccountingClearanceCheckDate(Date accountingClearanceCheckDate) {
        this.accountingClearanceCheckDate = accountingClearanceCheckDate;
    }

    public String getAccountingClearanceChecker() {
        return accountingClearanceChecker;
    }

    public void setAccountingClearanceChecker(String accountingClearanceChecker) {
        this.accountingClearanceChecker = accountingClearanceChecker;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public double getAdmissionFee() {
        return admissionFee;
    }

    public void setAdmissionFee(double admissionFee) {
        this.admissionFee = admissionFee;
    }

    public double getAmountDeposit() {
        return amountDeposit;
    }

    public void setAmountDeposit(double amountDeposit) {
        this.amountDeposit = amountDeposit;
    }

    public boolean isCashierClearanceCheck() {
        return cashierClearanceCheck;
    }

    public void setCashierClearanceCheck(boolean cashierClearanceCheck) {
        this.cashierClearanceCheck = cashierClearanceCheck;
    }

    public Date getCashierClearanceCheckDate() {
        return cashierClearanceCheckDate;
    }

    public void setCashierClearanceCheckDate(Date cashierClearanceCheckDate) {
        this.cashierClearanceCheckDate = cashierClearanceCheckDate;
    }

    public String getCashierClearanceChecker() {
        return cashierClearanceChecker;
    }

    public void setCashierClearanceChecker(String cashierClearanceChecker) {
        this.cashierClearanceChecker = cashierClearanceChecker;
    }

    public boolean isNurseStationClearanceCheck() {
        return nurseStationClearanceCheck;
    }

    public void setNurseStationClearanceCheck(boolean nurseStationClearanceCheck) {
        this.nurseStationClearanceCheck = nurseStationClearanceCheck;
    }

    public Date getNurseStationClearanceCheckDate() {
        return nurseStationClearanceCheckDate;
    }

    public void setNurseStationClearanceCheckDate(Date nurseStationClearanceCheckDate) {
        this.nurseStationClearanceCheckDate = nurseStationClearanceCheckDate;
    }

    public String getNurseStationClearanceChecker() {
        return nurseStationClearanceChecker;
    }

    public void setNurseStationClearanceChecker(String nurseStationClearanceChecker) {
        this.nurseStationClearanceChecker = nurseStationClearanceChecker;
    }

    public int getPatientBillingId() {
        return patientBillingId;
    }

    public void setPatientBillingId(int patientBillingId) {
        this.patientBillingId = patientBillingId;
    }

    public boolean isPhysicianClearanceCheck() {
        return physicianClearanceCheck;
    }

    public void setPhysicianClearanceCheck(boolean physicianClearanceCheck) {
        this.physicianClearanceCheck = physicianClearanceCheck;
    }

    public Date getPhysicianClearanceCheckDate() {
        return physicianClearanceCheckDate;
    }

    public void setPhysicianClearanceCheckDate(Date physicianClearanceCheckDate) {
        this.physicianClearanceCheckDate = physicianClearanceCheckDate;
    }

    public String getPhysicianClearanceChecker() {
        return physicianClearanceChecker;
    }

    public void setPhysicianClearanceChecker(String physicianClearanceChecker) {
        this.physicianClearanceChecker = physicianClearanceChecker;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public boolean isRequestAdmission() {
        return requestAdmission;
    }

    public void setRequestAdmission(boolean requestAdmission) {
        this.requestAdmission = requestAdmission;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isRoomClearanceCheck() {
        return roomClearanceCheck;
    }

    public void setRoomClearanceCheck(boolean roomClearanceCheck) {
        this.roomClearanceCheck = roomClearanceCheck;
    }

    public Date getRoomClearanceCheckDate() {
        return roomClearanceCheckDate;
    }

    public void setRoomClearanceCheckDate(Date roomClearanceCheckDate) {
        this.roomClearanceCheckDate = roomClearanceCheckDate;
    }

    public String getRoomClearanceChecker() {
        return roomClearanceChecker;
    }

    public void setRoomClearanceChecker(String roomClearanceChecker) {
        this.roomClearanceChecker = roomClearanceChecker;
    }

    public double getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
    }

    @Override
    public String toString() {
        return "Admission[" + seq + "]";
    }

    @SuppressWarnings("unchecked")
    public List<PatientBillingItem> getBillingItems() {
        List<PatientBillingItem> lst = DBClient.getList("SELECT a FROM PatientBillingItem a WHERE a.patientBillingId=" + seq);
        if (lst == null) {
            return new ArrayList();
        }
        return lst;
    }
    @SuppressWarnings("unchecked")
    public List<InPatientDailyService> getInPatientDailyService() {
        List<InPatientDailyService> lst = DBClient.getList("SELECT a FROM InPatientDailyService a WHERE a.admissionId=" + seq);
        if (lst == null) {
            return new ArrayList();
        }
        return lst;
    }
    public double getTotalCharges() {
        Object d = DBClient.getSingleColumn("SELECT SUM(a.amount) FROM Patient_Billing_Item a WHERE a.patientBillingId=" + seq);
        if (d == null) {
            return 0;
        }
        return new Double(d.toString());
    }
    public double getTotalDiscount() {
        Object d = DBClient.getSingleColumn("SELECT SUM(a.discount) FROM Patient_Billing_Item a WHERE a.patientBillingId=" + seq);
        if (d == null) {
            return 0;
        }
        return new Double(d.toString());
    }
    public double getTotalNetCharges() {
        Object d = DBClient.getSingleColumn("SELECT SUM(a.net_amount) FROM Patient_Billing_Item a WHERE a.patientBillingId=" + seq);
        if (d == null) {
            return 0;
        }
        return new Double(d.toString());
    }
    public boolean isAllCleared() {
        return isAccountingClearanceCheck() && isCashierClearanceCheck() && isNurseStationClearanceCheck() && isPhysicianClearanceCheck() && isRoomClearanceCheck();
    }
}
