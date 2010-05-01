/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import bean.BeanTester;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Entokwaa
 */
public class RunGenerator {
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Admission");
        list.add("Allergy");
        list.add("Appointment");
        list.add("Blood");
        list.add("Diagnosis");
        list.add("Dietary");
        list.add("DoctorSpecialization");
        list.add("Drug");
        list.add("Emergency");
        list.add("InPatientDailyService");
        list.add("Insurance");
        list.add("LaboratoryStaff");
        list.add("LaboratoryTest");
        list.add("MedicineBrand");
        list.add("Nurse");
        list.add("OutPatient");
        list.add("Patient");
        list.add("Patient2DEcho");
        list.add("PatientAllergy");
        list.add("PatientBillingItem");
        list.add("PatientCCTA");
        list.add("PatientCTScan");
        list.add("PatientDoctorRound");
        list.add("PatientFee");
        list.add("PatientHematology");
        list.add("PatientImmunization");
        list.add("PatientInsurance");
        list.add("PatientLaboratory");
        list.add("PatientMRI");
        list.add("PatientMedicalRecord");
        list.add("PatientMedication");
        list.add("PatientNurseNote");
        list.add("PatientPackage");
        list.add("PatientPackageHistory");
        list.add("PatientPet");
        list.add("PatientRadiology");
        list.add("PatientRenalScan");
        list.add("PatientSputum");
        list.add("PatientTransaction");
        list.add("PatientUltrasound");
        list.add("PatientUltrasoundOb");
        list.add("PatientUrinalysis");
        list.add("PatientXRay");
        list.add("PharmacyInventoryDelivery");
        list.add("PharmacyInventoryExpired");
        list.add("PharmacyInventoryStocks");
        list.add("Physician");
        list.add("Room");
        list.add("RoomManagement");
        list.add("VaccineType");
        BeanTester.displayFields(list);
    }
}
