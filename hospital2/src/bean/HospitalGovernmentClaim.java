/*
 * Appointment.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.persistence.Id;

import service.util.AbstractIBean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "HospitalGovernmentClaim")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4,
columnSearch = {"appointmentType", "appointmentDate", "patient", "physician"},
criteriaSearch = {"appointmentType", "appointmentDate", "patientId", "physicianId"})
@ChildRecords(value = {@template.ChildRecord(title="Government Claim", entity = GovernmentClaim.class, sql = "SELECT a FROM GovernmentClaim a WHERE a.opdId=${opdId}", fieldMapping={"opdId","opdId"},template=ChildTemplateListPopupDownButton.class)},
info = {
    @ParentAddInfo(title = "Employer and Spouse", gridCount = 4, displayFields = {""}, hideGroup = "0,2,3,4,5"),
    @ParentAddInfo(title = "Hospital Data and Charges", gridCount = 4,
    		displayFields = {"hospitalHealthNumber", "hospitalName",
    			"hospitalAddressSt", "hospitalAddressBarangay", "hospitalAddressMunicipality", "hospitalAddressProvince", "hospitalDiagnosis",
    			"confinementStartDate", "confinementStartTime", "confinementEndDate", "confinementEndTime", "confinementNumberOfDays", "confinementDateOfDeath"}, hideGroup = "0,1,3"),
    @ParentAddInfo(title = "Professional Data and Charges", gridCount = 4,
    		displayFields = {"caseType","completeFinalDiagnosis"}, hideGroup = "0,1,2")
    
})
@DisplayGroups({
//@DisplayGroup(title = "Member's Certification", gridCount = 4,
//	fields = { "patientID", "idNo", "patientIsTheMember" 
//	}),
    @DisplayGroup(title = "Member's Certification", gridCount = 6,
    fields = {  "membershipType", "memberLastName", "memberFirstName", "memberMiddleName", "memberBirthDate", "memberCivilStatus", "memberGender",
        "memberAddressSt", "memberAddressBarangay", "memberAddressMunicipality", "memberAddressProvince",
         "patientRelationshipToMember"
    }),
    //1
    @DisplayGroup(title = "Employer's Certification and Spouse", gridCount = 4,
    fields = {"employerName", "employerId",
        "employerAddressSt", "employerAddressBarangay", "employerAddressMunicipality", "employerAddressProvince",
        "memberSpouseLastName", "memberSpouseFirstName", "memberSpouseMiddleName", "memberSpouseApplicable"}, addInfoOnly=true),
    //2
    @DisplayGroup(title = "Hospital / Ambulatory Services, Charges and Claims", gridCount = 8,
    fields = {"hospitalRoomActualCharges", "hospitalRoomBenefitClaimOfHospital", "hospitalRoomBenefitClaimOfPatient", "hospitalRoomReductionCode",
        "hospitalMedicineActualCharges", "hospitalMedicineBenefitClaimOfHospital", "hospitalMedicineBenefitClaimOfPatient", "hospitalMedicineReductionCode",
        "hospitalLaboratoryActualCharges", "hospitalLaboratoryBenefitClaimOfHospital", "hospitalLaboratoryBenefitClaimOfPatient", "hospitalLaboratoryReductionCode",
        "hospitalOperatingRoomActualCharges", "hospitalOperatingRoomBenefitClaimOfHospital", "hospitalOperatingRoomBenefitClaimOfPatient", "hospitalOperatingRoomReductionCode",
        "hospitalOutsideActualCharges", "hospitalOutsideBenefitClaimOfHospital", "hospitalOutsideBenefitClaimOfPatient", "hospitalOutsideReductionCode"}, addInfoOnly = true),
   //3 
   @DisplayGroup(title = "Professional Charges", gridCount = 14,
    fields = {"physician","physicianHealthId","physicianTin",
        "physicianActualCharges", "physicianBenefitClaim", "physicianPatientBenefitClaim","physicianReductionCode","physicianServicesPerformed",
    "surgeonName","surgeonHealthId","surgeonTin",
        "surgeonActualCharges", "surgeonBenefitClaim", "surgeonPatientBenefitClaim","surgeonReductionCode","surgeonServicesPerformed",
     "anesthesiologistName","anesthesiologistHealthId","anesthesiologistTin",
        "anesthesiologistActualCharges", "anesthesiologistBenefitClaim", "anesthesiologistPatientBenefitClaim","anesthesiologistReductionCode","anesthesiologistServicesPerformed"},addInfoOnly=true)
})

@Displays({
    @Display(name = "patientId",label = "Patient", gridFieldWidth = 3, width = -1, type = "PopSearch", linktoBean = Patient.class, enabled = false),
	@Display(name = "idNo", enabled = false),
	@Display(name = "patientIsTheMember", label = "Patient Is The Member"),
    @Display(name = "membershipType"),
    @Display(name = "memberLastName", label = "Last Name"),
    @Display(name = "memberFirstName", label = "First Name"),
    @Display(name = "memberMiddleName", label = "Middle Name"),
    @Display(name = "memberBirthDate", label = "Birth Date",width=-1),
    @Display(name = "memberCivilStatus", label = "Civil Status", type = "Combo", modelCombo = {"SINGLE", "MARRIED"}),
    @Display(name = "memberGender", label = "Sex", type = "Combo", modelCombo = {"MALE", "FEMALE"}),
    @Display(name = "memberAddressSt", label = "No.Street"),
    @Display(name = "memberAddressBarangay", label = "Barangay"),
    @Display(name = "memberAddressMunicipality", label = "Municipality/City"),
    @Display(name = "memberAddressProvince", label = "Province"),
    // @Display(name = "memberAddressZipCode",label="Zip Code",width=50),

    @Display(name = "patientRelationshipToMember",label = "Relationship", gridFieldWidth = 3, width = -1),
    @Display(name = "employerName", addInfoOnly = true, label="Name"),
    @Display(name = "employerId", addInfoOnly = true,label="ID No."),
    @Display(name = "employerAddressSt", addInfoOnly = true, label="Street"),
    @Display(name = "employerAddressBarangay", addInfoOnly = true, label="Barangay"),
    @Display(name = "employerAddressMunicipality", addInfoOnly = true, label="Municipality"),
    @Display(name = "employerAddressProvince", addInfoOnly = true, label="Province"),
    @Display(name = "memberSpouseLastName"),
    @Display(name = "memberSpouseFirstName"),
    @Display(name = "memberSpouseMiddleName"),
    @Display(name = "memberSpouseApplicable"),
    
    // @Display(name = "patientId"),
    //    @Display(name = "employerAddressZipcode",width=50)
    @Display(name = "hospitalHealthNumber", label = "Philhealth Accreditation No.", addInfoOnly = true),
    @Display(name = "hospitalName", addInfoOnly = true),
    @Display(name = "hospitalAddressSt", addInfoOnly = true, label="Street"),
    @Display(name = "hospitalAddressBarangay", addInfoOnly = true, label="Barangay"),
    @Display(name = "hospitalAddressMunicipality", addInfoOnly = true, label="Municipality"),
    @Display(name = "hospitalAddressProvince", addInfoOnly = true, label="Province"),
    //    @Display(name = "hospitalAddressZipcode"),
    @Display(name = "hospitalDiagnosis", type = "TextArea", label = "Admission Diagnosis", gridFieldWidth = 3, width = -1, addInfoOnly = true),
    //
    @Display(name = "confinementStartDate", label = "Date Admitted", addInfoOnly = true,width=-1),
    @Display(name = "confinementStartTime", label = "Time Admitted", type = "Time", addInfoOnly = true,width=-1),
    @Display(name = "confinementEndDate", label = "Date Discharged", addInfoOnly = true,width=-1),
    @Display(name = "confinementEndTime", label = "Time Discharged", type = "Time", addInfoOnly = true,width=-1),
    @Display(name = "confinementNumberOfDays", label = "Claimed No. of Days", addInfoOnly = true,width=-1),
    @Display(name = "confinementDateOfDeath", label = "Date of Death", addInfoOnly = true,width=-1),
    //
    @Display(name = "hospitalRoomActualCharges", leftLabel = "Room and Board", labelTop = true, label = "Actual Charges", width = 60, addInfoOnly = true),
    @Display(name = "hospitalRoomBenefitClaimOfHospital", labelTop = true, label = "Hospital Claim", width = 60, addInfoOnly = true),
    @Display(name = "hospitalRoomBenefitClaimOfPatient", labelTop = true, label = "Patient Claim", width = 60, addInfoOnly = true),
    @Display(name = "hospitalRoomReductionCode", labelTop = true, label = "Reduction Code", width = 60, addInfoOnly = true),
    //
    @Display(name = "hospitalMedicineActualCharges", label = "Drugs and Medicines", width = 60, addInfoOnly = true),
    @Display(name = "hospitalMedicineBenefitClaimOfHospital", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalMedicineBenefitClaimOfPatient", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalMedicineReductionCode", hideLabel = true, width = 60, addInfoOnly = true),
    //
    @Display(name = "hospitalLaboratoryActualCharges", label = "X-ray/Lab.Test/Others", width = 60, addInfoOnly = true),
    @Display(name = "hospitalLaboratoryBenefitClaimOfHospital", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalLaboratoryBenefitClaimOfPatient", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalLaboratoryReductionCode", hideLabel = true, width = 60, addInfoOnly = true),
    //
    @Display(name = "hospitalOperatingRoomActualCharges", label = "Operating Room Fee", width = 60, addInfoOnly = true),
    @Display(name = "hospitalOperatingRoomBenefitClaimOfHospital", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalOperatingRoomBenefitClaimOfPatient", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalOperatingRoomReductionCode", hideLabel = true, width = 60, addInfoOnly = true),
    //
    @Display(name = "hospitalOutsideActualCharges", label = "Medicine & Laboratory Outside", width = 60, addInfoOnly = true),
    @Display(name = "hospitalOutsideBenefitClaimOfHospital", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalOutsideBenefitClaimOfPatient", hideLabel = true, width = 60, addInfoOnly = true),
    @Display(name = "hospitalOutsideReductionCode", hideLabel = true, width = 60, addInfoOnly = true),
    //
    @Display(name = "caseType", type = "Combo", modelCombo = {"ORDINARY", "INTENSIVE", "CATASTROPHIC"}, addInfoOnly = true,gridFieldWidth=3,width=150),
    @Display(name = "completeFinalDiagnosis", type = "TextArea", gridFieldWidth = 3, addInfoOnly = true,width=350,height=70),
    
    //    @Display(name = "physicianId"),
    @Display(name = "physician",labelTop=true,leftLabel="Physician",addInfoOnly=true,width=140, type = "PopSearch", linktoBean=Physician.class),
    @Display(name = "physicianHealthId",labelTop=true, label = "Philhealth No.",addInfoOnly=true,width=80),
    @Display(name = "physicianTin",labelTop=true, label = "BIR/TIN No.", addInfoOnly =true,width=80),
    @Display(name = "physicianActualCharges",labelTop=true,label="Actual Charges",width=80),
    @Display(name = "physicianBenefitClaim",labelTop=true,label="Physician Claim",width=80),
    @Display(name = "physicianPatientBenefitClaim",labelTop=true,label="Patient Claim",width=80),
    @Display(name = "physicianReductionCode",labelTop=true,label="Reduction Code",width=80,addInfoOnly=true),
     @Display(name ="physicianServicesPerformed",label="Services",gridFieldWidth=13,width=-1,addInfoOnly=true),
    
//
    @Display(name = "surgeonName",label="Surgeon",addInfoOnly=true,width=140, type = "PopSearch", linktoBean=Physician.class),
    @Display(name = "surgeonHealthId",hideLabel=true,addInfoOnly=true,width=80),
    @Display(name = "surgeonTin",hideLabel=true, addInfoOnly =true,width=80),
    @Display(name = "surgeonActualCharges",hideLabel=true,width=80),
    @Display(name = "surgeonBenefitClaim",hideLabel=true,width=80),
    @Display(name = "surgeonPatientBenefitClaim",hideLabel=true,width=80),
    @Display(name = "surgeonReductionCode",hideLabel=true,width=80,addInfoOnly=true),
     @Display(name = "surgeonServicesPerformed",label="Services",gridFieldWidth=13,width=-1,addInfoOnly=true),
//    

    @Display(name = "anesthesiologistName",label="Anesthesiologist",addInfoOnly=true,width=140, type = "PopSearch", linktoBean=Physician.class),
    @Display(name = "anesthesiologistHealthId",hideLabel=true,addInfoOnly=true,width=80),
    @Display(name = "anesthesiologistTin",hideLabel=true, addInfoOnly =true,width=80),
    @Display(name = "anesthesiologistActualCharges",hideLabel=true,width=80),
    @Display(name = "anesthesiologistBenefitClaim",hideLabel=true,width=80),
    @Display(name = "anesthesiologistPatientBenefitClaim",hideLabel=true,width=80),
    @Display(name = "anesthesiologistReductionCode",hideLabel=true,width=80,addInfoOnly=true),
     @Display(name = "anesthesiologistServicesPerformed",label="Services",gridFieldWidth=13,width=-1,addInfoOnly=true)
//
//    @Display(name = "amountDeductedFromHospitalCharges"),
//    @Display(name = "amountDeductedFromProfessionalCharges"),
//    @Display(name = "amountPaidForMedicine")
})
@Reports( {
	@template.Report(reportFile = "PhilHealthClaim", reportTitle = "PhilHealth Claim", reportSql = "${seq}")
})
public class HospitalGovernmentClaim extends AbstractIBean implements Serializable {

    public static void main(String[] args) {
        view(HospitalGovernmentClaim.class);
    }
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "opdId", nullable = false)
    public int opdId;
    @Column(name = "membershipType")
    public String membershipType;
    @Column(name = "idNo")
    public String idNo;
    @Column(name = "memberLastName")
    public String memberLastName;
    @Column(name = "memberFirstName")
    public String memberFirstName;
    @Column(name = "memberMiddleName")
    public String memberMiddleName;
    @Column(name = "memberCivilStatus")
    public String memberCivilStatus;
    @Column(name = "memberGender")
    public String memberGender;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "memberBirthDate")
    public Date memberBirthDate;
    @Column(name = "memberAddressSt")
    public String memberAddressSt;
    @Column(name = "memberAddressBarangay")
    public String memberAddressBarangay;
    @Column(name = "memberAddressMunicipality")
    public String memberAddressMunicipality;
    @Column(name = "memberAddressProvince")
    public String memberAddressProvince;
    @Column(name = "memberAddressZipCode")
    public String memberAddressZipCode;
    @Column(name = "memberSpouseLastName")
    public String memberSpouseLastName;
    @Column(name = "memberSpouseFirstName")
    public String memberSpouseFirstName;
    @Column(name = "memberSpouseMiddleName")
    public String memberSpouseMiddleName;
    @Column(name = "memberSpouseApplicable")
    public boolean memberSpouseApplicable;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "patient")
    public String patient;
    @Column(name = "patientIsTheMember")
    public boolean patientIsTheMember;
    @Column(name = "patientRelationshipToMember")
    public String patientRelationshipToMember;
    @Column(name = "employerName")
    public String employerName;
    @Column(name = "employerId")
    public String employerId;
    @Column(name = "employerAddressSt")
    public String employerAddressSt;
    @Column(name = "employerAddressBarangay")
    public String employerAddressBarangay;
    @Column(name = "employerAddressMunicipality")
    public String employerAddressMunicipality;
    @Column(name = "employerAddressProvince")
    public String employerAddressProvince;
    @Column(name = "employerAddressZipcode")
    public String employerAddressZipcode;
    @Column(name = "hospitalHealthNumber")
    public String hospitalHealthNumber;
    @Column(name = "hospitalName")
    public String hospitalName;
    @Column(name = "hospitalAddressSt")
    public String hospitalAddressSt;
    @Column(name = "hospitalAddressBarangay")
    public String hospitalAddressBarangay;
    @Column(name = "hospitalAddressMunicipality")
    public String hospitalAddressMunicipality;
    @Column(name = "hospitalAddressProvince")
    public String hospitalAddressProvince;
    @Column(name = "hospitalAddressZipcode")
    public String hospitalAddressZipcode;
    @Column(name = "hospitalDiagnosis")
    public String hospitalDiagnosis;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "confinementStartDate")
    public Date confinementStartDate;
    @Column(name = "confinementStartTime")
    public String confinementStartTime;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "confinementEndDate")
    public Date confinementEndDate;
    @Column(name = "confinementEndTime")
    public String confinementEndTime;
    @Column(name = "confinementNumberOfDays")
    public int confinementNumberOfDays;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "confinementDateOfDeath")
    public Date confinementDateOfDeath;
    @Column(name = "hospitalRoomActualCharges")
    public double hospitalRoomActualCharges;
    @Column(name = "hospitalRoomBenefitClaimOfHospital")
    public double hospitalRoomBenefitClaimOfHospital;
    @Column(name = "hospitalRoomBenefitClaimOfPatient")
    public double hospitalRoomBenefitClaimOfPatient;
    @Column(name = "hospitalRoomReductionCode")
    public String hospitalRoomReductionCode;
    @Column(name = "hospitalMedicineActualCharges")
    public double hospitalMedicineActualCharges;
    @Column(name = "hospitalMedicineBenefitClaimOfHospital")
    public double hospitalMedicineBenefitClaimOfHospital;
    @Column(name = "hospitalMedicineBenefitClaimOfPatient")
    public double hospitalMedicineBenefitClaimOfPatient;
    @Column(name = "hospitalMedicineReductionCode")
    public String hospitalMedicineReductionCode;
    @Column(name = "hospitalLaboratoryActualCharges")
    public double hospitalLaboratoryActualCharges;
    @Column(name = "hospitalLaboratoryBenefitClaimOfHospital")
    public double hospitalLaboratoryBenefitClaimOfHospital;
    @Column(name = "hospitalLaboratoryBenefitClaimOfPatient")
    public double hospitalLaboratoryBenefitClaimOfPatient;
    @Column(name = "hospitalLaboratoryReductionCode")
    public String hospitalLaboratoryReductionCode;
    @Column(name = "hospitalOperatingRoomActualCharges")
    public double hospitalOperatingRoomActualCharges;
    @Column(name = "hospitalOperatingRoomBenefitClaimOfHospital")
    public double hospitalOperatingRoomBenefitClaimOfHospital;
    @Column(name = "hospitalOperatingRoomBenefitClaimOfPatient")
    public double hospitalOperatingRoomBenefitClaimOfPatient;
    @Column(name = "hospitalOperatingRoomReductionCode")
    public String hospitalOperatingRoomReductionCode;
    @Column(name = "hospitalOutsideActualCharges")
    public double hospitalOutsideActualCharges;
    @Column(name = "hospitalOutsideBenefitClaimOfHospital")
    public double hospitalOutsideBenefitClaimOfHospital;
    @Column(name = "hospitalOutsideBenefitClaimOfPatient")
    public double hospitalOutsideBenefitClaimOfPatient;
    @Column(name = "hospitalOutsideReductionCode")
    public String hospitalOutsideReductionCode;
    @Column(name = "completeFinalDiagnosis")
    public String completeFinalDiagnosis;
    @Column(name = "caseType")
    public String caseType;
    
    @Column(name = "physicianId")
    public int physicianId;
    
    @Column(name = "physician")
    public String physician;
    @Column(name = "physicianHealthId")
    public String physicianHealthId;
    @Column(name = "physicianTin")
    public String physicianTin;
    @Column(name = "physicianServicesPerformed")
    public String physicianServicesPerformed;
    @Column(name = "physicianActualCharges")
    public double physicianActualCharges;
    @Column(name = "physicianBenefitClaim")
    public double physicianBenefitClaim;
    @Column(name = "physicianPatientBenefitClaim")
    public double physicianPatientBenefitClaim;
    @Column(name = "physicianReductionCode")
    public String physicianReductionCode;
    
    
    @Column(name = "surgeonName")
    public String surgeonName;
    @Column(name = "surgeonHealthId")
    public String surgeonHealthId;
    @Column(name = "surgeonTin")
    public String surgeonTin;
    @Column(name = "surgeonServicesPerformed")
    public String surgeonServicesPerformed;
    @Column(name = "surgeonActualCharges")
    public double surgeonActualCharges;
    @Column(name = "surgeonBenefitClaim")
    public double surgeonBenefitClaim;
    @Column(name = "surgeonPatientBenefitClaim")
    public double surgeonPatientBenefitClaim;
    @Column(name = "surgeonReductionCode")
    public String surgeonReductionCode;
    
    @Column(name = "anesthesiologistName")
    public String anesthesiologistName;
    @Column(name = "anesthesiologistHealthId")
    public String anesthesiologistHealthId;
    @Column(name = "anesthesiologistTin")
    public String anesthesiologistTin;
    @Column(name = "anesthesiologistServicesPerformed")
    public String anesthesiologistServicesPerformed;
    @Column(name = "anesthesiologistActualCharges")
    public double anesthesiologistActualCharges;
    @Column(name = "anesthesiologistBenefitClaim")
    public double anesthesiologistBenefitClaim;
    @Column(name = "anesthesiologistPatientBenefitClaim")
    public double anesthesiologistPatientBenefitClaim;
    @Column(name = "anesthesiologistReductionCode")
    public String anesthesiologistReductionCode;
    
    @Column(name = "amountDeductedFromHospitalCharges")
    public double amountDeductedFromHospitalCharges;
    @Column(name = "amountDeductedFromProfessionalCharges")
    public double amountDeductedFromProfessionalCharges;
    @Column(name = "amountPaidForMedicine")
    public double amountPaidForMedicine;
    //patient's clinical record must be extracted from OPD

    @Override
    public void save() {
        if (physicianId > 0) {
            Person p = (Person) extractPerson(physicianId);
            if (p != null) {
                physician = p.toString();
            }
        }
        if (patientId > 0) {
            Person p = (Person) extractPerson(patientId);
            if (p != null) {
                patient = p.toString();
            }
        }
        super.save();
    }

    public String getAnesthesiologistReductionCode() {
        return anesthesiologistReductionCode;
    }

    public void setAnesthesiologistReductionCode(String anesthesiologistReductionCode) {
        this.anesthesiologistReductionCode = anesthesiologistReductionCode;
    }

    public String getHospitalLaboratoryReductionCode() {
        return hospitalLaboratoryReductionCode;
    }

    public void setHospitalLaboratoryReductionCode(String hospitalLaboratoryReductionCode) {
        this.hospitalLaboratoryReductionCode = hospitalLaboratoryReductionCode;
    }

    public String getHospitalMedicineReductionCode() {
        return hospitalMedicineReductionCode;
    }

    public void setHospitalMedicineReductionCode(String hospitalMedicineReductionCode) {
        this.hospitalMedicineReductionCode = hospitalMedicineReductionCode;
    }

    public String getHospitalOperatingRoomReductionCode() {
        return hospitalOperatingRoomReductionCode;
    }

    public void setHospitalOperatingRoomReductionCode(String hospitalOperatingRoomReductionCode) {
        this.hospitalOperatingRoomReductionCode = hospitalOperatingRoomReductionCode;
    }

    public String getHospitalOutsideReductionCode() {
        return hospitalOutsideReductionCode;
    }

    public void setHospitalOutsideReductionCode(String hospitalOutsideReductionCode) {
        this.hospitalOutsideReductionCode = hospitalOutsideReductionCode;
    }

    public String getHospitalRoomReductionCode() {
        return hospitalRoomReductionCode;
    }

    public void setHospitalRoomReductionCode(String hospitalRoomReductionCode) {
        this.hospitalRoomReductionCode = hospitalRoomReductionCode;
    }

    public String getPhysicianReductionCode() {
        return physicianReductionCode;
    }

    public void setPhysicianReductionCode(String physicianReductionCode) {
        this.physicianReductionCode = physicianReductionCode;
    }

    public String getSurgeonReductionCode() {
        return surgeonReductionCode;
    }

    public void setSurgeonReductionCode(String surgeonReductionCode) {
        this.surgeonReductionCode = surgeonReductionCode;
    }


    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getAnesthesiologistTin() {
        return anesthesiologistTin;
    }

    public void setAnesthesiologistTin(String anesthesiologistTin) {
        this.anesthesiologistTin = anesthesiologistTin;
    }

    public double getPhysicianBenefitClaim() {
        return physicianBenefitClaim;
    }

    public void setPhysicianBenefitClaim(double physicianBenefitClaim) {
        this.physicianBenefitClaim = physicianBenefitClaim;
    }

    public String getPhysicianHealthId() {
        return physicianHealthId;
    }

    public void setPhysicianHealthId(String physicianHealthId) {
        this.physicianHealthId = physicianHealthId;
    }

    public String getPhysicianTin() {
        return physicianTin;
    }

    public void setPhysicianTin(String physicianTin) {
        this.physicianTin = physicianTin;
    }

    public double getSurgeonActualCharges() {
        return surgeonActualCharges;
    }

    public void setSurgeonActualCharges(double surgeonActualCharges) {
        this.surgeonActualCharges = surgeonActualCharges;
    }

    public String getSurgeonHealthId() {
        return surgeonHealthId;
    }

    public void setSurgeonHealthId(String surgeonHealthId) {
        this.surgeonHealthId = surgeonHealthId;
    }

    public String getSurgeonName() {
        return surgeonName;
    }

    public void setSurgeonName(String surgeonName) {
        this.surgeonName = surgeonName;
    }

    public double getSurgeonPatientBenefitClaim() {
        return surgeonPatientBenefitClaim;
    }

    public void setSurgeonPatientBenefitClaim(double surgeonPatientBenefitClaim) {
        this.surgeonPatientBenefitClaim = surgeonPatientBenefitClaim;
    }

    public String getSurgeonServicesPerformed() {
        return surgeonServicesPerformed;
    }

    public void setSurgeonServicesPerformed(String surgeonServicesPerformed) {
        this.surgeonServicesPerformed = surgeonServicesPerformed;
    }

    public String getSurgeonTin() {
        return surgeonTin;
    }

    public void setSurgeonTin(String surgeonTin) {
        this.surgeonTin = surgeonTin;
    }
    
    

    public String getMemberAddressProvince() {
        return memberAddressProvince;
    }

    public void setMemberAddressProvince(String memberAddressProvince) {
        this.memberAddressProvince = memberAddressProvince;
    }

    public int getOpdId() {
        return opdId;
    }

    public void setOpdId(int opdId) {
        this.opdId = opdId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public String getMemberMiddleName() {
        return memberMiddleName;
    }

    public void setMemberMiddleName(String memberMiddleName) {
        this.memberMiddleName = memberMiddleName;
    }

    public String getMemberCivilStatus() {
        return memberCivilStatus;
    }

    public void setMemberCivilStatus(String memberCivilStatus) {
        this.memberCivilStatus = memberCivilStatus;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public Date getMemberBirthDate() {
        return memberBirthDate;
    }

    public void setMemberBirthDate(Date memberBirthDate) {
        this.memberBirthDate = memberBirthDate;
    }

    public String getMemberAddressSt() {
        return memberAddressSt;
    }

    public void setMemberAddressSt(String memberAddressSt) {
        this.memberAddressSt = memberAddressSt;
    }

    public String getMemberAddressBarangay() {
        return memberAddressBarangay;
    }

    public void setMemberAddressBarangay(String memberAddressBarangay) {
        this.memberAddressBarangay = memberAddressBarangay;
    }

    public String getMemberAddressMunicipality() {
        return memberAddressMunicipality;
    }

    public void setMemberAddressMunicipality(String memberAddressMunicipality) {
        this.memberAddressMunicipality = memberAddressMunicipality;
    }

    public String getMemberAddressZipCode() {
        return memberAddressZipCode;
    }

    public void setMemberAddressZipCode(String memberAddressZipCode) {
        this.memberAddressZipCode = memberAddressZipCode;
    }

    public String getMemberSpouseLastName() {
        return memberSpouseLastName;
    }

    public void setMemberSpouseLastName(String memberSpouseLastName) {
        this.memberSpouseLastName = memberSpouseLastName;
    }

    public String getMemberSpouseFirstName() {
        return memberSpouseFirstName;
    }

    public void setMemberSpouseFirstName(String memberSpouseFirstName) {
        this.memberSpouseFirstName = memberSpouseFirstName;
    }

    public String getMemberSpouseMiddleName() {
        return memberSpouseMiddleName;
    }

    public void setMemberSpouseMiddleName(String memberSpouseMiddleName) {
        this.memberSpouseMiddleName = memberSpouseMiddleName;
    }

    public boolean isMemberSpouseApplicable() {
        return memberSpouseApplicable;
    }

    public void setMemberSpouseApplicable(boolean memberSpouseApplicable) {
        this.memberSpouseApplicable = memberSpouseApplicable;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public boolean isPatientIsTheMember() {
        return patientIsTheMember;
    }

    public void setPatientIsTheMember(boolean patientIsTheMember) {
        this.patientIsTheMember = patientIsTheMember;
    }

    public String getPatientRelationshipToMember() {
        return patientRelationshipToMember;
    }

    public void setPatientRelationshipToMember(String patientRelationshipToMember) {
        this.patientRelationshipToMember = patientRelationshipToMember;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerAddressSt() {
        return employerAddressSt;
    }

    public void setEmployerAddressSt(String employerAddressSt) {
        this.employerAddressSt = employerAddressSt;
    }

    public String getEmployerAddressBarangay() {
        return employerAddressBarangay;
    }

    public void setEmployerAddressBarangay(String employerAddressBarangay) {
        this.employerAddressBarangay = employerAddressBarangay;
    }

    public String getEmployerAddressMunicipality() {
        return employerAddressMunicipality;
    }

    public void setEmployerAddressMunicipality(String employerAddressMunicipality) {
        this.employerAddressMunicipality = employerAddressMunicipality;
    }

    public String getEmployerAddressProvince() {
        return employerAddressProvince;
    }

    public void setEmployerAddressProvince(String employerAddressProvince) {
        this.employerAddressProvince = employerAddressProvince;
    }

    public String getEmployerAddressZipcode() {
        return employerAddressZipcode;
    }

    public void setEmployerAddressZipcode(String employerAddressZipcode) {
        this.employerAddressZipcode = employerAddressZipcode;
    }

    public String getHospitalHealthNumber() {
        return hospitalHealthNumber;
    }

    public void setHospitalHealthNumber(String hospitalHealthNumber) {
        this.hospitalHealthNumber = hospitalHealthNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddressSt() {
        return hospitalAddressSt;
    }

    public void setHospitalAddressSt(String hospitalAddressSt) {
        this.hospitalAddressSt = hospitalAddressSt;
    }

    public String getHospitalAddressBarangay() {
        return hospitalAddressBarangay;
    }

    public void setHospitalAddressBarangay(String hospitalAddressBarangay) {
        this.hospitalAddressBarangay = hospitalAddressBarangay;
    }

    public String getHospitalAddressMunicipality() {
        return hospitalAddressMunicipality;
    }

    public void setHospitalAddressMunicipality(String hospitalAddressMunicipality) {
        this.hospitalAddressMunicipality = hospitalAddressMunicipality;
    }

    public String getHospitalAddressProvince() {
        return hospitalAddressProvince;
    }

    public void setHospitalAddressProvince(String hospitalAddressProvince) {
        this.hospitalAddressProvince = hospitalAddressProvince;
    }

    public String getHospitalAddressZipcode() {
        return hospitalAddressZipcode;
    }

    public void setHospitalAddressZipcode(String hospitalAddressZipcode) {
        this.hospitalAddressZipcode = hospitalAddressZipcode;
    }

    public String getHospitalDiagnosis() {
        return hospitalDiagnosis;
    }

    public void setHospitalDiagnosis(String hospitalDiagnosis) {
        this.hospitalDiagnosis = hospitalDiagnosis;
    }

    public Date getConfinementStartDate() {
        return confinementStartDate;
    }

    public void setConfinementStartDate(Date confinementStartDate) {
        this.confinementStartDate = confinementStartDate;
    }

    public String getConfinementStartTime() {
        return confinementStartTime;
    }

    public void setConfinementStartTime(String confinementStartTime) {
        this.confinementStartTime = confinementStartTime;
    }

    public Date getConfinementEndDate() {
        return confinementEndDate;
    }

    public void setConfinementEndDate(Date confinementEndDate) {
        this.confinementEndDate = confinementEndDate;
    }

    public String getConfinementEndTime() {
        return confinementEndTime;
    }

    public void setConfinementEndTime(String confinementEndTime) {
        this.confinementEndTime = confinementEndTime;
    }

    public int getConfinementNumberOfDays() {
        return confinementNumberOfDays;
    }

    public void setConfinementNumberOfDays(int confinementNumberOfDays) {
        this.confinementNumberOfDays = confinementNumberOfDays;
    }

    public Date getConfinementDateOfDeath() {
        return confinementDateOfDeath;
    }

    public void setConfinementDateOfDeath(Date confinementDateOfDeath) {
        this.confinementDateOfDeath = confinementDateOfDeath;
    }

    public double getHospitalRoomActualCharges() {
        return hospitalRoomActualCharges;
    }

    public void setHospitalRoomActualCharges(double hospitalRoomActualCharges) {
        this.hospitalRoomActualCharges = hospitalRoomActualCharges;
    }

    public double getHospitalRoomBenefitClaimOfHospital() {
        return hospitalRoomBenefitClaimOfHospital;
    }

    public void setHospitalRoomBenefitClaimOfHospital(
            double hospitalRoomBenefitClaimOfHospital) {
        this.hospitalRoomBenefitClaimOfHospital = hospitalRoomBenefitClaimOfHospital;
    }

    public double getHospitalRoomBenefitClaimOfPatient() {
        return hospitalRoomBenefitClaimOfPatient;
    }

    public void setHospitalRoomBenefitClaimOfPatient(
            double hospitalRoomBenefitClaimOfPatient) {
        this.hospitalRoomBenefitClaimOfPatient = hospitalRoomBenefitClaimOfPatient;
    }

    public double getHospitalMedicineActualCharges() {
        return hospitalMedicineActualCharges;
    }

    public void setHospitalMedicineActualCharges(
            double hospitalMedicineActualCharges) {
        this.hospitalMedicineActualCharges = hospitalMedicineActualCharges;
    }

    public double getHospitalMedicineBenefitClaimOfHospital() {
        return hospitalMedicineBenefitClaimOfHospital;
    }

    public void setHospitalMedicineBenefitClaimOfHospital(
            double hospitalMedicineBenefitClaimOfHospital) {
        this.hospitalMedicineBenefitClaimOfHospital = hospitalMedicineBenefitClaimOfHospital;
    }

    public double getHospitalMedicineBenefitClaimOfPatient() {
        return hospitalMedicineBenefitClaimOfPatient;
    }

    public void setHospitalMedicineBenefitClaimOfPatient(
            double hospitalMedicineBenefitClaimOfPatient) {
        this.hospitalMedicineBenefitClaimOfPatient = hospitalMedicineBenefitClaimOfPatient;
    }


    public double getHospitalLaboratoryActualCharges() {
        return hospitalLaboratoryActualCharges;
    }

    public void setHospitalLaboratoryActualCharges(
            double hospitalLaboratoryActualCharges) {
        this.hospitalLaboratoryActualCharges = hospitalLaboratoryActualCharges;
    }

    public double getHospitalLaboratoryBenefitClaimOfHospital() {
        return hospitalLaboratoryBenefitClaimOfHospital;
    }

    public void setHospitalLaboratoryBenefitClaimOfHospital(
            double hospitalLaboratoryBenefitClaimOfHospital) {
        this.hospitalLaboratoryBenefitClaimOfHospital = hospitalLaboratoryBenefitClaimOfHospital;
    }

    public double getHospitalLaboratoryBenefitClaimOfPatient() {
        return hospitalLaboratoryBenefitClaimOfPatient;
    }

    public void setHospitalLaboratoryBenefitClaimOfPatient(
            double hospitalLaboratoryBenefitClaimOfPatient) {
        this.hospitalLaboratoryBenefitClaimOfPatient = hospitalLaboratoryBenefitClaimOfPatient;
    }

    public double getHospitalOperatingRoomActualCharges() {
        return hospitalOperatingRoomActualCharges;
    }

    public void setHospitalOperatingRoomActualCharges(
            double hospitalOperatingRoomActualCharges) {
        this.hospitalOperatingRoomActualCharges = hospitalOperatingRoomActualCharges;
    }

    public double getHospitalOperatingRoomBenefitClaimOfHospital() {
        return hospitalOperatingRoomBenefitClaimOfHospital;
    }

    public void setHospitalOperatingRoomBenefitClaimOfHospital(
            double hospitalOperatingRoomBenefitClaimOfHospital) {
        this.hospitalOperatingRoomBenefitClaimOfHospital = hospitalOperatingRoomBenefitClaimOfHospital;
    }

    public double getHospitalOperatingRoomBenefitClaimOfPatient() {
        return hospitalOperatingRoomBenefitClaimOfPatient;
    }

    public void setHospitalOperatingRoomBenefitClaimOfPatient(
            double hospitalOperatingRoomBenefitClaimOfPatient) {
        this.hospitalOperatingRoomBenefitClaimOfPatient = hospitalOperatingRoomBenefitClaimOfPatient;
    }


    public double getHospitalOutsideActualCharges() {
        return hospitalOutsideActualCharges;
    }

    public void setHospitalOutsideActualCharges(double hospitalOutsideActualCharges) {
        this.hospitalOutsideActualCharges = hospitalOutsideActualCharges;
    }

    public double getHospitalOutsideBenefitClaimOfHospital() {
        return hospitalOutsideBenefitClaimOfHospital;
    }

    public void setHospitalOutsideBenefitClaimOfHospital(
            double hospitalOutsideBenefitClaimOfHospital) {
        this.hospitalOutsideBenefitClaimOfHospital = hospitalOutsideBenefitClaimOfHospital;
    }

    public double getHospitalOutsideBenefitClaimOfPatient() {
        return hospitalOutsideBenefitClaimOfPatient;
    }

    public void setHospitalOutsideBenefitClaimOfPatient(
            double hospitalOutsideBenefitClaimOfPatient) {
        this.hospitalOutsideBenefitClaimOfPatient = hospitalOutsideBenefitClaimOfPatient;
    }


    public String getCompleteFinalDiagnosis() {
        return completeFinalDiagnosis;
    }

    public void setCompleteFinalDiagnosis(String completeFinalDiagnosis) {
        this.completeFinalDiagnosis = completeFinalDiagnosis;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getPhysicianServicesPerformed() {
        return physicianServicesPerformed;
    }

    public void setPhysicianServicesPerformed(String physicianServicesPerformed) {
        this.physicianServicesPerformed = physicianServicesPerformed;
    }

    public double getPhysicianActualCharges() {
        return physicianActualCharges;
    }

    public void setPhysicianActualCharges(double physicianActualCharges) {
        this.physicianActualCharges = physicianActualCharges;
    }

    public double getSurgeonBenefitClaim() {
        return surgeonBenefitClaim;
    }

    public void setSurgeonBenefitClaim(double surgeonBenefitClaim) {
        this.surgeonBenefitClaim = surgeonBenefitClaim;
    }

    

    public double getPhysicianPatientBenefitClaim() {
        return physicianPatientBenefitClaim;
    }

    public void setPhysicianPatientBenefitClaim(double physicianPatientBenefitClaim) {
        this.physicianPatientBenefitClaim = physicianPatientBenefitClaim;
    }

    public String getAnesthesiologistName() {
        return anesthesiologistName;
    }

    public void setAnesthesiologistName(String anesthesiologistName) {
        this.anesthesiologistName = anesthesiologistName;
    }

    public String getAnesthesiologistHealthId() {
        return anesthesiologistHealthId;
    }

    public void setAnesthesiologistHealthId(String anesthesiologistHealthId) {
        this.anesthesiologistHealthId = anesthesiologistHealthId;
    }

    public String getAnesthesiologistServicesPerformed() {
        return anesthesiologistServicesPerformed;
    }

    public void setAnesthesiologistServicesPerformed(
            String anesthesiologistServicesPerformed) {
        this.anesthesiologistServicesPerformed = anesthesiologistServicesPerformed;
    }

    public double getAnesthesiologistActualCharges() {
        return anesthesiologistActualCharges;
    }

    public void setAnesthesiologistActualCharges(
            double anesthesiologistActualCharges) {
        this.anesthesiologistActualCharges = anesthesiologistActualCharges;
    }

    public double getAnesthesiologistBenefitClaim() {
        return anesthesiologistBenefitClaim;
    }

    public void setAnesthesiologistBenefitClaim(
            double anesthesiologistBenefitClaim) {
        this.anesthesiologistBenefitClaim = anesthesiologistBenefitClaim;
    }

    public double getAnesthesiologistPatientBenefitClaim() {
        return anesthesiologistPatientBenefitClaim;
    }

    public void setAnesthesiologistPatientBenefitClaim(
            double anesthesiologistPatientBenefitClaim) {
        this.anesthesiologistPatientBenefitClaim = anesthesiologistPatientBenefitClaim;
    }

    public double getAmountDeductedFromHospitalCharges() {
        return amountDeductedFromHospitalCharges;
    }

    public void setAmountDeductedFromHospitalCharges(
            double amountDeductedFromHospitalCharges) {
        this.amountDeductedFromHospitalCharges = amountDeductedFromHospitalCharges;
    }

    public double getAmountDeductedFromProfessionalCharges() {
        return amountDeductedFromProfessionalCharges;
    }

    public void setAmountDeductedFromProfessionalCharges(
            double amountDeductedFromProfessionalCharges) {
        this.amountDeductedFromProfessionalCharges = amountDeductedFromProfessionalCharges;
    }

    public double getAmountPaidForMedicine() {
        return amountPaidForMedicine;
    }

    public void setAmountPaidForMedicine(double amountPaidForMedicine) {
        this.amountPaidForMedicine = amountPaidForMedicine;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, this._Key());
    }
}
